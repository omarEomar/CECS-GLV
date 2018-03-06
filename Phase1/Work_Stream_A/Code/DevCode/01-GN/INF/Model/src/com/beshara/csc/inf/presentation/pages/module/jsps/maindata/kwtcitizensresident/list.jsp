<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{kwtCitizensResidentBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{kwtCitizensResidentBean}">
            <t:saveState value="#{pageBeanName.kwtCitizensSearchDTO}"/>
            <t:saveState value="#{pageBeanName.singleSelection}"/>
            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <!-- Start Paging -->
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.oldPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
            <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
            <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
            <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
            <!-- End Paging -->
            <tiles:insert flush="false" definition="kwtCitizensResidentBean.page">
                <t:saveState value="#{kwtCitizensResidentBean.pageMode}"/>
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
        </script>
    </h:form>
</f:view>
