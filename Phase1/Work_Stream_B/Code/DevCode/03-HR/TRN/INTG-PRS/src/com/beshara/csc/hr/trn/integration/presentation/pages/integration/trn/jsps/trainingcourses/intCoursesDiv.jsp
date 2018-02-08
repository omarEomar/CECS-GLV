<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="com.beshara.csc.hr.trn.integration.presentation.resources.integration_ar" var="trnResources"/>
<t:saveState value="#{trnIntegrationBeanName.courseTypeCode}" id="coursestypecodeoverriden"/>
<t:saveState value="#{trnIntegrationBeanName.coursesTypeList}" id="coursestypelistoverriden"/>
<%-- <t:saveState value="#{trnIntegrationBeanName.searchString}" id="coursestypesearchStringoverriden"/>--%>

<%--<t:saveState value="#{detailBeanName.pageIndexAdd}"/>--%>
<t:saveState value="#{trnIntegrationBeanName.renderDivComponents}"/>
<t:saveState value="#{trnIntegrationBeanName.searchTypeForAddDiv}"/>


<t:saveState value="#{trnIntegrationBeanName.intgSearchMode}"/>
<t:saveState value="#{trnIntegrationBeanName.returnMethodName}"/>
<t:saveState value="#{trnIntegrationBeanName.preOpenMethodName}"/>
<t:saveState value="#{trnIntegrationBeanName.resetIntgDivAfterClose}"/>
<t:saveState value="#{trnIntegrationBeanName.resetIntgDivAfterHide}"/>


<t:saveState  value="#{trnIntegrationBeanName.selectedDTOSList}" />
<t:saveState  value="#{trnIntegrationBeanName.availableDetails}" />
<t:saveState  value="#{detailBeanName.availableDetails}" />
<t:saveState value="#{trnIntegrationBeanName.availableListSize}"/>
<t:saveState  value="#{trnIntegrationBeanName.execludedList}" /> 
<htm:style>
    .m2mAddDivClass {left:18% !important;}
</htm:style>


<h:panelGroup id="trnIntegrationFilterPanel" forceId="true">
    <t:selectOneMenu id="coursesList" forceId="true" value="#{trnIntegrationBeanName.courseTypeCode}"
                     styleClass="DropdownboxMedium2" onchange="filterByCourseType();" style="margin-top: 5px;">
        <f:selectItem itemValue="-100"
                      itemLabel="#{trnResources.course_Type_Select_Label}"/>
        <t:selectItems value="#{trnIntegrationBeanName.coursesTypeList}" var="coursesTypeList"
                       itemValue="#{coursesTypeList.code.key}" itemLabel="#{coursesTypeList.name}"/>
        <a4j:jsFunction name="filterByCourseType" id="filterByCourseType"
                        reRender="trnIntegrationSearchPanelGrp,trnIntegration_dataTblPanel,trnIntegrationBtnPanel,trnIntegrationScrollerPanel"
                        action="#{trnIntegrationBeanName.filterByCourseType}"/>
    </t:selectOneMenu>
</h:panelGroup>

