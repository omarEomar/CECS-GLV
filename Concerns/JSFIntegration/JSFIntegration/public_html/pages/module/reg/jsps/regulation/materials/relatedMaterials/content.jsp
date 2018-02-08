<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>


<f:verbatim> <br/> </f:verbatim>
<htm:table width="100%">
    
    <htm:tr valign="top">
        <htm:td width="45%">
            <t:panelGrid columns="1" border="0">
                <t:panelGroup>
                    <t:graphicImage url="/app/media/images/op_arrow.jpg"/>
                    <f:verbatim escape="true"> </f:verbatim> 
                    <t:outputLabel value="#{regResources.selectedRegulation}" styleClass="lable01" />
                </t:panelGroup>
            </t:panelGrid>
        </htm:td>
        <htm:td width="10%" valign="middle"/>
        <htm:td width="45%">
            <t:panelGrid columns="1" border="0">
                <t:panelGroup>
                    <t:graphicImage url="/app/media/images/op_arrow.jpg"/>
                    <f:verbatim escape="true"> </f:verbatim> 
                    <t:outputLabel value="#{regResources.relatedMaterials}#{regulationMaterialsMaintainBean.pageDTO.regmaterialHeader}" styleClass="lable01" />
                </t:panelGroup>
            </t:panelGrid>
        </htm:td>
    </htm:tr>
    
    <htm:tr valign="top">
        <htm:td width="45%" styleClass="borderedTable">
            <t:panelGrid width="100%" forceId="true" id="dataTableGrp">
                <t:panelGrid columns="3" cellpadding="0" cellspacing="0" rowClasses="row01" width="100%">
                    <t:outputText value="#{regResources.regulation_description}"/>
                    <t:inputText disabled="true" styleClass="textboxlarge" value="#{pageBeanName.selectedRegulation.regulationTitle}"/>
                    <a4j:commandButton styleClass="cssButtonSmall" value="#{regResources.selectRegulation}" oncomplete="changeVisibilityDiv(window.blocker,window.divSearch);" rendered="#{regulationMaintainBean.maintainMode!=2}"/>
                </t:panelGrid>
                <t:panelGrid width="100%" rendered="#{empty pageBeanName.regMaterialsList && !pageBeanName.searchedForReg}" style="text-align:center">
                    <t:outputText value="#{regResources.noSelectedRegulation}#{regulationMaterialsMaintainBean.pageDTO.regmaterialHeader}" styleClass="noDataMsgFreeWidth" rendered="#{regulationMaintainBean.maintainMode!=2}"/>
                </t:panelGrid>
                <t:panelGrid width="100%" rendered="#{empty pageBeanName.regMaterialsList && pageBeanName.searchedForReg}" style="text-align:center">
                    <t:outputText value="#{regResources.no_data_found}" styleClass="noDataMsgFreeWidth"/>
                </t:panelGrid>
                <t:panelGroup style="#{(!empty pageBeanName.regMaterialsList)?'height:205px; width: 100%; overflow: auto;direction: rtl;':'height:170px; width: 100%; overflow: auto;direction: rtl;' }" >
                    <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.regMaterialsList}" renderedIfEmpty="false"
                        forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" binding="#{pageBeanName.myDataTable}"
                        rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                        rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%"
                        align="center" dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" sortAscending="#{pageBeanName.ascending}">
                        
                        <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                            <f:facet name="header">
                            </f:facet>
                            <t:selectBooleanCheckbox forceId="true" id="matChk" value="#{list.checked}">
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedMaterialsCheckboxs}"
                                             oncomplete="checkCheckAll('matChk');"
                                             reRender="divEditLookup,divDeleteAlert,OperationBar,addMatBtn"/>
                            </t:selectBooleanCheckbox>
                        </t:column>
                        
                        <t:column id="code_column"  width="25%">
                            <f:facet name="header">
                                    <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{typeListBean.sortDataTable}">                         
                                            <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                            <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                    </t:commandLink>                     
                            </f:facet>
                          <t:outputText id="content_code" value="#{list.code.keys[0]}"/>
                        </t:column>
                        
                        <t:column id="name_column">
                            <f:facet name="header">
                                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{regResources.SubjectName}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                            </f:facet>
                          <t:outputText id="content_name" value="#{list.regmaterialHeader}"/>
                        </t:column>
                    </t:dataTable>
                </t:panelGroup>
                
            </t:panelGrid>
        </htm:td>
        
        <htm:td width="10%" valign="middle">
            <htm:table width="100%">
                <htm:tr>
                    <htm:td width="100%" align="center">
                        <t:commandButton forceId="true" id="addMatBtn" disabled="#{empty pageBeanName.regMaterialsList || empty pageBeanName.selectedMaterialsList || regulationMaintainBean.maintainMode==2}" styleClass="cssButtonSmall" value=">" action="#{pageBeanName.moveSelectedMaterialsToRelated}" title="#{regResources.addMatHint}"/>
                    </htm:td>
                </htm:tr>
                <htm:tr>
                    <htm:td width="100%" align="center">
                        <t:commandButton forceId="true" id="addAllMatBtn"  disabled="#{empty pageBeanName.regMaterialsList || regulationMaintainBean.maintainMode==2}" styleClass="cssButtonSmall" value=">>" action="#{pageBeanName.moveAllMaterialsToRelated}" title="#{regResources.addAllMatsHint}"/>
                    </htm:td>
                </htm:tr>
                <htm:tr>
                    <htm:td width="100%" align="center">
                        <t:commandButton forceId="true" id="delMatBtn"  disabled="#{empty pageBeanName.relatedMaterialsList || empty pageBeanName.selectedRelMaterialsList  || regulationMaintainBean.maintainMode==2}" styleClass="cssButtonSmall" value="<" action="#{pageBeanName.moveSelectedRelatedToMaterials}" title="#{regResources.delMatHint}"/>
                    </htm:td>
                </htm:tr>
                <htm:tr>
                    <htm:td width="100%" align="center">
                        <t:commandButton forceId="true" id="delAllMatBtn"  disabled="#{empty pageBeanName.relatedMaterialsList || regulationMaintainBean.maintainMode==2}" styleClass="cssButtonSmall" value="<<" action="#{pageBeanName.moveAllRelatedToMaterials}" title="#{regResources.delAllMatsHint}"/>
                    </htm:td>
                </htm:tr>
            </htm:table>
        </htm:td>
        
        <htm:td width="45%" styleClass="borderedTable">
            <t:panelGrid forceId="true" id="relatedDataGrd" width="100%" style="vertical-align:text-top">
                
                 <t:outputText value="#{globalResources.noOfRecords} : #{relatedMaterialsBean.relatedMaterialSize}" styleClass="noOfRecords"/>
                 <t:panelGrid width="100%" rendered="#{empty pageBeanName.relatedMaterialsList}" style="text-align:center">
                    <t:outputText value="#{regResources.noRelatedMaterials}#{regulationMaterialsMaintainBean.pageDTO.regmaterialHeader}" styleClass="noDataMsgFreeWidth"/>
                </t:panelGrid>
                 <t:outputLabel  style="font-size:5pt;height:5px"/>
                    <t:panelGroup style="#{(!empty pageBeanName.relatedMaterialsList)?'height:205px; width: 100%; overflow: auto;direction: rtl;':'height:170px; width: 100%; overflow: auto;direction: rtl;'}" >
                    <t:dataTable id="dataT_data2" var="list" value="#{pageBeanName.relatedMaterialsList}" renderedIfEmpty="false"
                        rowIndexVar="index" binding="#{pageBeanName.relatedDataTable}"
                        rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data2');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                        rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%"
                        align="center" dir="#{shared_util.pageDirection}" preserveSort="true" style="vertical-align:text-top">
                        
                        <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{regulationMaintainBean.maintainMode!=2}">
                            <f:facet name="header">
                            </f:facet>
                            <t:selectBooleanCheckbox forceId="true" id="relatedChk" value="#{list.checked}" rendered="#{regulationMaintainBean.maintainMode!=2}">
                                <a4j:support event="onclick" actionListener="#{pageBeanName.selectedRelatedMaterialsCheckboxs}"
                                             oncomplete="checkCheckAll('relatedChk');" 
                                             reRender="divEditLookup,divDeleteAlert,OperationBar,delMatBtn"/>
                            </t:selectBooleanCheckbox>
                        </t:column>
                        
                        <t:column id="desc_column"  width="20%">
                            <f:facet name="header">
                            </f:facet>
                            <t:graphicImage id="minorNumber2" value="/app/media/images/icon_details_number.gif" rendered="true" border="0" onmouseover="doTooltip(event,'#{regResources.regulation_References_reg_type}: #{list.relatedMaterialDTO.regulationsDTO.typesDto.name} \\n #{regResources.reg_year}: #{list.relatedMaterialDTO.regulationsDTO.yearsDto.name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; \\n #{regResources.reg_number}: #{list.relatedMaterialDTO.regulationsDTO.code.regulationNumber}')" onmouseout="hideTip()"/>
                        </t:column>
                        
                        <t:column id="code_column"  width="25%">
                            <f:facet name="header">
                                <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" actionListener="#{typeListBean.sortDataTable}">                         
                                    <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                    <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='code') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>                     
                            </f:facet>
                          <t:outputText id="content_code" value="#{list.relatedMaterialDTO.code.keys[0]}"/>
                        </t:column>
                        
                        <t:column id="name_column"  width="50%">
                            <f:facet name="header">
                                <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{regResources.SubjectName}" actionListener="#{pageBeanName.sortDataTable}">                         
                                <t:graphicImage  value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"  border="0"/>
                                <t:graphicImage  value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"  border="0"/>
                                </t:commandLink>
                            </f:facet>
                          <t:outputText id="content_name" value="#{list.relatedMaterialDTO.regmaterialHeader}"/>
                        </t:column>
                        
                        <t:column id="types_column"  width="20%">
                            <f:facet name="header">
                                <t:outputText id="relType" value="#{regResources.relationType}"/>
                            </f:facet>
                            <t:panelGroup>
                            <t:selectOneMenu id="relTypesSearch" value="#{list.mtrreltypeCode}" forceId="true"  styleClass="textbox" disabled="#{regulationMaintainBean.maintainMode==2}">
                                <f:selectItem itemLabel="#{regResources.select}" itemValue=""/>
                                <t:selectItems var="type" value="#{pageBeanName.relationTypes}" itemLabel="#{type.name}" itemValue="#{type.code.mtrreltypeCode}"/>
                            </t:selectOneMenu>
                             <c:requiredFieldValidator componentToValidate="relTypesSearch" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" uniqueId="materialRelationID"
                                isArray="true" arraySize="#{relatedMaterialsBean.relatedMaterialSize}"/>
                            </t:panelGroup>
                        </t:column>
                        
                    </t:dataTable>
                </t:panelGroup>
                
                
                
            </t:panelGrid>
        </htm:td>
        
    </htm:tr>
</htm:table>
 <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"  value="search_regDateFrom,310,165:search_regDateTo,310,165" />


<t:panelGroup>
    <t:panelGrid columns="2" cellspacing="3">
        <t:commandButton styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{pageBeanName.saveRelatedMaterials}" onclick="return validatemyForm();" rendered="#{regulationMaintainBean.maintainMode!=2}"/>
        <t:commandButton styleClass="cssButtonSmall" value="#{globalResources.back}" action="#{pageBeanName.back}"/>
    </t:panelGrid>
</t:panelGroup>