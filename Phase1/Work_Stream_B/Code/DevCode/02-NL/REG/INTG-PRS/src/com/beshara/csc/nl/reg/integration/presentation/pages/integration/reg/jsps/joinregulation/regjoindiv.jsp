<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<!-- Added By Yassmine  -->
<f:loadBundle basename="com.beshara.csc.nl.reg.integration.presentation.resources.integration"
              var="regResources"/>
<tiles:importAttribute scope="request"/>
<htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/integration/reg/js/integration.js"/>
<%-- Added By Yassmine, Make this bean as an attribute in the detailBeanName so i comment aliasBean line and use
     pageBeanName.regulationJoinBean directly <t:aliasBean alias="#{regulationJoinNameBean}"
     value="#{regulationJoinBean}" >--%>
<t:panelGroup id="Reg_regulationjoinPanel" forceId="true">
    <htm:center>
        <t:outputText value="#{regResources.join_reg_make_regulation}" styleClass="TitlePage"/>
    </htm:center>
    <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.intgSearchMode : pageBeanName.regulationJoinBean.intgSearchMode}"/>
    <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backBeanNameFrom : pageBeanName.regulationJoinBean.backBeanNameFrom}"/>
    <t:panelGroup>
        <t:messages showDetail="true"/>
        <%-- Added By Yassmine--%>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.okButtonMethod : pageBeanName.regulationJoinBean.okButtonMethod"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.resetButtonMethod : pageBeanName.regulationJoinBean.resetButtonMethod"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.searchStatusValue : pageBeanName.regulationJoinBean.searchStatusValue"/>
        <%-- Added By Yassmine--%>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean : pageBeanName.regulationJoinBean"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO : pageBeanName.regulationJoinBean.regulationSearchDTO}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableDetails : pageBeanName.regulationJoinBean.availableDetails}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.selectedDTOS : pageBeanName.regulationJoinBean.selectedDTOS}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.yearsList : pageBeanName.regulationJoinBean.yearsList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regTypesList : pageBeanName.regulationJoinBean.regTypesList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.decisionMakerList : pageBeanName.regulationJoinBean.decisionMakerList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.subjectList : pageBeanName.regulationJoinBean.subjectList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backMethodName : pageBeanName.regulationJoinBean.backMethodName}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.resetButtonMethod : pageBeanName.regulationJoinBean.resetButtonMethod}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.initializeBeforeSaveMethod : pageBeanName.regulationJoinBean.initializeBeforeSaveMethod}"/>
        
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.divId : pageBeanName.regulationJoinBean.divId}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableListSize : pageBeanName.regulationJoinBean.availableListSize}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.treePage : pageBeanName.regulationJoinBean.treePage}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backButtonId : pageBeanName.regulationJoinBean.backButtonId}"/>
        <%-- <t:saveState value="#{pageBeanName.dtoDetails}"/> <t:saveState value="#{pageBeanName.dto}"/>--%>
        <t:panelGroup forceId="true" id="hideDivImg" styleClass="hideDivPnl">
            <t:panelGroup onclick="toggleDivUsingCstmHeight('hideDivImg', 'searchPnl', 'Reg_dataTablePanel')"/>
        </t:panelGroup>
        <t:panelGroup id="searchPnl" forceId="true">
            <t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"
                         onkeypress="FireButtonClick('search_reg_join_div_btn');" forceId="true"
                         id="searchContentPanel">
                <h:outputText id="type" value="#{regResources.join_reg_type}" styleClass="lable01"/>
                <t:selectOneMenu forceId="true" id="Reg_regType" converter="javax.faces.Long"
                                 styleClass="DropdownboxMedium2"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.regulationType  : pageBeanName.regulationJoinBean.regulationSearchDTO.regulationType}"
                                 onblur="setFocusOnlyOnElement('issuance_year_id');"
                                 onchange="handleRegIntgSearchBtn();">
                    <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue }"
                                  itemLabel="#{regResources.join_reg_all}"/>
                    <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regTypesList : pageBeanName.regulationJoinBean.regTypesList}"
                                   itemLabel="#{regTypesList.name}" itemValue="#{regTypesList.code.regtypeCode}"
                                   var="regTypesList"/>
                    <a4j:support event="onchange"
                                 actionListener="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regTypeChanged : pageBeanName.regulationJoinBean.regTypeChanged}"
                                 reRender="decisionMakerType,Reg_regType,Reg_searchBtns_pnlgrp"/>
                </t:selectOneMenu>
                <h:outputText id="issuance_year" value="#{regResources.join_reg_issuance_year}"
                              styleClass="lable01"/>
                <t:selectOneMenu forceId="true" converter="javax.faces.Long" styleClass="DropdownboxMedium2"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.regulationYear : pageBeanName.regulationJoinBean.regulationSearchDTO.regulationYear}"
                                 id="issuance_year_id" onchange="handleRegIntgSearchBtn();">
                    <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                                  itemLabel="#{regResources.join_reg_all}"/>
                    <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.yearsList : pageBeanName.regulationJoinBean.yearsList}"
                                   itemLabel="#{entry.code.key}" itemValue="#{entry.code.yearCode}" var="entry"/>
                </t:selectOneMenu>
                <h:outputText id="regulation_number" value="#{regResources.join_reg_regulation_number}"
                              styleClass="lable01"/>
                <t:inputText forceId="true" maxlength="10" converter="javax.faces.Long" styleClass="textboxmedium"
                             id="join_reg_regulation_number"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.number : pageBeanName.regulationJoinBean.regulationSearchDTO.number}"
                             onmouseout="disableCharacters(this);"
                             onblur = "trimBorders(this);disableCharacters(this);validateNumber(this);"
                             onchange="disableCharacters(this);handleRegIntgSearchBtn();"/>
                <h:outputText id="regulation_title" value="#{regResources.join_reg_regulation_title}"
                              styleClass="lable01"/>
                <t:inputText styleClass="textboxmedium" id="join_reg_regulation_title" onkeyup="handleRegIntgSearchBtn();"
                             onkeypress="handleRegIntgSearchBtn();" onmouseout="handleRegIntgSearchBtn();" onblur="handleRegIntgSearchBtn();" onchange="handleRegIntgSearchBtn();"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.title : pageBeanName.regulationJoinBean.regulationSearchDTO.title}" forceId="true"/>
                <h:outputText id="regulation_decision_maker"
                              value="#{regResources.join_reg_regulation_decision_maker}" styleClass="lable01"/>
                <t:selectOneMenu id="decisionMakerType" forceId="true" converter="javax.faces.Long"
                                 styleClass="DropdownboxMedium2" onchange="handleRegIntgSearchBtn();"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.decisionMakerType : pageBeanName.regulationJoinBean.regulationSearchDTO.decisionMakerType}">
                    <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                                  itemLabel="#{regResources.join_reg_all}"/>
                    <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.decisionMakerList : pageBeanName.regulationJoinBean.decisionMakerList}"
                                   itemLabel="#{entry.name}" itemValue="#{entry.code.decmkrtypeCode}" var="entry"/>
                    <a4j:support event="onchange"
                                 actionListener="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.decisionMakerTypeChanged : pageBeanName.regulationJoinBean.decisionMakerTypeChanged}"
                                 reRender="Reg_regType,decisionMakerType"/>
                </t:selectOneMenu>
                <h:outputText value="#{regResources.join_reg_regulation_subject}" styleClass="lable01"/>
                <t:selectOneMenu forceId="true" id="subjectCode" converter="javax.faces.Long"
                                 styleClass="DropdownboxMedium2" onchange="handleRegIntgSearchBtn();"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.subjectCode : pageBeanName.regulationJoinBean.regulationSearchDTO.subjectCode}">
                    <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                                  itemLabel="#{regResources.join_reg_all}"/>
                    <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.subjectList : pageBeanName.regulationJoinBean.subjectList}"
                                   itemLabel="#{entry.name}" itemValue="#{entry.code.subjectCode}" var="entry"/>
                </t:selectOneMenu>
                <h:outputText id="regulation_date_from" value="#{regResources.join_reg_regulation_date_from}"
                              styleClass="lable01"/>
                <t:panelGroup forceId="true">
                    <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg"
                                     popupDateFormat="dd/MM/yyyy" forceId="true" onchange="handleRegIntgSearchBtn();"
                                     value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.dateFrom : pageBeanName.regulationJoinBean.regulationSearchDTO.dateFrom}"
                                     id="Reg_div_clndr_maintain_regDate" size="20" maxlength="200" styleClass="textbox"
                                     currentDayCellClass="currentDayCell" renderAsPopup="true"
                                     align="#{globalResources.align}" title="#{globalResources.inputCalendarHelpText}"
                                     popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                        <f:converter converterId="TimeStampConverter"/>
                    </t:inputCalendar>
                </t:panelGroup>
                <h:outputText value="#{regResources.join_reg_regulation_date_to}" styleClass="lable01"/>
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true" onchange="handleRegIntgSearchBtn();"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.dateTo : pageBeanName.regulationJoinBean.regulationSearchDTO.dateTo}"
                                 title="#{globalResources.inputCalendarHelpText}"
                                 id="Reg_div_clndr_maintain_regAppliedDate" size="20" maxlength="200"
                                 styleClass="textbox" currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" popupLeft="#{shared_util.calendarpopupLeft}"
                                 renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
            </t:panelGrid>
        </t:panelGroup>
        <%-- <t:panelGroup forceId="true" id="Reg_dataTablePanel" styleClass="divContentSmallss">--%>
        <t:panelGroup id="tablePnl" forceId="true">
            <t:panelGrid id="Reg_searchBtns_pnlgrp" align="center" columns="2" style="margin: auto;">
                <t:commandButton type="button" forceId="true" styleClass="ManyToManySearchButton"
                                 onclick="searchRegJoin_fun();" id="search_reg_join_div_btn"
                                 value="#{globalResources.SearchButton}"
                                 rendered="#{appMainLayout.manyToMany ? !detailBeanName.regulationJoinBean.intgSearchMode : !pageBeanName.regulationJoinBean.intgSearchMode}">
                    <a4j:jsFunction name="searchRegJoin_fun"
                                    action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.search : pageBeanName.regulationJoinBean.search}"
                                    reRender="searchContentPanel,Reg_regulationjoinPanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"
                                    oncomplete="setFocusOnlyOnElement('cancel_search_reg_join_div_btn');appendHiddenInput();"/>
                </t:commandButton>
                <a4j:commandButton id="cancel_search_reg_join_div_btn"
                                   action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.cancelSearch : pageBeanName.regulationJoinBean.cancelSearch}"
                                   rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.intgSearchMode : pageBeanName.regulationJoinBean.intgSearchMode}"
                                   reRender="Reg_regulationjoinPanel,searchContentPanel,Reg_dataTablePanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"
                                   styleClass="ManyToManyCancelSearchButton" value="#{globalResources.cancelsearch}" oncomplete="handleRegIntgSearchBtn();"/>
            </t:panelGrid>
            <t:panelGroup forceId="true" id="Reg_dataTablePanel" style="height:135px;display:block;overflow: auto;">
                <t:dataTable id="Reg_dataT_data3" var="list"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableDetails : pageBeanName.regulationJoinBean.availableDetails}"
                             forceIdIndexFormula="#{list.code.key}" rows="2" rowIndexVar="index" renderedIfEmpty="false"
                             binding="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableDataTable : pageBeanName.regulationJoinBean.availableDataTable}"
                             rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                             styleClass="grid-footer" headerClass="standardTable_Header"
                             rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered" width="100%"
                             align="top" dir="#{shared_util.pageDirection}" preserveSort="true">
                    <t:column id="Reg_check_column" styleClass="standardTable_Header2" width="5%">
                        <h:selectOneRadio valueChangeListener="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.selectedRadioButton : pageBeanName.regulationJoinBean.selectedRadioButton}"
                                          onmousedown="toggleRadio(this);"
                                          onkeypress="FireButtonClick('integrateregokbutton');"
                                          onclick="document.getElementById('integrateregokbutton').disabled=false;"
                                          onkeyup="toggleRadioKeyUpEnableBtn(this,'integrateregokbutton');">
                            <f:selectItem itemLabel=" " itemValue="#{list.code.key}"/>
                        </h:selectOneRadio>
                    </t:column>
                    <t:column id="Reg_code_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="Reg_header_code" value="#{regResources.join_reg_type}"/>
                        </f:facet>
                        <h:outputText id="Reg_content_code" value="#{list.typesDto.name}"/>
                    </t:column>
                    <t:column id="Reg_name_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="Reg_header_name" value="#{regResources.join_reg_issuance_year}"/>
                        </f:facet>
                        <h:outputText id="Reg_content_name" value="#{list.yearsDto.code.key}"/>
                    </t:column>
                    <t:column id="Reg_regulation_number_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="regulation_number"
                                          value="#{regResources.join_reg_regulation_number}"/>
                        </f:facet>
                        <h:outputText id="regulationNumber" value="#{list.code.regulationNumber}"/>
                    </t:column>
                    <t:column id="Reg_regulation_description_column" sortable="false" width="35%">
                        <f:facet name="header">
                            <h:outputText id="regulation_title"
                                          value="#{regResources.join_reg_regulation_title}"/>
                        </f:facet>
                        <h:outputText id="regulationTitle" value="#{list.regulationTitle}"/>
                    </t:column>
                    <t:column id="Reg_regulation_decision_maker_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="regulation_decision_maker"
                                          value="#{regResources.join_reg_regulation_decision_maker}"/>
                        </f:facet>
                        <h:outputText id="decisionMakerDTOName" value="#{list.decisionMakerDTO.name}"/>
                    </t:column>
                    <t:column id="Reg_reg_date_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="reg_date" value="#{regResources.join_reg_reg_date}"/>
                        </f:facet>
                        <h:outputText id="regulationDate" converter="TimeStampConverter"
                                      value="#{list.regulationDate}"/>
                    </t:column>
                    <t:column id="Reg_apply_date_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="apply_date" value="#{regResources.join_reg_apply_date}"/>
                        </f:facet>
                        <h:outputText id="regulationAppliedDate" converter="TimeStampConverter"
                                      value="#{list.regulationAppliedDate}"/>
                    </t:column>
                    <t:column id="Reg_details_btn_column" sortable="false" width="10%">
                        <f:facet name="header">
                            <h:outputText id="details_btn" value="#{regResources.join_reg_view_details}"/>
                        </f:facet>
                        <a4j:commandButton styleClass="cssButtonSmall" id="regulation_details" value="..."
                                           actionListener="#{regDetails.openRegDetails}"
                                           reRender="regIntgHiddenValues" oncomplete="openRegulationDivWindow('regIntgFullURLId','regIntgWindowTitleId');">
                            
                            <f:param name="regType" value="#{(list.code != null && list.code.regtypeCode != null) ? list.code.regtypeCode : '' }"/>
                            <f:param name="regYear" value="#{(list.code != null && list.code.regyearCode != null) ? list.code.regyearCode : '' }"/>
                            <f:param name="regNum" value="#{(list.code != null && list.code.regulationNumber != null) ? list.code.regulationNumber : '' }"/>
                            
                            
                        </a4j:commandButton>
                    </t:column>
                </t:dataTable>
                <t:panelGrid columns="1" width="100%"
                             rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableListSize == 0 : pageBeanName.regulationJoinBean.availableListSize == 0}"
                             align="center">
                    <t:outputText id="global_noTableResults" value="#{regResources.join_reg_noTableResults}"
                                  styleClass="msg" style="width: 515px; display: block; height: 50px;"/>
                </t:panelGrid>
            </t:panelGroup>
            <t:panelGrid columns="1" forceId="true" id="integratescrollerPanel" style="text-align:center; width: 100%;">
                <t:panelGrid id="integratepanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}"
                             styleClass="scroller" width="300px"
                             rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.availableListSize > 2 : pageBeanName.regulationJoinBean.availableListSize > 2}">
                    <t:dataScroller id="integratescroll_1add"
                                    actionListener="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.scrollerAction : pageBeanName.regulationJoinBean.scrollerAction}"
                                    fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true"
                                    paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                                    fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                                    nextStyleClass="textpage" previousStyleClass="textpage"
                                    paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                                    paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                                    styleClass="textpage" immediate="false" for="Reg_dataT_data3"
                                    renderFacetsIfSinglePage="false">
                        <f:facet name="first">
                            <t:panelGroup id="integratereq_list_panelGrp_first">
                                <t:graphicImage id="integratereq_list_img_firstOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_firstOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                        <f:facet name="last">
                            <t:panelGroup id="integratereq_list_panelGrp_last">
                                <t:graphicImage id="integratereq_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_last}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_lastOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                        <f:facet name="previous">
                            <t:panelGroup id="integratereq_list_panelGrp_prv">
                                <t:graphicImage id="integratereq_list_img_prvOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_previous}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_prvOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                        <f:facet name="next">
                            <t:panelGroup id="integratereq_list_panelGrp_nxt">
                                <t:graphicImage id="integratereq_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_next}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                        <f:facet name="fastforward">
                            <t:panelGroup id="integratereq_list_panelGrp_ffrwrd">
                                <t:graphicImage id="integratereq_list_img_ffrwrdOn"
                                                rendered="#{pageIndex &lt; pageCount}"
                                                title="#{globalResources.scroller_fastforward}"
                                                url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_ffrwrdOff" onclick="return false;"
                                                rendered="#{pageIndex >= pageCount}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                        <f:facet name="fastrewind">
                            <t:panelGroup id="integratereq_list_panelGrp_frwnd">
                                <t:graphicImage id="integratereq_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_fastrewind}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                                border="0"/>
                                <t:graphicImage id="integratereq_list_img_frwndOff" onclick="return false;"
                                                rendered="#{pageIndex &lt;= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                                border="0"/>
                            </t:panelGroup>
                        </f:facet>
                    </t:dataScroller>
                </t:panelGrid>
            </t:panelGrid>
        </t:panelGroup>
        <script type="text/javascript">
          function setFocusAtMyRegJoinDiv() {
              setFocusOnElement('search_reg_join_div_btn');
          }
        </script>
    </t:panelGroup>
    <t:panelGrid forceId="true" id="buttonspanel" columns="3" border="0" align="center" style="margin: auto;">
        <%--<t:commandButton disabled="true" forceId="true" id="integrateregokbutton" value="#{globalResources.ok}"
                         styleClass="cssButtonSmall"
                         onclick=" hidTreeDiv('add',window.blocker,window.masterDetailDiv,'successMsgTreeCorrect');"
                         action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.save : pageBeanName.regulationJoinBean.save}"/>--%>

        <t:commandButton type="button" disabled="true" forceId="true" id="integrateregokbutton" value="#{globalResources.ok}"
                         styleClass="cssButtonSmall"
                         onclick="okBtnFn();" />
        <a4j:jsFunction name="okBtnFn"
                            oncomplete="hidTreeDiv('add',window.blocker,'#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.divId : pageBeanName.regulationJoinBean.divId}','successMsgTreeCorrect');javascript:changeVisibilityMsg();"
                            action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.save : pageBeanName.regulationJoinBean.save}"
                            reRender="divMsg,Reg_regulationjoinPanel,searchContentPanel,Reg_dataTablePanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"/>

        <t:panelGroup id="backPanelGroup" forceId="true">
            <a4j:jsFunction name="backBtn"
                            oncomplete="hidTreeDiv('add',window.blocker,'#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.divId : pageBeanName.regulationJoinBean.divId}','successMsgTreeCorrect');"
                            action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.cancelSearch : pageBeanName.regulationJoinBean.cancelSearch}"
                            reRender="Reg_regulationjoinPanel,searchContentPanel,Reg_dataTablePanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"/>
        </t:panelGroup>
    </t:panelGrid>
    <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID"
                   value="Reg_div_clndr_maintain_regDate,100,160:Reg_div_clndr_maintain_regAppliedDate,100,160"/>
