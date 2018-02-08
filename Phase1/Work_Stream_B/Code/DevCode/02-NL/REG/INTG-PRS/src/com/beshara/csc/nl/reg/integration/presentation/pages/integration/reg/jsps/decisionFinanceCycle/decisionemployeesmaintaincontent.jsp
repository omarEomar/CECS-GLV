<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="https://ajax4jsf.dev.java.net/ajax" prefix="a4j"%> 
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c2"%>
<%@ taglib uri="http://jsftutorials.net/htmLib" prefix="htm"%>
<htm:style>
.dataT-With-6-row-filter-and-collapse {
    height: 189px !important;
}
</htm:style>
<t:panelGroup forceId="true" id="dataT_data_panel" rendered="#{pageBeanName.multiOrSingleEmps == '1'}" styleClass="dataT-With-6-row-filter-and-collapse">

       <!--rowStyleClass="#{list.disableFlage ? 'deletedRecordItemStylee' : standardTable_Row1}"-->
<!--rowStyleClass="#{detailBeanName.selected ? 'standardTable_RowHighlighted' : null}"-->
    <t:dataTable id="dataT_data" var="list" renderedIfEmpty="false"
       value="#{detailBeanName.usingPaging ? detailBeanName.pagingBean.myPagedDataModel :detailBeanName.currentDisplayDetails}"
       rowIndexVar="index" binding="#{detailBeanName.currentDataTable}" rows="#{shared_util.noOfTableRows}"
       rowOnMouseOver="javascript:addRowHighlight('regForm:dataT_data');" rowStyleClass="#{detailBeanName.selected ? 'standardTable_RowHighlighted' : 'standardTable_Row1'}" 
       footerClass="grid-footer" styleClass="grid-footer"
       headerClass="standardTable_Header"
       rowClasses="standardTable_Row1,standardTable_Row2"
       columnClasses="standardTable_ColumnCentered,standardTable_ColumnCentered,standardTable_ColumnCentered"
       width="100%" align="top" rendered="#{detailBeanName.numberOfRecordsDisplayed > 0}"
       dir="#{shared_util.pageDirection}" preserveSort="true">
       
       <t:column id="check_column" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" disabled="#{pageBeanName.showOnly}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAll}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" disabled="#{list.disableFlage || pageBeanName.showOnly}">                                            
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrent}" oncomplete="checkCheckAll('chk2');" reRender="dataT_data_panel,divDeleteAlert,OperationBar" />
            </t:selectBooleanCheckbox>
       </t:column>
    
        <t:column id="index_column" width="5%">
            <f:facet name="header">
             <t:outputText id="sort_index" value="#{regResources.index}"/>
            </f:facet>
            <t:outputText id="content_index" value="#{index+1}"/>
        </t:column>
        
        <t:column id="civilid_column" width="15%">
            <f:facet name="header">
             <t:outputText id="sort_employeesDTO-code-civilId" value="#{regResources.civil_id}"/>
            </f:facet>
          <t:outputText id="content_civilid" value="#{list.employeesDTO.citizensResidentsDTO.code.civilId}"/>
        </t:column>
        
       <t:column id="name_column" width="30%">
            <f:facet name="header">
                <t:outputText id="sort_employeesDTO-citizensResidentsDTO-fullName" value="#{regResources.decision_employees_emp_name}"/>
            </f:facet>
            <t:outputText id="content_name" value="#{list.employeesDTO.citizensResidentsDTO.fullName}" />
       </t:column>
       <t:column id="minId_column" width="25%">
            <f:facet name="header">
                <t:outputText id="sort_minId" value="#{regResources.select_decision_employees_geha}"/>
            </f:facet>
               <t:outputText id="sort_minOutId" value="#{list.employeesDTO.workCenterDTO.ministriesDTO.name}"/>
           </t:column>
          <t:column id="value_column" width="25%">
            <f:facet name="header">
              <t:panelGroup>
                <t:outputText id="sort_valueInDetail" value="#{regResources.financialEmpValueNum}  #{regResources.fels_Denar}"/>
                </t:panelGroup>
            </f:facet>
            <t:inputText forceId="true" id="fels_number"  value="#{list.felsValue}" tabindex="#{(index*2)+7}" onkeypress="FireNextDinar(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="8"  styleClass="textbox" style="width:35px;"    disabled="#{pageBeanName.showOnly}"  />
            <t:inputText forceId="true" id="denar_number" value="#{list.denarValue}" tabindex="#{(index*2)+6}" onkeypress="fireCurrentFels(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="8"  styleClass="filteration_input_text"   style=" width: 50px;  margin-right: 4px; margin-left: 1px;"  disabled="#{pageBeanName.showOnly}"  />
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.showOnly}"/>
            <a4j:jsFunction name="adjustTotalValueFn"
                            action="#{detailBeanName.getTotal_Value}"
                            reRender="total_value,divDeleteAlert,OperationBar,errorValueMessage" />
             <f:verbatim>
                        <br/>
           
                    </f:verbatim>
                    <t:outputText id="errorValueMessage" forceId="true" styleClass="errMsg" value="#{list.errorValueMessage}" />
              <%--<h:message for="denar_number" style="color:red" />--%>  
            
       </t:column>
    </t:dataTable>
    
    <t:inputHidden forceId="true" id="calederIDandLeftTopDeductionsHiddenFieldID" value="edit_third_inputTxt,420,190:add_third_inputTxt,420,200:clndr_merDurationToDate,10,0:clndr_merDurationFromDate,10,0"/>
    <t:inputHidden id="empCountId" forceId="true" value="#{financeDecisionCycleEmployeesMaintainBean.countOfAllEmpDecision}"/>
    
