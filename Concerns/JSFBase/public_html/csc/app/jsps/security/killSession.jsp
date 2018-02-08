<%@ page import="com.beshara.common.web.shared.SessionManager"%>
<%
    SessionManager.getInstance().killSession(request.getParameter("sid"));
    session.invalidate();
%>