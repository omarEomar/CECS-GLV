package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.gn.map.business.client.IDataClient;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.hr.sal.business.shared.IMerConstant;
import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.IPersonsInformationCMTClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dao.KwMapDAO;
import com.beshara.csc.inf.business.dao.KwtCitizensResidentsDAO;
import com.beshara.csc.inf.business.dao.PersonQualificationsDAO;
import com.beshara.csc.inf.business.dao.PersonsInformationDAO;
import com.beshara.csc.inf.business.dto.GenderTypesDTO;
import com.beshara.csc.inf.business.dto.HandicapStatusDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGenderTypesDTO;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.dto.IInfDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsSearchDTO;
import com.beshara.csc.inf.business.dto.IMaritalStatusDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.IReligionsDTO;
import com.beshara.csc.inf.business.dto.IRequestDTO;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.IWifeSonInfoDTO;
import com.beshara.csc.inf.business.dto.IWifeSonParametersDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.MaritalStatusDTO;
import com.beshara.csc.inf.business.dto.ReligionsDTO;
import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.HandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IGenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IMaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.entity.ReligionsEntityKey;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.inf.paciwsproxy.WSProxy;
import com.beshara.csc.inf.paciwsproxy.types.AN100SWifeSonInfo;
import com.beshara.csc.inf.paciwsproxy.types.AN101SGetPersonInfo;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemFailureException;
import com.beshara.csc.sharedutils.business.exception.fil.MaxNoOfRecordsRequiredException;
import com.beshara.csc.sharedutils.business.exception.fil.PageNumRequiredException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "KwtCitizensResidentsSession", mappedName = "Inf-Model-KwtCitizensResidentsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(KwtCitizensResidentsSession.class)
public class KwtCitizensResidentsSessionBean extends InfBaseSessionBean implements KwtCitizensResidentsSession {


    /**
     * JobsSession Default Constructor */
    public KwtCitizensResidentsSessionBean() {
        super();

    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return KwtCitizensResidentsEntity.class;
    }

    @Override
    protected KwtCitizensResidentsDAO DAO() {
        return (KwtCitizensResidentsDAO)super.DAO();
    }

    /**
     * get kwtCitizensResidents Data only in table (without list relations).
     * @param id
     * @return kwtCitizensResidentsDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @throws DataBaseException
     * @auther KareemSayed
     */
    public IBasicDTO getKwtCitizensResidents(IRequestInfoDTO ri, IEntityKey id) throws DataBaseException,
                                                                                       SharedApplicationException,
                                                                                       DataBaseException {
        return DAO().getKwtCitizensResidents(id);
    }

