package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public interface IDecisionMakerTypesClient extends IBasicClient {
    /** 
     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List 
     * @throws DataBaseException 
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException,SharedApplicationException;

//    /** 
//     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List 
//     * @throws DataBaseException 
//     */
//    public List<IDecisionMakerTypesDTO> getCodeNameInCenter() throws DataBaseException;
//    
//     /** 
//      * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List 
//      * @throws DataBaseException 
//      */
     public List<IBasicDTO> getCodeNameByMin(Long minCode) throws DataBaseException,SharedApplicationException;
     public List<IBasicDTO> getDecisionMakerTypesByRecType(Long regtypeCode) throws DataBaseException,SharedApplicationException;
//
//    /** 
//     * Get list of code and name * return list of IDecisionMakerTypesDTO initialize with code and name only * @return List 
//     * @throws DataBaseException 
//     */
//    public List<IDecisionMakerTypesDTO> getCodeNameByMinInCenter(Long minCode) throws DataBaseException,SharedApplicationException;
}
