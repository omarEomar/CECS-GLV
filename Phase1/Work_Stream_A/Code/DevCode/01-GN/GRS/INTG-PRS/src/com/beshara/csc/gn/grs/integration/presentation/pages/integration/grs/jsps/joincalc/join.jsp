<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>

<%-- ##################START################# Strat of Join Condition Section #################SHEKA############### --%>

<t:panelGroup forceId="true" id="grsJoinCalcHideDivImg" styleClass="hideDivPnl">
    <t:panelGroup onclick="toggleDivUsingMaxHeight('grsJoinCalcHideDivImg', 'grsJoinCalcSearchPnl', 'grsJoinCalcSearchResultTblPnl')"/>
</t:panelGroup>

<t:panelGroup forceId="true" id="grsJoinCalcSearchPnl">
    <t:panelGroup forceId="true" id="grsJoinCalcSearchCriteriaPnl">
        <t:panelGrid columns="4" border="0" width="100%" rowClasses="row01,row02" cellpadding="3" cellspacing="0">
        
            <t:outputLabel value="#{grsIntgResources.divInfoCalcCode}" styleClass="lable01"/>
            <t:inputText forceId="true" id="grsJoinCalcSearchCalcCode" styleClass="textbox" maxlength="6"
                    value="#{joinCalcHelperBeanName.searchCalcDTO.calcCode}"
                    onkeypress="FireGrsJoinCalcSearchBtn();" style="width:75px"
                    onkeyup="disableCharacters(this);handleGrsJoinCalcSearchBtn();"
                    onblur="setFocusOnlyOnElement('grsJoinCalcSearchCalcName');" converter="javax.faces.Long"/>            
            <t:outputLabel value="#{grsIntgResources.divInfoCalcName}" styleClass="lable01"/>
            <t:inputText forceId="true" id="grsJoinCalcSearchCalcName" styleClass="textboxmedium"
                    value="#{joinCalcHelperBeanName.searchCalcDTO.calcName}"
                    onkeypress="FireGrsJoinCalcSearchBtn();"
                    onkeyup="trimBorders(this);handleGrsJoinCalcSearchBtn();" />
            
            

        </t:panelGrid>
        <t:outputText id="codeErrMsg" forceId="true"  value="#{grsIntgResources.lineCodeFormat}" styleClass="errMsg" style="display:none;width:auto;margin-right: 5px;"/>
        <t:outputText id="codeErrMsg2" forceId="true"  value="#{globalResources.missingField}" styleClass="errMsg" style="display:none;width:auto;margin-right: 5px;"/>
    </t:panelGroup>
    
    <t:panelGrid columns="2" align="center" border="0" forceId="true" id="grsJoinCalcSearchBtnsPnl">
        <t:commandButton forceId="true" id="grsJoinCalcSearchCalcBtn" value="" styleClass="ManyToManySearchButton" type="button" onclick="if(validateSearchJoinCalc()){searchJoinCalc();}else{return false;}">
            <a4j:jsFunction name="searchJoinCalc" action="#{joinCalcHelperBeanName.search}" reRender="grsJoinCalcSearchBtnsPnl, grsJoinCalcSearchResultPnl"
                    oncomplete="document.getElementById('grsJoinCalcSearchCalcBtn').focus();"/>
        </t:commandButton>
    
        <a4j:commandButton value="" styleClass="ManyToManyCancelSearchButton" rendered="#{joinCalcHelperBeanName.searchMode}"
                action="#{joinCalcHelperBeanName.cancelSearch}" reRender="grsJoinCalcSearchPnl, grsJoinCalcSearchResultPnl"
                oncomplete="handleGrsJoinCalcSearchBtn();"/>
    </t:panelGrid>
    
