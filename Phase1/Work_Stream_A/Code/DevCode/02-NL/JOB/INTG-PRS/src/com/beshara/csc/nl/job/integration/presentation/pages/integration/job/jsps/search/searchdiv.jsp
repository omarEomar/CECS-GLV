<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.nl.job.integration.presentation.resources.integration" var="jobIntgResources"/>
<htm:style>.extraWideDiv {right: 6% !important;width: 860px !important; top: 15px !important;}</htm:style>
<h:panelGrid columns="1" align="center">
    <t:outputText id="jobIntgJobFieldValError" forceId="true" styleClass="errMsg"
                  value="#{jobIntgResources.inavlid_job_val}" binding="#{jSearchBeanName.errorMsgBind}"
                  style="#{jSearchBeanName.showMsg}"/>
</h:panelGrid>
<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('hideDivImg', 'jobIntgSearchPnl', 'jobIntgSearchResultTblPnl')"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="jobIntgSearchPnl">
    <t:panelGroup forceId="true" id="jobIntgSearchCriteriaPnl">
        <t:panelGrid columns="6" border="0" width="100%" rowClasses="row01,row02" cellpadding="1" cellspacing="0">
            <h:outputLabel styleClass="lable01" value="#{globalResources.Code}"/>
            <t:panelGroup colspan="2">
                <t:inputText forceId="true" id="jobIntgJobKey" value="#{jSearchBeanName.jobKey}" styleClass="textbox"
                             onchange="trimBorders(jobIntgJobKey);" disabled="#{jSearchBeanName.searchMode}"
                             onkeypress="FireJobIntgSearchBtn();trimBorders(jobIntgJobKey);"
                             onkeyup="handleJobIntgSearchBtn();" style="width:289px;"/>
                <a4j:jsFunction reRender="jobIntgSearchBtnsPnl,searchJobBtnId" name="rerendersearchJobBtnIdButton"
                                oncomplete="setFocusOnElement('jobIntgJobName');"/>
            </t:panelGroup>
            <t:outputLabel value="#{jobIntgResources.job_title}" for="jobIntgJobName" styleClass="lable01"/>
            <t:panelGroup colspan="3">
                <t:inputText maxlength="250" value="#{jSearchBeanName.jobName}" id="jobIntgJobName" forceId="true"
                             styleClass="textbox" onchange="trimBorders(jobIntgJobName);" style="width:289px;"
                             onkeypress="FireJobIntgSearchBtn();trimBorders(jobIntgJobName);"
                             onkeyup="handleJobIntgSearchBtn();" disabled="#{jSearchBeanName.searchMode}"/>
                <a4j:jsFunction reRender="jobIntgSearchBtnsPnl,searchJobBtnId" name="rerendersearchJobBtnIdButton"/>
            </t:panelGroup>
            <!---R1----------------------- jobGroup Section------------------
        -->
            <h:outputLabel styleClass="lable01" for="jobIntgJobGroubVal" value="#{jobIntgResources.job_groub}"/>
            <t:inputText forceId="true" id="jobIntgJobGroubVal" value="#{jSearchBeanName.jobGroupCode}"
                         styleClass="filteration_input_text" maxlength="#{jSearchBeanName.codeMaxLength}"
                         onkeypress="validate(event);gochangeJobGroupVal(event);" onchange="changeJobGroupValOnBlur();"
                         disabled="#{jSearchBeanName.disableJobGroup?true:jSearchBeanName.searchMode}">
                <a4j:jsFunction action="#{jSearchBeanName.jobGroupCodeChanged}" name="changeJobGroupVal"
                                reRender="jobIntgSearchBtnsPnl,searchJobBtnId,jobIntgLevelsGroupVal,jobIntgLevelsGroupCombo,jobIntgJobGroubCombo,jobIntgJobFieldValError,jobIntgLevelsCombo,jobIntgLevelsVal,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo,jobIntgGroupTypeVal,jobIntgGroupTypeMenu,jobIntgJobLevelVal,jobIntgJobLevelCombo"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','jobIntgLevelsGroupVal','jobIntgJobGroubCombo');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgJobGroubCombo" value="#{jSearchBeanName.selectedJobGroup}"
                             styleClass="DropdownboxMedium" disabled="#{jSearchBeanName.disableJobGroup?true:jSearchBeanName.searchMode}"
                             onkeypress="FireJobIntgSearchBtn();" onkeyup="handleJobIntgSearchBtn();">
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.jobGroups}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedJobCodeChanged}"
                             reRender="jobIntgSearchBtnsPnl,searchJobBtnId,jobIntgLevelsGroupVal,jobIntgLevelsGroupCombo,jobIntgJobGroubVal,jobIntgLevelsCombo,jobIntgLevelsVal,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo,jobIntgGroupTypeVal,jobIntgGroupTypeMenu,jobIntgJobLevelVal,jobIntgJobLevelCombo"/>
            </t:selectOneMenu>
            <!---R1----------------------- levelsGroup Section------------------
        -->
            <h:outputLabel styleClass="lable01" for="jobIntgLevelsGroupVal" value="#{jobIntgResources.job_cat}"/>
            <t:inputText forceId="true" id="jobIntgLevelsGroupVal" value="#{jSearchBeanName.levelsGroupCode}"
                         maxlength="#{jSearchBeanName.codeMaxLength}" styleClass="filteration_input_text"
                         onkeypress="gochangeLevelsGroupVal(event);" onchange="ChangeLevelsGroupValOnBlur();"
                         disabled='#{jSearchBeanName.disableLevelGroup?true:(jSearchBeanName.searchMode || jSearchBeanName.selectedJobGroup == null || jSearchBeanName.jobGroupCode =="")}'>
                <a4j:jsFunction action="#{jSearchBeanName.levelsGroupCodeChanged}" name="changeLevelsGroup"
                                reRender="jobIntgLevelsGroupCombo,jobIntgJobFieldValError,jobIntgLevelsCombo,jobIntgLevelsVal,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','jobIntgLevelsVal','levelGroupVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgLevelsGroupCombo" value="#{jSearchBeanName.selectedlevelsGroup}"
                             styleClass="DropdownboxMedium"
                             disabled='#{jSearchBeanName.disableLevelGroup?true:(jSearchBeanName.searchMode || jSearchBeanName.selectedJobGroup == null || jSearchBeanName.jobGroupCode =="")}'>
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.levelsGroupFields}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedLevelsGroupCodeChanged}"
                             reRender="jobIntgLevelsGroupVal,jobIntgLevelsCombo,jobIntgLevelsVal,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo,jobIntgGroupTypeMenu"/>
            </t:selectOneMenu>
            <!---R2----------------------- levels Section------------------
        -->
            <h:outputLabel styleClass="lable01" for="jobIntgLevelsVal" value="#{jobIntgResources.categories}"/>
            <t:inputText forceId="true" id="jobIntgLevelsVal" value="#{jSearchBeanName.levelCode}"
                         styleClass="filteration_input_text" maxlength="#{jSearchBeanName.codeMaxLength}"
                         onkeypress="validate(event);goChangeLevelsVal(event);" onchange="ChangeLevelsValOnBlur();"
                         disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedlevelsGroup ==null || jSearchBeanName.levelsGroupCode ==""}'>
                <a4j:jsFunction action="#{jSearchBeanName.levelsValChanged}" name="ChangeLevels"
                                reRender="jobIntgLevelsCombo,jobIntgJobFieldValError,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','childTypeVal','jobIntgLevelsVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgLevelsCombo" value="#{jSearchBeanName.selectedLevel}"
                             styleClass="DropdownboxMedium"
                             disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedlevelsGroup ==null || jSearchBeanName.levelsGroupCode ==""}'>
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.levels}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedLevelsChanged}"
                             reRender="jobIntgLevelsVal,jobIntgSubWorkTypeCombo,jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo,jobIntgGroupTypeMenu"/>
            </t:selectOneMenu>
            <!---R2----------------------- jobIntgSubWorkType Section------------------
        -->
            <h:outputLabel styleClass="lable01" for="jobIntgSubWorkType" value="#{jobIntgResources.sup_job_type}"/>
            <t:inputText forceId="true" id="jobIntgSubWorkType" value="#{jSearchBeanName.subWorkTypeCode}"
                         maxlength="#{jSearchBeanName.codeMaxLength}" styleClass="filteration_input_text"
                         onkeypress="validate(event);goChangeSubWorkTypeVal(event);"
                         onchange="ChangeSubWorkTypeValOnBlur();"
                         disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedLevel ==null || jSearchBeanName.levelCode ==""}'>
                <a4j:jsFunction action="#{jSearchBeanName.subWorkTypeValChanged}" name="ChangeSubWorkType"
                                reRender="jobIntgSubWorkTypeCombo,jobIntgJobFieldValError,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','workTypeNameVal','childTypeVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgSubWorkTypeCombo" value="#{jSearchBeanName.selectedSubWorkType}"
                             styleClass="DropdownboxMedium"
                             disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedLevel ==null || jSearchBeanName.levelCode ==""}'>
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.subWorkTypes}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedSubWorkTypeChanged}"
                             reRender="jobIntgSubWorkType,jobIntgWorkTypeDescVal,jobIntgWorkTypeDescCombo,jobIntgGroupTypeMenu"/>
            </t:selectOneMenu>
            <!---R3----------------------- workTypeDecs Section------------------
        -->
            <h:outputLabel styleClass="lable01" for="jobIntgWorkTypeDescVal" value="#{jobIntgResources.workTypeName}"/>
            <t:inputText forceId="true" id="jobIntgWorkTypeDescVal" value="#{jSearchBeanName.workTypeDescCode}"
                         maxlength="#{jSearchBeanName.codeMaxLength}" styleClass="filteration_input_text"
                         onkeypress="validate(event);goCahngeWorkTypeDecsVal(event);"
                         onchange="CahngeWorkTypeDecsValOnBlur();"
                         disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedSubWorkType ==null || jSearchBeanName.subWorkTypeCode ==""}'>
                <a4j:jsFunction action="#{jSearchBeanName.workTypeDescValChanged}" name="CahngeWorkTypeDecs"
                                reRender="jobIntgWorkTypeDescCombo,jobIntgJobFieldValError"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','workTypeNameVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgWorkTypeDescCombo"
                             value="#{jSearchBeanName.selectedWorkTypeDesc}" styleClass="DropdownboxMedium"
                             disabled='#{jSearchBeanName.searchMode || jSearchBeanName.selectedSubWorkType ==null || jSearchBeanName.subWorkTypeCode ==""}'>
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.workTypeDescFields}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedWorkTypeDescChanged}"
                             reRender="jobIntgWorkTypeDescVal,jobIntgGroupTypeMenu"/>
            </t:selectOneMenu>
            <!---R3----------------------- groupType Section------------------
        -->
            <h:outputLabel styleClass="lable01" value="#{jobIntgResources.job_typeGroup}"/>
            <t:inputText id="jobIntgGroupTypeVal" value="#{jSearchBeanName.groupTypeCode}"
                         maxlength="#{jSearchBeanName.codeMaxLength}" styleClass="filteration_input_text"
                         onkeypress="validate(event);goChangeGroupTypeVal(event);"
                         onchange="ChangeGroupTypeValOnBlur();"
                         disabled="#{jSearchBeanName.searchMode || empty jSearchBeanName.groupTypeList }">
                <a4j:jsFunction action="#{jSearchBeanName.groupTypeValChanged}" name="ChangeGroupType"
                                reRender="jobIntgGroupTypeMenu,jobIntgJobFieldValError,jobIntgJobLevelVal,jobIntgJobLevelCombo"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','jobTypeVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgGroupTypeMenu" value="#{jSearchBeanName.selectedGroupType}"
                             styleClass="DropdownboxMedium"
                             disabled="#{jSearchBeanName.searchMode || empty jSearchBeanName.groupTypeList }">
                <f:selectItem itemValue="" itemLabel="#{jobIntgResources.AllCategories}"/>
                <f:selectItems value="#{jSearchBeanName.groupTypeList}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.selectedGroupTypeCodeChanged}"
                             reRender="jobIntgGroupTypeVal,jobIntgJobLevelVal,jobIntgJobLevelCombo"/>
            </t:selectOneMenu>
            <!---R4----------------------- joblevel Section------------------
        -->
            <t:outputLabel value="#{jobIntgResources.job_level}" styleClass="lable01"/>
            <t:inputText id="jobIntgJobLevelVal" value="#{jSearchBeanName.jobLevelCode}"
                         maxlength="#{jSearchBeanName.codeMaxLength}" styleClass="filteration_input_text"
                         onkeypress="validate(event);goChangeJobLevelsVal(event);"
                         onchange="ChangeJobLevelsValOnBlur();"
                         disabled="#{jSearchBeanName.searchMode || empty jSearchBeanName.jobLevelList}">
                <a4j:jsFunction action="#{jSearchBeanName.jobLevelsChanged}" name="changeJobLevels"
                                reRender="jobIntgJobLevelCombo,jobIntgJobFieldValError"
                                oncomplete="setFocusOnElement('jobIntgJobFieldValError','jobTypeVal');"/>
            </t:inputText>
            <t:selectOneMenu forceId="true" id="jobIntgJobLevelCombo" value="#{jSearchBeanName.selectedJobLevel}"
                             styleClass="DropdownboxMedium"
                             disabled="#{jSearchBeanName.searchMode || empty jSearchBeanName.jobLevelList}">
                <f:selectItem itemLabel="#{jobIntgResources.AllCategories}" itemValue=""/>
                <f:selectItems value="#{jSearchBeanName.jobLevelList}"/>
                <a4j:support event="onchange" actionListener="#{jSearchBeanName.jobLevelCodeChanged}"
                             reRender="jobIntgJobLevelVal,jobIntgJobFieldValError"/>
            </t:selectOneMenu>
            <!--disabled="#{jSearchBeanName.searchMode}"
    -->
        </t:panelGrid>
        <%-- <a4j:commandButton id="searchJobBtnId" action="#{jSearchBeanName.fillDataTable}"
             value="#{jobIntgResources[jSearchBeanName.buttonValue]}"
             reRender="searchJobBtnId,filterPanelGrp,filter,filterPnl,addjobs,dataT_data_panel,dataT_data" disabled='#{( (
             jSearchBeanName.selectedJobGroup == null || jSearchBeanName.selectedJobGroup =="") && (
             jSearchBeanName.jobIntgJobKey == null || jSearchBeanName.jobIntgJobKey =="") && ( jSearchBeanName.jobName
             == null || jSearchBeanName.jobName =="") ) }' styleClass="cssButtonSmall"
             onkeypress="rerendersearchJobBtnIdButtonEvent(event);"/> disabled='#{( ( jSearchBeanName.selectedJobGroup == null
             || jSearchBeanName.selectedJobGroup =="") && ( jSearchBeanName.jobIntgJobKey == null ||
             jSearchBeanName.jobIntgJobKey =="") && ( jSearchBeanName.jobName == null || jSearchBeanName.jobName =="") )
             }'--%>
    </t:panelGroup>
    <t:panelGrid columns="2" align="center" border="0" forceId="true" id="jobIntgSearchBtnsPnl">
        <t:commandButton forceId="true" id="searchJobBtnId" value="" styleClass="ManyToManySearchButton" type="button">
            <a4j:support event="onclick" action="#{jSearchBeanName.searchJobs}"
                         reRender="jobIntgSearchBtnsPnl,jobIntgSearchPnl, jobIntgSearchResultPnl, jobIntgNavPnl,searchjob_paging_panel"
                         oncomplete="document.getElementById('searchJobBtnId').focus();"/>
        </t:commandButton>
        <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{jSearchBeanName.searchMode}"
                           action="#{jSearchBeanName.cancelSearch}"
                           reRender="jobIntgSearchBtnsPnl,jobIntgSearchPnl, jobIntgSearchResultPnl, jobIntgNavPnl,searchjob_paging_panel"
                           oncomplete="handleJobIntgSearchBtn();"/>
    </t:panelGrid>
