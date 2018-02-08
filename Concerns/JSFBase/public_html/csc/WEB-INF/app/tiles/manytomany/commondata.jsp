<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%---------------note(this will be used to put common save state )--------------------%>
<!-- Attributes for divs css added by ahmed abd el-fatah 06/05/2008 -->
<t:saveState value="#{detailBeanName.divMsg}"/>
<t:saveState value="#{detailBeanName.iframeBlock}"/>
<t:saveState value="#{detailBeanName.divSearch}"/>
<t:saveState value="#{detailBeanName.delAlert}"/>
<t:saveState value="#{detailBeanName.delConfirm}"/>
<t:saveState value="#{detailBeanName.content1Div}"/>
<t:saveState value="#{detailBeanName.divMainContent}"/>
<t:saveState value="#{detailBeanName.divMainContentMany}"/>

<t:saveState value="#{detailBeanName.lookupAddDiv}"/>
<t:saveState value="#{detailBeanName.lookupEditDiv}"/>
<t:saveState value="#{detailBeanName.masterDetailDiv}"/>
<t:saveState value="#{detailBeanName.divTreeAdd}"/>
<t:saveState value="#{detailBeanName.divTreeEdit}"/>
<t:saveState value="#{detailBeanName.delAlertTree}"/>
<t:saveState value="#{detailBeanName.divTreeDetails}"/>

<t:saveState value="#{detailBeanName.divMsgMany}"/>
<t:saveState value="#{detailBeanName.divSearchMany}"/>
<t:saveState value="#{detailBeanName.divAddMany}"/>
<t:saveState value="#{detailBeanName.divDelMany}"/>
<t:saveState value="#{detailBeanName.divConfirmMany}"/>

<t:saveState value="#{detailBeanName.selectedRadio}"/>
<t:saveState value="#{detailBeanName.filterMode}"/>


<t:inputHidden forceId="true" id="noOfTableRows" value="#{shared_util.noOfTableRows}"/>
<t:inputHidden forceId="true" id="arrayId" value=""/>
<t:inputHidden forceId="true" id="arrayIdAdd" value=""/>
<t:inputHidden value="#{detailBeanName.currentListSize}" forceId="true" id="listSize"/>   
<t:inputHidden value="#{detailBeanName.availableListSize}" forceId="true" id="listSizeAdd"/> 
