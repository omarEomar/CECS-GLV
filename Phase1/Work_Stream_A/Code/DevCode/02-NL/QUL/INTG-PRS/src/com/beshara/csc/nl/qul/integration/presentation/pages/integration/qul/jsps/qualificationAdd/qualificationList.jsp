<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
    <!--f:loadBundle basename="com.beshara.csc.hr.crs.presentation.resources.crs" var="resourcesBundle"/-->
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <h:form id="myForm" binding="#{qualificationAddHelperBean.frm}">
        <t:messages showSummary="true" showDetail="true"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{qualificationAddHelperBean}">
            <t:aliasBean alias="#{detailBeanName}" value="#{qualificationAddHelperBean}">
                <t:aliasBean alias="#{qulEduLevelsIntegrationBean}"
                             value="#{qualificationAddHelperBean.qulEduLevelsIntegrationBean}">
                    <t:aliasBean alias="#{qulCentersIntegrationBean}"
                                 value="#{qualificationAddHelperBean.qulCentersIntegrationBean}">
                        <tiles:insert definition="qulIntigration.page" flush="false">
                            <t:saveState value="#{pageBeanName.degreeValuesList}"/>
                            <t:saveState value="#{pageBeanName.shangLangth}"/>
                            <t:saveState value="#{pageBeanName.qulIntegrationListBean}"/>
                            <t:saveState value="#{pageBeanName.selectedQulDegreeValue}"/>
                            <t:saveState value="#{pageBeanName.qulDegreeType}"/>
                            <t:saveState value="#{pageBeanName.inValidValue}"/>
                            <t:saveState value="#{pageBeanName.inValidMissionTypeCode}"/>
                            <%--<t:saveState value="#{pageBeanName.qul_country_list}"/>--%>
                            <t:saveState value="#{pageBeanName.personQualificationsDTO}"/>
                            <t:saveState value="#{pageBeanName.selectedQulType}"/>
                            <t:saveState value="#{pageBeanName.searchMode}"/>
                            <t:saveState value="#{pageBeanName.qualificationName}"/>
                            <%-- <t:saveState value="#{pageBeanName.qualificationList}"/>--%>
                            <t:saveState value="#{pageBeanName.searchQulifictaionString}"/>
                            <%--<t:saveState value="#{pageBeanName.countriesList}"/>--%>
                            <%--<t:saveState value="#{pageBeanName.centersList}"/>--%>
                            <t:saveState value="#{pageBeanName.countryCode}"/>
                            <t:saveState value="#{pageBeanName.centerCode}"/>
                            <t:saveState value="#{pageBeanName.societyCode}"/>
                            <t:saveState value="#{pageBeanName.kwtCitizensResidentsDTO}"/>
                            <t:saveState value="#{pageBeanName.civilId}"/>
                            <t:saveState value="#{pageBeanName.personsInformationDTO}"/>
                            <t:saveState value="#{pageBeanName.success}"/>
                            <t:saveState value="#{pageBeanName.showErrorMsg}"/>
                            <t:saveState value="#{pageBeanName.qulCenterCode}"/>
                            <t:saveState value="#{pageBeanName.qulCentersList}"/>
                            <t:saveState value="#{pageBeanName.qulCenterTreeDiv}"/>
                            <t:saveState value="#{pageBeanName.qulCountryCode}"/>
                            <t:saveState value="#{pageBeanName.qulCountriesList}"/>
                            <t:saveState value="#{pageBeanName.generalSpecslist}"/>
                            <t:saveState value="#{pageBeanName.majorSpecsList}"/>
                            <t:saveState value="#{pageBeanName.educationKey}"/>
                            <t:saveState value="#{pageBeanName.generalSpecsKey}"/>
                            <t:saveState value="#{pageBeanName.majorSpecsKey}"/>
                            <t:saveState value="#{pageBeanName.pageMode}"/>
                            <t:saveState value="#{pageBeanName.qulAddDivTitleName}"/>
                            <t:saveState value="#{pageBeanName.selectedCenterName}"/>
                            <t:saveState value="#{pageBeanName.enableResetData}"/>
                            <t:saveState value="#{pageBeanName.certificateLevelId}"/>
                            <t:saveState value="#{pageBeanName.selectedCertificateId}"/>
                            <t:saveState value="#{pageBeanName.centerDTO}"/>
                            <t:saveState value="#{pageBeanName.levelDTO}"/>
                            <t:saveState value="#{pageBeanName.certificatesList}"/>
                            <t:saveState value="#{pageBeanName.selectedCertificateId}"/>
                            <t:saveState value="#{pageBeanName.certificateLevel}"/>
                            <t:saveState value="#{pageBeanName.pageDTO}"/>
                            <t:saveState value="#{pageBeanName.prepareMethodName}"/>
                            <t:saveState value="#{pageBeanName.returnMethodName}"/>
                            <t:saveState value="#{pageBeanName.civilId}"/>
                            <t:saveState value="#{pageBeanName.disableQulDateRegistration}"/>
                            
                    <%-- start qulList--%>
                    <t:saveState value="#{qulListIntegrationBean.kwtCitizenDTO}"/>
                    <t:saveState value="#{qulListIntegrationBean.civilId}"/>
                    <t:saveState value="#{qulListIntegrationBean.citizinFullName}"/>
                    <t:saveState value="#{qulListIntegrationBean.personsInformationDTOList}"/>
                    <t:saveState value="#{qulListIntegrationBean.selectedDTOS}"/>
                    <t:saveState value="#{qulListIntegrationBean.listSize}"/>
                    <t:saveState value="#{qulListIntegrationBean.pageType}"/>
                    <t:saveState value="#{qulListIntegrationBean.qulListPageinWizardBar}"/>
                    <t:saveState value="#{qulListIntegrationBean.saveStateList}"/>
                    <t:saveState value="#{qulListIntegrationBean.saveInDB}"/>
                    <t:saveState value="#{qulListIntegrationBean.notSavedPersonsInformationDTOList}"/>
                    <t:saveState value="#{qulListIntegrationBean.dataTablestyleClass}"/>
                    
                    <tiles:put name="integrationDiv2" value="/integration/qul/jsps/certificates/searchCertificateDiv.jsp"/>
                    <tiles:put name="titleIntegrationDiv2" value="searchCertificateTitleDiv"/>

                        </tiles:insert>
                    </t:aliasBean>
                </t:aliasBean>
            </t:aliasBean>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanel">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
        <script type="text/javascript">
          
          function setFocusAfterSelectDegreeType() {
              if (document.getElementById('qulDegreeType') != null && document.getElementById('qulDegreeType').value != null) {
                  if (document.getElementById('qulDegreeType').value == 3 || document.getElementById('qulDegreeType').value == 2) {
                      setFocusOnlyOnElement('qulDegreeValueCombo');
                  }
                  else {
                      setFocusOnlyOnElement('qulDegreeValueID');
                  }
              }
          }

          foucsCountryBoxOnList();

          function foucsCountryBoxOnList() {
              if (document.getElementById('countriesListt') != null) {
                  document.getElementById('countriesListt').focus();
              }
          }

          function reinitializeSaveMsg(componentID) {

              if (document.getElementById(componentID) != null) {
                  document.getElementById(componentID).innerHTML = '';
              }
          }
        </script>
    </h:form>
</f:view>