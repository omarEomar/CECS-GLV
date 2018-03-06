package com.beshara.csc.inf.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public interface IPersonQualificationsCMTClient extends IBasicClient {
    public Boolean updateRegisterationOrderCMT(IPersonQualificationsDTO personQualificationsDTO) throws SharedApplicationException, 
                                                                                                        DataBaseException;

}
