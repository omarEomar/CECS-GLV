package com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.client.IFileClient;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.flm.flm.business.entity.FileEntityKey;
import com.beshara.csc.inf.business.client.IPersonDocAtchTypesClient;
import com.beshara.csc.inf.business.client.IPersonDocAttachemntsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.PersonDocAtchTypesDTO;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.dto.PersonDocumntsDTO;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.IPersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.FlmHelper;
import com.beshara.jsfbase.csc.util.IntegrationBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.IOException;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.myfaces.custom.fileupload.UploadedFile;


public class AddDocAttachmentsIntegrationBean extends LookUpBaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long civilId;
    private String moduleName;
    private String empName;
    private String selectedDocType;
    private List docTypeList;
    private String docName;
    private String docPath;
    private Date fromDate;
    private Date untilDate;
    private String attchmentPath;
    private String attchmentName;
    // Attachment
    private List<IBasicDTO> attachmentList;
    private Long doctypeCode;
    private String fakeImageString;
    private String relativeFileName;
    private UploadedFile uploadedFile;
    private boolean uploadingError;
    private boolean invalidImageType;
    private Map<String, Object> detailsSavedStates;
    private PersonDocAttachemntsDTO clonedPageDTO;
    private String selectedImagePath;
    private String selectedImagePath2;
    PersonDocumntsDTO personDocumntsDTO = new PersonDocumntsDTO();
    PersonDocAttachemntsDTO personDocAttachemntsDTO = new PersonDocAttachemntsDTO();
    private Boolean disableSelectedDocType;
    private ResourceBundle bundle = null;
    private TransactionDTO filesTransaction;
    private FileDTO file;
    private String doctypeName;
    private boolean forShowAttachmentOnly;
    private List<IBasicDTO> currentDisplayList;
    private boolean enableFileNet = true;

    public AddDocAttachmentsIntegrationBean() {

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowDelAlertTree(false);
        app.setShowbar(true);
        app.setShowdatatableContent(true);
        if (isForShowAttachmentOnly()) {
            app.setShowContent1(false);
        } else {
            app.setShowContent1(true);
        }
        return app;
    }

    private void fillDto() {
        try {
            personDocumntsDTO.setKwtCitizensResidentsDTO(new KwtCitizensResidentsDTO());
            personDocumntsDTO.getKwtCitizensResidentsDTO().setCivilId(civilId);
            IInfDocumentTypesEntityKey ek1 =
                InfEntityKeyFactory.createInfDocumentTypesEntityKey(Long.valueOf(selectedDocType));
            personDocumntsDTO.setInfDocumentTypesDTO((InfDocumentTypesDTO)InfClientFactory.getInfDocumentTypesClient().getById(ek1));
            personDocumntsDTO.setStatus(1L);
            IPersonDocAtchTypesEntityKey personDocAtchTypesEntityKey = new PersonDocAtchTypesEntityKey(1L);
            IPersonDocAtchTypesClient client = ClientFactoryUtil.getInstance(IPersonDocAtchTypesClient.class);
            PersonDocAtchTypesDTO personDocAtchTypesDTO =
                (PersonDocAtchTypesDTO)client.getById(personDocAtchTypesEntityKey);
            personDocAttachemntsDTO.setPersonDocAtchTypesDTO(personDocAtchTypesDTO);
            personDocAttachemntsDTO.setAttachmentDate(SharedUtils.getCurrentDate());
            personDocAttachemntsDTO.setAttachmentDesc(getDocName());
            personDocAttachemntsDTO.setFile(getFile());
            personDocAttachemntsDTO.setValidFrom(getFromDate());
            personDocAttachemntsDTO.setStatus(1L);
            personDocAttachemntsDTO.setValidUntil(getUntilDate());
            personDocAttachemntsDTO.setPersonDocumntsDTO(personDocumntsDTO);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }

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
            if (isCheckImgExtention() == true) {
                invalidImageType = false;
                addAttachment();
                fillDto();
                IPersonDocAttachemntsClient client = ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
                client.add(getPersonDocAttachemntsDTO());
                addDocAttachment();
                setSuccess(true);
                shared_util.setSuccessMsgValue("SuccessAdds");
                reSetData();
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

    /**
     * Added By H.Omar[B.Horse Team] @ 04/08/2016
     * remove PersonDocAttachemntsRow
     */
    public void deleteAttachment() {
        System.out.println("delete selected record ...");
        PersonDocAttachemntsDTO attachDTO = (PersonDocAttachemntsDTO)getMyDataTable().getRowData();
        PersonDocAttachemntsDTO itemToDelete = (PersonDocAttachemntsDTO)getSelectedRecord(attachDTO.getCode());
        IPersonDocAttachemntsClient client = ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
        try {
            client.remove(itemToDelete);
            System.out.println("record deleted successfully.");
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
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
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
            if (selectedDocType != null && civilId != null) {
                IPersonDocAttachemntsClient client = ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
                attachmentList = client.getAllByCivilAndDocType(Long.valueOf(civilId), selectedDocType);

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
            PersonDocAttachemntsDTO attachDTO = (PersonDocAttachemntsDTO)getMyDataTable().getRowData();

            if (attachDTO != null) {
                FileDTO file = attachDTO.getFile();
                FileEntityKey fileEK = (FileEntityKey)file.getCode();
                IFileClient client = ClientFactoryUtil.getInstance(IFileClient.class);
                file = (FileDTO)client.getById(fileEK);
                String fileID = file.getCode().getKey();
                System.out.println("FileNet Enabled = " + isEnableFileNet());
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

    public Integer getListSize() {
        if (attachmentList == null) {
            return 0;
        }
        return attachmentList.size();
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
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
        if (docTypeList == null) {
            try {
                docTypeList = InfClientFactory.getInfDocumentTypesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
            }

        }
        return docTypeList;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getDocPath() {
        return docPath;
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

    public void setRelativeFileName(String relativeFileName) {
        this.relativeFileName = relativeFileName;
    }

    public String getRelativeFileName() {
        return relativeFileName;
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

    public void setDetailsSavedStates(Map<String, Object> detailsSavedStates) {
        this.detailsSavedStates = detailsSavedStates;
    }

    public Map<String, Object> getDetailsSavedStates() {
        if (detailsSavedStates == null) {
            detailsSavedStates = new HashMap<String, Object>();
        }
        return detailsSavedStates;
    }

    public void setSelectedImagePath(String selectedImagePath) {
        this.selectedImagePath = selectedImagePath;
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public void setAttchmentPath(String attchmentPath) {
        this.attchmentPath = attchmentPath;
    }

    public String getAttchmentPath() {
        return attchmentPath;
    }

    public void setAttchmentName(String attchmentName) {
        this.attchmentName = attchmentName;
    }

    public String getAttchmentName() {
        return attchmentName;
    }

    public void setAttachmentList(List<IBasicDTO> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<IBasicDTO> getAttachmentList() {
        return attachmentList;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setPersonDocumntsDTO(PersonDocumntsDTO personDocumntsDTO) {
        this.personDocumntsDTO = personDocumntsDTO;
    }

    public PersonDocumntsDTO getPersonDocumntsDTO() {
        return personDocumntsDTO;
    }

    public void setPersonDocAttachemntsDTO(PersonDocAttachemntsDTO personDocAttachemntsDTO) {
        this.personDocAttachemntsDTO = personDocAttachemntsDTO;
    }

    public PersonDocAttachemntsDTO getPersonDocAttachemntsDTO() {
        return personDocAttachemntsDTO;
    }

    public void setClonedPageDTO(PersonDocAttachemntsDTO clonedPageDTO) {
        this.clonedPageDTO = clonedPageDTO;
    }

    public PersonDocAttachemntsDTO getClonedPageDTO() {
        return clonedPageDTO;
    }

    public void setSelectedImagePath2(String selectedImagePath2) {
        this.selectedImagePath2 = selectedImagePath2;
    }

    public String getSelectedImagePath2() {
        return selectedImagePath2;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
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
        if (selectedDocType != null && !selectedDocType.equals("")) {
            file.setDocumentType(Integer.valueOf(selectedDocType));
        }
        FileManager fileManager = FileManager.getInstance();
        return fileManager.uploadFileWithFileNet(file, uploadedFile.getInputStream());
    }

    public Long getFileNetCivilId() {
        if (civilId == null) {
            return 0L;
        }
        return civilId;
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
}
