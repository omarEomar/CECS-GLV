<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.csc.hr.bgt.integration.presentation.resources.integration" var="intgBundle"/>
    <h:form id="myForm" binding="#{joinBudgetBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{joinBudgetBean}">
                <tiles:insert definition="joinbudget.page" flush="false"/> 
                <t:saveState value="#{pageBeanName.selectedElementCode}"/>
                <t:saveState value="#{pageBeanName.selectedElementName}"/>
                <t:saveState value="#{pageBeanName.defaultFlag}"/>
                <t:saveState value="#{pageBeanName.backNavCase}"/>
                <t:saveState value="#{pageBeanName.caders}"/>
                <t:saveState value="#{pageBeanName.selectedCader}"/>
                <t:saveState value="#{pageBeanName.groups}"/>
                <t:saveState value="#{pageBeanName.selectedGroup}"/>
                <t:saveState value="#{pageBeanName.degrees}"/>
                <t:saveState value="#{pageBeanName.selectedDegree}"/>
                <t:saveState value="#{pageBeanName.selectedBudgetDTO}"/>
                
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.backMethod}"/>
                <t:saveState value="#{pageBeanName.savedDataObj}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedRadio}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.pageMode}"/>
        </t:aliasBean>
    </h:form>
    
    <t:panelGroup forceId="true" id="scriptGeneratorPanel">
        <c:scriptGenerator form="myForm"/>
     </t:panelGroup>
     <script type="text/javascript">
//        adjustDataTable('dataT_data_panel',255);
        function focusOnSearchText() {
            if(document.getElementById("searchText") != null){
                setTimeout("document.getElementById('searchText').focus()",400);
                document.getElementById('searchText').focus();
            }
        }
     </script>
</f:view>