<t:panelGroup id="trnIntegrationSearchPanelGrp" forceId="true">
    <t:panelGrid  id="trnIntegrationSearchTypePanel" forceId="true" border="0" rendered="#{trnIntegrationBeanName.renderDivComponents}" align="center">
        <t:panelGrid align="center">
            <t:outputText forceId="true" id="errorMessag_lov" styleClass="errMsgNoHeight"/>
            <t:selectOneRadio forceId="true" id="trn_searchRadioBtn" value="#{trnIntegrationBeanName.searchTypeForAddDiv}"
                              styleClass="divtext" onblur="settingFoucsAtLovDiv();">
                <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
            </t:selectOneRadio>
        </t:panelGrid>
        <t:panelGrid columns="4" width="50%" id="trnIntegrationSearchPanel" forceId="true">
            <t:inputText forceId="true" id="trnIntegrationSearchText" value="#{trnIntegrationBeanName.searchString}"
                         styleClass="textboxmedium" onblur="setFocusOnlyOnElement('search_btn_many_to_amny');"
                         onkeypress="FireButtonClick('search_btn_many_to_amny');"/>
            <t:commandButton id="search_btn_many_to_amny" type="button"
                             onclick="if(validateCodeNameSrchParamter('trn_searchRadioBtn','trnIntegrationSearchText','','errorMessag_lov')){ searchM2M();}"
                             forceId="true" styleClass="ManyToManySearchButton"/>
            <a4j:jsFunction id="searchM2M" name="searchM2M" action="#{trnIntegrationBeanName.search}"
                            oncomplete="document.getElementById('trnIntegrationSearchText').focus();"
                            reRender="trnIntegration_dataTblPanel,trnIntegrationBtnPanel,trnIntegrationScrollerPanel,trnIntegrationSearchPanel,errorConsole"/>
            <a4j:commandButton action="#{trnIntegrationBeanName.filterByCourseType}"
                               reRender="trnIntegration_dataTblPanel,trnIntegrationBtnPanel,trnIntegrationScrollerPanel,trnIntegrationSearchText,trnIntegrationSearchPanel,errorConsole"
                               styleClass="ManyToManyCancelSearchButton" rendered="#{trnIntegrationBeanName.intgSearchMode}"/>
        </t:panelGrid>
    </t:panelGrid>
    <t:panelGroup forceId="true" id="trnIntegration_dataTblPanel"  styleClass="DelDivScroll" style="height: 155px;overflow:auto;">
        <t:dataTable id="trnIntegrationDataT_available" var="list" value="#{trnIntegrationBeanName.availableDetails}" renderedIfEmpty="false"
                     rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                     binding="#{trnIntegrationBeanName.availableDataTable}" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:trnIntegrationDataT_available');" footerClass="grid-footer"
                     styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2"
                     columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column"
                     width="100%" align="top" dir="#{shared_util.pageDirection}" preserveSort="false">
                       
                       
                       
                       
                  <t:column id="check_column" styleClass="standardTable_Header3" width="5%"
                      >
                <f:facet name="header">
                    <t:selectBooleanCheckbox forceId="true" id="trnIntgChk2All2" value="#{trnIntegrationBeanName.checkAllFlagAvailable}">
                        <a4j:support event="onclick" actionListener="#{trnIntegrationBeanName.selectedCheckboxsAllForIntgDiv}"
                                     oncomplete="setAllTrnIntg('trnIntgChk2All2', 'trnIntgChk2');"
                                     reRender="OperationBar,trnIntegrationBtnPanel"/>
                    </t:selectBooleanCheckbox>
                  
                </f:facet>
                
                <t:selectBooleanCheckbox forceId="true" id="trnIntgChk2" value="#{list.checked}">
    
                    <a4j:support event="onclick" actionListener="#{trnIntegrationBeanName.selectedCheckboxsForIntgDiv}"
                                 oncomplete="checkCheckAll('trnIntgChk2');"
                                 reRender="trnIntegrationBtnPanel,trnIntegration_dataTblPanel"></a4j:support>
                </t:selectBooleanCheckbox>
                
              
            </t:column>
                     
   
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">
                    <h:outputText id="header_code" value="#{globalResources.Code}" styleClass="standardTable_Header2"/>
                </f:facet>
                <h:outputText id="content_code" value="#{list.code.key}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="75%">
                <f:facet name="header">
                    <h:outputText id="header_name" value="#{globalResources.name}" styleClass="standardTable_Header2"/>
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid rendered="#{empty trnIntegrationBeanName.availableDetails && trnIntegrationBeanName.renderDivComponents}"
                     styleClass="msg" style="height:auto;width:100%">
            <h:outputText value="#{trnResources.no_courses_found}"/>
        </t:panelGrid>
    </t:panelGroup>
</t:panelGroup>
<t:panelGrid columns="1" forceId="true" id="trnIntegrationScrollerPanel" align="center">
    <h:panelGrid id="trnIntegrationPanelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller"
                 width="300px" rendered="#{trnIntegrationBeanName.availableListSize > shared_util.noOfTableRows}">
        <t:dataScroller id="trnIntegrationScroll_1add" actionListener="#{trnIntegrationBeanName.scrollerAction}"
                        binding="#{trnIntegrationBeanName.dataScroller}" fastStep="5" pageCountVar="pageCount"
                        pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5" paginatorTableClass="scroller"
                        fastfStyleClass="textpage" fastrStyleClass="textpage" firstStyleClass="textpage"
                        lastStyleClass="textpage" nextStyleClass="textpage" previousStyleClass="textpage"
                        paginatorColumnClass="textpage" paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-weight:bold" styleClass="textpage" immediate="false"
                        for="trnIntegrationDataT_available" renderFacetsIfSinglePage="false">
            <f:facet name="first">
                <h:panelGroup id="trnIntegration_list_panelGrp_first">
                    <t:graphicImage id="trnIntegration_list_img_firstOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_first}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_firstOff" onclick="return false;" rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="last">
                <h:panelGroup id="trnIntegration_list_panelGrp_last">
                    <t:graphicImage id="trnIntegration_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_last}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_lastOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="previous">
                <h:panelGroup id="trnIntegration_list_panelGrp_prv">
                    <t:graphicImage id="trnIntegration_list_img_prvOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_previous}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_prvOff" onclick="return false;" rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="next">
                <h:panelGroup id="trnIntegration_list_panelGrp_nxt">
                    <t:graphicImage id="trnIntegration_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_next}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <h:panelGroup id="trnIntegration_list_panelGrp_ffrwrd">
                    <t:graphicImage id="trnIntegration_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_fastforward}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_ffrwrdOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
                </h:panelGroup>
            </f:facet>
            <f:facet name="fastrewind">
                <h:panelGroup id="trnIntegration_list_panelGrp_frwnd">
                    <t:graphicImage id="trnIntegration_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_fastrewind}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                    <t:graphicImage id="trnIntegration_list_img_frwndOff" onclick="return false;" rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
                                    
                                     <t:inputHidden forceId="true" id="trnIntgPageIndex" value="#{pageIndex}"
                                   valueChangeListener="#{shared_util.changePageIndex}"/>
                            <t:inputHidden forceId="true" id="trnIntgNoOfTableRows" value="#{shared_util.noOfTableRows}"/>
                </h:panelGroup>
            </f:facet>
        </t:dataScroller>
    </h:panelGrid>
