/* 
Purpose:This Method is called in navigation bar in M2M use cases and i modified it to call function called validateTwoDatesInDataTable.
If the page doesnot contain this validation so the function will return true otherwise will return the validation value.
Creation/Modification History :
   1.1 - Developer Name:Yassmine Ali
   1.2 - Date: 22/5/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:
   */
//this function work assuming that validateTwoDatesInDataTable function exist in the base js files

function stepValidation(){
 var returnFromCmpareDates=validateTwoDatesInDataTable();
 if(returnFromCmpareDates)
    {
        //if(typeof doValidatemyForm != 'undefined'){
            if(doValidatemyForm){
                return validatemyForm();
            }
        //}
        return true;   
    }
    
    return false;
 }

 function ignoreClientSideValidation(){
  if(doValidatemyForm){
   doValidatemyForm=false;
 }
 }
 
/* 
Purpose:compare two dates 
*@param start,start date
*@param end ,end date 
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function compare2DatesDataTable(start,end,outputMessageID,index,msg){          
                     
                     var startDate;
                     var endDate;
                     if(document.getElementById(start) !=null)
                        startDate=document.getElementById(start).value;
                     if(document.getElementById(end+"["+index+"]")!=null)   
                      endDate= document.getElementById(end+"["+index+"]").value; 
                     
                     var isFieldValid=true;
                     var retVal=validateRequiredField((end+"["+index+"]"),'workAroundSpan',false,'dynamic');
                     isFieldValid=isFieldValid && retVal;
                     
                     retVal=validateDate((end+"["+index+"]"),'workAroundSpan',false,'dynamic');
                     isFieldValid=isFieldValid && retVal;
                     
                     
                     if(!isFieldValid)
                     return false;
                     
                    
                       sdd = startDate.substring(0,startDate.indexOf("/"));
                       sdm = startDate.substring(startDate.indexOf("/")+1,startDate.lastIndexOf("/"));
                       sdy = startDate.substring(startDate.lastIndexOf("/")+1,startDate.length);
                       sd = new Date(sdy,sdm-1,sdd);
                       

                       edd = endDate.substring(0,endDate.indexOf("/"));
                       edm = endDate.substring(endDate.indexOf("/")+1,endDate.lastIndexOf("/"));
                       edy = endDate.substring(endDate.lastIndexOf("/")+1,endDate.length);
                       ed = new Date(edy,edm-1,edd);
                       
                       sdms = sd.getTime();
                       edms = ed.getTime();
                       obj = edms - sdms;
                       if(parseInt(obj)>=0){
                        if(document.getElementById(outputMessageID+"["+index+"]")!=null)
                           document.getElementById(outputMessageID+"["+index+"]").innerHTML = '';
                           return true;
                           }                        
                       else{
                        if(document.getElementById(outputMessageID+"["+index+"]")!=null)
                           document.getElementById(outputMessageID+"["+index+"]").innerHTML = msg;

                           return false;
                       }
                       
                       return false;
                   }
                   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
Purpose:check all behavior in the add div
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
 ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 

*/
function setAllAdd(checkAllId, arrayId) {
  
    var object;
    var pageIndex;
     var noOfTableRows;
    if(document.getElementById('checkAllAdd')!=null)
       object= document.getElementById('checkAllAdd');
       
   if(document.getElementById('arrayIdAdd') !=null)
    document.getElementById('arrayIdAdd').value = arrayId;

   if(document.getElementById('listSizeAdd')!=null)
     pageIndex = document.getElementById('listSizeAdd').value;
   
   if(document.getElementById('noOfTableRows')!=null)
    noOfTableRows= document.getElementById('noOfTableRows').value;
    
    if(document.getElementById('pageIndexAdd') != null){
        pageIndex = document.getElementById('pageIndexAdd').value;
    }
    for (i = 0; ; i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            } else if (elem == null && pageIndex!=null && noOfTableRows!=null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
            } else {
              if(object !=null)
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
            for (var i=0; i<elems.length; i++) {
               if(elems[i].type == "checkbox") {
                   if(elems[i].checked) {
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
   1.4-  Description:yy
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 

*/
        
function checkCheckAllAdd(arrayId) {

 var pageIndex;
 var noOfTableRows;
  if(document.getElementById('arrayIdAdd')!=null)
     document.getElementById('arrayIdAdd').value = arrayId;
     
  if(document.getElementById('listSizeAdd')!=null)   
     pageIndex= document.getElementById('listSizeAdd').value;
   
    if(document.getElementById('pageIndexAdd') != null)
        pageIndex = document.getElementById('pageIndexAdd').value;
    
    if(document.getElementById('noOfTableRows')!=null) 
    noOfTableRows= document.getElementById('noOfTableRows').value;
    
    var checked = 0;
    var checkBoxLength = 0;
    for (i = 0; ; i++) {
        id = arrayId + '[' + i + ']';
        if (document.getElementById) {
            elem = document.getElementById(id);
            if (elem == null && i != 0) {
                break;
            } else if (elem == null && noOfTableRows !=null && pageIndex !=null) {
                i = ((pageIndex - 1) * noOfTableRows) - 1;
                checkBoxLength = checkBoxLength - 1;
            } else if(elem.checked) {
               checked++; 
            }
        }
        checkBoxLength++;// = i+1;
    }
    if(checked == checkBoxLength){
        if(document.getElementById('checkAllAdd')!=null)
            document.getElementById('checkAllAdd').checked = true;
    }
    else{
        if(document.getElementById('checkAllAdd')!=null)
            document.getElementById('checkAllAdd').checked = false;
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
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 

*/
function checkAllCheckBoxAdd(){
    var arrayId='';
    if(document.getElementById('arrayIdAdd') != null)
        arrayId = document.getElementById('arrayIdAdd').value;
    
 if(arrayId != ''){
    var pageIndex;
    var noOfTableRows ;
    
     if(document.getElementById('listSizeAdd')!=null)
           pageIndex= document.getElementById('listSizeAdd').value;
    
    if(document.getElementById('pageIndexAdd') != null)
            pageIndex = document.getElementById('pageIndexAdd').value;
      
     if(document.getElementById('noOfTableRows')!=null)
        noOfTableRows= document.getElementById('noOfTableRows').value;
        
        var checked = 0;
        var checkBoxLength = 0;
        for (i = 0; ; i++) {
            
            id = arrayId + '[' + i + ']';
            if (document.getElementById) {
                elem = document.getElementById(id);
                if (elem == null && i != 0) {
                    break;
                } else if (elem == null && noOfTableRows!=null && pageIndex!=null) {
                    i = ((pageIndex - 1) * noOfTableRows) - 1;
                    checkBoxLength = checkBoxLength - 1;
                } else if(elem.checked) {
                   checked++; 
                }
            }
            checkBoxLength++;
        }
        
        if(checked == checkBoxLength) {
           if(document.getElementById('checkAllAdd')!=null)
            document.getElementById('checkAllAdd').checked = true;
        } else {
           if(document.getElementById('checkAllAdd')!=null)
            document.getElementById('checkAllAdd').checked = false;
        }
    }
}
checkAllCheckBoxAdd();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:this function to check if any item selected in certain checkbox group
Creation/Modification History :
   1.1 - Developer Name:Ahamed Abdel Fatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 

*/
function confirmCheckBoxSelection(arrayId){

if(arrayId != ''){
 var previouslySelectedCheckBoxes;
 
  if(document.getElementById('selectedAvailableListSize')!=null)
     previouslySelectedCheckBoxes= document.getElementById('selectedAvailableListSize').value;

  if(previouslySelectedCheckBoxes!=null && previouslySelectedCheckBoxes==0){
    document.getElementById('errorConsole').innerHTML="من �?ضلك، اختر أحد العناصر";
   return false;
  }else{
   if(document.getElementById('errorConsole')!=null)
     document.getElementById('errorConsole').innerHTML="";
  return true;

  }
  }
  if(document.getElementById('errorConsole')!=null)
    document.getElementById('errorConsole').innerHTML="";
    
return false;
}


/*
function alertSaveBeforeNavigation(button){

//clickedButton=document.getElementById(button);
//alert(button.id);
 save = confirm("Do you want to save this Group ?");
// alert(document.getElementById('saveGroupWhileNavigation').value);
if(save){

//if(stepValidation()){
//alert('after stepvalidation');
hidden=document.getElementById('myForm:saveGroupWhileNavigation');
hidden.value=1;
alert(document.getElementById('myForm:saveGroupWhileNavigation').value);
return true;
//}else{
//alert('step validation false');
//return false;
//}

}else{
alert('confirm false');
return false;
}



}
*/
/* 
Purpose:compare two dates
Creation/Modification History :
   1.1 - Developer Name:
   1.2 - Date: 15/4/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:
* 
*/
/*
function compareOptionalTwoDates(startDateID,endDateID,OutPutID,requiredDateMessage,compareDatesMessage)
{
 
 
 var startDate=null ;
 var endDate=null;
 
 if(document.getElementById(startDateID)!=null){
    startDate=document.getElementById(startDateID).value;
   
 }
 if(document.getElementById(endDateID)!=null){
 endDate=document.getElementById(endDateID).value;
 
 }
 
 if( (endDate != null && endDate !="") && (startDate== null || startDate=="" )){
   
    document.getElementById(OutPutID).innerHTML=requiredDateMessage;
    return false;
    }
 if(endDate=="" || endDate == null)
   {
     document.getElementById(OutPutID).innerHTML = '';
     return true;                     
  }
  
  if( (endDate != null && endDate !="") && (startDate!= null && startDate!="" ) ){
   sdd = startDate.substring(0,startDate.indexOf("/"));
   sdm = startDate.substring(startDate.indexOf("/")+1,startDate.lastIndexOf("/"));
   sdy = startDate.substring(startDate.lastIndexOf("/")+1,startDate.length);
   sd = new Date(sdy,sdm-1,sdd);
                       

  edd = endDate.substring(0,endDate.indexOf("/"));
  edm = endDate.substring(endDate.indexOf("/")+1,endDate.lastIndexOf("/"));
  edy = endDate.substring(endDate.lastIndexOf("/")+1,endDate.length);
  ed = new Date(edy,edm-1,edd);
                       
  sdms = sd.getTime();
  edms = ed.getTime();
  obj = edms - sdms;
     if(parseInt(obj)>=0){
                             
         document.getElementById(OutPutID).innerHTML = '';
         return true;
     }                        
      else{
          document.getElementById(OutPutID).innerHTML = compareDatesMessage;
          return false;
         }
    }     
 document.getElementById(OutPutID).innerHTML = '';        
 return true;
}
*/
