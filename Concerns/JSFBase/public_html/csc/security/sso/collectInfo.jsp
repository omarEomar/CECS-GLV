<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>collect-info</title>
    <script type="text/javascript">
        var infoAppletInfo = null;
        function setInfoAppletInfo(info) {
                infoAppletInfo = info;
        }

        var infoAppletDone = false;
        function setInfoAppletDone() {
                infoAppletDone = true;
        }

        function alertError() {
                alert("Cannot get the client information, please contact your administrator.");
        }

        function submitInfo() {
                var infoApplet = document.getElementById("infoApplet");
                if (infoApplet == null) {
                        alertError();
                        return;
                }

                if (!infoAppletDone) {
                        setTimeout("submitInfo();", 100);
                        return;
                }

                if (infoAppletInfo == null) {
                        alertError();
                } else {
                        var infoForm = document.getElementById("infoForm");
                        if (infoForm == null) {
                                alertError();
                                return;
                        }

                        var infoText = infoForm.elements["infoText"];
                        if (infoText == null) {
                                alertError();
                                return;
                        }

                        infoText.value = infoAppletInfo;
                        infoForm.submit();
                }
        }
    </script>
</head>
<body>
    <applet id="infoApplet" name="infoApplet" archive="sso-collect-info-applet-1.3.0.jar" code="com.beshara.sso.collectinfo.applet.SSOCollectInfoApplet.class" style="visibility: hidden"
            height="1" width="1">
            Applet failed to run. No Java plug-in was found.
    </applet>
    <form id="infoForm" name="infoForm" action="" method="post"
            style="visibility: hidden">
            <input type="text" id="infoText" name="infoText" />
    </form>
    <script type="text/javascript">
            submitInfo();
    </script>
</body>
</html>
