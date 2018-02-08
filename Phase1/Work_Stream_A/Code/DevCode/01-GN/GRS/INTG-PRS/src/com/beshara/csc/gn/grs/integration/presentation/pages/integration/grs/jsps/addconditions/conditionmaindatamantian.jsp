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
     <h:form id="myForm" binding="#{conditionsIntgBaseLineBeanFirst.frm}">
          <t:aliasBean alias="#{pageBeanName}" value="#{conditionsIntgMaintainBean}">
               <t:aliasBean alias="#{detailBeanName}" value="#{conditionsIntgBaseLineBeanFirst}">
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentStep}"/>
                    <t:saveState value="#{pageBeanName.maintainMode}"/>
                    <t:saveState value="#{pageBeanName.renderSave}"/>
                    <t:saveState value="#{pageBeanName.renderFinish}"/>
                    
                    <t:saveState value="#{conditionsIntgMaintainBean.listBeanObject}"/>
                    
                    <t:saveState value="#{detailBeanName.parametertype}"/>
                    <t:saveState value="#{detailBeanName.parameterTypesList}"/>
                    
                    <t:saveState value="#{detailBeanName.appModulesList}"/>
                    <t:saveState value="#{detailBeanName.toBeAddedModulesList}"/>
                    <t:saveState value="#{detailBeanName.toBeRemovedModulesList}"/>
                    <t:saveState value="#{detailBeanName.availableModulesList}"/>
            
                    <t:saveState value="#{detailBeanName.highlightedDTOS}"/>
                    <t:saveState value="#{detailBeanName.searchNameCritria}"/>
                    <t:saveState value="#{pageBeanName.hmObjects}"/>
                    <t:saveState value="#{pageBeanName.beanName}"/>
                    <t:saveState value="#{pageBeanName.backAction}"/>
                    <t:saveState value="#{pageBeanName.navigationCase}"/>
                    
                    <t:saveState value="#{conditionsIntgMaintainBean.targetForJoinConditionDTO}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.allowMultiCondition}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.transactionName}"/>
                <t:saveState value="#{conditionsIntgMaintainBean.restoreMethod}"/>
                        <t:saveState value="#{conditionsIntgMaintainBean.objectCode}"/>
                    <tiles:insert definition="conditionsIntgBaseFirst.page" flush="false">
                         
                         
                    </tiles:insert>
               </t:aliasBean>
          </t:aliasBean>
          <c2:scriptGenerator form="myForm"/>
     </h:form>
</f:view>
