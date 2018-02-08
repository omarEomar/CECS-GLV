<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="custom_div3">
    <htm:td align="center">
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.customDiv3 : pageBeanName.customDiv3}"/>
        <t:saveState value="#{appMainLayout.manyToMany ? detailBeanName.customDiv3TitleKey : pageBeanName.customDiv3TitleKey}"/>
        <t:div forceId="true"
                styleClass="#{appMainLayout.manyToMany ? detailBeanName.customDiv3 : pageBeanName.customDiv3}"
                style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
                id="customDiv3">
            <htm:div styleClass="popup_header">
                <t:commandButton id="customDiv3Close" forceId="true" type="button" 
                        onclick="hideCustomDiv();return false;" styleClass="close"/>
                <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[title]}"
                        rendered="#{pageBeanName.customDiv3TitleKey==null && detailBeanName.customDiv3TitleKey==null}"/>     
                <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.customDiv3TitleKey : pageBeanName.customDiv3TitleKey]}"
                        rendered="#{pageBeanName.customDiv3TitleKey!=null || detailBeanName.customDiv3TitleKey!=null}"/>
            </htm:div>
            <htm:div styleClass="popup_body">
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="customDiv3"/>
                </htm:div>
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>
