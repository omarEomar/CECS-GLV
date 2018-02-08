// USED BY disableDragAndDrop FUNCTION BY SHERIF OMAR
var reFloat = /^((\d+(\.\d*)?)|((\d*\.)?\d+))$/
var defaultEmptyOK = false
var BR_MSG_TREE_ADD_ID = 'treeform:brMsgTreeAdd' 
var ERROR_MSG_TREE_ADD_ID = 'treeform:error' 


/* 
Purpose:this function used for search process in list of values Div
*@param radioId ,code or name radio 
*@param searchQueryID, search string 
*@param isSrchByCodeSpecial ,A true value treated as string used to allow search by special char in list of value div
Creation/Modification History :
   1.1 - Developer Name:Sherif Muhammed Omar
   1.2 - Date: 04-02-2009
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/    
 function validateCodeNameSrchParamter(radioId,searchQueryID,isSrchByCodeSpecial,errMsgId) {
        var radio;
        var searchStr;
        var seachValidationType;
        var allowSpecialCharInSrchByCode = false;
        
        if(isSrchByCodeSpecial != null)
            if(isSrchByCodeSpecial == 'true')
                allowSpecialCharInSrchByCode = true;
                
        
        if(document.forms['myForm'] != null){
            radio =document['forms']['myForm'][radioId];
            
        }
        else if(document.forms['treeform'] != null){
            radio = document['forms']['treeform'][radioId] ;
        }
        if(!radio[0].checked && !radio[1].checked) {
        
             if(document.getElementById(errMsgId)!=null)
                document.getElementById(errMsgId).innerHTML = selectOneRadio;
                
                
                
            return false;
        }
        
        if(document.getElementById(errMsgId)!=null)
           document.getElementById(errMsgId).innerHTML = '';
           
       if(document.getElementById(searchQueryID)!=null)
             searchStr= document.getElementById(searchQueryID).value;
        
        if(checkEmpty2(searchStr,errMsgId))
             return false;
       
       
       if(radio[0].checked ) {
                   if(allowSpecialCharInSrchByCode)
                    {
                        
                            if(checkStringLength2(searchStr,errMsgId,2))
                                return false;
                            
                            
                            return checkStringOnly2(searchStr,errMsgId,null);
                            
                            
                    }
                        else if(!allowSpecialCharInSrchByCode)
                        {
                                    var pattern = "0123456789";
                                    var i = 0; 
                                do {
                                        var pos = 0; 
                                        for (var j=0; j< pattern.length; j++) 
                                        if (searchStr.charAt(i)==pattern.charAt(j)) {
                                            pos = 1; 
                                            break; 
                                    } 
                                i++; 
                                    } while (pos==1 && i< searchStr.length) 
                                    if (pos == 0) {
                                if(document.getElementById(errMsgId)!=null)
                                    document.getElementById(errMsgId).innerHTML = numbersOnly;
                                    return false;
                                }
                               if(document.getElementById(errMsgId)!=null)
                                 document.getElementById(errMsgId).innerHTML = '';
                                return true;

                         }
                   }     
        else if(radio[1].checked) {
            
              var valid = checkLenghtGreaterthan(searchQueryID,1,errMsgId,lessThanTwo);
              if (valid){
                valid = checkStringOnly2(searchStr,errMsgId,null);
              }
             return valid;
        }
         return true;
        
    }
    
/* 
Purpose:compare 2 numbers
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/  
function compareNumbers(firstNumId,secondNumId,msgId,msgContent){

var firstNumValue;
var secondNumValue;

   if(document.getElementById(firstNumId)!=null)
        firstNumValue=document.getElementById(firstNumId).value;
   if(document.getElementById(secondNumId)!=null)
        secondNumValue=document.getElementById(secondNumId).value;
         
  if(firstNumValue!=null && secondNumValue!=null && firstNumValue!='' && secondNumValue!='' &&( eval(firstNumValue)>=eval(secondNumValue))) {
      if(document.getElementById(msgId)!=null)
           document.getElementById(msgId).innerHTML = msgContent;
    return false;
  }
   if(document.getElementById(msgId)!=null)
      document.getElementById(msgId).innerHTML = '';
    
return true;
}


/* 
Purpose:compare 2 numbers
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/  
function compareNumbers_Gen(firstNumId,secondNumId,msgId,msgContent,ignoreEqual){

var firstNumValue;
var secondNumValue;

   if(document.getElementById(firstNumId)!=null)
        firstNumValue=document.getElementById(firstNumId).value;
   if(document.getElementById(secondNumId)!=null)
        secondNumValue=document.getElementById(secondNumId).value;
         
  
  if(ignoreEqual==false)
     {
  if(firstNumValue!=null && secondNumValue!=null && firstNumValue!='' && secondNumValue!='' && (eval(firstNumValue)>=eval(secondNumValue))) {
     
     
      if(document.getElementById(msgId)!=null)
           document.getElementById(msgId).innerHTML = msgContent;
      return false;
  
  }}else{
  
  
  if(firstNumValue!=null && secondNumValue!=null && firstNumValue!='' && secondNumValue!='' && (eval(firstNumValue)>eval(secondNumValue))) {
     
     
      if(document.getElementById(msgId)!=null)
           document.getElementById(msgId).innerHTML = msgContent;
       return false;
  
  }}
   
   
   if(document.getElementById(msgId)!=null)
      document.getElementById(msgId).innerHTML = '';
    
return true;
}

/*used only by checkRadio_CivilId_Gen*/
function checkNumber_Gen(searchStr,msgId) {
        var pattern = "0123456789";
        var i = 0; 
        do {
            var pos = 0; 
            for (var j=0; j< pattern.length; j++) 
                if (searchStr.charAt(i)==pattern.charAt(j)) {
                    pos = 1; 
                    break; 
                } 
            i++; 
        } while (pos==1 && i< searchStr.length) 
        if (pos == 0) {
        if(document.getElementById(msgId)!=null)
            document.getElementById(msgId).innerHTML = numbersOnly;
            return false;
        }
       if(document.getElementById(msgId)!=null)
         document.getElementById(msgId).innerHTML = '';
        return true;
    }
