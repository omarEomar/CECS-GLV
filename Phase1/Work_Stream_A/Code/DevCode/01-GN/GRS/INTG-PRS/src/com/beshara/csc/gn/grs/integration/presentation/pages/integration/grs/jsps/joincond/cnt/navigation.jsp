<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<%-- ##################START################ Strat of Control Buttons Section #################SHEKA############### --%>

<t:panelGrid columns="1" border="0" width="100%" columnClasses="center">
    <t:panelGroup>
        <a4j:commandButton id="grsIntgJoinAlertOkBtn" value="#{globalResources.ok}" styleClass="cssButtonSmall"
                rendered="#{jcHelperBeanName.joinMode && jcHelperBeanName.showJoinAlert}"
                action="#{jcHelperBeanName.hideJoinAlert}" 
                reRender="grsIntgJoinCancelSection,grsIntgNavSection"/>
                    
        <t:commandButton  type="button" forceId="true" id="grsIntgJoinSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall" disabled="true"
                rendered="#{jcHelperBeanName.joinMode && !jcHelperBeanName.showJoinAlert}"
                onclick="okBtnFn();"/>
        <a4j:jsFunction name="okBtnFn"
                            oncomplete="hideJoinCondDiv(); javascript:changeVisibilityMsg();"
                            action="#{jcHelperBeanName.execute}"
                            reRender="dataT_data_panel,divMsg,grsIntgDiv,OperationBar"/>
        
        <t:commandButton forceId="true" id="grsIntgCancelSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall"
                rendered="#{jcHelperBeanName.cancelMode}"
                action="#{jcHelperBeanName.execute}" onclick="hideJoinCondDiv(); block();"/>
                    
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        
        <t:commandButton forceId="true" id="grsIntgDivBackBtn" type="button" 
        value="#{(jcHelperBeanName.joinMode && jcHelperBeanName.showJoinAlert)?globalResources.cancel:globalResources.back}" styleClass="cssButtonSmall" 
                onblur="settingfoucsOnGrsIntgDiv();" onclick="doGrsIntgDivBack();"/>
            <a4j:jsFunction name="grsIntgDivBackJS" action="#{jcHelperBeanName.resetData}" reRender="grsIntgDiv"
                oncomplete="settingfoucsAfterBackFromGrsIntgDiv();"/>
        <t:inputHidden forceId="true" id="divId" value="#{jcHelperBeanName.divId}" />
    </t:panelGroup>
</t:panelGrid>
<f:verbatim>
<script type="text/javascript">
    function hideJoinCondDiv(){
        var divId = document.getElementById('divId').value;
        if(divId != null){
            var divObj = document.getElementById(divId);
            hideLookUpDiv(window.blocker,divObj,null,null,null);
        }
        else hideLookUpDiv();
    }
    
    function doGrsIntgDivBack(){
        hideJoinCondDiv();
        //hidTreeDiv('add',window.blocker,window.integrationDiv1,'successMsgTreeCorrect');    
        grsIntgDivBackJS();
        return false;
    }
    
    settingfoucsOnGrsIntgDiv();

    function settingfoucsOnGrsIntgDiv() {
        var done = false;
        if(document.getElementById('grsIntgSearchCondCode') != null){
            done = true;
            document.getElementById('grsIntgSearchCondCode').focus();
            document.getElementById('grsIntgSearchCondCode').focus();
        }
        if(!done){
            document.getElementById('grsIntgDivBackBtn').focus();
            document.getElementById('grsIntgDivBackBtn').focus();
        }
    }

    function settingfoucsAfterBackFromGrsIntgDiv() {
        if (typeof (setFocusAfterBackFromGrsIntgDiv) != 'undefined') {
            setFocusAfterBackFromGrsIntgDiv();
        }
    }
  
    settingfoucsOnJoinConditionDiv();

    function settingfoucsOnJoinConditionDiv() {
        try{
            document.getElementById('grsIntgSearchCondCode').focus();
            document.getElementById('grsIntgSearchCondCode').focus();
        } catch(e){}
    }
</script>
</f:verbatim>
