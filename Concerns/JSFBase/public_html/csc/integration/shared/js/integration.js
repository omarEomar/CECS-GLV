function openGrsIntgNewWindow(fullURL, winNameInput) {
    var url = document.getElementById(fullURL).value;
    var windowTitle = document.getElementById(winNameInput).innerHTML;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 632;
    var popupWindowheight = 540;
    
    /* Modification by A.AbdelHalim
     * To solve the Problems for Applying CSC on Chrome CSC-13885
     * */
    if(navigator.userAgent.toLowerCase().indexOf('chrome') > -1){
        //chrome
        popupWindowWidth = 700;
        popupWindowheight = 550;
    }
    
    sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";
    
    /* Modification by A.AbdelHalim
     * To solve the Problems for Applying CSC on Chrome CSC-13885
     * */
    if(navigator.userAgent.toLowerCase().indexOf('chrome') > -1){
        //chrome
        globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=637 height=450 src=\"" + url + "\"/></body></html>";
    }
    
    wOpen = window.open("", '', sOptions);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

    return wOpen;
}

function openGrsIntgNewWindowNew(fullURL,moduleName ,winNameInput) {
    var url = document.getElementById(fullURL).value;
    var newUrl = window.location.protocol + "//" + window.location.host;
    url = newUrl+moduleName+url;
    var windowTitle = document.getElementById(winNameInput).innerHTML;
    var wOpen;
    var sOptions;
    var popupWindowWidth = 632;
    var popupWindowheight = 540;

    sOptions = 'status=no,menubar=no,location=no,scrollbars=no,toolbar=no,resizable=no,addressbar=no';
    sOptions = sOptions + ',width=' + (popupWindowWidth).toString();
    sOptions = sOptions + ',height=' + (popupWindowheight).toString();
    sOptions = sOptions + ',screenX=0,screenY=0,left=0,top=0';

    globalHTML = "<html><head><title>" + windowTitle + "</title><meta charset=\"utf-8\"></head>" + "<body dir='rtl'> <iframe style='overflow:hidden'  width=625 height=500 src=\"" + url + "\"/></body></html>";

    wOpen = window.open("", '', sOptions);
    wOpen.document.write(globalHTML);
    wOpen.focus();
    wOpen.moveTo((screen.availWidth - popupWindowWidth) / 2, (screen.availHeight - popupWindowheight) / 2);

    return wOpen;
}
