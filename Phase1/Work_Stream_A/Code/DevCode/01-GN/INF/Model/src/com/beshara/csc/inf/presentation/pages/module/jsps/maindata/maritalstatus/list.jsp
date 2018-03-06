<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{maritalStatusListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{maritalStatusListBean}">
            <tiles:insert flush="false" definition="maritalstatuslist.page"></tiles:insert>
            <t:saveState value="#{pageBeanName.genderTypesList}"/>
            <t:saveState value="#{pageBeanName.maleLinkEditDTO}"/>
            <t:saveState value="#{pageBeanName.femaleLinkEditDTO}"/>
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
            <t:saveState value="#{pageBeanName.genderMaritalDTO}"/>
            <t:saveState value="#{pageBeanName.pageMode}"/>
            <t:saveState value="#{pageBeanName.selectedGender}"/>
            <t:saveState value="#{pageBeanName.maleLinkDTO}"/>
            <t:saveState value="#{pageBeanName.femaleLinkDTO}"/>
            <t:saveState value="#{pageBeanName.showEditLinkDiv}"/>
            <t:saveState value="#{pageBeanName.showLinkDiv}"/>
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
              if (document.getElementById('maritalStatusName') != null) {
                  document.getElementById('maritalStatusName').focus();
              }
          }

          function settingFoucsAtDivEdit() {
              if (document.getElementById('currNameEdit') != null) {
                  document.getElementById('currNameEdit').focus();
              }
          }
        </script>
    </h:form>
    <c:scriptGenerator form="myForm"/>
</f:view>