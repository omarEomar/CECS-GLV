<%@ page import="com.beshara.jsfbase.csc.backingbean.AppMainLayout, javax.faces.context.FacesContext"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="/webapp/htmlRender" prefix="htmlRender"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<tiles:importAttribute scope="request"/>
<t:saveState value="#{appMainLayout}"/>
<%

        AppMainLayout appMainLayout = (AppMainLayout)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{appMainLayout}").getValue(FacesContext.getCurrentInstance());
        String integration = (String)request.getAttribute("actualResourcesBundlePath");
          if(integration != null && !integration.equals("") )
          {
  %>
<f:loadBundle basename="#{actualResourcesBundlePath}" var="realPath"/>
<%}%>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
<f:loadBundle basename="#{resourcesBundlePath}" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
<jsp:include page="/WEB-INF/app/tiles/titles_scriptlet.jsp"/>
<htm:html>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/ViewStateFix.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/integration/shared/js/integration.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Uitl.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Shortcuts.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/GenericPopup.js"/>
    <!-- By I.Omar -->
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Editor/Editor.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Editor/tinymce.min.js"/>
    <!-- end -->
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Tree.js"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_event.js"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_viewport.js"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/dw_tooltip.js"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Ajax.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/keypress-1.0.9.min.js"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/A4J.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/popcalendar_ar.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/CustomComponentSearchBox.jsf"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/lovdiv.js"/>
   <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/DataTable.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/FormElementsValidation.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Div.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/TableHeader.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}#{jsPaht}"/>
    <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/m2m.js"/>
    <htm:script type="text/javascript" language="JavaScript1.2"
                src="#{shared_util.contextPath}/app/js/ViewStateFix.js"/>
  <htm:link rel="stylesheet" type="text/css" href="#{shared_util.contextPath}/app/media/css/ar/Style.css"/>
  <htm:link rel="stylesheet" type="text/css" href="#{shared_util.contextPath}#{stylePath}"/>
  <htm:link rel="shortcut icon" href="#{shared_util.contextPath}/app/media/images/favicon.ico"/>
    <htm:body onload="insureViewStateLoaded();Tooltip.init();" onmousedown="hideNavigationMenu();"
              rendered="#{utilBean.onlineUser}" dir="#{shared_util.pageDirection}" onunload="clearDivMsg();">
    <t:div forceId="true" id='blocker' style="blocker"/>
    <t:div styleClass="shadow">
    <t:div id="tree_path_part" styleClass="Pass" rendered="#{appMainLayout.showNavigationPath}">
        <h:outputLink value="#{shared_util.contextPath}/index.jsf" styleClass="link" target="_parent">
                <h:outputText value="#{!empty customModuleTitle? (empty actualResourcesBundlePath ? resourcesBundle[customModuleTitle] : realPath[customModuleTitle]) : (empty actualResourcesBundlePath ? resourcesBundle.module_div_title : realPath.module_div_title)}"/>
          </h:outputLink>
                <h:outputText value="»" escape="false"/>
          <h:outputText value="#{globalResources[titlepage] != globaltitlepage ? globalResources[titlepage] : resourcesBundle[titlepage] == globaltitlepage ? titlepage : resourcesBundle[titlepage]}" />
    </t:div>
    </t:div>
        <htm:table border="0" width="100%" cellspacing="0" cellpadding="0" align="center" styleClass="gbody"
                   style="height:100%" id="main_table">
        <htm:tr id="div_msg">
                <htm:td colspan="3">
                     <t:div id="divMsg" forceId="true"
                               styleClass="#{appMainLayout.manyToMany ? detailBeanName.divMsg : pageBeanName.divMsg}">
                                <t:commandButton id="msgcloseXX" forceId="true" type="button" 
                                            onclick="hideLookUpDiv(window.blocker,window.divMsg,null,null,null);clearDivMsg();" styleClass="closeMsg16"/>
                                <t:div styleClass="popup_body" style="border-radius: 4px;margin: 3px; padding: 5px 10px;background-color:#B5D1DE;">
                                                <tiles:insert name="msg"/>
                                </t:div>
                    </t:div>
                </htm:td>
            </htm:tr>
             
            <htm:tr id="ifram_blocker_part">
                <htm:td colspan="3">
                    <f:verbatim>
                        <iframe id='iFrame' frameborder="0" scrolling="no"
                                class="${appMainLayout.manyToMany ? detailBeanName.iframeBlock : pageBeanName.iframeBlock}"
                                src="${shared_util.contextPath}/app/jsps/shared/iframebody.jsf"></iframe>
                        <iframe id='iFrameWait' frameborder="0" scrolling="no" class="divIframeWating"></iframe>
                        <%-- @Author waleed badr, i moved this line out of the iframe--%>
                        <script type="text/javascript">
                          document.getElementById('iFrameWait').style.visibility = 'visible';
                        </script>
                    </f:verbatim>
                </htm:td>
            </htm:tr>
             
            <htm:tr id="container_of_all_div_tr">
                <htm:td width="5"></htm:td>
                <htm:td width="100%" height="100%" valign="top">
                    <htm:div styleClass="BodyContentInner">
                        <htm:table border="0" width="100%" style="height:100%" cellspacing="0" cellpadding="0"
                                   align="center" id="container_of_all_div_table">
                            
                             
                            <%-- End Content 1--%>
                             
                            <%-- ---------------henaaaaaaaaaaaaaaaaaaaaa------------------%>
                             
                            <%-- End Title part--%>
                             
                             
                             
                            <%-- Start shortcut div--%>
                             
                            <%
           if(appMainLayout.isShowshortCut())
            {
                %>
                             
                            <%-- End Tree Delete Div--%>
                             
                            <%-- The best practice to be applyed on lookup divs to add a new type named DIV contians Div
                                 title, size and style and inject it value from managed-bean.--%>
                             
                            <htm:tr id="shortcut_Div">
                                <htm:td align="center">
                                    <t:div id="shortcutDiv" forceId="true" styleClass="helpdiv">
                                        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0"
                                                   styleClass="helpdivCon">
                                            <htm:tr>
                                                <htm:td>
                                                    <htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_right.jpg"
                                                             width="43" height="24"/>
                                                </htm:td>
                                                <htm:td width="100%"
                                                        background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_mid.jpg"
                                                        bgcolor="#063E93" styleClass="helpdivtitle">
                                                    <t:outputText escape="false" forceId="true" id="screenNameOnTable"
                                                                  value="#{globalResources.shortCutDescriptionTitleOntable}"/>
                                                </htm:td>
                                                <htm:td>
                                                    <htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_left.jpg"
                                                             width="24" height="24"/>
                                                </htm:td>
                                            </htm:tr>
                                             
                                            <htm:tr>
                                                <htm:td>
                                                    <htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_title_right.jpg"
                                                             width="43" height="51"/>
                                                </htm:td>
                                                <htm:td background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_mid_right.jpg"
                                                        styleClass="helpdivheading">
                                                    <h:outputText value="#{globalResources.titleShortcutDiv}"/>
                                                </htm:td>
                                                <htm:td>
                                                    <htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_title_left.jpg"
                                                             width="24" height="51"/>
                                                </htm:td>
                                            </htm:tr>
                                             
                                            <htm:tr>
                                                <htm:td align="right" colspan="3" styleClass="helpdivbg"
                                                        background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_bg.jpg">
                                                    <htm:table align="center" width="100%" border="0" cellspacing="0"
                                                               cellpadding="0">
                                                        <htm:td valign="top" align="right" colspan="3"
                                                                styleClass="helpdivbg">
                                                            <t:outputText escape="false" forceId="true" id="screenName"
                                                                          value="#{globalResources.shortCutDescription}"/>
                                                        </htm:td>
                                                        <htm:td valign="top" align="left" styleClass="helpdivbg">
                                                            <t:outputText escape="false" forceId="true"
                                                                          id="screenNameDef"
                                                                          value="#{globalResources.shortCutDescriptionDef}"/>
                                                        </htm:td>
                                                    </htm:table>
                                                </htm:td>
                                            </htm:tr>
                                        </htm:table>
                                        <htm:table align="center" width="100%" border="0" cellspacing="0"
                                                   cellpadding="0">
                                            <htm:td align="center" colspan="3" styleClass="helpdivbg"
                                                    background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_bg.jpg">
                                                <p>
                                                    <t:commandButton id="backshortCutButton" forceId="true"
                                                                     type="button" value="#{globalResources.back}"
                                                                     onclick="hideLookUpDiv(window.blocker,window.shortcutDiv,null,null);"
                                                                     styleClass="cssButtonSmall"/>
                                                </p>
                                            </htm:td>
                                        </htm:table>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
           }
           %>
                             
                            <%-- end shortcut div--%>
                             
                            <%
                                    if(appMainLayout.isShowTreediv())
                                    {
                                        %>
                             
                            <htm:tr id="div_tree_tr">
                                                            <htm:td>
                                                                     
                                        <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.treeDivStyle : pageBeanName.treeDivStyle}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="divTree">
                                        <htm:div styleClass="popup_header">
                                                                     
                                            <t:commandButton id="divTreeclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('backButtonTreeDiv').click();"
                                                                     styleClass="close"/>
                                             
                                            <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}" rendered="#{pageBeanName.treeDivTitleKey==null}"/>
                                            <t:outputText  styleClass="popup_title" value="#{resourcesBundle[pageBeanName.treeDivTitleKey]}" rendered="#{pageBeanName.treeDivTitleKey!=null}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                            <htm:div styleClass="popup_inner_title">
                                                    <h:outputText value="#{globalResources[titleTreeDiv] == globaltitleTreeDiv ? resourcesBundle[titleTreeDiv] : globalResources[titleTreeDiv]}"/>
                                            </htm:div>        
                                            <htm:div styleClass="popup_body_contents">
                                                                            <tiles:insert attribute="treediv"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                                                     
                                                                     
                                                            </htm:td>
                                                        </htm:tr>
                                             
                            <%
                                    }
                                %>
                             
                            <%-- paged tree deiv, by MLotfy 25/6/2009--%>
                             
                            <%
                if(appMainLayout.isShowPagedTreediv()) {
            %>
                            
                            
                            <htm:tr id="paged_div_tree_tr" >
                                <htm:td >
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.divTreeAdd : pageBeanName.divTreeAdd}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="pagedDivTree">
                                        <div class="popup_header">
                                            <button type="button" class="close" onclick="hideCustomDiv();return false;">&nbsp;</button>
                                             
                                            <htm:span rendered="#{pageBeanName.treeDivTitleKey!=null}">
                                                 <t:panelGrid id="treedivTitleKey" forceId="true">
                                                        <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                                                                      rendered="#{pageBeanName.treeDivTitleKey==null}"/>
                                                        <t:outputText styleClass="popup_title" value="#{resourcesBundle[pageBeanName.treeDivTitleKey]}"
                                                                      rendered="#{pageBeanName.treeDivTitleKey!=null}"/>
                                                    </t:panelGrid>
                                            </htm:span>
                                        </div>
                                        <div class="popup_body">
                                            <div class="popup_body_contents">
                                                <tiles:insert attribute="pagedtreediv"/>
                                            </div>
                                        </div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                            
                            
                                       
                                          
                                                                       
                             
                            <%
                }
            %>
                             
                            <%-- End paged Tree Div, by MLotfy 25/6/2009--%>
                             
                            <%-- End Tree Div 07-08-2008 by sherif.omar--%>
                             
                            <%
                                  if(appMainLayout.isShowSearch())
                                    {
                                        %>
                             
                            <%-- Start Search Div--%>
                             
                            <htm:tr id="div_search">
                                <htm:td>
                                    <%--<htmlRender:renderSearchDiv title="#{pageBeanName.searchDivTitleKeyFlage ? resourcesBundle[pageBeanName.searchDivTitleKey] : globalResources[pageBeanName.searchDivTitleKey]}"
                                                                id="divSearch" forceId="true"
                                                                styleClass="#{appMainLayout.manyToMany ? detailBeanName.divSearch : pageBeanName.divSearch}"
                                                                dir="#{shared_util.pageDirection}" align="center">
                                        <tiles:insert name="search"/>
                                    </htmlRender:renderSearchDiv>--%>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.divSearch : pageBeanName.divSearch}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="divSearch" dir="#{shared_util.pageDirection}" >
                                        <htm:div styleClass="popup_header">
                                            <t:commandButton id="divSearchclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('customSearchBackBtn').click();"
                                                                     styleClass="close"/>
                                            <h:outputText styleClass="popup_title"
                                                          value="#{pageBeanName.searchDivTitleKeyFlage ? resourcesBundle[pageBeanName.searchDivTitleKey] : globalResources[pageBeanName.searchDivTitleKey]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                            <htm:div styleClass="popup_body_contents">
                                                <tiles:insert name="search"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Search Div--%>
                             
                            <%
                                    if(appMainLayout.isShowTreeAdd())
                                    {
                                        %>
                             
                            <htm:tr id="div_tree_add">
                                <htm:td align="center">
                                        <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.divTreeAdd : pageBeanName.divTreeAdd}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="divTreeAdd">
                                        <htm:div styleClass="popup_header">
                                            <t:commandButton id="addTreeCloseDiv" styleClass="close"  onclick="document.getElementById('backButtonTreeAddDiv').click();" 
											 type="button"/>
                                             
                                            <h:outputText styleClass="popup_title"
                                                          value="#{pageBeanName.addTreeDivTitleKeyFlage ? resourcesBundle[pageBeanName.addTreeDivTitleKey] : resourcesBundle[title]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                           <htm:div styleClass="popup_inner_title">
                                            <h:outputText value="#{globalResources[titleAddDiv] == globaltitleAddDiv ? resourcesBundle[titleAddDiv] : globalResources[titleAddDiv]}"/>
                                            </htm:div>
                                            <htm:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="treeadd"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Tree Add Div--%>
                             
                            <%-- Start Tree Edit Div--%>
                             
                            <%
                                    if(appMainLayout.isShowTreeEdit())
                                    {
                                        %>
                             
                            <htm:tr id="div_tree_edit">
                            <htm:td>
                                    <t:div id="divTreeEdit" forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.divTreeEdit : pageBeanName.divTreeEdit}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100">
                                        <htm:div styleClass="popup_header">
                                        
                                            <t:commandButton id="divTreeclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('backButtonTreeDiv').click();"
                                                                     styleClass="close"/>
                                             
                                            <h:outputText styleClass="popup_title"
                                                                      value="#{pageBeanName.editTreeDivTitleKeyFlage ? resourcesBundle[pageBeanName.editTreeDivTitleKey] : resourcesBundle[title]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                             <htm:div styleClass="popup_inner_title">
                                                <h:outputText value="#{globalResources[titleEditDiv] == globaltitleEditDiv ? resourcesBundle[titleEditDiv] : globalResources[titleEditDiv]}"/>
                                            </htm:div>    
                                            <htm:div styleClass="popup_body_contents">
                                     <tiles:insert attribute="treeedit"/>
                                            </htm:div>
                                        </htm:div>
                </t:div>
              </htm:td>
            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Tree Edit Div--%>
                             
                            <%-- Start Tree Delete Div--%>
                             
                            <%
                                   if(appMainLayout.isShowDelAlertTree())
                                    {
                                        %>
                             
                            <htm:tr id="div_tree_del_alert">
                                                            <htm:td>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.delAlertTree : pageBeanName.delAlertTree}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="delAlertTree">
                                        <htm:div styleClass="popup_header">
                                        
                                             <t:commandButton id="delAlertTreeclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('CancelButtonTreeDelAlertDiv').click();"
                                                                     styleClass="close"/>                         
                                             <h:outputText  styleClass="popup_title" value="#{resourcesBundle[title]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">       
                                            <htm:div styleClass="popup_body_contents">
                                                <h:outputText styleClass="msg warning" value="#{globalResources[deletealerttree] == globalDeleteAlertTree ? resourcesBundle[deletealerttree] : globalResources[deletealerttree]}"/>
                                                                            <tiles:insert attribute="delalerttree"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                                                     
                                                                        </htm:td>
                                                                    </htm:tr>
                                                                     
                            <%
                                    }
                                %>
                             
                            <%-- End Tree Delete Div--%>
                             
                            <%-- Start Lookup Add Div--%>
                             
                            <%
                                   if(appMainLayout.isShowLookupAdd())
                                    {
                                        %>
                             
                            <htm:tr id="look_up_add_div">
                                <htm:td>
                                    <%-- <htmlRender:renderAddEditDiv footer="#{appMainLayout.showLookupAddDivBorder ?
                                         '/html/addEdit/addedit_div_footer.html' :
                                         '/html/addEdit/addedit_div_footer_Without_border.html'}"
                                         header="#{appMainLayout.showLookupAddDivBorder ?
                                         '/html/addEdit/addedit_div_header.html' :
                                         '/html/addEdit/addedit_div_header_Without_border.html'}"
                                         title="#{(appMainLayout.manyToMany ? detailBeanName.addDivTitleKeyFlage :
                                         pageBeanName.addDivTitleKeyFlage) ? resourcesBundle[appMainLayout.manyToMany ?
                                         detailBeanName.addDivTitleKey : pageBeanName.addDivTitleKey] :
                                         resourcesBundle[title]}" id="lookupAddDiv" forceId="true"
                                         styleClass="#{appMainLayout.manyToMany ? detailBeanName.lookupAddDiv :
                                         pageBeanName.lookupAddDiv}" dir="#{shared_util.pageDirection}" align="center"
                                         innerTitle="#{globalResources[titleAddDiv] == globaltitleAddDiv ?
                                         resourcesBundle[titleAddDiv] : globalResources[titleAddDiv]}"> <tiles:insert
                                         attribute="lookupadd" flush="false"/> </htmlRender:renderAddEditDiv>--%>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.lookupAddDiv : pageBeanName.lookupAddDiv}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="lookupAddDiv">
                                        <htm:div styleClass="popup_header">
                                            
                                             <t:commandButton id="lookupAddDivclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('backButtonAddDiv').click();"
                                                                     styleClass="close"/>
                                            <h:outputText styleClass="popup_title"
                                                          value="#{(appMainLayout.manyToMany ? detailBeanName.addDivTitleKeyFlage : pageBeanName.addDivTitleKeyFlage) ? resourcesBundle[appMainLayout.manyToMany ? detailBeanName.addDivTitleKey : pageBeanName.addDivTitleKey] : resourcesBundle[title]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                             <htm:div styleClass="popup_inner_title">
                                                <h:outputText value="#{globalResources[titleAddDiv] == globaltitleAddDiv ? resourcesBundle[titleAddDiv] : globalResources[titleAddDiv]}"/>
                                            </htm:div>
                                            <htm:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="lookupadd" flush="false"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Lookup Add Div--%>
                             
                            <%-- Start Lookup Edit Div--%>
                             
                            <%
                                   if(appMainLayout.isShowLookupEdit())
                                    {
                                        %>
                             
                            <htm:tr id="look_up_edit_div">
                                <htm:td align="center">
                                    <%-- <htmlRender:renderAddEditDiv footer="#{appMainLayout.showLookupEditDivBorder ?
                                         '/html/addEdit/addedit_div_footer.html' :
                                         '/html/addEdit/addedit_div_footer_Without_border.html'}"
                                         header="#{appMainLayout.showLookupEditDivBorder ?
                                         '/html/addEdit/addedit_div_header.html' :
                                         '/html/addEdit/addedit_div_header_Without_border.html'}"
                                         title="#{(appMainLayout.manyToMany ? detailBeanName.editDivTitleKeyFlage :
                                         pageBeanName.editDivTitleKeyFlage) ? resourcesBundle[appMainLayout.manyToMany ?
                                         detailBeanName.editDivTitleKey : pageBeanName.editDivTitleKey] :
                                         resourcesBundle[title]}" id="lookupEditDiv" forceId="true"
                                         styleClass="#{appMainLayout.manyToMany ? detailBeanName.lookupEditDiv :
                                         pageBeanName.lookupEditDiv}" dir="#{shared_util.pageDirection}" align="center"
                                         innerTitle="#{globalResources[titleEditDiv] == globaltitleEditDiv ?
                                         resourcesBundle[titleEditDiv] : globalResources[titleEditDiv]}"> <tiles:insert
                                         attribute="lookupedit"/> </htmlRender:renderAddEditDiv>--%>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.lookupEditDiv : pageBeanName.lookupEditDiv}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="lookupEditDiv">
                                        <htm:div styleClass="popup_header">
                                            
                                            <t:commandButton id="lookupEditDivclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('CancelButtonEdit').click();"
                                                                     styleClass="close"/>
                                            <h:outputText styleClass="popup_title"
                                                          value="#{(appMainLayout.manyToMany ? detailBeanName.editDivTitleKeyFlage : pageBeanName.editDivTitleKeyFlage) ? resourcesBundle[appMainLayout.manyToMany ? detailBeanName.editDivTitleKey : pageBeanName.editDivTitleKey] : resourcesBundle[title]}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                             <htm:div styleClass="popup_inner_title">
                                                <h:outputText value="#{globalResources[titleEditDiv] == globaltitleEditDiv ? resourcesBundle[titleEditDiv] : globalResources[titleEditDiv]}"/>
                                            </htm:div>    
                                            <htm:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="lookupedit" flush="false"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Lookup Edit Div--%>
                             
                            <%-- Start Lov Div--%>
                             
                            <%
                                   if(appMainLayout.isShowLovDiv())
                                    {
                                        %>
                             
                            <jsp:include page="/WEB-INF/app/tiles/lovdivpage.jsp"/>
                             
                            <%
                                    }
                                %>
                                
                                
                             
                            <%-- End Lov Div--%>
                            
                            <%
                                   if(appMainLayout.isShowSearchTreeDiv())
                                    {
                                        %>
                             
                            <htm:tr id="searchTree">
                                <htm:td>
                                    <t:div forceId="true" styleClass="#{appMainLayout.manyToMany ? detailBeanName.lovDiv : pageBeanName.lovDiv}"
                                           id="searchTreeDiv">
                                        <t:div styleClass="popup_header">
                                            <t:commandButton id="divLov2closeXX" forceId="true" type="button" 
                                                            onclick="document.getElementById('lov2_cancel').click();" styleClass="close"/>
                                            <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                                                          rendered="#{pageBeanName.lovDivTitleKey==null}"/>
                                             
                                            <t:outputText styleClass="popup_title" value="#{resourcesBundle[pageBeanName.lovDivTitleKey]}"
                                                          rendered="#{pageBeanName.lovDivTitleKey!=null}"/>
                                        </t:div>
                                        <t:div styleClass="popup_body">
                                            <h:outputText value="#{globalResources[titleLovDiv] == globaltitleLovDiv ? resourcesBundle[titleLovDiv] : globalResources[titleLovDiv]}"/>
                                            <t:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="searchTreeDiv"/>
                                            </t:div>
                                        </t:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- Start empl LOV Div--%>
                             
                            <%
                                   if(appMainLayout.isShowEmpSrchDiv())
                                    {
                                        %>
                             
                            <jsp:include page="/WEB-INF/app/tiles/emplcondiv.jsp"/>
                             
                            <%
                                    }
                                %>
                             
                            <%
                                   if(appMainLayout.isShowEmpSrchDivPaging())
                                    {
                                        %>
                             
                            <jsp:include page="/WEB-INF/app/tiles/emplcondivpaging.jsp"/>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Lov Div--%>
                             
                            <%-- Start Delete Alert Div--%>
                             
                            <%
                                   if(appMainLayout.isShowDelAlert())
                                    {
                                        %>
                             
                            <htm:tr id="look_up_del-alert_div">
                                <htm:td align="center">
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.delAlert : pageBeanName.delAlert}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="delAlert">
                                        <t:div styleClass="popup_header">
                                            <t:commandButton id="delalertcloseXX" forceId="true" type="button" 
                                                onclick="document.getElementById('CancelButtonDelAlertDiv').click();return false;" styleClass="close"/> 
                                            <h:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                                                          rendered="#{pageBeanName.deleteDivTitleKey==null}"/><t:outputText styleClass="popup_title"
                                                                                                                                        value="#{resourcesBundle[pageBeanName.deleteDivTitleKey]}"
                                                                                                                                        rendered="#{pageBeanName.deleteDivTitleKey!=null}"/>
                                        </t:div>
                                        <t:div styleClass="popup_body">
                                            <t:div styleClass="popup_body_contents">
                                            <h:outputText styleClass="msg warning" value="#{globalResources[titleDelDiv] == globaltitleDelDiv ? resourcesBundle[titleDelDiv] : globalResources[titleDelDiv]}"/>
                                               <tiles:insert attribute="delalert"/>
                                            </t:div>
                                        </t:div>
                                    </t:div>                                  
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Delete Alert Div--%>
                             
                            <%-- Start Delete Confirmation Div--%>
                             
                            <%
                                 if(appMainLayout.isShowDelConfirm())
                                    {
                                        %>
                             
                            <htm:tr id="look_up_del_confirm_div">
                                <htm:td>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.delConfirm : pageBeanName.delConfirm}"
                                           style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                                           id="delConfirm">
                                        <t:div styleClass="popup_header">
                                             <t:commandButton id="delconfirmcloseXX" forceId="true" type="button" 
                                                    onclick="document.getElementById('CancelButtonDelConfirmDiv').click();return false;" styleClass="close"/>
                                            <h:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"/>
                                    </t:div>
                                        <t:div styleClass="popup_body">
                                              <htm:div styleClass="popup_inner_title">
                                                <h:outputText styleClass="successMsg msg" value="#{globalResources[titleDelConDiv] == globaltitleDelConDiv ? resourcesBundle[titleDelConDiv] : globalResources[titleDelConDiv]}"/>
                                            </htm:div>    
                                            <t:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="delconfirm"/>
                                            </t:div>
                                        </t:div>
                                    </t:div>         
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            <%-- End Delete Confirmation Div--%>
                             
                             <htm:tr id="content1_container">
                                <htm:td align="center">
                                    <htm:table border="0" width="99%" id="table5" cellspacing="0" cellpadding="0"  style="#{(appMainLayout.showWizardBar or appMainLayout.showsteps)?'min-height: 475px;display: block;':''}">
                                        
                                        <%-- Start Title part--%>
                             
                                                        <%
                                          if(!appMainLayout.isShowbar())
                                             {
                                          %>
                                                         
                                                        <htm:tr id="title_page">
                                                            <htm:td valign="middle" align="center" colspan="2">
                                                                <%-- h:outputText value="#{appMainLayout.manyToMany ? detailBeanName.titlePage :
                                                                     pageBeanName.titlePage}" /--%>
                                                                 <htm:div styleClass="pageTitle" >
                                                                <h:outputText value="#{globalResources[titlepage] != globaltitlepage ? globalResources[titlepage] : resourcesBundle[titlepage] == globaltitlepage ? titlepage : resourcesBundle[titlepage]}"
                                                                              rendered="#{!appMainLayout.showbar}"/>
                                                                 </htm:div>
                                                                <%-- h:outputText value="#{resourcesBundle[titlepage]}"/--%>
                                                            </htm:td>
                                                        </htm:tr>
                                                         
                                                        <%
                                             }
                                        %>
                                        <%-- Start Bar--%>
                                         
                                        <%
                                                if(appMainLayout.isShowbar())
                                                {
                                                    %>
                                         
                                        <htm:tr id="opration_bar">
                                            <htm:td align="center" colspan="2">
                                                <tiles:insert name="bar"/>
                                            </htm:td>
                                        </htm:tr>
                                         
                                        <%
                                                }
                                            %>
                                         
                                        <%-- End Bar--%>
                                        
                                        <htm:tr>
                                        
                                        <%-- Start Steps part--%>
                                        <%
                                              if(appMainLayout.isShowsteps())
                                                {
                                                    %>
                                            <htm:td valign="top" id="steps" styleClass="TitlePage" style="padding-top: 25px;" width="210">
                                                <tiles:insert name="steps"/>
                                            </htm:td>
                                        <%
                                                }
                                              else if(appMainLayout.isShowWizardBar())
                                                {
                                        %>
                                            <htm:td valign="top" id="wizard_steps" styleClass="TitlePage" style="padding-top: 25px;">
                                                <tiles:insert name="wizardsteps"/>
                                            </htm:td>
                                            
                                        <%
                                                }else{
                                        %>
                                            <htm:td valign="top" id="emptyTD" style="width:0px;">
                                            </htm:td>
                                            
                                        <%
                                                }
                                        %>
                                            <htm:td valign="top" align="center">
                                                <htm:table border="0" width="100%" cellspacing="0" cellpadding="0">
                                                
                                                <%
                                                if(appMainLayout.isShowContent1()){
                                                %>
                                                    <htm:tr><htm:td>
                                                        <htm:div id="content1Div"
                                                                 styleClass="#{appMainLayout.manyToMany ? detailBeanName.content1Div : pageBeanName.content1Div}">
                                                            <tiles:insert attribute="content1"/>
                                                        </htm:div>
                                                    </htm:td></htm:tr>
                                                <%
                                                }
                                                %>
                                                
                                                <%
                                                if(appMainLayout.isShowdatatableContent()){
                                                %>
                                 
                                                    <htm:tr id="main_content_container">
                                                        <%-- h:outputLabel value="#{(appMainLayout.showContent1 &&
                                                             appMainLayout.showdatatableContent) ? '99%' : '814'}" /--%>
                                                        <htm:td width="#{(appMainLayout.showContent1 && appMainLayout.showdatatableContent) ? '99%' : '100%'}"
                                                                align="center">
                                                            <t:div id="showdatatableContent_showContent1_content" forceId="true" styleClass="#{appMainLayout.manyToMany ? detailBeanName.contentDiv : pageBeanName.contentDiv}">
                                                                <tiles:insert attribute="content"/>
                                                            </t:div>
                                                        </htm:td>
                                                    </htm:tr>
                                                    <%-- End showdatatableContent and content 1--%>
                                                <%
                                                }
                                                %>
                                                <%
                                                if(appMainLayout.isShowpaging())
                                                {
                                                %>
                                                    <htm:tr id="paging_part">
                                                        <htm:td width="100%" align="center">
                                                            <tiles:insert name="paging"/>
                                                        </htm:td>
                                                    </htm:tr>
                                                <%
                                                        }
                                                %>
                                                </htm:table>
                                            </htm:td>
                                        </htm:tr>
                                    </htm:table>
                                </htm:td>
                            </htm:tr>
                             
                             
                            <%-- Start Content 1--%>
                             
                            
                             
                            <!-- Start twoVerticalCntpage by Ashraf Gaber-->
                             
                            <%
            if(appMainLayout.isShowTwoVerticalCnt())
            {
            %>
                             
                            <jsp:include page="/WEB-INF/app/tiles/twoVerticalCntpage.jsp"/>
                             
                            <%
            }
            %>
                             
                            <%-- Start showtreeContent--%>
                             
                            <%
                                    if(appMainLayout.isShowtreeContent())
                                    {
                                        %>
                             
                            <htm:tr id="tree_content_container">
              <htm:td align="center">
                <htm:table border="0" width="99%" cellspacing="0" cellpadding="0">
                  <htm:tr>
                    <htm:td valign="top">
                      <htm:div styleClass="#{appMainLayout.manyToMany ? detailBeanName.divMainContent : pageBeanName.divMainContent}">
                        <tiles:insert attribute="content"/>
                      </htm:div>
                    </htm:td>
                  </htm:tr>
                </htm:table>
              </htm:td>
            </htm:tr>
                             
            <%
                                    }
                                %>
                             
                            <%-- End showtreeContent--%>
                             
                            
                             
                            <%-- Start showTreeDivDetails--%>
                             
                            <%-- Start Dive To show Tree Div Details added by Ahmed Abd El-Fatah--%>
                             
                            <%
                                   if(appMainLayout.isShowTreeDivDetails())
                                    {
                                        %>
                             
                            <htm:tr id="tree_div_details">
                                <htm:td width="810" align="center">
                                    <tiles:insert name="treedivdetails"/>
                                </htm:td>
                            </htm:tr>
                             
                            <%
                                    }
                                %>
                             
                            
                             
                            <%-- End paging--%>
                             
                            <%-- Start showMasterDetail--%>
                             
                            <%
                               if(appMainLayout.isShowMasterDetail())
                                    {
                                        %>
                             
                            <htm:tr id="master_detail_div">
                                <htm:td>
                                    <t:div forceId="true"
                                           styleClass="#{appMainLayout.manyToMany ? detailBeanName.masterDetailDiv : pageBeanName.masterDetailDiv}"
                                           id="masterDetailDiv" style="width:70%;position:absolute;top:70px;right:15%;z-index:100;">
                                        <t:div styleClass="popup_header">
                                            <t:commandButton id="masterdetaildivcloseXX" forceId="true" type="button" 
                                                    onclick="document.getElementById('BackButtonMasterDetailDiv').click();" styleClass="close"/>
                                            <h:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                                                          rendered="#{pageBeanName.masterDeailTitleKey==null}"/><t:outputText styleClass="popup_title"
                                                                                                                                          value="#{resourcesBundle[pageBeanName.masterDeailTitleKey]}"
                                                                                                                                          rendered="#{pageBeanName.masterDeailTitleKey!=null}"/>
                                        </t:div>
                                        <t:div styleClass="popup_body">
                                            <t:div styleClass="popup_body_contents">
                                                 <tiles:insert attribute="masterdetaildiv"/>
                                            </t:div>
                                        </t:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%-- End showMasterDetail--%>
                             
                            <%
                                    }
                                %>
                             
                            <%
                                    if(appMainLayout.isShowNavigation())
                                    {
                                        %>
                             
                            <%-- Start showNavigation--%>
                             
                            <htm:tr>
                                <htm:td width="100%" align="center" height="4px"></htm:td>
                            </htm:tr>
                             
                            <htm:tr id="navigation_part">
                                <htm:td width="100%" align="center">
                                    <t:div id="navigationDiv" style="visibility:visible;" forceId="true">
                                        <tiles:insert name="navigation"/>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%-- End showNavigation--%>
                             
                            <%
                                    }
                                %>
                             
                            <%
                                    if(appMainLayout.isShowWizardBar())
                                    {
                                        %>
                             
                            <%-- Start showNavigation--%>
                             
                            <htm:tr>
                                <htm:td width="100%" align="center" height="4px"></htm:td>
                            </htm:tr>
                             
                            <htm:tr id="wizard_buttons_part">
                                <htm:td width="100%" align="center">
                                    <t:div id="wizardButtonsDiv" style="visibility: visible; position: absolute; display: block; width: 94%; bottom: 4px;" forceId="true">
                                        <tiles:insert name="wizardbuttons"/>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
                             
                            <%-- End showNavigation--%>
                             
                            <%
                            }
                            %>
                           <!-- Edited by Ashraf Gaber 18/3/2011, HR-1226 -->
                           
                           <%-- Start showCustomDiv1--%>
                            <%
                            if(appMainLayout.isShowCustomDiv1()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/customdiv1page.jsp"/>
                            <%
                            }
                            %><%-- End showCustomDiv1--%>
                             
                            <%-- Start showCustomDiv2--%>                             
                            <%
                            if(appMainLayout.isShowCustomDiv2()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/customdiv2page.jsp"/>
                            <%
                            }
                            %><%-- End showCustomDiv2--%>
                             
                            <%-- Start showCustomDiv3--%>                             
                            <%
                            if(appMainLayout.isShowCustomDiv3()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/customdiv3page.jsp"/>
                            <%
                            }
                            %><%-- End showCustomDiv3--%>
                            
                            <%-- Start showIntegrationDiv1  by Islam Omar 01-07-2014 --%>                             
                            <%
                            if(appMainLayout.isShowIntegrationDiv1()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/integrationdiv1.jsp"/>
                            <%
                            }
                            %>
                            <%-- End showIntegrationDiv1--%>
                            
                            <%-- Start showIntegrationDiv2--%>                             
                            <%
                            if(appMainLayout.isShowIntegrationDiv2()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/integrationdiv2.jsp"/>
                            <%
                            }
                            %>
                            <%-- End showIntegrationDiv2--%>
                            
                            <%-- Start conditionActivationIntegrationDiv  by Noran Osama 23-04-2015 --%>                             
                            <%
                            if(appMainLayout.isConditionActivationIntgDiv()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/conditionActivationIntgDiv.jsp"/>
                            <%
                            }
                            %>
                            <%-- End conditionActivationIntegrationDiv--%>
                            
                             <%-- Start showCustomDiv2--%>                             
                            <%
                            if(appMainLayout.isShowRetroactiveSettDetailsDiv()){
                            %>
                                <jsp:include page="/WEB-INF/app/tiles/retroactivesettdetailsdivpage.jsp"/>
                            <%
                            }
                            %><%-- End showCustomDiv2--%>
            
                            <htm:tr>
                                <htm:td width="100%" align="center" height="100%"></htm:td>
                            </htm:tr>
                        </htm:table>
                    </htm:div>
                </htm:td>
                <htm:td width="5"></htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td align="center" colspan="3">
                    <tiles:insert name="wait"/>
                </htm:td>
            </htm:tr>
             
            <htm:tr>
                <htm:td align="center" colspan="3">
                    <tiles:insert name="divwait"/>
                </htm:td>
            </htm:tr>
        </htm:table>
        <%-- Start Common Data--%>
         
        <%

 if(appMainLayout.isShowCommonData())
                                    {
                                        %>
         
        <tiles:insert name="commondata"/>
         
        <%
                                    }

                                %>
         
        <%

 if(appMainLayout.isShowWizardBar())
                                    {
                                        %>
         
        <tiles:insert name="wizardsavestate"/>
         
        <%
                                    }

                                %>
         
        <%-- End Common Data--%>
         
        <jsp:include page="/app/js/globalJSvar.jsp"/>
         
        <%-- show client validation script gernrator--%>
         
        <%
                                if(appMainLayout.isShowScirptGenerator())
                                    {
                                        %>
         
        <t:panelGroup forceId="true" id="scriptGenerator">
            <c2:scriptGenerator form="myForm"/>
        </t:panelGroup>
         
        <%                              }
                                %>
         
        <%-- -end script generator ----%>
         
        <!-- by Ashraf Gaber just to reRender the jsCaller -->
         
        <!-- by Ashraf Gaber just to reRender the jsCaller -->
         
    <t:panelGroup forceId="true" id="javaScriptCallerOutputText">
        <htm:script>
            <h:outputText value="#{!appMainLayout.manyToMany ? pageBeanName.javaScriptCaller: pageBeanName.javaScriptCaller == null ? detailBeanName.javaScriptCaller : pageBeanName.javaScriptCaller }"/>
        </htm:script>
    </t:panelGroup>
    <f:verbatim>
        <!-- by Ashraf Gaber just to disable form autocomplete (cached data) -->
        <script type="text/javascript"> 
            function turnOffFormAutoComplete(){
                try{
                    for (f=0; f < document.forms.length; f++){
                        document.forms[f].autocomplete = 'off';
                    }
                }catch(exception) {}
            }
            turnOffFormAutoComplete();
            
            /* ctxPath variable used by openReportWindow jsfunction , to build report complete path
            *  added by TechnicalTeam[Ali Agamy] 
            *  13/10/2015
            *  CSC-14760 
            */
            var ctxPath = '<%=request.getContextPath()%>';
        </script>
    </f:verbatim>
  </htm:body>
</htm:html>
