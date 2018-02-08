/*
 * By: Ashraf Gaber
 * Date:  Feb, 25, 2009
 * Ref: "http://www.openjs.com/scripts/events/keyboard_shortcuts/", Version : 2.01.A
 */
shortcut = {
	'all_shortcuts':{},//All the shortcuts are stored in this array
	'add': function(shortcut_combination,callback,opt) {
		//Provide a set of default options
		var default_options = {
			'type':'keydown',
			'propagate':false,
			'disable_in_input':false,
			'target':document,
			'keycode':false
		}
		if(!opt) opt = default_options;
		else {
			for(var dfo in default_options) {
				if(typeof opt[dfo] == 'undefined') opt[dfo] = default_options[dfo];
			}
		}

		var ele = opt.target
		if(typeof opt.target == 'string') ele = document.getElementById(opt.target);
		var ths = this;
		shortcut_combination = shortcut_combination.toLowerCase();

		//The function to be called at keypress
		var func = function(e) {
			e = e || window.event;
			
			if(opt['disable_in_input']) { //Don't enable shortcut keys in Input, Textarea fields
				var element;
				if(e.target) element=e.target;
				else if(e.srcElement) element=e.srcElement;
				if(element.nodeType==3) element=element.parentNode;

				if(element.tagName == 'INPUT' || element.tagName == 'TEXTAREA') return;
			}
	
			//Find Which key is pressed
			if (e.keyCode) code = e.keyCode;
			else if (e.which) code = e.which;
			var character = String.fromCharCode(code).toLowerCase();
			
			if(code == 188) character=","; //If the user presses , when the type is onkeydown
			if(code == 190) character="."; //If the user presses , when the type is onkeydown
	
			var keys = shortcut_combination.split("+");
			//Key Pressed - counts the number of valid keypresses - if it is same as the number of keys, the shortcut function is invoked
			var kp = 0;
			
			//Work around for stupid Shift key bug created by using lowercase - as a result the shift+num combination was broken
			var shift_nums = {
				"`":"~",
				"1":"!",
				"2":"@",
				"3":"#",
				"4":"$",
				"5":"%",
				"6":"^",
				"7":"&",
				"8":"*",
				"9":"(",
				"0":")",
				"-":"_",
				"=":"+",
				";":":",
				"'":'"',
				",":"<",
				".":">",
				"/":"?",
				"\\":"|"
			}
			//Special Keys - and their codes
			var special_keys = {
				'esc':27,
				'escape':27,
				'tab':9,
				'space':32,
				'return':13,
				'enter':13,
				'backspace':8,
	
				'scrolllock':145,
				'scroll_lock':145,
				'scroll':145,
				'capslock':20,
				'caps_lock':20,
				'caps':20,
				'numlock':144,
				'num_lock':144,
				'num':144,
				
				'pause':19,
				'break':19,
				
				'insert':45,
				'home':36,
				'delete':46,
				'end':35,
				
				'pageup':33,
				'page_up':33,
				'pu':33,
	
				'pagedown':34,
				'page_down':34,
				'pd':34,
	
				'left':37,
				'up':38,
				'right':39,
				'down':40,
	
				'f1':112,
				'f2':113,
				'f3':114,
				'f4':115,
				'f5':116,
				'f6':117,
				'f7':118,
				'f8':119,
				'f9':120,
				'f10':121,
				'f11':122,
				'f12':123
			}
	
			var modifiers = { 
				shift: { wanted:false, pressed:false},
				ctrl : { wanted:false, pressed:false},
				alt  : { wanted:false, pressed:false},
				meta : { wanted:false, pressed:false}	//Meta is Mac specific
			};
                        
			if(e.ctrlKey)	modifiers.ctrl.pressed = true;
			if(e.shiftKey)	modifiers.shift.pressed = true;
			if(e.altKey)	modifiers.alt.pressed = true;
			if(e.metaKey)   modifiers.meta.pressed = true;
                        
			for(var i=0; k=keys[i],i<keys.length; i++) {
				//Modifiers
				if(k == 'ctrl' || k == 'control') {
					kp++;
					modifiers.ctrl.wanted = true;

				} else if(k == 'shift') {
					kp++;
					modifiers.shift.wanted = true;

				} else if(k == 'alt') {
					kp++;
					modifiers.alt.wanted = true;
				} else if(k == 'meta') {
					kp++;
					modifiers.meta.wanted = true;
				} else if(k.length > 1) { //If it is a special key
					if(special_keys[k] == code) kp++;
					
				} else if(opt['keycode']) {
					if(opt['keycode'] == code) kp++;

				} else { //The special keys did not match
					if(character == k) kp++;
					else {
						if(shift_nums[character] && e.shiftKey) { //Stupid Shift key bug created by using lowercase
							character = shift_nums[character]; 
							if(character == k) kp++;
						}
					}
				}
			}

			if(kp == keys.length && 
						modifiers.ctrl.pressed == modifiers.ctrl.wanted &&
						modifiers.shift.pressed == modifiers.shift.wanted &&
						modifiers.alt.pressed == modifiers.alt.wanted &&
						modifiers.meta.pressed == modifiers.meta.wanted) {
				callback(e);
	
				if(!opt['propagate']) { //Stop the event
					//e.cancelBubble is supported by IE - this will kill the bubbling process.
					e.cancelBubble = true;
					e.returnValue = false;
                                        e.keyCode = 0;
	
					//e.stopPropagation works in Firefox.
					if (e.stopPropagation) {
						e.stopPropagation();
						e.preventDefault();
					}
					return false;
				}
			}
		}
		this.all_shortcuts[shortcut_combination] = {
			'callback':func, 
			'target':ele, 
			'event': opt['type']
		};
		//Attach the function with the event
		if(ele.addEventListener) ele.addEventListener(opt['type'], func, false);
		else if(ele.attachEvent) ele.attachEvent('on'+opt['type'], func);
		else ele['on'+opt['type']] = func;
	},

	//Remove the shortcut - just specify the shortcut and I will remove the binding
	'remove':function(shortcut_combination) {
		shortcut_combination = shortcut_combination.toLowerCase();
		var binding = this.all_shortcuts[shortcut_combination];
		delete(this.all_shortcuts[shortcut_combination])
		if(!binding) return;
		var type = binding['event'];
		var ele = binding['target'];
		var callback = binding['callback'];

		if(ele.detachEvent) ele.detachEvent('on'+type, callback);
		else if(ele.removeEventListener) ele.removeEventListener(type, callback, false);
		else ele['on'+type] = false;
	}
}

