<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{bloodGroupsListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{bloodGroupsListBean}">
            <tiles:insert flush="false" definition="bloodgroupslist.page">
            </tiles:insert>            
        </t:aliasBean>
        
    <!-- this java script method used with overriding to make search by name accept only one character 
    the default in the system search by name accepts minimum 2 characters
    
    Developed by M.Abdelsabour
    
    -->    
        
        <script type="text/javascript">
function checkStringLength(searchStr) {
        if(searchStr.length < 1) {
         if(document.getElementById('errorMessage')!=null)
            document.getElementById('errorMessage').innerHTML = lessThanTwo;
            return true;
        }
        if(document.getElementById('errorMessage')!=null)
          document.getElementById('errorMessage').innerHTML = '';
          
        return false;
    }

</script>
    </h:form>
</f:view>