</t:panelGroup>
<t:panelGroup forceId="true" id="grsJoinCalcSearchResultPnl">
    <t:panelGrid border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
        <t:panelGroup forceId="true" id="grsJoinCalcSearchResultTblPnl" styleClass="fullWidthScroll190">
            <t:dataTable id="grsJoinCalcSearchResultTbl" var="list" value="#{joinCalcHelperBeanName.myTableData}"
                    forceIdIndexFormula="#{list.code.key}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index"
                    renderedIfEmpty="false" binding="#{joinCalcHelperBeanName.myDataTable}"
                    rowOnMouseOver="javascript:addRowHighlight('myForm:grsJoinCalcSearchResultTbl');addRowHighlight('treeform:grsJoinCalcSearchResultTbl');" 
                    styleClass="grid-footer" footerClass="grid-footer" headerClass="standardTable_Header"
                    rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered"
                    width="100%" align="top" dir="#{shared_util.pageDirection}">
                    
                <t:column id="joincalc_radio_column" styleClass="standardTable_Header3" width="5%">
                    <f:facet name="header"/>
                    <t:selectOneRadio styleClass="radioButton_DataTable" id="joincalc_chk" value="#{joinCalcHelperBeanName.selectedRadio}"
                                      onmousedown="toggleRadio(this);" onkeyup="toggleRadioKeyUpVersionTwo (this,event);">
                        <f:selectItem itemLabel="" itemValue="#{list.code.key}"/>
                        <a4j:support event="onclick" actionListener="#{joinCalcHelperBeanName.selectedRadioButton}" reRender="grsJoinCalcNavSection"/>
                    </t:selectOneRadio>
                </t:column>
                <t:column id="code_column" sortable="false" width="10%">
                    <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}"/>
                    </f:facet>
                    <h:outputText id="content_code" value="#{list.code.key}"/>
                </t:column>
                <t:column id="name_column" sortable="false" width="70%">
                    <f:facet name="header">
                        <h:outputText id="header_name" value="#{globalResources.name}"/>
                    </f:facet>
                    <h:outputText id="content_name" value="#{list.name}"/>
                </t:column>
                <t:column id="detail_column" sortable="false" width="15%">
                    <f:facet name="header">
                        <h:outputText id="header_detail" value="#{grsIntgResources.calc_details}"/>
                    </f:facet>
                    <a4j:commandButton styleClass="cssButtonSmaller" id="joincalc_detail" value="#{globalResources.Available}" 
                                       actionListener="#{joinCalcHelperBeanName.viewCalculationDetails}" reRender="grsIntgerationHiddenValues"
                                       oncomplete="openGrsIntgCalculationDetailsWindow();">
                        <f:attribute name="calculationCode"
                                     value="#{joinCalcHelperBeanName.listSize == 0 ? null : list.code.key }"/>
                    </a4j:commandButton>
                </t:column>
               
            </t:dataTable>
            <t:panelGrid columns="1" rendered="#{ joinCalcHelperBeanName.listSize == 0 }" align="center">
                <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
            </t:panelGrid>
        </t:panelGroup>
    </t:panelGrid>
    <t:panelGrid id="grsJoinCalcSearchResultScrollPnl" columns="1" dir="#{shared_util.pageDirection}"
                 styleClass="scroller" width="300px" rendered="#{joinCalcHelperBeanName.listSize > shared_util.noOfTableRows}">
        <t:dataScroller id="grsJoinCalcSearchResultScroll"
                        actionListener="#{joinCalcHelperBeanName.scrollerAction}" fastStep="5"
                        pageCountVar="pageCount" pageIndexVar="pageIndex" paginator="true" paginatorMaxPages="5"
                        paginatorTableClass="scroller" fastfStyleClass="textpage" fastrStyleClass="textpage"
                        firstStyleClass="textpage" lastStyleClass="textpage" nextStyleClass="textpage"
                        previousStyleClass="textpage" paginatorColumnClass="textpage"
                        paginatorActiveColumnClass="paging"
                        paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                        styleClass="textpage" immediate="false" for="grsJoinCalcSearchResultTbl" renderFacetsIfSinglePage="false">
            <f:facet name="first">
                <t:panelGroup id="joincalc_req_list_panelGrp_first">
                    <t:graphicImage id="joincalc_req_list_img_firstOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_first}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back3.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_firstOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="last">
                <t:panelGroup id="joincalc_req_list_panelGrp_last">
                    <t:graphicImage id="joincalc_req_list_img_lastOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_last}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next3.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_lastOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="previous">
                <t:panelGroup id="joincalc_req_list_panelGrp_prv">
                    <t:graphicImage id="joincalc_req_list_img_prvOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_previous}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back1.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_prvOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="next">
                <t:panelGroup id="joincalc_req_list_panelGrp_nxt">
                    <t:graphicImage id="joincalc_req_list_img_nxtOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_next}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next1.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_nxtOff" rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastforward">
                <t:panelGroup id="joincalc_req_list_panelGrp_ffrwrd">
                    <t:graphicImage id="joincalc_req_list_img_ffrwrdOn" rendered="#{pageIndex &lt; pageCount}"
                                    title="#{globalResources.scroller_fastforward}"
                                    url="/app/media/images/#{globalResources.photoFolder}/next2.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_ffrwrdOff" onclick="return false;"
                                    rendered="#{pageIndex >= pageCount}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
            <f:facet name="fastrewind">
                <t:panelGroup id="joincalc_req_list_panelGrp_frwnd">
                    <t:graphicImage id="joincalc_req_list_img_frwndOn" rendered="#{pageIndex > 1}"
                                    title="#{globalResources.scroller_fastrewind}"
                                    url="/app/media/images/#{globalResources.photoFolder}/back2.jpg" border="0"/>
                    <t:graphicImage id="joincalc_req_list_img_frwndOff" onclick="return false;"
                                    rendered="#{pageIndex &lt;= 1}"
                                    url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg" border="0"/>
                </t:panelGroup>
            </f:facet>
        </t:dataScroller>
    </t:panelGrid>