function checkRadio_CivilId_Gen(radioId,searchQueryID,errCivilMsg, msgId) {
       
        var radio;
        var searchStr;
        var seachValidationType;
        if(document.forms['myForm'] != null){
            radio =document['forms']['myForm'][radioId];
           
        }
        else if(document.forms['treeform'] != null){
            radio = document['forms']['treeform'][radioId] ;
         }
        if( !radio[0].checked && !radio[1].checked) {
             if(document.getElementById(msgId)!=null)
                document.getElementById(msgId).innerHTML = selectOneRadio;
                
            return false;
        }
        if(document.getElementById(msgId)!=null)
           document.getElementById(msgId).innerHTML = '';
           
       if(document.getElementById(searchQueryID)!=null)
         searchStr= document.getElementById(searchQueryID).value;
        
          
        if(checkEmpty2(searchStr,msgId))
         return false;
       
       if(radio[0].checked ) {
              {
             
              if(checkNumber_Gen(searchStr,msgId))
                   { 
                    
                   if(!checkLength(searchQueryID,'12'))
                   {
                        document.getElementById(msgId).innerHTML = errCivilMsg;
                    }    
                    else
                        return true;
                   }     
              return false;      
              }     
          }
        else if(radio[1].checked) {
             return checkStringOnly2(searchStr,msgId,null);
        }
        return true;
        
    }    

/* 
Purpose:this function used @ case that search contain radio button and code is civil id that it is 12 number 
*@param param ,the input calender object 
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 24-11-2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/    
 function checkRadio_CivilId(radioId,searchQueryID,errCivilMsg) {
        var radio;
        var searchStr;
        var seachValidationType;
        
        if(document.forms['myForm'] != null){
            radio =document['forms']['myForm'][radioId];
        }
        else if(document.forms['treeform'] != null){
            radio = document['forms']['treeform'][radioId] ;
         }
        if( !radio[0].checked && !radio[1].checked) {
        
             if(document.getElementById('errorMessage')!=null)
                document.getElementById('errorMessage').innerHTML = selectOneRadio;
                
            return false;
        }
        
        if(document.getElementById('errorMessage')!=null)
           document.getElementById('errorMessage').innerHTML = '';
           
       if(document.getElementById(searchQueryID)!=null)
         searchStr= document.getElementById(searchQueryID).value;
          
        if(checkEmpty(searchStr))
         return false;
       
       if(radio[0].checked ) {
              {
              
              if(checkNumber(searchStr))
                   { 
                   
                   if(!checkLength(searchQueryID,'12'))
                        document.getElementById('errorMessage').innerHTML = errCivilMsg;
                    else
                        return true;
                   }     
              return false;      
              }     
          }
        else if(radio[1].checked) {
             return checkStringOnly2(searchStr,'errorMessage',null);
        }
        return true;
        
    }    
  /* 
Purpose:validate submitted value has exist length 
*@param param ,the input calender object 
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 24-11-2008
   1.3 - Creation/Modification:Modification to be generic 
   1.4-  Description:
* 
*/      
 function checkLength(textID,length) {
        var textValue = '';
        if(document.getElementById(textID)!=null);
                textValue=document.getElementById(textID).value;

        if(textValue.length == length) 
         return true;
        
        return false;
  }   
  /* 
Purpose:validated that input calender has correct format 
*@param param ,the input calender object 
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 25/9/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/      
function validateInputCalenderFormate(dateId,output,errorMsg){

  if(isDate(dateId,output,errorMsg)==false){
    
       if(typeof dateId == 'string' && document.getElementById(dateId)!=null){
            document.getElementById(dateId).value="";
        }else if(dateId!=null){
            dateId.value="";
        }
    
   return false;
  }
  return true;
}

/////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:This Method used in conditionlinessub to validate the code has numbers only and then search.
*/
function searchAndvalidate()
{
    var returnFromValidation=validatemyForm();
    if(returnFromValidation)
    {
    searchLines();
    }
    else{  
    return false;
    }
    return true;
}
/* 

/* 
Purpose:this function is used to validate if submited value is integer or not
*@param name , the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg, the msg to be displayed to the user
*@required,   required  if you don't need name as required send this parameter with 'false' value otherwise  it will be required
Creation/Modification History :
   1.1 - Developer Name: Yassmine
   1.2 - Date: 23/6/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:it is more gneral function than , other idential name function , except it is now accept more parameters for erorr message & ids , ...
*/ 
function isPositiveInteger(name,output,requiredmsg,numbermsg,required) {
      var pattern = "0123456789" ;
      var str;
     if( document.getElementById(name) != null )
        str = document.getElementById(name).value;
     else
       return false;
       
     if(str == '' && required =='true')
     {
        document.getElementById(output).innerHTML = requiredmsg;    
        return false;
     }
     if(str != ''){
      var i = 0; 
      do {
           var pos = 0; 
            for (var j=0; j<pattern.length; j++) 
               if (str.charAt(i)==pattern.charAt(j)) {
                   pos = 1; 
                   break; 
               } 
        i++; 
      } while (pos==1 && i<str.length) 
      if (pos==0) 
      {
         if( document.getElementById(output) != null )
           document.getElementById(output).innerHTML=numbermsg;
          return false;
      }
    }
  return true;
} 
/* 
Purpose:this function is used to validate if submited value is integer or not
*@param name , the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg, the msg to be displayed to the user
*@required,   required  if you don't need name as required send this parameter with 'false' value otherwise  it will be required
Creation/Modification History :
   1.1 - Developer Name: Assmaa Omar
   1.2 - Date: 07/1/2009
   1.3 - Creation/Modification:Modification      
   1.4-  Description:it is more gneral function than , other idential name function , except it is now accept more parameters for erorr message & ids , ...
    I just make the same function (isPositiveInteger)  with another name to avoid the problem of 2 function with the same  name (isPositiveInteger) 
*/ 
function isIntegerValueWithRequiredParam(name,output,requiredmsg,numbermsg,required) {
      var pattern = "0123456789" ;
      var str;
     if( document.getElementById(name) != null )
        str = document.getElementById(name).value;
     else
       return false;
       
     if(str == '' && required =='true')
     {
        document.getElementById(output).innerHTML = requiredmsg;    
        return false;
     }
     if(str != ''){
      var i = 0; 
      do {
           var pos = 0; 
            for (var j=0; j<pattern.length; j++) 
               if (str.charAt(i)==pattern.charAt(j)) {
                   pos = 1; 
                   break; 
               } 
        i++; 
      } while (pos==1 && i<str.length) 
      if (pos==0) 
      {
         if( document.getElementById(output) != null )
           document.getElementById(output).innerHTML=numbermsg;
          return false;
      }
    }
  return true;
} 
//////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:validate date in the format dd/mm/yyyy you need the helper functions above for this to run correctly
*@param id , the id of the input text
@param dateId ,the ide of date control to be validated
*@param the id of the div to handle the error message
*@param the error message to be displayed
Creation/Modification History :
   1.1 - Developer Name: Yassmine
   1.2 - Date: 7/7/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:it is more gneral function than , other idential name function , except it is now accept more parameters for erorr message & ids , ...
