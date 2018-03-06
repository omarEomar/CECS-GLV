package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.PersonDocumntsDTO;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class PersonDocumntsDAO extends InfBaseDAO {
    public PersonDocumntsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonDocumntsDAO();
    }

    /**<code>select o from PersonDocumntsEntity o</code>.
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonDocumntsEntity.findAll");
            List<PersonDocumntsEntity> entList = query.getResultList();
            if (entList == null || entList.size() <= 0) {
                throw new NoResultException();
            }
            List<PersonDocumntsDTO> dtoList = new ArrayList<PersonDocumntsDTO>();
            for (PersonDocumntsEntity ent : entList) {
                dtoList.add(InfEntityConverter.getPersonDocumntsDTO(ent));
            }
            return (List)dtoList;
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
    public PersonDocumntsDTO add(IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException {

        try {
            PersonDocumntsDTO dto = (PersonDocumntsDTO)dtoParam;
            PersonDocumntsEntity ent = new PersonDocumntsEntity();
            if (dto.getKwtCitizensResidentsDTO() != null) {
                IEntityKey ek =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(dto.getKwtCitizensResidentsDTO().getCivilId());
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, ek);
                ent.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
                ent.setEmpDocSerial(findNewIdByCivilID(dto.getKwtCitizensResidentsDTO().getCivilId()));
            }
            if (dto.getInfDocumentTypesDTO() != null) {
                InfDocumentTypesEntityKey ek = (InfDocumentTypesEntityKey)dto.getInfDocumentTypesDTO().getCode();

                InfDocumentTypesEntity infDocumentTypesEntity = EM().find(InfDocumentTypesEntity.class, ek);
                ent.setInfDocumentTypesEntity(infDocumentTypesEntity);

            }

            ent.setStatus(dto.getStatus());
            ent.setComments(dto.getComments());
            ent.setReferenceTableName(dto.getReferenceTableName());
            ent.setReferenceTableSerial(dto.getReferenceTableSerial());
            doAdd(ent);

            return InfEntityConverter.getPersonDocumntsDTO(ent);
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long findNewIdByCivilID(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonDocumntsEntity.getMaxId");
            query.setParameter("civilId", civilID);
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

    public List<IBasicDTO> getAllByCivilAndDocType(Long civilId, String doctypeCode) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonDocumntsEntity.getAllByCivilAndDocType");
            query.setParameter("civilId", civilId);
            query.setParameter("doctypeCode", doctypeCode);
            List<PersonDocumntsEntity> entList = query.getResultList();
            List<PersonDocumntsDTO> dtoList = new ArrayList<PersonDocumntsDTO>();
            for (PersonDocumntsEntity ent : entList) {
                dtoList.add(InfEntityConverter.getPersonDocumntsDTO(ent));
            }
            return (List)dtoList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                e.printStackTrace();
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
}