</t:panelGrid>





<t:panelGroup id="trnIntegrationBtnPanel" forceId="true">
                        
    <a4j:commandButton id="int_div_ok" value="#{globalResources.ok}" action="#{trnIntegrationBeanName.closeIntDiv}" 
    oncomplete="hideLookUpDiv(window.blocker,#{trnIntegrationBeanName.divId})"
    styleClass="cssButtonSmall" reRender="#{trnIntegrationBeanName.okCommandButton.reRender}" binding="#{trnIntegrationBeanName.okCommandButton}" 
    disabled="#{empty trnIntegrationBeanName.selectedDTOSList}" ></a4j:commandButton>
    
    
    <t:panelGroup>
        <t:commandButton forceId="true" id="backButtonIntegrationDiv1" type="button" styleClass="cssButtonSmall" 
        value="#{globalResources.back}" />
        <a4j:support action="#{trnIntegrationBeanName.hideIntDiv}" event="onclick" reRender="trnIntegrationSearchPanelGrp,trnIntegrationFilterPanel,trnIntegrationScrollerPanel"
        oncomplete="hideLookUpDiv(window.blocker,#{trnIntegrationBeanName.divId})"></a4j:support>
    </t:panelGroup>
                        
</t:panelGroup>


<script type="text/javascript">

  function setAllTrnIntg(checkAllId, arrayId) {
      var object;
      var pageIndex;
      var noOfTableRows;
      if (document.getElementById(checkAllId) != null)
          object = document.getElementById(checkAllId);

      if (document.getElementById(arrayId) != null)
          document.getElementById(arrayId).value = arrayId;

      if (document.getElementById('trnIntgNoOfTableRows') != null)
          noOfTableRows = document.getElementById('trnIntgNoOfTableRows').value;

      if (document.getElementById('trnIntgPageIndex') != null)
          pageIndex = document.getElementById('trnIntgPageIndex').value;

      for (i = 0;;i++) {
          id = arrayId + '[' + i + ']';

          if (document.getElementById) {
              elem = document.getElementById(id);
              if (elem == null && i != 0) {
                  break;
              }
              else if (elem == null) {
                  i = ((pageIndex - 1) * noOfTableRows) - 1;
              }
              else {
                  elem.checked = object.checked;
                  //setSelected(elem, arrayId);
              }
          }
      }
  }

  trnIntgCheckCheckAll();

  function trnIntgCheckCheckAll() {
      var arrayId = 'trnIntgChk2';
      var pageIndex;
      var noOfTableRows;
      var checkAll = 'trnIntgChk2All2';

      if (document.getElementById('trnIntgPageIndex') != null && document.getElementById('trnIntgPageIndex').value != '')
          pageIndex = document.getElementById('trnIntgPageIndex').value;

      if (document.getElementById('trnIntgNoOfTableRows') != null)
          noOfTableRows = document.getElementById('trnIntgNoOfTableRows').value;
      var startIndex = eval((pageIndex * noOfTableRows) - noOfTableRows);
      var endIndex = startIndex + (noOfTableRows - 1);
      // alert("startIndex>>"+ startIndex)
      // alert("endIndex>>"+ endIndex)
      var uncheckedOneFound = false;
      var chkElmId = '';
      var chkElm = null;
      for (var i = startIndex;i <= endIndex;i++) {
          chkElmId = arrayId + '[' + i + ']';
          if (document.getElementById(chkElmId) != null) {
              chkElm = document.getElementById(chkElmId);
              if (chkElm.checked == false) {
                  //      alert("foundnotchecked>>"+ i)
                  uncheckedOneFound = true;
                  break;
              }
          }
      }

      if (uncheckedOneFound) {
        if(document.getElementById(checkAll)!= null)
          document.getElementById(checkAll).checked = false;
          // alert("scheck all set to false")
      }
      else {
        if(document.getElementById(checkAll)!= null)
          document.getElementById(checkAll).checked = true;
          // alert("scheck all set to true")
      }
  }

  

  



  
</script>

