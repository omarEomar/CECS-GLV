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
        <a4j:commandButton id="grsJoinCalcAlertOkBtn" value="#{globalResources.ok}" styleClass="cssButtonSmall"
                rendered="#{joinCalcHelperBeanName.showJoinAlert}"
                action="#{joinCalcHelperBeanName.hideJoinAlert}" 
                reRender="grsJoinCalcIntgDiv,grsJoinCalcNavSection"/>
                    
        <t:commandButton  type="button" forceId="true" id="grsJoinCalcSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall" disabled="#{empty joinCalcHelperBeanName.selectedDTOS && joinCalcHelperBeanName.calcType == joinCalcHelperBeanName.selectCalcType}"
                rendered="#{!joinCalcHelperBeanName.showJoinAlert && joinCalcHelperBeanName.calcType != joinCalcHelperBeanName.addJoinCalcType}"
                onclick="if(validateDiv()){okBtnFn();}else{return false;}"/>
        <a4j:jsFunction name="okBtnFn"
                            oncomplete="hideJoinCalcDiv(); javascript:changeVisibilityMsg();"
                            action="#{joinCalcHelperBeanName.execute}"
                            reRender="divMsg,grsJoinCalcIntgDiv,OperationBar"/>
       <t:commandButton  type="button" forceId="true" id="grsAddJoinCalcSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall" 
                rendered="#{!joinCalcHelperBeanName.showJoinAlert && joinCalcHelperBeanName.calcType == joinCalcHelperBeanName.addJoinCalcType}"
                onclick="grsAddJoinBtnFn();"/>
        <a4j:jsFunction name="grsAddJoinBtnFn"
                            oncomplete="hideJoinCalcDiv(); javascript:changeVisibilityMsg();"
                            action="#{joinCalcHelperBeanName.addAndJoinCalc}"
                            reRender="divMsg,grsJoinCalcIntgDiv,OperationBar"/>
        
        <%--<t:commandButton forceId="true" id="grsIntgCancelSaveBtn" value="#{globalResources.ok}"
                styleClass="cssButtonSmall"
                rendered="#{joinCalcHelperBeanName.cancelMode}"
                action="#{joinCalcHelperBeanName.execute}" onclick="hideJoinCalcDiv(); block();"/>--%>
                    
        <f:verbatim>&nbsp;&nbsp;</f:verbatim>
        
        <t:commandButton forceId="true" id="grsJoinCalcBackBtn" type="button" 
        value="#{joinCalcHelperBeanName.showJoinAlert ? globalResources.cancel : globalResources.back}" styleClass="cssButtonSmall" 
                onblur="settingfoucsOnGrsIntgDiv();" onclick="doGrsJoinCalcDivBack();"/>
            <a4j:jsFunction name="grsJoinCalcBackJS" action="#{joinCalcHelperBeanName.resetData}"/>
        <t:inputHidden forceId="true" id="joincalcdivId" value="#{joinCalcHelperBeanName.divId}" />
        <t:inputHidden forceId="true" id="searchMode" value="#{joinCalcHelperBeanName.searchMode}" converter="javax.faces.Boolean"/>
    </t:panelGroup>
</t:panelGrid>
<t:commandButton id="backButtonTreeAddDiv" forceId="true" onclick="document.getElementById('grsJoinCalcBackBtn').click();" style="display:none;" type="button"/>
<f:verbatim>
<script type="text/javascript">
    function hideJoinCalcDiv(){
        var joincalcdivId = document.getElementById('joincalcdivId').value;
        if(joincalcdivId != null){
            var divObj = document.getElementById(joincalcdivId);
            hideLookUpDiv(window.blocker,divObj,null,null,null);
        }
        else hideLookUpDiv();
    }
    
    function doGrsJoinCalcDivBack(){
        hideJoinCalcDiv();
        //hidTreeDiv('add',window.blocker,window.integrationDiv1,'successMsgTreeCorrect');    
        grsJoinCalcBackJS();
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
            document.getElementById('grsJoinCalcBackBtn').focus();
            document.getElementById('grsJoinCalcBackBtn').focus();
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
    
    function validateDiv() {
        try{
            var radioVal = null;
            var radios = document.getElementsByName('radio_options');
            for( i = 0; i < radios.length; i++ ) {
                if( radios[i].checked ) {
                    radioVal = radios[i].value;
                    break;
                }
            }
            if(radioVal != null){
                var inputVal = null;
                var errMsg = null;
                if(radioVal == '1'){
                     inputVal = document.getElementById('grsJoinCalcValue').value;
                     var name = document.getElementById('grsJoinCalcName').value;
                     errMsg = document.getElementById('valueErrMsg');
                     errMsg2 = document.getElementById('valueErrMsg2');
                     var namErrMsg = document.getElementById('NameErrMsg');
                     errMsg.style.display = 'none';
                     errMsg2.style.display = 'none';
                     namErrMsg.style.display = 'none';
                     var valid = true;
                     if(name == null || name == ''){
                        namErrMsg.style.display = 'inline';
                        valid = false;
                     }
                     if(inputVal == null || inputVal == ''){
                        errMsg2.style.display = 'inline';
                        valid = false;
                     }else if(!isFloat(inputVal)){
                        errMsg.style.display = 'inline';
                        document.getElementById('grsJoinCalcValue').value = '';
                        valid =  false;
                    }
                    return valid;
                }else if(radioVal == '2'){
                    inputVal = document.getElementById('grsJoinCalcSearchCalcCode').value;
                    errMsg = document.getElementById('codeErrMsg');
                    errMsg.style.display = 'none';                    
                    if(inputVal == null || inputVal == ''){
                        return true;
                    }else if(!isFloat(inputVal)){
                        errMsg.style.display = 'inline';
                        document.getElementById('grsJoinCalcSearchCalcCode').value = '';
                        return false;
                    }else{
                        return true;
                    }
                }
            }
            
        } catch(e){
        ;
        }
    }
</script>

</f:verbatim>
