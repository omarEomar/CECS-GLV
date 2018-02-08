<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<tiles:importAttribute scope="request"/>
<f:loadBundle basename="com.beshara.csc.nl.qul.integration.presentation.resources.qulintegration" var="qulintegration"/>
<h:panelGrid columns="1" align="center">
    <t:outputText id="qulIntgJobFieldValError" forceId="true" styleClass="errMsg"
                  value="#{qulintegration.inavlid_Qul_val}" binding="#{qulSearchBeanName.errorMsgBind}"
                  style="#{qulSearchBeanName.showMsg}"/>
</h:panelGrid>
<t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
    <%-- #{pageBeanName.iconPnlStyle}--%>
    <t:panelGroup onclick="showHideFilterPnlFn();"/>
</t:panelGroup>
<a4j:jsFunction action="#{qulSearchBeanName.showHideFilterPnl}" name="showHideFilterPnlFn"
                reRender="hiddenBtnPnl,hideDivImg,qulIntgSearchPnl,qulIntgSearchResultTblPnl"
                oncomplete="toggleDivUsingMaxHeightVer2('hideDivImg', 'qulIntgSearchPnl', 'qulIntgSearchResultTblPnl',document.getElementById('filterPnlCollapsed').value);"/>
<t:panelGroup forceId="true" id="qulIntgSearchPnl">
    <%-- style="display:#{pageBeanName.filterPnlDisplay}"--%>
    <t:panelGroup forceId="true" id="qulIntgSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="1" cellspacing="0">
            <h:outputLabel styleClass="lable01" value="#{globalResources.Code}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="qulIntgQulKey" value="#{qulSearchBeanName.qulKey}" styleClass="textbox"
                             onchange="trimBorders(qulIntgQulKey);" disabled="#{qulSearchBeanName.searchMode}"
                             onkeypress="FireQulIntgSearchBtn();trimBorders(qulIntgQulKey);"
                             onkeyup="handleQulIntgSearchBtn();" style="width:228px;"/>
                <a4j:jsFunction reRender="qulIntgSearchBtnsPnl,searchQulBtn" name="rerendersearchQulBtnButton"
                                oncomplete="setFocusOnElement('qulIntgQulName');"/>
            </t:panelGroup>
            <t:outputLabel value="#{qulintegration.qul_name}" styleClass="lable01"/>
            <t:panelGroup colspan="1">
                <t:inputText maxlength="250" value="#{qulSearchBeanName.qulName}" id="qulIntgQulName" forceId="true"
                             styleClass="textbox" onchange="trimBorders(qulIntgQulName);" style="width:228px;"
                             onkeypress="FireQulIntgSearchBtn();trimBorders(qulIntgQulName);"
                             onkeyup="handleQulIntgSearchBtn();" disabled="#{qulSearchBeanName.searchMode}"/>
                <a4j:jsFunction reRender="qulIntgSearchBtnsPnl,searchQulBtn" name="rerendersearchQulBtnButton"/>
            </t:panelGroup>
            <!---R1----------------------- mainEduLevel Section------------------
        -->
            <h:outputLabel styleClass="lable01 nowrap_txt" value="#{qulintegration.main_Edu_Level}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="mainEduLevelInput" value="#{qulSearchBeanName.mainEduLevelCode}"
                             styleClass="filteration_input_text" maxlength="#{qulSearchBeanName.codeMaxLength}"
                             onkeypress="filterationInputOnKeyPress(event, this, 'qulIntgMainEduLevelCombo', 'mainEduLevelCodeError', changeMainEduLevelCode,'levelChildCodeInput');validate(event);"
                             onblur="filterationInputOnBlur(event,this,'qulIntgMainEduLevelCombo','mainEduLevelCodeError',changeMainEduLevelCode,'levelChildCodeInput');"
                             onchange="enabelIntegerOnly(this);handleQulIntgSearchBtn();"
                             disabled="#{qulSearchBeanName.searchMode}">
                    <a4j:jsFunction action="#{qulSearchBeanName.mainEduLevelCodeChanged}" name="changeMainEduLevelCode"
                                    reRender="qulIntgSearchBtnsPnl,searchQulBtn,mainEduLevelCodeError,qulIntgMainEduLevelCombo,levelChildCodeInput,levelChildCodeError,levelChildCombo,generalSpecsCodeInput,generalSpecsCombo,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput,generalFieldValError,minorSpecsCodeError,majorSpecCodeError"
                                    oncomplete="clearInputText('levelChildCodeInput');clearInputText('generalSpecsCodeInput');clearInputText('majorSpecCodeInput');clearInputText('minorSpecsCodeInput');"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="qulIntgMainEduLevelCombo"
                                 value="#{qulSearchBeanName.selectedmainEduLevel}" styleClass="DropdownboxMedium"
                                 disabled="#{qulSearchBeanName.searchMode}" style="width: 170px; margin-right: 5px;"
                                 onchange="handleQulIntgSearchBtn();filterationDropboxOnChange(event,this,'mainEduLevelInput','mainEduLevelCodeError',changeMainEduLevelCode,'levelChildCodeInput');">
                    <f:selectItem itemValue="" itemLabel="#{qulintegration.all_major_list_label}"/>
                    <t:selectItems var="item" itemLabel="#{item.name}" itemValue="#{item.code.key}" value="#{qulSearchBeanName.mainEduLevels}"/>
                    <a4j:support event="onchange"
                                 reRender="qulIntgSearchBtnsPnl,searchQulBtn,mainEduLevelInput,levelChildCodeInput,levelChildCombo,levelChildCodeError,generalSpecsCodeInput,generalSpecsCombo,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1">
                    <t:outputLabel forceId="true" id="mainEduLevelCodeError"
                                   value="#{qulintegration.invalid_MainEduLevel_Code}" styleClass="validation_error"
                                   style="display:none"/>
                </t:panelGroup>
            </t:panelGroup>
            <!---L1----------------------- levelChildCode Section------------------
        -->
            <h:outputLabel styleClass="lable01 nowrap_txt" value="#{qulintegration.edu_level_child}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="levelChildCodeInput" value="#{qulSearchBeanName.levelChildCode}"
                             styleClass="textboxsmall" maxlength="#{qulSearchBeanName.codeMaxLength}"
                             onkeypress="validate(event);filterationInputOnKeyPress(event, this, 'levelChildCombo', 'levelChildCodeError', changeLevelChildCode, 'generalSpecsCodeInput');"
                             onblur="filterationInputOnBlur(event, this, 'levelChildCombo', 'levelChildCodeError', changeLevelChildCode, 'generalSpecsCodeInput');"
                             onchange="enabelIntegerOnly(this);"
                             disabled='#{qulSearchBeanName.searchMode || qulSearchBeanName.mainEduLevelCode=="" || qulSearchBeanName.selectedmainEduLevel==""}'>
                    <a4j:jsFunction action="#{qulSearchBeanName.levelChildValChanged}" name="changeLevelChildCode"
                                    reRender="qulIntgSearchBtnsPnl,searchQulBtn,levelChildCombo,levelChildCodeError,generalSpecsCodeInput,generalSpecsCombo,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput,generalFieldValError,minorSpecsCodeError,majorSpecCodeError"
                                    oncomplete="clearInputText('generalSpecsCodeInput');clearInputText('majorSpecCodeInput');clearInputText('minorSpecsCodeInput');"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="levelChildCombo" value="#{qulSearchBeanName.selectedLevelChild}"
                                 onchange="filterationDropboxOnChange(event, this, 'levelChildCodeInput', 'levelChildCodeError', changeLevelChildCode, 'generalSpecsCodeInput')"
                                 style="width: 170px;" styleClass="DropdownboxMedium"
                                 disabled='#{qulSearchBeanName.searchMode || qulSearchBeanName.mainEduLevelCode=="" || qulSearchBeanName.selectedmainEduLevel==""}'>
                    <f:selectItem itemValue="" itemLabel="#{qulintegration.all_major_list_label}"/>
                    <t:selectItems var="item" itemLabel="#{item.name}" itemValue="#{item.code.key}" value="#{qulSearchBeanName.levelChildList}"/>
                    <a4j:support event="onchange"
                                 reRender="qulIntgSearchBtnsPnl,searchQulBtn,levelChildCodeInput,generalSpecsCodeInput,generalSpecsCombo,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1">
                    <t:outputLabel forceId="true" id="levelChildCodeError"
                                   value="#{qulintegration.inavlid_edu_child_Code}" styleClass="validation_error"
                                   style="display:none"/>
                </t:panelGroup>
            </t:panelGroup>
            <!---R2----------------------- generalSpecsCode Section------------------
        -->
            <h:outputLabel styleClass="lable01" value="#{qulintegration.gen_spec}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="generalSpecsCodeInput" value="#{qulSearchBeanName.generalSpecsCode}"
                             styleClass="textboxsmall" disabled="#{qulSearchBeanName.searchMode}"
                             maxlength="#{qulSearchBeanName.codeMaxLength}"
                             onkeypress="validate(event);filterationInputOnKeyPress(event, this, 'generalSpecsCombo', 'generalFieldValError', changeGeneralVal, 'majorSpecCodeInput');"
                             onblur="filterationInputOnBlur(event, this, 'generalSpecsCombo', 'generalFieldValError', changeGeneralVal, 'majorSpecCodeInput');">
                    <a4j:jsFunction action="#{qulSearchBeanName.generalSpecsCodeChanged}" name="changeGeneralVal"
                                    reRender="qulIntgSearchBtnsPnl,searchQulBtn,generalSpecsCombo,generalFieldValError,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput,minorSpecsCodeError,majorSpecCodeError"
                                    oncomplete="clearInputText('majorSpecCodeInput');clearInputText('minorSpecsCodeInput');"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="generalSpecsCombo" value="#{qulSearchBeanName.selectedGeneralSpecs}"
                                 onchange="filterationDropboxOnChange(event, this, 'generalSpecsCodeInput', 'generalFieldValError', changeGeneralVal, 'majorSpecCodeInput')"
                                 style="width: 170px;" styleClass="DropdownboxMedium"
                                 disabled="#{qulSearchBeanName.searchMode}">
                    <f:selectItem itemValue="" itemLabel="#{qulintegration.all_major_list_label}"/>
                    <t:selectItems var="item" itemLabel="#{item.name}" itemValue="#{item.code.key}" value="#{qulSearchBeanName.generalSpecsList}"/>
                    <a4j:support event="onchange"
                                 reRender="qulIntgSearchBtnsPnl,searchQulBtn,generalSpecsCodeInput,majorSpecCodeInput,majorSpecCombo,minorSpecsCombo,minorSpecsCodeInput,majorSpecCodeError"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1">
                    <t:outputText forceId="true" id="generalFieldValError"
                                  value="#{qulintegration.inavlid_general_Code}" styleClass="validation_error"
                                  style="display:none;"/>
                </t:panelGroup>
            </t:panelGroup>
            <!---L2----------------------- majorSpecCode Section------------------
        -->
            <h:outputLabel styleClass="lable01" value="#{qulintegration.major_spec}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="majorSpecCodeInput" value="#{qulSearchBeanName.majorSpecCode}"
                             styleClass="textboxsmall" disabled="#{qulSearchBeanName.searchMode}"
                             maxlength="#{qulSearchBeanName.codeMaxLength}"
                             onkeypress="validate(event);filterationInputOnKeyPress(event, this, 'majorSpecCombo', 'majorSpecCodeError', changeMajorVal, 'minorSpecsCodeInput');"
                             onblur="filterationInputOnBlur(event, this, 'majorSpecCombo', 'majorSpecCodeError', changeMajorVal, 'minorField');">
                    <a4j:jsFunction action="#{qulSearchBeanName.majorSpecsCodeChanged}" name="changeMajorVal"
                                    reRender="qulIntgSearchBtnsPnl,searchQulBtn,majorSpecCombo,majorSpecCodeError,minorSpecsCodeInput,minorSpecsCombo,minorSpecsCodeError"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="majorSpecCombo" value="#{qulSearchBeanName.selectedMajorSpec}"
                                 onchange="filterationDropboxOnChange(event, this, 'majorSpecCodeInput', 'majorSpecCodeError', changeMajorVal, 'minorSpecsCodeInput')"
                                 style="width: 170px;" styleClass="DropdownboxMedium"
                                 disabled="#{qulSearchBeanName.searchMode}">
                    <f:selectItem itemValue="" itemLabel="#{qulintegration.all_major_list_label}"/>
                    <t:selectItems var="item" itemLabel="#{item.name}" itemValue="#{item.code.key}" value="#{qulSearchBeanName.majorSpecsList}"/>
                    <a4j:support event="onchange"
                                 reRender="qulIntgSearchBtnsPnl,searchQulBtn,majorSpecCodeInput,minorSpecsCodeInput,minorSpecsCombo"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1">
                    <t:outputText forceId="true" id="majorSpecCodeError" value="#{qulintegration.major_Spec_val}"
                                  styleClass="validation_error" style="display:none;"/>
                </t:panelGroup>
            </t:panelGroup>
            <!---R3----------------------- minorspecs Section------------------
        -->
            <h:outputLabel styleClass="lable01" value="#{qulintegration.minorspecs_title}"/>
            <t:panelGroup colspan="1">
                <t:inputText forceId="true" id="minorSpecsCodeInput" value="#{qulSearchBeanName.minorSpecsCode}"
                             styleClass="textboxsmall" disabled="#{qulSearchBeanName.searchMode}"
                             maxlength="#{qulSearchBeanName.codeMaxLength}"
                             onkeypress="filterationInputOnKeyPress(event, this, 'minorSpecsCombo', 'minorSpecsCodeError', changeMinorSpecsVal, null);validate(event);"
                             onblur="filterationInputOnBlur(event, this, 'minorSpecsCombo', 'minorSpecsCodeError', changeMinorSpecsVal, null);">
                    <a4j:jsFunction action="#{qulSearchBeanName.minorSpecsCodeChanged}" name="changeMinorSpecsVal"
                                    reRender="qulIntgSearchBtnsPnl,searchQulBtn,minorSpecsCombo,minorSpecsCodeError,majorSpecCodeError"/>
                </t:inputText>
                <f:verbatim>&nbsp;</f:verbatim>
                <t:selectOneMenu forceId="true" id="minorSpecsCombo" value="#{qulSearchBeanName.selectedMinorSpecs}"
                                 onchange="filterationDropboxOnChange(event, this, 'minorSpecsCodeInput', 'minorSpecsCodeError', changeMinorSpecsVal, null)"
                                 style="width: 170px;" styleClass="DropdownboxMedium"
                                 disabled="#{qulSearchBeanName.searchMode}">
                    <f:selectItem itemValue="" itemLabel="#{qulintegration.all_major_list_label}"/>
                    <t:selectItems var="item" itemLabel="#{item.name}" itemValue="#{item.code.key}" value="#{qulSearchBeanName.minorSpecsList}"/>
                    <a4j:support event="onchange" reRender="qulIntgSearchBtnsPnl,searchQulBtn,minorSpecsCodeInput"/>
                </t:selectOneMenu>
                <f:verbatim>
                    <br/>
                </f:verbatim>
                <t:panelGroup colspan="1">
                    <t:outputText forceId="true" id="minorSpecsCodeError" value="#{qulintegration.inavlid_minor_Code}"
                                  styleClass="validation_error" style="display:none;"/>
                </t:panelGroup>
            </t:panelGroup>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:panelGrid columns="2" align="center" border="0" forceId="true" id="qulIntgSearchBtnsPnl">
    <%-- <a4j:commandButton id="searchQulBtn" value="" styleClass="ManyToManySearchButton"
         action="#{qulSearchBeanName.search}" disabled=""
         reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
         oncomplete="document.getElementById('searchQulBtn').focus();"/>--%>
    <t:commandButton forceId="true" id="searchQulBtn" value="" styleClass="ManyToManySearchButton" type="button"
                     rendered="#{!qulSearchBeanName.searchMode}">
        <a4j:support event="onclick" action="#{qulSearchBeanName.search}"
                     reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
                     oncomplete="document.getElementById('searchQulBtn').focus();"/>
    </t:commandButton>
    <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{qulSearchBeanName.searchMode}"
                       action="#{qulSearchBeanName.cancelSearch}"
                       reRender="qulIntgSearchBtnsPnl,qulIntgSearchPnl,qulIntgSearchResultPnl,qul_paging_panel_group,qulIntgNavPnl"
                       oncomplete="handleQulIntgSearchBtn();"/>
