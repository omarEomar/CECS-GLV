
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:view>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global_ar" var="globalResources"/>


  <htm:html>
  <htm:link rel="stylesheet" type="text/css" href="#{shared_util.contextPath}/app/media/css/ar/Style.css"/>
  <htm:script type="text/javascript" language="JavaScript1.2" src="#{shared_util.contextPath}/app/js/Shortcuts.js"/>
    <f:verbatim>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <title>${globalResources.shortCutDescriptionTitleOntable}</title>
    </head>
    </f:verbatim>
    
    <htm:body dir="#{shared_util.pageDirection}">
        <h:form>
            <t:div forceId="true" id="shortCutDivPage" style="visibility:visible;">        
            <htm:table border="0" width="100%" cellspacing="0" cellpadding="0" align="center" >
                <htm:tr id="shortcut_Div" >
                   <htm:td align="center"> 
                     <t:div id="shortcutDiv" forceId="true" styleClass="helpdivShortcut">
                        <htm:table width="100%" border="0" cellspacing="0" cellpadding="0" styleClass="helpdivCon" >
                <htm:tr>
                  <htm:td><htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_right.jpg" width="43" height="24" /></htm:td>
                  <htm:td width="100%" background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_mid.jpg" bgcolor="#063E93" styleClass="helpdivtitle">
                     <t:outputText escape="false" forceId="true" id="screenNameOnTable" value="#{globalResources.shortCutDescriptionTitleOntable}"/>
                     </htm:td>
                  <htm:td><htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_left.jpg" width="24" height="24" /></htm:td>
                </htm:tr>
                <htm:tr>
                  <htm:td><htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_title_right.jpg" width="43" height="51" /></htm:td>
                  <htm:td background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_mid_right.jpg"  styleClass="helpdivheading"> 
                         <h:outputText value="#{globalResources.titleShortcutDiv}"/>
                  </htm:td>
                  <htm:td><htm:img src="#{shared_util.contextPath}/app/media/images/shortCutImages/help_title_left.jpg" width="24" height="51" /></htm:td>
                </htm:tr>
              
                <htm:tr>
                  <htm:td  align="right" colspan="3" styleClass="helpdivbg" background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_bg.jpg"> 
                    <htm:table   align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
                     <htm:td valign="top"  align="right" colspan="3" styleClass="helpdivbg"> 
                       <t:outputText escape="false" forceId="true" id="screenName" value="#{globalResources.shortCutDescription}"/>
                     </htm:td>
                     <htm:td  valign="top"  align="left" styleClass="helpdivbg" > 
                          <t:outputText  escape="false" forceId="true" id="screenNameDef" value="#{globalResources.shortCutDescriptionDef}" />
                     </htm:td>  
                   </htm:table>
                  </htm:td>     
                </htm:tr>
              </htm:table>
            
                       
               <htm:table  align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
                <htm:td   align="center" colspan="3" styleClass="helpdivbg" background="#{shared_util.contextPath}/app/media/images/shortCutImages/help_div_bg.jpg"> 
                  
                 <t:commandButton id="backshortCutButton" forceId="true"   type="button" value="#{globalResources.back}" onclick="window.close();"  styleClass="cssButtonSmall"/>          
             
               </htm:td>
               </htm:table>
              
            </t:div>   
                              </htm:td>
                            </htm:tr>    
            </htm:table>
            </t:div>  
        </h:form>
    </htm:body>
  </htm:html>
  
  
</f:view>