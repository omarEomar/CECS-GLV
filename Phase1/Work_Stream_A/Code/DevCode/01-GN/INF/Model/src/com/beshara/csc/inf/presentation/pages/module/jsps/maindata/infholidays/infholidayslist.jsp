<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

<f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{infHolidaysListBean.frm}">

  <t:aliasBean alias="#{pageBeanName}" value="#{infHolidaysListBean}">
     
        <tiles:insert definition="infholidayslist.page" flush="false">
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
	    <t:saveState value="#{pageBeanName.selectedYears}"/>
            <t:saveState value="#{pageBeanName.yearsList}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>
            <t:saveState value="#{pageBeanName.enableAddButton}"/>
        </tiles:insert>
		
      </t:aliasBean>
	   <t:panelGroup forceId="true" id="scriptPanelGroup">
     <c:scriptGenerator form="myForm"/>
 </t:panelGroup>

    </h:form>

  
  
</f:view>
