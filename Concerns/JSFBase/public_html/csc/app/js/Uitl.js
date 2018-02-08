/* ===================Start of global variables===============================*/
/*used in the date validation functions*/
var dtCh = "/";
var minYear = 1900;
var maxYear = 2100;

var dialogWin = new Object();// Global flag for Navigator 4-only event handling branches.
var i = 0;

var Nav4 = ((navigator.appName == "Netscape") && (parseInt(navigator.appVersion) == 4))
/* is flag for do validation or not  */
var doValidate = true;

/*appLocale will hold the current application locale by getting it from hidden field in a form in the template header
* added by Amir nasr
*/
var appLocale = "ar";
if (document.getElementById("header_frm_form1:header_hidden_locale") != null) {
    appLocale = document.getElementById("header_frm_form1:header_hidden_locale").value;
}

/* to hold the message to appear to the user when he tries one of the browser disabled functions
*  added by Aboelhassan hamed 3/1/2008
*/

/*
to hold the message to appear to the user when he tries to select the tree root node used in tree.js file
*  added by Aboelhassan hamed 3/1/2008
*/
var treeRootSelectedMsg;
var disabledFunctionMsg;
/* 
Purpose: Enable numbers Only with enter ability
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Dec, 12, 2010
*/
function keyPressNumberOrEnter(decimal) {
    var kc = window.event.keyCode;
    var t = window.event.srcElement.value;

    if (decimal) {
        if (t.indexOf(',') !=  - 1) {
            if ((kc >= 48 && kc <= 57 || kc == 13) == false) {
                window.event.keyCode = 0;
            }
        }
        else {
            if ((kc >= 48 && kc <= 57 || kc == 44 || kc == 13) == false) {
                window.event.keyCode = 0;
            }
        }
    }
    else {
        if ((kc >= 48 && kc <= 57 || kc == 13) == false) {
            window.event.keyCode = 0;
        }
    }
}
/* 
Purpose: Open a specific url in  popup window
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function openPopupWindowFullScreen(url) {
    if (url == '')
        return;
    window.open(url, '', 'width=' + screen.availWidth + ',height=' + screen.availHeight + ',top=0,left=0,toolbar=no,directories=no,status=no,menubar=no,resizable=no');
}

function openPopupWindow(url, width, height, top, left) {
    if (url == '')
        return;
    window.open(url, '', 'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left + ',toolbar=no,directories=no,status=no,menubar=no,resizable=no');
}
/*=================== End of global variables=============================*/
/* 
Purpose: needed while using <t:outputText.... to display server side err msg and u want to reset ur msgs to avoid displaying more than one msg at a time
@param componentId 
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Jan, 27, 2010
*/
function clearAndHideOutputText(componentId) {
    var component = document.getElementById(componentId);
    if (component != null) {
        component.innerText = '';
        component.style.display = 'none';
        component.style.height = '1px';
        component.style.width = '1px';
    }
}

function clearOutputText(componentId) {
    var component = document.getElementById(componentId);
    if (component != null) {
        try {
            component.innerText = '';
        }
        catch (e) {
        }
        try {
            component.innerHTML = '';
        }
        catch (e) {
        }
    }
}

function clearInputText(componentId) {
    var component = document.getElementById(componentId);
    if (component != null) {
        component.value = '';
    }
}

/* 
Purpose: Removes leading and trailing spaces
@param componentId 
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 9, 2009
*/
function trimBorders(componentOrId) {
    var component;
    if (typeof componentOrId == 'string') {
        component = document.getElementById(componentOrId);
    }
    else {
        component = componentOrId
    }
    if (component != null) {
        var strValue = component.value;
        if (strValue != null) {
            var objRegExp = /^(\s*)$/;
            var tempStrValue = strValue;
            if (objRegExp.test(tempStrValue)) {
                tempStrValue = tempStrValue.replace(objRegExp, '');
                if (tempStrValue.length == 0)
                    strValue = tempStrValue;
            }
            else {
                //check for leading & trailing spaces
                objRegExp = /^(\s*)([\W\w]*)(\b\s*$)/;
                if (objRegExp.test(strValue)) {
                    //remove leading and trailing whitespace characters
                    strValue = strValue.replace(objRegExp, '$2');
                }
            }
            component.value = strValue;
        }
    }
}

/* 
Purpose: Fire a command action
@param commandId
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Jan, 15, 2009
*/
function doClick(commandId, submit) {
    var link = document.getElementById(commandId);
    link.click();
    return submit;
}

/* 
Purpose: Calculate the difference between two dates and save the value at the passed component id
@param date1ComponentId, date2ComponentId, resultComponentId
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Jan, 21, 2009
*/

