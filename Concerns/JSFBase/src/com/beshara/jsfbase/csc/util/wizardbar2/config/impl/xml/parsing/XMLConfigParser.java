/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.parsing;

import com.beshara.jsfbase.csc.util.wizardbar2.model.NavBar;
import com.beshara.jsfbase.csc.util.wizardbar2.model.NavButton;
import com.beshara.jsfbase.csc.util.wizardbar2.model.Step;
import com.beshara.jsfbase.csc.util.wizardbar2.model.Wizard;
import com.beshara.jsfbase.csc.util.wizardbar2.model.WizardBarConfig;

import java.io.BufferedReader;
import java.io.FileReader;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 *
 * @author A.hamed
 */
public class XMLConfigParser extends ConfigParser {

    protected String configFileLocation;
    protected Stack parentElements;
    protected Wizard currentelyParsedWizard;

    public XMLConfigParser() {

        throw new UnsupportedOperationException("Use XMLConfigParser(String configFileLocation) istead");

    }

    public XMLConfigParser(String configFileLocation) {

        this.configFileLocation = configFileLocation;

    }

    public WizardBarConfig parseConfigurations() {

        WizardBarConfig wizardBarConfig = new WizardBarConfig();
        try {

            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(new WizardConfigContentHandler(wizardBarConfig));
            reader.setFeature("http://xml.org/sax/features/validation", true);
            BufferedReader buffReader = 
                new BufferedReader(new FileReader(configFileLocation));
            reader.parse(new InputSource(buffReader));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wizardBarConfig;
    }

    public class WizardConfigContentHandler extends DefaultHandler {

        private WizardBarConfig wizardBarConfig;

        public WizardConfigContentHandler() {
        }

        public WizardConfigContentHandler(WizardBarConfig wizardBarConfig) {
            this.wizardBarConfig = wizardBarConfig;
        }

        @Override
        public void endElement(String uri, String localName, 
                               String qName) throws SAXException {

            if (qName.equals("wizards")) {

                System.out.println("End parsing wizards root element attributes :::::");


            }

            if (qName.equals("wizard")) {

                System.out.println("End parsing wizard  element attributes :::::");


            }

            if (qName.equals("step")) {

                System.out.println("End parsing step  element attributes :::::");


            }

            if (qName.equals("navBar")) {

                System.out.println("End parsing navBar  element attributes :::::");


            }

            if (qName.equals("button")) {

                System.out.println("End parsing button  element attributes :::::");


            }

        }

        @Override
        public void startElement(String uri, String localName, String qName, 
                                 Attributes attributes) throws SAXException {

            if (qName.equals("wizards")) {

                System.out.println("Start parsing wizards root element attributes :::::");

                for (int attributeIndex = 0; 
                     attributeIndex < attributes.getLength(); 
                     attributeIndex++) {

                    System.out.println(attributes.getQName(attributeIndex) + 
                                       " : value= " + 
                                       attributes.getValue(attributeIndex));
                    setSimpleAttribute(attributes.getQName(attributeIndex), 
                                       attributes.getValue(attributeIndex), 
                                       String.class, 
                                       WizardBarConfig.class.getName(), 
                                       wizardBarConfig);

                }

            }


            if (qName.equals("wizard")) {

                System.out.println("Start parsing wizard  element attributes :::::");
                Wizard wizard = new Wizard();

                for (int attributeIndex = 0; 
                     attributeIndex < attributes.getLength(); 
                     attributeIndex++) {

                    System.out.println(attributes.getQName(attributeIndex) + 
                                       " : value= " + 
                                       attributes.getValue(attributeIndex));
                    setSimpleAttribute(attributes.getQName(attributeIndex), 
                                       attributes.getValue(attributeIndex), 
                                       String.class, Wizard.class.getName(), 
                                       wizard);

                }
                if (wizardBarConfig.getWizardMap() == null) {
                    wizardBarConfig.setWizardMap(new HashMap<String, Wizard>());
                }
                wizardBarConfig.getWizardMap().put(wizard.getId(), wizard);
                currentelyParsedWizard = wizard;

            }

            if (qName.equals("step")) {

                System.out.println("Start parsing step  element attributes :::::");
                Step step = new Step();
                for (int attributeIndex = 0; 
                     attributeIndex < attributes.getLength(); 
                     attributeIndex++) {

                    System.out.println(attributes.getQName(attributeIndex) + 
                                       " : value= " + 
                                       attributes.getValue(attributeIndex));
                    setSimpleAttribute(attributes.getQName(attributeIndex), 
                                       attributes.getValue(attributeIndex), 
                                       String.class, Step.class.getName(), 
                                       step);

                }

                if (currentelyParsedWizard.getStepMap() == null) {
                    currentelyParsedWizard.setStepMap(new LinkedHashMap<String, Step>());
                }

                currentelyParsedWizard.getStepMap().put(step.getId(), step);

            }
            if (qName.equals("navBar")) {

                System.out.println("Start parsing navBar  element attributes :::::");
                NavBar navBar = new NavBar();
                for (int attributeIndex = 0; 
                     attributeIndex < attributes.getLength(); 
                     attributeIndex++) {

                    System.out.println(attributes.getQName(attributeIndex) + 
                                       " : value= " + 
                                       attributes.getValue(attributeIndex));
                    setSimpleAttribute(attributes.getQName(attributeIndex), 
                                       attributes.getValue(attributeIndex), 
                                       String.class, NavBar.class.getName(), 
                                       navBar);

                }

                currentelyParsedWizard.setNavBar(navBar);

            }

            if (qName.equals("button")) {

                System.out.println("Start parsing button  element attributes :::::");
                NavButton navButton = new NavButton();
                for (int attributeIndex = 0; 
                     attributeIndex < attributes.getLength(); 
                     attributeIndex++) {

                    System.out.println(attributes.getQName(attributeIndex) + 
                                       " : value= " + 
                                       attributes.getValue(attributeIndex));
                    setSimpleAttribute(attributes.getQName(attributeIndex), 
                                       attributes.getValue(attributeIndex), 
                                       String.class, NavButton.class.getName(), 
                                       navButton);

                }

                if (currentelyParsedWizard.getNavBar().getNavBarButtons() == 
                    null) {

                    currentelyParsedWizard.getNavBar().setNavBarButtons(new LinkedHashMap<String, NavButton>());
                }
                currentelyParsedWizard.getNavBar().getNavBarButtons().put(navButton.getId(), 
                                                                          navButton);

            }


        }

        //        /**
        //         * @return the wizardBarConfig
        //         */
        //        public WizardBarConfig getWizardBarConfig() {
        //            return wizardBarConfig;
        //        }

        private void setSimpleAttribute(String attributeName, 
                                        String attributeValue, 
                                        Class attributeType, String className, 
                                        Object instance) {

            try {
                Class cls = Class.forName(className);
                Class partypes[] = new Class[1];
                partypes[0] = attributeType;
                attributeName = 
                        attributeName.replaceFirst(attributeName.substring(0, 
                                                                           1), 
                                                   attributeName.substring(0, 
                                                                           1).toUpperCase());
                Method meth = cls.getMethod("set" + attributeName, partypes);
                Object arglist[] = new Object[1];
                arglist[0] = attributeValue;
                meth.invoke(instance, arglist);
            } catch (Throwable e) {
                System.err.println(e);
            }


        }
    }
}
