<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
    <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{officialVacListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{officialVacListBean}">
            <tiles:insert definition="officialvaclist.page" flush="false">
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedYear}"/>
                <t:saveState value="#{pageBeanName.yearSelected}"/>
                <t:saveState value="#{pageBeanName.selectedType}"/>
                <t:saveState value="#{pageBeanName.typeList}"/>
                <t:saveState value="#{pageBeanName.typeListAdd}"/>
                <t:saveState value="#{pageBeanName.yearList}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.editMode}"/>
                <t:saveState value="#{pageBeanName.yearName}"/>
                <t:saveState value="#{pageBeanName.pageMode}"/>
                <t:saveState value="#{pageBeanName.firstDayInYears}"/>
                <t:saveState value="#{pageBeanName.endDayInYears}"/>
                
                <%-- islam - to apply Sorting--%>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <tiles:put name="titleAddDiv" type="string"
                           value="${pageBeanName.editMode == true ? 'officialVac_editTitle' :  'officialVac_addTitle' }"/>
            </tiles:insert>
        </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptpanel">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>
    <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
                   value="fromDateAdd,50,80:untilDateAdd,50,90:search_pup_from_date,50,100:search_pup_End_date,50,110"/>
    <script type="text/javascript">
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){
                if(document.getElementById('addButton') != null){
                    document.getElementById('addButton').focus();
                }
            }
            
                function settingFoucsAtDivAdd() {
              if (document.getElementById('typeListAdd') != null) {
                  document.getElementById('typeListAdd').focus();
              }
          }
          
           function settingFoucsAtDivEdit() {
              if (document.getElementById('fromDateAdd') != null) {
                  document.getElementById('fromDateAdd').focus();
              }
          }
        </script>
</f:view>