function calcDaysBetween(date1ComponentId, date2ComponentId, resultComponentId) {

    var result = '';
    var valid = validateInputCalenderFormate(date1ComponentId, 'null', 'null');
    if (valid) {
        valid = validateInputCalenderFormate(date2ComponentId, 'null', 'null');
    }
    if (valid) {
        var date1Object = document.getElementById(date1ComponentId).value;
        var date2Object = document.getElementById(date2ComponentId).value;

        if (date1Object != null && date1Object != '' && date2Object != null && date2Object != '') {
            var dateDetails = new Array();
            dateDetails = date1Object.split('/');
            var date1 = new Date(dateDetails[2], dateDetails[1] - 1, dateDetails[0]);
            dateDetails = date2Object.split('/');
            var date2 = new Date(dateDetails[2], dateDetails[1] - 1, dateDetails[0]);

            if (date1 <= date2) {

                // The number of milliseconds in one day
                var ONE_DAY = 1000 * 60 * 60 * 24;
                // Convert both dates to milliseconds
                var date1_ms = date1.getTime();
                var date2_ms = date2.getTime();
                // Calculate the difference in milliseconds
                var difference_ms = Math.abs(date1_ms - date2_ms);
                // Convert back to days and return
                result = Math.round(difference_ms / ONE_DAY) + 1;// to take the start day also
            }
        }
    }

    document.getElementById(resultComponentId).value = result;
    return valid;
}

/* 
Purpose: set the localized messages to be used in this file (assuming only arabic and english are used)
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  3/1/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function setLocalizedMessages() {

    if (appLocale == "en") {
        disabledFunctionMsg = "You can't use this function";

    }
    else {
        disabledFunctionMsg = "لا يمكن استخدام هذه الخاصيه";

    }

}
setLocalizedMessages()
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: disable the browser context menu,when user rightClick ,menu will not appear 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  3/1/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/

function disableContextMenu() {
    document.oncontextmenu = function () {
        alert(disabledFunctionMsg);
        return false;
    }
}
// To enable the browser context menu comment the following line 
//disableContextMenu();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:  disable the accelerator keys for the tool bar buttons 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed,Amir nasr
   1.2 - Date:  3/1/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function handleAccelerator(event) {

    if (event) {
        // for F5
        if (event.keyCode == 116) {
            alert(disabledFunctionMsg);
            event.preventDefault();
            return false;
        }

        //for ctrl+r
        if (event.keyCode == 82 && event.ctrlKey) {
            event.preventDefault();
            // alert(disabledFunctionMsg);
            return false;
        }
        //for ctr+f5
        if (event.keyCode == 116 && event.ctrlKey) {
            //    alert(disabledFunctionMsg);
            event.preventDefault();
            return false;
        }
        //for alt+left arrow    
        if (event.keyCode == 37 && event.altKey) {
            alert(disabledFunctionMsg);
            event.preventDefault();
            return false;

        }

        //for alt+right arrow    
        if (event.keyCode == 39 && event.altKey) {
            alert(disabledFunctionMsg);
            event.preventDefault();
            return false;

        }
    }

}

//To enable toolbar accelerator keys comment the following line 
//document.attachEvent("onkeydown", handleAccelerator);
var isIE = /*@cc_on!@*/false || document.documentMode;
if (isIE == 'true') {
    window.attachEvent('keydown', handleAccelerator(event));
}
else {
    window.addEventListener('keydown', function (event) {
        handleAccelerator(event);
    });
}

//added By Hisham Mounir
function HandleOnClose() {

}

/////////////////////////////////End of related three functions//////////////////////////////////////////////////////////
/* 
Purpose: reset error messages after validation 
Creation/Modification History :
   1.1 - Developer Name: Waleed Bader
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  7/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/

function resetMsgs() {
    var args = resetMsgs.arguments;
    var argsLen = resetMsgs.arguments.length;
    for (var i = 0;i != argsLen;++i) {
        var msgId = args[i];
        if (msgId == null) {
            continue;
        }

        var msg;
        if (document.getElementById(msgId) != null)
            msg = document.getElementById(msgId);
        if (msg != null) {
            msg.innerHTML = '';
            msg.style.display = 'none';
        }
    }
}

//////////////
/* 
Purpose:  disable editing for text box
Creation/Modification History :
   1.1 - Developer Name: Waleed badr
   1.2 - Date:  12/5/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function disableEditing(e) {
    if (e == null) {
        e = window.event;
    }

    //IE
    e.cancelBubble = true;
    e.returnValue = false;
    e.keyCode = 0;

    //e.stopPropagation works in Firefox.
    if (e.stopPropagation) {
        e.stopPropagation();
        e.preventDefault();
    }
}

/* 
Purpose:  disable characheters
Creation/Modification History :
   1.1 - Developer Name: Waleed badr
   1.2 - Date:  12/5/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function disableCharacters(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g, "");
    }
}
/* usage: onkeydown="return enableEnCharOnly(this);" onkeyup="return enableEnCharOnly(this);"*/
function enableEnCharOnly(field) {
    var val = field.value;
    if (/^[0-9A-Za-z ]*$/.test(val)) {
        return true;
    }
    if (val.length > 1) {
        val = val.substring(0, val.length - 1);
    }
    else {
        val = '';
    }
    field.value = val;
    return false;
}

