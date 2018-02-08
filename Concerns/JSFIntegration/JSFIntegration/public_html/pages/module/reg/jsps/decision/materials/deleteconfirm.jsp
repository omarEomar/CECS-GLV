<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>


 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<t:panelGroup forceId="true" id="divDeleteConfirm" styleClass="delDivScroll">

    <t:dataTable id="dataT_DeleteConf" var="list"
             value="#{detailBeanName.nonDeletedMaterial}" preserveDataModel="false"
             renderedIfEmpty="false" 
             footerClass="grid-footer" sortable="false"
             styleClass="grid-footer"
             headerClass="standardTable_Header"
             rowClasses="standardTable_Row1,standardTable_Row2"
             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
             rowIndexVar="index"
             >
        <t:column sortable="false"  width="75%" defaultSorted="true">
            <f:facet name="header">   
                <h:outputText id="header_name" value="#{regResources.SubjectName}"/>
             </f:facet>
            <h:outputText id="content_name" value="#{list.decmaterialHeader}"/>
        </t:column>    
    </t:dataTable>
</t:panelGroup>


<t:panelGrid columns="3" border="0" align="center" >
 <t:commandButton forceId="true" id="CancelButtonDelConfirmDiv" onblur="settingFoucsAtDivDeleteConfirm();" type="button" value="#{globalResources.back}"  onclick="hideLookUpDiv(window.blocker,window.delConfirm,null,null);" styleClass="cssButtonSmall"/>
</t:panelGrid>