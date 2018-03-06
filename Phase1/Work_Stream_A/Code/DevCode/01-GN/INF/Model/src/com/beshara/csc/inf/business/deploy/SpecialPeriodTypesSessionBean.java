package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.SpecialPeriodTypesDAO;
import com.beshara.csc.inf.business.entity.SpecialPeriodTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "SpecialPeriodTypesSession", mappedName = "Inf-Model-SpecialPeriodTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SpecialPeriodTypesSession.class)
public class SpecialPeriodTypesSessionBean extends InfBaseSessionBean implements SpecialPeriodTypesSession{
    public SpecialPeriodTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SpecialPeriodTypesEntity.class;
    }
    @Override
    protected SpecialPeriodTypesDAO DAO() {
        return (SpecialPeriodTypesDAO)super.DAO();
    }
    
    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    
}
