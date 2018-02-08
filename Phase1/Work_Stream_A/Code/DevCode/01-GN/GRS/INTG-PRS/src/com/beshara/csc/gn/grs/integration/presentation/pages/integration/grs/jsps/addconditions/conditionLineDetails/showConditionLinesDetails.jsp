<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    
    
    
    
    <!-- End Paging -->
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="grsResources"/>
    <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions"
                  var="globalexceptions"/>
    <h:form id="myForm" binding="#{linesEditBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{linesEditBean}">
            <tiles:insert definition="showConditionLinesDetails.page" flush="false"></tiles:insert>
            <t:saveState value="#{conditionsIntgMaintainBean.listBeanObject}"/> 
                        <t:saveState value="#{conditionIntgLineSub.selectedRow}"/>
                        <t:saveState value="#{wizardbar.wizardSteps}"/>
                        <t:saveState value="#{wizardbar.currentStep}"/>
                        <t:saveState value="#{wizardbar.configurationId}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.pageDTO}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.nextNavigationCase}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.previousNavigationCase}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.finishNavigationCase}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.currentNavigationCase}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.currentStep}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.maintainMode}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.renderSave}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.renderFinish}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.availableDetails}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.currentDetails}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.joinConditions}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.currentDataTable}"/>
                        <t:saveState value="#{conditionIntgLineSub.rowNo}"/>
                        <t:saveState value="#{conditionIntgLineSub.lineParamter}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.currentDisplayDetails}"/>
                        <%-- t:saveState value="#{detailBeanName.condition}"/--%>
                        <t:saveState value="#{conditionIntgLineDetailBean.mainLinesDetails}"/>
                        <t:saveState value="#{conditionIntgLineDetailBean.alternativeLinesDetails}"/>
                        <t:saveState value="#{conditionListBean.selectedParameterType}"/>
                        <t:saveState value="#{wizardbar.wizardSteps}"/>
                        <t:saveState value="#{wizardbar.configurationId}"/>
                        <t:saveState value="#{wizardbar.currentStep}"/>
        </t:aliasBean>
     
       
    </h:form>
</f:view>