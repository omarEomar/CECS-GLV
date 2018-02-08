<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<t:panelGrid onkeypress="FireButtonClickOldStyle(event,'myForm:SearchButton');" id="srch_elements" forceId="true"
             width="100%" columns="6" align="center" rowClasses="row01,row02" cellpadding="3" cellspacing="0" border="0">
    <h:outputLabel value="#{resourcesBundle.lineCode}" styleClass="lable01"/>
    <h:panelGroup>
        <t:inputText onkeyup="enabelIntegerOnly(this);" id="codeSearch" forceId="true" value="#{pageBeanName.code}"
                     styleClass="textboxsmall" converter="javax.faces.Long"/>
        <f:verbatim>
            <br/>
        </f:verbatim>
        <c2:regularExpressionValidator componentToValidate="codeSearch" pattern="/^[0-9]/" display="dynamic"
                                       errorMessage="#{resourcesBundle.numbersOnly}" highlight="false"/>
    </h:panelGroup>
    <h:outputLabel value="#{resourcesBundle.LineName}" styleClass="lable01"/>
    <h:inputText id="lineName" value="#{pageBeanName.lineName}" styleClass="textboxmedium"/>
    <h:outputLabel value="#{resourcesBundle.parameter}" styleClass="lable01"/>
    <t:panelGroup>
        <t:inputText forceId="true" id="parameterId" styleClass="filteration_input_text"
                     onkeypress="filterationInputOnKeyPress(event,this,'paraCode','parameterError',changeParameterVal);"
                     onblur="filterationInputOnBlur(event,this,'paraCode','parameterError',changeParameterVal);"
                     onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
            <a4j:jsFunction name="changeParameterVal"
                            reRender="savePanel,parameters,dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
        </t:inputText>
        <t:selectOneMenu id="paraCode" value="#{pageBeanName.paramCode}" styleClass="DropdownboxLarge" forceId="true"
            onchange="filterationDropboxOnChange(event,this,'parameterId','parameterError',null);">
            <f:selectItem itemValue="#{pageBeanName.itemSelectionRequiredValueString}" itemLabel="#{resourcesBundle.all}"/>
            <t:selectItems itemLabel="#{paramCode.name}" itemValue="#{paramCode.code.key}" var="paramCode"
                           value="#{pageBeanName.lineParamter}"/>
        </t:selectOneMenu>
        <t:outputLabel id="parameterError" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
    </t:panelGroup>
</t:panelGrid>
<t:panelGroup colspan="6">
    <t:panelGrid width="100%" columnClasses="center">
        <t:panelGroup>
            <h:commandButton type="button" onclick="return searchAndvalidate();" styleClass="cssButtonSmall"
                             id="SearchButton" value="#{resourcesBundle.search}"/>
            <a4j:jsFunction name="searchLines" action="#{conditionIntgLineSub.searchLine}"
                            reRender="dataT_data_panel,paging_panel"/>
            <a4j:commandButton value="#{globalResources.cancelsearch}" action="#{conditionIntgLineSub.cancelSearchLine}"
                               styleClass="cssButtonSmall" oncomplete="clearFilterationInput('parameterId');"
                               reRender="dataT_data_panel,paraCode,paging_panel,srch_elements"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function clearFilterationInput(inputtextId) {
      var inputtext = document.getElementById(inputtextId);
      inputtext.value = '';
  }
  </script>