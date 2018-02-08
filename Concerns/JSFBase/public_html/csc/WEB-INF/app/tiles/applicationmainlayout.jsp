<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.beshara.jsfbase.csc.util.SharedUtilBean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- read the current page direction from the SharedUtil Bean -->
<tiles:importAttribute scope="request"/>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="#{resourcesBundlePath}" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<%
    
%>
<html>
 <head>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Uitl.js"></script>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/GenericPopup.js"></script>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Tree.js"></script>
   <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/dw_event.js"></script>
  <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/dw_viewport.js"></script>
   <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/dw_tooltip.js"></script>
    <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Ajax.js"></script>
  
       <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/CustomComponentSearchBox.jsf"></script>
 

     
     <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/module/media/css/${globalResources.photoFolder}/Style.css" />
     <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/app/media/css/${globalResources.photoFolder}/Style.css" />
    <link rel="shortcut icon" href="${shared_util.contextPath}/app/media/images/favicon.ico"/>
  <title>
   ${resourcesBundle[titlepage]}
  </title>
   
  
 </head>

    <% 
        SharedUtilBean utilBean = (SharedUtilBean) session.getAttribute("shared_util"); 
        if(utilBean.isOnlineUser()) {
    %>
            <body onload="Tooltip.init()" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" dir="${shared_util.pageDirection}" >
                
                <t:div forceId="true" id='blocker' styleClass="blocker"/>
                <table border="0" width="810" id="table1" cellspacing="0" cellpadding="0" height="100%" align="center" class="gbody">
                     <tr>
                        <td colspan="3" class="Pass" ><a class="link" href="${shared_util.contextPath}/index.jsf" target="_parent">${resourcesBundle.module_div_title}</a> 
                        &laquo; 
                        ${resourcesBundle[titlepage]}</td>
                    </tr>
                    <tr>
                        <td colspan="3" >
                           <div id="divMsg" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divMsg}" <%}else{%> class="${pageBeanName.divMsg}" <%}%>>
                            <!-- css task used for apply divMsg images  -->
                            <table width="350"  border="0" cellpadding="0" cellspacing="0" align="center" dir="ltr">
                                <tr>
                                    <td><img src="${shared_util.contextPath}/app/media/images/popup/popup-pannel-08.gif" width="14" height="8"></td>
                                    <td background="${shared_util.contextPath}/app/media/images/popup/popup-pannel-09.gif"></td>
                                    <td><img src="${shared_util.contextPath}/app/media/images/popup/popup-pannel-01.gif" width="9" height="8"></td>
                                      </tr>
                                        <tr>
                                    <td background="${shared_util.contextPath}/app/media/images/popup/popup-pannel-07.gif"><img src="${shared_util.contextPath}/app/media/images/popup/popup-pannel-06.gif" width="14" height="35"></td>
                                    <td width="100%" bgcolor="#FFFFFF" dir="rtl" align="center">

                                    <tiles:insert name="msg"/>
                                    <td background="${shared_util.contextPath}/app/media/images/popup/popup-pannel-02.gif"></td>
                                    </tr>
                                    <tr>
                                    <td><img src="${shared_util.contextPath}/app/media/images/popup/popup-pannel-05.gif" width="14" height="12"></td>
                                    <td background="${shared_util.contextPath}/app/media/images/popup/popup-pannel-04.gif"></td>
                                    <td><img src="${shared_util.contextPath}/app/media/images/popup/popup-pannel-03.gif" width="9" height="12"></td>
                                    </tr>
                                    </table>
                                
                            </div>
                        </td>
                    </tr>
                    
                    <tr>
                        <td width="5"></td>
                        <td width="814">
                      <%
                                if(request.getAttribute("manyToMany").equals("true")) {
                            %>
                                    <iframe id='iFrame' frameborder="0" scrolling="no" class="${detailBeanName.iframeBlock}"></iframe>
                            <%
                                } else {
                            %> 
                                    <iframe id='iFrame' frameborder="0" scrolling="no" class="${pageBeanName.iframeBlock}"></iframe>
                            <%
                                }
                            %>
                            <iframe id='iFrameWait' frameborder="0" scrolling="no" class="divIframeWating"></iframe>
                            <table border="0" width="810" id="table1" cellspacing="0" cellpadding="0" height="100%" align="center" class="gbody">
                                    <tr>
                                      <td align="center"><tiles:insert name="wait"/></td>
                                    </tr>  
                                    <tr>
                                      <td align="center"><tiles:insert name="divwait"/></td>
                                    </tr>  
                                <tr>
                                   <td  width="810" valign="middle" class="logo" align="center">${resourcesBundle[titlepage]}</td>
                                </tr>
                                
                                <%
                                    if(request.getAttribute("showsteps").equals("true"))
                                    {
                                        %>
                                            <tr>
                                               <td  width="810" valign="middle" class="TitlePage" align="center"><tiles:insert name="steps"/></td>
                                            </tr>        
                                        <%
                                    }
                                %>
                                
                                 <%
                                    if(request.getAttribute("showSearch").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center">
                                                    <div id="divSearch" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divSearch}" <%}else{%> class="${pageBeanName.divSearch}" <%}%>>
                                                    
                                                    <table border="0" width="500" cellspacing="0" cellpadding="0">
                                                    <!-- css task used for apply search css  -->
                                                            <tr>
                                                                    <td height="45" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                                                                    <td height="45" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" colspan="3" bgcolor="#FFFFFF">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                            <tr>
                                                                                    <td class="divcontent" valign="top" height="150">
                                                                                    <div align="right"><tiles:insert name="search"/></div>
                                                                              </td>
                                                                            </tr>
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    
                                                      </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                %>
                                <%
                                    if(request.getAttribute("showTreediv").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center">
                                                    <div id="divTree" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divTreeAdd}" <%}else{%> class="${pageBeanName.divTreeAdd}" <%}%>>
                                                       <table border="0" width="600" cellspacing="0" cellpadding="0">
                                                    <!-- css task table used for Tree lookup adding showTreeAdd images  -->
                                                    <!-- edit by m.elsaied (fix issue key : NL-279 - 2.  add 001.JPG) -->
                                                            <tr>
                                                                    <td height="30" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="0" class="topgxdiv" width="97%">${resourcesBundle[title]}</td>
                                                                    <td height="3" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr>
                                                            <tr>
                                                                    <!-- <td valign="top" colspan="3" bgcolor="#FFFFFF"> -->
                                                                    <td colspan="3" class="divcontent1">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                        <tr>
                                                                            <td>                                                                            
                                                                                <table border="0" width="90%" height="100%" id="table5" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                        <td colspan="3" align="center" valign="middle" height="20"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                                                                        <td height="100%" width="100%"><div align="center"><tiles:insert attribute="treediv"/></div></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="3">&nbsp;</td>
                                                                                    </tr>
                                                                                </table>                                                                            
                                                                            </td>
                                                                        </tr>
                                                                                                                                      
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                %>
                                <%
                                    if(request.getAttribute("showTreeAdd").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center">
                                                    <div id="divTreeAdd" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divTreeAdd}" <%}else{%> class="${pageBeanName.divTreeAdd}" <%}%>>
                                                       <table border="0" width="500" cellspacing="0" cellpadding="0">
                                                    <!-- css task table used for Tree lookup adding showTreeAdd images  -->
                                                    <!-- edit by m.elsaied (fix issue key : NL-279 - 2.  add 001.JPG) -->
                                                            <tr>
                                                                    <td height="45" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                                                                    <td height="45" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr>
                                                            <tr>
                                                                    <!-- <td valign="top" colspan="3" bgcolor="#FFFFFF"> -->
                                                                    <td colspan="3" class="divcontent1">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                        <tr>
                                                                            <td>                                                                            
                                                                                <table border="0" width="75%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                        <td colspan="3" align="center" valign="middle" height="50"><span lang="ar-eg" class="TitlePage">${resourcesBundle[titleAddDiv]}</span></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                                                                        <td height="100%" width="100%"><div align="center"><tiles:insert attribute="treeadd"/></div></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="3">&nbsp;</td>
                                                                                    </tr>
                                                                                </table>                                                                            
                                                                            </td>
                                                                        </tr>
                                                                                                                                      
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                %>
                                <%
                                    if(request.getAttribute("showTreeEdit").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center">
                                                    <div id="divTreeEdit" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divTreeEdit}" <%}else{%> class="${pageBeanName.divTreeEdit}" <%}%>> 
                                                        <table border="0" width="500" cellspacing="0" cellpadding="0">
                                                        <!-- css task table used for lookup editing css -->
                                                        <!-- edit by m.elsaied (fix issue key : NL-279 - 2.  add 001.JPG) -->
                                                            <tr>
                                                                    <td height="45" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                                                                    <td height="45" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr> 
                                                            <tr>
                                                                    <td colspan="3" class="divcontent1">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                        <tr>
                                                                            <td>                                                                            
                                                                                <table border="0" width="90%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                        <td colspan="3" align="center" valign="middle" height="50"><span lang="ar-eg" class="TitlePage">${resourcesBundle[titleEditDiv]}</span></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                                                                        <td height="100%" width="100%"><div align="center"><tiles:insert attribute="treeedit"/></div></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="3">&nbsp;</td>
                                                                                    </tr>
                                                                                </table>                                                                            
                                                                            </td>
                                                                        </tr>
                                                                            
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                %>
                                <!--  Start Dive To Alret Before Delete added by Ahmed Abd El-Fatah-->
                                <%
                                    if(request.getAttribute("showDelAlertTree").equals("true"))
                                    {
                                        %>
                                          <tr>
                                                <td align="center">
                                                    <div id="delAlertTree" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.delAlertTree}" <%}else{%> class="${pageBeanName.delAlertTree}" <%}%>>  
                                                        <table border="0" width="500" cellspacing="0" cellpadding="0">
                                                        <!-- css task table used for lookup editing css -->
                                                        <!-- edit by m.elsaied (fix issue key : NL-279 - 2.  add 001.JPG) -->
                                                            <tr>
                                                                    <td height="45" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                                                                    <td height="45" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr> 
                                                            <tr>
                                                                    <td colspan="3" class="divcontent1">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                        <tr>
                                                                            <td>                                                                            
                                                                                <table border="0" width="90%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                        <td colspan="3" align="center" valign="middle" height="50"><span lang="ar-eg" class="TitlePage">${globalResources.deletealerttree}</span></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                                                                        <td height="100%" width="100%"><div align="center"><tiles:insert attribute="delalerttree"/></div></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                                                                        <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                                                                        <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td colspan="3">&nbsp;</td>
                                                                                    </tr>
                                                                                </table>                                                                            
                                                                            </td>
                                                                        </tr>
                                                                            
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    </div>
                                                </td>
                                            </tr>
                                  
                                        <%
                                    }
                                %>
                                <!--  End Dive To Alret Before Delete -->
                               <%
                                    if(request.getAttribute("showLookupAdd").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center">
                                                    <div id="lookupAddDiv" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.lookupAddDiv}" <%}else{%> class="${pageBeanName.lookupAddDiv}" <%}%>>                                                        
