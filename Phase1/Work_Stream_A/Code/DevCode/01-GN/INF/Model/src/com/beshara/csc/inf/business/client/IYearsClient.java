package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Timestamp;

import java.util.List;


public interface IYearsClient extends IBasicClient {
    /**
     * Get list of code and name * return list of IYearsDTO initialize with code and name only * @return List
     * @throws DataBaseException
     */
    public List<IYearsDTO> getCodeName() throws DataBaseException, SharedApplicationException;

    /**
     * Get list of code and name * return list of IYearsDTO initialize with code and name only * @return List
     * @throws DataBaseException
     */
    List<IYearsDTO> getCodeNameInCenter() throws DataBaseException, SharedApplicationException;

    public List<IYearsDTO> getCodeNameUntil(Long yearCode) throws DataBaseException, SharedApplicationException;

    public List<IYearsDTO> getCodeNameUntilInCenter(Long yearCode) throws DataBaseException,
                                                                          SharedApplicationException;

    public List<IBasicDTO> getYearByName(String name) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;
    
    public IYearsDTO getByIdSimple(IEntityKey entityKey) throws DataBaseException, SharedApplicationException ;
    
    public List<IBasicDTO> getAllExcludedList(List<String> excludedYears) throws DataBaseException, SharedApplicationException;
    
    List<IYearsDTO> getCodeNameAfterDate(Timestamp date) throws DataBaseException, SharedApplicationException;
}
