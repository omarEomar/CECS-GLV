package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public interface IInfGradeTypesClient extends IBasicClient {


    public Double getFormulaByGradeType(Long gradeTypeCode, String gradeValue) throws SharedApplicationException,
                                                                                      DataBaseException;


}
