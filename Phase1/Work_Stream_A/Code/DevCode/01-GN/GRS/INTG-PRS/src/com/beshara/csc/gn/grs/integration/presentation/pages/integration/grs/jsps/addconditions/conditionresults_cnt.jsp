<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.dataT-With-3-row-filter-and-collapse {
    height: 287px !important;
}
</htm:style>
<t:panelGrid forceId="true" id="resultsPnl" border="0" cellpadding="0" cellspacing="0" width="100%" columns="1">
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="#{pageBeanName.resultTableStyleClass}"
                  style="width: 890px;">
        <t:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 && conditionIntgResultsBean.searchMode}"
                     align="center">
            <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.myTableData}"                     
                     rowStyleClass="#{ list.passAllConditions==1 ? 'standardTable_RowHighlighted_GRS' : 'standardTable_RowHighlighted_GRS_Rejected'}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_Column,standardTable_ColumnCentered"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true"
                     sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}"
                     varDetailToggler="detailToggler">
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{globalResources.globalCivilId}"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="60%">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{globalResources.globalName}"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
            <t:column id="toggler">
                <f:facet name="header">
                    <t:div>
                        <h:commandLink id="showAllTglr" action="#{detailToggler.expandAllDetails}"
                                       rendered="#{detailToggler.expandedEmpty}">
                            <h:graphicImage id="showAllImg" value="/app/media/images/icon_details_number.gif"/>
                        </h:commandLink>
                         
                        <h:commandLink id="hideAllTglr" action="#{detailToggler.collapseAllDetails}"
                                       rendered="#{!detailToggler.expandedEmpty}">
                            <h:graphicImage id="hideAllImg" value="/app/media/images/icon_details_number.gif"/>
                        </h:commandLink>
                    </t:div>
                </f:facet>
                <h:commandLink id="showTglr" action="#{detailToggler.toggleDetail}"
                               rendered="#{!detailToggler.currentDetailExpanded}">
                    <h:graphicImage id="showOneImg" value="/app/media/images/Middle_root_plus_close.gif"/>
                </h:commandLink>
                <h:commandLink id="hideTglr" action="#{detailToggler.toggleDetail}"
                               rendered="#{detailToggler.currentDetailExpanded}">
                    <h:graphicImage id="hideOneImg" value="/app/media/images/minus_open_Root.gif"/>
                </h:commandLink>
            </t:column>
            <f:facet name="detailStamp">
                <t:column id="namexx_column" sortable="false" width="100%" styleClass="center">
                    <t:dataTable id="details" value="#{list.result}" var="detail" width="100%"
                                 headerClass="standardTable_Header"
                                 columnClasses="standardTable_Column,standardTable_Column">
                        <t:column width="70%">
                            <%-- f:facet name="header"> <h:outputText value="#{resourcesBundle.conditionName}"
                                 styleClass="lable01" style="color:#fff"/> </f:facet--%>
                            <h:outputText styleClass="lable01" value="#{detail.currentObject.name}"/>
                        </t:column>
                        <t:column width="15%" headerstyle="{background-color: #ace4f3;}">
                            <%-- f:facet name="header"> <h:outputText value="#{globalResources.Status}"
                                 styleClass="lable01" style="color:#fff"/> </f:facet--%>
                            <h:outputText styleClass="errMsg" style="color:RED" rendered="#{detail.status!= 1}"
                                          value="#{(detail.status == 0) ? resourcesBundle.test_condition_match_no:resourcesBundle.ErrorCondtionNotFound}"/>
                            <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{detail.status== 1}"
                                          value="#{resourcesBundle.test_condition_match}"/>
                        </t:column>
                        <t:column width="15%" >
                            <%-- f:facet name="header"> <h:outputText value=""
                                 styleClass="lable01" style="color:#fff"/> </f:facet--%>
                        <t:commandButton id="showLinesId" styleClass="cssButtonsmall" actionListener="#{conditionIntgResultsBean.showLineDiv}" value="#{resourcesBundle.view_menu_details}">
                        <f:attribute name="civilIdParam" value="#{list.code.key }"/>
                        <f:attribute name="employeeNameParam" value="#{list.name }"/>
                        <f:attribute name="conditionCodeParam" value="#{detail.currentObject.code.key }"/>
                        
                         </t:commandButton>
                        </t:column>
                        
                    </t:dataTable>
                </t:column>
            </f:facet>
        </t:dataTable>
       <%--ddddddd --%>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
 function toggleDetails(image) {
      var detailsId = image.id.substring(0, image.id.lastIndexOf(':')) + ':details';
      var details = document.getElementById(detailsId);
      if (details.style.display == 'none') {
          details.style.display = 'block';
          //image.src = 'collapse.gif';
      }
      else {
          details.style.display = 'none';
          //image.src = 'expand.gif';
      }
  }
</script>
<f:verbatim>
    <script language="javascript" type="text/javascript">
      adjustDataTable('dataT_data');
    </script>
</f:verbatim>
