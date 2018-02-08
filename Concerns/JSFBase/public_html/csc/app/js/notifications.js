
var footerObj = document.getElementById("footer_div");
var screen_height =   GetTopLeft(footerObj).Top;
startNotification();
function startNotification() {
    var firstCount = document.getElementById("activeUserNotificationsCount").value;
    if (firstCount > 0) {
        document.getElementById("notifications_cnt").innerHTML = firstCount;
        document.getElementById("notifications_alert").style.display = 'block';
        r = setTimeout(changeNotificationAlert, 30000);
    }
    else {
        document.getElementById("notifications_alert").style.display = 'none';
    }
}

var blockerListSize = document.getElementById("blockerListSize").value;
if (blockerListSize > 0) {
    blockerListSize--;
}
var index = 0;
var r;
changeNotificationAlert();

function changeNotificationAlert() {
try{
    //alert(index);
    if(document.getElementById("blockerListSize")!= null){
        blockerListSize = document.getElementById("blockerListSize").value;
    if (blockerListSize > 0) {
        blockerListSize--;

        document.getElementById("currentBlockerListIndex").value = index;
        if (index < blockerListSize) {
            index++;
        }
        else {
            index = 0;
        }

        changeNotificationAlertJS();

    }
    }
    }catch(Exception){
        clearTimeout(c);
    clearTimeout(t);
    clearTimeout(r);
    }
    
    
}
var c;
refreshExpiredListSize();

function refreshExpiredListSize() {
try{
    if(document.getElementById("listExpiredSize") != null){
        refreshExpiredListSizeJS();
    }
     c = setTimeout(refreshExpiredListSize, 20000);
}catch(Exception){
    clearTimeout(c);
    clearTimeout(t);
    clearTimeout(r);
}
}

function checkListExpiredSize() {
if(document.getElementById("listExpiredSize")!= null){
    var listExpiredSize = document.getElementById("listExpiredSize").value;
    if (listExpiredSize > 0) {
    if(document.getElementById("activeUserNotificationsCount")!= null){
        updateNotificationsPnl();
    }
        
        index = 0;
        if(document.getElementById("blockerListSize")!= null){
            updateBlockerListIndex();    
     
        blockerListSize = document.getElementById("blockerListSize").value;
        if (blockerListSize > 0) {
            blockerListSize--;
        }
    }
        startNotification();
    }
}
    
}

UpdateNotifications();
function UpdateNotifications() {
try{
if(document.getElementById("blockerListSize")!= null){
        var val = document.getElementById("blockerListSize").value;

    if(document.getElementById("activeUserNotificationsCount")!= null){
        updateNotificationsPnl();
    }
    if (document.getElementById("notifications_alert") != null) {

        if (val > 0) {
            changeNotificationAlert();
          //  alert("dispaly")
            document.getElementById("notifications_alert").style.display = 'block';
             document.getElementById("notifications_alert").style.display = 'block';

        }
        else {
         //alert("hide")
            document.getElementById("notifications_alert").style.display = 'none';
            //  document.getElementById("notifications_cnt").innerHTML="";
            hideContentDiv();
            hideListDiv();
        }
       
    }
 
    t = setTimeout(UpdateNotifications, 25000);
}
    // t = setTimeout(UpdateNotifications, 900000);    
}catch(Exception){
   clearTimeout(c);
    clearTimeout(t);
    clearTimeout(r);
}

}

function showHideNotificationsList() {
    if(document.getElementById("notifications_list")!= null){
        var notifications_list_shown = (document.getElementById("notifications_list").style.display == 'block') ? true : false;
    if (notifications_list_shown) {
        document.getElementById("notifications_list").style.display = 'none';
    }
    else {
        if (document.getElementById("notifications_list").style.left == 0 || document.getElementById("notifications_list").style.left == '0px') {
            resetListPosition();
        }
        updateNotificationsListJS();
        refreshList();
        document.getElementById("notifications_list").style.display = 'block';
    }
    }
    
}

