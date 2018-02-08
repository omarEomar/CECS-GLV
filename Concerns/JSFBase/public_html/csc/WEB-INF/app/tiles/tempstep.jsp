<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.faces.context.FacesContext"%>
<%@ page import="com.beshara.jsfbase.csc.util.wizardbar.WizardBar"%>
<tiles:importAttribute scope="request"/>

<%

String wizardCofigId = (String) request.getAttribute("wizardId");
WizardBar bar=(WizardBar)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{wizardbar}").getValue(FacesContext.getCurrentInstance());
if(bar.getConfigurationId()==null || bar.getConfigurationId().equals("")||(!bar.getConfigurationId().equals(wizardCofigId))){

bar.setWizardSteps(new HashMap());
bar.setCurrentStep(null);
bar.setStepsList(new ArrayList());


bar.setConfigurationId(wizardCofigId);
bar.getBarConfiguration();
System.out.println("parsed bar");


}
%>
   <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global"   var="globalResources"/>

<f:verbatim>
                            	
                                <!------------the wizardbar code--------------------->
                              
              <style type="text/css">
<!--
.btn {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #0000FF;
	text-decoration: underline;
	background-color: #FFFFFF;
	border: 1px none #CCCCCC;
}
-->
</style>
<span id="wizardbar">
 </f:verbatim> 
   <t:panelGrid dir="#{shared_util.pageDirection}" columns="1"  binding="#{wizardbar.panelGrid}" border="0"  cellpadding="0" cellspacing="0"/>
 <f:verbatim>
 </span>
  </f:verbatim>
<%--<script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/js/WizardBar.jsf"></script>--%>

 <t:saveState value="#{wizardbar.wizardSteps}"/>
 <t:saveState value="#{wizardbar.configurationId}"/>
                            