</t:panelGroup>

 <t:panelGroup forceId="true" rendered="#{pageBeanName.multiOrSingleEmps == '0'}" id="salVariedElmGuidesGroupId" styleClass="dataT-With-6-row-filter-and-collapse">

             <t:dataTable  forceId="true" id="salVariedElmGuidesDataTable" var="list" rowIndexVar="index"
                             value="#{detailBeanName.currentDisplayedSalVariedElmGuideDTOList}" binding="#{detailBeanName.salVariedElmGuidesDataTable}"
                             forceIdIndexFormula="#{list.code.key}" footerClass="grid-footer"
                             headerClass="standardTable_Header" rowClasses="standardTable_Row1,standardTable_Row2"
                             columnClasses="standardTable_ColumnCentered" width="100%" align="top"
                             dir="#{shared_util.pageDirection}" styleClass="grid-footer" renderedIfEmpty="false">
         
         <t:column id="check_column" width="5%">
            <f:facet name="header">
                <t:selectBooleanCheckbox forceId="true" id="checkAll" value="#{detailBeanName.checkAllFlag}" disabled="#{pageBeanName.showOnly}">
                    <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentAllElements}"  oncomplete="setAll('checkAll', 'chk2');"  reRender="salVariedElmGuidesGroupId,divDeleteAlert,OperationBar"/>
                </t:selectBooleanCheckbox>
            </f:facet>
            <t:selectBooleanCheckbox forceId="true" id="chk2" value="#{list.checked}" disabled="#{pageBeanName.showOnly}">                                            
                <a4j:support event="onclick" actionListener="#{detailBeanName.selectedCurrentElement}" oncomplete="checkCheckAll('chk2');" reRender="salVariedElmGuidesGroupId,divDeleteAlert,OperationBar" />
            </t:selectBooleanCheckbox>
       </t:column>
    
        <t:column id="index_column" width="5%">
            <f:facet name="header">
             <t:outputText id="sort_index" value="#{regResources.index}"/>
            </f:facet>
            <%--<t:outputText id="content_index" value="#{index+1}"/>--%>
            <t:outputText id="content_index" value="#{index+1}"/>
        </t:column>
        
          <t:column id="elmguide_column" width="15%">
            <f:facet name="header">
             <t:outputText id="sort_elmguideCode" value="#{regResources.regulation_subjects_symbol}"/>
            </f:facet>
            <t:outputText id="content_elmguideCode" value="#{list.code.elmguideCode}"/>
        </t:column> 
         <t:column id="name_column" width="45%">
            <f:facet name="header">
             <t:outputText id="sort_name" value="#{regResources.section}"/>
            </f:facet>
            <t:outputText id="content_name" value="#{list.name}"/>
        </t:column> 
         <t:column id="value2_column" width="30%">
            <f:facet name="header">
              <t:panelGroup>
                <t:outputText id="sort_valueInDetail2" value="#{regResources.financialEmpValueNum}  #{regResources.fels_Denar}"/>
                </t:panelGroup>
            </f:facet>
            <t:inputText forceId="true" id="fels_number2"  value="#{list.felsValue}" tabindex="#{(index*2)+7}" onkeypress="FireNextDinar2(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="8"  styleClass="textbox" style="width:35px;"    disabled="#{pageBeanName.showOnly}"  />
            <t:inputText forceId="true" id="denar_number2" value="#{list.denarValue}" tabindex="#{(index*2)+6}" onkeypress="fireCurrentFels2(event,#{index});" onchange="adjustTotalValueFn();" onkeyup="disableCharacters(this);" maxlength="3"  styleClass="filteration_input_text"   style=" width: 50px;  margin-right: 4px; margin-left: 1px;"  disabled="#{pageBeanName.showOnly}"  />
            <h:outputText value="*" styleClass="mandatoryAsterisk" rendered="#{!pageBeanName.showOnly}"/>
            <a4j:jsFunction name="adjustTotalValueFn"
                            action="#{detailBeanName.getTotal_ValueForOneEmpMultiElements}"
                            reRender="total_value,divDeleteAlert,OperationBar" />
              <f:verbatim>
                        <br/>
           
                    </f:verbatim>
          <t:outputText id="errorValueMessage" forceId="true" styleClass="errMsg" value="#{list.otherDesc}" />
       </t:column>
    </t:dataTable>
 </t:panelGroup>
