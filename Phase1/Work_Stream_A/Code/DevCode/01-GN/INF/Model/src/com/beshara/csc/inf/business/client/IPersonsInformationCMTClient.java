package com.beshara.csc.inf.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public interface IPersonsInformationCMTClient extends IBasicClient{
    public IBasicDTO addCMT(IBasicDTO personsInformationDTO1) throws SharedApplicationException, DataBaseException;
}
