<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<!--
/*
 * Licensed to the Beshara Group (BG)
 * authore: Ahmed Abd El-Fatah
 */
//-->
<f:view locale="#{shared_util.locale}">
<f:loadBundle basename="com.beshara.csc.gn.req.presentation.resources.req" var="resourcesBundle"/>
<f:loadBundle basename="com.beshara.jsfbase.csc.msgresources.global" var="globalResources"/>
    <f:verbatim>
      <link rel="stylesheet" type="text/css" href="${shared_util.contextPath}/module/req/media/css/request_ar.css" />
      <script>
        
        function setFieldValue()
        {
            
            var fldCalCode = document.getElementById("fldCalCode").value;
            var fldCalValue = document.getElementById("fldCalValue").value;
            
            var elems =  document.myForm.elements;
            
            for(var i=0; i<elems.length; i++) {
                if(elems[i].name != null){    
                    
                    if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == ":"+fldCalCode) {
                        elems[i].value= fldCalValue;
                    }
                }
            }
        }
        function setFieldValue2()
        {
       
            var fldCalCode = document.getElementById("fldCalCode").value;
            var fldCalValue = document.getElementById("fldCalValue").value;

                    //alert(fldCalValue);
                    
             var fldCalCodeValue=fldCalValue.split(',');       
             for(var j=0; j< fldCalCodeValue.length ;j++){
                if(fldCalCodeValue[j] != null && fldCalCodeValue[j]!=""){
                    var fldCalCodeValuelist=fldCalCodeValue[j].split(":");
                    //alert(fldCalCodeValuelist);
                    var fldCode = fldCalCodeValuelist[0];
                    //alert(fldCode);
                    var fldValue = fldCalCodeValuelist[1];
                    //alert(fldValue);
                    var elems =  document.myForm.elements;
                    
                    for(var i=0; i<elems.length; i++) {
                        if(elems[i].name != null){    
                            //alert(elems[i].name);
                            if(elems[i].name.substring(elems[i].name.lastIndexOf(':')) == ":"+fldCode) {
                                elems[i].value= fldValue;
                            }
                        }
                    }
                }
            }
        }
        function validateBasicData()
        {
            
            if(document.myForm.requestReason.value=="-100")
            {
                document.getElementById("reasonMsg").innerHTML =${resourcesBundle.Select_alert_msg};
                document.myForm.requestReason.focus();
                return false;
            }
            changeVisibilityDiv(window.blocker,window.lookupAddDiv);
        }
      </script>
     </f:verbatim>
    <!-- Load the resource bundle of the page -->


  <h:form id="myForm" binding="#{requestEditBean.frm}">
  <t:aliasBean alias="#{pageBeanName}" value="#{requestEditBean}">
  
    
     <tiles:insert flush="false" definition="requestedit.page">
        <!-- Set the Body content of the page -->
        <t:saveState value="#{requestEditBean.requestsDTOSelected}" />
        <t:saveState value="#{requestEditBean.requestsDTO}" />
        <t:saveState value="#{requestEditBean.typeList}" />
        <t:saveState value="#{requestEditBean.reasonList}" />            
        <t:saveState value="#{requestEditBean.selectedTypeCode}" />
        <t:saveState value="#{requestEditBean.selectedReasonCode}" />
        <t:saveState value="#{requestEditBean.requestsDTO.typeDTO}" />
        <t:saveState value="#{requestEditBean.requestsDTO.reasonDTO}" />
        <t:saveState value="#{requestEditBean.completeFlag}" />
        <t:saveState value="#{requestEditBean.relatedFiled}" />
        <t:saveState value="#{requestEditBean.hm}" />
        <t:saveState value="#{requestEditBean.hmFieldValue}" />
        <t:saveState value="#{requestEditBean.nonFieldGroups}" />
        <t:saveState value="#{pageBeanName.typeName}"/>    
        <t:saveState value="#{pageBeanName.typeListHm}"/>  
        <t:saveState value="#{requestEditBean.hmAllFields}"/>
        <t:saveState value="#{requestEditBean.selectType}" />
        <%-- start paging --%>
        <t:saveState value="#{requestDataList.currentPageIndex}"/>
        <t:saveState value="#{requestDataList.oldPageIndex}"/>
        <t:saveState value="#{requestDataList.sorting}"/>
        <t:saveState value="#{requestDataList.usingPaging}"/>
        <t:saveState value="#{requestDataList.updateMyPagedDataModel}"/>
        <t:saveState value="#{requestDataList.resettedPageIndex}"/>
        <t:saveState value="#{requestDataList.pagingRequestDTO}"/>
        <t:saveState value="#{requestDataList.pagingBean.totalListSize}"/>
        <t:saveState value="#{requestDataList.pagingBean.myPagedDataModel}"/>
        <t:saveState value="#{requestDataList.pagingBean.preUpdatedDataModel}"/>
        
        <%-- end paging --%>
        
    </tiles:insert>
    </t:aliasBean>  
    <script type="text/javascript"> 
 //setFocusOnElement("requestReason");
 </script>
   </h:form>
  <c2:scriptGenerator form="myForm"/>
</f:view>