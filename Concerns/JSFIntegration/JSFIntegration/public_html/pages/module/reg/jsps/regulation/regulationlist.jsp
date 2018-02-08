
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<f:view locale="#{shared_util.locale}">

    
    <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
    

    <h:form id="myForm" binding="#{regulationListBean.frm}">

      <t:aliasBean alias="#{pageBeanName}" value="#{regulationListBean}">
      <t:aliasBean alias="#{customDiv1Bean}" value="#{regulationListBean}">
        
        <tiles:insert definition="regulationlist.page" flush="false">
            
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            
            <t:saveState value="#{pageBeanName.typeColumnVisible}"/>
            <t:saveState value="#{pageBeanName.yearColumnVisible}"/>
            <t:saveState value="#{pageBeanName.numberColumnVisible}"/>
            <t:saveState value="#{pageBeanName.titleColumnVisible}"/>
            <t:saveState value="#{pageBeanName.decisionMakerColumnVisible}"/>
            <t:saveState value="#{pageBeanName.dateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.applyDateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.cancelDateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.applyCancelDateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.statusColumnVisible}"/>
            <t:saveState value="#{pageBeanName.cancelDivDisplayed}"/>
            
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
            
            <t:saveState value="#{pageBeanName.indexArray}"/>
            
            
        
        </tiles:insert>
        </t:aliasBean> 
      </t:aliasBean>
    
    <t:panelGroup forceId="true" id="scriptGeneratorID">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
        <script type="text/javascript"> 
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){        
                if(document.getElementById('addButton') != null){            
                    document.getElementById('addButton').focus();
                }
            }
            
            function setFocusAtMySearchDiv(){
                setFocusOnlyOnElement('reg_number_id');  
            }
            
            function setFocusAtMyCustom1Div(){
                setFocusOnlyOnElement('DecisionMakerSelect');
            }
        </script>  
    </h:form>
  
</f:view>