*/

function isDate(dateId,output,requiredmsg,errorMsg,required){
  var dtStr;
  
  if(typeof dateId == 'string' && document.getElementById(dateId)!=null){
        dtStr = document.getElementById(dateId).value;
  }else if(dateId!=null){
        dtStr = dateId.value
  }
  
  if(dtStr=='' &&  required == 'true')
  {
       if(document.getElementById(output) != null){
        document.getElementById(output).innerHTML = requiredmsg;    
       }
    return false;
  }
  else if(dtStr!='')
  {
   var daysInMonth = DaysArray(12)
   var pos1=dtStr.indexOf(dtCh)
   var pos2=dtStr.indexOf(dtCh,pos1+1)
   var strDay=dtStr.substring(0,pos1)
   var strMonth=dtStr.substring(pos1+1,pos2)
   var strYear=dtStr.substring(pos2+1)
   strYr=strYear
   if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
   if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
   for (var i = 1; i <= 3; i++) {
       if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
   }
   month=parseInt(strMonth)
   day=parseInt(strDay)
   year=parseInt(strYr)
   if (pos1==-1 || pos2==-1){
      if(document.getElementById(output)!=null)    
            document.getElementById(output).innerHTML =errorMsg;
      return false
   }
   if (strMonth.length<1 || month<1 || month>12){
       if(document.getElementById(output)!=null)
               document.getElementById(output).innerHTML =errorMsg;
       return false
   }
   if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
          if(document.getElementById(output)!=null)
                document.getElementById(output).innerHTML =errorMsg;
       return false
   }
   if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
      if(document.getElementById(output)!=null)    
               document.getElementById(output).innerHTML =errorMsg;
       return false
   }
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
           if(document.getElementById(output)!=null)  
               document.getElementById(output).innerHTML =errorMsg;
       return false
   }
      }

 if(document.getElementById(output)!=null)
   document.getElementById(output).innerHTML ='';
       
return true;
}
//////////////////////////////////////////////////////////////////////////////////

/* 
Purpose:check the lenght of submitted field is equal to specific length
*@param length :length of entered data
Creation/Modification History :
   1.1 - Developer Name: 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/          
     function checkExactLength(length,fieldId,outputId,message) {
        if(document.getElementById(fieldId) != null ){
           
           var fieldValue=document.getElementById(fieldId).value;
        
           if((fieldValue!=null && fieldValue!='')&& isInteger(fieldValue) && (fieldValue.length != length)){
           
            if(document.getElementById(outputId) != null)
              document.getElementById(outputId).innerHTML = message; 
              return false;
             }
             else{
                document.getElementById(outputId).innerHTML ='';
                     return true;
                 }
           }
           
         document.getElementById(outputId).innerHTML ='';            
        return false;
        }   
          
          
                
                
//////////////////////////////////////////////////////////////////////////////////


/* 
Purpose:This Method ask the user to at least one element of search criteria
This Function depends on that the ids u send is forced ids each id is comma seperated from the other one
Creation/Modification History :
  1.1 - Developer Name:Assmaa Omar
  1.2 - Date: 16/6/2008
  1.3 - Creation/Modification:Creation      
  1.4-  Description:
  Note if u wants to call client validator  so you must set fireClientValidator flag
  to true.
  */


function  validateSearchData(fieldsForcedIDsArray,output,msg)
{
    var submit=false; 
    
    if( document.getElementById(output) != null )
        document.getElementById(output).innerHTML = '';   
        
    var fieldArrayIds=fieldsForcedIDsArray.split(',');
    
      for(var j=0; j<fieldArrayIds.length; j++)
        {
            var x=fieldArrayIds[j];
            if(document.getElementById(x)!=null && document.getElementById(fieldArrayIds[j]).value != '')
            {
                submit=true;
                break;
            }
        }
        
        if( submit==false && document.getElementById(output) != null )// all fileds empty so it will show error message
            document.getElementById(output).innerHTML = msg;
        //else if(submit==true && fireClientValidator=='true'){  // mean at least one field entered and client validator will be fired    
        //   return validatemyForm();
        //}
       
    return submit;
}

/* 
Purpose:This Method ask the user to at least one element of search criteria
This Function depends on that the ids u send is forced ids each id is comma seperated from the other one
Note : the function have the same purpose of previous validateSearchData method but here you have to send me combo boxes ids and virtual input id  as separted parameters to check if combo box  equal virtual value or not 
Creation/Modification History :
  1.1 - Developer Name:Nora Ismail
  1.2 - Date: 12-11-2008
  1.3 - Creation/Modification:Creation      
  1.4-  Description:
  */
function  validateSearchData_comboBox(fieldsForcedIDsArray,comboBoxsForcedIDs,virtualForcedID,output,msg){

    var submit=false; 
    if(fieldsForcedIDsArray!=null)
    {
        var fieldArrayIds=fieldsForcedIDsArray.split(',');
    }
    
     if(comboBoxsForcedIDs!=null)
    {
    var comboBoxsIDs=comboBoxsForcedIDs.split(',');  
    }
    var virtualValue='';
    
    if(document.getElementById(virtualForcedID)!=null)
        virtualValue=document.getElementById(virtualForcedID).value;
    if( document.getElementById(output) != null )
       document.getElementById(output).innerHTML = '';  
 
    if(fieldArrayIds!=null){
      for(var j=0; j<fieldArrayIds.length; j++)
        {
            var x=fieldArrayIds[j];
            if(document.getElementById(x)!=null && document.getElementById(fieldArrayIds[j]).value != '')
            {
                submit=true;
                break;
            }
        }
        }
    
        if(comboBoxsIDs!=null){
         for(var j=0; j<comboBoxsIDs.length; j++)
        { 
             var x=comboBoxsIDs[j];
            if(document.getElementById(x)!=null && document.getElementById(x).value != virtualValue && document.getElementById(x).value !='')
            {
                submit=true;
                break;
            }
        }
        }
        
        if( submit==false && document.getElementById(output) != null )// all fileds empty so it will show error message
            document.getElementById(output).innerHTML = msg;
       
    return submit;
}



