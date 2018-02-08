<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
    <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>

<t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">               
                
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <%--<t:saveState value="#{pageBeanName.searchMode}"/>--%>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                
                <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}"
                             forceIdIndexFormula="#{list.code.key}" 
                             rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                             renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                             rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                             rowOnMouseOver="javascript:showToolTip(event , '#{regResources.dec_title}' , '#{list.decisionTitle}' , '#{regResources.total_emp_count}' , '#{list.empDecCount}' , '#{resourcesBundle.total_values}' , '#{list.decTotalValue}');"
                             rowOnMouseOut="hideTip()"
                             footerClass="grid-footer" styleClass="grid-footer"
                             headerClass="standardTable_Header" 
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                             width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true" 
                             sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                        
                    
                    <t:column id="check_column" styleClass="standardTable_Header3"  width="2%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header"/>
                        <t:selectOneRadio styleClass="radioButton_DataTable" id="chk" value="#{pageBeanName.selectedRadio}" 
                            onmousedown="toggleRadio(this);" onkeypress="toggleRadio(this);" >
                           <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                           <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" reRender="OperationBar,divDeleteAlert"/>
                        </t:selectOneRadio>
                    </t:column>
                    <t:column id="typesDTO-name_column"  width="10%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_typesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.typeDecisionLabel}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="bsnSortingColumnName" value="o.typesEntity.regtypeName"/>

                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='typesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            
                        </f:facet>
                       <h:outputText id="dec_type" value="#{list.typesDTO.name}"  />
                    </t:column>
                    
                    <t:column id="yearsDTO-code-key_column" width="10%"  styleClass="standardTable_ColumnCentered"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} " >
                        <f:facet name="header">
                            <t:commandLink id="sort_yearsDTO-code-key" forceId="true" styleClass="headerLink" value="#{regResources.reg_year}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="bsnSortingColumnName" value="o.decyearCode"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDTO-code-key') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='yearsDTO-code-key') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                           
                        </f:facet>
                        <h:outputText id="dec_year" value="#{list.yearsDTO.code.key}"  />
                    </t:column>
                    
                    <t:column id="regNum_column"  width="10%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_regNum" forceId="true" styleClass="headerLink" value="#{regResources.dec_number}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="bsnSortingColumnName" value="o.regNum"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regNum') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regNum') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            
                        </f:facet>
                       <h:outputText id="reg_number"  value="#{list.regNum}" />
                    </t:column>
                    
                    <t:column id="regAutoNumber_column"  width="10%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_regAutoNumber" forceId="true" styleClass="headerLink" value="#{regResources.auto_number}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="bsnSortingColumnName" value="o.regAutoNumber"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regAutoNumber') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regAutoNumber') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                            
                        </f:facet>
                       <h:outputText id="reg_auto_number"  value="#{list.regAutoNumber}" />
                    </t:column>
                    
                   <%--<t:column id="decisionTitle_column" width="10%"   styleClass="standardTable_Column">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionTitle" forceId="true" styleClass="headerLink" value="#{regResources.dec_title}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionTitle') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionTitle') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                          
                        </f:facet>
                       <h:outputText id="dec_title" value="#{list.decisionTitle}"  />
                    </t:column>--%>
                    
                    <t:column id="decisionMakerTypesDTO-name_column" width="10%"   style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionMakerTypesDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.dec_decision_maker}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="bsnSortingColumnName" value="o.decisionMakerTypesEntity.decmkrtypeName"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerTypesDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionMakerTypesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                           
                        </f:facet>
                       <h:outputText id="decision_maker" value="#{list.decisionMakerTypesDTO.name}" />
                    </t:column>
                    
                    <t:column id="decisionDate_column"  width="10%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionDate" forceId="true" styleClass="headerLink" value="#{regResources.dec_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                            <f:param name="bsnSortingColumnName" value="o.decisionDate"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                        </f:facet>
                       <t:outputText converter="TimeStampConverter"  id="dec_date" value="#{list.decisionDate}" />
                    </t:column>
                    
                    
                    <t:column id="decisionAppliedDate_column" rendered="#{pageBeanName.indexArray[6]}" width="10%"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_decisionAppliedDate" forceId="true" styleClass="headerLink" value="#{regResources.apply_date}" actionListener="#{pageBeanName.sortDataTable}">                         
                            <f:param name="bsnSortingColumnName" value="o.decisionAppliedDate"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionAppliedDate') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='decisionAppliedDate') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                        </f:facet>
                        <t:outputText converter="TimeStampConverter"  id="apply_date" value="#{list.decisionAppliedDate}"
                            />
                    </t:column>

                    
                    
                   <t:column id="status_column" width="10%"  styleClass="standardTable_ColumnCentered"  style="#{list.regStatusDTO.code.regstatusCode == 2 ? 'background-color: #D3D3D3;' : ''} ">
                        <f:facet name="header">
                            <t:commandLink id="sort_regStatusDTO-name" forceId="true" styleClass="headerLink" value="#{regResources.status}" actionListener="#{pageBeanName.sortDataTable}">                         
                               
                                <f:param name="bsnSortingColumnName" value="o.regulationStatusEntity.regstatusName"/>
                                <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regStatusDTO-name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='regStatusDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                            </t:commandLink>
                           
                        </f:facet>
                        
                        <h:outputText id="dec_status_valid" value="#{list.regStatusDTO.name}" />
                    </t:column> 
                                       
                    
                </t:dataTable>
                
                <h:panelGrid columns="1" rendered="#{ pageBeanName.listSize == 0 }">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}" />
                </h:panelGrid>
                <%--<t:inputHidden value="#{pageBeanName.cancelDescisionFlag}" forceId="true" id="cancelDescision"/>--%>
                <%--<t:inputHidden value="#{pageBeanName.validDecision}" forceId="true" id="validDecision"/>--%>
                 <%--<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"  value="search_regDateFrom_dec,425,200:search_regDateTo_dec,440,200" />--%>
                <%--<t:inputHidden id="decTitle" forceId="true" value="#{resourcesBundle.dec_title}"/>--%>
                <%--<t:inputHidden id="decEmpNoTitle" forceId="true" value="#{resourcesBundle.total_emp_count}"/>--%>
                <%--<t:inputHidden id="decTotalValueTitle" forceId="true" value="#{resourcesBundle.total_values}"/>--%>
</t:panelGroup>                


<t:saveState value="#{pageBeanName.yearsList}"/>
<t:saveState value="#{pageBeanName.typesList}"/>
<t:saveState value="#{pageBeanName.finDecisionStatusList}"/>
<t:saveState value="#{pageBeanName.selectedDTOS}"/>
<t:saveState value="#{pageBeanName.selectedTypeKey}"/>
<t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

<t:saveState value="#{pageBeanName.currentPageIndex}"/>
<t:saveState value="#{pageBeanName.oldPageIndex}"/>
<t:saveState value="#{pageBeanName.sorting}"/>
<t:saveState value="#{pageBeanName.usingPaging}"/>
<t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{pageBeanName.resettedPageIndex}"/>
<t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
<t:saveState value="#{pageBeanName.indexArray}"/>
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.searchMode}"/>


<t:saveState value="#{pageBeanName.disapleButton}"/>    
<t:saveState value="#{pageBeanName.decisionSearchDTO}"/>   
<t:saveState value="#{pageBeanName.systemSettingCode}"/>
 