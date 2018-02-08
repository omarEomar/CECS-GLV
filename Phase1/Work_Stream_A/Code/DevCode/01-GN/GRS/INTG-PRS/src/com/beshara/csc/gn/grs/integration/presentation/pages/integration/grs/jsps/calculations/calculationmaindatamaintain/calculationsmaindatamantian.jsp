<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
     <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
     <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="resourcesBundle"/>
     <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions" var="globalexceptions"/>
     <h:form id="myForm" binding="#{calculationsMainDataMaintainBean.frm}">
          <t:aliasBean alias="#{pageBeanName}" value="#{calculationsMaintainBean}">
               <t:aliasBean alias="#{detailBeanName}" value="#{calculationsMainDataMaintainBean}">
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentStep}"/>
                    <t:saveState value="#{pageBeanName.maintainMode}"/>
                    <t:saveState value="#{pageBeanName.renderSave}"/>
                    <t:saveState value="#{pageBeanName.renderFinish}"/>
                    <t:saveState value="#{pageBeanName.pageDTO.calculationDetailsDTOList}"/>
                    <t:saveState value="#{calculationsMaintainBean.listBeanObject}"/>
                    
                   
                    
                    <t:saveState value="#{detailBeanName.appModulesList}"/>
                    <t:saveState value="#{detailBeanName.toBeAddedModulesList}"/>
                    <t:saveState value="#{detailBeanName.toBeRemovedModulesList}"/>
                    <t:saveState value="#{detailBeanName.availableModulesList}"/>
                    <t:saveState value="#{detailBeanName.allAvailableModulesList}"/>
                    <t:saveState value="#{detailBeanName.highlightedDTOS}"/>
                    <t:saveState value="#{detailBeanName.searchNameCritria}"/>
                    <t:saveState value="#{pageBeanName.hmObjects}"/>
                    <t:saveState value="#{pageBeanName.beanName}"/>
                    <t:saveState value="#{pageBeanName.backAction}"/>
                    <t:saveState value="#{pageBeanName.navigationCase}"/>
                    
                    <t:saveState value="#{calculationsMaintainBean.backNavCase}"/>
                    <t:saveState value="#{calculationsMaintainBean.backMethod}"/>
                    <t:saveState value="#{calculationsMaintainBean.savedDataObj}"/>
                    <t:saveState value="#{calculationsMaintainBean.tabricSerialRef}"/>
                    <t:saveState value="#{calculationsMaintainBean.elmGuideCode}"/>
                    <t:saveState value="#{calculationsMaintainBean.hideAppModulesList}"/>
                    <t:saveState value="#{calculationsMaintainBean.joinedBefore}"/>
                    <t:saveState value="#{calculationsMaintainBean.tableName}"/>
                    <t:saveState value="#{calculationsMaintainBean.checkBenefitRewardCondition}"/>
                    <t:saveState value="#{calculationsMaintainBean.loggedInMinistry}"/>
                    <t:saveState value="#{calculationsMaintainBean.benefitRewardConditionCode}"/>
                    <t:saveState value="#{calculationsMaintainBean.benefitRewardCode}"/>
                    <t:saveState value="#{calculationsMaintainBean.newCalcName}"/>
                
                
                     
                     
                <t:saveState value="#{calculationsDetailBean.currentDetails}"/>
                <t:saveState value="#{calculationsDetailBean.currentDisplayDetails}"/>
                <t:saveState value="#{calculationsDetailBean.selectedCurrentDetails}"/>
                <t:saveState value="#{calculationsDetailBean.currentDataTable}"/>
                <t:saveState value="#{calculationsDetailBean.linesDTODetails}"/>
                <t:saveState value="#{calculationsDetailBean.rowNo}"/>
                <t:saveState value="#{calculationsDetailBean.parametersList}"/>
        
                    
                    <tiles:insert definition="calculationmainpage.page" flush="false">
                         
                         
                    </tiles:insert>
               </t:aliasBean>
          </t:aliasBean>
          <c2:scriptGenerator form="myForm"/>
     </h:form>
</f:view>
