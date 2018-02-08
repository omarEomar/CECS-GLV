<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
   <f:loadBundle basename="com.beshara.csc.nl.job.integration.presentation.resources.integration" var="jobIntgResources"/> 
    
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    <f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration"
                  var="integrateResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.grs.integration.presentation.resources.integration"
                  var="grsIntgResources"/>
    <h:form id="myForm" binding="#{prmListBean.frm}">
        <t:aliasBean alias="#{pageBeanName}" value="#{prmListBean}">
                <tiles:insert definition="list.page" flush="false">
                
                   <t:saveState value="#{pageBeanName.jobFieldsList}" />
                   <t:saveState value="#{pageBeanName.catsGroupList}" />
                   <t:saveState value="#{pageBeanName.catsSubList}" />
                   
                   <t:saveState value="#{pageBeanName.childTypeFieldsList}" />
                   
                   <t:saveState value="#{pageBeanName.workTypeNameFieldsList}" />
                   
                   <t:saveState value="#{pageBeanName.jobTypesList}" />
                   
                   <t:saveState value="#{pageBeanName.jobCatLevelsList}" />
                   
                   <t:saveState value="#{pageBeanName.selectedJobCode}" />
                   
                   <t:saveState value="#{pageBeanName.selectedLevelGroupCode}" />
                   
                   <t:saveState value="#{pageBeanName.selectedLevelsCode}" />
                   
                   <t:saveState value="#{pageBeanName.selectedChildTypeCode}" />
                   
                   <t:saveState value="#{pageBeanName.selectedWorkTypeNameCode}" />
                   
                   <t:saveState value="#{pageBeanName.selectedJobTypeCode}" />
                   
                   <t:saveState value="#{pageBeanName.jobCatLevelsScaleCode}" />
                    
                    <t:saveState value="#{pageBeanName.myListSize}" />
                    
                   <t:saveState value="#{pageBeanName.displayedList}" /> 
                   
                   <t:saveState value="#{pageBeanName.selectedJobToPromote}" /> 
                   
                   <t:saveState value="#{pageBeanName.jobsList}" /> 
                   
                   <t:saveState value="#{pageBeanName.filterMode}" /> 
                  
                  <t:saveState value="#{pageBeanName.buttonValue}" />  
                   
                   
                   <t:saveState value="#{pageBeanName.jobsMap}" />
                   
                </tiles:insert>
            </t:aliasBean>
    </h:form>
    <t:panelGroup forceId="true" id="scriptGeneratorPanelID">
        <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>

</f:view>