<!-- edit by m.elsaied (fix issue key : NL-279 - 2.  add 001.JPG) -->                                                                                    
                                                    
                                                    
<table border="0" width="500" cellspacing="0" cellpadding="0">
    <tr>
        <td height="45" valign="top" width="1%" align="right"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
        <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
        <td height="45" valign="top" width="1%" align="left"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
    </tr>
    <tr>
        <td colspan="3" class="divcontent1">
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="top" height="150">
                        <table border="0" width="75%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td colspan="3" align="center"><span lang="ar-eg" class="TitlePage">${resourcesBundle[titleAddDiv]}</span></td>
                            </tr>
                            <tr>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                            </tr>
                            <tr>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                <td height="100%" width="100%" valign="top"><div align="center"><tiles:insert attribute="lookupadd"/></div></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                            </tr>
                            <tr>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td valign="top" height="29" align="right" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
        <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
        <td valign="top" height="29" align="left" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
    </tr>
</table>
                                                    
                                                
                                                    
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                %>
                                <%
                                    if(request.getAttribute("showLookupEdit").equals("true"))
                                    {
                                        %>
                                           <tr>
                                                <td align="center">
                                                    <div id="lookupEditDiv" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.lookupEditDiv}" <%}else{%> class="${pageBeanName.lookupEditDiv}" <%}%>>  
                                                    
                                                    
                                                    
