<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<f:view locale="#{shared_util.locale}">
   <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
   <f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="grsResources"/>
   <f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions" var="globalexceptions"/>
   <h:form id="myForm" binding="#{conditionListBean.frm}">
      <t:aliasBean alias="#{pageBeanName}" value="#{conditionListBean}">
         <tiles:insert definition="conditionlist.page" flush="false">
            <t:saveState value="#{pageBeanName.renderedBack}"/>
            
<t:saveState value="#{pageBeanName.fullColumnName}"/>
<t:saveState value="#{pageBeanName.sortAscending}"/>
<t:saveState value="#{conditionListBean.viewInCenter}"/>
            
         </tiles:insert>
      </t:aliasBean>
   </h:form>
      
<script type="text/javascript"> 
    foucsAddbuttonOnList();
    function foucsAddbuttonOnList(){        
        if(document.getElementById('addButton') != null){            
            document.getElementById('addButton').focus();
        }
    }
    
    function setFocusAtMySearchDiv(){
    setFocusOnlyOnElement('conditionCodeID'); 
    }
    
</script>
</f:view>