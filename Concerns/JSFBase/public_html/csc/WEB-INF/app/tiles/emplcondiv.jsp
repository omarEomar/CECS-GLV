<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="civil_div">
    <htm:td align="center">
        <t:div forceId="true"
                styleClass="#{appMainLayout.manyToMany ? detailBeanName.empListOfValues.empListOfValuesStyle : pageBeanName.empListOfValues.empListOfValuesStyle}"
               style="width:70%;position:absolute;top:70px;right:15%;z-index:100"
               id="lovEmp">
            <t:div styleClass="popup_header">
                <t:commandButton id="closeemplov" forceId="true" type="button" 
                                onclick="document.getElementById('backButtonEmpLovDiv').click();" styleClass="close"/>
                 
                <htm:span>
                    <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}" rendered="#{pageBeanName.empLovDivTitleKey==null}"/>
                    <t:outputText styleClass="popup_title" value="#{resourcesBundle[pageBeanName.empLovDivTitleKey]}" rendered="#{pageBeanName.empLovDivTitleKey!=null}"/>
                </htm:span>
            </t:div>
            <t:div styleClass="popup_body">
                <t:div styleClass="popup_body_contents">
                    <tiles:insert attribute="lovEmp"/>
                </t:div>
            </t:div>
        </t:div>
    </htm:td>
</htm:tr>