/* 
Purpose:  enable integer values only , cnt start with 0
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  07/06/2010
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function enabelIntegerOnly(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g, "");
    }
    for (i = 0;i < field.value.length;i++) {
        if (field.value.charAt(0) == '0') {
            field.value = field.value.replace(field.value.charAt(0), "");
            i--;
        }
    }
}

/* 
Purpose: fire the click event of the supplied button when the user click enter in text box 
Creation/Modification History :
   1.1 - Developer Name: aboelhassan hamed,Hisham Mounir
   1.2 - Date:  28/12/2013
   1.3 - Creation/Modification:Modiification     
   1.4-  Description: takes the button id as paramter (tested on internet explorer)
   1.5- modified:- return false;
* 
*/
function FireButtonClick(buttonId) {
    keypress.combo("enter", function () {
        var button = document.getElementById(buttonId);
        button.click();
        return false;
    });
}

function FireButtonClickOldStyle(e, buttonId) {
    var evt = e || window.event;

    if (evt.keyCode == 13) {
        evt.cancelBubble = true;
        evt.returnValue = false;
        evt.preventDefault();
        var button = document.getElementById(buttonId);
        button.click();
    }
}
/* 
Purpose: get 
Creation/Modification History : get the style.left for the obj
   1.1 - Developer Name:Assmaa Omar
   1.2 - Date:  24/12/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function findPosX(obj) {
    var curleft = 0;
    if (obj.offsetParent)
        while (1) {
            curleft += ((obj.offsetLeft) - 1);
            if (!obj.offsetParent)
                break;
            obj = obj.offsetParent;
        }
    else if (obj.x)
        curleft += ((obj.x) - 1);
    return curleft;
}
/* 
Purpose: set focus on specific element (id) 
Creation/Modification History : get the style.left for the obj
   1.1 - Developer Name:Assmaa Omar
   1.2 - Date:  23/1/2009 
   1.3 - Creation/Modification:Creation      
   1.4-  Description: you can send id(as forcedID) to the function or it will focus the first input text in the form
* usage---> i called this funtion in operationBar when opening adddiv to set focus on الوص�?
for example --javascript:setFocusOnElement('myForm:Name');
*/
function setFocusAndSelect(id, selectIt) {
    try {
        var bFound = false;
        // for each form
        for (f = 0;f < document.forms.length;f++) {
            // for each element in each form
            for (i = 0;i < document.forms[f].length;i++) {
                //      // if it's not a hidden element
                if (document.forms[f][i].type != "hidden") {
                    // and it's not disabled
                    if (id != null) {
                        if (document.forms[f][i].id == id) {

                            // set the focus to it
                            document.forms[f][i].focus();
                            var bFound = true;
                        }
                    }
                    else if (document.forms[f][i].disabled != true && document.forms[f][i].type == 'text') {
                        // set the focus to it
                        document.forms[f][i].focus();
                        var bFound = true;
                    }
                }
                // if found in this element, stop looking
                if (bFound == true)
                    break;
            }
            // if found in this form, stop looking
            if (bFound == true)
                break;
        }
        if (document.getElementById(id) != null) {
            document.getElementById(id).focus();
            if (selectIt) {
                document.getElementById(id).select();
            }
        }
    }
    catch (exception) {
    }
}

function setFocusOnElement(id) {
    setFocusAndSelect(id, true);
}

function setFocusOnlyOnElement(id) {
    setFocusAndSelect(id, false);
}

function toggleHack(radio) {
    if (event.keyCode == 37 || event.keyCode == 38 || event.keyCode == 39 || event.keyCode == 40) {
        event.cancelBubble = true;
        event.returnValue = false;
    }
    else {
        toggleRadio(radio);
    }
}

