<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

              <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                           value="#{detailBeanName.currentDisplayDetails}"
                           rowIndexVar="index" binding="#{detailBeanName.currentDataTable}"
                           rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');"
                           footerClass="grid-footer" styleClass="grid-footer"
                           headerClass="standardTable_Header"
                           rowClasses="standardTable_Row1,standardTable_Row2"
                           columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                           width="100%" align="center"
                           dir="#{shared_util.pageDirection}" preserveSort="true">
                   <%--t:column id="check_column" width="5%">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');updateButtonsStatusTable();updateMenuItemsStatusTable('regForm_myMenu_menu',regForm_myMenu_menu,'hbl');"  reRender="divDeleteAlert,selectedNumber,searchMode"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');updateButtonsStatusTable();updateMenuItemsStatusTable('regForm_myMenu_menu',regForm_myMenu_menu,'hbl');" reRender="divDeleteAlert,selectedNumber,searchMode" />
                        </t:selectBooleanCheckbox>
                   </t:column--%>

                   <t:column id="civilid_column" width="20%">
                        <f:facet name="header">
                            <h:outputText id="header_type" value="#{regResources.civil_id}"/>
                            <%--t:commandSortHeader id="typeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                <f:facet name="ascending">
                                    <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <f:facet name="descending">
                                    <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                </f:facet>
                                <h:outputText id="header_type" value="#{regResources.civil_id}"/>
                            </t:commandSortHeader--%>
                        </f:facet>
                      <h:outputText id="content_civilid" value="#{list.employeesDTO.code.key}"/>
                    </t:column>
                    
                   <t:column id="name_column" width="75%">
                        <f:facet name="header">
                             <h:outputText id="header_name" value="#{regResources.employee_name}"/>
                             <%--t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_name" value="#{regResources.employee_name}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.fullName}"/>
                   </t:column>
              </t:dataTable>
            <t:panelGrid columns="1" align="center">
                    <h:outputText styleClass="msg" value="#{globalResources.global_noTableResults}" rendered="#{ detailBeanName.currentListSize == 0 }"/>
                    <h:outputText styleClass="regErrMsg" value="#{regResources.must_add_employee}" rendered="#{ detailBeanName.showErrMsg }"/>
            </t:panelGrid>     
        