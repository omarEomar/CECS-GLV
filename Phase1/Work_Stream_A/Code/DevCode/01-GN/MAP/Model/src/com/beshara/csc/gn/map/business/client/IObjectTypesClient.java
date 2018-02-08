package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
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
public interface IObjectTypesClient extends IBasicClient { /**
 * get all ObjectTypesDTOs * @return List IObjectTypesDTO
 * @throws DataBaseException
 */
    // List<IObjectTypesDTO> getAll ( ) throws DataBaseException ; 
    // 
    // /** 
    // * create a MapDTOFactory.createObjectTypesDTO 
    // * @param objectTypesDTO the dto to insert 
    // * @return objectTypesDTO the inserted dto with generated code 
    // * @throws DataBaseException 
    // */ 
    // IObjectTypesDTO createObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws DataBaseException ; 
    // 
    // /** 
    // * update an existing IObjectTypesDTO 
    // * @param objectTypesDTO the dto to update 
    // * @return Boolean 
    // * @throws DataBaseException 
    // * @throws ItemNotFoundException 
    // */ 
    // Boolean updateObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws DataBaseException , ItemNotFoundException ; 
    // 
    // /** 
    // * Remove an existing dto 
    // * @param objectTypesDTO the dto to be removed 
    // * @return Boolean 
    // * @throws DataBaseException 
    // * @throws ItemNotFoundException 
    // */ 
    // Boolean removeObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws DataBaseException , ItemNotFoundException ; 
    // 
    // /** 
    // * Get ObjectTypeDTO with specific Primary Key 
    // * @param id the ObjectTypeDTO to retrieve primary key 
    // * @return IObjectTypesDTO 
    // * @throws DataBaseException 
    // * @throws ItemNotFoundException 
    // */ 
    // IObjectTypesDTO getById ( Long id ) throws DataBaseException , ItemNotFoundException ; 
    // 
    // /** 
    // * get all ObjectTypesDTOs with objecttypename like a given name 
    // * @return List IObjectTypesDTO 
    // * @throws DataBaseException 
    // */ 
    // public List<IObjectTypesDTO> getAllByName ( String name ) throws DataBaseException ; 

    /** 
     * get all ObjectTypesDTOs with code and name set * @return List of IBasicDTO 
     * @throws DataBaseException 
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException;
    
    public List<IBasicDTO> getObjectTypeSearchCrieteria(String searchQuery,String searchType) throws DataBaseException,SharedApplicationException;
}
