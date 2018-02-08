<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<f:view locale="#{shared_util.locale}">
  <f:loadBundle basename="com.beshara.csc.nl.reg.presentation.resources.reg" var="regResources"/>
  <h:form id="myForm" binding="#{decisionListBean.frm}">
    <t:aliasBean alias="#{pageBeanName}" value="#{decisionListBean}" id="decisionList">
      <tiles:insert definition="decisionlist.page" flush="false"></tiles:insert>
      
             <t:saveState value="#{pageBeanName.typeColumnVisible}"/>
            <t:saveState value="#{pageBeanName.yearColumnVisible}"/>
            <t:saveState value="#{pageBeanName.numberColumnVisible}"/>
            <t:saveState value="#{pageBeanName.titleColumnVisible}"/>
            <t:saveState value="#{pageBeanName.decisionMakerColumnVisible}"/>
            <t:saveState value="#{pageBeanName.dateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.applyDateColumnVisible}"/>
            <t:saveState value="#{pageBeanName.statusColumnVisible}"/>
            <t:saveState value="#{pageBeanName.fullColumnName}"/>
                                                                       
    </t:aliasBean>
    <c2:scriptGenerator form="myForm"/>
    <script type="text/javascript"> 
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){        
                if(document.getElementById('addButton') != null){            
                    document.getElementById('addButton').focus();
                }
            }
            
            function setFocusAtMySearchDiv(){
                setFocusOnlyOnElement('regulationTypeID'); 
            }
            
        </script>
  </h:form>
</f:view>