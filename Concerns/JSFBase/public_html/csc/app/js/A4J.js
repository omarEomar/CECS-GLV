/*
 * Prolog for created Ajax4Jsf library
 */
if (!window.A4J) { window.A4J= {};}

//if(window.A4J.AJAX && window.A4J.AJAX.XMLHttpRequest) return;
/*
 * ====================================================================
 * About Sarissa: http://dev.abiss.gr/sarissa
 * ====================================================================
 * Sarissa is an ECMAScript library acting as a cross-browser wrapper for native XML APIs.
 * The library supports Gecko based browsers like Mozilla and Firefox,
 * Internet Explorer (5.5+ with MSXML3.0+), Konqueror, Safari and Opera
 * @author: Copyright 2004-2007 Emmanouil Batsis, mailto: mbatsis at users full stop sourceforge full stop net
 * ====================================================================
 * Licence
 * ====================================================================
 * Sarissa is free software distributed under the GNU GPL version 2 (see <a href="gpl.txt">gpl.txt</a>) or higher, 
 * GNU LGPL version 2.1 (see <a href="lgpl.txt">lgpl.txt</a>) or higher and Apache Software License 2.0 or higher 
 * (see <a href="asl.txt">asl.txt</a>). This means you can choose one of the three and use that if you like. If 
 * you make modifications under the ASL, i would appreciate it if you submitted those.
 * In case your copy of Sarissa does not include the license texts, you may find
 * them online in various formats at <a href="http://www.gnu.org">http://www.gnu.org</a> and 
 * <a href="http://www.apache.org">http://www.apache.org</a>.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY,FITNESS FOR A PARTICULAR PURPOSE 
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR 
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * <p>Sarissa is a utility class. Provides "static" methods for DOMDocument, 
 * DOM Node serialization to XML strings and other utility goodies.</p>
 * @constructor
 * @static
 */
function Sarissa(){}
Sarissa.VERSION = "0.9.9.6";
Sarissa.PARSED_OK = "Document contains no parsing errors";
Sarissa.PARSED_EMPTY = "Document is empty";
Sarissa.PARSED_UNKNOWN_ERROR = "Not well-formed or other error";
Sarissa.IS_ENABLED_TRANSFORM_NODE = false;
Sarissa.REMOTE_CALL_FLAG = "gr.abiss.sarissa.REMOTE_CALL_FLAG";
/** @private */
Sarissa._lastUniqueSuffix = 0;
/** @private */
Sarissa._getUniqueSuffix = function(){
	return Sarissa._lastUniqueSuffix++;
};
/** @private */
Sarissa._SARISSA_IEPREFIX4XSLPARAM = "";
/** @private */
Sarissa._SARISSA_HAS_DOM_IMPLEMENTATION = document.implementation && true;
/** @private */
Sarissa._SARISSA_HAS_DOM_CREATE_DOCUMENT = Sarissa._SARISSA_HAS_DOM_IMPLEMENTATION && document.implementation.createDocument;
/** @private */
Sarissa._SARISSA_HAS_DOM_FEATURE = Sarissa._SARISSA_HAS_DOM_IMPLEMENTATION && document.implementation.hasFeature;
/** @private */
Sarissa._SARISSA_IS_MOZ = Sarissa._SARISSA_HAS_DOM_CREATE_DOCUMENT && Sarissa._SARISSA_HAS_DOM_FEATURE;
/** @private */
Sarissa._SARISSA_IS_SAFARI = navigator.userAgent.toLowerCase().indexOf("safari") != -1 || navigator.userAgent.toLowerCase().indexOf("konqueror") != -1;
/** @private */
Sarissa._SARISSA_IS_SAFARI_OLD = Sarissa._SARISSA_IS_SAFARI && (parseInt((navigator.userAgent.match(/AppleWebKit\/(\d+)/)||{})[1], 10) < 420);
/** @private */
Sarissa._SARISSA_IS_IE = document.all && window.ActiveXObject && navigator.userAgent.toLowerCase().indexOf("msie") > -1  && navigator.userAgent.toLowerCase().indexOf("opera") == -1;
/** @private */
Sarissa._SARISSA_IS_IE9 = Sarissa._SARISSA_IS_IE && (navigator.userAgent.toLowerCase().indexOf("msie 9") > -1 || navigator.userAgent.toLowerCase().indexOf("msie 10") > -1 || document.documentMode >= 9);
/** @private */
Sarissa._SARISSA_IS_OPERA = navigator.userAgent.toLowerCase().indexOf("opera") != -1;
if(!window.Node || !Node.ELEMENT_NODE){
    Node = {ELEMENT_NODE: 1, ATTRIBUTE_NODE: 2, TEXT_NODE: 3, CDATA_SECTION_NODE: 4, ENTITY_REFERENCE_NODE: 5,  ENTITY_NODE: 6, PROCESSING_INSTRUCTION_NODE: 7, COMMENT_NODE: 8, DOCUMENT_NODE: 9, DOCUMENT_TYPE_NODE: 10, DOCUMENT_FRAGMENT_NODE: 11, NOTATION_NODE: 12};
}

//This breaks for(x in o) loops in the old Safari
if(Sarissa._SARISSA_IS_SAFARI_OLD){
	HTMLHtmlElement = document.createElement("html").constructor;
	Node = HTMLElement = {};
	HTMLElement.prototype = HTMLHtmlElement.__proto__.__proto__;
	HTMLDocument = Document = document.constructor;
	var x = new DOMParser();
	XMLDocument = x.constructor;
	Element = x.parseFromString("<Single />", "text/xml").documentElement.constructor;
	x = null;
}
if(typeof XMLDocument == "undefined" && typeof Document !="undefined"){ XMLDocument = Document; } 

