<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%-- edit by m.elsaied (fix issue key : NL-279 - 2. Delete 001.JPG)--%>


<f:verbatim>
<table border="0" width="100%" id="table5" cellspacing="0" cellpadding="0" align="center"></f:verbatim>
<f:verbatim><tr></f:verbatim>
                    <f:verbatim><td colspan="3" align="center"><span lang="ar-eg" class="TitlePage">${resourcesBundle.rejectionGruop_div_title}</span></td></f:verbatim>
                            <f:verbatim></tr></f:verbatim>
                            <f:verbatim><tr></f:verbatim>
                                <f:verbatim><td></f:verbatim><f:verbatim><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_01.jpg"/></td></f:verbatim>
                                <f:verbatim><td background="${shared_util.contextPath}/app/media/images/div/Border_blue_08.jpg"/></f:verbatim>
                                <f:verbatim><td><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_07.jpg"/></td></f:verbatim>
                                    <f:verbatim></tr></f:verbatim>
                                    <f:verbatim><tr></f:verbatim>
                                <f:verbatim><td background="${shared_util.contextPath}/app/media/images/div/Border_blue_02.jpg"/></f:verbatim>
                                <f:verbatim><td  width="100%" valign="middle"></f:verbatim><f:verbatim><div align="center"></f:verbatim>
                                
                                    <t:panelGrid columns="1" width="100%" border="0">
                                    <t:panelGroup forceId="true" id="divRejectionReasons"  >
                                    <t:panelGroup>
                                    <f:verbatim> <center>       </f:verbatim>
                                    <h:outputLabel value="#{resourcesBundle.request_rej_reason}" styleClass="lable01" />
                                    <f:verbatim> &nbsp;       </f:verbatim>
                                    <t:selectOneMenu forceId="true" onblur="document.getElementById('okButtonCustomDiv1').focus();" id="rejectionReason" value="#{pageBeanName.selectedReasonId}" valueChangeListener="#{pageBeanName.rejectionReasonListener}"  styleClass="DropdownboxMedium">                                    
                                    <f:selectItem itemLabel="#{resourcesBundle.request_rej_reason}" itemValue="-100"/>
                                    <t:selectItems value="#{pageBeanName.rejectionResonList}" var="reasonId" itemValue="#{reasonId.code.key}" itemLabel="#{reasonId.name}" />
                                    <%-- a4j:support event="onchange" reRender="dataT_data_panel"/--%> 
                                    </t:selectOneMenu>
                                     <t:inputHidden 
                                        value="#{pageBeanName.itemSelectionRequiredValueString}" 
                                        forceId="true" 
                                        id="itemSelectionRequiredValueStringID" />
                                    <f:verbatim> <br> </f:verbatim>
                                    <c2:compareValidator 
                                    componentToValidate="rejectionReason" 
                                    componentToCompare="itemSelectionRequiredValueStringID" 
                                    operator="not" 
                                    uniqueId="rejectionReasonVirtualValuedd"                                     
                                    errorMessage="#{resourcesBundle.rejectreason}" 
                                    display="dynamic" 
                                    highlight="false"                                    
                                    />
                                    
                                    <f:verbatim> </center> </f:verbatim>
                                    </t:panelGroup>
                                    </t:panelGroup>                                    
                                    <t:panelGrid columns="2" align="center">
                                    <t:commandButton forceId="true" id="okButtonCustomDiv1" value="#{globalResources.ok}" action="#{pageBeanName.saveApprovel}" styleClass="cssButtonSmall" onclick="return validatemyForm();"/>
                                    <t:commandButton forceId="true" id="backButtonCustomDiv1" onblur="setFocusAtMyCustomDiv();" type="button" value="#{globalResources.back}" onclick="hideLookUpDiv(window.blocker,window.customDiv1,null,null);" styleClass="cssButtonSmall"/>
                                    </t:panelGrid>
                                    </t:panelGrid>
                                
                                
                                <f:verbatim></div></f:verbatim><f:verbatim></td></f:verbatim>
                                <f:verbatim><td background="${shared_util.contextPath}/app/media/images/div/Border_blue_06.jpg"/></f:verbatim>
                                <f:verbatim></tr></f:verbatim>
                                <f:verbatim><tr></f:verbatim>
                                <f:verbatim><td></f:verbatim><f:verbatim><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_03.jpg"/></f:verbatim><f:verbatim></td></f:verbatim>
                                <f:verbatim><td background="${shared_util.contextPath}/app/media/images/div/Border_blue_04.jpg"/></f:verbatim>
                                <f:verbatim><td></f:verbatim><f:verbatim><img src="${shared_util.contextPath}/app/media/images/div/Border_blue_05.jpg"/></f:verbatim><f:verbatim></td></f:verbatim>
                            <f:verbatim></tr></f:verbatim>
                           
<f:verbatim></table></f:verbatim>


