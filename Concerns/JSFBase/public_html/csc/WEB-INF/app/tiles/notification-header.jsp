 <%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
 
 <t:panelGroup forceId="true" id="selectedNotificationId_pnl">
   <t:inputHidden forceId="true" id="selectedNotificationId" value="#{notificationBean.selectedNotificationId}"  />
   
</t:panelGroup>
 <t:inputHidden forceId="true" id="activeUserNotificationsCount" value="#{notificationBean.activeUserNotificationsCount}"  />  
 
 <t:inputHidden forceId="true" id="listExpiredSize" value="#{notificationBean.listExpiredSize}"/>  
 <t:inputHidden forceId="true" id="oldListExpiredSize" value=""  />  
 <t:inputHidden forceId="true" id="blockerListSize" value="#{notificationBean.blockerListSize}"  />  
 <t:inputHidden forceId="true" id="currentBlockerListIndex" value="#{notificationBean.currentBlockerListIndex}"  />  
 <t:panelGroup forceId="true" id="listDivContent_pnl">

 </t:panelGroup>
<%--a4j:jsFunction name="updateNotificationsPnl" reRender="activeUserNotificationsCount,blockerListSize,currentBlockerListIndex,pnl_notifications,notifications_list_dtl" ></a4j:jsFunction--%>
<a4j:jsFunction id="updateNotificationsPnl"  name="updateNotificationsPnl" reRender="activeUserNotificationsCount,blockerListSize,currentBlockerListIndex,pnl_notifications"  oncomplete="refreshList();" ajaxSingle="true"></a4j:jsFunction>
<a4j:jsFunction id="updateNotificationsListJS" name="updateNotificationsListJS" reRender="listDivContent_pnl" ajaxSingle="true" ></a4j:jsFunction>
<a4j:jsFunction id="updateBlockerListIndex" name="updateBlockerListIndex" reRender="blockerListSize,currentBlockerListIndex" ajaxSingle="true"></a4j:jsFunction>
<a4j:jsFunction id="changeSelectedNotificationJS" name="changeSelectedNotificationJS"reRender="selectedNotificationId_pnl,notificationDetails" oncomplete="showContentDiv();hideListDiv();" ajaxSingle="true" />
<a4j:jsFunction id="refreshExpiredListSizeJS" name="refreshExpiredListSizeJS" reRender="listExpiredSize" oncomplete="checkListExpiredSize();" ajaxSingle="true"></a4j:jsFunction>
<a4j:jsFunction id="changeNotificationAlertJS" name="changeNotificationAlertJS" reRender="notifications_alert" oncomplete="restoreNotificationMinimizeStatus();" ajaxSingle="true"></a4j:jsFunction>


