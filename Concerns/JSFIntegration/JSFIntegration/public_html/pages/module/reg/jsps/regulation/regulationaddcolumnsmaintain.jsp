<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>



<f:view locale="#{shared_util.locale}">

    <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>

    <h:form id="myForm" binding="#{regulationAddColumns.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}" id="alias1">
        <t:aliasBean alias="#{detailBeanName}" value="#{regulationAddColumns}" id="alias2">
    
     
            <tiles:insert definition="regulationaddcolumns.page" flush="false">  
             <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true ? 'addcolumn_Title' : (pageBeanName.maintainMode==2 || pageBeanName.copyFlage==false ? 'view_addcolumn_Title' : 'addcolumn_Title')}"></tiles:put>
            </tiles:insert>
            
            <t:saveState value="#{wizardbar.wizardSteps}" id="wizardSteps"/>
            <t:saveState value="#{wizardbar.configurationId}" id="configurationId"/>
            
            
            <t:saveState value="#{detailBeanName.masterEntityKey}" />
            <t:saveState value="#{pageBeanName.pageDTO}" />
            <t:saveState value="#{pageBeanName.nextNavigationCase}" />
            <t:saveState value="#{pageBeanName.previousNavigationCase}" />
            <t:saveState value="#{pageBeanName.finishNavigationCase}" />
            <t:saveState value="#{pageBeanName.currentNavigationCase}" />
            <t:saveState value="#{pageBeanName.maintainMode}"/>
            <t:saveState value="#{pageBeanName.renderSave}" />
            <t:saveState value="#{pageBeanName.renderFinish}" />
            <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
            <t:saveState value="#{pageBeanName.currentStep}"/>
            <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
            <t:saveState value="#{regulationAttachmentsMaintain.selectedCurrentDetails}"/>
    
            <t:saveState value="#{detailBeanName.currentDetails}" />
            <%--t:saveState value="#{detailBeanName.availableDetails}"/--%>
            <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
            <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
            
            <t:saveState  value="#{regulationListBean.fullColumnName}" />
            <t:saveState  value="#{regulationListBean.sortAscending}" />
            <t:saveState  value="#{regulationListBean.saveSortingState}" />
            <t:saveState  value="#{regulationListBean.sortColumn}" />
            <t:saveState  value="#{regulationListBean.indexArray}" />
            <t:saveState value="#{detailBeanName.index}"/>   
            <t:saveState value="#{detailBeanName.preEditDTO}"/>   
            <t:saveState value="#{detailBeanName.editDTO}"/>  
            
            <t:saveState value="#{detailBeanName.dataTableValue}"/>  
            <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
            <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
            <t:saveState value="#{detailBeanName.selectedPageDTO}"/>  
            
            <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
            <t:saveState value="#{detailBeanName.finishButtonOverride}"/> 
            <t:saveState value="#{detailBeanName.selectedListSize}"/> 
            <t:saveState value="#{detailBeanName.backupCurrentDetails}"/> 
            <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
            <t:saveState value="#{regulationMaintainBean.copyFlage}" id="copyFlageId"/>
        </t:aliasBean>
        </t:aliasBean>
    </h:form>
    <script type="text/javascript">
        setFocusOnElement('addButton');
    </script>
    <%--t:saveState value="#{typeListBean.selectedDTOS}"/--%>
    
</f:view>