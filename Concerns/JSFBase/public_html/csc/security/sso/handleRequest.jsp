<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title>handle request</title>
</head>
<body onload="document.getElementById('handleRequestForm').submit();">
	<jsp:useBean id="handleRequestBean"
		class="com.beshara.sso.web.backingbeans.HandleRequestBean"
		scope="request" />
	<form id="handleRequestForm" method="post"
		action="${handleRequestBean.authUrl}">
		<input name="com.beshara.sso.web.login.reqId" type="hidden"
			value="${handleRequestBean.requestId}" /> <input
			name="com.beshara.sso.web.login.requestId" type="hidden"
			value="${handleRequestBean.encryptedRequestId}" /> <input
			name="com.beshara.sso.web.login.locale" type="hidden"
			value="${systemLocaleBean.locale}">
	</form>
</body>
</html>