//TODO Start is here
//************************* Helper Methods *************************************
/* 
Purpose: Check the visibility for a specific component
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function isVisibleComponent(componentId){
    var component;
    if(typeof componentId == 'string'){
        component = document.getElementById(componentId);
    }else{
        component = componentId;
    }
    if(component != null && component.style.visibility == 'visible'){
        return true;
    }
    return false;
}

/* 
Purpose: Fire a command action by calling the click method
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function fireCommandAction(commandId) {
    var commandAction;
    if(typeof commandId == 'string'){
        commandAction = document.getElementById(commandId);
        if(commandAction == null){
            commandAction = window.parent.document.getElementById(commandId);
        }
    }else{
        commandAction = commandId;
    }
    
    try{
        hideNavigationMenu();
    }catch(exception){
    
    }
    if(commandAction != null){
        commandAction.click();
    }
    
}
//******************************************************************************
/* 
Purpose: Setup the shortcut options
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function handleApplicationShortcuts() {
    
    var parent_shortcut_options = {
            'type':'keydown',
            'propagate':false,
            'disable_in_input':false,
            'target':window.parent.document,
            'keycode':false
    };    
    addApplicationShortcuts(parent_shortcut_options);

    var shortcut_options = {
            'type':'keydown',
            'propagate':false,
            'disable_in_input':false,
            'target':document,
            'keycode':false
    };
    addApplicationShortcuts(shortcut_options);
}
handleApplicationShortcuts();

/* 
Purpose: Add a new shortcut
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function addApplicationShortcuts(options) {
    shortcut.add('esc', doEscAction, options);
    shortcut.add('f11', openSearchDiv, options);
    shortcut.add('ctrl+up', openDeleteDiv, options);
    shortcut.add('ctrl+Down', openAddDiv, options); // Amr Abdo 20-4-2009
    shortcut.add('ctrl+e', openEditDiv, options); // Amr Abdo 20-4-2009
    shortcut.add('ctrl+h', openHelpPage, options);
    shortcut.add('f4', logoutAction, options);
    shortcut.add('ctrl+home', goToHomePage, options);
    shortcut.add('f10', cancelSearchProcess, options);//a.beltagy 17-05-2009
    shortcut.add('ctrl+s', saveAndNew, options);//a.beltagy 17-05-2009
    shortcut.add('ctrl+k', openShortcut, options);//open shourtcut div
    shortcut.add('ctrl+o', openTreeDivDetal, options);//open TreeDivDetail div
    shortcut.add('ctrl+u', closeTreeDivDetal, options);//close TreeDivDetail div
    shortcut.add('ctrl+p', collapseExpandTreeNode, options);//Amr Abdo (( open shourtcut div ))
   
   ///////////////////new short cut to tree ////////////////////////////
    shortcut.add('ctrl+f', tabInTreeSearchForward, options);
    shortcut.add('ctrl+g', tabInTreeSearchBackward, options);
    ////////////////////////////////////////////////////////////////
    
}

/* 
Purpose: Handle the Esc button action
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function doEscAction(){  
    var divAndBackButonArray = new Array();
    var index = 0;
    // defined at Div.js
    if(typeof (currentOpenedDivId) != 'undefined' && currentOpenedDivId != null && typeof (closeDivButtonId) != 'undefined' && closeDivButtonId != null ){
        divAndBackButonArray [divAndBackButonArray.length] = [currentOpenedDivId,closeDivButtonId];
    }
    divAndBackButonArray [divAndBackButonArray.length] = ["divMsg","jsfBase_msgDiv_backBtn"];
    divAndBackButonArray [divAndBackButonArray.length] = ["lovEmp","backButtonEmpLovDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["lovEmpPaging","backButtonEmpLovDivPaging"];
    divAndBackButonArray [divAndBackButonArray.length] = ["divLov","lov_cancel"];
    divAndBackButonArray [divAndBackButonArray.length] = ["divTree","backButtonTreeDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["shortcutDiv","backshortCutButton"];
    divAndBackButonArray [divAndBackButonArray.length] = ["delAlert","CancelButtonDelAlertDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["delConfirm","CancelButtonDelConfirmDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["divTreeAdd","backButtonTreeAddDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["divTreeEdit","CancelButtonTreeEditDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["delAlertTree","CancelButtonTreeDelAlertDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["masterDetailDiv","BackButtonMasterDetailDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["divSearch","customSearchBackBtn"];
    divAndBackButonArray [divAndBackButonArray.length] = ["customDiv1","backButtonCustomDiv1"];
    divAndBackButonArray [divAndBackButonArray.length] = ["customDiv2","backButtonCustomDiv2"];
    divAndBackButonArray [divAndBackButonArray.length] = ["customDiv3","backButtonCustomDiv3"];
    divAndBackButonArray [divAndBackButonArray.length] = ["integrationDiv1","backButtonIntegrationDiv1"];
    divAndBackButonArray [divAndBackButonArray.length] = ["integrationDiv2","backButtonIntegrationDiv2"];
    divAndBackButonArray [divAndBackButonArray.length] = ["lookupAddDiv","backButtonAddDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["lookupEditDiv","CancelButtonEdit"];
    divAndBackButonArray [divAndBackButonArray.length] = ["navigationDiv","BackButtonManyToMany"];
    divAndBackButonArray [divAndBackButonArray.length] = ["pagedDivTree","backButtonPagedTreeDiv"];
    divAndBackButonArray [divAndBackButonArray.length] = ["wizardButtonsDiv","BackButtonManyToMany"];
    divAndBackButonArray [divAndBackButonArray.length] = ["shortCutDivPage","backshortCutButton"];
    divAndBackButonArray [divAndBackButonArray.length] = ["OperationBar","OperationBarBackButton"];
    
    for(var i=0;i<divAndBackButonArray.length;i++){
        
        if(isVisibleComponent(divAndBackButonArray[i][0])){
            fireCommandAction(divAndBackButonArray[i][1]);
            break;
        }
    }

    
}


/* 
Purpose: Handle the f11 action [Open the search div]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function openSearchDiv(){
    if(!isVisibleComponent('iFrame')){
        fireCommandAction('searchButton');
    }
}

/* 
Purpose: Handle the ctrl+f11 action [do the search div action]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 26, 2009
*/
function doSearchAction(){
    if(isVisibleComponent('divSearch')){
        fireCommandAction('customSearchBtn');
    }
}

