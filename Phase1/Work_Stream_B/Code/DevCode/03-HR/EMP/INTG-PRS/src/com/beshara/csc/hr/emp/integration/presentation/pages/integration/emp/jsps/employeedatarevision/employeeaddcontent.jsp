<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>

<t:outputText value="" forceId="true" id="errorConsole" styleClass="errMsg" />
<t:panelGrid  border="0" cellpadding="0" cellspacing="0" width="100%">
 <t:panelGroup forceId="true" id="divAdds" styleClass="delDivScroll" style="width:100%" >
            <t:saveState value="#{employeesAddBean.tempCodeKeyStr}" id="tempCodeKey"/>
                <t:dataTable id="dataT_available" var="list" value="#{detailBeanName.availableDetails}" rows="#{shared_util.noOfTableRows}" rowIndexVar="index" 
                                binding="#{detailBeanName.availableDataTable}" renderedIfEmpty="false" rowOnMouseOver="javascript:addRowHighlight('myForm:dataT_available');"
                                footerClass="grid-footer" styleClass="grid-footer" headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                                columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_Column" width="100%" align="center"
                                dir="#{shared_util.pageDirection}" preserveSort="true" >
                                
                    <t:column id="check_column" styleClass="standardTable_Header3" width="5%" rendered="#{!detailBeanName.singleSelection}">
                        <f:facet name="header">
                            <t:selectBooleanCheckbox forceId="true" id="checkAllAdd" value="#{detailBeanName.checkAllFlagAvailable}">
                                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailableAll}"  oncomplete="setAllAdd('checkAllAdd', 'chk2Add');" reRender="selectedAvailableListSize,buttonPanel"/>
                            </t:selectBooleanCheckbox>
                        </f:facet>
                        
                        <t:selectBooleanCheckbox forceId="true" id="chk2Add"  value="#{list.checked}">
                            <a4j:support event="onclick" actionListener="#{detailBeanName.selectedAvailable}" oncomplete="checkCheckAllAdd('chk2Add')" reRender="selectedAvailableListSize,buttonPanel" />
                        </t:selectBooleanCheckbox>
                        </t:column>
                        
                        <t:column id="pnlgrd_dec_radio"  styleClass="standardTable_Header3" width="5%" rendered="#{detailBeanName.singleSelection}">
                  
                    <t:selectOneRadio  id="chk" value="#{detailBeanName.tempCodeKeyStr}"   onmousedown="toggleRadio(this);"  >
                    <f:selectItem    itemLabel=" " itemValue="#{list.code.key}"  />
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedRadioButton}"  />
                </t:selectOneRadio>
                    
                </t:column>
                        
                        
                        <t:column id="code_columnCode" sortable="false" width="20%" >
                            <f:facet name="header">                           
                                   <t:outputText id="header_code" value="#{resourcesBundle.employees_civilId}" styleClass="standardTable_Header3"/>                     
                            </f:facet>
                          <h:outputText id="content_code" value="#{list.code.key}"/>
                        </t:column>
                        <t:column id="name_columnFullName" sortable="false" width="75%">
                            <f:facet name="header">                            
                                   <t:outputText id="header_name" value="#{resourcesBundle.app_empEvaluatorsDTO_employeesDTO_citizensResidentsDTO_fullName}" styleClass="standardTable_Header3"/>                          
                            </f:facet>
                            <h:outputText id="content_name" value="#{list.citizensResidentsDTO.fullName}"/>
                        </t:column>
                    </t:dataTable>
             <t:panelGrid rendered="#{detailBeanName.availableListSize==0}" styleClass="msg" align="center">
             <h:outputText value="#{globalResources.global_noTableResults}"/>
         </t:panelGrid>
         
</t:panelGroup>
  </t:panelGrid>   

<t:panelGrid columns="1" forceId="true" id="scrollerPanel">
  <h:panelGrid id="panelGrd_scrolleradd" columns="1" dir="#{shared_util.pageDirection}" styleClass="scroller" width="300px" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}">
            
          <t:dataScroller id="scroll_1add"   binding="#{pageBeanName.dataScroller}"
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
                    paginatorActiveColumnStyle="font-weight:bold"
                    styleClass="textpage"
                    immediate="false"
                    for="dataT_available"
                    renderFacetsIfSinglePage="false">
                <f:facet name="first" >                            
                    <h:panelGroup id="req_list_panelGrp_first">
                        <t:graphicImage id="req_list_img_firstOn"
                                                rendered="#{pageIndex > 1}"
                                                title="#{globalResources.scroller_first}"
                                                url="/app/media/images/#{globalResources.photoFolder}/back3.jpg"
                                                border="0"/>
                        <t:graphicImage id="req_list_img_firstOff"
                                                onclick="return false;"
                                                rendered="#{pageIndex <= 1}"
                                                url="/app/media/images/#{globalResources.photoFolder}/dis-back3.jpg"
                                                border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">                            
                    <h:panelGroup id="req_list_panelGrp_last">
                            <t:graphicImage id="req_list_img_lastOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_last}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next3.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_lastOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next3.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">                            
                    <h:panelGroup id="req_list_panelGrp_prv">
                            <t:graphicImage id="req_list_img_prvOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_previous}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_prvOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">                            
                    <h:panelGroup id="req_list_panelGrp_nxt">
                            <t:graphicImage id="req_list_img_nxtOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_next}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next1.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_nxtOff"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next1.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="req_list_panelGrp_ffrwrd">
                            <t:graphicImage id="req_list_img_ffrwrdOn"
                                            rendered="#{pageIndex < pageCount}"
                                            title="#{globalResources.scroller_fastforward}"
                                            url="/app/media/images/#{globalResources.photoFolder}/next2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_ffrwrdOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex >= pageCount}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-next2.jpg"
                                            border="0"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="req_list_panelGrp_frwnd">
                            <t:graphicImage id="req_list_img_frwndOn"
                                            rendered="#{pageIndex > 1}"
                                            title="#{globalResources.scroller_fastrewind}"
                                            url="/app/media/images/#{globalResources.photoFolder}/back2.jpg"
                                            border="0"/>
                            <t:graphicImage id="req_list_img_frwndOff"
                                            onclick="return false;"
                                            rendered="#{pageIndex <= 1}"
                                            url="/app/media/images/#{globalResources.photoFolder}/dis-back2.jpg"
                                            border="0"/>
                            
                    </h:panelGroup>
                    
                </f:facet>
                
              
            </t:dataScroller>
        </h:panelGrid>
        
       
        
        </t:panelGrid>
     
<t:panelGrid columns="3" forceId="true" id="buttonPanel">
<h:commandButton id="ok" value="#{globalResources.ok}" action="#{detailBeanName.addEmployees}" styleClass="cssButtonSmall"  rendered="#{detailBeanName.availableListSize > 0}" /> <f:verbatim>&nbsp; </f:verbatim>
<t:commandButton id="cancel" value="#{globalResources.back}" styleClass="cssButtonSmall"  action="#{detailBeanName.backFromSearch}" />
</t:panelGrid>

<t:panelGroup>
    
    <t:inputHidden forceId="true" id="pageIndexAdd" value="#{pageBeanName.pageIndexAdd}" rendered="#{detailBeanName.availableListSize > shared_util.noOfTableRows}" />
 <t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/>                
 <t:inputHidden forceId="true" id="arrayIdAdd" value=""/>
 <t:inputHidden value="#{detailBeanName.availableListSize}" forceId="true" id="listSizeAdd"/> 
 <t:inputHidden value="#{detailBeanName.selectedAvailableListSize}" forceId="true" id="selectedAvailableListSize"/> 
 </t:panelGroup>