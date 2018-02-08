<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
 <%-- <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/> --%>
<f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>

 
 <h:form id="myForm" binding="#{RequestApprovalsListBean.frm}">   
     
<%-- <a4j:log popup="false" level="ALL"  height="400" width="800"></a4j:log>--%>
  <t:aliasBean alias="#{pageBeanName}" value="#{RequestApprovalsListBean}" id="requestApprovalsListBean">
  
   
       
   
     <%-- <a4j:log hotkey="M" /> --%>
        <tiles:insert definition="requestconfirmation.page" flush="false">
              <tiles:put name="titlepage" type="string" value="${resourcesBundle.approvallist}" ></tiles:put>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState  value="#{pageBeanName.selectedapprovalMakerId}"/>
                <t:saveState value="#{pageBeanName.rejectionResonList}"/>
                <t:saveState value="#{pageBeanName.integrationCase}"/>
                <t:saveState value="#{pageBeanName.moduleType}"/>
                <t:saveState value="#{pageBeanName.rejected}"/>
                <t:saveState value="#{pageBeanName.approvalMakerList}"/>
                <t:saveState value="#{pageBeanName.userCode}"/>
                
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
    
<c2:scriptGenerator form="myForm"/>
        
        <script type="text/javascript">
            setFocusAtMyFirstElement();
            
            function setFocusAtMyFirstElement(){
                setFocusOnElement('approvalMakerList');
            }
            function setFocusAtMyCustomDiv(){
                document.getElementById('rejectionReason').focus();
            }
        </script>
  </h:form>
  
</f:view>