<table border="0" width="500" cellspacing="0" cellpadding="0">
    <tr>
        <td height="45" valign="top" width="1%" align="right"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
        <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
        <td height="45" valign="top" width="1%" align="left"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
    </tr>
    <tr>
        <td colspan="3" class="divcontent1">
            <table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" valign="middle" height="50"><span lang="ar-eg" class="TitlePage">${resourcesBundle[titleEditDiv]}</span></td>
                </tr>
                <tr>
                    <td valign="top" height="150">
                        
                        <table border="0" width="75%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td>
                            </tr>
                            <tr>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/>
                                <td height="100%" width="100%"><div align="center"><tiles:insert attribute="lookupedit"/></div></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/>
                            </tr>
                            <tr>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></td>
                                <td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/>
                                <td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td valign="top" height="29" align="right" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
        <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
        <td valign="top" height="29" align="left" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
    </tr>
</table>                                                    
                                                    
                                                    
                                                    
                                                    
                                                     
                                                    </div>
                                                </td>
                                            </tr>
                                        
                                        <%
                                    }
                                %>
 <%
                                    if(request.getAttribute("showDelAlert").equals("true"))
                                    {
                                        %>
<!-- edit by m.elsaied (fix issue key : NL-279 - 2.  Delete 001.JPG) -->
<tr>
    <td align="center">
        <div id="delAlert" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.delAlert}" <%}else{%> class="${pageBeanName.delAlert}" <%}%>>  
            <table border="0" width="500" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="45" valign="top" width="1%" align="right"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                    <td height="45" valign="top" width="1%" align="left"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                </tr>
                <tr>
                    <td class="divcontent1" colspan="3">
                        <table border="0" width="90%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td align="center" class="TitlePage">${resourcesBundle[titleDelDiv]}</td>
                            </tr>
                            <tr>
                                <td align="center"><tiles:insert attribute="delalert"/></td>
                            </tr>
                            <tr>
                                <td valign="bottom"/>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" height="29" align="right" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                    <td valign="top" height="29" align="left" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                </tr>
            </table>
        </div>
    </td>
