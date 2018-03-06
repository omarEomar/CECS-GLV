package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IPersonQualificationsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.nl.qul.business.entity.ICentersEntityKey;
import com.beshara.csc.nl.qul.business.entity.IQualificationsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class PersonQualificationsDAO extends InfBaseDAO {
    public PersonQualificationsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonQualificationsDAO();
    }

    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            PersonQualificationsEntity personQualificationsEntity =
                EM().find(PersonQualificationsEntity.class, (IPersonQualificationsEntityKey)id);
            if (personQualificationsEntity == null) {
                throw new ItemNotFoundException();
            }
            IPersonQualificationsDTO personQualificationsDTO =
                InfDTOFactory.createPersonQualificationsDTO(personQualificationsEntity);
            return personQualificationsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonQualificationsEntity> list =
                EM().createNamedQuery("PersonQualificationsEntity.findAll").setHint("toplink.refresh",
                                                                                    "true").getResultList();
            for (PersonQualificationsEntity personQualificationsEntity : list) {
                arrayList.add(InfDTOFactory.createPersonQualificationsDTO(personQualificationsEntity));
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

    public List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonQualificationsEntity> list =
                EM().createNamedQuery("PersonQualificationsEntity.findAllByCivilId").setParameter("civilId",
                                                                                                  civilId).getResultList();
            for (PersonQualificationsEntity personQualificationsEntity : list) {
                arrayList.add(InfDTOFactory.createPersonQualificationsDTO(personQualificationsEntity));
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

    public IBasicDTO add(IBasicDTO personQualificationsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
            PersonQualificationsEntity personQualificationsEntity = new PersonQualificationsEntity();
            if (personQualificationsDTO.getKwtCitizensResidentsDTO() != null) {
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, personQualificationsDTO.getKwtCitizensResidentsDTO().getCode());
                if (kwtCitizensResidentsEntity == null)
                    throw new ItemNotFoundException();
                personQualificationsEntity.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (personQualificationsDTO.getQualificationsDTO() != null) {
                IQualificationsEntityKey entityKey =
                    (IQualificationsEntityKey)personQualificationsDTO.getQualificationsDTO().getCode();
                try {
                    personQualificationsEntity.setQualificationKey(entityKey.getQualificationKey().toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            //        if (personQualificationsDTO.getQualificationsDTO() != null) {
            //            QualificationsEntity qualificationsEntity =
            //                em.find(QualificationsEntity.class, personQualificationsDTO.getQualificationsDTO().getCode());
            //            if (qualificationsEntity == null)
            //                throw new  ();
            //            personQualificationsEntity.setQualificationsEntity(qualificationsEntity);
            //        } else {
            //            throw new  ();
            //        }
            if (personQualificationsDTO.getCentersDTO() != null) {
                ICentersEntityKey entityKey = (ICentersEntityKey)personQualificationsDTO.getCentersDTO().getCode();
                try {
                    personQualificationsEntity.setCenterCode(entityKey.getCenterCode());
                } catch (Exception e) {
                    personQualificationsEntity.setCenterCode(null);
                }
            }
            //        if (personQualificationsDTO.getCentersDTO() != null) {
            //            CentersEntity centersEntity =
            //                em.find(CentersEntity.class, (ICentersEntityKey)personQualificationsDTO.getCentersDTO().getCode());
            //            if (centersEntity == null)
            //                throw new  ();
            //            personQualificationsEntity.setCentersEntity(centersEntity);
            //        } else {
            //            personQualificationsEntity.setCentersEntity(null);
            //        }
            if (personQualificationsDTO.getGradeTypeDto() != null) {
                InfGradeTypesEntity gradeTypesEntity =
                    EM().find(InfGradeTypesEntity.class, personQualificationsDTO.getGradeTypeDto().getCode());
                if (gradeTypesEntity == null)
                    throw new ItemNotFoundException();
                personQualificationsEntity.setGradeTypeEntity(gradeTypesEntity);
            } else {
                personQualificationsEntity.setGradeTypeEntity(null);
            }
            personQualificationsEntity.setGradeValue(personQualificationsDTO.getGradeValue());
            personQualificationsEntity.setQualificationDegree(personQualificationsDTO.getQualificationDegree());
            personQualificationsEntity.setQualificationDate(personQualificationsDTO.getQualificationDate());
            personQualificationsEntity.setCreatedBy(personQualificationsDTO.getCreatedBy());
            personQualificationsEntity.setCreatedDate(personQualificationsDTO.getCreatedDate());
            personQualificationsEntity.setCrsRegistrationOrder(personQualificationsDTO.getCrsRegistrationOrder());

            if (personQualificationsDTO.getCurrentQual() != null) {
                personQualificationsEntity.setCurrentQual(personQualificationsDTO.getCurrentQual());
            } else {
                personQualificationsEntity.setCurrentQual(0L);
            }
            
            doAdd(personQualificationsEntity);
            if (personQualificationsEntity.getQualificationsEntity() != null) {
                personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(personQualificationsEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                                                        String.valueOf(personQualificationsEntity.getQualificationsEntity().getQualificationKey())));
            } else {
                personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(personQualificationsEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                                                        String.valueOf(personQualificationsEntity.getQualificationKey())));
            }
            return personQualificationsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO personQualificationsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonQualificationsDTO personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsDTO1;
            PersonQualificationsEntity personQualificationsEntity =
                EM().find(PersonQualificationsEntity.class, (IPersonQualificationsEntityKey)personQualificationsDTO.getCode());
            //        if (personQualificationsDTO.getCentersDTO() != null) {
            //            CentersEntity centersEntity =
            //                em.find(CentersEntity.class, (ICentersEntityKey)personQualificationsDTO.getCentersDTO().getCode());
            //            if (centersEntity == null)
            //                throw new  ();
            //            personQualificationsEntity.setCentersEntity(centersEntity);
            //        } else {
            //            personQualificationsEntity.setCentersEntity(null);
            //        }
            if (personQualificationsDTO.getCentersDTO() != null) {
                ICentersEntityKey entityKey = (ICentersEntityKey)personQualificationsDTO.getCentersDTO().getCode();
                try {
                    personQualificationsEntity.setCenterCode(entityKey.getCenterCode());
                } catch (Exception e) {
                    personQualificationsEntity.setCenterCode(null);
                }
            }
            if (personQualificationsDTO.getGradeTypeDto() != null) {
                InfGradeTypesEntity gradeTypesEntity =
                    EM().find(InfGradeTypesEntity.class, personQualificationsDTO.getGradeTypeDto().getCode());
                if (gradeTypesEntity == null)
                    throw new ItemNotFoundException();
                personQualificationsEntity.setGradeTypeEntity(gradeTypesEntity);
            } else {
                personQualificationsEntity.setGradeTypeEntity(null);
            }
            personQualificationsEntity.setGradeValue(personQualificationsDTO.getGradeValue());
            personQualificationsEntity.setQualificationDegree(personQualificationsDTO.getQualificationDegree());
            personQualificationsEntity.setQualificationDate(personQualificationsDTO.getQualificationDate());
            personQualificationsEntity.setCreatedBy(personQualificationsDTO.getCreatedBy());
            personQualificationsEntity.setCreatedDate(personQualificationsDTO.getCreatedDate());
            personQualificationsEntity.setCrsRegistrationOrder(personQualificationsDTO.getCrsRegistrationOrder());
			personQualificationsEntity.setCurrentQual(personQualificationsDTO.getCurrentQual());
            doUpdate(personQualificationsEntity);
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
     * Remove an Existing PersonQualificationsEntity * @param IPersonQualificationsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO personQualificationsDTO) throws DataBaseException, SharedApplicationException {
        try {
            PersonQualificationsEntity personQualificationsEntity =
                EM().find(PersonQualificationsEntity.class, (IPersonQualificationsEntityKey)personQualificationsDTO.getCode());
            if (personQualificationsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(personQualificationsEntity);
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

    public IBasicDTO getCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonQualificationsEntity.getCentralEmpPersonQul");
            query.setParameter("civilId", civilId);
            List<PersonQualificationsEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            return InfDTOFactory.createPersonQualificationsDTO(list.get(0));
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> listAvailableEntities(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                EM().createNamedQuery("PersonQualificationsEntity.listAvailabe").setParameter("civilId",
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

    public IBasicDTO getLastPersonQualification(IEntityKey civilKey) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonQualificationsEntity.getLastPersonQualification");
            query.setParameter("civilId", ((IKwtCitizensResidentsEntityKey)civilKey).getCivilId());
            List<PersonQualificationsEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            return InfDTOFactory.createPersonQualificationsDTO(list.get(0));
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public IBasicDTO getCurrentCentralEmpPersonQul(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonQualificationsEntity.getCurrentCentralEmpPersonQul");
            query.setParameter("civilId", civilId);
            List<PersonQualificationsEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            return InfDTOFactory.createPersonQualificationsDTO(list.get(0));
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
