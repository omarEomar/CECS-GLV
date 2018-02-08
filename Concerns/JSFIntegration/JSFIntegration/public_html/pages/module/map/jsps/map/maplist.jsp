<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%>
<%@ taglib uri="http://www.beshara.com" prefix="beshara"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>

<f:view locale="#{shared_util.locale}">
    <!-- link rel="stylesheet" type="text/css" href="../../css/ar/Style.css" / -->
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:loadBundle basename="com.beshara.csc.gn.map.presentation.resources.map" var="mapResources"/>
    <f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.globalexceptions" var="globalexceptions"/>
    
    
    
    <h:form id="myForm" binding="#{mapped_Data_div.frm}">
    
    
    
<%-- <a4j:log popup="false" level="ALL"  height="400" width="800"></a4j:log>--%>
    <t:saveState value="#{mappedDataBean.soc_to}" />
    <t:saveState value="#{mappedDataBean.soc_from}" />
    <t:saveState value="#{mappedDataBean.objectType}" />
    <t:saveState value="#{mappedDataBean.selectedObjectTypeId}" />
    <t:saveState value="#{mappedDataBean.selectedSocity2Id}" />
    <t:saveState value="#{mappedDataBean.mapped_data_societies}" />
    <t:saveState value="#{mappedDataBean.mappedValue}" />
    <t:saveState value="#{mappedDataBean.selectedSocityFrom}" />
    <t:saveState value="#{mappedDataBean.socValue}" />
    <t:saveState value="#{mappedDataBean.socitiy2valuecode}" />
    <t:saveState value="#{mappedDataBean.ALL_MENUS_DEFAULT_VALUE}" />
    <t:saveState value="#{mappedDataBean.showdetails}" />
    <t:saveState value="#{mappedDataBean.showedRow}" />
    <t:saveState value="#{mappedDataBean.totalMapped_data_societiesNo}" />
    <t:saveState value="#{mappedDataBean.showLabel}" />
    <t:saveState value="#{mappedDataBean.mappedRecordNo}" />
    
    <t:saveState value="#{mappedDataBean.searchTypeLongVal}" />
    <t:saveState value="#{mappedDataBean.divBackBean.selectedDTOS}" />
    <t:saveState value="#{mappedDataBean.divBackBean.listSize}" />
    
    <t:saveState value="#{mappedDataBean.divBackBean.checkAllFlag}" />
    <t:saveState value="#{mappedDataBean.divBackBean.myTableData}" />
    <t:saveState value="#{mappedDataBean.mapping_title}" />
    <t:saveState value="{mappedDataBean.msg}" />
    
    
    
    
    
    <%-- a4j:log hotkey="M" /--%>
   
    
   
    
    
     
    
     <t:aliasBean alias="#{pageBeanName}" value="#{mapped_Data_div}"> 
     <t:saveState value="#{pageBeanName.myTableData}"/>
     <t:saveState value="#{pageBeanName.selectedDTOS}"/>
     
     <t:messages/>    
    <tiles:insert definition="mapped_data.page" flush="false">
          
             
          
        </tiles:insert>
       
                        </t:aliasBean>
                        
          <script type="text/javascript">
            foucsAddbuttonOnList();
            function foucsAddbuttonOnList(){        
                if(document.getElementById('objectType') != null){            
                    document.getElementById('objectType').focus();
                }
            }
            function setFocusAtMySearchDiv(){        
                if(isVisibleComponent('divSearch'))
                document.getElementsByName('radioBtn')[0].focus();
            }
            function setFocusAtMySearchText(){        
                if(isVisibleComponent('divSearch'))
                document.getElementById('search_first_inputTxt').focus();
            }
            
            
            function validateSearchDiv(codeLengthErrMsg, nameLengthErrMsg){        
                document.getElementById('errorMessage').innerHTML = '';
                
                if(document.getElementsByName('radioBtn')[0].checked){
                    if(checkLength('search_first_inputTxt','0')){
                        document.getElementById('errorMessage').innerHTML = codeLengthErrMsg;
                        return false;
                    }
                }else{
                    if(checkLength('search_first_inputTxt','0') || checkLength('search_first_inputTxt','1')){
                        document.getElementById('errorMessage').innerHTML = nameLengthErrMsg;
                        return false;
                    }
                }
                return true;
            }
        </script>
      </h:form>
    
</f:view>