/* 
Purpose: to enable float charachters only,   onkeypress="enabelFloatOnly(this);" onkeyup="enabelFloatOnly(this)"
Creation/Modification History :
   1.1 - Developer Name:Omar Ez, Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/

function enabelFloatOnly(field) {
    var num = field.value;
    if (!isFloat(num)) {
        var strLen = num.length;
        if (strLen > 1) {
            num = num.slice(0, strLen - 1);
        }
        else {
            num = "";
        }
        field.value = num;
    }
}

function isFloat(s) {
    var reFloat = /^((\d+(\.\d*)?)|((\d*\.)?\d+))$/
    if ((s == null) || (s.length == 0))
        if (isFloat.arguments.length == 1)
            return false;
        else 
            return (isFloat.arguments[1] == true);
    return reFloat.test(s)
}

function changeVisibilityNavigationMenu() {
    try {
        iFrameNavgationMenuObj = document.getElementById('iFrameNavgationMenu');

        if (document.getElementById('navigationMenuTD').style.visibility == 'hidden') {
            iFrameNavgationMenuObj.style.visibility = 'visible';
            document.getElementById('navigationMenuTD').style.visibility = 'visible';
            document.getElementById('div_nav_btn').style.left = '600';

        }
        else if (document.getElementById('navigationMenuTD').style.visibility == 'visible') {
            iFrameNavgationMenuObj.style.visibility = 'hidden';
            document.getElementById('navigationMenuTD').style.visibility = 'hidden';
            document.getElementById('div_nav_btn').style.left = '910';
        }
    }
    catch (exception) {
    }
}

function hideNavigationMenu() {
    try {
        if (document.getElementById('navigationMenuTD') != null) {
            if (document.getElementById('navigationMenuTD').style.visibility == 'visible') {
                document.getElementById('navigationMenuTD').style.visibility = 'hidden';
                document.getElementById('div_nav_btn').style.left = '910';
                iFrameNavgationMenuObj = document.getElementById('iFrameNavgationMenu');
                iFrameNavgationMenuObj.style.visibility = 'hidden';
            }
        }
        else if (window.parent.navigationMenuTD != null) {
            window.parent.navigationMenuTD.style.visibility = 'hidden';
            window.parent.div_nav_btn.style.left = '910';
            iFrameNavgationMenuObj = window.parent.document.getElementById('iFrameNavgationMenu');
            iFrameNavgationMenuObj.style.visibility = 'hidden';

        }
    }
    catch (exception) {
    }
}

function FireSpaceButtonClick(buttonId) {

    if (event.keyCode == 32) {
        event.cancelBubble = true;
        event.returnValue = false;
        var button = document.getElementById(buttonId);
        button.click();
    }
}

function setFoucsBetweenButtons(buttonId1, buttonId2) {
    if (document.getElementById(buttonId1) != null) {
        setFocusOnlyOnElement(buttonId1);
    }
    else if (document.getElementById(buttonId2) != null) {
        setFocusOnlyOnElement(buttonId2);

    }

}

function openWindowMaximized(aURL, aWinName) {
    var wOpen;
    var sOptions;

    sOptions = 'status=yes,menubar=yes,scrollbars=yes,resizable=yes,toolbar=yes';
    sOptions = sOptions + ',width=' + (screen.availWidth - 10).toString();
    sOptions = sOptions + ',height=' + (screen.availHeight - 122).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    wOpen = window.open('', aWinName, sOptions);
    wOpen.location = aURL;
    wOpen.focus();
    wOpen.moveTo(0, 0);
    wOpen.resizeTo(screen.availWidth, screen.availHeight);
    return wOpen;
}

//TODO locking code
// executes a script if the locking wasn't failed
// this method checks the lockingFailed hidden field if its value set to 1
// that's mean the locking was failed and shows an exception message instead of
// executing the script.
// note: You must call this function in the ajax_oncomplete 
// wrapping your script inside it
function executeAfterLock(script, formName) {
    var form = null;
    if (formName == null || formName == '') {
        form = document['forms']['myForm'];
    }
    else {
        form = document['forms'][formName];
    }
    var lockingFaildElm = form ? form['lockingFailed'] : null;
    var lockingFaild = lockingFaildElm == null ? false : lockingFaildElm.value == '1';
    if (lockingFaild) {
        changeVisibilityDiv(window.blocker, window.divMsg);
        //setfocusAtConfirmMsg();
    }
    else {
        eval(script);
    }
}

// this function for adjust IFrame height according to content page
// you have to call this function in onload event of the IFrame component
//send the ID of the IFrame as aparameter
function adjustIFrameHeight(iframeID) {
    //find the height of the internal page
    var the_height = document.getElementById(iframeID).contentWindow.document.body.scrollHeight;
    //change the height of the iframe
    document.getElementById(iframeID).height = the_height;
}

/**
 * do display tool tip for dropdowns and inputs
 * by Ashraf Gaber
 * @param e
 * @param elementId
 */
function displayTooltip(e, componentOrId) {
    var elt;
    if (typeof componentOrId == 'string') {
        elt = document.getElementById(componentOrId);
    }
    else {
        elt = componentOrId
    }
    if (elt.tagName == "SELECT") {
        if (elt.selectedIndex == 0 || elt.options[elt.selectedIndex].value != elt.value) {
            return null;
        }
        doTooltip(e, elt.options[elt.selectedIndex].text);
    }
    else if (elt.tagName == "INPUT") {
        if (elt.value == '')
            return null;
        doTooltip(e, elt.value);
    }

}

function filterationInputOnKeyPress(e, inputtext, dropdownId, errMsgId, ajaxFunction, nextFocusId) {
    var event = e || window.event;

    if (event.keyCode == 13) {
        var errMsg = document.getElementById(errMsgId);
        var dropdown = document.getElementById(dropdownId);
        var isFound = false;
        errMsg.style.display = "none";
        trimBorders(inputtext.value);
        if ((inputtext.value == '' && (dropdown.value == '' || dropdown.value == '-100')) || (inputtext.value == dropdown.value)) {
            if (nextFocusId != null) {
                setFocusOnElement(nextFocusId);
            }
            event.stopPropagation();
            event.preventDefault();
            return false;
        }
        for (var i = 0;i < dropdown.options.length;i++) {
            if (dropdown.options[i].value == inputtext.value) {
                dropdown.options[i].selected = true;
                isFound = true;
                event.stopPropagation();
                event.preventDefault();
            }
        }
        if (isFound == false) {
            event.stopPropagation();
            event.preventDefault();
            if (dropdown.value == '' || dropdown.value == '-100') {
                errMsg.style.display = "inline";
                inputtext.focus();
                inputtext.select();
                return false;
            }
            else {
                if (inputtext.value != '') {
                    errMsg.style.display = "inline";
                }
                dropdown.options[0].selected = true;
                if (ajaxFunction != null) {
                    ajaxFunction();
                }
                inputtext.focus();
                inputtext.select();
            }
        }
        else {
            if (ajaxFunction != null) {
                ajaxFunction();
            }
            else {
                event.stopPropagation();
                event.preventDefault();
            }
            if (nextFocusId != null) {
                setFocusOnElement(nextFocusId);
            }
        }
    }
}

