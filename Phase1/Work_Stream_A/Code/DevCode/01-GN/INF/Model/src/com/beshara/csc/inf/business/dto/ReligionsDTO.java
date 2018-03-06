package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.ReligionsEntity;

import java.util.List;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class ReligionsDTO extends InfDTO implements IReligionsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long tabrecSerial;  
    List<IGenderReligionDTO> genderReligionList;

    /**
     * ReligionsDTO Default Constructor */
    public ReligionsDTO() {
    }

    public ReligionsDTO(Long code, String name) {
        setCode(InfEntityKeyFactory.createReligionsEntityKey(code));
        setName(name);
    }

    /**
     * @param religionsEntity
     */
    public ReligionsDTO(ReligionsEntity religionsEntity) {
        setCode(InfEntityKeyFactory.createReligionsEntityKey(religionsEntity.getReligionCode()));
        setName(religionsEntity.getReligionName());        
        this.setCreatedBy(religionsEntity.getCreatedBy());
        this.setCreatedDate(religionsEntity.getCreatedDate());
        this.setLastUpdatedBy(religionsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(religionsEntity.getLastUpdatedDate());
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    public void setGenderReligionList(List<IGenderReligionDTO> genderReligionList) {
        this.genderReligionList = genderReligionList;
    }

    public List<IGenderReligionDTO> getGenderReligionList() {
        return genderReligionList;
    }
}
