package com.beshara.csc.gn.inf.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.gn.inf.business.dto.InfOldDataAuditDTO;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.gn.inf.business.dao.*;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.rmi.RemoteException; 
import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.gn.inf.business.entity.InfOldDataAuditEntity;
import com.beshara.csc.inf.business.deploy.InfBaseSessionBean;

import javax.ejb.Remote;

 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Stateless(name = "InfOldDataAuditSession" , mappedName = "Inf-Model-InfOldDataAuditSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfOldDataAuditSessionBean extends InfBaseSessionBean implements InfOldDataAuditSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfOldDataAuditSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfOldDataAuditEntity.class;
    }

    @Override
    protected InfOldDataAuditDAO DAO() {
        return (InfOldDataAuditDAO)super.DAO();
    }
    
}
