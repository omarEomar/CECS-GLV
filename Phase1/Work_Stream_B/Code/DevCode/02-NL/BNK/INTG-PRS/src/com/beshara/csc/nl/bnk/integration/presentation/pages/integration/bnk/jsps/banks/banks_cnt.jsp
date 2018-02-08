<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGrid border="0" cellpadding="0" columns="6" cellspacing="0" width="100%">
<t:panelGroup forceId="true" id="dataT_data_panel"  colspan="6" >
<a4j:jsFunction name="tabledblClickJsFunction" actionListener="#{pageBeanName.dblClickAction}"
                        status="notdefined" reRender="divDeleteAlert,OperationBar,divEditLookup"
                        oncomplete="javascript:FireButton('editButton');">
            <a4j:actionparam name="JS_PARAM_INDEX" assignTo="#{pageBeanName.clickedRowIndex}"/>
            <a4j:actionparam name="JS_PARAM_TYPE" assignTo="#{pageBeanName.selectionComponentType}"/>
            <a4j:actionparam name="tableBinding" value="pageBeanName.myDataTable"/>
            <a4j:actionparam name="clickedDTOList" value="pageBeanName.selectedDTOS"/>
        </a4j:jsFunction>
            <t:dataTable id="dataT_data" binding="#{pageBeanName.myDataTable}" var="list"
                     value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                     rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" >                         
                     
                     
                    <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                              rendered="#{pageBeanName.singleSelection}">
                        <f:facet name="header"/>
                        <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}"
                                          onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                            <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                            <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}"
                                         reRender="OperationBar,divDeleteAlert"/>
                        </t:selectOneRadio>
                    </t:column>
                    
                    <t:column id="bankCode_column" width="10%">
                    <f:facet name="header">
                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{integrationBundle.bank_code}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                            
                    </f:facet>
                    <h:outputText id="bank_code" value="#{list.code.key}"/>
                    </t:column>                    
                    
                     <t:column id="bankName_column" width="45%">
                    <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{integrationBundle.bank_name}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                                
                    </f:facet>
                    <h:outputText id="bank_Name" value="#{list.name}"/>
                    </t:column>
                    
                     <t:column id="bankEmail_column" width="45%">
                    <f:facet name="header">
                    <t:commandLink id="sort_email" forceId="true" styleClass="headerLink" value="#{integrationBundle.bank_email}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/ascending-arrow.gif' : ''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                            
                    </f:facet>
                    <h:outputText id="bank_email" value="#{list.email}"/>
                    </t:column>                                        
                    
            </t:dataTable>
</t:panelGroup>
</t:panelGrid>
