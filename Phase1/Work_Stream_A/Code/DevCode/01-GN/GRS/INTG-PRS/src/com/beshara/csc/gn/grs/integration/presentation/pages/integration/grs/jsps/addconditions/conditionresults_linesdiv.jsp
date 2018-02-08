<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.csc.gn.grs.presentation.resources.grs" var="grs"/>
<t:panelGrid >
     <t:panelGrid columns="2" cellpadding="3" cellspacing="0" rowClasses="row02,row01" width="100%">
     
     <h:outputText id="civilIDForEmp" value="#{globalResources.globalCivilId}" styleClass="lable01 nowrap_txt"/>
     <t:inputText disabled="true" forceId="true" id="civilIDForEmpId"
                         value="#{pageBeanName.civilId}" styleClass="textboxmedium"/>
     
     <h:outputText id="empName" value="#{globalResources.globalName}" styleClass="lable01 nowrap_txt"/>
     <t:inputText disabled="true" forceId="true" id="empNameId"
                         value="#{pageBeanName.empName}" styleClass="textboxlarge"/>                    
     </t:panelGrid>
     
    <t:panelGroup forceId="true" id="dataT_data_pnl" styleClass="delDivScroll" style="width: 480px;">
        <t:dataTable id="dataT_data1234" var="list" styleClass="grid-footer"
                     value="#{conditionIntgResultsBean.linesList}"  renderedIfEmpty="false">
       
            <t:column id="code_column1" sortable="false" width="8%">
                <f:facet name="header">
                    <t:outputText id="code" forceId="true"  value="#{grs.code}"/>
                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.lineDTO.code.key}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.lineDTO.code.key}"/>
            </t:column>
            <t:column id="name_column1" sortable="false" width="50%">
                <f:facet name="header">
            <t:outputText id="name" forceId="true"  value="#{grs.linedetails}"/>

                </f:facet>
                
                  <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{list.lineDTO.name}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{list.lineDTO.name}"/>
            </t:column>
            <t:column id="actualValue_column1" sortable="false" width="25%">
                <f:facet name="header">
            <t:outputText id="actualValue" forceId="true"  value="#{grs.lineActualValue}"/>

                </f:facet>
                
                  <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{(list.actualLineValue != null) ? list.actualLineValue : resourcesBundle.noActualValue}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{(list.actualLineValue != null) ? list.actualLineValue : resourcesBundle.noActualValue}"/>
            </t:column>
            
            <t:column id="passCond_column1" sortable="false" width="17%">
                <f:facet name="header">
                 <t:outputText id="passCond" forceId="true"  value="#{grs.lineStatus}"/>

                </f:facet>
                <h:outputText styleClass="errMsg" style="color:RED" rendered="#{list.passAllConditions!= 1}"
                                          value="#{(list.passAllConditions == 0) ? resourcesBundle.ConditionsNotPassed:resourcesBundle.ErrorCondtionNotFound}"/>
                <h:outputText styleClass="sucessMsg2" style="color:GREEN" rendered="#{list.passAllConditions== 1}"
                                          value="#{resourcesBundle.ConditionsPassed}"/>
                
            </t:column>
          
        </t:dataTable>
        
       
        
    </t:panelGroup>
    <h:panelGrid columns="2" rendered="#{ pageBeanName.linesListSize ==0 }">
        <f:verbatim>
            <br/>
        </f:verbatim>
        <h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
    </h:panelGrid>
            <h:panelGroup style="text-align: center; display: block;">
        <t:commandButton forceId="true" id="backButtonCustomDiv1" 
                        onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);" styleClass="cssButtonSmall"  value="#{globalResources.back}"/>
        
    </h:panelGroup>
</t:panelGrid>

<!-- End Paging -->