/* 
Purpose:This Method Compares two dates in dataTable 
This Function depends on four hidden fields - hidden field contains id of from date
                                           - hidden field contains id of outputLable in which errors message will appear if there is errors in FromDate
                                           - hidden field contains id of until date
                                           - hidden field contains id of outputLable in which errors message will appear if there is errors in UntilDate
Note : All These components (inputcalendars and outputLabels) dont have forcedId attribute.
Creation/Modification History :
   1.1 - Developer Name:Yassmine Ali
   1.2 - Date: 13/5/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  1/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null   
  */


function  validateTwoDatesInDataTable(){

    if(document.getElementById("fromDateID") == null || document.getElementById("fromDateMessageID") == null || document.getElementById("untilDateID") == null
    || document.getElementById("untilDateMessageID") == null)
    {
       return true;
    }

var fromDate=document.getElementById("fromDateID").value;
var fromDateMessage=document.getElementById("fromDateMessageID").value;
var untilDate=document.getElementById("untilDateID").value;
var untilDateMessage=document.getElementById("untilDateMessageID").value;


var submit=true; 
var elems;
if(document.getElementById("myForm:dataT_data")!=null) 
    elems= document.getElementById("myForm:dataT_data:tbody_element");

 for(var y=0;elems!=null && y <elems.rows.length;y++){
 
            var publish_date_isDate=true;
            var publish_date_Message="myForm:dataT_data:"+y+":"+fromDateMessage;
            var until_date_Message="myForm:dataT_data:"+y+":"+untilDateMessage;
            
            if(document.getElementById(publish_date_Message)!=null){
             document.getElementById(publish_date_Message).innerHTML='';
             document.getElementById(publish_date_Message).style.fontSize='1px';
            }
            
            if(document.getElementById(until_date_Message)!=null){
             document.getElementById(until_date_Message).innerHTML='';
             document.getElementById(until_date_Message).style.fontSize='1px';
            }
            
            var publish_date="myForm:dataT_data:"+y+":"+fromDate;
            var publish_dateValue;
            
             if(document.getElementById(publish_date)!=null)
                 publish_dateValue=document.getElementById(publish_date).value;
           
            if(publish_dateValue == ''){
            
              if(document.getElementById(publish_date_Message)!=null){
                  document.getElementById(publish_date_Message).innerHTML=missingField;
                  document.getElementById(publish_date_Message).style.fontSize='10px';
             }
             
              submit = false;
            }
          else{
                 publish_date_isDate=isDate(publish_date,publish_date_Message,isDateMessage);
                
                 if(publish_date_isDate  == false){
                 
                    if(document.getElementById(publish_date_Message)!=null)
                       document.getElementById(publish_date_Message).style.fontSize='10px';
                    submit = false;
                }
                
             }           
            var until_date="myForm:dataT_data:"+y+":"+untilDate;
            var until_dateValue;
            
             if(document.getElementById(until_date)!=null)
                  until_dateValue=document.getElementById(until_date).value;
                  
            if(until_dateValue != ''){
                 var until_date_isDate=isDate(until_date,until_date_Message,isDateMessage);
                 
                 if(until_date_isDate  == false){
                   if(document.getElementById(until_date_Message)!=null)
                     document.getElementById(until_date_Message).style.fontSize='10px';
                     
                     submit = false;
                 }
                 else {
                    if(publish_date_isDate){
                        var compareDates=compare2Dates(publish_date,until_date,until_date_Message,compare2DatesMessage);
                        if(compareDates == false){
                          if(document.getElementById(until_date_Message)!=null)
                             document.getElementById(until_date_Message).style.fontSize='10px';
                            submit = false;
                        }
                   }
                 }
            
            }
         }
           return submit;
}


/* 
Purpose:fire client validation using client validator and if client validation is correct call js function to execute save new Object
this method is called at ajaxable save and new button
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 6/5/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   */
function validateSaveAndNewClientValidator()
{
 
     var valid=validatemyForm();
     if(valid){
      saveAndNew();
     }
     else {
      return false;
     }
  
      
   return true;  
}


