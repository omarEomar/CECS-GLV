<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
 <htm:tr id="civil_div">
                                <htm:td align="center">                                             
                                     <t:div id="lovEmpPaging" forceId="true" 
                                        styleClass="#{appMainLayout.manyToMany ? detailBeanName.empListOfValues.empListOfValuesStyle : pageBeanName.empListOfValues.empListOfValuesStyle}"
                                         style="width:70%;position:absolute;top:70px;right:15%;z-index:100">
                                        <htm:div styleClass="popup_header">
                                            
                                            <t:commandButton id="lovEmpPagingDivclose" forceId="true"
                                                                     type="button" 
                                                                     onclick="document.getElementById('CancelButtonEdit').click();"
                                                                     styleClass="close"/>
                                            <t:outputText styleClass="popup_title" value="#{resourcesBundle[title]}" rendered="#{pageBeanName.empLovDivTitleKey==null}"/>
                                            <t:outputText styleClass="popup_title" value="#{resourcesBundle[pageBeanName.empLovDivTitleKey]}" rendered="#{pageBeanName.empLovDivTitleKey!=null}"/>
                                        </htm:div>
                                        <htm:div styleClass="popup_body">
                                            <h:outputText value="#{globalResources[titleEmpLovDiv] == globaltitleEmpLovDiv ? resourcesBundle[titleEmpLovDiv] : globalResources[titleEmpLovDiv]}"/>
                                            <htm:div styleClass="popup_body_contents">
                                                <tiles:insert attribute="lovEmpPaging" flush="false"/>
                                            </htm:div>
                                        </htm:div>
                                    </t:div>
                                </htm:td>
                            </htm:tr>