function resetListPosition() {
    var obj = document.getElementById("notification_lbl");
    if(obj!= null){
        var pos = GetTopLeft(obj);
    
        document.getElementById("notifications_list").style.top = pos.Top + 12;
        document.getElementById("notifications_list").style.left = pos.Left - 350;
    }
}

function resetblockerPosition() {

    

    var obj = document.getElementById("notifications_alert_container");
    
    if(obj!= null)
        obj.style.top=screen_height-50;
 
    
}
//resetblockerPosition();

function GetTopLeft(elm) {

    var x, y = 0;
    x = elm.offsetLeft;
    y = elm.offsetTop;
    elm = elm.offsetParent;
    while (elm != null) {
        x = parseInt(x) + parseInt(elm.offsetLeft);
        y = parseInt(y) + parseInt(elm.offsetTop);
        elm = elm.offsetParent;
    }
    return {Top : y, Left : x};
}

function hideDiv(divId) {
    document.getElementById(divId).style.display = 'none';
}

function changeSelectedNotification(val) {
    //alert(document.getElementById("selectedNotificationId").value);
    if(document.getElementById("selectedNotificationId")!= null){
        document.getElementById("selectedNotificationId").value = val;
        changeSelectedNotificationJS();    
    }
    

}

function showContentDiv() {
    document.getElementById("notification_cntent").style.display = 'block';
}

function hideContentDiv() {
    document.getElementById("notification_cntent").style.display = 'none';
}

function hideListDiv() {
    document.getElementById("notifications_list").style.display = 'none';
}

/********************************************** HighLight table  ***************************************************/
var lastHighlightedTableRowCSS = null;
var tableHighlightedRowCSS = "standardTable_RowHighlighted";
// highlight row(onmouseover)
function highlightTableRow(row) {
    lastHighlightedTableRowCSS = row.className;
    row.className = tableHighlightedRowCSS;

}
// unhighlight row (onmouseout)
function unhighlightTableRow(row) {
    row.className = lastHighlightedTableRowCSS;

}
var iconInf = '<img width="25" height="27" border="0" src="app/media/images/notifications/icn-inf.png">';
var iconErg = '<img width="25" height="27" border="0" src="app/media/images/notifications/icn-urgent.png">';

refreshList();

function refreshList() {
try{
    if(document.getElementById("listDivContent")!= null){
        var list = document.getElementById("listDivContent").innerHTML;
    if (list != null && list != '') {
        str = '<table width=\"100%\" align=\"center\" class=\"notification_table\" id=\"notifications_list_tbl\">';
        str += '<tr><td>';
        var obj = JSON.parse(list);
        for (var j = 0;j < obj.length;j++) {
            str += '<div class=\"notification_area_text_icon_new\">';
            str += '<table width=\"300\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" dir=\"rtl\"><tbody><tr>';
            str += '<tr  onclick=\"changeSelectedNotification(' + obj[j].listIndex + ')\" onmouseover=\"highlightTableRow(this)\" onmouseout=\"unhighlightTableRow(this)\">';
            str += '<td class="icon_not" width="3%;" align=\"center\" style="border-bottom:1px solid #666666;padding-right:15px;">';
            str += (obj[j].type == 1) ? iconErg : iconInf;
            str += '</td>';
            str += '<td class="from_not" style="border-bottom:1px solid #666666;">&nbsp;';
            str += obj[j].createdByUserName;

            str += '</td>';
            str += '<td class="details_not" style="border-bottom:1px solid #666666;">';
            str += obj[j].briefMsg;
            str += '</td>';
            str += '</tr>';
            str += '</table></div>';
        }
        str += '</td></tr>';
        str += '</table>';
        document.getElementById("notifications_list_dtl").innerHTML = str;
    }        
    }
    
}
    catch(Exception ){
        
    }
    
}
    var notificationMinimized= false;
    
