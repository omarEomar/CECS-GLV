
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

 <%
        String errMissingKey = "MISSING: "+((com.beshara.jsfbase.csc.util.SharedUtilBean)session.getAttribute("shared_util")).getErrMsgValue()+" :MISSING";
        String succMissingKey = "MISSING: "+((com.beshara.jsfbase.csc.util.SharedUtilBean)session.getAttribute("shared_util")).getSuccessMsgValue()+" :MISSING";
        request.setAttribute("errMissingKey",errMissingKey);
        request.setAttribute("succMissingKey",succMissingKey);
        
    %>
<t:panelGrid align="center" id="msgShow" forceId="true" width="100%">

    <%-- TODO locking code --%>
    <t:saveState value="#{pageBeanName.lastLockingAction}"/>
    <t:inputHidden forceId="true" id="lockingFailed" value="#{pageBeanName.lockingFailed}"/>
    
    <%-- t:inputText id="successMsg" forceId="true" value="#{globalResources[shared_util.successMsgValue]}" styleClass="msg" readonly="true" rendered="#{shared_util.successMsgValue != null}"/>
    <t:inputText id="ErrMsg" forceId="true" value="#{globalexceptions[shared_util.errMsgValue]}" styleClass="errmsg" readonly="true" rendered="#{shared_util.errMsgValue != null}"/>
    </t:panelGrid--%><%-- Start By css person used to enhance look for the msg --%>
     
    <%--
    <c:set var="errMissingKey" value="MISSING: ${shared_util.errMsgValue} :MISSING" scope="request"/>
    <c:set var="succMissingKey" value="MISSING: ${shared_util.successMsgValue} :MISSING" scope="request"/>
    --%>
    <t:inputTextarea id="successMsg" forceId="true" value="#{globalResources[shared_util.successMsgValue] == succMissingKey ? shared_util.successMsgValue : globalResources[shared_util.successMsgValue] }" styleClass="msg32" readonly="true" rendered="#{shared_util.successMsgValue != null}" style="#{appMainLayout.manyToMany ? detailBeanName.msgDivHeightStyle : pageBeanName.msgDivHeightStyle}"/>
    <t:inputTextarea id="ErrMsg" forceId="true" value="#{globalexceptions[shared_util.errMsgValue] == errMissingKey ? shared_util.errMsgValue : globalexceptions[shared_util.errMsgValue] }" styleClass="errmsg32" readonly="true" rendered="#{shared_util.errMsgValue != null}" style="#{appMainLayout.manyToMany ? detailBeanName.msgDivHeightStyle : pageBeanName.msgDivHeightStyle}"/>
    
    </t:panelGrid>
    <a4j:jsFunction name="clearDivMsg" status="testing"  actionListener="#{shared_util.setMsgValue}" reRender="msgShow"/>
    <%--<t:panelGrid align="center" >
        --%><%--a4j:commandButton type="button" value="#{globalResources.back}" styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.divMsg,null,null,null);" actionListener="#{shared_util.setMsgValue}"
                          reRender="msgShow"/--%><%--
        <t:commandButton id="jsfBase_msgDiv_backBtn" onblur="if(isVisibleComponent('divMsg'))settingFoucsAtDivMsg();" forceId="true"  type="button" value="#{globalResources.back}" styleClass="cssButtonSmall">
            <a4j:support id="jsfBase_msgDiv_ajxSprt"  event="onclick" oncomplete="hideLookUpDiv(window.blocker,window.divMsg,null,null,null);" actionListener="#{shared_util.setMsgValue}" reRender="msgShow"/>
        </t:commandButton>
    </t:panelGrid>--%>
	<t:inputHidden value="#{appMainLayout.manyToMany ? detailBeanName.msgDivTimeoutPeriod : pageBeanName.msgDivTimeoutPeriod}" id="timeout_period" forceId="true"/>
<htm:script >
    var div = document.getElementById('divMsg');
    div.onmouseover=function(){document.getElementById('msgcloseXX').style.visibility="visible";};
    div.onmouseout=function(){document.getElementById('msgcloseXX').style.visibility="hidden";};
    function enableMsgScript() {
        var msg_div = document.getElementById('divMsg');
        msg_div.onmouseover=function(){document.getElementById('msgcloseXX').style.visibility="visible";};
        msg_div.onmouseout=function(){document.getElementById('msgcloseXX').style.visibility="hidden";};
    }
</htm:script>
