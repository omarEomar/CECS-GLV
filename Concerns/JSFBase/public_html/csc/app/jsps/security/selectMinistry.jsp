<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.sec.web.jsf.resources.login" var="loginResources"/>
    <f:loadBundle basename="com.beshara.csc.sec.web.jsf.resources.login" var="loginExtResources"/>
    <html>
        <head>
            <!--<link rel="stylesheet" type="text/css"
                      href="${SharedUtils.contextPath}/ContextPathWriter/css/login.css"/>-->
            <link rel="stylesheet" type="text/css" href="../../media/css/login.css"/>
            <meta http-equiv="CACHE-CONTROL" content="NO-CACHE"/>
            <meta http-equiv="EXPIRES" content="0"/>
            <title>
                <h:outputText value="#{loginResources.loginTitle}"/>
            </title>
            <script type="text/javascript">
              function showTitleTip(theComboBox) {
                  var selectedIndex = theComboBox.selectedIndex;
                  if (selectedIndex >= 0) {
                      theComboBox.title = theComboBox[selectedIndex].text;
                  }
              }
            </script>
        </head>
        <body style="text-align:center;">
            <h:form id="form">
                <div class="main_container">
                    <div class="login_area">
                        <div class="login_full_logo">&nbsp;</div>
                        <div class="login_notes_text">
                            <p id="p1">
                                <t:outputText value="#{loginResources.p1}"/>
                            </p>
                            <p id="p2">
                                <t:outputText value="#{loginResources.p2}"/>
                            </p>
                        </div>
                        <div class="login_box">
                            <div class="login_box_header">&nbsp;</div>
                            <div class="login_box_body">
                                <div class="login_label1">&nbsp;</div>
                                <table cellpadding="1" cellspacing="0" border="1" width="230" class="inner_login_table">
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginResources.userNameLabel}" id="userNameLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                             <t:inputText forceId="true" id="userNameText"
                                                         value="#{LoginMinistry.userName}"
                                                         title="#{LoginMinistry.userName}" maxlength="200"
                                                         autocomplete="off" styleClass="input_login1" disabled="true"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginExtResources.loginGroupLabel}"
                                                           id="groupNameLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:inputText forceId="true" id="groupNameText"
                                                         value="#{LoginMinistry.groupName}"
                                                         title="#{LoginMinistry.groupName}" styleClass="input_login1"
                                                         disabled="true"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2" class="login_label2">
                                            <h:outputLabel value="#{loginExtResources.loginMinistriesLabel}"
                                                           id="loginMinistriesLabel"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:selectOneMenu value="#{LoginMinistry.selectedMinistryCode}"
                                                             converter="javax.faces.Long" styleClass="input_login1"
                                                             disabled="#{LoginMinistry.hasSSOMinistry}"
                                                             onchange="if(document.getElementById('ministryList').value!=null){document.getElementById('loginButton').focus();}"
                                                             onkeydown="if(document.getElementById('ministryList').value!=null){FireButtonClickOldStyle(event,'loginButton');}"
                                                             onmouseover="showTitleTip(this);" id="ministryList" forceId="true"
                                                             onkeyup="onKeyDownFirstElement(event,'loginButton',null);">
                                                <t:selectItems itemLabel="#{ministry.name}" itemValue="#{ministry.code}"
                                                               var="ministry" id="loginMinistriesMenu"
                                                               value="#{LoginMinistry.groupMinistries}"></t:selectItems>
                                            </t:selectOneMenu>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="2">
                                            <t:outputText forceId="true" id="errorMessage"
                                                          value="#{LoginMinistry.errorMessage}" styleClass="error"/>
                                        </td>
                                    </tr>
                                     
                                    <tr>
                                        <td colspan="3" align="center" style="text-align:center">
                                            <t:commandButton value="" action="#{LoginMinistry.login}"
                                                             styleClass="login_login_btn" id="loginButton" forceId="true"
                                                             onchange="if(document.getElementById('ministryList').value!=null){document.getElementById('loginButton').focus();}"
                                                             onkeyup="onKeyDownFirstElement(event,'loginButton',null);"/>
                                             
                                            <h:commandButton value="" action="#{LoginMinistry.back}"
                                                             styleClass="login_back_btn" id="backButton"
                                                             rendered="#{!LoginMinistry.hasSSOMinistry}"/>
                                             
                                            <h:commandButton value="" action="#{LoginMinistry.logout}"
                                                             styleClass="login_logout_btn" id="logoutButton"/>
                                        </td>
                                    </tr>
                                </table>
                                <img src="${shared_util.contextPath}/app/media/images/login/login-icon-bg.png"
                                     width="100" height="118" align="left" border="0"
                                     style="margin-top:5px;margin-left:20px;"/>
                            </div>
                        </div>
                        <div class="login_copyrights">
                            <t:outputText value="#{loginResources.copyright}"/>
                        </div>
                    </div>
                </div>
            </h:form>
        </body>
    </html>
    <script type="text/javascript">
          foucsOnMinstryList();

          function foucsOnMinstryList() {
              if (document.getElementById('ministryList') != null) {
                  document.getElementById('ministryList').focus();
              }
          }
    </script>
    <t:saveState value="#{LoginMinistry.selectedMinistryCode}"/>
    <t:saveState value="#{LoginMinistry.groupMinistries}"/>
</f:view>