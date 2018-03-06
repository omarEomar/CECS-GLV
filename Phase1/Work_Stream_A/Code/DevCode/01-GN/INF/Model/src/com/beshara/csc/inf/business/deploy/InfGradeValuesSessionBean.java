package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfGradeValuesDAO;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfGradeValuesSession", mappedName = "Inf-Model-InfGradeValuesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote

public class InfGradeValuesSessionBean extends InfBaseSessionBean implements InfGradeValuesSession {

    public InfGradeValuesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfGradeValuesEntity.class;
    }

    @Override
    protected InfGradeValuesDAO DAO() {
        return (InfGradeValuesDAO)super.DAO();
    }

    public List<IBasicDTO> getAllByTypeCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                    SharedApplicationException {
        return DAO().getAllByTypeCode(code);
    }
}
