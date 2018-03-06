package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dao.InfTmpDataTypesDAO;
import com.beshara.csc.inf.business.entity.InfTmpDataTypesEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Stateless(name = "InfTmpDataTypesSession" , mappedName = "Inf-Model-InfTmpDataTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfTmpDataTypesSessionBean extends InfBaseSessionBean implements InfTmpDataTypesSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfTmpDataTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfTmpDataTypesEntity.class;
    }

    @Override
    protected InfTmpDataTypesDAO DAO() {
        return (InfTmpDataTypesDAO)super.DAO();
    }
    
}
