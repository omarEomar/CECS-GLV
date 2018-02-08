<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:view locale="#{shared_util.locale}">
   
    <h:form id="myForm" binding="#{documentTypesIntegrationBean.frm}" enctype="multipart/form-data">
        <t:aliasBean alias="#{pageBeanName}" value="#{documentTypesIntegrationBean}">
            <tiles:insert flush="false" definition="documentTypes.page">
   
            </tiles:insert>
        </t:aliasBean>
        <script type="text/javascript">
          function checkStringLength(searchStr) {
              if (searchStr.length < 1) {
                  if (document.getElementById('errorMessage') != null)
                      document.getElementById('errorMessage').innerHTML = lessThanTwo;
                  return true;
              }
              if (document.getElementById('errorMessage') != null)
                  document.getElementById('errorMessage').innerHTML = '';

              return false;
          }
        </script>
    </h:form>
</f:view>