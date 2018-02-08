<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<f:verbatim>
    <script>
        function open_Div()
        {
            if(document.getElementById("opendiv") != null)
            {
                if(document.getElementById("opendiv").value == "1")
                {
                    changeVisibilityDiv(window.blocker,window.customDiv1);
                    setFocusOnlyOnElement('lov_searchText_req');
                }
            }
        }
        function selectRadio(radio)
        {
            var id = radio.name.substring(radio.name.lastIndexOf(':'));
            var elems =  document.myForm.elements;
            var curRadio;
            for(var i=0; i<elems.length; i++) {
                if(elems[i].type == "radio") {
                    if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == id) {
                        elems[i].checked = false;
                    }
                }
            }
            radio.checked = true;
//            var listId = document.getElementById("itemId").value 
            /*for(var i=0; i<elems.length; i++) {
                if(elems[i].name != null){    
                    if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == ":"+listId) {
                        elems[i].value= radio.value;
                    }
                }
            }*/
            //alert(document.getElementById(listId));
            if(document.getElementById('listBoxValueId') !=null)
                 document.getElementById('listBoxValueId').value = radio.value;
            //alert(document.getElementById("myForm:groupObject:0:nonRelatedFieldsList:0:nonRelatedFieldsList:1:"+listId));
            //document.getElementById("myForm:groupObject:0:nonRelatedFieldsList:0:nonRelatedFieldsList:1:"+listId).value= radio.value;
        }
        
        function setListBoxNewValue(){
            var listId = document.getElementById("itemId").value ;
            if(document.getElementById('listBoxValueId') !=null)
            document.getElementById(listId).value =document.getElementById('listBoxValueId').value;
        }
    </script>
</f:verbatim>



 <htm:table>
        <htm:tr>
            <htm:td align="center">
                        <htm:table border="0" id="listdivSearchFrame" cellspacing="0" cellpadding="0" style="width:95%">
                <htm:tr>
                    <htm:td valign="bottom" width="19"><htm:img border="0" src="${shared_util.contextPath}/app/media/images/R-top.gif" width="19" height="16"/></htm:td>
                    <htm:td valign="bottom" style="background-repeat: repeat-x; background-position-y: bottom" styleClass="gtopbox"></htm:td>
                    <htm:td valign="bottom" width="12"><htm:img border="0" src="${shared_util.contextPath}/app/media/images/L-top.gif" width="12" height="16"/></htm:td>
                </htm:tr>
                
                <htm:tr>
                    <htm:td valign="top" style="background-image: url('${shared_util.contextPath}/app/media/images/g-r.gif'); background-repeat: repeat-y" styleClass="grightbox"></htm:td>
                    <htm:td valign="top" bgcolor="#FFFFFF" styleClass="paddingbox" align="center">
                        

                            <t:panelGroup style="width:50%" forceId="true" id="searchPanelPart_req">
                            <t:panelGrid align="center">
                                    <t:selectOneRadio forceId="true" id="lov_searchRadioBtn_req"  styleClass="divtext" value="#{pageBeanName.searchTyp}" >
                                     <f:selectItem itemLabel="#{globalResources.Code}" itemValue="0"/>
                                     <f:selectItem itemLabel="#{globalResources.name}" itemValue="1"/>
                                    </t:selectOneRadio>
                             </t:panelGrid> 
                                    
                            <t:panelGrid align="center" columns="3" width="90%" forceId="true" id="lov_searchPanel_req">
                            
                             <t:panelGroup>
                                    <t:inputText forceId="true" id="lov_searchText_req"  styleClass="textboxmedium"  value="#{pageBeanName.searchQuery}" onkeypress="FireButtonClick('lov_search_btn_req');"/>
                                    <t:commandButton  type="button" id="lov_search_btn_req" forceId="true"  styleClass="ManyToManySearchButton" onclick="lov_search();"  />
                                    <a4j:jsFunction name="lov_search" id="lov_search_req" oncomplete="setFocusOnlyOnElement('lov_searchText_req');" action="#{pageBeanName.searchDivList}"  reRender="dataT_data_panel,lov_searchPanel_req,errorMessag_lov,panelGrd_scroller,lovDiv_btnsPnlGrd"  />
                             </t:panelGroup>
                             <a4j:commandButton id="lov_cancelSearch_req" oncomplete="settingFoucsAtLovDiv();" rendered="#{pageBeanName.searchModeDivList}" action="#{pageBeanName.cancelSearchDivList}" reRender="lov_searchText_req,dataT_data_panel,lov_searchPanel_req,errorMessag_lov,panelGrd_scroller,lov_searchRadioBtn_req,lovDiv_btnsPnlGrd"  styleClass="ManyToManyCancelSearchButton"  />
                            
                             </t:panelGrid>
                            </t:panelGroup> 

               </htm:td>
                <htm:td valign="top" style="background-repeat: repeat-y" styleClass="gleftbox"></htm:td>
              </htm:tr>
              
                <htm:tr>
                    <htm:td valign="top" width="19">
                            <htm:img border="0" src="${shared_util.contextPath}/app/media/images/R-bottom.gif" width="19" height="11"/>
                    </htm:td>
                    <htm:td valign="top" style="background-repeat: repeat-x" styleClass="gbottombox"></htm:td>
                    <htm:td valign="top">
                        <htm:img border="0" src="${shared_util.contextPath}/app/media/images/L-bottom.gif" width="12" height="11"/>
                    </htm:td>
                </htm:tr>
                    
                </htm:table>
                
            </htm:td>
            </htm:tr>
            </htm:table>