// IE initialization
if(Sarissa._SARISSA_IS_IE){
    // for XSLT parameter names, prefix needed by IE
    Sarissa._SARISSA_IEPREFIX4XSLPARAM = "xsl:";
    // used to store the most recent ProgID available out of the above
    var _SARISSA_DOM_PROGID = "";
    var _SARISSA_XMLHTTP_PROGID = "";
    var _SARISSA_DOM_XMLWRITER = "";
    /**
     * Called when the sarissa.js file is parsed, to pick most recent
     * ProgIDs for IE, then gets destroyed.
     * @memberOf Sarissa
     * @private
     * @param idList an array of MSXML PROGIDs from which the most recent will be picked for a given object
     * @param enabledList an array of arrays where each array has two items; the index of the PROGID for which a certain feature is enabled
     */
    Sarissa.pickRecentProgID = function (idList){
        // found progID flag
        var bFound = false, e;
        var o2Store;
        for(var i=0; i < idList.length && !bFound; i++){
            try{
                var oDoc = new ActiveXObject(idList[i]);
                o2Store = idList[i];
                bFound = true;
            }catch (objException){
                // trap; try next progID
                e = objException;
            }
        }
        if (!bFound) {
            throw "Could not retrieve a valid progID of Class: " + idList[idList.length-1]+". (original exception: "+e+")";
        }
        idList = null;
        return o2Store;
    };
    // pick best available MSXML progIDs
    _SARISSA_DOM_PROGID = null;
    _SARISSA_THREADEDDOM_PROGID = null;
    _SARISSA_XSLTEMPLATE_PROGID = null;
    _SARISSA_XMLHTTP_PROGID = null;
    // commenting the condition out; we need to redefine XMLHttpRequest 
    // anyway as IE7 hardcodes it to MSXML3.0 causing version problems 
    // between different activex controls 
    //if(!window.XMLHttpRequest){
    /**
     * Emulate XMLHttpRequest
     * @constructor
     */
    XMLHttpRequest = function() {
        if(!_SARISSA_XMLHTTP_PROGID){
            _SARISSA_XMLHTTP_PROGID = Sarissa.pickRecentProgID(["Msxml2.XMLHTTP.6.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"]);
        }
        return new ActiveXObject(_SARISSA_XMLHTTP_PROGID);
    };
    //}
    // we dont need this anymore
    //============================================
    // Factory methods (IE)
    //============================================
    // see non-IE version
    Sarissa.getDomDocument = function(sUri, sName){
        if(!_SARISSA_DOM_PROGID){
        	try{
        		_SARISSA_DOM_PROGID = Sarissa.pickRecentProgID(["Msxml2.DOMDocument.6.0", "Msxml2.DOMDocument.3.0", "MSXML2.DOMDocument", "MSXML.DOMDocument", "Microsoft.XMLDOM"]);
        	}catch(e){
        		_SARISSA_DOM_PROGID = "noActiveX";
        	}
        }

        // Not sure how far IE can carry this but try to do something useful when ActiveX is disabled
        var oDoc = _SARISSA_DOM_PROGID == "noActiveX" ? document.createElement("xml") : new ActiveXObject(_SARISSA_DOM_PROGID);
        // set validation off, make sure older IEs dont choke (no time or IEs to test ;-)
        try{
        	oDoc.validateOnParse = false; 
        	oDoc.resolveExternals = "false";
        	oDoc.setProperty("ProhibitDTD", false);
        }catch(e){}
        
        // if a root tag name was provided, we need to load it in the DOM object
        if (sName){
            // create an artifical namespace prefix 
            // or reuse existing prefix if applicable
            var prefix = "";
            if(sUri){
                if(sName.indexOf(":") > 1){
                    prefix = sName.substring(0, sName.indexOf(":"));
                    sName = sName.substring(sName.indexOf(":")+1); 
                }else{
                    prefix = "a" + Sarissa._getUniqueSuffix();
                }
            }
            // use namespaces if a namespace URI exists
            if(sUri){
                oDoc.loadXML('<' + prefix+':'+sName + " xmlns:" + prefix + "=\"" + sUri + "\"" + " />");
            } else {
                oDoc.loadXML('<' + sName + " />");
            }
        }
        return oDoc;
    };
    // see non-IE version   
    Sarissa.getParseErrorText = function (oDoc) {
        var parseErrorText = Sarissa.PARSED_OK;
        if(oDoc && oDoc.parseError && oDoc.parseError.errorCode && oDoc.parseError.errorCode != 0){
            parseErrorText = "XML Parsing Error: " + oDoc.parseError.reason + 
                "\nLocation: " + oDoc.parseError.url + 
                "\nLine Number " + oDoc.parseError.line + ", Column " + 
                oDoc.parseError.linepos + 
                ":\n" + oDoc.parseError.srcText +
                "\n";
            for(var i = 0;  i < oDoc.parseError.linepos;i++){
                parseErrorText += "-";
            }
            parseErrorText +=  "^\n";
        }
        else if(oDoc.documentElement === null){
            parseErrorText = Sarissa.PARSED_EMPTY;
        }
        return parseErrorText;
    };
    // see non-IE version
    Sarissa.setXpathNamespaces = function(oDoc, sNsSet) {
        oDoc.setProperty("SelectionLanguage", "XPath");
        oDoc.setProperty("SelectionNamespaces", sNsSet);
    };
    /**
     * A class that reuses the same XSLT stylesheet for multiple transforms.
     * @constructor
     */
    XSLTProcessor = function(){
        if(!_SARISSA_XSLTEMPLATE_PROGID){
            _SARISSA_XSLTEMPLATE_PROGID = Sarissa.pickRecentProgID(["Msxml2.XSLTemplate.6.0", "MSXML2.XSLTemplate.3.0"]);
        }
        this.template = new ActiveXObject(_SARISSA_XSLTEMPLATE_PROGID);
        this.processor = null;
    };
    /**
     * Imports the given XSLT DOM and compiles it to a reusable transform
     * <b>Note:</b> If the stylesheet was loaded from a URL and contains xsl:import or xsl:include elements,it will be reloaded to resolve those
     * @param {DOMDocument} xslDoc The XSLT DOMDocument to import
     */
    XSLTProcessor.prototype.importStylesheet = function(xslDoc){
        if(!_SARISSA_THREADEDDOM_PROGID){
            _SARISSA_THREADEDDOM_PROGID = Sarissa.pickRecentProgID(["MSXML2.FreeThreadedDOMDocument.6.0", "MSXML2.FreeThreadedDOMDocument.3.0"]);
        }
        xslDoc.setProperty("SelectionLanguage", "XPath");
        xslDoc.setProperty("SelectionNamespaces", "xmlns:xsl='http://www.w3.org/1999/XSL/Transform'");
        // convert stylesheet to free threaded
        var converted = new ActiveXObject(_SARISSA_THREADEDDOM_PROGID);
        // make included/imported stylesheets work if exist and xsl was originally loaded from url
        try{
            converted.resolveExternals = true; 
            converted.setProperty("AllowDocumentFunction", true); 
            converted.setProperty("AllowXsltScript", true);
        }
        catch(e){
            // Ignore. "AllowDocumentFunction" and "AllowXsltScript" is only supported in MSXML 3.0 SP4+ and 3.0 SP8+ respectively.
        } 
        if(xslDoc.url && xslDoc.selectSingleNode("//xsl:*[local-name() = 'import' or local-name() = 'include']") != null){
            converted.async = false;
            converted.load(xslDoc.url);
        } 
        else {
            converted.loadXML(xslDoc.xml);
        }
        converted.setProperty("SelectionNamespaces", "xmlns:xsl='http://www.w3.org/1999/XSL/Transform'");
        var output = converted.selectSingleNode("//xsl:output");
        //this.outputMethod = output ? output.getAttribute("method") : "html";
        if(output) {
            this.outputMethod = output.getAttribute("method");
        } 
        else {
            delete this.outputMethod;
        } 
        this.template.stylesheet = converted;
        this.processor = this.template.createProcessor();
        // for getParameter and clearParameters
        this.paramsSet = [];
    };

    /**
     * Transform the given XML DOM and return the transformation result as a new DOM document
     * @param {DOMDocument} sourceDoc The XML DOMDocument to transform
     * @return {DOMDocument} The transformation result as a DOM Document
     */
    XSLTProcessor.prototype.transformToDocument = function(sourceDoc){
        // fix for bug 1549749
        var outDoc;
        if(_SARISSA_THREADEDDOM_PROGID){
            this.processor.input=sourceDoc;
            outDoc=new ActiveXObject(_SARISSA_DOM_PROGID);
            this.processor.output=outDoc;
            this.processor.transform();
            return outDoc;
        }
        else{
            if(!_SARISSA_DOM_XMLWRITER){
                _SARISSA_DOM_XMLWRITER = Sarissa.pickRecentProgID(["Msxml2.MXXMLWriter.6.0", "Msxml2.MXXMLWriter.3.0", "MSXML2.MXXMLWriter", "MSXML.MXXMLWriter", "Microsoft.XMLDOM"]);
            }
            this.processor.input = sourceDoc;
            outDoc = new ActiveXObject(_SARISSA_DOM_XMLWRITER);
            this.processor.output = outDoc; 
            this.processor.transform();
            var oDoc = new ActiveXObject(_SARISSA_DOM_PROGID);
            oDoc.loadXML(outDoc.output+"");
            return oDoc;
        }
    };
    
    /**
     * Transform the given XML DOM and return the transformation result as a new DOM fragment.
     * <b>Note</b>: The xsl:output method must match the nature of the owner document (XML/HTML).
     * @param {DOMDocument} sourceDoc The XML DOMDocument to transform
     * @param {DOMDocument} ownerDoc The owner of the result fragment
     * @return {DOMDocument} The transformation result as a DOM Document
     */
    XSLTProcessor.prototype.transformToFragment = function (sourceDoc, ownerDoc) {
        this.processor.input = sourceDoc;
        this.processor.transform();
        var s = this.processor.output;
        var f = ownerDoc.createDocumentFragment();
        var container;
        if (this.outputMethod == 'text') {
            f.appendChild(ownerDoc.createTextNode(s));
        } else if (ownerDoc.body && ownerDoc.body.innerHTML) {
            container = ownerDoc.createElement('div');
            container.innerHTML = s;
            while (container.hasChildNodes()) {
                f.appendChild(container.firstChild);
            }
        }
        else {
            var oDoc = new ActiveXObject(_SARISSA_DOM_PROGID);
            if (s.substring(0, 5) == '<?xml') {
                s = s.substring(s.indexOf('?>') + 2);
            }
            var xml = ''.concat('<my>', s, '</my>');
            oDoc.loadXML(xml);
            container = oDoc.documentElement;
            while (container.hasChildNodes()) {
                f.appendChild(container.firstChild);
            }
        }
        return f;
    };
    
    /**
     * Set global XSLT parameter of the imported stylesheet. This method should 
     * only be used <strong>after</strong> the importStylesheet method for the 
     * context XSLTProcessor instance.
     * @param {String} nsURI The parameter namespace URI
     * @param {String} name The parameter base name
     * @param {String} value The new parameter value
     */
     XSLTProcessor.prototype.setParameter = function(nsURI, name, value){
         // make value a zero length string if null to allow clearing
         value = value ? value : "";
         // nsURI is optional but cannot be null
         if(nsURI){
             this.processor.addParameter(name, value, nsURI);
         }else{
             this.processor.addParameter(name, value);
         }
         // update updated params for getParameter
         nsURI = "" + (nsURI || "");
         if(!this.paramsSet[nsURI]){
             this.paramsSet[nsURI] = [];
         }
         this.paramsSet[nsURI][name] = value;
     };
    /**
     * Gets a parameter if previously set by setParameter. Returns null
     * otherwise
     * @param {String} name The parameter base name
     * @param {String} value The new parameter value
     * @return {String} The parameter value if reviously set by setParameter, null otherwise
     */
    XSLTProcessor.prototype.getParameter = function(nsURI, name){
        nsURI = "" + (nsURI || "");
        if(this.paramsSet[nsURI] && this.paramsSet[nsURI][name]){
            return this.paramsSet[nsURI][name];
        }else{
            return null;
        }
    };
    
    /**
     * Clear parameters (set them to default values as defined in the stylesheet itself)
     */
    XSLTProcessor.prototype.clearParameters = function(){
        for(var nsURI in this.paramsSet){
            for(var name in this.paramsSet[nsURI]){
                if(nsURI!=""){
                    this.processor.addParameter(name, "", nsURI);
                }else{
                    this.processor.addParameter(name, "");
                }
            }
        }
        this.paramsSet = [];
    };
}else{ /* end IE initialization, try to deal with real browsers now ;-) */
    if(Sarissa._SARISSA_HAS_DOM_CREATE_DOCUMENT){
        /**
         * <p>Ensures the document was loaded correctly, otherwise sets the
         * parseError to -1 to indicate something went wrong. Internal use</p>
         * @private
         */
        Sarissa.__handleLoad__ = function(oDoc){
            Sarissa.__setReadyState__(oDoc, 4);
        };
        /**
        * <p>Attached by an event handler to the load event. Internal use.</p>
        * @private
        */
        _sarissa_XMLDocument_onload = function(){
            Sarissa.__handleLoad__(this);
        };
        /**
         * <p>Sets the readyState property of the given DOM Document object.
         * Internal use.</p>
         * @memberOf Sarissa
         * @private
         * @param oDoc the DOM Document object to fire the
         *          readystatechange event
         * @param iReadyState the number to change the readystate property to
         */
        Sarissa.__setReadyState__ = function(oDoc, iReadyState){
            oDoc.readyState = iReadyState;
            oDoc.readystate = iReadyState;
            if (oDoc.onreadystatechange != null && typeof oDoc.onreadystatechange == "function") {
                oDoc.onreadystatechange();
            }
        };
        
        Sarissa.getDomDocument = function(sUri, sName){
            var oDoc = document.implementation.createDocument(sUri?sUri:null, sName?sName:null, null);
            if(!oDoc.onreadystatechange){
            
                /**
                * <p>Emulate IE's onreadystatechange attribute</p>
                */
                oDoc.onreadystatechange = null;
            }
            if(!oDoc.readyState){
                /**
                * <p>Emulates IE's readyState property, which always gives an integer from 0 to 4:</p>
                * <ul><li>1 == LOADING,</li>
                * <li>2 == LOADED,</li>
                * <li>3 == INTERACTIVE,</li>
                * <li>4 == COMPLETED</li></ul>
                */
                oDoc.readyState = 0;
            }
            oDoc.addEventListener("load", _sarissa_XMLDocument_onload, false);
            return oDoc;
        };
        if(window.XMLDocument){
            // do nothing
        }// TODO: check if the new document has content before trying to copynodes, check  for error handling in DOM 3 LS
        else if(Sarissa._SARISSA_HAS_DOM_FEATURE && window.Document && !Document.prototype.load && document.implementation.hasFeature('LS', '3.0')){
    		//Opera 9 may get the XPath branch which gives creates XMLDocument, therefore it doesn't reach here which is good
            /**
            * <p>Factory method to obtain a new DOM Document object</p>
            * @memberOf Sarissa
            * @param {String} sUri the namespace of the root node (if any)
            * @param {String} sUri the local name of the root node (if any)
            * @returns {DOMDOcument} a new DOM Document
            */
            Sarissa.getDomDocument = function(sUri, sName){
                var oDoc = document.implementation.createDocument(sUri?sUri:null, sName?sName:null, null);
                return oDoc;
            };
        }
        else {
            Sarissa.getDomDocument = function(sUri, sName){
                var oDoc = document.implementation.createDocument(sUri?sUri:null, sName?sName:null, null);
                // looks like safari does not create the root element for some unknown reason
                if(oDoc && (sUri || sName) && !oDoc.documentElement){
                    oDoc.appendChild(oDoc.createElementNS(sUri, sName));
                }
                return oDoc;
            };
        }
    }//if(Sarissa._SARISSA_HAS_DOM_CREATE_DOCUMENT)
}
//==========================================
// Common stuff
//==========================================
if(!window.DOMParser || Sarissa._SARISSA_IS_IE9){
    if(Sarissa._SARISSA_IS_SAFARI){
        /**
         * DOMParser is a utility class, used to construct DOMDocuments from XML strings
         * @constructor
         */
        DOMParser = function() { };
        /** 
        * Construct a new DOM Document from the given XMLstring
        * @param {String} sXml the given XML string
        * @param {String} contentType the content type of the document the given string represents (one of text/xml, application/xml, application/xhtml+xml). 
        * @return {DOMDocument} a new DOM Document from the given XML string
        */
        DOMParser.prototype.parseFromString = function(sXml, contentType){
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.open("GET", "data:text/xml;charset=utf-8," + encodeURIComponent(sXml), false);
            xmlhttp.send(null);
            return xmlhttp.responseXML;
        };
    }else if(Sarissa.getDomDocument && Sarissa.getDomDocument() && Sarissa.getDomDocument(null, "bar").xml){
        DOMParser = function() { };
        DOMParser.prototype.parseFromString = function(sXml, contentType){
            var doc = Sarissa.getDomDocument();
            try{
            	doc.validateOnParse = false; 
            	doc.setProperty("ProhibitDTD", false);
            }catch(e){}
            doc.loadXML(sXml);
            return doc;
        };
    }
}

