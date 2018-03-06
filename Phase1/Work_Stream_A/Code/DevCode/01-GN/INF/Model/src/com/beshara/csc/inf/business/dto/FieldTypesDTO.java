package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.inf.business.entity.FieldTypesEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/** 
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ; 
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Abdul Mejid 30-Oct-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ; 
 * - Add Javadoc Comments to Methods. * * @author Beshara Group 
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany 
 * @version 1.0 
 * @since 03/09/2007 
 */
public class FieldTypesDTO extends InfDTO implements IFieldTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<IFieldsDTO> fieldsDTOList;
    /** 
     * FieldTypesDTO Default Constructor */
    public FieldTypesDTO() {    }    /** 
     * @param fieldTypesEntity 
     */
    public FieldTypesDTO(FieldTypesEntity fieldTypesEntity) {        this.setCode(new FieldTypesEntityKey(fieldTypesEntity.getFldtypeCode()));
        this.setName(fieldTypesEntity.getFldtypeName());
        this.setCreatedBy(fieldTypesEntity.getCreatedBy());
        this.setCreatedDate(fieldTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(fieldTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(fieldTypesEntity.getLastUpdatedDate());
        // if ( fieldTypesEntity.getFieldsEntityList ( ) != null ) { 
        // List<FieldsDTO> fieldlist = new ArrayList ( ) ; 
        // for ( FieldsEntity dto: fieldTypesEntity.getFieldsEntityList ( ) ) { 
        // fieldlist.add ( new FieldsDTO ( dto ) ) ; 
        // } 
        // this.setFieldsDTOList ( fieldlist ) ; 
        // } 
    }    /** 
     * Constructor * @param code 
     * @param name 
     */
    public FieldTypesDTO(Long code, String name) {        this.setCode(new FieldTypesEntityKey(code));
        this.setName(name);
    }    /** 
     * * @param fieldsDTOList 
     */
    public void setFieldsDTOList(List<IFieldsDTO> fieldsDTOList) {        this.fieldsDTOList = fieldsDTOList;
    }    /** 
     * * @return 
     */
    public List<IFieldsDTO> getFieldsDTOList() {        return fieldsDTOList;
    }}
