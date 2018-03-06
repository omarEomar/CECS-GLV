package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.emp.InfReasonTypesDAO;
import com.beshara.csc.inf.business.entity.emp.InfReasonTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfReasonTypesSession", mappedName = "Inf-Model-InfReasonTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfReasonTypesSessionBean extends InfBaseSessionBean implements InfReasonTypesSession {

    public InfReasonTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfReasonTypesEntity.class;
    }

    @Override
    protected InfReasonTypesDAO DAO() {
        return (InfReasonTypesDAO)super.DAO();
    }

    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }
}
