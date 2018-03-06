package com.beshara.csc.inf.business.deploy;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.BloodGroupsDAO;
import com.beshara.csc.inf.business.dao.UnitsOfMeasureDAO;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;

import com.beshara.csc.inf.business.entity.UnitsOfMeasureEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
@Stateless(name = "UnitsOfMeasureSession", mappedName = "Inf-Model-UnitsOfMeasureSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote

public class UnitsOfMeasureSessionBean extends InfBaseSessionBean implements UnitsOfMeasureSession { 
    
    public UnitsOfMeasureSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return UnitsOfMeasureEntity.class;
    }

    @Override
    protected UnitsOfMeasureDAO DAO() {
        return (UnitsOfMeasureDAO)super.DAO();
    }
    
    public List<IBasicDTO> getUnitsOfMeasureCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getUnitsOfMeasureCodeName();
    }

}
