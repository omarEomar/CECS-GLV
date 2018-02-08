<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


              <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                           value="#{detailBeanName.currentDisplayDetails}"
                           rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                           rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');"
                           footerClass="grid-footer" styleClass="grid-footer"
                           headerClass="standardTable_Header"
                           rowClasses="standardTable_Row1,standardTable_Row2"
                           columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered"
                           width="100%" align="center"
                           dir="#{shared_util.pageDirection}" preserveSort="true">
                   <t:column id="check_column" width="5%">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                        </t:selectBooleanCheckbox>
                   </t:column>
                   
                   <t:column id="type_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_type" value="#{regResources.regulation_References_type}"/>
                            <%--t:commandSortHeader id="typeColumn" columnName="type" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_type" value="#{regResources.regulation_References_type}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content___type" value="#{list.regulationsDTO.typesDto.name}"/>
                    </t:column>
                    
                    <t:column id="year_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_year" value="#{regResources.regulation_References_year}"/>
                            <%--t:commandSortHeader id="yearColumn" columnName="year" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_year" value="#{regResources.regulation_References_year}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_year" value="#{list.regulationsDTO.yearsDto.code.key}"/>
                    </t:column>
                    
                    <t:column id="regNo_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_regNo" value="#{regResources.regulation_References_reg_no}"/>
                            <%--t:commandSortHeader id="regNoColumn" columnName="regNo" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_regNo" value="#{regResources.regulation_References_reg_no}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_regNo" value="#{list.regulationsDTO.code.regulationNumber}"/>
                    </t:column>                    
                   
                   <t:column id="name_column" width="95%">
                        <f:facet name="header">
                            <h:outputText id="header_name" value="#{globalResources.SearchName}"/>
                             <%--t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_name" value="#{globalResources.SearchName}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.regulationsDTO.regulationTitle}"/>
                   </t:column>
              </t:dataTable>
              <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <h:panelGrid columns="1" >
                       <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults_m2m}" rendered="#{ detailBeanName.currentListSize == 0 }"/>
                       <h:outputText styleClass="regErrMsg" value="#{regResources.decision_reference_error_message}" rendered="#{!(detailBeanName.existReference)}"/>
            </h:panelGrid>