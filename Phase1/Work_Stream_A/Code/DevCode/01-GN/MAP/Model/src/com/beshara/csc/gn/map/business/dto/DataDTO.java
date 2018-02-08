package com.beshara.csc.gn.map.business.dto;


import com.beshara.csc.gn.map.business.entity.DataEntity;
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
public class DataDTO extends MapClientDTO implements IDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long objtypeCode;
    private Long socCode;
    private String sqlStatement;
    private ISocietiesDTO societiesDTO;
    private IObjectTypesDTO objectTypesDTO;
    /** 
     * DataDTO Default Constructor */
    public DataDTO() {    }    /** 
     * @param dataEntity 
     */
    @Deprecated
    public DataDTO(DataEntity dataEntity) {        MapEntityConverter.fillDataDTO(this, dataEntity);
    }    /** 
     * @return Long 
     */
    public Long getObjtypeCode() {        return objtypeCode;
    }    /** 
     * @return Long 
     */
    public Long getSocCode() {        return socCode;
    }    /** 
     * @return String 
     */
    public String getSqlStatement() {        return sqlStatement;
    }    /** 
     * @param objtypeCode 
     */
    public void setObjtypeCode(Long objtypeCode) {        this.objtypeCode = objtypeCode;
    }    /** 
     * @param socCode 
     */
    public void setSocCode(Long socCode) {        this.socCode = socCode;
    }    /** 
     * @param sqlStatement 
     */
    public void setSqlStatement(String sqlStatement) {        this.sqlStatement = sqlStatement;
    }    public void setSocietiesDTO(ISocietiesDTO societiesDTO) {        this.societiesDTO = societiesDTO;
    }    public ISocietiesDTO getSocietiesDTO() {        return societiesDTO;
    }    public void setObjectTypesDTO(IObjectTypesDTO objectTypesDTO) {        this.objectTypesDTO = objectTypesDTO;
    }    public IObjectTypesDTO getObjectTypesDTO() {        return objectTypesDTO;
    }


}
