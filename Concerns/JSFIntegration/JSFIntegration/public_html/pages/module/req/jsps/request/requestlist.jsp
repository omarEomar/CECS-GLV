<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">

    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>
    
    <h:form id="myForm" binding="#{requestListBean.frm}">
    
        <t:aliasBean alias="#{pageBeanName}" value="#{requestListBean}">
    
            <tiles:insert definition="maintainrequestlist.page" flush="false">
                
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>   
                <t:saveState value="#{pageBeanName.searchMode}"/>     
                <t:saveState value="#{pageBeanName.selectedDTOS}"/> 
                <t:saveState value="#{requestListBean.searchMode}"/>
                
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                
                <t:saveState value="#{pageBeanName.codeMI}"/>
                <t:saveState value="#{pageBeanName.requestTypeMI}"/>   
                <t:saveState value="#{pageBeanName.requestReasonMI}"/>     
                <t:saveState value="#{pageBeanName.requestDateMI}"/>
                <t:saveState value="#{pageBeanName.requestStatusMI}"/>   
                <t:saveState value="#{pageBeanName.completedRequestStatusMI}"/>     
                
                
                <%-- start paging --%>
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
                <%-- end paging --%>
            
            </tiles:insert>
        
        </t:aliasBean>
        <script type="text/javascript">
            settingFoucsAtListPage();
        </script>
    </h:form>
    
</f:view>