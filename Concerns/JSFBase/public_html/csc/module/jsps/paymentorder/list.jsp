<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{paymentOrderListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{paymentOrderListBean}">
            <tiles:insert definition="paymentorderadd.page" flush="false">
                
                
               
                
            </tiles:insert>
        </t:aliasBean>
    <t:panelGroup forceId="true" id="scriptpanel">
          <c:scriptGenerator form="myForm" />
    </t:panelGroup>  
    </h:form>
  
</f:view>
