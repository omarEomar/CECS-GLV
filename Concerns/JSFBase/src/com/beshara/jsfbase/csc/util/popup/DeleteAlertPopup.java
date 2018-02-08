package com.beshara.jsfbase.csc.util.popup;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;


public class DeleteAlertPopup implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List deletedItems = new ArrayList();
    private List myData = new ArrayList();
    private String loadMethod;
    private String deleteMethod;
    private String formName;
    private String dtoCode;
    private String dtoName;
    private String objectName;

    public DeleteAlertPopup() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpServletRequest req = (HttpServletRequest)ex.getRequest();

        loadMethod = req.getParameter("load_method");
        if (loadMethod != null && !loadMethod.equals("")) {
            load();
        }
        deleteMethod = req.getParameter("delete_method");
        formName = req.getParameter("form_name");
        dtoCode = req.getParameter("dto_code");
        dtoName = req.getParameter("dto_name");
        if (dtoName != null) {
            String[] split = dtoName.split("\\.");
            objectName = split[0];
            String param = getInitParam("DTOPath");
            objectName = param + "." + objectName;
            dtoName = split[1];
            split = dtoCode.split("\\.");
            dtoCode = split[1];
        }
        organizeList();
    }

    public void load() {
        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                   loadMethod + 
                                                                                   "}", 
                                                                                   null);
        deletedItems = 
                (List)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                           null);
    }

    private void organizeList() {
        Class c;
        for (int i = 0; i < deletedItems.size(); i++) {
            c = deletedItems.get(i).getClass();
            readFieldsValues(deletedItems.get(i), c);
        }
    }

    private void readFieldsValues(Object object, Class c) {
        try {
            List row = new ArrayList();
            Method concatMethod = c.getMethod(dtoCode, null);
            String code = concatMethod.invoke(object, null) + "";
            concatMethod = c.getMethod(dtoName, null);
            String name = concatMethod.invoke(object, null) + "";
            row.add(code);
            row.add(name);
            myData.add(row);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callDelete() {
        try {
            MethodBinding methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       deleteMethod + 
                                                                                       "}", 
                                                                                       null);
            methodBinding.invoke(FacesContext.getCurrentInstance(), null);
        } catch (Exception ex) {
            closeWindow();
            //TODO...............
        }
        closeWindow();
    }

    public void closeWindow() {
        String javaScriptText = 
            "window.opener.blocker.style.visibility='hidden';window.opener.document.getElementById('reloadList').value='reloadList';window.opener.document.forms['" + 
            formName + "'].submit();window.close();";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, 
                                              AddResource.HEADER_BEGIN, 
                                              javaScriptText);
    }

    private String getInitParam(String paramName) {
        return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(paramName);
    }


    public void setDeletedItems(List deletedItems) {
        this.deletedItems = deletedItems;
    }

    public List getDeletedItems() {
        return deletedItems;
    }

    public void setLoadMethod(String loadMethod) {
        this.loadMethod = loadMethod;
    }

    public String getLoadMethod() {
        return loadMethod;
    }

    public void setDeleteMethod(String deleteMethod) {
        this.deleteMethod = deleteMethod;
    }

    public String getDeleteMethod() {
        return deleteMethod;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setDtoCode(String dtoCode) {
        this.dtoCode = dtoCode;
    }

    public String getDtoCode() {
        return dtoCode;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getDtoName() {
        return dtoName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setMyData(List myData) {
        this.myData = myData;
    }

    public List getMyData() {
        return myData;
    }
}
