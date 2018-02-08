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


         
                     <f:verbatim>
               
               <table border="0" width="100%"  cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                        
                        <%
                        //if(typeCodeEx == null || typeCodeEx.equals(""))
                        //{
                            %>
                                <div style="width:100%;height:290px;overflow-y:auto;">
                                
                            <%
                        //}
                       
                     %>
                        <input type="hidden" name="typeCodeEx" value="<%=typeCodeEx%>"/>
                         <t:messages id="messageList" showSummary="true" showDetail="false" summaryFormat="{0}" style="color:Red;font-size: 8pt;" errorStyle="color:Red;font-size: 8pt;"/>
                <table border="0" width="100%"  cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="25%" class="row02" >
            </f:verbatim>
                            <h:outputText value="#{resourcesBundle.typeTitle}"/>
            <f:verbatim>
                        </td>
                        <td width="50%" class="row02" >
            </f:verbatim>
                            <t:selectOneMenu id="requestType" forceId="true" value="#{requestAddBean.selectType}"
                                             valueChangeListener="#{requestAddBean.setSelectedType}"
                                             onchange="submit();" disabled="#{requestAddBean.typeDisable}"
                                             styleClass="textboxlarge" >
                                <t:selectItems value="#{requestAddBean.typeList}" itemLabel="#{type.name}" itemValue="#{type.code.key}" var="type"/>
                            </t:selectOneMenu>
            <f:verbatim>
                            <font color="Red">*</font>
                        </td>
                            <td width="25%" class="row02"><span id="typeMsg" class="errMsg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%" class="row01">
            </f:verbatim>
                            <t:outputText value="#{resourcesBundle.reasonNameRE}" />
            <f:verbatim>
                        </td>
                        <td width="50%" class="row01">
            </f:verbatim>
                           <t:selectOneMenu  id="requestReason" forceId="true" 
                                              value="#{requestAddBean.selectReason}"
                                              
                                              styleClass="textboxlarge" >
                                <t:selectItems value="#{requestAddBean.reasonList}" itemLabel="#{reason.name}" itemValue="#{reason.code.key}" var="reason"/>
                                
                            </t:selectOneMenu>
            
            <f:verbatim>
                            <font color="Red">*</font>
            </td>
                            <td width="25%" class="row01"><span id="reasonMsg" class="errMsg"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="25%" class="row02">
            </f:verbatim>
                            <h:outputText value="#{resourcesBundle.requestDate}" />
            <f:verbatim>
                        </td>
                        <td width="50%" class="row02">
            </f:verbatim>
                            
                            <h:panelGroup>
                                      <%--<t:inputCalendar
                                      popupButtonImageUrl="/images/icon_calendar.jpg" 
                                      popupDateFormat="dd/MM/yyyy" forceId="true" id="requestActivateDate" 
                                      size="20"
                                      maxlength="200"
                                      styleClass="textbox"
                                      currentDayCellClass="currentDayCell"
                                      value="#{requestAddBean.requestsDTO.reqDate}" 
                                      renderAsPopup="true" 
                                      align="#{globalResources.align}"
                                      popupLeft="#{shared_util.calendarpopupLeft}" 
                                      renderPopupButtonAsImage="true">
                                        <f:converter converterId="SqlDateConverter"/>
                                    </t:inputCalendar>--%>
                                <t:inputText value="#{requestAddBean.dateNow}" id="activeDate" forceId="true" styleClass="textbox" readonly="true" dir="ltr" align="center"/>      
                                </h:panelGroup>     
                                
            <f:verbatim>
                            
            </td>
                            <td width="25%" class="row02">
            </f:verbatim>
                            
            <f:verbatim>
            
                        </td>
                    </tr>
                        <td width="25%" class="row01">
            </f:verbatim>
                            <h:outputText value="#{resourcesBundle.completeFlag}" />
            <f:verbatim>
                        </td>
                        <td width="50%" class="row01">
            </f:verbatim>
                            <h:selectBooleanCheckbox value="#{requestAddBean.completeFlag}"/>
            <f:verbatim>
            </td>
                            <td width="25%" class="row01">
                        </td>
                    </tr>
                        <td width="25%" class="row02" colspan="3">
            </f:verbatim>
            
                    
                    <!-- End of Basic part -->
                    
            
                    <!-- Details part
                        styleClass="#{(fieldRowIndex%2) == 0 ? 'row02' : 'row01'}"
                    -->
           
                    
            <t:dataList id="groupObject"
                        var="groupObject"
                        value="#{requestAddBean.allFieldsGroups}"
                        layout="simple"
                        rowCountVar="groupObjectRowCount"
                        rowIndexVar="groupObjectRowIndex" binding="#{requestAddBean.datalist}">
                    <f:verbatim>
                     <br>
                    </f:verbatim>
                    <!-- Start of Details Simple but in group and not related part -->
                    <t:dataList id="groupNonRelatedFieldsList"
                                var="fieldGroup"
                                value="#{requestAddBean.nonFieldGroups}"
                                layout="simple"
                                rowCountVar="fieldRowCount"
                                rowIndexVar="gfieldRowIndex">
                    
                    <h:panelGroup rendered="#{fieldGroup.fldgrpOrder == groupObject.fldgrpOrder}">
                        <f:verbatim>
                                <fieldset>
                                    <legend>
                            </f:verbatim>
                                        <h:outputText value="#{fieldGroup.name} " />                                        
                            <f:verbatim>
                                    </legend>
                            </f:verbatim>
                         <t:dataList id="nonRelatedFieldsList"
                                    var="requestField" 
                                    value="#{fieldGroup.requestFieldsDToList}"
                                    layout="simple"
                                    rowCountVar="fieldRowCount" 
                                    rowIndexVar="fieldRowIndex" >
                                  <t:panelGrid columns="2" cellpadding="0" cellspacing="0" id="panelGrid" rowClasses="row01,row02" columnClasses="col1,col2" binding="#{requestAddBean.panelFieldOneRecord}" rendered="#{(requestField.maxRecords == 1)}" >
                                        <t:panelGroup binding="#{requestAddBean.panelGroupCol1}" id="panelGroupCol1"/>
                                        <t:panelGroup binding="#{requestAddBean.panelGroupCol2}" id="panelGroupCol2"/>
                                    </t:panelGrid>
                            <!-- value="#{requestField.fieldsDTO.name}" reversed to make sure that locale controle the ui lanaguage-->                            
                            <%-- <f:verbatim>
                                <table align="Center" cellspacing="0" cellpadding="0" class="row01">
                                    <tr>
                                        <td style="width:25%">
                            </f:verbatim>
                                            <h:outputText value="#{requestField.fieldsDTO.name}  "/>
                                            <t:panelGroup rendered="#{(requestField.booleanMandatoryFlag) &&
                                                                    (requestField.maxRecords != 1)}" >
                                                    <f:verbatim><font color="Red">*</font></f:verbatim>
                                            </t:panelGroup>
                            <f:verbatim>
                                        </td>
                                        <td style="width:50%">
                            </f:verbatim>
                            
                                             <t:inputText value="#{requestField.fieldsDTO.requestDataDTOList[0].fldValue}" 
                                                          rendered="#{( (requestField.fieldsDTO.fieldTypesDTO.code.key == 1) || 
                                                                        (requestField.fieldsDTO.fieldTypesDTO.code.key == 2) ||
                                                                        (requestField.fieldsDTO.fieldTypesDTO.code.key == 4) 
                                                                       ) &&
                                                                       (empty requestField.fieldValues) &&
                                                                       (requestField.maxRecords == 1) }"
                                                                 styleClass="textboxmedium" maxlength="150" />
                                                    
                                                    <!-- value="#{requestField.fieldsDTO.fldCode}" -->
                                                    <t:selectOneMenu  id="requestFieldValues_1" 
                                                                      value="#{requestField.fieldsDTO.requestDataDTOList[0].fldValue}"
                                                                      rendered="#{(!empty requestField.fieldValues) &&
                                                                                  (requestField.maxRecords == 1)}"
                                                                      styleClass="textboxmedium" >
                                                        <t:selectItems value="#{requestField.fieldValues}"
                                                                       itemLabel="#{fieldValue.name}" 
                                                                       itemValue="#{fieldValue.code.key}" 
                                                                       var="fieldValue"/>
                                                    </t:selectOneMenu>
                                                    <!-- rendered="#{requestField.fieldsDTO.fieldTypesDTO.code.key == 1}" -->
                                                   <t:inputCalendar id="Date"                                                                     
                                                                     value="#{requestField.fieldsDTO.requestDataDTOList[0].fldValue.fldValue}"
                                                                     rendered="#{(requestField.fieldsDTO.fieldTypesDTO.code.key == 3) &&
                                                                                 (requestField.maxRecords == 1)}"
                                                                     renderAsPopup="true"
                                                                     helpText="#{group1.columnValue.fldValue}"
                                                                     popupDateFormat="dd/MM/yyyy"
                                                                     align="#{globalResources.align}"
                                                                     popupLeft="#{shared_util.calendarpopupLeft}"
                                                                     renderPopupButtonAsImage="true"
                                                                     styleLocation="/js/controls/#{globalResources.photoFolder}/calendar/"
                                                                     immediate="true"
                                                                     javascriptLocation="/js/controls/#{globalResources.photoFolder}/calendar/" 
                                                                     styleClass="textboxmedium" 
                                                                     size="#{group1.groupFieldsDTO[columnHeader.columnIndex].fieldLength}" >
                                                       <f:converter converterId="StringConverter" />
                                                   </t:inputCalendar>
                                                   
                                                   <t:panelGroup rendered="#{(requestField.booleanMandatoryFlag) &&
                                                                             (requestField.maxRecords == 1)}" >
                                                    <f:verbatim><font color="Red">*</font></f:verbatim>
                                                   </t:panelGroup>
                            <f:verbatim>
                                        </td>
                                        <td align="right" style="width:25%">
                            </f:verbatim>
                                            
                            <f:verbatim>
                                        </td>                                        
                                    </tr>
                                </table>
                            </f:verbatim>--%>
                            
                            <!-- styleClass="#{(fieldRowIndex%2) == 0 ? 'row02' : 'row01'}" -->
                            <t:dataList id="fieldsDataList"
                                    style="width:100%"
                                    var="requestDataField"
                                    value="#{requestField.fieldsDTO.requestDataDTOList}"
                                    layout="simple"
                                    rowCountVar="rowCount"
                                    rowIndexVar="rowIndex" binding="#{requestAddBean.dataListField}">
                                    <!-- value="#{requestField.fieldsDTO.name}"  -->   
                                    <t:panelGrid columns="2" rowClasses="row02,row01" columnClasses="col1,col2" binding="#{requestAddBean.panelField}" rendered="#{requestField.maxRecords != 1}">
                                        <t:panelGroup binding="#{requestAddBean.panelGroupColMax1}" id="panelGroupColMax1"/>
                                        <t:panelGroup binding="#{requestAddBean.panelGroupColMax2}" id="panelGroupColMax2"/>
                                    </t:panelGrid>
                                    
                            </t:dataList>
                        </t:dataList>
                        <f:verbatim>
                            </fieldset>
                        </f:verbatim> 
                        </h:panelGroup>
                    </t:dataList>
                    <!-- End of Details Simple but in group part -->
                    
                    <!--
                        Start of Fields that are related and in the same group
                        Groups Part
                    -->
            <t:dataList id="groupList"
                        var="group1"
                        value="#{requestAddBean.requestGroupFields.allData}"
                        layout="simple"
                        rowCountVar="fieldRowCount"
                        rowIndexVar="fieldRowIndex">
                <h:panelGroup rendered="#{group1.groupFieldsDTO[0].fieldGroupsDTO.fldgrpOrder == groupObject.fldgrpOrder}">
                <%--h:outputText value="#{resourcesBundle.groupNumber} #{(fieldRowIndex+1)} : " styleClass="row03"/--%>
                <h:outputText value="#{group1.groupFieldsDTO[0].fieldGroupsDTO.fldgrpTitle} : " />
                <!-- sortColumn="#{group1.sort}" -->
                <t:dataTable id="data"
                             styleClass="grid-footer"
                             headerClass="standardTable_Header"
                             footerClass="standardTable_Header"
                             rowClasses="standardTable_Row1,standardTable_Row2"                             
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                             var="row"
                             value="#{group1.data}"
                             preserveDataModel="false"
                             rows="100"                             
                             sortAscending="#{group1.ascending}"
                             preserveSort="true"
                             rowIndexVar="rowNumber"
                             width="80%">
                <t:columns id="columns" value="#{group1.columnHeaders}" 
                           var="columnHeader" style="width:#{group1.columnWidth}%">
                    <f:facet name="header">
                        <%--t:commandSortHeader columnName="#{columnHeader.label}" arrow="false" immediate="false" styleClass="standardTable_Header2">
                            <f:facet name="ascending">
                                <t:graphicImage value="../../images/ascending-arrow.gif" rendered="true" border="0"/>
                            </f:facet>
                            <f:facet name="descending">
                                <t:graphicImage value="../../images/descending-arrow.gif" rendered="true" border="0"/>
                            </f:facet--%>
                            <!-- to make sort works uncomment the above code and remove the panelGrid below -->
                            <t:panelGroup>
                                <h:outputText value="#{columnHeader.label}" styleClass="standardTable_Header2"/>
                                <h:outputText rendered="#{group1.groupFieldsDTO[columnHeader.columnIndex].booleanMandatoryFlag}" value="    *" style="Msg"/>
                            </t:panelGroup>
                        <%--/t:commandSortHeader--%>
                    </f:facet>
                    
                    <!-- row is also available
                         size="#{(group1.groupFieldsDTO[columnHeader.columnIndex].fieldLength == null)? 12 : group1.groupFieldsDTO[columnHeader.columnIndex].filedLength}"
                         rendered="#{group1.valueModifiable}"
                    -->
                    <t:panelGroup binding="#{requestAddBean.fieldRelatedPanal}">
                    </t:panelGroup>
                    <%--<h:inputText rendered="#{group1.controlType == 0}" value="#{group1.columnValue.fldValue}" 
                                 styleClass="textboxmedium" size="#{group1.groupFieldsDTO[columnHeader.columnIndex].fieldLength}" />
                    <%--h:outputText value="#{group1.columnValue}" 
                                     rendered="#{!group1.valueModifiable}"/--%>
                    <%--<t:selectOneMenu  id="requestFieldValues"
                                      rendered="#{group1.controlType == 2}"
                                      value="#{group1.columnValue.fldValue}"
                                      styleClass="textboxmedium" >
                        <t:selectItems value="#{group1.groupFieldsDTO[columnHeader.columnIndex].fieldValues}"
                                       itemLabel="#{fieldValue.name}"
                                       itemValue="#{fieldValue.code}"
                                       var="fieldValue"/>
                   </t:selectOneMenu>
                   
                   <t:inputCalendar id="Date"
                                    rendered="#{group1.controlType == 1}"
                                    value="#{group1.columnValue.fldValue}"
                                    renderAsPopup="true"
                                    helpText="#{group1.columnValue.fldValue}"
                                    popupDateFormat="dd/MM/yyyy"
                                    align="#{globalResources.align}"
                                    popupLeft="#{shared_util.calendarpopupLeft}"
                                    renderPopupButtonAsImage="true"
                                    styleLocation="/js/controls/#{globalResources.photoFolder}/calendar/"
                                    immediate="true"
                                    javascriptLocation="/js/controls/#{globalResources.photoFolder}/calendar/" 
                                    styleClass="textboxmedium" 
                                    size="#{group1.groupFieldsDTO[columnHeader.columnIndex].fieldLength}" >
                       <f:converter converterId="StringConverter" />
                   </t:inputCalendar>
                   <%--h:inputText value="requestAddForm:groupList:0:data:#{rowNumber}:columns:#{columnHeader.columnIndex}_#{columnHeader.columnIndex}:Date" /--%>
                   <%--c2:dateFormatValidator componentToValidate="requestAddForm:groupList:0:data:#{rowNumber}:columns:#{columnHeader.columnIndex}_#{columnHeader.columnIndex}:Date"
                                           display="dynamic" highlight="true" errorMessage="#{resourcesBundle.InvalidDateFormat}" /--%>
		</t:columns>
            </t:dataTable>

                
                </h:panelGroup>
            </t:dataList>
        </t:dataList>
            <!-- End Of Groups Part -->
                    <!-- End of Details part-->
