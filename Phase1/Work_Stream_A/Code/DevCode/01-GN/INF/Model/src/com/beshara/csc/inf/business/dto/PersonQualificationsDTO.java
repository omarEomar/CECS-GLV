package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.CentersDTO;
import com.beshara.csc.nl.qul.business.dto.ICentersDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.QualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.CentersEntityKey;
import com.beshara.csc.nl.qul.business.entity.ICentersEntityKey;
import com.beshara.csc.nl.qul.business.entity.IQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;

import java.sql.Date;

public class PersonQualificationsDTO extends InfDTO implements IPersonQualificationsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    //private Long centerCode ;
    private Long crsRegistrationOrder;
    private Date qualificationDate;
    private Double qualificationDegree;
    private Long tabrecSerial;
    private IQualificationsDTO qualificationsDTO;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    private ICentersDTO centersDTO;
    private Long gradeTypeCode;
    private Long gradeValueCode;
    private String gradeValue;
    private IInfGradeTypesDTO gradeTypeDto;
    private Long currentQual;

    public PersonQualificationsDTO() {
    }

    public PersonQualificationsDTO(PersonQualificationsEntity infPersonQualificationsEntity) {
        setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(infPersonQualificationsEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                        String.valueOf(infPersonQualificationsEntity.getQualificationsEntity().getQualificationKey())));
        this.setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO(infPersonQualificationsEntity.getKwtCitizensResidentsEntity()));
        //this.setQualificationsDTO(QulDTOFactory.createQualificationsDTO(infPersonQualificationsEntity.getQualificationsEntity()));
        if (infPersonQualificationsEntity.getQualificationKey() != null &&
            !infPersonQualificationsEntity.getQualificationKey().equals("")) {
            IQualificationsEntityKey entityKey =
                QulEntityKeyFactory.createQualificationsEntityKey(Long.valueOf(infPersonQualificationsEntity.getQualificationKey()));
            try {
                this.setQualificationsDTO((IQualificationsDTO)QulClientFactory.getQualificationsClient().getById(entityKey));
            } catch (Exception e) {
                e.printStackTrace();
                this.setQualificationsDTO(null);
            }
        }
        //this.setCode ( new InfPersonQualificationsEntityKey ( infPersonQualificationsEntity.getCivilId ( ) , infPersonQualificationsEntity.getQualificationKey ( ) ) ) ;
        this.setAuditStatus(infPersonQualificationsEntity.getAuditStatus());
        //        if (infPersonQualificationsEntity.getCentersEntity() != null)
        //            this.centersDTO = QulEntityConverter.getCentersDTO(infPersonQualificationsEntity.getCentersEntity());
        if (infPersonQualificationsEntity.getCenterCode() != null) {
            ICentersEntityKey entityKey =
                QulEntityKeyFactory.createCentersEntityKey(infPersonQualificationsEntity.getCenterCode());
            try {
                this.setCentersDTO((ICentersDTO)QulClientFactory.getCentersClient().getById(entityKey));
            } catch (Exception e) {
                this.setCentersDTO(null);
            }
        }
        //this.setCenterCode ( infPersonQualificationsEntity.getCenterCode ( ) ) ;
        this.setCreatedBy(infPersonQualificationsEntity.getCreatedBy());
        this.setCreatedDate(infPersonQualificationsEntity.getCreatedDate());
        this.setCrsRegistrationOrder(infPersonQualificationsEntity.getCrsRegistrationOrder());
        this.setLastUpdatedBy(infPersonQualificationsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(infPersonQualificationsEntity.getLastUpdatedDate());
        this.setQualificationDate(infPersonQualificationsEntity.getQualificationDate());
        this.setQualificationDegree(infPersonQualificationsEntity.getQualificationDegree());
        this.setTabrecSerial(infPersonQualificationsEntity.getTabrecSerial());
        this.setGradeValue(infPersonQualificationsEntity.getGradeValue());
        if (infPersonQualificationsEntity.getGradeTypeEntity() != null) {
            this.gradeTypeDto = InfEntityConvertr.getGradeTypesDTO(infPersonQualificationsEntity.getGradeTypeEntity());
        }
        this.setCurrentQual(infPersonQualificationsEntity.getCurrentQual());
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    } // public void setCenterCode ( Long centerCode ) {
    // this.centerCode = centerCode ;
    // }
    //
    // public Long getCenterCode ( ) {
    // return centerCode ;
    // }

    public void setCrsRegistrationOrder(Long crsRegistrationOrder) {
        this.crsRegistrationOrder = crsRegistrationOrder;
    }

    public Long getCrsRegistrationOrder() {
        return crsRegistrationOrder;
    }

    public void setQualificationDate(Date qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

    public Date getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDegree(Double qualificationDegree) {
        this.qualificationDegree = qualificationDegree;
    }

    public Double getQualificationDegree() {
        return qualificationDegree;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setQualificationsDTO(IQualificationsDTO qualificationsDTO) {
        this.qualificationsDTO = qualificationsDTO;
    }

    public IQualificationsDTO getQualificationsDTO() {
        return qualificationsDTO;
    }

    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }

    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {
        return kwtCitizensResidentsDTO;
    }

    public void setCentersDTO(ICentersDTO centersDTO) {
        this.centersDTO = centersDTO;
    }

    public ICentersDTO getCentersDTO() {
        return centersDTO;
    }

    public void setGradeValueCode(Long gradeValueCode) {
        this.gradeValueCode = gradeValueCode;
    }

    public Long getGradeValueCode() {
        return gradeValueCode;
    }

    public void setGradeTypeCode(Long gradeTypeCode) {
        this.gradeTypeCode = gradeTypeCode;
    }

    public Long getGradeTypeCode() {
        return gradeTypeCode;
    }

    public String getCentersKey() {
        if (this.centersDTO != null) {
            return (String)centersDTO.getCode().getKey();
        }
        return null;
    }

    public void setCentersKey(String key) {
        if (key != null && key.length() > 0) {
            CentersEntityKey entityKey = new CentersEntityKey(Long.parseLong(key));
            this.centersDTO = new CentersDTO();
            this.centersDTO.setCode(entityKey);
        }
    }

    public String getQualificationsKey() {
        if (this.qualificationsDTO != null) {
            return (String)qualificationsDTO.getCode().getKey();
        }
        return null;
    }

    public void setQualificationsKey(String key) {
        if (key != null && key.length() > 0) {
            QualificationsEntityKey entityKey = new QualificationsEntityKey(key);
            this.qualificationsDTO = new QualificationsDTO();
            this.qualificationsDTO.setCode(entityKey);
        }
    }

    public void setGradeTypeDto(IInfGradeTypesDTO gradeTypeDto) {
        this.gradeTypeDto = gradeTypeDto;
    }

    public IInfGradeTypesDTO getGradeTypeDto() {
        return gradeTypeDto;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    //    @Override
    //    public int compareTo(PersonQualificationsDTO o) {
    //        return (crsRegistrationOrder == null) ? -1 : crsRegistrationOrder < o.getCrsRegistrationOrder() ? 1 : 0;
    //    }

    public void setCurrentQual(Long currentQual) {
        this.currentQual = currentQual;
    }

    public Long getCurrentQual() {
        return currentQual;
    }
}
