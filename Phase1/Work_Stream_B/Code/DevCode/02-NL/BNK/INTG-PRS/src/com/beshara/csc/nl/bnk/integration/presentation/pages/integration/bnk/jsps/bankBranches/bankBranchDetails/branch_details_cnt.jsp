<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">

<t:panelGrid align="center" columns="6" border="0" 
    cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="98%" >
<t:panelGroup style="background-color:#ffffff;" colspan="6">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{integrationBundle.branch_coordinators}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
</t:panelGrid>
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});" sortAscending="#{pageBeanName.ascending}">
             
               
              <%--<t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>--%>
              
               
                
            <t:column id="civilId_column" sortable="false"  width="20%" >
                <f:facet name="header">
                    <t:commandLink id="sort_code-civilId" forceId="true" styleClass="headerLink" value="#{integrationBundle.CivilID}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-civilId') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_civilId" value="#{list.code.civilId}"/>
            </t:column>
            
            <t:column id="contactName_column" sortable="false" width="20%">
                <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{integrationBundle.contactName}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="contact_Name" value="#{list.name}" />
            </t:column>

         <t:column id="directPhones_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_directPhones" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_Tel}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='directPhones') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='directPhones') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>                    
            </f:facet>
            <h:outputText id="contact_directPhones" value="#{list.directPhones}"/>
        </t:column>
        
         <t:column id="conactFax_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_fax" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_Fax}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fax') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fax') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>                    

            </f:facet>
            <h:outputText id="contact_Fax" value="#{list.fax}"/>
        </t:column>            

            <t:column id="contactMobile_column" width="10%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_mobileNo" forceId="true" styleClass="headerLink" value="#{integrationBundle.contactMobile}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='mobileNo') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='mobileNo') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactMobile" value="#{list.mobileNo}"/>
            </t:column>
            
            <t:column id="contactEmail_column" width="20%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_email" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_email}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactEmail" value="#{list.email}"/>
            </t:column>
            
            <t:column id="contactGender_column" width="5%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_gender" forceId="true" styleClass="headerLink" value="#{integrationBundle.Type}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='gender') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='gender') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactGender" value="#{list.gender}"/>
            </t:column>

        </t:dataTable>
        
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
    </t:panelGroup>
    
</t:panelGrid>

<!-- added by nora to enable single selection -->
<t:saveState value="#{pageBeanName.singleSelection}"/>
<t:saveState value="#{pageBeanName.myTableData}"/>
<t:saveState value="#{pageBeanName.highlightedDTOS}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>

<!-- Start Paging -->
<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>

<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<!-- End Paging -->

<script type="text/javascript">
    foucsAddbuttonOnList();
  function foucsAddbuttonOnList() {
      if (document.getElementById('addButton') != null) {
          document.getElementById('addButton').focus();
      }
  }
</script>
