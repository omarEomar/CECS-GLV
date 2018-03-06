package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.SpecialPeriodsDAO;
import com.beshara.csc.inf.business.entity.SpecialPeriodsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "SpecialPeriodsSession", mappedName = "Inf-Model-SpecialPeriodsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(SpecialPeriodsSession.class)
public class SpecialPeriodsSessionBean extends InfBaseSessionBean implements SpecialPeriodsSession {
    public SpecialPeriodsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return SpecialPeriodsEntity.class;
    }

    @Override
    protected SpecialPeriodsDAO DAO() {
        return (SpecialPeriodsDAO)super.DAO();
    }

    @Override
    public IBasicDTO add(IRequestInfoDTO p1, IBasicDTO dto) throws DataBaseException, SharedApplicationException {

        List existData = DAO().getAllByTypeAndMinCodeAndYear((BasicDTO)dto);
        if (existData != null && existData.size() > 0)
            throw new SharedApplicationException("duplicateYearException");
        return DAO().add(dto);

    }

    public List<IBasicDTO> getAllByTypeAndMinCode(IRequestInfoDTO ri, BasicDTO dto) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getAllByTypeAndMinCode(dto);
    }
}