</t:panelGrid>
<t:panelGroup forceId="true" id="qulIntgSearchResultPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="qulIntgSearchResultTblPnl" style="max-height:100px;"
                      styleClass="fullWidthScroll80">
            <%-- #{pageBeanName.resultPnlHeight}--%>
            <t:dataTable id="qulIntgSearchResultTbl" var="list"
                         value="#{qulSearchBeanName.pagingBean.myPagedDataModel}"
                         forceId="true" forceIdIndexFormula="#{list.code.key}" rowIndexVar="index"
                         renderedIfEmpty="false" binding="#{qulSearchBeanName.myDataTable}" rows="12"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:qulIntgSearchResultTbl');addRowHighlight('treeform:qulIntgSearchResultTbl');"
                         styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}">
                <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{!qulSearchBeanName.singleSelection}">
                    <f:facet name="header">
                        <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{qulSearchBeanName.checkAllFlag}">
                            <f:attribute name="checkAll" value="true"/>
                            <f:attribute name="listSize" value="#{qulSearchBeanName.pagingBean.totalListSize}"/>
                            <a4j:support event="onclick" actionListener="#{qulSearchBeanName.selectedCheckboxsAll}"
                                         oncomplete="setAll('checkAll', 'chk');" reRender="qulIntgNavPnl,addqul"/>
                        </t:selectBooleanCheckbox>
                    </f:facet>
                    <t:selectBooleanCheckbox forceId="true" id="chk" value="#{list.checked}">
                        <a4j:support event="onclick" actionListener="#{qulSearchBeanName.selectedCheckboxs}"
                                     oncomplete="checkCheckAll('chk');" reRender="qulIntgNavPnl,addqul"/>
                    </t:selectBooleanCheckbox>
                </t:column>
                <t:column id="radio_column" styleClass="standardTable_Header3" width="5%"
                          rendered="#{qulSearchBeanName.singleSelection}">
                    <f:facet name="header"/>
                    <t:selectOneRadio styleClass="radioButton_DataTable" id="chkRadio"
                                      value="#{qulSearchBeanName.selectedRadio}" onmousedown="toggleRadio(this);"
                                      onkeyup="toggleRadioKeyUp(this);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick" actionListener="#{qulSearchBeanName.selectedRadioButton}"
                                     reRender="qulIntgNavPnl"/>
                    </t:selectOneRadio>
                </t:column>
                <t:column id="code_column" sortable="false" width="18%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.qualificationDtlCode}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="70%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{qulSearchBeanName.pagingBean.totalListSize == 0 }" align="center">
                <t:outputText value="#{qulintegration.noQualificationResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<t:panelGroup forceId="true" id="qul_paging_panel_group">
