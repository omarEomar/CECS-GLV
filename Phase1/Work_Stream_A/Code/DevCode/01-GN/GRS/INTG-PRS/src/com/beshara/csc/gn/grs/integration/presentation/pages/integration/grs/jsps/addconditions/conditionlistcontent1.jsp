<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators"  prefix="c2"%>
    
                    <t:panelGrid columns="3" width="100%" align="right"  rowClasses="row01," cellspacing="0" cellpadding="3" border="0"> 
                                <h:outputLabel value="#{grsResources.ParameterType}" styleClass="lable01" />
                                <t:panelGroup>
                                    <t:inputText forceId="true" id="paramTypeId" styleClass="filteration_input_text" 
                                        onkeypress="filterationInputOnKeyPress(event,this,'paramTypeList','errorCodeId',changeParamTypeVal);"
                                        onblur="filterationInputOnBlur(event,this,'paramTypeList','errorCodeId',changeParamTypeVal);"
                                        onkeyup="enabelIntegerOnly(this);" style="margin-left:5px;">
                                        <a4j:jsFunction name="changeParamTypeVal" actionListener="#{pageBeanName.filterByParameterType}"   reRender="dataT_data_panel,OperationBar,paging_panel,listSize,divSearch"/>
                                    </t:inputText>                                
    
                                    <t:selectOneMenu id="paramTypeList" forceId="true" value="#{pageBeanName.selectedParameterType}" styleClass="DropdownboxxLarge2"
                                                onchange="filterationDropboxOnChange(event,this,'paramTypeId','errorCodeId',changeParamTypeValD);">
                                        <f:selectItem itemLabel="#{resourcesBundle.SelectOneCategory}" itemValue="#{pageBeanName.virtualConstValue}"/>
                                        <t:selectItems value="#{pageBeanName.parameterTypesList}" var="item" itemValue="#{item.code.key}" itemLabel="#{item.name}"/>
                                        <a4j:jsFunction name="changeParamTypeValD" actionListener="#{pageBeanName.filterByParameterType}" 
                                            reRender="dataT_data_panel,OperationBar,paging_panel,listSize,divSearch,errorCodePanelId"/>
                                    </t:selectOneMenu>
                                </t:panelGroup>    
                                
                                <t:panelGroup forceId = "true" id="errorCodePanelId" style="display:block;width:250px;">
                                    <t:outputLabel id="errorCodeId" value="#{resourcesBundle.MessageForWrongCode}" forceId = "true" styleClass="error" style="display:none"/>
                                </t:panelGroup>
                    </t:panelGrid>                
                
                <script language="javascript" type="text/javascript">
                    copyDropdownIntoInputtext('paramTypeList', 'paramTypeId');
                </script>
