package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.SystemSettingsSession;
import com.beshara.csc.inf.business.deploy.UnitsOfMeasureSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class UnitsOfMeasureClientImpl extends BaseClientImpl3 implements IUnitsOfMeasureClient {
    public UnitsOfMeasureClientImpl() {
       
    }
    
    
    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return UnitsOfMeasureSession.class;
    }

    @Override
    protected UnitsOfMeasureSession SESSION() {
        return (UnitsOfMeasureSession)super.SESSION();
    }

    public List<IBasicDTO> getUnitsOfMeasureCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getUnitsOfMeasureCodeName(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
}
