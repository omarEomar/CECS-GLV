package com.beshara.jsfbase.csc.backingbean.appissues;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.flm.flm.business.FileManager;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.gn.inf.integration.presentation.backingbean.adddocAttachments.AttachmentUtilIntegrationBean;
import com.beshara.csc.gn.sup.business.client.ISupAttachmentsClient;
import com.beshara.csc.gn.sup.business.client.SupClientFactory;
import com.beshara.csc.gn.sup.business.dto.ISupAttachmentsDTO;
import com.beshara.csc.gn.sup.business.dto.SupDTOFactory;
import com.beshara.csc.gn.sup.business.entity.ISupAttachmentsEntityKey;
import com.beshara.csc.gn.sup.business.entity.SupAttachmentsEntityKey;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.FlmHelper;
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


public class AttachmentsListBean extends LookUpBaseBean {
    @SuppressWarnings("compatibility:718000764353175300")
    private static final long serialVersionUID = 1L;

    
    private static final String RESOURCE_BUNDLE = "com.beshara.jsfbase.csc.msgresources.global";
    private String moduleName;    
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
    private ISupAttachmentsDTO clonedPageDTO;
    private String selectedImagePath;
    private String selectedImagePath2;    
    ISupAttachmentsDTO supAttachmentsDTO=SupDTOFactory.createSupAttachmentsDTO();    
    private Boolean disableSelectedDocType;
    private ResourceBundle bundle = null;
    private TransactionDTO filesTransaction;
    private FileDTO file;
    private String doctypeName;
    private boolean forShowAttachmentOnly;
    private List<IBasicDTO> currentDisplayList;
    
    private Long problemCode;
    private Long moveSerial;
    private Long atctypeCode;
    
    private Object savedDataObj;
    private String backNavCase;
    private String backMethod;
    private Boolean saveInDB=false;
    private int PAGE_MODE_EDIT = 2;
  
   
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowDelAlertTree(false);
        app.setShowbar(false);
    
        app.setShowdatatableContent(true);
        if (isForShowAttachmentOnly()) {
            app.setShowContent1(false);
        } else {
            app.setShowContent1(true);
        }

       
        System.out.println();
        return app;
    }
    public AttachmentsListBean() {
        setPageDTO(SupDTOFactory.createSupAttachmentsDTO());
        super.setSelectedPageDTO(SupDTOFactory.createSupAttachmentsDTO());
        setClient((ISupAttachmentsClient)SupClientFactory.getSupAttachmentsClient());
        setSingleSelection(true);
        setSaveSortingState(true);
       // String moveSerial =((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("moveSerial");
    }

