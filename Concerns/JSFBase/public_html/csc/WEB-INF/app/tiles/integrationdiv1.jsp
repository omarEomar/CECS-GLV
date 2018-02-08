<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>


<htm:tr id="integration_div1">
    <htm:td align="center">
        <t:div forceId="true"
               styleClass="#{appMainLayout.manyToMany ? detailBeanName.integrationDiv1 : pageBeanName.integrationDiv1}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
               id="integrationDiv1">
            <htm:div styleClass="popup_header">
                <t:commandButton id="integrationDiv1close" forceId="true" type="button"
                                 onclick="hideIntegrationDiv();return false;" styleClass="close"/>
                 
                <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                              rendered="#{pageBeanName.integrationDiv1TitleKey==null && detailBeanName.integrationDiv1TitleKey==null}"/>
                 
                <t:outputText styleClass="popup_title"
                              value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.integrationDiv1TitleKey : pageBeanName.integrationDiv1TitleKey]}"
                              rendered="#{pageBeanName.integrationDiv1TitleKey!=null || detailBeanName.integrationDiv1TitleKey!=null}"/>
            </htm:div>
            
            <htm:div styleClass="popup_body popup_body_integration">
            
                <htm:div styleClass="popup_inner_title">
                     <t:outputText value="#{globalResources[titleIntegrationDiv1] == globaltitleIntegrationDiv1 ? resourcesBundle[titleIntegrationDiv1] : globalResources[titleIntegrationDiv1]}"/>
                </htm:div>
                
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="integrationDiv1"/>
                </htm:div>
                
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>
