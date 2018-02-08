<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 
 <h:form id="myForm" binding="#{regulationSubjectsMaintainBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationSubjectsMaintainBean}">

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
    <t:saveState value="#{pageBeanName.copyFlage}"/>
    <t:saveState value="#{detailBeanName.currentDetails}"/>
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
     <t:saveState value="#{detailBeanName.searchMode}" />    
     <t:saveState value="#{detailBeanName.searchType}" />    
    
     <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
     <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
    
     <t:saveState  value="#{regulationListBean.fullColumnName}" />
    <t:saveState  value="#{regulationListBean.sortAscending}" />
    <t:saveState  value="#{regulationListBean.saveSortingState}" />
    <t:saveState  value="#{regulationListBean.sortColumn}" />
    <t:saveState  value="#{regulationListBean.indexArray}" />
    
    <t:saveState value="#{pageBeanName.initializeTablesTab}"/>

    <tiles:insert definition="regulationsubjectsmaintain.page" flush="false">
        <tiles:put name="titlepage" type="string" value="${pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true ? 'regulation_master_title' : (pageBeanName.maintainMode==2 || pageBeanName.copyFlage==false ? 'view_regulation_master_title' : 'edit_regulation_master_title')}"></tiles:put>
        
        
       
        </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
  <c2:scriptGenerator form="myForm"/>
      
    <script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('addButton');
        }
        
        function setFocusAtMyAddDiv(){
            var comp = document.getElementById('hidSearchType');
            if(comp.value == '0'){
                setFocusOnlyOnElement('searchQuery_symbol');
            }
            else if (comp.value == '1'){
                setFocusOnlyOnElement('searchQuery_desc');
            }
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
    </script>
 </h:form>
</f:view>