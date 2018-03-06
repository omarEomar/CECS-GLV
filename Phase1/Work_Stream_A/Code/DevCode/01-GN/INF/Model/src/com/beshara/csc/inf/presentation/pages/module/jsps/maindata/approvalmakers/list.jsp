<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{approvalMakersBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{approvalMakersBean}">
            <tiles:insert flush="false" definition="approvalMakersBean.page">
                <t:saveState value="#{approvalMakersBean.pageMode}"/>    
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
        
        <script type="text/javascript">
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){
                if(document.getElementById('addButton') != null){
                    document.getElementById('addButton').focus();
                }
            }
             function settingFoucsAtDivAdd() {
              if (document.getElementById('aprmakerName') != null) {
                  document.getElementById('aprmakerName').focus();
              }
          }

          function settingFoucsAtDivEdit() {
               if (document.getElementById('currNameEdit') != null) {
                  document.getElementById('currNameEdit').focus();
              }
          }
        </script>
    </h:form>
</f:view>