function filterationInputOnBlur(e, inputtext, dropdownId, errMsgId, ajaxFunction, nextFocusId) {
    var event = e || window.event;
    var errMsg = document.getElementById(errMsgId);
    var dropdown = document.getElementById(dropdownId);
    var isFound = false;
    errMsg.style.display = "none";
    trimBorders(inputtext.value);
    if ((inputtext.value == '' && (dropdown.value == '' || dropdown.value == '-100')) || (inputtext.value == dropdown.value)) {
        return false;
    }
    for (var i = 0;i < dropdown.options.length;i++) {
        if (dropdown.options[i].value == inputtext.value) {
            dropdown.options[i].selected = true;
            isFound = true;
        }
    }
    if (isFound == false) {
        event.stopPropagation();
        event.preventDefault();
        if (dropdown.value == '' || dropdown.value == '-100') {
            errMsg.style.display = "inline";
            //inputtext.focus(); inputtext.select();
            return false;
        }
        else {
            if (inputtext.value != '') {
                errMsg.style.display = "inline";
            }
            dropdown.options[0].selected = true;
            if (ajaxFunction != null) {
                ajaxFunction();
            }
            //inputtext.focus(); inputtext.select();
        }
    }
    else {
        if (ajaxFunction != null) {
            ajaxFunction();
        }
        if (nextFocusId != null) {
            event.stopPropagation();
            event.preventDefault();
            setFocusOnElement(nextFocusId);
        }
    }
}

function filterationDropboxOnChange(e, dropdown, inputtextId, errMsgId, ajaxFunction, nextFocusId) {
    var event = e || window.event;
    var inputtext = document.getElementById(inputtextId);
    var errMsg = document.getElementById(errMsgId);
    errMsg.style.display = "none";
    if (dropdown.value == '' || dropdown.value == '-100') {
        inputtext.value = '';
    }
    else {
        inputtext.value = dropdown.value;
    }
    if (ajaxFunction != null) {
        ajaxFunction();
    }
    event.stopPropagation();
    event.preventDefault();
    if (inputtext.value != '' && nextFocusId != null) {
        setFocusOnElement(nextFocusId);
    }
    else {
        setFocusOnlyOnElement(dropdown.id);// changed by A.nour 8/2/2015 because setFocusOnElement raise 
                                           // javascript error on combobox input (select not a function)
    }
}

function copyDropdownIntoInputtext(dropdownId, inputtextId) {
    var inputtext = document.getElementById(inputtextId);
    var dropdown = document.getElementById(dropdownId);
    if (dropdown != null && inputtext != null) {
        if (dropdown.value == '' || dropdown.value == '-100') {
            inputtext.value = '';
        }
        else {
            inputtext.value = dropdown.value;
        }
    }
}

function getNextInput(inputtext) {
    for (f = 0;f < document.forms.length;f++) {
        for (i = 0;i < document.forms[f].length;i++) {
            if (document.forms[f][i].id == inputtext.id) {
                for (j = i + 1;j < document.forms[f].length;j++) {
                    if (document.forms[f][j].tagName == 'input') {
                        return document.forms[f][j];
                    }
                }
            }
        }
    }
    return inputtext
}

/***********************************************************************************************************************
 * validate civil id, full PACI validation
 * by Ashraf Gaber
 * @param componentOrId
 */
function isValidCivilId(componentOrId) {
    var field;
    if (typeof componentOrId == 'string') {
        field = document.getElementById(componentOrId);
    }
    else {
        field = componentOrId
    }
    var value = field.value;

    return isValidCivilIdValue(value);
}

function isValidCivilIdValue(value) {
    if (value == null || value == '') {
        return true;
    }

    var valid = isPositiveIntegerValue(value);
    if (valid) {
        if (value.length != 12) {
            valid = false;
        }
        else {
            var TOT_SUM = parseInt(value.substring(0, 1)) * 2 + parseInt(value.substring(1, 2)) * 1 + parseInt(value.substring(2, 3)) * 6 + parseInt(value.substring(3, 4)) * 3 + parseInt(value.substring(4, 5)) * 7 + parseInt(value.substring(5, 6)) * 9 + parseInt(value.substring(6, 7)) * 10 + parseInt(value.substring(7, 8)) * 5 + parseInt(value.substring(8, 9)) * 8 + parseInt(value.substring(9, 10)) * 4 + parseInt(value.substring(10, 11)) * 2;

            var CHECK_DIGIT = 11 - (TOT_SUM - (trunc(TOT_SUM / 11) * 11));
            if (CHECK_DIGIT != parseInt(value.substring(11, 12))) {
                valid = false;
            }

        }
    }
    return valid;
}

