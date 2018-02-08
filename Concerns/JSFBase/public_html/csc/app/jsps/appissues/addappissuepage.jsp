<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.csc.gn.inf.integration.presentation.resources.infintegration" var="infintegrationBundle"/>

    <h:form id="myForm"  binding="#{addAppProblemBean.frm}" enctype="multipart/form-data">

  <t:aliasBean alias="#{pageBeanName}" value="#{addAppProblemBean}">
          <t:aliasBean alias="#{attachmentHelperBean}" value="#{addAppProblemBean.attachmentsListBean}">
            <tiles:insert definition="addappproblem.page" flush="false">
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <!-- added by nora to enable single selection -->
                <t:saveState value="#{pageBeanName.singleSelection}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <!-- Start Paging -->
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <!-- End Paging -->
                <t:saveState value="#{pageBeanName.catCode}"/>
                <t:saveState value="#{pageBeanName.minCode}"/>
                <t:saveState value="#{pageBeanName.ministryList}"/>
                <t:saveState value="#{pageBeanName.categoryList}"/>
                <t:saveState value="#{pageBeanName.problemImportanceCode}"/>
                <t:saveState value="#{pageBeanName.problemImportanceList}"/>
                <t:saveState value="#{pageBeanName.problemType}"/>
                <t:saveState value="#{pageBeanName.machineSearchValue}"/>
                <t:saveState value="#{pageBeanName.machineSearchMode}"/>
                <t:saveState value="#{pageBeanName.machineSearchType}"/>
                <t:saveState value="#{pageBeanName.myMachinesDataTable}"/>
                <t:saveState value="#{pageBeanName.machinesList}"/>
                <t:saveState value="#{pageBeanName.selectedMachiesDTOS}"/>
                <t:saveState value="#{pageBeanName.problemCatList}"/>
                <t:saveState value="#{pageBeanName.problemCatCode}"/>
                <t:saveState value="#{pageBeanName.selectedMachineCode}"/>
                <t:saveState value="#{pageBeanName.wrongMachineSerial}"/>
                <t:saveState value="#{pageBeanName.selectedMachineDesc}"/>

                <t:saveState value="#{pageBeanName.modulesDataTable}"/>
                <t:saveState value="#{pageBeanName.modulesDTOS}"/>
                <t:saveState value="#{pageBeanName.moduleSearchType}"/>
                <t:saveState value="#{pageBeanName.moduleSearchValue}"/>
                <t:saveState value="#{pageBeanName.moduleSearchMode}"/>
                <t:saveState value="#{pageBeanName.selectedModuleRadio}"/>
                <t:saveState value="#{pageBeanName.selectedModulesDTOS}"/>
                
                <t:saveState value="#{pageBeanName.selectedModuleCode}"/>
                <t:saveState value="#{pageBeanName.selectedModuleDesc}"/>
                <t:saveState value="#{pageBeanName.wrongModuleSerial}"/>
                <t:saveState value="#{pageBeanName.coordinatorDTO}"/>
                <t:saveState value="#{pageBeanName.coordinatorName}"/>
                <t:saveState value="#{pageBeanName.SYSTEM_PROBLRM}"/>
                <t:saveState value="#{pageBeanName.MACHINE_PROBLRM}"/>
                <t:saveState value="#{pageBeanName.NETWORK_PROBLRM}"/>
                <t:saveState value="#{pageBeanName.disableMinCat}"/>

                <t:saveState value="#{pageBeanName.pageMode}"/>
                <t:saveState value="#{pageBeanName.PAGE_MODE_EDIT}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.applicantName}"/>
                <t:saveState value="#{pageBeanName.problemSourcesList}"/>
                <t:saveState value="#{pageBeanName.selectedProblemSourceCode}"/>
              

    

            </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup id="scriptGenerator" forceId="true">
		    <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
  
    </h:form>

  
  
</f:view>
