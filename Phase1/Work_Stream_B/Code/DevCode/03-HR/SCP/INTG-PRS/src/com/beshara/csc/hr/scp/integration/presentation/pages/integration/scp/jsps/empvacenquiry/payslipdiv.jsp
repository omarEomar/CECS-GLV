<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<htm:style>
.paySlipTabel2 {
    width: 594px; !important;
}


</htm:style>
  
            <t:panelGrid columns="2" width="100%" forceId="true" id="payslipDataPanel" rowClasses="row02,row01" cellpadding="0" cellspacing="0">
                <t:panelGroup colspan="2">
                    <htm:table width="100%">
                        <htm:tr>
                            <htm:td width="9">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                         height="9"/>
                            </htm:td>
                            <htm:td styleClass="group_title">
                                &nbsp; 
                                <t:outputLabel value="#{scpIntgResources.merits}" styleClass="lable01"/>
                            </htm:td>
                        </htm:tr>
                         
                        <htm:tr>
                            <htm:td colspan="2" height="1">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"
                                         width="100%" height="1"/>
                            </htm:td>
                        </htm:tr>
                    </htm:table>
                    <f:verbatim/>
                    </t:panelGroup>
                   
                
                    <%-- ********************************************** el este7qaqaaaat yasta ;)
                         ***************************************--%>
               
            <t:panelGroup  forceId="true" id="meritsDiv" styleClass="paySlipTabel2" style="background-color: #ffffff;width:594px; height: 150px; overflow-y: auto;" colspan="2">
            <t:dataTable forceId="true" id="mdataT_delete" var="list" value="#{pageBeanName.meritsList}"
                         rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.meritsDataTable}"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                         dir="#{shared_util.pageDirection}"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column">
                <t:column id="mcode_column" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_mcode" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_mcode" value="#{list.financialGuideCode}"/>
                </t:column>
                <t:column id="mname_column" width="30%">
                    <f:facet name="header">
                        <h:outputText id="header_mname" value="#{scpIntgResources.sal_elm_guide}"/>
                    </f:facet>
                    <h:outputText id="content_mname" value="#{list.financialGuideName}"/>
                </t:column>
                <t:column id="emp_column" width="20%">
                    <f:facet name="header">
                        <h:outputText id="header_percentage" value="#{scpIntgResources.emp_vac_percentage}"/>
                    </f:facet>
                    <t:panelGroup>
                        <t:inputText forceId="true" id="content_percentage" maxlength="3" value="#{list.elementRatio}" disabled="#{!pageBeanName.editMode}"
                                     styleClass="textboxsmall" onkeyup="enabelIntegerOnlyWithZero(this);" onblur=""/>
                        <t:outputText value=" % " styleClass="lable01"/>
                        <t:outputText id="valueErr3" forceId="true" value="#{globalResources.missingField}"
                                  styleClass="errMsg" style="display:none;"/>
                    </t:panelGroup>
                </t:column>
                <t:column id="mvalue_column" width="20%">
                    <f:facet name="header">
                        <t:outputText id="header_mvalue" value="#{scpIntgResources.sal_base}"/>
                    </f:facet>
                    <t:selectOneMenu forceId="true" id="salBaseList" styleClass="Dropdownbox" disabled="#{!pageBeanName.editMode}"
                                     value="#{list.basicCalcFlag}" converter="javax.faces.Long">
                        <%--<f:selectItem itemLabel="#{scpIntgResources.sal_base_undefined}"
                                      itemValue="#{pageBeanName.salBaseUndefined}"/>--%>
                        <f:selectItem itemLabel="#{scpIntgResources.sal_base_basic}"
                                      itemValue="#{pageBeanName.salBaseBasic}"/>
                        <f:selectItem itemLabel="#{scpIntgResources.sal_base_actual}"
                                      itemValue="#{pageBeanName.salBaseActual}"/>
                    </t:selectOneMenu>
                </t:column>
                <t:column id="mperoid_column" width="20%">
                    <f:facet name="header">
                        <t:outputText id="header_mperoid" value="#{scpIntgResources.sal_period}"/>
                    </f:facet>
                 
                    <t:selectOneMenu forceId="true" id="salPeriodList" styleClass="Dropdownbox"  disabled="#{!pageBeanName.editMode}"
                                     value="#{list.peroidCalcFlag}" converter="javax.faces.Long">
                        <%/*style="width:365px;"*/%>
                        <%--<f:selectItem itemLabel="#{scpIntgResources.sal_base_undefined}"
                                      itemValue="#{pageBeanName.salBaseUndefined}"/>--%>
                        <f:selectItem itemLabel="#{scpIntgResources.sal_period_percent}"
                                      itemValue="#{pageBeanName.salBaseBasic}"/>
                        <f:selectItem itemLabel="#{scpIntgResources.sal_period_completeValue}"
                                      itemValue="#{pageBeanName.salBaseActual}"/>
                    </t:selectOneMenu>
                </t:column>
            </t:dataTable>
        </t:panelGroup>
                       
                   
                    <%-- ********************************************** el esteqta3aaaat yasta ;)
                         ***************************************--%>
                         
                          <t:panelGroup colspan="2">
                    <htm:table width="100%">
                        <htm:tr>
                            <htm:td width="9">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/op_arrow.jpg" width="9"
                                         height="9"/>
                            </htm:td>
                            <htm:td styleClass="group_title">
                                &nbsp; 
                                <t:outputLabel value="#{scpIntgResources.deductions}" styleClass="lable01"/>
                            </htm:td>
                        </htm:tr>
                         
                        <htm:tr>
                            <htm:td colspan="2" height="1">
                                <htm:img src="#{shared_util.contextPath}/app/media/images/seprator_group.jpg"
                                         width="100%" height="1"/>
                            </htm:td>
                        </htm:tr>
                    </htm:table>
                </t:panelGroup>
                  
                        <t:panelGroup  colspan="2" forceId="true" id="deductionsDiv" styleClass="paySlipTabel2" style="width:594px; height: 150px; overflow-y: auto;">
                            <t:dataTable forceId="true" id="ddataT_delete" var="list"
                                         value="#{pageBeanName.deductionsList}" rowIndexVar="index"
                                         renderedIfEmpty="false" binding="#{pageBeanName.deductionsDataTable}"
                                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_delete');"
                                         footerClass="grid-footer" styleClass="grid-footer"
                                         headerClass="standardTable_Header"
                                         rowClasses="standardTable_Row1,standardTable_Row2" width="100%" align="center"
                                         dir="#{shared_util.pageDirection}"
                                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column">
                               
                                <t:column id="dcode_column" width="10%">
                                    <f:facet name="header">
                                        <h:outputText id="header_dcode" value="#{globalResources.Code}"/>
                                    </f:facet>
                                    <h:outputText id="content_dcode" value="#{list.financialGuideCode}"/>
                                </t:column>
                                <t:column id="dname_column" width="30%">
                                    <f:facet name="header">
                                        <h:outputText id="header_dname" value="#{scpIntgResources.sal_elm_guide}"/>
                                    </f:facet>
                                    <h:outputText id="content_dname" value="#{list.financialGuideName}"/>
                                </t:column>
                                
                                <t:column id="emp_columnxee" width="20%">
                                    <f:facet name="header">
                                        <h:outputText id="header_percentagex" value="#{scpIntgResources.emp_vac_percentage}"/>
                                    </f:facet>
                                  <t:panelGroup>
                                        <t:inputText disabled="#{!pageBeanName.editMode}" forceId="true" id="ratio2" maxlength="3" value="#{list.elementRatio}"
                                                     styleClass="textboxsmall" onkeyup="enabelIntegerOnlyWithZero(this);" onblur=""/>
                                           <t:outputText value=" % " styleClass="lable01"/>
                                                                <t:outputText id="valueErr2" forceId="true" value="#{globalResources.missingField}"
                                  styleClass="errMsg" style="display:none;"/>
                                 </t:panelGroup>
                                </t:column>
                                <t:column id="mvalue_columnx" width="20%">
                                    <f:facet name="header">
                                        <t:outputText id="header_mvaluex" value="#{scpIntgResources.sal_base}"/>
                                    </f:facet>
                                     <t:selectOneMenu forceId="true" id="salBaseList2" styleClass="Dropdownbox"
                                                      value="#{list.basicCalcFlag}" disabled="#{!pageBeanName.editMode}"
                                                     converter="javax.faces.Long">
                                        <%--<f:selectItem itemLabel="#{scpIntgResources.sal_base_undefined}"
                                                      itemValue="#{pageBeanName.salBaseUndefined}"/>--%>
                                        <f:selectItem itemLabel="#{scpIntgResources.sal_base_basic}"
                                                      itemValue="#{pageBeanName.salBaseBasic}"/>
                                        <f:selectItem itemLabel="#{scpIntgResources.sal_base_actual}"
                                                      itemValue="#{pageBeanName.salBaseActual}"/>
                                      </t:selectOneMenu>
                                </t:column>
                                <t:column id="mperoid_ffff" width="20%">
                                    <f:facet name="header">
                                        <t:outputText id="header_fffff" value="#{scpIntgResources.sal_period}"/>
                                    </f:facet>
                                    <t:selectOneMenu forceId="true" id="salPeriodList2" styleClass="Dropdownbox"  disabled="#{!pageBeanName.editMode}"
                                                 value="#{list.peroidCalcFlag}" converter="javax.faces.Long">
                                    <%/*style="width:365px;"*/%>
                                    <%--<f:selectItem itemLabel="#{scpIntgResources.sal_base_undefined}"
                                                  itemValue="#{pageBeanName.salBaseUndefined}"/>--%>
                                    <f:selectItem itemLabel="#{scpIntgResources.sal_period_percent}"
                                                  itemValue="#{pageBeanName.salBaseBasic}"/>
                                    <f:selectItem itemLabel="#{scpIntgResources.sal_period_completeValue}"
                                                  itemValue="#{pageBeanName.salBaseActual}"/>
                                   </t:selectOneMenu>
        
                                </t:column>
            </t:dataTable>
                    <t:panelGroup>      
                <t:inputHidden value="#{pageBeanName.dedsSize}"   id="dedsSizeInputHidden" forceId="true"/>   
                <t:inputHidden value="#{pageBeanName.meritsSize}" id="meritsSizeInputHidden" forceId="true"/>      
           </t:panelGroup>
                        </t:panelGroup>
                       
          
            </t:panelGrid>