    public IBasicDTO getCitizenInformation(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                    SharedApplicationException {
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)DAO().getCitizenInformation(civilId);
        PersonQualificationsDAO personQualificationsDAO =
            (PersonQualificationsDAO)(super.newDAOInstance(PersonQualificationsEntity.class));
        PersonsInformationDAO personInformationDAO =
            (PersonsInformationDAO)(super.newDAOInstance(PersonsInformationEntity.class));

        KwMapDAO kwMapDAO = (KwMapDAO)(super.newDAOInstance(KwMapEntity.class));

        try {

            System.out.println(" Start Add Address Data");
            /// new change request added By M.abdelsabour
            List<Vector> result = null;
            if (kwtCitizensResidentsDTO.getKwMapDTO() != null &&
                kwtCitizensResidentsDTO.getKwMapDTO().getCode() != null) {
                try {
                    result =
                            kwMapDAO.getAddress(Long.valueOf(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey()));
                } catch (Exception e) {
                    e.printStackTrace();
                    result = null;
                }
                if (result != null && result.size() > 0) {

                    for (Vector obj : result) {
                        if (obj.elementAt(0) != null) {
                            kwtCitizensResidentsDTO.setGovernrate(obj.elementAt(0).toString());
                        }
                        if (obj.elementAt(1) != null) {
                            kwtCitizensResidentsDTO.setArea(obj.elementAt(1).toString());
                        }
                        if (obj.elementAt(2) != null) {
                            kwtCitizensResidentsDTO.setPartNo(obj.elementAt(2).toString());
                        }

                    }
                }
            }
            System.out.println(" END Add Address Data");

            IPersonQualificationsDTO personQualificationsDTO =
                (IPersonQualificationsDTO)personQualificationsDAO.getCentralEmpPersonQul(civilId);
            List<IPersonQualificationsDTO> personQulList = new ArrayList<IPersonQualificationsDTO>();
            personQulList.add(personQualificationsDTO);
            kwtCitizensResidentsDTO.setPersonQualificationsDTOList(personQulList);

            List<IPersonsInformationDTO> personInformationList = (List)personInformationDAO.getAll(civilId);
            if (personInformationList != null) {
                kwtCitizensResidentsDTO.setPersonsInformationDTOList(personInformationList);
            }

        } catch (ItemNotFoundException e) {
            return kwtCitizensResidentsDTO;
        }
        return kwtCitizensResidentsDTO;
    }

    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {
        return DAO().getAll();
    }

    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    public IBasicDTO getCitizenName(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                             SharedApplicationException {
        return DAO().getCitizenName(civilId);
    }

    /**
     * check if the civilId exist * @param civilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public void checkCivilIdExist(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {
        DAO().checkCivilIdExist(civilId);
    }

    /**
     * get person information , and it's last qualification if exist * @param civilKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getCitizenInfoForEmp(IRequestInfoDTO ri, IEntityKey civilKey) throws DataBaseException,
                                                                                          SharedApplicationException {
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            (IKwtCitizensResidentsDTO)DAO().getCitizenInformation(((IKwtCitizensResidentsEntityKey)civilKey).getCivilId());
        PersonQualificationsDAO personQualificationsDAO =
            (PersonQualificationsDAO)(super.newDAOInstance(PersonQualificationsEntity.class));
        List<IPersonQualificationsDTO> personQulList = null;
        try {
            IPersonQualificationsDTO personQualificationsDTO =
                (IPersonQualificationsDTO)personQualificationsDAO.getLastPersonQualification(civilKey);
            personQulList = new ArrayList<IPersonQualificationsDTO>();
            personQulList.add(personQualificationsDTO);
        } catch (ItemNotFoundException e) {
            ;
        }
        kwtCitizensResidentsDTO.setPersonQualificationsDTOList(personQulList);
        return kwtCitizensResidentsDTO;
    }

    /**
     * search citizens by their civilid or name or both *
     * @param kwtCitizensResidentsSearchDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */


    public List<IBasicDTO> searchAboutCitizens(IRequestInfoDTO ri,
                                               IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().searchAboutCitizens(kwtCitizensResidentsSearchDTO);
    }

    public List<IBasicDTO> searchAboutCitizensNotEmployees(IRequestInfoDTO ri,
                                                           IBasicDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                                           SharedApplicationException {
        return DAO().searchAboutCitizensNotEmployees(kwtCitizensResidentsSearchDTO);
    }

    public IBasicDTO getCitizenInfoByCallingProcedure(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException {

        IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();
        IKwtCitizensResidentsEntityKey kwtCitizensResidentsEntityKey =
            InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId);
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = null;
        boolean citizenInLocal = false;
        try {
            kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)DAO().getCitizenInformation(civilId);
            citizenInLocal = true;
        } catch (Exception e) {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.getByIdInCenter(kwtCitizensResidentsEntityKey);
        } // if ( !citizenInLocal ) {
        // kwtCitizensResidentsDTO = ( IKwtCitizensResidentsDTO ) kwtCitizensResidentsClient.addInLocal ( kwtCitizensResidentsDTO ) ;
        // }
        return kwtCitizensResidentsDTO;
    }

    public List<IBasicDTO> searchForWife(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        List<IKwtCitizensResidentsDTO> kwtCitizensResidentsDTOList = null;
        try {
            kwtCitizensResidentsDTOList = (List)DAO().search(basicDTO);
        } catch (ItemNotFoundException e) {

            IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();

            kwtCitizensResidentsDTOList =
                    //   (List<IKwtCitizensResidentsDTO>)kwtCitizensResidentsClient.searchInCenter(basicDTO);
                    (List)kwtCitizensResidentsClient.searchInCenter(basicDTO);

            if (kwtCitizensResidentsDTOList != null && !kwtCitizensResidentsDTOList.isEmpty()) {
                for (IKwtCitizensResidentsDTO citizen : kwtCitizensResidentsDTOList) {
                    kwtCitizensResidentsClient.addInLocal(citizen);
                }
            }

        }

        //return (List<IBasicDTO>)kwtCitizensResidentsDTOList;
        return (List)kwtCitizensResidentsDTOList;
    }


    //
    //New Methods With RequestInfo
    //


    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */


    /**
     * check if the civilId exist * @param civilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     */


    /**
     * get person information , and it's last qualification if exist * @param civilKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */


    /**
     * search citizens by their civilid or name or both
     * @param kwtCitizensResidentsSearchDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IResponseDTO searchCitizens(IRequestInfoDTO ri,
                                       IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            List<IBasicDTO> result = null;
            IResponseDTO responseDTO = InfDTOFactory.createResponseDTO();
            IRequestDTO requestDTO = null;
            IKwtCitizensResidentsSearchDTO searchDTO = (IKwtCitizensResidentsSearchDTO)kwtCitizensResidentsSearchDTO;
            if (kwtCitizensResidentsSearchDTO != null) {
                requestDTO = (IRequestDTO)searchDTO.getRequestDTO();
                if (requestDTO != null) {
                    Long pageNum = requestDTO.getPageNum();
                    Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                    if (pageNum != null) {
                        if (maxNoOfRecords != null) {
                            Long firstRow = (pageNum - 1) * maxNoOfRecords;
                            requestDTO.setFirstRowNumber(firstRow);
                        } else {
                            throw new MaxNoOfRecordsRequiredException();
                        }
                    } else {
                        throw new PageNumRequiredException();
                    }
                    if (requestDTO.isCountRequired()) {
                        Long count = DAO().getSearchCitizensCount(kwtCitizensResidentsSearchDTO);
                        responseDTO.setCount(count);
                    }
                    try {
                        result = DAO().searchCitizens(kwtCitizensResidentsSearchDTO);
                    } catch (ItemNotFoundException e) {
                        throw new NoResultException();
                    }
                    responseDTO.setBasicDTOList(result);
                    responseDTO.setRequestDTO(requestDTO);
                    return responseDTO;
                }
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return null;
    }


    public IPagingResponseDTO filterAvailableInfUsingPaging(IRequestInfoDTO ri, IBasicDTO employeeSearchDTO1,
                                                            IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().filterAvailableInfCountUsingPaging(employeeSearchDTO1));
            }

            List<IBasicDTO> result = null;
            try {
                result = DAO().filterAvailableInfUsingPaging(employeeSearchDTO1, requestDTO);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new SharedApplicationException();
            }

            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;
    }


    public IBasicDTO getCitizenCodeName(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                 SharedApplicationException {
        return DAO().getCitizenCodeName(civilId);
    }


    public List<IBasicDTO> searchCandIllegal(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().searchCandIllegal(basicDTO);

    }


    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();

    }

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object object) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().searchByCode(object);
    }

    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByName(name);

    }

    public IWifeSonInfoDTO getWifeSonInfo(IRequestInfoDTO ri,
                                          IWifeSonParametersDTO wifeSonParametersDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        IWifeSonInfoDTO wifeSonInfoDTO = InfDTOFactory.createWifeSonInfoDTO();
        IGenderCountryDTO genderCountryDTO;
        wifeSonInfoDTO.setGenderTypesDTO(InfDTOFactory.createGenderTypesDTO());

        try {

            String empCivilId = wifeSonParametersDTO.getEmpCivilId().toString();
            String brancheCivilId = wifeSonParametersDTO.getBarncheCivilId().toString();
            String relationType = wifeSonParametersDTO.getRelationType().toString();
            //convert Date marriageDate to String
            Date marriageDate = wifeSonParametersDTO.getMarriageDate();
            String marriageDateString = "0";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            if (marriageDate != null) {
                marriageDateString = sdf.format(marriageDate);
            }
            //get instance from WSProxy mapped into factory.xml
            WSProxy wsproxy = WSProxy.getInstance();
            AN100SWifeSonInfo an100WifeSonInfo =
                wsproxy.getWifeSonInfo(empCivilId, brancheCivilId, relationType, marriageDateString);


            if (an100WifeSonInfo != null) {
                wifeSonInfoDTO.setEmpCivilId(wifeSonParametersDTO.getEmpCivilId());
                wifeSonInfoDTO.setBrancheCivilId(wifeSonParametersDTO.getBarncheCivilId());
                wifeSonInfoDTO.setRelationType(an100WifeSonInfo.getWVRELATIONTYPE());
                wifeSonInfoDTO.setMarriageDate(marriageDate);
                wifeSonInfoDTO.setBrancheName(an100WifeSonInfo.getWVBRANCHNAME());
                wifeSonInfoDTO.setReplyMsgCode(an100WifeSonInfo.getWVRC());
                wifeSonInfoDTO.setRequestTime(an100WifeSonInfo.getWVEnquiryTime());
                wifeSonInfoDTO.setReplyMsg(an100WifeSonInfo.getWVMsg());
                wifeSonInfoDTO.setResponseTime(an100WifeSonInfo.getWVResponseTime());
                wifeSonInfoDTO.setVersionNumber(an100WifeSonInfo.getWVVerNo());
                wifeSonInfoDTO.setInfoMsg(an100WifeSonInfo.getWVInfo());

                //get BirthDate
                java.util.Date birthDate = sdf.parse(an100WifeSonInfo.getWVBIRTHDATE().toString());
                Long birthDateLong = birthDate.getTime();
                wifeSonInfoDTO.setBirthDate(new Date(birthDateLong));

                if (an100WifeSonInfo.getWVSEX() != 0) {
                    if (an100WifeSonInfo.getWVSEX() == 1) {
                        wifeSonInfoDTO.getGenderTypesDTO().setGentypeCode(2L);
                    } else if (an100WifeSonInfo.getWVSEX() == 2) {
                        wifeSonInfoDTO.getGenderTypesDTO().setGentypeCode(1L);
                    } else {
                        wifeSonInfoDTO.getGenderTypesDTO().setGentypeCode(3L);
                    }
                }
                wifeSonInfoDTO.setDeathDate(new Date(an100WifeSonInfo.getWVDEATHDATE().longValue()));
                wifeSonInfoDTO.setEnvironmentDtls(an100WifeSonInfo.getWVEnv());
                Long wvNatLong = new Long(an100WifeSonInfo.getWVNAT());
                if (an100WifeSonInfo.getWVNAT() != 0) {
                    IDataClient dataClient = MapClientFactory.getDataClient();
                    List<IBasicDTO> list =
                        dataClient.ListMappedT0ByTypeAndSoc2Code(5L, 1L, 2L, wvNatLong.toString(), ISystemConstant.ACTIVE_FLAG.toString());
                    if (list != null && list.size() > 0) {
                        Long WvSex = wifeSonInfoDTO.getGenderTypesDTO().getGentypeCode();
                        Long strCode = Long.valueOf(((IMappedValueDTO)list.get(0)).getStrCode());
                        genderCountryDTO =
                                (IGenderCountryDTO)super.newDAOInstance(GenderCountryEntity.class).getById(InfEntityKeyFactory.createGenderCountryEntityKey(WvSex,
                                                                                                                                                            strCode));
                        wifeSonInfoDTO.setGenderCountryDTO(genderCountryDTO);
                    }
                }
                wifeSonInfoDTO.setNoOfRequestRemaining(Long.valueOf(an100WifeSonInfo.getWVHitsRemaining()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifeSonInfoDTO;
    }

    /**
     * @author Black Horse[m.sayed]
     * @since 10/11/2015
     * @param ri
     * @param realCivilID
     * @return IKwtCitizensResidentsDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IKwtCitizensResidentsDTO getPersonInfo(IRequestInfoDTO ri, Long realCivilID) throws DataBaseException,
                                                                                               SharedApplicationException {
        IKwtCitizensResidentsDTO personInfoDTO = null;
        Boolean isUpdate = Boolean.TRUE;

        try {


            //get instance from WSProxy mapped into factory.xml
            WSProxy wsproxy = WSProxy.getInstance();
            AN101SGetPersonInfo an100PersonInfo = wsproxy.getPersonInfo(realCivilID.toString());
            if (an100PersonInfo != null) {

                System.out.println("an100PersonInfo getPVBIRTHDATE = " + an100PersonInfo.getPVBIRTHDATE());
                System.out.println("an100PersonInfo getPVCARDVALIDATE = " + an100PersonInfo.getPVCARDVALIDATE());
                System.out.println("an100PersonInfo getPVCIVNO = " + an100PersonInfo.getPVCIVNO());
                System.out.println("an100PersonInfo getPVCIVNOBARCODE = " + an100PersonInfo.getPVCIVNOBARCODE());
                System.out.println("an100PersonInfo getPVNAME = " + an100PersonInfo.getPVNAME());
                System.out.println("an100PersonInfo getPVNATIONALITY = " + an100PersonInfo.getPVNATIONALITY());
                System.out.println("an100PersonInfo getPVRC = " + an100PersonInfo.getPVRC());
                System.out.println("an100PersonInfo getPVSEX = " + an100PersonInfo.getPVSEX());
                System.out.println("an100PersonInfo getWVEnquiryTime = " + an100PersonInfo.getWVEnquiryTime());
                System.out.println("an100PersonInfo getWVEnv = " + an100PersonInfo.getWVEnv());
                System.out.println("an100PersonInfo getWVInfo = " + an100PersonInfo.getWVInfo());
                System.out.println("an100PersonInfo getWVMsg = " + an100PersonInfo.getWVMsg());
                System.out.println("an100PersonInfo getWVResponseTime = " + an100PersonInfo.getWVResponseTime());
                System.out.println("an100PersonInfo getWVVerNo = " + an100PersonInfo.getWVVerNo());


                IEntityKey key = new KwtCitizensResidentsEntityKey(realCivilID);
                try {
                    personInfoDTO = (IKwtCitizensResidentsDTO)getById(ri, key);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("civil found in IS and not found in DB");
                }
                if (personInfoDTO == null) {
                    personInfoDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
                    personInfoDTO.setCode(key);
                    isUpdate = Boolean.FALSE;
                }
                //set CivilID for kwt Citizen DTO
                personInfoDTO.setCivilId(realCivilID);
                //getting first name and second and ....
                String[] civilName = an100PersonInfo.getPVNAME().split("\\s+");
                if (civilName == null || civilName[0].toString().equals("0")) {
                    return null;
                }
                if (civilName.length > 0 && civilName[0] != null) {
                    personInfoDTO.setFirstName(civilName[0].toString());
                }
                if (civilName.length > 1 && civilName[1] != null) {
                    personInfoDTO.setSecondName(civilName[1].toString());
                } else {
                    personInfoDTO.setSecondName(".");
                }

                if (civilName.length > 2 && civilName[2] != null) {
                    personInfoDTO.setThirdName(civilName[2].toString());
                } else {
                    personInfoDTO.setThirdName(".");
                }

                if (civilName.length > 3 && civilName[3] != null) {
                    personInfoDTO.setLastName(civilName[3].toString());
                } else {
                    personInfoDTO.setLastName(".");
                }
                if (civilName.length > 4 && civilName[4] != null) {
                    personInfoDTO.setFamilyName(civilName[4].toString());
                }
                //marital Status
                //cbeck if there is data for marital or not
                if (personInfoDTO.getMaritalStatusDTO() == null) {
                    IMaritalStatusDTO marDTO = new MaritalStatusDTO();
                    IMaritalStatusEntityKey marKey = new MaritalStatusEntityKey(5L);
                    marDTO.setCode(marKey);
                    personInfoDTO.setMaritalStatusDTO(marDTO);
                }

                //Religion
                //cbeck if there is data for religion or not
                if (personInfoDTO.getReligionsDTO() == null) {
                    IReligionsDTO religionsDTO = new ReligionsDTO();
                    IReligionsEntityKey regKey = new ReligionsEntityKey(0L);
                    religionsDTO.setCode(regKey);
                    personInfoDTO.setReligionsDTO(religionsDTO);
                    personInfoDTO.setReligionCode(0L);
                }
                //CAPSTATUS
                if (personInfoDTO.getHandicapStatusDTO() == null) {
                    IHandicapStatusDTO handicapStatusDTO = new HandicapStatusDTO();
                    IHandicapStatusEntityKey capStatusKey = new HandicapStatusEntityKey(11L);
                    handicapStatusDTO.setCode(capStatusKey);
                    personInfoDTO.setHandicapStatusDTO(handicapStatusDTO);
                    personInfoDTO.setCapstatusCode(11L);
                }

                //Gender Type
                if (an100PersonInfo.getPVSEX() != 0) {
                    String genType = null;
                    IGenderTypesEntityKey genKey;
                    IGenderTypesDTO genDTO = new GenderTypesDTO();
                    genType =
                            DAO().getMapedValueFromWS(String.valueOf(an100PersonInfo.getPVSEX()), IInfConstant.WEB_SERVICES_PARAM_GENDER_TYPE);
                    genKey = new GenderTypesEntityKey(Long.valueOf(genType));
                    genDTO.setCode(genKey);
                    personInfoDTO.setGenderTypesDTO(genDTO);
                    personInfoDTO.setGentypeCode(Long.getLong(personInfoDTO.getGenderTypesDTO().getCode().getKey()));
                }


                //Nationality
                if (an100PersonInfo.getPVNATIONALITY() > 0) {
                    String nationality = null;
                    nationality =
                            DAO().getMapedValueFromWS(String.valueOf(an100PersonInfo.getPVNATIONALITY()), IInfConstant.WEB_SERVICES_PARAM_NATIONALITY_TYPE);
                    personInfoDTO.setNationality(Long.valueOf(nationality));
                }

                //get BirthDate
                if (an100PersonInfo.getPVBIRTHDATE() != null && an100PersonInfo.getPVBIRTHDATE().intValue() > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    java.util.Date birthDate = sdf.parse(an100PersonInfo.getPVBIRTHDATE().toString());
                    java.sql.Timestamp sq = new java.sql.Timestamp(birthDate.getTime());
                    personInfoDTO.setBirthDate(sq);
                }
                if (isUpdate) {
                    personInfoDTO.setStatusFlag(ISystemConstant.ADDED_LAST_ITEM);
                } else {
                    personInfoDTO.setStatusFlag(ISystemConstant.ADDED_ITEM);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personInfoDTO;
    }

    public Boolean updateWifeSon(IRequestInfoDTO ri, IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().updateWifeSon(kwtCitizensResidentsDTO);
    }

    public IBasicDTO getKwtCitizensResidentQuls(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().getKwtCitizensResidentQuls(civilId);
    }

    public Boolean updateKwtCitizenInfo_WS(IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                            SharedApplicationException {

            return DAO().updateKwtCitizenInfo_WS(kwtCitizensResidentsDTO1);
        }
    public IBasicDTO updateDTO(IRequestInfoDTO ri, IBasicDTO kwtCitizensResidentsDTO,
                               IBasicDTO kwtCitizensResidentsWSDTO, List qual) throws DataBaseException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException {
        if (kwtCitizensResidentsWSDTO != null) {
            IKwtCitizensResidentsDTO WSDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsWSDTO;
            //WSDTO returned from webServices
            if (WSDTO.getStatusFlag() != null && WSDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                DAO().add(WSDTO);
            } else {
                DAO().update(WSDTO);
            }
        }

        IBasicDTO savedDTO = DAO().updateDTO(kwtCitizensResidentsDTO);
        List<IPersonsInformationDTO> qualDto = qual;
        if (qualDto != null) {
            IPersonsInformationCMTClient personsInformationClient = InfClientFactory.getPersonsInformationCMTClient();
            for (IPersonsInformationDTO dto : qualDto) {
                personsInformationClient.addCMT(dto);

            }

        }
        return savedDTO;
    }

    public IPagingResponseDTO searchWithPaging(IRequestInfoDTO ri,
                                               IBasicDTO basicDTO) throws SharedApplicationException,
                                                                          DataBaseException, DataBaseException {

        IKwtCitizensResidentsSearchDTO searchDTO = (IKwtCitizensResidentsSearchDTO)basicDTO;
        IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getCountSearchWithPaging(searchDTO));
            }

            List<IBasicDTO> result = null;
            try {
                result = DAO().searchWithPaging(searchDTO);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new SharedApplicationException();
            }

            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }


    public Boolean updatekwtCitizensResidentStatus(IRequestInfoDTO ri,
                                                   IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().updatekwtCitizensResidentStatus(kwtCitizensResidentsDTO);
    }

    public Boolean updatekwtCitizensResidentMaritalStatus(IRequestInfoDTO ri,
                                                          IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                                     SharedApplicationException {
        return DAO().updatekwtCitizensResidentMaritalStatus(kwtCitizensResidentsDTO1);
    }

    /**
     * Get KwtCitizensResidents By Primary Key * @param id
     * Mainly Used in Module GRS - Exceptions
     * @return KwtCitizensResidentsDTO
     */
    public IBasicDTO getKwtCitizensResidentsById(IRequestInfoDTO ri, IEntityKey id) throws DataBaseException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getKwtCitizensResidentsById(id);
    }

