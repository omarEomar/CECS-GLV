<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{citiesListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{citiesListBean}">
            <tiles:insert flush="false" definition="citieslist.page">
                <t:saveState value="#{pageBeanName.masterDTO}"/>
                <t:saveState value="#{citiesListBean.pageMode}"/>
                <t:saveState value="#{citiesListBean.selectedDTOS}"/>
                <t:saveState value="#{citiesListBean.selectedPageDTO}"/>
                <t:saveState value="#{citiesListBean.singleSelection}"/>
                <t:saveState value="#{citiesListBean.masterBackBeanName}"/>
                <t:saveState value="#{countriesListBean.singleSelection}"/>
                <t:saveState value="#{countriesListBean.myTableData}"/>
                <t:saveState value="#{countriesListBean.highlightedDTOS}"/>
                <t:saveState value="#{countriesListBean.searchMode}"/>
                <t:saveState value="#{countriesListBean.selectedDTOS}"/>
                <t:saveState value="#{countriesListBean.selectedPageDTO}"/>
                <t:saveState value="#{countriesListBean.highlightedDTOS}"/>
                <t:saveState value="#{countriesListBean.fullColumnName}"/>
                <t:saveState value="#{countriesListBean.sortColumn}"/>
                <t:saveState value="#{countriesListBean.sortAscending}"/>
                <t:saveState value="#{countriesListBean.masterDTO}"/>
                <!--  Paging in countriesListBean -->
                <t:saveState value="#{countriesListBean.currentPageIndex}"/>
                <t:saveState value="#{countriesListBean.oldPageIndex}"/>
                <t:saveState value="#{countriesListBean.sorting}"/>
                <t:saveState value="#{countriesListBean.usingPaging}"/>
                <t:saveState value="#{countriesListBean.updateMyPagedDataModel}"/>
                <t:saveState value="#{countriesListBean.resettedPageIndex}"/>
                <t:saveState value="#{countriesListBean.pagingRequestDTO}"/>
                <t:saveState value="#{countriesListBean.saveSortingState}"/>
                <t:saveState value="#{countriesListBean.fullColumnName}"/>
                <t:saveState value="#{countriesListBean.currentSortingRowIndex}"/>
                <t:saveState value="#{countriesListBean.sortColumn}"/>
                <t:saveState value="#{countriesListBean.ascending}"/>
                <t:saveState value="#{shared_util.pageIndex}"/>
                <t:saveState value="#{countriesListBean.myDataTable}"/>
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