package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public interface IPersonDocAttachemntsClient extends IBasicClient {
   
    public List<IBasicDTO> getAllByCivilAndDocType(Long civilId, String doctypeCode) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public IBasicDTO addPersonDocAttachment(IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException;
    
    public List<IBasicDTO> searchAllDocuments(IBasicDTO searchDTO) throws DataBaseException,SharedApplicationException;
}
