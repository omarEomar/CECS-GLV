<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions"
                  var="globalexceptions"/>
    <h:form id="myForm" binding="#{calculationsDetailBean.frm}" >
        <t:aliasBean alias="#{pageBeanName}" value="#{calculationsMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{calculationsDetailBean}">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{calculationsMaintainBean.listBeanObject}"/>
                <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                <t:saveState value="#{pageBeanName.currentStep}"/>
                <t:saveState value="#{pageBeanName.maintainMode}"/>
                <t:saveState value="#{pageBeanName.renderSave}"/>
                <t:saveState value="#{pageBeanName.renderFinish}"/>
                <t:saveState value="#{pageBeanName.hasActiveOperations}"/>
                <t:saveState value="#{pageBeanName.activeOperationsList}"/>
                <t:saveState value="#{pageBeanName.availableOperationsList}"/>
                <t:saveState value="#{pageBeanName.toBeJoinedOperationsList}"/>
                <t:saveState value="#{pageBeanName.toBeAddedOperationsList}"/>
                <t:saveState value="#{pageBeanName.toBeRemovedOperationsList}"/>
                <t:saveState value="#{detailBeanName.joinConditions}"/>
                <%--<t:saveState value="#{detailBeanName.availableDetails}"/>--%>
                <t:saveState value="#{detailBeanName.currentDetails}"/>
                <t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
                <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
                <t:saveState value="#{detailBeanName.currentDataTable}"/>
                <t:saveState value="#{detailBeanName.linesDTODetails}"/>
                <%--<t:saveState value="#{detailBeanName.lineValueslist}"/>--%>
                <%--<t:saveState value="#{conditionListBean.viewInCenter}"/>--%>
                <%--<t:saveState value="#{conditionListBean.selectedParameterType}"/>--%>
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
                    
                <t:saveState value="#{detailBeanName.rowNo}"/>
                <t:saveState value="#{detailBeanName.parametersList}"/>
                 <t:saveState value="#{calculationsMainDataMaintainBean.appModulesList}"/>
                <t:saveState value="#{calculationsMainDataMaintainBean.toBeAddedModulesList}"/>
                    <t:saveState value="#{calculationsMainDataMaintainBean.toBeRemovedModulesList}"/>
                    <t:saveState value="#{calculationsMainDataMaintainBean.availableModulesList}"/>
                    <t:saveState value="#{calculationsMainDataMaintainBean.allAvailableModulesList}"/>
        
                <tiles:insert definition="calculationsDetails.page" flush="false">
                    <t:messages/>
                    <%-- t:saveState value="#{detailBeanName.condition}"/--%>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <c2:scriptGenerator form="myForm"/>
    </h:form>
    <script type="text/javascript">
  foucsAddbuttonOnList();

      function foucsAddbuttonOnList() {
          if (document.getElementById('addButton') != null) {
              document.getElementById('addButton').focus();
          }
      }
    </script>
</f:view>
