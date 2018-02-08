<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<htm:style>
    #test10 tbody tr td {
                vertical-align: top;
    }
</htm:style>

<t:messages showDetail="true" />

<t:panelGrid columns="8" width="100%" forceId="true" id="mainDataEmpPanel" rowClasses="row01,row02" cellpadding="0" cellspacing="0" >

    
    
    <h:outputText value="#{resourcesBundle.budgetProgram}" styleClass="divtext"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="budgetProgram" styleClass="Dropdownbox"  style="margin: 0px 11px 0px -2px;" 
            value="#{pageBeanName.selbgtProgram}" onblur="setFousAtNextAfterComboItem();">
            <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
            <t:selectItems value="#{pageBeanName.bgtListPrograms}" itemLabel="#{bgtListProgram.name}" itemValue="#{bgtListProgram.code.key}" var="bgtListProgram"/>
        </t:selectOneMenu>
        <h:outputText value="#{resourcesBundle.select_bgt_level}" styleClass="errMsg" style="display:none"/>
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.work_level}" styleClass="divtext"/>
    <t:selectOneMenu forceId="true" id="WorkLevel" styleClass="Dropdownbox"  style="margin: 0px 11px 0px -2px;" 
        value="#{pageBeanName.selWrkLevel}" onblur="setFousAtNextAfterComboItem();">
        <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
        <t:selectItems value="#{pageBeanName.wrkLevels}" itemLabel="#{wrkLevel.name}" 
            itemValue="#{wrkLevel.code.key}" var="wrkLevel"/>
        <a4j:support actionListener="#{pageBeanName.getWorkCentersByOrganizationLevel}" event="onchange"
                                 reRender="WorkCenter"/>
    </t:selectOneMenu>
    
    
    <h:outputText value="#{resourcesBundle.work_center}" styleClass="divtext"/>
     <t:selectOneMenu forceId="true" id="WorkCenter" styleClass="Dropdownbox"  style="margin: 0px 11px 0px -2px;" 
        value="#{pageBeanName.selWrkCenter}" onblur="setFousAtNextAfterComboItem();">
        
        <f:selectItem itemLabel="#{resourcesBundle.all}" itemValue="#{pageBeanName.virtualConstValue}"/>
        <t:selectItems value="#{pageBeanName.wrkCenters}" itemLabel="#{wrkCenter.name}" itemValue="#{wrkCenter.code.key}" var="wrkCenter"/>
    </t:selectOneMenu>
    
    <h:outputText value="#{resourcesBundle.month}" styleClass="divtext"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="monthMenu" styleClass="Dropdownbox"  style="margin: 0px 45px 0px -2px;" value="#{pageBeanName.month}">
            <t:selectItems value="#{pageBeanName.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}" var="months"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        
    </t:panelGroup>
    
    
    <h:outputText value="#{resourcesBundle.year}" styleClass="divtext"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="yearMenu" styleClass="Dropdownbox"  style="margin: 0px 11px 0px -2px;" value="#{pageBeanName.year}" onblur="setFousAtNextAfterComboItem();">
            <t:selectItems value="#{pageBeanName.years}" itemLabel="#{years.code.key}" itemValue="#{years.code.key}" var="years"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
    </t:panelGroup>
    
    <h:outputText value="#{resourcesBundle.account_status}" styleClass="divtext"/>
    <t:panelGroup>
        <t:selectOneMenu forceId="true" id="status" styleClass="Dropdownbox"  style="margin: 0px 11px 0px -2px;" value="#{pageBeanName.selStatus}" 
         converter="javax.faces.Long" onblur="setFousAtNextAfterComboItem();">
            <f:selectItem itemValue="#{pageBeanName.allStatus}" itemLabel="#{resourcesBundle.all_status}"/>
            <f:selectItem itemValue="#{pageBeanName.reviewed}" itemLabel="#{resourcesBundle.reviewed}"/>
            <f:selectItem itemValue="#{pageBeanName.notReviewed}" itemLabel="#{resourcesBundle.not_reviewed}"/>
        </t:selectOneMenu>
    </t:panelGroup>
    <t:panelGroup colspan="2" forceId="true" id="displayBtnPanel" >
        <a4j:commandButton id="display_btn_id" value="#{resourcesBundle.display}"  style="margin:0 15px 0;" 
             action="#{pageBeanName.loadEmpList}" styleClass="cssButtonSmall" 
            reRender="bankingDataPanel,paySlipAllDataPanel,dataT_data_panel" onblur="setFocusOnlyOnElement('myForm:resetData_btn_id');"/>
    </t:panelGroup>
    
