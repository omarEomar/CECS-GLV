<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<htm:tr id="content_container">
  <htm:td align="center" width="100%">
    <htm:table border="0" cellspacing="0" cellpadding="0" width="100%">
      <htm:tr>
        <htm:td valign="top" align="center">
          <t:div id="contentPanel1" forceId="true" styleClass="#{appMainLayout.manyToMany ? detailBeanName.divMainContent : pageBeanName.divMainContent}">
            <tiles:insert attribute="contentPanel1"/>
          </t:div>
        </htm:td>
        <htm:td valign="top" align="center" width="50%">
          <t:div id="contentPanel2" forceId="true" styleClass="#{appMainLayout.manyToMany ? detailBeanName.divMainContentMany : pageBeanName.divMainContent}">
            <tiles:insert attribute="contentPanel2"/>
          </t:div>
        </htm:td>
      </htm:tr>
    </htm:table>
  </htm:td>
</htm:tr>