/* 
Purpose: Handle the ctrl+up action [Open the delete div]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function openDeleteDiv(){
    if(!isVisibleComponent('iFrame')){
        fireCommandAction('deleteButton');
    }
}

/* 
Purpose: Handle the ctrl+n action [Open the Add div]
Creation/Modification History :
   1.1 - Developer Name: Amr Abdo
   1.2 - Date:  Apr, 20, 2009
*/
function openAddDiv(){
   if(!isVisibleComponent('iFrame')){
       if(document.getElementById('addButton') != null){
           fireCommandAction('addButton');
           
       } else if(document.getElementById('addButtonTree') != null){
           fireCommandAction('addButtonTree');
       }
   }
}

/* 
Purpose: Handle the ctrl+e action [Open the Edit div]
Creation/Modification History :
   1.1 - Developer Name: Amr Abdo
   1.2 - Date:  Apr, 20, 2009
*/
function openEditDiv(){
    if(!isVisibleComponent('iFrame')){
        fireCommandAction('editButton');
    }
}

/* 
Purpose: Handle the ctrl+h action [Open the help page]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function openHelpPage(){
    fireCommandAction('HelpButton');
}

/* 
Purpose: Handle the f4 action [logout]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function logoutAction(){
    fireCommandAction('LogoutButton');
}

/* 
Purpose: Handle the home action [go to home page]
Creation/Modification History :
   1.1 - Developer Name: Ashraf Gaber
   1.2 - Date:  Feb, 25, 2009
*/
function goToHomePage(){
    fireCommandAction('HomePageButton');
}

