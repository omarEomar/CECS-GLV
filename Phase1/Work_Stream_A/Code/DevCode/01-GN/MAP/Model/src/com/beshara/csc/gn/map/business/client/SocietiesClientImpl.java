package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.client.BasicClientImpl;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.deploy.SocietiesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;

import java.rmi.RemoteException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class SocietiesClientImpl extends BaseClientImpl3 implements ISocietiesClient {
    public SocietiesClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return SocietiesSession.class;
    }

    @Override
    protected SocietiesSession SESSION() {
        return (SocietiesSession)super.SESSION();
    }

    /**
     * retrieve a ISocietiesDTO with the given Id * @param id Id for the requiredDto
     * @return ISocietiesDTO
     * @throws DataBaseException
     * @throws ItemNotFoundException
     */
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getById(RI(), id);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get all data with specific objectTypeCode * @param objtypeCode
     * @return List ISocietiesDTO
     * @throws DataBaseException
     */
    public List<IBasicDTO> listByObjectType(Long objtypeCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().listByObjectType(RI(), objtypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get all SocietiesDTOs with socName like a given name * @return List ISocietiesDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().searchByName(RI(), name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> getAllByFalg(Long flag) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllByFalg(RI(), flag);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByMinCode(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().searchByMinCode(RI(), minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchByNameAndStatus(String name, String SystemOrCenterFlag) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return SESSION().searchByNameAndStatus(RI(), name, SystemOrCenterFlag);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


}
