package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ISeekerLanguageSkillsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.ILanguagesEntityKey;
import com.beshara.csc.inf.business.entity.ISeekerLanguageSkillsEntityKey;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.inf.business.entity.SeekerLanguageSkillsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class SeekerLanguageSkillsDAO extends InfBaseDAO {
    public SeekerLanguageSkillsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SeekerLanguageSkillsDAO();
    }

    /**<code>select o from SeekerLanguageSkillsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<ISeekerLanguageSkillsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SeekerLanguageSkillsEntity> list =
                EM().createNamedQuery("SeekerLanguageSkillsEntity.findAll").setHint("toplink.refresh",
                                                                                    "true").getResultList();
            for (SeekerLanguageSkillsEntity seekerLanguageSkills : list) {
                arrayList.add(InfDTOFactory.createSeekerLanguageSkillsDTO(seekerLanguageSkills));
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
            Query query = EM().createNamedQuery("SeekerLanguageSkillsEntity.findNewId");
            Long id = (Long)query.getSingleResult();
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

    /**
     * Create a New SeekerLanguageSkillsEntity * @param seekerLanguageSkillsDTO
     * @return ISeekerLanguageSkillsDTO
     */
    public ISeekerLanguageSkillsDTO add(IBasicDTO seekerLanguageSkillsDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            SeekerLanguageSkillsEntity seekerLanguageSkillsEntity = new SeekerLanguageSkillsEntity();
            ISeekerLanguageSkillsDTO seekerLanguageSkillsDTO = (ISeekerLanguageSkillsDTO)seekerLanguageSkillsDTO1;
            seekerLanguageSkillsEntity.setCivilId(((ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode()).getCivilId());
            seekerLanguageSkillsEntity.setLanguageCode(((ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode()).getLanguageCode());
            seekerLanguageSkillsEntity.setSkillDegree(seekerLanguageSkillsDTO.getSkillDegree());
            seekerLanguageSkillsEntity.setCreatedBy(seekerLanguageSkillsDTO.getCreatedBy());
            seekerLanguageSkillsEntity.setCreatedDate(seekerLanguageSkillsDTO.getCreatedDate());
            seekerLanguageSkillsEntity.setAuditStatus(seekerLanguageSkillsDTO.getAuditStatus());
            seekerLanguageSkillsEntity.setTabrecSerial(seekerLanguageSkillsDTO.getTabrecSerial());
            if (seekerLanguageSkillsDTO.getKwtCitizensResidentsDTO() != null) {
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)seekerLanguageSkillsDTO.getKwtCitizensResidentsDTO().getCode());
                if (kwtCitizensResidentsEntity == null)
                    throw new FinderException();
                seekerLanguageSkillsEntity.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
            }
            if (seekerLanguageSkillsDTO.getLanguagesDTO() != null) {
                LanguagesEntity languagesEntity =
                    EM().find(LanguagesEntity.class, (ILanguagesEntityKey)seekerLanguageSkillsDTO.getLanguagesDTO().getCode());
                if (languagesEntity == null)
                    throw new FinderException();
                seekerLanguageSkillsEntity.setLanguagesEntity(languagesEntity);
            }
            doAdd(seekerLanguageSkillsEntity);
            // Set PK after creation
            return seekerLanguageSkillsDTO;
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
     * Update an Existing SeekerLanguageSkillsEntity * @param seekerLanguageSkillsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO seekerLanguageSkillsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISeekerLanguageSkillsDTO seekerLanguageSkillsDTO = (ISeekerLanguageSkillsDTO)seekerLanguageSkillsDTO1;
            SeekerLanguageSkillsEntity seekerLanguageSkillsEntity =
                EM().find(SeekerLanguageSkillsEntity.class, (ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode());
            seekerLanguageSkillsEntity.setCivilId(((ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode()).getCivilId());
            seekerLanguageSkillsEntity.setLanguageCode(((ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode()).getLanguageCode());
            seekerLanguageSkillsEntity.setSkillDegree(seekerLanguageSkillsDTO.getSkillDegree());
            seekerLanguageSkillsEntity.setCreatedBy(seekerLanguageSkillsDTO.getCreatedBy());
            seekerLanguageSkillsEntity.setCreatedDate(seekerLanguageSkillsDTO.getCreatedDate());
            seekerLanguageSkillsEntity.setLastUpdatedBy(seekerLanguageSkillsDTO.getLastUpdatedBy());
            seekerLanguageSkillsEntity.setLastUpdatedDate(seekerLanguageSkillsDTO.getLastUpdatedDate());
            seekerLanguageSkillsEntity.setAuditStatus(seekerLanguageSkillsDTO.getAuditStatus());
            seekerLanguageSkillsEntity.setTabrecSerial(seekerLanguageSkillsDTO.getTabrecSerial());
            doUpdate(seekerLanguageSkillsEntity);
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
     * Remove an Existing SeekerLanguageSkillsEntity * @param seekerLanguageSkillsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO seekerLanguageSkillsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ISeekerLanguageSkillsDTO seekerLanguageSkillsDTO = (ISeekerLanguageSkillsDTO)seekerLanguageSkillsDTO1;
            SeekerLanguageSkillsEntity seekerLanguageSkillsEntity =
                EM().find(SeekerLanguageSkillsEntity.class, (ISeekerLanguageSkillsEntityKey)seekerLanguageSkillsDTO.getCode());
            if (seekerLanguageSkillsEntity == null) {
                throw new NoResultException();
            }
            doRemove(seekerLanguageSkillsEntity);
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
     * Get SeekerLanguageSkillsEntity By Primary Key * @param id
     * @return ISeekerLanguageSkillsDTO
     */
    public ISeekerLanguageSkillsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ISeekerLanguageSkillsEntityKey id = (ISeekerLanguageSkillsEntityKey)id1;
            SeekerLanguageSkillsEntity seekerLanguageSkillsEntity = EM().find(SeekerLanguageSkillsEntity.class, id);
            if (seekerLanguageSkillsEntity == null) {
                throw new NoResultException();
            }
            ISeekerLanguageSkillsDTO seekerLanguageSkillsDTO =
                InfDTOFactory.createSeekerLanguageSkillsDTO(seekerLanguageSkillsEntity);
            return seekerLanguageSkillsDTO;
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
     * Search for SeekerLanguageSkillsEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return super.search(basicDTO);
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
     * list avialable language to an exist civilId * @param civilId
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> listAvailableEntities(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                EM().createNamedQuery("SeekerLanguageSkillsEntity.listAvailabe").setParameter("civilId",
                                                                                              civilId).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            return list;
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
