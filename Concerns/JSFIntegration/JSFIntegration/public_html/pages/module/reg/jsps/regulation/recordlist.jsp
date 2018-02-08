<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>


 <h:form id="myForm" binding="#{regulationRecordsBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationRecordsBean}">

    <t:saveState value="#{pageBeanName.pageDTO}"/>
    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentStep}"/>
    <t:saveState value="#{pageBeanName.maintainMode}"/>
    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
    <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
    
    <t:saveState value="#{detailBeanName.currentDetails}"/>
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
    
    <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
    <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
    
    <t:saveState  value="#{regulationListBean.fullColumnName}" />
    <t:saveState  value="#{regulationListBean.sortAscending}" />
    <t:saveState  value="#{regulationListBean.saveSortingState}" />
    <t:saveState  value="#{regulationListBean.sortColumn}" />
    <t:saveState  value="#{regulationListBean.indexArray}" />
    <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
    <t:saveState value="#{regulationRecordsBean.record}"/>
    <t:saveState value="#{regulationRecordsBean.record.data}"/>
    <t:saveState value="#{regulationRecordsBean.record.columnHeaders}"/>
    <t:saveState value="#{regulationRecordsBean.record.columns}"/>
    <t:saveState value="#{regulationRecordsBean.record.recordDataList}"/>
    <t:saveState value="#{regulationRecordsBean.record.maxRecordIndex}"/>
    
    <t:saveState value="#{regulationRecordsBean.editableRecordDataDTO}"/>
    <t:saveState value="#{regulationRecordsBean.finalEditableRecordDataDTO}"/>
    <t:saveState value="#{regulationRecordsBean.designTablesDTO}"/>
    <t:saveState value="#{regulationRecordsBean.backupColumnsList}"/>
   
    <t:saveState value="#{wizardbar.wizardSteps}" id="wizardSteps"/>
    <t:saveState value="#{wizardbar.configurationId}" id="configurationId"/>
    <t:saveState value="#{regulationRecordsBean.currentListSize}" />
    <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
     <t:saveState value="#{regulationMaintainBean.copyFlage}" id="copyFlageId"/>
    <tiles:insert definition="regulationrecordsmaintain.page" flush="false">
     <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true ? 'record_title_page' : (pageBeanName.maintainMode==2 || pageBeanName.copyFlage==false ? 'view_record_title_page' : 'record_title_page')}"></tiles:put>
    </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
   <t:panelGroup forceId="true" id="clientValidatorPanel"> 
         <c:scriptGenerator form="myForm" popup="false"/>
    </t:panelGroup>
 <script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
          setFocusOnlyOnElement('addButton').focus;
        }
        
        function setFocusAtMyAddDiv(){
            setFocusOnlyOnElement('maintain_regNumber');
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
        
         function setfocusAtConfirmMsg(){
           setFocusOnlyOnElement('jsfBase_msgDiv_backBtn');
        }
        
    </script>
 </h:form>
</f:view>