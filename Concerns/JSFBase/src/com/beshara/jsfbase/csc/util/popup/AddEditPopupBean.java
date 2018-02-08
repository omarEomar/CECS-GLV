package com.beshara.jsfbase.csc.util.popup;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;


public class AddEditPopupBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private String name;
    private String title;
    private String saveMethod;
    private String loadMethod;
    private String errorMsg;
    private boolean success;
    private String formName;
    private boolean refreshOppener;
    private String objectName;
    private String fieldName;
    private String pageName;
    private IBasicDTO pageDTO;


    public AddEditPopupBean() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ex = ctx.getExternalContext();
        HttpServletRequest req = (HttpServletRequest)ex.getRequest();

        loadMethod = req.getParameter("load_method");
        formName = req.getParameter("form_name");
        String objField = req.getParameter("field_name");
        if (objField != null) {
            String[] split = objField.split("\\.");
            objectName = split[0];
            String param = getInitParam("DTOPath");
            objectName = param + "." + objectName;
            fieldName = split[1];
        }
        if (loadMethod != null && !loadMethod.equals("")) {
            load();
        }
        title = req.getParameter("title");
        saveMethod = req.getParameter("save_method");
        if (saveMethod != null) {
            pageName = saveMethod.split("\\.")[0];
            System.out.println(pageName);
        }

    }

    public void load() {
        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                   loadMethod + 
                                                                                   "}", 
                                                                                   null);
        pageDTO = 
                (IBasicDTO)methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                                null);
        Class c = pageDTO.getClass();
        try {
            Method concatMethod = 
                c.getMethod(fieldName.replaceAll("set", "get"), null);
            name = (String)concatMethod.invoke(pageDTO, null);
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

    public void save() {
        try {
            callSave();
            closeWindow();
        } catch (Exception ex) {
            errorMsg = ex.getCause().getMessage();
        }
    }

    public void saveAndNew() {
        try {
            callSave();
            success = true;
            name = "";
        } catch (Exception ex) {
            success = false;
            errorMsg = ex.getCause().getMessage();
        }
    }

    private void callSave() throws SharedApplicationException {
        IBasicDTO base = createDTO();
        MethodBinding methodBinding = 
            FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                   pageName + 
                                                                                   ".setPageDTO" + 
                                                                                   "}", 
                                                                                   new Class[] { IBasicDTO.class });
        methodBinding.invoke(FacesContext.getCurrentInstance(), 
                             new Object[] { base });

        methodBinding = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{" + 
                                                                                       saveMethod + 
                                                                                       "}", 
                                                                                       null);
        methodBinding.invoke(FacesContext.getCurrentInstance(), null);
        refreshOppener = true;
    }

    public void closeWindow() {
        String javaScriptText;
        if (refreshOppener)
            javaScriptText = 
                    "window.opener.top.blocker.style.visibility='hidden';window.opener.document.getElementById('reloadList').value='reloadList';window.opener.document.forms['" + 
                    formName + "'].submit();window.close();";
        else
            javaScriptText = 
                    "window.opener.top.blocker.style.visibility='hidden';window.close();";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        AddResource addResource = AddResourceFactory.getInstance(facesContext);
        addResource.addInlineScriptAtPosition(facesContext, 
                                              AddResource.HEADER_BEGIN, 
                                              javaScriptText);
    }

    private IBasicDTO createDTO() {
        Class classDefinition;
        Object object = null;
        Class c = null;
        try {
            classDefinition = Class.forName(objectName);
            object = classDefinition.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (pageDTO != null)
            object = pageDTO;
        c = object.getClass();

        Class[] parameterTypes = new Class[] { String.class };
        Object[] arguments = new Object[] { name };
        try {
            Method concatMethod = c.getMethod(fieldName, parameterTypes);
            concatMethod.invoke(object, arguments);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (IBasicDTO)object;
    }

    private String getInitParam(String paramName) {
        return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(paramName);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSaveMethod(String saveMethod) {
        this.saveMethod = saveMethod;
    }

    public String getSaveMethod() {
        return saveMethod;
    }

    public void setLoadMethod(String loadMethod) {
        this.loadMethod = loadMethod;
    }

    public String getLoadMethod() {
        return loadMethod;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setRefreshOppener(boolean refreshOppener) {
        this.refreshOppener = refreshOppener;
    }

    public boolean isRefreshOppener() {
        return refreshOppener;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageDTO(IBasicDTO pageDTO) {
        this.pageDTO = pageDTO;
    }

    public IBasicDTO getPageDTO() {
        return pageDTO;
    }
}
