<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">
    <!-- link rel="stylesheet" type="text/css" href="../../css/ar/Style.css" / -->
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.map.presentation.resources.map" var="mapResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    
    
    
    <h:form id="myForm" binding="#{societyRelationAddBean.frm}">
 
     <t:aliasBean alias="#{pageBeanName}" value="#{societyRelationAddBean}"> 
 
     <t:saveState value="#{societyRelationAddBean.soc_to}" />
    <t:saveState value="#{societyRelationAddBean.soc_from}" />
    
    <t:saveState value="#{societyRelationAddBean.objectType}" />
    <t:saveState value="#{societyRelationAddBean.soc_rel_types}" />
    <t:saveState value="#{societyRelationAddBean.selectedObjectTypeId}" />
    <t:saveState value="#{societyRelationAddBean.selectedSocity2Id}" />
    <t:saveState value="#{societyRelationAddBean.selectedRelationTypeCode}" />
    <t:saveState value="#{societyRelationAddBean.selectedSocityFrom}" />
    <t:saveState value="#{societyRelationAddBean.socValue}" />
    <t:saveState value="#{societyRelationAddBean.socitiy2valuecode}" />
    <t:saveState value="#{societyRelationAddBean.ALL_MENUS_DEFAULT_VALUE}" />
      
    <tiles:insert definition="societyEditRelation.page" flush="false">


          <tiles:put name="titlepage" type="string" value="societyRelationedit"/>
             
          
        </tiles:insert>
       <t:panelGroup forceId="true" id="scriptPanelID">
             <c2:scriptGenerator form="myForm"/>
         </t:panelGroup>
                        </t:aliasBean>
                        
          <script type="text/javascript">
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){        
                if(document.getElementById('objectType') != null){            
                    document.getElementById('objectType').focus();
                }
            }
        </script>
      </h:form>
    
</f:view>
