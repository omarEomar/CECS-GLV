 <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 
 <%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
 <%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
 
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/DataTable.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/FormElementsValidation.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Div.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/TableHeader.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}#{jsPaht}"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/m2m.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/ViewStateFix.js"/>


 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 
<script>
 /*
  global validation messages will appear if user enter wrong data 
 */
var isDateMessage='${globalResources.messageErrorForAdding}';
var compare2DatesMessage='${globalResources.error_message_to_date_must_be_after_from_date}';
</script>
<t:inputHidden forceId="true" id="seachValidationType" value="#{pageBeanName.seachValidationType}"/>
<t:inputHidden forceId="true" id="virtualvalueId" value="#{pageBeanName.virtualConstValue}"/>