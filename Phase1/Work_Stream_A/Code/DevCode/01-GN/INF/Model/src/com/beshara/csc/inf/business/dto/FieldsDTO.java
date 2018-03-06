package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.req.business.dto.IRequestDataDTO;
import com.beshara.csc.hr.mis.business.dto.IMisOperationDetailsDTO;
import com.beshara.csc.inf.business.entity.FieldsEntity;
import com.beshara.csc.inf.business.entity.FieldsEntityKey;

import java.util.ArrayList;
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
public class FieldsDTO extends InfDTO implements IFieldsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String fldDesc;
    private String sqlStatement;
    private Long fldtypeCode;
    private Long displayedType;
    private IFieldTypesDTO fieldTypesDTO;
    private List<IRequestDataDTO> requestDataDTOList = 
        new ArrayList<IRequestDataDTO>();
    private List<IRelatedFieldsDTO> relatedFieldsDTOList;
    private boolean hasRelatedFields;
    private List<IFieldValueDTO> fieldValueDTOList;
    private List<IMisOperationDetailsDTO>  misOperationDetailsDTOList = 
        new ArrayList<IMisOperationDetailsDTO>();

    /** 
     * FieldsDTO Default Constructor */
    public FieldsDTO() {
    }

  
    /** 
     * @param fieldsEntity 
     */
    public FieldsDTO(FieldsEntity fieldsEntity) {
        this.setCode(new FieldsEntityKey(fieldsEntity.getFldCode()));
        this.setName(fieldsEntity.getFldName());
        this.fldDesc = fieldsEntity.getFldDesc();
        this.displayedType = fieldsEntity.getDisplayedType();
        this.sqlStatement = fieldsEntity.getSqlStatement();
        this.setCreatedBy(fieldsEntity.getCreatedBy());
        this.setCreatedDate(fieldsEntity.getCreatedDate());
        this.setLastUpdatedBy(fieldsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(fieldsEntity.getLastUpdatedDate());
        if (fieldsEntity.getFieldTypesEntity() != null)
            this.setFieldTypesDTO(new FieldTypesDTO(fieldsEntity.getFieldTypesEntity()));
    }

    /** 
     * Constructor * @param code 
     * @param name 
     */
    public FieldsDTO(Long code, String name, Long _displayType) {
        this.setCode(new FieldsEntityKey(code));
        this.setName(name);
        this.setDisplayedType(_displayType);
    }

    /** 
     * * @param fldDesc 
     */
    public void setFldDesc(String fldDesc) {
        this.fldDesc = fldDesc;
    }

    /** 
     * * @return 
     */
    public String getFldDesc() {
        return fldDesc;
    }

    /** 
     * * @param sqlStatement 
     */
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    /** 
     * * @return 
     */
    public String getSqlStatement() {
        return sqlStatement;
    }

    /** 
     * * @param fldtypeCode 
     */
    public void setFldtypeCode(Long fldtypeCode) {
        this.fldtypeCode = fldtypeCode;
    }

    /** 
     * * @return 
     */
    public Long getFldtypeCode() {
        return fldtypeCode;
    }

    /** 
     * * @param fieldTypesDTO 
     */
    public void setFieldTypesDTO(IFieldTypesDTO fieldTypesDTO) {
        this.fieldTypesDTO = fieldTypesDTO;
    }

    /** 
     * * @return 
     */
    public IFieldTypesDTO getFieldTypesDTO() {
        return fieldTypesDTO;
    }

    public void setRequestDataDTOList(List<IRequestDataDTO> requestDataDTOList) {
        this.requestDataDTOList = requestDataDTOList;
    }

    public List<IRequestDataDTO> getRequestDataDTOList() {
        return requestDataDTOList;
    }

    public void setDisplayedType(Long displayedType) {
        this.displayedType = displayedType;
    }

    public Long getDisplayedType() {
        return displayedType;
    }

    public void setRelatedFieldsDTOList(List<IRelatedFieldsDTO> relatedFieldsDTOList) {
        this.relatedFieldsDTOList = relatedFieldsDTOList;
    }

    public List<IRelatedFieldsDTO> getRelatedFieldsDTOList() {
        return relatedFieldsDTOList;
    }

    public void setHasRelatedFields(boolean hasRelatedFields) {
        this.hasRelatedFields = hasRelatedFields;
    }

    public boolean isHasRelatedFields() {
        return hasRelatedFields;
    }

    public void setFieldValueDTOList(List<IFieldValueDTO> fieldValueDTOList) {
        this.fieldValueDTOList = fieldValueDTOList;
    }

    public List<IFieldValueDTO> getFieldValueDTOList() {
        return fieldValueDTOList;
    }

    public void setMisOperationDetailsDTOList(List<IMisOperationDetailsDTO> misOperationDetailsDTOList) {
        this.misOperationDetailsDTOList = misOperationDetailsDTOList;
    }

    public List<IMisOperationDetailsDTO> getMisOperationDetailsDTOList() {
        return misOperationDetailsDTOList;
    }
}
