<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>

 <h:form enctype="multipart/form-data" id="myForm" binding="#{regulationMainDataMaintainBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationMainDataMaintainBean}">
    
    <tiles:insert definition="regulationmaindatamaintain.page" flush="false">
        <%--tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 ? 'regulation_master_title' : (pageBeanName.maintainMode==2 ? 'view_regulation_master_title' : 'edit_regulation_master_title')}"></tiles:put--%>
        <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true ? 'regulation_master_title' : (pageBeanName.maintainMode==2 || pageBeanName.copyFlage==false ? 'view_regulation_master_title' : 'edit_regulation_master_title')}"></tiles:put>
        <t:saveState value="#{pageBeanName.pageDTO}" id="mainostep"/>
        <t:saveState value="#{pageBeanName.nextNavigationCase}" id="mainnstep"/>
        <t:saveState value="#{pageBeanName.previousNavigationCase}"  id="mainpstep"/>
        <t:saveState value="#{pageBeanName.finishNavigationCase}" id="mainfstep"/>
        <t:saveState value="#{pageBeanName.currentNavigationCase}" id="maincstep"/>
        <t:saveState value="#{pageBeanName.currentStep}" id="mainstep"/>
        <t:saveState value="#{pageBeanName.maintainMode}" id="mainmode"/>
        <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
        <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
        <t:saveState value="#{pageBeanName.copyFlage}" id="copyFlageId"/>
        <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
        
        <t:saveState value="#{detailBeanName.typesDTOKey}"/>
        <t:saveState value="#{detailBeanName.yearsDTOKey}"/>
        <t:saveState value="#{detailBeanName.currentDetails}"/>
        <t:saveState value="#{detailBeanName.availableDetails}"/>
        <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
        <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
        <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
        <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
        <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>
        
       
        <t:saveState value="#{detailBeanName.types}" id="types"/>
        <t:saveState value="#{detailBeanName.issuanceYears}" id="issuanceYears"/>
        <t:saveState value="#{detailBeanName.status}" id="status"/>
        <t:saveState value="#{detailBeanName.scopes}" id="scopes"/>
        <t:saveState value="#{detailBeanName.decisionMakers}" id="decisionMakers"/>
        <t:saveState value="#{detailBeanName.templates}" id="templates"/>
        <t:saveState value="#{detailBeanName.searchDto}" id="searchDto"/>
        <t:saveState value="#{detailBeanName.selectedParentRegKey}" id="selectedParentRegKey"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValue}" id="itemSelectionRequiredValue"/>
        <t:saveState value="#{detailBeanName.itemSelectionRequiredValueString}" id="itemSelectionRequiredValueString"/>
        <t:saveState value="#{detailBeanName.searchMode}" id="searchMode"/>
        <t:saveState value="#{detailBeanName.disableStatusMenu}" id="disableStatusMenu"/>
        
        <t:saveState value="#{detailBeanName.disableSaveButton}" id="disableSaveButtonId"/>
        
        
        <t:saveState  value="#{regulationListBean.fullColumnName}" />
        <t:saveState  value="#{regulationListBean.sortAscending}" />
        <t:saveState  value="#{regulationListBean.saveSortingState}" />
        <t:saveState  value="#{regulationListBean.sortColumn}" />
        <t:saveState  value="#{regulationListBean.indexArray}" />
        
        <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
         <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
        
    </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
    <c2:scriptGenerator form="myForm"/>
    
    <script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            document.getElementById("myForm:content1Div").scrollTop = 0;
            var maintainModeValue = document.getElementById('pageBeanNameMaintainMode').value;
            if(maintainModeValue == '0'){
                setFocusOnlyOnElement('maintain_regType');
            }
            else if (maintainModeValue == '1'){
                setFocusOnlyOnElement('maintain_regTitle');
            }
        }
    </script>
 </h:form>
</f:view>