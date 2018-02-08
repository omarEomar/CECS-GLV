<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:verbatim>
<style type="text/css">
    .node {
        color: #feec39;
        font: bold 16px "Calibri","Calibri";
        text-decoration: none;
        white-space: nowrap;
    }
    a.linkNode {
        font-family: Tahoma; 
        color: #ffffff; 
        font-size: 12px; 
        text-decoration: none;
        width:180px;
        vertical-align: top;
    }
    a:hover.linkNode {
        font-family: Tahoma; 
        color: #ffffff; 
        font-size: 12px; 
        text-decoration: none;
        width:180px;
        vertical-align: top;
    }
</style>
<script type="text/javascript">
function openIntWindow(url) {
    var width=1010,height=592;
    var left = (screen.width/2)-(width/2);
    var top = (screen.height/2)-(height/2);
    window.open(url, '', 'toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0,width='+width+',height='+height+',top='+top+',left='+left);
}
</script>
<table class="gtree" border="0" id="table1" cellspacing="0" cellpadding="0">
	<tr>
            <td valign="top" dir="rtl">
                    <div class="MenuTitle"></div>
            </td>
	</tr>
	<tr>
            <td valign="top" dir="rtl"><div class="gtree2"></f:verbatim>
                    <h:panelGroup rendered="#{!ModuleMenu.enabled}"><f:verbatim><script type="text/javascript" language="JavaScript1.2"  src="${shared_util.contextPath}/module/js/NavigationMenu.jsf"></script></f:verbatim></h:panelGroup>
                    <h:form  rendered="#{ModuleMenu.enabled}"><t:tree2 binding="#{ModuleMenu.tree}" id="menuTree" value="#{ModuleMenu.rootNode}" var="node" varNodeToggler="t">
                    <f:facet name="TreeNode">
                         <h:panelGroup>
                            <t:graphicImage value="/app/media/images/tree/file_f.png" rendered="#{node.leaf}" border="0" width="14" height="13"/>
                            <f:facet name="expand">
                                <t:graphicImage value="/app/media/images/tree/file_uf.png" rendered="#{!node.leaf && t.nodeExpanded}" border="0" width="14" height="13"/>
                            </f:facet>
                            <f:facet name="collapse">
                                <t:graphicImage value="/app/media/images/tree/file_uf.png" rendered="#{!node.leaf && !t.nodeExpanded}" border="0" width="14" height="13"/>
                            </f:facet>
                            <f:verbatim>&nbsp;</f:verbatim>
                            <h:outputText rendered="#{!(node.hasViewId || node.hasNoTarget)}" value="#{node.description}" title="#{node.description}" styleClass="node"/>
                            <h:outputLink rendered="#{node.hasViewId}" value="#{node.viewId}" target="#{ModuleMenu.target}" styleClass="linkNode">
                                <a4j:support event="onclick" actionListener="#{shared_util.setMsgValue}" ajaxSingle="true" ignoreDupResponses="true" oncomplete="hideNavigationMenu();"/>
                                <h:outputText value="#{node.description}" title="#{node.description}"/>
                            </h:outputLink>
                            <h:outputLink rendered="#{node.hasNoTarget}" target="_blank" value="#{node.noTargetUrl}" styleClass="linkNode">
                                <a4j:support event="onclick" actionListener="#{shared_util.setMsgValue}" ajaxSingle="true" ignoreDupResponses="true" oncomplete="hideNavigationMenu();"/>
                                <h:outputText value="#{node.description}" title="#{node.description}"/>
                            </h:outputLink>                            
                        </h:panelGroup>
                    </f:facet>
                </t:tree2></h:form><f:verbatim></div></td>
	</tr>
</table>
</f:verbatim>
