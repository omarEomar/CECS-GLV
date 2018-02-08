<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGroup forceId="true" id="filterPanelExecute">

 <t:outputLabel  styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.promoteToJob}"/>
 
<t:panelGrid columns="3" align="center" dir="#{jobIntgResources.align}" id="exeFilter" forceId="true"
                 columnClasses="col1,col2" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">

 <t:outputLabel styleClass="lable01 nowrap_txt nowrap_txt"  value="#{jobIntgResources.job_title_lable}"/>
        
        <t:inputText forceId="true" id="jobVal" value="#{pageBeanName.selectedJobToPromote}"
                     styleClass="textbox_175" 
                     onkeypress=" filterationInputOnKeyPress(event,this,'jobId','errorCodeJobID',changeJob,null);"
                        onblur=" filterationInputOnBlur(event,this,'jobId','errorCodeJobID',changeJob,null);"
                        />
        <t:panelGroup> 
        <t:selectOneMenu forceId="true" id="jobId" value="#{pageBeanName.selectedJobToPromote}" styleClass="DropdownboxMedium3"
                         onchange="filterationDropboxOnChange(event,this,'jobVal','errorCodeJobID',changeJob);">
            <f:selectItem itemValue=""  itemLabel="#{jobIntgResources.select_any_item}" />
            <t:selectItems value="#{pageBeanName.jobsList}" itemLabel="#{job.name}"
                           itemValue="#{job.jobKey}" var="job"/>
        
        </t:selectOneMenu>
            <a4j:jsFunction  name="changeJob"  reRender="executePromoteBtn,executePRMId,jobVal" />
        <t:outputLabel id="errorCodeJobID" value="#{jobIntgResources.messageForWrongCode}" forceId="true" styleClass="errMsg" style="display:none; "/>
        </t:panelGroup>

</t:panelGrid>
   
</t:panelGroup>

<t:panelGroup id="executePRMId" forceId="true" >
    <t:commandButton id="executePromoteBtn" forceId="true" type="button" onclick="executionAction();" 
                     value="#{jobIntgResources.execute}" disabled="#{pageBeanName.selectedJobToPromote == null || pageBeanName.selectedJobToPromote == ''}"
                     styleClass="cssButtonSmall"  />
    <a4j:jsFunction  name="executionAction" action="#{pageBeanName.doExecuteJobPromotion}"  
                     oncomplete="javascript:changeVisibilityMsg(); hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false ; "
                     reRender="divMsg,paging_panel,myForm:executePRM,customDiv1,filterPanelGrp,OperationBar,dataT_data_panel" />
    <t:panelGroup>
        <t:commandButton forceId="true" id="backButtonCustomDiv1"  onclick="cancelDivBackJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
        <a4j:jsFunction name="cancelDivBackJsFunction"  oncomplete="hideLookUpDiv(window.blocker,window.customDiv1,null,null);"/>
    </t:panelGroup>
   </t:panelGroup>

