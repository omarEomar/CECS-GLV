package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.inf.business.dto.InfTmpDataFieldsDTO;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.inf.business.dao.*;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.rmi.RemoteException; 
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.entity.InfTmpDataFieldsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Remote;

 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Stateless(name = "InfTmpDataFieldsSession" , mappedName = "Inf-Model-InfTmpDataFieldsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfTmpDataFieldsSessionBean extends InfBaseSessionBean implements InfTmpDataFieldsSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfTmpDataFieldsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfTmpDataFieldsEntity.class;
    }

    @Override
    protected InfTmpDataFieldsDAO DAO() {
        return (InfTmpDataFieldsDAO)super.DAO();
    }
    
    public List<IBasicDTO> getDataFieldsByType(IRequestInfoDTO  ri,Long dataType) throws DataBaseException,SharedApplicationException{
        return DAO().getDataFieldsByType(dataType);
        }
    
}
