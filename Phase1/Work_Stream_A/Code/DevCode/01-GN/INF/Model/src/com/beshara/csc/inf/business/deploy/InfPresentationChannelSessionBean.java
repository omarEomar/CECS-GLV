package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dao.InfPresentationChannelDAO;
import com.beshara.csc.inf.business.entity.InfPresentationChannelEntity;

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
@Stateless(name = "InfPresentationChannelSession", mappedName = "Inf-Model-InfPresentationChannelSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfPresentationChannelSessionBean extends InfBaseSessionBean implements InfPresentationChannelSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfPresentationChannelSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfPresentationChannelEntity.class;
    }

    @Override
    protected InfPresentationChannelDAO DAO() {
        return (InfPresentationChannelDAO)super.DAO();
    }

}
