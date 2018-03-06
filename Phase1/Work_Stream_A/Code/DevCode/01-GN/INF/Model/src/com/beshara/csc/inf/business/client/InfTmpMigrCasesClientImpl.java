package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfTmpMigrCasesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpMigrCasesClientImpl extends BaseClientImpl3 implements IInfTmpMigrCasesClient {

    private InfTmpMigrCasesSession infTmpMigrCasesSession;

    /**
     * @param InfTmpMigrCasesSession
     */
    public InfTmpMigrCasesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfTmpMigrCasesSession.class;
    }

    @Override
    protected InfTmpMigrCasesSession SESSION() {
        return (InfTmpMigrCasesSession)super.SESSION();
    }

    public List<IBasicDTO> getAllByDataTypeCode(Long dataType, Long deskCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().getAllByDataTypeCode(RI(), dataType, deskCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public String getCaseCode(Long dataType, Long diskCode) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCaseCode(RI(), dataType, diskCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw getWrappedException(e);
        }
    }

}
