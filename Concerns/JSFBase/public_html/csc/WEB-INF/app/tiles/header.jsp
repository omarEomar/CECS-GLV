<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<h:outputText value="#{ModuleMenuExt.insureCreated}"/>
<h:form id="header_frm_form1" onmousedown="hideNavigationMenu();">
    
    <htm:table border="0" width="100%" id="table2" cellspacing="0" cellpadding="0" dir="ltr" styleClass="HeaderTbl">
        <htm:tr>
            <htm:td>            
                <!-- Sample Section for localization switching -->
                <%--<h:panelGrid id="header_panelGrid" columns="2">
                    <h:commandButton id="header_commandLnk_arabic" value="#{globalResources.language_link}"
                            action="#{shared_util.switchToArabic}" rendered="#{shared_util.locale == \"en\"}"/>
                    <h:commandButton id="header_commandLnk_english" value="#{globalResources.language_link}"
                            action="#{shared_util.switchToEnglish}" rendered="#{shared_util.locale == \"ar\"}"/>
                </h:panelGrid>--%>
                <!-- End of sample -->
                
                <!-- Hidden field to carry the value of current locale -->
                <h:inputHidden id="header_hidden_locale" value="#{shared_util.locale}"/>
            </htm:td>
        </htm:tr>
        <htm:tr>
            <htm:td valign="top">
                <htm:div styleClass="header">
                    <htm:div styleClass="Logo"></htm:div>
                    <htm:div styleClass="UserInfo">
                        <htm:div styleClass="userNamePannel" onmouseover="showGroupName();" onmouseout="hideGroupName();">
                            <t:outputLabel value="" styleClass="UserNameInfo"/>
                            <t:outputText value="#{Login.citizenName}" id="citizenName" styleClass="UserHeaderText" forceId="true"/>
                            <t:outputText id = "grp" value="#{Login.ministryShortName}" styleClass="GroupHeaderText" forceId="true" />
                        </htm:div>
                        
                        <htm:div styleClass="UserNameParent">
                        <%--<t:outputLabel forceId="true" id="notification_lbl" value="" styleClass="UserRole" onclick="showHideNotificationsList();" style="cursor:pointer;"/>--%>                         
                        <t:panelGroup forceId="true" id="pnl_notifications">                        
                            <t:outputLabel forceId="true" id="notification_lbl"  for="notifications_cnt" value="" styleClass="UserNotification" onclick="showHideNotificationsList();" style="cursor:pointer;" rendered="#{notificationBean.activeUserNotificationsCount>0}" /> 
                            <t:outputLabel forceId="true" id="notification_lbl2" for="notifications_cnt" value="" styleClass="UserNotificationNon" style="cursor:pointer;" rendered="#{notificationBean.activeUserNotificationsCount==0}" /> 
                            <%--<t:outputText value="#{notificationBean.notificationsCount}" styleClass="no_notification"/>--%>
                            <%--t:outputText forceId="true"  id="notifications_cnt" styleClass="no_notification"  value = "#{notificationBean.notificationsCount}"  /--%>
                            <t:outputText forceId="true"  id="notifications_cnt" styleClass="no_notification"  value = "#{notificationBean.activeUserNotificationsCount}"  />
                             <t:outputText  style="display:none;" forceId="true" id="listDivContent" value="#{notificationBean.listDivContent}"  />
                        </t:panelGroup>
                    </htm:div>
                        
                        <htm:div styleClass="LoginDate">
                            <t:outputLabel value="" styleClass="LoginDate_Icon"/>
                            <t:outputText value="#{Login.loginTimeFormatted}" styleClass="UserLoginDate" />
                        </htm:div>
                            <%--t:commandLink forceId="true" id="LogoutButton" action="#{Login.logout}"  title="#{globalResources.exit}" ></t:commandLink--%>
                            <t:commandLink forceId="true" id="LogoutButton" title="#{globalResources.exit}" action="#{Login.logout}"></t:commandLink>
                    </htm:div>
                    <htm:div styleClass="EnglishBt">
                        <t:commandLink forceId="true" id="SwapLanguageButton" onclick="return false;" ></t:commandLink>
                    </htm:div>                    
                    <htm:div styleClass="TopMenu">
                         <%--t:commandLink forceId="true" id="HomePageButton" action="#{Login.goToApplicationHomePageRedirectUrl}" title="#{globalResources.HomePage_header_title}"></t:commandLink--%>
                        <t:commandLink forceId="true" id="HomePageButton" title="#{globalResources.HomePage_header_title}" action="#{shared_util.goToMenuPage}"></t:commandLink>
                        <%--t:commandLink forceId="true" title="#{globalResources.Email_header_title}" id="EmailButton" onclick="openPopupWindowFullScreen('#{ModuleMenuExt.emlUrl}'); return false;" ></t:commandLink--%>
                        <t:commandLink forceId="true" title="#{globalResources.Email_header_title}" id="EmailButton"  action="emailPage" target="body"></t:commandLink>
                        
                       <t:commandLink forceId="true" title="#{globalResources.knowshortcut}"  id="HelpButton" action="emailPage" target="body" ></t:commandLink>
                        <t:commandLink forceId="true" title="#{globalResources.UserGuide_title}" id="UserGuideButton" styleClass="UserGuideButton" onclick="openPopupWindowFullScreen('#{shared_util.contextPath}/userGuide.jsf'); return false;" rendered="#{ModuleMenuExt.hasUserGuide}"></t:commandLink>
                        <t:commandLink forceId="true"  title="#{globalResources.UserGuide_title}" id="NoUserGuideButton" styleClass="UserGuideButton" action="emailPage" target="body" rendered="#{!ModuleMenuExt.hasUserGuide}"></t:commandLink>
                        
                        <%--t:commandLink forceId="true" title="#{globalResources.UserGuide_title}" id="HelpButton" onclick="openPopupWindowFullScreen('#{shared_util.contextPath}/userGuide.jsf'); return false;" ></t:commandLink--%>
                        <%--<t:commandLink forceId="true" title="#{globalResources.UserGuide_title}" id="HelpButton" action="emailPage" target="body"></t:commandLink>--%>
                        <%--t:commandLink forceId="true" title="#{globalResources.knowshortcut}" id="UserGuideButton" onclick="window.open('#{shared_util.contextPath}/app/jsps/shared/shortcutspage.jsf', '', 'width=353px,height=380px,left=500px,top=200px'); return false;"></t:commandLink--%>
                        <%--t:commandLink forceId="true" title="#{globalResources.knowshortcut}" id="UserGuideButton" action="emailPage" target="body"></t:commandLink--%>
                        <a4j:commandButton value="" id="IssuesButton" styleClass="IssuesButton" type="button" onclick="openPopupWindow('#{shared_util.contextPath}/app/jsps/appissues/addappissuepage.jsf', 950,600, (screen.availWidth - 950)/2 , (screen.availHeight - 500)/2); return false;" reRender="appissuesaddPanel_bs"/>
                    </htm:div>
                </htm:div>
            </htm:td>
        </htm:tr>
    </htm:table>
    <jsp:include page="/WEB-INF/app/tiles/notification-header.jsp"/>    
    <%-- uncomment the line below if u want to activate the report menu scanner solution --%>
    <%-- Story-id : CSC-12634 --%>
    <%-- commited by : Hany Omar --%>
    
    <%--<jsp:include page="/module/jsps/reports/reportMenuScanner.jsp"/>--%>
    
</h:form>
