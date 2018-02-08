<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="custom_div">
    <htm:td align="center">
        <t:div forceId="true"
               styleClass="#{appMainLayout.manyToMany ? detailBeanName.customDiv1 : pageBeanName.customDiv1}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
               id="customDiv1">
            <htm:div styleClass="popup_header">
                 <t:commandButton id="customDiv1close" forceId="true"
                                                                     type="button" 
                                                                     onclick="hideCustomDiv();return false;"
                                                                     styleClass="close"/>
                <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[title]}"
                        rendered="#{pageBeanName.customDiv1TitleKey==null && detailBeanName.customDiv1TitleKey==null}"/>     
                <t:outputText styleClass="popup_title"
                        value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.customDiv1TitleKey : pageBeanName.customDiv1TitleKey]}"
                        rendered="#{pageBeanName.customDiv1TitleKey!=null || detailBeanName.customDiv1TitleKey!=null}"/>
            </htm:div>
            <htm:div styleClass="popup_body">
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="customDiv1"/>
                </htm:div>
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>
