/*
when user select check all button =>it makes all check boxes selected 
*/
function setAllDiv(checkAllId, arrayId, listSize ,hiddenId,noOfTableRowId) {


   var object;
   var pageIndex;
   var noOfTableRows;
   
    
   if(document.getElementById(checkAllId) != null)
          object = document.getElementById(checkAllId);
    
    if(document.getElementById(listSize)!=null)
         pageIndex = document.getElementById(listSize).value;
      
      
     
     if(document.getElementById(noOfTableRowId)!=null)
     noOfTableRows = document.getElementById(noOfTableRowId).value;
      
      
     if(document.getElementById(hiddenId) != null)
       pageIndex = document.getElementById(hiddenId).value;
        
 
        
        
        
        
   for (i = 0; ; i++) {
       id = arrayId + '[' + i + ']';
        
       if (document.getElementById) {
           elem = document.getElementById(id);
            
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
/*
when all check boxes selected =>it reflected that check all button
*/
function checkCheckAllDiv(arrayId,listSize,checkAll,pageIndexId,noOfTableRowId) {

    var pageIndex;
    var noOfTableRows ;
    
      
        
       if(document.getElementById(listSize)!=null)
         pageIndex = document.getElementById(listSize).value;
        
        if(document.getElementById(pageIndexId) != null && document.getElementById(pageIndexId).value != '')
            pageIndex = document.getElementById(pageIndexId).value;
        
        if(document.getElementById(noOfTableRowId)!=null)
           noOfTableRows = document.getElementById(noOfTableRowId).value;
           
    var checked = 0;
    var checkBoxLength = 0;
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
      
/*
it is called global when user navigate between div pages 
*/
function checkAllCheckBoxDiv(checkBoxId,listSize,checkAll,pageIndexId,noOfTableRowId){

    var arrayId='';
    var pageIndex;
    var noOfTableRows ;
       
       
         arrayId = checkBoxId;
        
     if(arrayId != ''){
         if(document.getElementById(listSize)!=null)
             pageIndex = document.getElementById(listSize).value;
             
     if(document.getElementById(pageIndexId) != null && document.getElementById(pageIndexId).value != '')
         pageIndex = document.getElementById(pageIndexId).value;
         
       
        
     if(document.getElementById(noOfTableRowId)!=null)
          noOfTableRows = document.getElementById(noOfTableRowId).value;
          
        var checked = 0;
        var checkBoxLength = 0;
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
        if(checked == checkBoxLength) {
            if(document.getElementById(checkAll)!=null)
            document.getElementById(checkAll).checked = true;
        } else {
          if(document.getElementById(checkAll)!=null)
            document.getElementById(checkAll).checked = false;
        }
    }
}    
