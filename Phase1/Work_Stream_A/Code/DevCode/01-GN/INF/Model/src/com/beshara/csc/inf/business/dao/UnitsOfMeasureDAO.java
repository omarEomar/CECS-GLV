package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IUnitsOfMeasureEntityKey;
import com.beshara.csc.inf.business.entity.UnitsOfMeasureEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

public class UnitsOfMeasureDAO extends InfBaseDAO {
    public UnitsOfMeasureDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new UnitsOfMeasureDAO();
    }

    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<UnitsOfMeasureEntity> list =
                EM().createNamedQuery("UnitsOfMeasureEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (UnitsOfMeasureEntity unitOfmeasureEntity : list) {
                arrayList.add(InfEntityConverter.getUnitsOfMeasureDTO(unitOfmeasureEntity));
            }
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> getUnitsOfMeasureCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("UnitsOfMeasureEntity.findUnitsOfMeasureCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


}
