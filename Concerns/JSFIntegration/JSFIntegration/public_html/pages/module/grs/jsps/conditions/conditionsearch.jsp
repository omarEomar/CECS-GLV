<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.csc.sharedutils.presentation.msgresources.global" var="globalResources"/>



<t:message for="lineValuesList" />
 
<t:panelGrid columns="4"  width="85%" cellpadding="1" cellspacing="1" forceId="true" id="searchGridID" onkeypress="FireButtonClick('search_line_btn');">

 
 <h:outputText id="Condition_Code" value="#{resourcesBundle.Condition_Code}" styleClass="lable01" />
 <t:inputText value="#{conditionListBean.searchConditionsDTO.conditionCode}" styleClass="textbox" forceId="true" id="conditionCodeID"/>
 <h:outputText id="conditionName" value="#{resourcesBundle.conditionName}" styleClass="lable01" />
 <t:inputText value="#{conditionListBean.searchConditionsDTO.conditionName}" styleClass="textbox"/>
 <h:outputText id="Condition_Line" value="#{resourcesBundle.Condition_Line}" styleClass="lable01" />
 
 <t:panelGroup>
      <t:selectOneMenu id="lineList" forceId="true" styleClass="textboxmedium" value="#{conditionListBean.lineCodeText}">
       <f:selectItem itemValue="" itemLabel="#{resourcesBundle.Selected_Item_Default}"/>
       <t:selectItems value="#{conditionListBean.linesList}" var="linesList" itemValue="#{linesList.code.key}" itemLabel="#{linesList.name}"/>
      </t:selectOneMenu>
 </t:panelGroup>
 
 <h:outputText id="Condition_Parameters" value="#{resourcesBundle.Condition_Parameters}" styleClass="lable01" />
 <t:panelGroup>
  <t:selectOneMenu forceId="true" id="parameters"  value="#{conditionListBean.parameterCode}"  styleClass="textboxmedium">
                    <f:selectItem itemLabel="#{grsResources.select}" itemValue=""/>
                    <t:selectItems var="parametersList" itemLabel="#{parametersList.name}" itemValue="#{parametersList.code.key}" value="#{conditionListBean.parametersList}"/>
                     <a4j:support event="onchange"  actionListener="#{conditionListBean.updateParametrValuesList}" reRender="values" />
            </t:selectOneMenu> 
 </t:panelGroup>
 
  <h:outputText id="Condition_LineValues" value="#{resourcesBundle.Condition_LineValues}" styleClass="lable01" />
   <t:selectOneMenu forceId="true" id="values"  value="#{conditionListBean.searchConditionsDTO.lnevalue}" readonly="#{conditionListBean.parameterCode==null || conditionListBean.parameterCode==''}"  styleClass="textboxmedium">
                 <f:selectItem itemLabel="#{grsResources.select}" itemValue=""/>
                 <t:selectItems var="lineValuesList" itemLabel="#{lineValuesList.name}" itemValue="#{lineValuesList.strCode}" value="#{conditionListBean.lineValuesList}"/>
                
 </t:selectOneMenu>
 
 </t:panelGrid>
 <%-- <t:selectOneMenu id="lineValuesList" forceId="true" styleClass="textbox" value="#{conditionListBean.searchConditionsDTO.lnevalue}">
  
   <f:selectItem itemValue="#{conditionListBean.itemSelectionRequiredValueString}" itemLabel="#{resourcesBundle.Selected_Item_Default}"/>
   <t:selectItems value="#{conditionListBean.lineValuesList}" var="parameterValues" itemLabel="#{parameterValues.name}" itemValue="#{parameterValues.strCode}"/>
  </t:selectOneMenu--%>





 <t:panelGrid  align="center" columns="2" id="buttonsPanel">
   <t:commandButton value="#{globalResources.SearchButton}" action="#{conditionListBean.search}" styleClass="cssButtonSmall"/>
   <t:panelGroup>
    <t:commandButton value="#{globalResources.back}" styleClass="cssButtonSmall" onclick="back_search_function();" type="button"  forceId="true"  id="customSearchBackBtn" onblur="setFocusAtMySearchDiv();" />
          <a4j:jsFunction name="back_search_function" reRender="searchGridID,dataT_data_panel,noOfRecords,paging_panel"  oncomplete="hideLookUpDiv(window.blocker,window.divSearch,null,null,null);" action="#{conditionListBean.cancelSearch}" />
   </t:panelGroup>               
  </t:panelGrid>

