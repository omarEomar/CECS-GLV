package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.deploy.CountriesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

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
public class CountriesClientImpl extends BaseClientImpl3 implements ICountriesClient {

    public CountriesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return CountriesSession.class;
    }

    @Override
    protected CountriesSession SESSION() {
        return (CountriesSession)super.SESSION();
    }

    /**
     * Returns list of all countries Name and code only * @return List
     * @throws DataBaseException
     */
    @Override
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * Returns list of all countries Name and code only * @return List
     * @throws DataBaseException
     */
    @Override
    public List<IBasicDTO> getCodeNameInCenter() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public List<IBasicDTO> getAllOrderByClass() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllOrderByClass(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return country without cities
     * @author I.Omar
     * */
    public List<IBasicDTO> searchCountries(Long code, List<String> excludedList) throws SharedApplicationException,
                                                                                        DataBaseException {
        try {
            return SESSION().searchCountries(RI(), code, excludedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return country without cities
     * @author I.Omar
     * */
    public List<IBasicDTO> searchCountries(String name, List<String> excludedList) throws SharedApplicationException,
                                                                                          DataBaseException {
        try {
            return SESSION().searchCountries(RI(), name, excludedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * return country without excluded list
     * @author I.Omar
     * */
    public List<IBasicDTO> getAllWithoutExcludedList(List<String> excludedList) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return SESSION().getAllWithoutExcludedList(RI(), excludedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getCodeName(IEntityKey key) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI(), key);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public List<IBasicDTO> getCodeNameByQulKey(String qulKey) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeNameByQulKey(RI(), qulKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

}
