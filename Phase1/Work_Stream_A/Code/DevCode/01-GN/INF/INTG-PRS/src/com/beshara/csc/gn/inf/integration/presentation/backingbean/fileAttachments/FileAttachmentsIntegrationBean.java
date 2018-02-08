package com.beshara.csc.gn.inf.integration.presentation.backingbean.fileAttachments;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.client.IFileClient;
import com.beshara.csc.flm.flm.business.client.IFlmAttachmentsClient;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.FlmAttachmentsDTO;
import com.beshara.csc.flm.flm.business.dto.FlmDTOFactory;
import com.beshara.csc.flm.flm.business.dto.IFlmAttachmentsDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.flm.flm.business.entity.FileEntityKey;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments.AttachmentUtilIntegrationBean;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.util.FlmHelper;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.IOException;

import java.math.BigDecimal;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.custom.fileupload.UploadedFile;


/**
 * @author       Beshara Group
 * @author       Cappuchino Team [Amr Abdel Halim]
 * @version      1.0
 * @since        31/10/2017
 */

public class FileAttachmentsIntegrationBean extends LookUpBaseBean {


    @SuppressWarnings("compatibility:-7276321066318604745")
    private static final long serialVersionUID = 1L;
    private String selectedDocType;
    private List docTypeList;
    private String docName;
    private Date fromDate;
    private Date untilDate;
    // Attachment
    private List<IBasicDTO> attachmentList;
    private String fakeImageString;
    private UploadedFile uploadedFile;
    private boolean uploadingError;
    private boolean invalidImageType;
    private IFlmAttachmentsDTO clonedPageDTO;
    private String selectedImagePath;
    
    private Boolean disableSelectedDocType;
    private ResourceBundle bundle = null;
    private TransactionDTO filesTransaction;
    private FileDTO file;
    private String doctypeName;
    private boolean forShowAttachmentOnly;
    private List<IBasicDTO> currentDisplayList;
    private boolean enableFileNet;
    
    
    private String selectedElementName;
    
    
    private String comments;
    IFlmAttachmentsDTO flmAttachmentsDTO = new FlmAttachmentsDTO();
    private static final String INF_INTGRATION_BUNDLE = "com.beshara.csc.gn.inf.integration.presentation.resources.infintegration";
    private String validDesign;
    private BigDecimal refTabrecSerial;
    
    public FileAttachmentsIntegrationBean() {

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowDelAlertTree(false);
        app.setShowbar(true);
        app.setShowpaging(true);
        app.setShowdatatableContent(true);
        if (isForShowAttachmentOnly()) {
            app.setShowContent1(false);
        } else {
            app.setShowContent1(true);
        }
        return app;
    }
    
    public void initiateBeanOnce(){
        loadDoctypes();
    }

    private void fillDto() {

        if (getSelectedDocType() != null && !"".equals(getSelectedDocType())) {
            flmAttachmentsDTO.setAttachmentTypeCode(Long.valueOf(getSelectedDocType()));
        }
        flmAttachmentsDTO.setStatus(ISystemConstant.ACTIVE_FLAG);
        flmAttachmentsDTO.setRTabrecSerial(getRefTabrecSerial());
        flmAttachmentsDTO.setAttachmentDesc(getDocName());
        flmAttachmentsDTO.setFile(getFile());
        flmAttachmentsDTO.setComments(comments);
        Long appModuleCode = CSCSecurityInfoHelper.getModuleCode();
        flmAttachmentsDTO.setAppModuleCode(appModuleCode);

    }

    private boolean isCheckImgExtention() {
        invalidImageType = false;
        String tempFileExtension = null;
        if (uploadedFile != null)
            tempFileExtension = AttachmentUtilIntegrationBean.mapImageTypeToExtension(uploadedFile.getContentType());
        if (tempFileExtension == null) {
            invalidImageType = true;
            return false;
        }
        return true;

    }
    
