<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration"
                  var="infIntegrationBundle"/>
    <f:loadBundle basename="com.beshara.csc.nl.job.integration.presentation.resources.integration"
                  var="jobIntgResources"/>
    <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration"
                  var="orgIntgResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <h:form id="myForm" binding="#{addExperienceListBean.frm}">
        <%-- <t:messages showSummary="true" showDetail="true"/>--%>
        <t:aliasBean alias="#{pageBeanName}" value="#{addExperienceListBean}">
            
                <t:aliasBean alias="#{jSearchBeanName}" value="#{addExperienceListBean.jobFilter}">
                    <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{addExperienceListBean.wcIntegrationBean}">
                        <tiles:insert definition="addExperinceIntegration.page" flush="false">
                            <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                            <t:saveState value="#{pageBeanName.selectedCategory}"/>
                            <t:saveState value="#{pageBeanName.categoryList}"/>
                            <t:saveState value="#{pageBeanName.ministryList}"/>
                            <t:saveState value="#{pageBeanName.pageDTO}"/>
                            <t:saveState value="#{pageBeanName.showErrorMsg}"/>
                            <t:saveState value="#{pageBeanName.success}"/>
                            <t:saveState value="#{pageBeanName.errorWorkCenter}"/>
                            <t:saveState value="#{pageBeanName.workCenterCode}"/>
                            <t:saveState value="#{pageBeanName.jobDivMode}"/>
                            <t:saveState value="#{pageBeanName.jobCode}"/>
                            <t:saveState value="#{pageBeanName.jobName}"/>
                            <t:saveState value="#{pageBeanName.otherJobCode}"/>
                            <t:saveState value="#{pageBeanName.otherJobName}"/>
                            <t:saveState value="#{pageBeanName.jobType}"/>
                            <t:saveState value="#{pageBeanName.perFlag}"/>
                            <t:saveState value="#{pageBeanName.renderJobtypeRadio}"/>
                            <t:saveState value="#{pageBeanName.renderTechJob}"/>
                            <t:saveState value="#{pageBeanName.renderExperDuration}"/>
                            <t:saveState value="#{pageBeanName.renderJobs}"/>
                            <t:saveState value="#{pageBeanName.includeTrx}"/>
                            <t:saveState value="#{pageBeanName.trxCodList}"/>
                            <t:saveState value="#{pageBeanName.kwtCitizenDTO}"/>
                            <t:saveState value="#{pageBeanName.rcivilId}"/>
                            <t:saveState value="#{addExperienceListBean.jobFilter}"/>
                            <t:saveState value="#{addExperienceListBean.wcIntegrationBean}"/>
                            <t:saveState value="#{addExperienceListBean.workDataListBean}"/>
                            <t:saveState value="#{pageBeanName.trxTypesCode}"/>
                            <t:saveState value="#{pageBeanName.selectedMiniName}"/>
                            <%-- End Paging--%>
                        </tiles:insert>
                        </t:aliasBean>
            
                </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup id="myFormGrp" forceId="true">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
    <script type="text/javascript">
      setFocusAtMyFirstElement();

      function setFocusAtMyFirstElement() {
          setFocusOnlyOnElement('categoryList');
      }
    </script>
</f:view>