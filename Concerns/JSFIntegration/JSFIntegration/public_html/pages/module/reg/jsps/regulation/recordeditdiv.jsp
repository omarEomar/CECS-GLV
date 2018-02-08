<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGroup forceId="true" id="divEditLookup" style="width:100%;">
<t:dataList id="rowObject"
            var="rowObject"
            value="#{regulationRecordsBean.editableRecordDataDTO.recordDataRow}"
            layout="simple"
            rowCountVar="groupObjectRowCount"
            rowIndexVar="groupObjectRowIndex">
            
    <t:panelGrid columns="2" border="0" rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">          
        <t:outputText value="#{rowObject.regDesignTabColumnsDTO.name}" /> 
        <t:inputText value="#{rowObject.value}" styleClass="textbox"/>
    </t:panelGrid>
   

</t:dataList>                        
                        
</t:panelGroup> 

<t:panelGrid columns="3" border="0" align="center">
    <t:commandButton forceId="true" id="SaveButtonEdit" styleClass="cssButtonSmall" value="#{globalResources.SaveButton}" action="#{regulationRecordsBean.edit}" />
    <a4j:commandButton  id="CancelButtonEdit" action="#{regulationRecordsBean.cancelEdit}" reRender="divEditLookup,OperationBar,dataT_data_panel" styleClass="cssButtonSmall" value="#{globalResources.back}" oncomplete="hideLookUpDiv(window.blocker,window.lookupEditDiv,'edit_first_inputTxt','myForm:errorEdit');return false;" />
</t:panelGrid>