<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<t:panelGrid id="bankBranchDataPanel" align="center" columns="6" border="0" 
    cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >
    
    <%--h:outputText value="#{integrationBundle.branch_bank_code}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="bankID" value="#{branchesListBean.bankBranchDTO.banksDTO.code.key}" styleClass="textbox"  /--%>
    
    <%--start 1st row--%>
    <h:outputText value="#{integrationBundle.banks_name_label}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="bankName" value="#{branchDetailsListBean.bankBranchDTO.banksDTO.name}" disabled="true"/>

    <h:outputText value="#{integrationBundle.branch_Code}" styleClass="divtext"/>
    <t:inputText styleClass="textbox" forceId="true" id="bankBranchID" value="#{branchDetailsListBean.bankBranchDTO.code.bnkbranchCode}" disabled="true"  maxlength="5"/>
    
    <h:outputText value="#{integrationBundle.branch_Name}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="bankBranchName" value="#{branchDetailsListBean.bankBranchDTO.name}" disabled="true"/>
    <%--end 1st row--%>
    
    <%--start 2nd row--%>
    <h:outputText value="#{integrationBundle.branch_email}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="branchEmail" value="#{branchDetailsListBean.bankBranchDTO.email}" disabled="true"/>

    <h:outputText value="#{integrationBundle.branch_Tel}" styleClass="divtext"/>
    <t:inputText styleClass="textbox" forceId="true" id="branchTel" value="#{branchDetailsListBean.bankBranchDTO.phonesNo}" disabled="true"  maxlength="5"/>
    
    <h:outputText value="#{integrationBundle.branch_Fax}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="branchFax" value="#{branchDetailsListBean.bankBranchDTO.faxNo}" disabled="true"/>
    <%--end 2nd row--%>

    <%--start 3rd row--%>
    <%--<t:panelGroup colspan="6">
        <h:outputText value="#{integrationBundle.branch_Address}" styleClass="divtext"/>
        --%><%--<t:inputText styleClass="textboxmedium" forceId="true" id="branchAddress" value="#{branchDetailsListBean.bankBranchDTO.addressInDetails}" disabled="true"/>--%><%--
    </t:panelGroup>--%>

<t:panelGroup colspan="6" style="background-color:#ffffff;">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="2">
        <htm:tr>
            <htm:td width="9">
                <t:graphicImage value="/app/media/images/op_arrow.jpg" width="9" height="9" border="0"/>
            </htm:td>
            <htm:td style="background: none repeat scroll 0% 0% white;">
                <t:outputLabel value="#{integrationBundle.branch_Address}" styleClass="lable01"/>
            </htm:td>
        </htm:tr>
         
        <htm:tr>
            <htm:td colspan="2" height="1">
                <t:graphicImage value="/app/media/images/seprator_group.jpg" width="100%" height="1" border="0"/>
            </htm:td>
        </htm:tr>
    </htm:table>
</t:panelGroup>
            
    <h:outputText value="#{integrationBundle.branches_governrate}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="branches_governrate" value="#{branchDetailsListBean.bankBranchDTO.governate}" disabled="true"/>

    <h:outputText value="#{integrationBundle.branches_district}" styleClass="divtext"/>
    <t:inputText styleClass="textbox" forceId="true" id="branches_district" value="#{branchDetailsListBean.bankBranchDTO.zone}" disabled="true" />
    
    <h:outputText value="#{integrationBundle.branches_sector}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="branches_sector" value="#{branchDetailsListBean.bankBranchDTO.part}" disabled="true"/>
    <%--end 3rd row--%>
    
    
    <%--start 4th row--%>
    <h:outputText value="#{integrationBundle.branches_streets}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="branches_streets" value="#{branchDetailsListBean.bankBranchDTO.streetName}" disabled="true" />
    
    <h:outputText value="#{integrationBundle.branches_building}" styleClass="divtext"/>
    <t:panelGroup colspan="3">
        <t:inputText styleClass="textbox" forceId="true" id="branches_building" value="#{branchDetailsListBean.bankBranchDTO.buildingNo}" disabled="true"/>
    </t:panelGroup>
    <%--end 4th row--%>
    
</t:panelGrid>
