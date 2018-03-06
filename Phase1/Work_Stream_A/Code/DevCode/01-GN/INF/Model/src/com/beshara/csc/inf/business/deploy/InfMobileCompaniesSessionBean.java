package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dao.InfMobileCompaniesDAO;
import com.beshara.csc.inf.business.entity.InfMobileCompaniesEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */
@Stateless(name = "InfMobileCompaniesSession", mappedName = "Inf-Model-InfMobileCompaniesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfMobileCompaniesSessionBean extends InfBaseSessionBean implements InfMobileCompaniesSession {

    public InfMobileCompaniesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfMobileCompaniesEntity.class;
    }

    @Override
    protected InfMobileCompaniesDAO DAO() {
        return (InfMobileCompaniesDAO)super.DAO();
    }

}
