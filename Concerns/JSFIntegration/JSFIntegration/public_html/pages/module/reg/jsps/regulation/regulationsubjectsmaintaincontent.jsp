<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
   
 <t:panelGroup forceId="true" id="dataT_data_panel">
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
                   <t:column id="check_column" width="5%" rendered="#{pageBeanName.maintainMode!=2}">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" rendered="#{pageBeanName.maintainMode!=2}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" rendered="#{pageBeanName.maintainMode!=2}">                                            
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                        </t:selectBooleanCheckbox>
                   </t:column>
                   
                   <t:column id="code_column" width="5%">
                        <f:facet name="header">
                            <h:outputText id="header_code" value="#{regResources.regulation_subjects_symbol}"/>
                             <%--t:commandSortHeader id="codeColumn" columnName="code" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_code" value="#{regResources.regulation_subjects_symbol}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_code" value="#{list.subjectsDTO.code.key}"/>
                   </t:column>
                   
                   <t:column id="name_column" width="80%">
                        <f:facet name="header">
                            <h:outputText id="header_name" value="#{regResources.regulation_subjects_desc}"/>
                             <%--t:commandSortHeader id="nameColumn" columnName="name" arrow="false" styleClass="standardTable_Header2" immediate="true">
                                  <f:facet name="ascending">
                                        <t:graphicImage id="ascendingArrow" value="/images/ascending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <f:facet name="descending">
                                        <t:graphicImage id="descendingArrow" value="/images/descending-arrow.gif" rendered="true" border="0"/>
                                  </f:facet>
                                  <h:outputText id="header_name" value="#{regResources.regulation_subjects_desc}"/>
                             </t:commandSortHeader--%>
                        </f:facet>
                        <h:outputText id="content_name" value="#{list.subjectsDTO.name}"/>
                   </t:column>
              </t:dataTable>
			  <h:panelGrid columns="1" dir="#{shared_util.pageDirection}"/>
            <h:panelGrid columns="1" >
                <h:outputText styleClass="subjectErrMsg" value="#{regResources.global_noTableResultsRegSubject}" rendered="#{ detailBeanName.currentListSize == 0 }"/>
            </h:panelGrid>     
 </t:panelGroup>       
