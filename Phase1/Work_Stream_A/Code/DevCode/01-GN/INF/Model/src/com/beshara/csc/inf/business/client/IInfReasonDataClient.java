package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public interface IInfReasonDataClient  extends IBasicClient {
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException  ;
    public List<IBasicDTO> getReasonDataByType(long reasontype) throws DataBaseException, 
                                                                     SharedApplicationException;
    public List<IBasicDTO> searchReasonData(IBasicDTO infReasonDataDTO)  throws DataBaseException, 
                                                                     SharedApplicationException;
}
