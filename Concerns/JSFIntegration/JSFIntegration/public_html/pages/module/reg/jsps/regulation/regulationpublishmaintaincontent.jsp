<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<!-- Hidden Fields Related To JS Function validateTwoDatesInDataTable -->
<t:inputHidden forceId="true" value="#{pageBeanName.pageDTO.regulationDate}"  id="mainRegDateID">
       <f:converter converterId="TimeStampConverter"/>
</t:inputHidden>
<!-- -->

              <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                           value="#{detailBeanName.currentDisplayDetails}"
                           rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                           rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');"
                           footerClass="grid-footer" styleClass="grid-footer"
                           headerClass="standardTable_Header"
                           rowClasses="standardTable_Row1,standardTable_Row2"
                           columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                           width="100%" align="center"
                           dir="#{shared_util.pageDirection}" preserveSort="true">
                   <t:column id="check_column_" width="5%" rendered="#{pageBeanName.maintainMode!=2}">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" rendered="#{pageBeanName.maintainMode!=2}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}"  rendered="#{pageBeanName.maintainMode!=2}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                        </t:selectBooleanCheckbox>
                   </t:column>
                   
                   <t:column id="code_column" width="25%">
                        <f:facet name="header">
                            <h:outputText id="header_newsName" value="#{regResources.regulation_publish_news_name}"/>
                             <%--t:commandSortHeader id="newsNameColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/app/media/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/app/media/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_newsName" value="#{regResources.regulation_publish_news_name}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="news_name" value="#{list.newspapersDTO.name}"/>
                   </t:column>
                   
                   <t:column id="publish_date_column" width="25%">
                        <f:facet name="header">
                            <h:outputText id="header_publish_date" value="#{regResources.regulation_publish_publish_date}"/>
                        </f:facet>
                       <t:panelGroup>
                             <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" value="#{list.publishDate}" forceId="true" id="clndr_maintain_ministriesPublishDate_DataTableCalender"
                                size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" disabled="#{pageBeanName.maintainMode==2}"
                                renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                            <f:converter converterId="TimeStampConverter"/>
                            </t:inputCalendar> 
                            <t:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{pageBeanName.maintainMode!=2}"/>                        
                        </t:panelGroup>
                        <c2:dateFormatValidator componentToValidate="clndr_maintain_ministriesPublishDate_DataTableCalender"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}" active="#{regulationMaintainBean.maintainMode!=2}"
                                    highlight="false"
                                    isArray="true"
                                    uniqueId="publishDateformate_uniqueId"
                                    arraySize="#{regulationPublishMaintainBean.currentListSize}"/>
                        <c2:requiredFieldValidator componentToValidate="clndr_maintain_ministriesPublishDate_DataTableCalender" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            display="dynamic"
                                                            errorMessage="#{globalResources.missingField}"
                                                            highlight="false"
                                                            uniqueId="publishDaterequired_uniqueId"
                                                            isArray="true"
                                                            arraySize="#{regulationPublishMaintainBean.currentListSize}"/>
                        <c2:compareDateValidator componentToValidate="mainRegDateID"
                                                            componentToCompare="clndr_maintain_ministriesPublishDate_DataTableCalender" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            display="dynamic"
                                                            errorMessage="#{regResources.must_be_after_reg_date}"
                                                            operator="before"
                                                            highlight="false"
                                                            isArray="true"
                                                            uniqueId="publishDatecompare_uniqueId"
                                                            arraySize="#{regulationPublishMaintainBean.currentListSize}"/>
                   </t:column>
                   
                   <t:column id="until_date_column" width="25%">
                        <f:facet name="header">
                            <h:outputText id="header_until_date" value="#{regResources.regulation_publish_until_date}"/>
                            
                        </f:facet>
                       
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" value="#{list.untilDate}" forceId="true" id="clndr_maintain_ministriesuntilDate_DataTableCalender"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" disabled="#{pageBeanName.maintainMode==2}"
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                      
                      <c2:dateFormatValidator componentToValidate="clndr_maintain_ministriesuntilDate_DataTableCalender"
                                    display="dynamic" active="#{regulationMaintainBean.maintainMode!=2}"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    isArray="true"
                                    uniqueId="untilDateformate_uniqueId"
                                    arraySize="#{regulationPublishMaintainBean.currentListSize}"/>
                        <c2:compareDateValidator componentToValidate="clndr_maintain_ministriesPublishDate_DataTableCalender"
                                                            componentToCompare="clndr_maintain_ministriesuntilDate_DataTableCalender"
                                                            display="dynamic"
                                                            errorMessage="#{regResources.must_be_after_publish_date}"
                                                            operator="before" active="#{regulationMaintainBean.maintainMode!=2}"
                                                            highlight="false"
                                                            uniqueId="untilDatecompareID_uniqueId"
                                                            isArray="true"
                                                            arraySize="#{regulationPublishMaintainBean.currentListSize}"/>

                  </t:column>
                   
                   <t:column id="number_column" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_newsNumber" value="#{regResources.regulation_publish_number}"/>
                        </f:facet>
                        <h:inputText id="newsNumber_name" value="#{list.publishRefrence}" styleClass="textboxsmall2" maxlength="10" disabled="#{pageBeanName.maintainMode==2}" />
                   </t:column>
                   
                   <t:column id="newsNotes_column" width="10%">
                        <f:facet name="header">
                            <h:outputText id="header_newsNotes" value="#{regResources.regulation_publish_notes}"/>
                        </f:facet>
                        <h:inputText id="newsNotes_name" value="#{list.publishNotes}" styleClass="textbox" maxlength="#{pageBeanName.nameMaxLength}" disabled="#{pageBeanName.maintainMode==2}" />
                   </t:column>
                   
                   
              </t:dataTable>
			  <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <h:panelGrid columns="1" rendered="#{ detailBeanName.currentListSize == 0 && pageBeanName.maintainMode!=2 }">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}"/>
            </h:panelGrid>     
          <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"  value="clndr_maintain_ministriesPublishDate_DataTableCalender,215,50:clndr_maintain_ministriesuntilDate_DataTableCalender,190,50" />