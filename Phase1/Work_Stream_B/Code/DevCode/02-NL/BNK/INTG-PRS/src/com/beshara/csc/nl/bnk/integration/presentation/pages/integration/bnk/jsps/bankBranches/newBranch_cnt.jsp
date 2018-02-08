<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%">

<t:panelGrid align="center" columns="6" border="0" 
    cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="98%" >
<t:panelGroup id="headerGroup" forceId="true" style="background-color:#ffffff;" colspan="6" rendered="#{ pageBeanName.listSize > 0 }">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{integrationBundle.branch_coordinators}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
</t:panelGrid>
    <t:panelGroup forceId="true" id="dataT_data_panel" styleClass="dataT-With-1-row-filter">
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.usingPaging ? pageBeanName.pagingBean.myPagedDataModel : pageBeanName.myTableData}" rowStyleClass="#{ pageBeanName.selected ? 'standardTable_RowHighlighted' : null}"
                     forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" binding="#{pageBeanName.myDataTable}"
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" preserveSort="true" sortColumn="#{pageBeanName.sortColumn}" rowOnDblClick="javascript:rowOnDblClickJs('chk',#{index});" sortAscending="#{pageBeanName.ascending}">
                
              
            <t:column id="civilId_column" sortable="false"  width="12%" >
                <f:facet name="header">
                    <t:commandLink id="sort_civilId" forceId="true" styleClass="headerLink" value="#{integrationBundle.CivilID}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilId') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='civilId') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_civilId" value="#{list.civilId}"/>
            </t:column>
            
            <t:column id="contactName_column" sortable="false" width="30%">
                <f:facet name="header">
                    <t:commandLink id="sort_name" forceId="true" styleClass="headerLink" value="#{integrationBundle.contactName}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='name') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="contact_Name" value="#{list.name}" />
            </t:column>

         <t:column id="directPhones_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_directPhones" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_Tel}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='directPhones') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='directPhones') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>                    
            </f:facet>
            <h:outputText id="contact_directPhones" value="#{list.directPhones}"/>
        </t:column>
        
         <t:column id="conactFax_column" width="10%">
            <f:facet name="header">
                <t:commandLink id="sort_fax" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_Fax}"
                               actionListener="#{pageBeanName.sortDataTable}">
                    <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fax') ? '/app/media/images/ascending-arrow.gif' :''}"
                                    border="0"/>
                    <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='fax') ? '/app/media/images/descending-arrow.gif' :''}"
                                    border="0"/>
                </t:commandLink>                    

            </f:facet>
            <h:outputText id="contact_Fax" value="#{list.fax}"/>
        </t:column>            

            <t:column id="contactMobile_column" width="10%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_mobileNo" forceId="true" styleClass="headerLink" value="#{integrationBundle.contactMobile}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='mobileNo') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='mobileNo') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactMobile" value="#{list.mobileNo}"/>
            </t:column>
            
            <t:column id="contactEmail_column" width="20%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_email" forceId="true" styleClass="headerLink" value="#{integrationBundle.branch_email}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='email') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactEmail" value="#{list.email}"/>
            </t:column>
            
            <t:column id="contactGender_column" width="5%" sortable="false">
                <f:facet name="header">
                    <t:commandLink id="sort_gender" forceId="true" styleClass="headerLink" value="#{integrationBundle.Type}"
                                   actionListener="#{pageBeanName.sortDataTable}">
                        <t:graphicImage value="#{(pageBeanName.sortAscending&&pageBeanName.fullColumnName=='gender') ? '/app/media/images/ascending-arrow.gif' :''}"
                                        border="0"/>
                        <t:graphicImage value="#{(!pageBeanName.sortAscending&&pageBeanName.fullColumnName=='gender') ? '/app/media/images/descending-arrow.gif' :''}"
                                        border="0"/>
                    </t:commandLink>                    
                </f:facet>
                <h:outputText id="content_contactGender" value="#{list.gender}"/>
            </t:column>
            
            <t:column id="contactUpdate_column" width="5%" sortable="false">
                
                    <t:commandButton type="button" styleClass="cssButtonSmaller" value="تعديل" >
                        <a4j:support event="onclick" action="#{pageBeanName.updateCoordinator}"  reRender="civilInList,msgShowId,customDiv1,divEditLookup,OperationBar"
                                oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv1);settingFoucsAtDivDelete();return false;"/>
                    </t:commandButton>                    
                
                <%--<h:outputText id="content_contactDel" value="حذف"/>--%>
            </t:column>

            <t:column id="contactDel_column" width="5%" sortable="false">
                
                    <t:commandButton type="button" styleClass="cssButtonSmaller" value="حذف" >
                        <a4j:support event="onclick" action="#{pageBeanName.delCoordinator}"  reRender="msgShowId,divDeleteAlert,divEditLookup,OperationBar"
                                oncomplete="javascript:changeVisibilityDiv(window.blocker,window.delAlert);settingFoucsAtDivDelete();return false;"/>
                    </t:commandButton>                    
                
                <%--<h:outputText id="content_contactDel" value="حذف"/>--%>
            </t:column>


        </t:dataTable>
        
    <t:panelGrid styleClass="btnSaveGrd" columns="1" border="0" align="center">
        <t:commandButton forceId="true" id="buttonSaveBranch" styleClass="cssButtonSmall" value="#{integrationBundle.saveBranch}" action="#{pageBeanName.saveBranchData}" onclick="return ValidateBranchEmail();"/>
    </t:panelGrid>        
        <t:inputHidden forceId="true" id="reloadList" valueChangeListener="#{pageBeanName.reloadList}" binding="#{pageBeanName.reloadField}"/>
    
    </t:panelGroup>
    
</t:panelGrid>

<script language="javascript" type="text/javascript">
    function ValidateBranchEmail()  
    {  
        var reWhitespace = /^\s+$/;
        if(document.getElementById('branchNameTxt')!=null)
        {
            inputTextValue= document.getElementById('branchNameTxt').value;
            if((inputTextValue == null) || (inputTextValue.length == 0) || reWhitespace.test(inputTextValue)) 
            {
                document.getElementById('nameError').style.display = "inline";
                return false;
            }
            else
            {
                document.getElementById('nameError').style.display = "none";
            }
        }
        else
        {
            document.getElementById('nameError').style.display = "inline";
            return false;
        }
        if(document.getElementById('branchMailTxt')!=null){
            inputTextValue= document.getElementById('branchMailTxt').value;
            if((inputTextValue == null) || (inputTextValue.length == 0) || reWhitespace.test(inputTextValue)) {
            document.getElementById('mailError').style.display = "none";
            return true;
            }
        }   
        else{
            document.getElementById('mailError').style.display = "none";
            return true;
        }
            
        var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
        if(inputTextValue.match(mailformat))  
        {  
            document.getElementById('mailError').style.display = "none";
            return true;  
        }  
        else {
            document.getElementById('mailError').style.display = "inline";
            document.getElementById('branchMailTxt').focus();
            return false;  
        }
    }  
</script>
