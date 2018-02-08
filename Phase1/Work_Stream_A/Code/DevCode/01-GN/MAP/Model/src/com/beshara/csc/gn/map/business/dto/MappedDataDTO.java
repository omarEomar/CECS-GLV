package com.beshara.csc.gn.map.business.dto;

import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;

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
public class MappedDataDTO extends MapClientDTO implements IMappedDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private IObjectTypesDTO objtype1Code;
    private ISocietiesDTO soc1Code;
    private String soc1Value;
    private IObjectTypesDTO objtype2Code;
    private ISocietiesDTO soc2Code;
    private String soc2Value;
    private Long mapStatus;
    private boolean booleanMapStatus;
    /** 
     * MappedDataDTO Default Constructor */
    public MappedDataDTO() {    }    /** 
     * @param mappedDataEntity 
     */
    @Deprecated
    public MappedDataDTO(MappedDataEntity mappedDataEntity) {        MapEntityConverter.fillMappedDataDTO(this, mappedDataEntity);
    }    /** 
     * @return String 
     */
    public String getSoc1Value() {        return soc1Value;
    }    /** 
     * @return String 
     */
    public String getSoc2Value() {        return soc2Value;
    }    /** 
     * @return Long 
     */
    public Long getMapStatus() {        return mapStatus;
    }    /** 
     * @param soc1Value 
     */
    public void setSoc1Value(String soc1Value) {        this.soc1Value = soc1Value;
    }    /** 
     * @param soc2Value 
     */
    public void setSoc2Value(String soc2Value) {        this.soc2Value = soc2Value;
    }    /** 
     * @param mapStatus 
     */
    public void setMapStatus(Long mapStatus) {        this.mapStatus = mapStatus;
    }    /** 
     * * @param objtype1Code 
     */
    public void setObjtype1Code(IObjectTypesDTO objtype1Code) {        this.objtype1Code = objtype1Code;
    }    /** 
     * * @return 
     */
    public IObjectTypesDTO getObjtype1Code() {        return objtype1Code;
    }    /** 
     * * @param soc1Code 
     */
    public void setSoc1Code(ISocietiesDTO soc1Code) {        this.soc1Code = soc1Code;
    }    /** 
     * * @return ISocietiesDTO 
     */
    public ISocietiesDTO getSoc1Code() {        return soc1Code;
    }    /** 
     * * @param objtype2Code 
     */
    public void setObjtype2Code(IObjectTypesDTO objtype2Code) {        this.objtype2Code = objtype2Code;
    }    /** 
     * * @return IObjectTypesDTO 
     */
    public IObjectTypesDTO getObjtype2Code() {        return objtype2Code;
    }    /** 
     * * @param soc2Code 
     */
    public void setSoc2Code(ISocietiesDTO soc2Code) {        this.soc2Code = soc2Code;
    }    /** 
     * * @return ISocietiesDTO 
     */
    public ISocietiesDTO getSoc2Code() {        return soc2Code;
    }    /** 
     * * @param booleanMapStatus 
     */
    public void setBooleanMapStatus(boolean booleanMapStatus) {        if (booleanMapStatus)            this.setMapStatus(new Long(1));
        else            this.setMapStatus(new Long(0));
    }    /** 
     * * @return boolean 
     */
    public boolean isBooleanMmapStatus() {        return booleanMapStatus;
    }}