    private void insureFilesTransactionCreated() throws DataBaseException, SharedApplicationException {
        if (filesTransaction == null) {
            FileManager fileManager = FileManager.getInstance();
            filesTransaction = new TransactionDTO();
            filesTransaction.setModule(fileManager.getModule());
            filesTransaction.setCategory("doc");
            filesTransaction = fileManager.addTransaction(filesTransaction);
        }
    }


    public void addAttachment() {

        try {
            file = uploadFile();
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        } catch (IOException e) {
        }
    }

    public void save() {
        SharedUtilBean shared_util = getSharedUtil();
        try {
            if (refTabrecSerial != null) {
                if (isCheckImgExtention() == true) {
                    invalidImageType = false;
                    addAttachment();
                    fillDto();
                    IFlmAttachmentsClient client = ClientFactoryUtil.getInstance(IFlmAttachmentsClient.class);
                    flmAttachmentsDTO = (IFlmAttachmentsDTO)client.add(getFlmAttachmentsDTO());
                    addDocAttachment();
                    highlightRecord(getFlmAttachmentsDTO());
                    setSuccess(true);
                    shared_util.setSuccessMsgValue("SuccessAdds");
                    reSetData();
                }
            }
            else { // TabrecSerial Not Found
                getSharedUtil().setErrMsgValue(getMessageFromBundle("tabrecserialNotFound"));
                setSuccess(false);
            }
            
        } catch (DataBaseException e) {
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
            setSuccess(false);
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            this.setErrorMsg("FailureInAdd");
            shared_util.setErrMsgValue("FailureInAdd");
            setSuccess(false);
            e.printStackTrace();
        }
    }
    
    @Override
    public List getTotalList() {
        return attachmentList;
    }
    
    public void highlightRecord(IBasicDTO addedDTO) {
        this.getHighlightedDTOS().clear();
        if (addedDTO != null && addedDTO.getCode() != null) {
            this.getHighlightedDTOS().add(addedDTO);
            try {
                this.getPageIndex(addedDTO.getCode().getKey());
            } catch (Exception e) {
            }
        }
    }
    
    @Override
    public void getAll() throws DataBaseException {

        if (isUsingPaging()) {

            setUpdateMyPagedDataModel(true);
            setPagingRequestDTO(new PagingRequestDTO("getAllWithPaging"));

        } else {

            setMyTableData(this.getTotalList());

            this.reinitializeSort();

            if (getSelectedDTOS() != null) {
                getSelectedDTOS().clear();
            }

        }

    }
    
    public static String getMessageFromBundle(String bundleParameter) {
        String targetString = SharedUtilBean.getInstance().messageLocator(INF_INTGRATION_BUNDLE,bundleParameter);
        return targetString;
    }
    