</t:panelGroup>
<t:panelGroup forceId="true" id="jobIntgSearchResultPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="jobIntgSearchResultTblPnl" styleClass="fullWidthScroll110">
            <t:dataTable forceId="true" id="jobIntgSearchResultTbl" var="list" value="#{jSearchBeanName.usingPaging ? jSearchBeanName.pagingBean.myPagedDataModel : jSearchBeanName.myTableData}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                         binding="#{jSearchBeanName.myDataTable}" rows="#{shared_util.noOfTableRows}"
                         rowOnMouseOver="javascript:addRowHighlight('jobIntgSearchResultTbl');addRowHighlight('jobIntgSearchResultTbl');"
                         styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{!jSearchBeanName.singleSelection}">
                    <f:facet name="header">
                        <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{jSearchBeanName.checkAllFlag}">
                            <f:attribute name="checkAll" value="true"/>
                            <f:attribute name="listSize" value="#{jSearchBeanName.listSize}"/>
                            <a4j:support event="onclick" actionListener="#{jSearchBeanName.selectedCheckboxsAll}"
                                         oncomplete="setAll('checkAll', 'chk');" reRender="jobIntgNavPnl"/>
                        </t:selectBooleanCheckbox>
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                        <a4j:support event="onclick" actionListener="#{jSearchBeanName.selectedCheckboxs}"
                                     oncomplete="checkCheckAll('chk');" reRender="jobIntgNavPnl"/>
                    </t:selectBooleanCheckbox>
                </t:column>
                <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{jSearchBeanName.singleSelection}">
                    <f:facet name="header"/>
                    <t:selectOneRadio styleClass="radioButton_DataTable" id="chkRadio"
                                      value="#{jSearchBeanName.selectedRadio}" onmousedown="toggleRadio(this);"
                                      onkeyup="toggleRadioKeyUp(this);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick" actionListener="#{jSearchBeanName.selectedRadioButton}"
                                     reRender="jobIntgNavPnl"/>
                    </t:selectOneRadio>
                </t:column>
                <t:column id="code_column" sortable="false" width="20%" style=" direction: ltr;">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.jobKey}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="60%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
                
                 <t:column id="releatedMinistries_column"  width="10%" rendered="#{pageBeanName.indexArray[2]}"   sortable="false" >
                  <f:facet name="header">
                        <h:outputText id="privateTo_name" value="#{jobIntgResources.releated_Ministries}"/>
                    </f:facet>
                <t:graphicImage url='/app/media/images/TopMenu-UserGuideButton-hover.png'  border="0" 
                onmouseout="hideTip()" onmouseover="doTooltip(event,'#{list.releatedMinistries!='' ? list.releatedMinistries:jobIntgResources.public_Job }')" />        
                 </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ jSearchBeanName.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>

