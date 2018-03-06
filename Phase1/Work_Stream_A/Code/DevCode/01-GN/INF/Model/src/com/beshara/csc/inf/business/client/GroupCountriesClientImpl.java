package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.GroupCountriesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class GroupCountriesClientImpl extends BaseClientImpl3 implements IGroupCountriesClient {

    /**
     * @param groupCountriesSession
     */
    public GroupCountriesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return GroupCountriesSession.class;
    }

    @Override
    protected GroupCountriesSession SESSION() {
        return (GroupCountriesSession)super.SESSION();
    }

    public List<IBasicDTO> getCountries(Long groupCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCountries(RI(), groupCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * list available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getAvailableCountries(Long catCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAvailableCountries(RI(), catCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * search available countries for group * @param catCode
     * @return list
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchAvailableCountriesByCode(Long catCode, Long countryCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().searchAvailableCountriesByCode(RI(), catCode, countryCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * search available countries for group * @param catCode
     * @return list
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchAvailableCountriesByName(Long catCode, String countryName) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return SESSION().searchAvailableCountriesByName(RI(), catCode, countryName);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * @param countryGroupDTO
     * @param countriesDTOList
     * @return Boolean
     * @throws DataBaseException
     */
    public List<IBasicDTO> addCountries(IBasicDTO countryGroupDTO,
                                        List<IBasicDTO> countriesDTOList) throws SharedApplicationException,
                                                                                 DataBaseException {
        try {
            return SESSION().addCountries(RI(), countryGroupDTO, countriesDTOList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByCode(Long groupCode, Long countryCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().searchByCode(RI(), groupCode, countryCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByName(Long groupCode, String countryName) throws SharedApplicationException,
                                                                                   DataBaseException {
        try {
            return SESSION().searchByName(RI(), groupCode, countryName);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
