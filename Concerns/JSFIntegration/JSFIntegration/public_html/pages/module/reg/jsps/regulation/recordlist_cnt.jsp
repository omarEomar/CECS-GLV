<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

 <t:panelGroup forceId="true" id="dataT_data_panel" >
      <t:messages showDetail="true" />
        <t:outputText forceId="true" id="rowMandatoryMsg"  styleClass="errMsgNoHeight"  />
        <t:outputText forceId="true" id="nonvalidData"  styleClass="errMsgNoHeight"  value="#{regResources.DataValueNoMatchColumnTypeException}" rendered="#{!regulationRecordsBean.validDataFlag}"  />
        <t:outputText forceId="true" id="At_least_One_Record"  styleClass="errMsgNoHeight"  value="#{regResources.At_least_One_Record}" rendered="#{!regulationRecordsBean.enterAtLeastOneRow}"  />
        
      <t:dataTable id="dataT_data" var="list" renderedIfEmpty="true"
                   value="#{regulationRecordsBean.record.data}"
                   rowIndexVar="index" binding="#{regulationRecordsBean.currentDataTable}"
                   rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                   footerClass="grid-footer" styleClass="grid-footer"
                   headerClass="standardTable_Header" 
                   rowClasses="standardTable_Row1,standardTable_Row2"
                   columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered"
                   width="100%" align="top"
                   dir="#{shared_util.pageDirection}" preserveSort="false">
                     
            <t:columns id="columns" value="#{regulationRecordsBean.record.columnHeaders}"  var="columnHeader" >
                <f:facet name="header">
                    <t:panelGroup>
                           <t:panelGroup rendered="#{columnHeader.columnIndex==0 && regulationMaintainBean.maintainMode!=2 }">
                                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{regulationRecordsBean.checkAllFlag}" rendered="#{regulationMaintainBean.maintainMode!=2}" disabled="#{regulationMaintainBean.maintainMode==2}">
                                    <a4j:support event="onclick" actionListener="#{regulationRecordsBean.selectedCurrentAll}"  oncomplete="setAll_Rows('checkAll[0]', 'chk2','listSize');"  reRender="divEditLookup,OperationBar"/>
                                </t:selectBooleanCheckbox>
                            </t:panelGroup>
                            <t:panelGroup rendered="#{columnHeader.columnIndex !=0}">
                                    <h:outputText value="#{columnHeader.label}" styleClass="standardTable_Header2"/> 
                                    <h:outputText value="(*)" styleClass="starStyleRecord"/>
                            </t:panelGroup>
                    </t:panelGroup>
                </f:facet>
                <t:panelGroup rendered="#{columnHeader.columnIndex == 0 && regulationMaintainBean.maintainMode!=2 }">
                    <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list[1].checked}" forceIdIndex="0" rendered="#{regulationMaintainBean.maintainMode!=2}" disabled="#{regulationMaintainBean.maintainMode==2}">                                            
                        <a4j:support event="onclick" actionListener="#{regulationRecordsBean.selectedCurrent}" oncomplete="checkCheckAll_Rows('chk2','listSize','checkAll[0]');" reRender="OperationBar,divEditLookup" />
                    </t:selectBooleanCheckbox>
                </t:panelGroup>
                 <t:panelGroup rendered="#{columnHeader.columnIndex !=0}">
                     <t:inputText id="cellValue" value="#{list[columnHeader.columnIndex].value}" onkeypress="keyPressNumberByColumnType('#{columnHeader.columnType}',event);" styleClass="#{(columnHeader.columnType==managedConstantsBean.regColumnTextType)?'textboxmedium':'textboxsmall'}" onkeyup="disableCharactersByColumnType('#{columnHeader.columnType}',this)" maxlength="3800" disabled="#{regulationMaintainBean.maintainMode==2}"/>
                 </t:panelGroup>
                
            </t:columns>
          
      </t:dataTable>
        
     <t:panelGrid columns="1" rendered="#{ regulationRecordsBean.currentListSize == 0 }">
            <t:outputText styleClass="msg" value="#{globalResources.global_noTableResults_m2m}"/>
            
     </t:panelGrid>    
     <t:inputHidden forceId="true" id="currentListSizeID" value="#{regulationRecordsBean.currentListSize}"  />
			
 </t:panelGroup>       