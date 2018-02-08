<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>



<t:panelGrid  onkeypress="FireButtonClickOldStyle(event,'searchButton')" columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" border="0" forceId="true" id="mainDataPanel">
 <%--<t:messages showDetail="true" />--%>   
    <%-- category--%>
    <h:outputText id="category_name" value="#{integrationBundle.category}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="categoryId" styleClass="filteration_input_text"
                     value="#{insuranceFormBaseBean.pageDTO.catCode}"
                     onkeypress="filterationInputOnKeyPress(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                     onblur="filterationInputOnBlur(event,this,'categoryList','errorCodeId',changeCategoryVal);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            <a4j:jsFunction name="changeCategoryVal" actionListener="#{insuranceFormBaseBean.filterByCategory}"
                            oncomplete="resetMinistry();"
                            reRender="ministryGroupPanel,errorCodePanelId,errorCodePanelId2"/>
        </t:inputText>
        <t:selectOneMenu id="categoryList" forceId="true" styleClass="DropdownboxLarge"
                         value="#{insuranceFormBaseBean.pageDTO.catCode}" style="width: 220px;"
                         onchange="filterationDropboxOnChange(event,this,'categoryId','errorCodeId',changeCategoryValD);">
            <f:selectItem itemValue="" itemLabel="#{integrationBundle.select}"/>
            <t:selectItems value="#{insuranceFormBaseBean.categoryList}" var="categoryList" itemValue="#{categoryList.code.catCode}"
                           itemLabel="#{categoryList.name}"/>
            <a4j:jsFunction name="changeCategoryValD" actionListener="#{insuranceFormBaseBean.filterByCategory}"
                            oncomplete="resetMinistry();"
                            reRender="ministryGroupPanel,errorCodePanelId,errorCodePanelId2"/>
        </t:selectOneMenu>
       <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
       
        <t:panelGroup forceId="true" id="errorCodePanelId" style="display:block;width:250px;">
            <t:outputLabel id="errorCodeId" value="#{integrationBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
        <c:requiredFieldValidator  componentToValidate="categoryList" display="dynamic" errorMessage="#{globalResources.missingField}" />
    </t:panelGroup>
    <%-- minisry--%>
    <h:outputText id="ministry_name" value="#{integrationBundle.ministry}" styleClass="lable01"/>
    <t:panelGroup forceId="true" id="ministryGroupPanel">
        <t:inputText forceId="true" id="ministryId" styleClass="filteration_input_text"
                     value="#{insuranceFormBaseBean.pageDTO.minCode}"
                     disabled="#{insuranceFormBaseBean.pageDTO.catCode == null}"
                     onkeypress="filterationInputOnKeyPress(event,this,'ministryListID','errorCodeId2',null);"
                     onblur="filterationInputOnBlur(event,this,'ministryListID','errorCodeId2',null);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">

        </t:inputText>
        <t:selectOneMenu id="ministryListID" forceId="true" styleClass="DropdownboxLarge" style="width: 220px;"
                         value="#{insuranceFormBaseBean.pageDTO.minCode}"   disabled="#{insuranceFormBaseBean.pageDTO.catCode == null}"
                         onchange="filterationDropboxOnChange(event,this,'ministryId','errorCodeId',null);">
            <f:selectItem itemValue="" itemLabel="#{integrationBundle.select}"/>
            <t:selectItems value="#{insuranceFormBaseBean.ministryList}" var="ministryList" itemValue="#{ministryList.code.minCode}"
                           itemLabel="#{ministryList.name}"/>
          
        </t:selectOneMenu>
         <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
         
         
          <t:panelGroup forceId="true" id="errorCodePanelId2" style="display:block;width:250px;">
            <t:outputLabel id="errorCodeId2" value="#{integrationBundle.MessageForWrongCode}" forceId="true"
                           styleClass="error" style="display:none"/>
        </t:panelGroup>
         <c:requiredFieldValidator componentToValidate="ministryListID"   display="dynamic"  errorMessage="#{globalResources.missingField}" />
    </t:panelGroup>

    <h:outputText value="#{integrationBundle.year}" styleClass="lable01"/>
    <t:panelGroup id="year_panel" forceId="true">
        <t:selectOneMenu forceId="true" id="yearMenu" styleClass="DropdownboxLarge" value="#{insuranceFormBaseBean.pageDTO.year}"
                         converter="javax.faces.Long">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
            <t:selectItems value="#{insuranceFormBaseBean.years}" itemLabel="#{year.code.key}" itemValue="#{year.code.yearCode}"
                           var="year"/>
        </t:selectOneMenu>
       <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <%--<t:outputText id="yearErrMsg" forceId="true" value="#{integrationBundle.selectItem}" style="display:none;"
                      styleClass="error"/>--%>
         <c:requiredFieldValidator componentToValidate="yearMenu"   display="dynamic"  
            errorMessage="#{integrationBundle.select_one_item}" />
    </t:panelGroup>

    <h:outputText value="#{integrationBundle.month}" styleClass="lable01"/>
    <t:panelGroup id="month_panel" forceId="true">
        <t:selectOneMenu forceId="true" id="monthMenu" styleClass="DropdownboxLarge"
                         value="#{insuranceFormBaseBean.pageDTO.month}">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
            <t:selectItems value="#{insuranceFormBaseBean.months}" itemLabel="#{months.name}" itemValue="#{months.code.key}"
                           var="months"/>
        </t:selectOneMenu>
       <h:outputLabel value="*" styleClass="mandatoryAsterisk"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <%--<t:outputText id="monthErrMsg" forceId="true" value="#{integrationBundle.selectItem}" style="display:none;"
                      styleClass="error"/>--%>
         <c:requiredFieldValidator  componentToValidate="monthMenu"   display="dynamic"  
            errorMessage="#{integrationBundle.select_one_item}" />
    </t:panelGroup>





     
