<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="custom_div2">
    <htm:td align="center">
        <t:div forceId="true"
               styleClass="#{appMainLayout.manyToMany ? detailBeanName.customDiv2 : pageBeanName.customDiv2}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
               id="customDiv2">
            <htm:div styleClass="popup_header">
            <t:commandButton id="customDiv2Close" forceId="true" type="button" 
                                onclick="hideCustomDiv();return false;" styleClass="close"/>
               <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[title]}"
                        rendered="#{pageBeanName.customDiv2TitleKey==null && detailBeanName.customDiv2TitleKey==null}"/>     
                <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.customDiv2TitleKey : pageBeanName.customDiv2TitleKey]}"
                        rendered="#{pageBeanName.customDiv2TitleKey!=null || detailBeanName.customDiv2TitleKey!=null}"/>
            </htm:div>
            <htm:div styleClass="popup_body">
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="customDiv2"/>
                </htm:div>
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>
