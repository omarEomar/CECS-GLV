<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="retroactivesettdetails_div">
    <htm:td align="center">
        <t:div forceId="true"
               styleClass="#{appMainLayout.manyToMany ? detailBeanName.retroactiveSettDetailsDivStyleClass : pageBeanName.retroactiveSettDetailsDivStyleClass}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100" id="retroactiveSettDetailsDiv">
            <htm:div styleClass="popup_header">
                <t:commandButton id="RetroactiveSettDetailsDivclose" forceId="true" type="button"
                                 onclick="document.getElementById('backButtonRetroactiveSettDetailsIntgDiv').click();" styleClass="close"/>
                 
                <t:outputText styleClass="popup_title" value="#{resourcesBundle[sett_title]!=null ? resourcesBundle[sett_title] : resourcesBundle[title] }"
                              rendered="#{pageBeanName.retroactiveSettDetailsDivTitleKey==null && detailBeanName.retroactiveSettDetailsDivTitleKey==null}"/>
                 
                <t:outputText styleClass="popup_title"
                              value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.retroactiveSettDetailsDivTitleKey : pageBeanName.retroactiveSettDetailsDivTitleKey]}"
                              rendered="#{pageBeanName.retroactiveSettDetailsDivTitleKey!=null || detailBeanName.retroactiveSettDetailsDivTitleKey!=null}"/>
            </htm:div>
            <htm:div styleClass="popup_body">
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="retroactiveSettDetailsDiv"/>
                </htm:div>
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>