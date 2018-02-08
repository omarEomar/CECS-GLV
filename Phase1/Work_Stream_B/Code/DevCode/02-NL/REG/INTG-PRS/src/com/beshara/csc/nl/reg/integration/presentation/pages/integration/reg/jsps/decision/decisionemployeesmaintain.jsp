<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
    <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration"
                  var="resourcesBundle"/>
    <h:form id="myForm" binding="#{decisionEmployeesMaintainBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{decisionMaintainBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{decisionEmployeesMaintainBean}">
                <tiles:insert definition="decisionemployeesmaintain.page" flush="false">
                    <tiles:put name="titlepage" type="string"
                               value="${!pageBeanName.cancelDecisionMode ? 'decision_master_title' : 'decision_cancellation'}"></tiles:put>
                    <t:saveState value="#{pageBeanName.pageDTO}"/>
                    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
                    <t:saveState value="#{pageBeanName.currentStep}"/>
                    <t:saveState value="#{pageBeanName.maintainMode}"/>
                    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
                    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
                    <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
                    <t:saveState value="#{pageBeanName.cancelDecisionMode}"/>
                    <t:saveState value="#{detailBeanName.currentDetails}"/>
                    <t:saveState value="#{detailBeanName.availableDetails}"/>
                    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
                    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
                    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>
                    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
                    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
                    <t:saveState value="#{detailBeanName.isSearchMode}"/>
                    <t:saveState value="#{detailBeanName.content1Div}"/>
                    <t:saveState value="#{detailBeanName.divMainContentMany}"/>
                    <t:saveState value="#{decisionMainDataMaintainBean.typesDTOKey}"/>
                    <t:saveState value="#{decisionMainDataMaintainBean.yearsDTOKey}"/>
                    <t:saveState value="#{detailBeanName.isEmployee}"/>
                    <%-- t:saveState value="#{decisionEmployeesMaintainBean.employeesAddBean.savedData}"/--%>
                    <t:saveState value="#{decisionEmployeesMaintainBean.showBarMainData}"/>
                    <t:saveState value="#{decisionMainDataMaintainBean.showLovDivFlag}"/>
                    <t:saveState value="#{wizardbar.wizardSteps}"/>
                    <t:saveState value="#{wizardbar.configurationId}"/>
                    <t:saveState value="#{wizardbar.currentStep}"/>
                    <t:saveState value="#{decisionListBean.fullColumnName}"/>
                    <t:saveState value="#{decisionListBean.sortAscending}"/>
                    <t:saveState value="#{decisionListBean.saveSortingState}"/>
                    <t:saveState value="#{decisionListBean.sortColumn}"/>
                    <t:saveState value="#{decisionListBean.indexArray}"/>
                    <t:saveState value="#{decisionMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
                    <t:saveState value="#{pageBeanName.copyDecisionWithEmployeesMode}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <c2:scriptGenerator form="myForm"/>
        <script type="text/javascript">
 > setFocusAtMyFirstElement();

          function setFocusAtMyFirstElement() {
              setFocusOnElement('addButton');
          }

          function setFocusAtMyAddDiv() {
              setFocusOnlyOnElement('maintain_regType_div');
          }

          function setFocusAtMyDelDiv() {
              setFocusOnlyOnElement('CancelButtonDelAlertDiv');
          }
        </script>
    </h:form>
</f:view>