//================================================ Start of Global Functions================================================
changeVisibilityMsg();
changeNavigationMenuStatus();
//================================================ End of Global Functions================================================

/////////////////////////////////Start of Message confirmation Div for exceptions or success executing transaction///////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:method used to show information message as Div after specific operation ,for example after adding new item ,confirmation message will appear inform user that addition has been done succsessfully  
Creation/Modification History :
   1.1 - Developer Name: Ahamed Abdelfatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
  //////////////////////////////////////////////////////////////////////////////
   1.1 - Developer Name: Ahamed Abdelfatah
   1.2 - Date: 26/2/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:change the id of 'successMsg' textBox from msg to 'successMsg'
   //////////////////////////////////////////////////////////////////////////////
   1.1 - Developer Name: Nora Ismail
   1.2 - Date: 26/2/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:generalizing the method
* 
*/

function changeVisibilityMsg()
{
   if(document.getElementById('successMsg') != null && document.getElementById('successMsg').value != '')
   {
      changeVisibilityMsgDiv(window.divMsg);
      settingFoucsAtDivMsg();
       i = 0;
   }
   if(document.getElementById('ErrMsg') != null && document.getElementById('ErrMsg').value != '')
   {
       changeVisibilityDiv(window.blocker,window.divMsg);
       settingFoucsAtDivMsg();
       i = 0;
   }
   enableMsgScript();
}

 //added by 3laa Elmasry to solve javaScript error appear when invoke changeVisibilityMsg
function enableMsgScript() {
        var msg_div = document.getElementById('divMsg');
        if(msg_div!= null){
            msg_div.onmouseover=function(){document.getElementById('msgcloseXX').style.visibility="visible";};
            msg_div.onmouseout=function(){document.getElementById('msgcloseXX').style.visibility="hidden";};    
        }
    }


function changeVisibilityMsgDiv(currentDivID)
{
     var currentID = currentDivID.id;
     var div = document.getElementById(currentID);
     var timeoutPeriod = document.getElementById('timeout_period').value;
     div.style.visibility="visible";
     currentOpenedDivId = currentID;
     if(isVisibleComponent(div) && timeoutPeriod != 0){
        setTimeout(function(){fadeOut(div); setTimeout(function(){clearDivMsg();},1000); },timeoutPeriod);
     }
     
}



function fadeOut(div){
    var duration = 10;
    // how many times should it should be changed in delay duration
    var AmountOfActions=85;
//    div.style.opacity = 0.85; 
//    div.style.filter = 'alpha(opacity=85);'
//    div.style.visibility="visible";    
    var count=0;
    var ratio = 0;
    setInterval(function(){
        count ++;
       if ( count<AmountOfActions) { 
        ratio = AmountOfActions - count;
        div.style.opacity = ratio / 100;
        div.style.filter = 'alpha(opacity='+ ratio / 100 + ')';
        }else{
        div.style.visibility="hidden";
        }
    },duration);
}


//////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: This function make specific Div visible
@Param blokerID:large Div ID that contains another Div for add/edit/alert/confirm
@Param currentDivID :Add/Edit/Alert/Confirm Div appear inside bloker Div 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:28/2/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   //////////////////////////////////////////////////////////////
   Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:2/3/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:Generalizing these methods
* 
*/

var closeDivButtonId = null;
var currentOpenedDivId = null;
function changeVisibilityDiv(blokerID,currentDivID,closeButtonId)
{
    try{
        obj = document.getElementById('iFrame');
        if(obj.style.visibility != 'visible'){
            obj.style.visibility = 'visible';
            //blokerID.style.visibility="visible"
            if(navigator.userAgent.toLowerCase().indexOf('firefox') > -1 || navigator.userAgent.toLowerCase().indexOf('chrome') > -1)
            {
                 var currentID = currentDivID.id;
                 var div = document.getElementById(currentID);
                 div.style.visibility="visible" 
                 //Do Firefox-related activities
            }
            else{
                currentDivID.style.visibility="visible";    
            }
            
            //by Ashraf Gaber to focuse at custom search div component
            if(currentDivID == window.divSearch){
                settingFoucsAtSearchDiv();
            }
            //by Ashraf Gaber to update del confirm div icon
            if(currentDivID == window.delConfirm){
                updateDelConfirmIcon();
            }
            i = 0;
            if(window.parent.nav_btn != null)
                window.parent.nav_btn.disabled=false;
            else 
                window.top.nav_btn.disabled=false; 
                // fadeDiv(blokerID,currentDivID);
            closeDivButtonId = closeButtonId;
            currentOpenedDivId = currentDivID;
        }
    }catch(exception) {
    ;
    }
}

