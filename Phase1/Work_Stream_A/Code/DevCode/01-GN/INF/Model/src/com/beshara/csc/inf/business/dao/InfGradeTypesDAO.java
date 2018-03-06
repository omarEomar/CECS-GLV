package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfGradeTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfGradeTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class InfGradeTypesDAO extends InfBaseDAO {
    public InfGradeTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfGradeTypesDAO();
    }

    public List<IInfGradeTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGradeTypesEntity> list =
                EM().createNamedQuery("InfGradeTypesEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            if (list != null && list.size() == 0) {
                throw new NoResultException();
            }
            for (InfGradeTypesEntity grade : list) {
                arrayList.add(InfEntityConvertr.getGradeTypesDTO(grade));
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


    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfGradeTypesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            name = "%" + name + "%";
            List<InfGradeTypesEntity> list =
                EM().createNamedQuery("InfGradeTypesEntity.searchByName").setParameter("gradeTypeName",
                                                                                       name).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();

            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (InfGradeTypesEntity entity : list) {
                arrayList.add(InfEntityConvertr.getGradeTypesDTO(entity));
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

    public IBasicDTO searchByCode(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            InfGradeTypesEntity entity = EM().find(InfGradeTypesEntity.class, id);

            if (entity == null) {
                throw new ItemNotFoundException();
            }
            return InfEntityConvertr.getGradeTypesDTO(entity);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public IInfGradeTypesDTO add(IBasicDTO gradeTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfGradeTypesEntity gradeTypesEntity = new InfGradeTypesEntity();
            IInfGradeTypesDTO grdTypesDTO = (IInfGradeTypesDTO)gradeTypesDTO1;
            grdTypesDTO.setCode(InfEntityKeyFactory.createGradeTypesEntityKey(findNewId()));
            gradeTypesEntity.setGradeTypeCode(((IInfGradeTypesEntityKey)grdTypesDTO.getCode()).getGradeTypeCode());
            gradeTypesEntity.setGradeTypeName(grdTypesDTO.getName());
            gradeTypesEntity.setReferenceFlag(0L);
            gradeTypesEntity.setGradeTypeValType(grdTypesDTO.getGradeTypeValType() != null ?
                                                 new Long(grdTypesDTO.getGradeTypeValType()) : null);
            gradeTypesEntity.setFormula(grdTypesDTO.getFormula());
            gradeTypesEntity.setMaxValue(grdTypesDTO.getMaxValue());
            gradeTypesEntity.setMinValue(grdTypesDTO.getMinValue());
            doAdd(gradeTypesEntity);
            // Set PK after creation
            return grdTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO gradeTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGradeTypesDTO gradeTypesDTO = (IInfGradeTypesDTO)gradeTypesDTO1;
            InfGradeTypesEntity gradeTypesEntity = EM().find(InfGradeTypesEntity.class, gradeTypesDTO.getCode());
            gradeTypesEntity.setGradeTypeName(gradeTypesDTO.getName());
            gradeTypesEntity.setReferenceFlag(gradeTypesDTO.getReferenceFlag());
            gradeTypesEntity.setGradeTypeValType(gradeTypesDTO.getGradeTypeValType() != null ?
                                                 new Long(gradeTypesDTO.getGradeTypeValType()) : null);
            gradeTypesEntity.setFormula(gradeTypesDTO.getFormula());
            gradeTypesEntity.setMinValue(gradeTypesDTO.getMinValue());
            gradeTypesEntity.setMaxValue(gradeTypesDTO.getMaxValue());
            gradeTypesEntity.setCreatedBy(gradeTypesDTO.getCreatedBy());
            gradeTypesEntity.setCreatedDate(gradeTypesDTO.getCreatedDate());
            gradeTypesEntity.setLastUpdatedBy(gradeTypesDTO.getLastUpdatedBy());
            gradeTypesEntity.setLastUpdatedDate(gradeTypesDTO.getLastUpdatedDate());
            gradeTypesEntity.setAuditStatus(gradeTypesDTO.getAuditStatus());
            doUpdate(gradeTypesEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean remove(IBasicDTO gradeTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfGradeTypesDTO gradeTypessDTO = (IInfGradeTypesDTO)gradeTypesDTO1;
            InfGradeTypesEntity gradeTypesEntity = EM().find(InfGradeTypesEntity.class, gradeTypessDTO.getCode());
            if (gradeTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(gradeTypesEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    //searchByCode

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfGradeTypesEntity> list =
                EM().createNamedQuery("InfGradeTypesEntity.searchByCode").setParameter("gradeTypeCode",
                                                                                       code).getResultList();
            for (InfGradeTypesEntity entity : list) {
                arrayList.add(InfEntityConvertr.getGradeTypesDTO(entity));
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

    /**
     * get formula from coulm percentage in table gradeType
     * @param gradeValue
     * @param gradeTypeCode
     * @return percentageValue
     * @auther @Abd elsabour && Kareem Sayed
     */
    public Double getFormulaByGradeType(Long gradeTypeCode, String gradeValue) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            String formula = "";
            StringBuilder nativeQuery =
                new StringBuilder("select REPLACE(INF_GRADE_TYPES.CHANGE_TO_PRECENTAGE_FORMULA,'X','");
            nativeQuery.append(gradeValue + "') from INF_GRADE_TYPES where INF_GRADE_TYPES.GRDTYPE_CODE=");
            nativeQuery.append(gradeTypeCode);
            Query query = EM().createNativeQuery(nativeQuery.toString());
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                formula = query.getResultList().get(0).toString();
            }
            formula = formula.replace("[", "");
            formula = formula.replace("]", "");
            StringBuilder nativeQuery2 = new StringBuilder("select " + formula + " from dual");
            Object obj = EM().createNativeQuery(nativeQuery2.toString()).getSingleResult();
            if (obj != null) {
                Vector v = (Vector)obj;
                return ((BigDecimal)v.get(0)).doubleValue();
            }
            return null;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> checkDublicateName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List<InfGradeTypesEntity> list =
                EM().createNamedQuery("InfGradeTypesEntity.checkDuplicatName").setParameter("gradeTypeName",
                                                                                            name).getResultList();
            ArrayList arrayList = new ArrayList();
            for (InfGradeTypesEntity gradeTypesEntity : list) {
                arrayList.add(InfDTOFactory.createGradeTypesDTO(gradeTypesEntity));
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
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("KwStreetsEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                      "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createKwStreetsDTO((KwStreetsEntity)list.get(0)));
            }
            return null;
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
