/*
File Content Description:this file about operations may be applied to button component such as disabling and enabling buttons based on specific conditions
*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: check edit and delete buttons are disabled in case user does not select row to be udated or row/rows to be deleted 
@param arrayId ,  the id of  checkboxe control exist in datatable
@param delBtnId , the id of delete button 
@param editBtnId , the id of edit  button 
Creation/Modification History :
   1.1 - Developer Name: Amir Nasr
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:Enhancement,to make modified/added row is more clear to user 
* 
*/
function checkForDisabled(arrayId, delBtnId, editBtnId) {
    document.getElementById('arrayId').value = arrayId;
    var pageIndex = document.getElementById('pageIndex').value;
    var noOfTableRows = document.getElementById('noOfTableRows').value;
    var checked = 0;
    var checkBoxLength = 0;
    var delButton = document.getElementById(delBtnId);
    var editButton = document.getElementById(editBtnId);
    
    for (i = 0; ; i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            } else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
                checkBoxLength = checkBoxLength - 1;
            } else if(elem.checked) {
               checked++; 
            }
        }
        checkBoxLength++;
    }
    
    var hidden = document.getElementById('disabledButtons');
    hidden.value = '';
    
    var selectedCheckBoxes = document.getElementById('selectedCheckBoxes').value;
    
    if(checked == 0 && selectedCheckBoxes == ',') {
        hidden.value = delBtnId + ',' + editBtnId;
        delButton.disabled = true;
        editButton.disabled = true;
    } else if(checked > 1 && selectedCheckBoxes == ',') {
        hidden.value = editBtnId;
        delButton.disabled = false;
        editButton.disabled = true;
    } else if(checked == 1 && selectedCheckBoxes == ',') {
        delButton.disabled = false;
        editButton.disabled = false;
    } else if(selectedCheckBoxes.split(',').length == 3) {
        delButton.disabled = false;
        editButton.disabled = false;
    } else if(selectedCheckBoxes.split(',').length > 3) {
        hidden.value = editBtnId;
        delButton.disabled = false;
        editButton.disabled = true;
    }
     
    if(checked == checkBoxLength) {
        document.getElementById('checkAll').checked = true;
    } else {
        document.getElementById('checkAll').checked = false;
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: clear selected check boxes and make delete and edit button disable
Creation/Modification History :
   1.1 - Developer Name:Amir Nasr 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function resetTableBar() {
    document.getElementById('selectedCheckBoxes').value = ',';
    document.getElementById('disabledButtons').value = 'myForm:delButton,myForm:editButton';
    document.forms['myForm'].submit();
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: check for the menus, to be disabled depend on the single selection or multi-selection,it is the same as checkForDisabled function 
@param index: the index of the menu item
Creation/Modification History :
   1.1 - Developer Name:Amir Nasr
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function checkDisableMenus(index) {
    var selectionFlag = document.getElementById('myForm:hiddenSelectionFlag_' + index) != null ? 
                        document.getElementById('myForm:hiddenSelectionFlag_' + index).value 
                        : null;
    
    if(selectionFlag != null) {
        var arrayId = document.getElementById('arrayId').value;
        if(arrayId == '') {
            return false;
        } else if(selectionFlag == 1) {
            return checkItemsSelection(arrayId, true);
        } else if(selectionFlag == 2) {
            return checkItemsSelection(arrayId, false);
        } else {
            return true;
        }
    } else {
        return false;
    }
}

/////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: check for single selection or multi-selection
@param arrayId: the name of the array that carry the checkboxes in the table
@param singleSelection: boolean var to indicate the enforce of single selection only or not
Creation/Modification History :
   1.1 - Developer Name:Amir Nasr
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function checkItemsSelection(arrayId, singleSelection) {
    var checked = false;
    var checkedCount = 0;
    var pageIndex = document.getElementById('pageIndex').value;
    var noOfTableRows = document.getElementById('noOfTableRows').value;
    var selectedCheckBoxes = document.getElementById('selectedCheckBoxes').value;
    
    for (i = 0; ; i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            } else if (elem == null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
            } else if (elem.checked) {
                if( !singleSelection) {
                    checked = true;
                    break;
                } else {
                    checkedCount++;
                    if(checkedCount > 1) {
                        checked = false;
                        break;
                    }
                    checked = true;
                }
            }
        }
    }
    
    if(checked) {
        return true;
    } else if (selectedCheckBoxes.split(',').length == 3 && singleSelection) {
        return true
    } else if (selectedCheckBoxes.split(',').length > 3 && !singleSelection) {
        return true
    }
    
    return false;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: t's handle the sorting in table-header from order menu:
@param ColumnId: Column Id that it will be sorted by
Creation/Modification History :
   1.1 - Developer Name:Amir Nasr
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function doOrderOnTable(ColumnId) {
       var link = document.getElementById('myForm:dataT_data:' + ColumnId);
       link.click();
}
////////////////////////Yassmine Part/////////////////////////////////////////////////////////////////////////////////////////


function updateTableHeader(pageType)
{
  if(pageType == '0')//DataTable
  {
    // updateButtonsStatusTable();
    
    // Waleed Badr
    //BEGIN --------------------------------------------------------------------------
    // some pages dosenot contain table header so we must check this
    if(!updateButtonsStatusTable()) {
        return;
    }
    // some pages dosenot contain JSCookieMenu so we must check this
    if(document.getElementById('myForm_myMenu_menu')==null) {
        return;
    }
    //END --------------------------------------------------------------------------
    
    updateMenuItemsStatusTable('myForm_myMenu_menu',myForm_myMenu_menu,'hbl');
  }
  else if(pageType == '1')//Tree
  { 
       updateButtonsStatusTree(null,null,null);      
       updateMenuItemsStatusTree(null,null,null,'treeform_myMenu_menu',treeform_myMenu_menu,'hbl');
  }
}


/* 
Purpose: updateMenuItemsStatusTable = calls the 'checkDisableAndEnableStatusTable' which returns  
enabled hidden fields containg the indexes.
                                 These indexes represent the action of items.
                                 Then calls changeActionAndStyle to change style and action of these 
indexes.
                                 
@param tableID=It is dataTable id in case of DataTable page
       checkBoxID=It is the id of checkbox in case of DataTable page
       menuID=The Id of menu in the page
       menu= menu object
       orient= orient of the menu like vbl..etc

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  27/2/2008
1- Creation/Modification:Creation      
*/ 

function updateMenuItemsStatusTable(menuID,menu,orient)
    {     
    var mainMenu=menu;
    if(checkDisableAndEnableStatusTable())
        {
                changeActionAndStyle("myForm",menu);
                cmDraw (menuID, menu, orient, cmBesharaTheme, 'BesharaTheme');
        }    
    }
/* 
Purpose: replaceSubstring  = replaces subString with another SubString in an inputString.
                                 
@param inputString: String in which the function will replace SubString with another SubString.
       fromString : String that will be removed from the inputString.
       toString   : String that will be replaced in the inputString.

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  5/3/2008
1- Creation/Modification:Creation      
*/        function replaceSubstring(inputString, fromString, toString) {
   // Goes through the inputString and replaces every occurrence of fromString with toString
   var temp = inputString;
   if (fromString == "") {
      return inputString;
   }
   if (toString.indexOf(fromString) == -1) { // If the string being replaced is not a part of the replacement string (normal situation)
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   } else { // String being replaced is part of replacement string (like "+" being replaced with "++") - prevent an infinite loop
      var midStrings = new Array("~", "`", "_", "^", "#");
      var midStringLen = 1;
      var midString = "";
      // Find a string that doesn't exist in the inputString to be used
      // as an "inbetween" string
      while (midString == "") {
         for (var i=0; i < midStrings.length; i++) {
            var tempMidString = "";
            for (var j=0; j < midStringLen; j++) { tempMidString += midStrings[i]; }
            if (fromString.indexOf(tempMidString) == -1) {
               midString = tempMidString;
               i = midStrings.length + 1;
            }
         }
      } // Keep on going until we build an "inbetween" string that doesn't exist
      // Now go through and do two replaces - first, replace the "fromString" with the "inbetween" string
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + midString + toTheRight;
      }
      // Next, replace the "inbetween" string with the "toString"
      while (temp.indexOf(midString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(midString));
         var toTheRight = temp.substring(temp.indexOf(midString)+midString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   } // Ends the check to see if the string being replaced is part of the replacement string or not
   return temp; // Send the updated string back to the user
} // Ends the "replaceSubstring" function
    
 /* 
Purpose: changeActionAndStyle = gets The values of enabled and allIndexes hidden fields.
                                change the style and action of the indexes in enabled hidden field  and 
the others in allIndexes to be disabled. 
                                 
@param menu= menu object

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  27/2/2008
1- Creation/Modification:Creation      
*/  


 function  changeActionAndStyle (formName,menu)
 {
   var allIDs=document.getElementById("allIndexes").value;
   var allIDsArr1=allIDs.split(",");
   for(var j=0; j< allIDsArr1.length ;j++){
     if(allIDsArr1[j] != ""){
       var allIDsArr2=allIDsArr1[j].split("-");
        if( allIDsArr2.length == 4 ){
           menu[allIDsArr2[0]][allIDsArr2[1]][allIDsArr2[2]]=null;//Action.....
           var temp=replaceSubstring(menu[allIDsArr2[0]][allIDsArr2[1]][allIDsArr2[2]-
1],"JSCookMenuEnabled","JSCookMenuDisabled");
           if( temp == menu[allIDsArr2[0]][allIDsArr2[1]][allIDsArr2[2]-1]){
                   menu[allIDsArr2[0]][allIDsArr2[1]][allIDsArr2[2]-1]=
                          "<font class='JSCookMenuDisabled'>"+menu[allIDsArr2[0]][allIDsArr2[1]]
[allIDsArr2[2]-1]+"<//font>";
              }
           else{
                   menu[allIDsArr2[0]][allIDsArr2[1]][allIDsArr2[2]-1]=temp;
             }
        }
   }
 }
 
   var targetIDs=document.getElementById("enabled").value;
   var targetIDsArr1=targetIDs.split(",");
   for(var i=0; i < targetIDsArr1.length ;i++)
     {
          if(targetIDsArr1[i] != ""){
                var targetIDsArr2=targetIDsArr1[i].split("-");
                if( targetIDsArr2.length == 4 ){
                menu[targetIDsArr2[0]][targetIDsArr2[1]][targetIDsArr2[2]]=formName+'_myMenu_menu:A]'+targetIDsArr2[3];//Action.....
                
                var temp=replaceSubstring(menu[targetIDsArr2[0]][targetIDsArr2[1]][targetIDsArr2[2]-
1],"JSCookMenuDisabled","JSCookMenuEnabled");
               if( temp == menu[targetIDsArr2[0]][targetIDsArr2[1]][targetIDsArr2[2]-1]){
                     menu[targetIDsArr2[0]][targetIDsArr2[1]][targetIDsArr2[2]-1]=
                          "<font class='JSCookMenuEnabled'>"+menu[targetIDsArr2[0]][targetIDsArr2[1]]
[targetIDsArr2[2]-1]+"<//font>";
                }
               else{
                     menu[targetIDsArr2[0]][targetIDsArr2[1]][targetIDsArr2[2]-1]=temp;
               }
          }
     }
}
}
/* 
Purpose: checkDisableAndEnableStatusTable = Determines the indexes that will be enabled or disabled.
                                       --Must Make Two Hidden Fields Named disabled and enabled in which 
this function will put the required indexes from the current case 's fields.

                                       --There is 3 cases and each case must have 2 values in casesArray 
containg the reqiured indexes to be disabled or enabled :
                                                        1) Zero Check box selected:             
casesArray[0] contains the indexes to be enabled in that case 
                                                        2) One and only one Check box selected: 
casesArray[1] contains the indexes to be enabled in that case 
                                                        3) One or More Check boxes selected:    
casesArray[2] contains the indexes to be enabled in that case 
                                       
                                       --The format of the values of these hidden fields must be sent 
like this:  menuindex-menuitemindex-2-#{beanname.actionmethod},...,.... example:   0-5-2-#
{abilitybean.listFunction},0-5-2-#{abilitybean.listFunction},                                 

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  27/2/2008
1- Creation/Modification:Creation      
*/ 
function checkDisableAndEnableStatusTable()
    {
        var selectedNumber=document.getElementById("selectedNumber").value;
        var casesArrayConcatinated=document.getElementById("casesArrayConcatinated").value;
        var casesArray=casesArrayConcatinated.split('$');
        
        
        //Waleed Badr
        //BEGIN--------------------------------------------------------------------------------------------
        //check if we have any cases, if we don't have any cases that means we don't have any menu items
        var hasCases = false;
        for(var i = 0; i < casesArray.length; ++i) {
            if(casesArray[i].length != 0) {
                hasCases = true;
                break;
            }
        }        
        if(!hasCases) {
            return;
        }
        //END--------------------------------------------------------------------------------------------

        
//        var count=0;
//        var elems= document.getElementById(tableID);
//        for (var i=0; i<elems.rows.length; i++) {
//            if(document.getElementById(checkBoxID+"["+i+"]").checked)
//            {
//                count++;
//             }
//        }
        if    (selectedNumber == 0)
            {
                document.getElementById("enabled").value=casesArray[0];
                return true;
            }
        else if(selectedNumber == 1)
            {
                document.getElementById("enabled").value=casesArray[1];
                return true;
            }
        else
            {
                document.getElementById("enabled").value=casesArray[2];
                return true;
            } 
        return false
  
}
 /* 
Purpose: updateButtonsStatusTable =determines whether the buttons to be  disabled or enabled depending 
on the number of selected checkboxex in dataTable

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  28/2/2008
1- Creation/Modification:Creation      
*/ 

function updateButtonsStatusTable(){
      //Waleed Badr
      //BEGIN------------------------------------------------------ 
      //Check if the table header exists or not
      if(document.getElementById("selectedNumber")==null) {
        //not exists
        return false;
      }
      //END------------------------------------------------------ 
      
      var selectedNumber=document.getElementById("selectedNumber").value;
       var casesArrayButtonConcatinated=document.getElementById("casesArrayConcatinatedButon").value;
       var casesArrayButton=casesArrayButtonConcatinated.split('$');
        var buttonsIDs="";
//        for (var i=0; i<elems.rows.length; i++) {
//            if(document.getElementById(checkBoxID+"["+i+"]").checked)
//            {
//                count++;
//             }
//        }
//         if  (count == 0)
//            {
//              buttonsIDs=casesArrayButton[0];
//            }
//        else if(count == 1)
//            {
//               buttonsIDs=casesArrayButton[1];
//            }
//        else
//            {
//               buttonsIDs=casesArrayButton[2];
//            } 
        //alert("selectedNumber   "+selectedNumber);
        if  (selectedNumber == 0)
            {
              buttonsIDs=casesArrayButton[0];
            }
        else if(selectedNumber == 1)
            {
               buttonsIDs=casesArrayButton[1];
            }
        else
            {
               buttonsIDs=casesArrayButton[2];
            }
       //Disable All Buttons.
        var allIndexesButton=document.getElementById("allIndexesButton").value;
        var allIndexesButtonArr=allIndexesButton.split(",");
        
        for(var i=0; i<allIndexesButtonArr.length-1; i++)
        {
            document.getElementById("myForm:"+allIndexesButtonArr[i]).disabled=true;
            changeStyleButton("myForm:"+allIndexesButtonArr[i],true);
        }
        //Enable Required Buttons
        var buttonsIDsArr=buttonsIDs.split(",");
       
        for(var j=0; j<buttonsIDsArr.length-1; j++)
        {
            document.getElementById("myForm:"+buttonsIDsArr[j]).disabled=false;
            changeStyleButton("myForm:"+buttonsIDsArr[j],false);
        }
       
       //cancelSearch  
       if(document.getElementById("myForm:cancelSearchButton")!= null && document.getElementById("searchMode").value == 'false')
        {
         document.getElementById("myForm:cancelSearchButton").disabled=true;
         changeStyleButton("myForm:cancelSearchButton",true);
         }
       else if(document.getElementById("myForm:cancelSearchButton")!= null && document.getElementById("searchMode").value == 'true' ){
        document.getElementById("myForm:cancelSearchButton").disabled=false;
        changeStyleButton("myForm:cancelSearchButton",false);
       }
       
       //Waleed Badr
       //BEGIN------------------------------------------------------ 
       //the table header exists
       return true;
       //END------------------------------------------------------ 
}

/*
Purpose: changeStyleButton = Change the style of buttons depending on its status wheter it is disable or enable .
@param formName=The Name of The Form.
       buttonID=The Id of the button which we want to change its style.
       disable=it is boolean.
              true=indicates this button should be disabled.
              false=indicates this button should be enabled.

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  17/4/2008
1- Creation/Modification:Creation      
*/ 

function changeStyleButton(buttonID,disable)
{
     var ids=buttonID.split(":");
     if(disable){
            document.getElementById(buttonID).style.backgroundImage='url("../../../app/media/images/menu_images/'+ids[1]+'_Disabled.jpg")';
     }
     else{
            document.getElementById(buttonID).style.backgroundImage='url("../../../app/media/images/menu_images/'+ids[1]+'.jpg")';
     }
}

/*
Purpose: checkDisableAndEnableStatusTree = Determines the indexes that will be enabled or disabled.
                                       --Must Make Two Hidden Fields Named disabled and enabled in which 
this function will put the required indexes from the current case 's fields.
                                       
                                       --There is 4 cases and each case must have 2 values in 
treeCasesArray containg the reqiured indexes to be disabled or enabled :
                                                        1) Leaf Case: treeCasesArray[1] contains the 
indexes to be enabled in that case 
                                                        2) Root Case: treeCasesArray[2] contains the 
indexes to be enabled in that case 
                                                        3) Parent with children: treeCasesArray[3] 
contains the indexes to be enabled in that case
                                                        4) Parent without children: treeCasesArray[4] 
contains the indexes to be enabled in that casee
                                       --The format of the values of these hidden fields must be sent 
like this:  menuindex-menuitemindex-2-#{beanname.actionmethod},...,.... example:   0-5-2-#
{abilitybean.listFunction},0-5-2-#{abilitybean.listFunction},                                 
@param leafAtt=boolean shows if the node is leaf or not.
       hasChildAtt=boolean shows if the node has children or not.
       idAtt=shows the id of node

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  27/2/2008
1- Creation/Modification:Creation      
*/ 


function checkDisableAndEnableStatusTree(leafAtt,hasChildAtt,idAtt)
    {  
         
        var treeCases = document.getElementById("casesArrayConcatinated").value;
        var treeCasesArray=treeCases.split('$');
        var menuIds="";
        if(leafAtt == null && hasChildAtt ==null && idAtt == null)
        {
           menuIds+=treeCasesArray[0];
           //alert("null   "+menuIds);
        }
        else
        {
         if(hasChildAtt == 'false')//Not Has Child
            {
                menuIds+=treeCasesArray[1];
            }
         if(idAtt != '0')//Not Root
            {
                menuIds+=treeCasesArray[2];
            }
         if(leafAtt == 'false')//Not Leaf
            {
                menuIds+=treeCasesArray[3];        
            }
        }   
      document.getElementById("enabled").value=menuIds;
      return true;          
    }

 /* 
Purpose: updateMenuItemsStatusTree = calls the 'checkDisableAndEnableStatusTable' which returns  enabled 
hidden fields containg the indexes.
                                 These indexes represent the action of items.
                                 Then calls changeActionAndStyle to change style and action of these 
indexes.
                                 
@param leaf=boolean shows if the node is leaf or not.
       hasChildt=boolean shows if the node has children or not.
       id=shows the id of node
       menuID=The Id of menu in the page
       menu= menu object
       orient= orient of the menu like vbl..etc

Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  27/2/2008
1- Creation/Modification:Creation      
*/    

function updateMenuItemsStatusTree(leaf,hasChild,id,menuID,menu,orient)
    {
    
    var mainMenu=menu;
    var action;
    if(checkDisableAndEnableStatusTree(leaf,hasChild,id))
        {
                changeActionAndStyle("treeform",menu);
                cmDraw (menuID, menu, orient, cmBesharaTheme, 'BesharaTheme');
        }
    
    }






   
/* 
Purpose: updateButtonsStatusTree = determines whether the add,edit and delete buttons to be  disabled or 
enabled depending on the node state root, paernt with children, parent without children and leaf
               leaf  :delete and edit buttons enabled
               parent:-with children : add and edit buttons enabled
                     :-without children : add, delete and edit buttons enabled   
               root  :add button enabled
@param leaf=boolean shows if the node is leaf or not.
       hasChild=boolean shows if the node has children or not.
       id=shows the id of node
      
Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  20/2/2008
1- Creation/Modification:Creation      
*/ 
function updateButtonsStatusTree(leaf,hasChild,id){
      var casesArrayButtonConcatinated=document.getElementById("casesArrayConcatinatedButon").value;
       var casesArrayButton=casesArrayButtonConcatinated.split('$');
       var buttonsIDs="";
       if(leaf == null && hasChild == null && id == null)
        {
           buttonsIDs+=casesArrayButton[0];
        //   alert("null   "+buttonsIDs);
        }
        else{
       
        if(hasChild == 'false')//Not Has Child
         {
            buttonsIDs+=casesArrayButton[1];
         }
        if(id != '0')//Not Root
        {
        buttonsIDs+=casesArrayButton[2];
        }
       if(leaf == 'false')//Not Leaf
        {
          buttonsIDs+=casesArrayButton[3];
        }
      }
        //Disable All Buttons.
        var allIndexesButton=document.getElementById("allIndexesButton").value;
        var allIndexesButtonArr=allIndexesButton.split(",");
        
        for(var i=0; i<allIndexesButtonArr.length-1; i++)
        {
            document.getElementById("treeform:"+allIndexesButtonArr[i]).disabled=true;
            changeStyleButton("treeform:"+allIndexesButtonArr[i],true);
        }
        //Enable Required Buttons
        var buttonsIDsArr=buttonsIDs.split(",");
       
        for(var j=0; j<buttonsIDsArr.length-1; j++)
        {
            document.getElementById("treeform:"+buttonsIDsArr[j]).disabled=false;
            changeStyleButton("treeform:"+buttonsIDsArr[j],false);
        }
        
        
       //cancelSearch  
       if(document.getElementById("searchMode").value == 'false')
        {
         document.getElementById("treeform:cancelSearchButton").disabled=true;
         changeStyleButton("treeform:cancelSearchButton",true);
         }
       else{
        document.getElementById("treeform:cancelSearchButton").disabled=false;
         changeStyleButton("treeform:cancelSearchButton",false);
       }
}






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////