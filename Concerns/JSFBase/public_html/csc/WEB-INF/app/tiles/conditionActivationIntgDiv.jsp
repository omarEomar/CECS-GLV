<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>


<htm:tr id="conditionActivationIntgDiv_tr">
    <htm:td align="center">
        <t:div forceId="true" id="conditionActivationIntgDiv"
               styleClass="#{appMainLayout.manyToMany ? detailBeanName.conditionActivationIntgDivStyleClass : pageBeanName.conditionActivationIntgDivStyleClass}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100">
            <htm:div styleClass="popup_header">
                <t:commandButton id="conditionActivationIntgDivclose" forceId="true" type="button"
                                 onclick="document.getElementById('backButtonConditionActivationIntgDiv').click();" styleClass="close"/>
                 
                <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                              rendered="#{pageBeanName.conditionActivationIntgDivTitleKey==null && detailBeanName.conditionActivationIntgDivTitleKey==null}"/>
                 
                <t:outputText styleClass="popup_title"
                              value="#{resourcesBundle[appMainLayout.manyToMany ? detailBeanName.conditionActivationIntgDivTitleKey : pageBeanName.conditionActivationIntgDivTitleKey]}"
                              rendered="#{pageBeanName.conditionActivationIntgDivTitleKey!=null || detailBeanName.conditionActivationIntgDivTitleKey!=null}"/>
            </htm:div>
            
            <htm:div styleClass="popup_body popup_body_integration">
            
                <htm:div styleClass="popup_inner_title">
                     <t:outputText value="#{globalResources[titleConditionActivationIntgDiv] == globaltitleConditionActivationIntgDiv ? resourcesBundle[titleConditionActivationIntgDiv] : globalResources[titleConditionActivationIntgDiv]}"/>
                </htm:div>
                
                <htm:div styleClass="popup_body_contents">
                    <tiles:insert attribute="conditionActivationIntgDiv"/>
                </htm:div>
                
            </htm:div>
        </t:div>
    </htm:td>
</htm:tr>