</t:panelGroup>
<t:panelGroup forceId="true" id="regIntgHiddenValues">
    <t:inputHidden forceId="true" id="regIntgFullURLId"  value="#{regDetails.fullURL}"/>
    <t:outputText forceId="true" id="regIntgWindowTitleId" value="#{regResources.reg_dtls_title}"
                  style="display:none;"/>
    <t:inputHidden forceId="true" id="backBtnIdValue" value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backButtonId : pageBeanName.regulationJoinBean.backButtonId}"/>
    <t:inputHidden forceId="true" id="backBtnTitleValue" value="رجوع"/>
</t:panelGroup>
<script type="text/javascript">
  function appendHiddenInput() {
      var hiddenInput = document.createElement("input");
      hiddenInput.setAttribute("id", document.getElementById("backBtnIdValue").value);
      hiddenInput.setAttribute("type", "button");
      hiddenInput.setAttribute("class", "cssButtonSmall");
      hiddenInput.setAttribute("value", document.getElementById("backBtnTitleValue").value);
      hiddenInput.setAttribute("onclick", "backBtn();");
      if (document.getElementById(document.getElementById("backBtnIdValue").value)==null) {
        document.getElementById("backPanelGroup").appendChild(hiddenInput);
      }
  }

  function handleRegIntgSearchBtn() {
      appendHiddenInput();
      document.getElementById('search_reg_join_div_btn').disabled = true;
      document.getElementById('search_reg_join_div_btn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
      var Reg_regType = document.getElementById("Reg_regType").value;
      var issuance_year_id = document.getElementById("issuance_year_id").value;
      var join_reg_regulation_number = document.getElementById("join_reg_regulation_number").value;
      var join_reg_regulation_title = document.getElementById("join_reg_regulation_title").value;
      var decisionMakerType = document.getElementById("decisionMakerType").value;
      var subjectCode = document.getElementById("subjectCode").value;
      var Reg_div_clndr_maintain_regDate = document.getElementById("Reg_div_clndr_maintain_regDate").value;
      var Reg_div_clndr_maintain_regAppliedDate = document.getElementById("Reg_div_clndr_maintain_regAppliedDate").value;
      if (Reg_regType != '-100' || issuance_year_id != '-100' || join_reg_regulation_number.length != 0 || join_reg_regulation_title.length != 0 || decisionMakerType != '-100' || subjectCode != '-100' || Reg_div_clndr_maintain_regDate.length != 0 || Reg_div_clndr_maintain_regAppliedDate.length != 0) {
          document.getElementById('search_reg_join_div_btn').disabled = false;
          document.getElementById('search_reg_join_div_btn').className = 'ManyToManySearchButton';
      }
  }

      function toggleDivUsingCstmHeight(togglerId, cnt1DivId, cntDivId) {
          var collapsedHeight;
          var displayedHeight;
          if (document.getElementById(cnt1DivId).style.display == 'none') {
              document.getElementById(cnt1DivId).style.display = 'block';
              collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
              displayedHeight = document.getElementById(cntDivId).style.height;
              document.getElementById(togglerId).className = 'hideDivPnl';
              document.getElementById(cntDivId).style.height = '135';
          }
          else {
              collapsedHeight = document.getElementById(cnt1DivId).offsetHeight;
              displayedHeight = document.getElementById(cntDivId).offsetHeight;
              document.getElementById(cnt1DivId).style.display = 'none';
              document.getElementById(togglerId).className = 'showDivPnl';
              document.getElementById(cntDivId).style.height = '245';
          }
      }
      function validateNumber(ctrl) {
     var s = ctrl.value;
     try {
        var num = parseInt(s);
        if(num <= 0) {
            ctrl.value = '';
        }
     } catch(e) {
         ctrl.value = '';
     }     
 }
</script>
<%-- </t:aliasBean>--%>
