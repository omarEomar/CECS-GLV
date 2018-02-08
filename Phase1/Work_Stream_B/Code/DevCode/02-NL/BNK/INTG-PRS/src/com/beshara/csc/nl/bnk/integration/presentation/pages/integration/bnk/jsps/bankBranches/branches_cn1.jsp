<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid id="bankDataPanel" align="center" columns="4" border="0" cellpadding="3" cellspacing="0" rowClasses="row01,row02" width="100%" >

    
    <h:outputText value="#{integrationBundle.branch_bank_code}" styleClass="divtext"/>
    <t:inputText disabled="true" maxlength="5" forceId="true" id="bankBranchID" value="#{branchesListBean.bankDTO.code.key}" styleClass="textbox"  />
    
    <h:outputText value="#{integrationBundle.banks_name_label}" styleClass="divtext"/>
    <t:inputText styleClass="textboxmedium" forceId="true" id="bankBranchName" value="#{branchesListBean.bankDTO.name}" disabled="true"/>
    

</t:panelGrid>
