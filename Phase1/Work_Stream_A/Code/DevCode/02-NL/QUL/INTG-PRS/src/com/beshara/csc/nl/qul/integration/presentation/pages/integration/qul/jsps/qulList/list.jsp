<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%-- <tiles:importAttribute scope="request"/>--%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{qulListIntegrationBean.frm}" style="margin-bottom: 0">
    <%-- two Alias to handle list(pageBeanName) and manyTomany(detailBeanName) pages--%>
        <t:aliasBean alias="#{pageBeanName}" value="#{qulListIntegrationBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qulListIntegrationBean}">
                <f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration"
                              var="qulintegration"/>
                <tiles:insert definition="qulListIntegration.page" flush="false">
                    <%-- <t:saveState value="#{pageBeanName.pageDTO}"/>--%>
                    <t:saveState value="#{pageBeanName.civilId}"/>
                    <t:saveState value="#{pageBeanName.citizinFullName}"/>
                     <%--<t:saveState value="#{pageBeanName.kwtCitizenDTO}"/>--%>
                    <t:saveState value="#{pageBeanName.personsInformationDTOList}"/>
                    <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                    <t:saveState value="#{pageBeanName.listSize}"/>
                    <t:saveState value="#{pageBeanName.bundleMsg}"/>
                    <t:saveState value="#{pageBeanName.pageType}"/>
                </tiles:insert>
            </t:aliasBean>
        </t:aliasBean>
        <c2:scriptGenerator form="myForm"/>
        <script type="text/javascript">
          foucsAddbuttonOnList();

          function foucsAddbuttonOnList() {
              if (document.getElementById('addButton') != null) {
                  document.getElementById('addButton').focus();
              }
          }
        </script>
    </h:form>
</f:view>