</tr>
                                           
                                  
                                        <%
                                    }
                                %>
                                
<%
                                    if(request.getAttribute("showDelConfirm").equals("true"))
                                    {
                                        %>
                                           <tr>
    <td align="center">
        <div id="delConfirm" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.delConfirm}" <%}else{%> class="${pageBeanName.delConfirm}" <%}%>>  
            <table border="0" width="500" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="45" valign="top" width="1%" align="right"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                    <td height="45" valign="top" width="1%" align="left"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                </tr>
                <tr>
                    <td colspan="3" class="divcontent1">
                        <table border="0" width="90%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <td align="center" class="TitlePage">${resourcesBundle[titleDelConDiv]}</td>
                            </tr>
                            <tr>
                                <td><tiles:insert attribute="delconfirm"/></td>
                            </tr>                                        
                            <tr>
                                <td valign="bottom">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" height="29" align="right" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                    <td valign="top" height="29" align="left" width="1%"><img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                </tr>
            </table>
        </div>
    </td>
</tr>
                                        <%
                                    }
                                %>
                                <%
                                   if(request.getAttribute("showContent1").equals("true"))
                                   {
                                       %>
                                           <tr>
                                               <td align="center">
                                                   <table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td valign="bottom" width="19">
                                                            <img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                                                            <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                                                            <td valign="bottom" width="12">
                                                            <img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                                                            <td valign="top" bgcolor="#FFFFFF" class="paddingbox">
                                                            <div id="content1Div" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.content1Div}" <%}else{%> class="${pageBeanName.content1Div}" <%}%>>  
                                                               <tiles:insert attribute="content1"/>
                                                               </div>
                                                            </td>
                                                            <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="top" width="19">
                                                            <img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                                                            <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                                                            <td valign="top">
                                                            <img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                                                        </tr>
                                                   </table>
                                               </td>
                                           </tr>
                                       <%
                                   }
                               %>
                                <%
                                    if(request.getAttribute("showbar").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td align="center"><tiles:insert name="bar"/></td>
                                            </tr>       
                                        <%
                                    }
                                %>
                                
                                <%
                                    if(request.getAttribute("showtreeContent").equals("true"))
                                    {
                                        %>
                                             <tr>
                                                <td align="center">
                                                <table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="bottom" width="19">
								<img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
								<td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
								<td valign="bottom" width="12">
								<img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
							</tr>
							<tr>
								<td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
								<td valign="top" bgcolor="#FFFFFF" class="paddingbox">
                                                                                             <div style="height:142px;overflow:auto;">
                                                                                             <!--style="height:235px;overflow:auto;"-->
                                                                                                                      <tiles:insert attribute="content"/>
                                                                                             </div>
								</td>
								<td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
							</tr>
							<tr>
								<td valign="top" width="19">
								<img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
								<td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
								<td valign="top">
								<img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
							</tr>
						</table>
                                                
                                                
                                                    
                                                </td>
                                            </tr>      
                                        <%
                                    }
                                %>
                                
                                <!-- By Sherif.omar applying tree border -->
                               <%--
                                 this modification applied to adapt master detail use case (as we need scrolling in case detail page)
                                --%>
                                <%
                                    if(request.getAttribute("showdatatableContent").equals("true") && request.getAttribute("showContent1").equals("true") )
                                    { 
                                        %>
                                             <tr>
                                                <td width="99%" align="center" >
                                                    <div id="content" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divMainContentMany}" <%}else{%> class="${pageBeanName.divMainContentMany}" <%}%>>  
                                                        <tiles:insert attribute="content"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                    else if(request.getAttribute("showdatatableContent").equals("true"))
                                    {
                                            %>
                                             <tr>
                                                <td width="814" align="center" >
                                                    <div id="content" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.divMainContent}" <%}else{%> class="${pageBeanName.divMainContent}" <%}%>>  
                                                        <tiles:insert attribute="content"/>
                                                    </div>
                                                </td>
                                            </tr>
                                        <%
                                    }
                                                                        
                                %>
                                
                                
                                
                                <!--  Start Dive To show Tree Div Details added by Ahmed Abd El-Fatah-->
                                 <%
                                    if(request.getAttribute("showTreeDivDetails").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                <td width="810" align="center"><tiles:insert name="treedivdetails"/></td>
                                            </tr>
                                    <%
                                    }
                                %>
                                <!--  End Dive To show Tree Div Details -->

                                 <%
                                    if(request.getAttribute("showpaging").equals("true"))
                                    {
                                        %>
                                            <tr>
                                                 <td width="810" align="center" height="4px"></td>
                                            </tr>
                                            <tr>
                                                <td width="810" align="center"><tiles:insert name="paging"/></td>
                                            </tr>
                                    <%
                                    }
                                %>
                                   <%
                                 if(request.getAttribute("showMasterDetail").equals("true"))
                                    {
                                        %>
                                           <tr>
                                                <td align="center">
                                                    <div id="masterDetailDiv" <%if(request.getAttribute("manyToMany").equals("true")){%> class="${detailBeanName.masterDetailDiv}" <%}else{%> class="${pageBeanName.masterDetailDiv}" <%}%>>  
                                                        <table border="0" width="500" cellspacing="0" cellpadding="0">
                                                        <!-- css task table used for lookup delete Alert images  -->
                                                            <tr>
                                                                    <td height="45" valign="top" width="1%" align="right">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivtop.gif" width="10" height="45"></td>
                                                                    <td height="45" class="topgxdiv" width="97%"><span lang="ar-eg">${resourcesBundle[title]}</span></td>
                                                                    <td height="45" valign="top" width="1%" align="left">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivtop.gif" width="9" height="45"></td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" colspan="3" bgcolor="#FFFFFF">
                                                                    <table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                                            <tr>
                                                                                    <td valign="top" height="150">
                                                                                                                                                                        <table border="0" width="75%" height="90%" id="table5" cellspacing="0" cellpadding="0" align="center">
                                                                                        <tr>
                                                                                                <td valign="bottom" width="19">
                                                                                                <img border="0" src="${shared_util.contextPath}/app/media/images/div/R-top.gif" width="19" height="16"></td>
                                                                                                <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                                                                                                <td valign="bottom" width="12">
                                                                                                <img border="0" src="${shared_util.contextPath}/app/media/images/div/L-top.gif" width="12" height="16"></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                                <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/div/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                                                                                                <td valign="top" bgcolor="#FFFFFF" class="paddingbox">

                                                                                    <div align="center">
                                                                                    <tiles:insert attribute="masterdetaildiv"/>
                                                                                </div>
                                                                                </td>
                                                                                        <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td valign="top" width="19">
                                                                                        <img border="0" src="${shared_util.contextPath}/app/media/images/div/R-bottom.gif" width="19" height="11"></td>
                                                                                        <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                                                                                        <td valign="top">
                                                                                        <img border="0" src="${shared_util.contextPath}/app/media/images/div/L-bottom.gif" width="12" height="11"></td>
                                                                                </tr>
						</table>
                                                                                </td>
                                                                            </tr>
                                                                    </table>
                                                                    </td>
                                                            </tr>
                                                            <tr>
                                                                    <td valign="top" height="29" align="right" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/rightdivbot.gif" width="10" height="29"></td>
                                                                    <td valign="top" height="29" class="bottomgxdiv" width="98%">&nbsp;</td>
                                                                    <td valign="top" height="29" align="left" width="1%">
                                                                    <img border="0" src="${shared_util.contextPath}/app/media/images/div/leftdivbot.gif" width="9" height="29"></td>
                                                            </tr>
                                                    </table>
                                                    </div>
                                                </td>
                                            </tr>    
                                        <%
                                    }
                                %>
                                 <%
                                    if(request.getAttribute("showNavigation").equals("true"))
                                    {
                                        %>
                                          
                                            <tr>
                                                <td width="810" align="center"><tiles:insert name="navigation"/></td>
                                            </tr>
                                    <%
                                    }
                                %>
                                
                               
                                
                                
                                <tr><td width="810"  align="center" height="100%"></td></tr>
                            </table>                        
                        </td>
                        <td width="5"></td>
                    </tr>
                </table>
                 
<%

  if(request.getAttribute("showCommonData").equals("true"))
                                    {
                                        %>
                                   <tiles:insert name="commondata"/>
                                   
                                    <%
                                    }

                                %>
            <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/DataTable.js"></script>
            <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/FormElementsValidation.js"></script>
           <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/Div.js"></script>
          <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/TableHeader.js"></script>
           <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/module/js/module.js"></script>
            <script type="text/javascript" language="JavaScript1.2" src="${shared_util.contextPath}/app/js/m2m.js"></script>
           
           
           <jsp:include page="/app/js/globalJSvar.jsp"/>
           <script>
              ${pageBeanName.javaScriptCaller}
           </script>
   
           
            </body>
    <%
        }
    %>
</html>
