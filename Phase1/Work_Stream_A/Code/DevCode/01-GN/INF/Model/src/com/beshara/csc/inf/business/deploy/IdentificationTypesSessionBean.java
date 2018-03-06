package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dao.IdentificationTypesDAO;
import com.beshara.csc.inf.business.entity.IdentificationTypesEntity;

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
@Stateless(name = "IdentificationTypesSession", mappedName = "Inf-Model-IdentificationTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class IdentificationTypesSessionBean extends InfBaseSessionBean implements IdentificationTypesSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public IdentificationTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return IdentificationTypesEntity.class;
    }

    @Override
    protected IdentificationTypesDAO DAO() {
        return (IdentificationTypesDAO)super.DAO();
    }

}
