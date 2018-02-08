<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.scp.integration.presentation.resources.integration" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions"  var="globalExceptions"/>
    <h:form id="myForm" binding="#{paySlipMaintainQueryBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{paySlipMaintainQueryBean}">
        <t:aliasBean alias="#{empSettEnquiryHelperBeanName}" value="#{paySlipMaintainQueryBean.empSettEnquiryBeanHelper}">
        
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            
            <t:saveState value="#{pageBeanName.civilId}"/>    
            
            <t:saveState value="#{pageBeanName.civilExist}" />   
            <t:saveState value="#{pageBeanName.validCivilId}"/>
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            <t:saveState value="#{pageBeanName.salEmpSalaryElementsDTO}"/>
            <t:saveState value="#{pageBeanName.degree}"/>
            
            <t:saveState value="#{pageBeanName.month}"/>
            <t:saveState value="#{pageBeanName.months}"/>
            <t:saveState value="#{pageBeanName.year}"/>
            <t:saveState value="#{pageBeanName.years}"/>
            <t:saveState value="#{pageBeanName.meritsList}"/>
            <t:saveState value="#{pageBeanName.empEndedService}"/>
            <t:saveState value="#{pageBeanName.deductionsList}"/>
            
            <%--t:saveState value="#{pageBeanName.salEmpMonSalariesDTO}"/--%>
            <t:saveState value="#{pageBeanName.salEmpPayslipsDTO}"/>
            <t:saveState  value="#{pageBeanName.totalDeductions}" />
            <t:saveState value="#{pageBeanName.totalMerits}" />
            <t:saveState   value="#{pageBeanName.actualSalary}"/>
            <t:saveState   value="#{pageBeanName.realCivilId}"/>
            <t:saveState   value="#{pageBeanName.empDTO}"/>
            <t:saveState   value="#{pageBeanName.monSalDTO}"/>
            <t:saveState value="#{pageBeanName.employeeHelper}" id="employeeHelper1"/>
            <t:saveState   value="#{pageBeanName.showPaySlipPanel}"/>
            
            <t:saveState value="#{pageBeanName.settler}"/>
            <t:saveState value="#{pageBeanName.hasIntervals}"/>
            
            <t:saveState value="#{pageBeanName.settlemenetType}"/>
            <t:saveState value="#{pageBeanName.retroactive_SettType}"/>
            <t:saveState value="#{pageBeanName.settWithDecsType}"/>
            <t:saveState value="#{pageBeanName.sheetCode}"/>
            
            <t:saveState value="#{pageBeanName.monYearDegree}"/>
            <t:saveState value="#{pageBeanName.monYearJob}"/>
            <t:saveState value="#{pageBeanName.monYearBgtPrg}"/>
            <t:saveState value="#{pageBeanName.monYearBgtType}"/>
            <t:saveState value="#{pageBeanName.monYearWorkCenter}"/>
            <t:saveState value="#{pageBeanName.currAccountNo}"/>
            <t:saveState value="#{pageBeanName.currBranch}"/>
            <t:saveState value="#{pageBeanName.currBank}"/>
            
            <t:saveState value="#{pageBeanName.paymentMethodCode}"/>
            <t:saveState value="#{pageBeanName.paymentMethods}"/>
            <t:saveState value="#{pageBeanName.showPaymentMethod}"/>
            
            
             <t:saveState value="#{pageBeanName.advancedPaymentMonthYear}"/>
            
            <tiles:insert flush="false" definition="payslipmaintainquery.page"/>
            </t:aliasBean>
        </t:aliasBean>

        <script type="text/javascript"> 
            adjustDataTable('meritsDiv',150); adjustDataTable('deductionsDiv',150);
    
            function resetMsgInAdd()
            {
                if(document.getElementById('invalCivilID') != null)
                {
                    document.getElementById('invalCivilID').innerHTML='';
                } 
                if(document.getElementById('empHired')!=null)
                {
                    document.getElementById('empHired').innerHTML='';
                }
                if(document.getElementById('payrollInfoExist')!=null)
                {
                    document.getElementById('payrollInfoExist').innerHTML='';
                }
            }
            
            
        </script>

    </h:form>
     <script type="text/javascript">
         setFocusOnElement('CivilIdAdd');
         
         function setFousAtNextAfterComboItem(){
         
            var civilIDValue = document.getElementById('civilExistHidden').value;
            if(civilIDValue == 'true'){
                setFocusOnlyOnElement('display_btn_id');
            }
            else {
                setFocusOnlyOnElement('CivilIdAdd');
            }
        }
        function adjustDataTable(tableId, tableHeight) {
            if (tableId == null) {
                tableId = 'myDataTableId';
            }
            if(document.getElementById(tableId) != null){
            var tableRect = document.getElementById(tableId).getBoundingClientRect();
            if (tableHeight == null) {
                var totalAvailableHeight = 502;
                tableHeight = totalAvailableHeight - tableRect.top;
            }
        
            var renderedHeight = tableRect.bottom - tableRect.top;
            var isIE = window.navigator.userAgent.indexOf("MSIE ") > 0;
            if (isIE) {
                document.getElementById(tableId).style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";
                document.getElementById(tableId).style.display = 'block';
                document.getElementById(tableId).style.height = tableHeight;
                document.getElementById(tableId).style.overflow = 'auto';
                document.getElementById(tableId).style.width = '100%';
            }
            else {
                if (tableHeight > renderedHeight) {
                    document.getElementById(tableId).style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";
                }
                else {
                    var myHeader = getNextElementByTag(tableId, 'THEAD');
                    tableHeight = tableHeight - myHeader.offsetHeight;
                    var scrollbarWidth = getScrollbarWidth();
        
                    var myBody = getNextElementByTag(tableId, 'TBODY');
        
                    var headerColumns = getTableHeaderColumns(tableId);
                    var originalWidthArr = [];
                    var headerWidth = myHeader.offsetWidth;
                    var colWidth = 0;
                    var deductedSpace = 0;
                    var lastIndex = headerColumns.length - 1;
                    for (var i = 0;i < headerColumns.length;i++) {
                        colWidth = headerColumns[i].offsetWidth;
                        deductedSpace = Math.round((scrollbarWidth / headerWidth) * (colWidth));
                        if (i == lastIndex) {
                            originalWidthArr[originalWidthArr.length] = (colWidth + deductedSpace);
                        }
                        else {
                            originalWidthArr[originalWidthArr.length] = (colWidth - deductedSpace);
                        }
                    }
        
                    myHeader.style = "display: block;width: 100%;"
                    myBody.style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";
        
                    for (var xx = 0;xx < headerColumns.length - 1;xx++) {
                        headerColumns[xx].style = "padding:0px;width:" + (originalWidthArr[xx]) + "px;"
                    }
        
                    headerColumns[lastIndex].style = "padding:0px;width:" + (originalWidthArr[lastIndex] + scrollbarWidth) + "px;"
        
                    var bodyRows = myBody.childNodes;
                    var bodyRowColumns = null;
                    for (var ii = 0;ii < bodyRows.length;ii++) {
                        bodyRowColumns = bodyRows[ii].childNodes;
                        for (var jj = 0;jj < bodyRowColumns.length;jj++) {
                            bodyRowColumns[jj].style = "padding:0px;width:" + originalWidthArr[jj] + "px;"
                        }
                    }
                }
            }
    }
}
    
     </script>
</f:view>