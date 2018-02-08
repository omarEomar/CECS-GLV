<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.globalexceptions" var="globalExceptions"/>
 
 


<h:panelGrid columns="5" id="navigationpanel" >
  
  
  <h:commandButton value="#{globalResources.PreviousButton}" action="#{pageBeanName.previous}" disabled="#{pageBeanName.previousNavigationCase==null}" styleClass="cssButtonSmall"   onclick="return validateStepsAndCompare2Dates();"  rendered="#{pageBeanName.showPrevious}"/>
  <h:commandButton value="#{globalResources.NextButton}" action="#{pageBeanName.next}" disabled="#{pageBeanName.nextNavigationCase==null}" styleClass="cssButtonSmall"   onclick="return validateStepsAndCompare2Dates();"/>
  <h:commandButton value="#{globalResources.FinishButton}" action="#{pageBeanName.finish}" disabled="#{!pageBeanName.renderFinish}" styleClass="cssButtonSmall"    onclick="return validateStepsAndCompare2Dates();"/>
  <%--<h:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.saveObject}" disabled="#{!pageBeanName.renderSave}"  styleClass="cssButtonSmall"   onclick="return stepValidation();" />--%>
 <h:commandButton value="#{globalResources.CancelButton}" action="#{pageBeanName.back}"   styleClass="cssButtonSmall"   onclick="ignoremyFormValidation();" immediate="true" />
</h:panelGrid>