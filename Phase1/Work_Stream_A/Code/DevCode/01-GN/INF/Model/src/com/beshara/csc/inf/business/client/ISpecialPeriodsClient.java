package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public interface ISpecialPeriodsClient extends IBasicClient {
    public List<IBasicDTO> getAllByTypeAndMinCode(BasicDTO dto) throws DataBaseException, SharedApplicationException;

}
