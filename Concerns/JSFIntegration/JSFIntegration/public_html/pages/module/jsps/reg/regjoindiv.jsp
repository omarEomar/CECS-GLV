<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>.divSearch { left: 80px !important; position: absolute !important; top: 30px !important; width: 700px
           !important; } .divSearchContent1Fixed { display: block !important; height: 185px !important; margin-bottom:
           5px !important; overflow: auto; width: 100%; }</htm:style>
<!-- Added By Yassmine  -->
<f:loadBundle basename="com.beshara.csc.integration.presentation.resources.integrate" var="integrateResources"/>
<tiles:importAttribute scope="request"/>
<%-- Added By Yassmine, Make this bean as an attribute in the detailBeanName so i comment aliasBean line and use
     pageBeanName.regulationJoinBean directly <t:aliasBean alias="#{regulationJoinNameBean}"
     value="#{regulationJoinBean}" >--%>
<t:panelGroup id="Reg_regulationjoinPanel" forceId="true">
    <htm:center>
        <t:outputText value="#{integrateResources.make_regulation}" styleClass="TitlePage"
                      rendered="#{appMainLayout.manyToMany ? !detailBeanName.regulationJoinBean.showDetailDiv : !pageBeanName.regulationJoinBean.showDetailDiv}"/>
        <t:outputText value="#{integrateResources.VIEW_RELATED_REGULATIONS}" styleClass="TitlePage"
                      rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.showDetailDiv : pageBeanName.regulationJoinBean.showDetailDiv}"/>
    </htm:center>
    <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.showDetailDiv : pageBeanName.regulationJoinBean.showDetailDiv}"/>
    <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backBeanNameFrom : pageBeanName.regulationJoinBean.backBeanNameFrom}"/>
    <t:panelGroup rendered="#{appMainLayout.manyToMany ? !detailBeanName.regulationJoinBean.showDetailDiv : !pageBeanName.regulationJoinBean.showDetailDiv}">
        <t:messages showDetail="true"/>
        <%-- Added By Yassmine--%>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.okButtonMethod : pageBeanName.regulationJoinBean.okButtonMethod"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.resetButtonMethod : pageBeanName.regulationJoinBean.resetButtonMethod"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.searchStatusValue : pageBeanName.regulationJoinBean.searchStatusValue"/>
        <%-- Added By Yassmine--%>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean : pageBeanName.regulationJoinBean"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO : pageBeanName.regulationJoinBean.regulationSearchDTO}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.myTableData : pageBeanName.regulationJoinBean.myTableData}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.selectedDTOS : pageBeanName.regulationJoinBean.selectedDTOS}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.yearsList : pageBeanName.regulationJoinBean.yearsList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regTypesList : pageBeanName.regulationJoinBean.regTypesList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.decisionMakerList : pageBeanName.regulationJoinBean.decisionMakerList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.subjectList : pageBeanName.regulationJoinBean.subjectList}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.searchMode : pageBeanName.regulationJoinBean.searchMode}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backMethodName : pageBeanName.regulationJoinBean.backMethodName}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.resetButtonMethod : pageBeanName.regulationJoinBean.resetButtonMethod}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.initializeBeforeSaveMethod : pageBeanName.regulationJoinBean.initializeBeforeSaveMethod}"/>
        <%-- <t:saveState value="#{pageBeanName.dtoDetails}"/> <t:saveState value="#{pageBeanName.dto}"/>--%>
        <t:panelGrid columns="4" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0"
                     rendered="#{appMainLayout.manyToMany ? !detailBeanName.regulationJoinBean.searchMode : !pageBeanName.regulationJoinBean.searchMode}"
                     onkeypress="FireButtonClick('search_reg_join_div_btn');" forceId="true" id="searchContentPanel">
            <h:outputText id="type" value="#{integrateResources.type}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="Reg_regType" converter="javax.faces.Long"
                             styleClass="DropdownboxMedium2"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.regulationType  : pageBeanName.regulationJoinBean.regulationSearchDTO.regulationType}"
                             onblur="setFocusOnlyOnElement('issuance_year_id');">
                <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue }"
                              itemLabel="#{integrateResources.select}"/>
                <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regTypesList : pageBeanName.regulationJoinBean.regTypesList}"
                               itemLabel="#{regTypesList.name}" itemValue="#{regTypesList.code.regtypeCode}"
                               var="regTypesList"/>
            </t:selectOneMenu>
            <h:outputText id="issuance_year" value="#{integrateResources.issuance_year}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" converter="javax.faces.Long" styleClass="DropdownboxMedium2"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.regulationYear : pageBeanName.regulationJoinBean.regulationSearchDTO.regulationYear}"
                             id="issuance_year_id">
                <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                              itemLabel="#{integrateResources.select}"/>
                <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.yearsList : pageBeanName.regulationJoinBean.yearsList}"
                               itemLabel="#{entry.code.key}" itemValue="#{entry.code.yearCode}" var="entry"/>
            </t:selectOneMenu>
            <h:outputText id="regulation_number" value="#{integrateResources.regulation_number}" styleClass="lable01"/>
            <t:inputText forceId="true" maxlength="10" converter="javax.faces.Long" styleClass="textboxmedium"
                         value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.number : pageBeanName.regulationJoinBean.regulationSearchDTO.number}"
                         onkeyup="disableCharacters(this);" />
            <h:outputText id="regulation_title" value="#{integrateResources.regulation_title}" styleClass="lable01"/>
            <t:inputText styleClass="textboxmedium"
                         value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.title : pageBeanName.regulationJoinBean.regulationSearchDTO.title}"/>
            <h:outputText id="regulation_decision_maker" value="#{integrateResources.regulation_decision_maker}"
                          styleClass="lable01"/>
            <t:selectOneMenu id="decisionMakerType" forceId="true" converter="javax.faces.Long"
                             styleClass="DropdownboxMedium2"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.decisionMakerType : pageBeanName.regulationJoinBean.regulationSearchDTO.decisionMakerType}">
                <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                              itemLabel="#{integrateResources.select}"/>
                <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.decisionMakerList : pageBeanName.regulationJoinBean.decisionMakerList}"
                               itemLabel="#{entry.name}" itemValue="#{entry.code.decmkrtypeCode}" var="entry"/>
            </t:selectOneMenu>
            <h:outputText value="#{integrateResources.regulation_subject}" styleClass="lable01"/>
            <t:selectOneMenu forceId="true" id="subjectCode" converter="javax.faces.Long"
                             styleClass="DropdownboxMedium2"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.subjectCode : pageBeanName.regulationJoinBean.regulationSearchDTO.subjectCode}">
                <f:selectItem itemValue="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.virtualLongValue : pageBeanName.regulationJoinBean.virtualLongValue}"
                              itemLabel="#{integrateResources.select}"/>
                <t:selectItems value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.subjectList : pageBeanName.regulationJoinBean.subjectList}"
                               itemLabel="#{entry.name}" itemValue="#{entry.code.subjectCode}" var="entry"/>
            </t:selectOneMenu>
            <h:outputText id="regulation_date_from" value="#{integrateResources.regulation_date_from}"
                          styleClass="lable01"/>
            <t:panelGroup forceId="true">
                <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                                 forceId="true"
                                 value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.dateFrom : pageBeanName.regulationJoinBean.regulationSearchDTO.dateFrom}"
                                 id="Reg_div_clndr_maintain_regDate" size="20" maxlength="200" styleClass="textbox"
                                 currentDayCellClass="currentDayCell" renderAsPopup="true"
                                 align="#{globalResources.align}" title="#{globalResources.inputCalendarHelpText}"
                                 popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                    <f:converter converterId="TimeStampConverter"/>
                </t:inputCalendar>
            </t:panelGroup>
            <h:outputText value="#{integrateResources.regulation_date_to}" styleClass="lable01"/>
            <t:inputCalendar popupButtonImageUrl="/app/media/images/icon_calendar.jpg" popupDateFormat="dd/MM/yyyy"
                             forceId="true"
                             value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationSearchDTO.dateTo : pageBeanName.regulationJoinBean.regulationSearchDTO.dateTo}"
                             title="#{globalResources.inputCalendarHelpText}" id="Reg_div_clndr_maintain_regAppliedDate"
                             size="20" maxlength="200" styleClass="textbox" currentDayCellClass="currentDayCell"
                             renderAsPopup="true" align="#{globalResources.align}"
                             popupLeft="#{shared_util.calendarpopupLeft}" renderPopupButtonAsImage="true">
                <f:converter converterId="TimeStampConverter"/>
            </t:inputCalendar>
        </t:panelGrid>
        <t:panelGrid id="Reg_searchBtns_pnlgrp" align="center" columns="2">
            <t:commandButton type="button" forceId="true" styleClass="cssButtonSmall" onclick="searchRegJoin_fun();"
                             id="search_reg_join_div_btn" value="#{globalResources.SearchButton}">
                <a4j:jsFunction name="searchRegJoin_fun"
                                action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.search : pageBeanName.regulationJoinBean.search}"
                                reRender="searchContentPanel,Reg_regulationjoinPanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"
                                oncomplete="setFocusOnlyOnElement('search_reg_join_div_btn');"/>
            </t:commandButton>
            <a4j:commandButton oncomplete="document.getElementById('integrateregokbutton').disabled=true;"
                               action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.cancelSearch : pageBeanName.regulationJoinBean.cancelSearch}"
                               rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.searchMode : pageBeanName.regulationJoinBean.searchMode}"
                               reRender="Reg_regulationjoinPanel,searchContentPanel,Reg_dataTablePanel,integratescrollerPanel,Reg_searchBtns_pnlgrp"
                               styleClass="cssButtonSmall" value="#{globalResources.cancelsearch}"/>
        </t:panelGrid>
        <%-- <t:panelGroup forceId="true" id="Reg_dataTablePanel" styleClass="divContentSmallss">--%>
        <t:panelGroup forceId="true" id="Reg_dataTablePanel" styleClass="divSearchContent1Fixed"
                      rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.searchMode : pageBeanName.regulationJoinBean.searchMode}">
            <t:dataTable id="Reg_dataT_data3" var="list"
                         value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.myTableData : pageBeanName.regulationJoinBean.myTableData}"
                         rowStyleClass="#{appMainLayout.manyToMany ? (detailBeanName.regulationJoinBean.selected ? 'standardTable_RowHighlighted' : null) : (pageBeanName.regulationJoinBean.selected ? 'standardTable_RowHighlighted' : null)}"
                         forceIdIndexFormula="#{list.code.key}" rows="2" rowIndexVar="index" renderedIfEmpty="false"
                         binding="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.myDataTable : pageBeanName.regulationJoinBean.myDataTable}"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered"
                         width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="true"
                         sortColumn="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.sortColumn : pageBeanName.regulationJoinBean.sortColumn}"
                         sortAscending="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.ascending : pageBeanName.regulationJoinBean.ascending}">
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
                        <h:outputText id="Reg_header_code" value="#{integrateResources.type}"/>
                    </f:facet>
                    <h:outputText id="Reg_content_code" value="#{list.typesDto.name}"/>
                </t:column>
                <t:column id="Reg_name_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="Reg_header_name" value="#{integrateResources.reg_year}"/>
                    </f:facet>
                    <h:outputText id="Reg_content_name" value="#{list.yearsDto.code.key}"/>
                </t:column>
                <t:column id="Reg_regulation_number_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regulation_number" value="#{integrateResources.regulation_number}"/>
                    </f:facet>
                    <h:outputText id="regulationNumber" value="#{list.code.regulationNumber}"/>
                </t:column>
                <t:column id="Reg_regulation_description_column" sortable="false" width="35%">
                    <f:facet name="header">
                        <h:outputText id="regulation_title" value="#{integrateResources.regulation_title}"/>
                    </f:facet>
                    <h:outputText id="regulationTitle" value="#{list.regulationTitle}"/>
                </t:column>
                <t:column id="Reg_regulation_decision_maker_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regulation_decision_maker"
                                      value="#{integrateResources.regulation_decision_maker}"/>
                    </f:facet>
                    <h:outputText id="decisionMakerDTOName" value="#{list.decisionMakerDTO.name}"/>
                </t:column>
                <t:column id="Reg_reg_date_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="reg_date" value="#{integrateResources.reg_date}"/>
                    </f:facet>
                    <h:outputText id="regulationDate" converter="TimeStampConverter" value="#{list.regulationDate}"/>
                </t:column>
                <t:column id="Reg_apply_date_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="apply_date" value="#{integrateResources.apply_date}"/>
                    </f:facet>
                    <h:outputText id="regulationAppliedDate" converter="TimeStampConverter"
                                  value="#{list.regulationAppliedDate}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1"
                         rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.listSize == 0 : pageBeanName.regulationJoinBean.listSize == 0}"
                         align="center">
                <t:outputText id="global_noTableResults" value="#{globalResources.global_noTableResults}"
                              styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
        <t:panelGrid columns="1" forceId="true" id="integratescrollerPanel" style="text-align:center; width: 100%;">
            <t:panelGrid id="integratepanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}"
                         styleClass="scroller" width="300px"
                         rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.listSize > 2 : pageBeanName.regulationJoinBean.listSize > 2}">
                <t:dataScroller id="integratescroll_1add"
                                actionListener="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.scrollerAction : pageBeanName.regulationJoinBean.scrollerAction}"
                                fastStep="5" pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true"
                                paginatorMaxPages="5" paginatorTableClass="scroller" fastfStyleClass="textpage"
                                fastrStyleClass="textpage" firstStyleClass="textpage" lastStyleClass="textpage"
                                nextStyleClass="textpage" previousStyleClass="textpage" paginatorColumnClass="textpage"
                                paginatorActiveColumnClass="paging"
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
                            <t:graphicImage id="integratereq_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
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
        <%-- t:panelGrid forceId="true" id="buttonspanel" columns="3" border="0" align="center"> <t:commandButton
             disabled="true" forceId="true" id="integrateregokbutton" value="#{globalResources.ok}"
             styleClass="cssButtonSmall" onclick="
             hidTreeDiv('add',window.blocker,window.masterDetailDiv,'successMsgTreeCorrect');"
             action="#{pageBeanName.regulationJoinBean.save}"/> <t:panelGroup> <t:commandButton forceId="true"
             id="BackButtonMasterDetailDiv" type="button" onblur="setFocusAtMyRegJoinDiv();"
             onclick="document.getElementById('integrateregokbutton').disabled=true; BackButtonMasterDetailDivJs();"
             value="#{globalResources.back}" styleClass="cssButtonSmall"/> <a4j:jsFunction
             name="BackButtonMasterDetailDivJs"
             oncomplete="hidTreeDiv('add',window.blocker,window.masterDetailDiv,'successMsgTreeCorrect');"
             action="#{pageBeanName.regulationJoinBean.cancelSearch}" reRender="Reg_regulationjoinPanel"/>
             </t:panelGroup> </t:panelGrid--%>
        <script type="text/javascript">
          function setFocusAtMyRegJoinDiv() {
              setFocusOnElement('search_reg_join_div_btn');
          }
        </script>
    </t:panelGroup>
    <!-- this part display regulations related to specific record -->
    <t:panelGroup rendered="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.showDetailDiv : pageBeanName.regulationJoinBean.showDetailDiv}">
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationDataList : pageBeanName.regulationJoinBean.regulationDataList}"/>
        <t:panelGroup forceId="true" id="Reg_dataTableDetailPanel" styleClass="divViewRelatedReg">
            <t:dataTable id="Reg_dataT_dataDetail" var="list"
                         value="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.regulationDataList : pageBeanName.regulationJoinBean.regulationDataList}"
                         forceIdIndexFormula="#{list.code.key}" rowIndexVar="index" renderedIfEmpty="false"
                         rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer"
                         styleClass="grid-footer" headerClass="standardTable_Header"
                         rowClasses="standardTable_Row1,standardTable_Row2"
                         columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                         width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="false">
                <t:column id="Reg_code_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="Reg_header_Detail_code" value="#{integrateResources.type}"/>
                    </f:facet>
                    <h:outputText id="Reg_content_Detail_code" value="#{list.typesDto.name}"/>
                </t:column>
                <t:column id="Reg_name_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="Reg_header_Detail_name" value="#{integrateResources.reg_year}"/>
                    </f:facet>
                    <h:outputText id="Reg_content_Detail_name" value="#{list.yearsDto.code.key}"/>
                </t:column>
                <t:column id="Reg_regulation_number_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regNumberHeader" value="#{integrateResources.regulation_number}"/>
                    </f:facet>
                    <h:outputText id="regNum" value="#{list.code.regulationNumber}"/>
                </t:column>
                <t:column id="Reg_regulation_description_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regTitle"  value="#{integrateResources.regulation_title}"/>
                    </f:facet>
                    <h:outputText  id="regTitleContent" value="#{list.regulationTitle}"/>
                </t:column>
                <t:column id="Reg_regulation_decision_maker_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regDecMake" value="#{integrateResources.regulation_decision_maker}"/>
                    </f:facet>
                    <h:outputText id="regDecMakeContent" value="#{list.decisionMakerDTO.name}"/>
                </t:column>
                <t:column id="Reg_reg_date_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regDate" value="#{integrateResources.reg_date}"/>
                    </f:facet>
                    <h:outputText id="regDateContent" converter="TimeStampConverter" value="#{list.regulationDate}"/>
                </t:column>
                <t:column id="Reg_apply_date_Detail_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="regAppDate"  value="#{integrateResources.apply_date}"/>
                    </f:facet>
                    <h:outputText id="regAppDate2"  converter="TimeStampConverter" value="#{list.regulationAppliedDate}"/>
                </t:column>
            </t:dataTable>
            <t:panelGrid columns="1"
                         rendered="#{appMainLayout.manyToMany ? empty detailBeanName.regulationJoinBean.regulationDataList : empty pageBeanName.regulationJoinBean.regulationDataList}"
                         align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
        <%-- t:panelGrid forceId="true" id="buttonspanel_detail" columns="3" border="0" align="center"> <t:commandButton
             forceId="true" id="BackButtonMasterDetailDiv_detail" type="button"
             onblur="setFocusOnlyOnElement('BackButtonMasterDetailDiv_detail');"
             onclick="hideLookUpDiv(window.blocker,window.masterDetailDiv,null,null,null);BackButtonMasterDetailDivJs();"
             value="#{globalResources.back}" styleClass="cssButtonSmall" > <a4j:jsFunction
             name="BackButtonMasterDetailDivJs" action="#{pageBeanName.regulationJoinBean.backFromDisplayDiv}"
             reRender="Reg_regulationjoinPanel"/> </t:commandButton> </t:panelGrid--%>
    </t:panelGroup>
    <t:panelGrid forceId="true" id="buttonspanel" columns="3" border="0" align="center">
        <t:commandButton disabled="true" forceId="true" id="integrateregokbutton" value="#{globalResources.ok}"
                         styleClass="cssButtonSmall"
                         onclick=" hidTreeDiv('add',window.blocker,window.masterDetailDiv,'successMsgTreeCorrect');"
                         action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.save : pageBeanName.regulationJoinBean.save}"
                         rendered="#{appMainLayout.manyToMany ? !detailBeanName.regulationJoinBean.showDetailDiv : !pageBeanName.regulationJoinBean.showDetailDiv}"/>
        <t:panelGroup>
            <t:commandButton forceId="true" id="BackButtonMasterDetailDiv" type="button"
                             onblur="backButtonOnblur('#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.showDetailDiv : pageBeanName.regulationJoinBean.showDetailDiv}');"
                             onclick="backButtonOnclick('#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.showDetailDiv : pageBeanName.regulationJoinBean.showDetailDiv}');"
                             value="#{globalResources.back}" styleClass="cssButtonSmall"/>
            <a4j:jsFunction name="BackButtonMasterDetailDivJs"
                            oncomplete="hidTreeDiv('add',window.blocker,window.masterDetailDiv,'successMsgTreeCorrect');"
                            action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.cancelAdd : pageBeanName.regulationJoinBean.cancelAdd}"
                            reRender="Reg_regulationjoinPanel"/>
            <a4j:jsFunction name="BackButtonMasterDetailDisplayDivJs"
                            action="#{appMainLayout.manyToMany ? detailBeanName.regulationJoinBean.backFromDisplayDiv : pageBeanName.regulationJoinBean.backFromDisplayDiv}"
                            reRender="Reg_regulationjoinPanel,OperationBar"/>
        </t:panelGroup>
    </t:panelGrid>
</t:panelGroup>
<script type="text/javascript">
  function backButtonOnclick(detailDiv) {
      if (detailDiv) {
          hideLookUpDiv(window.blocker, window.masterDetailDiv, null, null, null);
          BackButtonMasterDetailDisplayDivJs();
      }
      else {
          document.getElementById('integrateregokbutton').disabled = true;
          BackButtonMasterDetailDivJs();
      }
  }

      function backButtonOnblur(detailDiv) {
          if (detailDiv) {
              setFocusOnlyOnElement('BackButtonMasterDetailDiv');
          }
          else {
              setFocusAtMyRegJoinDiv();
          }
      }
</script>
<%-- </t:aliasBean>--%>
