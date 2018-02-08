<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
#dataT_data_panel {
height:240px;
}
</htm:style>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{pageBeanName.selectedPageDTO}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedRadio}"/>


    <t:panelGroup forceId="true" id="dataT_data_panel"  >
        <t:messages showDetail="true"/>
  
        <t:dataTable id="dataT_data" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
            
            
            <t:column id="check_column" width="3%">
            <f:facet name="header">
                <%--<t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}"
                    disabled="#{jobMaintainBean.disableJobConditionsTab || pageBeanName.viewOnly}">
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCurrentAll}"
                        oncomplete="setAll('checkAll', 'chk2');"
                        reRender="divDeleteAlert,OperationBar,searchMode"/>
                </t:selectBooleanCheckbox>--%>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" >
                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                       oncomplete="checkCheckAll('chk2');"
                       reRender="divDeleteAlert,OperationBar,searchMode"/>
            </t:selectBooleanCheckbox>
        </t:column>
        
            <t:column id="citizensResidentsDTO-civilId_column" sortable="false" width="10%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-civilId" forceId="true" styleClass="headerLink"
                                   value="#{jobIntgResources.civilID}" actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-civilId') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_citizensResidentsDTO-civilId" value="#{list.citizensResidentsDTO.civilId}"/>
            </t:column>
            
            
            <t:column id="citizensResidentsDTO-name_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_citizensResidentsDTO-name" forceId="true" styleClass="headerLink" value="#{jobIntgResources.empName}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='citizensResidentsDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_citizensResidentsDTO-name" value="#{list.citizensResidentsDTO.name}"/>
            </t:column>
            
            
            <t:column id="jobDTO-name_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_jobDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{jobIntgResources.job_title_lable}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='jobDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_jobDTO-name" value="#{list.jobDTO.name}"/>
            </t:column>
            
            <t:column id="workCenterDTO-name_column"  sortable="false" width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_workCenterDTO-name" forceId="true" styleClass="headerLink"
                                   value="#{jobIntgResources.work_center}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='workCenterDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>
                </f:facet>
                <h:outputText id="content_workCenterDTO-name" value="#{list.workCenterDTO.name}"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ pageBeanName.myListSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}"
                       binding="#{pageBeanName.reloadField}"/>
    
        <f:verbatim>
            <script language="javascript" type="text/javascript">
            //   adjustDataTable('myDataTableId');
            </script>
        </f:verbatim>
    </t:panelGroup>
