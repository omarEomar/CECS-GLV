package com.beshara.csc.gn.inf.integration.presentation.backingbean.documentAttachments;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.client.IFileClient;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.flm.flm.business.entity.FileEntityKey;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments.AttachmentUtilIntegrationBean;
import com.beshara.csc.gn.inf.integration.presentation.shared.IntegrationBeansUtil;
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
import com.beshara.csc.inf.business.entity.IPersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
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

import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.custom.fileupload.UploadedFile;


public class DocumentAttachmentsIntegrationBean extends LookUpBaseBean {

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
    private List<IBasicDTO> attachmentList = new ArrayList<IBasicDTO>();
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

    public DocumentAttachmentsIntegrationBean() {

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

    private Long GenerateDummyCode(List<IBasicDTO> lst) {
        Long code = 0L;
        for (IBasicDTO dto : lst) {
            IPersonDocAttachemntsEntityKey ek = (IPersonDocAttachemntsEntityKey)dto.getCode();
            if (ek.getSerial() > code)
                code = ek.getSerial();
        }
        return (code + 1L);
    }

    private void fillDTO() {
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

            //            String tempFileExtension = IntegrationBeansUtil.mapImageTypeToExtension(uploadedFile.getContentType());
            //            NDriveDTO driveDTO = new NDriveDTO();
            //            driveDTO.setNpath(IntegrationBeansUtil.saveToTempFile(uploadedFile.getInputStream(), tempFileExtension));
            //            getFile().setNdrive(driveDTO);

            personDocAttachemntsDTO.setFile(getFile());
            personDocAttachemntsDTO.setValidFrom(getFromDate());
            personDocAttachemntsDTO.setStatus(1L);
            personDocAttachemntsDTO.setValidUntil(getUntilDate());
            personDocAttachemntsDTO.setPersonDocumntsDTO(personDocumntsDTO);

            Long serial = GenerateDummyCode(attachmentList);
            IPersonDocAttachemntsEntityKey personDocAttachemntsEK =
                new PersonDocAttachemntsEntityKey(civilId, serial, serial);
            personDocAttachemntsDTO.setCode(personDocAttachemntsEK);
            personDocAttachemntsDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
        } catch (Exception e) {
            e.printStackTrace();
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

    public FileDTO uploadFile() throws DataBaseException, SharedApplicationException, IOException {
        insureFilesTransactionCreated();
        FileDTO file = new FileDTO();
        file.setTransaction(filesTransaction);
        file.setName(uploadedFile.getName());
        file.setSize(uploadedFile.getSize());
        file.setContentType(uploadedFile.getContentType());
        file.setSubjectName(getDocName());
        file.setOwnerCivilId(getFileNetCivilId());
        FileManager fileManager = FileManager.getInstance();
        return fileManager.uploadFileWithFileNet(file, uploadedFile.getInputStream());
    }

    public Long getFileNetCivilId() {
        if (civilId == null) {
            return 0L;
        }
        return civilId;
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
        if (isCheckImgExtention() == true) {
            invalidImageType = false;
            addAttachment();
            fillDTO();
            //IPersonDocAttachemntsClient client = ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
            //client.add(getPersonDocAttachemntsDTO());
            attachmentList.add(getPersonDocAttachemntsDTO());
            refreshMyTableData();
            setSuccess(true);
            shared_util.setSuccessMsgValue("SuccessAdds");
            reSetData();
        }
    }

    private IBasicDTO getSelectedRecord(IEntityKey code) {
        IPersonDocAttachemntsEntityKey codeEK = (IPersonDocAttachemntsEntityKey)code;
        for (IBasicDTO basicDTO : getAttachmentList()) {
            IPersonDocAttachemntsEntityKey ek = (IPersonDocAttachemntsEntityKey)basicDTO.getCode();
            if (ek.getSerial().equals(codeEK.getSerial())) {
                return basicDTO;
            }
        }
        return null;
    }

    public void deleteAttachment() {
        System.out.println("delete selected record ...");
        PersonDocAttachemntsDTO attachDTO = (PersonDocAttachemntsDTO)getMyDataTable().getRowData();
        PersonDocAttachemntsDTO itemToDelete = (PersonDocAttachemntsDTO)getSelectedRecord(attachDTO.getCode());
        if (itemToDelete != null) {
            if (itemToDelete.getStatusFlag() == null) {
                itemToDelete.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
            } else if (itemToDelete.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                getAttachmentList().remove(itemToDelete);
            }
            try {
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

            } catch (SharedApplicationException e) {
            }
        }
        refreshMyTableData();
    }

    private void refreshMyTableData() {
        try {
            setCurrentDisplayList(new ArrayList());
            for (IBasicDTO basicDTO : getAttachmentList()) {
                if (basicDTO.getStatusFlag() == null ||
                    !basicDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                    getCurrentDisplayList().add(basicDTO);
                }
            }
        } catch (Exception e) {
        }
    }

    private void reSetData() {
        personDocumntsDTO = new PersonDocumntsDTO();
        personDocAttachemntsDTO = new PersonDocAttachemntsDTO();
        setDocName("");
        setFakeImageString("");
        setFromDate(null);
        setUntilDate(null);
    }

    public void fillAttachmentList(ValueChangeEvent vce) {
        try {
            selectedDocType = (String)vce.getNewValue();
            addDocAttachment(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDocAttachment(List lst) {
        try {
            if (lst == null || lst.isEmpty()) {
                if (selectedDocType != null && civilId != null) {
                    IPersonDocAttachemntsClient client =
                        ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
                    try {
                        lst = client.getAllByCivilAndDocType(Long.valueOf(civilId), selectedDocType);
                    } catch (NoResultException no) {
                        lst = new ArrayList<IBasicDTO>();
                    }
                }
            }
            attachmentList = lst;
            refreshMyTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showAttachmentsDetails(ActionEvent e) {
        try {
            System.out.println("attch details");
            String s = IntegrationBeansUtil.getWebApplicationRootPath();
            PersonDocAttachemntsDTO attachDTO = (PersonDocAttachemntsDTO)getMyDataTable().getRowData();
            if (attachDTO != null) {
                FileDTO file = attachDTO.getFile();
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
            ex.printStackTrace();
        }
    }

    private String getFileNetUrl(String fileId) {

        String url = String.format("%s/downloadservlet/?fid=%s&fn=true", getSharedUtil().getContextPath(), fileId);
        System.out.println(String.format("Retreive file from filenet using this URL: %s", url));
        return url;
    }

    private String buildURL(HttpServletRequest req, String filePath) {
        StringBuilder url = new StringBuilder("Http://");
        try {
            url.append(req.getServerName());
            url.append(":");
            url.append(req.getServerPort());
            url.append(req.getContextPath() + "/");
            url.append(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return "/";
        }
        return url.toString();
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
        return currentDisplayList;
    }

    public void setEnableFileNet(boolean enableFileNet) {
        this.enableFileNet = enableFileNet;
    }

    public boolean isEnableFileNet() {
        return enableFileNet;
    }
}
