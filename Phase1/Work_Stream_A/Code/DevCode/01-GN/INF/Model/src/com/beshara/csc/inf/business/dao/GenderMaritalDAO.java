package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IGenderMaritalDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GenderMaritalEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IGenderMaritalEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntity;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class GenderMaritalDAO extends InfBaseDAO {
    public GenderMaritalDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new GenderMaritalDAO();
    }

    public List<IGenderMaritalDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderMaritalEntity> list =
                EM().createNamedQuery("GenderMaritalEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (GenderMaritalEntity genderMarital : list) {
                arrayList.add(InfDTOFactory.createGenderMaritalDTO(genderMarital));
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

    public IGenderMaritalDTO add(IBasicDTO genderMaritalDTO1) throws DataBaseException, SharedApplicationException {
        try {
            GenderMaritalEntity genderMaritalEntity = new GenderMaritalEntity();
            IGenderMaritalDTO genderMaritalDTO = (IGenderMaritalDTO)genderMaritalDTO1;
            if (genderMaritalDTO.getGentypeCode() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (new GenderTypesEntityKey(genderMaritalDTO.getGentypeCode())));
                if (genderTypesEntity != null)
                    genderMaritalEntity.setGenderTypesEntity(genderTypesEntity);
                else
                    throw new ItemNotFoundException();
            }
            if (genderMaritalDTO.getMarstatusCode() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, (new MaritalStatusEntityKey(genderMaritalDTO.getMarstatusCode())));
                if (maritalStatusEntity != null)
                    genderMaritalEntity.setMaritalStatusEntity(maritalStatusEntity);
                else
                    throw new ItemNotFoundException();
            }
            genderMaritalEntity.setGentypeCode(((IGenderMaritalEntityKey)genderMaritalDTO.getCode()).getGentypeCode());
            genderMaritalEntity.setMarstatusCode(((IGenderMaritalEntityKey)genderMaritalDTO.getCode()).getMarstatusCode());
            genderMaritalEntity.setGenmarName(genderMaritalDTO.getGenmarName());
            genderMaritalEntity.setCreatedBy(genderMaritalDTO.getCreatedBy());
            genderMaritalEntity.setCreatedDate(genderMaritalDTO.getCreatedDate());
            genderMaritalEntity.setAuditStatus(genderMaritalDTO.getAuditStatus());
            genderMaritalEntity.setTabrecSerial(genderMaritalDTO.getTabrecSerial());
            doAdd(genderMaritalEntity);
            return genderMaritalDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO genderMaritalDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderMaritalDTO genderMaritalDTO = (IGenderMaritalDTO)genderMaritalDTO1;
            GenderMaritalEntity genderMaritalEntity2 = new GenderMaritalEntity();
            if (genderMaritalDTO.getGentypeCode() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (new GenderTypesEntityKey(genderMaritalDTO.getGentypeCode())));
                if (genderTypesEntity != null)
                    genderMaritalEntity2.setGenderTypesEntity(genderTypesEntity);
                else
                    throw new ItemNotFoundException();
            }
            if (genderMaritalDTO.getMarstatusCode() != null) {
                MaritalStatusEntity maritalStatusEntity =
                    EM().find(MaritalStatusEntity.class, (new MaritalStatusEntityKey(genderMaritalDTO.getMarstatusCode())));
                if (maritalStatusEntity != null)
                    genderMaritalEntity2.setMaritalStatusEntity(maritalStatusEntity);
                else
                    throw new ItemNotFoundException();
            }
            GenderMaritalEntity genderMaritalEntity = EM().find(GenderMaritalEntity.class, genderMaritalDTO.getCode());
            genderMaritalEntity.setGentypeCode(((IGenderMaritalEntityKey)genderMaritalDTO.getCode()).getGentypeCode());
            genderMaritalEntity.setMarstatusCode(((IGenderMaritalEntityKey)genderMaritalDTO.getCode()).getMarstatusCode());
            genderMaritalEntity.setGenmarName(genderMaritalDTO.getGenmarName());
            genderMaritalEntity.setCreatedBy(genderMaritalDTO.getCreatedBy());
            genderMaritalEntity.setCreatedDate(genderMaritalDTO.getCreatedDate());
            genderMaritalEntity.setLastUpdatedBy(genderMaritalDTO.getLastUpdatedBy());
            genderMaritalEntity.setLastUpdatedDate(genderMaritalDTO.getLastUpdatedDate());
            genderMaritalEntity.setAuditStatus(genderMaritalDTO.getAuditStatus());
            genderMaritalEntity.setTabrecSerial(genderMaritalDTO.getTabrecSerial());
            doUpdate(genderMaritalEntity);
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


    public Boolean remove(IBasicDTO genderMaritalDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderMaritalDTO genderMaritalDTO = (IGenderMaritalDTO)genderMaritalDTO1;
            GenderMaritalEntity genderMaritalEntity = EM().find(GenderMaritalEntity.class, genderMaritalDTO.getCode());
            if (genderMaritalEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(genderMaritalEntity);
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

    public IGenderMaritalDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderMaritalEntityKey id = (IGenderMaritalEntityKey)id1;
            GenderMaritalEntity genderMaritalEntity = EM().find(GenderMaritalEntity.class, id);
            if (genderMaritalEntity == null) {
                throw new ItemNotFoundException();
            }
            IGenderMaritalDTO genderMaritalDTO = InfDTOFactory.createGenderMaritalDTO(genderMaritalEntity);
            return genderMaritalDTO;
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
            Long id = (Long)EM().createNamedQuery("GenderMaritalEntity.findNewId").getSingleResult();
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


    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderMaritalEntity> list =
                EM().createNamedQuery("GenderMaritalEntity.searchByCode").setParameter("marstatusCode",
                                                                                       code).setHint("toplink.refresh",
                                                                                                     "true").getResultList();
            for (GenderMaritalEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderMaritalDTO(entity));
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

    public List<IBasicDTO> searchByGenMarCode(Object genTypeCode, Object marstatusCode) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderMaritalEntity> list =
                EM().createNamedQuery("GenderMaritalEntity.searchByGenMarCode").setParameter("gentypeCode",
                                                                                             genTypeCode).setParameter("marstatusCode",
                                                                                                                       marstatusCode).setHint("toplink.refresh",
                                                                                                                                              "true").getResultList();
            for (GenderMaritalEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderMaritalDTO(entity));
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