if((typeof(document.importNode) == "undefined") && Sarissa._SARISSA_IS_IE){
    try{
        /**
        * Implementation of importNode for the context window document in IE.
        * If <code>oNode</code> is a TextNode, <code>bChildren</code> is ignored.
        * @param {DOMNode} oNode the Node to import
        * @param {boolean} bChildren whether to include the children of oNode
        * @returns the imported node for further use
        */
        document.importNode = function(oNode, bChildren){
            var tmp;
            if (oNode.nodeName=='#text') {
                return document.createTextNode(oNode.data);
            }
            else {
                if(oNode.nodeName == "tbody" || oNode.nodeName == "tr"){
                    tmp = document.createElement("table");
                }
                else if(oNode.nodeName == "td"){
                    tmp = document.createElement("tr");
                }
                else if(oNode.nodeName == "option"){
                    tmp = document.createElement("select");
                }
                else{
                    tmp = document.createElement("div");
                }
                if(bChildren){
                    tmp.innerHTML = oNode.xml ? oNode.xml : oNode.outerHTML;
                }else{
                    tmp.innerHTML = oNode.xml ? oNode.cloneNode(false).xml : oNode.cloneNode(false).outerHTML;
                }
                return tmp.getElementsByTagName("*")[0];
            }
        };
    }catch(e){ }
}
if(!Sarissa.getParseErrorText){
    /**
     * <p>Returns a human readable description of the parsing error. Usefull
     * for debugging. Tip: append the returned error string in a &lt;pre&gt;
     * element if you want to render it.</p>
     * <p>Many thanks to Christian Stocker for the initial patch.</p>
     * @memberOf Sarissa
     * @param {DOMDocument} oDoc The target DOM document
     * @returns {String} The parsing error description of the target Document in
     *          human readable form (preformated text)
     */
    Sarissa.getParseErrorText = function (oDoc){
        var parseErrorText = Sarissa.PARSED_OK;
        if((!oDoc) || (!oDoc.documentElement)){
            parseErrorText = Sarissa.PARSED_EMPTY;
        } else if(oDoc.documentElement.tagName == "parsererror"){
            parseErrorText = oDoc.documentElement.firstChild.data;
            parseErrorText += "\n" +  oDoc.documentElement.firstChild.nextSibling.firstChild.data;
        } else if(oDoc.getElementsByTagName("parsererror").length > 0){
            var parsererror = oDoc.getElementsByTagName("parsererror")[0];
            parseErrorText = Sarissa.getText(parsererror, true)+"\n";
        } else if(oDoc.parseError && oDoc.parseError.errorCode != 0){
            parseErrorText = Sarissa.PARSED_UNKNOWN_ERROR;
        }
        return parseErrorText;
    };
}
/**
 * Get a string with the concatenated values of all string nodes under the given node
 * @param {DOMNode} oNode the given DOM node
 * @param {boolean} deep whether to recursively scan the children nodes of the given node for text as well. Default is <code>false</code>
 * @memberOf Sarissa 
 */
Sarissa.getText = function(oNode, deep){
    var s = "";
    var nodes = oNode.childNodes;
    // opera fix, finds no child text node for attributes so we use .value
    if (oNode.nodeType == Node.ATTRIBUTE_NODE && nodes.length == 0) {
        return oNode.value;
    }
    // END opera fix
    for(var i=0; i < nodes.length; i++){
        var node = nodes[i];
        var nodeType = node.nodeType;
        if(nodeType == Node.TEXT_NODE || nodeType == Node.CDATA_SECTION_NODE){
            s += node.data;
        } else if(deep === true && (nodeType == Node.ELEMENT_NODE || nodeType == Node.DOCUMENT_NODE || nodeType == Node.DOCUMENT_FRAGMENT_NODE)){
            s += Sarissa.getText(node, true);
        }
    }
    return s;
};
if(!window.XMLSerializer && Sarissa.getDomDocument && Sarissa.getDomDocument("","foo", null).xml){
    /**
     * Utility class to serialize DOM Node objects to XML strings
     * @constructor
     */
    XMLSerializer = function(){};
    /**
     * Serialize the given DOM Node to an XML string
     * @param {DOMNode} oNode the DOM Node to serialize
     */
    XMLSerializer.prototype.serializeToString = function(oNode) {
        return oNode.xml;
    };
} else if (Sarissa._SARISSA_IS_IE9 && window.XMLSerializer) {
    // We save old object for any futur use with IE Document (not xml)
    IE9XMLSerializer= XMLSerializer;
    XMLSerializer = function(){ this._oldSerializer=new IE9XMLSerializer() };
    XMLSerializer.prototype.serializeToString = function(oNode) {
        if (typeof(oNode)=='object' && 'xml' in oNode) {
            return oNode.xml;
        } else {
            return this._oldSerializer.serializeToString(oNode);
        }
    };
}

/**
 * Strips tags from the given markup string. If the given string is 
 * <code>undefined</code>, <code>null</code> or empty, it is returned as is. 
 * @memberOf Sarissa
 * @param {String} s the string to strip the tags from
 */
Sarissa.stripTags = function (s) {
    return s?s.replace(/<[^>]+>/g,""):s;
};
/**
 * <p>Deletes all child nodes of the given node</p>
 * @memberOf Sarissa
 * @param {DOMNode} oNode the Node to empty
 */
Sarissa.clearChildNodes = function(oNode) {
    // need to check for firstChild due to opera 8 bug with hasChildNodes
    while(oNode.firstChild) {
        oNode.removeChild(oNode.firstChild);
    }
};
/**
 * <p> Copies the childNodes of nodeFrom to nodeTo</p>
 * <p> <b>Note:</b> The second object's original content is deleted before 
 * the copy operation, unless you supply a true third parameter</p>
 * @memberOf Sarissa
 * @param {DOMNode} nodeFrom the Node to copy the childNodes from
 * @param {DOMNode} nodeTo the Node to copy the childNodes to
 * @param {boolean} bPreserveExisting whether to preserve the original content of nodeTo, default is false
 */
Sarissa.copyChildNodes = function(nodeFrom, nodeTo, bPreserveExisting) {
    if(Sarissa._SARISSA_IS_SAFARI && nodeTo.nodeType == Node.DOCUMENT_NODE){ // SAFARI_OLD ??
    	nodeTo = nodeTo.documentElement; //Apparently there's a bug in safari where you can't appendChild to a document node
    }
    
    if((!nodeFrom) || (!nodeTo)){
        throw "Both source and destination nodes must be provided";
    }
    if(!bPreserveExisting){
        Sarissa.clearChildNodes(nodeTo);
    }
    var ownerDoc = nodeTo.nodeType == Node.DOCUMENT_NODE ? nodeTo : nodeTo.ownerDocument;
    var nodes = nodeFrom.childNodes;
    var i;
    if(typeof(ownerDoc.importNode) != "undefined")  {
        for(i=0;i < nodes.length;i++) {
            nodeTo.appendChild(ownerDoc.importNode(nodes[i], true));
        }
    } else {
        for(i=0;i < nodes.length;i++) {
            nodeTo.appendChild(nodes[i].cloneNode(true));
        }
    }
};

/**
 * <p> Moves the childNodes of nodeFrom to nodeTo</p>
 * <p> <b>Note:</b> The second object's original content is deleted before 
 * the move operation, unless you supply a true third parameter</p>
 * @memberOf Sarissa
 * @param {DOMNode} nodeFrom the Node to copy the childNodes from
 * @param {DOMNode} nodeTo the Node to copy the childNodes to
 * @param {boolean} bPreserveExisting whether to preserve the original content of nodeTo, default is
 */ 
Sarissa.moveChildNodes = function(nodeFrom, nodeTo, bPreserveExisting) {
    if((!nodeFrom) || (!nodeTo)){
        throw "Both source and destination nodes must be provided";
    }
    if(!bPreserveExisting){
        Sarissa.clearChildNodes(nodeTo);
    }
    var nodes = nodeFrom.childNodes;
    // if within the same doc, just move, else copy and delete
    if(nodeFrom.ownerDocument == nodeTo.ownerDocument){
        while(nodeFrom.firstChild){
            nodeTo.appendChild(nodeFrom.firstChild);
        }
    } else {
        var ownerDoc = nodeTo.nodeType == Node.DOCUMENT_NODE ? nodeTo : nodeTo.ownerDocument;
        var i;
        if(typeof(ownerDoc.importNode) != "undefined") {
           for(i=0;i < nodes.length;i++) {
               nodeTo.appendChild(ownerDoc.importNode(nodes[i], true));
           }
        }else{
           for(i=0;i < nodes.length;i++) {
               nodeTo.appendChild(nodes[i].cloneNode(true));
           }
        }
        Sarissa.clearChildNodes(nodeFrom);
    }
};

/** 
 * <p>Serialize any <strong>non</strong> DOM object to an XML string. All properties are serialized using the property name
 * as the XML element name. Array elements are rendered as <code>array-item</code> elements, 
 * using their index/key as the value of the <code>key</code> attribute.</p>
 * @memberOf Sarissa
 * @param {Object} anyObject the object to serialize
 * @param {String} objectName a name for that object, to be used as the root element name
 * @param {String} indentSpace Optional, the indentation space to use, default is an empty 
 *        string. A single space character is added in any recursive call.
 * @param {noolean} skipEscape Optional, whether to skip escaping characters that map to the 
 *        five predefined XML entities. Default is <code>false</code>.
 * @return {String} the XML serialization of the given object as a string
 */
Sarissa.xmlize = function(anyObject, objectName, indentSpace, skipEscape){
    indentSpace = indentSpace?indentSpace:'';
    var s = indentSpace  + '<' + objectName + '>';
    var isLeaf = false;
    if(!(anyObject instanceof Object) || anyObject instanceof Number || anyObject instanceof String || anyObject instanceof Boolean || anyObject instanceof Date){
        s += (skipEscape ? Sarissa.escape(anyObject) : anyObject);
        isLeaf = true;
    }else{
        s += "\n";
        var isArrayItem = anyObject instanceof Array;
        for(var name in anyObject){
        	// do not xmlize functions 
        	if (anyObject[name] instanceof Function){
        		continue;
        	} 
            s += Sarissa.xmlize(anyObject[name], (isArrayItem?"array-item key=\""+name+"\"":name), indentSpace + " ");
        }
        s += indentSpace;
    }
    return (s += (objectName.indexOf(' ')!=-1?"</array-item>\n":"</" + objectName + ">\n"));
};

/** 
 * Escape the given string chacters that correspond to the five predefined XML entities
 * @memberOf Sarissa
 * @param {String} sXml the string to escape
 */
Sarissa.escape = function(sXml){
    return sXml.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;");
};

/** 
 * Unescape the given string. This turns the occurences of the predefined XML 
 * entities to become the characters they represent correspond to the five predefined XML entities
 * @memberOf Sarissa
 * @param  {String}sXml the string to unescape
 */
Sarissa.unescape = function(sXml){
    return sXml.replace(/&apos;/g,"'").replace(/&quot;/g,"\"").replace(/&gt;/g,">").replace(/&lt;/g,"<").replace(/&amp;/g,"&");
};

/** @private */
Sarissa.updateCursor = function(oTargetElement, sValue) {
    if(oTargetElement && oTargetElement.style && oTargetElement.style.cursor != undefined ){
        oTargetElement.style.cursor = sValue;
    }
};