function hideCustomDiv() {

    if(closeDivButtonId) {
        var closeDivButton;
        if(typeof closeDivButtonId == 'string'){
            closeDivButton = document.getElementById(closeDivButtonId);
        }else{
            closeDivButton = closeDivButtonId;
        }
        closeDivButton.click();
        closeDivButton = null;
    } else {
        if(document.getElementById('backButtonCustomDiv1') != null){
            document.getElementById('backButtonCustomDiv1').click();
        }
        if(document.getElementById('backButtonCustomDiv2') != null){
            document.getElementById('backButtonCustomDiv2').click();
        }
        if(document.getElementById('backButtonCustomDiv3') != null){
            document.getElementById('backButtonCustomDiv3').click();
        }
    }
}

//by Islam Omar 01-07-2014
function hideIntegrationDiv() {

    if(closeDivButtonId) {
        var closeDivButton;
        if(typeof closeDivButtonId == 'string'){
            closeDivButton = document.getElementById(closeDivButtonId);
        }else{
            closeDivButton = closeDivButtonId;
        }
        closeDivButton.click();
        closeDivButton = null;
    } else {
        if(document.getElementById('backButtonIntegrationDiv1') != null){
            document.getElementById('backButtonIntegrationDiv1').click();
        }
        if(document.getElementById('backButtonIntegrationDiv2') != null){
            document.getElementById('backButtonIntegrationDiv2').click();
        }
    }
}

function settingFoucsAtSearchDiv(){
    if(document.getElementById('searchString') != null){        
        document.getElementById('searchString').focus();
	document.getElementById('searchString').focus();
    }
}

function settingFoucsAtListPage(){
    if(document.getElementById('addButton') != null){
        document.getElementById('addButton').focus();
        document.getElementById('addButton').focus();
    }
}

function settingFoucsAtDivAdd(firstInputId){
    if(document.getElementById(firstInputId) != null){        
        document.getElementById(firstInputId).focus();
        document.getElementById(firstInputId).focus();        
    }
    else
    if(document.getElementById('add_first_inputTxt') != null){        
        document.getElementById('add_first_inputTxt').focus();
        document.getElementById('add_first_inputTxt').focus();        
    }
    //setTimeout("foucsFieldAtDivAdd();",1000);
}

function settingFoucsAtDivEdit(firstInputId){
    if(document.getElementById(firstInputId) != null){        
        document.getElementById(firstInputId).focus();
        document.getElementById(firstInputId).focus();        
    }
    else
    if(document.getElementById('edit_first_inputTxt') != null){        
        document.getElementById('edit_first_inputTxt').focus();
        document.getElementById('edit_first_inputTxt').focus();        
    }
}

function settingFoucsAtDivDelete(){
    if(document.getElementById('CancelButtonDelAlertDiv') != null){
        document.getElementById('CancelButtonDelAlertDiv').focus();
    }
}

function settingFoucsAtTreeDivDelete(){
    if(document.getElementById('CancelButtonTreeDelAlertDiv') != null){
        document.getElementById('CancelButtonTreeDelAlertDiv').focus();
    }
}

function settingFoucsAtDivDeleteConfirm(){

    if(document.getElementById('CancelButtonDelConfirmDiv') != null){
        document.getElementById('CancelButtonDelConfirmDiv').focus();
    }
}

function settingFoucsAtCancelButtonDelAlert(){
    if(document.getElementById('CancelButtonDelAlertDiv') != null){
        document.getElementById('CancelButtonDelAlertDiv').focus();
    }
}


