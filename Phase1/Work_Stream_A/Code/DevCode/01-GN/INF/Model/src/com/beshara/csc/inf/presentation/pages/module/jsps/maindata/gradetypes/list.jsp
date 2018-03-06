<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://beshara.net/projects/jsf-comp/clientvalidators" prefix="c"%>
<f:view locale="#{shared_util.locale}">
    <h:form id="myForm" binding="#{gradeTypesListBean.frm}">
        <f:loadBundle basename="com.beshara.csc.inf.presentation.resources.inf" var="resourcesBundle"/>
        <t:aliasBean alias="#{pageBeanName}" value="#{gradeTypesListBean}">
            <tiles:insert flush="false" definition="gradeTypes.page">
                <t:saveState value="#{pageBeanName.validMode}"/>
                <t:saveState value="#{pageBeanName.pageMode}"/>
                <t:saveState value="#{pageBeanName.showEdit}"/>
                <t:saveState value="#{pageBeanName.selectedPageDTO}"/>
                <t:saveState value="#{pageBeanName.pageDTO}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.singleSelection}"/>
                <t:saveState value="#{pageBeanName.myTableData}"/>
                <t:saveState value="#{pageBeanName.highlightedDTOS}"/>
                <t:saveState value="#{pageBeanName.searchMode}"/>
                <t:saveState value="#{pageBeanName.selectedDTOS}"/>
                <t:saveState value="#{pageBeanName.fullColumnName}"/>
                <t:saveState value="#{pageBeanName.sortAscending}"/>
                <t:saveState value="#{pageBeanName.renderOption}"/>
                <t:saveState value="#{gradeTypesListBean.selectedListSize}"/>
                <!-- Start Paging -->
                <t:saveState value="#{pageBeanName.currentPageIndex}"/>
                <t:saveState value="#{pageBeanName.oldPageIndex}"/>
                <t:saveState value="#{pageBeanName.sorting}"/>
                <t:saveState value="#{pageBeanName.usingPaging}"/>
                <t:saveState value="#{pageBeanName.updateMyPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.resettedPageIndex}"/>
                <t:saveState value="#{pageBeanName.pagingRequestDTO}"/>
                <t:saveState value="#{pageBeanName.pagingBean.totalListSize}"/>
                <t:saveState value="#{pageBeanName.pagingBean.myPagedDataModel}"/>
                <t:saveState value="#{pageBeanName.pagingBean.preUpdatedDataModel}"/>
                <t:saveState value="#{pageBeanName.gradeTypeValType}"/>
                <t:saveState value="#{pageBeanName.selectedGradeTypesDTO}"/>
            </tiles:insert>
        </t:aliasBean>
        <t:panelGroup forceId="true" id="scriptPanelGroup">
            <c:scriptGenerator form="myForm"/>
        </t:panelGroup>
        <script type="text/javascript">
          foucsAddbuttonOnList();

          function foucsAddbuttonOnList() {
              if (document.getElementById('addButton') != null) {
                  document.getElementById('addButton').focus();
              }
          }

          function settingFoucsAtDivAdd() {
              if (document.getElementById('grade_name') != null) {
                  document.getElementById('grade_name').focus();
              }
          }

          function settingFoucsAtDivEdit() {
              if (document.getElementById('grade_name1') != null) {
                  document.getElementById('grade_name1').focus();
              }
          }

          function settingFoucsAtAddGradeDiv() {
              if (document.getElementById('gradeValue') != null) {
                  document.getElementById('gradeValue').focus();
              }
          }

          function settingFoucsAtShowGradeDiv() {
              if (document.getElementById('backButtonCustomDiv2') != null) {
                  document.getElementById('backButtonCustomDiv2').focus();
              }
          }

          function evaluateMathExpForEdit() {
              var result = true;
              var valforvlidateedit = document.getElementById("myForm:radioValueedit").value;
              if (valforvlidateedit == 'true') {
                  document.getElementById('formulaErrorMessageForEdit').innerHTML = '';

                  mathExp = document.getElementById('grade1_equation').value;
                  if(isBlank(mathExp)){
                      return result;
                  }
                  var CUtmathExpEdit = mathExp.substr(0, 5);
                  if (CUtmathExpEdit == 'ROUND') {
                      mathExp = mathExp.replace('ROUND', ' ');
                  }
                  mathExp = mathExp.replace('X', '1');
                  mathExp = mathExp.replace('x', '1');
                  try {
                      Number(eval('(0+(' + mathExp + '))'));
                  }
                  catch (err) {
                      document.getElementById('formulaErrorMessageForEdit').innerHTML = 'معادلة غير صحيحة';
                      result = false;
                  }
                  return result;
              }
              else {
                  return true;
              }
          }

          function validatemyForm1() {
              var x = evaluateMathExpForEdit();
              var y = validatemyForm();
              return x && y;
          }
          
        function isBlank(str) {
            return (!str || /^\s*$/.test(str));
        }

          function evaluateMathExpForAdd() {
              var result = true;
              var valforvlidate = document.getElementById("myForm:radioValue").value;
              if (valforvlidate == 'true') {
                  document.getElementById('formulaErrorMessageForAdd').innerHTML = '';

                  mathExp = document.getElementById('grade_equation').value;
                  if(isBlank(mathExp)){
                      return result;
                  }
                  
                  var CUtmathExpAdd = mathExp.substr(0, 5);
                  if (CUtmathExpAdd.ignoreCase.equals( 'ROUND')) {
                      mathExp = mathExp.replace(CUtmathExpAdd.ignoreCase, ' ');
                  }
                  mathExp = mathExp.replace('X', '1');
                  mathExp = mathExp.replace('x', '1');
                  try {
                      Number(eval('(0+(' + mathExp + '))'));
                  }
                  catch (err) {
                      document.getElementById('formulaErrorMessageForAdd').innerHTML = 'معادلة غير صحيحة';
                      result = false;
                  }
                  return result;
              }
              else {
                  return true;
              }
          }

          function validatemyForm2() {
              var x = evaluateMathExpForAdd();
              var y = validatemyForm();
              return x && y;
          }
        </script>
    </h:form>
</f:view>