function toggleNotificationAlert(){

        if(notificationMinimized){
            maximizeNotification();
        }else{
            minimizeNotification();
        }
    }
 
 function fadeOut(div){
    var duration =5;
    var maxTop = screen_height - 12;
    // how many times should it should be changed in delay duration
    var AmountOfActions=100;
    var count=0;
    var ratio = 0;
    //var currentTop = screen_height - 212;
    var currentTop = screen_height-50;
  
    setInterval(function(){
        count ++;
       if ( count<AmountOfActions) { 
        ratio = AmountOfActions - count;
        div.style.opacity = ratio / 100;
        div.style.filter = 'alpha(opacity='+ ratio / 100 + ')';
        currentTop++;
        if(currentTop<maxTop){
            document.getElementById("notifications_alert_container").style.top = currentTop;
        }
        }else{
        //div.style.visibility="hidden";
       // div.style.display='none';
   //    document.getElementById("notifications_alert_container").style.top=screen_height - 0;
        }
    },duration);
}

 function fadeIn(div){
    var duration = 5;
    // how many times should it should be changed in delay duration
    var AmountOfActions=100;
    
    var count=100;
    //var minTop = screen_height - 213;
    var minTop = screen_height-50;
    //var currentTop = screen_height - 12;
    var currentTop = screen_height-12;
    var ratio = 0;
    setInterval(function(){
        count --;
       if ( count>0) { 
        ratio = AmountOfActions - count;
        div.style.opacity = ratio / 100;
        div.style.filter = 'alpha(opacity='+ratio / 100 + ')';
        currentTop--;
        if(currentTop>minTop){
            document.getElementById("notifications_alert_container").style.top = currentTop;
        }
        }else{
    //    div.style.visibility="visible";
  //  div.style.display='block';
       //document.getElementById("notifications_alert_container").style.top=screen_height - 213;
        }
    },duration);
}



 
 function minimizeNotification(){
 
 //document.getElementById("notifications_alert_content").style.display='none';
 var div = document.getElementById("notifications_alert_content");
 if(div != null){
    setTimeout(function(){fadeOut(div);},100);
    document.getElementById("notifications_alert_img").src= "app/media/images/notifications/notification-max_with_title.png";
     notificationMinimized = true;
 }
 }
 
 function rapidMinimizeNotification(){
    var div = document.getElementById("notifications_alert_content");
    if(div!= null){
        var ratio = 0;
        div.style.opacity = ratio / 100;
        div.style.filter = 'alpha(opacity='+ ratio / 100 + ')';
        document.getElementById("notifications_alert_img").src= "app/media/images/notifications/notification-max_with_title.png";
        document.getElementById("notifications_alert_container").style.top = screen_height - 12;
    }
 }

 function rapidMaxmizeNotification(){
    var div = document.getElementById("notifications_alert_content");
    if(div!= null){
        var ratio = 100;
        div.style.opacity = ratio / 100;
        div.style.filter = 'alpha(opacity='+ ratio / 100 + ')';
        document.getElementById("notifications_alert_img").src= "app/media/images/notifications/notification-min_with_title.png";
        document.getElementById("notifications_alert_container").style.top = screen_height - 50;
    }
 }
 
function  maximizeNotification(){
 
 var div = document.getElementById("notifications_alert_content");
 if(div != null){
    setTimeout(function(){fadeIn(div);},100);
    document.getElementById("notifications_alert_img").src= "app/media/images/notifications/notification-min_with_title.png";
    notificationMinimized = false;
 }
}

function restoreNotificationMinimizeStatus(){
 var obj = document.getElementById("notifications_alert_container");
    if(obj!= null){
      if(notificationMinimized){
        rapidMinimizeNotification();
      }else{
         rapidMaxmizeNotification(); 
      }
    }else{
        notificationMinimized = false;
    }
}