    public void deleteAttachment() {
        System.out.println("delete selected record ...");
        IFlmAttachmentsDTO attachDTO = (FlmAttachmentsDTO)getMyDataTable().getRowData();
        IFlmAttachmentsDTO itemToDelete = (FlmAttachmentsDTO)getSelectedRecord(attachDTO.getCode());
        IFlmAttachmentsClient client = ClientFactoryUtil.getInstance(IFlmAttachmentsClient.class);
        try {
            client.remove(itemToDelete);
            System.out.println("record deleted successfully.");
            getSharedUtil().setSuccessMsgValue(getMessageFromBundle("attachment_deleted"));
            refreshMyTableData();
            addDocAttachment();
            if (isEnableFileNet()) {
                System.out.println("<<< filenet is enabled, now delete from filenet >>>");
                if (attachDTO != null) {
                    FileDTO file = attachDTO.getFile();
                    FileEntityKey fileEK = (FileEntityKey)file.getCode();
                    IFileClient fclient = ClientFactoryUtil.getInstance(IFileClient.class);
                    file = (FileDTO)fclient.getById(fileEK);
                    String fileID = file.getCode().getKey();
                    FileManager fileManager = FileManager.getInstance();
                    fileManager.deleteFile(fileID);
                    System.out.println("<<< delete from filenet end >>>");
                }
            }
            
        }catch (DataBaseException db) {
            getSharedUtil().handleException(db);
        } catch (ItemNotFoundException item) {
            getSharedUtil().setErrMsgValue("ItemCanNotBeDeletedException");
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteDiv() throws DataBaseException, ItemNotFoundException {
        this.delete();
        this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");

    }

    private IBasicDTO getSelectedRecord(IEntityKey code) {
        for (IBasicDTO basicDTO : getAttachmentList()) {
            if (basicDTO.getCode().equals(code)) {
                return basicDTO;
            }
        }
        return null;
    }

    private void refreshMyTableData() {
        setCurrentDisplayList(getAttachmentList());
    }

    private void reSetData() {
        setDocName("");
        setFakeImageString("");
        setFromDate(null);
        setUntilDate(null);
        setComments("");
    }

    public void fillAttachmentList(ValueChangeEvent vce) {
        try {
            selectedDocType = (String)vce.getNewValue();
            addDocAttachment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDocAttachment() {
        try {
            if (refTabrecSerial != null) { 
                IFlmAttachmentsClient client = ClientFactoryUtil.getInstance(IFlmAttachmentsClient.class);
                IFlmAttachmentsDTO searchDTO = FlmDTOFactory.createFlmAttachmentsDTO();
                searchDTO.setRTabrecSerial(refTabrecSerial);
                if(selectedDocType != null && !"".equals(selectedDocType)){
                    searchDTO.setAttachmentTypeCode(Long.valueOf(selectedDocType));
                }
                attachmentList = client.getAll(searchDTO);

                if (attachmentList == null) {
                    attachmentList = new ArrayList<IBasicDTO>();
                }
            } else {
                attachmentList = new ArrayList<IBasicDTO>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            attachmentList = new ArrayList<IBasicDTO>();
        }
        setCurrentDisplayList(attachmentList);
    }


    public void showAttachmentsDetails(ActionEvent e) {
        try {
            System.out.println("attch details");
            IFlmAttachmentsDTO attachDTO = (IFlmAttachmentsDTO)getMyDataTable().getRowData();

            if (attachDTO != null) {
                FileDTO file = attachDTO.getFile();
                FileEntityKey fileEK = (FileEntityKey)file.getCode();
                IFileClient client = ClientFactoryUtil.getInstance(IFileClient.class);
                file = (FileDTO)client.getById(fileEK);
                String fileID = file.getCode().getKey();
                System.out.println("FileNet ENabled = " + isEnableFileNet());
                if (isEnableFileNet()) {
                    System.out.println("Get File From file Net");
                    selectedImagePath = getFileNetUrl(fileID);
                } else {
                    System.out.println("Get File From FLM Drive");
                    selectedImagePath = FlmHelper.getFileURL(fileID);
                }

                setJavaScriptCaller("openRegIntgDecisionDetailsWindow();");
            }
        
        } catch (Exception ex) {
        }
    }


    public String back() {
        IntegrationBean integrationBean =
            (IntegrationBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{integrationBean}").getValue(FacesContext.getCurrentInstance());

        return integrationBean.goFrom();
    }
    
    public void loadDoctypes() {
        if (docTypeList == null) {
            try {
                docTypeList = InfClientFactory.getInfDocumentTypesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
            }

        }
    }

    public Integer getListSize() {
        if (attachmentList == null) {
            return 0;
        }
        return attachmentList.size();
    }

    public void setSelectedDocType(String selectedDocType) {
        this.selectedDocType = selectedDocType;
    }

    public String getSelectedDocType() {
        return selectedDocType;
    }

    public void setDocTypeList(List docTypeList) {
        this.docTypeList = docTypeList;
    }

    public List getDocTypeList() {
        return docTypeList;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocName() {
        return docName;
    }


    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setFakeImageString(String fakeImageString) {
        this.fakeImageString = fakeImageString;
    }

    public String getFakeImageString() {
        return fakeImageString;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadingError(boolean uploadingError) {
        this.uploadingError = uploadingError;
    }

    public boolean isUploadingError() {
        return uploadingError;
    }

    public void setInvalidImageType(boolean invalidImageType) {
        this.invalidImageType = invalidImageType;
    }

    public boolean isInvalidImageType() {
        return invalidImageType;
    }

    

    public void setSelectedImagePath(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    

    public void setAttachmentList(List<IBasicDTO> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<IBasicDTO> getAttachmentList() {
        return attachmentList;
    }


    public void setDisableSelectedDocType(Boolean disableSelectedDocType) {
        this.disableSelectedDocType = disableSelectedDocType;
    }

    public Boolean getDisableSelectedDocType() {
        return disableSelectedDocType;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setFilesTransaction(TransactionDTO filesTransaction) {
        this.filesTransaction = filesTransaction;
    }

    public TransactionDTO getFilesTransaction() {
        return filesTransaction;
    }

    public void setFile(FileDTO file) {
        this.file = file;
    }

    public FileDTO getFile() {
        return file;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

    public String getDoctypeName() {
        return doctypeName;
    }

    public void setForShowAttachmentOnly(boolean forShowAttachmentOnly) {
        this.forShowAttachmentOnly = forShowAttachmentOnly;
    }

    public boolean isForShowAttachmentOnly() {
        return forShowAttachmentOnly;
    }

    public void setCurrentDisplayList(List currentDisplayList) {
        this.currentDisplayList = currentDisplayList;
    }

    public List getCurrentDisplayList() {
        if (currentDisplayList == null || currentDisplayList.isEmpty()) {
            currentDisplayList = attachmentList;
        }
        return currentDisplayList;
    }


    /************************************************ FILENET  **************************************/

    public FileDTO uploadFile() throws DataBaseException, SharedApplicationException, IOException {
        insureFilesTransactionCreated();
        FileDTO file = new FileDTO();
        file.setTransaction(filesTransaction);
        file.setName(uploadedFile.getName());
        file.setSize(uploadedFile.getSize());
        file.setContentType(uploadedFile.getContentType());
        file.setSubjectName(getDocName());
        file.setOwnerCivilId(getFileNetCivilId());
        if(selectedDocType!= null && !selectedDocType.equals("")){
            file.setDocumentType(Integer.valueOf(selectedDocType));
        }
        FileManager fileManager = FileManager.getInstance();
        return fileManager.uploadFile(file, uploadedFile.getInputStream());
    }

    public Long getFileNetCivilId() {
        return 0L;
    }

    public void setEnableFileNet(boolean enableFileNet) {
        this.enableFileNet = enableFileNet;
    }

    public boolean isEnableFileNet() {
        return enableFileNet;
    }

    private String getFileNetUrl(String fileId) {

        String url = String.format("%s/downloadservlet/?fid=%s&fn=true", getSharedUtil().getContextPath(), fileId);
        System.out.println(String.format("Retreive file from filenet using this URL: %s", url));
        return url;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setFlmAttachmentsDTO(IFlmAttachmentsDTO flmAttachmentsDTO) {
        this.flmAttachmentsDTO = flmAttachmentsDTO;
    }

    public IFlmAttachmentsDTO getFlmAttachmentsDTO() {
        return flmAttachmentsDTO;
    }

    public void setSelectedElementName(String selectedElementName) {
        this.selectedElementName = selectedElementName;
    }

    public String getSelectedElementName() {
        return selectedElementName;
    }

    public void setClonedPageDTO(IFlmAttachmentsDTO clonedPageDTO) {
        this.clonedPageDTO = clonedPageDTO;
    }

    public IFlmAttachmentsDTO getClonedPageDTO() {
        return clonedPageDTO;
    }

    public void setValidDesign(String validDesign) {
        this.validDesign = validDesign;
    }

    public String getValidDesign() {
        if(isForShowAttachmentOnly()){
            return "";
        }
        else {
            return "dataT-With-5-row-filter";
        }
    }

    public void setRefTabrecSerial(BigDecimal refTabrecSerial) {
        this.refTabrecSerial = refTabrecSerial;
    }

    public BigDecimal getRefTabrecSerial() {
        return refTabrecSerial;
    }
}
