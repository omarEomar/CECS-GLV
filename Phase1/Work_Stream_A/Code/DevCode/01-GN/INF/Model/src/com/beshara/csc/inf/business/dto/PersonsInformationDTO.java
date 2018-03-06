package com.beshara.csc.inf.business.dto;


import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.inf.business.client.IPersonQualificationsClient;
import com.beshara.csc.inf.business.client.IPersonsInformationClient;
import com.beshara.csc.inf.business.client.PersonQualificationsClientImpl;
import com.beshara.csc.inf.business.client.PersonsInformationClientImpl;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.ICenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Date;


public class PersonsInformationDTO extends InfDTO implements IPersonsInformationDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long auditStatus;
    // private Long centerCode ;
    // private Long civilId ;
    //private String cntqualificationCode ;
    private Double degree;
    private Date registrationDate;
    //private Long socCode ;
    private Long tabrecSerial;
    private Date untilDate;
    private ICenterQualificationsDTO centerQualificationsDTO;
    private ISocietiesDTO societiesDTO;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;

    private Long gradeTypeCode;
    private Long gradeValueCode;
    private String gradeValue;
    private IInfGradeTypesDTO gradeTypeDto;
    private String cntqualificationCode;
    private Long centerCode;
    private String cntqualificationName ; 
    private Long cntryCode;
    private String centerName ;
    private String cntryName ;
    private IPersonQualificationsDTO personQualificationsDTO;

    public PersonsInformationDTO() {
    }

    public PersonsInformationDTO(PersonsInformationEntity personsInformationEntity) {
        setCode(InfEntityKeyFactory.createPersonsInformationEntityKey(personsInformationEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                      personsInformationEntity.getRegistrationDate(),
                                                                      personsInformationEntity.getSocCode()));
        this.degree = personsInformationEntity.getDegree();
        //        if (personsInformationEntity.getCenterQualificationsEntity() != null)
        //            this.centerQualificationsDTO =
        //                    QulDTOFactory.createCenterQualificationsDTO(personsInformationEntity.getCenterQualificationsEntity());
        if (personsInformationEntity.getCenterCode() != null &&
            personsInformationEntity.getCntqualificationCode() != null) {
            ICenterQualificationsEntityKey entityKey =
                QulEntityKeyFactory.createCenterQualificationsEntityKey(personsInformationEntity.getCenterCode(),
                                                                        personsInformationEntity.getCntqualificationCode());
            try {
                setCenterQualificationsDTO((ICenterQualificationsDTO)QulClientFactory.getCenterQualificationsClient().getById(entityKey));
            } catch (Exception e) {
                setCenterQualificationsDTO(null);
            }
            personsInformationEntity.setCntqualificationCode(entityKey.getCntqualificationCode());
        } else {
            personsInformationEntity.setCenterCode(null);
            personsInformationEntity.setCntqualificationCode(null);
        }
        this.auditStatus = personsInformationEntity.getAuditStatus();
        this.setCreatedBy(personsInformationEntity.getCreatedBy());
        this.setCreatedDate(personsInformationEntity.getCreatedDate());
        if (personsInformationEntity.getKwtCitizensResidentsEntity() != null)
            this.kwtCitizensResidentsDTO =
                    InfDTOFactory.createKwtCitizensResidentsDTO(personsInformationEntity.getKwtCitizensResidentsEntity());
        //        if (personsInformationEntity.getSocietiesEntity() != null)
        //            this.societiesDTO = MapDTOFactory.createSocietiesDTO(personsInformationEntity.getSocietiesEntity());
        if (personsInformationEntity.getSocCode() != null) {
            ISocietiesEntityKey entityKey =
                MapEntityKeyFactory.createSocietiesEntityKey(personsInformationEntity.getSocCode());
            try {
                setSocietiesDTO((ISocietiesDTO)MapClientFactory.getSocietiesClient().getById(entityKey));
            } catch (Exception e) {
                setSocietiesDTO(null);
            }
        }
        this.untilDate = personsInformationEntity.getUntilDate();
        this.tabrecSerial = personsInformationEntity.getTabrecSerial();
        this.setLastUpdatedBy(personsInformationEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(personsInformationEntity.getLastUpdatedDate());
        this.setGradeValue(personsInformationEntity.getGradeValue());
        if (personsInformationEntity.getGradeTypeEntity() != null) {
            this.gradeTypeDto = InfEntityConvertr.getGradeTypesDTO(personsInformationEntity.getGradeTypeEntity());
        }

        String qualificationKey = "";
        try {
            IPersonsInformationClient personsInformationClient = new PersonsInformationClientImpl();
            qualificationKey = personsInformationClient.getQualKey(centerQualificationsDTO);
            if (qualificationKey != null && !qualificationKey.equals("null")) {
                IEntityKey key =
                    InfEntityKeyFactory.createPersonQualificationsEntityKey(kwtCitizensResidentsDTO.getCivilId(),
                                                                            qualificationKey);
                IPersonQualificationsClient personQualificationsClient = new PersonQualificationsClientImpl();
                this.personQualificationsDTO = (IPersonQualificationsDTO)personQualificationsClient.getById(key);

            }
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }

    public Double getDegree() {
        return degree;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setCenterQualificationsDTO(ICenterQualificationsDTO centerQualificationsDTO) {
        this.centerQualificationsDTO = centerQualificationsDTO;
    }

    public ICenterQualificationsDTO getCenterQualificationsDTO() {
        return centerQualificationsDTO;
    }

    public void setSocietiesDTO(ISocietiesDTO societiesDTO) {
        this.societiesDTO = societiesDTO;
    }

    public ISocietiesDTO getSocietiesDTO() {
        return societiesDTO;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setGradeTypeCode(Long gradeTypeCode) {
        this.gradeTypeCode = gradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }

    public void setGradeValueCode(Long gradeValueCode) {
        this.gradeValueCode = gradeValueCode;
    }

    public Long getGradeValueCode() {
        return gradeValueCode;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    public void setGradeTypeDto(IInfGradeTypesDTO gradeTypeDto) {
        this.gradeTypeDto = gradeTypeDto;
    }

    public IInfGradeTypesDTO getGradeTypeDto() {
        return gradeTypeDto;
    }

    public void setPersonQualificationsDTO(IPersonQualificationsDTO personQualificationsDTO) {
        this.personQualificationsDTO = personQualificationsDTO;
    }

    public IPersonQualificationsDTO getPersonQualificationsDTO() {
        return personQualificationsDTO;
    }

    public void setCntqualificationCode(String cntqualificationCode) {
        this.cntqualificationCode = cntqualificationCode;
}

    public String getCntqualificationCode() {
        return cntqualificationCode;
    }

    public void setCenterCode(Long centerCode) {
        this.centerCode = centerCode;
    }

    public Long getCenterCode() {
        return centerCode;
    }

    public void setCntqualificationName(String cntqualificationName) {
        this.cntqualificationName = cntqualificationName;
    }

    public String getCntqualificationName() {
        return cntqualificationName;
    }

    public void setCntryCode(Long cntryCode) {
        this.cntryCode = cntryCode;
    }

    public Long getCntryCode() {
        return cntryCode;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCntryName(String cntryName) {
        this.cntryName = cntryName;
    }

    public String getCntryName() {
        return cntryName;
    }
}
