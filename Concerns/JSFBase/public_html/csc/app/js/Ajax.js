/*
File Content Description:this file about pure ajax functions that used to send xml request 
and receive response ,this file contains examples to show how to use main ajax functions getReadyStateHandler,newXMLHttpRequest 
*/

////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: initialize new ajax request
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description: 
* 
*/
  function newXMLHttpRequest() {

          var xmlreq = false;
        
          if (window.XMLHttpRequest) {
        
            // Create XMLHttpRequest object in non-Microsoft browsers
            xmlreq = new XMLHttpRequest();
        
          } else if (window.ActiveXObject) {
        
            // Create XMLHttpRequest via MS ActiveX
            try {
              // Try to create XMLHttpRequest in later versions
              // of Internet Explorer
        
              xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
        
            } catch (e1) {
        
              // Failed to create required ActiveXObject
        
              try {
                // Try version supported by older versions
                // of Internet Explorer
        
                xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
        
              } catch (e2) {
        
                // Unable to create an XMLHttpRequest with ActiveX
              }
            }
          }

  return xmlreq;
}
////////////////////////////////////////////////////////////////////////////////////////////
/* 
Purpose: check if user request is executed sucessfully .
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description: 
* 
*/
function getReadyStateHandler(req, responseXmlHandler) {

  // Return an anonymous function that listens to the 
  // XMLHttpRequest instance
  return function () {

    // If the request's status is "complete"
    if (req.readyState == 4) {
      
      // Check that a successful server response was received
      if (req.status == 200) {

        // Pass the XML payload of the response to the 
        // handler function
        responseXmlHandler(req.responseXML);

      } else {

        // An HTTP problem has occurred
        alert("HTTP error: "+req.status);
      }
    }
  }
}

/* 
Purpose:  is a sample function that show how to use two previous functions(newXMLHttpRequest,getReadyStateHandler).
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description: 
* 
*/
function getServersList(){

//using the initial ajax requests 
 var req = newXMLHttpRequest();

 // Set the handler function to receive callback notifications
 // from the request object
 var handlerFunction = getReadyStateHandler(req, process);
 req.onreadystatechange = handlerFunction;
 
 // Open an HTTP POST connection to the shopping cart servlet.
 // Third parameter specifies request is asynchronous.
 req.open("get", "/Console/consoleservlet?type=servers", true);

 // Specify that the body of the request contains form data
 req.setRequestHeader("Content-Type",  "txt/xml");

 // Send form encoded data stating that I want to add the 
 // specified item to the cart.
 req.send();


 }
 
/* 
Purpose:  is used as example 
Creation/Modification History :
   1.1 - Developer Name: Aboelhassan hamed
   1.2 - Date:  
   1.3 - Creation/Modification:Creation      
   1.4-  Description: 
* 
*/
function process(response){
  
    }
 