/* 
Purpose: Handle the f10 action [cancel search process]
Creation/Modification History :
   1.1 - Developer Name: a.beltagy
   1.2 - Date:  May, 17, 2009
*/
function cancelSearchProcess(){
    if(!isVisibleComponent('iFrame')){
        fireCommandAction('cancelSearchButton');
    }
}

/* 
Purpose: Handle the ctrl+s action [to make save and new operation]
Creation/Modification History :
   1.1 - Developer Name: a.beltagy
   1.2 - Date:  May, 17, 2009
*/
function saveAndNew(){
    fireCommandAction('SaveNewButton');
}


/*function to open shortcut div*/
function openShortcut(){
    changeVisibilityDiv(window.blocker,window.shortcutDiv);
    return false;
}


/*function to open TreeDivDetail div*/
function openTreeDivDetal(){ 
    if(document.getElementById('theSelectedNodeId')!=null && document.getElementById('theSelectedNodeId').value!=0)
    {
    window.divTreeDetails.style.visibility='visible'; 
   // changeVisibilityDiv(window.blocker,window.divTreeDetails);
    }
    return false;
}


/*function to close TreeDivDetail div*/
function closeTreeDivDetal(){
    hideLookUpDiv(window.blocker,window.divTreeDetails,'','','');
    return false;
}

function tabInTreeSearchForward(){
    navigateForwordSearch();
}


function tabInTreeSearchBackward(){
   navigateBackSearch(); 
}



function collapseExpandTreeNode(){
    
    var treeNodeLevels = '';
    var hiddinTreeNodeLevels = document.getElementById('treeNodeNameForCollapseExpand');
    
    if(hiddinTreeNodeLevels != null){
        treeNodeLevels = hiddinTreeNodeLevels.value;
    }
    var imgCollapse = 'myForm:clientTree:' + treeNodeLevels + ':t2' ;
    if(document.getElementById('treeform') != null){
        imgCollapse = 'treeform:clientTree:' + treeNodeLevels + ':t2' ;
    }
    
    fireCommandAction(imgCollapse);
}

