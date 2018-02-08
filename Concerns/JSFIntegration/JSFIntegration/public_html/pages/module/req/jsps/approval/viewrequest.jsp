<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<!--
/*
 * Licensed to the Beshara Group (BG)
 * authore: Ahmed Abd El-Fatah
 */
//-->
<%
String typeCodeEx="";
String defName="add.page";
if(request.getParameter("typeCode") != null)
{
typeCodeEx=request.getParameter("typeCode");
}
if(request.getParameter("typeCodeEx") != null)
{
typeCodeEx=request.getParameter("typeCodeEx");
}
if((typeCodeEx!=null)&&(!typeCodeEx.equals(""))){
defName="add";
}
%>
<f:view locale="#{shared_util.locale}">
    <f:verbatim>
     <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/module/req/media/css/request_ar.css" />
     </f:verbatim>
    <!-- Load the resource bundle of the page -->
<f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>

  <h:form id="myForm" binding="#{requestViewDataBean.frm}">
  <t:aliasBean alias="#{pageBeanName}" value="#{requestViewDataBean}">
    
     <tiles:insert flush="false" definition="viewrequestapp.page">
         <tiles:put name="titlepage" type="string" value="${resourcesBundle.viewreq}" ></tiles:put>
        <!-- Set the Body content of the page -->
        <t:saveState value="#{requestViewDataBean.requestsDTOSelected}" />
          <t:saveState value="#{requestViewDataBean.requestsDTO}" />
            <t:saveState value="#{requestViewDataBean.typeList}" />
            <t:saveState value="#{requestViewDataBean.reasonList}" />            
            <t:saveState value="#{requestViewDataBean.selectedTypeCode}" />
            <t:saveState value="#{requestViewDataBean.selectedReasonCode}" />
            <t:saveState value="#{requestViewDataBean.requestsDTO.typeDTO}" />
            <t:saveState value="#{requestViewDataBean.requestsDTO.reasonDTO}" />
            <t:saveState value="#{requestViewDataBean.completeFlag}" />
            <t:saveState value="#{RequestApprovalsListBean.selectedapprovalMakerId}"/>
            <t:saveState value="#{RequestApprovalsListBean.myTableData}"/>
            <t:saveState value="#{RequestApprovalsListBean.selectedDTOS}"/>
            <t:saveState value="#{requestViewDataBean.nonFieldGroups}" />
            <t:saveState value="#{RequestApprovalsListBean.integrationCase}"/>
            <t:saveState value="#{RequestApprovalsListBean.moduleType}"/>
            <t:saveState value="#{RequestApprovalsListBean.approvalMakerList}"/>
            <t:saveState value="#{RequestApprovalsListBean.userCode}"/>
            <%-- start paging --%>
                <t:saveState value="#{RequestApprovalsListBean.currentPageIndex}"/>
                <t:saveState value="#{RequestApprovalsListBean.oldPageIndex}"/>
                <t:saveState value="#{RequestApprovalsListBean.sorting}"/>
                <t:saveState value="#{RequestApprovalsListBean.usingPaging}"/>
                <t:saveState value="#{RequestApprovalsListBean.updateMyPagedDataModel}"/>
                <t:saveState value="#{RequestApprovalsListBean.resettedPageIndex}"/>
                <t:saveState value="#{RequestApprovalsListBean.pagingRequestDTO}"/>
                <t:saveState value="#{RequestApprovalsListBean.pagingBean.totalListSize}"/>
                <t:saveState value="#{RequestApprovalsListBean.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{RequestApprovalsListBean.pagingBean.preUpdatedDataModel}"/>
                <%-- end paging --%>
                 <t:saveState value="#{pageBeanName.navigatinBack}" />
          
        
    </tiles:insert>
</t:aliasBean>
   </h:form>
  <c2:scriptGenerator form="myForm"/>
</f:view>