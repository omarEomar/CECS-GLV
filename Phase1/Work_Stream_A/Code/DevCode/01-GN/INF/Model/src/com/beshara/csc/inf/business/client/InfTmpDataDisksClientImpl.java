package com.beshara.csc.inf.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.inf.business.dto.InfTmpDataDisksDTO;
import com.beshara.csc.inf.business.deploy.InfTmpDataDisksSession;
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

public class InfTmpDataDisksClientImpl extends BaseClientImpl3 implements IInfTmpDataDisksClient {

    private InfTmpDataDisksSession infTmpDataDisksSession;

    /**
     * @param InfTmpDataDisksSession
     */
    public InfTmpDataDisksClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfTmpDataDisksSession.class;
    }

    @Override
    protected InfTmpDataDisksSession SESSION() {
        return (InfTmpDataDisksSession)super.SESSION();
    }
    
    public List<IBasicDTO> getByDataTypeCode(Long dataType) throws DataBaseException,SharedApplicationException{
            try {
                return SESSION().getByDataTypeCode(RI(), dataType);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
    
    public Long getDiskNum(Long dataType)throws DataBaseException,SharedApplicationException{
            try {
                return SESSION().getDiskNum(RI(), dataType);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        
        }

}
