<%@ page session="false" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.notifications.presentation.resources.notifications" var="notificationResources"/>

<t:panelGroup forceId="true" id="notifications_list" styleClass="notification_top_area">
   <t:panelGroup forceId="true" id="notifications_list_dtl" >
   
   </t:panelGroup>
   
</t:panelGroup>


   <t:panelGroup forceId="true" id="notification_cntent" styleClass="notification_cntent" >
                <htm:div styleClass="divSheardStyle1" id="divNot" style="visibility: visible;" >
                    <htm:div styleClass="popup_header">
                    <f:verbatim>
                        <input type="button" class="close" onclick="document.getElementById('not_cancel').click();;
                if(typeof window.clearFormHiddenParams_myForm!='undefined'){clearFormHiddenParams_myForm('myForm');}"
                               name="divNotcloseXX" id="divNotcloseXX"></input>
                         </f:verbatim>
                        <htm:span styleClass="popup_title">
                            <t:outputText id="cnt_tityle" value="#{notificationResources.notificationsDetails}"></t:outputText>
                        </htm:span>
                    </htm:div>
                    <htm:div styleClass="popup_body">
                        <htm:div styleClass="popup_body_contents">
                            <htm:table align="center" id="notDiv_btnsPnlGrd" width="100%">
                                <htm:tbody>
                                    <htm:tr>
                                        <htm:td>
                                        
                            <htm:table align="center" id="notificationDetails" width="100%">
                                    <htm:tr>
                                        <htm:td align="center">
                                        <t:graphicImage value="app/media/images/notifications/icon-critical.png"
                                            rendered="#{notificationBean.selectedNotificationForAlert.type==1}" border="0" width="55" height="50"/>
                                        <t:graphicImage value="app/media/images/notifications/i-icon.jpg"
                                            rendered="#{notificationBean.selectedNotificationForAlert.type==2}" border="0" width="55" height="50"/>
                                        </htm:td>
                                        <htm:td>
                                            <t:panelGrid columns="1" rowClasses="" style="width: 100%; font-family: Tahoma; font-size: 8pt; color:#175195;" cellpadding="3" cellspacing="0">
                                                <t:outputText id="createedBy" value="#{notificationBean.selectedNotificationForAlert.createdByUserName}"></t:outputText>
                                                <t:outputText id="breifMessage" value="#{notificationBean.selectedNotificationForAlert.briefMsg}"></t:outputText>
                                                <t:outputText id="dtlMessage" value="#{notificationBean.selectedNotificationForAlert.detailedMsg}"></t:outputText>
                                                <t:panelGroup>
                                                <t:outputText id="space1" value=" "></t:outputText>
                                                <t:outputText id="startFrom" value="#{notificationResources.dateFrom}"></t:outputText>
                                                <t:outputText id="space2" value=": "></t:outputText>
                                                <t:outputText id="startTime" value="#{notificationBean.selectedNotificationForAlert.startTime}"></t:outputText>
                                                <t:outputText id="space3" value="    "></t:outputText>
                                                <t:outputText id="startTo" value="#{notificationResources.dateTo}"></t:outputText>
                                                <t:outputText id="space4" value=": "></t:outputText>
                                                <t:outputText id="endTime" value="#{notificationBean.selectedNotificationForAlert.endTime}"></t:outputText>
                                                </t:panelGroup>
                                            </t:panelGrid>
                                        </htm:td>
                            
                                    </htm:tr>
                            </htm:table>
                            
                                        
                                        </htm:td>
                                    </htm:tr>
                                     <htm:tr>
                                        <htm:td align="center">
                                            <f:verbatim>
                                                <input type="button" class="cssButtonSmall"  onclick="hideDiv('notification_cntent')" value="رجوع" name="not_cancel" id="not_cancel"></input>
                                                   </f:verbatim>
                                        
                                        
                                        </htm:td>
                                        </htm:tr>
                                </htm:tbody>
                            </htm:table>
                        </htm:div>
                    </htm:div>
                </htm:div>
            </t:panelGroup>
<t:panelGroup id="notifications_alert_container" forceId="true">         
<t:panelGroup id="notifications_alert" forceId="true">         

  <htm:div id="notifications_alert_1" styleClass="notification_top_urgent_area" rendered="#{notificationBean.blockerListSize>0}" >
  <htm:div  id="notifications_alert_head"  >
  <t:graphicImage id="notifications_alert_img" forceId="t   rue" value="app/media/images/notifications/notification-min_with_title.png"
             border="0" width="99" height="23" styleClass="notifications_alert_head_img" onclick="toggleNotificationAlert();"/>
  </htm:div>
  
  <htm:div id="notifications_alert_content">
    <htm:table width="100%" border="0" cellspacing="0" cellpadding="0" rendered="#{notificationBean.selectedBlockerNotificationForAlert!=null}"  onclick="changeSelectedNotification(#{notificationBean.selectedBlockerNotificationForAlert.listIndex});">
        <htm:tr styleClass="notification_top_urgent_name-area">
           <htm:td style="padding-right:30px;width:70px;" align="right">
                    <t:outputText id="blockerIndex" value="#{notificationBean.currentBlockerListIndex + 1}"></t:outputText>
                     <t:outputText id="space5" value=" "></t:outputText>
                    <t:outputText id="sp" value="#{notificationResources.dateFrom}"/>
                     <t:outputText id="space6" value=" "></t:outputText>
                    <t:outputText id="blockerTotal" value="#{notificationBean.blockerListSize}"></t:outputText>
                     <%--<t:outputText id="space7" value=" "></t:outputText>--%>
                    <%--<t:outputText id="notificationImportant" value=" تنبية هام:" styleClass="notificationImportant"></t:outputText>--%>
                </htm:td>
              <htm:td colspan="2" styleClass="notification_text_urgent" align="right">
                <t:outputText id="selected_text1" value="#{notificationBean.selectedBlockerNotificationForAlert.briefMsg}"></t:outputText>
            </htm:td>
            <htm:td align="left" style="padding-left:15px;" rendered="#{!notificationBean.selectedBlockerNotificationForAlert.countDown}">
                <t:outputText id="to" value="نهاية الوقت"  />
                <t:outputText id="space10" value=": "   ></t:outputText>
                <t:outputText id="toTime" value="#{notificationBean.selectedBlockerNotificationForAlert.endTime}" />
            </htm:td>
            
            
            <htm:td align="left" rendered="#{notificationBean.selectedBlockerNotificationForAlert.countDown}">
                <t:outputText id="remain" value="الوقت المتبقى"  />
            </htm:td>
            
            
               <htm:td styleClass="countdown_urgent" rendered="#{notificationBean.selectedBlockerNotificationForAlert.countDown}" >
            <htm:span styleClass="countdown_no">
                <t:outputText id="countDown" value="#{notificationBean.selectedBlockerNotificationForAlert.remainTime}" style="color:#fff;" />
                </htm:span>
            </htm:td>
        </htm:tr>
    </htm:table>
    </htm:div>
</htm:div>
</t:panelGroup>

</t:panelGroup>



 
            
            
<t:saveState value="#{notificationBean.selectedNotificationId}"/>

<t:saveState value="#{notificationBean.currentBlockerListIndex}"/>
