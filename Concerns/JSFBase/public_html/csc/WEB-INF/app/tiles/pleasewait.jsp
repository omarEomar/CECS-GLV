<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<script type="text/javascript">
  var toResetMSG;
  function block() {
      try {
          obj = document.getElementById('iFrameWait');
          obj.style.visibility = 'visible';
          document.getElementById('divWait').style.visibility = 'visible';
          window.parent.nav_btn.disabled = true;
          setProgressBarMsg();
      }
      catch (exception) {
      }
  }

  function unblock() {
      try {
          obj = document.getElementById('iFrameWait');
          obj.style.visibility = 'hidden';
          document.getElementById('divWait').style.visibility = 'hidden';
          changeNavigationMenuStatus();
          updatePageIndexHiddenField();
          resetProgressBarMsg();
      }
      catch (exception) {
      }
  }

  function setProgressBarMsg() {
      var progressMsgVal = document.getElementById('progressMsgVal');
      if (progressMsgVal != null && progressMsgVal.value != '') {
          var pleaseWait = document.getElementById('pleaseWait').innerHTML;
          toResetMSG = true;
          var divWait = document.getElementById("divWait");
          hideLookUpDiv(window.blocker, window.customDiv2, null, null);
          divWait.innerHTML = "<span class='divWatingMsgValue'> <span style='margin: 10px 0px 5px;'>" + pleaseWait + " </span> <br/>" + progressMsgVal.value + "</span><br/>" + '<img src="/apr/app/media/images/plz_wait_msg.gif" id="myForm:GI-divwait">';
          divWait.className = "divWatingMsg";
          document.getElementById("myForm:GI-divwait").style = "margin: 10px 0px 5px; width: 350px; height: 13px;";
      }
      else {
          toResetMSG = false;
      }
  }

  function resetProgressBarMsg() {
      if (toResetMSG) {
          document.getElementById("divWait").innerHTML = '<img src="/apr/app/media/images/plz_wait.gif" id="myForm:GI-divwait">';
          document.getElementById("divWait").className = "divWating";
          document.getElementById('progressMsgVal').value = ""
          document.getElementById("myForm:GI-divwait").style = "";
          toResetMSG = false;
      }
  }
</script>
<a4j:status onstart="block()" onstop="unblock()"></a4j:status>