</t:panelGrid>
<%-- --%>
<t:panelGroup style="display:block;text-align:center;">
<t:panelGrid columns="2" align="center"> 
    <t:commandButton forceId="true" id="searchButton" styleClass="cssButtonSmall"
                     value="#{globalResources.view_menu}" action="#{pageBeanName.search}" onclick="return validatemyForm();"/>
                     
                     
    
   </t:panelGrid>                  
</t:panelGroup>
<t:panelGrid  columns="4" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" border="0" forceId="true" id="signatureDataPanel">

<t:outputLabel value="#{integrationBundle.signature}" styleClass="lable01"/>
        <t:panelGroup colspan="3">
        <t:selectOneMenu forceId="true" id="MAsignList" styleClass="DropdownboxLarge" value="#{insuranceFormBaseBean.pageDTO.signature}" style="width:640px">
            <f:selectItem itemLabel="#{integrationBundle.select}" itemValue=""/>
            <f:selectItem itemLabel="#{integrationBundle.emp1}" itemValue="1"/>
            <f:selectItem itemLabel="#{integrationBundle.emp2}" itemValue="2"/>
            <f:selectItem itemLabel="#{integrationBundle.emp3}" itemValue="3"/>
            <f:selectItem itemLabel="#{integrationBundle.emp4}" itemValue="4"/>
            <f:selectItem itemLabel="#{integrationBundle.emp5}" itemValue="5"/>
            
        </t:selectOneMenu>
        </t:panelGroup>
        </t:panelGrid>
<script type="text/javascript">
  /*function validateSearch() {
      var month = document.getElementById("monthMenu");
      var year = document.getElementById("yearMenu");
      var yearErrMsg = document.getElementById("yearErrMsg");
      var monthErrMsg = document.getElementById("monthErrMsg");

      var valid = true;
      if (month.value != null && month.value != '' && month.value != '-100') {

          if (year.value == null || year.value == '' || year.value == '-100') {
              yearErrMsg.style.display = "block";
              valid = false;
          }
          else {
              yearErrMsg.style.display = "none";
          }
      }
      
       if (year.value != null && year.value != '' && year.value != '-100') {

          if (month.value == null || month.value == '' || month.value == '-100') {
              monthErrMsg.style.display = "block";
              valid = false;
          }
          else {
              monthErrMsg.style.display = "none";
          }
      }
      return valid;

  }*/
  
  
</script>
