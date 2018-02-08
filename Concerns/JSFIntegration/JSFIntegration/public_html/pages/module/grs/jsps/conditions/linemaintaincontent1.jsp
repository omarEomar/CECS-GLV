<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>


             <t:panelGrid columns="2" width="100%" rowClasses="row02,row01" cellpadding="5" cellspacing="0" columnClasses="col3 nowrap_txt">
             
                <t:outputLabel value="#{resourcesBundle.conditionName}" styleClass="lable01" />
                
                <h:inputText value="#{pageBeanName.pageDTO.name}" styleClass="textboxlarge" disabled="true" />   
               
                </t:panelGrid>

          
          