<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>


<f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>


<t:panelGrid columns="4" rowClasses="row01,row02" width="80%" cellpadding="0" align="center" cellspacing="0" forceId="true" id="searchGridID" onkeypress="FireButtonClick('SearchButtonRequestID');">

 <h:outputText id="requestType_name" value="#{resourcesBundle.typeTitle}"/>
 <h:panelGroup>
  <t:selectOneMenu id="requestTypeList" forceId="true" value="#{requestListBean.requestsSearchCriteriaDTO.reqType}" styleClass="DropdownboxMedium2">
  <f:selectItem itemValue="#{requestListBean.virtualValue}" itemLabel="#{resourcesBundle.Select_One_menu_Default_Label}" />
  <t:selectItems value="#{requestListBean.requestTypes}" var="requestTypeList" itemValue="#{requestTypeList.code.key}" itemLabel="#{requestTypeList.name}" /> 
</t:selectOneMenu>
</h:panelGroup>
<h:outputText id="header_requestReason" value="#{resourcesBundle.request_reason}"/>
 <h:panelGroup>
  <t:selectOneMenu id="requestReasonList" forceId="true" value="#{requestListBean.requestsSearchCriteriaDTO.reqReason}" styleClass="DropdownboxMedium2">
  <f:selectItem itemValue="#{requestListBean.virtualValue}" itemLabel="#{resourcesBundle.Select_One_menu_Default_Label}" />
  <t:selectItems value="#{requestListBean.requestReasons}" var="requestReasonList" itemValue="#{requestReasonList.code.key}" itemLabel="#{requestReasonList.name}" /> 
</t:selectOneMenu>
</h:panelGroup>


  <h:outputText id="header_code" value="#{globalResources.Code}" />
  <t:inputText value="#{requestListBean.requestsSearchCriteriaDTO.requestCode}" styleClass="textboxmedium"/>
  
  <h:outputText id="header_requestStatus" value="#{resourcesBundle.statusTitle}"/>
  <%--t:selectBooleanCheckbox forceId="true"  id="statusFlag" value="#{requestListBean.requestsSearchCriteriaDTO.booleanCompleteFlag}" /--%>
  <t:selectOneMenu id="jobFreezeSearch" value="#{requestListBean.requestsSearchCriteriaDTO.completeFlag}"  styleClass="textbox" >
                                <f:selectItem itemLabel="#{resourcesBundle.select_status}" itemValue="" />
                                <f:selectItem itemLabel="#{resourcesBundle.completed_reqest}" itemValue="#{requestListBean.requestStatusList[1][0]}" />
                                <f:selectItem itemLabel="#{resourcesBundle.notCompleted_reqest}" itemValue="#{requestListBean.requestStatusList[0][0]}" />
                                <%--t:selectItems itemLabel="#{jobFreezed.typeName}" itemValue="#{jobFreezed.typeCode}" var="jobFreezed" value="#{jobListBean.jobFreezed}" /--%>
                    </t:selectOneMenu>
</t:panelGrid>

<f:verbatim>
  <br/>
</f:verbatim>

  <t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
  <t:commandButton value="#{globalResources.SearchButton}"  action="#{requestListBean.search}"  styleClass="cssButtonSmall" forceId="true" id="SearchButtonRequestID"/>
    <t:panelGroup>
        <t:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="back_search_function();" type="button"  forceId="true"  id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();" />
              <a4j:jsFunction name="back_search_function" action="#{requestListBean.cancelSearch}" reRender="searchGridID,dataT_data_panel,noOfRecords,paging_panel"  oncomplete="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);"  />
       </t:panelGroup>     
</t:panelGrid>                    


 <script type="text/javascript"> 
 function setFocusAtMySearchDiv(){
    setFocusOnlyOnElement('requestTypeList'); 
    }
</script>