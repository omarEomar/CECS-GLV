<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">

 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>

 <h:form id="myForm" >

    <t:aliasBean alias="#{pageBeanName}" value="#{regulationMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" value="#{regulationMinistriesMaintainBean}">

    
    <t:saveState value="#{pageBeanName.pageDTO}"/>
    <t:saveState value="#{pageBeanName.nextNavigationCase}"/>
    <t:saveState value="#{pageBeanName.previousNavigationCase}"/>
    <t:saveState value="#{pageBeanName.finishNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentNavigationCase}"/>
    <t:saveState value="#{pageBeanName.currentStep}"/>
    <t:saveState value="#{pageBeanName.maintainMode}"/>
    <t:saveState value="#{pageBeanName.detailsSavedStates}" id="detailsSavedStates"/>
    
    <t:saveState value="#{pageBeanName.renderSave}" id="mainmode2"/>
    <t:saveState value="#{pageBeanName.renderFinish}" id="mainmode3"/>
    <t:saveState value="#{detailBeanName.currentDetails}"/>
    <t:saveState value="#{detailBeanName.availableDetails}"/>
    <t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
    <t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
    <t:saveState value="#{detailBeanName.masterEntityKey}" id="mentitykey"/>    
    <t:saveState value="#{detailBeanName.saveButtonOverride}" id="mainmode4"/>
    <t:saveState value="#{detailBeanName.finishButtonOverride}" id="mainmode5"/>    
    
    <t:saveState value="#{detailBeanName.categories}" id="categories"/>    
    <t:saveState value="#{detailBeanName.govType}" id="govType"/>    
    <t:saveState value="#{detailBeanName.searchDto}" id="searchDto"/>       
    
    <t:saveState value="#{regulationMainDataMaintainBean.typesDTOKey}"/>
    <t:saveState value="#{regulationMainDataMaintainBean.yearsDTOKey}"/>
    <t:saveState value="#{pageBeanName.initializeTablesTab}"/>
    <tiles:insert definition="regulationministriesmaintain.page" flush="false">
    
        <tiles:put name="titlepage" type="string">                
            <h:outputText value="#{regResources.edit_regulation_master_title}" />
        </tiles:put>
         <tiles:put name="content1" type="string">
                <t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" >     
            <!--- Start of Row 1-->
                <h:outputText value="#{regResources.regulation_subjects_reg_type}" />
                <t:inputText forceId="true"  id="add_regType" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}" disabled="true"/>
                
                <h:outputText value="#{regResources.regulation_subjects_reg_year}" />
                <t:inputText forceId="true"  id="add_regYear" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.yearsDto.name}" disabled="true"/>
                
                <h:outputText value="#{regResources.regulation_subjects_reg_number}" />
                <t:inputText  rendered="#{pageBeanName.maintainMode==0}" forceId="true" id="add_regNoAdd" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.regulationNumber}" disabled="true"/>
                <t:inputText  rendered="#{pageBeanName.maintainMode==1}" forceId="true" id="add_regNoEdit" styleClass="textboxsmall2" value="#{detailBeanName.masterEntityKey.regulationNumber}" disabled="true"/>
            <!--- Start of Row 2-->
                <h:outputText value="#{regResources.regulation_description}"/>
                <t:panelGroup colspan="5">
                    <t:inputText disabled="true" styleClass="regTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.regulationTitle}"/>
                </t:panelGroup>
            </t:panelGrid>
            <t:panelGrid columns="5" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" >     
                <t:outputLabel value="#{regResources.regulation_ministries_apply_date}"/> 
                <t:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterApplyDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                                     currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                     value="#{detailBeanName.masterApplyDate}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                    <f:verbatim><br/> </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="masterApplyDate_clndrId"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                </t:panelGroup>
            
            
                <t:outputLabel value="#{regResources.regulation_ministries_cancel_date}"/>
                <t:panelGrid cellpadding="0" cellspacing="0" border="0" >
                <t:panelGroup>
                    <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterCancelDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                                     currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                                     value="#{detailBeanName.masterCancelDate}">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                </t:panelGroup>
                    <c2:dateFormatValidator componentToValidate="masterCancelDate_clndrId"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                    <c2:compareDateValidator componentToValidate="masterApplyDate_clndrId" componentToCompare="masterCancelDate_clndrId" operator="before" display="dynamic" errorMessage="#{resourcesBundle.startgreaterThanEnd}"/>
                </t:panelGrid>
                
                <t:commandButton onclick="return validatemyForm();" value="#{regResources.apply_on_all_ministries}" styleClass="cssButtonMedium" action="#{detailBeanName.applyOnAllMinistries}"/>
            </t:panelGrid>
        </tiles:put>
        <tiles:put name="content" type="string">        
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
                   <t:column id="check_column" width="5%">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                        </t:selectBooleanCheckbox>
                   </t:column>

                   <t:column id="name_column" sortable="true" width="95%">
                        <f:facet name="header">
                             <t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_name" value="#{regResources.regulation_ministries_minName}"/>
                             </t:commandSortHeader>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.minMinistriesDTO.name}"/>
                   </t:column>
                   <t:column id="apply_date_column" sortable="true" width="60%">
                        <f:facet name="header">
                             <t:commandSortHeader id="apply_dateColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_apply_date" value="#{regResources.regulation_ministries_apply_date}"/>
                             </t:commandSortHeader>
                        </f:facet>
                        <t:panelGroup>
                        <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" value="#{list.minMinistriesDTO.createdDate}" id="clndr_maintain_ministriesAppliedDate"
                            size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox" currentDayCellClass="currentDayCell" 
                            renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>         
                    <f:verbatim><br/> </f:verbatim>
                    <c2:dateFormatValidator componentToValidate="clndr_maintain_ministriesAppliedDate"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
                    </t:panelGroup>        
                   </t:column>
              </t:dataTable>
            <h:panelGrid columns="1" rendered="#{ detailBeanName.currentListSize == 0 }">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}"/>
            </h:panelGrid>     
        </tiles:put>
        </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
  <c2:scriptGenerator form="myForm"/>
  <script type="text/javascript">
        setFocusAtMyFirstElement();
        
        function setFocusAtMyFirstElement(){
            setFocusOnElement('masterApplyDate_clndrId');
        }
        
        function setFocusAtMyAddDiv(){
            setFocusOnButton('maintain_regType');
        }
        
        function setFocusAtMyDelDiv(){
            setFocusOnButton('CancelButtonDelAlertDiv');
        }
    </script>
 </h:form>
</f:view>