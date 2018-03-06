package com.beshara.csc.inf.business.dto;

import com.beshara.csc.inf.business.entity.GenderReligionEntity;
import com.beshara.csc.inf.business.entity.GenderReligionEntityKey;
import com.beshara.csc.inf.business.entity.ReligionsEntityKey;

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
public class GenderReligionDTO extends InfDTO implements IGenderReligionDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String genderTypesKey; 
    private Long gentypeCode;
    private Long religionCode;
    private String genregName;
    private Long auditStatus;
    private Long tabrecSerial;
    private IGenderTypesDTO genderTypesDTO;
    private IReligionsDTO religionsDTO ;
    /** 
     * GenderReligionDTO Default Constructor */
    public GenderReligionDTO() {        super();
    }    
    /** 
     * @param genderReligionEntity 
     */
    public GenderReligionDTO(GenderReligionEntity genderReligionEntity) {
        if (genderReligionEntity.getGenderTypesEntity() != null) {
            this.genderTypesDTO = 
                    new GenderTypesDTO(genderReligionEntity.getGenderTypesEntity());
        } 
        if (genderReligionEntity.getReligionsEntity() != null) {
            this.religionsDTO = 
                    new ReligionsDTO(genderReligionEntity.getReligionsEntity());
        }
        setCode(new GenderReligionEntityKey(genderReligionEntity.getGenderTypesEntity().getGentypeCode(), genderReligionEntity.getReligionsEntity().getReligionCode()));
        setName(genderReligionEntity.getGenregName());
        this.gentypeCode = genderReligionEntity.getGentypeCode();
        this.religionCode = genderReligionEntity.getReligionCode();
        this.genregName = genderReligionEntity.getGenregName();
        this.setCreatedBy(genderReligionEntity.getCreatedBy());
        this.setCreatedDate(genderReligionEntity.getCreatedDate());
        this.setLastUpdatedBy(genderReligionEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(genderReligionEntity.getLastUpdatedDate());
        this.auditStatus = genderReligionEntity.getAuditStatus();
        this.tabrecSerial = genderReligionEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getGentypeCode() {        return gentypeCode;
    }    /** 
     * @return Long 
     */
    public Long getReligionCode() {        return religionCode;
    }    /** 
     * @return String 
     */
    public String getGenregName() {        return genregName;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param gentypeCode 
     */
    public void setGentypeCode(Long gentypeCode) {        this.gentypeCode = gentypeCode;
    }    /** 
     * @param religionCode 
     */
    public void setReligionCode(Long religionCode) {        this.religionCode = religionCode;
    }    /** 
     * @param genregName 
     */
    public void setGenregName(String genregName) {        this.genregName = genregName;
    } /**
 * @param createdBy
 */
    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }
      


          public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
              this.genderTypesDTO = genderTypesDTO;
          }

          public IGenderTypesDTO getGenderTypesDTO() {
              return genderTypesDTO;
          }

    public void setReligionsDTO(IReligionsDTO religionsDTO) {
        this.religionsDTO = religionsDTO;
    }

    public IReligionsDTO getReligionsDTO() {
        return religionsDTO;
    }




    public void setGenderTypesKey(String genderTypesKey) {
        if (genderTypesKey != null) 
        {
           ReligionsEntityKey entityKey = 
                new ReligionsEntityKey(Long.parseLong(genderTypesKey));
            this.genderTypesDTO = new GenderTypesDTO();
            this.genderTypesDTO.setCode(entityKey);
        } else
            this.genderTypesDTO = null;
    }

    public String getGenderTypesKey() {
        try {
            if (this.genderTypesDTO != null)
                return (String)genderTypesDTO.getCode().getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
