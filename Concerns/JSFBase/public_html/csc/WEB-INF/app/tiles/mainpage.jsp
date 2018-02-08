<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.beshara.jsfbase.csc.util.SharedUtilBean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!-- read the current page direction from the SharedUtil Bean -->
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global_ar" var="globalResources"/>
<f:loadBundle basename="#{resourcesBundlePath}" var="resourcesBundle"/>
<html>
 <head>
    <meta HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=UTF-8" />  
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Uitl.js"></script>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Shortcuts.js"></script>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/module/js/NavigationMenu.jsf"></script>
    <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/app/media/css/${globalResources.photoFolder}/Style.css" />
     <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/module/media/css/${globalResources.photoFolder}/Style.css" />
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/GenericPopup.js"></script>
    <link rel="shortcut icon" href="${shared_util.contextPath}/app/media/images/favicon.ico">
  <title>
    ${resourcesBundle.module_div_title}
  </title>
 </head>
   
            <body onunload="HandleOnClose()"  onclick="checkModal();" onfocus="return checkModal();" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" dir="${shared_util.pageDirection}" style="overflow: hidden;">
                <t:div forceId="true" id='blocker' styleClass="blocker" style="height:100%;width:100%;overflow:hidden;"/>
                
                <div class="mainpg" >
                <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" height="640" align="center">
                    <tr>
                        <td valign="top">
                              <table border="0" id="table1" cellspacing="0" cellpadding="0" class="headtbl">
                                <tr><td><tiles:insert attribute="header"/></td></tr>
                              </table>
                        </td>
                    </tr>
                    <tr>
                        <td height="100%">
                              <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" height="100%" class="MainTbl">
                                    <tr>
                                        <td valign="top" dir="rtl" width="290" >
                                         <div  style="position:absolute;top:60px;left:649px;width:296px;visibility:hidden;height:440px;z-index:100;" id="navigationMenuTD">
                                            <tiles:insert attribute="tree"/>
                                          </div>  
                                          <iframe id='iFrameNavgationMenu' frameborder="0" scrolling="no" class="iFrameNavgationMenu" ></iframe>
                                        </td>
                                         <td valign="top">
                                            <div  style="position:absolute;width:35px;left:910px;margin-top: 25px;text-align:center;height=100%;z-index:100;" id="div_nav_btn">
                                            <table class="treeButton" border="0" cellspacing="0" cellpadding="0" height="100%">
                                                <tr>
                                                    <td>
                                                        <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" height="100%">
                                                            <tr><td><input type="button" id="nav_btn" class="navagation_btn" onclick="changeVisibilityNavigationMenu();"></td></tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                          </div>
                                         </td>
                                        <td width="100%" valign="top" height="100%" class="gbody"  >
                                           <!-- <div style="height:235px;overflow:auto;">-->
                                            <table border="0" width="948px"  align="left"  cellspacing="0" cellpadding="0" height="100%">
                                               <%--tr>
                                                    <td height="24" width="948px"><tiles:insert attribute="loginHeader"/></td>
                                                </tr--%>
                                                
                                               <tr onclick="hideNavigationMenu();">
                                                    <td width="100%" height="100%" valign="top" >
                                                        <tiles:insert attribute="body"/>
                                                    </td>
                                               </tr>
                                            </table>
                                           <!-- </div>-->
                                        </td>
                                        
                                    </tr>
                                    
                              </table>
                        </td>
                    </tr>
                    
                    <tr>
                        <td valign="bottom" height="22">
                              <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" height="22" >
                                <tr><td><tiles:insert attribute="footer"/></td></tr>
                              </table>
                        </td>
                    </tr>
                </table>
                
                <jsp:include page="/WEB-INF/app/tiles/notifications.jsp"/>
                
                <t:panelGrid columns="1" id="groupNamePanel" styleClass="groupNamePanel" >
                    <t:outputText value="#{Login.citizenName}" />
                    <t:outputText value="#{Login.ministryName}" />
                    <t:outputText value="#{Login.groupName}" />
                    <t:panelGroup>
                    <t:outputText value="#{globalResources.UserCode}" />
                    <t:outputText value="#{Login.userName}" />
                    </t:panelGroup>
                    <t:panelGroup>
                    <t:outputText value="#{globalResources.UserGroup}" />
                    <t:outputText value="#{Login.groupCode}" />
                    </t:panelGroup>
                </t:panelGrid>
                 <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/notifications.js"></script>
                 
                </div>
            </body>
</html>
