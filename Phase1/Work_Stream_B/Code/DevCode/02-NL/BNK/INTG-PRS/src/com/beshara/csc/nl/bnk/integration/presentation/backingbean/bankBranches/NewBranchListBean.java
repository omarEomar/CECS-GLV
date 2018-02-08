package com.beshara.csc.nl.bnk.integration.presentation.backingbean.bankBranches;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwMapEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.bnk.business.client.BnkClientFactory;
import com.beshara.csc.nl.bnk.business.client.IBankContactPersonClient;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.bnk.business.dto.BankContactPersonDTO;
import com.beshara.csc.nl.bnk.business.dto.BanksDTO;
import com.beshara.csc.nl.bnk.business.dto.BnkDTOFactory;
import com.beshara.csc.nl.bnk.business.entity.IBankBranchesEntityKey;
import com.beshara.csc.nl.bnk.business.shared.IBnkConstant;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.validations.GlobalValidation;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class NewBranchListBean extends LookUpBaseBean {
    private static final String BNK_RESOURCES = "com.beshara.csc.nl.bnk.integration.presentation.resources.integration";
    private final Long governorateTypeCode = 1L;

    private String branchName = "";
    private String branchEmail = "";
    private String branchTel = "";
    private String branchFax = "";
    private String branchGovernorateCode = "";
    private String branchDistrictCode = "";
    private String branchSectorCode = "";
    private String branchStreetCode = "";
    private String branchMapCode = "";
    private String buildingNumber = "";

    private Boolean validCivilId = false;
    private String civilId = "";
    private String civilEmail = "";
    private String civilTel = "";
    private String civilMobile = "";
    private String civilFax = "";
    private String civilName = "";
    private String civilGender = "";
    private Boolean enableSave = false;
    private Boolean civilExist = false;

    private BanksDTO bankDTO = null;
    private BankBranchesDTO bankBranchDTO = null;
    private IKwtCitizensResidentsDTO kwtCitizenDTO = null;

    private List<IBasicDTO> governorateList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> districtList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> sectorList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> streetList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> bankContactPersonsList = new ArrayList<IBasicDTO>();
    private List<IBasicDTO> removedBankContactPersonsList = new ArrayList<IBasicDTO>();
    private List<Long> civilList = new ArrayList<Long>();

    /* nora csc_bnk036 */
    private int pageMode = 0; // 0 for add 1 for edit
    private int divMode = 1; // 1 for add 2 for edit
    private String pageTitle;

    private boolean coordinatorExistBefore = false;
    private String bnkBrnchNameMsg = null;

    public NewBranchListBean() {
        setPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setSelectedPageDTO(BnkDTOFactory.createBankBranchesDTO());
        setClient(BnkClientFactory.getBankBranchesClient());
        setMyTableData(new ArrayList<IBasicDTO>());
        removedBankContactPersonsList = new ArrayList<IBasicDTO>();

        setUsingPaging(false);
        setSaveSortingState(true);
        setSingleSelection(true);

        kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        bankBranchDTO = BnkDTOFactory.createBankBranchesDTO();
        //  setMsgDivHeight(90L);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(true);
        app.setShowDelConfirm(true);
        app.setShowContent1(true);
        app.setShowCustomDiv1(true);
        return app;
    }
    /* nora csc_bnk036 */

    public void initEditMode(BankBranchesDTO bankBranchDTO) {
        setPageMode(1);

        getGovernorates();
        IBankBranchesEntityKey code = (IBankBranchesEntityKey)bankBranchDTO.getCode();
        Long bankCode = code.getBankCode();
        Long bnkBranchCode = code.getBnkbranchCode();
        List<IBasicDTO> list = new ArrayList();

        try {
            this.bankBranchDTO = bankBranchDTO;
            branchName = bankBranchDTO.getName();

            branchEmail = bankBranchDTO.getEmail();
            branchTel = bankBranchDTO.getPhonesNo();
            branchFax = bankBranchDTO.getFaxNo();
            branchStreetCode =
                    (bankBranchDTO.getStreetCode() != null) ? bankBranchDTO.getStreetCode().toString() : null;
            branchMapCode = bankBranchDTO.getMapCode();
            String tempBranchMapCode = branchMapCode;
            setBranchGovernorateCode(branchMapCode);
            buildingNumber = bankBranchDTO.getBuildingNo();
            if (branchMapCode != null)
                getBranchAddressDetails();
            setBankDTO(bankBranchDTO.getBanksDTO());
            list =
BnkClientFactory.getBankContactPersonClient().getBankContactPersonsByBankBranch(bankCode, bnkBranchCode);
            setBankContactPersonsList(list);
            setMyTableData(bankContactPersonsList);
            changeGovernoratesEvent(null);
            branchMapCode = tempBranchMapCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getGovernorates() {
        try {
            governorateList = //new ArrayList<IBasicDTO>()
                    InfClientFactory.getKwMapClient().searchByTypeCode(governorateTypeCode);
            //        } catch (DataBaseException db) {
            //            governorateList = new ArrayList<IBasicDTO>();
            //            getSharedUtil().handleException(db);
        } catch (Exception e) {
            governorateList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(e);
        }
    }

    public void changeGovernoratesEvent(ActionEvent e) {
        try {
            branchMapCode = branchGovernorateCode;
            getDistricts();
            getStreets();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getDistricts() {
        try {
            if (branchMapCode == null || branchMapCode.equals("")) {
                districtList = new ArrayList<IBasicDTO>();
                sectorList = new ArrayList<IBasicDTO>();
            } else {
                IKwMapEntityKey parentKey = InfEntityKeyFactory.createKwMapEntityKey(branchMapCode);
                districtList = InfClientFactory.getKwMapClient().getChildrenList(parentKey);
            }
        } catch (DataBaseException db) {
            districtList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            districtList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(e);
        }
    }

    public void changeDistrictsEvent(ActionEvent e) {
        try {
            branchMapCode = branchDistrictCode;
            getSectors();
            getStreets();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getSectors() {
        try {
            if (branchMapCode == null || branchMapCode.equals("")) {
                sectorList = new ArrayList<IBasicDTO>();
            } else {
                IKwMapEntityKey parentKey = InfEntityKeyFactory.createKwMapEntityKey(branchMapCode);
                sectorList = InfClientFactory.getKwMapClient().getChildrenList(parentKey);
            }
        } catch (DataBaseException db) {
            sectorList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            sectorList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(e);
        }
    }

    public void changeSectorsEvent(ActionEvent e) {
        try {
            branchMapCode = branchSectorCode;
            getStreets();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getStreets() {
        try {
            if (branchMapCode == null || branchMapCode.equals("")) {
                streetList = new ArrayList<IBasicDTO>();
            } else {
                streetList = InfClientFactory.getStreetZonesClient().getByMapCode(branchMapCode);
            }
        } catch (DataBaseException db) {
            streetList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(db);
        } catch (Exception e) {
            streetList = new ArrayList<IBasicDTO>();
            getSharedUtil().handleException(e);
        }
    }

    public void IntiateGovernorates() {
        try {
            getGovernorates();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveBranchData() {

        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            if (pageMode == 0) {
                bankBranchDTO = BnkDTOFactory.createBankBranchesDTO();
            }
            bankBranchDTO.setName(branchName);
            bankBranchDTO.setEmail(branchEmail);
            bankBranchDTO.setPhonesNo(branchTel);
            bankBranchDTO.setFaxNo(branchFax);

            if (!branchStreetCode.equals("")) {
                bankBranchDTO.setStreetCode(Long.valueOf(branchStreetCode));
            }
            bankBranchDTO.setMapCode(branchMapCode);
            bankBranchDTO.setBuildingNo(buildingNumber);
            bankBranchDTO.setStatus(1L);
            bankBranchDTO.setBanksDTO(bankDTO);
            if (removedBankContactPersonsList.size() > 0) {
                List newBankContactPersonsList = new ArrayList(removedBankContactPersonsList);
                for (int i = 0; i < bankContactPersonsList.size(); i++) {
                    newBankContactPersonsList.add(bankContactPersonsList.get(i));
                }
                bankContactPersonsList = newBankContactPersonsList;
            }

            IBankContactPersonClient branchContactClient = BnkClientFactory.getBankContactPersonClient();

            bankBranchDTO =
                    (BankBranchesDTO)branchContactClient.SaveBankBranchWithCoordinators(bankBranchDTO, bankContactPersonsList);

            sharedUtil.handleSuccMsg(BNK_RESOURCES, "branches_add_success");

            resetData();

            BranchesListBean branchesListBean = (BranchesListBean)evaluateValueBinding("branchesListBean");
            branchesListBean.getBankBranchesList();

            try {
                branchesListBean.getPageIndex(bankBranchDTO.getCode().getKey());
                branchesListBean.setPageDTO(bankBranchDTO);
                if (branchesListBean.getHighlightedDTOS() != null) {
                    branchesListBean.getHighlightedDTOS().add(bankBranchDTO);
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
            }

            return "ShowBranchesList";
        }
        //        catch (ContactPersonAlreadyExistWithDiffBnkException e) {
        //            BankContactPersonDTO dto = (BankContactPersonDTO)e.getExtraInformation().get(0);
        //            e.printStackTrace();
        //            String bnkName=sharedUtil.messageLocator(BNK_RESOURCES, "contact_person_work_at_bnk")+"\n"+sharedUtil.messageLocator(BNK_RESOURCES, "at_bnk")+":"+dto.getBankBranchesDTO().getBanksDTO().getName();
        //            String branchName=sharedUtil.messageLocator(BNK_RESOURCES, "at_branch")+":"+dto.getBankBranchesDTO().getName()+"";
        //            String msgVal=bnkName+"\n"+branchName;
        //            System.out.println("msgVal"+msgVal);
        //            sharedUtil.setErrMsgValue(msgVal);
        //           // sharedUtil.handleException(e, BNK_RESOURCES, "contact_person_Already_exist_msg");
        //        }

        catch (SharedApplicationException e) {
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "branche_already_exist");
        } catch (DataBaseException e) {
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "branches_add_fail");
        }

        return "";
    }

    public void filterByCivilId() {
        try {
            coordinatorExistBefore = false;
            bnkBrnchNameMsg = null;
            this.setShowErrorMsg(false);
            if (this.getCivilId() != null) {
                Long citizenCivilID = Long.parseLong(civilId);
                if (GlobalValidation.isValidCivilId(citizenCivilID)) {
                    validCivilId = true;

                    IKwtCitizensResidentsClient kwtClient = InfClientFactory.getKwtCitizensResidentsClient();
                    try {
                        kwtClient.checkCivilIdExist(citizenCivilID);
                        IKwtCitizensResidentsEntityKey citizenKey =
                            InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(citizenCivilID);
                        kwtCitizenDTO = (IKwtCitizensResidentsDTO)kwtClient.getKwtCitizensResidents(citizenKey);
                        civilEmail = kwtCitizenDTO.getEMail();
                        civilTel = kwtCitizenDTO.getPhonesNo();
                        civilMobile = kwtCitizenDTO.getMobileNo();
                        civilName = kwtCitizenDTO.getFullName();
                        civilGender = kwtCitizenDTO.getGenderTypesDTO().getName();
                        enableSave = true;
                        BankContactPersonDTO dto =
                            BnkClientFactory.getBankContactPersonClient().getBankContactPersonByCivilId(citizenCivilID);
                        if (dto != null) {
                            coordinatorExistBefore = true;
                            String bnkName =
                                getSharedUtil().messageLocator(BNK_RESOURCES, "contact_person_work_at_bnk") + ":" +
                                dto.getBankBranchesDTO().getBanksDTO().getName();
                            String branchName =
                                getSharedUtil().messageLocator(BNK_RESOURCES, "at_branch") + ":" + dto.getBankBranchesDTO().getName() +
                                "";
                            String msgVal = bnkName + " " + branchName;
                            bnkBrnchNameMsg = msgVal;

                            kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
                            validCivilId = false;
                            enableSave = false;
                        }
                    } catch (Exception civilNotExist) {
                        civilNotExist.printStackTrace();
                        reCoordinatorSetData(null);
                        kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
                        validCivilId = false;
                        enableSave = false;
                    }
                } else {
                    kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
                    validCivilId = false;
                    enableSave = false;
                }
            }
        } catch (Exception err) {
            kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
            enableSave = false;
            err.printStackTrace();
        }
    }

    public void addCoordinator() {
        try {
            setDivMode(1);
            reCoordinatorSetData(null);
            civilFax = "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delCoordinator() {
        try {
            //            reCoordinatorSetData(null);
            //            civilFax = "";
            getSelectedDTOS().clear();
            getSelectedDTOS().add((BankContactPersonDTO)getMyDataTable().getRowData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() throws DataBaseException, ItemNotFoundException {
        //        SharedUtilBean shared_util = getSharedUtil();
        System.out.println("Calling delete()...");
        //        FacesContext ctx = FacesContext.getCurrentInstance();
        //        ExternalContext ex = ctx.getExternalContext();
        //        HttpSession session = (HttpSession)ex.getSession(true);

        if (getSelectedDTOS() == null)
            setSelectedDTOS(loadSelectedDTOS());

        //            for (IBasicDTO dto: getSelectedDTOS()) {


        try {
            //deleteStatusDTOS = client.remove(selectedDTOS);
            getDeleteStatusDTOS().clear();
            Long civId = ((BankContactPersonDTO)getSelectedDTOS().get(0)).getCivilId();
            civilList.remove(civId);
            BankContactPersonDTO bankContactPersonDTO2 = null;
            for (int i = 0; i < bankContactPersonsList.size(); i++) {
                bankContactPersonDTO2 = (BankContactPersonDTO)bankContactPersonsList.get(i);
                if (civId.equals((bankContactPersonDTO2).getCivilId())) {
                    IResultDTO resultDTO = new ResultDTO();
                    if (bankContactPersonDTO2.getOperation() == null) { // come from DB
                        bankContactPersonDTO2.setOperation(IBnkConstant.OPERATION_DELETE);
                        removedBankContactPersonsList.add(bankContactPersonDTO2);
                    }
                    resultDTO.setCurrentObject(bankContactPersonDTO2);
                    resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                    getDeleteStatusDTOS().add(resultDTO);

                    bankContactPersonsList.remove(i);
                    break;
                }
            }

            setMyTableData(bankContactPersonsList);
            if (getMyDataTable() != null && getMyDataTable().getRowCount() != 0) {
                getMyDataTable().setFirst(0);
            }
        } catch (Exception e) {
            //getSharedUtil().handleException(e);
            e.printStackTrace();
        }
        this.getSelectedDTOS().clear();
        //        session.setAttribute("selectedDTOS", null);
        //        cancelSearch();
        restoreTablePosition();
    }

    public void updateCoordinator() {
        try {
            //resetData();
            //civilExist=false;
            reCoordinatorSetData(null);
            setDivMode(2);
            getSelectedDTOS().clear();
            BankContactPersonDTO bankContactPersonDTO = (BankContactPersonDTO)getMyDataTable().getRowData();
            getSelectedDTOS().add(bankContactPersonDTO);
            BankContactPersonDTO selDto = (BankContactPersonDTO)getSelectedDTOS().get(0);
            IKwtCitizensResidentsClient kwtClient = InfClientFactory.getKwtCitizensResidentsClient();
            IKwtCitizensResidentsEntityKey citizenKey =
                InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(selDto.getCivilId());
            kwtCitizenDTO = (IKwtCitizensResidentsDTO)kwtClient.getKwtCitizensResidents(citizenKey);
            enableSave = true;
            civilId = selDto.getCivilId().toString();
            civilEmail = bankContactPersonDTO.getEmail(); // kwtCitizenDTO.getEMail();
            civilTel = bankContactPersonDTO.getDirectPhones(); // kwtCitizenDTO.getPhonesNo();
            civilMobile = bankContactPersonDTO.getMobileNo(); // kwtCitizenDTO.getMobileNo();
            civilName = bankContactPersonDTO.getName(); // kwtCitizenDTO.getFullName();
            civilGender = bankContactPersonDTO.getGender(); // kwtCitizenDTO.getGenderTypesDTO().getName();
            civilFax = bankContactPersonDTO.getFax(); // "";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SaveCoordinator() throws SharedApplicationException, DataBaseException {

        if (divMode == 2) /*edit mode*/ {
            BankContactPersonDTO branchContactPersonDTO = (BankContactPersonDTO)getSelectedDTOS().get(0);
            //            IKwtCitizensResidentsClient kwtClient = InfClientFactory.getKwtCitizensResidentsClient();
            //            IKwtCitizensResidentsEntityKey citizenKey = InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(selDto.getCivilId() );
            //            kwtCitizenDTO = (IKwtCitizensResidentsDTO)kwtClient.getKwtCitizensResidents(citizenKey);
            branchContactPersonDTO.setEmail(civilEmail);
            branchContactPersonDTO.setDirectPhones(civilTel);
            branchContactPersonDTO.setMobileNo(civilMobile);
            branchContactPersonDTO.setFax(civilFax);

        } else if (divMode == 1) /*add mode*/ {
            if (civilList.indexOf(Long.valueOf(civilId)) > -1) { /** check if exist in memory */
                civilExist = true;
                throw new SharedApplicationException("CivilExistInList");
            }

            BankContactPersonDTO branchContactPersonDTO = null;

            IBankBranchesEntityKey code = (IBankBranchesEntityKey)bankBranchDTO.getCode();
            if (code != null) {
                Long bankCode = code.getBankCode();
                Long bnkBranchCode = code.getBnkbranchCode();
                branchContactPersonDTO =
                        BnkDTOFactory.createBankContactPersonDTO(Long.valueOf(civilId), bankCode, bnkBranchCode);
                /** check if exist in D.B. */
                //                try {
                //                    BankContactPersonDTO dB_branchContactPersonDTO =
                //                        (BankContactPersonDTO)BnkClientFactory.getBankContactPersonClient().getById(branchContactPersonDTO.getCode());
                //                    if(dB_branchContactPersonDTO != null){
                //                        civilExist = true;
                //                        throw new SharedApplicationException("CivilExistInList");
                //                    }
                //                } catch (DataBaseException dbe) {
                //                    // TODO: Add catch code
                //                    dbe.printStackTrace();
                //                } catch (SharedApplicationException sae) {
                //                    // TODO: Add catch code
                //                    sae.printStackTrace();
                //                    if(sae.getMessage().equals("CivilExistInList") )throw sae;
                //                }

            } else {
                branchContactPersonDTO = BnkDTOFactory.createBankContactPersonDTO();
            }

            branchContactPersonDTO.setBankCode(Long.valueOf(bankDTO.getCode().getKey()));
            branchContactPersonDTO.setCivilId(Long.valueOf(civilId));
            branchContactPersonDTO.setName(civilName);
            branchContactPersonDTO.setGender(civilGender);
            branchContactPersonDTO.setEmail(civilEmail);
            branchContactPersonDTO.setDirectPhones(civilTel);
            branchContactPersonDTO.setMobileNo(civilMobile);
            branchContactPersonDTO.setFax(civilFax);
            branchContactPersonDTO.setStatus(1L);
            branchContactPersonDTO.setOperation(IBnkConstant.OPERATION_ADD);

            civilExist = false;
            bankContactPersonsList.add(branchContactPersonDTO);
            civilList.add(Long.valueOf(civilId));

            setMyTableData(bankContactPersonsList);

        }
    }

    public void AddBranchCoordinator() {
        SharedUtilBean sharedUtil = getSharedUtil();
        try {
            SaveCoordinator();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            sharedUtil.handleException(e, BNK_RESOURCES, "civilInListError");
        } catch (Exception e) {
            e.printStackTrace();
            bankContactPersonsList = new ArrayList<IBasicDTO>();
            sharedUtil.handleException(e, BNK_RESOURCES, "coordinator_add_fail");
        }
    }

    public void saveAndNewCoordinator() {
        SharedUtilBean sharedUtil = getSharedUtil();

        try {
            SaveCoordinator();
            reCoordinatorSetData(null);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            bankContactPersonsList = new ArrayList<IBasicDTO>();
            sharedUtil.handleException(e, BNK_RESOURCES, "coordinator_add_fail");
        }
    }

    public void resetData() {
        branchName = "";
        branchEmail = "";
        branchTel = "";
        branchFax = "";
        branchGovernorateCode = "";
        branchDistrictCode = "";
        branchSectorCode = "";
        branchStreetCode = "";
        branchMapCode = "";
        buildingNumber = "";
    }

    public void reCoordinatorSetData(ActionEvent event) {

        kwtCitizenDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        civilId = null;
        validCivilId = false;
        civilEmail = "";
        civilTel = "";
        civilMobile = "";
        civilFax = "";
        civilName = "";
        civilGender = "";
        this.setShowErrorMsg(false);
        enableSave = false;
        civilExist = false;
        coordinatorExistBefore = false;
        bnkBrnchNameMsg = null;

    }

    public String backToBranches() {
        BranchesListBean branchesListBean = (BranchesListBean)evaluateValueBinding("branchesListBean");
        branchesListBean.getBankBranchesList();

        return "ShowBranchesList";
    }

    //Added by Ayman for CSC_BNK036
    //Start

    public void getBranchAddressDetails() {
        try {
            String tempSectorCode = null;
            String tempDistrictCode = null;

            IKwMapEntityKey kwMapEntityKey = InfEntityKeyFactory.createKwMapEntityKey(branchMapCode);
            IKwMapDTO kwMapDTO = (IKwMapDTO)InfClientFactory.getKwMapClient().getById(kwMapEntityKey);

            branchGovernorateCode = ((IKwMapEntityKey)kwMapDTO.getFirstParent()).getMapCode();
            branchMapCode = branchGovernorateCode;

            getDistricts();
            getStreets();

            while (kwMapDTO.getParentCode() != null) {
                if (kwMapDTO.getTypeCode().equals(IBnkConstant.ADDRESS_BUILDING_TYPE_CODE)) {
                    tempSectorCode = ((IKwMapEntityKey)kwMapDTO.getCode()).getMapCode();
                } else if (kwMapDTO.getTypeCode().equals(IBnkConstant.ADDRESS_DISTRICT_TYPE_CODE)) {
                    tempDistrictCode = ((IKwMapEntityKey)kwMapDTO.getCode()).getMapCode();
                }
                kwMapDTO = (IKwMapDTO)InfClientFactory.getKwMapClient().getById(kwMapDTO.getParentCode());
            }

            if (tempDistrictCode != null) {
                branchMapCode = tempDistrictCode;
                getSectors();
                getStreets();
                branchDistrictCode = tempDistrictCode;
            }

            if (tempSectorCode != null) {
                branchMapCode = tempSectorCode;
                getStreets();
                branchSectorCode = tempSectorCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //End

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public String getBranchEmail() {
        return branchEmail;
    }

    public void setBranchTel(String branchTel) {
        this.branchTel = branchTel;
    }

    public String getBranchTel() {
        return branchTel;
    }

    public void setBranchFax(String branchFax) {
        this.branchFax = branchFax;
    }

    public String getBranchFax() {
        return branchFax;
    }

    public void setBranchGovernorateCode(String branchGovernorateCode) {
        this.branchGovernorateCode = branchGovernorateCode;
    }

    public String getBranchGovernorateCode() {
        return branchGovernorateCode;
    }

    public void setBranchDistrictCode(String branchDistrictCode) {
        this.branchDistrictCode = branchDistrictCode;
    }

    public String getBranchDistrictCode() {
        return branchDistrictCode;
    }

    public void setBranchSectorCode(String branchSectorCode) {
        this.branchSectorCode = branchSectorCode;
    }

    public String getBranchSectorCode() {
        return branchSectorCode;
    }

    public void setBranchStreetCode(String branchStreetCode) {
        this.branchStreetCode = branchStreetCode;
    }

    public String getBranchStreetCode() {
        return branchStreetCode;
    }

    public void setDistrictList(List<IBasicDTO> districtList) {
        this.districtList = districtList;
    }

    public List<IBasicDTO> getDistrictList() {
        return districtList;
    }

    public void setSectorList(List<IBasicDTO> sectorList) {
        this.sectorList = sectorList;
    }

    public List<IBasicDTO> getSectorList() {
        return sectorList;
    }

    public void setStreetList(List<IBasicDTO> streetList) {
        this.streetList = streetList;
    }

    public List<IBasicDTO> getStreetList() {
        return streetList;
    }

    public void setGovernorateList(List<IBasicDTO> governorateList) {
        this.governorateList = governorateList;
    }

    public List<IBasicDTO> getGovernorateList() {
        return governorateList;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBranchMapCode(String branchMapCode) {
        this.branchMapCode = branchMapCode;
    }

    public String getBranchMapCode() {
        return branchMapCode;
    }

    public void setBankDTO(BanksDTO bankDTO) {
        this.bankDTO = bankDTO;
    }

    public BanksDTO getBankDTO() {
        return bankDTO;
    }

    public void setKwtCitizenDTO(IKwtCitizensResidentsDTO kwtCitizenDTO) {
        this.kwtCitizenDTO = kwtCitizenDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizenDTO() {
        return kwtCitizenDTO;
    }

    public void setValidCivilId(Boolean validCivilId) {
        this.validCivilId = validCivilId;
    }

    public Boolean getValidCivilId() {
        return validCivilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setCivilEmail(String civilEmail) {
        this.civilEmail = civilEmail;
    }

    public String getCivilEmail() {
        return civilEmail;
    }

    public void setCivilTel(String civilTel) {
        this.civilTel = civilTel;
    }

    public String getCivilTel() {
        return civilTel;
    }

    public void setCivilMobile(String civilMobile) {
        this.civilMobile = civilMobile;
    }

    public String getCivilMobile() {
        return civilMobile;
    }

    public void setCivilFax(String civilFax) {
        this.civilFax = civilFax;
    }

    public String getCivilFax() {
        return civilFax;
    }

    public void setEnableSave(Boolean enableSave) {
        this.enableSave = enableSave;
    }

    public Boolean getEnableSave() {
        return enableSave;
    }

    public void setBankBranchDTO(BankBranchesDTO bankBranchDTO) {
        this.bankBranchDTO = bankBranchDTO;
    }

    public BankBranchesDTO getBankBranchDTO() {
        return bankBranchDTO;
    }

    public void setBankContactPersonsList(List<IBasicDTO> bankContactPersonsList) {
        this.bankContactPersonsList = bankContactPersonsList;
    }

    public List<IBasicDTO> getBankContactPersonsList() {
        return bankContactPersonsList;
    }

    public void setCivilList(List<Long> civilList) {
        this.civilList = civilList;
    }

    public List<Long> getCivilList() {
        return civilList;
    }

    public void setCivilExist(Boolean civilExist) {
        this.civilExist = civilExist;
    }

    public Boolean getCivilExist() {
        return civilExist;
    }

    public void setCivilName(String civilName) {
        this.civilName = civilName;
    }

    public String getCivilName() {
        return civilName;
    }

    public void setCivilGender(String civilGender) {
        this.civilGender = civilGender;
    }

    public String getCivilGender() {
        return civilGender;
    }

    public void setPageMode(int pageMode) {
        this.pageMode = pageMode;
    }

    public int getPageMode() {
        return pageMode;
    }

    public void setDivMode(int divMode) {
        this.divMode = divMode;
    }

    public int getDivMode() {
        return divMode;
    }

    public void setRemovedBankContactPersonsList(List<IBasicDTO> removedBankContactPersonsList) {
        this.removedBankContactPersonsList = removedBankContactPersonsList;
    }

    public List<IBasicDTO> getRemovedBankContactPersonsList() {
        return removedBankContactPersonsList;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }


    public String getPageTitle() {
        try {
            if (pageMode == 1) {
                return "branches_edit_title";
            } else {
                return "branches_add_title";
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        return null;
    }

    public void setCoordinatorExistBefore(boolean coordinatorExistBefore) {
        this.coordinatorExistBefore = coordinatorExistBefore;
    }

    public boolean isCoordinatorExistBefore() {
        return coordinatorExistBefore;
    }


    public void setBnkBrnchNameMsg(String bnkBrnchNameMsg) {
        this.bnkBrnchNameMsg = bnkBrnchNameMsg;
    }

    public String getBnkBrnchNameMsg() {
        return bnkBrnchNameMsg;
    }
}
