<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

    <t:panelGrid border="0" align="center" width="100%" forceId="true" id="buttonsPanel" >
                <t:panelGroup/>
                <t:panelGroup>cnt1
                <t:commandButton styleClass="cssButtonSmall" forceId="true" id="searchButton" value="#{globalResources.SearchButton}" action="#{detailBeanName.searchAvailable}" />
                  <f:verbatim>&nbsp;</f:verbatim>
		<h:panelGroup>
                    <t:commandButton  id="resetButton" action="#{detailBeanName.cancelSearchMethod}" styleClass="cssButtonMedium" value="#{intResourcesBundle.clear_search_data}"/>
                    
                </h:panelGroup>
                <%--a4j:jsFunction name="resetJsFunction" action="#{detailBeanName.cancelSearchMethod}"  reRender="cnt1"/--%>
                <%--h:commandButton type="reset" styleClass="cssButtonMedium" id="resetButton" value="#{intResourcesBundle.clear_search_data}" onclick="document.getElementById('employees_nationalty').disabled=true; return true;"/--%>
                 <%--h:commandButton type="reset" styleClass="cssButtonMedium" id="resetButton" value="#{intResourcesBundle.clear_search_data}" action="#{detailBeanName.searchAvailable}" /--%>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:commandButton id="BackButtonManyToMany" forceId="true" value="#{globalResources.back}" styleClass="cssButtonSmall"  action="#{detailBeanName.backFromSearch}" onblur="document.getElementById('employees_civilId').focus();" />
              </t:panelGroup>
         </t:panelGrid>       
     
