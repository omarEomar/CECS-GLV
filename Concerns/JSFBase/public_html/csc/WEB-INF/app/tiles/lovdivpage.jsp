<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="lov_div">
    <htm:td>
        <t:div forceId="true" styleClass="#{appMainLayout.manyToMany ? detailBeanName.lovDiv : pageBeanName.lovDiv}"
               id="divLov">
            <t:div styleClass="popup_header">
                <t:commandButton id="divLovcloseXX" forceId="true" type="button" 
                                onclick="document.getElementById('lov_cancel').click();" styleClass="close"/>
                <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}"
                              rendered="#{pageBeanName.lovDivTitleKey==null}"/>
                 
                <t:outputText styleClass="popup_title" value="#{resourcesBundle[pageBeanName.lovDivTitleKey]}"
                              rendered="#{pageBeanName.lovDivTitleKey!=null}"/>
            </t:div>
            <t:div styleClass="popup_body">
                <h:outputText value="#{globalResources[titleLovDiv] == globaltitleLovDiv ? resourcesBundle[titleLovDiv] : globalResources[titleLovDiv]}"/>
                <t:div styleClass="popup_body_contents">
                    <tiles:insert attribute="lovDiv"/>
                </t:div>
            </t:div>
        </t:div>
    </htm:td>
</htm:tr>
