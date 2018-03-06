package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.IBasicDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;


public interface IKwtCitizensResidentsDAO extends IBasicDAO {
    /**
     * get kwtCitizensResidents Data only in table (without list relations).
     * @param id
     * @return kwtCitizensResidentsDTO
     * @throws RemoteException
     * @throws ItemNotFoundException
     * @auther KareemSayed
     */
    public IBasicDTO getKwtCitizensResidents(IEntityKey id) throws RemoteException, ItemNotFoundException;

    public IBasicDTO getCitizenInformation(Long civilId) throws RemoteException, ItemNotFoundException;

    /**
     * Get Citizen CodeName * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public IBasicDTO getCitizenCodeName(Long civilId) throws RemoteException, ItemNotFoundException;
    public List<IBasicDTO> searchCandIllegal(IBasicDTO basicDTO) throws RemoteException,ItemNotFoundException;
   

    /**
     * Get Citizen Name * @param civilId
     * @return IBasicDTO
     * @throws RemoteException
     * @throws FinderException
     */
    IBasicDTO getCitizenName(Long civilId) throws RemoteException, ItemNotFoundException;


    List<IBasicDTO> getCodeName() throws RemoteException;

    /**
     * check if the civilId exist * @param civilId
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public void checkCivilIdExist(Long civilId) throws RemoteException, ItemNotFoundException;

    /**
     * search citizens by their civilid or name or both * @param civilId
     * @param name
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchCitizens(IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,
                                                                                          ItemNotFoundException;

    public List<IBasicDTO> searchAboutCitizens(IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,
                                                                                               ItemNotFoundException;

    public List<IBasicDTO> searchAboutCitizensNotEmployees(IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,
                                                                                               ItemNotFoundException;
    
    public Long getSearchCitizensCount(IBasicDTO kwtCitizensResidentsSearchDTO) throws RemoteException,
                                                                                       ItemNotFoundException;


    public List<IBasicDTO> filterAvailableInfUsingPaging(IBasicDTO employeeSearchDTO1,
                                                         IPagingRequestDTO requestDTO) throws RemoteException,
                                                                                              ItemNotFoundException;

    public Long filterAvailableInfCountUsingPaging(IBasicDTO employeeSearchDTO1) throws RemoteException,
                                                                                        ItemNotFoundException;

    public List<IBasicDTO> searchByCode(Object code) throws RemoteException, ItemNotFoundException;

    public Boolean updateWifeSon(IBasicDTO kwtCitizensResidentsDTO) throws RemoteException, ItemNotFoundException;

    public  IBasicDTO getKwtCitizensResidentQuls(Long civilId) throws RemoteException,ItemNotFoundException ;
    
    public IBasicDTO updateDTO(IBasicDTO kwtCitizensResidentsDTO , List qual) throws RemoteException, ItemNotFoundException; 
    
    
    public Long getCountSearchWithPaging(IBasicDTO basicDTO) throws RemoteException,DataBaseException, SharedApplicationException ;
    
    
    public List<IBasicDTO> searchWithPaging(IBasicDTO basicDTO) throws RemoteException,DataBaseException, SharedApplicationException ;
    
    public Boolean updatekwtCitizensResidentStatus(IBasicDTO kwtCitizensResidentsDTO1) throws RemoteException, ItemNotFoundException ;
    
    public IBasicDTO getKwtCitizensResidentsById(IEntityKey id) throws RemoteException,ItemNotFoundException ;
}