<t:outputText dir="ltr" forceId="true" id="listSize1" style="width:100%" styleClass="noOfRecords"
             value="#{globalResources.noOfRecords} : #{qulSearchBeanName.pagingBean.totalListSize}" rendered="#{qulSearchBeanName.pagingBean.totalListSize > 0}"/>
    <t:panelGroup forceId="true" id="paging_panel_popup"
                  rendered="#{ (qulSearchBeanName.pagingBean.totalListSize > 12) ? true : false }">
        <h:panelGrid id="qul_panelGrd_scroller" columns="1" dir="#{rtl}" styleClass="scroller"
                     rendered="#{qulSearchBeanName.pagingBean.totalListSize > 12}">
            <t:dataScroller id="scroll_1" fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex"
                            paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                            fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                            lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                            paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            styleClass="textpage" immediate="false" for="qulIntgSearchResultTbl"
                            renderFacetsIfSinglePage="false" binding="#{qulSearchBeanName.dataScroller}"
                            actionListener="#{qulSearchBeanName.scrollerAction}">
                <f:facet name="first">
                    <h:panelGroup id="jobs_list_panelGrp_first">
                        <t:graphicImage id="jobs_list_img_firstOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="jobs_list_panelGrp_last">
                        <t:graphicImage id="jobs_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_lastOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="jobs_list_panelGrp_prv">
                        <t:graphicImage id="jobs_list_img_prvOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_prvOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="jobs_list_panelGrp_nxt">
                        <t:graphicImage id="jobs_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                        <t:graphicImage id="jobs_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_ffrwrdOff" onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                        border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd">
                        <t:graphicImage id="jobs_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                        <t:graphicImage id="jobs_list_img_frwndOff" onclick="return false;"
                                        rendered="#{pageIndex &lt;= 1}"
                                        url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                        border="0"/>
                        <t:inputHidden forceId="true" id="pageIndexWithoutPaging" value="#{pageIndex}"
                                       valueChangeListener="#{shared_util.changePageIndex}"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
            <t:inputHidden forceId="true" id="qulpageIndex" value=""/>
        </h:panelGrid>
        <t:inputHidden forceId="true" id="qulnoOfTableRows" value="12"/>
        <t:inputHidden forceId="true" id="qularrayId" value=""/>
    </t:panelGroup>