function trunc(n) {
    return n - n % 1;
}

function isPositiveIntegerValue(s) {
    var rePositiveInteger = /^\d+$/;
    if ((s == null) || (s.length == 0))
        if (isPositiveIntegerValue.arguments.length == 1)
            return false;
        else 
            return (isPositiveIntegerValue.arguments[1] == true);
    return rePositiveInteger.test(s);
}

/**********************************************************************************************************************/

function getValue(elementId) {
    if (document.getElementById(elementId) != null) {
        return document.getElementById(elementId).value;
    }
    return null;
}

function getRadioValue(elementId) {
    var elements = document.getElementsByName(elementId);
    for (var ii = 0;ii < elements.length;ii++) {
        if (elements[ii].checked) {
            return elements[ii].value;
        }
    }
    return null;
}

if (!document.getElementsByClassName) {
    document.getElementsByClassName = function (cn) {
        var allT = document.getElementsByTagName('*'), allCN = [], i = 0, a;
        while (a = allT[i++]) {
            a.className == cn ? allCN[allCN.length] = a : null;
        }
        return allCN
    }
}
/*
* open a new window with specific text (read from hidden field), you need to pass window name (read from output text)
* created by ali agamy
*/
function showDoc(textId, winNameInput) {
    var decText = document.getElementById(textId).value;
    var aWinName = document.getElementById(winNameInput).innerHTML;
    var title = aWinName;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 725;
    var popupWindowheight = 480;
    sOptions = 'status=no,menubar=no,location=no,scrollbars=yes,toolbar=no,resizable=0,addressbar=0';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';
    globalHTML = "<html><head><title>" + title + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'><p style='color: #105B84; direction: rtl; font-family: tahoma; font-size: 12px; font-weight: normal;line-height: 25px; text-align: justify;'> " + decText + " </p></body> </html>";
    wOpen = window.open("", '', sOptions);
    //alert(wOpen);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);
    return wOpen;
}
/*
* open a new window with specific Title and URL
* CPD and modified  by Islam Omar
*/
function openNewWindow(fullURL, winName) {

    var url = document.getElementById(fullURL).value;
    //Modified by Ali Agamy
    var windowTitle;
    if(document.getElementById(winName) != null){
        windowTitle = document.getElementById(winName).value;
		if(windowTitle == null || windowTitle == '') {
			windowTitle = document.getElementById(winName).innerHTML;
		}
    }
	//End Modification by Ali Agamy
	//var windowTitle = document.getElementById(winNameInput).innerHTML;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 990;
    var popupWindowheight = 540;

    sOptions = 'status=no,menubar=no,location=no,scrollbars=yes,toolbar=no,resizable=0,addressbar=0';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=950 height=500 src=\"" + url + "\"/></body></html>";

    wOpen = window.open("", '', sOptions);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

    return wOpen;
}

/*
* toggle Div appearance between show and hide 
* Created by H.Ahmed & I.Omar
*/
var divHidden = false;
function toggleDiv(hideDivBtn, searchDivId, contentId) {
    if (divHidden) {
        document.getElementById(searchDivId).style.display = 'block';
        divHidden = false;
        document.getElementById(hideDivBtn).className = 'hideDiv';
        document.getElementById(contentId).style.height = '100';
    }
    else {
        document.getElementById(searchDivId).style.display = 'none';
        divHidden = true;
        document.getElementById(hideDivBtn).className = 'showDiv';
        document.getElementById(contentId).style.height = '280';

    }
}

/**
 * Used to apply div collapse/expand for search panel and result panel
 * @param togglerId          control div
 * @param cnt1DivId searchPanelGroup
 * @param cntDivId  resultTabelPanelGroup
 * @author Ashraf Gaber
 * 
 * <t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
 *      <t:panelGroup onclick="toggleDivUsingMaxHeight('hideDivImg', 'jobIntgSearchPnl', 'jobIntgSearchResultTblPnl')"/>
 * </t:panelGroup>
 * <t:panelGroup forceId="true" id="jobIntgSearchPnl">
 *      ...
 * </t:panelGroup>
 * <t:panelGroup forceId="true" id="jobIntgSearchResultTblPnl" styleClass="fullWidthScroll90">
 *      ...
 * </t:panelGroup>
 */
