<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<h:panelGrid columns="2" id="navigationpanel"  >
  
  <h:commandButton value="#{globalResources.NextButton}" action="#{calculationsMaintainBean.next}"  styleClass="cssButtonSmall"  />
  
  <%--<h:commandButton value="#{globalResources.SaveButton}" action="#{pageBeanName.saveObject}" disabled="#{!pageBeanName.renderSave}"  styleClass="cssButtonSmall"   onclick="return stepValidation();" />--%>
 <t:commandButton  forceId="true" id="BackButtonManyToMany" onblur="if(typeof(setFocusAtMyFirstElement)!='undefined'){setFocusAtMyFirstElement()};"  value="#{globalResources.back}" action="#{pageBeanName.back}"   styleClass="cssButtonSmall"   onclick="ignoremyFormValidation();" immediate="true" />
</h:panelGrid>
