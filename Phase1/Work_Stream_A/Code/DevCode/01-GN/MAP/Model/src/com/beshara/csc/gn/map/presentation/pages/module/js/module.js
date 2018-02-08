// JavaScript Document
/* 
Purpose:Checking if at least there is one checkBox selected 
@param form, the form
Creation/Modification History :
   1.1 - Developer Name: Sherif.omar
   1.2 - Date: 06-04-2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:inform user that he should select one checkBox
* 
*/   

function validateText(inputTextID,outputMessageID,pattern,length) {
     
     
     var defaultPattern=null;
     var defaultLength=null;
     if(document.getElementById(outputMessageID) !=null)
      {
       document.getElementById(outputMessageID).value = '';
      }
       var inputTextValue = document.getElementById(inputTextID).value;
   
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
        
    function CheckOneCheckBoxSelected(form) {
        
        
        if(document.getElementById('checkedAttrSize')!=null)
            selectedCheckBoxes= document.getElementById('checkedAttrSize').value;

  if(selectedCheckBoxes!=null && selectedCheckBoxes==0){
        document.getElementById('errorConsole').innerHTML="من فضلك، اختر أحد العناصر";
            return false;
   }
//        var counter = 0;
//        var elems = form.elements;
//              
//        for (var i=0; i<elems.length; i++) {
//            if(elems[i].type == "checkbox"  && elems[i].id.indexOf('chk2') >= 0) {
//                if(elems[i].checked) {
//                    counter++;
//                    break;
//                }
//            }
//        }
//        if (counter == 0) {
//            if(appLocale == "en")
//                document.getElementById('errorConsole').innerHTML="Please Choose One."
//            else if(appLocale == "ar")
//            document.getElementById('errorConsole').innerHTML="من فضلك، اختر أحد العناصر";
//            return false;
//        }
        
        return true;
    }
    
     /* 
Purpose:Reset any validation message .
@param form, the form
Creation/Modification History :
   1.1 - Developer Name: Sherif.omar
   1.2 - Date: 07-04-2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:inform user that he should select one checkBox
* 
*/ 
    
    function removeMsg(msgdivId)
    {
     document.getElementById(msgdivId).innerHTML="";
    }
    
/* 
Purpose:when user write number in inputtext before comboBox and press enter 
filter combobox with this number .
added by 3laa Elmasry 31/12/2014
*/  
    
    function filterById(event , inputId , disapledText , errorId , JSMethod ) {
      document.getElementById(errorId).style.display = "none";
          if (event.keyCode == 13 || event.keyCode == 9) {
                      event.cancelBubble = true;
                      event.returnValue = false;
                      event.preventDefault();
                      
                    
                      if(document.getElementById(inputId).value == null || 
                      document.getElementById(inputId).value == ""){
                          document.getElementById("disapledText").value = "";
                 
                      }else{ 
                             JSMethod(); 
                      }
                        
                      }
                      return;
                  }
         
 function showvalidate_msg(inputTextId , outputTextId , buttonId){
              if(document.getElementById(inputTextId).value ==""){
                   document.getElementById(outputTextId).style.display = "inline";
              }else{
                   document.getElementById(outputTextId).style.display = "none";
                   setFocusOnlyOnElement(buttonId);
               }
               
 } 
 
 function enabelIntegerOnlyWithZero(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   