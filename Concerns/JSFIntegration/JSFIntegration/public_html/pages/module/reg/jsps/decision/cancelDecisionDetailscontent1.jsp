<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

                
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                

                <t:panelGroup>
<%--                
                <f:verbatim>                
                <table width="100%">
                        <tr>
                            <td width="100%" align="center">
                                
                                <table border="0" width="100%"  cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td valign="bottom" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                                        <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                                        <td valign="bottom" width="12"><img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                                        <td valign="top" bgcolor="#FFFFFF" class="paddingbox">               
                    </f:verbatim>                
                        <h:panelGrid id="pg1" columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">
                            <h:panelGroup>
                                <h:outputLabel for="type" value="#{regResources.type} : " styleClass="divtextbold"/>
                                <h:outputText id="type" value="#{pageBeanName.selectedDTOS[0].typesDTO.name}" styleClass="divtext"/>
                            </h:panelGroup>
                            <h:panelGroup>
                                <h:outputLabel for="decisionMaker" id="decision_Maker" value="#{regResources.dec_decision_maker} : " styleClass="divtextbold"/>
                                <h:outputText id="decisionMaker" value="#{pageBeanName.selectedDTOS[0].decisionMakerTypesDTO.name}" styleClass="divtext"/>
                            </h:panelGroup>
                            <h:panelGroup>
                                <h:outputLabel for="subject" id="subjec_t" value="#{regResources.dec_subjects} : " styleClass="divtextbold"/>
                                <h:outputText id="subject" value="#{pageBeanName.selectedDTOS[0].subjectsDTO.name}" styleClass="divtext"/>
                            </h:panelGroup>
                        </h:panelGrid>                   
                    <f:verbatim>
                                         </td>
                                         <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                                        <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                                        <td valign="top"><img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </f:verbatim>
--%>                
                






    <f:verbatim>                
                <table width="100%">
                        <tr>
                            <td width="100%" align="center">
                                
                                <table border="0" width="100%" id="table5" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td valign="bottom" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                                        <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                                        <td valign="bottom" width="12"><img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                                        <td valign="top" bgcolor="#FFFFFF" class="paddingbox">               
                    </f:verbatim>
        
        
        
        
        
        <h:outputText value="#{regResources.canceled_by}" styleClass="divtext"/>
        <t:panelGrid id="pg2" columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">    
                <%--Row 01--%>
                <h:outputLabel for="type" value="#{regResources.type} : " styleClass="divtextbold"/>
                <h:outputText id="type" value="#{pageBeanName.selectedDTOS[0].typesDTO.name}" styleClass="divtext"/>    
                
                <h:outputLabel for="issuanceYear" id="issuance_Year" value="#{regResources.issuance_year} : " styleClass="divtextbold"/>
                <h:outputText id="issuanceYear" value="#{pageBeanName.selectedDTOS[0].yearsDTO.code.key}" styleClass="divtext"/>
                
                <h:outputLabel for="decNumber" id="dec_Number" value="#{regResources.dec_number} : " styleClass="divtextbold"/>   
                <h:outputText id="decNumber" value="#{pageBeanName.selectedDTOS[0].code.decisionNumber}" styleClass="divtext"/>
                
                <%--Row 02--%>
                <h:outputLabel for="title" id="titl_e" value="#{regResources.dec_title} : " styleClass="divtextbold"/>
                <h:outputText id="title" value="#{pageBeanName.selectedDTOS[0].decisionTitle}" styleClass="divtext"/>                    
                
                <h:outputLabel for="subject" id="subjec_t" value="#{regResources.dec_subjects} : " styleClass="divtextbold"/>
                <h:outputText id="subject" value="#{pageBeanName.selectedDTOS[0].subjectsDTO.name}" styleClass="divtext"/>
                
                <h:outputLabel for="decisionMaker" id="decision_Maker" value="#{regResources.dec_decision_maker} : " styleClass="divtextbold"/>
                <h:outputText id="decisionMaker" value="#{pageBeanName.selectedDTOS[0].decisionMakerTypesDTO.name}" styleClass="divtext"/>
                
                <%--Row 03--%>
                <h:outputLabel for="decDate" id="dec_Date" value="#{regResources.dec_date} : " styleClass="divtextbold"/>
                <h:outputText id="decDate" value="#{pageBeanName.selectedDTOS[0].decisionDate}" converter="TimeStampConverter" styleClass="divtext"/>    
                
                <h:outputLabel for="applyDate" id="apply_Date" value="#{regResources.apply_date} : " styleClass="divtextbold"/>
                <h:outputText id="applyDate" value="#{pageBeanName.selectedDTOS[0].decisionAppliedDate}" converter="TimeStampConverter" styleClass="divtext"/>
                
                <f:verbatim></f:verbatim>
                <f:verbatim></f:verbatim>
                
                <%--Row 04--%>
                <h:outputLabel for="decisionText" value="#{regResources.decision_text}:" styleClass="divtextbold" id="decision_text"/>
                <t:panelGroup colspan="5">
                    <t:inputTextarea disabled="true" cols="110" rows="3" id="decisionText" value="#{pageBeanName.selectedDTOS[0].decisionText}" styleClass="divtext"/>
                </t:panelGroup>
                
                <%--Row 05--%>
                <h:outputLabel for="regulationImage" id="regulation_Image" value="#{regResources.regulation_image} : " styleClass="divtextbold"/>
                <h:commandButton id="browse" value="#{regResources.view}" 
                    onclick="javascript:canceledPhoto.style.display='inline';changeVisibilityDiv(window.blocker,window.customDiv1);return(false);" 
                    disabled="#{pageBeanName.selectedDTOS[0].decisionImage == null}"
                    styleClass="cssButtonSmall"/>
                    
                <h:outputLabel for="regulationTemplate" id="regulation_Template" value="#{regResources.regulation_template} : " styleClass="divtextbold"/>
                <h:outputText id="regulationTemplate" value="#{pageBeanName.selectedDTOS[0].templatesDTO.name}" styleClass="divtext"/>
                
                <f:verbatim></f:verbatim>
                <f:verbatim></f:verbatim>
