package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface YearsSession extends BasicSession {

    public List<IYearsDTO> getCodeName(IRequestInfoDTO  ri) throws RemoteException, DataBaseException, SharedApplicationException;

    public List<IYearsDTO> getCodeNameUntil(IRequestInfoDTO  ri,Long yearCode) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public List<IBasicDTO> getYearByName(IRequestInfoDTO  ri,String name) throws RemoteException, DataBaseException,
                                                             SharedApplicationException;

    public List<IBasicDTO> search(IRequestInfoDTO  ri,IBasicDTO basicDTO) throws RemoteException, DataBaseException,
                                                             SharedApplicationException;
    
    public IYearsDTO getByIdSimple(IRequestInfoDTO  ri,IEntityKey entityKey) throws RemoteException,DataBaseException, SharedApplicationException ;
    
    public List<IBasicDTO> getAllExcludedList(IRequestInfoDTO  ri,List<String>excludedYears) throws RemoteException, DataBaseException,
                                                             SharedApplicationException;
    
    List<IYearsDTO> getCodeNameAfterDate(IRequestInfoDTO  ri,Timestamp date) throws RemoteException,DataBaseException, SharedApplicationException;
    
}
