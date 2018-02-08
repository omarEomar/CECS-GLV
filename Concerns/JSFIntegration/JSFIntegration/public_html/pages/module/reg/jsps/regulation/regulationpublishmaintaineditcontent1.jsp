<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" >     
<!--- Start of Row 1-->
    <h:outputText value="#{regResources.regulation_subjects_reg_type}" />
    <t:inputText forceId="true"  id="add_regType" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_year}" />
    <t:inputText forceId="true"  id="add_regYear" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.yearsDto.code.key}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_number}" />
    <t:inputText  rendered="#{pageBeanName.maintainMode==0}" forceId="true" id="add_regNoAdd" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.regulationNumber}" disabled="true"/>
    <t:inputText  rendered="#{pageBeanName.maintainMode==1}" forceId="true" id="add_regNoEdit" styleClass="textboxsmall2" value="#{detailBeanName.masterEntityKey.regulationNumber}" disabled="true"/>
<!--- Start of Row 2-->
    <h:outputText value="#{regResources.regulation_description}"/>
    <t:panelGroup colspan="5">
        <t:inputText disabled="true" styleClass="regTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.regulationTitle}"/>
    </t:panelGroup>
</t:panelGrid>

<t:panelGrid forceId="true" id="webSitePanelGrid" columns="5" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" >     
    <htm:table>
        <htm:tr>
            <htm:td width="10px;">
                <h:panelGroup>
                    <t:selectBooleanCheckbox forceId="true" id="maintain_publicationFlag" value="#{pageBeanName.pageDTO.pulishOnWebsite}" onclick="pulishOnWebsiteChangedFunction();"/>
                    <a4j:jsFunction name="pulishOnWebsiteChangedFunction" reRender="webSitePanelGrid,scriptPanel" oncomplete="setFocusAtMyFirstElement();"/>
                </h:panelGroup>
            </htm:td>
            <htm:td width="130px;">
                <h:outputText value="#{regResources.regulation_publication_on_website}" styleClass="lable01"/>    
            </htm:td>
        </htm:tr>
    </htm:table>
    <t:outputLabel value="#{regResources.regulation_publish_publish_date}" rendered="#{pageBeanName.pageDTO.pulishOnWebsite != null && pageBeanName.pageDTO.pulishOnWebsite}"/>
    <t:panelGrid cellpadding="0" cellspacing="0" border="0" >
        <t:panelGroup rendered="#{pageBeanName.pageDTO.pulishOnWebsite != null && pageBeanName.pageDTO.pulishOnWebsite}">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterPublishDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                             currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             value="#{pageBeanName.pageDTO.pulishWebsite.publishDate}">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
            <t:outputText value="*" styleClass="mandatoryAsterisk"/>
        </t:panelGroup>
        <c2:dateFormatValidator componentToValidate="masterPublishDate_clndrId"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"
                                    active="#{regulationMaintainBean.pageDTO.pulishOnWebsite}"/>
        <c2:requiredFieldValidator active="#{regulationMaintainBean.pageDTO.pulishOnWebsite}" componentToValidate="masterPublishDate_clndrId" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false"/>
        <c2:compareDateValidator active="#{regulationMaintainBean.pageDTO.pulishOnWebsite}" componentToValidate="mainRegDateID"
                                                            componentToCompare="masterPublishDate_clndrId"
                                                            display="dynamic"
                                                            errorMessage="#{regResources.must_be_after_reg_date}"
                                                            operator="before"
                                                                highlight="false"/>
    </t:panelGrid>


    <t:outputLabel value="#{regResources.regulation_publish_until_date}" rendered="#{pageBeanName.pageDTO.pulishOnWebsite != null && pageBeanName.pageDTO.pulishOnWebsite}"/>
    <t:panelGrid cellpadding="0" cellspacing="0" border="0" >
        <t:panelGroup rendered="#{pageBeanName.pageDTO.pulishOnWebsite != null && pageBeanName.pageDTO.pulishOnWebsite}">
            <t:inputCalendar title="#{globalResources.inputCalendarHelpText}" popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy" forceId="true" id="masterCancelDate_clndrId" size="20" maxlength="#{pageBeanName.calendarTextLength}" styleClass="textbox"
                             currentDayCellClass="currentDayCell" renderAsPopup="true" align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true"
                             value="#{pageBeanName.pageDTO.pulishWebsite.untilDate}">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
        </t:panelGroup>
        <c2:dateFormatValidator active="#{regulationMaintainBean.pageDTO.pulishOnWebsite}" componentToValidate="masterCancelDate_clndrId"
                                    display="dynamic"
                                    errorMessage="#{globalResources.messageErrorForAdding}"
                                    highlight="false"/>
        <c2:compareDateValidator active="#{regulationMaintainBean.pageDTO.pulishOnWebsite}" componentToValidate="masterPublishDate_clndrId" componentToCompare="masterCancelDate_clndrId" operator="before" display="dynamic" errorMessage="#{regResources.must_be_after_publish_date}"/>
        
    </t:panelGrid>
</t:panelGrid>
<t:messages/>