<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

               <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
                <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalExceptions"/>
                
             
                <t:panelGroup forceId="true" id="divAddLookup" style="width:100%">
                <t:saveState value="#{pageBeanName.success}"/>
                <t:outputText forceId="true" id="successAddLockup" value="#{globalResources.SuccessAdds}" styleClass="sucessMsg"  rendered="#{pageBeanName.success}"/>
                <f:verbatim> <br/></f:verbatim>
                <h:outputText id="error" value="#{globalExceptions[pageBeanName.errorMsg]}" styleClass="errMsg" rendered="#{pageBeanName.showErrorMsg}"/>
                <h:outputText id="clientErrorMessage" styleClass="errMsg" />
                   
                   
                <t:panelGrid columns="2" border="0" styleClass="row01" width="100%" >
                
                 <h:outputText value="#{globalResources.SearchName}:" styleClass="lable01"/> 
                 <t:panelGroup>
                     <h:inputText maxlength="#{pageBeanName.nameMaxLength}" id="Name" value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge"/>
                     <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                  <c:requiredFieldValidator componentToValidate="Name" display="dynamic" errorMessage="#{globalResources.missingField}" highlight="false" active="#{handicapStatusListBean.pageMode==1}"/>
                 </t:panelGroup>
                    <h:outputText value="#{resourcesBundle.INF_HandicapStatusDTO_handicapRatio}:" styleClass="lable01"/>
                   <t:panelGroup>  <t:inputText  id="handicapRatioXy" value="#{pageBeanName.pageDTO.handicapRatio}" styleClass="textboxsmall" forceId="true"/>
                  <h:outputText value="%" styleClass="lable01"/> 

                    <c:regularExpressionValidator componentToValidate="handicapRatioXy"  pattern="/^(\\d{0,2}(\\.\\d{0,3})?$)|(100(\\.0{0,3})?)$/"  display="dynamic" errorMessage="#{resourcesBundle.from_to_percentage_msg}" highlight="false" active="#{handicapStatusListBean.pageMode==1}"/>      
                   </t:panelGroup>
                </t:panelGrid> 
                       
                   <t:panelGrid columns="3" border="0" align="center">
                    <%-- Start By css  used to spreate button --%>
                   
                    <t:commandButton forceId="true" styleClass="cssButtonSmall" id="SaveButton" value="#{globalResources.SaveButton}" action="#{pageBeanName.save}" onclick="return validatemyForm();"/>
                     <h:panelGroup>
                     <t:commandButton forceId="true" type="button"  onclick="validateSaveAndNewClientValidator();" styleClass="cssButtonSmall" id="SaveNewButton" value="#{globalResources.AddNewButton}"/>
                     <a4j:jsFunction name="saveAndNew"  action="#{pageBeanName.saveAndNew}" reRender="divAddLookup"/>
                     </h:panelGroup>
                   
                       <%--a4j:commandButton  value="#{globalResources.back}" styleClass="cssButtonSmall" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');" action="#{pageBeanName.cancelAdd}" reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,scriptPanelGroup"/--%>
                       <h:panelGroup>
                            <t:commandButton forceId="true" id="backButtonAddDiv" onblur="setFocusOnlyOnElement('myForm:Name');" onclick="backJsFunction(); return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>
                            <a4j:jsFunction name="backJsFunction" action="#{pageBeanName.cancelAdd}" oncomplete="hideLookUpDiv(window.blocker,window.lookupAddDiv,'myForm:Name','myForm:error','add');settingFoucsAtListPage(); " reRender="divAddLookup,dataT_data_panel,noOfRecords,paging_panel,listSize,OperationBar"/>
                            <%--t:commandButton id="foucs_btn" forceId="true" style="visible:false;hidden=true;" value="#{globalResources.SaveButton}" onclick="settingFoucsAtDivAdd();return false;"/--%>
                        </h:panelGroup>
                    <%-- End By css  used to spreate button --%>
                    
                    </t:panelGrid>
                   
                </t:panelGroup>
             <f:verbatim><br/></f:verbatim>