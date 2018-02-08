/*
 * @version 1.0
 * POWERD BY #CAPPUCCINO_TEAM
 * @author I.Omar
 * @since 18-NOV-2015
 * @see http://www.tinymce.com/index.php
 */
function initMCEEditor(inputTextIds){
  tinyMCE.init( {
      mode : "exact",
      language: "ar",
      directionality : 'rtl',
      width:"650px",
      elements : inputTextIds,
      menubar: false,
      statusbar: false,
      theme : "modern",
      fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
      plugins : ["table fontselect  fontformat directionality", "textcolor colorpicker"], 
      toolbar1 : "table bold italic underline | fontselect fontsizeselect | alignleft aligncenter alignright alignjustify | numlist  forecolor backcolor "
  });
}

// validate
 function validateEditor(inputTextId) {
      var editorValue = tinyMCE.get(inputTextId).getContent();
      if (editorValue == null || editorValue == "") {
          return false;
      }
      return true;
  }

// ADJUST RICH EDITR (CAPPUCCINO TEAM 10/12/2015)  
function initMCEEditorWithWidth(inputTextIds,inputTextWidth){
  tinyMCE.init( {
      mode : "exact",
      language: "ar",
      directionality : 'rtl',
      width:inputTextWidth,
      elements : inputTextIds,
      menubar: false,
      statusbar: false,
      theme : "modern",
      fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
      plugins : ["table fontselect  fontformat directionality", "textcolor colorpicker"], 
      toolbar1 : "table bold italic underline | fontselect fontsizeselect | alignleft aligncenter alignright alignjustify | numlist  forecolor backcolor "
  });
}