<script type="text/javascript">
var denar_id = 'denar_number';
  var fels_id = 'fels_number';
  
var denar_id2 = 'denar_number2';
  var fels_id2 = 'fels_number2';
  function FireNextDinar(e, index) {
      var code1 = (e.keyCode ? e.keyCode : e.which);
      //alert("fireDinar" + index);  
      if (code1 == 13) {
          index = index + 1;
          if (document.getElementById(denar_id + '[' + index + ']') != null) {
              document.getElementById(denar_id + '[' + index + ']').focus();
              //document.getElementById(denar_id + '[' + index + ']').select();
              //document.getElementById(fels_id + '[' + index-1 + ']').unselect();
          }
          return false;

      }
      
  }

  function fireCurrentFels(e, index) {
      var code = (e.keyCode ? e.keyCode : e.which);
      //alert("fireFels" + index);
      if (code == 13) {
          if (document.getElementById(fels_id + '[' + index + ']') != null) {
              document.getElementById(fels_id + '[' + index + ']').focus();
              //document.getElementById(fels_id + '[' + index + ']').select();
              //document.getElementById(denar_id + '[' + index + ']').unselect();
          }
          return false;
      }
   
  }
 function calcualteFelsAndDenar(){
       var rowsCount = '${detailBeanName.currentListSize}';
        var total =0;
        var val = 0;
        var v1=0;
        var v2= 0;
        for (var i=0;i<rowsCount ;i++){
            // updated by A,Abdel Halim to handle Empty Values
            var vDenarValue = document.getElementById('denar_number['+i+']').value;
            var vFelsValue = document.getElementById('fels_number['+i+']').value;
            
            if(isBlank(vDenarValue)){
                vDenarValue = "0";
            }
            
            if(isBlank(vFelsValue)){
                vFelsValue = "0";
            }
            
            val=  parseFloat(vDenarValue+"."+vFelsValue);
            total +=val;
        }        
        document.getElementById('total_value').value =  toFixed(total,3);
        
     //setFocusAtMyFirstElement();
  }
  
  function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }
  function toFixed(value, precision) {
    var power = Math.pow(10, precision || 0);
    return String(Math.round(value * power) / power);
}

 function FireNextDinar2(e, index) {
      var code1 = (e.keyCode ? e.keyCode : e.which);
      //alert("fireDinar" + index);  
      if (code1 == 13) {
          index = index + 1;
          if (document.getElementById(denar_id2 + '[' + index + ']') != null) {
              document.getElementById(denar_id2 + '[' + index + ']').focus();
          }
          return false;

      }
      
  }

  function fireCurrentFels2(e, index) {
      var code = (e.keyCode ? e.keyCode : e.which);
      //alert("fireFels" + index);
      if (code == 13) {
          if (document.getElementById(fels_id2 + '[' + index + ']') != null) {
              document.getElementById(fels_id2 + '[' + index + ']').focus();
        }
          return false;
      }
   
  }
 function calcualteFelsAndDenar2(){
       var rowsCount = '${detailBeanName.currentListSize}';
        var total =0;
        var val = 0;
        var v1=0;
        var v2= 0;
        for (var i=0;i<rowsCount ;i++){
            // updated by A,Abdel Halim to handle Empty Values
            var vDenarValue = document.getElementById('denar_number2['+i+']').value;
            var vFelsValue = document.getElementById('fels_number2['+i+']').value;
            
            if(isBlank(vDenarValue)){
                vDenarValue = "0";
            }
            
            if(isBlank(vFelsValue)){
                vFelsValue = "0";
            }
            
            val=  parseFloat(vDenarValue+"."+vFelsValue);
            total +=val;
        }        
        document.getElementById('total_value').value =  toFixed(total,3);
        
     //setFocusAtMyFirstElement();
  }
  
  calcualteFelsAndDenar();
  calcualteFelsAndDenar2();
</script>
