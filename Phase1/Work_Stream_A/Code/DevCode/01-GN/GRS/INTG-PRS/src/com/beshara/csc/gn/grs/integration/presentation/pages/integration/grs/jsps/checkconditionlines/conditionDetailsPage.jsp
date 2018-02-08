<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:view locale="#{shared_util.locale}">
    <htm:style>div#tipDiv .green { border:1px solid green; width:auto; height:auto; background:white; border-radius:6px;
               } div#tipDiv .red { border:1px solid red; width:auto; height:auto; background:white; border-radius:6px; }
               .valid_style { color:Green; text-align: left; font-size: 11px; } .not_valid_style { color:red; font-size:
               11px; text-align: left; } .apply{ color:green !important; } .textarea_GRS { max-height: 200px;
               min-height: 22px; overflow: auto; } div#tipDiv {  width: auto !important;min-width:140px; }</htm:style>
    <htm:link rel="SHORTCUT ICON" href="#{shared_util.contextPath}/app/media/images/favicon.ico" type="image/x-icon"/>
    <htm:link rel="stylesheet" href="../../../shared/media/css/ar/csc_report_ar.css" type="text/css"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_tooltip.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_event.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_viewport.js"/>
    <htm:link rel="stylesheet" type="text/css" href="#{shared_util.contextPath}/app/media/css/ar/Style.css"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                  var="grsIntgResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm">
        <script type="text/javascript">
          Tooltip.init();
        </script>
        <t:saveState value="#{conditionLinesdetailsHelperBean.civilId}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.conditionName}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.empName}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.exceptionType}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.tabRecSerial}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.linesDTOList}"/>
        <t:saveState value="#{conditionLinesdetailsHelperBean.containerBeanName}"/>
        <t:panelGroup styleClass="report_contents" id="condLinesReport">
            <t:panelGrid styleClass="report_header">
                <t:panelGroup styleClass="header_data"/>
            </t:panelGrid>
            <t:outputText value="#{grsIntgResources.conditionDetails}" styleClass="report_title"/>
            <t:panelGroup forceId="true" id="condLinesReportdiv">
                <t:panelGrid forceId="true" id="showLinesDetailsDivId" width="100%" styleClass="reoprt_body">
                    <t:panelGrid columns="4" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%">
                        <h:outputText id="civilIDForEmp" value="#{globalResources.globalCivilId}"
                                      styleClass="td_key td_width120 nowrap_txt"/>
                        <t:panelGroup colspan="3">
                            <t:inputText disabled="true" forceId="true" id="civilIDForEmpId"
                                         value="#{conditionLinesdetailsHelperBean.civilId}" styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText id="empName" value="#{globalResources.globalName}"
                                      styleClass="td_key nowrap_txt"/>
                        <t:panelGroup colspan="3">
                            <t:inputText disabled="true" forceId="true" id="empNameId" style=" width: 358px; "
                                         value="#{conditionLinesdetailsHelperBean.empName}" styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText id="conditionIDForEmp" value="#{grsIntgResources.condition}"
                                      styleClass="td_key nowrap_txt"/>
                        <t:panelGroup colspan="3">
                            <t:inputTextarea disabled="true" forceId="true" id="conditionNamId"
                                             value="#{conditionLinesdetailsHelperBean.conditionName}"
                                             styleClass="#{(conditionLinesdetailsHelperBean.conditionStatus == true)? 'textboxlarge apply td_value':'textboxlarge dont_apply td_value'}"
                                             style=" width: 358px; "/>
                        </t:panelGroup>
                        <t:panelGroup rendered="#{(conditionLinesdetailsHelperBean.exceptionType != null && conditionLinesdetailsHelperBean.exceptionType != '')}">
                            <t:selectBooleanCheckbox id="ExceptionFromChkId"
                                                     value="#{conditionLinesdetailsHelperBean.exceptionFromCondition}"
                                                     disabled="true"/>
                            <t:outputText value="#{grsIntgResources.exceptionFrom}" styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText id="ExcepTypeIDForEmp" value="#{grsIntgResources.exceptionType}"
                                      styleClass="td_key nowrap_txt" style="margin-right:67px;" rendered="#{(conditionLinesdetailsHelperBean.exceptionType != null && conditionLinesdetailsHelperBean.exceptionType != '')}"/>
                        <t:panelGroup colspan="2" rendered="#{(conditionLinesdetailsHelperBean.exceptionType != null && conditionLinesdetailsHelperBean.exceptionType != '')}">
                            <t:inputText disabled="true" forceId="true" id="ExceptionTypeIDForEmpOut"
                                         style="width: 210px;"
                                         value="#{(conditionLinesdetailsHelperBean.exceptionType == 1)? grsIntgResources.exceptionTypeValid : grsIntgResources.exceptionTypeInValid}"
                                         styleClass="td_value"/>
                        </t:panelGroup>
                        <h:outputText id="linesListId" value="#{grsIntgResources.lines}"
                                      styleClass="td_key nowrap_txt"/>
                        <t:panelGroup styleClass="textarea_GRS td_value" colspan="3">
                            <t:dataList forceId="true" id="linesList" var="list"
                                        value="#{conditionLinesdetailsHelperBean.linesDTOList}">
                                <t:outputText value="#{(list.rightArcs !=null)? list.rightArcs:''}"
                                              styleClass="conditions"/>
                                <t:outputText value="#{list.linesDTO.name}" onmouseout="hideTip();"
                                              onmouseover="doTooltip(event,getToolTipHTML('#{(list.linesDTO.actualLineValue != null)? list.linesDTO.actualLineValue : grsIntgResources.noActualValue}','#{(list.lineStatus == 1)? grsIntgResources.valid : grsIntgResources.unvalid}','#{(list.exceptionDTO.exceptionType == 1)? grsIntgResources.exceptionTypeValid : grsIntgResources.exceptionTypeInValid}','#{(list.exceptionStatus == 1) ? grsIntgResources.valid : grsIntgResources.unvalid}','#{(list.exceptionDTO != null && list.exceptionStatus != null) ? true : false}','#{(list.lineStatus ==1)?'green':'red'}'));"
                                              styleClass="#{(list.lineStatus ==1)?'apply':'dont_apply'}"/>
                                <t:outputText value="#{(list.leftArcs !=null)? list.leftArcs:''}"
                                              styleClass="conditions"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                                <t:outputText value="#{(list.joinSign !=null )? list.joinSign:''}"
                                              styleClass="conditions"/>
                                <f:verbatim>
                                    <br/>
                                </f:verbatim>
                            </t:dataList>
                        </t:panelGroup>
                    </t:panelGrid>
                </t:panelGrid>
                
            </t:panelGroup>
            <t:panelGroup styleClass="footer_rep" style="display:block;">
                    <t:outputText styleClass="copyrights" value="#{grsIntgResources.copyrights}"/>
                </t:panelGroup>
        </t:panelGroup>
        <script type="text/javascript">
          function getToolTipHTML(param1, param2, param3, param4, valid, style) {
              var str = ' <div class=\"' + style + '\">';
              if (style == 'green') {
                  str += '<table class=\"valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">' + param1 + '</td></tr>';
                  str += '<tr><td>الحالة:</td><td>' + param2 + '</td></tr>';
              }
              else {
                  str += '<table class=\"not_valid_style\"><tr><td>حالة الموظف:</td><td nowrap=\"nowrap\">' + param1 + '</td></tr>';
                  str += '<tr><td>الحالة:</td><td>' + param2 + '</td></tr>';
              }
              if (valid == 'true') {
                  str += '<tr><td>نوع الاستثناء:</td><td>' + param3 + '</td></tr>';
                  str += '<tr><td>مستثنى من السطور:</td>';
                  str += '<td>' + param4 + '</td></tr>';
              }
              return str;

          }
        </script>
    </h:form>
</f:view>
