package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.PersonDocAttachemntsSession;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public class PersonDocAttachemntsClientImpl extends BaseClientImpl3 implements IPersonDocAttachemntsClient {
    public PersonDocAttachemntsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonDocAttachemntsSession.class;
    }

    @Override
    protected PersonDocAttachemntsSession SESSION() {
        return (PersonDocAttachemntsSession)super.SESSION();
    }

    public List<IBasicDTO> getAllByCivilAndDocType(Long civilId, String doctypeCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().getAllByCivilAndDocType(RI(), civilId, doctypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO addPersonDocAttachment(IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().addPersonDocAttachment(RI(), dtoParam);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public List<IBasicDTO> searchAllDocuments(IBasicDTO searchDTO) throws DataBaseException,SharedApplicationException {
        try {
            return SESSION().searchAllDocuments(RI(), searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
}