function settingFoucsAtCancelButtonTreeDelAlert(){
    if(document.getElementById('CancelButtonTreeDelAlertDiv') != null){
        document.getElementById('CancelButtonTreeDelAlertDiv').focus();
    }
}

function settingFoucsAtDivSearch(){
    if(document.getElementById('search_first_inputTxt') != null){
        document.getElementById('search_first_inputTxt').focus();
        document.getElementById('search_first_inputTxt').focus();
    }
}

function settingFoucsAtLovDiv(){
    if(isVisibleComponent('divLov') && document.getElementById('lov_searchText') != null){
        document.getElementById('lov_searchText').focus();
        document.getElementById('lov_searchText').focus();
    }
}

function settingFoucsAtEmpLovDiv(){
    if((isVisibleComponent('lovEmp') || isVisibleComponent('lovEmpPaging')) && document.getElementById('civil_div_searchText') != null){
        document.getElementById('civil_div_searchText').focus();
        document.getElementById('civil_div_searchText').focus();
    }
}
function settingFocusAfterSearchAtEmpLov(){
    if(document.getElementById('myForm:civil_div_cancelSearch') != null) {
        document.getElementById('myForm:civil_div_cancelSearch').focus();
        document.getElementById('myForm:civil_div_cancelSearch').focus();
    }else{
        settingFoucsAtEmpLovDiv();
        document.getElementById('civil_div_searchText').select();
    }
}

function settingFoucsAtDivMsg(){
    if(document.getElementById('jsfBase_msgDiv_backBtn') != null){
            document.getElementById('jsfBase_msgDiv_backBtn').focus();
    }
}

function settingFoucsAtTreeDiv(){
    if(document.getElementById('searchText') != null){
        document.getElementById('searchText').focus();
    }
}

function settingFoucsAtDivAddMany(){
    if(isVisibleComponent('lookupAddDiv') && document.getElementById('searchText') != null){
        document.getElementById('searchText').focus();
        document.getElementById('searchText').focus();
    }
}

function settingFoucsAtDecJoinDiv(){
    if(document.getElementById('integratetype_list') != null){
        document.getElementById('integratetype_list').focus();
        document.getElementById('integratetype_list').focus();
    }
}

function settingFoucsAtRelatedDecListDiv(){
    if(isVisibleComponent('customDiv2') && document.getElementById('backButtonCustomDiv2') != null){
        document.getElementById('backButtonCustomDiv2').focus();
        document.getElementById('backButtonCustomDiv2').focus();
    }
}

function settingFoucsAtRelatedConditionsDiv(){
    if(document.getElementById('relatedConditionsBackBtn') != null){
        document.getElementById('relatedConditionsBackBtn').focus();
        document.getElementById('relatedConditionsBackBtn').focus();
    }
}