<%--    
                    <h:outputLabel for="title" id="titl_e" value="#{regResources.dec_title} : " styleClass="divtextbold"/>
                    <h:outputText id="title" value="#{pageBeanName.selectedDTOS[0].decisionTitle}" styleClass="divtext"/>                    
                    <h:outputLabel for="decNumber" id="dec_Number" value="#{regResources.dec_number} : " styleClass="divtextbold"/>   
                    <h:outputText id="decNumber" value="#{pageBeanName.selectedDTOS[0].code.decisionNumber}" styleClass="divtext"/>
                    <h:outputLabel for="decDate" id="dec_Date" value="#{regResources.dec_date} : " styleClass="divtextbold"/>
                    <h:outputText id="decDate" value="#{pageBeanName.selectedDTOS[0].decisionDate}" converter="TimeStampConverter" styleClass="divtext"/>    
                    <h:outputLabel for="issuanceYear" id="issuance_Year" value="#{regResources.issuance_year} : " styleClass="divtextbold"/>
                    <h:outputText id="issuanceYear" value="#{pageBeanName.selectedDTOS[0].yearsDTO.code.key}" styleClass="divtext"/>
                    <h:outputLabel for="applyDate" id="apply_Date" value="#{regResources.apply_date} : " styleClass="divtextbold"/>
                    <h:outputText id="applyDate" value="#{pageBeanName.selectedDTOS[0].decisionAppliedDate}" converter="TimeStampConverter" styleClass="divtext"/>
                    <h:outputLabel for="regulationTemplate" id="regulation_Template" value="#{regResources.regulation_template} : " styleClass="divtextbold"/>
                    <h:outputText id="regulationTemplate" value="#{pageBeanName.selectedDTOS[0].templatesDTO.name}" styleClass="divtext"/>
                    <h:outputLabel for="regulationImage" id="regulation_Image" value="#{regResources.regulation_image} : " styleClass="divtextbold"/>
                    <h:commandButton id="browse" value="#{regResources.view}" 
                        onclick="javascript:canceledPhoto.style.display='inline';changeVisibilityDiv(window.blocker,window.customDiv1);return(false);" 
                        disabled="#{pageBeanName.selectedDTOS[0].decisionImage == null}"
                        styleClass="cssButtonSmall"/>

                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
                    <f:verbatim></f:verbatim>
   
                    <h:outputLabel value="#{regResources.decision_text}" styleClass="divtext" id="decision_text"/>
                    <t:panelGroup colspan="5">
                        <t:inputTextarea readonly="true" cols="110" rows="3" id="decisionText" value="#{pageBeanName.selectedDTOS[0].decisionText}" styleClass="divtext"/>
                    </t:panelGroup>
--%>
    </t:panelGrid>    
           
                    <f:verbatim>
                                         </td>
                                         <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                                        <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                                        <td valign="top"><img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </f:verbatim>










<t:dataList id="datalist"
  var="decision"
  value="#{pageBeanName.canceledDecisionslist}"
  rowCountVar="rowCount"
  rowIndexVar="rowIndex">



