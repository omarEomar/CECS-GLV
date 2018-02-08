
function pause(millis) {
 var date = new Date();
 var curDate = null;

 do { curDate = new Date(); } 
 while(curDate-date < millis)
}

function insureViewStateLoaded() {   
   while(true) {
       if(document.getElementById('javax.faces.ViewState') != null) {   
          document.getElementById('iFrameWait').style.visibility = 'hidden';
          break;
       }
       pause(100);
  }
}