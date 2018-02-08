///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: set the localized messages to be used in this file (assuming only arabic and english are used)
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  3/1/2008
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function setLocalizedMessages(){
 
 if(appLocale == "en") {

   treeRootSelectedMsg="This  Element Can't Be Selected";

 }else{

  treeRootSelectedMsg="�� ���� ������ ��� ������";

 }
}

//////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: alert information message that telling user not allowed to editing selected node 
@param selectedNodeId     the current selected Node of the tree
@param rootNodeValue      the value to represent the tree root
Creation/Modification History :
   1.1 - Developer Name: Aasmaa 
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
//////////////////////////////////////////////////////////////////////////////////////////
    2.1- Developer Name: Aboelhassan
    2.2- Date: 
    2.3- Creation/Modification:Modification
    2.4- Desc: 
* 
*/
function alertRootUpdates(nodeID,rootElementID)
{  
     var appLocale;
     var rootNodeID;
     
    if(document.getElementById("header_frm_form1:header_hidden_locale") != null && document.getElementById("header_frm_form1:header_hidden_locale").value != '')
       appLocale= document.getElementById("header_frm_form1:header_hidden_locale").value;
       
    if(document.getElementById(rootElementID) != null && document.getElementById(rootElementID).value != '') 
     rootNodeID=document.getElementById(rootElementID).value; 
    
    var msg;
    if(rootNodeID==nodeID){  
            if(appLocale == "en"){
                                msg="This  Element Can't Be Updated";
                                }
            else if(appLocale == "ar"){
                                    msg="لا يمكن تعديل هذا العنصر";
                               }
            alert(msg);           
       }
}
//////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: get the id of the selected node dto and set it in hidden variable
@ param id:id the id of the selected node data
@ param nodeid:the name of the hidden field to set the hidden in
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed 
   1.2 - Date:  3/1/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
 function getNode(id,nodeid){
 
    if(document.getElementById(nodeid) != null){
              document.getElementById(nodeid).value = id;
              }   
           
         }
//////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: highlight the selected node and clear the previous selected nodes
@param node:the current id selected node control passed ( this )
@param treeDivId :treeDivId  the id of the div which encapsulates the tree
@param controlName:the name of the control which used as the tree node link (label,link,button)
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed 
   1.2 - Date:  3/1/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/

function reStyle(node,treeDivId,controlName){ 
    
    var currnetNode = null;
    var previousNode = null;
    
    if(node == null){
        var currnetNodeId = document.getElementById('lastNode').value;
        currnetNode = document.getElementById(currnetNodeId);
        node = currnetNode;
    } else {
        currnetNode = document.getElementById(node.id);
    }
    if(currnetNode != null){
        try {
            if(document.getElementById('lastNode') != null){
                var lastNodeId = document.getElementById('lastNode').value;
                previousNode = document.getElementById(lastNodeId);
            }
            
            var oldStyle=currnetNode.style.backgroundColor;
            if(currnetNode != previousNode){
                clearTreeSelectedNodes(oldStyle,treeDivId,controlName);
            }
            if(previousNode){ 
                previousNode.style.backgroundColor = oldStyle;
            }
            
            currnetNode.style.backgroundColor = '#F2CB8A' ;
            
            if(document.getElementById('lastNode') != null){
                document.getElementById('lastNode').value = node.id;
            }
        } catch (e){}
    }
}

//////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: clear all the selected nodes of the tree
@ param oldStyle the style of the unselected tree node
*@param treeDivId  the id of the div which encapsulates the tree
*@param the name of the control which used as the tree node link (label,link,button)
Creation/Modification History :
   1.1 - Developer Name:Aboelhassan hamed 
   1.2 - Date:  3/1/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
*    ==================================================================
   1.1 - Developer Name:Nora
   1.2 - Date:  6/7/2007
   1.3 - Creation/Modification:Modification      
   1.4-  Description:add condition to check accessed element not null 
*/

  function clearTreeSelectedNodes(oldStyle,treeDivId,controlName){
     
  var  elems;
    if(document.getElementById(treeDivId) !=  null) 
         elems=document.getElementById(treeDivId).getElementsByTagName(controlName);
       
       for (var i=0; i<elems.length; i++) {
       
        if(oldStyle==''){
           elems[i].style.backgroundColor='transparent';
          }
         else{
            elems[i].style.backgroundColor=oldStyle;
            }
        }
    
    
    }
          
//////////////////////////////////////////////////////////////////////////////////////////          
/* 
Purpose: Display  the details of the node
Creation/Modification History :
   1.1 - Developer Name:Ahmed Abd El-fatah
   1.2 - Date:  3/1/2008 
   1.3 - Creation/Modification:Creation      
   1.4-  Description:
* 
*/
function findPosY(obj)
  {
    var curtop = 0;
    if(obj.offsetParent)
        while(1)
        {
          curtop += ((obj.offsetTop)-1);
          if(!obj.offsetParent)
            break;
          obj = obj.offsetParent;
        }
    else if(obj.y)
        curtop += ((obj.y)-1);
    return curtop;
  }
  
  
  //////////////////////////////////////////////////////////////////////////////////////////
