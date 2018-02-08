/*************************************************************************
 dw_tooltip.js   requires: dw_event.js and dw_viewport.js
 version date: May 21, 2005 moved init call to body onload
 (March 14, 2005: minor changes in position algorithm and timer mechanism)
 
 This code is from Dynamic Web Coding at dyn-web.com
 Copyright 2003-5 by Sharon Paine
 See Terms of Use at www.dyn-web.com/bus/terms.html
 regarding conditions under which you may use this code.
 This notice must be retained in the code as is!
 *************************************************************************/
window.onload = testdw_tooltip();

function testdw_tooltip()
{
    var vv = '111';
    //alert('dw_tooltip js');
    //Tooltip.init();
    //alert(Tooltip);
    return vv;
}
function doTooltip(e, msg) {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
    Tooltip.show(e, msg);
}

function doTooltipWithDetails(e, msg) {
    if (typeof Tooltip == "undefined" || !Tooltip.ready)
        return;
    
    Tooltip.showDetails(e, msg);
   
}

function hideTip() {
  if ( typeof Tooltip == "undefined" || !Tooltip.ready ) return;
    Tooltip.hide();
}



var Tooltip = {
    followMouse: true,
    offX: 1,
    offY: 1,
    tipID: "tipDiv",
    showDelay: 100,
    hideDelay: 300,
    ready:false, timer:null, tip:null, 
  
    init: function() {  
        //    alert('called init of tooltip');
        if (document.createElement && document.body && typeof document.body.appendChild != "undefined") {
            //        alert('init of tooltip is working and will be ready ');
            //        alert(this.tipID);
            if (!document.getElementById(this.tipID)) {
                //            alert('getelement by id returned false :( ');
                var el = document.createElement("DIV");
                el.id = this.tipID; document.body.appendChild(el);

            }
            this.ready = true;
        }
        //        alert('end of init ');
        //    alert(this.tipID);
    },
    
    show : function (e, msgword) {
        var msg = '<img  src="/' + getModuleName() + '/app/media/images/icon_tooltip_info.gif"  style="float:left";  border="0"><div class="tooltipbackground;">' + msgword + '</div>';

        //alert('we are in show ');
        //alert(e);
        //alert(this.tipID);
        if (this.timer) { clearTimeout(this.timer);	this.timer = 0; }
        this.tip = document.getElementById(this.tipID);
        if (this.followMouse)// set up mousemove 
            dw_event.add(document, "mousemove", this.trackMouse, true);
        //            alert('after addon event');
        this.writeTip("");// for mac ie
        //        alert('after write tp');
        this.writeTip(msg);
        //         alert('after write tip2');
        viewport.getAll();
        //         alert('after get all');
        this.positionTip(e);
        //         alert('after position tp');
        this.timer = setTimeout("Tooltip.toggleVis('" + this.tipID + "', 'visible')", this.showDelay);
        //        alert('end show ');
    },
    
    showDetails : function (e, html) {
        var msg = html;
        if (this.timer) {
            clearTimeout(this.timer);
            this.timer = 0;
        }
        this.tip = document.getElementById(this.tipID);
        
        if (this.followMouse)// set up mousemove 
            dw_event.add(document, "mousemove", this.trackMouse, true);
        //            alert('after addon event');
        this.writeTip("");// for mac ie
        //        alert('after write tp');
        this.writeTip(msg);
        //         alert('after write tip2');
        viewport.getAll();
        //         alert('after get all');
        this.positionTip(e);
        //         alert('after position tp');
        this.timer = setTimeout("Tooltip.toggleVis('" + this.tipID + "', 'visible')", this.showDelay);
        //        alert('end show ');
    },
    writeTip : function (msg) {
        if ( this.tip && typeof this.tip.innerHTML != "undefined" ) this.tip.innerHTML = msg;
    },
    
    positionTip : function (e) {
        if (this.tip && this.tip.style) {
            // put e.pageX/Y first! (for Safari)
            var x = e.pageX ? e.pageX : e.clientX + viewport.scrollX;
            var y = e.pageY ? e.pageY : e.clientY + viewport.scrollY;
            // alert(y);
            //alert(this.tip.offsetWidth); 
            //this.tip.offsetWidth = 500;
            if (x + this.tip.offsetWidth + this.offX > viewport.width + viewport.scrollX) {
                x = x - this.tip.offsetWidth - this.offX;
                if ( x < 0 ) x = 0;
            } else x = x + this.offX;

        
            if (y + this.tip.offsetHeight + this.offY > viewport.height + viewport.scrollY) {
                y = y - this.tip.offsetHeight - this.offY;
                if ( y < viewport.scrollY ) y = viewport.height + viewport.scrollY - this.tip.offsetHeight;
            } else y = y + this.offY;
            //added by nora to modify position of top and left 
            if (document.getElementById('toolTipXYpositionField') != null) {
                var xyPositionStrValues = document.getElementById('toolTipXYpositionField').value;
                var xyPositionValues = xyPositionStrValues.split(',');
                var leftValue = 0;
                var topValue = 0;

                if (xyPositionValues.length >= 2) {
                    if (xyPositionValues[0] != null && xyPositionValues[0] != "")
                        leftValue = xyPositionValues[0];
                    if (xyPositionValues[1] != null && xyPositionValues[1] != "")
                        topValue = xyPositionValues[1];
                }
                if (xyPositionValues.length == 4) {
                    this.tip.style.width = xyPositionValues[2] + "px";
                    this.tip.style.height = xyPositionValues[3] + "px";
                }
                 this.tip.style.left = (x-leftValue) + "px"; this.tip.style.top = (y-topValue) + "px";

            }
            else {
               this.tip.style.left = x + "px"; this.tip.style.top = y + "px";
            }
        }
    },
    
    hide : function () {
        //alert('we are in hide ');
        if (this.timer) { clearTimeout(this.timer);	this.timer = 0; }
        this.timer = setTimeout("Tooltip.toggleVis('" + this.tipID + "', 'hidden')", this.hideDelay);
        if (this.followMouse)// release mousemove
            dw_event.remove(document, "mousemove", this.trackMouse, true);
        this.tip = null;
    },

    toggleVis: function(id, vis) { // to check for el, prevent (rare) errors
        var el = document.getElementById(id);
        if (el) el.style.visibility = vis;
    },
    
    trackMouse : function (e) {
        e = dw_event.DOMit(e);
        Tooltip.positionTip(e);
    }

}


function getModuleName() {
    var pathArray = window.location.pathname.split('/');
    moduleName = "";
    moduleName += pathArray[1];
    return moduleName;

}