/**
 * Asynchronously update an element with response of a GET request on the given URL.  Passing a configured XSLT 
 * processor will result in transforming and updating oNode before using it to update oTargetElement.
 * You can also pass a callback function to be executed when the update is finished. The function will be called as 
 * <code>functionName(oNode, oTargetElement);</code>
 * @memberOf Sarissa
 * @param {String} sFromUrl the URL to make the request to
 * @param {DOMElement} oTargetElement the element to update
 * @param {XSLTProcessor} xsltproc (optional) the transformer to use on the returned
 *                  content before updating the target element with it
 * @param {Function} callback (optional) a Function object to execute once the update is finished successfuly, called as <code>callback(sFromUrl, oTargetElement)</code>. 
 *        In case an exception is thrown during execution, the callback is called as called as <code>callback(sFromUrl, oTargetElement, oException)</code>
 * @param {boolean} skipCache (optional) whether to skip any cache
 */
Sarissa.updateContentFromURI = function(sFromUrl, oTargetElement, xsltproc, callback, skipCache) {
    try{
        Sarissa.updateCursor(oTargetElement, "wait");
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", sFromUrl, true);
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4) {
            	try{
            		var oDomDoc = xmlhttp.responseXML;
	            	if(oDomDoc && Sarissa.getParseErrorText(oDomDoc) == Sarissa.PARSED_OK){
		                Sarissa.updateContentFromNode(xmlhttp.responseXML, oTargetElement, xsltproc);
        				if(callback){
		                	callback(sFromUrl, oTargetElement);
		                }
	            	}
	            	else{
	            		throw Sarissa.getParseErrorText(oDomDoc);
	            	}
            	}
            	catch(e){
            		if(callback){
			        	callback(sFromUrl, oTargetElement, e);
			        }
			        else{
			        	throw e;
			        }
            	}
            }
        };
        if (skipCache) {
             var oldage = "Sat, 1 Jan 2000 00:00:00 GMT";
             xmlhttp.setRequestHeader("If-Modified-Since", oldage);
        }
        xmlhttp.send("");
    }
    catch(e){
        Sarissa.updateCursor(oTargetElement, "auto");
        if(callback){
        	callback(sFromUrl, oTargetElement, e);
        }
        else{
        	throw e;
        }
    }
};

/**
 * Update an element's content with the given DOM node. Passing a configured XSLT 
 * processor will result in transforming and updating oNode before using it to update oTargetElement.
 * You can also pass a callback function to be executed when the update is finished. The function will be called as 
 * <code>functionName(oNode, oTargetElement);</code>
 * @memberOf Sarissa
 * @param {DOMNode} oNode the URL to make the request to
 * @param {DOMElement} oTargetElement the element to update
 * @param {XSLTProcessor} xsltproc (optional) the transformer to use on the given 
 *                  DOM node before updating the target element with it
 */
Sarissa.updateContentFromNode = function(oNode, oTargetElement, xsltproc) {
    try {
        Sarissa.updateCursor(oTargetElement, "wait");
        Sarissa.clearChildNodes(oTargetElement);
        // check for parsing errors
        var ownerDoc = oNode.nodeType == Node.DOCUMENT_NODE?oNode:oNode.ownerDocument;
        if(ownerDoc.parseError && ownerDoc.parseError.errorCode != 0) {
            var pre = document.createElement("pre");
            pre.appendChild(document.createTextNode(Sarissa.getParseErrorText(ownerDoc)));
            oTargetElement.appendChild(pre);
        }
        else {
            // transform if appropriate
            if(xsltproc) {
                oNode = xsltproc.transformToDocument(oNode);
            }
            // be smart, maybe the user wants to display the source instead
            if(oTargetElement.tagName.toLowerCase() == "textarea" || oTargetElement.tagName.toLowerCase() == "input") {
                oTargetElement.value = new XMLSerializer().serializeToString(oNode);
            }
            else {
                // ok that was not smart; it was paranoid. Keep up the good work by trying to use DOM instead of innerHTML
                try{
                    oTargetElement.appendChild(oTargetElement.ownerDocument.importNode(oNode, true));
                }
                catch(e){
                    oTargetElement.innerHTML = new XMLSerializer().serializeToString(oNode);
                }
            }
        }
    }
    catch(e) {
    	throw e;
    }
    finally{
        Sarissa.updateCursor(oTargetElement, "auto");
    }
};


/**
 * Creates an HTTP URL query string from the given HTML form data
 * @memberOf Sarissa
 * @param {HTMLFormElement} oForm the form to construct the query string from
 */
Sarissa.formToQueryString = function(oForm){
    var qs = "";
    for(var i = 0;i < oForm.elements.length;i++) {
        var oField = oForm.elements[i];
        var sFieldName = oField.getAttribute("name") ? oField.getAttribute("name") : oField.getAttribute("id"); 
        // ensure we got a proper name/id and that the field is not disabled
        if(sFieldName && 
            ((!oField.disabled) || oField.type == "hidden")) {
            switch(oField.type) {
                case "hidden":
                case "text":
                case "textarea":
                case "password":
                    qs += sFieldName + "=" + encodeURIComponent(oField.value) + "&";
                    break;
                case "select-one":
                    qs += sFieldName + "=" + encodeURIComponent(oField.options[oField.selectedIndex].value) + "&";
                    break;
                case "select-multiple":
                    for (var j = 0; j < oField.length; j++) {
                        var optElem = oField.options[j];
                        if (optElem.selected === true) {
                            qs += sFieldName + "[]" + "=" + encodeURIComponent(optElem.value) + "&";
                        }
                     }
                     break;
                case "checkbox":
                case "radio":
                    if(oField.checked) {
                        qs += sFieldName + "=" + encodeURIComponent(oField.value) + "&";
                    }
                    break;
            }
        }
    }
    // return after removing last '&'
    return qs.substr(0, qs.length - 1); 
};


/**
 * Asynchronously update an element with response of an XMLHttpRequest-based emulation of a form submission. <p>The form <code>action</code> and 
 * <code>method</code> attributess will be followed. Passing a configured XSLT processor will result in 
 * transforming and updating the server response before using it to update the target element.
 * You can also pass a callback function to be executed when the update is finished. The function will be called as 
 * <code>functionName(oNode, oTargetElement);</code></p>
 * <p>Here is an example of using this in a form element:</p>
 * <pre name="code" class="xml">
 * &lt;div id="targetId"&gt; this content will be updated&lt;/div&gt;
 * &lt;form action="/my/form/handler" method="post" 
 *     onbeforesubmit="return Sarissa.updateContentFromForm(this, document.getElementById('targetId'));"&gt;<pre>
 * <p>If JavaScript is supported, the form will not be submitted. Instead, Sarissa will
 * scan the form and make an appropriate AJAX request, also adding a parameter 
 * to signal to the server that this is an AJAX call. The parameter is 
 * constructed as <code>Sarissa.REMOTE_CALL_FLAG = "=true"</code> so you can change the name in your webpage
 * simply by assigning another value to Sarissa.REMOTE_CALL_FLAG. If JavaScript is not supported
 * the form will be submitted normally.
 * @memberOf Sarissa
 * @param {HTMLFormElement} oForm the form submition to emulate
 * @param {DOMElement} oTargetElement the element to update
 * @param {XSLTProcessor} xsltproc (optional) the transformer to use on the returned
 *                  content before updating the target element with it
 * @param {Function} callback (optional) a Function object to execute once the update is finished successfuly, called as <code>callback(oNode, oTargetElement)</code>. 
 *        In case an exception occurs during excecution and a callback function was provided, the exception is cought and the callback is called as 
 *        <code>callback(oForm, oTargetElement, exception)</code>
 */
