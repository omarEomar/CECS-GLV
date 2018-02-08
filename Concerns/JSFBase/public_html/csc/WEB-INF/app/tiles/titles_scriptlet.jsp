<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%     
        String globalDeleteAlertTree = "";
       
        String globaltitleShortcutDivDiv = "MISSING: "+request.getAttribute("shortcutDiv")+" :MISSING";
        String globaltitleAddDiv = "MISSING: "+request.getAttribute("titleAddDiv")+" :MISSING";
        String globaltitleEditDiv = "MISSING: "+request.getAttribute("titleEditDiv")+" :MISSING";
        String globaltitleDelDiv = "MISSING: "+request.getAttribute("titleDelDiv")+" :MISSING";
        String globaltitleDelConDiv = "MISSING: "+request.getAttribute("titleDelConDiv")+" :MISSING";
        String globaltitleTreeDiv = "MISSING: "+request.getAttribute("titleTreeDiv")+" :MISSING";
        String globaltitleLovDiv = "MISSING: "+request.getAttribute("titleLovDiv")+" :MISSING";       
        String globaltitlepage = "MISSING: "+request.getAttribute("titlepage")+" :MISSING";
        String globaltitleIntegrationDiv1 = "MISSING: "+request.getAttribute("titleIntegrationDiv1")+" :MISSING";
        String globaltitleIntegrationDiv2 = "MISSING: "+request.getAttribute("titleIntegrationDiv2")+" :MISSING";
        
        if(request.getAttribute("deletealerttree") != null)
            globalDeleteAlertTree = "MISSING: "+request.getAttribute("deletealerttree")+" :MISSING";
        else
            globalDeleteAlertTree = "MISSING: deletealerttree :MISSING";            
        request.setAttribute("globaltitleShortcutDivDiv",globaltitleShortcutDivDiv);
        request.setAttribute("globaltitleAddDiv",globaltitleAddDiv);
        request.setAttribute("globaltitleEditDiv",globaltitleEditDiv);
        request.setAttribute("globalDeleteAlertTree",globalDeleteAlertTree);
        request.setAttribute("globaltitleDelDiv",globaltitleDelDiv);
        request.setAttribute("globaltitleDelConDiv",globaltitleDelConDiv);
        request.setAttribute("globaltitleTreeDiv",globaltitleTreeDiv);
        request.setAttribute("globaltitleLovDiv",globaltitleLovDiv);
        request.setAttribute("globaltitlepage",globaltitlepage);
        request.setAttribute("globaltitleIntegrationDiv1",globaltitleIntegrationDiv1);
        request.setAttribute("globaltitleIntegrationDiv2",globaltitleIntegrationDiv2);
        
        
        //System.err.print("---------------------------------------------------"+globaltitlepage);
        %>
