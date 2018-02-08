<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
div#tipDiv
{
    width: auto !important;
    min-width:140px;
}

div#tipDiv .green
{
 border:1px solid green;
    width:auto;
    height:auto;
    background:white;
    border-radius:6px;
}
div#tipDiv .red
{
 border:1px solid red;
    width:auto;
    height:auto;
    background:white;
    border-radius:6px;
}
 .valid_style {
    color:Green;
    text-align: left;
    font-size: 11px;
}
 .not_valid_style {
    color:red;
    font-size: 11px;
    text-align: left;
    
}
.apply{
 color:green !important;
}
.textarea_GRS {
    max-height: 200px;
    min-height: 22px;
    overflow: auto;
}

</htm:style>
<f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>
<t:panelGrid forceId="true" id="showLinesDetailsDivId"  width="100%">
     <t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%">
     
     <h:outputText id="civilIDForEmp" value="#{globalResources.globalCivilId}" styleClass="lable01 nowrap_txt"/>
     <t:panelGroup colspan="3">
     <t:inputText disabled="true" forceId="true" id="civilIDForEmpId"
                         value="#{cdIntegrationBeanName.civilId}" styleClass="textboxmedium"/>
     </t:panelGroup>
     <h:outputText id="empName" value="#{globalResources.globalName}" styleClass="lable01 nowrap_txt"/>
     <t:panelGroup colspan="3">
     <t:inputText disabled="true" forceId="true" id="empNameId" style=" width: 358px; "
                         value="#{cdIntegrationBeanName.empName}" styleClass="textboxlarge"/>     
     </t:panelGroup>
     <h:outputText id="conditionIDForEmp" value="#{grsIntgResources.condition}" styleClass="lable01 nowrap_txt"/>                         
     <t:panelGroup colspan="3">
     <t:inputTextarea disabled="true" forceId="true" id="conditionNamId" 
                         value="#{cdIntegrationBeanName.conditionName}" styleClass="#{(cdIntegrationBeanName.conditionStatus == true)? 'textboxlarge apply':'textboxlarge dont_apply'}" style=" width: 358px; " />
     </t:panelGroup>
     <t:panelGroup rendered="#{(cdIntegrationBeanName.exceptionType != null && cdIntegrationBeanName.exceptionType != '')}">
      <t:selectBooleanCheckbox id="ExceptionFromChkId" value="#{cdIntegrationBeanName.exceptionFromCondition}"  disabled="true" />
      <t:outputText value="#{grsIntgResources.exceptionFrom}" styleClass="lable01"/>
     </t:panelGroup>
     <h:outputText id="ExcepTypeIDForEmp" value="#{grsIntgResources.exceptionType}" styleClass="lable01 nowrap_txt" style="margin-right:67px;" rendered="#{(cdIntegrationBeanName.exceptionType != null && cdIntegrationBeanName.exceptionType != '')}"/>
     <t:panelGroup colspan="2" rendered="#{(cdIntegrationBeanName.exceptionType != null && cdIntegrationBeanName.exceptionType != '')}">
      <t:inputText disabled="true" forceId="true" id="ExceptionTypeIDForEmpOut" style="width: 210px;"
                           value="#{(cdIntegrationBeanName.exceptionType == 1)? grsIntgResources.exceptionTypeValid : grsIntgResources.exceptionTypeInValid}" styleClass="textboxmedium"/>
     </t:panelGroup>
     <h:outputText id="linesListId" value="#{grsIntgResources.lines}" styleClass="lable01 nowrap_txt"/>
   
     
     
     <t:panelGroup styleClass="textarea_GRS" colspan="3">
     <t:dataList forceId="true" id="linesList"
               var="list"
               value="#{cdIntegrationBeanName.linesDTOList}">
         <t:outputText value="#{(list.rightArcs !=null)? list.rightArcs:''}" styleClass="conditions" />      
         <t:outputText value="#{list.linesDTO.name}" onmouseout="hideTip();" onmouseover="doTooltipWithDetails(event,getToolTipHTML('#{(list.linesDTO.actualLineValue != null)? list.linesDTO.actualLineValue : grsIntgResources.noActualValue}','#{(list.lineStatus == 1)? grsIntgResources.valid : grsIntgResources.unvalid}','#{(list.exceptionDTO.exceptionType == 1)? grsIntgResources.exceptionTypeValid : grsIntgResources.exceptionTypeInValid}','#{(list.exceptionStatus == 1) ? grsIntgResources.valid : grsIntgResources.unvalid}','#{(list.exceptionDTO != null && list.exceptionStatus != null) ? true : false}','#{(list.lineStatus ==1)?'green':'red'}'));" styleClass="#{(list.lineStatus ==1)?'apply':'dont_apply'}" />
         <t:outputText value="#{(list.leftArcs !=null)? list.leftArcs:''}" styleClass="conditions" />
         <f:verbatim>
           <br/>
         </f:verbatim>
         <t:outputText value="#{(list.joinSign !=null )? list.joinSign:''}" styleClass="conditions" />
         <f:verbatim>
           <br/>
         </f:verbatim>
      </t:dataList>
     </t:panelGroup>
     </t:panelGrid>
     
     
    
    
    
    
    <h:panelGroup style="text-align: center; display: block;">
        <t:commandButton forceId="true" id="backButtonIntegrationDiv2" 
                        onclick="hideLookUpDiv(window.blocker,#{cdIntegrationBeanName.divName},null,null);" styleClass="cssButtonSmall"  value="#{globalResources.back}"/>
        
    </h:panelGroup>
</t:panelGrid>
  <t:saveState value="#{cdIntegrationBeanName.civilId}"/>
  <t:saveState value="#{cdIntegrationBeanName.conditionName}"/>
  <t:saveState value="#{cdIntegrationBeanName.empName}"/>
  <t:saveState value="#{cdIntegrationBeanName.exceptionType}"/>
  <t:saveState value="#{cdIntegrationBeanName.tabRecSerial}"/>
  <t:saveState value="#{cdIntegrationBeanName.linesDTOList}"/>
  
  <t:saveState value="#{cdIntegrationBeanName.containerBeanName}"/>
  <t:saveState value="#{cdIntegrationBeanName.divName}"/>
  
  
  
  
   <script type="text/javascript"> 
    function getToolTipHTML(param1,param2,param3,param4,valid ,style){
         var str = ' <div class=\"'+ style +'\">';
            if(style == 'green'){
               str +='<table class=\"valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">'+param1+'</td></tr>';
               str +='<tr><td>الحالة:</td><td>'+param2+'</td></tr>';
            }else{
               str +='<table class=\"not_valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">'+param1+'</td></tr>';
               str +='<tr><td>الحالة:</td><td>'+param2+'</td></tr>';
            }
             if(valid == 'true'){
                 str +='<tr><td>نوع الاستثناء:</td><td>'+param3+'</td></tr>';
                 str +='<tr><td>مستثنى من السطور:</td>';
                 str +='<td>'+param4+'</td></tr>';
             }
       return str;
       
     }
     
   </script>
  
<!-- End Paging -->
