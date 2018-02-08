package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.deploy.BasicSessionBean;
import com.beshara.base.entity.BasicEntity;

import com.beshara.csc.gn.map.business.dao.SocietyRelationTypesDAO;
import com.beshara.csc.gn.map.business.entity.SocietyRelationTypesEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author Beshara Group
 * @author Heba.Ahmed
 * @version 1.0
 * @since 31/12/2014
 */

@Stateless(name = "SocietyRelationTypesSession", mappedName = "Map-Model-SocietyRelationTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SocietyRelationTypesSession.class)
public class SocietyRelationTypesSessionBean extends MapBaseSessionBean implements SocietyRelationTypesSession {
    public SocietyRelationTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SocietyRelationTypesEntity.class;
    }
    
    @Override
    protected SocietyRelationTypesDAO DAO() {
        return (SocietyRelationTypesDAO)super.DAO();
    }

   
}