<t:panelGrid border="0" cellpadding="0" cellspacing="0"  forceId="true" id="dataT_data_panel" style="width:95%;" >
   
    
      
        
    <t:panelGroup>
    
     
        
    <t:panelGroup  styleClass="delDivScroll" style="width:100%;">
    
        <t:saveState value="#{pageBeanName.listDiv}"/>
        <t:saveState value="#{pageBeanName.originListDiv}"/>
        <t:saveState value="#{pageBeanName.searchQuery}"/>
        <t:saveState value="#{pageBeanName.searchTyp}"/>
        <t:saveState value="#{pageBeanName.searchModeDivList}"/>
        <t:saveState value="#{pageBeanName.radioCode}"/>
    
     
        
        <t:dataTable id="dataT_data" var="list" value="#{pageBeanName.listDiv}" binding="#{pageBeanName.myDataTable}" 
                      rows="#{shared_util.noOfTableRows}" rowIndexVar="index" renderedIfEmpty="false" 
                     rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_data');" footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header"
                     rowClasses="standardTable_Row1,standardTable_Row2" columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="top"
                     dir="#{shared_util.pageDirection}" >
            <t:column id="check_column" styleClass="standardTable_Header3" width="5%">
                <f:facet name="header">
                    <t:outputText value=""/>
                </f:facet>
                <h:selectOneRadio id="list_radio" value="#{pageBeanName.radioCode}"  onclick="selectRadio(this);document.getElementById('okButton_div').disabled=false;setFocusOnlyOnElement('okButton_div');" onkeypress="FireButtonClick('okButton_div');">
                    <f:selectItem itemLabel="" itemValue="#{list.code}" />
                </h:selectOneRadio>
            </t:column>
            <t:column id="code_column" sortable="false" width="20%">
                <f:facet name="header">
                        <h:outputText id="header_code" value="#{globalResources.Code}" styleClass="standardTable_Header2"/>
                    
                </f:facet>
                <h:outputText id="content_code" value="#{list.code}"/>
            </t:column>
            <t:column id="name_column" sortable="false" width="75%">
                <f:facet name="header">
                    
                        <h:outputText id="header_name" value="#{globalResources.name}" styleClass="standardTable_Header2"/>
                    
                </f:facet>
                <h:outputText id="content_name" value="#{list.name}"/>
            </t:column>
        </t:dataTable>
        <t:panelGrid columns="1" rendered="#{empty pageBeanName.listDiv}" align="center" width="10%" style="width:10%;text-align: center;">
            <t:outputText value="#{globalResources.global_noTableResults}" styleClass="msg"/>
        </t:panelGrid>
        
        
        </t:panelGroup>
        
         <t:panelGrid id="panelGrd_scroller" align="center" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="150px" style="width:200px;text-align: center;">
            
          <t:dataScroller id="scroll_1" actionListener="#{pageBeanName.scrollerAction}" 
                    fastStep="5" pageCountVar="pageCount" 
                    pageIndexVar="pageIndex"
                     paginator="true"
                    paginatorMaxPages="5"
                    paginatorTableClass="scroller"
                    fastfStyleClass="textpage"
                    fastrStyleClass="textpage"
                    firstStyleClass="textpage"
                    lastStyleClass="textpage"
                    nextStyleClass="textpage"
                    previousStyleClass="textpage"
                    paginatorColumnClass="textpage"
                    paginatorActiveColumnClass="paging"
                    paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                    styleClass="textpage"
                    immediate="false"
                    for="dataT_data"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <h:panelGroup id="jobs_list_panelGrp_first">
                        <t:graphicImage id="jobs_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="jobs_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="jobs_list_panelGrp_last">
                            <t:graphicImage id="jobs_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="jobs_list_panelGrp_prv">
                            <t:graphicImage id="jobs_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="jobs_list_panelGrp_nxt">
                            <t:graphicImage id="jobs_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="jobs_list_panelGrp_ffrwrd">
                            <t:graphicImage id="jobs_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="jobs_list_panelGrp_frwnd">
                            <t:graphicImage id="jobs_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="jobs_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
            </t:dataScroller>
        </t:panelGrid>
        
       
        <t:panelGrid columns="2" align="center" style="width:10%;text-align: center;" border="0" >
            <%--<h:outputText value="#{globalResources.global_noTableResults}" styleClass="msg" />--%>
            <t:commandButton action="#{pageBeanName.scrollerActionEmpty}" value="#{globalResources.ok}" forceId="true" id="okButton_div" onclick="setListBoxNewValue();hideLookUpDiv(window.blocker,window.customDiv1);" styleClass="cssButtonSmall" disabled="true"/>
            <t:commandButton forceId="true" id="lov_cancel_req" onblur="setFocusOnlyOnElement('okButton_div');" type="button" onclick="hideLookUpDiv(window.blocker,window.customDiv1);return false;" styleClass="cssButtonSmall" value="#{globalResources.back}"/>

           
        </t:panelGrid>
        
        <t:saveState value="#{pageBeanName.listBoxCode}"/>
        <t:saveState value="#{pageBeanName.javaScriptCaller}"/>
    
        <t:inputHidden value="#{pageBeanName.opendiv}" id="opendiv" forceId="true"/>
        <t:inputHidden value="#{pageBeanName.listBoxCode}" forceId="true" id="itemId"/>
        <t:inputHidden  forceId="true" id="listBoxValueId"/>
    </t:panelGroup>
</t:panelGrid>
