<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 

 <h:form id="myForm"  binding="#{regulationPublishMaintainBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}" id="alias1">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationPublishMaintainBean}" id="alias2">
    

    <t:saveState value="#{pageBeanName.pageDTO}" />
    <t:saveState value="#{pageBeanName.nextNavigationCase}" />
    <t:saveState value="#{pageBeanName.previousNavigationCase}" />
    <t:saveState value="#{pageBeanName.finishNavigationCase}" />
    <t:saveState value="#{pageBeanName.currentNavigationCase}" />
    <t:saveState value="#{pageBeanName.currentStep}" />
    <t:saveState value="#{pageBeanName.maintainMode}"/>
    <t:saveState value="#{pageBeanName.renderSave}" />
    <t:saveState value="#{pageBeanName.renderFinish}" />
    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
    
    <t:saveState value="#{detailBeanName.currentDetails}" />
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" />    
    <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}"/>    
    <t:saveState value="#{detailBeanName.editDTO}"/>    
    <t:saveState value="#{detailBeanName.index}"/>   
    <t:saveState value="#{detailBeanName.dataTableValue}"/>   
    <t:saveState value="#{detailBeanName.preEditDTO}"/>   
    
    <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
    <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
    <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
    <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
   
    <tiles:insert definition="regulationpublishmaintainedit.page" flush="false">
    
        
        
        
        </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
  <t:panelGroup forceId="true" id="scriptPanel">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
  <script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('maintain_publicationFlag');
        }
        
        function setFocusAtMyAddDiv(){
            setFocusOnlyOnElement('checkAllAdd');
        }
        
        function setFocusAtMyCustom1Div(){
            setFocusOnlyOnElement('clndr_maintain_publish_date');
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
    </script>
 </h:form>
</f:view>