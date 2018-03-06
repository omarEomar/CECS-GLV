<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<tiles:importAttribute scope="request"/>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%> 
<script type="text/javascript">
function hideBlocker()
{
    try{
        obj = document.getElementById('iFrame');
        if(obj.style.visibility != 'hidden'){
            obj.style.visibility = 'hidden';
        }
    }catch(exception) {
    }
}
</script>

<t:div styleClass="#{pageBeanName.divTreeDetails}" style="z-index:100;" forceId="true" id="divTreeDetails">      
    <f:verbatim>
        <table width="300" border="0" cellpadding="0" cellspacing="0">
    </f:verbatim>
    <f:verbatim>
        <tr>
    </f:verbatim>
    <f:verbatim>
        <td>
    </f:verbatim>
    <f:verbatim>
        <img src="${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_01.gif" width="13" height="31" alt=""></td>
        <td style="background-image:url(${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_08.gif)">
    </f:verbatim>
    <f:verbatim>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
    </f:verbatim>
    <f:verbatim>
                <tr>
                    <td height="5"></td>
                </tr>
               
    </f:verbatim>
    <f:verbatim> <tr>
                    <td><input type="image" src="${shared_util.contextPath}/app/media/images/tree/x.gif" onclick="cancelEditFunction();hideBlocker();hideLookUpDiv(window.blocker,window.divTreeDetails,'','','');return false;" tabindex="1" /></td>
                </tr>
                <tr>
                    <td height="12"></td>
                </tr>
            </table>
            </td>
    </f:verbatim>
    <f:verbatim><td></f:verbatim>
    <f:verbatim><img src="${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_07.gif" width="12" height="31" alt=""></f:verbatim>
    <f:verbatim></td></f:verbatim>
    <f:verbatim></tr></f:verbatim>
    <f:verbatim><tr></f:verbatim>
    <f:verbatim><td  style="background-image:url(${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_02.gif)"></f:verbatim>
    <f:verbatim></td></f:verbatim>
    <f:verbatim><td width="100%" height="100%" align="center" valign="top" bgcolor="#FFFFFF"></f:verbatim> 
    <t:saveState value="#{pageBeanName.dtoDetails}"/>    
    <t:saveState value="#{pageBeanName.enableEdit}"/>
    
    <t:panelGroup id="treeViewAndEditDiv" forceId="true">
    <t:panelGrid columns="2"  width="100%" id="treeDetailsDiv" forceId="true" columnClasses="col1,col2" cellpadding="0" cellspacing="0">
        
         
            <%--item code--%>
            <t:outputText value="#{resourcesBundle[code]}" styleClass="lable01"/>
              
                <t:inputText disabled="true" value="#{pageBeanName.dtoDetails.code.key}" styleClass="textboxsmall"/>
             
            <%--item name--%>
            <t:outputText value="#{resourcesBundle[name]}" styleClass="lable01"/>
          
                <t:inputText disabled="#{!pageBeanName.enableEdit}" forceId="true" id="descriptionText" value="#{pageBeanName.dtoDetails.name}" styleClass="textboxmedium" >                   
                </t:inputText>
          
            
            <%--english item name--%>
            <t:outputText value="#{resourcesBundle.mapEnName}" styleClass="lable01"/>
            
                <t:inputText disabled="#{!pageBeanName.enableEdit}"  forceId="true" id="englishName_div" value="#{pageBeanName.dtoDetails.englishName}" styleClass="textboxmedium" />  
       
            
            <%--has leaf--%>    
          
            <t:outputText value="#{resourcesBundle[leaf]}" styleClass="lable01"/>
        
                <t:selectBooleanCheckbox disabled="#{!pageBeanName.enableEdit}"  value="#{pageBeanName.booleanLeafFlag}" forceId="true" id="booleanLeafFlag" />
         
  
    </t:panelGrid>
    
    <%--buttons--%>
    <t:panelGrid columns="3" border="0" align="center" id="buttonsEditDiv" forceId="true">
    <%--save button--%>
    <t:commandButton
        rendered="#{pageBeanName.enableEdit}" 
        type="button"
        forceId="true" 
        id="SaveButton"
        styleClass="cssButtonSmall" 
        value="#{resourcesBundle.saveButton}"  
        onclick="if(validatetreeform()){;refreshFunction();editFunction();hideBlocker();}">
        
        </t:commandButton>
        
    <%--edit button--%>
    <t:commandButton
        rendered="#{!pageBeanName.enableEdit}" 
        forceId="true" 
        id="SaveButtonEdit" 
        styleClass="cssButtonSmall" 
        value="#{resourcesBundle.editButton}"  
        onclick="enableEditFunction();changeVisibilityDiv(window.blocker,window.divTreeDetails);return false;">
            <a4j:jsFunction action="#{pageBeanName.enableEdit}" name="enableEditFunction" reRender="treeViewAndEditDiv,SaveButton,"/>
        </t:commandButton>
    
    <%--cancel edit button--%>
    <t:commandButton 
        forceId="true" 
        type="button"
        id="cancelButton" 
        rendered="#{pageBeanName.enableEdit}"
        styleClass="cssButtonSmall"
        value="#{resourcesBundle.cancelButton}"
        onclick="cancelEditFunction();hideBlocker();"/>
    </t:panelGrid>
    <a4j:jsFunction name="refreshFunction" reRender="treeViewAndEditDiv"/>    
    <a4j:jsFunction action="#{pageBeanName.edit}" name="editFunction" reRender="treeViewAndEditDiv,treeList"/> 
    <a4j:jsFunction action="#{pageBeanName.cancelEdit}" name="cancelEditFunction" reRender="treeViewAndEditDiv,"/>
</t:panelGroup>
    
    <f:verbatim><td style="background-image:url(${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_06.gif)"></td></f:verbatim>     
    <f:verbatim></td></f:verbatim>
    <f:verbatim><td style="background-image:url(${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_06.gif)"></f:verbatim><f:verbatim></td></f:verbatim>
    <f:verbatim></tr></f:verbatim>
    <f:verbatim><tr></f:verbatim>
    <f:verbatim><td></f:verbatim><f:verbatim><img src="${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_03.gif" width="13" height="38" alt=""></td></f:verbatim>
    <f:verbatim><td style="background-image:url(${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_04.gif)"></f:verbatim><f:verbatim></td></f:verbatim>
    <f:verbatim><td></f:verbatim><f:verbatim><img src="${shared_util.contextPath}/app/media/images/tree/div/BlueDiv_05.gif" width="12" height="38" alt=""></f:verbatim><f:verbatim></td></f:verbatim>
    <f:verbatim></tr></f:verbatim>
    <f:verbatim></table></f:verbatim>
</t:div>
