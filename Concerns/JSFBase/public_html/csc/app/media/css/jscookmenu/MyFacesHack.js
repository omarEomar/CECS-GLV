// this part done to handle the  menu items that have  javascript actions depending on Selection Flag.
//Author:Yassmine Ali.
function disabledAndEnabledActionTable(array,index)
{
 var indexActionValue=array.split('#');
 if(indexActionValue[0]==index){ 
 
 var selectedNumber=document.getElementById("selectedNumber").value;
 if(indexActionValue[2] == '0')
 {
    eval(indexActionValue[1]);
 }
if(indexActionValue[2] == '1' && selectedNumber == '1' )
 {
    eval(indexActionValue[1]);
 }
 if(indexActionValue[2] == '2' && selectedNumber != '0' )
 {
    eval(indexActionValue[1]);
 }
}
}

function cmItemMouseUp (obj, index)
{   
//    if( ! checkDisableMenus(index))
//        return false;
    
     /*
           his part done to handle the  menu items that have  javascript actions I do it through hidden field in the page 
           hat hold yhe index and corresponding javascript function
       */
       
       var pageType=document.getElementById('pageType').value;
      if(  document.getElementById('indexAction') != null )   
           if(  document.getElementById('indexAction').value!=''){
             var indexAction=document.getElementById('indexAction') .value;
             var indexActionArray=indexAction.split('$');
             for(var i=0;i<indexActionArray.length ; i++){     
             if(pageType == '0'){
             disabledAndEnabledActionTable(indexActionArray[i],index);
             }
             else if(pageType == '1'){
       //     disabledAndEnabledActionTree(indexActionArray[i],index);
             }
             
               
             }    
            }          
/* 
Purpose:handle java script actions on jscookmenu when user mouse up on menu items,this is occured embeded ,in other words 
jscook menu component not contains java script event attribute that take js action
Creation/Modification History :
  1.1 - Developer Name:there is no developer name ,it is downloaded function 
  1.2 - Date: 
  1.3 - Creation/Modification:Creation      
  1.4-  Description: 
////////////////////////////////////////////////////////////////////////////////////////////////////////
Creation/Modification History :
  1.1 - Developer Name: Assama Omr
  1.2 - Date: 
  1.3 - Creation/Modification:Modification      
  1.4-  Description: handle java script actions on jscookmenu  either Ajax Request or Not Ajax Request 
* 
*/

function cmItemMouseUp (obj, index)
{   
//    if( ! checkDisableMenus(index))
//        return false;
      
/*
           his part done to handle the  menu items that have  javascript actions I do it through hidden field in the page 
           hat hold yhe index and corresponding javascript function
       */
      if(  document.getElementById('indexAction') != null )   
           if(  document.getElementById('indexAction').value!=''){
             var indexAction=document.getElementById('indexAction') .value;
             var indexActionArray=indexAction.split('$');
             for(var i=0;i<indexActionArray.length ; i++){              
                var indexActionValue=indexActionArray[i].split('#');
                 if(indexActionValue[0]==index){ 
                       eval(indexActionValue[1]);
                   }
             }    
            }          
/*
     this part is for calling the javascript function that handle the ajaxRequest for jsCookMenu
      if i have ahidden field in the page whith the id index so it's values 
      represent  the indecis that will fire the ajaxRequest using the generated 
      function handleMenuAjaxAction() with thye current index as aparameter 
      if there is no components in the page with the id index or it's value 
      is null  so all the menu items will fire the ajaxRequest 
*/      
  if(  document.getElementById('menuIndex') != null ){
    var value=document.getElementById('menuIndex').value;
       if( value.length!=0){
         var indexArray=value.split('$');
         for(var i=0;i<indexArray.length ; i++){
            if(indexArray[i]==index){   
               
                handleMenuAjaxAction(index);  
             }
         }    
        }  
       }

    var item = _cmItemList[index];

    var link = null, target = '_self';

    if (item.length > 2)
        link = item[2];
    if (item.length > 3 && item[3])
        target = item[3];

    if (link != null)
    {
    
        // changes by Richard J. Barbalace
        if (link.match(/^\w*:A\]\w*:\/\//) != null ) {
            // Link is a URL
            link = link.replace(/^\w*:A\]/, "");  // Remove JSF ID
            window.open (link, target);
        } else if (link.match(/^\w*:A\]\w*:/) != null ) {
            // Link is a script method
            link = link.replace(/^\w*:A\]\w*:/, "");  // Remove JSF ID
            eval(link);
        } else {
            // Link is a JSF action
            var dummyForm = document.forms[target];
            dummyForm.elements['jscook_action'].value = link;
            dummyForm.submit();
        }
    }

    var prefix = obj.cmPrefix;
    var thisMenu = cmGetThisMenu (obj, prefix);

    var hasChild = (item.length > 5);
    if (!hasChild)
    {
        if (cmIsDefaultItem (item))
        {
            if (obj.cmIsMain)
                obj.className = prefix + 'MainItem';
            else
                obj.className = prefix + 'MenuItem';
        }
        cmHideMenu (thisMenu, null, prefix);
    }
    else
    {
        if (cmIsDefaultItem (item))
        {
            if (obj.cmIsMain)
                obj.className = prefix + 'MainItemHover';
            else
                obj.className = prefix + 'MenuItemHover';
        }
    }
}
