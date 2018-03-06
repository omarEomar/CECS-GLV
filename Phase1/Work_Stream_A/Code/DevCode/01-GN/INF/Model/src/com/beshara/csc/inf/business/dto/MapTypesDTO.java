package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.MapTypesEntity;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class MapTypesDTO extends InfDTO implements IMapTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long typeCode;
    private String typeName;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * MapTypesDTO Default Constructor */
    public MapTypesDTO() {        super();
    }    /** 
     * @param mapTypesEntity 
     */
    public MapTypesDTO(MapTypesEntity mapTypesEntity) {        this.typeCode = mapTypesEntity.getTypeCode();
        this.typeName = mapTypesEntity.getTypeName();
        this.setCreatedBy(mapTypesEntity.getCreatedBy());
        this.setCreatedDate(mapTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(mapTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(mapTypesEntity.getLastUpdatedDate());
        this.auditStatus = mapTypesEntity.getAuditStatus();
        this.tabrecSerial = mapTypesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getTypeCode() {        return typeCode;
    }    /** 
     * @return String 
     */
    public String getTypeName() {        return typeName;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param typeCode 
     */
    public void setTypeCode(Long typeCode) {        this.typeCode = typeCode;
    }    /** 
     * @param typeName 
     */
    public void setTypeName(String typeName) {        this.typeName = typeName;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
