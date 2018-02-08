<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>



<t:panelGrid id="srch_elements" forceId="true" width="100%" columns="1" align="center" rowClasses="gopentab">
   <h:panelGroup>
      <t:panelGrid columns="4" width="100%">
         <h:outputLabel value="#{resourcesBundle.lineCode}" styleClass="lable01"/>
         <h:panelGroup>
            <t:inputText id="codeSearch" forceId="true" value="#{pageBeanName.code}" styleClass="textboxmedium" converter="javax.faces.Long"/>
            <f:verbatim>
               <br/>
            </f:verbatim>
            <c2:regularExpressionValidator componentToValidate="codeSearch" pattern="/^[0-9]/" display="dynamic" errorMessage="#{resourcesBundle.numbersOnly}" highlight="false"/>
         </h:panelGroup>
         <h:outputLabel value="#{resourcesBundle.LineName}" styleClass="lable01"/>
         <h:inputText id="lineName" value="#{pageBeanName.lineName}" styleClass="textboxmedium"/>
      </t:panelGrid>
   </h:panelGroup>
   <h:panelGroup>
      <t:panelGrid columns="2" width="100%">
         <h:outputLabel value="#{resourcesBundle.chooseParameters}" styleClass="lable01"/>
         <t:selectOneMenu id="paraCode" value="#{pageBeanName.paramCode}" styleClass="textboxlarge" forceId="true">
            <f:selectItem itemValue="#{pageBeanName.itemSelectionRequiredValueString}" itemLabel="#{resourcesBundle.select}"/>
            <t:selectItems itemLabel="#{paramCode.name}" itemValue="#{paramCode.code.key}" var="paramCode" value="#{pageBeanName.lineParamter}"/>
         </t:selectOneMenu>
      </t:panelGrid>
   </h:panelGroup>
   <t:panelGroup>
      <t:panelGrid columns="2" align="center">
         <%-- <a4j:commandButton action="#{conditionLineSub.searchLine}" value="#{resourcesBundle.search}" styleClass="cssButtonSmall" reRender="dataT_data_panel,paging_panel" />--%>
         <h:panelGroup>
            <h:commandButton type="button" onclick="return searchAndvalidate();" styleClass="cssButtonSmall" id="SearchButton" value="#{resourcesBundle.search}"/>
            <a4j:jsFunction name="searchLines" action="#{conditionLineSub.searchLine}" reRender="dataT_data_panel,paging_panel"/>
         </h:panelGroup>
         <a4j:commandButton value="#{globalResources.cancelsearch}" action="#{conditionLineSub.cancelSearchLine}" styleClass="cssButtonSmall"
                            reRender="dataT_data_panel,paraCode,paging_panel,srch_elements"/>
      </t:panelGrid>
   </t:panelGroup>
</t:panelGrid>