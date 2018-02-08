//This Function is overloaded because of cutomized updates in Reg Module
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
function checkDisableAndEnableStatusTable(daummyparameter)
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
         // This if Statement is customized to check wheter the selected Decision is cancelDecsion or not .. 
         //this value is stored in hidden field which is updated when select a record.
        else if(selectedNumber == 1) 
            {
                // canceled Decision Condition..
                if(document.getElementById("cancelDescision") == null)
                {
                    document.getElementById("enabled").value=casesArray[1];
                }
                else
                {
                    if(document.getElementById("cancelDescision").value == 'false')
                    {
                        var newcases=casesArray[1].substring(41);
                        document.getElementById("enabled").value=newcases;    
                    }
                    else
                    {
                        document.getElementById("enabled").value=casesArray[1];
                    }
                }
                
               //valid Decision Condition..
                if(document.getElementById("validDecision") == null)
                {
                    document.getElementById("enabled").value=casesArray[1];
                }
                else
                {
                    if(document.getElementById("validDecision").value == 'true')
                    {
                        var newCasesArray="";
                        var caseArr=casesArray[1].split(',');
                        for(var j=0;j<caseArr.length-1;j++)
                        {
                           if(caseArr[j] != '2-5-2-#{decisionListBean.viewCancelDecisionDetails}')
                            {
                                newCasesArray+=caseArr[j]+",";
                            }
                        }
                        document.getElementById("enabled").value=newCasesArray;
                    }
                    else
                    {
                        document.getElementById("enabled").value=casesArray[1];
                    }
                }
                
                return true;
            }
        else
            {
                document.getElementById("enabled").value=casesArray[2];
                return true;
            } 
        return false
  
}








//This Function is overloaded because of cutomized updates in Reg Module
// Overrides the original JSCookMenu function to work with MyFaces
//
// this part done to handle the  menu items that have  javascript actions depending on Selection Flag in a page its type is DataTable.
//Author:Yassmine Ali.
function disabledAndEnabledActionTable(array,index,dummyparameter)
{
 var indexActionValue=array.split('#');
 if(indexActionValue[0]==index){ 
 
 var selectedNumber=document.getElementById("selectedNumber").value;
 if(indexActionValue[2] == '0')
 {
    eval(indexActionValue[1]);
 }
  // This if Statement is customized to check wheter the selected Regulation is Suggested or not .. 
 //this value is stored in hidden field which is updated when select a record.
if(indexActionValue[2] == '1' && selectedNumber == '1' )
 {
    if(document.getElementById("SuggestedORNot") == null)
    {
        eval(indexActionValue[1]);
    }
    else
    {
        if(document.getElementById("SuggestedORNot").value == 'true' )
        {
          eval(indexActionValue[1]);  
        }
    }
 }
 if(indexActionValue[2] == '2' && selectedNumber != '0' )
 {
    eval(indexActionValue[1]);
 }
}
}

