<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration" var="grsIntgResources"/>

<t:panelGroup forceId="true" id="grsJoinCalcIntgDiv">


        <t:panelGroup forceId="true" id="grsJoinCalcAlertSection" rendered="#{joinCalcHelperBeanName.showJoinAlert}">
            <t:outputText value="#{grsIntgResources.changeActiveCalculationsAlertMsg}"
                    styleClass="msg warning" rendered="#{joinCalcHelperBeanName.showJoinAlert}"/>
            <jsp:include page="/integration/grs/jsps/joincalc/activeConditionCnt.jsp" flush="true" />
            
        </t:panelGroup>
        
        <t:panelGroup forceId="true" id="grsIntgJoinCalcSection" styleClass="grsIntgJoinContainerStyle"
                rendered="#{!joinCalcHelperBeanName.showJoinAlert}">
                <htm:center>
                    <t:outputText value="#{grsIntgResources.joinCalcDivTitle}" styleClass="TitlePage"/>
                     <f:verbatim>
                        <br/>
                    </f:verbatim>
                    <t:outputText forceId="true"
                                  id="CondActivationWarnMsgId"
                                  rendered="#{joinCalcHelperBeanName.checkBenefitRewardCondition}"
                                  value="#{grsIntgResources.Calc_Condition_Activation_Warn_Msg}"
                                  styleClass="errMsg"/>
                    <f:verbatim>
                        <br/>
                    </f:verbatim>
                <t:selectOneRadio id="radio_options" value="#{joinCalcHelperBeanName.calcType}" forceId="true">
                    <f:selectItem itemLabel="#{grsIntgResources.fixedvalue}" itemValue="#{joinCalcHelperBeanName.fixedValueCalcType}"/>
                    <f:selectItem itemLabel="#{grsIntgResources.calctype}" itemValue="#{joinCalcHelperBeanName.selectCalcType}"/>                   
                    <f:selectItem itemLabel="#{grsIntgResources.addJoinCalcType}" itemValue="#{joinCalcHelperBeanName.addJoinCalcType}"/>
                    
                    <a4j:support event="onclick" actionListener="#{joinCalcHelperBeanName.changeCalcType}" reRender="joinCalc_cnt,grsJoinCalcNavSection"/>
                </t:selectOneRadio>
                </htm:center>
                <t:panelGroup id="joinCalc_cnt" forceId="true">
                    <t:panelGroup rendered="#{joinCalcHelperBeanName.calcType == joinCalcHelperBeanName.fixedValueCalcType}">
                        <t:panelGrid columns="2" rowClasses="row01,row02" cellpadding="2" cellspacing="0" width="100%">
                            <t:outputLabel value="#{grsIntgResources.name}" styleClass="lable01"/>
                            <t:panelGroup>
                            <t:inputText forceId="true" id="grsJoinCalcName" styleClass="textboxtreelarge"
                                    value="#{joinCalcHelperBeanName.newCalcName}" style="width:450px;" onkeyup="trimBorders(this);">
                                    <%--<a4j:support event="onchange" reRender="grsJoinCalcNavSection"/>--%>
                            </t:inputText>
                            <f:verbatim>
                                <br/>
                            </f:verbatim>
                             <t:outputText id="NameErrMsg" forceId="true"  value="#{globalResources.missingField}" styleClass="errMsg" style="display:none;width:auto;margin-right: 5px;"/>
                            </t:panelGroup>
                            <t:outputLabel value="#{grsIntgResources.value}" styleClass="lable01"/>
                            <t:panelGroup>
                            <t:inputText forceId="true" id="grsJoinCalcValue" styleClass="textbox" maxlength="6"
                                    value="#{joinCalcHelperBeanName.value}" 
                                    onkeypress="enabelFloatOnly(this);" 
                                    onkeyup="enabelFloatOnly(this);">
                                    <%--<a4j:support event="onchange" reRender="grsJoinCalcNavSection"/>--%>
                            </t:inputText>
                            <%--<f:verbatim>
                                <br/>
                            </f:verbatim>--%>
                            <t:outputText id="valueErrMsg2" forceId="true"  value="#{globalResources.missingField}" styleClass="errMsg" style="display:none;width:auto;margin-right: 5px;"/>
                            <t:outputText id="valueErrMsg" forceId="true"  value="#{grsIntgResources.valueCodeFormat}" styleClass="errMsg" style="display:none;width:auto;margin-right: 5px;"/>
                            </t:panelGroup>
                        </t:panelGrid>
                    </t:panelGroup>
                    <t:panelGroup rendered="#{joinCalcHelperBeanName.calcType == joinCalcHelperBeanName.selectCalcType}">
                        <jsp:include page="/integration/grs/jsps/joincalc/join.jsp" flush="true" />
                    </t:panelGroup>
                </t:panelGroup> 
        </t:panelGroup>
    
<%-- ##################START################ Strat of Control Buttons Section ###########SHEKA##################### --%>
    <t:panelGroup forceId="true" id="grsJoinCalcNavSection">
        <jsp:include page="/integration/grs/jsps/joincalc/btns.jsp" flush="true" />
    </t:panelGroup>
    
    
    
</t:panelGroup>
<t:panelGroup forceId="true" id="grsIntgerationHiddenValues">
        <t:inputHidden forceId="true" id="grsIntgrFullURLId" value="#{joinCalcHelperBeanName.fullURL}"/>
        <t:outputText forceId="true" id="grsIntgrWindowTitleId" value="#{grsIntgResources.calc_details}" style="display:none;"/>
</t:panelGroup>
<f:verbatim>
<script type="text/javascript">
      function openGrsIntgConditionDetailsWindow() {
      var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew('grsIntgFullURLId' ,moduleName, 'grsIntgWindowTitleId');
      }
      function openGrsIntgCalculationDetailsWindow() {
      var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew('grsIntgrFullURLId' ,moduleName, 'grsIntgrWindowTitleId');
      }
      function openGrsIntgNewWindow(fullURL, winNameInput) {
          var moduleName ='${shared_util.contextPath}';
          openGrsIntgNewWindowNew(fullURL ,moduleName,winNameInput); 
      }
    
 
</script>
</f:verbatim>
