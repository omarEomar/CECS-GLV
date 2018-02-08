<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

            
               <t:panelGroup>
                        <f:verbatim> <center>       </f:verbatim>
                        <h:outputLabel value="#{resourcesBundle.ApprovalMaker}" styleClass="lable01" />
                        <f:verbatim> &nbsp;       </f:verbatim>
                        <t:selectOneMenu forceId="true" id="approvalMakerList" value="#{pageBeanName.selectedapprovalMakerId}" valueChangeListener="#{pageBeanName.filterDataTableByMaker}" styleClass="DropdownboxMedium">
                          <f:selectItem itemLabel="#{resourcesBundle.ChooseApprovalMakerName}" itemValue="-100"/>
                          <t:selectItems value="#{pageBeanName.approvalMakerList}" var="approvalmaker" itemValue="#{approvalmaker.code.key}" itemLabel="#{approvalmaker.name}" />
                          <a4j:support event="onchange" reRender="dataT_data_panel,OperationBar,paging_panel"/> 
                            </t:selectOneMenu>
                            
                            <f:verbatim> </center> </f:verbatim>
                </t:panelGroup>

        
             