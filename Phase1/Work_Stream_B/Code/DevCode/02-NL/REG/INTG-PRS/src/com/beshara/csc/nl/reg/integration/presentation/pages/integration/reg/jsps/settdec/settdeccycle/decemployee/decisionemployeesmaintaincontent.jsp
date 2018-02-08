<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%> 
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

 <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regResources"/>
 
<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-5-row-filter">

       <!--rowStyleClass="#{list.disableFlage ? 'deletedRecordItemStylee' : standardTable_Row1}"-->
<!--rowStyleClass="#{detailBeanName.selected ? 'standardTable_RowHighlighted' : null}"-->
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
       value="#{detailBeanName.currentDisplayDetails}"
       rowIndexVar="index" binding="#{detailBeanName.currentDataTable}" rows="#{shared_util.noOfTableRows}"
       rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');" 
       footerClass="grid-footer" styleClass="grid-footer"
       rowStyleClass="#{ (list.statusFlag == '0'  && empty list.salElmGuideDTOList) ? 'standardTable_RowHighlighted_GRS_Rejected'  : null}"
       headerClass="standardTable_Header"
       rowClasses="standardTable_Row1,standardTable_Row2"
       columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
       width="100%" align="top" 
       dir="#{shared_util.pageDirection}" preserveSort="true">
       
       <t:column id="nncheck_column" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" disabled="#{pageBeanName.showOnly}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" disabled="#{list.disableFlage || pageBeanName.showOnly}">                                            
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="dataT_data_panel,divDeleteAlert,OperationBar" />
            </t:selectBooleanCheckbox>
       </t:column>
    
        <t:column id="civilid_column" width="15%">
            <f:facet name="header">
             <t:outputText id="sort_employeesDTO-code-civilId" value="#{regResources.civil_id}"/>
            </f:facet>
          <t:outputText id="content_civilid" value="#{list.employeesDTO.citizensResidentsDTO.code.civilId}"/>
        </t:column>
        
        
       <t:column id="name_column" width="30%">
            <f:facet name="header">
                <t:outputText id="sort_employeesDTO-citizensResidentsDTO-fullName" value="#{regResources.decision_employees_emp_name}"/>
            </f:facet>
            <t:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.fullName}" />
       </t:column>
       
       <t:column id="minId_column" width="25%">
            <f:facet name="header">
                <t:outputText id="sort_minId" value="#{regResources.select_decision_employees_geha}"/>
            </f:facet>
           <t:outputText id="sort_minOutId" value="#{list.employeesDTO.workCenterDTO.ministriesDTO.name}"/>
      </t:column>
         
      <t:column id="sett_column" width="25%">
        <f:facet name="header">
            <t:panelGroup>
               <t:outputText id="sort_sett_Detail" value=""/>
            </t:panelGroup>
        </f:facet>
          <t:commandButton id="detail_btn" value="#{regResources.sett_detail_btn}" styleClass="cssButtonSmall" 
                                   action="#{settlementDecisionCycleEmployeesMaintainBean.gotoSettlementDetail}"  
                                  >
           </t:commandButton> 
        
   </t:column>
</t:dataTable>
 
</t:panelGroup>
<t:saveState value="#{detailBeanName.currentDetails}"/>
<t:saveState value="#{detailBeanName.availableDetails}"/>
<t:saveState value="#{detailBeanName.selectedCurrentDetails}"/>
<t:saveState value="#{detailBeanName.selectedAvailableDetails}"/>
<t:saveState value="#{detailBeanName.currentDisplayDetails}"/>
<t:saveState value="#{detailBeanName.empAddedBefore}"/>
