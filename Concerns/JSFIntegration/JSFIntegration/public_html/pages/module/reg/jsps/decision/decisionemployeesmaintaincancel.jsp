<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<f:view locale="#{shared_util.locale}">


 <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>


 <h:form id="myForm" binding="#{decisionEmployeesMaintainBean.frm}">

    <t:aliasBean alias="#{pageBeanName}" value="#{decisionMaintainBean}">
    <t:aliasBean alias="#{detailBeanName}" id="decisionEmployeesMaintainBeancancel" value="#{decisionEmployeesMaintainBean}">

    
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
    <t:saveState value="#{detailBeanName.cancelDecisionMode}" id="cancelDecisionMode"/>
    
    <tiles:insert definition="decisionemployeesmaintaincancel.page" flush="false">
    
        
                <%--<tiles:put name="delalert" type="string">
                <t:panelGrid columns="1" width="100%">
                    <t:panelGroup forceId="true" id="divDeleteAlert" styleClass="delDivScroll">
                        <t:dataTable id="dataT_Delete" var="list" value="#{detailBeanName.selectedCurrentDetails}" preserveDataModel="false" renderedIfEmpty="false" footerClass="grid-footer" sortable="false"
                                     styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center" rowIndexVar="index" dir="rtl">
                            <t:column width="25%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="code" value="#{globalResources.Code}"/>
                                </f:facet>
                                <h:outputText value="#{list.empEmployeesDTO.code.civilId}"/>
                            </t:column>
                            <t:column width="75%" defaultSorted="true">
                                <f:facet name="header">
                                    <h:outputText id="name" value="#{globalResources.TableName}"/>
                                </f:facet>
                                <h:outputText id="popup_name" value="#{list.empEmployeesDTO.citizensResidentsDTO.firstName} #{list.empEmployeesDTO.citizensResidentsDTO.secondName} #{list.empEmployeesDTO.citizensResidentsDTO.thirdName} #{list.empEmployeesDTO.citizensResidentsDTO.lastName}"/>
                            </t:column>
                        </t:dataTable>
                    </t:panelGroup>
                    <t:panelGrid columns="2" align="center">
                        <h:commandButton id="delOk" value="#{globalResources.ok}" action="#{detailBeanName.deleteDiv}" styleClass="cssButtonSmall"/>
                        <h:commandButton id="delCancel" type="button" value="#{globalResources.CancelButton}" onclick="hideLookUpDiv(window.blocker,window.delAlert,null,null);" styleClass="cssButtonSmall"/>
                    </t:panelGrid>
                </t:panelGrid>
            </tiles:put>--%>
         <%--<tiles:put name="content1" type="string">
             <h:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" >     
                  <!--- Start of Row 1-->
                      <h:outputText value="#{regResources.type}" />
                      <t:inputText forceId="true"  id="add_type" size="20" maxlength="200" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.typesDTO.name}" readonly="true"/>

                      <h:outputText value="#{regResources.issuance_year}" />
                      <t:inputText forceId="true"  id="add_year" size="20" maxlength="200" styleClass="textboxmedium" value="#{pageBeanName.pageDTO.yearsDTO.name}" readonly="true"/>

                 <!--- Start of Row 2-->
                      <h:outputText value="#{regResources.decision_number}" />
                      <t:inputText forceId="true"  id="add_no" styleClass="textboxmedium" value="#{(pageBeanName.maintainMode==0) ? pageBeanName.pageDTO.decisionNumber : pageBeanName.pageDTO.code.decisionNumber}" readonly="true"/>
              </h:panelGrid>
        </tiles:put>--%>
        
        </tiles:insert>
    </t:aliasBean>
    </t:aliasBean>
  <c2:scriptGenerator form="myForm"/>
 </h:form>
</f:view>