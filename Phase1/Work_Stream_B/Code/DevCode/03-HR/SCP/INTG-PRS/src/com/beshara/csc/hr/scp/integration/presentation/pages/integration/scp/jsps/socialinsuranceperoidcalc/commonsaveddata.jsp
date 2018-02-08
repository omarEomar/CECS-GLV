<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>


<htm:script>


 function validateRequiredata(){
  var validCivil=validatemyForm('civilValidation');
  if(validCivil){
    return validatemyForm('dateValidationGroup');
  }else{
    return false;
  }
 
 }
function validateCorrectCivil(){
  var validCivil=validatemyForm('civilValidation');
  if(validCivil)
   if(document.getElementById('myForm:resetData_btn_id') !=null){
         return true;
    }
    return false;
 }
 
 function enabelIntegerIncludeZero(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g, "");
    }
    for (i = 0;i < field.value.length;i++) {
        /*if (field.value.charAt(0) == '0') {
            field.value = field.value.replace(field.value.charAt(0), "");
            i--;
        }*/
    }
}
</htm:script>

<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.employeeHelper}" />
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.realCivilId}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.empDTO}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.civilId}"/>    
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.civilExist}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.validCivilId}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.enableEmpLovDiv}" />
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.payrollInfoExist}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.empHired}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.regNo}"/>    
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.catCode}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.minCode}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.ministryList}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.categoryList}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.fromDate}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.todate}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.calcTemplateType}" />
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.peroidPerMonth}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.yearPerMonth}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.tableNo}"/>    
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.yearPeroidInstallmentValue}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.installmentValueDK}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.totalPaymentValue}" />   
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.empHasInsurrance}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.filterPnlCollapsed}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.tabIndex}"/>
<t:saveState value="#{socialInsurPeroidCalcJobCategoryBean.saveCode}"/>

<t:inputHidden id="reportUrlId" forceId="true" value="#{socialInsurPeroidCalcJobCategoryBean.reportUrlLink}"/>


