<meta HTTP-EQUIV="Content-Type" CONTENT="text/html;charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>


<t:inputText id="modCode" forceId="true" value="write module code here" onclick="javascript:this.value='';"></t:inputText>
<t:inputText id="repId" forceId="true" value="write first Report Id here" onclick="javascript:this.value='';"></t:inputText>


<input id="reports" onclick="generateReportScript();" value="generateReportScript" type="button"/>
<input id="reports2" onclick="generateReplacedUrls();" value="generateReplacedUrls" type="button"/>


<script type="text/javascript">
  function generateReportScript() {
      var arr = document.getElementsByTagName("a");
      var str = new Blob();
      var obj;
      var title;
      var href;
      var param;
      var url = new Array();
      var modCode = document.getElementById("modCode").value;
      var repId = document.getElementById("repId").value;
      str += "\n";
      str += "SET DEFINE OFF;";
      for (var i = 0;i < arr.length;i++) {
          obj = arr[i];
          if (obj.className == "sta" && obj.href.indexOf("?") >  - 1) {
              title = obj.innerHTML;
              while (title.indexOf("&nbsp;") >  - 1) {
                  title = title.replace("&nbsp;", " ");
              }
              url = obj.href.split("?");
              href = url[0];
              href = href.replace("http://newis-bip:9704", "");
              param = url[1];
              param = param.replace("&id=hr_user&passwd=hr123456","");
              param = param.replace("id=hr_user&passwd=hr123456","");
              param = param.replace("ANDid=hr_userANDpasswd=hr123456","");
              param = param.replace("Andid=hr_userAndpasswd=hr123456","");
              param = param.replace("andid=hr_userandpasswd=hr123456","");
              
              str += "\n";
              str += "Insert into DPL_OWNER.GN_REP_HEADER (REP_TITLE_AR, AUDIT_STATUS, REP_ID, REP_PATH, REP_PARAMS, REP_MODULE)";
              str += "\n";
              str += "Values";
              str += "\n";
              str += "('" + title + "', 0, " + repId + ",'" + href + "','" + param + "', " + modCode + ");";
              repId++;
          }
      }
      copyToClipb(str);
      alert("Now Paste")
  }
 
  function CopyToClipboard(text) {
    Copied = text.createTextRange;
    Copied.execCommand("Copy");
}

  function generateReplacedUrls() {
      var arr = document.getElementsByTagName("a");
      var repId = document.getElementById("repId").value;

      var str = "";
      var obj;
      for (var i = 0;i < arr.length;i++) {
          obj = arr[i];
          if (obj.className == "sta" && obj.href.indexOf("?") >  - 1) {
              str += "\n";
              str += "\$\{shared_util.contextPath\}/module/jsps/reports/reportsFrame.jsf?rep=" + repId + "\\";
              repId++;
          }
      }
       copyToClipb(str);
      alert("Now Paste")
  }


 
  
function copyToClipb(txt) {


	 if (window.clipboardData) {

	   window.clipboardData.setData("Text", txt);
	   }
	   else if (window.netscape)
	   {
		try
		{
	   netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');

	   var clip = Components.classes['@mozilla.org/widget/clipboard;1']
	                 .createInstance(Components.interfaces.nsIClipboard);
	   if (!clip) return;

	   var trans = Components.classes['@mozilla.org/widget/transferable;1']
	                  .createInstance(Components.interfaces.nsITransferable);
	   if (!trans) return;
	   trans.addDataFlavor('text/unicode');
	   var str = new Object();
	   var len = new Object();

	   var str = Components.classes["@mozilla.org/supports-string;1"]
	                .createInstance(Components.interfaces.nsISupportsString);

	   var copytext=txt;
	   str.data=copytext;
	   trans.setTransferData("text/unicode",str,copytext.length*2);
	   var clipid=Components.interfaces.nsIClipboard;
	   if (!clip) return false;
	   clip.setData(trans,null,clipid.kGlobalClipboard);
		}

		catch(e){
                    alert(e.message);         
		 }
                 
      
	   }
	   return false;
}
</script>
