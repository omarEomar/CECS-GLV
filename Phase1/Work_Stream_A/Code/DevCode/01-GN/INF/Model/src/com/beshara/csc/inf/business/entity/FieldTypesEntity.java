package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of FieldTypes Entity.
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
@NamedQueries( { @NamedQuery(name = "FieldTypesEntity.findAll", 
                             query = "select o from FieldTypesEntity o order by o.fldtypeCode")
        , 
        @NamedQuery(name = "FieldTypesEntity.findNewId", query = "select MAX(o.fldtypeCode) from FieldTypesEntity o")
        , 
        @NamedQuery(name = "FieldTypesEntity.findCodeName", query = "select new com.beshara.csc.inf.business.dto.FieldTypesDTO(o.fldtypeCode,o.fldtypeName) from FieldTypesEntity o order by o.fldtypeName")
        , 
        @NamedQuery(name = "FieldTypesEntity.findName", query = "select o from FieldTypesEntity o where o.fldtypeName like :name order by o.fldtypeCode")
        } )

@Table(name = "INF_FIELD_TYPES")
@IdClass(IFieldTypesEntityKey.class)
public class FieldTypesEntity extends  BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Id
    @Column(name = "FLDTYPE_CODE", nullable = false)
    private Long fldtypeCode;
    @Column(name = "FLDTYPE_NAME", nullable = false)
    private String fldtypeName;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @OneToMany(mappedBy = "fieldTypesEntity", 
               cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<FieldsEntity> fieldsEntityList;

    public FieldTypesEntity() {
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
    public Long getFldtypeCode() {
        return fldtypeCode;
    }

    /**
     * 
     * @param fldtypeCode
     */
    public void setFldtypeCode(Long fldtypeCode) {
        this.fldtypeCode = fldtypeCode;
    }

    /**
     * 
     * @return
     */
    public String getFldtypeName() {
        return fldtypeName;
    }

    /**
     * 
     * @param fldtypeName
     */
    public void setFldtypeName(String fldtypeName) {
        this.fldtypeName = fldtypeName;
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
    public List<FieldsEntity> getFieldsEntityList() {
        return fieldsEntityList;
    }

    /**
     * 
     * @param fieldsEntityList
     */
    public void setFieldsEntityList(List<FieldsEntity> fieldsEntityList) {
        this.fieldsEntityList = fieldsEntityList;
    }

    /**
     * 
     * @param fieldsEntity
     * @return
     */
    public FieldsEntity addFieldsEntity(FieldsEntity fieldsEntity) {
        getFieldsEntityList().add(fieldsEntity);
        fieldsEntity.setFieldTypesEntity(this);
        return fieldsEntity;
    }

    /**
     * 
     * @param fieldsEntity
     * @return
     */
    public FieldsEntity removeFieldsEntity(FieldsEntity fieldsEntity) {
        getFieldsEntityList().remove(fieldsEntity);
        fieldsEntity.setFieldTypesEntity(null);
        return fieldsEntity;
    }


}

