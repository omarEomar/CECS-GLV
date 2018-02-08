<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<t:panelGrid columns="2" align="center" width="100%" rowClasses="row02,row01" cellpadding="5" cellspacing="0" columnClasses="col3 nowrap_txt" >
   <t:outputText value="#{globalResources.Code}" styleClass="lable01" rendered="#{pageBeanName.maintainMode == 1}"/>
   <t:inputText value="#{pageBeanName.pageDTO.code.key}" styleClass="textboxlarge" rendered="#{pageBeanName.maintainMode == 1}" disabled="true"/>
   <t:outputText value="#{resourcesBundle.conditionName}" styleClass="lable01"/>
   <t:panelGroup>
        <t:inputText value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge" id="conditionName" forceId="true" disabled="#{pageBeanName.maintainMode==2}"/>
        <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}"/>
        <f:verbatim > <br/> </f:verbatim>
        <c2:requiredFieldValidator active="#{conditionsMaintainBean.maintainMode!=2}" componentToValidate="conditionName" errorMessage="#{globalResources.missingField}" display="dynamic" uniqueId="lineDTOBox"/>
   </t:panelGroup>
   <t:outputText value="#{resourcesBundle.conditionDesc}" style="vertical-align: top;"/>
   <t:inputTextarea value="#{pageBeanName.pageDTO.conditionDesc}" cols="100" rows="5" style="width: 600px;" disabled="#{pageBeanName.maintainMode==2}"/>
</t:panelGrid>


<script type="text/javascript">   
 setFocusAtMyFirstElement();
     
   function setFocusAtMyFirstElement(){
        if(document.getElementById('conditionName') !=null)
                setFocusOnlyOnElement('conditionName').focus();
     }   
 </script>
