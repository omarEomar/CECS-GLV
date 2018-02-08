package com.beshara.jsfbase.csc.util.wizardbar;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class WizardConfigurator extends DefaultHandler {
    private String locale = "ar";

    private Map wizardSteps;
    private String configuratorId;
    private String currentNodeConfiguratorId;
    private boolean currentlyParsed = false;
    // controlTYPE 0 COMMANDBUTTON 1 COMMANDlINK  2 HTMLlINK
    //STATUS     0 VISITED 1 NONVISITED 2 MODIFIED
    //NAVIGATIONSTATUS 0 ALLOWED(ENABLED) 1 NOT ALLOWED(DISABLED)

    public WizardConfigurator() {
        //getting the current application locale
        locale =
                (String)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util.locale}").getValue(FacesContext.getCurrentInstance());

    }

    public void parseSteps() {

        String path =
            ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("//WEB-INF//module//config//wizardbar-conf.xml");
        //XMLReader reader;
        try {

            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(this);
            reader.setFeature("http://xml.org/sax/features/validation", true);
            BufferedReader buffReader = new BufferedReader(new FileReader(path));
            reader.parse(new InputSource(buffReader));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WizardConfigurator(Map _wizardSteps, String _configuratorId) {
        this.setWizardSteps(_wizardSteps);
        this.setConfiguratorId(_configuratorId);
    }

    public void setWizardSteps(Map wizardSteps) {
        this.wizardSteps = wizardSteps;
    }

    public Map getWizardSteps() {
        return wizardSteps;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("wizard") && this.isCurrentlyParsed()) {
            this.setCurrentlyParsed(false);
        }

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("wizard")) {
            String confId = attributes.getValue("configurationId");
            if (this.getConfiguratorId().equalsIgnoreCase(confId)) {
                this.setCurrentlyParsed(true);
                this.setCurrentNodeConfiguratorId(confId);
            }
        } else {
            if (qName.equals("step") && this.isCurrentlyParsed()) {
                boolean rendered = true;
                
                String renderedStr = attributes.getValue("rendered");
                if(renderedStr != null && !renderedStr.equals("")) {
                    FacesContext ctx = FacesContext.getCurrentInstance();
                    rendered = ((Boolean)ctx.getApplication().createValueBinding(renderedStr).getValue(ctx)).booleanValue();
                }
                
                if (rendered) {
                    WizardStep step = new WizardStep();
                    String bundle = attributes.getValue("bundle");
                    String bundleKey = attributes.getValue("bundleKey");
                    String title =
                        getBundleValue(bundleKey, bundle); //get it using the bundle and bundleKey attributes
                    String mapKey = attributes.getValue("mapKey");
                    String navigationCase = attributes.getValue("navigationCase");
                    String mandatory = attributes.getValue("mandatory");

                    if (mandatory != null)
                        step.setMandatory(Boolean.valueOf(mandatory).booleanValue());
                    step.setTitle(title);
                    step.setMapKey(mapKey);
                    step.setNavigationCase(navigationCase);
                    step.setOrder(Integer.valueOf(attributes.getValue("order")));
                    step.setAction(attributes.getValue("action"));
                    step.setJsValidation(attributes.getValue("jsValidation"));
                    step.setControlType(Integer.valueOf(attributes.getValue("controlType")));
                    step.setStatus(Integer.valueOf(attributes.getValue("status")));
                    step.setNavigateStatus(Integer.valueOf(attributes.getValue("navigateStatus")));
                    step.setStepBeanName(attributes.getValue("stepBeanName"));
                    System.out.println(step.getTitle());
                    System.out.println(step.getControlType());
                    String relevant = attributes.getValue("relevant");
                    step.setRelevantSteps(this.getRelevant(relevant));
                    step.setDependancyMap(this.createDepencancyMap(step.getRelevantSteps()));
                    this.getWizardSteps().put(step.getMapKey(), step);
                }
            }
        }
    }

    private String getBundleValue(String key, String bundleName) {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(bundleName, new Locale(locale));
            return bundle.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public void setConfiguratorId(String configuratorId) {
        this.configuratorId = configuratorId;
    }

    public String getConfiguratorId() {
        return configuratorId;
    }

    public void setCurrentNodeConfiguratorId(String currentNodeConfiguratorId) {
        this.currentNodeConfiguratorId = currentNodeConfiguratorId;
    }

    public String getCurrentNodeConfiguratorId() {
        return currentNodeConfiguratorId;
    }

    public void setCurrentlyParsed(boolean currentlyParsed) {
        this.currentlyParsed = currentlyParsed;
    }

    public boolean isCurrentlyParsed() {
        return currentlyParsed;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public List getRelevant(String relevantString) {
        List relevant = new ArrayList();
        if (relevantString != null) {
            String[] relevants = relevantString.split(",");
            if (relevants != null) {
                for (int i = 0; i < relevants.length; i++) {
                    relevant.add(relevants[i]);

                }

            }
        }
        return relevant;

    }

    public Map createDepencancyMap(List<String> relevants) {
        Map dependancyMap = new HashMap();
        if (relevants != null)
            for (String relevant : relevants) {
                dependancyMap.put(relevant, false);

            }
        return dependancyMap;
    }

}
