<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<jsp:include page="/integration/org/jsps/ministry/searchministrieswithpagingdiv.jsp"/>
<t:panelGrid id="cnt1PnlGrid" forceId="true" columns="6" rowClasses="row02" width="100%" cellpadding="3" cellspacing="0">

   <%--<t:messages showDetail="true"/>--%>
    
        <t:outputText id="requestcreator-content_ministrytype" value="#{integrationBundle.type}"  styleClass="lable01 nowrap_txt" />
        <t:panelGroup>
        <t:selectOneMenu id="minType" value="#{pageBeanName.selectedTypeId}"  style="width:80px !important">
            <t:selectItems id="requestcreator-content_selectministrytype"
                   value="#{pageBeanName.banksTypeList}" var="list" itemLabel="#{list.name}"
                   itemValue="#{list.code.keys[0]}"/>
          <%--f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/--%>
          <%--<f:selectItem id="requestcreator-content_selectministrytype1"
                        itemLabel="#{integrationBundle.governmental}" itemValue="#{pageBeanName.govFlag}"/>--%>
          <%--<f:selectItem id="requestcreator-content_selectministrytype2"
                        itemLabel="#{integrationBundle.nongovernmental}" itemValue="#{pageBeanName.nonGovFlag}"/>--%>
          <a4j:support event="onchange" actionListener="#{pageBeanName.updateCategories}" 
          reRender="catPanelID,cats_combo,scriptGeneratorPanel,OperationBar,searchPanelId" oncomplete="clearInputText('categoryFilterationId');clearInputText('minFilterationId');clearInputText('ministryName');" />
        </t:selectOneMenu>
    </t:panelGroup>
    
        <t:outputText id="category_Label" value="#{integrationBundle.category}" styleClass="lable01"/>
        <t:panelGroup forceId="true" id="catPanelID">
        <t:inputText forceId="true" id="categoryFilterationId" styleClass="filteration_input_text"
                     onkeypress="filterationInputOnKeyPress(event,this,'cats_combo','errorCodeId1',changeCategory,null);"
                     onblur="filterationInputOnBlur(event,this,'cats_combo','errorCodeId1',changeCategory,null);" 
                     onkeyup="enabelIntegerOnly(this);">
        </t:inputText>
        <f:verbatim>&nbsp;</f:verbatim>
        <t:selectOneMenu forceId="true"
                         onchange="filterationDropboxOnChange(event,this,'categoryFilterationId','errorCodeId1',changeCategory,null);"
                         id="cats_combo" value="#{pageBeanName.selectedCatId}"
                         styleClass="textbox_175" converter="javax.faces.Long">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue="#{pageBeanName.virtualLongValue}"/>
            <t:selectItems var="cat" itemLabel="#{cat.name}" itemValue="#{cat.code.keys[0]}"
                           value="#{pageBeanName.categories}"/>
            <a4j:jsFunction name="changeCategory" actionListener="#{pageBeanName.clearMinData}"
                            reRender="ministryNamePanelId,catPanelID,scriptGeneratorPanel,OperationBar,searchPanelId" oncomplete="clearInputText('minFilterationId'); clearInputText('ministryName');"/>
        </t:selectOneMenu>
        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim><br/></f:verbatim>
        <t:panelGroup  forceId = "true" id="errorCodePanelId1">
            <t:outputText id="errorCodeId1" value="#{integrationBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="display:none;color: #FF0000;"/>
             <c:compareValidator componentToValidate="cats_combo" componentToCompare="virtualvalueId" operator="not" active="#{ministriesBnkAccountsBean.pageMode==0}"
                            errorMessage="#{integrationBundle.select_one_item}" highlight="false" display="dynamic"/>
        </t:panelGroup>
       
    </t:panelGroup>
    
    <t:outputText id="min_Label" value="#{integrationBundle.ministry}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="searchPanelId">
<t:panelGrid id="minPnlGrid" forceId="true" columns="3" width="100%" cellpadding="0" cellspacing="0">


            <t:inputText forceId="true" id="minFilterationId" styleClass="filteration_input_text"
                         onkeypress="filterDivInputOnKeyPress(event,this,changeMinistry,'searchBtn');"
                         onblur="filterDivInput('minFilterationId',changeMinistry,'searchBtn');"
                         
                         onkeyup="enabelIntegerOnly(this);" value="#{pageBeanName.selectedMiniId}">
                         <%--onmouseout="filterDivInput(this,changeMinistry,'searchBtn');"--%>
                <a4j:jsFunction name="changeMinistry" actionListener="#{pageBeanName.changeMinistry}"
                                reRender="ministryNamePanelId, OperationBar,ministryName,errorCodePanelId2"/>
            </t:inputText>
        
        <t:panelGroup forceId = "true" id="ministryNamePanelId">
            <t:inputText forceId="true" id="ministryName" disabled="true" styleClass="textboxmedium" value="#{pageBeanName.selectedMiniName}"/>
            <h:outputText value="*" styleClass="mandatoryAsterisk"/>       
            <f:verbatim><br/></f:verbatim>
            <c:requiredFieldValidator componentToValidate="ministryName" active="#{ministriesBnkAccountsBean.pageMode==0}"
                                      errorMessage="#{globalResources.missingField}" highlight="false" display="dynamic"/>
        </t:panelGroup>

        
        <a4j:commandButton id="searchMinistryBtn" value="..." action="#{pageBeanName.openSearchMinistryDiv}" styleClass="cssButtonSmall" disabled="#{pageBeanName.selectedCatId == null || pageBeanName.selectedCatId == pageBeanName.virtualLongValue}" reRender="searchMinistryDiv" oncomplete="changeVisibilityDiv(window.blocker,window.searchMinistryDiv);"/>
 </t:panelGrid>       
        <t:panelGroup  forceId = "true" id="errorCodePanelId2">
            <f:verbatim rendered="#{pageBeanName.wrongMinCode}"><br/></f:verbatim>
            <t:outputText id="errorCodeId2" value="#{integrationBundle.MessageForWrongCode}" forceId = "true" styleClass="validation_error" style="color: #FF0000;" rendered="#{pageBeanName.wrongMinCode}"/>
        </t:panelGroup>        
        
    </t:panelGroup>
       
</t:panelGrid>
<t:panelGroup style="display:block;text-align: center;">
    <t:commandButton id="searchBtn" forceId="true" value="#{integrationBundle.display}" styleClass="cssButtonSmall" action="#{pageBeanName.updateAccountsList}"  onclick="clearMsgs();return validatemyForm();"/>
</t:panelGroup>
<t:saveState value="#{ministriesBnkAccountsBean.pageMode}"/>
