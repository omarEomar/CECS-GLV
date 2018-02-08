<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>

<t:panelGroup forceId="true" id="grsIntgDiv">

<%-- ##################START################# Strat of Join/Cancel Section ##############SHEKA##################### --%>
    <t:panelGroup forceId="true" id="grsIntgJoinCancelSection" rendered="#{jcHelperBeanName.joinMode || jcHelperBeanName.cancelMode}">
        <t:panelGroup forceId="true" id="grsIntgAlertSection" rendered="#{jcHelperBeanName.cancelMode || jcHelperBeanName.showJoinAlert}">
        
            <t:outputText value="#{grsIntgResources[jcHelperBeanName.cancelConditionAlertKey]}" 
                    styleClass="#{jcHelperBeanName.cancelConditionAlertStyle}" rendered="#{jcHelperBeanName.cancelMode}"/>
            <t:outputText value="#{grsIntgResources[jcHelperBeanName.changeActiveConditionAlertKey]}"
                    styleClass="#{jcHelperBeanName.changeActiveConditionAlertStyle}" rendered="#{jcHelperBeanName.showJoinAlert}"/>
            <jsp:include page="${jcHelperBeanName.activeConditionCntPath}" flush="true" />
            
        </t:panelGroup>
        
        <t:panelGroup forceId="true" id="grsIntgJoinCondSection" styleClass="#{jcHelperBeanName.joinContainerStyle}"
                rendered="#{jcHelperBeanName.joinMode && !jcHelperBeanName.showJoinAlert}">
            <jsp:include page="${jcHelperBeanName.joinConditionPath}" flush="true" />
        </t:panelGroup>
    </t:panelGroup>
    
<%-- ##################START################# Strat of View Section #####################SHEKA##################### --%>
    <t:panelGroup forceId="true" id="grsIntgViewSection" rendered="#{jcHelperBeanName.viewMode}">
        <jsp:include page="${jcHelperBeanName.relatedConditionsPath}" flush="true" />
    </t:panelGroup>
    
<%-- ##################START################ Strat of Control Buttons Section ###########SHEKA##################### --%>
    <t:panelGroup forceId="true" id="grsIntgNavSection">
        <jsp:include page="${jcHelperBeanName.navigationPath}" flush="true" />
    </t:panelGroup>
    
<%-- ##################START################ Strat of Hidden Values Section #############SHEKA##################### --%>
    <t:panelGroup forceId="true" id="grsIntgHiddenValues">
        <t:inputHidden forceId="true" id="grsIntgFullURLId" value="#{jcHelperBeanName.fullURL}"/>
        <t:outputText forceId="true" id="grsIntgWindowTitleId" value="#{grsIntgResources.conditionDetails}" style="display:none;"/>
    </t:panelGroup>
    
</t:panelGroup>
<f:verbatim>
<script type="text/javascript">
      function openGrsIntgConditionDetailsWindow() {
      var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew('grsIntgFullURLId' ,moduleName, 'grsIntgWindowTitleId');
      }
    function openGrsIntgNewWindow(fullURL, winNameInput) {
          var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew(fullURL ,moduleName,winNameInput);
          
//        var url = document.getElementById(fullURL).value;
//        var windowTitle = document.getElementById(winNameInput).innerHTML;
//        var wOpen;
//        var sOptions;
//        var popupWindowWidth = 632;
//        var popupWindowheight = 540;
//    
//        sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no';
//        sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
//        sOptions = sOptions + ',height=' + (popupWindowheight).toString();
//        sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';
//    
//        globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";
//    
//        wOpen = window.open("", '', sOptions);
//        wOpen.document.write(globalHTML);
//        wOpen.focus();
//        wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);
//    
//        return wOpen;
    }
</script>
</f:verbatim>
