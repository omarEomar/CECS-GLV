package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.SpecialPeriodsSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class SpecialPeriodsClientImpl extends BaseClientImpl3 implements ISpecialPeriodsClient {
    public SpecialPeriodsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return SpecialPeriodsSession.class;
    }

    @Override
    protected SpecialPeriodsSession SESSION() {
        return (SpecialPeriodsSession)super.SESSION();
    }

    public List<IBasicDTO> getAllByTypeAndMinCode(BasicDTO dto) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllByTypeAndMinCode(RI(), dto);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

}
