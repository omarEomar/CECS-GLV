<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
    .divContent1Dynamic{width:730px !important;}/*---- by hooma---*/
    #showdatatableContent_showContent1_content{height: 348px !important; vertical-align: text-top; width:99.8% !important}
    #navigationDiv{margin-top: -10px;}
</htm:style>
              <t:panelGroup>
                  <t:panelGrid rendered="#{detailBeanName.currentListSize == 0}" styleClass="msg" style="height:100px">
                    <h:outputText value="#{globalResources.global_noTableResults}"/>
                    <h:outputText value="#{globalResources.global_noTableResults_m2m}"/>
                </t:panelGrid>
                  
                <t:panelGroup forceId="true" id="dataTable" >
                    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false" 
                                         value="#{detailBeanName.currentDisplayDetails}"
                                         rowIndexVar="index"
                                         binding="#{detailBeanName.currentDataTable}"
                                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                         footerClass="grid-footer"
                                         styleClass="grid-footer"
                                         headerClass="standardTable_Header"
                                         rowClasses="standardTable_Row1,standardTable_Row2"
                                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                                         width="100%" align="top"
                                         dir="#{shared_util.pageDirection}"
                                         preserveSort="true">
                    
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                    <f:facet name="header">
                      <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                       <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                     </t:selectBooleanCheckbox>
                    </f:facet>
                     <t:selectBooleanCheckbox forceId="true"   id="chk2" value="#{list.checked}">
                                               
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                    </t:selectBooleanCheckbox>
               </t:column>
                                
                               
                                
                                
                                <%-- t:column>
                                <h:outputLabel value="#{index}" />
                                </t:column--%>
                                
                                <t:column id="clm_3" width="10%">
                                    <f:facet name="header">
                                            <h:outputText id="brace_h" value="#{resourcesBundle.brace}"/>
                                    </f:facet>
                                    
                                    <t:selectOneMenu value="#{list.rightArcs}" valueChangeListener="#{detailBeanName.overViewPanelLisentner}" >
                                    <f:selectItem itemLabel="" itemValue="" />
                                        <f:selectItem itemLabel="{" itemValue="(" />
                                        <f:selectItem itemLabel="{{" itemValue="((" />
                                        <f:selectItem itemLabel="{{{" itemValue="(((" />
                                        <f:selectItem itemLabel="{{{{" itemValue="((((" />
                                        <f:selectItem itemLabel="{{{{{" itemValue="(((((" />
                                        <f:selectItem itemLabel="{{{{{{" itemValue="((((((" />
                                        <f:selectItem itemLabel="{{{{{{{" itemValue="(((((((" />
                                        <f:selectItem itemLabel="{{{{{{{{" itemValue="((((((((" />
                                        <f:selectItem itemLabel="{{{{{{{{{" itemValue="(((((((((" />
                                        <f:selectItem itemLabel="{{{{{{{{{{" itemValue="((((((((((" />
                                        <a4j:support event="onchange" reRender="overviews"   />
                                    </t:selectOneMenu>
                                </t:column>
                                
                                <t:column id="clm_4" width="55%" >
                                    <f:facet name="header">
                                            <h:outputText id="line_h" value="#{resourcesBundle.line}"/>
                                    </f:facet>
                                    <t:inputText  onmouseout="hideTip()" onmouseover="displayTooltip(event,this)" styleClass="mastertextboxlarge" value="#{list.linesDTO.name}" id="lineDTO" readonly="true" forceId="true" valueChangeListener="#{detailBeanName.overViewPanelLisentner}" />
                                    <h:outputText value="*" styleClass="mandatoryAsterisk"  rendered="#{pageBeanName.maintainMode!=2}" />
                                    <h:commandButton binding="#{detailBeanName.readLineBtn}" value="..." styleClass="cssButtonSmaller" action="#{detailBeanName.readLine}"
                                    disabled="#{pageBeanName.maintainMode==2}">
                                     <f:attribute name="selectedRowNo" value="#{index}" />
                                    </h:commandButton>
                                    
                                </t:column>
                                
                                <t:column id="clm_5" width="10%">
                                    <f:facet name="header">
                                            <h:outputText id="brace2_h" value="#{resourcesBundle.brace}"/>
                                    </f:facet>
                                    <t:selectOneMenu value="#{list.leftArcs}" valueChangeListener="#{detailBeanName.overViewPanelLisentner}"   >
                                       <f:selectItem itemLabel="" itemValue=""  />
                                        <f:selectItem itemLabel="}" itemValue=")"  />
                                        <f:selectItem itemLabel="}}" itemValue="))" />
                                        <f:selectItem itemLabel="}}}" itemValue=")))" />
                                        <f:selectItem itemLabel="}}}}" itemValue="))))" />
                                        <f:selectItem itemLabel="}}}}}" itemValue=")))))" />
                                        <f:selectItem itemLabel="}}}}}}" itemValue="))))))" />
                                        <f:selectItem itemLabel="}}}}}}}" itemValue=")))))))" />
                                        <f:selectItem itemLabel="}}}}}}}}" itemValue="))))))))" />
                                        <f:selectItem itemLabel="}}}}}}}}}" itemValue=")))))))))" />
                                        <f:selectItem itemLabel="}}}}}}}}}}" itemValue="))))))))))" />
                                        <a4j:support event="onchange" reRender="overviews"    />
                                    </t:selectOneMenu>
                                </t:column>
                                
                                
                                 <t:column id="clm_2" width="15%">
                                    <f:facet name="header">
                                            <h:outputText id="header_name" value="#{resourcesBundle.link}"/>
                                    </f:facet>
                                    
                                <t:selectOneMenu id="joinCondition" forceId="true" valueChangeListener="#{detailBeanName.overViewPanelLisentner}"     styleClass="Dropdownboxsmall"  value="#{list.jonconditionSign}" >  
                                    <f:selectItem itemLabel="" itemValue="-100" />
                                    <t:selectItems value="#{detailBeanName.joinConditions}" var="element" itemLabel="#{element.name}" itemValue="#{element.code.key}"  />
                                    <a4j:support event="onchange" reRender="overviews"    />
                                </t:selectOneMenu>
                                </t:column>
                                
                                
                                 
                                
                    
                </t:dataTable>
                </t:panelGroup>
                
                
              
                </t:panelGroup>
                  
                  <t:panelGrid id="overviews" forceId="true" dir="#{shared_util.pageDirection}" cellpadding="3" cellspacing="0" columns="2" width="100%" styleClass="gopentab" rendered="#{conditionIntgLineDetailBean.currentListSize > 0}" >
                    
                    
                    <t:outputLabel value="#{resourcesBundle.overview}" styleClass="lable01" />
                    <%--<a4j:support action="#{detailBeanName.overview}" event="onclick" reRender="overview" /> --%>
                    
                    <h:panelGroup>
                    
                    <t:inputTextarea readonly="true"  value="#{detailBeanName.queryOverview}" styleClass="masterinput_hooma" rows="5" style="width:650px"/>
                </h:panelGroup>
                
                </t:panelGrid>
                <script type="text/javascript">
                    adjustDataTable('dataTable',230);
                </script>
