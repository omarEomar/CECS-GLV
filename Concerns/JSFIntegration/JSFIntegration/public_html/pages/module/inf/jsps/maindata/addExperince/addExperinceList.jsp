<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.gn.inf.presentation.resources.inf" var="resourcesBundle"/>
   <f:loadBundle basename="com.beshara.csc.nl.org.integration.presentation.resources.integration" var="orgIntgResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <h:form id="myForm" binding="#{addExperinceIntegrationBean.frm}">
        <%--<t:messages showSummary="true" showDetail="true"/>--%>
        <t:aliasBean alias="#{pageBeanName}" value="#{addExperinceIntegrationBean}">
         <t:aliasBean alias="#{minHelperBeanName}" value="#{addExperinceIntegrationBean.ministryHelper}">   
            <t:aliasBean alias="#{jSearchBeanName}" value="#{addExperinceIntegrationBean.jobFilter}">
                <t:aliasBean alias="#{wcIntegrationBeanName}" value="#{addExperinceIntegrationBean.wcIntegrationBean}">
            <tiles:insert definition="addExperinceIntegration.page" flush="false">
                <t:saveState value="#{pageBeanName.categoryBindingValue}" />
                <t:saveState value="#{pageBeanName.selectedMinistry}"/>
                <t:saveState value="#{pageBeanName.categoryTypeCode}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.ministryList}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.popListSize}"/>
                <t:saveState value="#{pageBeanName.popName}"/>
                <t:saveState value="#{pageBeanName.searchPopString}"/>
                <t:saveState value="#{pageBeanName.popList}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
                <t:saveState value="#{pageBeanName.selectedJobRadio}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                 
                <t:saveState value="#{pageBeanName.myTableData}" />
                <t:saveState value="#{pageBeanName.showErrorMsg}"/>
                <t:saveState value="#{pageBeanName.success}"/>
                
                <t:saveState value="#{pageBeanName.lovBaseBean.myTableData}"/>
                <t:saveState value="#{pageBeanName.validateSrchString}"/>
                
                
                
                <t:saveState value="#{pageBeanName.regDate}"/>
                
                 
                 <%-- Start Paging --%>
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.deepCopyMin}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{pageBeanName.myExcludedMinistriesList}"/>
                <t:saveState value="#{pageBeanName.jobExcludedCode}"/>
                <%--<t:saveState value="#{pageBeanName.disableWorkCenterDiv}"/>--%>
                <t:saveState value="#{addExperinceIntegrationBean.ministryHelper}"/>
                <t:saveState value="#{addExperinceIntegrationBean.jobFilter}"/>
                <t:saveState value="#{addExperinceIntegrationBean.wcIntegrationBean}"/>
<%-- End Paging --%>

            </tiles:insert>
        </t:aliasBean>
        </t:aliasBean>
        </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup id="myFormGrp" forceId="true">
        <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
    </h:form>
    
<script type="text/javascript">
setFocusAtMyFirstElement();
function setFocusAtMyFirstElement(){
    setFocusOnlyOnElement('categoryList');
 }
 
 function settingFoucsAtMinisteryDiv(){
    if(document.getElementById('ministryNameadd') != null){
        document.getElementById('ministryNameadd').focus();
        document.getElementById('ministryNameadd').focus();
    }
}
 </script>
</f:view>
