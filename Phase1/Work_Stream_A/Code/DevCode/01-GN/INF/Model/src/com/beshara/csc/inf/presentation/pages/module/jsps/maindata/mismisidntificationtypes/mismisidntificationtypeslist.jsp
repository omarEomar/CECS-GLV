<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">

<f:loadBundle basename="com.beshara.csc.hr.mis.presentation.resources.mis" var="resourcesBundle"/>
    <h:form id="myForm" binding="#{misMisIdntificationTypesListBean.frm}">

  <t:aliasBean alias="#{pageBeanName}" value="#{misMisIdntificationTypesListBean}">
     
        <tiles:insert definition="mismisidntificationtypeslist.page" flush="false">
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
			
        </tiles:insert>
		
      </t:aliasBean>
	  
    </h:form>

  
  
</f:view>
