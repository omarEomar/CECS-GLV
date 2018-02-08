<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                  var="grsIntgResources"/>
    <h:form id="myForm" binding="#{grsExceptionHelperBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{grsExceptionHelperBean}">
                <t:aliasBean alias="#{jcHelperBeanName}" value="#{grsExceptionHelperBean.jcHelperBean}">
        
           <t:saveState value="#{grsExceptionHelperBean.selectedAppModuleKey}" />
           <t:saveState value="#{grsExceptionHelperBean.selectedTransactionKey}" />
           <t:saveState value="#{grsExceptionHelperBean.selectedConditionsKey}" />
           <t:saveState value="#{grsExceptionHelperBean.selectedRadioValue}" />
           <t:saveState value="#{grsExceptionHelperBean.appModuleList}" />
           <t:saveState value="#{grsExceptionHelperBean.transactionList}" />
           <t:saveState value="#{grsExceptionHelperBean.conditionsList}" />
           <t:saveState value="#{grsExceptionHelperBean.searchCriteriaDTO}" />
           <t:saveState value="#{grsExceptionHelperBean.linesButtonsEnabled}" />
           <t:saveState value="#{grsExceptionHelperBean.transactionsButtonsEnabled}" />
           <t:saveState value="#{grsExceptionHelperBean.conditionsButtonsEnabled}" />
           <t:saveState value="#{grsExceptionHelperBean.searchTransactions}" />
           <t:saveState value="#{grsExceptionHelperBean.searchConditions}" />
           <t:saveState value="#{pageBeanName.toBeAddedLinesList}"/>
           <t:saveState value="#{pageBeanName.jcHelperBean}"/>
            <t:saveState value="#{grsExceptionHelperBean.temp}" />

    <t:saveState value="#{pageBeanName.availableLinesList}"/>
    <t:saveState value="#{pageBeanName.toBeRemovedLinesList}"/>
    <t:saveState value="#{pageBeanName.addedLinesList}"/>
    <t:saveState value="#{pageBeanName.showLines}"/>
    <t:saveState value="#{pageBeanName.linesButtonsEnabled}"/>
    <t:saveState value="#{pageBeanName.loadLinesList}"/>
    <t:saveState value="#{pageBeanName.enableViewButton}"/>
    <t:saveState value="#{pageBeanName.viewDetailsPageURL}"/>
    <t:saveState value="#{pageBeanName.viewMode}"/>
<t:saveState value="#{grsExceptionHelperBean.myTableData}"/>

                <tiles:insert definition="grsExceptionIntegration.page" flush="false">
                    <tiles:put name="titlepage" type="string" value="exceptionTitle"/>
                </tiles:insert>
        </t:aliasBean></t:aliasBean>
        
          <script type="text/javascript"> 
            
             function openGrsIntgConditionDetailsWindow() { 
               var moduleName ='${shared_util.contextPath}';
               openGrsIntgNewWindowNew('fullURLId' ,moduleName, 'grsLineWindowTitleId');
              }
              
              </script>
    </h:form>
    <t:panelGroup forceId="true" id="scriptGeneratorPanelID">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
   
</f:view>