/* 
Purpose:alert message in unicode form
@param x: message that will be converted to unicode
Creation/Modification History :
   1.1 - Developer Name: Ahamed Abdelfatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
    function alertuni(x) {
           separator =";&#";
           stringToSplit = x;
           if(stringToSplit.substring(0,2)=="&#") {
               for ( i=0;i<20;i++)
                    stringToSplit = stringToSplit.replace(/\s+/,"&#32;");
               
               stringToSplit = stringToSplit.substring(2,stringToSplit.length-1);
               arrayOfStrings = stringToSplit.split(separator);
               s2="";
               
               for(counter=0;counter<arrayOfStrings.length;counter++) {       
                    s2+=String.fromCharCode(arrayOfStrings[counter]);
               }
               
               alert(s2);
           } else {
                alert(stringToSplit);
           }
       }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
/* 
Purpose:Appear confirmation unicode confirmation message
@param x:unicode code message will be appear in confirmation message
Creation/Modification History :
   1.1 - Developer Name: Ahamed Abdelfatah
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
     function confirmuni(x)
                {
                        separator =";&#";
                        separator2 ="; &#";
                        s2="";
                        stringToSplit = x;
                        if(stringToSplit.substring(0,2)=="&#")
                        {
                                //stringToSplit+="&#32;";
                                //stringToSplit = stringToSplit.replace(/[\s]{1}/,"&#32;");
                                for ( i=0;i<20;i++)
                                        stringToSplit = stringToSplit.replace(/\s+/,"&#32;");
                                
                                //alert(stringToSplit);
                                stringToSplit = stringToSplit.substring(2,stringToSplit.length-1);
                                arrayOfStrings = stringToSplit.split(separator);		
                                //alert(arrayOfStrings);
                                for(counter=0;counter<arrayOfStrings.length;counter++)
                                {		
                                        s2+=String.fromCharCode(arrayOfStrings[counter]);
                                }
                                //for(i=0;i<10;i++)
                                //	s2 = " "+s2;
                                return confirm(s2);
                        }
                        else
                        {
                                return confirm(stringToSplit);
                        }
                } 
///////////////////////////////////////////////////////////////////////////////////////////////////////   
/* 
Purpose: validate url   (this function is missing doesn't validate urls which end with .eg .kw)Redendent.
@param id : the id of validated value
@param output: the span /output will display error message 
@param msg: the message will be diaplayed
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function validateURL(id,output,msg)
{

  var URL;
  
  if(document.getElementById(id)!=null)
     URL=document.getElementById(id).value;
     
  var reURL=/^(([h]{1})+([t]{2})+([p]{1})+([:])+([\/\/])+([w]{3})+([a-z-0-9\.])+(\.[a-z]{3}))$/;

  if(URL==''){
       if(document.getElementById(output)!=null)
         document.getElementById(output).innerHTML = '';
      return true;
    }

 if(URL.match(reURL)==null){
   if(document.getElementById(output)!=null)
      document.getElementById(output).innerHTML =msg;
      
      return false;
      }
 if(document.getElementById(output)!=null)  
  document.getElementById(output).innerHTML ='';
      return true;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:validate mail
@param id: the id of validated mail
 @param output : the span /output will display error message 
 @param msg : the message will be diaplayed
 Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/


function validateMail(id,output,msg){
 var mail;
  if(document.getElementById(id)!=null)
      mail=document.getElementById(id).value;
      
  var reEmail = /^[a-z][a-z_0-9\.]+@[a-z-0-9\.]+\.[a-z]{3}/i;
  
  if(mail==''){
   if(document.getElementById(output)!=null)
     document.getElementById(output).innerHTML = '';
  
   return true;
   }
    
 if(mail.match(reEmail)==null){
     if(document.getElementById(output)!=null)
      document.getElementById(output).innerHTML =msg;
     return false;
     }
     
 if(document.getElementById(output)!=null) 
  document.getElementById(output).innerHTML ='';
  
 return true;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:check that input text follow given patters
@param id: the id of validated value
@param pattern: the pattern that submitted value will validated across it  
@param output: the span /output will display error message 
@param msg: the message will be diaplayed
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function checkOfPattern(id,pattern,output,msg){
var str;

  if(document.getElementById(id)!=null)
      str=document.getElementById(id).value;
        
       if(str!=null && str != '' && str.length > 0){
         var exist=false;
           for(var i=0;i<str.lenght;i++){
              for(var j=0;j<pattern.length;j++){
                     if (str.charAt(i)!=pattern.charAt(j)) {
                     exist = true; 
                     break; 
                    }   

                   }
            }

      if(exist){
       if(document.getElementById(output)!=null)
          document.getElementById(output).innerHTML = msg;
          return false;    
           }
         }
 if(document.getElementById(output)!=null)
   document.getElementById(output).innerHTML = '';
  
 return true;    

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:check that input text does't follow given patter
@param id , the id of validated value
@param pattern , the pattern that submitted value will validated across it  
@param output , the span /output will display error message 
@param msg , the message will be diaplayed
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/

function checkDoesnotContain(id,pattern,output,msg){
 
 var str;
 
 if(document.getElementById(id)!=null)
   str=document.getElementById(id).value;
        
       if(str!=null && str != '' && str.length > 0){
         var exist=false;
         
           for(var i=0;i<str.lenght;i++){
                 for(var j=0;j<pattern.length;j++){
                     if (str.charAt(i)==pattern.charAt(j)){
                     exist = true; 
                     break; 
                    }   
                   }
                }

          if(exist){
           if(document.getElementById(output)!=null)
             document.getElementById(output).innerHTML = msg;
          return false;    
           }
         }
         
  if(document.getElementById(output)!=null)
     document.getElementById(output).innerHTML = '';
    return true;    

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:check the lenght of the input text less than given value
*@param id , the id of the input text
*@param length ,the length to check against
*@param output ,the id of the div to handle the error
*@param msg , the msg to be displayed to the user
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*  ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function checkLenghtLessthan(id,length,output,msg)
{
 
 var str;
  if(document.getElementById(id)!=null)
     str=document.getElementById(id).value;
        
       if(str==null || str == ''){
        if(document.getElementById(output)!=null)
            document.getElementById(output).innerHTML ='';
        return true;
       }

       if(str.length>length){
         if(document.getElementById(output)!=null)
          document.getElementById(output).innerHTML = msg;
        return false;
         }
if(document.getElementById(output)!=null)
   document.getElementById(output).innerHTML = '';
                 return true;    

}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:check the lenght of the input text greater than given value 
*@param id , the id of the input text
*@param length , the length to check against
*@param output , the id of the div to handle the error
*@param msg , the msg to be displayed to the user
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*  ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function checkLenghtGreaterthan(id,length,output,msg){

 var str;
 if(document.getElementById(id)!=null)
   str=document.getElementById(id).value;
        
       if(str==null || str == ''){
       if(document.getElementById(output)!=null)
         document.getElementById(output).innerHTML = '';
         return true;
       }

       if(str.length<length){
         if(document.getElementById(output)!=null)
           document.getElementById(output).innerHTML = msg;
        return false;
         }
     if(document.getElementById(output)!=null)
        document.getElementById(output).innerHTML = '';

 return true;    

}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:helper functions for the date validation 
@param s :the validate value
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:validate date in the format dd/mm/yyyy you need the helper functions above for this to run correctly
*@param id , the id of the input text
@param dateId ,the ide of date control to be validated
*@param the id of the div to handle the error message
*@param the error message to be displayed
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function isDate(dateId,output,errorMsg){
  
    var dtStr;
    if(typeof dateId == 'string' && document.getElementById(dateId)!=null){
        dtStr = document.getElementById(dateId).value;
    }else if(dateId!=null){
        dtStr = dateId.value
    }
      
    if(dtStr!=''){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
	   if(document.getElementById(output)!=null)	
              document.getElementById(output).innerHTML =errorMsg;
        return false
	}
	if (strMonth.length<1 || month<1 || month>12){
	    if(document.getElementById(output)!=null)
                 document.getElementById(output).innerHTML =errorMsg;
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
            if(document.getElementById(output)!=null)
                  document.getElementById(output).innerHTML =errorMsg;
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
	   if(document.getElementById(output)!=null)	
                 document.getElementById(output).innerHTML =errorMsg;
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
             if(document.getElementById(output)!=null)	
                 document.getElementById(output).innerHTML =errorMsg;
         return false
	}
        }
 if(document.getElementById(output)!=null)	
  document.getElementById(output).innerHTML ='';
         
return true;
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:compare two dates 
*@param start,start date
*@param end ,end date 
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/
function compare2Dates(start,end,outputMessageID,messageText){          
                        
var msg=messageText;
var confrm;
var startDate ;
var endDate;

    if(document.getElementById(start)!=null)
        startDate=document.getElementById(start).value;
        
    if(document.getElementById(end)!=null)    
        endDate= document.getElementById(end).value;

    if(endDate=='' || endDate == null){
     if(document.getElementById('outputMessageID')!=null)
        document.getElementById('outputMessageID').innerHTML = '';
     return true;                     
    }
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
      if(document.getElementById(outputMessageID)!=null)
        document.getElementById(outputMessageID).innerHTML = '';
     return true;
     }                        
   else{
    if(document.getElementById(outputMessageID)!=null)
        document.getElementById(outputMessageID).innerHTML = msg;
    return false;
    }
                   
}      

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:fire client validator
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
  function checkValidation() {
    if(doValidate)
    return validate();
    return true;
    
    }
/* 
Purpose:ignore client validation 
Creation/Modification History :
   1.1 - Developer Name:  Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/ 
    function ignoreValidation() {
    doValidate=false;
    
    }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
 /* 
Purpose:is used for search form validation to check all user enters are validate ,not contain special charatcer ,specify search length has to be more than 2 characters
in case user search by description and if user searches by code then this function validate this code is integer ,.........................
*@param name ,the id of the input text
*@param act , operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg , the msg to be displayed to the user
 *@param form ,is search form
Creation/Modification History :
   1.1 - Developer Name: Yassmine Ali
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
 function determine(name,act,output,msg,form)
 {
  var elems = form.elements;
        var value1;
        for (var i=0; i<2; i++) {
            if(elems[i].type == "radio") {
                if(elems[i].checked) {
                  value1=elems[i].value;
                }
            }
        }
        if(value1 == 'true')
        {
        var y  = form_Check(name,act,output,msg);
        return y;
        }
        else
        {
         var y=isPositiveInteger(name,act,output,msg);
         return y;
        }
 }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:is used by determine function ,this function is used to validate if submited value is integer or not
*@param name , the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg, the msg to be displayed to the user
Creation/Modification History :
   1.1 - Developer Name: 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/    
/* 
Purpose:is used by determine function ,this function is used to validate if submited value is integer or not
*@param name , the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg, the msg to be displayed to the user
*@required,   required  if you don't need name as required send this parameter with 'false' value otherwise  it will be required
Creation/Modification History :
   1.1 - Developer Name: Assmaa Omar
   1.2 - Date: 23/6/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add required parameter
*  ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null
*/ 
function isPositiveInteger(name,act,output,msg) {
       var pattern = "0123456789" ;     
       var str=document.getElementById(name).value;  
      if(str == '')
      {
       if(appLocale == "en")
         {
                document.getElementById(output).innerHTML = "please enter  number";
        }
          else if(appLocale == "ar")
           {
           document.getElementById(output).innerHTML="تأكد من ملء الحقل" ;
           }
                
                return false;
       }
      
       else{
       var i = 0; 
       do {
            var pos = 0; 
             for (var j=0; j<pattern.length; j++) 
                if (str.charAt(i)==pattern.charAt(j)) {
                    pos = 1; 
                    break; 
                } 
         i++; 
       } while (pos==1 && i<str.length) 
       if (pos==0 ) 
       {
       if( msg ==null)
       {
               if(appLocale == "en")
         {
          document.getElementById(output).innerHTML="Please Enter Number";
          }
       else if(appLocale == "ar")
           {
           document.getElementById(output).innerHTML="?�?�?� ??U??� U�?�U�U�?� ?�?�?�???�?�?�U� ?�?�?��?�?" ;
           }
       }
       else{
        document.getElementById(output).innerHTML=msg;
       }
           return false;

       }
        return true;
       }
       return true;
} 

