<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>

<t:panelGrid columns="5" cellpadding="3" align="center">
    
    <a4j:commandLink action="#{detailBeanName.moveFirst}" reRender="dataT_data" rendered="#{index > 0}"> <t:graphicImage value="/app/media/images/moveUpDown/moveFirst.gif" border="0" title="#{globalResources.moveFirst}"/> </a4j:commandLink>
    <t:graphicImage value="/app/media/images/moveUpDown/moveEmpty.gif" border="0" rendered="#{index == 0}"/>
    
    <a4j:commandLink action="#{detailBeanName.moveUp}" reRender="dataT_data" rendered="#{index > 0}"> <t:graphicImage value="/app/media/images/moveUpDown/moveUp.gif" border="0" title="#{globalResources.moveUp}"/> </a4j:commandLink>
    <t:graphicImage value="/app/media/images/moveUpDown/moveEmpty.gif" border="0" rendered="#{index == 0}"/>
    
    <a4j:commandLink action="#{detailBeanName.moveDown}" reRender="dataT_data" rendered="#{index < detailBeanName.currentListSize-1}"> <t:graphicImage value="/app/media/images/moveUpDown/moveDown.gif" border="0" title="#{globalResources.moveDown}"/> </a4j:commandLink>
    <t:graphicImage value="/app/media/images/moveUpDown/moveEmpty.gif" border="0" rendered="#{index == detailBeanName.currentListSize-1}"/>
    
    <a4j:commandLink action="#{detailBeanName.moveLast}" reRender="dataT_data" rendered="#{index < detailBeanName.currentListSize-1}"> <t:graphicImage value="/app/media/images/moveUpDown/moveLast.gif" border="0" title="#{globalResources.moveLast}"/> </a4j:commandLink>
    <t:graphicImage value="/app/media/images/moveUpDown/moveEmpty.gif" border="0" rendered="#{index == detailBeanName.currentListSize-1}"/>
    
</t:panelGrid>