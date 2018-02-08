<%--%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

    <f:verbatim>
    
    <style type="text/css">
        .UserHeaderRow
        {
            color: #000000; 
            vertical-align:middle;
            font-family: Tahoma; 
            font-size: 7pt;     
            border-left-width: 1px; 
            border-right-width: 1px; 
            border-top-width: 1px; 
            border-bottom: 1px solid #FFFFFF; 
            background-color: #F3F3F8; 
            padding-right:5px;
            text-align: right;
        }    
        .UserHeaderLabel{
            font-family: Tahoma; 
            font-size: 7pt; 
            color: #EFB34F;  
            padding-top:4px; 
            padding-bottom:1px;
            border: 0;     
            height: 25px;
            text-align:right;
            vertical-align: middle;
        }      
        .UserHeaderText{
            font-family: Tahoma; 
            font-size: 7pt; 
            color: #083866;  
            padding-top:4px; 
            padding-bottom:1px;
            padding-left: 4px;
            padding-right: 4px;
            border: 0;     
            height: 25px;
            text-align:right;
            vertical-align: middle;
        }        
    </style>
    
    <table border="0" width="100%"  cellspacing="0" cellpadding="0" style="height:100%">
        <tr>
        
        <td width="40%" class="UserHeaderRow">
        </f:verbatim>
        <t:outputLabel value="المستخدم الحالي : " styleClass="UserHeaderLabel"/>
        <t:outputText value="#{Login.citizenName}" styleClass="UserHeaderText"/>
        <%--f:verbatim>[<b></f:verbatim--%>
        <%--t:outputText value="#{Login.userName}" styleClass="UserHeaderText"/>
        <%--f:verbatim></b>]</f:verbatim--%>
        <%--f:verbatim>
        </td>                                                    
        
         <td width="20%" class="UserHeaderRow">
         </f:verbatim>
        <t:outputLabel value="الجهة : " styleClass="UserHeaderLabel"/>
        <t:outputText value="#{Login.ministryName}" styleClass="UserHeaderText"/>
        <f:verbatim>
        </td>                                                    
        
         <td width="20%" class="UserHeaderRow">         
         </f:verbatim>
        <t:outputLabel value="المجموعة : " styleClass="UserHeaderLabel"/>
        <t:outputText value="#{Login.groupName}" styleClass="UserHeaderText"/>
        <f:verbatim>
        </td>
        
         <td width="20%" class="UserHeaderRow">
         </f:verbatim>
        <t:outputLabel value="تاريخ الدخول : " styleClass="UserHeaderLabel"/>
        <t:outputText value="#{Login.loginTimeFormatted}" styleClass="UserHeaderText" />
        <f:verbatim>
        </td>
        
     </tr>
</table>
</f:verbatim--%>