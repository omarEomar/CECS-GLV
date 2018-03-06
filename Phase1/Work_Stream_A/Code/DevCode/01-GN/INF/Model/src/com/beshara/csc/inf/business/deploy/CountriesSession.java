package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface CountriesSession extends BasicSession {
    /**
     * Returns list of all countries Name and code only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public List<IBasicDTO> getAllOrderByClass(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                         SharedApplicationException;
    
    /**
     * return country without cities
     * @author I.Omar
     * */
    List<IBasicDTO> searchCountries(IRequestInfoDTO ri, Long code , List<String> excludedList) throws RemoteException,SharedApplicationException, DataBaseException;
    List<IBasicDTO> searchCountries(IRequestInfoDTO ri, String name, List<String> excludedList) throws RemoteException,SharedApplicationException, DataBaseException;
    
    /**
     * return country without excluded list
     * @author I.Omar
     * */
    List<IBasicDTO> getAllWithoutExcludedList(IRequestInfoDTO ri,List<String> excludedList) throws RemoteException,DataBaseException,  SharedApplicationException;
    IBasicDTO getCodeName(IRequestInfoDTO ri, IEntityKey key) throws RemoteException,DataBaseException,
                                                                            SharedApplicationException;
    
    public List<IBasicDTO> getCodeNameByQulKey(IRequestInfoDTO ri,String qulKey) throws RemoteException, 
                                                                DataBaseException, SharedApplicationException;
}
