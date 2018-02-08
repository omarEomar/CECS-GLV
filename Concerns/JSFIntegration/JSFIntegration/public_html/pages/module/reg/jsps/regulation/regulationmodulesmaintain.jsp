<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">


 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>


 <h:form id="myForm" binding="#{regulationModulesMaintainBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}" id="alias1">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationModulesMaintainBean}" id="alias2">

    <t:saveState value="#{pageBeanName.pageDTO}" />
    <t:saveState value="#{pageBeanName.nextNavigationCase}" />
    <t:saveState value="#{pageBeanName.previousNavigationCase}" />
    <t:saveState value="#{pageBeanName.finishNavigationCase}" />
    <t:saveState value="#{pageBeanName.currentNavigationCase}" />
    <t:saveState value="#{pageBeanName.currentStep}" />
    <t:saveState value="#{pageBeanName.maintainMode}"/>
    <t:saveState value="#{pageBeanName.renderSave}" />
    <t:saveState value="#{pageBeanName.renderFinish}" />
    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
    
    <t:saveState value="#{detailBeanName.currentDetails}" />
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" />    
    <t:saveState value="#{detailBeanName.saveButtonOverride}"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}"/>    
    
    <t:saveState value="#{detailBeanName.selectedModuleKey}"/>    
    <t:saveState value="#{detailBeanName.modules}"/>    
    <t:saveState value="#{detailBeanName.selectedTableKey}"/>    
    <t:saveState value="#{detailBeanName.tables}"/>    
    <t:saveState value="#{detailBeanName.customAvailableDetails}"/>    
    <t:saveState value="#{detailBeanName.selectedAvailableDetailsRowIndices}"/> 
    <t:saveState value="#{detailBeanName.customAvailableDetailsSize}"/> 
    <t:saveState value="#{detailBeanName.moduleRelations}"/> 
    <t:saveState value="#{detailBeanName.customCurrentDetails}"/> 
    
    <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
    <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
    <t:saveState value="#{pageBeanName.copyFlage}"/>
    <t:saveState  value="#{regulationListBean.fullColumnName}" />
    <t:saveState  value="#{regulationListBean.sortAscending}" />
    <t:saveState  value="#{regulationListBean.saveSortingState}" />
    <t:saveState  value="#{regulationListBean.sortColumn}" />
    <t:saveState  value="#{regulationListBean.indexArray}" />
    <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
    <t:saveState value="#{regulationMaterialsMaintainBean.firstVisitForRelatedMaterial}"/>
    <tiles:insert definition="regulationmodulesmaintain.page" flush="false">
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
            setFocusOnlyOnElement('system_name');
        }
        
        function setFocusAtMyEditDiv(){
            setFocusOnlyOnElement('CancelButtonEdit');
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnlyOnElement('CancelButtonDelAlertDiv');
        }
    </script>
 </h:form>
</f:view>