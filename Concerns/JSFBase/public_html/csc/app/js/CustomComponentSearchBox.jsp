 <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 
 <f:view locale="#{shared_util.locale}">
 <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
 /*
  validation messages will appear if user enter wrong data 
 */
    var selectOneRadio = '${globalResources.selectOneRadio}';
    var missingField = '${globalResources.missingField}';
    var lessThanTwo = '${globalResources.SearchMsg}';
    var numbersOnly = '${globalResources.ValidateNumbers}';
    var charsOnly = '${globalResources.searchCharcterOnly}';
    
    
    
/* 
Purpose:hide search div for custom component when user press cancel
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
      function hideDiv() {
      
      try{
            obj = document.getElementById('iFrame');
            obj.style.visibility = 'hidden';
        } 
        catch(exception) {
        }
       // window.blocker.style.visibility="hidden";
        window.divSearch.style.visibility="hidden";
        
        if(document.getElementById('errorMessage')!=null) 
          document.getElementById('errorMessage').innerHTML = '';
        
        var cancelSearchArr = document.getElementsByClassName("OB_cancleSearch");
        if (cancelSearchArr.length == 0){
        if(document.getElementById('searchString')!=null) 
            document.getElementById('searchString').value='';
        }
          
      window.parent.nav_btn.disabled=false;     
    }
    
/* 
Purpose:validate user input in custom component search box 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
   *   ==================================================================
   1.1 - Developer Name:Sherif Muhammed Omar
   1.2 - Date:  15/01/2009
   1.3 - Creation/Modification:Modification      
   1.4-  Description: To fix search by code in global search using special characters add a hidden field named allowSpecialCharInSrchByCode 
                      and set its value to true, this will stop normal code search and use string search function.
*/ 
function checkInput() {
    var radio;
    var searchStr;
    var seachValidationType;
    var allowSpecialCharInSrchByCode;
    var validateLengthInSrchByCode = false;
        
    if(document.getElementById('allowSpecialCharInSrchByCode') != null){
        if(document.getElementById('allowSpecialCharInSrchByCode').value == 'true')
            allowSpecialCharInSrchByCode = true;
    }
    if(document.getElementById('validateLengthInSrchByCode') != null){
        validateLengthInSrchByCode = true;
    }
        
    if(document.forms['myForm'] != null){
        radio = document.forms['myForm'].radioBtn;            
    } else if (document.forms['treeform'] != null){
        radio = document.forms['treeform'].radioBtn;
    }
         
    if( !radio[0].checked & !radio[1].checked) {
        if(document.getElementById('errorMessage')!=null)
            document.getElementById('errorMessage').innerHTML = selectOneRadio;
            return false;
    }
        
    if(document.getElementById('errorMessage')!=null)
        document.getElementById('errorMessage').innerHTML = '';
           
    if(document.getElementById('searchString')!=null)
        searchStr= document.getElementById('searchString').value;
        
    if(checkEmpty(searchStr))
        return false;
         
    if(document.getElementById('seachValidationType')!=null)
        seachValidationType=document.getElementById('seachValidationType').value;
         
    if(radio[0].checked ) {
        if(seachValidationType !=null && seachValidationType == '0'){
            var valid = true;
                
            if(!allowSpecialCharInSrchByCode){
                valid = checkNumber(searchStr);
                if(valid && validateLengthInSrchByCode){
                    valid = checkSrchCodeLength(searchStr);
                }
            } else if(allowSpecialCharInSrchByCode){
                valid = ! checkStringLength(searchStr);
                if(valid){
                    valid = checkStringOnly(searchStr);
                }
            }
            return valid;
        }
    } else if(radio[1].checked) {
        if(checkStringLength(searchStr))
            return false;
        return checkStringOnly(searchStr);
    }
    return true;        
}
    
/* 
Purpose:validate user input in custom component search box 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*   ==================================================================
   1.1 - Developer Name:Khalid Mohie
   1.2 - Date:  5/Nov/2008
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add boolean parameter to if code is number or string
                   if true then that function will validate if code is number , if false , funcation will not validate code is number
*/ 
   function checkInput_Gen(chkCode,radioId) {
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
           
       if(document.getElementById('searchString')!=null)
         searchStr= document.getElementById('searchString').value;
        
        if(checkEmpty(searchStr))
         return false;
         
      if(document.getElementById('seachValidationType')!=null)
         seachValidationType=document.getElementById('seachValidationType').value;
         
        if(radio[0].checked ) {
            if(seachValidationType !=null && seachValidationType == '0'){
              if(chkCode=='true'){
                    return checkNumber(searchStr);
              }     
            }
          }
        else if(radio[1].checked) {
          if(checkStringLength(searchStr))
            return false;
            return checkStringOnly(searchStr);
        }
        return true;
        
    }    
    
/* 
Purpose:check user enter data and submitted value not empty
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* *   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/ 
    function checkEmpty(searchStr) {
        if((searchStr == null) || (searchStr.length == 0)) {
          if(document.getElementById('errorMessage')!=null)
            document.getElementById('errorMessage').innerHTML = missingField;
         return true;
        }
     if(document.getElementById('errorMessage')!=null)
        document.getElementById('errorMessage').innerHTML = '';
    
     return false;
    }
    
    
/* 
Purpose:check the length of the eneterd text,it's not allwoed fot the user to search with string with length less than 2 characters
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* * *   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/
    function checkStringLength(searchStr) {
        if(searchStr.length < 2) {
         if(document.getElementById('errorMessage')!=null)
            document.getElementById('errorMessage').innerHTML = lessThanTwo;
            return true;
        }
        if(document.getElementById('errorMessage')!=null)
          document.getElementById('errorMessage').innerHTML = '';
          
        return false;
    }
    
/* 
Purpose:validate if submited value is integer or not
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/
    function checkNumber(searchStr) {
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
        if(document.getElementById('errorMessage')!=null)
            document.getElementById('errorMessage').innerHTML = numbersOnly;
            return false;
        }
       if(document.getElementById('errorMessage')!=null)
         document.getElementById('errorMessage').innerHTML = '';
        return true;
    }
  
 
function checkSrchCodeLength(searchStr){
    var hiddenField = document.getElementById('validateLengthInSrchByCode').innerHTML;
    var params = hiddenField.split(':');
    if(searchStr.length != params[0]){
        if(document.getElementById('errorMessage')!=null){
            document.getElementById('errorMessage').innerHTML = params[1];
            return false;
        }
    }
    if(document.getElementById('errorMessage')!=null)
        document.getElementById('errorMessage').innerHTML = '';
    return true;
}
  
/* 
Purpose:check if entered data is string
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed
   1.2 - Date: 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*   ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  2/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/
  
    
    function checkStringOnly(searchStr) {
        
        var numb = null;
        var reWhitespace = /^\s+$/;
        if((searchStr == null) || (searchStr.length == 0) || reWhitespace.test(searchStr)) {
          if(document.getElementById('errorMessage')!=null)
             document.getElementById('errorMessage').innerHTML = missingField;
            return false;
        }
        else{
        for (var i = 0; i < searchStr.length; i++) {
            if(numb.indexOf(searchStr.charAt(i)) != -1 || searchStr.charAt(0) == ' ') {
               if(document.getElementById('errorMessage')!=null)
                  document.getElementById('errorMessage').innerHTML = charsOnly;
                return false;
            }
        }
        }
        if(document.getElementById('errorMessage')!=null)
          document.getElementById('errorMessage').innerHTML = '';
        return true;
    }
  
    </f:view>