<t:panelGroup forceId="true" id="searchjob_paging_panel" style="height: 31px;">
                <h:panelGrid id="searchjob_panelGrd_scroller" columns="1" dir="#{shared_util.pageDirection}"
                             styleClass="scroller" width="300" rendered="#{jSearchBeanName.listSize > shared_util.noOfTableRows}">
                    <t:dataScroller id="searchjob_scroll_1" fastStep="5" pageCountVar="pageCount"
                                    pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                                    paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                                    firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                                    previousStyleClass="textpage" paginatorColumnClass="textpage"
                                    paginatorActiveColumnClass="paging" 
                                    paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                                    styleClass="textpage" immediate="false" for="jobIntgSearchResultTbl"
                                    renderFacetsIfSinglePage="false"
                                    actionListener="#{jSearchBeanName.openSearchJobsDiv}">
                        <%--<f:facet name="first">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_first1">
                                <t:graphicImage id="searchjob_jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_jobs_list_img_firstOff" onclick="return false;"
                                                rendered="#{pageIndex<= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>--%>
                        <%--<f:facet name="last">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_last">
                                <t:graphicImage id="searchjob_jobs_list_img_lastOn"
                                                rendered="#{pageIndex < pageCount}"
                                                title="#{globalResources.scroller_last}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_jobs_list_img_lastOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>--%>
                        <f:facet name="previous">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_prv">
                                <t:graphicImage id="searchjob_jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_previous}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_jobs_list_img_prvOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="next">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_nxt">
                                <t:graphicImage id="searchjob_jobs_list_img_nxtOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_next}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_jobs_list_img_nxtOff"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastforward">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_ffrwrd">
                                <t:graphicImage id="jsearchjob_obs_list_img_ffrwrdOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_fastforward}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_jobs_list_img_ffrwrdOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                        <f:facet name="fastrewind">
                            <h:panelGroup id="searchjob_jobs_list_panelGrp_frwnd">
                                <t:graphicImage id="searchjob_jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_fastrewind}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                                border="0"/>
                                <t:graphicImage id="searchjob_searchjob_jobs_list_img_frwndOff"
                                                onclick="return false;" rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                                border="0"/>
                            </h:panelGroup>
                        </f:facet>
                    </t:dataScroller>
                </h:panelGrid>
     </t:panelGroup>

