<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>

<%--<a4j:jsFunction name="confirmSaveFunction" 
    action="#{pageBeanName.openConfirmSave}" 
    oncomplete="javascript:changeVisibilityDiv(window.blocker,window.customDiv2);setFocusAtMyCustomDiv();return false;" 
    reRender="customeDiv2"/>--%>

<h:panelGrid columns="5" id="navigationpanel" >
  
  
  <t:commandButton value="#{globalResources.PreviousButton}"
   action="#{pageBeanName.previous}"
   disabled="#{pageBeanName.previousNavigationCase==null}"
   styleClass="cssButtonSmall"   
   rendered="#{pageBeanName.showPrevious}" id="PreviousButtonManyToMany" forceId="true"/>
   <!--onclick="return validateCivilAndTwoTables();" -->
  <t:commandButton value="#{globalResources.NextButton}" 
    action="#{pageBeanName.next}" 
    rendered="#{pageBeanName.nextNavigationCase!=null}" 
    styleClass="cssButtonSmall"  
    onclick="#{pageBeanName.currentStepJSValidation}"  
    id="NextButtonManyToMany" forceId="true"/>
    <t:commandButton 
        value="#{ globalResources.FinishButton}" 
        action="#{pageBeanName.finish}"
        styleClass="cssButtonSmall"
        onclick=" return validateCivilAndTwoTables();"
        id="FinishButtonManyToMany" 
        forceId="true" disabled="#{pageBeanName.showOnly || empty detailBeanName.currentDisplayDetails}"
        rendered="#{pageBeanName.nextNavigationCase==null && pageBeanName.maintainMode != 2}"/>
  <%--<t:commandButton 
        value="#{pageBeanName.nextNavigationCase==null? globalResources.FinishButton : globalResources.SaveButton}" 
        action="#{pageBeanName.openConfirmSave}" 
        type="button"
        onclick=" function validation(){ #{pageBeanName.currentStepJSValidation} } ; " 
        disabled="#{pageBeanName.showOnly}"
        styleClass="cssButtonSmall"
        id="FinishButtonManyToMany" 
        forceId="true"
        rendered="#{(pageBeanName.currentStag==2) ? false: pageBeanName.maintainMode!=2? pageBeanName.currentStag!=3: true}"/>--%>

        
  <%--<t:commandButton 
        value="#{pageBeanName.nextNavigationCase==null? globalResources.FinishButton : globalResources.SaveButton}" 
        action="#{pageBeanName.totallySave}" 
        disabled="false" 
        styleClass="cssButtonSmall"
        id="FinishButtonManyToManySave" 
        forceId="true" 
        rendered="#{pageBeanName.currentStag==2}"/>--%>
        
  <%--<t:commandButton value="#{globalResources.SaveButton}" 
            action="#{pageBeanName.saveDecision}"  
            styleClass="cssButtonSmall"    
            id="SaveButtonManyToMany" forceId="true" 
             
            rendered="#{pageBeanName.currentStag==3}"/>--%>      
    <%--<t:commandButton 
        value="#{pageBeanName.nextNavigationCase==null? globalResources.FinishButton : globalResources.SaveButton}" 
        action="#{pageBeanName.finish}" 
        disabled="#{!pageBeanName.renderFinish}" 
        styleClass="cssButtonSmall"  
        onclick="#{pageBeanName.currentStepJSValidation}"  
        id="FinishButtonManyToMany" 
        forceId="true" 
        rendered="#{pageBeanName.maintainMode!=2}"/>--%>
        
  <%--<h:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.saveObject}" disabled="#{!pageBeanName.renderSave}"  styleClass="cssButtonSmall"   onclick="return stepValidation();" />--%>
  <%-- modified by m.elsaied --- NL-485 Job Change Request
 <h:commandButton value="#{globalResources.CancelButton}" action="#{pageBeanName.back}"   styleClass="cssButtonSmall"   onclick="ignoremyFormValidation();" immediate="true" />
  --%>
<%--
<t:commandButton id="reviewButton" value="#{intgResources.revision}" action="#{pageBeanName.review}" styleClass="cssButtonSmall"  onclick="#{pageBeanName.currentStepJSValidation}" forceId="true" rendered="#{pageBeanName.currentStag==2}"/>
--%>

<%--<t:commandButton id="reviewButton" value="#{intgResources.revision}" action="#{pageBeanName.review}" styleClass="cssButtonSmall"  onclick="#{pageBeanName.currentStepJSValidation}" forceId="true" rendered="#{pageBeanName.currentStag ==2}" disabled="#{pageBeanName.showOnly}"/>--%>
<%--<t:commandButton value="#{intgResources.execute_decision_title}" 
            action="#{pageBeanName.finish}"  
            styleClass="cssButtonSmall" 
            onclick="#{pageBeanName.currentStepJSValidation}"  
            id="FinishButton" forceId="true" 
            rendered="#{pageBeanName.currentStag==3}"/>--%>

            
 <t:commandButton  forceId="true" id="BackButtonManyToMany" 
    value="#{globalResources.back}" action="#{pageBeanName.back}" 
    onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};" 
    styleClass="cssButtonSmall" onclick="ignoremyFormValidation();" immediate="true" />
</h:panelGrid>
 <t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/> 
 
 <script>


 </script>
