package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.StreetZonesEntity;


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
public class StreetZonesDTO extends InfDTO implements IStreetZonesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String mapCode;
    private Long streetCode;
    private Long auditStatus;
    private Long tabrecSerial;
    private IKwStreetsDTO kwStreetsDTO;
    private IKwMapDTO kwMapDTO;

    /**
     * StreetZonesDTO Default Constructor */
    public StreetZonesDTO() {
        super();
    }

    /**
     * @param streetZonesEntity
     */
    public StreetZonesDTO(StreetZonesEntity streetZonesEntity) {
        this.mapCode = streetZonesEntity.getMapCode();
        this.streetCode = streetZonesEntity.getStreetCode();
        this.setCreatedBy(streetZonesEntity.getCreatedBy());
        this.setCreatedDate(streetZonesEntity.getCreatedDate());
        this.setLastUpdatedBy(streetZonesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(streetZonesEntity.getLastUpdatedDate());
        this.auditStatus = streetZonesEntity.getAuditStatus();
        this.tabrecSerial = streetZonesEntity.getTabrecSerial();
//        this.kwMapDTO = InfDTOFactory.createKwMapDTO(streetZonesEntity.getKwMapEntity());
//        this.kwStreetsDTO = InfDTOFactory.createKwStreetsDTO(streetZonesEntity.getKwStreetsEntity());
    }

    /**
     * @return String
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * @return Long
     */
    public Long getStreetCode() {
        return streetCode;
    }

    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    /**
     * @param mapCode
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    /**
     * @param streetCode
     */
    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setKwStreetsDTO(KwStreetsDTO IkwStreetsDTO) {
        this.kwStreetsDTO = kwStreetsDTO;
    }

    public IKwStreetsDTO getKwStreetsDTO() {
        return kwStreetsDTO;
    }

    public void setKwMapDTO(IKwMapDTO kwMapDTO) {
        this.kwMapDTO = kwMapDTO;
    }

    public IKwMapDTO getKwMapDTO() {
        return kwMapDTO;
    }
}