/*Creation/Modification History :
1- Developer Name: Yassmine Ali El-Dahshan
1- Date:  28/2/2008
1- Creation/Modification:Creation      
*/ 
/*Creation/Modification History :
          1- Developer Name: Kahlid Mohie
          1- Date:  02/June/2008
          1- Creation/Modification:Modification
*/      
function updateButtonsStatusTable(DummyParameter){
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
       
    //--------------------------------------------------------------------------
        //Khalid Mohie 
        //02-June-2008
        //deleteButton for regulationlist.jsp in REG module        
       if(document.getElementById("validStatus") != null && document.getElementById("validStatus").value == 'true')
        {
         document.getElementById("myForm:delButton").disabled=true;
         changeStyleButton("myForm:delButton",true);
         }
       else if(document.getElementById("validStatus")!= null && document.getElementById("validStatus").value == 'false' ){
          document.getElementById("myForm:delButton").disabled=false;
          changeStyleButton("myForm:delButton",false);
       }     
    //-------------------------End my modification------------------------------
       
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



/////////////////////////////////////  function to compare Year and Year of Date in Reg and Decision ///////////////////////////////////  

function vaildateIssuanceYearWithRegDate()
{
    if(stepValidation() == false)
    {
    document.getElementById('vaildateIssuanceYearWithRegDateId').innerText='';
    return false;
    }
    else
    {
        if(document.getElementById('clndr_maintain_regDate')!=null && document.getElementById('maintain_regIssuanceYear')!=null)
        {
         return compareYearWithYearofDate('maintain_regIssuanceYear','clndr_maintain_regDate','vaildateIssuanceYearWithRegDateId','yearAndYearDateMsgErrorId');
        }
        
        if(document.getElementById('clndr_maintain_regDate')!=null && document.getElementById('maintain_regIssuanceYearEdit')!=null)
        {
         return compareYearWithYearofDate('maintain_regIssuanceYearEdit','clndr_maintain_regDate','vaildateIssuanceYearWithRegDateId','yearAndYearDateMsgErrorId');  
        }
        return true;
    }

}

function vaildateIssuanceYearWithDecitionDate()
{
    if(stepValidation() == false)
    {
    document.getElementById('vaildateIssuanceYearWithDecitionDateId').innerText='';
    return false;
    }
    else
    {
        if(document.getElementById('clndr_maintain_decisionDate')!=null && document.getElementById('maintain_regIssuanceYearAdd')!=null)
        {
         return compareYearWithYearofDate('maintain_regIssuanceYearAdd','clndr_maintain_decisionDate','vaildateIssuanceYearWithDecitionDateId','yearAndYearDateMsgErrorDecId');
        }
        
        if(document.getElementById('clndr_maintain_decisionDate')!=null && document.getElementById('maintain_regIssuanceYearEdit')!=null)
        {
         return compareYearWithYearofDate('maintain_regIssuanceYearEdit','clndr_maintain_decisionDate','vaildateIssuanceYearWithDecitionDateId','yearAndYearDateMsgErrorDecId');  
        }
        return true;
    }

}

///////////////////////////////////// END of  function to compare Year and Year of Date in Reg and Decision ///////////////////////////////////  


////////////////////////////////////////////////////// Start of java script function related for add morfkat requlation tab adedd by nora //////////////////////////////////////


function checkCheckAll_Rows(arrayId,listSize,checkAll) {
    var pageIndex;
    var noOfTableRows ;
    
       if(document.getElementById('arrayId')!=null)
        document.getElementById('arrayId').value = arrayId;
        
       if(document.getElementById(listSize)!=null)
         pageIndex = document.getElementById(listSize).value;
        
        if(document.getElementById('pageIndex') != null && document.getElementById('pageIndex').value != '')
            pageIndex = document.getElementById('pageIndex').value;
        
        if(document.getElementById('noOfTableRows')!=null)
           noOfTableRows = document.getElementById('noOfTableRows').value;
           
    var checked = 0;
    var checkBoxLength = 0;
     if(document.myForm.chk2.length == undefined){
        document.getElementById(checkAll).checked=document.myForm.chk2.checked;
     }
     else{
        for (i = 0;i < document.myForm.chk2.length ; i++) {
                id = arrayId + '[' + i + ']';
                if (document.getElementById) {
                    elem = document.myForm.chk2[i];
                    if (elem == null && i != 0) {
                        break;
                    } else if (elem == null) {
                        i = ((pageIndex - 1) * noOfTableRows) - 1;
                        checkBoxLength = checkBoxLength - 1;
                    } else if(elem.checked) {
                       checked++; 
                    }
                }
                checkBoxLength++;// = i+1;
            }
        if(checked == checkBoxLength){  
              if(document.getElementById(checkAll)!=null)
                 document.getElementById(checkAll).checked = true;
            }
            else{  
                if(document.getElementById(checkAll)!=null)
                   document.getElementById(checkAll).checked = false;
            }
 }
}        

function setAll_Rows(checkAllId, arrayId, listSize ) {
    var object;
    var pageIndex;
    var noOfTableRows;
    
    if(document.getElementById(checkAllId) != null)
       object = document.getElementById(checkAllId);

        //alert('object------->'+object);
        
     if(document.getElementById('arrayId')!=null)  
      document.getElementById('arrayId').value = arrayId;
      
     if(document.getElementById(listSize)!=null)
      pageIndex = document.getElementById(listSize).value;
      //alert('pageIndex------->'+pageIndex);
      if(document.getElementById('noOfTableRows')!=null)
      noOfTableRows = document.getElementById('noOfTableRows').value;

      if(document.getElementById('pageIndex') != null)
        pageIndex = document.getElementById('pageIndex').value;

        if(document.myForm.chk2.length == undefined){
          elem = document.myForm.chk2;
          elem.checked = object.checked;
        }
        else{
        for (i = 0;i <document.myForm.chk2.length; i++) {
            id = arrayId + '[' + i + ']';
            if (document.getElementById) {
                elem = document.myForm.chk2[i];
                if (elem == null && i != 0) {
                    break;
                } else if (elem == null) {
                    i = ((pageIndex - 1) * noOfTableRows) - 1;
                   
                } else {
                    elem.checked = object.checked;
                     
                    
                }
            }
        }
      }
      
      
        
}

function disableCharactersByColumnType(type,component){
    if(eval(type) == 2)
        disableCharacters(component);
}

function keyPressNumberByColumnType(type,event){
 if(eval(type) == 2)
        keyPressNumber(false,event);
}


function validateRequiredFields(colsize,errMsg){
 var rowsize=0;
   if(document.getElementById('currentListSizeID') != null)
        rowsize= document.getElementById('currentListSizeID').value;
    
    if(document.getElementById('rowMandatoryMsg') != null){
        // document.getElementById('rowMandatoryMsg').style.fontSize='0pt';
         document.getElementById('rowMandatoryMsg').innerHTML='';
     }
    if(document.getElementById('nonvalidData') != null) 
        document.getElementById('nonvalidData').innerHTML='';
 
 for (y = 0; y<rowsize ; y++) {
      for (x = 0; x<colsize ; x++) {
             var id='myForm:dataT_data:'+y+':columns:'+(x+1)+'_'+(x+1)+':cellValue' ;
             var elem= document.getElementById(id);
             var elemValue='';
             if(elem !=null)
                 elemValue=elem.value;
            
            if(elemValue == null || elemValue == ''){
                 if(document.getElementById('rowMandatoryMsg') != null){
                      //document.getElementById('rowMandatoryMsg').style.fontSize='11pt';
                      document.getElementById('rowMandatoryMsg').innerHTML=errMsg;
                  }
              setFocusOnlyOnElement(id);
              return false;
              }   
        }
     }
 return true;
}
