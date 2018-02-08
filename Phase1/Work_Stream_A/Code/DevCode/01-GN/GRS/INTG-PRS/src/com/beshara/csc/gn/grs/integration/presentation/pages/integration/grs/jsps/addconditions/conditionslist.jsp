<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">
   <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
   <f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="grsResources"/>
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions"
                  var="globalexceptions"/>
   <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration"
                  var="integrateResources"/>
   <h:form id="myForm" binding="#{conditionListBean.frm}">
   <t:aliasBean alias="#{cdIntegrationBeanName}" value="#{conditionListBean.cdIntegrationBean}">
      <t:aliasBean alias="#{pageBeanName}" value="#{conditionListBean}">
       
         <tiles:insert definition="conditionlist.page" flush="false">
            <t:saveState value="#{pageBeanName.renderedBack}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.testConditionCode}"/>
            <t:saveState value="#{pageBeanName.testCivilId}"/>
            <t:saveState value="#{pageBeanName.testConditionResult}"/>
            <t:saveState value="#{pageBeanName.testConditionMessage}"/>
            <t:saveState value="#{pageBeanName.employeeName}"/>
            <t:saveState value="#{pageBeanName.selectedParameterType}"/>
            
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <t:saveState value="#{pageBeanName.viewInCenter}"/>
            
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.searchConditionsDTO}"/>
            <t:saveState value="#{pageBeanName.selectedOperationId}"/>
            <t:saveState value="#{pageBeanName.conditionStatus}"/>
            <t:saveState value="#{pageBeanName.parameterCode}"/>
            <t:saveState value="#{pageBeanName.linevalue}"/>
            <t:saveState value="#{pageBeanName.lineCodeText}"/>

            <t:saveState value="#{pageBeanName.operationsList}"/>
            <t:saveState value="#{pageBeanName.parametersList}"/>
            <t:saveState value="#{pageBeanName.allParametersList}"/>
            <t:saveState value="#{pageBeanName.lineValuesList}"/>
            <t:saveState value="#{pageBeanName.linesList}"/>
            
            <t:saveState value="#{pageBeanName.selectedConditionName}"/>
            <t:saveState value="#{pageBeanName.selectedConditionDescription}"/>
             <t:saveState value="#{pageBeanName.appModulesList}"/>
             <t:saveState value="#{pageBeanName.moduleCodeText}"/>
            
          
  <t:saveState value="#{pageBeanName.cdIntegrationBean}"/>
            <t:panelGroup forceId="true" id="grsIntgHiddenValues">
                <t:inputHidden forceId="true" id="fullURLId" value="#{pageBeanName.fullURLId}"/>
                <t:inputHidden forceId="true" id="grsLineWindowTitleId" value="aaaaa"/>
            </t:panelGroup>
            
         </tiles:insert>
        </t:aliasBean>  
      </t:aliasBean>
    <c2:scriptGenerator form="myForm"/>
   

        <script type="text/javascript"> 
            /*foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){        
                if(document.getElementById('addButton') != null){            
                    document.getElementById('addButton').focus();
                }
            }
            */
             function openGrsIntgConditionDetailsWindow() {
              var moduleName ='${shared_util.contextPath}';
              openGrsIntgNewWindowNew('fullURLId', moduleName ,'grsLineWindowTitleId');
              }
        
            function setFocusAtMySearchDiv(){
                copyDropdownIntoInputtext('conditionStatus', 'conditionStatusId');
                copyDropdownIntoInputtext('operationsList', 'operationsListId');
                copyDropdownIntoInputtext('parameters', 'parametersId');
                copyDropdownIntoInputtext('values', 'valuesId');
                copyDropdownIntoInputtext('lineList', 'lineListId');
                copyDropdownIntoInputtext('modulesList', 'modulesListId');
                
                try{
                setFocusOnlyOnElement('conditionCodeID'); 
            }
              catch (e) {
            }
            
        }  
            
            
        </script>
    </h:form>
</f:view>