//*************** //////////////////////////Abo el 7assan Functions//////////////////////////////////////////////////*************
/* 
Purpose:validate input text empty or not 
*@param inputTextValue :text value 
*@param errorId :ID of output text that show error message
Creation/Modification History :
   1.1 - Developer Name:Abo el Hassan 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:generalizing the method
* 
*/      
function checkEmpty2(inputTextValue,errorId) {
       var reWhitespace = /^\s+$/;
       if((inputTextValue == null) || (inputTextValue.length == 0) || reWhitespace.test(inputTextValue)) {
       if(document.getElementById(errorId) !=null){
           document.getElementById(errorId).innerHTML = missingField;
           }
            return true;
       }
       if(document.getElementById(errorId) !=null){
       document.getElementById(errorId).innerHTML = '';
       }
       return false;
   }
   ///////////////////////////////////////////////////////////
  /* 
Purpose:validate input text is greater than specific length
*@param inputTextValue :text value 
*@@param errorId :ID of output text that show error message
*@param length: the length that input text has to be greater than
Creation/Modification History :
   1.1 - Developer Name:Abo el Hassan 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   /////////////////////////////////////////
   1.1 - Developer Name:Nora Ismail 
   1.2 - Date: 
   1.3 - Creation/Modification:Modification      
   1.4-  Description:generalizing the method
* 
*/     
  function checkStringLength2(inputTextValue,errorId,length) {
  if(length != null){
  
      if(inputTextValue.length < length) {
      
      if(document.getElementById(errorId) !=null){
          document.getElementById(errorId).innerHTML = lessThanTwo;
       }   
      return true;
      }
      if(document.getElementById(errorId) !=null){
      document.getElementById(errorId).innerHTML = '';
      }
  
      }
 return false;
  }
    /* 
Purpose:validate input text is greater than specific length
*@param inputTextValue :text value 
*@@param errorId :ID of output text that show error message
*@param pattern: the pattern that has not be in submitted string
Creation/Modification History :
  1.1 - Developer Name:Abo el Hassan 
  1.2 - Date: 
  1.3 - Creation/Modification:Creation      
  1.4-  Description:
  /////////////////////////////////////////
  1.1 - Developer Name:Nora Ismail 
  1.2 - Date: 
  1.3 - Creation/Modification:Modification      
  1.4-  Description:generalizing the method
/////////////////////////////////////////
  1.1 - Developer Name:Nora Ismail 
  1.2 - Date: 20/3/2008
  1.3 - Creation/Modification:Modification      
  1.4-  Description:to allow that submitted pattern is null
* 
*/     
   function checkStringOnly2(inputTextValue,errorId,pattern) {
   
    if(pattern !=null ){
          for (var i = 0; i < inputTextValue.length; i++) {
              if(pattern.indexOf(inputTextValue.charAt(i)) != -1 || inputTextValue.charAt(0) == ' ') {
               if( document.getElementById(errorId) !=null){
                  document.getElementById(errorId).innerHTML = charsOnly;
                }  
                  return false;
              }
          }
          if(document.getElementById(errorId) !=null){
          document.getElementById(errorId).innerHTML = '';
          }
      }
      return true;
  }
  
   
   ////////////////////////////////////////////////////////////////
