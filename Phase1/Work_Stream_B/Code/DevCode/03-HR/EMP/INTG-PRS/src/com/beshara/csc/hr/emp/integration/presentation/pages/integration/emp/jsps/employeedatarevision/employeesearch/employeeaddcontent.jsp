<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" forceId="true" id="cntPanel">
    <t:panelGroup forceId="true" id="divAdds" styleClass="dataT-With-4-row-filter">
        <t:dataTable id="dataT_available" var="list" forceId="true"
                     value="#{detailBeanName.usingPaging ? detailBeanName.pagingBean.myPagedDataModel : detailBeanName.availableDetails}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="center" dir="#{shared_util.pageDirection}" preserveSort="true">
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      rendered="#{!detailBeanName.singleSelection}">
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="checkAll"
                                             value="#{detailBeanName.checkAllFlagAvailable}">
                        <f:attribute name="checkAll" value="true"/>
                        <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"
                                     oncomplete="setAll('checkAll', 'chk');"
                                     reRender="selectedAvailableListSize,buttonPanel"/>
                    </t:selectBooleanCheckbox>
                </f:facet>
                <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}"
                                         disabled="#{list.disableIfFound}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}"
                                 oncomplete="checkCheckAll('chk');" reRender="selectedAvailableListSize,buttonPanel"/>
                </t:selectBooleanCheckbox>
            </t:column>
            <t:column id="pnlgrd_dec_radio" styleClass="standardTable_Header3" width="5%"
                      rendered="#{detailBeanName.singleSelection}">
                <t:selectOneRadio id="selectOneRadio1" value="#{detailBeanName.tempCodeKeyStr}"
                                  onmousedown="toggleRadio(this);">
                    <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRadioButton}"
                                 reRender="selectedAvailableListSize,buttonPanel"/>
                </t:selectOneRadio>
            </t:column>
            <t:column id="code_columnCode" sortable="false" width="20%" styleClass="standardTable_ColumnCentered">
                <f:facet name="header">
                    <t:outputText id="header_code" value="#{intResourcesBundle.employees_civilId}"
                                  styleClass="standardTable_Header3"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.realCivilId}"/>
            </t:column>
            <t:column id="name_columnFullName" sortable="false" width="75%">
                <f:facet name="header">
                    <t:outputText id="header_name"
                                  value="#{intResourcesBundle.employeesDTO_citizensResidentsDTO_fullName}"
                                  styleClass="standardTable_Header3"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid rendered="#{detailBeanName.pagingBean.totalListSize==0}" styleClass="msg" align="center">
            <h:outputText value="#{globalResources.global_noTableResults}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGrid>
<t:inputHidden value="#{detailBeanName.availableListSize}" forceId="true" id="listSizeAdd"/>
<t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/>