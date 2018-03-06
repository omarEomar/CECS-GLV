package com.beshara.csc.inf.presentation.backingbean.maindata.identificationtypes;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.fileAttachments.FileAttachmentsIntegrationBean;
import com.beshara.csc.inf.business.client.IIdentificationTypesClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IdentificationTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;


public class IdentificationTypesListBean extends LookUpBaseBean {
    
    protected static final String METHOD_NAME_RESTORE = "restorePage";
    protected static final String NAVIGATION_KEY = "identificationTypesPage"; 
    protected static final String BEAN_NAME = "identificationTypesListBean";
    protected static final String BUNDLE_NAME = "com.beshara.csc.inf.presentation.resources.inf";


    public IdentificationTypesListBean() {

        setPageDTO(InfDTOFactory.createIdentificationTypesDTO());
        super.setSelectedPageDTO(InfDTOFactory.createIdentificationTypesDTO());
        setClient((IIdentificationTypesClient)InfClientFactory.getInfTypesClient());
        setSingleSelection(true);
        setSaveSortingState(true);
    }


    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createIdentificationTypesDTO());
    }

    public void initiateBeanOnce() {


    }
    


    public void search() throws DataBaseException, NoResultException {
        if (this.getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }
    
    /******** add Attachment Integration by Amr Abdel Halim 6-NOV-2017 ******************************/
    public String addIntegrationAttachment() {
        if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
            IdentificationTypesDTO selected = ((IdentificationTypesDTO)this.getSelectedDTOS().get(0));
            BigDecimal rTabrecSerial = selected.getTabRecSerial();
            if (rTabrecSerial != null) {
                getIntegrationBean().reInitializeBean();
                getIntegrationBean().setBeanNameFrom(BEAN_NAME);
                getIntegrationBean().setActionFrom(METHOD_NAME_RESTORE);
                // add your needed Save States
                getIntegrationBean().getHmObjects().put("currentPageIndex", getCurrentPageIndex());
                getIntegrationBean().getHmObjects().put("oldPageIndex", getOldPageIndex());
                getIntegrationBean().getHmObjects().put("selectedRadio", getSelectedRadio());
                getIntegrationBean().getHmObjects().put("selectedDTOS", getSelectedDTOS());
                getIntegrationBean().getHmObjects().put("selectedPageDTO", getSelectedPageDTO());
                
                String fullName = selected.getName(); // Descripion or Name of the Target Object
                String docTypeName = getMessageFromBundle("attachment_title");


                getFileAttachmentsIntegrationBean().setRefTabrecSerial(rTabrecSerial);
                getFileAttachmentsIntegrationBean().setSelectedElementName(fullName);
                getFileAttachmentsIntegrationBean().setDoctypeName(docTypeName);
                boolean pageHasDocType = false; // set it to true if you have a docType for Each attachment
                getFileAttachmentsIntegrationBean().setDisableSelectedDocType(!pageHasDocType);
                getFileAttachmentsIntegrationBean().addDocAttachment();
                boolean viewMode = false; // set it to true to view Attachments Only
                getFileAttachmentsIntegrationBean().setForShowAttachmentOnly(viewMode);
                return "addFileAttachments";
            }
        }
        return "";
    }

    public String restorePage() {
        if (getIntegrationBean() != null && getIntegrationBean().getHmObjects() != null &&
            !getIntegrationBean().getHmObjects().isEmpty()) {
            HashMap paramsMap = getIntegrationBean().getHmObjects();
            setCurrentPageIndex(Integer.parseInt(paramsMap.get("currentPageIndex").toString()));
            setOldPageIndex(Integer.parseInt(paramsMap.get("oldPageIndex").toString()));
            setSelectedRadio(paramsMap.get("selectedRadio").toString());
            setSelectedDTOS((List)paramsMap.get("selectedDTOS"));
            setSelectedPageDTO((IBasicDTO)paramsMap.get("selectedPageDTO"));
            
            if (getSelectedDTOS() != null && getSelectedDTOS().size() > 0) {
                highLightAddedRecord(getSelectedDTOS().get(0)); 
                    //getPageIndex(getSelectedRadio());
            }
            
            getSelectedDTOS().clear();
            setSelectedRadio("");
        }
        return NAVIGATION_KEY;
    }

    public static String getMessageFromBundle(String bundleParameter) {
        String targetString = SharedUtilBean.getInstance().messageLocator(BUNDLE_NAME, bundleParameter);
        return targetString;
    }

    public FileAttachmentsIntegrationBean getFileAttachmentsIntegrationBean() {
        return (FileAttachmentsIntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{fileAttachmentsIntegrationBean}").getValue(FacesContext.getCurrentInstance());

    }
    
    public void highLightAddedRecord(IBasicDTO dto){
        this.getHighlightedDTOS().clear();
        try {
            this.getPageIndex(dto.getCode().getKey());
        } catch (Exception e) {
        } 
        this.getHighlightedDTOS().add(dto);
    }
    /**************************************************************************/


}

