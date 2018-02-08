/*
File Content Description:this file about popup windows ,it includes functions to open popup for add or edit or select list of elements.
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/* 
Purpose: open a page as popup window(the old one ) 
@param url:the the name of jsp page will be opened as popup
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
        function openPopup(url) {
            changeVisibility();
            dialogWin.win = window.open("../jsps/popup/" + url, "popup", "top=0,left=0,width=500,height=350");
            dialogWin.win.focus();
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
/* 
Purpose: open a page as popup window ,it is used in case Add operation in look up case
@param saveMethod:the method will save new added element 
@param title:title of popup page 
@param formName: form name 
@param fieldName:the field appears in popup window during adding
Creation/Modification History :
   1.1 - Developer Name:Amir Nasr 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/  
         function openAddPopup(saveMethod, title, formName, fieldName) {
         
            changeVisibility();
            var winl = (screen.width-400)/2;
            var wint = (screen.height-175)/2;
            dialogWin.win = window.open("../popup/addedit_popup.jsf?save_method="+saveMethod 
                    + "&title="+title+"&form_name="+formName+"&field_name="+fieldName, title, "top="+wint+",left="+winl+",width=400,height=175");
            dialogWin.win.focus();
        }
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 /* 
Purpose: open a page as popup window ,it is used in case Edit operation in look up case
@param saveMethod:the method will save editable object 
@param loadMethod:the method will load editable object 
@param title:title of popup page 
@param formName: form name 
@param fieldName:the field appears in popup window during editting
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/         function openEditPopup(saveMethod, loadMethod, title, formName, fieldName) {
            changeVisibility();
            var winl = (screen.width-400)/2;
            var wint = (screen.height-175)/2;
            dialogWin.win = window.open("../popup/addedit_popup.jsf?save_method="+saveMethod 
                    +"&title="+title+"&form_name="+formName+"&load_method="+loadMethod+"&field_name="+fieldName, title, "top="+wint+",left="+winl+",width=400,height=175");
            dialogWin.win.focus();
        }
        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
/* 
Purpose: open a page as popup window,it will appear as confirmation message when user press delete button
@param url:the url of page that will appear as confirmation message
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
        function openDeleteConfirmPopup(url) {
            changeVisibility();
            var winl = (screen.width-400)/2;
            var wint = (screen.height-175)/2;
            dialogWin.win = window.open(url, "popup", "top="+wint+",left="+winl+",width=400,height=300");
            dialogWin.win.focus();
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
/* 
Purpose: open a page as popup window,it will appear after user select set of items to be deleted,this page will contains list of items that are deleted successfully
@param loadMethod:the method will load list of objects that are deleted successfully
@param deleteMethod : the method name that will be used in deletion operation
@param formName :the form name will contain this popup 
@param dtoCode : code of object that will be deleted
@param dtoName : Name of object that will be deleted
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
         function openDeleteAlertPopup(loadMethod, deleteMethod, formName, dtoCode, dtoName) {
            changeVisibility();
            var winl = (screen.width-400)/2;
            var wint = (screen.height-175)/2;
            dialogWin.win = window.open("../popup/delete_alert_popup.jsf?load_method="+loadMethod+"&delete_method="+deleteMethod+"&form_name="+formName
                                    + "&dto_code="+dtoCode+"&dto_name="+dtoName, "popup", "top="+wint+",left="+winl+",width=400,height=300");
            dialogWin.win.focus();
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
/* 
Purpose: set  visibilty  true to show poupup window ,used in openAddPopup,...
Creation/Modification History :
   1.1 - Developer Name: Ahmed Sabry and Amir Nasr and Ahamed Abdelfatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/  
        var i=0;
        function changeVisibility() {
       // alert(" i ="+i);
            window.top.blocker.style.visibility = "visible";
            i = 0;
            fade();
        } 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
 /* 
Purpose: Changing back ground color of parent window while popup appear
Creation/Modification History :
   1.1 - Developer Name:  Ahmed Sabry
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/         
        function fade() {
            i+=7;
            window.top.blocker.style.filter="alpha(opacity="+i+++")";
            if(i<60) setTimeout("fade()",5);
        }
        
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: Event handler to inhibit Navigator 4 form element and IE link activity when dialog window is active.
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/   
        function deadend( ) {
            if (dialogWin.win && !dialogWin.win.closed) {
                dialogWin.win.focus( );
                return false;
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////           
/* 
Purpose: Grab all Navigator events that might get through to form elements while dialog is open. For IE, disable form elements.
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/     
        function blockEvents( ) {
            if (Nav4) {
                window.captureEvents(Event.CLICK | Event.MOUSEDOWN | Event.MOUSEUP | Event.FOCUS);
                window.onclick = deadend;
            }
            window.onfocus = checkModal;
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////       
 /* 
Purpose:As dialog closes, restore the main window's original event mechanisms.
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/          
        function unblockEvents( ) {
            if (Nav4) {
                window.releaseEvents(Event.CLICK | Event.MOUSEDOWN | Event.MOUSEUP | Event.FOCUS);
                window.onclick = null;
                window.onfocus = null;
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
/* 
Purpose:Invoked by onfocus event handler of EVERY frame,return focus to dialog window if it's open.
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
        function checkModal( ) {
            setTimeout("finishChecking( )", 1);
            return true;
        }
           
        function finishChecking( ) {
            if(dialogWin.win && !dialogWin.win.closed) {
                dialogWin.win.focus( );
            } else if (body.dialogWin && body.dialogWin.win && !body.dialogWin.win.closed) {
                body.dialogWin.win.focus( );
            }
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:Get the value of the selected item (checkbox or radio) and save it's Id in a hidden field to be passed to the parent page laters
@param selected:value of the selected item (checkbox or radio)
@param type:checkBox,radioBox.
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
        function setSelected(selected, type) {
            var checkIndex;
            if(type == 'check') {
                checkIndex = selected.id;
            } else {
                checkIndex = selected.name;
            }
            
            var end;
            if(checkIndex.indexOf("popup_check") != -1) {
                end = checkIndex.indexOf("popup_check");
            } else {
                end = checkIndex.indexOf("popup_radio");
            }
            checkIndex = checkIndex.substring(22,end-1);
            
            var choosen = window.document.forms[0].choosen.value
            
            var id = document.getElementById("popup_form:dataT_data:"+checkIndex+":hiddenLongCode").value;
            if(id == "") {
                id = document.getElementById("popup_form:dataT_data:"+checkIndex+":hiddenStringCode").value;
            }
            
            if(selected.checked) {
                if(type == 'check') {
                    choosen += id + ","; 
                } else {
                    choosen = "," + id;
                }
                window.document.forms[0].choosen.value = choosen;
            } else {
                var index = choosen.indexOf(","+id+",");
                choosen = choosen.substring(0, index) + choosen.substring(index + 1 + id.length , choosen.length);
                window.document.forms[0].choosen.value = choosen;
            }
        }
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:Pass the Ids from the popup page to the parent page
@param form:the form name 
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/        
        function choooseR(form) {
            var appLocalePopup = document.getElementById("ourLocale").value;
            var choosen = window.document.forms[0].choosen.value;
            var counter = 0;
            var elems = form.elements;
            if(choosen == ",") {
                for (var i=0; i<elems.length; i++) {
                    if(elems[i].type == "checkbox") {
                        if(elems[i].checked) {
                            counter++;
                            break;
                        }
                    }
                }
                if (counter == 0) {
                    if(appLocalePopup == "en")
                        alert("please, select one item");
                    else if(appLocalePopup == "ar")
                       alertuni("&#1605;&#1606; &#1601;&#1590;&#1604;&#1603;&#1548; &#1575;&#1582;&#1578;&#1585; &#1571;&#1581;&#1583; &#1575;&#1604;&#1593;&#1606;&#1575;&#1589;&#1585;");
                    return false;
                }
            }
            return true;
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:close popup window when user press cancel
@param form:the form name 
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/   
    function HandleClose() {
                    window.opener.top.blocker.style.visibility="hidden";
                    window.close();
                }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////             
/*
Purpose:open window used in popup function
@param theURL:jsp page path  
@param winName :window name and may take no value
@param features:width and height of opened window
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*/
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
Purpose:submit parent window and close popup
@param theURL:jsp page path  
@param winName :window name and may take no value
@param features:width and height of opened window
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*/
      function reload()
    {
        window.opener.document.forms[1].submit();
        window.close();
    }