function toggleDivUsingMaxHeight(togglerId, cnt1DivId, cntDivId, cntStyleClassWhileCnt1Expanded, cntStyleClassWhileCnt1Collapsed) {
    var collapsedHeight;
    var displayedHeight;
    if (document.getElementById(cnt1DivId).style.display == 'none') {
        document.getElementById(cnt1DivId).style.display = 'block';
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).style.height;
        document.getElementById(togglerId).className = 'hideDivPnl';
        if(cntStyleClassWhileCnt1Expanded != null ){
            document.getElementById(cntDivId).className = cntStyleClassWhileCnt1Expanded;
        } else {
            document.getElementById(cntDivId).style = "max-height:sheka;";
        }
    } else {
        collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
        displayedHeight = document.getElementById(cntDivId).offsetHeight;
        document.getElementById(cnt1DivId).style.display = 'none';
        
        document.getElementById(togglerId).className = 'showDivPnl';
        if(cntStyleClassWhileCnt1Collapsed != null ){
            document.getElementById(cntDivId).className = cntStyleClassWhileCnt1Collapsed;
        } else {
            document.getElementById(cntDivId).style = "max-height:"+(displayedHeight+collapsedHeight-5)+"px;";
        }
    }
}

  function toggleDivUsingMaxHeightVer2(togglerId, cnt1DivId, cntDivId, collapse) {
    var collapsedHeight;
    var displayedHeight;
//alert("toggleDivUsingMaxHeightVer2 collapse= "+collapse);
      if (collapse == 'true' ) {
      //alert("yes true");
          collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
          displayedHeight = document.getElementById(cntDivId).offsetHeight;
          document.getElementById(cnt1DivId).style.display = 'none';
          document.getElementById(togglerId).className = 'showDivPnl';
          document.getElementById(cntDivId).style = "max-height:"+(displayedHeight+collapsedHeight-5)+"px;";
      }
      else {
      //alert("no flase ");
          document.getElementById(cnt1DivId).style.display = 'block';
          document.getElementById(togglerId).className = 'hideDivPnl';
          //document.getElementById(cntDivId).style = "max-height:"+expandedHieght+"px;";
      }
  }  


  function togglePageUsingCstmHeight(togglerId, cnt1DivId, cntDivId,expandedHieght,collapsedHieght,collapse) {
//          document.getElementById(cnt1DivId).style.display = document.getElementById('filterPnlDisplay').value;//'inline-table';
//          document.getElementById(togglerId).className = document.getElementById('iconPnlStyle').value;//'hideDivPnl';
//          document.getElementById(cntDivId).style.height = document.getElementById('resultPnlHeight').value;//'322';
//          document.getElementById('testId').value = 1;
//alert("collapse= "+collapse);
      if (collapse == 'true' ) {
      //alert("yes true");
          document.getElementById(cnt1DivId).style.display = 'none';
          document.getElementById(togglerId).className = 'showDivPnl';
          document.getElementById(cntDivId).style.height = collapsedHieght;
          //document.getElementById('testId').value = 1;
      }
      else {
      //alert("no flase ");
          document.getElementById(cnt1DivId).style.display = 'inline-table';
          document.getElementById(togglerId).className = 'hideDivPnl';
          document.getElementById(cntDivId).style.height = expandedHieght;
          //document.getElementById('testId').value = 0;
      }
          
  }
  
function openEmpListWindow(url, windowTitle) {
    var wOpen;
    var sOptions;
    var popupWindowWidth = 990;
    var popupWindowheight = 540;
    sOptions = 'status=no,menubar=no,location=no,scrollbars=yes,toolbar=no,resizable=0,addressbar=0';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';
    wOpen = window.open(url, '', sOptions);
    wOpen.document.title = windowTitle;
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);
    return wOpen;
}

function FireButton(buttonId) {
        var button = document.getElementById(buttonId);
        button.click();
       
}

/* to move to next element whose id nextElementId when press tab
*  and move to last element whose id lastElementId when press shift+tab
*  added by Aly Noor 23/09/2014
*/
    
function onKeyDownFirstElement(e,nextElementId,lastElementId) {
        if(e.shiftKey && e.keyCode == 9) {//shift+tab
            //alert("onKeyDownFirstElement - shift+tab : lastElementId = "+lastElementId+" , document.getElementById(lastElementId) = "+document.getElementById(lastElementId));
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            document.getElementById(lastElementId).focus();
            return;
        }
        else if(e.keyCode == 9) {
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            document.getElementById(nextElementId).focus();//elId is id of element to focus when press tab
            //document.getElementById(elId).select();
        }
    }

/* to move to first element whose id firstElementId when press tab 
*  and move to previous element whose id prevElementId when press shift+tab
*  added by Aly Noor 23/09/2014
*/    
    function onKeyDownLastElement(e,prevElementId,firstElementId) {
        if(e.shiftKey && e.keyCode == 9) {//shift+tab
            //alert("onKeyDownLastElement - shift+tab : prevElementId = "+prevElementId+" , document.getElementById(prevElementId) = "+document.getElementById(prevElementId));
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            document.getElementById(prevElementId).focus();
            return;
        }
        else if(e.keyCode == 9) {
            e.cancelBubble = true;
            e.returnValue = false;
            e.preventDefault();
            document.getElementById(firstElementId).focus();//elId is id of element to focus when press tab
            //document.getElementById(elId).select();
        }
    }


/* to focus row in scrollable datatable 
*  added by Ali Agamy 24/03/2015
*/
function focusHighlightedRow() {
    var compIH = document.getElementById("indexInputHidden");
    if (compIH != null) {
        var index = compIH.value;
        var cmdId = "chk[" + index + "]";
        resetScrollPositionForDataTable(cmdId);
        document.getElementById(cmdId).focus();
        document.getElementById(cmdId).focus();

    }

}

