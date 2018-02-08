<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>

<f:view locale="#{shared_util.locale}">
<h:form id="myForm" binding="#{newBranchListBean.frm}">
    <f:loadBundle basename="com.beshara.csc.nl.bnk.integration.presentation.resources.integration"
                  var="integrationBundle"/>
    <t:aliasBean alias="#{pageBeanName}" value="#{newBranchListBean}">
    
            
            <t:saveState value="#{pageBeanName.currentPageIndex}"/>
            <t:saveState value="#{pageBeanName.sorting}"/>
            <t:saveState value="#{pageBeanName.usingPaging}"/>
            <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>

            <t:saveState value="#{pageBeanName.myTableData}"/>
            <t:saveState value="#{pageBeanName.searchMode}"/>
            <t:saveState value="#{pageBeanName.divMode}"/>
            <t:saveState value="#{pageBeanName.selectedDTOS}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
            <t:saveState value="#{pageBeanName.sortAscending}"/>
            <t:saveState value="#{pageBeanName.pageDTO}"/>    
            <t:saveState value="#{pageBeanName.selectedPageDTO}"/>        
            <t:saveState value="#{pageBeanName.selectedListSize}"/>            
            
            
            <t:saveState value="#{pageBeanName.governorateList}" id ="governorateList"/>
            <t:saveState value="#{pageBeanName.districtList}" id ="districtList"/>
            <t:saveState value="#{pageBeanName.sectorList}" id ="sectorList"/>
            <t:saveState value="#{pageBeanName.streetList}" id ="streetList"/>
            <t:saveState value="#{pageBeanName.buildingNumber}" id ="buildingNumber"/>
            <t:saveState value="#{pageBeanName.branchMapCode}" id ="branchMapCode"/>
            <t:saveState value="#{pageBeanName.branchName}" id ="branchName"/>
            <t:saveState value="#{pageBeanName.branchEmail}" id ="branchEmail"/>
            <t:saveState value="#{pageBeanName.branchTel}" id ="branchTel"/>
            <t:saveState value="#{pageBeanName.branchFax}" id ="branchFax"/>
            <t:saveState value="#{pageBeanName.branchGovernorateCode}" id ="branchGovernorateCode"/>
            <t:saveState value="#{pageBeanName.branchDistrictCode}" id ="branchDistrictCode"/>
            <t:saveState value="#{pageBeanName.branchSectorCode}" id ="branchSectorCode"/>
            <t:saveState value="#{pageBeanName.branchStreetCode}" id ="branchStreetCode"/>
            
            <t:saveState value="#{pageBeanName.validCivilId}" id ="validCivilId"/>
            <t:saveState value="#{pageBeanName.civilId}" id ="civilId"/>
            <t:saveState value="#{pageBeanName.civilEmail}" id ="civilEmail"/>
            <t:saveState value="#{pageBeanName.civilTel}" id ="civilTel"/>
            <t:saveState value="#{pageBeanName.civilMobile}" id ="civilMobile"/>
            <t:saveState value="#{pageBeanName.civilFax}" id ="civilFax"/>
            <t:saveState value="#{pageBeanName.enableSave}" id ="enableSave"/>
            <t:saveState value="#{pageBeanName.bankBranchDTO}" id ="bankBranchDTO"/>
            <t:saveState value="#{pageBeanName.kwtCitizenDTO}" id ="kwtCitizenDTO"/>
            <t:saveState value="#{pageBeanName.bankContactPersonsList}" id ="bankContactPersonsList"/>
            <t:saveState value="#{pageBeanName.removedBankContactPersonsList}" id ="removedBankContactPersonsList"/>
            <t:saveState value="#{pageBeanName.civilExist}" id ="civilExist"/>
            <t:saveState value="#{pageBeanName.civilList}" id ="civilList"/>
            <t:saveState value="#{pageBeanName.civilName}" id ="civilName"/>
            <t:saveState value="#{pageBeanName.civilGender}" id ="civilGender"/>
           
            <t:saveState value="#{pageBeanName.pageMode}" id ="pageMode"/>
            
            
            <t:saveState value="#{pageBeanName.bankDTO}" id ="bankDTO"/>    
            
            <t:saveState value="#{branchesListBean.bankDTO}" id ="bankDTO2"/>
            <t:saveState value="#{branchesListBean.branchDTO}" id ="branchDTO2"/>
            
            <tiles:insert flush="false" definition="newBranch.page">
              <t:saveState value="#{pageBeanName.pageTitle}" id ="pageTitle"/>
            
              <tiles:put name="titlepage" type="string" value="${pageBeanName.pageTitle}"/>
            </tiles:insert>                        
    </t:aliasBean>
    <t:panelGroup forceId="true" id="scriptPanelID">
      <c2:scriptGenerator form="myForm"/>
    </t:panelGroup>    
</h:form>
</f:view>