<t:panelGrid columns="2" forceId="true" id="payslipBtnPanel" border="0" align="center"  width="100px" >

  <t:commandButton forceId="true" id="SaveButton" styleClass="cssButtonSmall"  rendered="#{pageBeanName.editMode}"
             value="#{globalResources.SaveButton}" disabled="#{(empty pageBeanName.meritsList) && (empty pageBeanName.deductionsList)}"
             action="#{pageBeanName.update}" onclick="return validateRequiredRatio();" />     
     <t:commandButton id="backButtonCustomDiv1" forceId="true" 
                         styleClass="cssButtonSmall" value="#{globalResources.back}" 
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1);return false;">
        </t:commandButton>
  </t:panelGrid> 
  
  
<t:saveState value="#{pageBeanName.showPaySlipPanel}"/>
<t:saveState value="#{pageBeanName.meritsList}"/>
<t:saveState value="#{pageBeanName.deductionsList}"/>
<t:saveState value="#{pageBeanName.editMode}" />
<t:saveState value="#{pageBeanName.dedsSize}"/>
<t:saveState value="#{pageBeanName.meritsSize}"/>

<f:verbatim>
    <script type="text/javascript">
    

      function validateRequiredRatio() {

          var dedSize = document.getElementById('dedsSizeInputHidden').value;
          var meritSize = document.getElementById('meritsSizeInputHidden').value;
          var validTable = true;
          var validDedTable=true;

          for (var i = 0;i < meritSize;i++) {
              var ratioValue = document.getElementById('content_percentage' + '[' + i + ']').value;
              document.getElementById('valueErr3' + '[' + i + ']').style = "display: none;";
              if (ratioValue == null || ratioValue == '' ) {
                  document.getElementById('valueErr3' + '[' + i + ']').style = "display: inline;";
                  validTable = false;
                
              }

          }
          for (var i = 0;i < dedSize;i++) {
              var ratioValue = document.getElementById('ratio2' + '[' + i + ']').value;
               document.getElementById('valueErr2' + '[' + i + ']').style = "display: none;";
              if (ratioValue == null || ratioValue == '' ) {
                  document.getElementById('valueErr2' + '[' + i + ']').style = "display: inline;";
                  validDedTable = false;
                
              }

          }
        

          return validTable && validDedTable;
      }

 
    </script>
</f:verbatim>