<f:verbatim>                
                <table width="100%">
                        <tr>
                            <td width="100%" align="center">
                                
                                <table border="0" width="100%"  cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td valign="bottom" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"></td>
                                        <td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" class="gtopbox">&nbsp;</td>
                                        <td valign="bottom" width="12"><img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"></td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" class="grightbox">&nbsp;</td>
                                        <td valign="top" bgcolor="#FFFFFF" class="paddingbox">               
                    </f:verbatim>
                    
                    
                    
                    
                    
                    
                    
                    <h:outputText id="canceled_by" value="#{regResources.canceled_decision}" styleClass="divtext"/>
        <t:panelGrid id="pg3" columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0">        
                 <%--Row 01--%>
                <h:outputLabel for="type2" value="#{regResources.type} : " styleClass="divtextbold"/>
                <h:outputText id="type2" value="#{pageBeanName.selectedDTOS[0].typesDTO.name}" styleClass="divtext"/>
                
                <h:outputLabel for="pIssuanceYear" id="pIssuance_Year" value="#{regResources.issuance_year} : " styleClass="divtextbold"/>
                <h:outputText id="pIssuanceYear" value="#{decision.yearsDTO.code.key}" styleClass="divtext"/>
                    
                <h:outputLabel for="pDecNumber" id="p_DecNumber" value="#{regResources.dec_number} : " styleClass="divtextbold"/>
                <h:outputText id="pDecNumber" value="#{decision.code.decisionNumber}" styleClass="divtext"/>   
                
                <%--Row 02--%>
                <h:outputLabel for="pTitle" id="p_Title" value="#{regResources.dec_title} : " styleClass="divtextbold"/>
                <h:outputText id="pTitle" value="#{decision.decisionTitle}" styleClass="divtext"/>
                
                <h:outputLabel for="subject2" id="subjec_t2" value="#{regResources.dec_subjects} : " styleClass="divtextbold"/>
                <h:outputText id="subject2" value="#{pageBeanName.selectedDTOS[0].subjectsDTO.name}" styleClass="divtext"/>
                
                <h:outputLabel for="decisionMaker2" id="decision_Maker2" value="#{regResources.dec_decision_maker} : " styleClass="divtextbold"/>
                <h:outputText id="decisionMaker2" value="#{pageBeanName.selectedDTOS[0].decisionMakerTypesDTO.name}" styleClass="divtext"/>
                
                <%--Row 03--%>
                <h:outputLabel for="pDecDate" id="p_DecDate" value="#{regResources.dec_date} : " styleClass="divtextbold"/>
                <h:outputText id="pDecDate" value="#{decision.decisionDate}" converter="TimeStampConverter" styleClass="divtext"/>
                
                <h:outputLabel for="pApplyDate" id="pApply_Date" value="#{regResources.apply_date} : " styleClass="divtextbold"/>
                <h:outputText id="pApplyDate" value="#{decision.decisionAppliedDate}" converter="TimeStampConverter" styleClass="divtext"/>
                
                <f:verbatim></f:verbatim>
                <f:verbatim></f:verbatim>
                
                <%--Row 04--%>
                <h:outputLabel id="decision_text5" value="#{regResources.decision_text}" styleClass="divtextbold"/>
                <t:panelGroup colspan="5">
                    <t:inputTextarea disabled="true" cols="110" rows="3" id="cancelDecisionText" value="#{decision.decisionText}" styleClass="divtext"/>
                </t:panelGroup>
                
                <%--Row 05--%>
                <h:outputLabel for="pRegulationImage" id="pRegulation_Image" value="#{regResources.regulation_image} : " styleClass="divtextbold"/>                        
                <h:commandButton id="pBrowse"  value="#{regResources.view}" 
                    onclick="javascript:cancellingPhoto.style.display='inline';changeVisibilityDiv(window.blocker,window.customDiv1);return(false);" 
                    disabled="#{decision.decisionImage == null}"
                    styleClass="cssButtonSmall"/>
                
                <h:outputLabel for="pRegulationTemplate" id="pRegulation_Template" value="#{regResources.regulation_template} : " styleClass="divtextbold"/>                        
                <h:outputText id="pRegulationTemplate"  value="#{decision.templatesDTO.name}" styleClass="divtext"/>
                
                <f:verbatim></f:verbatim>
                <f:verbatim></f:verbatim>
                
                    

        </t:panelGrid>
                    <f:verbatim>
                                         </td>
                                         <td valign="top" style="background-repeat: repeat-y" class="gleftbox">&nbsp;</td>
                                    </tr>
                    
                                    <tr>
                                        <td valign="top" width="19"><img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"></td>
                                        <td valign="top" style="background-repeat: repeat-x" class="gbottombox">&nbsp;</td>
                                        <td valign="top"><img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    </f:verbatim>
</t:dataList>
                
                

                
        
                </t:panelGroup>
               
