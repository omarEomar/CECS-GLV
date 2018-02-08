// directory of where all the images are
var cmBesharaThemeBase = 'jscookmenu/BesharaTheme/';
var myBesharaThemeBase = 'jscookmenu/BesharaTheme/';
if(myBesharaThemeBase)
    cmBesharaThemeBase = myBesharaThemeBase;

var myPrefix = cmBesharaThemeBase;
var mySuffix="";

// sub menu display attributes
if(cmBesharaThemeBase.indexOf("/;j")>-1){
	myPrefix=cmBesharaThemeBase.substring(0,cmBesharaThemeBase.indexOf("/;j")) + "/";
	mySuffix=cmBesharaThemeBase.substring(cmBesharaThemeBase.indexOf("/;j")+1);
}

var myFolderLeft = myPrefix + 'spacer.gif' + mySuffix;
var myFolderRight = myPrefix + 'arrow.gif' + mySuffix;
var myItemLeft = myPrefix + 'spacer.gif' + mySuffix;
var myItemRight = myPrefix + 'blank.gif' + mySuffix;


var cmBesharaTheme =
{
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '&#160;',
        
  	// HTML code to the right of the folder item
  	mainFolderRight: '&#160;',

	// HTML code to the left of the regular item
        mainItemLeft: '&#160;',

	// HTML code to the right of the regular item
	mainItemRight: '&#160;',


	// sub menu display attributes

	// 0, HTML code to the left of the folder item
//	folderLeft: '<img alt="" src="' + myFolderLeft + '">',by sherif.omar
        folderLeft:'',
	// 1, HTML code to the right of the folder item
//	folderRight: '<img alt="" src="' + myFolderRight + '">',by sherif.omar
        folderRight:'',
	// 2, HTML code to the left of the regular item
//	itemLeft: '<img alt="" src="' + myItemLeft + '">',by sherif.omar
        itemLeft: '',
	// 3, HTML code to the right of the regular item
//	itemRight: '<img alt="" src="' + myItemRight + '">',
        itemRight:'',
	// 4, cell spacing for main menu
	mainSpacing: 0,
	// 5, cell spacing for sub menus
	subSpacing: 0,
	// 6, auto dispear time for submenus in milli-seconds
	delay: 500,
        amir: 'amir'
};

// for horizontal menu split
var cmBesharaThemeHSplit = [_cmNoAction, '<td class="BesharaThemeMenuItemLeft"></td><td colspan="2"><div class="BesharaThemeMenuSplit"></div></td>'];
var cmBesharaThemeMainHSplit = [_cmNoAction, '<td class="BesharaThemeMainItemLeft"></td><td colspan="2"><div class="BesharaThemeMenuSplit"></div></td>'];
var cmBesharaThemeMainVSplit = [_cmNoAction, '|'];

