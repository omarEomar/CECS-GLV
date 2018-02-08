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
    <h:form id="myForm" binding="#{conditionIntgResultsBean.frm}">
        <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{conditionIntgResultsBean.wcIntegrationBean}">
        <t:aliasBean alias="#{pageBeanName}" value="#{conditionIntgResultsBean}">
            <t:aliasBean alias="#{contianerBean}" value="#{conditionsIntgMaintainBean}">
                <t:saveState value="#{pageBeanName.callerBeanObject}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.searchTypeFlag}"/>
                <t:saveState value="#{pageBeanName.resultTableStyleClass}"/>
                <t:saveState value="#{pageBeanName.civilId}"/>
                <t:saveState value="#{pageBeanName.conditionsList}"/>
                <t:saveState value="#{pageBeanName.selectedCategoryId}"/>
                <t:saveState value="#{pageBeanName.categoriesList}"/>
                <t:saveState value="#{pageBeanName.selectedMinistryId}"/>
                <t:saveState value="#{pageBeanName.ministriesList}"/>
                <t:saveState value="#{pageBeanName.selectedWorkCenterId}"/>
                <t:saveState value="#{pageBeanName.selectedWorkCenterName}"/>
                <t:saveState value="#{pageBeanName.selectedCaderId}"/>
                <t:saveState value="#{pageBeanName.caderList}"/>
                <t:saveState value="#{pageBeanName.selectedGroupId}"/>
                <t:saveState value="#{pageBeanName.groupsList}"/>
                <t:saveState value="#{pageBeanName.selectedDegreeId}"/>
                <t:saveState value="#{pageBeanName.degreesList}"/>
                <t:saveState value="#{pageBeanName.selectedRaiseId}"/>
                <t:saveState value="#{pageBeanName.raisesList}"/>
                <t:saveState value="#{pageBeanName.selectedBgtProgramId}"/>
                <t:saveState value="#{pageBeanName.bgtProgramsList}"/>
                <t:saveState value="#{pageBeanName.selectedBgtTypeId}"/>
                <t:saveState value="#{pageBeanName.bgtTypesList}"/>
                <t:saveState value="#{pageBeanName.wcIntegrationBean}"/>
                <t:saveState value="#{pageBeanName.allPassedCond}"/>
                
                <t:saveState value="#{pageBeanName.filterBy}"/>
                <t:saveState value="#{pageBeanName.conditionName}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.targetForJoinConditionDTO}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.allowMultiCondition}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.transactionName}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.restoreMethod}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.objectCode}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.hmObjects}"/>
                        
                </t:aliasBean>
                <tiles:insert definition="conditionintgresults.page" flush="false"></tiles:insert>
            </t:aliasBean>
            <c2:scriptGenerator form="myForm"/>
        </t:aliasBean>
    </h:form>
    <script type="text/javascript">
      setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          setFocusOnlyOnElement('categoriesList');
      }
    </script>
</f:view>
