<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" style="height: 303px; width: 721px;">

        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="itemIndex"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" 
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" footerClass="grid-footer-totale"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top"  
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}"
                     sortAscending="#{pageBeanName.ascending}">
            
            <t:column id="radio_column" styleClass="standardTable_Header3" width="3%"  footercolspan="3">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
                <f:facet name="footer" >
                    <h:outputText   forceId="true" id="content_sheetDateVal1" value="#{integrationBundle.total}" style="font-family: Arial; font-size: 16px; font-weight: bold;" /> 
                </f:facet>
              </t:column>
            <t:column id="code-salsheetCode_column" sortable="false" width="8%"  >
                <f:facet name="header">
                    <t:commandLink id="sort_code-salsheetCode" forceId="true" styleClass="headerLink" value="#{integrationBundle.sheet_no}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-salsheetCode') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-salsheetCode') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_code-salsheetCode" value="#{list.code.salsheetCode}"/>
         </t:column>
         
         <t:column id="sheetDateVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_sheetDateVal" forceId="true" styleClass="headerLink" value="#{integrationBundle.sheet_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='sheetDateVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='sheetDateVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_sheetDateVal" value="#{list.sheetDateVal}">
                <%--<f:converter converterId="TimeStampConverter" />--%>
                </h:outputText>
         </t:column>
         
          <t:column id="basicVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_basicVal" forceId="true" styleClass="headerLink" value="#{integrationBundle.main_insurance}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='basicVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='basicVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink> 
                </f:facet>
                <h:outputText id="content_basicVal" value="#{list.basicVal}" converter="DoubleThreeDigitConverter"/>
                <f:facet name="footer">
                    <h:outputText id="content_basicVal1" value="#{pageBeanName.salSalarySheetsDTO.basicVal}" converter="DoubleThreeDigitConverter"/>
                </f:facet>
            </t:column>
             <t:column id="takmeelyVal_column" sortable="false" width="8%" > 
                <f:facet name="header">
                    <t:commandLink id="sort_takmeelyVal" forceId="true" styleClass="headerLink" value="#{integrationBundle.supplementary_insurance}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='takmeelyVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='takmeelyVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_takmeelyVal" value="#{list.takmeelyVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_takmeelyVal1" value="#{pageBeanName.salSalarySheetsDTO.takmeelyVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="zamalaVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_zamalaVal" forceId="true" value="#{integrationBundle.increase_pensions_fund}" 
                        actionListener="#{pageBeanName.sortDataTable}" styleClass="headerLink">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='zamalaVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='zamalaVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_zamalaVal" value="#{list.zamalaVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_zamalaVal1" value="#{pageBeanName.salSalarySheetsDTO.zamalaVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="maashatVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_maashatVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.financial_reward_fund}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='maashatVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='maashatVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_maashatVal" value="#{list.maashatVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_maashatVal1" value="#{pageBeanName.salSalarySheetsDTO.maashatVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="daamVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_daamVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.support_equivalent}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='daamVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='daamVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_daamVal" value="#{list.daamVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_daamVal1" value="#{pageBeanName.salSalarySheetsDTO.daamVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="estbdalVal_column" sortable="false" width="8%" > 
                <f:facet name="header">
                    <t:commandLink id="sort_estbdalVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.installments_exchange}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='estbdalVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='estbdalVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_estbdalVal" value="#{list.estbdalVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_estbdalVal1" value="#{pageBeanName.salSalarySheetsDTO.estbdalVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="notMerVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_notMerVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.non_deserved}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='notMerVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='notMerVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_notMerVal" value="#{list.notMerVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_notMerVal1" value="#{pageBeanName.salSalarySheetsDTO.notMerVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="install_takmeelyVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_install_takmeelyVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.supplementary_installments}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='install_takmeelyVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='install_takmeelyVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_install_takmeelyVal" value="#{list.install_takmeelyVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_install_takmeelyVal1" value="#{pageBeanName.salSalarySheetsDTO.install_takmeelyVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="otherVal_column" sortable="false" width="8%" >
                <f:facet name="header">
                    <t:commandLink id="sort_otherVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.other_insurance}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='otherVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='otherVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_otherVal" value="#{list.otherVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_otherVal1" value="#{pageBeanName.salSalarySheetsDTO.otherVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
             <t:column id="totalVal_column" sortable="false" width="9%" >
                <f:facet name="header">
                    <t:commandLink id="sort_totalVal" forceId="true" styleClass="headerLink" 
                        value="#{integrationBundle.total}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='totalVal') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='totalVal') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                </f:facet>
                <t:outputText id="content_totalVal" value="#{list.totalVal}" converter="DoubleThreeDigitConverter" />
                <f:facet name="footer">
                    <t:outputText id="content_totalVal1" value="#{pageBeanName.salSalarySheetsDTO.totalVal}" converter="DoubleThreeDigitConverter" />
                </f:facet>
           </t:column>
           
           
           
        </t:dataTable>
        
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
    </t:panelGroup>
    
</t:panelGrid>