</t:panelGroup>
<t:panelGroup id="qulIntgNavPnl" forceId="true">
    <t:commandButton id="addqul" action="#{qulSearchBeanName.addQualifications}" value="#{globalResources.ok}"
                     styleClass="cssButtonSmall" disabled="#{empty qulSearchBeanName.selectedDTOS }"/>
    <t:commandButton forceId="true" id="backButtonIntegrationDiv1" value="#{globalResources.back}"
                     styleClass="cssButtonSmall"
                     action="#{qulSearchBeanName.back}"/>
</t:panelGroup>
<t:panelGroup id="hiddenBtnPnl" forceId="true">
    <%-- <t:inputHidden id="resultPnlHeight" forceId="true" value="#{pageBeanName.resultPnlHeight}" />--%>
    <%-- <t:inputHidden id="iconPnlStyle" forceId="true" value="#{pageBeanName.iconPnlStyle}" />--%>
    <%-- <t:inputHidden id="filterPnlDisplay" forceId="true" value="#{pageBeanName.filterPnlDisplay}" />--%>
    <t:inputHidden id="filterPnlCollapsed" forceId="true" value="#{qulSearchBeanName.filterPnlCollapsed}"/>
</t:panelGroup>
<%-- <t:inputHidden forceId="true" id="searchmode" value="#{qulSearchBeanName.searchMode}"/>--%>
<script language="javascript" type="text/javascript">
  handleQulIntgSearchBtn();

  toggleDivUsingMaxHeightVer2('hideDivImg', 'qulIntgSearchPnl', 'qulIntgSearchResultTblPnl', document.getElementById('filterPnlCollapsed').value);

  function toggleDivUsingMaxHeightVer2(togglerId, cnt1DivId, cntDivId, collapse) {
      var collapsedHeight;
      var displayedHeight;
      //alert("toggleDivUsingMaxHeightVer2 collapse= "+collapse);
      if (collapse == 'true') {
          //alert("yes true");
          collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
          displayedHeight = document.getElementById(cntDivId).offsetHeight;
          document.getElementById(cnt1DivId).style.display = 'none';
          document.getElementById(togglerId).className = 'showDivPnl';
          document.getElementById(cntDivId).style = "max-height:" + (displayedHeight + collapsedHeight - 5) + "px;";
      }
      else {
          //alert("no flase ");
          document.getElementById(cnt1DivId).style.display = 'block';
          document.getElementById(togglerId).className = 'hideDivPnl';
          //document.getElementById(cntDivId).style = "max-height:"+expandedHieght+"px;";
      }
  }

  function handleQulIntgSearchBtn() {
      var qulIntgQulKey = document.getElementById("qulIntgQulKey").value;
      var qulName = document.getElementById("qulIntgQulName").value;
      var qulIntgMainEduLevelCode = document.getElementById("qulIntgMainEduLevelCombo").value;
      if (qulIntgQulKey.length != 0 || qulName.length != 0 || qulIntgMainEduLevelCode != '') {
          document.getElementById('searchQulBtn').disabled = false;
          document.getElementById('searchQulBtn').className = 'ManyToManySearchButton';
      }
      else {
          document.getElementById('searchQulBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
          document.getElementById('searchQulBtn').disabled = true;
      }
  }

  function FireQulIntgSearchBtn() {
      handleQulIntgSearchBtn();
      FireButtonClick('searchQulBtn');
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
</script>
<t:saveState value="#{qulSearchBeanName.excludedQulList}"/>
<t:saveState value="#{qulSearchBeanName.okButtonMethod}"/>
<t:saveState value="#{qulSearchBeanName.errorMsgBind}"/>
<t:saveState value="#{qulSearchBeanName.showMsg}"/>
<t:saveState value="#{qulSearchBeanName.searchMode}"/>
<t:saveState value="#{qulSearchBeanName.singleSelection}"/>
<t:saveState value="#{qulSearchBeanName.highlightedDTOS}"/>
<t:saveState value="#{qulSearchBeanName.selectedDTOS}"/>
<t:saveState value="#{qulSearchBeanName.qulName}"/>
<t:saveState value="#{qulSearchBeanName.qulKey}"/>
<t:saveState value="#{qulSearchBeanName.selectedmainEduLevel}"/>
<t:saveState value="#{qulSearchBeanName.mainEduLevelCode}"/>
<t:saveState value="#{qulSearchBeanName.selectedLevelChild}"/>
<t:saveState value="#{qulSearchBeanName.levelChildCode}"/>
<t:saveState value="#{qulSearchBeanName.selectedGeneralSpecs}"/>
<t:saveState value="#{qulSearchBeanName.generalSpecsCode}"/>
<t:saveState value="#{qulSearchBeanName.selectedMajorSpec}"/>
<t:saveState value="#{qulSearchBeanName.majorSpecCode}"/>
<t:saveState value="#{qulSearchBeanName.minorSpecsCode}"/>
<t:saveState value="#{qulSearchBeanName.selectedMinorSpecs}"/>
<t:saveState value="#{qulSearchBeanName.mainEduLevels}"/>
<t:saveState value="#{qulSearchBeanName.levelChildList}"/>
<t:saveState value="#{qulSearchBeanName.generalSpecsList}"/>
<t:saveState value="#{qulSearchBeanName.majorSpecsList}"/>
<t:saveState value="#{qulSearchBeanName.minorSpecsList}"/>

<t:saveState value="#{qulSearchBeanName.fullColumnName}"/>
<t:saveState value="#{qulSearchBeanName.sortAscending}"/>
<t:saveState value="#{qulSearchBeanName.ascending}"/>
<t:saveState value="#{qulSearchBeanName.currentPageIndex}"/>
<t:saveState value="#{qulSearchBeanName.oldPageIndex}"/>
<t:saveState value="#{qulSearchBeanName.sorting}"/>
<t:saveState value="#{qulSearchBeanName.bsnSortingColumnName}"/>
<t:saveState value="#{qulSearchBeanName.usingPaging}"/>
<t:saveState value="#{qulSearchBeanName.sortedTable}"/>
<t:saveState value="#{qulSearchBeanName.usingBsnPaging}"/>
<t:saveState value="#{qulSearchBeanName.updateMyPagedDataModel}"/>
<t:saveState value="#{qulSearchBeanName.resettedPageIndex}"/>
<t:saveState value="#{qulSearchBeanName.pagingRequestDTO}"/>
<t:saveState value="#{qulSearchBeanName.pagingBean.totalListSize}"/>
<t:saveState value="#{qulSearchBeanName.pagingBean.myPagedDataModel}"/>
<t:saveState value="#{qulSearchBeanName.indexArray}"/>
<t:saveState value="#{qulSearchBeanName.filterDTO}"/>
<t:saveState value="#{qulSearchBeanName.searchDTO}"/>
<t:saveState value="#{qulSearchBeanName.pagingBean.preUpdatedDataModel}"/>
<t:saveState value="#{qulSearchBeanName.searchQualDTO}"/>