    public IBasicDTO getSimpleKwtCitizensResidentsById(IRequestInfoDTO ri, IEntityKey id) throws DataBaseException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException {
        return DAO().getSimpleKwtCitizensResidentsById(id);
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                 SharedApplicationException {

        return DAO().search(basicDTO);

    }

    /**
     * Simple Search in KwtCitizensResidents with Paging
     * @param IKwtCitizensResidentsSearchDTO
     * @return IPagingResponseDTO
     * @author Amr Abdel Halim
     * @since 29-AUG-2016
     * */

    public IPagingResponseDTO simpleSearchWithPaging(IRequestInfoDTO ri,
                                                     IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException, DataBaseException {

        IKwtCitizensResidentsSearchDTO searchDTO = (IKwtCitizensResidentsSearchDTO)basicDTO;
        IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
        IPagingResponseDTO responseDTO = null;
        if (requestDTO != null) {
            Long pageNum = requestDTO.getPageNum();
            Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
            if (pageNum != null) {
                if (maxNoOfRecords != null) {
                    requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                } else {
                    throw new MaxNoOfRecordsRequiredException();
                }
            } else {
                throw new PageNumRequiredException();
            }

            responseDTO = new PagingResponseDTO();

            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getCountSimpleSearchWithPaging(searchDTO));
            }

            List<IBasicDTO> result = null;
            try {
                result = DAO().simpleSearchWithPaging(searchDTO);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new SharedApplicationException();
            }

            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;

    }


    public void updateWifeInfo(IRequestInfoDTO ri, IWifeSonInfoDTO wifeSonInfoDTO) throws DataBaseException,
                                                                                          SharedApplicationException {

        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        Long wifeCivil = null;
        boolean wifeExist = true;

        //        check if wife exist in KwCiznEntity
        try {
            wifeCivil = wifeSonInfoDTO.getBrancheCivilId();
            DAO().checkCivilIdExist(wifeCivil);
            IKwtCitizensResidentsEntityKey key = InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(wifeCivil);
            kwtCitizensResidentsDTO.setCode(key);
        } catch (Exception e) {
            wifeExist = false;
            e.printStackTrace();
        }
        try {
            //            beginTransaction();
            System.out.println("SalEmpWifesSessionBean add wifeCivil = " + wifeSonInfoDTO.getBrancheCivilId() +
                               " , \n wifeSonInfoDTO.getBrancheName() = " +
                               ((wifeSonInfoDTO.getBrancheName() == null) ? "null" : wifeSonInfoDTO.getBrancheName()));

            String[] names = wifeSonInfoDTO.getBrancheName().split(" ");
            int j = 0;
            for (int i = 0; i < names.length; i++) {
                System.out.println("names[" + i + "] = " + names[i]);
                if (names[i] == null || names[i].isEmpty())
                    continue; //kwtCitizensResidentsDTO.setFirstName("");
                switch (j) {
                case 0:
                    kwtCitizensResidentsDTO.setFirstName(names[i]);
                    j++;
                    break;
                case 1:
                    kwtCitizensResidentsDTO.setSecondName(names[i]);
                    j++;
                    break;
                case 2:
                    kwtCitizensResidentsDTO.setThirdName(names[i]);
                    j++;
                    break;
                case 3:
                    kwtCitizensResidentsDTO.setLastName(names[i]);
                    j++;
                    break;
                case 4:
                    kwtCitizensResidentsDTO.setFamilyName(names[i]);
                    j++;
                }
            }
            //            if(j < 3)throw new SharedApplicationException("name_error");
            if (j == 1) {
                kwtCitizensResidentsDTO.setSecondName(".");
                kwtCitizensResidentsDTO.setThirdName(".");
                kwtCitizensResidentsDTO.setLastName(".");
            }
            if (j == 2) {
                kwtCitizensResidentsDTO.setThirdName(".");
                kwtCitizensResidentsDTO.setLastName(".");
            }
            if (j == 3)
                kwtCitizensResidentsDTO.setLastName(".");
            kwtCitizensResidentsDTO.setName(wifeSonInfoDTO.getBrancheName());
            kwtCitizensResidentsDTO.setFullNameColumn(wifeSonInfoDTO.getBrancheName());
            kwtCitizensResidentsDTO.setBirthDate(new Timestamp(wifeSonInfoDTO.getBirthDate().getTime()));
            IGenderTypesDTO genderTypesDTO = InfDTOFactory.createGenderTypesDTO();
            genderTypesDTO.setGentypeCode(wifeSonInfoDTO.getGenderTypesDTO().getGentypeCode());

            kwtCitizensResidentsDTO.setGenderTypesDTO(genderTypesDTO);
            IMaritalStatusDTO maritalStatusDTO = InfDTOFactory.createMaritalStatusDTO();
            maritalStatusDTO.setMarstatusCode(IMerConstant.M_STATUS_MARRIED);
            kwtCitizensResidentsDTO.setMaritalStatusDTO(maritalStatusDTO);
            Long cntryCode = ((IGenderCountryEntityKey)wifeSonInfoDTO.getGenderCountryDTO().getCode()).getCntryCode();
            Long gentypeCode =
                ((IGenderCountryEntityKey)wifeSonInfoDTO.getGenderCountryDTO().getCode()).getGentypeCode();
            kwtCitizensResidentsDTO.setNationality(cntryCode);

            kwtCitizensResidentsDTO.setDeathDate(new Timestamp(wifeSonInfoDTO.getDeathDate().getTime()));
            kwtCitizensResidentsDTO.setActiveFlag(IMerConstant.ACTIVE);
            //set genderTypeDTO
            kwtCitizensResidentsDTO.setCivilId(wifeCivil);
            genderTypesDTO.setCode(InfEntityKeyFactory.createGenderTypesEntityKey(gentypeCode));
            //set genderCountryDTO
            IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO();
            genderCountryDTO.setCode((InfEntityKeyFactory.createGenderCountryEntityKey(gentypeCode, cntryCode)));
            kwtCitizensResidentsDTO.setCountriesDTO(genderCountryDTO);
            //set MAritalStatusDTO
            maritalStatusDTO.setCode(InfEntityKeyFactory.createMaritalStatusEntityKey(IMerConstant.M_STATUS_MARRIED));
            kwtCitizensResidentsDTO.setMaritalStatusDTO(maritalStatusDTO);
            if (wifeExist) {
                //update
                Long capStatusCode = kwtCitizensResidentsDTO.getCapstatusCode();
                if (capStatusCode == null || capStatusCode.equals(IMerConstant.CAP_STATUS_HEALTHY)) {
                    capStatusCode = IMerConstant.HEALTH_CHILDREN_DEGREE;
                    kwtCitizensResidentsDTO.setCapstatusCode(capStatusCode);
                    IHandicapStatusDTO handicapStatusDTO = InfDTOFactory.createHandicapStatusDTO();
                    handicapStatusDTO.setCode(InfEntityKeyFactory.createHandicapStatusEntityKey(IMerConstant.HEALTH_CHILDREN_DEGREE));
                    kwtCitizensResidentsDTO.setHandicapStatusDTO(handicapStatusDTO);
                }
                DAO().updateWifeSon(kwtCitizensResidentsDTO);
            } else {
                /******* A.Nour we need to insert Religion & handicapStatus in Add new kwtCitizensResident for wife only
                 * and handicapStatus is 11 = HEALTH_CHILDREN_DEGREE********/
                kwtCitizensResidentsDTO.setReligionCode(IMerConstant.R_STATUS_MUSLIM);
                kwtCitizensResidentsDTO.setCapstatusCode(IMerConstant.HEALTH_CHILDREN_DEGREE);
                //set ReligionCode
                IReligionsDTO religionsDTO = InfDTOFactory.createReligionsDTO();
                religionsDTO.setCode(InfEntityKeyFactory.createReligionsEntityKey(IMerConstant.R_STATUS_MUSLIM));
                kwtCitizensResidentsDTO.setReligionsDTO(religionsDTO);
                //set HandicapStatusDTO
                IHandicapStatusDTO handicapStatusDTO = InfDTOFactory.createHandicapStatusDTO();
                handicapStatusDTO.setCode(InfEntityKeyFactory.createHandicapStatusEntityKey(IMerConstant.HEALTH_CHILDREN_DEGREE));
                kwtCitizensResidentsDTO.setHandicapStatusDTO(handicapStatusDTO);
                DAO().add(kwtCitizensResidentsDTO);
            }


        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw new SystemFailureException();
        } catch (TransactionException e) {
            e.printStackTrace();
            throw new DataBaseException(SharedUtils.getExceptionMessage(e));
        }
    }

    public void updateChildernInfo(IRequestInfoDTO ri,
                                   IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                            SharedApplicationException {


        boolean sonExists = true;
        try {
            DAO().checkCivilIdExist(Long.valueOf(kwtCitizensResidentsDTO.getCode().getKey()));
        } catch (Exception dbe) {
            dbe.printStackTrace();
            sonExists = false;
        }

        if (!sonExists) { // add new record
            try {
                DAO().add(kwtCitizensResidentsDTO);
                System.out.println("Returned from INF");
            } catch (DataBaseException dbe) {
                dbe.printStackTrace();
                throw dbe;
            } catch (SharedApplicationException sae) {
                sae.printStackTrace();
                throw sae;
            }
        } else {
            try {
                //                    KwtCitizensResidentsDTO kwtCitizensResidentsPACIDataDTO = (KwtCitizensResidentsDTO)kwtCitizensResidentsDTO;
                //
                //                    kwtCitizensResidentsDTO.setCapstatusCode(kwtCitizensResidentsPACIDataDTO.getCapstatusCode());
                //                    kwtCitizensResidentsDTO.setHandicapStatusDTO(kwtCitizensResidentsPACIDataDTO.getHandicapStatusDTO());
                //
                //                    kwtCitizensResidentsDTO.setBirthDate(kwtCitizensResidentsPACIDataDTO.getBirthDate());
                //
                //                    kwtCitizensResidentsDTO.setGenderTypesDTO(kwtCitizensResidentsPACIDataDTO.getGenderTypesDTO());
                //                    kwtCitizensResidentsDTO.setGentypeCode(kwtCitizensResidentsPACIDataDTO.getGentypeCode());
                //                    kwtCitizensResidentsDTO.setNationality(kwtCitizensResidentsPACIDataDTO.getNationality());
                //                    kwtCitizensResidentsDTO.setFullNameColumn(kwtCitizensResidentsPACIDataDTO.getFullNameColumn());
                //                    kwtCitizensResidentsDTO.setFirstName(kwtCitizensResidentsPACIDataDTO.getFirstName());
                //                    kwtCitizensResidentsDTO.setSecondName(kwtCitizensResidentsPACIDataDTO.getSecondName());
                //                    kwtCitizensResidentsDTO.setThirdName(kwtCitizensResidentsPACIDataDTO.getThirdName());
                //                    kwtCitizensResidentsDTO.setLastName(kwtCitizensResidentsPACIDataDTO.getLastName());
                //                    kwtCitizensResidentsDTO.setFamilyName(kwtCitizensResidentsPACIDataDTO.getFamilyName());
                //
                //                    if (kwtCitizensResidentsDTO.getReligionCode() == null)
                //                        kwtCitizensResidentsDTO.setReligionCode(kwtCitizensResidentsPACIDataDTO.getReligionCode());
                //
                //                    if (kwtCitizensResidentsDTO.getReligionsDTO() == null)
                //                        kwtCitizensResidentsDTO.setReligionsDTO(kwtCitizensResidentsPACIDataDTO.getReligionsDTO());
                //
                //                    if (kwtCitizensResidentsDTO.getMaritalStatusDTO() == null)
                //                        kwtCitizensResidentsDTO.setMaritalStatusDTO(kwtCitizensResidentsPACIDataDTO.getMaritalStatusDTO());
                //
                //                    if (kwtCitizensResidentsDTO.getActiveFlag() == null)
                //                        kwtCitizensResidentsDTO.setActiveFlag(kwtCitizensResidentsPACIDataDTO.getActiveFlag());

                DAO().updateWifeSon(kwtCitizensResidentsDTO);

            } catch (DataBaseException dbe) {
                dbe.printStackTrace();
            } catch (SharedApplicationException sae) {
                sae.printStackTrace();
            }
        }


    }
    
    
    
    public IKwtCitizensResidentsDTO getPersonInfo_WS(IRequestInfoDTO ri, Long realCivilID) throws DataBaseException, SharedApplicationException {
        
        IKwtCitizensResidentsDTO personInfoDTO = null;
        Boolean isUpdate = Boolean.TRUE;

        try {


            //get instance from WSProxy mapped into factory.xml
            WSProxy wsproxy = WSProxy.getInstance();
            AN101SGetPersonInfo an100PersonInfo = wsproxy.getPersonInfo(realCivilID.toString());
            if (an100PersonInfo != null) {
                IEntityKey key = new KwtCitizensResidentsEntityKey(realCivilID);
           
                personInfoDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
                personInfoDTO.setCode(key);
                    
                //set CivilID for kwt Citizen DTO
                personInfoDTO.setCivilId(realCivilID);
                //getting first name and second and ....
                String[] civilName = an100PersonInfo.getPVNAME().split("\\s+");
                if (civilName == null || civilName[0].toString().equals("0")) {
                    return null;
}
                if (civilName.length > 0 && civilName[0] != null) {
                    personInfoDTO.setFirstName(civilName[0].toString());
                }
                if (civilName.length > 1 && civilName[1] != null) {
                    personInfoDTO.setSecondName(civilName[1].toString());
                } 
                else {
                    personInfoDTO.setSecondName(".");
                }

                if (civilName.length > 2 && civilName[2] != null) {
                    personInfoDTO.setThirdName(civilName[2].toString());
                } else {
                    personInfoDTO.setThirdName(".");
                }

                if (civilName.length > 3 && civilName[3] != null) {
                    personInfoDTO.setLastName(civilName[3].toString());
                } else {
                    personInfoDTO.setLastName(".");
                }
                if (civilName.length > 4 && civilName[4] != null) {
                    personInfoDTO.setFamilyName(civilName[4].toString());
                }
                //marital Status
                //cbeck if there is data for marital or not
                if (personInfoDTO.getMaritalStatusDTO() == null) {
                    IMaritalStatusDTO marDTO = new MaritalStatusDTO();
                    IMaritalStatusEntityKey marKey = new MaritalStatusEntityKey(5L);
                    marDTO.setCode(marKey);
                    personInfoDTO.setMaritalStatusDTO(marDTO);
                }

                //Religion
                //cbeck if there is data for religion or not
                if (personInfoDTO.getReligionsDTO() == null) {
                    IReligionsDTO religionsDTO = new ReligionsDTO();
                    IReligionsEntityKey regKey = new ReligionsEntityKey(0L);
                    religionsDTO.setCode(regKey);
                    personInfoDTO.setReligionsDTO(religionsDTO);
                    personInfoDTO.setReligionCode(0L);
                }
                //CAPSTATUS
                if (personInfoDTO.getHandicapStatusDTO() == null) {
                    IHandicapStatusDTO handicapStatusDTO = new HandicapStatusDTO();
                    IHandicapStatusEntityKey capStatusKey = new HandicapStatusEntityKey(11L);
                    handicapStatusDTO.setCode(capStatusKey);
                    personInfoDTO.setHandicapStatusDTO(handicapStatusDTO);
                    personInfoDTO.setCapstatusCode(11L);
                }
    //                Long gendertype = null;
    //                Long countryCode = null;
                //Gender Type
                if (an100PersonInfo.getPVSEX() != 0) {
                    String genType = null;
                    IGenderTypesEntityKey genKey;
                    IGenderTypesDTO genDTO = new GenderTypesDTO();
                    genType =
                            DAO().getMapedValueFromWS(String.valueOf(an100PersonInfo.getPVSEX()), IInfConstant.WEB_SERVICES_PARAM_GENDER_TYPE);
                    genKey = new GenderTypesEntityKey(Long.valueOf(genType));
                    genDTO.setCode(genKey);
                    personInfoDTO.setGenderTypesDTO(genDTO);
                    personInfoDTO.setGentypeCode(Long.getLong(personInfoDTO.getGenderTypesDTO().getCode().getKey()));
                 //   gendertype = personInfoDTO.getGentypeCode();
                }


                //Nationality
                if (an100PersonInfo.getPVNATIONALITY() > 0) {
                    String nationality = null;
                    nationality =
                            DAO().getMapedValueFromWS(String.valueOf(an100PersonInfo.getPVNATIONALITY()), IInfConstant.WEB_SERVICES_PARAM_NATIONALITY_TYPE);
                    personInfoDTO.setNationality(Long.valueOf(nationality));
                   // countryCode = Long.valueOf(nationality);

                }

                //get BirthDate
                if (an100PersonInfo.getPVBIRTHDATE() != null && an100PersonInfo.getPVBIRTHDATE().intValue() > 0) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    java.util.Date birthDate = sdf.parse(an100PersonInfo.getPVBIRTHDATE().toString());
                    java.sql.Timestamp sq = new java.sql.Timestamp(birthDate.getTime());
                    personInfoDTO.setBirthDate(sq);
                }
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personInfoDTO;
    }
}
