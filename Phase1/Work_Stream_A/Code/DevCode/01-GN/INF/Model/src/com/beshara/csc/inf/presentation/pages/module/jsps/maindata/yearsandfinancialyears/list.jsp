<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{yearsBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{yearsBean}">
            <tiles:insert flush="false" definition="years.page">
                <t:saveState value="#{yearsBean.pageMode}"/>
                <t:saveState value="#{pageBeanName.yearCode}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.startDate}"/>
                <t:saveState value="#{pageBeanName.endDate}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
        <script type="text/javascript">
          foucsAddbuttonOnList();

          function foucsAddbuttonOnList() {
              if (document.getElementById('addButton') != null) {
                  document.getElementById('addButton').focus();
              }
          }

          function settingFoucsAtDivAdd() {
              if (document.getElementById('yearCode') != null) {
                  document.getElementById('yearCode').focus();
              }
          }

          function settingFoucsAtDivEdit() {
              if (document.getElementById('f_startFrom') != null) {
                  document.getElementById('f_startFrom').focus();
              }
          }

          function settingFoucsAtDivSearch() {
              if (document.getElementById('yearCodeSearch') != null) {
                  document.getElementById('yearCodeSearch').focus();
              }
              // clear Data befor open search Div
              document.getElementById('yearCodeSearch').value='';
              document.getElementById('yearNameSaerch').value='';
              document.getElementById('f_start_from_search').value='';
              document.getElementById('f_end_date_search').value='';
              
              
              
          }
          
          
        </script>
    </h:form>
</f:view>