/* 
Purpose:validate input text has to be empty or contain special character from submitted pattern
*@param inputTextID :the id of the input text
*@param outputMessageID :ID of output text that show error message
*@param pattern:pattern of special character
*@param length: the length that input text has to be greater than
Creation/Modification History :
  1.1 - Developer Name:Abo el Hassan 
  1.2 - Date: 
  1.3 - Creation/Modification:Creation      
  1.4-  Description:
  /////////////////////////////////////////
  1.1 - Developer Name:Nora Ismail 
  1.2 - Date: 
  1.3 - Creation/Modification:Modification      
  1.4-  Description:
* 
*/      
function validateInputText(inputTextID,outputMessageID,pattern,length,sucessMessageId) {

var defaultPattern=null;
var defaultLength=null;
var inputTextValue;
    
    if(document.getElementById(BR_MSG_TREE_ADD_ID) != null){        
        document.getElementById(BR_MSG_TREE_ADD_ID).style.display = 'none';
    }
    if(document.getElementById(sucessMessageId) != null){
       document.getElementById(sucessMessageId).innerHTML = '';  
    }
    if(document.getElementById(ERROR_MSG_TREE_ADD_ID) != null){
        document.getElementById(ERROR_MSG_TREE_ADD_ID).innerHTML = '';  
    }
    if(document.getElementById(outputMessageID) !=null){
        document.getElementById(outputMessageID).value = '';
    }
    if(document.getElementById(inputTextID)!=null)
        inputTextValue= document.getElementById(inputTextID).value;
    if(checkEmpty2(inputTextValue,outputMessageID))
      return false;
    if(length==null){
        if(checkStringLength2(inputTextValue,outputMessageID,defaultLength))
            return false;
    }
    else{
        if(checkStringLength2(inputTextValue,outputMessageID,length))
        return false;
    }
    if(pattern==null){
             return checkStringOnly2(inputTextValue,outputMessageID,defaultPattern);
    }
    else {
        return checkStringOnly2(inputTextValue,outputMessageID,pattern);
    }
       
    return true;
  }

//////////////////////////////////////////////////////////////////////////
/* 
Purpose:validate input text has to be empty or contain special character from submitted pattern in case save and new button to test validaty and  make it button  ajaxable 
*@param fieldsIds :the ids of the input texts that have to be validated
*@param outputMessageID :ID of output text that show error message
*@param pattern:pattern of special character
*@param length: the length that input text has to be greater than
Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
   //////////////////////////////////////////
   
   Creation/Modification History :
   1.1 - Developer Name:Nora Ismail
   1.2 - Date: 12/3/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description: make function generic by passing fields ids as parameters
* 
*/    
String.prototype.contains = function(t) { return this.indexOf(t) >= 0 ? true : false }
function validateSaveAndNew(fieldsIds,outputMessageID,pattern,length,sucessMessageId)
{
 var fieldArrayIds=fieldsIds.split(',');
 var valid=true;       
  if(fieldArrayIds.length==1)
        {
         valid=validateInputText(fieldArrayIds,outputMessageID,pattern,length,sucessMessageId);
        }
   else if(fieldArrayIds.length > 1)
        {
          for(var j=0; j<fieldArrayIds.length; j++)
                {
                  if(!validateInputText(fieldArrayIds[j],outputMessageID,pattern,length,sucessMessageId))
                  {
                  valid=false;
                  break;
                  }
             
             }
        }
     if(valid)
        {
         saveAndNew();
         }
     else
         {
         return false;
         }
    return true;
}




function ajaxClientValidate(){
var valid=true;       
 valid=validatemyForm();
     if(valid)
        {
         saveAndNew();
         }
     else
         {
         return false;
         }
    return true;
}

//////////////////////////////////////////////////////////////////////////////////
/* 
Purpose:compare two dates
Creation/Modification History :
 1.1 - Developer Name:
 1.2 - Date: 15/4/2008
 1.3 - Creation/Modification:Modification      
 1.4-  Description:
* ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date: 6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/
function compareOptionalTwoDates(startDateID,endDateID,OutPutID,requiredDateMessage,compareDatesMessage,requiredFlag){
var startDate=null ;
var endDate=null;
var reuiredFlagValue=true;
var inactiveAdvancedSearch=true;
if(requiredFlag=="false")
reuiredFlagValue=false;
                if(document.getElementById(startDateID)!=null){
                  startDate=document.getElementById(startDateID).value;
                 
                }
                if(document.getElementById(endDateID)!=null){
                endDate=document.getElementById(endDateID).value;
                
                }
                if(reuiredFlagValue)
                {
                if( (endDate != null && endDate !="") && (startDate== null || startDate=="" )){
                   if(document.getElementById(OutPutID)!=null)
                     document.getElementById(OutPutID).innerHTML=requiredDateMessage;
                  return false;
                  }
                if(endDate=="" || endDate == null){
                  if(document.getElementById(OutPutID)!=null)
                     document.getElementById(OutPutID).innerHTML = '';
                   return true;                     
                }
                }
                
                else if(startDate == null){
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
                    if(document.getElementById(OutPutID)!=null)                       
                       document.getElementById(OutPutID).innerHTML = '';
                       return true;
                   }                        
                    else{
                    if(document.getElementById(OutPutID)!=null){
                         document.getElementById(OutPutID).innerHTML = compareDatesMessage;
                         document.getElementById(OutPutID).style.fontFamily='Tahoma';
                         document.getElementById(OutPutID).style.fontSize='8pt';
                         }
                        return false;
                       }
                  }     
            if(document.getElementById(OutPutID)!=null)     
                document.getElementById(OutPutID).innerHTML = '';        
return true;
}

//*************** //////////////////////////Abo el 7assan Functions//////////////////////////////////////////////////*************          
/* 
Purpose:validate submited value contain special character based on operation type(Add,Search)
*@param name ,the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg ,the msg to be displayed to the user
Creation/Modification History :
   1.1 - Developer Name:Sherif Omer 
   1.2 - Date: 14-06-2009
   1.3 - Creation/Modification:Creation      
   1.4-  Description: THE FOLLOWING TWO FUNCTIONS USED BY disableDragAndDrop TO DISABLE DIMMY USER ACTION TO DRAG TEXT INTO INPUT
* 
*/   

