<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c"%>

<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{citiesListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{citiesListBean}">
            <tiles:insert flush="false" definition="citieslist.page">
             <t:saveState value="#{pageBeanName.masterDTO}"/>
              <t:saveState value="#{citiesListBean.pageMode}"/>
            </tiles:insert>            
        </t:aliasBean>
      <t:panelGroup forceId="true" id="scriptPanelGroup">
        <c:scriptGenerator form="myForm"/>
  </t:panelGroup>
    <script type="text/javascript">
         setFocusAtFirstElem();
         
         function setFocusAtFirstElem(){
         
            if( (!isVisibleComponent('delConfirm')) && (!isVisibleComponent('delAlert')) && (!isVisibleComponent('lookupAddDiv'))
             && (!isVisibleComponent('lookupEditDiv')) && (!isVisibleComponent('divSearch')))
            {
                setFocusOnlyOnElement('addButton');
            }
            
        }
    
     </script>  
  
    </h:form>
</f:view>