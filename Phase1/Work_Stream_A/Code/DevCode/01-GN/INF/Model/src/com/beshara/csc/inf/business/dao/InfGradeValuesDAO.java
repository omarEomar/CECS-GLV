package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfGradeValuesDTO;
import com.beshara.csc.inf.business.dto.InfGradeTypesDTO;
import com.beshara.csc.inf.business.entity.IInfGradeTypesEntityKey;
import com.beshara.csc.inf.business.entity.IInfGradeValuesEntityKey;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.InfGradeValuesEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class InfGradeValuesDAO extends InfBaseDAO {
    public InfGradeValuesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfGradeValuesDAO();
    }

    @Override
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGradeValuesEntityKey id = (IInfGradeValuesEntityKey)id1;
            InfGradeValuesEntity gradeValuesEntity = EM().find(InfGradeValuesEntity.class, id);
            if (gradeValuesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfGradeValuesDTO gradeValuesDTO = InfEntityConvertr.getInfGradeValuesDTO(gradeValuesEntity);
            return gradeValuesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    public IInfGradeValuesDTO add(IBasicDTO infGradeValuesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfGradeValuesEntity infGradeValuesEntity = new InfGradeValuesEntity();
            IInfGradeValuesDTO infGradeValuesDTO = (IInfGradeValuesDTO)infGradeValuesDTO1;


            InfGradeTypesDTO infGradeTypesDTO = (InfGradeTypesDTO)infGradeValuesDTO.getGradeTypesDTO();
            InfGradeTypesEntity gradeTypesEntity =
                EM().find(InfGradeTypesEntity.class, (IInfGradeTypesEntityKey)infGradeTypesDTO.getCode());

            infGradeValuesEntity.setGradeTypeEntity(gradeTypesEntity);
            infGradeValuesEntity.setValue(infGradeValuesDTO.getValue());
            infGradeValuesEntity.setGradeTypeCode(infGradeValuesDTO.getGradeTypeCode());
            infGradeValuesEntity.setPercentageValue(infGradeValuesDTO.getPercentageValue());
            doAdd(infGradeValuesEntity);
            return infGradeValuesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    @Override
    public List<IInfGradeValuesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGradeValuesEntity> list =
                EM().createNamedQuery("InfGradeValuesEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            if (list == null && list.size() == 0) {
                throw new NoResultException();
            }
            for (InfGradeValuesEntity grade : list) {
                arrayList.add(InfEntityConvertr.getInfGradeValuesDTO(grade));
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


    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            name = "%" + name + "%";
            List<InfGradeValuesEntity> list =
                EM().createNamedQuery("InfGradeValuesEntity.searchByName").setParameter("value", name).getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (InfGradeValuesEntity entity : list) {
                arrayList.add(InfEntityConvertr.getInfGradeValuesDTO(entity));
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

    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGradeValuesEntity> list =
                EM().createNamedQuery("InfGradeValuesEntity.searchByCode").setParameter("gradeTypeCode",
                                                                                        (Long)code).getResultList();
            for (InfGradeValuesEntity entity : list) {
                arrayList.add(InfEntityConvertr.getInfGradeValuesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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
    public List<IBasicDTO> getAllByTypeCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGradeValuesEntity> list =
                EM().createNamedQuery("InfGradeValuesEntity.getAllByTypeCode").setParameter("gradeTypeCode",
                                                                                            (Long)code).getResultList();
            for (InfGradeValuesEntity entity : list) {
                arrayList.add(InfEntityConvertr.getInfGradeValuesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

}
