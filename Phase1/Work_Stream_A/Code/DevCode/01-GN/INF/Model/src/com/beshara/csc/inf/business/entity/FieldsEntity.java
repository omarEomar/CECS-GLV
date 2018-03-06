package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of Fields Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Abdul Mejid  30-Oct-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Entity
@NamedQueries( { @NamedQuery(name = "FieldsEntity.findAll", 
                             query = "select o from FieldsEntity o order by o.fldCode ")
        , 
        @NamedQuery(name = "FieldsEntity.findNewId", query = "select MAX(o.fldCode) from FieldsEntity o")
        , 
        @NamedQuery(name = "FieldsEntity.findCodeName", query = "select new com.beshara.csc.inf.business.dto.FieldsDTO(o.fldCode,o.fldName) from FieldsEntity o order by o.fldName")
        , 
        @NamedQuery(name = "FieldsEntity.findName", query = "select o from FieldsEntity o where o.fldName like :name order by o.fldCode ")
        , 
        @NamedQuery(name = "FieldsEntity.getRelatedFields", query = "select o.fieldEntity from RelatedFieldsEntity o where o.fldCodeReferenced = :fieldCode order by o.referOrder")
        } )
@Table(name = "INF_FIELDS")
@IdClass(IFieldsEntityKey.class)
public class FieldsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Id
    @Column(name = "FLD_CODE", nullable = false)
    private Long fldCode;
    @Column(name = "FLD_DESC")
    private String fldDesc;
    @Column(name = "FLD_NAME", nullable = false)
    private String fldName;
    @Column(name = "DISPLAYED_TYPE", nullable = false)
    private Long displayedType;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "SQL_STATEMENT")
    private String sqlStatement;
    @ManyToOne
    @JoinColumn(name = "FLDTYPE_CODE", referencedColumnName = "FLDTYPE_CODE")
    private FieldTypesEntity fieldTypesEntity;
//    @OneToMany(mappedBy = "fieldsEntity")
//    private //, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    List<RequestDataEntity> requestDataEntityList;
    @OneToMany(mappedBy = "fieldEntity")
    private List<RelatedFieldsEntity> relatedFieldsEntityList;

    public FieldsEntity() {
    }

    /**
     * 
     * @return
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * 
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     * @return
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * @return
     */
    public Long getFldCode() {
        return fldCode;
    }

    /**
     * 
     * @param fldCode
     */
    public void setFldCode(Long fldCode) {
        this.fldCode = fldCode;
    }

    /**
     * 
     * @return
     */
    public String getFldDesc() {
        return fldDesc;
    }

    /**
     * 
     * @param fldDesc
     */
    public void setFldDesc(String fldDesc) {
        this.fldDesc = fldDesc;
    }

    /**
     * 
     * @return
     */
    public String getFldName() {
        return fldName;
    }

    /**
     * 
     * @param fldName
     */
    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    /**
     * 
     * @return
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * 
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * 
     * @return
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * 
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * 
     * @return
     */
    public String getSqlStatement() {
        return sqlStatement;
    }

    /**
     * 
     * @param sqlStatement
     */
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    /**
     * 
     * @return
     */
    public FieldTypesEntity getFieldTypesEntity() {
        return fieldTypesEntity;
    }

    /**
     * 
     * @param fieldTypesEntity
     */
    public void setFieldTypesEntity(FieldTypesEntity fieldTypesEntity) {
        this.fieldTypesEntity = fieldTypesEntity;
    }

//    public void setRequestDataEntityList(List<RequestDataEntity> requestDataEntityList) {
//        this.requestDataEntityList = requestDataEntityList;
//    }
//
//    public List<RequestDataEntity> getRequestDataEntityList() {
//        return requestDataEntityList;
//    }


    public void setDisplayedType(Long displayedType) {
        this.displayedType = displayedType;
    }

    public Long getDisplayedType() {
        return displayedType;
    }

    public void setRelatedFieldsEntityList(List<RelatedFieldsEntity> relatedFieldsEntityList) {
        this.relatedFieldsEntityList = relatedFieldsEntityList;
    }

    public List<RelatedFieldsEntity> getRelatedFieldsEntityList() {
        return relatedFieldsEntityList;
    }
}
