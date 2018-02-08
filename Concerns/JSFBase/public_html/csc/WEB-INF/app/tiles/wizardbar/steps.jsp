<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.faces.context.FacesContext"%>
<%@ page import="com.beshara.jsfbase.csc.util.wizardbar2.beans.WizardBarBean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<tiles:importAttribute scope="request"/>
<%

//String wizardCofigId = (String) request.getAttribute("wizardbarid");
//WizardBarBean bar=(WizardBarBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{wizardBarBean}").getValue(FacesContext.getCurrentInstance());
//if(bar.getWizardId()==null ){
//bar.setWizardId(wizardCofigId);
//bar.initializeWizard();
//}
%>

<t:panelGrid  forceId="true" id="stepsPanel"  binding="#{wizardBarBean.stepsPanelGrid}" />