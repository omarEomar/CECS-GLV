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
        <t:commandButton forceId="true" id="decIntgJoinSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall" disabled="true"
                rendered="#{jdHelperBeanName.joinMode && !jdHelperBeanName.showJoinAlert}"
                action="#{jdHelperBeanName.execute}" onclick="hideLookUpDiv(); block();"/>
        
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        
        <t:commandButton forceId="true" id="decIntgDivBackBtn" type="button" value="#{globalResources.back}" styleClass="cssButtonSmall" 
                onblur="settingfoucsOnDecIntgDiv();" onclick="doDecIntgDivBack();"/>
        <a4j:jsFunction name="decIntgDivBackJS" action="#{jdHelperBeanName.resetData}" reRender="decIntgDiv"
                oncomplete="settingfoucsAfterBackFromDecIntgDiv();"/>
                
    </t:panelGroup>
</t:panelGrid>
<f:verbatim>
<script type="text/javascript">

    function doDecIntgDivBack(){
        hideLookUpDiv();
        decIntgDivBackJS();
        return false;
    }
    
    settingfoucsOnDecIntgDiv();

    function settingfoucsOnDecIntgDiv() {
        var done = false;
        if(document.getElementById('decIntgSearchRegulationType') != null){
            done = true;
            document.getElementById('decIntgSearchRegulationType').focus();
            document.getElementById('decIntgSearchRegulationType').focus();
        }
        if(!done){
            document.getElementById('decIntgDivBackBtn').focus();
            document.getElementById('decIntgDivBackBtn').focus();
        }
    }

    function settingfoucsAfterBackFromDecIntgDiv() {
        if (typeof (setFocusAfterBackFromDecIntgDiv) != 'undefined') {
            setFocusAfterBackFromDecIntgDiv();
        }
    }
  
    settingfoucsOnJoinConditionDiv();

    function settingfoucsOnJoinConditionDiv() {
        try{
            document.getElementById('decIntgSearchCondCode').focus();
            document.getElementById('decIntgSearchCondCode').focus();
        } catch(e){}
    }
</script>
</f:verbatim>
