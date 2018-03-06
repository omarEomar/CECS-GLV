package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.inf.business.dto.IInfDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.IWifeSonInfoDTO;
import com.beshara.csc.inf.business.dto.IWifeSonParametersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.FinderException;


public interface IKwtCitizensResidentsClient extends IBasicClient {
    public IBasicDTO getKwtCitizensResidents(IEntityKey id) throws SharedApplicationException, DataBaseException;

    public IBasicDTO getCitizenInformation(Long civilId) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> searchCandIllegal(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;


    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    IBasicDTO getCitizenCodeName(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    IBasicDTO getCitizenName(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * check if the civilId exist * @param civilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public void checkCivilIdExist(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * get person information , and it's last qualification if exist * @param civilKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    IBasicDTO getCitizenInfoForEmp(IEntityKey civilKey) throws DataBaseException, SharedApplicationException;

    /**
     * search citizens by their civilid or name or both * @param civilId
     * @param name
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IResponseDTO searchCitizens(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                     SharedApplicationException;

    public List<IBasicDTO> searchAboutCitizens(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                             SharedApplicationException;

    public List<IBasicDTO> searchAboutCitizensNotEmployees(IInfDTO kwtCitizensResidentsSearchDTO) throws DataBaseException,
                                                                                                         SharedApplicationException;

    IBasicDTO getCitizenInfoByCallingProcedure(Long civilKey) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> searchForWife(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;


    public IPagingResponseDTO filterAvailableInfUsingPaging(IBasicDTO employeeSearchDTO1,
                                                            IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public List<IBasicDTO> searchByCode(Object object) throws DataBaseException, SharedApplicationException;

    public IWifeSonInfoDTO getWifeSonInfo(IWifeSonParametersDTO wifeSonParametersDTO) throws DataBaseException,
                                                                                             SharedApplicationException;
    public IKwtCitizensResidentsDTO getPersonInfo(Long realCivilID) throws  DataBaseException,
                                                                                             SharedApplicationException;

    public Boolean updateWifeSon(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                           SharedApplicationException;

    public IBasicDTO getKwtCitizensResidentQuls(Long civilId) throws DataBaseException, SharedApplicationException;


    public IBasicDTO updateDTO(IBasicDTO kwtCitizensResidentsDTO,IBasicDTO kwtCitizensResidentsWSDTO, List qual) throws DataBaseException,
                                                                                    SharedApplicationException;


    public IPagingResponseDTO searchWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException;

    public Boolean updatekwtCitizensResidentStatus(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                             SharedApplicationException;
    
    public Boolean updatekwtCitizensResidentMaritalStatus(IBasicDTO kwtCitizensResidentsDTO) throws DataBaseException,
                                                                                             SharedApplicationException;

    /**
     * Get KwtCitizensResidents By Primary Key * @param id
     * Mainly Used in Module GRS - Exceptions
     * @return KwtCitizensResidentsDTO
     */
    public IBasicDTO getKwtCitizensResidentsById(IEntityKey id) throws DataBaseException,
                                                                       SharedApplicationException;
    
    public IBasicDTO getSimpleKwtCitizensResidentsById(IEntityKey id) throws DataBaseException,
                                                                       SharedApplicationException;

    public List getAllInCenter() throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException;

    public IBasicDTO addInLocal(IBasicDTO p1) throws DataBaseException, SharedApplicationException;
    
    public IPagingResponseDTO simpleSearchWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException;
    
    public void updateWifeInfo(IWifeSonInfoDTO wifeSonInfoDTO) throws DataBaseException,SharedApplicationException;
    
    public void updateChildernInfo(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) throws DataBaseException, SharedApplicationException;
    
    public IKwtCitizensResidentsDTO getPersonInfo_WS(Long realCivilID) throws DataBaseException ,  SharedApplicationException;
    public Boolean updateKwtCitizenInfo_WS(IBasicDTO kwtCitizensResidentsDTO1) throws DataBaseException,
                                                                                      SharedApplicationException;
}