function isEmpty(s){
 return ((s == null) || (s.length == 0))
}

function isFloat(s)
{   
if (isEmpty(s)) 
      if (isFloat.arguments.length == 1) return defaultEmptyOK;
      else return (isFloat.arguments[1] == true);
   return reFloat.test(s)
}



function disableDragAndDrop(elemId)
{
if(elemId.value != null)
{
    
    if(!isFloat(elemId.value))
        elemId.value="";
}

}
/*
purpose:validate that unitl date(year ,month) greater than from date(year ,month)
and if you wants to handle equalty of months when unitl year and from year equal send equaltyMonthsFlag paramter with true
*/
function compareTwoDatesYearMonth(firstMonthID, firstYearID, secondMonthID, secondYearID,msgId,msgContent,equaltyMonthsFlag) {

    var firstMonth =''; 
    var secondMonth = '';
    var firstYear = '';
    var secondYear ='' ;
    var virtualValue = -100;
    
    if(document.getElementById(firstMonthID) !=null)
        firstMonth =document.getElementById(firstMonthID).value;
    if(document.getElementById(firstYearID) !=null)   
        firstYear = document.getElementById(firstYearID).value;
    if(document.getElementById(secondMonthID) !=null)
        secondMonth =document.getElementById(secondMonthID).value;
    if(document.getElementById(secondYearID) !=null)
         secondYear =document.getElementById(secondYearID).value;
         
   if(document.getElementById(msgId) !=null)
         document.getElementById(msgId).innerHTML = '';
                 
    if(firstMonth == '' || firstMonth==virtualValue || secondMonth == '' || secondMonth==virtualValue ||  firstYear == '' || firstYear==virtualValue || secondYear == '' || secondYear == virtualValue){
        return true;
    }
    
    firstMonth = parseInt(firstMonth);
    secondMonth = parseInt(secondMonth);
    firstYear = parseInt(firstYear);
    secondYear = parseInt(secondYear);

    if(secondYear < firstYear){
        if(document.getElementById(msgId) !=null)
             document.getElementById(msgId).innerHTML = msgContent;
        return false;
    }else if(secondYear == firstYear)
      {
           if((equaltyMonthsFlag==false) && (secondMonth == firstMonth)){
                 if(document.getElementById(msgId) !=null)
                     document.getElementById(msgId).innerHTML = msgContent;
                return false;
        }
       else if(secondMonth < firstMonth){
            if(document.getElementById(msgId) !=null)
                document.getElementById(msgId).innerHTML = msgContent;
            return false;
        }
    }
    return true;
}

//////////////////////////////////////////////// Function to compare between year and year of date  ///////////////////////////

function compareYearWithYearofDate(yearId,dateId,msgId,msgContent)
{
        if(document.getElementById(dateId)!=null && document.getElementById(yearId)!=null)
        {
            var regYear=document.getElementById(dateId).value;
            var yearSplit= regYear.split("/");
            if(document.getElementById(yearId).value == yearSplit[2] )
            {
            document.getElementById(msgId).innerHTML='';
            return true;
            }
            else
            {
            document.getElementById(msgId).innerHTML=document.getElementById(msgContent).value;
            return false;
            }
        }
        
        return true;
    

}


////////////////////////////////////////////// End of Function compare /////////////////////////////////////////////////////////


//*************** //////////////////////////Abo el 7assan Functions//////////////////////////////////////////////////*************          
/* 
Purpose:validate submited value contain special character based on operation type(Add,Search)
*@param name ,the id of the input text
*@param act ,operation will be performed (Add/Search)
*@param output ,the span/output id that will display message error
*@param msg ,the msg to be displayed to the user
Creation/Modification History :
   1.1 - Developer Name:Sherif Omer 
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/      

 function form_Check(name,act,output,msg)
           {
            var txtBox = document.getElementById(name).value;
               if(txtBox == '' )
               {
                        document.getElementById(output).innerHTML = msg;
                        
                 return false;
                 }
           else
             {
              var numb = '';  
              if(act == "ADD")
              {
              numb ='0123456789!@#$^&*()+=_-[]\\;,.{}|/\":<>?؟\'';
               numb += '%~';
           
               }
               else if(act == "SEARCH")
               {
               numb ='~0123456789!@#$^&*()+=_-[]\\;,.{}|/\":<>?؟\'';
               if(txtBox.length < 2)
               {
                    document.getElementById(output).innerHTML = msg;
                    return false;
                    }
                    
               
               
               }
                  // TextBox has value    
                 
               for (var i = 0; i < document.getElementById(name).value.length; i++)
               {
                   if (numb.indexOf(document.getElementById(name).value.charAt(i)) != -1 || document.getElementById(name).value.charAt(0) == ' ') 
                   {
                        if(appLocale == "en")
                        {
                            document.getElementById(output).innerHTML  = "You has Entered a special character.\n\t Please remove them and try to search again";
                        }               
                        else if(appLocale == "ar")
                        {

                             document.getElementById(output).innerHTML  = "خطأ غير مسموح بأستخدام أحر�? خاصة ";
                        }                  
                       
                         return false;
                       
                   }
                                
                }
                
            
               }
                                             
          }
          
          
          
          

