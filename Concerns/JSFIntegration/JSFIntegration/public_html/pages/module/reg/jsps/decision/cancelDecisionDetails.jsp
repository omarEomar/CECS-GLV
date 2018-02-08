<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">

    
    <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
    
  
    <h:form id="myForm" binding="#{decisionListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{decisionListBean}" id="cancel_decisionList">  
    
        
        <tiles:insert definition="cancelDecisionDetails.page" flush="false">
        
            
            <t:saveState value="#{pageBeanName.canceledDecisionslist}"/>
            <t:saveState value="#{decisionListBean.indexArray}"/>
            <t:saveState  value="#{decisionListBean.fullColumnName}" />
            <t:saveState  value="#{decisionListBean.sortAscending}" />
            <t:saveState  value="#{decisionListBean.saveSortingState}" />
            <t:saveState  value="#{decisionListBean.sortColumn}" />            
        
        </tiles:insert>
      
      
        </t:aliasBean>
    </h:form>
    
</f:view>