Sarissa.updateContentFromForm = function(oForm, oTargetElement, xsltproc, callback) {
    try{
    	Sarissa.updateCursor(oTargetElement, "wait");
        // build parameters from form fields
        var params = Sarissa.formToQueryString(oForm) + "&" + Sarissa.REMOTE_CALL_FLAG + "=true";
        var xmlhttp = new XMLHttpRequest();
        var bUseGet = oForm.getAttribute("method") && oForm.getAttribute("method").toLowerCase() == "get"; 
        if(bUseGet) {
            xmlhttp.open("GET", oForm.getAttribute("action")+"?"+params, true);
        }
        else{
            xmlhttp.open('POST', oForm.getAttribute("action"), true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.setRequestHeader("Content-length", params.length);
            xmlhttp.setRequestHeader("Connection", "close");
        }
        xmlhttp.onreadystatechange = function() {
        	try{
	            if (xmlhttp.readyState == 4) {
	            	var oDomDoc = xmlhttp.responseXML;
	            	if(oDomDoc && Sarissa.getParseErrorText(oDomDoc) == Sarissa.PARSED_OK){
		                Sarissa.updateContentFromNode(xmlhttp.responseXML, oTargetElement, xsltproc);
        				if(callback){
		                	callback(oForm, oTargetElement);
		                }
	            	}
	            	else{
	            		throw Sarissa.getParseErrorText(oDomDoc);
	            	}
	            }
        	}
        	catch(e){
        		if(callback){
        			callback(oForm, oTargetElement, e);
        		}
        		else{
        			throw e;
        		}
        	}
        };
        xmlhttp.send(bUseGet?"":params);
    }
    catch(e){
        Sarissa.updateCursor(oTargetElement, "auto");
        if(callback){
        	callback(oForm, oTargetElement, e);
        }
        else{
        	throw e;
        }
    }
    return false;
};

/**
 * Get the name of a function created like:
 * <pre>function functionName(){}</pre>
 * If a name is not found, attach the function to 
 * the window object with a new name and return that
 * @param {Function} oFunc the function object
 */
Sarissa.getFunctionName = function(oFunc){
	if(!oFunc || (typeof oFunc != 'function' )){
		throw "The value of parameter 'oFunc' must be a function";
	}
	if(oFunc.name) { 
		return oFunc.name; 
	} 
	// try to parse the function name from the defintion 
	var sFunc = oFunc.toString(); 
	alert("sFunc: "+sFunc);
	var name = sFunc.substring(sFunc.indexOf('function') + 8 , sFunc.indexOf('(')); 
	if(!name || name.length == 0 || name == " "){
		// attach to window object under a new name
		name = "SarissaAnonymous" + Sarissa._getUniqueSuffix();
		window[name] = oFunc;
	}
	return name;
};

/**
 *
 */
Sarissa.setRemoteJsonCallback = function(url, callback, callbackParam) {
	if(!callbackParam){
		callbackParam = "callback";
	}
	var callbackFunctionName = Sarissa.getFunctionName(callback);
	//alert("callbackFunctionName: '" + callbackFunctionName+"', length: "+callbackFunctionName.length);
	var id = "sarissa_json_script_id_" + Sarissa._getUniqueSuffix(); 
	var oHead = document.getElementsByTagName("head")[0];
	var scriptTag = document.createElement('script');
	scriptTag.type = 'text/javascript';
	scriptTag.id = id;
	scriptTag.onload = function(){
		// cleanUp
		// document.removeChild(scriptTag);
	};
	if(url.indexOf("?") != -1){
		url += ("&" + callbackParam + "=" + callbackFunctionName);
	}
	else{
		url += ("?" + callbackParam + "=" + callbackFunctionName);
	}
	scriptTag.src = url;
  	oHead.appendChild(scriptTag);
  	return id;
};

//   EOF
// Global Variables
// var timeout = null;

// TODO - use sarissa for standard XMLHttpRequest Support.


// AJAX-JSF AJAX-like library, for communicate with view Tree on server side.

// Modified by Alexander J. Smirnov to use as JSF AJAX-like components. 

A4J.AJAX = {};

/**
 * XMLHttp transport class - incapsulate most of client-specifiv functions for call server requests.
 */
A4J.AJAX.XMLHttpRequest = function(query){
 	this._query = query;
 };

A4J.AJAX.XMLHttpRequest.prototype = {
	_query : null,
	_timeout : 0,
	_timeoutID : null,
	onready : null,
	_parsingStatus : Sarissa.PARSED_EMPTY,
	_errorMessage : "XML Response object not set",
	_contentType : null,
	_onerror : function(req,status,message) {
         // Status not 200 - error !!!! TODO - I18N ???
         // commented to resolve issue CSC-13321 , but should be un-commented in DEV-Envrionment
        //window.alert(message);
        },
	onfinish : null,
	options : {},
	domEvt : null,
	form : null,
	_request : null,
	_aborted : false,

	setRequestTimeout : function(timeout){
		this._timeout = timeout;
	},
	/**
	 * Send request to server with parameters from query ( POST or GET depend on client type )
	 */
	send : function(){
 	this._request = new XMLHttpRequest();
 	var _this = this;
 	this._request.onreadystatechange =  function(){
 				if(_this._aborted){return;};
          		LOG.debug("Reqest state : "+_this._request.readyState );
		      	if (_this._request.readyState == 4  ) {
          			LOG.debug("Reqest end with state 4");
		      		if(_this._timeoutID){
		      			window.clearTimeout(_this._timeoutID);
		      		}
		      		if(_this._request.status == 200){
						try {
            				LOG.debug("Response  with content-type: "+ _this.getResponseHeader('Content-Type'));
			            	LOG.debug("Full response content: ", _this.getResponseText());
						} catch(e) {
				// IE Can throw exception for any responses
						}
		      			// Prepare XML, if exist.
		      			if(_this._request.responseXML ){
			      			_this._parsingStatus = Sarissa.getParseErrorText(_this._request.responseXML);
			      			if(_this._parsingStatus == Sarissa.PARSED_OK && Sarissa.setXpathNamespaces ){
			      				Sarissa.setXpathNamespaces(_this._request.responseXML,"xmlns='http://www.w3.org/1999/xhtml'");
			      			}
		      			}
		      			if(_this.onready){
		      				_this.onready(_this);
		      			}
		      		} else {
		      			_this._errorMessage = "Reqest error, status : "+_this._request.status +" " + _this._request.statusText + "\n Message: " + _this.getResponseHeader("Ajax-Error-Message");
		      			LOG.error(_this._errorMessage);
		      			if(typeof(_this._onerror) == "function"){
		      				_this._onerror(_this,_this._request.status,_this._errorMessage);
		      			}
		      		}
		      		if(_this.onfinish){
		      			_this.onfinish(_this);
		      		}
			      	_this = undefined;
		      	}
	}; //this._onReady;
    try{
    LOG.debug("Start XmlHttpRequest");
    this._request.open('POST', this._query.getActionUrl("") , true);
    // Query use utf-8 encoding for prepare urlencode data, force request content-type and charset.
    var contentType = "application/x-www-form-urlencoded; charset=UTF-8";
	this._request.setRequestHeader( "Content-Type", contentType); 
    } catch(e){
    	// Opera 7-8 - force get
    	LOG.debug("XmlHttpRequest not support setRequestHeader - use GET instead of POST");
    	this._request.open('GET', this._query.getActionUrl("")+"?"+this._query.getQueryString() , true);
    }
    // send data.
    this._request.send(this._query.getQueryString());
    if(this._timeout > 0){
    	this._timeoutID = window.setTimeout(function(){
   			LOG.warn("request stopped due to timeout");
   			if(!_this._aborted){
			    A4J.AJAX.status(_this.containerId,_this.options.status,false);
		     if(typeof(A4J.AJAX.onAbort) == "function"){
   				A4J.AJAX.onAbort(_this);
		     }
   			}
			_this._aborted=true;
    		_this._request.abort();
    		if(_this._onerror){
      			_this._errorMessage = "Request timeout";
		    	_this._onerror(_this,500,_this._errorMessage);
		    }
      		if(_this.onfinish){
      			_this.onfinish(_this);
      		}
		    _this._request=undefined;
	      	_this = undefined;
    	},this._timeout);
    }
	},
	
	abort: function(){
   			if(!this._aborted){
		     A4J.AJAX.status(this.containerId,this.options.status,false);
		     if(typeof(A4J.AJAX.onAbort) == "function"){
   				A4J.AJAX.onAbort(this);
		     }
   			}
		this._aborted=true;
		if(this._request){
			try{
				this._request.abort();
				if(this._timeoutID){
					window.clearTimeout(this._timeoutID);
				}
			} catch (e){
				LOG.warn("Exception for abort current request "+e.Message);
			}
		}
	},
	getResponseText : function(){
		return this._request.responseText;
	},
	getError : function(){
		return this._errorMessage;
	},
	getParserStatus : function(){
		return this._parsingStatus;
	},
	getContentType : function(){
		if(!this._contentType){
			var contentType = this.getResponseHeader('Content-Type');
			if(contentType){
				var i = contentType.indexOf(';');
				if( i >= 0 ){
					this._contentType = contentType.substring(0,i);
				} else {
					this._contentType = contentType;				
				}
			} else {
				this._contentType="text/html";
			}
		}
		return this._contentType;
	},
	getResponseHeader : function(name){
		var result;
		// Different behavior - for non-existing headers, Firefox throws exception,
		// IE return "" , 
		try{
			result = this._request.getResponseHeader(name);
			if(result === ""){
				result = undefined;
			}
		} catch(e) {
		}
		if(!result){
		// Header not exist or Opera <=8.0 error. Try to find <meta > tag with same name.
			LOG.debug("Header "+name+" not found, search in <meta>");
			if(this._parsingStatus == Sarissa.PARSED_OK){
				var metas = this.getElementsByTagName("meta");
				for(var i = 0; i < metas.length;i++){
					var meta = metas[i];
					LOG.debug("Find <meta name='"+meta.getAttribute('name')+"' content='"+meta.getAttribute('content')+"'>");
					if(meta.getAttribute("name") == name){
						result = meta.getAttribute("content");
						break;
					}
				}
			}
			
		}
		return result;
	},
	/**
	 * get elements with elementname in responseXML or, if present - in element.
	 */
	getElementsByTagName : function(elementname,element){
		if(!element){
			element = this._request.responseXML;
		}
		LOG.debug("search for elements by name '"+elementname+"' "+" in element "+element.nodeName);
   		var elements; 
	    try
	    {
	        elements = element.selectNodes(".//*[local-name()=\""+ 
	                                           elementname +"\"]");
			LOG.debug("selectNodes found "+elements.length);
	    }
	    catch (ex) {
			elements = element.getElementsByTagName(elementname);
			LOG.debug("getElementsByTagName found "+elements.length);	    	
	    }
//	    return document.getElementsByTagName(tagName);
//		elements = element.getElementsByTagNameNS("http://www.w3.org/1999/xhtml",elementname);
//		LOG.debug("getElementsByTagNameNS found "+elements.length);
		return elements;
	},
	/**
	 * Find element in response by ID. Since in IE response not validated, use selectSingleNode instead.
	 */
	getElementById : function(id){
		// first attempt - .getElementById.
		var oDoc = this._request.responseXML;
    	if(typeof(oDoc.getElementById) != 'undefined') {
			LOG.debug("call getElementById for id= "+id);
    		return  oDoc.getElementById(id);
    	} 
    	else if(typeof(oDoc.selectSingleNode) != "undefined") {
			LOG.debug("call selectSingleNode for id= "+id);
    		return oDoc.selectSingleNode("//*[@id='"+id+"']"); /* XPATH istead of ID */
    	}
    	// nodeFromID not worked since XML validation disabled by
    	// default for MS 
    	else if(typeof(oDoc.nodeFromID) != "undefined") {
			LOG.debug("call nodeFromID for id= "+id);
    		return oDoc.nodeFromID(id);
    	} 
		LOG.error("No functions for getElementById found ");
    	return null;
		
	},
	
	getJSON : function(id){
		     	  	var data;
        	  		var dataElement = this.getElementById(id);
        	  		if(dataElement){
        	  			try {
        	  				data = Sarissa.getText(dataElement,true);
        	  				data = window.eval('('+data+')');
        	  			} catch(e){
        	  				LOG.error("Error on parsing JSON data "+e.message,data);
        	  			}
        	  		}
		return data;
	},
	
	evalScripts : function(node){
			var newscripts = this.getElementsByTagName("script",node);
	        LOG.debug("Scripts in updated part count : " + newscripts.length);
			if( newscripts.length > 0 ){
		      window.setTimeout(function() {
		        for (var i = 0; i < newscripts.length; i++){
		          var newscript = Sarissa.getText( newscripts[i],true ) ; // TODO - Mozilla disable innerHTML in XML page ..."";
	    	      try {
	    		        LOG.debug("Evaluate script replaced area in document: ", newscript);
    			  		if (window.execScript) {
				      		window.execScript( newscript );
    			  		} else {
     	      				window.eval(newscript);
    			  		}
		        	  } catch(e){
		          		LOG.error("ERROR Evaluate script:  Error name: " + e.name + e.message?". Error message: "+e.message:"");
		          	  }
			    }
			    newscripts = null;
			  },50);
		    }
		
	},
	/**
	 * Update DOM element with given ID by element with same ID in parsed responseXML
	 */
	updatePagePart : function(id){
		var newnode = this.getElementById(id);
		if( ! newnode ) { LOG.error("New node for ID "+id+" is not present in response");return;}
		var oldnode = document.getElementById(id);
		if ( oldnode  ) {
			var anchor = oldnode.parentNode ;
    // need to check for firstChild due to opera 8 bug with hasChildNodes
			Sarissa.clearChildNodes(oldnode);
			if(oldnode.outerHTML){
   		        LOG.debug("Replace content of node by outerHTML()");
				oldnode.outerHTML = new XMLSerializer().serializeToString(newnode); 
			} else {
		    	var importednode ;
	    		importednode = document.importNode(newnode, true);
   		        LOG.debug("Replace content of node by replaceChild()");
				anchor.replaceChild(importednode,oldnode);
			} 
	// re-execute all script fragments in imported subtree...
	// TODO - opera 8 run scripts at replace content stage.
			if(!A4J.AJAX._scriptEvaluated){
				this.evalScripts(newnode);
			}
	        LOG.debug("Update part of page for Id: "+id + " successful");
		} else {
			LOG.warn("Node for replace by response with id "+id+" not found in document");
		}
		
	},
	
	appendNewHeadElements : function(){
        	  // Append scripts and styles to head, if not presented in page before.
        	  this._appendNewElements("script","src",["type","language","charset"]);
        	  this._appendNewElements("link","href",["type","rel","rev","media"]);		
	}, 
	
	_appendNewElements : function(tag,href,attributes){
		      var newscripts = this.getElementsByTagName(tag);
        	  var oldscripts = document.getElementsByTagName(tag);
        	  var head = document.getElementsByTagName("head")[0]||document.documentElement;
        	  for(var i=0 ; i<newscripts.length;i++){
        	  	 var element = newscripts[i];
        	  	 var src = element.getAttribute(href);
        	  	 if(src){
        	  	 	var exist = false;
        	  	 	LOG.debug("<"+tag+"> in response with src="+src);
        	  				for(var j = 0 ; j < oldscripts.length; j++){
        	  					if(this._noSessionHref(src) == this._noSessionHref(oldscripts[j].getAttribute(href))){
        	  						LOG.debug("Such element exist in document");
        	  						exist = true;
        	  						break;
        	  					}
        	  				}
        	  		 if(!exist){
        	  		 	// var script = window.document.importNode(element,true); //
        	  		 	var script = document.createElement(tag);
        	  		 	script.setAttribute(href,src);
        	  		 	for(var j = 0 ; j < attributes.length; j++){
        	  		 		this._copyAttribute(element,script,attributes[j]);
        	  		 	}
        	  		 	LOG.debug("append element to document");
        	  		 	head.appendChild(script);
        	  		 }     	  	 	
        	  	 }
        	  }
		
	},
	
	_noSessionHref : function(href){
		var cref = href;
		if(href){
		var sessionid = href.lastIndexOf(";jsessionid=");
		if(sessionid>0){
			cref = href.substring(0,sessionid);
			var params = href.lastIndexOf("?");
			if(params>sessionid){
				cref=cref+href.substring(params);
			}
		}
		}
		return cref; 		
	},
	
	_copyAttribute : function(src,dst,attr){
		var value = src.getAttribute(attr);
		if(value){
			dst.setAttribute(attr,value);
		}
	}

};
// eventsQueues for ajax submit events.
A4J.AJAX._eventsQueues={};
  
//Listeners should be notified
A4J.AJAX.Listener = function(onafterajax){
	this.onafterajax = onafterajax;
};

A4J.AJAX._listeners= [];
A4J.AJAX.AddListener = function(listener){
	A4J.AJAX._listeners.push(listener);	
};
A4J.AJAX.removeListeners = function(listener){
	A4J.AJAX._listeners = [];	
};
// pollers timerId's
A4J.AJAX._pollers = {};
/*
 * 
 * 
 */
A4J.AJAX.Poll =  function( containerId, form, options ) {
	A4J.AJAX.StopPoll(options.pollId);
	options.onerror = function(){
		// For error, re-submit request.
		A4J.AJAX.Poll(containerId,form,options);
	};
	A4J.AJAX._pollers[options.pollId] = window.setTimeout(function(){
		A4J.AJAX._pollers[options.pollId]=undefined;
		if((typeof(options.onsubmit) == 'function') && (!options.onsubmit())){
			// Onsubmit disable current poll, start next interval.
			A4J.AJAX.Poll(containerId,form,options);			
		} else {
			A4J.AJAX.SubmitRequest(containerId,form,null,options);
		}
	},options.pollinterval);
};

A4J.AJAX.StopPoll =  function( Id ) {
	if(A4J.AJAX._pollers[Id]){
		window.clearTimeout(A4J.AJAX._pollers[Id]);
		A4J.AJAX._pollers[Id] = undefined;
	}
};

/*
 * 
 * 
 */
A4J.AJAX.Push =  function( containerId, form, options ) {
	A4J.AJAX.StopPush(options.pushId);
	options.onerror = function(){
		// For error, re-submit request.
		A4J.AJAX.Push(containerId,form,options);
	};
	A4J.AJAX._pollers[options.pushId] = window.setTimeout(function(){
		var request = new XMLHttpRequest();
		request.onreadystatechange =  function(){
		      	if (request.readyState == 4  ) {
		      		try {
		      		if(request.status == 200){
//		      			A4J.AJAX._pollers[options.pushId]=undefined;
				        A4J.AJAX.SubmitRequest(containerId,form||options.dummyForm,null,options);
		      		}
		      		} catch(e){
		      			// Network error.
		      		}
		      		// Re-send request.
		      		A4J.AJAX.Push( containerId, form, options );
		      		request=null;
//					A4J.AJAX._pollers[options.pushId] = window.setTimeout(function(){
//						A4J.AJAX.SendPush( request,options );
//					},options.pushinterval);
		      	}
		}
		A4J.AJAX.SendPush( request,options );
	},options.pushinterval);
};

A4J.AJAX.SendPush =  function( request,options ) {
	    var url = options.pushUrl || options.actionUrl;
		request.open('HEAD', url , true);
		request.setRequestHeader( "Ajax-Push-Key", options.pushId);
		if(options.timeout){
			request.setRequestHeader( "Timeout", options.timeout);			
		}
		request.send();	
}

A4J.AJAX.StopPush =  function( Id ) {
	if(A4J.AJAX._pollers[Id]){
		window.clearTimeout(A4J.AJAX._pollers[Id]);
		A4J.AJAX._pollers[Id] = undefined;
	}
};



A4J.AJAX.CloneObject =  function( obj, noFunctions ) {
	var cloned = {};
	for( var n in obj ){
		if(noFunctions && typeof(evt[prop]) == 'function'){
			continue;
		}
		cloned[n]=obj[n];
	}
	return cloned;
}


A4J.AJAX.SubmitForm =  function( containerId, form, options ) {
	var opt = A4J.AJAX.CloneObject(options);
	// Setup active control if form submitted by button.
	if(A4J._formInput){
		LOG.debug("Form submitted by button "+A4J._formInput.id);
		opt.control = A4J._formInput;
		A4J._formInput = null;
		opt.submitByForm=true;
	}
	A4J.AJAX.Submit(containerId,form,null,opt);
}
  
// Submit or put in queue request. It not full queues - framework perform waiting only one request to same queue, new events simple replace last.
// If request for same queue already performed, replace with current parameters.
A4J.AJAX.Submit =  function( containerId, form, evt , options ) {
	var domEvt;
	evt = evt || window.event || null;
	if(evt){
		// Create copy of event object, since most of properties undefined outside of event capture.
		try {
			domEvt = A4J.AJAX.CloneObject(evt,false);
		} catch(e){
			LOG.warn("Exception on clone event");
		}
		LOG.debug("Have Event "+domEvt+" with properties: target: "+domEvt.target+", srcElement: "+domEvt.srcElement+", type: "+domEvt.type);
	}
    if(options.eventsQueue){
      var eventsQueue =  A4J.AJAX._eventsQueues[options.eventsQueue];
       if( eventsQueue )   {
       		 var eventsCount = eventsQueue.options.eventsCount||1;
	         eventsQueue.wait=true;
	         eventsQueue.containerId=containerId;
	         eventsQueue.form=form;
	         eventsQueue.domEvt=domEvt;
	         eventsQueue.options=options;	         
	         eventsQueue.options.eventsCount = eventsCount+1;
	       if(options.ignoreDupResponses && eventsQueue.request){
		        LOG.debug("Abort uncompleted request in queue "+options.eventsQueue);
	       		eventsQueue.request.abort();
	       		eventsQueue.request=false;
	            eventsQueue.wait=false;
       	        if( options.requestDelay ){
		    		window.setTimeout(function() {
		        	LOG.debug("End delay waiting, make request in queue "+options.eventsQueue);
		    		A4J.AJAX.SubmiteventsQueue(A4J.AJAX._eventsQueues[options.eventsQueue]);
		    		},options.requestDelay);
	       			LOG.debug("Create new waiting for request in queue "+options.eventsQueue);
	       			return;
       	   		}
	       } else {
	         LOG.debug("Put new event to queue "+options.eventsQueue);
	         return;
	       }
       } else {
       	   var queue = { wait : false,  containerId : containerId , form : form, domEvt : domEvt, options : options};
	       A4J.AJAX._eventsQueues[options.eventsQueue] = queue;
       	   if( options.requestDelay ){
		    window.setTimeout(function() {
		        LOG.debug("End delay waiting, make request in queue "+options.eventsQueue);
		    	A4J.AJAX.SubmiteventsQueue(A4J.AJAX._eventsQueues[options.eventsQueue]);
		    },options.requestDelay);
	       LOG.debug("Event occurs, create waiting for request in queue "+options.eventsQueue);
	       return;
       	   }
       } 
   }
  A4J.AJAX.SubmitRequest( containerId, form, domEvt , options );
};

A4J.AJAX.SubmiteventsQueue =  function( eventsQueue ) {
	// Clear wait flag to avoid resend same request.
	 eventsQueue.wait=false;
	 A4J.AJAX.SubmitRequest( eventsQueue.containerId, eventsQueue.form ,eventsQueue.domEvt , eventsQueue.options );	 
};
  // Main request submitting functions.
  // parameters :
  // form - HtmlForm object for submit.
  // control - form element, called request, or, clientID for JSF view.
  // affected - Array of ID's for DOM Objects, updated after request. Override
  // list of updated areas in response.
  // statusID - DOM id request status tags.
  // oncomplete - function for call after complete request.
A4J.AJAX.SubmitRequest =  function( containerId, formId ,domEvt , options ) {
    // First - run onsubmit event for client-side validation.
	LOG.debug("NEW AJAX REQUEST !!! with form :"+formId );
//	var	form = A4J.AJAX.locateForm(event);
	var form = window.document.getElementById(formId);
	if( (!form || form.nodeName.toUpperCase() != "FORM") && domEvt ) {
		var srcElement = domEvt.target||domEvt.srcElement||null;
		if(srcElement){
			form = A4J.AJAX.locateForm(srcElement);
		};
	};
	// TODO - test for null of form object
    if(!options.submitByForm && form && form.onsubmit) {
		LOG.debug("Form have onsubmit function, call it" );
    	if( form.onsubmit() == false ){
    		return false;
    	};
    };
    var tosend = new A4J.Query(containerId, form);
    tosend.appendFormControls(options.single);
    if(options.control){
    	tosend.appendControl(options.control,true);
    };
    if(options.parameters){
    	tosend.appendParameters(options.parameters);
    }; 
    if(options.eventsCount){
    	tosend.appendParameter("AJAX:EVENTS_COUNT",options.eventsCount);
    };
    if(options.actionUrl){
    	tosend.setActionUrl(options.actionUrl);
    };
    // build  xxxHttpRequest. by Sarissa / JSHttpRequest class always defined.
    var req = new A4J.AJAX.XMLHttpRequest(tosend);
    
    req.options = options;
    req.containerId = containerId;
    req.domEvt = domEvt;
    req.form = form;
    if(options.timeout){
    	req.setRequestTimeout(options.timeout);
    };
    
    // Event handler for process response result.
    req.onready = A4J.AJAX.processResponse;
    
    if(options.onerror){
    	req._onerror = options.onerror;
    } else if(typeof(A4J.AJAX.onError)== "function"){
		req._onerror = A4J.AJAX.onError;
    }
	req.onfinish = A4J.AJAX.finishRequest;   

    A4J.AJAX.status(containerId,options.status,true);
    req.send();
    if(options.eventsQueue){
      var eventsQueue =  A4J.AJAX._eventsQueues[options.eventsQueue];
       if( eventsQueue )   {
       	  eventsQueue.request=req;
       }
    }
    
    return false;
  };

        

A4J.AJAX.processResponse = function(req) {
    	    var options = req.options;
			var ajaxResponse = req.getResponseHeader('Ajax-Response');
          	if( ajaxResponse != "true"){
          	  	// NO Ajax header - new page.
          	  	LOG.warn("No ajax response header ");
         		var loc = req.getResponseHeader("Location");
	         	try{
	         		if(ajaxResponse == 'redirect' && loc){
						var expiredMsg = req.getResponseHeader('Ajax-Expired');
	         			if(expiredMsg){
	         				loc = A4J.AJAX.onExpired(loc,expiredMsg);
	         				if(!loc){
	         					return;
	         				}
	         			} 
	         			window.location = loc;
	         		} else if(ajaxResponse == "reload"){
       					window.location.reload(true);
	         		} else {
	         			var isIE = _SARISSA_IS_IE;
						// maksimkaszynski
						//Prevent "Permission denied in IE7"
						//Reset calling principal
						if (isIE) {
							var oldDocOpen = window.document.open;
							window.document.open = function() {
								oldDocOpen.apply(this, arguments);
							}
						}
						// /maksimkaszynski
						window.setTimeout(function() {
							var isDocOpen=false;
							try {  	
		          				window.document.open(req.getContentType(),true);
		          				isDocOpen=true;
		          				window.document.write(req.getResponseText());
		          				window.document.close();
	          				if(isIE){
	          			// For Ie , scripts on page not activated.
	          					window.location.reload(false);
	          				}
							} catch(e) {
								if(isDocOpen){
		          					window.document.close();
								}
								// Firefox/Mozilla in XHTML case don't support document.write()
//								window.location = "data:"+req.getContentType()+";charset=utf-8," + encodeURIComponent(req.getResponseText())
								// Use dom manipulation instead.
								var	oDomDoc = (new DOMParser()).parseFromString(req.getResponseText(), "application/xhtml+xml");
						    	var node = window.document.importNode(oDomDoc.documentElement, true);
								window.document.replaceChild(node,window.document.documentElement);
								// TODO - scripts reloading ?
							}
	          			},30);
	         		}
	         	} catch(e){
	         		LOG.error("Error redirect to new location ");
	         	}
          	} else {
			  if(req.getParserStatus() == Sarissa.PARSED_OK){
				var idsFromResponse = req.getResponseHeader("Ajax-Update-Ids");
        // 3 strategy for replace :
        // if setted affected parameters - replace its
        	  	if( options.affected ) {
	        	  	req.appendNewHeadElements();
					for ( var k =0; k < options.affected.length ; k++ ) {
						LOG.debug("Update page part from call parameter for ID " + options.affected[k]);
						req.updatePagePart(options.affected[k]);
					};
		// if resopnce contains element with ID "ajax:update" get id's from
		// child text element . like :
		// <div id="ajax:update" style="display none" >
		//   <span>_id1:1234</span>
		//    .........
		// </div>
		//
        	  } else if( idsFromResponse != "" ) {
				LOG.debug("Update page by list of rendered areas from response " + idsFromResponse );
        	  // Append scripts and styles to head, if not presented in page before.
        	  	req.appendNewHeadElements();
				var childs = idsFromResponse.split(",");
	        	for ( var k=0 ; k < childs.length ; k++ ) {
	        		var id = childs[k];
	        		LOG.debug("Attempt to update part of page for Id: "+id);
					if ( id ) {req.updatePagePart(id);};
				};
        	  } else {
        			// if none of above - error ?
					// A4J.AJAX.replace(form.id,A4J.AJAX.findElement(form.id,xmlDoc));
					LOG.warn("No information in response about elements to replace");
        	  }
        	  // Replace client-side hidden inputs for JSF View state.
        	  var idsSpan = req.getElementById("ajax-update-ids");
	          	LOG.debug("Hidden JSF state fields: ");//+idsSpan);
        	  if(idsSpan != null){
        	  	// For a portal case, replace content in the current window only.
			        var namespace = options.parameters['org.ajax4jsf.portlet.NAMESPACE'];
			        LOG.debug("Namespace for hidden view-state input fields is "+namespace);
			        var anchor = namespace?window.document.getElementById(namespace):window.document;        	  	    
        	  		var inputs = anchor.getElementsByTagName("input");
        	  		var newinputs = req.getElementsByTagName("input",idsSpan);
        	  		A4J.AJAX.replaceViewState(inputs,newinputs);
        	  		// For any cases, new state can be in uppercase element
        	  		newinputs = req.getElementsByTagName("INPUT",idsSpan);
        	  		A4J.AJAX.replaceViewState(inputs,newinputs);
        	  }
        	  // Process listeners.
        	  for(var li = 0; li < A4J.AJAX._listeners.length; li++){
        	  	var listener = A4J.AJAX._listeners[li];
        	  	if(listener.onafterajax){
        	  		// Evaluate data as JSON String.
        	  		var data = req.getJSON('_ajax:data');
        	  		listener.onafterajax(req,req.domEvt,data);
        	  	}
        	  }
        	  // Set focus, if nessesary.
        	  var focusId = req.getJSON("_A4J.AJAX.focus");
        	  if(focusId){
        	  	LOG.debug("focus must be set to control "+focusId);
        	  	var focusElement=false;
        	  	if(req.form){
        	  		// Attempt to get form control for name. By Richfaces naming convensions, 
        	  		// complex component must set clientId as DOM id for a root element ,
        	  		// and as input element name.
        	  		focusElement = req.form.elements[focusId];
        	  	}
        	  	if(!focusElement){
        	  		// If not found as control element, search in DOM.
        	  		LOG.debug("No control element "+focusId+" in submitted form");
        	  		focusElement = document.getElementById(focusId);
        	  	}
        	  	if(focusElement){
        	  		LOG.debug("Set focus to control ");
        	  		focusElement.focus();
        	  	} else {
        	  		LOG.warn("Element for set focus not found");
        	  	}
        	  } else {
        	  	LOG.debug("No focus information in response");        	  	
        	  }
           } else {
           // No response XML
   			LOG.error( "Error parsing XML" );
			LOG.error("Parse Error: " + req.getParserStatus());
           }
          }
         }; 
         
A4J.AJAX.onExpired = function(loc,expiredMsg){
	if(window.confirm(expiredMsg)){
		return loc;
	} else {
		return undefined;
	}
};
         

A4J.AJAX.replaceViewState = function(inputs,newinputs){
	      	  		LOG.debug("Replace value for inputs: "+inputs.length + " by new values: "+ newinputs.length);
        	  		if( (newinputs.length > 0) && (inputs.length > 0) ){
        	  			for(var i = 0 ; i < newinputs.length; i++){
        	  				var newinput = newinputs[i];
        	  				LOG.debug("Input in response: "+newinput.getAttribute("name"));
        	  				for(var j = 0 ; j < inputs.length; j++){
        	  					var input = inputs[j];
        	  					if(input.name == newinput.getAttribute("name")){
	        	  				LOG.debug("Found same input on page with type: "+input.type);
        	  						input.value = newinput.getAttribute("value");
        	  					}
        	  				}
        	  			}
        	  		}
	
};
/**
 * 
 */
A4J.AJAX.finishRequest = function(req){
   	    var options = req.options;
       // mark status object ( if any ) for complete request ;
	     A4J.AJAX.status(req.containerId,options.status,false);
	     // we can set listener for complete request - for example,
	     // it can shedule next request for update page.
	     if(options.oncomplete){
   			LOG.debug( "Call request oncomplete function after processing updates" );
   			window.setTimeout(function(){
	     		options.oncomplete(req,req.domEvt,req.getJSON('_ajax:data'));},
	     	50);
	     	
	      }
	      // If we have events in queue - send next request.
          if(options.eventsQueue){
                var eventsQueue =  A4J.AJAX._eventsQueues[options.eventsQueue];
                 if( eventsQueue  )   {
              		A4J.AJAX._eventsQueues[options.eventsQueue]=false;
           	    	if(eventsQueue.wait){
	       				LOG.debug("Queue not empty, execute next request in queue "+options.eventsQueue);
           	     		A4J.AJAX.SubmiteventsQueue(eventsQueue);
           	   			}
          		}
          	}
          };
          
	// Locate enclosing form for object.
A4J.AJAX.locateForm = function(obj){
		
		var parent = obj;
		 while(parent && parent.nodeName.toLowerCase() != 'form'){
			parent = parent.parentNode;
		};
		return parent;
	
	};
	
A4J.AJAX.getElementById = function(id,options){
	var namespace = options['org.ajax4jsf.portlet.NAMESPACE'];
	var anchor = namespace?window.document.getElementById(namespace):window.document;
	var element;
	if(anchor){
		element = anchor.getElementById(id);
	} else {
		LOG.error("No root element for portlet namespace "+namespace+" on page");
	}
	return element;
}
    
    // hash for requests count for all ID's
A4J.AJAX._requestsCounts = {};
    // Change status object on start/stop request.
    // on start, document object with targetID+".start" make visible,
    // document object with targetID+".stop" make invisible.
    // on stop - inverse.
A4J.AJAX.status = function(regionID,targetID,start){
	try {
    	var elem;
    	targetID = targetID || regionID +":status";
	    A4J.AJAX._requestsCounts[targetID]=(A4J.AJAX._requestsCounts[targetID]||0)+(start?1:-1);
	    if(A4J.AJAX._requestsCounts[targetID]>0){
	    	elem = document.getElementById(targetID+".stop");
	    	if(elem){elem.style.display="none";}
	    	elem = document.getElementById(targetID+".start");
	    	if(elem){
	    		elem.style.display="";
	    		if(typeof(elem.onstart) == 'function'){
	    			elem.onstart();
	    		}
	    	}
	    }else{
	    	elem = document.getElementById(targetID+".start");
	    	if(elem){elem.style.display="none";}
	    	elem = document.getElementById(targetID+".stop");
	    	if(elem){
	    		elem.style.display="";
	    		if(typeof(elem.onstop) == 'function'){
	    			elem.onstop();
	    		}
	    	}
	    }
    } catch(e){
    	LOG.error("Exception on status change: ");
    }
};
    
	

  
// Class for build query string.
A4J.Query = function(containerId,form){ 
	// For detect AJAX Request.
	 this._query = {AJAXREQUEST : containerId};
	 this._oldSubmit = null ;	
	 this._form = form ;
	 this._actionUrl = ( this._form.action)?this._form.action:this._form;
	};

A4J.Query.prototype = {
	 _form : null,
	 _actionUrl : null,
	 _ext	: "",
	 _query : {},
	 _oldSubmit : null,
 // init at loading time - script can change location at run time ? ...
	 _pageBase : window.location.protocol+"//"+window.location.host,
 // hash for control elements query string functions
 	 
 	 hidden : function(control){
 	 		this._value_query(control);
 	 		// TODO - configurable mask for hidden command scripts.
 	 		if( (control.name.length > 4) && (control.name.lastIndexOf("_idcl") ==  (control.name.length-5)) ){
 	 			control.value="";
 	 		// MYfaces version ...	
 	 		} else if( (control.name.length > 12) && (control.name.lastIndexOf("_link_hidden_") ==  (control.name.length-13)) ){
 	 			control.value="";
 	 		} 
 	 },
 	 
 	 text : function(control){
 	 		this._value_query(control);
 	 },

 	 textarea : function(control){
 	 		this._value_query(control);
 	 },

 	 'select-one' : function(control){
 	 	// If none options selected, don't include parameter.
 	 	if (control.selectedIndex != -1) {
    		this._value_query(control);
		} 
//	 	 	for( var i =0; i< control.childNodes.length; i++ ){
//	 	 		var child=control.childNodes[i];
//	 	 		if( child.selected ){
//		 	 		this._value_query(control); 		
//		 	 		break;
//	 	 		}
//	 	 	}
 	 },

 	 password : function(control){
 	 		this._value_query(control);
 	 },

 	 file : function(control){
 	 		this._value_query(control);
 	 },

 	 radio : function(control){
 	 		this._check_query(control);
 	 },

 	 checkbox : function(control){
 	 		this._check_query(control);
 	 },

 	 
 	 'select-multiple' : function(control){
 		var cname = control.name;
 		var childs = control.childNodes;
	 	for( var i=0 ;i< childs.length;i++ ){
 		  var child=childs[i];
 		  if( child.tagName == 'OPTGROUP' ){
 			var options = child.childNodes;
			for(var j=0; j < options.length; j++){
				this._addOption(cname, options[j]);
			}
 		  } else {
			this._addOption(cname, child);
		  }
 		}
 	},
 	
 	_addOption : function(cname,option){
		if ( option.selected ){
			if( ! this._query[cname] ){
				this._query[cname]=[];
			}
			this._query[cname][this._query[cname].length]=option.value;
		}
 		
 	},
// command inputs

 	 image : function( control, action ){ 	 	
 	 		if(action) this._value_query(control);
 	 },
 	 button : function( control, action ){ 	 	
 	 		if(action) this._value_query(control);
 	 },
 	 
 	 submit : function( control, action ){ 	 	
 	 		if(action) { 
 	 			this._value_query(control);
 	 		}
 	 },
 	 
 	 // Anchor link pseudo-control.
 	 link : function(control, action ){
 	 		if(action) {
 	 			this._value_query(control);
 	 			if(control.parameters){
 	 				this.appendParameters(control.parameters);
 	 			}
 	 		}
 	 },
 	 
	// same as link, but have additional field - control, for input submit.
 	 input : function(control, action ){
 	 	if(action) {
 	 		this.link(control, action );
 	 		// append original control.
			if( control.control ) {
        		this.appendControl(control.control,action);
        	}
 	 	}
 	 },
 	 
	 // Append one control to query.
	 appendControl : function(control,action){
			if( this[control.type] ) {
        		this[control.type](control,action);
        	} else {
        		this._appendById(control.id||control);
          }
	 
	 },
	 
	 // Append all non-hidden controls from form to query.
	 appendFormControls : function(hiddenOnly){
	 	try {
	 	 var elems = this._form.elements;
	 	 if(elems){
		 var k = 0;
		   for ( k=0;k<elems.length;k++ ) {
		          var element=elems[k];
				  try {  
				    if(  !hiddenOnly || element.type == "hidden") {
		          		this.appendControl(element,false) ;
		            }
		   		  } catch( ee ) {
			        	 LOG.error("exception in building query ( append form control ) " + ee );
			      }
		    }
		  }
	 	} catch(e) {
	 		LOG.warn("Error with append form controls to query "+e)
	 	}
	 },

	// append map of parameters to query.
	 appendParameters : function(parameters){
		for( k in parameters ){
 	 	  if(typeof Object.prototype[k] == 'undefined'){
 	 	    LOG.debug( "parameter " + k  + " with value "+parameters[k]);
		  	this.appendParameter(k,parameters[k]);
		  }
		}	
	 },
	 
	 setActionUrl : function(actionUrl){
	 	this._actionUrl = actionUrl;
	 },
// Return action URL ( append extention, if present )
 	 getActionUrl : function( ext ) {
 	 	var actionUrl = this._actionUrl ;
 	 	var ask = actionUrl.indexOf('?');
 	 	// create absolute reference - for Firefox XMLHttpRequest base url can vary
 	 	if( actionUrl.substring(0,1) == '/' ) {
 	 		actionUrl = this._pageBase+actionUrl;
 	 	}
 	 	if ( ! ext ) ext = this._ext ;
 	 	if( ask >=0 )
 	 		{
 	 		return actionUrl.substring(0,ask) + ext + actionUrl.substring(ask); 	 		
 	 		}
 	 	else return actionUrl + ext;
 	 },
 	 
 	 
// Build query string for send to server.
 	 getQueryString : function() {
 	 	var qs = "";
 	 	var iname ;
 	 	for ( var k in this._query ){
 	 	  if(typeof Object.prototype[k] == 'undefined'){
 	 		iname = this._query[k];
 	 		if( iname instanceof Object ){
 	 			for ( var l=0; l< iname.length; l++ ) {
	 	 			qs += this._encode(k) + "=" + this._encode(iname[l]) + "&";
	 	 		}
 	 		} else {
 	 			qs += this._encode(k) + "=" + this._encode(iname) + "&";
 	 		}
 	 	  }
 	 	}
 	 	LOG.debug("QueryString: "+qs);
 	 	return qs;
 	 },
 	 // private methods
 	 
	 _appendById : function( id ) {
	 	this.appendParameter(this._form.id + "_link_hidden_", id);
	 	// JSF-ri version ...
	 	// this._query[this._form.id + "_idcl"]=id;
	 },
	 

 	 _value_query : function(control){
 	 	LOG.debug("Append "+control.type+" control "+control.name+" with value ["+control.value+"] and value attribute ["+control.getAttribute('value')+"]");
		if(null != control.value){
	 	 	this.appendParameter(control.name, control.value);
		}
 	 },
 	 
 	 _check_query : function(control){
 	 	if( control.checked ) {
 	 		this.appendParameter(control.name, control.value?control.value:"on");
 	 	}
 	 },
 	 
 	 // Append parameter to query. if name exist, append to array of parameters
 	 appendParameter: function(cname,value){
 	 			if( ! this._query[cname] ){
 	 				this._query[cname]=value;
 	 				return;
 	 			} else if( !(this._query[cname] instanceof Object) ){
 	 				this._query[cname] = [this._query[cname]];
 	 			}
 	 			this._query[cname][this._query[cname].length]=value;
 	 },
 	 
    // Encode data string for request string
    _encode : function(string) {
	    try {
	    	return encodeURIComponent(string);
	    } catch(e) {
	    var str = escape(string);
	    // escape don't encode +. but form replace  ' ' in fields by '+'
		return str.split('+').join('%2B');
	    }
    }
 	 
 	 
  }
  	
// Test for re-evaluate Scripts in updated part. Opera & Safari do it.
A4J.AJAX._scriptEvaluated=false;
if (!document.all || window.opera){
 setTimeout(function(){
		try{
			// Simulate same calls as on XmlHttp
			var oDomDoc = Sarissa.getDomDocument();
			var _span = document.createElement("span");
			document.documentElement.appendChild(_span);
			// If script evaluated with used replace method, variable will be set to true
			var xmlString = "<html xmlns='http://www.w3.org/1999/xhtml'><sc"+"ript>A4J.AJAX._scriptEvaluated=true;</scr"+"ipt></html>";
			oDomDoc = (new DOMParser()).parseFromString(xmlString, "text/xml");
			var _script=oDomDoc.getElementsByTagName("script")[0];
			if(_span.outerHTML){
				_span.outerHTML = new XMLSerializer().serializeToString(_script); 
			} else {
		    	var importednode ;
		   		importednode = window.document.importNode(_script, true);
				document.documentElement.replaceChild(importednode,_span);
			}
			
		} catch(e){ /* Mozilla in XHTML mode not have innerHTML */ };
  },50);
}

/**
 * Provide client side logging capabilities to AJAX applications.
 *
 * @author <a href="mailto:thespiegs@users.sourceforge.net">Eric Spiegelberg</a>
 * @see <a href="http://sourceforge.net/projects/log4ajax">Log4Ajax</a>
 */
 
function LOG()
{
  throw "Do not instantiate LOG";
}

LOG.Level = function(name, priority, color){
	this.name = name;
	this.priority = priority;
	if(color){
		this.color = color;
	}
}

LOG.OFF = new LOG.Level("off", 1000);
LOG.FATAL  = new LOG.Level("fatal", 900, "red");
LOG.ERROR = new LOG.Level("error", 800, "red");
LOG.WARN = new LOG.Level("warn", 500, "yellow");
LOG.INFO = new LOG.Level("info", 400, "blue");
LOG.DEBUG = new LOG.Level("debug", 300, "darkblue");
LOG.ALL = new LOG.Level("all", 100);
LOG.A4J_DEBUG = new LOG.Level("a4j_debug", 0, "green");

LOG.LEVEL = LOG.OFF;

LOG.window = null;
LOG.transmitToServer = true;
LOG.consoleDivId = "logConsole";
LOG.styles = {
a4j_debug: "green",
debug : "darkblue",
info : "blue",
warn : "yellow",
error : "red",
fatal : "red"
};

LOG.a4j_debug = function(msg,pre)
{
	LOG._log(msg, LOG.A4J_DEBUG ,pre);
}

LOG.debug = function(msg,pre)
{
	LOG._log(msg, LOG.DEBUG ,pre);
}

LOG.info = function(msg,pre)
{
	LOG._log(msg, LOG.INFO ,pre);
}

LOG.warn = function(msg,pre)
{
	LOG._log(msg, LOG.WARN ,pre);
}

LOG.error = function(msg,pre)
{
	LOG._log(msg, LOG.ERROR ,pre);
}

LOG.fatal = function(msg,pre)
{
	LOG._log(msg, LOG.FATAL ,pre);
}

LOG.registerPopup = function(hotkey,name,width,height,level){
	if(!LOG._onKeyUp){
		LOG._onKeyUp = document.onkeyup;
	}
	var key = hotkey.toUpperCase();
	document.onkeyup = function(e){
		if (window.event){ e = window.event;};
		if (String.fromCharCode(e.keyCode) == key & e.shiftKey & e.ctrlKey){ 
			LOG.LEVEL = level;
			LOG.openWindow(name,'width='+width+',height='+height+',toolbar=no,scrollbars=yes,location=no,statusbar=no,menubar=no,resizable=yes,left = '+((screen.width - width) / 2)+',top ='+((screen.height - height) / 2));
		} else {
	      if(LOG._onKeyUp) LOG._onKeyUp(e);
		}; 
	}
}

LOG.openWindow = function(name,features){
	if(LOG.window){
		LOG.window.focus();
	} else {
		LOG.window = window.open("",name,features);
		var _LOG = LOG;
		LOG.window.onunload = function(){
			_LOG.window = null;
			_LOG.LEVEL = _LOG.OFF;
			_LOG=undefined;
		}
	}
}

LOG._log = function(msg, logLevel,pre)
{
	if(logLevel.priority >= LOG.LEVEL.priority){
		LOG._logToConsole(msg, logLevel,pre);
	
		if (LOG.transmitToServer)
		{
			LOG._logToServer(msg, logLevel);
		}
	}
}

LOG._time = function(){
	var currentTime = new Date();
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}
	var seconds = currentTime.getSeconds();
	if (seconds < 10){
		seconds = "0" + seconds;
	}
	var millisec = currentTime.getTime()%1000;
	if(millisec<100){
		millisec = "0"+millisec;
	}
	if(millisec<10){
		millisec = "0"+millisec;
	}
	return hours+":"+minutes+":"+seconds+","+millisec;
}