<t:panelGroup id="jobIntgNavPnl" forceId="true">
    <t:commandButton id="addjobs" action="#{jSearchBeanName.addJobs}" value="#{globalResources.SaveButton}"
                     styleClass="cssButtonSmall" disabled="#{ jSearchBeanName.selectedListSize == 0 }"/>
    <t:commandButton forceId="true" id="backButtonCustomDiv1" type="button" value="#{globalResources.back}"
                     onclick="hideLookUpDiv(window.blocker,window.customDiv1)" styleClass="cssButtonSmall"
                     action="#{jSearchBeanName.back}"/>
</t:panelGroup>
<f:verbatim>
    <script language="javascript" type="text/javascript">
      handleJobIntgSearchBtn();

      function handleJobIntgSearchBtn() {
          document.getElementById('searchJobBtnId').disabled = true;
          document.getElementById('searchJobBtnId').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
          var jobIntgJobKey = document.getElementById("jobIntgJobKey").value;
          var jobName = document.getElementById("jobIntgJobName").value;
          var jobGroubCode = document.getElementById("jobIntgJobGroubCombo").value;

          if (jobIntgJobKey.length != 0 || jobName.length != 0 || jobGroubCode != '') {
              document.getElementById('searchJobBtnId').disabled = false;
              document.getElementById('searchJobBtnId').className = 'ManyToManySearchButton';
          }
      }

      function FireJobIntgSearchBtn() {
          handleJobIntgSearchBtn();
          FireButtonClick('searchJobBtnId');
      }

      function validate(evt) {
          var theEvent = evt || window.event;
          var key = theEvent.keyCode || theEvent.which;
          key = String.fromCharCode(key);
          var regex = /[0-9]|\./;
          if (!regex.test(key)) {
              theEvent.returnValue = false;
              if (theEvent.preventDefault)
                  theEvent.preventDefault();
          }
      }

      function goToTable(e) {
          if (e.keyCode == 13) {
              changeJobCodeVal();
              rerendersearchJobBtnIdButton();
              e.preventDefault();
          }
      }

      function rerendersearchJobBtnIdButtonEvent(e) {
          if (e.keyCode == 13) {
              rerendersearchJobBtnIdButton();
              e.preventDefault();
          }
      }

      function goToTablePage(e) {
          changeJobCodeVal();
      }

      function gochangeJobGroupVal(e) {
          if (e.keyCode == 13) {
              changeJobGroupVal();
              e.preventDefault();
          }
      }

      function gochangeLevelsGroupVal(e) {
          if (e.keyCode == 13) {
              changeLevelsGroup();
              e.preventDefault();
          }
      }

      function rerendersearchJobBtnIdButtonEvent(e) {
          if (e.keyCode == 13) {
              rerendersearchJobBtnIdButton();
              e.preventDefault();
          }
      }

      function goChangeLevelsVal(e) {
          if (e.keyCode == 13) {
              changeLevels();
              e.preventDefault();
          }
      }

      function goChangeJobLevelsVal(e) {
          if (e.keyCode == 13) {
              changeJobLevels();
              e.preventDefault();
          }
      }

      function goChangeSubWorkTypeVal(e) {
          if (e.keyCode == 13) {
              ChangeSubWorkType();
              e.preventDefault();
          }
      }

      function goCahngeWorkTypeDecsVal(e) {
          if (e.keyCode == 13) {
              CahngeWorkTypeDecs();
              e.preventDefault();
          }
      }

      function goChangeGroupTypeVal(e) {
          if (e.keyCode == 13) {
              ChangeGroupType();
              e.preventDefault();
          }
      }

      function CahngeWorkTypeDecsValOnBlur() {
          CahngeWorkTypeDecs();
      }

      function ChangeSubWorkTypeValOnBlur() {
          ChangeSubWorkType();
      }

      function ChangeLevelsValOnBlur() {
          changeLevels();
      }

      function changeJobGroupValOnBlur() {
          changeJobGroupVal();
      }

      function ChangeLevelsGroupValOnBlur() {
          changeLevelsGroup();
      }

      function ChangeGroupTypeValOnBlur() {
          ChangeGroupType();
      }

      function ChangeJobLevelsValOnBlur() {
          changeJobLevels();
      }

          function ChangeGroupTypeValOnBlur() {
              ChangeGroupType();
          }
    </script>
