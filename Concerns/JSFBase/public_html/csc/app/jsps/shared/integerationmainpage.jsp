<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.beshara.jsfbase.csc.util.SharedUtilBean"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ page import="com.beshara.jsfbase.csc.backingbean.EncryptHelper" %>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="resources"/>
<%
    String content="";
    if(request.getParameter("location") != null)
    {
        String finals = EncryptHelper.decrypt(request.getParameter("location"));
        System.out.println("finals"+finals);
        content = finals;
    }
%>
<htm:html>
    <f:verbatim>
    <head>
   </f:verbatim>
           <htm:link rel="stylesheet" type="text/css" href="#{shared_util.contextPath}/app/media/css/ar/StyleView.css"/>
    <f:verbatim>    
    <title>النظام المتكامل لمعلومات الخدمه الوطنيه</title>
    </head>
    </f:verbatim>
    <htm:body>
        <htm:table border="0" width="997" cellspacing="0" cellpadding="0" align="center">
            <htm:tr>
                <htm:td valign="top" height="75">
                <%--<htm:img border="0" src="${shared_util.contextPath}/app/media/images/integration/head.jpg" width="997" height="96"/--%>
                    <htm:table  width="997" cellspacing="0" cellpadding="0" align="center">
                        <htm:tr>
                            <htm:td width="38" height="75" rowspan="2"><htm:img src="${shared_util.contextPath}/app/media/images/integration/header_pop_01.jpg" width="38" height="75"/></htm:td>
                            <htm:td width="59" height="16"><htm:img src="${shared_util.contextPath}/app/media/images/integration/header_pop_02.jpg" width="59" height="16" border="0" onclick="window.opener='self';window.top.close();" style="cursor:hand;"/></htm:td>
                            <htm:td width="903" height="75" rowspan="2"><htm:img src="${shared_util.contextPath}/app/media/images/integration/header_pop_03.jpg" width="903" height="75"/></htm:td>
                        </htm:tr>
                        <htm:tr>
                            <htm:td width="59" height="59"><htm:img src="${shared_util.contextPath}/app/media/images/integration/header_pop_04.jpg" width="59" height="59"/></htm:td>
                    	</htm:tr>
                    </htm:table>
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td valign="top" styleClass="gb" dir="rtl" align="right">
                        <htm:table border="0" width="100%" id="table5" cellspacing="0" cellpadding="0" align="center">
                            <htm:tr>
                                <htm:td valign="top" >
                                    <htm:table width="100%" border="0" cellpadding="0" cellspacing="0">
                                        <htm:tr>
<%--                                             <htm:td>
                                                <f:verbatim>
                                                    <iframe frameborder="0" align="right"  width="85%" height="410" name="body" id="body" scrolling="NO" src="<%=content%>" ></iframe>
                                                </f:verbatim>
                                            </htm:td>
--%>

                                             <htm:td valign="top" height="465">
                                            <%  if(!"".equals(content)) { %>
                                                <f:verbatim>
                                                    <iframe frameborder="0" align="right"  width="100%" height="435" name="body" id="body" scrolling="no"  src="<%=content%>" ></iframe>
                                                </f:verbatim>
                                                <% } else {%>
                                                
                                                 <t:outputLabel  style="font-family: Tahoma; font-size: 22pt;color:RED;" value="#{resources.underConstruction}" />
                                                 <% }%>
                                            </htm:td>
                                            
                                        </htm:tr>
                                    </htm:table>
                                </htm:td>
                                
                            </htm:tr>
                            
                        </htm:table>
                    
                </htm:td>
            </htm:tr>
            <htm:tr>
                <htm:td valign="top" height="31" bgcolor="#FFFFFF">
                        <htm:table border="0" width="431" cellspacing="0" cellpadding="0" align="center">
                            <htm:tr>
                                <htm:td height="31" styleClass="gfooter" align="center">
                                        <htm:span lang="ar-eg">جميع الحقوق محفوظة لديوان الخدمة المدنية&copy; 2008
                                    </htm:span>
                                </htm:td>
                            </htm:tr>
                        </htm:table>
                </htm:td>
            </htm:tr>
        </htm:table>
    </htm:body>
</htm:html>