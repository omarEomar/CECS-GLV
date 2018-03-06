package com.beshara.csc.inf.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public interface IKwtCitizensResidentsCMTClient extends IBasicClient {
    public IBasicDTO addInLocal(IBasicDTO p1) throws DataBaseException, 
                                                     SharedApplicationException;

}
