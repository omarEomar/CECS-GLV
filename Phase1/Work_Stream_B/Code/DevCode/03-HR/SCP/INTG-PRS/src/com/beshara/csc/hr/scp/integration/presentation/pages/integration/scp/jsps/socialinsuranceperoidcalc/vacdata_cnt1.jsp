<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.ContainerTitle { max-width: 245px; line-height: 18px; margin-top: 1px; }</htm:style>
<jsp:include page="/integration/scp/jsps/socialinsuranceperoidcalc/commonsaveddata.jsp"/>
<t:panelGroup forceId="true" colspan="6" id="hideQulDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="showHideFilterPnlFn();"></t:panelGroup>
</t:panelGroup>
<a4j:jsFunction action="#{socialInsurPeroidCalcJobCategoryBean.showHideFilterPnl}" name="showHideFilterPnlFn"
                reRender="hiddenBtnPnl,dataT_data_panel,mainDataEmpPanel,dataT_data_panel,paging_panel"
                oncomplete="togglePageUsingCstmHeight('hideQulDivImg', 'mainPnl', 'dataT_data_panel',82 ,372,document.getElementById('filterPnlCollapsed').value);"/>
<t:panelGrid id="mainPnl" forceId="true" width="100%">
    <t:panelGrid width="100%" forceId="true" id="mainDataEmpPanel" cellpadding="0" cellspacing="0" border="0">
        <jsp:include page="/integration/scp/jsps/socialinsuranceperoidcalc/maindata_cnt1.jsp"/>
       
    </t:panelGrid>
    
 
    
</t:panelGrid>
<t:panelGroup id="hiddenBtnPnl" forceId="true">
    <t:inputHidden id="filterPnlCollapsed" forceId="true"
                   value="#{socialInsurPeroidCalcJobCategoryBean.filterPnlCollapsed}"/>
</t:panelGroup>
