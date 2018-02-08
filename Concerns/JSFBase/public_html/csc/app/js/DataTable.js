/*
File Content Description:this file about operations applied to data table component such selecting all rows or clear selection ,highlighting specific row
*/

/* 
Purpose: Written to get the current page index while using paging or not
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber, Islam ElNahrawy
   1.2 - Date: 2-11-2009
*/
function updatePageIndexHiddenField() {
    var pageIndex = '';
    if (document.getElementById('pageIndexWithoutPaging') != null) {
        pageIndex = document.getElementById('pageIndexWithoutPaging').value;
    }
    if (pageIndex == '') {
        if (document.getElementById('pageIndexWithPaging') != null) {
            pageIndex = document.getElementById('pageIndexWithPaging').value;
        }
    }
    if (document.getElementById('pageIndex') != null) {
        document.getElementById('pageIndex').value = pageIndex;
    }

}
updatePageIndexHiddenField();

/* 
Purpose:when user check check all button make all rows selected and when user un check check all button clear selection of all rows
@Param checkAllId : check all button ID
@Param arrayId : check box ID @ your data table
@Param listSize: input hidden ID  that represent list size of your data table
Creation/Modification History :
   1.1 - Developer Name: Nora Ismail
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function setAll_Gen(checkAllId, arrayId, listSize) {
    var object;
    var pageIndex;
    var noOfTableRows;

    if (document.getElementById(checkAllId) != null)
        object = document.getElementById(checkAllId);

    if (document.getElementById('arrayId') != null)
        document.getElementById('arrayId').value = arrayId;

    if (document.getElementById(listSize) != null)
        pageIndex = document.getElementById(listSize).value;

    if (document.getElementById('noOfTableRows') != null)
        noOfTableRows = document.getElementById('noOfTableRows').value;

    if (document.getElementById('pageIndex') != null)
        pageIndex = document.getElementById('pageIndex').value;

    for (i = 0;;i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            }
            else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
            }
            else {
                elem.checked = object.checked;
                //setSelected(elem, arrayId);
            }
        }
    }
}

///////////////////////////////////////////////////////////////////////////
/* 
Purpose:check if all rows @ data table selected or unselected and reflect that al check all button
@Param arrayId : check box ID @ your data table
@Param listSize: input hidden ID  that represent list size of your data table
@Param checkAll : check all button ID
Creation/Modification History :
   1.1 - Developer Name: Nora Ismail
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function checkCheckAll_Gen(arrayId, listSize, checkAll) {
    //    alert("checkCheckAll_Gen");
    var pageIndex;
    var noOfTableRows;

    if (document.getElementById('arrayId') != null)
        document.getElementById('arrayId').value = arrayId;

    if (document.getElementById(listSize) != null)
        pageIndex = document.getElementById(listSize).value;

    if (document.getElementById('pageIndex') != null && document.getElementById('pageIndex').value != '')
        pageIndex = document.getElementById('pageIndex').value;

    if (document.getElementById('noOfTableRows') != null)
        noOfTableRows = document.getElementById('noOfTableRows').value;

    var checked = 0;
    var checkBoxLength = 0;
    for (i = 0;;i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            }
            else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
                checkBoxLength = checkBoxLength - 1;
            }
            else if (elem.checked) {
                checked++;
            }
        }
        checkBoxLength++;// = i+1;
    }
    if (checked == checkBoxLength) {
        if (document.getElementById(checkAll) != null)
            document.getElementById(checkAll).checked = true;
    }
    else {
        if (document.getElementById(checkAll) != null)
            document.getElementById(checkAll).checked = false;
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: This function highlight row in data table
@param tableid: the id of the table to add row highlight
Creation/Modification History :
   1.1 - Developer Name: Sherif elrabbat
   1.2 - Date:  28/12/2007
   1.3 - Creation/Modification:Creation      
   1.4-  Description:Enhancement,to make modified/added row is more clear to user 
* 
*/

