<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="dataT_data_panel" >
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
             
             <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!pageBeanName.singleSelection}">
                <f:facet name="header">
                  <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{pageBeanName.checkAllFlag}">
                    <f:attribute name="checkAll" value="true"/>
                    <f:attribute name="listSize" value="#{pageBeanName.listSize}"/>
                    <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxsAll}"
                                 oncomplete="setAll('checkAll', 'chk');"
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                  </t:selectBooleanCheckbox>
                  
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                  <a4j:support event="onclick" actionListener="#{pageBeanName.selectedCheckboxs}"
                               oncomplete="checkCheckAll('chk');"
                               reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectBooleanCheckbox>
              </t:column>
               
              <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{pageBeanName.singleSelection}">  
                <f:facet name="header"/>
                <t:selectOneRadio styleClass="radioButton_DataTable"  id="chk" value="#{pageBeanName.selectedRadio}" onmousedown="toggleRadio(this);">
                   <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                   <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRadioButton}" 
                                 reRender="divDeleteAlert,divEditLookup,OperationBar"/>
                </t:selectOneRadio>
              </t:column>
              
               
                
            <t:column id="code_column" sortable="false"  width="20%">
                <f:facet name="header">
                     
                    <t:commandLink id="sort_code-cntryCode" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-cntryCode') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code-cntryCode') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>

                </f:facet>
                <h:outputText id="content_code" value="#{list.code.cntryCode}"/>
            </t:column>
            
            
            <t:column id="name_column" sortable="false" width="25%">
                <f:facet name="header">

                    <t:commandLink id="sort_countriesDTO-name" forceId="true" styleClass="headerLink" value="#{resourcesBundle.cntryName}" actionListener="#{pageBeanName.sortDataTable}">                         
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countriesDTO-name') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='countriesDTO-name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.countriesDTO.name}"/>
            </t:column>
            
            
            
            <t:column id="captial_column" sortable="false" width="35%">
                <f:facet name="header">

                    <t:commandLink id="sort_captial" forceId="true" styleClass="headerLink" value="#{resourcesBundle.captial}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <f:param name="listExpression" value="groupCountriesListBean.myTableData"/>
                                <f:param name="currentSortingRowIndex" value="groupCountriesListBean.currentSortingRowIndex"/>
                                <f:param name="elExpression" value="( !empty groupCountriesListBean.myTableData[groupCountriesListBean.currentSortingRowIndex].countriesDTO.countryCitiesDTOList ? groupCountriesListBean.myTableData[groupCountriesListBean.currentSortingRowIndex].countriesDTO.countryCitiesDTOList[0].name :null)"/>
                        <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='captial') ? '/app/media/images/ascending-arrow.gif' : ''}"  border="0"/>
                        <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='captial') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                    </t:commandLink>
                   
                </f:facet>
                <h:outputText id="content_captial" value="#{list.countriesDTO.countryCitiesDTOList[0].name}"/>
            </t:column>
            
            
            
            
            
            
            <t:column id="status_column" sortable="false"  width="15%" styleClass="standardTable_ColumnCentered">
                       <f:facet name="header">
                       <%--
                          <t:commandLink id="sort_reqSerial" forceId="true" styleClass="headerLink" value="#{resourcesBundle.nationality}" actionListener="#{pageBeanName.sortDataTable}">                         
                           <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reqSerial') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                           <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='reqSerial') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                           </t:commandLink>
                          --%>
                          <t:outputLabel value="#{resourcesBundle.nationality}"/>
                          
                       </f:facet>
                                               
                      
                           <t:graphicImage  value="/app/media/images/icon_details_number.gif"  onmouseover="doTooltip(event,' #{resourcesBundle.TooltipMale}(#{list.countriesDTO.genderCountryDTOList[1].name}) <br> #{resourcesBundle.TooltipFamale}(#{list.countriesDTO.genderCountryDTOList[0].name})')" onmouseout="hideTip()" title="#{resourcesBundle.nationality}" />
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