<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.scp.presentation.resources.scp" var="resourcesBundle"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions"  var="globalExceptions"/>
    <h:form id="myForm" binding="#{approveSalCalcBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{approveSalCalcBean}">
        
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            
             
            <t:saveState value="#{pageBeanName.employeesDTO}"/>
            
            <t:saveState value="#{pageBeanName.month}"/>
            <t:saveState value="#{pageBeanName.months}"/>
            <t:saveState value="#{pageBeanName.year}"/>
            <t:saveState value="#{pageBeanName.years}"/>
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            
            <%--t:saveState value="#{pageBeanName.salEmpMonSalariesDTO}"/--%>
            <t:saveState value="#{pageBeanName.salEmpPayslipsDTO}"/>
            <t:saveState   value="#{pageBeanName.actualSalary}"/>
            <t:saveState   value="#{pageBeanName.monSalList}"/>
            <tiles:insert flush="false" definition="approvesal.page"/>
        </t:aliasBean>

        <script type="text/javascript"> 
    
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
    
     </script>
</f:view>