<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<t:panelGrid columns="6" rowClasses="row01,row02" width="100%" cellpadding="0" cellspacing="0" columnClasses="colStaticSize1,colStaticSize2,colStaticSize1,colStaticSize3,colStaticSize1,colStaticSize3">
    
    <!--- Start of Row 1-->
    <t:outputText value="#{regResources.regulation_References_reg_type}" />
    <t:inputText forceId="true"  id="add_regType" styleClass="textboxlarge" value="#{pageBeanName.pageDTO.typesDto.name}" disabled="true"/>
    
    <t:outputText value="#{regResources.reg_year}" />
    <t:inputText forceId="true"  id="add_regYear" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.yearsDto.code.key}" disabled="true"/>
    
    <t:outputText value="#{regResources.reg_number}" />
    <t:inputText  rendered="#{pageBeanName.maintainMode==0 || pageBeanName.copyFlage==true}" forceId="true" id="add_regNoAdd" styleClass="textboxsmall2" value="#{pageBeanName.pageDTO.regulationNumber}" disabled="true"/>
    <t:inputText  rendered="#{(pageBeanName.maintainMode==1 || pageBeanName.maintainMode==2) && pageBeanName.copyFlage==false}" forceId="true" id="add_regNoEdit" styleClass="textboxsmall2" value="#{detailBeanName.masterEntityKey.regulationNumber}" disabled="true"/>
    
    <!--- Start of Row 2-->
    <t:outputText value="#{regResources.regulation_description}"/>
    <t:panelGroup colspan="6">
        <t:inputText disabled="true" styleClass="regTiTleTextBoxInHeader" value="#{pageBeanName.pageDTO.regulationTitle}"/>
    </t:panelGroup>
    
</t:panelGrid>

<t:messages/>