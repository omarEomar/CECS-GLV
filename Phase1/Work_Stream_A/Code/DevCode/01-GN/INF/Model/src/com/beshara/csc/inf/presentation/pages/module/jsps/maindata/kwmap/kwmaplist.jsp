<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="cv"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="treeform" binding="#{kwMapListBean.frm}" style="margin-bottom: 0">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{kwMapListBean}">
            <tiles:insert flush="false" definition="kwMapListBean.page">
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.lovBaseBean.myTableData}"/>
                <t:saveState value="#{pageBeanName.kwStreetZoneLinkBean.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.lastAddedItem}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.disableStatusInAdd}"/>
                <t:saveState value="#{pageBeanName.disableStatusInEdit}"/>
                <t:saveState value="#{pageBeanName.usingTreePaging}"/>
                <t:saveState value="#{pageBeanName.dto}"/>
                <t:saveState value="#{pageBeanName.parentName}"/>
                <t:saveState value="#{pageBeanName.success}"/>
                <t:saveState value="#{pageBeanName.renderEdit}"/>
                <t:saveState value="#{pageBeanName.enableEdit}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptGeneratorPanel">
            <cv:scriptGenerator form="treeform"/>
        </t:panelGroup>
        <script type="text/javascript">
          foucsAddbuttonOnList();

          function foucsAddbuttonOnList() {
              if (document.getElementById('addButton') != null) {
                  document.getElementById('addButton').focus();
              }
          }

          function settingFoucsAtSearchDiv() {
              if (document.getElementById('lov_searchText') != null) {
                  document.getElementById('lov_searchText').focus();
              }
          }

          function expandSelectedTreeNodePath() {
              try {
                  var cell = document.getElementById("nodefocus").parentNode;
                  var selectedNode = cell.getElementsByTagName("label")[0];
                  var selectedNodeID = selectedNode.id;
                  selectedNodeID = selectedNodeID.substring(0, selectedNodeID.lastIndexOf(":"));
                  var perfex = selectedNodeID.substring(0, selectedNodeID.indexOf(":0:") + 1);
                  selectedNodeID = selectedNodeID.substring(selectedNodeID.indexOf(":0:") + 1, selectedNodeID.length);
                  var toClickNode;
                  while (selectedNodeID.length > 1) {
                      selectedNodeID = selectedNodeID.substring(0, selectedNodeID.lastIndexOf(":"));
                      toClickNode = perfex + selectedNodeID + ":t2";
                      if (document.getElementById(toClickNode).src.indexOf("nav-plus-line-middle.gif") !=  - 1) {
                          doClick(toClickNode, null);
                      }
                  }
                  var divMainContent = document.getElementsByClassName("divMainContent")[0];
                  divMainContent.scrollTop = 0;
                  var pos = findPos(selectedNode);
                  if (pos > divMainContent.clientHeight) {
                      divMainContent.scrollTop = pos - 150;
                  }
              }
              catch (e) {
                  // alert("Error: " + e);
              }
          }

          function findPos(obj) {
              var curtop = 0;
              if (obj.offsetParent) {
                  do {
                      curtop += obj.offsetTop;
                  }
                  while (obj = obj.offsetParent);
                  return [curtop];
              }
          }
        </script>
    </h:form>
</f:view>