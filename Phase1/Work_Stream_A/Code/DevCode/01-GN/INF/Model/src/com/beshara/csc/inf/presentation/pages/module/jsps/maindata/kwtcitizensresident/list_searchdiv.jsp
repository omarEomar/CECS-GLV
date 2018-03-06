<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<tiles:importAttribute scope="request"/>
<t:panelGrid id="searchPanel" columns="2" dir="#{shared_util.pageDirection}" 
             rowClasses="row02,row01" width="100%" cellpadding="3" cellspacing="0">
    <t:outputLabel styleClass="lable01" value="#{resourcesBundle.kwt_citizen_code}"/>
    <t:inputText forceId="true" id="citezenCode" value="#{pageBeanName.kwtCitizensSearchDTO.civilId}"
                 styleClass="textbox" onkeypress="enabelIntegerOnlyWithZero(citezenCode);"/>        
                 
    <t:outputLabel value="#{resourcesBundle.kwt_citizen_name}" styleClass="lable01"/>
    <t:inputText forceId="true" id="citezenName" value="#{pageBeanName.kwtCitizensSearchDTO.name}"
                 styleClass="textboxlarge"/>
                 
    <t:outputText value="#{resourcesBundle.kwt_citizen_BD}" styleClass="lable01"/>
     <t:panelGroup>
       <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" 
                        popupDateFormat="dd/MM/yyyy" forceId="true"  id="birthDatecitezen" 
                        value="#{pageBeanName.kwtCitizensSearchDTO.birthDate}"
                        maxlength="#{pageBeanName.calendarTextLength}" 
                        onblur="return validateInputCalenderFormate('birthDateClientId','null','null');" 
                        styleClass="textbox" currentDayCellClass="currentDayCell" 
                        renderAsPopup="true" align="#{globalResources.align}" 
                        popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
            <f:converter converterId="SqlDateConverter"/>
        </t:inputCalendar>
     </t:panelGroup>   
                     
     <t:outputText value="#{resourcesBundle.kwt_citizen_mar_status}" styleClass="lable01"/>
     <t:selectOneMenu forceId="true"  id="kwt_citizen_mar_status" styleClass="Dropdownbox"  value="#{pageBeanName.kwtCitizensSearchDTO.maritalStatusCode}" converter="javax.faces.Long">
        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}"/>
        <t:selectItems value="#{pageBeanName.maritalStatusTyps}" itemLabel="#{entry.name}" itemValue="#{entry.code.marstatusCode}" var="entry"/>              
    </t:selectOneMenu>
    
  <t:outputText value="#{resourcesBundle.kwt_citizen_nat}" styleClass="lable01"/>
    <t:selectOneMenu forceId="true" id="nationality_name"  styleClass="Dropdownbox" value="#{pageBeanName.kwtCitizensSearchDTO.nationality}" converter="javax.faces.Long">
        <f:selectItem itemValue="" itemLabel="#{resourcesBundle.select_one_item}"/>
        <t:selectItems value="#{pageBeanName.nationalties}" itemLabel="#{entry.name}" itemValue="#{entry.code.cntryCode}" var="entry"/>              
    </t:selectOneMenu>
    
</t:panelGrid>
<t:panelGrid id="buttonsPanel" columns="2" align="center" dir="#{shared_util.pageDirection}">
    <t:commandButton value="#{globalResources.SearchButton}" styleClass="cssButtonSmall"
                     onclick="return validateAdvSearch();" action="#{pageBeanName.search}"/>
    <t:commandButton value="#{globalResources.back}" id="customSearchBackBtn" forceId="true" styleClass="cssButtonSmall"
                      action="#{pageBeanName.cancelSearch}"/>
</t:panelGrid>
<t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="birthDatecitezen,130,90" />
<script type="text/javascript">
  function setFocusAtMySearchElement() {
      document.getElementById('citezenCode').focus();
  }

  function setFocusAtMySearchText() {
      document.getElementById('searchJobCode').focus();
  }
  
  function enabelIntegerOnlyWithZero(field) {
    if (!/^\d*$/.test(field.value)) {
        field.value = field.value.replace(/[^\d]/g,"");
    }
}

</script>
