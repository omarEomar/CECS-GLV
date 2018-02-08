<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<!-- Hidden Fields Related To JS Function validateTwoDatesInDataTable -->
                <t:inputHidden forceId="true" value="#{pageBeanName.pageDTO.regulationAppliedDate}"  id="mainApplyDateID">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputHidden>
                
              <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                           value="#{detailBeanName.currentDisplayDetails}"
                           rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                           rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');"
                           footerClass="grid-footer" styleClass="grid-footer"
                           headerClass="standardTable_Header"
                           rowClasses="standardTable_Row1,standardTable_Row2"
                           columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                           width="100%" align="center"
                           dir="#{shared_util.pageDirection}" preserveSort="true">
                   <t:column id="check_column" width="5%" rendered="#{pageBeanName.maintainMode!=2}">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" disabled="#{detailBeanName.disableCheckAllFlag == 1}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" disabled="#{list.statusFlag == null}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar"/>
                        </t:selectBooleanCheckbox>
                   </t:column>

                   <t:column id="name_column" width="45%">
                        <f:facet name="header">
                            <h:outputText id="header_name" value="#{regResources.regulation_ministries_minName}"/>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.minMinistriesDTO.name}"/>
                   </t:column>
                   <t:column id="apply_date_column" width="25%">
                        <f:facet name="header">
                            <h:outputText id="header_apply_date" value="#{regResources.regulation_ministries_apply_date}"/>
                        </f:facet>
                       
                      <t:panelGroup>
       
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" value="#{list.appliedDate}" forceId="true" id="clndr_maintain_ministriesAppliedDate_DataTableCalender"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"  disabled="#{pageBeanName.maintainMode==2}"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}"/>
                        </t:panelGroup>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_ministriesAppliedDate_DataTableCalender"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false" active="#{regulationMaintainBean.maintainMode!=2}"
                                    isArray="true"
                                    arraySize="#{regulationMinistriesMaintainBean.currentListSize}"
                                    uniqueId="applyDateformat_uniqueId"/>
                        <c2:requiredFieldValidator componentToValidate="clndr_maintain_ministriesAppliedDate_DataTableCalender"
                                                            display="dynamic"
                                                            errorMessage="#{globalResources.missingField}"
                                                            highlight="false" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            uniqueId="applyDaterequired_uniqueId"
                                                            isArray="true"
                                                            arraySize="#{regulationMinistriesMaintainBean.currentListSize}"/>
                        <c2:compareDateValidator componentToValidate="mainApplyDateID"
                                                            componentToCompare="clndr_maintain_ministriesAppliedDate_DataTableCalender"
                                                            display="dynamic"
                                                            errorMessage="#{regResources.must_be_after_reg_applay_date}"
                                                            operator="before"
                                                            highlight="false" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            uniqueId="applyDatecompareID_uniqueId"
                                                            isArray="true"
                                                            arraySize="#{regulationMinistriesMaintainBean.currentListSize}"/>
                   </t:column>
                   <t:column id="cancel_date_column" width="25%"><%-- rendered="#{pageBeanName.maintainMode == 1}"--%>
                        <f:facet name="header">
                            <h:outputText id="header_cancel_date" value="#{regResources.regulation_ministries_cancel_date}"/>
                        </f:facet>
                        
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" value="#{list.cancelDate}" forceId="true" id="clndr_maintain_ministriesCanceldDate_DataTableCalender"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell"  disabled="#{pageBeanName.maintainMode==2}"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_ministriesCanceldDate_DataTableCalender"
                                    display="dynamic" active="#{regulationMaintainBean.maintainMode!=2}"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    isArray="true"
                                    arraySize="#{regulationMinistriesMaintainBean.currentListSize}"
                                    uniqueId="cancelDateformat_uniqueId"/>
                        <c2:compareDateValidator componentToValidate="clndr_maintain_ministriesAppliedDate_DataTableCalender"
                                                            componentToCompare="clndr_maintain_ministriesCanceldDate_DataTableCalender"
                                                            display="dynamic" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            errorMessage="#{regResources.must_be_after_applay_date}"
                                                            operator="before"
                                                            highlight="false"
                                                            uniqueId="cancelDatecompareID_uniqueId"
                                                            isArray="true"
                                                            arraySize="#{regulationMinistriesMaintainBean.currentListSize}"/>
                        
                   </t:column>
              </t:dataTable>
              <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <h:panelGrid columns="1" rendered="#{ detailBeanName.currentListSize == 0 && pageBeanName.maintainMode!=2 }">
                    <h:outputText styleClass="msg" value="#{regResources.global_noTableResultsRegMinistries}"/>
            </h:panelGrid>     
 <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"  value="clndr_maintain_ministriesAppliedDate_DataTableCalender,50,50:clndr_maintain_ministriesCanceldDate_DataTableCalender,50,50" />
