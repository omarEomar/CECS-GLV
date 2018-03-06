package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.IInfDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IResponseDTO;
import com.beshara.csc.inf.business.dto.IWifeSonInfoDTO;
import com.beshara.csc.inf.business.dto.IWifeSonParametersDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;
import javax.ejb.Remote;


@Remote
public interface KwtCitizensResidentsSession extends BasicSession {
    public IBasicDTO getKwtCitizensResidents( IRequestInfoDTO ri,IEntityKey id) throws DataBaseException, SharedApplicationException,
                                                                   RemoteException;

    IBasicDTO getCitizenInformation( IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;

    List<IBasicDTO> getCodeName( IRequestInfoDTO ri) throws RemoteException,DataBaseException,SharedApplicationException;
   

    public List<IBasicDTO> searchCandIllegal( IRequestInfoDTO ri,IBasicDTO basicDTO) throws RemoteException,DataBaseException, SharedApplicationException;
    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    IBasicDTO getCitizenCodeName( IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;

    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws FinderException
     */
    IBasicDTO getCitizenName( IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;

    /**
     * check if the civilId exist * @param civilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public void checkCivilIdExist( IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;

    /**
     * get person information , and it's last qualification if exist * @param civilKey
     * @return IBasicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    IBasicDTO getCitizenInfoForEmp( IRequestInfoDTO ri,IEntityKey civilKey) throws RemoteException,DataBaseException, SharedApplicationException;

    /**
     * search citizens by their civilid or name or both * @param civilId
     * @param name
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IResponseDTO searchCitizens( IRequestInfoDTO ri,IInfDTO kwtCitizensResidentsSearchDTO) throws RemoteException,DataBaseException,
                                                                                     SharedApplicationException;

    public List<IBasicDTO> searchAboutCitizens( IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,DataBaseException,
                                                                                               SharedApplicationException;
    
    public List<IBasicDTO> searchAboutCitizensNotEmployees( IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,DataBaseException,
                                                                                               SharedApplicationException;

    IBasicDTO getCitizenInfoByCallingProcedure( IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, DataBaseException,
                                                                    SharedApplicationException;

    List<IBasicDTO> searchForWife( IRequestInfoDTO ri,IBasicDTO basicDTO) throws RemoteException,DataBaseException, SharedApplicationException;
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
     * search citizens by their civilid or name or both * @param civilId
     * @param name
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
   
    public IPagingResponseDTO filterAvailableInfUsingPaging( IRequestInfoDTO ri,IBasicDTO employeeSearchDTO1,
                                                            IPagingRequestDTO requestDTO) throws RemoteException,DataBaseException,
                                                                                                 SharedApplicationException;


  
    public List<IBasicDTO> searchByCode( IRequestInfoDTO ri,Object object) throws RemoteException,DataBaseException, SharedApplicationException,
                                                              DataBaseException;

    public IWifeSonInfoDTO getWifeSonInfo( IRequestInfoDTO ri,IWifeSonParametersDTO wifeSonParametersDTO) throws RemoteException,
                                                                                             DataBaseException,
                                                                                             SharedApplicationException;
    
    public IKwtCitizensResidentsDTO getPersonInfo(IRequestInfoDTO ri,Long realCivilID) throws RemoteException, DataBaseException,
                                                                                             SharedApplicationException;

    public Boolean updateWifeSon( IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsDTO) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;
    public IBasicDTO getKwtCitizensResidentQuls( IRequestInfoDTO ri,Long civilId) throws RemoteException, DataBaseException,SharedApplicationException;
    
  
    public IBasicDTO updateDTO( IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsDTO ,IBasicDTO kwtCitizensResidentsWSDTO, List qual) throws RemoteException, DataBaseException,SharedApplicationException;
    
    public IPagingResponseDTO searchWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException, SharedApplicationException,
                                                                                              DataBaseException;
    
    
    public Boolean updatekwtCitizensResidentStatus( IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsDTO) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;

    public Boolean updatekwtCitizensResidentMaritalStatus(IRequestInfoDTO ri,
                                                          IBasicDTO kwtCitizensResidentsDTO1) throws RemoteException,
                                                                                                     DataBaseException,
                                                                                                     SharedApplicationException ;
    
    
    /**
     * Get KwtCitizensResidents By Primary Key * @param id
     * Mainly Used in Module GRS - Exceptions
     * @return KwtCitizensResidentsDTO
     */
    public IBasicDTO getKwtCitizensResidentsById(IRequestInfoDTO ri,IEntityKey id) throws RemoteException, DataBaseException,SharedApplicationException;
    
    public IBasicDTO getSimpleKwtCitizensResidentsById(IRequestInfoDTO ri,IEntityKey id) throws RemoteException, DataBaseException,SharedApplicationException;
    
    public IPagingResponseDTO simpleSearchWithPaging(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException, SharedApplicationException,
                                                                                              DataBaseException;
    
    public void updateWifeInfo(IRequestInfoDTO ri,IWifeSonInfoDTO wifeSonInfoDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException ;

    public void updateChildernInfo(IRequestInfoDTO ri, IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) throws  RemoteException,DataBaseException, SharedApplicationException ;   
    
    public IKwtCitizensResidentsDTO getPersonInfo_WS(IRequestInfoDTO ri, Long realCivilID) throws RemoteException, DataBaseException, SharedApplicationException;
 
    public Boolean updateKwtCitizenInfo_WS(IRequestInfoDTO ri,IBasicDTO kwtCitizensResidentsDTO1) throws  RemoteException,DataBaseException,
                                                                            SharedApplicationException;
}

