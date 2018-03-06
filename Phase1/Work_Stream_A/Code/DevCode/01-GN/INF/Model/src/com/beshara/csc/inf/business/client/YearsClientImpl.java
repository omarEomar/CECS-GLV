package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.deploy.YearsSession;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Timestamp;

import java.util.List;


public class YearsClientImpl extends BaseClientImpl3 implements IYearsClient {

    public YearsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return YearsSession.class;
    }

    @Override
    protected YearsSession SESSION() {
        return (YearsSession)super.SESSION();
    }

    /**
     * Get list of code and name * return list of IYearsDTO initialize with code and name only * @return List
     * @throws DataBaseException
     */
    public List<IYearsDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

    /**
     * Get list of code and name * return list of IYearsDTO initialize with code and name only * @return List
     * @throws DataBaseException
     */
    public List<IYearsDTO> getCodeNameInCenter() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeName(RI());
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

    public List<IYearsDTO> getCodeNameUntil(Long yearCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getCodeNameUntil(RI(),yearCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

    public List<IYearsDTO> getCodeNameUntilInCenter(Long yearCode) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().getCodeNameUntil(RI(),yearCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
    }

    public List<IBasicDTO> getYearByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getYearByName(RI(),name);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }

    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().search(RI(),basicDTO);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }
    
    public IYearsDTO getByIdSimple(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {

        try {
             return SESSION().getByIdSimple(RI(), entityKey);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }
    
    public List<IBasicDTO> getAllExcludedList(List<String> excludedYears) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAllExcludedList(RI(),excludedYears);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }
    
    /**
     * Get list of code and name greeter than specific date 
     * @return List
     * @author I.Omar
     */
    public List<IYearsDTO> getCodeNameAfterDate(Timestamp date) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCodeNameAfterDate(RI(), date);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }
    
}