function resetScrollPositionForDataTable(cmdId) {
    if (document.getElementById(cmdId) != null) {
        var y = findPosY(document.getElementById(cmdId));
        y = y - 200;
        document.getElementById('dataT_data_panel').scrollTop = y;
    }
}

function showElement(objId){
    var obj = document.getElementById(objId);
    if(obj != null && typeof(obj) != 'undefined'){
        obj.style.display='block';
    }
}

function hideElement(objId){
    var obj = document.getElementById(objId);
    if(obj != null && typeof(obj) != 'undefined'){
        obj.style.display='none';
    }
}
function changeElmClass(elmId,clazz){
    document.getElementById(elmId).className=clazz;
}
function showGroupName(){
    showElement('groupNamePanel');
    changeElmClass('citizenName','UserHeaderTextHover');
    changeElmClass('grp','GroupHeaderTextHover');
}

function hideGroupName(){
    hideElement('groupNamePanel')
    changeElmClass('citizenName','UserHeaderText');
    changeElmClass('grp','GroupHeaderText');
}
function validateDateOnEvent(field) {
    var fieldValue = field.value;
    var strLen = fieldValue.length;

    if (fieldValue == '/') {
        field.value = "";
        return;
    }
    if (fieldValue.indexOf("//") !=  - 1) {
        fieldValue = fieldValue.slice(0, strLen - 1);
        field.value = fieldValue;
        return;
    }

    var dateArr = fieldValue.split('/');
    var arrLength = dateArr.length;

    if (arrLength == 1) {
        if (!isValidMonth(fieldValue)) {
            fieldValue = fieldValue.slice(0, strLen - 1);
            field.value = fieldValue;
        }
        return;
    }
    /*//	if(arrLength == 2) {
//		if(dateArr[1].length == 0){
//			if(dateArr[0].length==1){
//				dateArr[0] = "0"+dateArr[0];
//				fieldValue = "0"+fieldValue;
//				field.value = fieldValue;
//			}
//			return;
//		}
//		
//		if(!isValidMonth(dateArr[1])){
//			fieldValue = fieldValue.slice(0,strLen-1);
//			field.value = fieldValue;
//	    }
//		return;
//	}*/
    if (dateArr[1].length == 0) {
        if (dateArr[0].length == 1) {
            if (dateArr[0] == "0") {
                fieldValue = fieldValue.slice(0, strLen - 1);
                field.value = fieldValue;
                return;
            }
            dateArr[0] = "0" + dateArr[0];
            fieldValue = dateArr[0] + "/";//+dateArr[1]+"/"
            field.value = fieldValue;
        }
        return;
    }

    if (dateArr[2] == '') {
        fieldValue = fieldValue.slice(0, strLen - 1);
        field.value = fieldValue;

        return;
    }

    if (!isValidYear(dateArr[1])) {
        fieldValue = fieldValue.slice(0, strLen - 1);
        field.value = fieldValue;

        return;
    }
}
function isValidMonth(s) {
    var reFloat = /^([0-9]|0[1-9]|1[0-2])$/;
    return reFloat.test(s);
}

function isValidYear(s) {
    var reFloat = /^([1-2]|19|2[0-9]|19[0-9]|2[0-9][0-9]|19[0-9][0-9]|2[0-9][0-9][0-9])$/;
    return reFloat.test(s);
}

/* to open report url in new window , but you must call this function onClick event
*  added by TechnicalTeam[Ali Agamy] 
*  13/10/2015
*  CSC-14760 
*/
function openReportWindow(params) {
    var paramsStr = document.getElementById(params).value;
    var url = ctxPath + "/module/jsps/reports/reportsFrame.jsf?"+ paramsStr;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 990;
    var popupWindowheight = 540;

    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    globalHTML = "<html><head><title></title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe width='100%' height='100%' src=\"" + url + "\"/></body></html>";

    wOpen = window.open("", '', sOptions);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

    return wOpen;
}
/************************************************************/
//By GEEKS
//Hide operation Menu on clickEvent
function closeMenus(idValue) {
    try {
        var formName = idValue.split(":")[0];
        var menuContainer = document.getElementById(formName + "_Menu_menu");
        var selMenu = menuContainer.getElementsByTagName("div");
        for (var i = 0;i < selMenu.length;i++) {
            selMenu[i].style.visibility = "hidden";
        }
    }
    catch (ex) {
    }
}
//Block Page in any submit
document.onclick = function (evt) {
   try{ 
       evt = evt || window.event;
        var selTarget = evt.target;
        var onClickValue = selTarget.getAttribute('onclick');
        if (selTarget.type === "submit" && onClickValue.indexOf("AJAX.Submit") ===  - 1) {
            var selForm = selTarget.form;
            selForm.onsubmit = function () {
                block();
            }
        }
    }
    catch (ex) {
    }
};
//End Hide operation Menu on clickEvent