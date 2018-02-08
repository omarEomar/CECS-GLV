<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" columnClasses="colStaticSize1,colStaticSize2,colStaticSize1,colStaticSize3,colStaticSize1,colStaticSize3">
<!--- Start of Row 1-->
    <h:outputText value="#{regResources.tableName}" />
    <t:inputText styleClass="textboxlarge" value="#{regulationAttachmentsMaintain.selectedCurrentDetails[0].name}" disabled="true"/>
    
    
</t:panelGrid>
<t:messages/>