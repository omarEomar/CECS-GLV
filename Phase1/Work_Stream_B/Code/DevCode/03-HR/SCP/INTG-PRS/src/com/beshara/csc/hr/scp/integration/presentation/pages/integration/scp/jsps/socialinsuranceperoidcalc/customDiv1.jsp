<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">
    <t:panelGroup forceId="true" id="changeType_panel" style="height: 85px;">
      
        <t:dataTable id="changeType_dataT_data" var="list"
                     value="#{pageBeanName.changeTypeList}"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     renderedIfEmpty="false"  binding="#{pageBeanName.changeTypeDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:changeType_dataT_data');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                     width="100%" align="top" 
                     >
					 
            <t:column id="check_Second_column" styleClass="standardTable_Header3" width="15%">
                    <f:facet name="header"/>
                <t:selectBooleanCheckbox forceId="true" id="chk_CV" value="#{list.checked}">
                       <a4j:support event="onclick" actionListener="#{pageBeanName.selectedChnageTypeCheckboxs}"
                                     oncomplete="checkCheckAll('chk_CV');"/>
                </t:selectBooleanCheckbox>
              </t:column>
              
            <%--<t:column  sortable="false"  width="20%">
                <f:facet name="header">
                 <t:commandLink id="sort_code" forceId="true" styleClass="headerLink" value="#{globalResources.Code}" onclick="return false;">                         
                 </t:commandLink>
                </f:facet>
                <t:outputText id="content_code" forceId="true" value="#{list.code.key}"/>
           </t:column>--%>  
        
            <t:column  sortable="false" width="85%">
                <f:facet name="header">
                 <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{globalResources.name}" onclick="return false;">
                 </t:commandLink>                   
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
            
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{ empty pageBeanName.changeTypeList}" align="center">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
       
    </t:panelGroup>
     
</t:panelGrid>
<t:panelGrid columns="2" align="center">
        <t:commandButton forceId="true" 
                        id="okBtnId" styleClass="cssButtonSmall"
                        value="#{globalResources.ok}"
                        action="#{pageBeanName.getAll}"/>
                        
       <t:commandButton forceId="true" id="backButtonCustomDiv1" styleClass="cssButtonSmall"
                         value="#{globalResources.back}"
                         onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null); return false;"/>
</t:panelGrid>