<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="cv"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{ministriesPaymentOrderBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{ministriesPaymentOrderBean}">
            <tiles:insert definition="otherMinistriesPaymentOrder.page" flush="false"></tiles:insert>
            <%-- <t:inputHidden id="reportUrlId" forceId="true" value="#{pageBeanName.reportUrlLink}"/>--%>
            <%-- <t:inputHidden id="reportWindowId" forceId="true" value="#{pageBeanName.reportWindowName}"/>--%>
          
          
            <t:saveState value="#{pageBeanName.paymentOrderDetailsList}"/>
            <t:saveState value="#{pageBeanName.paymentOrderDetailsListSize}"/>
            <t:saveState value="#{pageBeanName.reportUrlLink}"/>
            <t:saveState value="#{pageBeanName.reportWindowName}"/>
            <t:saveState value="#{pageBeanName.signature}"/>
            <t:saveState value="#{pageBeanName.otherSignature}"/>
            <t:saveState value="#{pageBeanName.reciever}"/>
            <t:saveState value="#{pageBeanName.reciever1}"/>
            <t:saveState value="#{pageBeanName.reciever2}"/>
            <t:saveState value="#{pageBeanName.salSalarySheetsDTO}"/>
            <t:saveState value="#{pageBeanName.selDedToMin}"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            
             <t:saveState value="#{salarySheetEnqueryBean.pageDTO}"/>
            <t:saveState value="#{salarySheetEnqueryBean.paymentList}"/>
            <t:saveState value="#{salarySheetEnqueryBean.months}"/>
            <t:saveState value="#{salarySheetEnqueryBean.years}"/>
            <t:saveState value="#{salarySheetEnqueryBean.salarySheets}"/>
            <t:saveState value="#{salarySheetEnqueryBean.selectedRadio}"/>
                        <t:saveState value="#{salarySheetEnqueryBean.selectedDTOS}"/>
            <t:saveState value="#{salarySheetEnqueryBean.reportUrlLink}"/>
            <t:saveState value="#{salarySheetEnqueryBean.reportWindowName}"/>
            <t:saveState value="#{salarySheetEnqueryBean.paymentOrderDetailsList}"/>
            <t:saveState value="#{salarySheetEnqueryBean.totalEmpsCount}"/>
            <t:saveState value="#{salarySheetEnqueryBean.totalEmpsMoney}"/>
            <t:saveState value="#{salarySheetEnqueryBean.paymentOrderDetailsListSize}"/>
            <t:saveState value="#{salarySheetEnqueryBean.myTableData}"/>
            <t:saveState value="#{salarySheetEnqueryBean.decisionsList}"/>
            <t:saveState value="#{salarySheetEnqueryBean.finalMessage}"/>
            
            <t:saveState value="#{salarySheetEnqueryBean.accountNo}"/>
            <t:saveState value="#{salarySheetEnqueryBean.accountNoList}"/>
            <t:saveState value="#{salarySheetEnqueryBean.selDedToMinCode}"/>
            <t:saveState value="#{salarySheetEnqueryBean.selDedToMin}"/>
            <t:saveState value="#{pageBeanName.deductionDetailsList}"/>
            
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptGenerator">
                    <c:scriptGenerator form="myForm" />
        </t:panelGroup>
    </h:form>
</f:view>
