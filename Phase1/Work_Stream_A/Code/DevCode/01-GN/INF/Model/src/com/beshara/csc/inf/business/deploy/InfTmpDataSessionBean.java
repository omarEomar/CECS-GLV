package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.inf.business.dto.InfTmpDataDTO;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.inf.business.dao.*;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.rmi.RemoteException; 
import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.entity.InfTmpDataEntity;

import javax.ejb.Remote;

 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Stateless(name = "InfTmpDataSession" , mappedName = "Inf-Model-InfTmpDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfTmpDataSessionBean extends InfBaseSessionBean implements InfTmpDataSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfTmpDataSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfTmpDataEntity.class;
    }

    @Override
    protected InfTmpDataDAO DAO() {
        return (InfTmpDataDAO)super.DAO();
    }
    
}
