<%@ page import="com.beshara.jsfbase.csc.util.wizardbar.WizardBar"%>
<%@ page import="javax.faces.context.FacesContext"%>
<%@ page import="com.beshara.jsfbase.csc.util.SharedUtilBean" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>


 <f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global"   var="globalResources"/>

/* 
Purpose: Get Current page Name
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function getCurrentPageName(){
        var fullPath=location.href;
        var pageName=fullPath.substring(fullPath.lastIndexOf('\/')+1,fullPath.lastIndexOf('.')+4)
        return pageName;
}

/* 
Purpose: store index of selected button in wizared bar and check if page name as current it will not change index of selected buttton
and if not the same it will rest index to 0
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function createCookie(name, value, days) 
{ 

        name=name+getCurrentPageName();
          if (days) { 
            var date = new Date(); 
            date.setTime(date.getTime()+(days*24*60*60*1000)); 
           // var expires = "; expires="+date.toGMTString(); 
             var expires = ";"; //test if no date will expire on window close 
            } 
          else var expires = ""; 
          document.cookie = name+"="+value+expires+"; path=/"; 
} 

/* 
Purpose:read cookie data 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/

function readCookie(name) 
{ 
        name=name+getCurrentPageName();
          var ca = document.cookie.split(';'); 
          var nameEQ = name + "="; 
          for(var i=0; i < ca.length; i++) { 
            var c = ca[i]; 
            while (c.charAt(0)==' ') c = c.substring(1, c.length); //delete spaces 
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length); 
            } 
          return null; 
} 

/* 
Purpose:destory /clear data in cookie
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/

function eraseCookie(name) 
{ 
        name=name+getCurrentPageName();
        createCookie(name, "", -1); 
} 


/* 
Purpose:form wizard bar as component
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
/***********************************************
* Scrollable Menu Links- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* Visit http://www.dynamicDrive.com for hundreds of DHTML scripts
* This notice must stay intact for legal use
***********************************************/
var shiftWidth;

//configure path for left and right arrows
var goleftimage="<h:outputText value='#{shared_util.contextPath}/images/#{globalResources.photoFolder}/arrow-next.gif'/> <f:verbatim>"; </f:verbatim>
<f:verbatim>var gorightimage="</f:verbatim><h:outputText value='#{shared_util.contextPath}/images/#{globalResources.photoFolder}/arrow-previous.gif'/><f:verbatim>";
//configure menu width (in px):
var menuwidth=600
//configure menu height (in px):
var menuheight=30
//Specify scroll buttons directions ("normal" or "reverse"):
var scrolldir="normal"
//configure scroll speed (1-10), where larger is faster
var scrollspeed=6

//specify menu content
var menucontents=document.getElementById('wizardbar').innerHTML;
document.getElementById('wizardbar').innerHTML='';

var appLocale='${shared_util.locale}';

if(appLocale=='en'){
scrolldir="reverse";

}


////NO NEED TO EDIT BELOW THIS LINE////////////

var iedom=document.all||document.getElementById
var leftdircode='onMouseover="moveleft()" onMouseout="clearTimeout(lefttime)"'
var rightdircode='onMouseover="moveright()" onMouseout="clearTimeout(righttime)"'
if (scrolldir=="reverse"){
var tempswap=leftdircode
leftdircode=rightdircode
rightdircode=tempswap
}
if (iedom)
document.write('<span id="temp" style="visibility:hidden;position:absolute;top:-100;">'+menucontents+'</span>')
var actualwidth=''
var cross_scroll, ns_scroll
var loadedyes=0


function fillup(){
if (iedom){
cross_scroll=document.getElementById? document.getElementById("test2") : document.all.test2
cross_scroll.innerHTML=menucontents
actualwidth=document.all? cross_scroll.offsetWidth : document.getElementById("temp").offsetWidth
}
else if (document.layers){
ns_scroll=document.ns_scrollmenu.document.ns_scrollmenu2
ns_scroll.document.write(menucontents)
ns_scroll.document.close()
actualwidth=ns_scroll.document.width
}
loadedyes=1
}

window.onload=fillup

function moveleft(){
if (loadedyes){
if (iedom&&parseInt(cross_scroll.style.left)>(menuwidth-actualwidth)){
cross_scroll.style.left=parseInt(cross_scroll.style.left)-scrollspeed+"px"
createCookie('left',cross_scroll.style.left ,10);
//alert(readCookie('left'));
}
else if (document.layers&&ns_scroll.left>(menuwidth-actualwidth))
{
ns_scroll.left-=scrollspeed
createCookie('left',ns_scroll.left ,10);
//alert(readCookie('left'));
}
}
lefttime=setTimeout("moveleft()",50)
}

function moveright(){
if (loadedyes){
if (iedom&&parseInt(cross_scroll.style.left)<0){
cross_scroll.style.left=parseInt(cross_scroll.style.left)+scrollspeed+"px"
createCookie('left',cross_scroll.style.left ,10);
}
else if (document.layers&&ns_scroll.left<0)
{
ns_scroll.left+=scrollspeed
createCookie('left',ns_scroll.left ,10);
}
}
righttime=setTimeout("moveright()",50)
}



if (iedom||document.layers){
with (document){
write('<table border="0" cellspacing="0" cellpadding="2" dir="${shared_util.pageDirection}">')
write('<td valign="middle"><a href="#" '+leftdircode+'><img src="'+goleftimage+'"border=0></a> </td>')
write('<td width="'+menuwidth+'px" valign="top">')
if (iedom){
var left=readCookie("left");
//alert(left);
if(left){}else{left=0;}

write('<div style="position:relative;width:'+menuwidth+'px;height:'+menuheight+'px;overflow:hidden;">')
write('<div id="test2" style="position:absolute;left:'+left+';top:0">')
write('</div></div>')
}
else if (document.layers){
write('<ilayer width='+menuwidth+' height='+menuheight+' name="ns_scrollmenu">')
write('<layer name="ns_scrollmenu2" left=0 top=0></layer></ilayer>')
}
write('</td>')
write('<td valign="middle"> <a href="#" '+rightdircode+'>')
write('<img src="'+gorightimage+'"border=0></a>')
write('</td></table>')
}
}
//if(appLocale=='ar'){
//var cross_scroll=document.getElementById? document.getElementById("test2") : document.all.test2
//cross_scroll.style.left=menuwidth-actualwidth;
//alert(menuwidth-actualwidth);
//}

</f:verbatim>
</f:view>