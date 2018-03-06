package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.inf.business.dto.InfTmpDataFieldsDTO;
import com.beshara.csc.inf.business.deploy.InfTmpDataFieldsSession;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;

import java.util.Collection;
import java.util.List;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpDataFieldsClientImpl extends BaseClientImpl3 implements IInfTmpDataFieldsClient {

    private InfTmpDataFieldsSession infTmpDataFieldsSession;

    /**
     * @param InfTmpDataFieldsSession
     */
    public InfTmpDataFieldsClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfTmpDataFieldsSession.class;
    }

    @Override
    protected InfTmpDataFieldsSession SESSION() {
        return (InfTmpDataFieldsSession)super.SESSION();
    }
    
    public List<IBasicDTO> getDataFieldsByType(Long dataType) throws DataBaseException,SharedApplicationException{
            try {
                return SESSION().getDataFieldsByType(RI(), dataType);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }

}
