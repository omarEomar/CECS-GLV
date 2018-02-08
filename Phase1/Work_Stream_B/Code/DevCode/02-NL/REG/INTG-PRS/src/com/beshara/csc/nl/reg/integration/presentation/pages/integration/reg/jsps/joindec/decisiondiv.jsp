<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration" var="regIntgResources"/>
<t:panelGroup forceId="true" id="decIntgDiv">
    <%-- ##################START################# Strat of Join/Cancel Section ##############SHEKA#####################--%>
     <%--<t:outputText value="#{jdHelperBeanName.activeDecisionCntPath} - #{jdHelperBeanName.joinDecisionPath} - #{jdHelperBeanName.relatedDecisionsPath} - #{jdHelperBeanName.navigationPath}"/>--%>
    <t:panelGroup forceId="true" id="decIntgJoinSection" rendered="#{jdHelperBeanName.joinMode}">
        <t:panelGroup forceId="true" id="decIntgAlertSection" rendered="#{jdHelperBeanName.showJoinAlert}">
            <t:outputText value="#{regIntgResources[jdHelperBeanName.alreadyHasDecisionAlertKey]}"
                          styleClass="#{jdHelperBeanName.alreadyHasDecisionAlertStyle}"
                          rendered="#{jdHelperBeanName.showJoinAlert}"/>
            <jsp:include page="${jdHelperBeanName.activeDecisionCntPath}" flush="true"/>
        </t:panelGroup>
        <t:panelGroup forceId="true" id="decIntgJoinDecSection" styleClass="#{jdHelperBeanName.joinContainerStyle}"
                      rendered="#{!jdHelperBeanName.showJoinAlert}">
            <jsp:include page="${jdHelperBeanName.joinDecisionPath}" flush="true"/>
        </t:panelGroup>
    </t:panelGroup>
    <%-- ##################START################# Strat of View Section #####################SHEKA#####################--%>
    <t:panelGroup forceId="true" id="decIntgViewSection" rendered="#{jdHelperBeanName.viewMode}">
        <jsp:include page="${jdHelperBeanName.relatedDecisionsPath}" flush="true"/>
    </t:panelGroup>
    <%-- ##################START################ Strat of Control Buttons Section ###########SHEKA#####################--%>
    <t:panelGroup forceId="true" id="decIntgNavSection">
        <jsp:include page="${jdHelperBeanName.navigationPath}" flush="true"/>
    </t:panelGroup>
    <%-- ##################START################ Strat of Hidden Values Section #############SHEKA#####################--%>
    <t:panelGroup forceId="true" id="decIntgHiddenValues">
        <t:inputHidden forceId="true" id="decIntgFullURLId" value="#{jdHelperBeanName.fullURL}"/>
        <t:outputText forceId="true" id="decIntgWindowTitleId" value="#{regIntgResources.decisionDetails}"
                      style="display:none;"/>
    </t:panelGroup>
</t:panelGroup>
<f:verbatim>
    <script type="text/javascript">
      function openRegIntgDecisionDetailsWindow() {
          openNewWindow('decIntgFullURLId', 'decIntgWindowTitleId');
      }

      function openNewWindow(fullURL, winName) {

          var url = document.getElementById(fullURL).value;
          //Modified by Ali Agamy
          var windowTitle;
          if (document.getElementById(winName) != null) {
              windowTitle = document.getElementById(winName).value;
              if (windowTitle == null || windowTitle == '') {
                  windowTitle = document.getElementById(winName).innerHTML;
              }
          }
          var wOpen;
          var sOptions;
          var popupWindowWidth = 920;
          var popupWindowheight = 540;

          sOptions = 'status=no,menubar=no,location=no,scrollbars=yes,toolbar=no,resizable=0,addressbar=0';
          sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
          sOptions = sOptions + ',height=' + (popupWindowheight).toString();
          sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

          globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=894 height=500 src=\"" + url + "\"/></body></html>";

          wOpen = window.open("", '', sOptions);
          wOpen.document.write(globalHTML);
          wOpen.focus();
          wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

          return wOpen;
      }
    </script>
</f:verbatim>