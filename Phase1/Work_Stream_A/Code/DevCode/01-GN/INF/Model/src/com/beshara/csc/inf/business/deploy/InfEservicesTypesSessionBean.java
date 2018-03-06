package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfEservicesTypesDAO;
import com.beshara.csc.inf.business.entity.InfEservicesTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfEservicesTypesSession", mappedName = "InfEservicesTypesSession")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(InfEservicesTypesSession.class)

public class InfEservicesTypesSessionBean extends InfBaseSessionBean implements InfEservicesTypesSession {


    public InfEservicesTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfEservicesTypesEntity.class;
    }

    @Override
    protected InfEservicesTypesDAO DAO() {
        return (InfEservicesTypesDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

}