function settingFoucsAtOrgIntgDiv(){
    if(document.getElementById('orgIntgFirstInputTextId') != null){
        document.getElementById('orgIntgFirstInputTextId').focus();
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: changes the background of parent window when Add/Edit/Alert/Confirm Div appear
@Param blokerID:large Div ID that contains another Div for add/edit/alert/confirm
@Param currentDivID :Add/Edit/Alert/Confirm Div appear inside bloker Div 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:28/2/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
    //////////////////////////////////////////////////////////////
   Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:2/3/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:Generalizing these methods
* 
*/
function fadeDiv(blokerID,currentDivID){

       i+=7;
       //blokerID.style.filter="alpha(opacity="+i+++")"
       currentDivID.style.filter="alpha(opacity="+(i++)*2+")"
    
     // alert("currentDivID.style.filterr ="+currentDivID.style.filter);
       if(i<60) setTimeout(fadeDiv(blokerID,currentDivID),5);
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:hide any Add/Edit/Alert/Confirm Div when user press back button
@Param blokerID:large Div ID that contains another Div for add/edit/alert/confirm
@Param currentDivID :Add/Edit/Alert/Confirm Div appear inside bloker Div 
@Param DTONameID: represent field name id that appear in Add/Edit Div only and its value in other cases (alert,confirm)is  null 
@Param errorMessageID:represent error message text box id that appear in Add/Edit Div only and its value in other cases (alert,confirm)is  null 
Creation/Modification History :
   1.1 - Developer Name: Ahamed Abdelfatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
    //////////////////////////////////////////////////////////////
   Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:2/3/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:Generalizing these methods
   //////////////////////////////////////////////////////////////
   Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:12/3/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:make it suitable for all look ups by passing all fields that will be cleared when user press cancel 
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function hideLookUpDiv(blokerID,currentDivID,fieldsIds,errorMessageID,operation){
    if(blokerID == null){
        blokerID = window.blocker;
    }
    if(currentDivID == null){
        currentDivID = currentOpenedDivId;
    }
    currentDivID.style.visibility="hidden";
//   alert(currentDivID);
    var fieldArrayIds="";
   
    if(fieldsIds != null){
        fieldArrayIds=fieldsIds.split(',');
    }
    
    if(fieldArrayIds.length==1){
       if(document.getElementById(fieldsIds) != null)
       document.getElementById(fieldsIds).value = '';
    }
    else if(fieldArrayIds.length > 1) {
    
      for(var j=0; j<fieldArrayIds.length; j++){
       if(document.getElementById(fieldArrayIds[j])!=null)
              document.getElementById(fieldArrayIds[j]).value = '';
      }
    
      if(document.getElementById(errorMessageID) != null)
         document.getElementById(errorMessageID).value='';
             
    }
    
    try{
        obj = document.getElementById('iFrame');
        obj.style.visibility = 'hidden';
    } 
    catch(exception){
        
    }
    
    try{
        if(window.parent.nav_btn != null)
            window.parent.nav_btn.disabled=false;
        else 
            window.top.nav_btn.disabled=false;
    } 
    catch(exception){
        
    }
    
     
}

/////////////////////////////////////////Start of Tree Div ////////////////////////////////////////////////////////////////////////////

/* 
Purpose:handle Tree Operation Add/Edit by showing Div for each operation
@Param divTreeDetailID:Div ID  that displays Details of current selected Node
@Param blokerDivID :large Div ID that contains other Appeared Div
@Param treeDivID:Div ID that will be displayed in case add/edit operation
Creation/Modification History :
   1.1 - Developer Name:Ahmed Abd El-fatah
   1.2 - Date:  3/1/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   ///////////////////////////////////////////////////////
   1.1 - Developer Name:Ahmed Abd El-fatah
   1.2 - Date:  5/3/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:handling Add and Edit operations as divs not popup nor normal page
   ///////////////////////////////////////////////////////
   1.1 - Developer Name:Nora Ismail
   1.2 - Date:  5/3/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:generalizing method as make all passed parameters Dynamic
*/
 function  treeOperation(divTreeDetailID,blokerDivID,treeDivID) 
   {
       if(divTreeDetailID != null)
           divTreeDetailID.style.visibility="hidden";
          
          changeVisibilityDiv(blokerDivID,treeDivID);
           /*blokerDivID.style.visibility="visible"
           treeDivID.style.visibility="visible"*/
     
   }
 /////////////////////////////////////////////////////////////////////////////////////////
 /* 
Purpose:hide Add/Edit Tree Div
@Param operation:it is Add or Edit ,it represents Operation that will be appplied
@Param blokerDivID :large Div ID that contains other Appeared Div
@Param treeDivID:Div ID that will be displayed in case edit operation
@Param successMessageTreeID:Id of label that show sucees message after success save Operation,this parameter used only in add operation 
and its value is null in case of edit operation
Creation/Modification History :
   1.1 - Developer Name:Ahmed Abd El-fatah
   1.2 - Date:  5/3/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
 /////////////////////////////////////////////////////
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 5/3/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
 
*/

 function hidTreeDiv(operation,blokerDivID,treeDivID,successMessageTreeID)
 {
 
      /*if(operation == 'add' && document.getElementById(successMessageTreeID) != null)
        window.location.reload();*/
       if(navigator.userAgent.toLowerCase().indexOf('firefox') > -1 || navigator.userAgent.toLowerCase().indexOf('chrome') > -1)
            {
                 var currentID = treeDivID.id;
                 var div = document.getElementById(currentID);
                  hideLookUpDiv(blokerDivID,div,null,null,null);
                 //Do Firefox-related activities
            }
            else{
                 hideLookUpDiv(blokerDivID,treeDivID,null,null,null);
            }
          
 }
 
//////////////////////////////////////////////////////////////////////////////////////////
 /* 
Purpose:Show Detail of current selected node  at the same level 
@Param object:current selected Node
@Param nodeLeaf :boolean to indicate if selected node is leaf or not 
@Param nodeCode: code of selected Node
@Param nodeName:Name of selected Node
for example:showDivTreeDetails(this,'#{node.booleanLeaf}','#{node.treeId}','#{node.description}');"
Creation/Modification History :
   1.1 - Developer Name:Ahmed Abd El-fatah
   1.2 - Date:  6/3/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
 
*/

function showDivTreeDetails(object,nodeCode,treeDivID)
{
   if(nodeCode == 0)
   {
      treeDivID.style.visibility='hidden'; 
   }
   else
   {
      treeDivID.style.visibility='visible'; 
   }
   //treeDivID.style.top=findPosY(object);
   getNode(nodeCode,'theSelectedNodeId'); 
   reStyle(object,'treeList','label'); 
}

function changeNavigationMenuStatus(){
    
    try{
        var obj = document.getElementById('iFrame');
        var navBtn = null;
        if(window.parent.nav_btn != null){
            navBtn = window.parent.nav_btn;
        }else {
            navBtn = window.top.nav_btn;
        }
         if(obj != null && obj.style.visibility == 'visible'){
            navBtn.disabled=true;
         }else   {
           navBtn.disabled=false;
         }
    }catch(exception) {
    }
    
}

/***********************************************************************************************************************
 * Used to check if the confirmation div after deletion operation contains any faild operation, it will change the 
 * title icon to be as a warning
 * 
 * by Ashraf Gaber
 */
function updateDelConfirmIcon(){
    try{
        var allElements=document.getElementsByTagName('*');
        var delConfirmElements=[];
        for (e=0; e < allElements.length; e++){
            if(allElements[e].id == 'delConfirm'){
                for (ee = e; ee < allElements.length; ee++){
                    delConfirmElements[delConfirmElements.length]=allElements[ee];
                    if(allElements[ee].id == 'CancelButtonDelConfirmDiv'){
                        break;
                    }
                }
                break;
            }
        }
        var doIt = false;
        var allFailed = true;
        var oneFailed = false;
        for (e=0; e < delConfirmElements.length; e++){
            if(delConfirmElements[e].className == 'sucessMsg2'){
                doIt = true;
                allFailed = false;
            } else if(delConfirmElements[e].className == 'errMsg'){
                doIt = true;
                oneFailed = true;
            }
        }
        if(doIt){
            for (e = 0; e < delConfirmElements.length; e++){
                if(delConfirmElements[e].className == 'successMsg msg'){
                    if(allFailed || oneFailed){
                        //delConfirmElements[e].className = "errorMsg msg";
                        delConfirmElements[e].className = "msg warning";
                    }/* else if(oneFailed){
                        delConfirmElements[e].className = "msg warning";
                    }*/
                    break;
                }
            }
        }
    }catch(e){}
}

function hidConditionActivationIntgDiv()
 {
 
      /*if(operation == 'add' && document.getElementById(successMessageTreeID) != null)
        window.location.reload();*/
       if(navigator.userAgent.toLowerCase().indexOf('firefox') > -1 || navigator.userAgent.toLowerCase().indexOf('chrome') > -1)
            {
                 var divId = "conditionActivationIntgDiv";
                 var div = document.getElementById(divId);
                  hideLookUpDiv(null,div,null,null,null);
                 //Do Firefox-related activities
            }
            else{
                 hideLookUpDiv(null,div,null,null,null);
            }
          
 }