</f:verbatim>
<t:saveState value="#{jSearchBeanName.excludedJobList}"/>
<t:saveState value="#{jSearchBeanName.okButtonMethod}"/>
<%--<t:saveState value="#{jSearchBeanName.cancelSearchButtonMethod}"/>--%>
<t:saveState value="#{jSearchBeanName.jobName}"/>
<t:saveState value="#{jSearchBeanName.jobKey}"/>
<t:saveState value="#{jSearchBeanName.jobGroupCode}"/>
<t:saveState value="#{jSearchBeanName.selectedJobGroup}"/>
<t:saveState value="#{jSearchBeanName.selectedlevelsGroup}"/>
<t:saveState value="#{jSearchBeanName.levelsGroupCode}"/>
<t:saveState value="#{jSearchBeanName.selectedLevel}"/>
<t:saveState value="#{jSearchBeanName.levelCode}"/>
<t:saveState value="#{jSearchBeanName.selectedSubWorkType}"/>
<t:saveState value="#{jSearchBeanName.subWorkTypeCode}"/>
<t:saveState value="#{jSearchBeanName.workTypeDescCode}"/>
<t:saveState value="#{jSearchBeanName.selectedWorkTypeDesc}"/>
<t:saveState value="#{jSearchBeanName.groupTypeList}"/>
<t:saveState value="#{jSearchBeanName.groupTypeCode}"/>
<t:saveState value="#{jSearchBeanName.selectedGroupType}"/>
<t:saveState value="#{jSearchBeanName.jobLevelCode}"/>
<t:saveState value="#{jSearchBeanName.selectedJobLevel}"/>
<t:saveState value="#{jSearchBeanName.jobLevelList}"/>
<t:saveState value="#{jSearchBeanName.jobFieldValError}"/>
<t:saveState value="#{jSearchBeanName.errorMsgBind}"/>
<t:saveState value="#{jSearchBeanName.showMsg}"/>
<t:saveState value="#{jSearchBeanName.searchMode}"/>
<t:saveState value="#{jSearchBeanName.buttonValue}"/>
<t:saveState value="#{jSearchBeanName.disableSearchBtn}"/>
<t:saveState value="#{jSearchBeanName.singleSelection}"/>
<t:saveState value="#{jSearchBeanName.myTableData}"/>
<t:saveState value="#{jSearchBeanName.highlightedDTOS}"/>
<t:saveState value="#{jSearchBeanName.searchMode}"/>
<t:saveState value="#{jSearchBeanName.selectedDTOS}"/>
<t:saveState value="#{jSearchBeanName.fullColumnName}"/>
<t:saveState value="#{jSearchBeanName.sortAscending}"/>
<t:saveState value="#{jSearchBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{jSearchBeanName.loadSecAccessibleJobsOnly}"/>

 <!-- Start Paging -->
             
<t:saveState value="#{jSearchBeanName.currentPageIndex}"/>
 
<t:saveState value="#{jSearchBeanName.oldPageIndex}"/>
 
<t:saveState value="#{jSearchBeanName.sorting}"/>
 
<t:saveState value="#{jSearchBeanName.usingPaging}"/>
 
<t:saveState value="#{jSearchBeanName.jobSearchCriteriaDTO}"/>

 <t:saveState value="#{jSearchBeanName.usingBsnPaging}"/>
 
<t:saveState value="#{jSearchBeanName.updateMyPagedDataModel}"/>
 
<t:saveState value="#{jSearchBeanName.resettedPageIndex}"/>
 
<t:saveState value="#{jSearchBeanName.bsnSortingColumnName}"/>
              
<t:saveState value="#{jSearchBeanName.pagingRequestDTO}"/>

<t:saveState value="#{jSearchBeanName.pagingBean.myPagedDataModel}"/>
 
<t:saveState value="#{jSearchBeanName.pagingBean.preUpdatedDataModel}"/>
