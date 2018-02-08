<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- read the current page direction from the SharedUtil Bean -->
<f:view>
    <t:inputHidden value="#{ReportsFrameBean.reportId}"></t:inputHidden>
    <iframe id='iFrame1' frameborder="0" scrolling="no" src="${ReportsFrameBean.iframeSrc}" width="100%" height="100%"></iframe>
    <t:saveState value="#{ReportsFrameBean.reportId}"/>
    <t:saveState value="#{ReportsFrameBean.iframeSrc}"/>
</f:view>
