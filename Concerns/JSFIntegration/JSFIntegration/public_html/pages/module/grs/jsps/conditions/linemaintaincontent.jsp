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
    #showdatatableContent_showContent1_content{height: 267px !important; vertical-align: text-top; width:99.8% !important}
    #navigationDiv{margin-top: -10px;}
</htm:style>
             
              <t:panelGroup  styleClass="delDivScroll" style="height:150px;width:100%;">
                <t:panelGroup forceId="true" id="dataTable" style="display: block; overflow-x: hidden; overflow-y: auto; margin-top: 7px; max-height: 165px;">
                    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
                                         value="#{detailBeanName.currentDisplayDetails}"
                                         rowIndexVar="index"
                                         binding="#{detailBeanName.currentDataTable}"
                                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');"
                                         footerClass="grid-footer"
                                         styleClass="grid-footer"
                                         headerClass="standardTable_Header"
                                         rowClasses="standardTable_Row1,standardTable_Row2"
                                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
                                         width="100%" align="top"
                                         dir="#{shared_util.pageDirection}"
                                         preserveSort="true">
                    
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{conditionsMaintainBean.maintainMode!=2}">
                    <f:facet name="header">
                      <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}">
                       <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                     </t:selectBooleanCheckbox>
                    </f:facet>
                     <t:selectBooleanCheckbox forceId="true"   id="chk2" value="#{list.checked}">
                                               
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="divDeleteAlert,OperationBar" />
                    </t:selectBooleanCheckbox>
               </t:column>
               
               <t:column id="radio_column" styleClass="standardTable_Header3" width="5%" rendered="#{conditionsMaintainBean.maintainMode==2}">  
                    <f:facet name="header"/>
                    <t:selectOneRadio valueChangeListener="#{detailBeanName.selectedRadioButton}" styleClass="radioButton_DataTable"  id="chk" value="#{detailBeanName.selectedRadio}" onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUp(this);">
                       <f:selectItem    itemLabel="" itemValue="#{list.code.key}" />
                       <a4j:support event="onclick" reRender="OperationBar"/>
                    </t:selectOneRadio>
              </t:column>
                                
                               
                                
                                
                                <%-- t:column>
                                <h:outputLabel value="#{index}" />
                                </t:column--%>
                                
                                <t:column id="clm_3" width="10%">
                                    <f:facet name="header">
                                            <h:outputText id="header_brace1" value="#{resourcesBundle.brace}"/>
                                    </f:facet>
                                    
                                    <t:selectOneMenu value="#{list.rightArcs}" rendered="#{pageBeanName.maintainMode!=2}" valueChangeListener="#{detailBeanName.overViewPanelLisentner}">
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
                                    <t:outputText id="rightArcsOutTxt" rendered="#{pageBeanName.maintainMode==2}" value="#{list.rightArcs}"/>
                                </t:column>
                                
                                <t:column id="clm_4" width="55%" >
                                    <f:facet name="header">
                                            <h:outputText id="header_line" value="#{resourcesBundle.line}"/>
                                    </f:facet>
                                    <t:panelGroup rendered="#{pageBeanName.maintainMode!=2}">
                                        <t:inputText styleClass="textboxlarge" value="#{list.linesDTO.name}" id="lineDTO" readonly="true" forceId="true" valueChangeListener="#{detailBeanName.overViewPanelLisentner}" />
                                        <h:outputText value="*" styleClass="mandatoryAsterisk"/>
                                        <h:commandButton binding="#{detailBeanName.readLineBtn}" value="..." styleClass="cssButtonSmall" action="#{detailBeanName.readLine}">
                                             
                                             <f:attribute name="selectedRowNo" value="#{index}" />
                                        </h:commandButton>
                                    </t:panelGroup>
                                    <t:outputText id="lineDTOOutTxt" rendered="#{pageBeanName.maintainMode==2}" value="#{list.linesDTO.name}"/>
                                </t:column>
                                
                                <t:column id="clm_5" width="10%">
                                    <f:facet name="header">
                                            <h:outputText id="header_brace" value="#{resourcesBundle.brace}"/>
                                    </f:facet>
                                    <t:selectOneMenu value="#{list.leftArcs}" rendered="#{pageBeanName.maintainMode!=2}" valueChangeListener="#{detailBeanName.overViewPanelLisentner}" >
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
                                    <t:outputText id="leftArcsOutTxt" rendered="#{pageBeanName.maintainMode==2}" value="#{list.leftArcs}"/>
                                </t:column>
                                
                                
                                 <t:column id="clm_2" width="15%">
                                    <f:facet name="header">
                                            <h:outputText id="header_name" value="#{resourcesBundle.link}"/>
                                    </f:facet>
                                <t:selectOneMenu id="joinCondition" forceId="true" rendered="#{pageBeanName.maintainMode!=2}" valueChangeListener="#{detailBeanName.overViewPanelLisentner}" styleClass="Dropdownboxsmall"  value="#{list.jonconditionSign}" >  
                                    <f:selectItem itemLabel="" itemValue="-100" />
                                    <t:selectItems value="#{detailBeanName.joinConditions}" var="element" itemLabel="#{element.name}" itemValue="#{element.code.key}"  />
                                    <a4j:support event="onchange" reRender="overviews"    />
                                </t:selectOneMenu>
                                <t:outputText id="jonconditionSignOutTxt" rendered="#{pageBeanName.maintainMode==2}" value="#{list.jonconditionSign}"/>
                                
                                </t:column>
                                
                                
                                 
                                
                    
                </t:dataTable>
                </t:panelGroup>
                
                
              
                </t:panelGroup>
                  
                  <t:panelGrid id="overviews" forceId="true" dir="#{shared_util.pageDirection}" columns="2" width="100%" rowClasses="row02,row01" cellpadding="5" cellspacing="0" columnClasses="col3 nowrap_txt" rendered="#{conditionLineDetailBean.currentListSize > 0}" >
                
                
                <t:outputLabel value="#{resourcesBundle.overview}" styleClass="lable01" />
                <%--<a4j:support action="#{detailBeanName.overview}" event="onclick" reRender="overview" /> --%>
                
                <h:panelGroup>
                
                <t:inputTextarea readonly="true"  value="#{detailBeanName.queryOverview}" style="width:650px" rows="4" />
                </h:panelGroup>
                
                </t:panelGrid>
                
                
              
