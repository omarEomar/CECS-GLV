package com.beshara.csc.inf.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.sec.INFUsersDAO;
import com.beshara.csc.inf.business.dto.UserDTO;
import com.beshara.csc.inf.business.entity.sec.INFUsersEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "INFUsers", mappedName = "Inf-Model-INFUsers")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(INFUsersSession.class) 
public class INFUsersSessionBean extends InfBaseSessionBean implements INFUsersSession {
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return INFUsersEntity.class;
    }

    @Override
    protected INFUsersDAO DAO() {
        return (INFUsersDAO)super.DAO();
    }

    public UserDTO getUserByCivilIdForPortal(IRequestInfoDTO ri, Long civilid, Long isForPortal) throws DataBaseException,
                                                                                                     SharedApplicationException {
        return DAO().getUserByCivilIdForPortal(civilid, isForPortal);

    } 

    public void UpdateUserForPortal(IRequestInfoDTO ri, Long civilid, Long isForPortal) throws DataBaseException,
                                                                                               SharedApplicationException {
        DAO().UpdateUserForPortal(civilid, isForPortal);
    }
}
