package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.InfPersonQualificationsEntity;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.IQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;

import java.sql.Timestamp;

public class InfPersonQualificationsDTO extends InfDTO implements IInfPersonQualificationsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long centerCode;
    private Long crsRegistrationOrder;
    private Timestamp qualificationDate;
    private Double qualificationDegree;
    private Long tabrecSerial;
    private IQualificationsDTO qualificationsDTO;
    private IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
    public InfPersonQualificationsDTO() {    }    
    public InfPersonQualificationsDTO(InfPersonQualificationsEntity infPersonQualificationsEntity) {  
        this.setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO(infPersonQualificationsEntity.getKwtCitizensResidentsEntity()));
        
     //   this.setQualificationsDTO(QulDTOFactory.createQualificationsDTO(infPersonQualificationsEntity.getQualificationsEntity()));
       if(infPersonQualificationsEntity.getQualificationKey()!=null && !infPersonQualificationsEntity.getQualificationKey().equals("")){
           IQualificationsEntityKey entityKey = QulEntityKeyFactory.createQualificationsEntityKey(infPersonQualificationsEntity.getQualificationKey());
           try{
              this.setQualificationsDTO((IQualificationsDTO)QulClientFactory.getQualificationsClient().getById(entityKey));  
           }catch(Exception e){
             this.setQualificationsDTO(null);
           }
       }
        //this.setCode ( new InfPersonQualificationsEntityKey ( infPersonQualificationsEntity.getCivilId ( ) , infPersonQualificationsEntity.getQualificationKey ( ) ) ) ; 
        this.setAuditStatus(infPersonQualificationsEntity.getAuditStatus());
        this.setCenterCode(infPersonQualificationsEntity.getCenterCode());
        this.setCreatedBy(infPersonQualificationsEntity.getCreatedBy());
        this.setCreatedDate(infPersonQualificationsEntity.getCreatedDate());
        this.setCrsRegistrationOrder(infPersonQualificationsEntity.getCrsRegistrationOrder());
        this.setLastUpdatedBy(infPersonQualificationsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(infPersonQualificationsEntity.getLastUpdatedDate());
        this.setQualificationDate(infPersonQualificationsEntity.getQualificationDate());
        this.setQualificationDegree(infPersonQualificationsEntity.getQualificationDegree());
        this.setTabrecSerial(infPersonQualificationsEntity.getTabrecSerial());
    }    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    public Long getAuditStatus() {        return auditStatus;
    }    public void setCenterCode(Long centerCode) {        this.centerCode = centerCode;
    }    public Long getCenterCode() {        return centerCode;
    }    public void setCrsRegistrationOrder(Long crsRegistrationOrder) {        this.crsRegistrationOrder = crsRegistrationOrder;
    }    public Long getCrsRegistrationOrder() {        return crsRegistrationOrder;
    }    public void setQualificationDate(Timestamp qualificationDate) {        this.qualificationDate = qualificationDate;
    }    public Timestamp getQualificationDate() {        return qualificationDate;
    }    public void setQualificationDegree(Double qualificationDegree) {        this.qualificationDegree = qualificationDegree;
    }    public Double getQualificationDegree() {        return qualificationDegree;
    }    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }    public void setQualificationsDTO(IQualificationsDTO qualificationsDTO) {        this.qualificationsDTO = qualificationsDTO;
    }    public IQualificationsDTO getQualificationsDTO() {        return qualificationsDTO;
    }    public void setKwtCitizensResidentsDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {        this.kwtCitizensResidentsDTO = kwtCitizensResidentsDTO;
    }    public IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO() {        return kwtCitizensResidentsDTO;
    }}
