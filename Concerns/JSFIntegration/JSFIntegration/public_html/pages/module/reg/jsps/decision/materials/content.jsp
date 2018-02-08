<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

 <t:panelGroup forceId="true" id="dataT_data_panel">
    
    <t:dataTable id="dataT_data" var="list" value="#{detailBeanName.currentDisplayDetails}"
        forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{detailBeanName.currentDataTable}"
        rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
        rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%"
        align="center" dir="#{shared_util.pageDirection}" preserveSort="false" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                        
        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                    <f:attribute name="checkAll" value="true"/>
                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"
                                 oncomplete="setAll('checkAll', 'chk');"
                                 reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}"
                             oncomplete="checkCheckAll('chk');"
                             reRender="divEditLookup,divDeleteAlert,OperationBar"/>
            </t:selectBooleanCheckbox>
        </t:column>
        
        <t:column id="code_column"  width="5%">
            <f:facet name="header">
                  <%--  <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{typeListBean.sortDataTable}">                         
                            <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                            <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink> --%>
                 <t:outputText value="#{globalResources.Code}" />      
            </f:facet>
          <t:outputText id="content_code" value="#{list.code.keys[3] > 0 ? list.code.keys[3] : regResources.unDefineLabel }"/>
        </t:column>
        
        <t:column id="name_column"  width="20%">
            <f:facet name="header">
                <%--<t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{regResources.SubjectName}" actionListener="#{pageBeanName.sortDataTable}">                         
                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink>--%>
                  <t:outputText value="#{regResources.SubjectName}"/>  
            </f:facet>
          <t:outputText id="content_name" value="#{list.decmaterialHeader}"/>
        </t:column>
        
        <t:column id="name_column_area"  width="60%">
            <f:facet name="header">
               <%-- <t:commandLink id="sort_nameArea" forceId="true" styleClass="headerLink" value="#{regResources.SubjectTextArea}" actionListener="#{pageBeanName.sortDataTable}">                         
                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='nameArea') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='nameArea') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                </t:commandLink> --%>
                <t:outputText value="#{regResources.SubjectTextArea}"/>
            </f:facet>
          <t:outputText id="content_nameArea" value="#{list.decmaterialText}"/>
        </t:column>
         <t:column id="reg_related_material" sortable="false" width="10%" styleClass="standardTable_ColumnCentered" >
            <f:facet name="header">
            </f:facet>
            <t:graphicImage id="relatedMaterial" value="/app/media/images/icon_details_number.gif" rendered="true" onmouseover="doTooltip(event,'#{regResources.has_related_decision_material}(#{list.relatedMaterialsCount})')" onmouseout="hideTip()" border="0"/>
         </t:column>
    </t:dataTable>
    <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" rendered="#{ detailBeanName.currentListSize == 0 }"/>

</t:panelGroup>             