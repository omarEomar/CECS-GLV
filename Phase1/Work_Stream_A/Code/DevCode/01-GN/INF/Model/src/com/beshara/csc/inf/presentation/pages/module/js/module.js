function validateCodeNameSrchParamterForKwMap(radioId,searchQueryID,isSrchByCodeSpecial,errMsgId) {
        var radio;
        var searchStr; 
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