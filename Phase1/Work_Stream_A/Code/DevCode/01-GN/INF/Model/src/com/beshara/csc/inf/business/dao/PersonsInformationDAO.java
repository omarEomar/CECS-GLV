package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IPersonsInformationEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfGradeTypesEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.QulDTOFactory;
import com.beshara.csc.nl.qul.business.entity.CenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.ICenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class PersonsInformationDAO extends InfBaseDAO {
    public PersonsInformationDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new PersonsInformationDAO();
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<PersonsInformationEntity> list =
                EM().createNamedQuery("PersonsInformationEntity.findAll").setHint("toplink.refresh",
                                                                                  "true").getResultList();
            for (PersonsInformationEntity personsInformationEntity : list) {
                arrayList.add(InfDTOFactory.createPersonsInformationDTO(personsInformationEntity));
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
            List<PersonsInformationEntity> list =
                EM().createNamedQuery("PersonsInformationEntity.findAllByCivilId").setParameter("civilId",
                                                                                                civilId).getResultList();
            for (PersonsInformationEntity personsInformationEntity : list) {
                arrayList.add(InfDTOFactory.createPersonsInformationDTO(personsInformationEntity));
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
     * Create a New PersonsInformationEntity * @param IPersonsInformationDTO
     * @return IPersonsInformationDTO
     */
    public IBasicDTO add(IBasicDTO personsInformationDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonsInformationDTO personsInformationDTO = (IPersonsInformationDTO)personsInformationDTO1;
            PersonsInformationEntity personsInformationEntity = new PersonsInformationEntity();
            if (personsInformationDTO.getKwtCitizensResidentsDTO() != null) {
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)personsInformationDTO.getKwtCitizensResidentsDTO().getCode());
                if (kwtCitizensResidentsEntity == null)
                    throw new NoResultException();
                personsInformationEntity.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
            } else {
                throw new NoResultException();
            }

            if (personsInformationDTO.getCenterQualificationsDTO() != null) {
                if (personsInformationDTO.getSocietiesDTO() == null ||
                    personsInformationDTO.getSocietiesDTO().getCode() == null) {
                    String socCode =
                        getSocietyCodeByCenter(personsInformationDTO.getCenterQualificationsDTO().getCentersDTO().getCode().getKey());
                    //personsInformationEntity.setSocCode(Long.valueOf(socCode));
                    if (socCode != null) {
                        String newSocCode = socCode.replace("[", "").replace("]", "").trim();
                        personsInformationDTO.getSocietiesDTO().setCode(MapEntityKeyFactory.createSocietiesEntityKey(Long.valueOf(newSocCode)));

                    }
                }
            }
            //            if (personsInformationDTO.getSocietiesDTO() != null) {
            //                SocietiesEntity societiesEntity =
            //                    em.find(SocietiesEntity.class, (ISocietiesEntityKey)personsInformationDTO.getSocietiesDTO().getCode());
            //                if (societiesEntity == null)
            //                    throw new FinderException();
            //                personsInformationEntity.setSocietiesEntity(societiesEntity);
            //            } else {
            //                throw new FinderException();
            //            }
            if (personsInformationDTO.getSocietiesDTO() != null) {
                ISocietiesEntityKey entityKey = (ISocietiesEntityKey)personsInformationDTO.getSocietiesDTO().getCode();
                personsInformationEntity.setSocCode(entityKey.getSocCode());
            } else {
                throw new RuntimeException();
            }

            //            CenterQualificationsEntity centerQualificationsEntity =
            //                em.find(CenterQualificationsEntity.class, (ICenterQualificationsEntityKey)personsInformationDTO.getCenterQualificationsDTO().getCode());
            //            if (centerQualificationsEntity == null)
            //                throw new FinderException();
            //            personsInformationEntity.setCenterQualificationsEntity(centerQualificationsEntity);
            //        } else {
            //            personsInformationEntity.setCenterQualificationsEntity(null);
            //        }
            if (personsInformationDTO.getCenterQualificationsDTO() != null) {
                ICenterQualificationsEntityKey entityKey =
                    (ICenterQualificationsEntityKey)personsInformationDTO.getCenterQualificationsDTO().getCode();
                personsInformationEntity.setCenterCode(entityKey.getCenterCode());
                personsInformationEntity.setCntqualificationCode(entityKey.getCntqualificationCode());
            } else {
                personsInformationEntity.setCenterCode(null);
                personsInformationEntity.setCntqualificationCode(null);
            }
            if (personsInformationDTO.getGradeTypeDto() != null) {
                InfGradeTypesEntity gradeTypesEntity =
                    EM().find(InfGradeTypesEntity.class, personsInformationDTO.getGradeTypeDto().getCode());
                if (gradeTypesEntity == null)
                    throw new NoResultException();
                personsInformationEntity.setGradeTypeEntity(gradeTypesEntity);
            } else {
                personsInformationEntity.setGradeTypeEntity(null);
            }
            personsInformationEntity.setGradeValue(personsInformationDTO.getGradeValue());
            personsInformationEntity.setDegree(personsInformationDTO.getDegree());
            personsInformationEntity.setRegistrationDate(personsInformationDTO.getRegistrationDate());
            personsInformationEntity.setUntilDate(personsInformationDTO.getUntilDate());
            personsInformationEntity.setCreatedBy(personsInformationDTO.getCreatedBy());
            personsInformationEntity.setCreatedDate(personsInformationDTO.getCreatedDate());
            doAdd(personsInformationEntity);
            personsInformationDTO.setCode(InfEntityKeyFactory.createPersonsInformationEntityKey(personsInformationEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                                                personsInformationEntity.getRegistrationDate(),
                                                                                                personsInformationEntity.getSocCode()));


            return personsInformationDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public String getSocietyCodeByCenter(String centerCode) throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder nativeQuery = new StringBuilder("select HR_QUL_INTERFACE_PACK.GET_CENTER_MAPPED_CODE('");
            nativeQuery.append(centerCode);
            nativeQuery.append("') from dual");
            System.out.println(nativeQuery);
            Query query = EM().createNativeQuery(nativeQuery.toString());
            if (query.getResultList() != null && query.getResultList().size() > 0)
                return query.getResultList().get(0).toString();
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

    /**
     * Update an Existing PersonsInformationEntity * @param personsInformationDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO personsInformationDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IPersonsInformationDTO personsInformationDTO = (IPersonsInformationDTO)personsInformationDTO1;
            PersonsInformationEntity personsInformationEntity =
                EM().find(PersonsInformationEntity.class, (IPersonsInformationEntityKey)personsInformationDTO.getCode());
            //        if (personsInformationDTO.getCenterQualificationsDTO() != null) {
            //            CenterQualificationsEntity centerQualificationsEntity =
            //                em.find(CenterQualificationsEntity.class, (ICenterQualificationsEntityKey)personsInformationDTO.getCenterQualificationsDTO().getCode());
            //            if (centerQualificationsEntity == null)
            //                throw new FinderException();
            //            personsInformationEntity.setCenterQualificationsEntity(centerQualificationsEntity);
            //        } else {
            //            personsInformationEntity.setCenterQualificationsEntity(null);
            //        }
            if (personsInformationDTO.getCenterQualificationsDTO() != null) {
                ICenterQualificationsEntityKey entityKey =
                    (ICenterQualificationsEntityKey)personsInformationDTO.getCenterQualificationsDTO().getCode();
                personsInformationEntity.setCenterCode(entityKey.getCenterCode());
                personsInformationEntity.setCntqualificationCode(entityKey.getCntqualificationCode());
            } else {
                personsInformationEntity.setCenterCode(null);
                personsInformationEntity.setCntqualificationCode(null);
            }
            if (personsInformationDTO.getGradeTypeDto() != null) {
                InfGradeTypesEntity gradeTypesEntity =
                    EM().find(InfGradeTypesEntity.class, personsInformationDTO.getGradeTypeDto().getCode());
                if (gradeTypesEntity == null)
                    throw new NoResultException();
                personsInformationEntity.setGradeTypeEntity(gradeTypesEntity);
            } else {
                personsInformationEntity.setGradeTypeEntity(null);
            }
            personsInformationEntity.setGradeValue(personsInformationDTO.getGradeValue());
            personsInformationEntity.setDegree(personsInformationDTO.getDegree());
            // commented by B.H [E.Saber] :: REGISTRATION_DATE is a primary key cannot be updated.
            //personsInformationEntity.setRegistrationDate(personsInformationDTO.getRegistrationDate());
            personsInformationEntity.setUntilDate(personsInformationDTO.getUntilDate());
            personsInformationEntity.setLastUpdatedBy(personsInformationDTO.getCreatedBy());
            personsInformationEntity.setLastUpdatedDate(personsInformationDTO.getCreatedDate());
            doUpdate(personsInformationEntity);
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
     * Remove an Existing PersonsInformationEntity * @param personsInformationDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO personsInformationDTO) throws DataBaseException, SharedApplicationException {
        try {
            PersonsInformationEntity personsInformationEntity =
                EM().find(PersonsInformationEntity.class, (IPersonsInformationEntityKey)personsInformationDTO.getCode());
            if (personsInformationEntity == null) {
                throw new NoResultException();
            }
            doRemove(personsInformationEntity);
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
     * Get PersonsInformationEntity By Primary Key * @param id
     * @return IPersonsInformationDTO
     */
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            PersonsInformationEntity personsInformationEntity =
                EM().find(PersonsInformationEntity.class, (IPersonsInformationEntityKey)id);
            if (personsInformationEntity == null) {
                throw new NoResultException();
            }
            IPersonsInformationDTO personsInformationDTO =
                InfDTOFactory.createPersonsInformationDTO(personsInformationEntity);
            return personsInformationDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public String getQualKey(ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            String qulKey = "";
            StringBuilder nativeQuery = new StringBuilder("select HR_QUL_INTERFACE_PACK.GET_QUL_MAP_CODE(");
            nativeQuery.append(((CenterQualificationsEntityKey)centerQualificationsDTO.getCode()).getCenterCode() +
                               ",");
            String cntQualCode =
                ((CenterQualificationsEntityKey)centerQualificationsDTO.getCode()).getCntqualificationCode();
            nativeQuery.append("'" + cntQualCode + "', ");
            nativeQuery.append("null,");
            nativeQuery.append("null");
            nativeQuery.append(") from dual");
            System.out.println(nativeQuery);
            Query query = EM().createNativeQuery(nativeQuery.toString());
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                qulKey = query.getResultList().get(0).toString();
                qulKey = qulKey.replace("[", "");
                qulKey = qulKey.replace("]", "");
                return qulKey;
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return null;
    }
// edited in 6-12-2017
//Added QUAL_DTL_CODE in the query     
    public List<IBasicDTO> getQualForEmp(Long civilID, boolean currentQul) throws DataBaseException,
                                                                                      SharedApplicationException{

        StringBuilder query =
            new StringBuilder("SELECT f.civil_id, f.soc_code, f.registration_date, f.cntqualification_code,\n" + 
            "       f.center_code, f.until_date, f.grade_value, f.grdtype_code, q.qualification_key,\n" + 
            "       qq.qualification_name, qc.center_name, qc.cntry_code,\n" + 
            "       c.cntqualification_name, ic.cntry_name ,qq.QUAL_DTL_CODE \n" +
                              
            "  FROM inf_persons_information f,\n" + 
            "       inf_person_qualifications q,\n" + 
            "       nl_qul_center_qualifications c,\n" + 
            "       nl_qul_qualifications qq,\n" + 
            "       nl_qul_centers qc,\n" + 
            "       inf_countries ic\n" + 
            " WHERE f.civil_id =");
        query.append(civilID);
        query.append(" AND f.cntqualification_code IS NOT NULL\n" +
                "   AND f.civil_id = q.civil_id\n" +
                "   AND f.center_code = c.center_code\n" +
                "   AND f.cntqualification_code = c.cntqualification_code\n" +
                "   AND q.qualification_key = qq.qualification_key\n" +
                "   AND f.center_code = qc.center_code\n" +
                "   and qc.CNTRY_CODE=ic.CNTRY_CODE\n" +
                "   AND q.qualification_key =\n" +
                "          (SELECT csnl_owner.hr_qul_interface_pack.get_qul_map_code\n" +
                "                                                      (f.center_code,\n" +
                "                                                       f.cntqualification_code\n" +
                "                                                      )\n" +
                "             FROM DUAL)\n" +
                "   AND q.current_qual");
        if (currentQul)
            query.append(" = 1");
        else
            query.append(" <> 1");
        System.out.println(query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list = q.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
}
            
        
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        for (Vector row : list) {
            Date sqlDate = new Date(((Timestamp)row.get(2)).getTime());
            IPersonsInformationDTO personsInformationDTO=InfDTOFactory.createPersonsInformationDTO();
            personsInformationDTO.setCode(InfEntityKeyFactory.createPersonsInformationEntityKey(((BigDecimal)row.get(0)).longValue(), sqlDate, ((BigDecimal)row.get(1)).longValue()));
            personsInformationDTO.setCntqualificationCode(row.get(3).toString());
            personsInformationDTO.setCenterCode(((BigDecimal)row.get(4)).longValue());
            Date untilDate = new Date(((Timestamp)row.get(5)).getTime());
            personsInformationDTO.setUntilDate(untilDate);
            if(row.get(6) !=null)
            personsInformationDTO.setGradeValue(row.get(6).toString());
            if(row.get(7) !=null)
            personsInformationDTO.setGradeTypeCode(((BigDecimal)row.get(7)).longValue());
            IPersonQualificationsDTO  personQualificationsDTO=InfDTOFactory.createPersonQualificationsDTO();
            personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(((BigDecimal)row.get(0)).longValue(), row.get(8).toString()));
            IQualificationsDTO qualificationsDTO=QulDTOFactory.createQualificationsDTO();
            qualificationsDTO.setCode(QulEntityKeyFactory.createQualificationsEntityKey(row.get(8).toString()));
            qualificationsDTO.setQualificationName(row.get(9).toString());
            if(row.get(14) !=null)
            qualificationsDTO.setQualificationDtlCode(row.get(14).toString());
            personQualificationsDTO.setQualificationsDTO(qualificationsDTO);
            personsInformationDTO.setPersonQualificationsDTO(personQualificationsDTO);
            ICenterQualificationsDTO centerQualificationsDTO=QulDTOFactory.createCenterQualificationsDTO();
            centerQualificationsDTO.setCode(QulEntityKeyFactory.createCenterQualificationsEntityKey(((BigDecimal)row.get(4)).longValue(), row.get(3).toString()));
            ICentersDTO centersDTO=QulDTOFactory.createCentersDTO();
            centersDTO.setCode(QulEntityKeyFactory.createCentersEntityKey(((BigDecimal)row.get(4)).longValue()));
            centerQualificationsDTO.setCentersDTO(centersDTO);
            personsInformationDTO.setCenterQualificationsDTO(centerQualificationsDTO);
            personsInformationDTO.setCenterName(row.get(10).toString());
            personsInformationDTO.setCntryCode(((BigDecimal)row.get(11)).longValue());  
            personsInformationDTO.setCntqualificationName(row.get(12).toString());
            personsInformationDTO.setCntryName(row.get(13).toString());
            arrayList.add(personsInformationDTO);
        }
        return arrayList;
    }
    
    
    /**
     * added By M.abdelsabour B>H Team
     * to check duplication  qual for person
     * used in adc Module
     */
    
    public Boolean checkQualificationForPerson(Long realCivil, Long centerCode,
                                               String centerQualCode) throws SharedApplicationException,
                                                                             DataBaseException {
        try {
           
            StringBuilder str = new StringBuilder();
            str.append(" SELECT COUNT(1)  ");
            str.append(" FROM INF_PERSONS_INFORMATION  PERSON ");
            str.append(" WHERE  PERSON.CIVIL_ID =" + realCivil);
            str.append(" AND  PERSON.CENTER_CODE= " + centerCode);
            str.append(" AND   PERSON.CNTQUALIFICATION_CODE = " + centerQualCode);

            System.out.println(str.toString());
            Vector result = (Vector)EM().createNativeQuery(str.toString()).getSingleResult();

            int count = ((BigDecimal)result.elementAt(0)).intValue();
            if (count == 0) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
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


}
