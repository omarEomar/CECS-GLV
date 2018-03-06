<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
    <t:messages/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{reasdondataListBean.frm}">
        <%-- <a4j:log popup="false" level="ALL" height="400" width="800"></a4j:log>--%>
        <t:aliasBean alias="#{pageBeanName}" value="#{reasdondataListBean}">
            <tiles:insert definition="reasdonDataList.page" flush="false">
                <t:saveState value="#{pageBeanName.pageMode}"/>
            <t:saveState value="#{pageBeanName.pageDTO.restypeCode}"/>
           </tiles:insert>
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
        </t:aliasBean>
   
    </h:form>
   <script type="text/javascript">
        
            setFocusAtMyFirstElement();
            
            function setFocusAtMyFirstElement(){
                setFocusOnElement('modulesListMenu');
            }
             </script>
               
</f:view>