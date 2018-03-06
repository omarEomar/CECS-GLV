<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<t:panelGrid border="0" columnClasses="" cellpadding="1" forceId="true" id="cnt1Panel" cellspacing="0" columns="2"
             width="100%" rowClasses="row01,row02">
    <t:panelGroup style="display: block; width: 15px;">
        <h:outputText value="#{resourcesBundle.Year}" styleClass="lable01"/>
    </t:panelGroup>
    <t:panelGroup style="width:85%;">
        <t:selectOneMenu forceId="true" id="yearListList" value="#{pageBeanName.selectedYear}"
                         styleClass="textboxmedium">
            <f:selectItem itemValue="#{managedConstantsBean.virtualStringValueConstant}"
                          itemLabel="#{resourcesBundle.select_one_item}"/>
            <t:selectItems itemLabel="#{yearList.code.key}" itemValue="#{yearList.code.key}" var="yearList"
                           value="#{pageBeanName.yearList}"/>
            <a4j:support event="onchange" actionListener="#{pageBeanName.yearChanged}"
                         reRender="OperationBar,dataT_data_panel,divAddLookup,paging_panel,searchPnl"/>
        </t:selectOneMenu>
    </t:panelGroup>
</t:panelGrid>
<script type="text/javascript">
  setFocusAtYearListList();

  function setFocusAtYearListList() {
      if (window.divSearch.style.visibility == 'visible') {
          setFocusAtYearListSearch();
      }
      else if (document.getElementById('yearListList') != null) {
          document.getElementById('yearListList').focus();
      }
  }

  function doOnBlurYearListList() {
      document.getElementById('addButton').focus();

  }

  function setFocusAtTypeListAdd() {
      if (document.getElementById('typeListAdd') != null) {
          document.getElementById('typeListAdd').focus();
      }
  }

  function setFocusAtFromDateAdd() {
      if (document.getElementById('fromDateAdd') != null) {
          document.getElementById('fromDateAdd').focus();
      }
  }

  function setFocusAtYearListSearch() {
      if (document.getElementById('yearListSearch') != null) {
          document.getElementById('yearListSearch').focus();
          document.getElementById('yearListSearch').focus();
          document.getElementById('yearListSearch').focus();
          document.getElementById('yearListSearch').focus();
          document.getElementById('yearListSearch').focus();
      }
  }
</script>