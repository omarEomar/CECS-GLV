package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

public interface InfGradeTypesSession extends BasicSession {
    public Double getFormulaByGradeType(IRequestInfoDTO ri, Long gradeTypeCode,
                                        String gradeValue) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;
}
