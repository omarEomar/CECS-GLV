package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.inf.business.dao.PersonDocAtchTypesDAO;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless(name = "InfPersonDocAtchTypesSession", mappedName = "InfPersonDocAtchTypesSession")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(PersonDocAtchTypesSession.class)
public class PersonDocAtchTypesSessionBean extends InfBaseSessionBean implements PersonDocAtchTypesSession {
    public PersonDocAtchTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return PersonDocAtchTypesEntity.class;
    }

    @Override
    protected PersonDocAtchTypesDAO DAO() {
        return (PersonDocAtchTypesDAO)super.DAO();
    }
}