<t:panelGrid binding="#{requestAddBean.panelGrid}">
</t:panelGrid>
            <f:verbatim>
                       </td>
                    </tr>
                     </table>
           <%
                        //if(typeCodeEx == null || typeCodeEx.equals(""))
                        //{
                            %>
                                </div>       
                            <%
                        //}
                     %>
                    </td>
               
                    <tr>
                        <td  align="center">
                            <table width="100%" align="center" border="0" cellspacing="1" cellpadding="1">
                                <tr>
                                    <td align="left">
                </f:verbatim>
                            <h:commandButton id="SaveButton" value="#{globalResources.SaveButton}" alt="Submit Request data" styleClass="cssButtonSmall" 
                                             action="#{requestAddBean.saveReq}" onclick="return validateBasicData();" />
                <f:verbatim>
                                    </td>                                    
                                   <td align="right" valign="bottom">
                </f:verbatim>
                            <%
                        if(typeCodeEx == null || typeCodeEx.equals(""))
                        {
                            %>
                                <h:commandButton styleClass="cssButtonSmall" id="CancelButton" value="#{globalResources.back}" alt="Cancel Request data" action="requestDatalist" immediate="true"/></td>
                            <%
                        }
                        else
                        {
                            %>
                                <h:commandButton styleClass="cssButtonSmall"  value="#{globalResources.back}" alt="Cancel Request data" type="button" onclick="window.opener='self';window.close()" immediate="true"/>       
                                
                            <%
                        }
                     %>
                            
                <f:verbatim>
                                  </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                  </table>
                  
                
            </f:verbatim>
            <t:panelGroup id="panelGroupFldCal" forceId="true">
                <t:saveState value="#{requestAddBean.fldCalCode}"/>
                <t:saveState value="#{requestAddBean.fldCalValue}"/>
                <t:inputHidden id="fldCalCode" forceId="true" value="#{requestAddBean.fldCalCode}"/>
                <t:inputHidden id="fldCalValue" forceId="true" value="#{requestAddBean.fldCalValue}"/>
                
            </t:panelGroup>
            
            