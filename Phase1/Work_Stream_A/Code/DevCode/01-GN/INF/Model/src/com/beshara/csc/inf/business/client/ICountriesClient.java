package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Interface Contains a set of Methods Which Should be Implemented * with Different Implementation ( SessionBean , Message Driven Bean , RMI Service etc ... ) * and Generated through The Client Factory Class . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif El-Rabbat 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry
 * @version 1.0
 * @since 03/09/2007
 */
public interface ICountriesClient extends IBasicClient {
    /** 
     * Returns list of all countries Name and code only * @return List 
     * @throws DataBaseException 
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException;

    /** 
     * Returns list of all countries Name and code only * @return List 
     * @throws DataBaseException 
     */
    List<IBasicDTO> getCodeNameInCenter() throws DataBaseException, SharedApplicationException;
    
    public List<IBasicDTO> getAllOrderByClass() throws DataBaseException, SharedApplicationException;
    
    List<IBasicDTO> searchCountries(Long code, List<String> excludedList) throws SharedApplicationException, DataBaseException;
    List<IBasicDTO> searchCountries(String name, List<String> excludedList) throws SharedApplicationException, DataBaseException;
    
    /**
     * return country without excluded list
     * @author I.Omar
     * */
    List<IBasicDTO> getAllWithoutExcludedList(List<String> excludedList) throws DataBaseException,  SharedApplicationException;
    IBasicDTO getCodeName(IEntityKey key) throws DataBaseException, SharedApplicationException;
    
    public List<IBasicDTO> getCodeNameByQulKey(String qulKey) throws DataBaseException, SharedApplicationException;
}