</t:panelGrid>


<%--start employees datatable--%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" style="height:282px;">
        <%-- rowOnDblClick="rowOnDblClickJs('chk',#{index});" by Ashraf Gaber
            <a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}" reRender="divDeleteAlert,OperationBar,divEditLookup" oncomplete="changeVisibilityDiv(window.blocker,window.lookupEditDiv);">
                <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
                <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
                <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
                <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
                <a4j:actionparam name="action" value="pageBeanName.showEditDiv"/>
            </a4j:jsFunction>
            --%>

        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.monSalList}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             
               
              <t:column id="radio_column" styleClass="standardTable_Header3" width="5%">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" 
                    onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar,paySlipAllDataPanel,finDataEmpPanel,buttonsPanel"/>
                </t:selectOneRadio>
              </t:column>
              
               
                
            <t:column id="code_column" sortable="false"  width="" >
                <f:facet name="header">
                     
                    <t:commandLink  id="sort_employeesDTO-realCivilId" forceId="true" styleClass="headerLink" value="#{resourcesBundle.civil_id}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="content_code" value="#{list.employeesDTO.realCivilId}"/>
                
            </t:column>
            
            <t:column id="name_column" sortable="false" width="">
                <f:facet name="header">

                    <t:commandLink id="sort_employeesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.maintain_payment_types_name_label}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.name}"  styleClass="inputTextForDataTable"/>
            </t:column>
            
            <t:column id="bgtProgramsName_column" sortable="false" width="">
                <f:facet name="header">

                    <t:commandLink id="sort_employeesDTO-bgtProgramsDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.budgetProgram}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_budgetProgram" value="#{list.employeesDTO.bgtProgramsDTO.name}"  styleClass="inputTextForDataTable"/>
            </t:column>
            
            <t:column id="bgtTypes_column" sortable="false" width="">
                <f:facet name="header">

                    <t:commandLink id="sort_employeesDTO-bgtTypesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.budgetType}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_bgtTypes" value="#{list.employeesDTO.bgtTypesDTO.name}" styleClass="inputTextForDataTable"/>
            </t:column>
            
            <t:column id="workCenter_column" sortable="false" width="">
                <f:facet name="header">

                    <t:commandLink id="sort_employeesDTO-workCenterDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.work_center}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_workCenter"  value="#{list.employeesDTO.workCenterDTO.name}" styleClass="inputTextForDataTable"/>
            </t:column>
            
            <t:column id="degree_column" sortable="false" width="">
                <f:facet name="header">

                    <t:commandLink id="sort_degree" forceId="true" styleClass="headerLink" value="#{resourcesBundle.finance_degree}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <t:outputText id="content_degree"  value="#{list.employeesDTO.salEmpSalaryElementsDTO.salElementGuidesDTO.fullName}" styleClass="inputTextForDataTable"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
    </t:panelGroup>
    
</t:panelGrid>
<%--end employees datatable--%>

 <t:panelGrid id="buttonsPanel" forceId="true" rowClasses="row02,row01"  align="center" width="100%">
    <t:panelGrid columns="2" align="center">
        <t:commandButton id="reviewBtn" forceId="true" disabled="#{pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == null || pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == 9 || pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == 10}"
                         styleClass="cssButtonSmall" value="#{resourcesBundle.review_btn_label}" 
                         action="#{pageBeanName.applyReviewEmpMonSalCalc}">
        </t:commandButton>
        
        <t:commandButton id="cancelReviewBtn" forceId="true" disabled="#{pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == null || pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == 16 || pageBeanName.selectedPageDTO.paymentStatusDTO.paystatusCode == 17}"
                         styleClass="cssButtonSmall" value="#{resourcesBundle.cancel_review_btn_label}" 
                         action="#{pageBeanName.applyCancelReviewEmpMonSalCalc}">
        </t:commandButton>
        </t:panelGrid>
    </t:panelGrid>