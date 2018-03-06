package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.entity.FileEntityKey;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.entity.IPersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class PersonDocAttachemntsDAO extends InfBaseDAO {
    public PersonDocAttachemntsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonDocAttachemntsDAO();
    }

    /**<code>select o from PersonDocumntsEntity o</code>.
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonDocAttachemntsEntity.findAll");
            List<PersonDocAttachemntsEntity> entList = query.getResultList();
            if (entList == null || entList.size() <= 0) {
                throw new NoResultException();
            }
            List<PersonDocAttachemntsDTO> dtoList = new ArrayList<PersonDocAttachemntsDTO>();
            for (PersonDocAttachemntsEntity ent : entList) {
                try {
                    dtoList.add(InfEntityConverter.getPersonDocAttachemntsDTO(ent));    
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    public PersonDocAttachemntsDTO add(IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException {

        try {
            PersonDocAttachemntsDTO dto = (PersonDocAttachemntsDTO)dtoParam;
            PersonDocAttachemntsEntity ent = new PersonDocAttachemntsEntity();
            Long civilID = null;
            Long empDocSerial = null;
            if (dto.getPersonDocumntsDTO() != null) {
                IEntityKey ek = dto.getPersonDocumntsDTO().getCode();
                PersonDocumntsEntity personDocumntsEntity = EM().find(PersonDocumntsEntity.class, ek);
                ent.setPersonDocumntsEntity(personDocumntsEntity);
                empDocSerial = personDocumntsEntity.getEmpDocSerial();
            }
            if (dto.getPersonDocAtchTypesDTO() != null) {
                PersonDocAtchTypesEntityKey ek = (PersonDocAtchTypesEntityKey)dto.getPersonDocAtchTypesDTO().getCode();

                PersonDocAtchTypesEntity personDocAtchTypesEntity = EM().find(PersonDocAtchTypesEntity.class, ek);
                ent.setPersonDocAtchTypesEntity(personDocAtchTypesEntity);

            }
            
            if (dto.getPersonDocumntsDTO() != null &&
                dto.getPersonDocumntsDTO().getKwtCitizensResidentsDTO() != null) {
                civilID = dto.getPersonDocumntsDTO().getKwtCitizensResidentsDTO().getCivilId();
                ent.setSerial(findNewId(civilID));
            }
            ent.setStatus(dto.getStatus());
            ent.setAttachmentDate(dto.getAttachmentDate());
            ent.setDocAtcType(dto.getDocAtcType());
            ent.setAttachmentDesc(dto.getAttachmentDesc());

            FileDTO file = dto.getFile();
            if (file == null) {
                throw new InvalidDataEntryException();
            }
            FileEntityKey fileEK = (FileEntityKey)file.getCode();
            if (fileEK == null) {
                throw new InvalidDataEntryException();
            }
            ent.setFileId(fileEK.getId());
            
            ent.setValidFrom(dto.getValidFrom());
            ent.setValidUntil(dto.getValidUntil());
            doAdd(ent);
            if(ent.getPersonDocumntsEntity() != null){
                
            }
            IPersonDocAttachemntsEntityKey mainKey =
                new PersonDocAttachemntsEntityKey(civilID, empDocSerial, ent.getSerial());
            PersonDocAttachemntsEntity addedEntity = EM().find(PersonDocAttachemntsEntity.class, mainKey);
            PersonDocAttachemntsDTO addedDTO = InfEntityConverter.getPersonDocAttachemntsDTO(addedEntity);
            addedDTO.setCode(mainKey);
            return addedDTO;
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

    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            PersonDocAttachemntsEntity ent = EM().find(PersonDocAttachemntsEntity.class, id);
            if (ent == null) {
                throw new ItemNotFoundException();
            }
            return InfEntityConverter.getPersonDocAttachemntsDTO(ent);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long findNewId(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("PersonDocAttachemntsEntity.getMaxId");
            query.setParameter("civilId", civilId);
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
            Query query = EM().createNamedQuery("PersonDocAttachemntsEntity.getAllByCivilAndDocType");
            query.setParameter("civilId", civilId);
            query.setParameter("doctypeCode", doctypeCode);
            List<PersonDocAttachemntsEntity> entList = query.getResultList();
            if (entList == null || entList.size() <= 0) {
                throw new NoResultException();
            }
            List<PersonDocAttachemntsDTO> dtoList = new ArrayList<PersonDocAttachemntsDTO>();
            for (PersonDocAttachemntsEntity ent : entList) {
                try {
                    dtoList.add(InfEntityConverter.getPersonDocAttachemntsDTO(ent));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
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
    
    /**
     * Added By H.Omar @ 07/08/2016
     * @param personDocAttachemntsDTO
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */

    public Boolean remove(IBasicDTO personDocAttachemntsDTO) throws DataBaseException, SharedApplicationException {
        try {
            PersonDocAttachemntsEntity personDocAttachemntsEntity = null;
            if (personDocAttachemntsDTO.getCode() != null) {
                personDocAttachemntsEntity =
                        EM().find(PersonDocAttachemntsEntity.class, personDocAttachemntsDTO.getCode());
            }
            if (personDocAttachemntsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(personDocAttachemntsEntity);
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
     * Search All Documents
     * @return List
     * @author Amr Abdel Halim
     * @since 16-NOV-2017
     * */
    public List<IBasicDTO> searchAllDocuments(IBasicDTO _searchDTO) throws DataBaseException, SharedApplicationException {
        try {
            PersonDocAttachemntsDTO searchDTO = (PersonDocAttachemntsDTO)_searchDTO;
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            StringBuilder sql = new StringBuilder(" SELECT PDA.* FROM INF_PERSON_DOC_ATTACHMENTS PDA , INF_PERSON_DOCUMENTS PD");
            sql.append(" WHERE PDA.CIVIL_ID = PD.CIVIL_ID");
            sql.append(" AND PDA.EMPDOC_SERIAL = PD.EMPDOC_SERIAL");
            if(searchDTO.getCivilId() != null){
                sql.append(" AND PDA.CIVIL_ID = '" + searchDTO.getCivilId() +"'");    
            }
            if(searchDTO.getDocumentType() != null){
                sql.append(" AND PD.DOCTYPE_CODE = '" + searchDTO.getDocumentType() +"'");    
            }
            if(searchDTO.getValidFrom() != null){
                String formattedDate = getDateStringFromDate(searchDTO.getValidFrom());
                sql.append(" AND PDA.VALID_FROM >= To_Date('" + formattedDate + "','dd/MM/yyyy')");    
            }
            if(searchDTO.getValidUntil() != null){
                String formattedDate = getDateStringFromDate(searchDTO.getValidUntil());
                sql.append(" AND PDA.VALID_UNTIL <= To_Date('" + formattedDate + "','dd/MM/yyyy')");    
            }
            
            System.out.println("INF Query : " + sql.toString());
            
            List<PersonDocAttachemntsEntity> list =
                EM().createNativeQuery(sql.toString(), PersonDocAttachemntsEntity.class).getResultList();
            
            for (PersonDocAttachemntsEntity ent : list) {
                try {
                    listDTO.add(InfEntityConverter.getPersonDocAttachemntsDTO(ent));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
            return listDTO;
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
    
    private  String getDateStringFromDate(Date date) {
        if(date==null){
            return null;
        }
        Timestamp dateAsTimeStamp = new Timestamp(date.getTime());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