function setTreeNodeLevel(treeLevelID){    
    var hiddinTreeNodeLevels = document.getElementById('treeNodeNameForCollapseExpand');
    if(hiddinTreeNodeLevels != null){
        hiddinTreeNodeLevels.value = treeLevelID;
    }
}
////////////////////////// Start Functions Related To Server Tree (Amr Abdo 07/06/2009 ) ///////////////////////////////////
var cmdTreeId = '';

function collapseExpandServerTree(control){  
 
    cmdTreeId = event.srcElement.id;
    //alert('1');
    var lastIndex = cmdTreeId.lastIndexOf(':') + 1;    
//    if(event.keyCode == 32){
//        var itemCollapseExpandId = cmdTreeId.substr(0,lastIndex) + 'commandDescription2';
//        if(document.getElementById(itemCollapseExpandId) != null){
//            alert(itemCollapseExpandId);
//            document.getElementById(itemCollapseExpandId).click();
//        }
//        return;
//    }    
    var hiddenCollapseExpandId = cmdTreeId.substr(0,lastIndex) + 'expandCollapseValue';
   
    if(document.getElementById(hiddenCollapseExpandId) == null){
     
        return false;
    }
   // alert('2')
    var  collapseExpandValue= document.getElementById(hiddenCollapseExpandId).value;
//   alert(collapseExpandValue);
 // alert(hiddenCollapseExpandId+" value: "+collapseExpandValue);
    if(event.keyCode == 32){
        control.click();
    }else if(collapseExpandValue == 'true' && (event.keyCode == 68 || event.keyCode == 100)){ 
        control.click();
    }else if(collapseExpandValue == 'false'  && (event.keyCode == 65 || event.keyCode == 97)){
        control.click();
    }
}

function foucsAtServerTree(){    
    if(cmdTreeId != ''){
      
      if( document.getElementById(cmdTreeId)!=null)
      {
        document.getElementById(cmdTreeId).focus();
        document.getElementById(cmdTreeId).focus(); 
      }
       //alert(cmdTreeId); 
    }
}

function resetCmdTreeId(){
    cmdTreeId = '';
}

//when clicking the label set cmdtreeid for set focus
function setCmdTreeId(control){

//  if(control==null){
//  
//  var target = event.target || event.srcElement;
//   tempCmdTreeId = target.id;
//    var lastIndex = tempCmdTreeId.lastIndexOf(':') + 1;    
////    if(event.keyCode == 32){
////        var itemCollapseExpandId = cmdTreeId.substr(0,lastIndex) + 'commandDescription2';
////        if(document.getElementById(itemCollapseExpandId) != null){
////            alert(itemCollapseExpandId);
////            document.getElementById(itemCollapseExpandId).click();
////        }
////        return;
////    }    
//    cmdTreeId = tempCmdTreeId.substr(0,lastIndex) + 'expandCollapseCmdLink';
//    //alert(cmdTreeId);
//
//
//   }else{
//    cmdTreeId=control.id;
//  //  alert(cmdTreeId);
//   }
    //cmdTreeId = '';
}



    function focusHighlightedNode(){
     
       
       
       if(document.getElementById("selectedNodeTreeLevelId")!=null)
       {
       var levelId=document.getElementById("selectedNodeTreeLevelId").value;
       
        var cmdId="treeform:clientTree:"+levelId+":expandCollapseCmdLink";
         if(document.getElementById(cmdId)==null){
         cmdId="treeform:clientTree:"+levelId+":hiddenLink";
         }
       
       resetScrollPositionAfterSelection(cmdId);
       document.getElementById(cmdId).focus();
       document.getElementById(cmdId).focus();
     
       }
       
       }
       
       
        function resetScrollPositionAfterSelection(cmdId) {
        
       
            if(document.getElementById(cmdId)!=null){
            var x =findPosX(document.getElementById(cmdId)); 
            var y = findPosY(document.getElementById(cmdId));
            document.getElementById('treeform:treeDivPanel').scrollLeft = x;
            document.getElementById('treeform:treeDivPanel').scrollTop = y;
          //  alert('x= '+x+"and y= "+y);
            }else{
           // alert('prob');
            }
        }
       
       
/////////////////////////// End Functions Related To Server Tree (Amr Abdo 07/06/2009 ) ///////////////////////////////////
