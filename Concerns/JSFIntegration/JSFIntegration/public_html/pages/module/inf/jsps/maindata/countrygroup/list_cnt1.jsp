<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:saveState value="#{pageBeanName.selectedCatCode}"/>
<t:saveState  value="#{pageBeanName.groupCategoryList}"/>

<t:panelGrid width="100%" columns="4"  align="center"  rowClasses="row01,row02" cellpadding="0" cellspacing="0">

   <t:outputText value="#{resourcesBundle.group_category}"  styleClass="lable01"/>
    <t:panelGroup>
         <t:selectOneMenu id="bookTypesListID" forceId="true"  styleClass="DropdownboxMedium2" value="#{pageBeanName.selectedCatCode}" converter="javax.faces.Long">
             <f:selectItem itemValue="#{pageBeanName.virtualLongValue}" itemLabel="#{resourcesBundle.select_one_item}" />
             <t:selectItems value="#{pageBeanName.groupCategoryList}" var="groupCategoryList" itemValue="#{groupCategoryList.code.cntrygrpCode}" itemLabel="#{groupCategoryList.name}" /> 
             <a4j:support actionListener="#{pageBeanName.filterByCategory}"  event="onchange" reRender="OperationBar,dataT_data_panel,paging_panel"/>
         </t:selectOneMenu>
     </t:panelGroup>
</t:panelGrid>