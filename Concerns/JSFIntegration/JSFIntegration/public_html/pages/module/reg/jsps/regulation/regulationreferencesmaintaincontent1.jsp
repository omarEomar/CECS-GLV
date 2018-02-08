<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>

<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="3" cellspacing="0" columnClasses="colStaticSize1,colStaticSize2,colStaticSize1,colStaticSize3,colStaticSize1,colStaticSize3">     
<!--- Start of Row 1-->
    <h:outputText value="#{regResources.regulation_subjects_reg_type}" />
    <t:inputText forceId="true"  id="add_regType" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_year}" />
    <t:inputText forceId="true"  id="add_regYear" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.yearsDto.code.key}" disabled="true"/>
    
    <h:outputText value="#{regResources.regulation_subjects_reg_number}" />
    <t:inputText  rendered="#{pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true}" forceId="true" id="add_regNoAdd" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.regulationNumber}" disabled="true"/>
    <t:inputText  rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false}" forceId="true" id="add_regNoEdit" styleClass="textboxsmall2" value="#{detailBeanName.masterEntityKey.regulationNumber}" disabled="true"/>
<!--- Start of Row 2-->
    <h:outputText value="#{regResources.regulation_description}"/>
    <t:panelGroup colspan="6">
        <t:inputText disabled="true" styleClass="regTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.regulationTitle}"/>
    </t:panelGroup>
</t:panelGrid>
<t:messages/>
        