</t:panelGroup>
<f:verbatim>
    
<script type="text/javascript">
    handleGrsJoinCalcSearchBtn();
    function handleGrsJoinCalcSearchBtn(){
        document.getElementById('grsJoinCalcSearchCalcBtn').disabled=true;
        document.getElementById('grsJoinCalcSearchCalcBtn').className = 'ManyToManySearchButton ManyToManySearchButtonDisabeled';
        var calcCode = document.getElementById("grsJoinCalcSearchCalcCode").value;
        var calcName = document.getElementById("grsJoinCalcSearchCalcName").value;
        if(calcCode.length != 0 || calcName.length != 0 ){
            document.getElementById('grsJoinCalcSearchCalcBtn').disabled=false;
            document.getElementById('grsJoinCalcSearchCalcBtn').className = 'ManyToManySearchButton';
        }
    }
    
    function FireGrsJoinCalcSearchBtn() {
        handleGrsJoinCalcSearchBtn();
        FireButtonClick('grsJoinCalcSearchCalcBtn');
    }
    
    function grsJoinCalcUpdateSelectedCondition(selectedCode){
        document.getElementById('selectedConditionCode').value = selectedCode;
        
        document.getElementById('grsJoinCalcJoinSaveBtn').disabled=false;
        document.getElementById('grsJoinCalcJoinSaveBtn').focus();
        document.getElementById('grsJoinCalcJoinSaveBtn').focus();
    }
    
    function FireGrsIntgSaveBtn(){
        FireButtonClick('grsJoinCalcJoinSaveBtn');
    }
    
    function validateSearchJoinCalc(){
        var inputVal = document.getElementById('grsJoinCalcSearchCalcCode').value;
        var errMsg = document.getElementById('codeErrMsg');
        var errMsg2 = document.getElementById('codeErrMsg2');
        errMsg.style.display = 'none';
        errMsg2.style.display = 'none';
        
        if(inputVal == null || inputVal == ''){
            return true;
        }else if(!isFloat(inputVal)){
            errMsg.style.display = 'inline';
            document.getElementById('grsJoinCalcSearchCalcCode').value = '';
            return false;
        }else{
            return true;
        }
    }
</script>

</f:verbatim>
