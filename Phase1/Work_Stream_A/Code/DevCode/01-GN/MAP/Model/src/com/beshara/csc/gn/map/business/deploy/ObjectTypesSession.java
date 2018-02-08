package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface ObjectTypesSession extends BasicSession {
    /**
     * get all ObjectTypesDTOs with code and name set * @return List of IBasicDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public List<IBasicDTO> getObjectTypeSearchCrieteria(IRequestInfoDTO ri, String searchQuery,
                                                        String searchType) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;
    // /**
    // * get all ObjectTypesDTOs
    // * @return List IObjectTypesDTO
    // * @throws RemoteException
    // */
    // List<IObjectTypesDTO> getAll ( ) throws RemoteException ;
    //
    // /**
    // * create a MapDTOFactory.createObjectTypesDTO
    // * @param objectTypesDTO the dto to insert
    // * @return objectTypesDTO the inserted dto with generated code
    // * @throws RemoteException
    // */
    // IObjectTypesDTO createObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws RemoteException ;
    //
    // /**
    // * update an existing IObjectTypesDTO
    // * @param objectTypesDTO the dto to update
    // * @return Boolean
    // * @throws RemoteException
    // * @throws ItemNotFoundException
    // */
    // Boolean updateObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws RemoteException , ItemNotFoundException ;
    //
    // /**
    // * Remove an existing dto
    // * @param objectTypesDTO the dto to be removed
    // * @return Boolean
    // * @throws RemoteException
    // * @throws ItemNotFoundException
    // */
    // Boolean removeObjectTypes ( IObjectTypesDTO objectTypesDTO ) throws RemoteException , ItemNotFoundException ;
    //
    // /**
    // * Get ObjectTypeDTO with specific Primary Key
    // * @param id the ObjectTypeDTO to retrieve primary key
    // * @return IObjectTypesDTO
    // * @throws RemoteException
    // * @throws ItemNotFoundException
    // */
    // IObjectTypesDTO getById ( Long id ) throws RemoteException , ItemNotFoundException ;
    //
    // /**
    // * get all ObjectTypesDTOs with objecttypename like a given name
    // * @return List IObjectTypesDTO
    // * @throws RemoteException
    // */
    // public List<IObjectTypesDTO> getAllByName ( String name ) throws RemoteException ;
    //
}