function addRowHighlight(tableid) {

    var trs = '';
    if (document.getElementById(tableid) != null) {
        trs = document.getElementById(tableid).getElementsByTagName('tr');
    }
    if (trs != null && trs != '') {
        for (var i = 0;i < trs.length;i++) {
            trs[i].onmouseover = new Function();
            trs[i].onmouseout = new Function("this.style.backgroundColor='" + trs[i].bgColor + "'");
        }
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: Grouping all rows in data table
@param radio,radio control
Creation/Modification History :
   1.1 - Developer Name:  Amir Nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:Bug for jsf to solve the problem of radio buttons with JSF DataTable
* 
*/
function toggleRadio(radio) {

    var id = radio.name.substring(radio.name.lastIndexOf(':'));
    var elems = radio.form.elements;
    //var curRadio;
    for (var i = 0;i < elems.length;i++) {
        if (elems[i].name.substring(elems[i].name.lastIndexOf(':')) == id) {
            elems[i].checked = false;
        }
    }
    radio.checked = true;
}
/* 
Purpose: Grouping all rows in data table By btns
@param radio,radio control
Creation/Modification History :
   1.1 - Developer Name:  Amr Abdo
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:Bug for jsf to solve the problem of radio buttons with JSF DataTable By keyBoard Btns
* 
*/
function toggleRadioKeyUp(radio) {
    var keyCodeVal = window.event.keyCode;
    if (keyCodeVal == 9 || keyCodeVal == 16) {
        return;
    }
    else {
        toggleRadio(radio);
    }
}

/* 
Creation/Modification History :
   1.1 - Developer Name:  Aly Noor
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:to solve the problem of radio buttons with JSF DataTable By keyBoard Btns 
        (select radio btn when click space or enter on focused one )
* 
*/
function toggleRadioKeyUpVersionTwo(radio, ev) {
    var keyCodeVal = ev.keyCode;
    //if (keyCodeVal == 9 || keyCodeVal == 16){// tab or shift
    //    return false;
    //}else 
    if (keyCodeVal == 32) {
        //space  //else if (keyCodeVal == 13 || keyCodeVal == 32) {// space or enter
        toggleRadio(radio);
        return true;
    }
    else if (keyCodeVal == 13) {
        //enter  //else if (keyCodeVal == 13 || keyCodeVal == 32) {// space or enter
        toggleRadio(radio);
        radio.click();
        return true;
    }
    else {
        return false;
    }
}

/*
     * added by Aly Noor 11/11/2014
     * to move when click shift+tab on first radio on page to element whose id prevElementId
     * and move when click tab on last radio on page to element whose id firstElementId
     * */
function onKeyDownRadio(e, radio, prevElementId, firstElementId, radioIndex, rowsNo) {
    var pageRadioIndex = radioIndex % rowsNo;
    //alert("rowsNo = "+rowsNo+" , radioIndex = "+radioIndex+" , pageRadioIndex = "+pageRadioIndex);
    var radioGroup = getRadioArray(radio);//document.getElementsByName('myForm:dataT_data:chk');
    //document["myForm"]["dataT_data"]["chk"];
    //alert("--++**radioGroup.length = "+radioGroup.length+" , pageRadioIndex == (radioGroup.length - 1 = "+ (pageRadioIndex == (radioGroup.length - 1)) );
    if (e.shiftKey && e.keyCode == 9 && pageRadioIndex == 0) {
        //shift+tab first radio
        //alert("onKeyDownLastElementRadio - shift+tab : prevElementId = "+prevElementId+" , document.getElementById(prevElementId) = "+document.getElementById(prevElementId));
        e.cancelBubble = true;
        e.returnValue = false;
        e.preventDefault();
        document.getElementById(prevElementId).focus();
        return;
    }
    else if (e.shiftKey && e.keyCode == 9 && pageRadioIndex == (radioGroup.length - 1)) {
        //shift+tab last radio
        return;
    }
    else if (e.keyCode == 9 && pageRadioIndex == (radioGroup.length - 1)) {
        //tab last radio
        //alert("onKeyDownLastElementRadio - tab : prevElementId = "+prevElementId);
        e.cancelBubble = true;
        e.returnValue = false;
        e.preventDefault();
        document.getElementById(firstElementId).focus();//elId is id of element to focus when press tab
        //document.getElementById(elId).select();
    }
}

/*
     * added by Aly Noor 11/11/2014
     * to get array of radio elements of jsp page form id : myForm
     * note : t:selectOneRadio att forceId is must ="false" (default) 
     * */
function getRadioArray(radio) {
    var formElements = window.document.getElementById("myForm").elements;
    var formElement;
    var radioArray = [];
    var id = radio.name.substring(radio.name.lastIndexOf(':'));

    for (var i = 0, j = 0;i < formElements.length;i++) {
        formElement = formElements.item(i);
        if (formElement.type == "radio" && formElement.name.substring(formElement.name.lastIndexOf(':')) == id) {
            radioArray[j] = formElement;
            //                formElement.parentNode.parentNode.style.backgroundColor="#FFFFFF";
            formElement.parentNode.style.border = "0px none transparent";++j;
        }
    }
    return radioArray;
}
/*Creation/Modification History :
   1.1 - Developer Name:  Aly Noor
   1.2 - Date: 2014/11/11
   1.3 - Creation/Modification:Creation      
   1.4-  Description:to give style to focused radio button
* 
*/

function changeBG(radio) {
    //alert("changeBG");
    //var tr = document.getElementById('dataT_data').getElementsByTagName('tr')[x];
    getRadioArray(radio);
    //radio.parentNode.parentNode.style.backgroundColor="#C8DFF4";
    radio.parentNode.style.border = "1px dotted orange";
}

function toggleRadioKeyUpEnableBtn(radio, okBtn) {
    var keyCodeVal = window.event.keyCode;
    if (keyCodeVal == 9 || keyCodeVal == 16) {
        return;
    }
    else {
        toggleRadio(radio);
        if (document.getElementById(okBtn)) {
            document.getElementById(okBtn).disabled = false;
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:Making all rows in datatable selected when user select all checkbox button 
@param checkAllId , the id of checkAll checkbox button
@param arrayId,  the id of other checkboxes exist in datatable
Creation/Modification History :
   1.1 - Developer Name: Amir nasr 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*     ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  30/6/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function setAll(checkAllId, arrayId) {
    var object;
    var pageIndex;
    var noOfTableRows;
    if (document.getElementById('checkAll') != null)
        object = document.getElementById('checkAll');

    if (document.getElementById('arrayId') != null)
        document.getElementById('arrayId').value = arrayId;

    if (document.getElementById('listSize') != null)
        pageIndex = document.getElementById('listSize').value;

    if (document.getElementById('noOfTableRows') != null)
        noOfTableRows = document.getElementById('noOfTableRows').value;

    if (document.getElementById('pageIndex') != null)
        pageIndex = document.getElementById('pageIndex').value;
    //    alert(" setAll pageIndex  = "+pageIndex+" , arrayId  = "+
    //            arrayId+" , noOfTableRows = "+noOfTableRows + " , object = "+object);
    for (i = 0;;i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            }
            else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
            }
            else {
                elem.checked = object.checked;
                //setSelected(elem, arrayId);
            }
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:Making all rows in datatable uncheckedz
Creation/Modification History :
   1.1 - Developer Name: Ahamed abdel fatah/Amir nasr
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function clearSelection() {
    window.document.forms[0].choosen.value = ',';
    var elems = window.document.forms[0].elements;
    for (var i = 0;i < elems.length;i++) {
        if (elems[i].type == "checkbox") {
            if (elems[i].checked) {
                elems[i].checked = false;
            }
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:When user select row in first page or any page ,this method check if all rows selected then  it will make all check box button  selected,if not and sender is selected ,it make check all button unchecked 
@param arrayId,  the id of other checkboxes exist in datatable
Creation/Modification History :
   1.1 - Developer Name:Ahamed Abdel Fatah
   1.2 - Date: 24/03/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*     ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/

function checkCheckAll(arrayId) {
    //    alert("checkCheckAll");
    var pageIndex;
    var noOfTableRows;

    if (document.getElementById('arrayId') != null)
        document.getElementById('arrayId').value = arrayId;

    if (document.getElementById('listSize') != null)
        pageIndex = document.getElementById('listSize').value;

    if (document.getElementById('pageIndex') != null && document.getElementById('pageIndex').value != '')
        pageIndex = document.getElementById('pageIndex').value;

    if (document.getElementById('noOfTableRows') != null)
        noOfTableRows = document.getElementById('noOfTableRows').value;

    var checked = 0;
    var checkBoxLength = 0;
    for (i = 0;;i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            }
            else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
                checkBoxLength = checkBoxLength - 1;
            }
            else if (elem.checked) {
                checked++;
            }
        }
        checkBoxLength++;// = i+1;
    }
    if (checked == checkBoxLength) {
        if (document.getElementById('checkAll') != null)
            document.getElementById('checkAll').checked = true;
    }
    else {
        if (document.getElementById('checkAll') != null)
            document.getElementById('checkAll').checked = false;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:When user select row in first page or any page ,this method check if all rows selected then  it will make all check box button  selected,if not and sender is selected ,it make check all button unchecked 
Creation/Modification History :
   1.1 - Developer Name:Ahamed Abdel Fatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*      ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  30/6/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function checkAllCheckBox() {

    var arrayId = '';
    var pageIndex;
    var noOfTableRows;

    if (document.getElementById('arrayId') != null) {
        arrayId = document.getElementById('arrayId').value;
    }
    //    alert(" 1 checkAllCheckBox pageIndex  = "+pageIndex+" , arrayId  = "+arrayId
    //        +" , noOfTableRows = "+noOfTableRows+" , (arrayId != '') "+(arrayId != '') +" arrayId.value = "+arrayId.value+" , typeof(arrayId) = "+typeof(arrayId)+" , (typeof(arrayId.value) != 'undefined') = "+ (typeof(arrayId.value) != 'undefined') );
    if (arrayId != '' && typeof (arrayId) != 'undefined') {
        //    alert(" 2 checkAllCheckBox pageIndex  = "+pageIndex+" , arrayId  = "+arrayId+" , noOfTableRows = "+noOfTableRows);
        if (document.getElementById('listSize') != null)
            pageIndex = document.getElementById('listSize').value;
        if (document.getElementById('pageIndex') != null && document.getElementById('pageIndex').value != '')
            pageIndex = document.getElementById('pageIndex').value;

        if (document.getElementById('noOfTableRows') != null) {
            noOfTableRows = document.getElementById('noOfTableRows').value;
        }

        var checked = 0;
        var checkBoxLength = 0;
        var startIndex = ((pageIndex - 1) * noOfTableRows);
        //    alert(" 3 checkAllCheckBox pageIndex  = "+pageIndex+" , arrayId  = "+arrayId+" , noOfTableRows = "+noOfTableRows+" , startIndex = "+startIndex);
        for (i = startIndex;i < (startIndex + noOfTableRows - 1);i++) {
            id = arrayId + '[' + i + ']';
            //            alert("id = "+id);
            if (document.getElementById) {
                elem = document.getElementById(id);
                //                alert("elem = "+elem);
                if (elem == null) {
                    //&& i <= 0) {
                    break;
                }
                /*else if (elem == null) {
                    i = ((pageIndex - 1) * noOfTableRows) - 1;
                    checkBoxLength = checkBoxLength - 1;
                }*/
                else if (elem.checked) {
                    checked++;
                }
            }
            checkBoxLength++;
        }
        if ((checked == checkBoxLength) && checked != 0) {
            if (document.getElementById('checkAll') != null)
                document.getElementById('checkAll').checked = true;
        }
        else {
            if (document.getElementById('checkAll') != null)
                document.getElementById('checkAll').checked = false;
        }
    }
}
checkAllCheckBox();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Unused Function
Purpose:Save the check/uncheck of the checkboxes over the pages of the data table (in a hiden field) 
@param selected: the selected checkbox
@param arrayId: the name of the array that array the checkboxes in the table
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
    ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function setSelected(selected, arrayId) {
    //    alert("setSelected");
    var checkIndex = selected.id;
    var selectedCheckBoxes;
    var end = checkIndex.indexOf(']');

    checkIndex = checkIndex.substring(arrayId.length + 1, end);

    if (document.getElementById('selectedCheckBoxes') != null) {
        selectedCheckBoxes = document.getElementById('selectedCheckBoxes').value;

        if (selected.checked && selectedCheckBoxes != null && selectedCheckBoxes.indexOf(',' + checkIndex + ',') ==  - 1) {
            selectedCheckBoxes += checkIndex + ",";
            document.getElementById('selectedCheckBoxes').value = selectedCheckBoxes;
        }
        else {
            var index = selectedCheckBoxes.indexOf("," + checkIndex + ",");
            if (index !=  - 1) {
                selectedCheckBoxes = selectedCheckBoxes.substring(0, index) + selectedCheckBoxes.substring(index + 1 + checkIndex.length, selectedCheckBoxes.length);
                document.getElementById('selectedCheckBoxes').value = selectedCheckBoxes;
            }
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:Checking if at least there is one radio button selected 
@param form, the form
@param trans ,the operation will be performed (Edit /Delete)
Creation/Modification History :
   1.1 - Developer Name: 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function CheckSelection(form, trans) {
    var counter = 0;
    var elems = form.elements;
    for (var i = 0;i < elems.length;i++) {
        if (elems[i].type == "radio") {
            if (elems[i].checked) {
                counter++;
                break;
            }
        }
    }
    if (counter == 0) {
        if (appLocale == "en")
            alert("please, select one item");
        else if (appLocale == "ar")
            alert("من �?ضلك، اختر أحد العناصر");
        return false;
    }
    else if (counter != 0) {
        if (trans == "delete") {
            var remove
            if (appLocale == "en")
                remove = confirm("Do you want to delete this Record ?");
            else if (appLocale == "ar")
                remove = confirm("هل انت متاكد من اتمام عملية الحذ�?؟");
            if (!remove) {
                return false;
            }
        }
    }
    return true;
}

/* 
Purpose:Checking if at least there is one radio button selected regardless of operation type
@param form, the form
@param trans ,the operation will be performed (Edit /Delete)
Creation/Modification History :
  1.1 - Developer Name: Nora
  1.2 - Date: 
  1.3 - Creation/Modification:Creation      
  1.4-  Description:
    ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
* 
*/
function CheckRadioSelection(form, errorMessage, outputMsgID) {
    //alert("CheckRadioSelection");
    var counter = 0;
    var elems = form.elements;
    for (var i = 0;i < elems.length;i++) {
        if (elems[i].type == "radio") {
            //   alert("radio");
            if (elems[i].checked) {
                counter++;
                break;
            }
        }
    }
    if (counter == 0) {
        if (document.getElementById(outputMsgID) != null)
            document.getElementById(outputMsgID).innerHTML = errorMessage;
        return false;
    }
    if (document.getElementById(outputMsgID) != null)
        document.getElementById(outputMsgID).innerHTML = '';

    return true;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: handle row double click action
Param:
    selectionComponentId: check box or radio button id
    index: row index
Creation/Modification History :
    1.1 - Developer Name: Ashraf Gaber
    1.2 - Date: 26-09-2010
*/

function rowOnDblClickJs(selectionComponentId, index) {
    var counter = 0;
    var isRadio = false;
    var radioIndex = 0;
    if (typeof (tabledblClickJsFunction) != 'undefined') {
        for (f = 0;f < document.forms.length;f++) {
            for (i = 0;i < document.forms[f].length;i++) {
                if (document.forms[f][i].id.contains(selectionComponentId) || document.forms[f][i].name.contains(selectionComponentId)) {

                    if (document.forms[f][i].type == "checkbox") {
                        if (document.forms[f][i].checked) {
                            counter++;
                            break;
                        }
                    }
                    else if (document.forms[f][i].type == "radio") {
                        isRadio = true;
                        document.forms[f][i].checked = false;
                        counter = 0;
                        // added by Geeks Team to fix DBClick on all pages not on first page only.
                        if ((index % 14) == radioIndex) {
                            document.forms[f][i].checked = true;
                        }
                        radioIndex++;
                    }
                }
            }
        }
        if (counter == 0) {
            if (isRadio == false) {
                document.getElementById(selectionComponentId + "[" + index + "]").checked = true;
            }
            tabledblClickJsFunction(index, radioIndex == 0 ? "0" : "1");
        }
        else {
            //in case double click on check box.
            rowDblClickJSForCheckBoksList(selectionComponentId, index);
        }
    }
}

/*
// Added by MLotfy, 
// If you have in your data table multible checked check boxes, then
// you tried to make double click action on a new row, then all the
// old checked rows before will be unchecked, and the new checked row
// will be only check, then fire the action on this new checked row.
*/
function rowDblClickJSForCheckBoksList(selectionComponentId, index) {
    if (typeof (tabledblClickJsFunction) != 'undefined') {
        for (f = 0;f < document.forms.length;f++) {
            for (i = 0;i < document.forms[f].length;i++) {
                if (document.forms[f][i].id.contains(selectionComponentId) || document.forms[f][i].name.contains(selectionComponentId)) {
                    if (document.forms[f][i].type == "checkbox") {
                        document.forms[f][i].checked = false;
                    }
                }
            }
        }
        document.getElementById(selectionComponentId + "[" + index + "]").checked = true;
        tabledblClickJsFunction(index, "0");
    }
}

/**
 * Adjust scrollable data table height
 * @param tableId ----- default value 'myDataTableId'
 * @param tableBodyHeight body height
 */
function adjustM2MDataTable(tableId, tableHeight) {
    if (tableHeight == null) {
        tableHeight = 275;
    }
    adjustDataTable(tableId, tableBodyHeight);
}

function adjustDataTable(tableId, tableHeight) {
    if (tableId == null) {
        tableId = 'myDataTableId';
    }
    var tableRect = document.getElementById(tableId).getBoundingClientRect();
    if (tableHeight == null) {
        var totalAvailableHeight = 502;
        tableHeight = totalAvailableHeight - tableRect.top;
    }

    var renderedHeight = tableRect.bottom - tableRect.top;
    var isIE = window.navigator.userAgent.indexOf("MSIE ") > 0;
    if (isIE) {
        document.getElementById(tableId).style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";
        document.getElementById(tableId).style.display = 'block';
        document.getElementById(tableId).style.height = tableHeight;
        document.getElementById(tableId).style.overflow = 'auto';
        document.getElementById(tableId).style.width = '100%';
    }
    else {
        if (tableHeight > renderedHeight) {
            document.getElementById(tableId).style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";
        }
        else {
            var myHeader = getNextElementByTag(tableId, 'THEAD');
            tableHeight = tableHeight - myHeader.offsetHeight;
            var scrollbarWidth = getScrollbarWidth();

            var myBody = getNextElementByTag(tableId, 'TBODY');

            var headerColumns = getTableHeaderColumns(tableId);
            var originalWidthArr = [];
            var headerWidth = myHeader.offsetWidth;
            var colWidth = 0;
            var deductedSpace = 0;
            var lastIndex = headerColumns.length - 1;
            for (var i = 0;i < headerColumns.length;i++) {
                colWidth = headerColumns[i].offsetWidth;
                deductedSpace = Math.round((scrollbarWidth / headerWidth) * (colWidth));
                if (i == lastIndex) {
                    originalWidthArr[originalWidthArr.length] = (colWidth + deductedSpace);
                }
                else {
                    originalWidthArr[originalWidthArr.length] = (colWidth - deductedSpace);
                }
            }

            myHeader.style = "display: block;width: 100%;"
            myBody.style = "display: block;max-height: " + tableHeight + "px;min-height: 30px;overflow: auto;width: 100%;";

            for (var xx = 0;xx < headerColumns.length - 1;xx++) {
                headerColumns[xx].style = "padding:0px;width:" + (originalWidthArr[xx]) + "px;"
            }

            headerColumns[lastIndex].style = "padding:0px;width:" + (originalWidthArr[lastIndex] + scrollbarWidth) + "px;"

            var bodyRows = myBody.childNodes;
            var bodyRowColumns = null;
            for (var ii = 0;ii < bodyRows.length;ii++) {
                bodyRowColumns = bodyRows[ii].childNodes;
                for (var jj = 0;jj < bodyRowColumns.length;jj++) {
                    bodyRowColumns[jj].style = "padding:0px;width:" + originalWidthArr[jj] + "px;"
                }
            }
        }
    }
}

function getTableHeaderColumns(tableId) {
    var allElements = document.getElementsByTagName('*');

    for (e = 0;e < allElements.length;e++) {
        if (allElements[e].id == tableId) {
            for (ee = e;ee < allElements.length;ee++) {
                if (allElements[ee].tagName == 'THEAD') {
                    return allElements[ee + 1].childNodes;
                }
            }
        }
    }
    return null;
}

function getNextElementByTag(tableId, targetTagName) {
    var allElements = document.getElementsByTagName('*');

    for (e = 0;e < allElements.length;e++) {
        if (allElements[e].id == tableId) {
            for (ee = e;ee < allElements.length;ee++) {
                if (allElements[ee].tagName == targetTagName) {
                    return allElements[ee];
                }
            }
        }
    }
    return null;
}

function getOffset(el) {
    var _x = 0;
    var _y = 0;
    while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
        _x += el.offsetLeft - el.scrollLeft;
        _y += el.offsetTop - el.scrollTop;
        el = el.offsetParent;
    }
    return {top : _y, left : _x};
}

function getScrollbarWidth() {
    var outer = document.createElement("div");
    outer.style.visibility = "hidden";
    outer.style.width = "100px";
    document.body.appendChild(outer);
    var widthNoScroll = outer.offsetWidth;
    outer.style.overflow = "scroll";
    var inner = document.createElement("div");
    inner.style.width = "100%";
    outer.appendChild(inner);
    var widthWithScroll = inner.offsetWidth;
    outer.parentNode.removeChild(outer);
    return widthNoScroll - widthWithScroll;
}

function fireEditButton(){
    document.getElementById("editButton").click();
}
