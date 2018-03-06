package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IGenderReligionDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GenderReligionEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntity;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IGenderReligionEntityKey;
import com.beshara.csc.inf.business.entity.ReligionsEntity;
import com.beshara.csc.inf.business.entity.ReligionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class GenderReligionDAO extends InfBaseDAO {
    public GenderReligionDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new GenderReligionDAO();
    }

    public List<IGenderReligionDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderReligionEntity> list =
                EM().createNamedQuery("GenderReligionEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (GenderReligionEntity genderReligion : list) {
                arrayList.add(InfDTOFactory.createGenderReligionDTO(genderReligion));
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

    /**
     * Create a New GenderReligionEntity * @param genderReligionDTO
     * @return IGenderReligionDTO
     */
    public IGenderReligionDTO add(IBasicDTO genderReligionDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)genderReligionDTO1;
            GenderReligionEntity genderReligionEntity = new GenderReligionEntity();
            if (genderReligionDTO.getGentypeCode() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (new GenderTypesEntityKey(genderReligionDTO.getGentypeCode())));
                if (genderTypesEntity != null)
                    genderReligionEntity.setGenderTypesEntity(genderTypesEntity);
                else
                    throw new ItemNotFoundException();
            }
            if (genderReligionDTO.getReligionCode() != null) {
                ReligionsEntity religionsEntity =
                    EM().find(ReligionsEntity.class, (new ReligionsEntityKey(genderReligionDTO.getReligionCode())));
                if (religionsEntity != null)
                    genderReligionEntity.setReligionsEntity(religionsEntity);
                else
                    throw new ItemNotFoundException();
            }
            genderReligionEntity.setGentypeCode(((IGenderReligionEntityKey)genderReligionDTO.getCode()).getGentypeCode());
            genderReligionEntity.setReligionCode(((IGenderReligionEntityKey)genderReligionDTO.getCode()).getReligionCode());
            genderReligionEntity.setGenregName(genderReligionDTO.getGenregName());
            genderReligionEntity.setCreatedBy(genderReligionDTO.getCreatedBy());
            genderReligionEntity.setCreatedDate(genderReligionDTO.getCreatedDate());
            genderReligionEntity.setAuditStatus(genderReligionDTO.getAuditStatus());
            genderReligionEntity.setTabrecSerial(genderReligionDTO.getTabrecSerial());
            doAdd(genderReligionEntity);
            // Set PK after creation
            return genderReligionDTO;
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
     * Update an Existing GenderReligionEntity * @param genderReligionDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO genderReligionDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)genderReligionDTO1;

            GenderReligionEntity genderReligionEntity2 = new GenderReligionEntity();
            if (genderReligionDTO.getGentypeCode() != null) {
                GenderTypesEntity genderTypesEntity =
                    EM().find(GenderTypesEntity.class, (new GenderTypesEntityKey(genderReligionDTO.getGentypeCode())));
                if (genderTypesEntity != null)
                    genderReligionEntity2.setGenderTypesEntity(genderTypesEntity);
                else
                    throw new ItemNotFoundException();
            }

            if (genderReligionDTO.getReligionCode() != null) {
                ReligionsEntity religionsEntity =
                    EM().find(ReligionsEntity.class, (new ReligionsEntityKey(genderReligionDTO.getReligionCode())));
                if (religionsEntity != null)
                    genderReligionEntity2.setReligionsEntity(religionsEntity);
                else
                    throw new ItemNotFoundException();
            }
            GenderReligionEntity genderReligionEntity =
                EM().find(GenderReligionEntity.class, genderReligionDTO.getCode());
            genderReligionEntity.setGentypeCode(((IGenderReligionEntityKey)genderReligionDTO.getCode()).getGentypeCode());
            genderReligionEntity.setReligionCode(((IGenderReligionEntityKey)genderReligionDTO.getCode()).getReligionCode());
            genderReligionEntity.setGenregName(genderReligionDTO.getGenregName());
            genderReligionEntity.setCreatedBy(genderReligionDTO.getCreatedBy());
            genderReligionEntity.setCreatedDate(genderReligionDTO.getCreatedDate());
            genderReligionEntity.setLastUpdatedBy(genderReligionDTO.getLastUpdatedBy());
            genderReligionEntity.setLastUpdatedDate(genderReligionDTO.getLastUpdatedDate());
            genderReligionEntity.setAuditStatus(genderReligionDTO.getAuditStatus());
            genderReligionEntity.setTabrecSerial(genderReligionDTO.getTabrecSerial());
            doUpdate(genderReligionEntity);
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

    /**
     * Remove an Existing GenderReligionEntity * @param genderReligionDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO genderReligionDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)genderReligionDTO1;
            GenderReligionEntity genderReligionEntity =
                EM().find(GenderReligionEntity.class, genderReligionDTO.getCode());
            if (genderReligionEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(genderReligionEntity);
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

    /**
     * Get GenderReligionEntity By Primary Key * @param id
     * @return IGenderReligionDTO
     */
    public IGenderReligionDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IGenderReligionEntityKey id = (IGenderReligionEntityKey)id1;
            GenderReligionEntity genderReligionEntity = EM().find(GenderReligionEntity.class, id);
            if (genderReligionEntity == null) {
                throw new ItemNotFoundException();
            }
            IGenderReligionDTO genderReligionDTO = InfDTOFactory.createGenderReligionDTO(genderReligionEntity);
            return genderReligionDTO;
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
            Long id = (Long)EM().createNamedQuery("GenderReligionEntity.findNewId").getSingleResult();
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

    public List<IBasicDTO> searchByGenRegCode(Object genTypeCode, Object religionsCode) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderReligionEntity> list =
                EM().createNamedQuery("GenderReligionEntity.searchByGenRegCode").setParameter("gentypeCode",
                                                                                              (Long)genTypeCode).setParameter("religionCode",
                                                                                                                              (Long)religionsCode).setHint("toplink.refresh",
                                                                                                                                                           "true").getResultList();
            for (GenderReligionEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderReligionDTO(entity));
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

    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderReligionEntity> list =
                EM().createNamedQuery("GenderReligionEntity.searchByCode").setParameter("religionCode",
                                                                                        (Long)code).setHint("toplink.refresh",
                                                                                                            "true").getResultList();
            for (GenderReligionEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderReligionDTO(entity));
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

    public List<IBasicDTO> getRelatedGenders(Long code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<GenderReligionEntity> list =
                EM().createNamedQuery("GenderReligionEntity.getRelatedGenders").setParameter("religionCode",
                                                                                             (Long)code).setHint("toplink.refresh",
                                                                                                                 "true").getResultList();
            for (GenderReligionEntity entity : list) {
                arrayList.add(InfDTOFactory.createGenderReligionDTO(entity));
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
