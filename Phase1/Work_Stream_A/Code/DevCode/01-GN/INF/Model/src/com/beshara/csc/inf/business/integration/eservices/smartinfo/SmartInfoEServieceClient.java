package com.beshara.csc.inf.business.integration.eservices.smartinfo;

import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class SmartInfoEServieceClient extends EServiceClient {
    public SmartInfoEServieceClient() {
        super();
    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return ISmartInfoEServiece.class;
    }

    @Override
    protected ISmartInfoEServiece SERVICE() {
        return (ISmartInfoEServiece)super.SERVICE();
    }

    public List getWeeks() throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().getWeeks();

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public List getDocInfType() throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().getDocInfType();

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public List getMonths() throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().getMonths();

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public List getyears() throws DataBaseException, SharedApplicationException {
        try {
            return SERVICE().getyears();

        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public List getCountries() throws DataBaseException, SharedApplicationException {
            try {
                return SERVICE().getCountries();

            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        
        }
}

