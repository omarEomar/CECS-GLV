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
    <h:form id="myForm" binding="#{conditionIntgLineDetailBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{conditionsIntgMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{conditionIntgLineDetailBean}">
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.listBeanObject}"/>
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
                <t:saveState value="#{detailBeanName.availableDetails}"/>
                <t:saveState value="#{detailBeanName.currentDetails}"/>
                <t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
                <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
                <t:saveState value="#{detailBeanName.currentDataTable}"/>
                <t:saveState value="#{detailBeanName.linesDTODetails}"/>
                <t:saveState value="#{detailBeanName.lineValueslist}"/>
                
                <t:saveState value="#{pageBeanName.hmObjects}"/>
                <t:saveState value="#{pageBeanName.beanName}"/>
                <t:saveState value="#{pageBeanName.backAction}"/>
                <t:saveState value="#{pageBeanName.navigationCase}"/>
                
                <t:saveState value="#{conditionsIntgMaintainBean.targetForJoinConditionDTO}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.allowMultiCondition}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.transactionName}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.restoreMethod}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.objectCode}"/>
                
                <tiles:insert definition="conditionsIntgLineDetails.page" flush="false">
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
