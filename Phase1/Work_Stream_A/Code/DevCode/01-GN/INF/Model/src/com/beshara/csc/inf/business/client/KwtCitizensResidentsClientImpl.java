package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.inf.business.deploy.KwtCitizensResidentsSession;
import com.beshara.csc.inf.business.dto.IInfDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.IWifeSonInfoDTO;
import com.beshara.csc.inf.business.dto.IWifeSonParametersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.FinderException;


public class KwtCitizensResidentsClientImpl extends BaseClientImpl3 implements IKwtCitizensResidentsClient {

    public KwtCitizensResidentsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return KwtCitizensResidentsSession.class;
    }

    @Override
    protected KwtCitizensResidentsSession SESSION() {
        return (KwtCitizensResidentsSession)super.SESSION();
    }

    /**
     * get kwtCitizensResidents Data only in table (without list relations).
     * @param id
     * @return kwtCitizensResidentsDTO
     * @throws SharedApplicationException
     * @throws DataBaseException
     * @auther KareemSayed
     */
    public IBasicDTO getKwtCitizensResidents(IEntityKey id) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().getKwtCitizensResidents(RI(), id);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> searchCandIllegal(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().searchCandIllegal(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IBasicDTO getCitizenInformation(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCitizenInformation(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    public IBasicDTO getCitizenName(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCitizenName(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    /**
     * check if the civilId exist * @param civilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public void checkCivilIdExist(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            SESSION().checkCivilIdExist(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * get person information , and it's last qualification if exist * @param civilKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getCitizenInfoForEmp(IEntityKey civilKey) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCitizenInfoForEmp(RI(), civilKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * search citizens by their civilid or name or both * @param civilId
     * @param name
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IResponseDTO searchCitizens(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().searchCitizens(RI(), kwtCitizensResidentsSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> searchAboutCitizens(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().searchAboutCitizens(RI(), kwtCitizensResidentsSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchAboutCitizensNotEmployees(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                                         SharedApplicationException {
        try {
            return SESSION().searchAboutCitizensNotEmployees(RI(), kwtCitizensResidentsSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO addInLocal(IBasicDTO p1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().add(RI(), p1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public IBasicDTO getCitizenInfoByCallingProcedure(Long civilKey) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().getCitizenInfoByCallingProcedure(RI(), civilKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchForWife(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().searchForWife(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IPagingResponseDTO filterAvailableInfUsingPaging(IBasicDTO employeeSearchDTO1,
                                                            IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().filterAvailableInfUsingPaging(RI(), employeeSearchDTO1, requestDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IBasicDTO getCitizenCodeName(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCitizenCodeName(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }


    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getCodeNameInCenter() throws DataBaseException {
        //        if (kwtCitizensResidentsSession != null) {
        //            try {
        //                return ((KwtCitizensResidentsSession)updateBasicSession).getCodeName();
        //            } catch (RemoteException e) {
        //                SystemException se = new SystemException(e);
        //                throw new DataBaseException(se.getSQLExceptionMessage());
        //            }
        //        }
        return null;
    }

    public List getAllInCenter() throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAll(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> searchByCode(Object object) throws DataBaseException, SharedApplicationException {
        return super.searchByCode(object);
    }

    public IWifeSonInfoDTO getWifeSonInfo(IWifeSonParametersDTO wifeSonParametersDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().getWifeSonInfo(RI(), wifeSonParametersDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IKwtCitizensResidentsDTO getPersonInfo(Long realCivilID) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().getPersonInfo(RI(), realCivilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean updateWifeSon(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().updateWifeSon(RI(), kwtCitizensResidentsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getKwtCitizensResidentQuls(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getKwtCitizensResidentQuls(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IBasicDTO updateDTO(IBasicDTO kwtCitizensResidentsDTO, IBasicDTO kwtCitizensResidentsWSDTO,
                               List qual) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateDTO(RI(), kwtCitizensResidentsDTO, kwtCitizensResidentsWSDTO, qual);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IPagingResponseDTO searchWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().searchWithPaging(RI(), basicDTO);

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Boolean updatekwtCitizensResidentStatus(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return SESSION().updatekwtCitizensResidentStatus(RI(), kwtCitizensResidentsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Boolean updatekwtCitizensResidentMaritalStatus(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            return SESSION().updatekwtCitizensResidentMaritalStatus(RI(), kwtCitizensResidentsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * Get KwtCitizensResidents By Primary Key * @param id
     * Mainly Used in Module GRS - Exceptions
     * @return KwtCitizensResidentsDTO
     */
    public IBasicDTO getKwtCitizensResidentsById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getKwtCitizensResidentsById(RI(), id);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public IBasicDTO getSimpleKwtCitizensResidentsById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getSimpleKwtCitizensResidentsById(RI(), id);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public IPagingResponseDTO simpleSearchWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().simpleSearchWithPaging(RI(), basicDTO);

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public void updateWifeInfo(IWifeSonInfoDTO wifeSonInfoDTO) throws DataBaseException,SharedApplicationException{
        
            try {
                 SESSION().updateWifeInfo(RI(), wifeSonInfoDTO);

            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
    
    public void updateChildernInfo(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) throws DataBaseException,SharedApplicationException{
        
            try {
                 SESSION().updateChildernInfo(RI(), kwtCitizensResidentsDTO);

            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
    
    public IKwtCitizensResidentsDTO getPersonInfo_WS(Long realCivilID) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().getPersonInfo_WS(RI(), realCivilID);

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean updateKwtCitizenInfo_WS(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SESSION().updateKwtCitizenInfo_WS(RI(), kwtCitizensResidentsDTO1);

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