LOG._logToConsole = function(msg, logLevel,preformat)
{
	var consoleDiv ;
	var doc;
	if(LOG.window && LOG.window.document){
		doc = LOG.window.document;
		consoleDiv = LOG.window.document.body;
	} else {
		doc = window.document;
		consoleDiv = window.document.getElementById(LOG.consoleDivId);
	}
	if (consoleDiv)
	{
		var span = doc.createElement("span");
		span.style.color=logLevel.color;
		span.appendChild(doc.createTextNode(logLevel.name+"["+LOG._time()+"]: "));
	 	var div = doc.createElement("div");
   		var textnode = doc.createTextNode(msg);
        div.appendChild(span);
   		div.appendChild(textnode);
   		// preformatted - for example, html
   		if(preformat){
		 	var pre = doc.createElement("span");
   			textnode = doc.createTextNode(preformat);
   			pre.appendChild(textnode);
	   		div.appendChild(pre);
   		}
        consoleDiv.appendChild(div);
/*	
		consoleDiv.innerHTML = "<span style='" + LOG.styles[logLevel] + "'>" + 
							   logLevel + "</span>: " + msg + "<br/>" + 
							   consoleDiv.innerHTML;*/
	}
	else
	{
		// If the consoleDiv is not available, you could create a 
		// new div or open a new window.
	}
}

LOG._logToServer = function(msg, logLevel)
{
	var data = logLevel.name.substring(0, 1) + msg;
	// TODO - use sarissa-enabled request.
	// Use request.js to make an AJAX transmission to the server
//	Http.get({
//		url: "log",
//		method: "POST",
//		body: data,
//		callback: LOG._requestCallBack
//	});
}

LOG._requestCallBack = function()
{
	// Handle callback functionality here; if appropriate
}
/*
* final trail for ajax jsf library
*/
// }
A4J.AJAX.onError = function(req, status, message){
return ;
}