//    public void reInitializePageDTO() {
//        this.setPageDTO(SupDTOFactory.createSupAttachmentsDTO());
//    }
//
//
//    public void search() throws DataBaseException, NoResultException {
//        if (this.getSearchType().intValue() == 0)
//            super.setSearchEntityObj(new Long(this.getSearchQuery()));
//        super.search();
//    }

    private void fillDTO(){
        if(saveInDB)
            fillDTOForDB();
        else
            fillDTOForList();
            
    }
    private void fillDTOForList() {
        try {
//            String tempFileExtension = IntegrationBeansUtil.mapImageTypeToExtension(uploadedFile.getContentType());
//            NDriveDTO driveDTO = new NDriveDTO();
//            driveDTO.setNpath(IntegrationBeansUtil.saveToTempFile(uploadedFile.getInputStream(), tempFileExtension));
//            getFile().setNdrive(driveDTO);           
            supAttachmentsDTO=SupDTOFactory.createSupAttachmentsDTO();
            //supAttachmentsDTO.setFile(getFile()); 
            supAttachmentsDTO.setProblemCode(this.problemCode);
            supAttachmentsDTO.setMoveSerial(1L);
            supAttachmentsDTO.setName(getFile().getName());
            supAttachmentsDTO.setFilePath(getFile().getCode().getKey());
            supAttachmentsDTO.setAtctypeCode(2L);
            supAttachmentsDTO.setSupattachmentName(getDocName()); 
            if(attachmentList==null){
                    attachmentList=new ArrayList();
                }
            Long serial = GenerateDummyCode(attachmentList);
            ISupAttachmentsEntityKey supAttachmentsEK = new SupAttachmentsEntityKey(serial,serial,serial);
            supAttachmentsDTO.setCode(supAttachmentsEK);
            supAttachmentsDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
    private void fillDTOForDB() {
            try {
                supAttachmentsDTO=SupDTOFactory.createSupAttachmentsDTO();
                supAttachmentsDTO.setProblemCode(this.problemCode);
                supAttachmentsDTO.setMoveSerial(this.moveSerial);
                supAttachmentsDTO.setName(getFile().getName());
                supAttachmentsDTO.setFilePath(getFile().getCode().getKey());
                supAttachmentsDTO.setAtctypeCode(this.atctypeCode);
                supAttachmentsDTO.setSupattachmentName(getDocName());           
                
            } catch (Exception e) {
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
//        if(!saveInDB)
//            return file;
        FileManager fileManager = FileManager.getInstance();
        return fileManager.uploadFile(file, uploadedFile.getInputStream());
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
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(){
        if(saveInDB)
            saveInDB();
        else
            saveInList(); 
                
    }
    public void saveInList() {
        SharedUtilBean shared_util = getSharedUtil();
            if (isCheckImgExtention() == true) {
                invalidImageType = false;
                addAttachment();
                fillDTO();
                //IPersonDocAttachemntsClient client = ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);
                //client.add(getPersonDocAttachemntsDTO());
                if(attachmentList==null){
                        attachmentList=new ArrayList<IBasicDTO> ();
                    }
                attachmentList.add(supAttachmentsDTO);
                refreshMyTableData();
                setSuccess(true);
                //shared_util.setSuccessMsgValue("SuccessAdds");
                reSetData();
            }
    }
    public void saveInDB() {
            SharedUtilBean shared_util = getSharedUtil();
            try {
                if (isCheckImgExtention() == true) {
                    invalidImageType = false;
                    addAttachment();
                    fillDTO();
                    ISupAttachmentsClient client = ClientFactoryUtil.getInstance(ISupAttachmentsClient.class);
                    client.add(supAttachmentsDTO);
                    addDocAttachment(null);
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
    public void deleteAttachment(){
        if(saveInDB)
            deleteAttachmentFromDB();
        else
            deleteAttachmentFromList();
    }
    public void deleteAttachmentFromDB() {
        System.out.println("delete selected record ...");
        ISupAttachmentsDTO attachDTO = (ISupAttachmentsDTO)getMyDataTable().getRowData();
        ISupAttachmentsDTO itemToDelete = (ISupAttachmentsDTO)getSelectedRecord(attachDTO.getCode());
        ISupAttachmentsClient client = ClientFactoryUtil.getInstance(ISupAttachmentsClient.class);
        try {
            client.remove(itemToDelete);
            System.out.println("record deleted successfully.");
            refreshMyTableData();
            addDocAttachment(null);
//            FileManager fileManager = FileManager.getInstance();
//            fileManager.deleteFile(itemToDelete.getFilePath());
            getSharedUtil().handleSuccMsg(RESOURCE_BUNDLE, "delete_Succ_Msg");
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }
    public void deleteAttachmentFromList() {
        System.out.println("delete selected record ...");
        ISupAttachmentsDTO attachDTO = (ISupAttachmentsDTO)getMyDataTable().getRowData();
        ISupAttachmentsDTO itemToDelete = (ISupAttachmentsDTO)getSelectedRecord(attachDTO.getCode());
        if(itemToDelete != null){
            if(itemToDelete.getStatusFlag() == null){
                itemToDelete.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
            }else if(itemToDelete.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)){
                getAttachmentList().remove(itemToDelete);
            }
//            FileManager fileManager = FileManager.getInstance();
//            fileManager.deleteFile(itemToDelete.getFilePath());
        }
        refreshMyTableData();
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
        try {
            if (saveInDB) {
                setCurrentDisplayList(getAttachmentList());
            } else {
                setCurrentDisplayList(new ArrayList());
                for (IBasicDTO basicDTO : getAttachmentList()) {
                    if (basicDTO.getStatusFlag() == null ||
                        !basicDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                        getCurrentDisplayList().add(basicDTO);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reSetData() {
        if(!saveInDB)
            supAttachmentsDTO=SupDTOFactory.createSupAttachmentsDTO(); 
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
    /*public void addDocAttachment() {
            try {            
                if (problemCode!=null && moveSerial!=null && atctypeCode!=null) {
                    ISupAttachmentsClient client = ClientFactoryUtil.getInstance(ISupAttachmentsClient.class);
                    attachmentList = client.getByProblemCodeAndMoveSerialAndType(problemCode, moveSerial,atctypeCode); 

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
    }*/
    public void addDocAttachment(List lst) {
        try {     
            if(lst == null || lst.isEmpty()){
            if (problemCode!=null && moveSerial!=null && atctypeCode!=null) {
                ISupAttachmentsClient client = ClientFactoryUtil.getInstance(ISupAttachmentsClient.class);
                lst = client.getByProblemCodeAndMoveSerialAndType(problemCode, moveSerial,atctypeCode); 

                if (lst == null) {
                    lst = new ArrayList<IBasicDTO>();
                }
            } else {
                lst = new ArrayList<IBasicDTO>();
            }
            }
            attachmentList = lst;
            refreshMyTableData();
        } catch (Exception e) {
            e.printStackTrace();
            lst = new ArrayList<IBasicDTO>();
        }
        
        if(saveInDB)
            setCurrentDisplayList(attachmentList);
    }

    /*public void showAttachmentsDetails(ActionEvent e){
        if(saveInDB)
            showAttachmentsDetailsFromDB(e);
        else
            showAttachmentsDetailsFromList(e);
    }
    public void showAttachmentsDetailsFromList(ActionEvent e) {
        try{
        System.out.println("attch details");
        String s = IntegrationBeansUtil.getWebApplicationRootPath();
        ISupAttachmentsDTO attachDTO = (ISupAttachmentsDTO)getMyDataTable().getRowData();
        if (attachDTO != null) {
            FileDTO file = attachDTO.getFile();
            if(attachDTO.getStatusFlag() != null){
                selectedImagePath = IntegrationBeansUtil.getTempUploadImagePathToOpen(file.getNdrive().getNpath());
                selectedImagePath = selectedImagePath.replace("\\", "/");
                HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                selectedImagePath = buildURL(req, selectedImagePath);
            }else{
//                FileEntityKey fileEK = (FileEntityKey)file.getCode();
//                IFileClient client = ClientFactoryUtil.getInstance(IFileClient.class);
//                file = (FileDTO)client.getById(fileEK);
//                String fileID = file.getCode().getKey();
                selectedImagePath = FlmHelper.getFileURL(attachDTO.getFilePath());
            }
            setJavaScriptCaller("openRegIntgDecisionDetailsWindow();");
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }*/
    public void showAttachmentsDetails(ActionEvent e) {
          try {
              System.out.println("attch details");

              ISupAttachmentsDTO dto = (ISupAttachmentsDTO)getMyDataTable().getRowData();
              selectedImagePath = FlmHelper.getFileURL(dto.getFilePath());
              if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
                  setJavaScriptCaller("openRegIntgDecisionDetailsWindow();");
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
  }    
    /*private String buildURL(HttpServletRequest req, String filePath) {
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
    }*/
    public String back() {
    if (backMethod != null && !backMethod.equals("")) {
        try {
            Object[] objects = new Object[1];   
            Map<String, Object> obj = (Map<String, Object>)savedDataObj;
            if(attachmentList!=null)
                obj.put("attachmentList", attachmentList);
            objects[0] = obj;

            Class[] classes = new Class[1];                
            classes[0] = Object.class;
            executeMethodBindingWithParams(backMethod, objects, classes);
        } catch (Exception er) {
            er.printStackTrace();
        }
    }
    return backNavCase;
    }
    
    
 
    
    /// new Modifications 
    private Long GenerateDummyCode(List<IBasicDTO> lst){
        
        Long code = 0L;
        for(IBasicDTO dto : lst){
            ISupAttachmentsEntityKey ek = (ISupAttachmentsEntityKey)dto.getCode();
            if(ek.getProblemCode() > code)
                code = ek.getProblemCode();
        }
        return (code + 1L);
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
        if (docTypeList == null) {
            try {
                docTypeList = InfClientFactory.getInfDocumentTypesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
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
    public void setSupAttachmentsDTO(ISupAttachmentsDTO supAttachmentsDTO) {
        this.supAttachmentsDTO = supAttachmentsDTO;
    }

    public ISupAttachmentsDTO getSupAttachmentsDTO() {
        return supAttachmentsDTO;
    }

    public void setClonedPageDTO(ISupAttachmentsDTO clonedPageDTO) {
        this.clonedPageDTO = clonedPageDTO;
    }

    public ISupAttachmentsDTO getClonedPageDTO() {
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
        if(saveInDB !=null && saveInDB.booleanValue()==true){
            if (currentDisplayList == null || currentDisplayList.isEmpty()) {
                currentDisplayList = attachmentList;
            }
        }
        return currentDisplayList;
    }

    public void setProblemCode(Long problemCode) {
        this.problemCode = problemCode;
    }

    public Long getProblemCode() {
        return problemCode;
    }

    public void setMoveSerial(Long moveSerial) {
        this.moveSerial = moveSerial;
    }

    public Long getMoveSerial() {
        return moveSerial;
    }

    public void setAtctypeCode(Long atctypeCode) {
        this.atctypeCode = atctypeCode;
    }

    public Long getAtctypeCode() {
        return atctypeCode;
    }

    public void setSavedDataObj(Object savedDataObj) {
        this.savedDataObj = savedDataObj;
    }

    public Object getSavedDataObj() {
        return savedDataObj;
    }

    public void setBackNavCase(String backNavCase) {
        this.backNavCase = backNavCase;
    }

    public String getBackNavCase() {
        return backNavCase;
    }

    public void setBackMethod(String backMethod) {
        this.backMethod = backMethod;
    }

    public String getBackMethod() {
        return backMethod;
    }

    public void setSaveInDB(Boolean saveInDB) {
        this.saveInDB = saveInDB;
    }

    public Boolean getSaveInDB() {
        return saveInDB;
    }

    public void setPAGE_MODE_EDIT(int PAGE_MODE_EDIT) {
        this.PAGE_MODE_EDIT = PAGE_MODE_EDIT;
    }

    public int getPAGE_MODE_EDIT() {
        return PAGE_MODE_EDIT;
    }
    
    public void initAttachments(javax.faces.event.ActionEvent actionEvent) {
        try {
            moveSerial =
                    StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("moveSerial"));
            atctypeCode =
                    StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("atctypeCode"));
            problemCode =
                    StringToLong(((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("problemCode"));
            System.out.println(moveSerial+"-"+atctypeCode+"-"+problemCode);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        }
    
    public Long StringToLong(String str){
        if(str == null || str.trim().equals("")){
            return null;
        }
        return Long.valueOf(str);
    }
    
    public static AttachmentsListBean getInstance() {
        return new AttachmentsListBean();
    }
}




