package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.BasicEntity;

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


@Entity
@NamedQueries( { @NamedQuery(name = "DataEntity.findAll", 
                             query = "select o from DataEntity o")
        , 
        @NamedQuery(name = "DataEntity.findNewId", query = "select MAX(o.objtypeCode) from DataEntity o")
        , 
        @NamedQuery(name = "DataEntity.findByObjType", query = "select new com.beshara.csc.gn.map.business.dto.SocietiesDTO(data.societiesEntity.socCode,data.societiesEntity.socName) from DataEntity data where data.objtypeCode=:objtypeCode")
        , 
        @NamedQuery(name = "DataEntity.getSQLQuery", query = "select data.sqlStatement from DataEntity data where data.objtypeCode=:objtypeCode and data.socCode=:socCode")
        , 
        @NamedQuery(name = "DataEntity.getTreeFlag", query = "select data.displayInTreeFlag from DataEntity data where data.objtypeCode=:objtypeCode and data.socCode=:socCode")
                 
        } )
@Table(name = "GN_MAP_DATA")
@IdClass(IDataEntityKey.class)
public class DataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "OBJTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long objtypeCode;
    @Id
    @Column(name = "SOC_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long socCode;
    @Column(name = "SQL_STATEMENT")
    private String sqlStatement;
    @OneToMany(mappedBy = "dataEntity")
    private List<MappedDataEntity> mappedDataEntityList;
    @OneToMany(mappedBy = "dataEntity1")
    private List<MappedDataEntity> mappedDataEntityList1;
    @ManyToOne
    @JoinColumn(name = "OBJTYPE_CODE", referencedColumnName = "OBJTYPE_CODE")
    private ObjectTypesEntity objectTypesEntity;
    @ManyToOne
    @JoinColumn(name = "SOC_CODE", referencedColumnName = "SOC_CODE")
    private SocietiesEntity societiesEntity;

    @Column(name = "DISPLAY_IN_TREE_FLAG")
    private Long displayInTreeFlag;
    
    public DataEntity() {
    }


    /**
     * 
     * @return Long
     */
    public Long getObjtypeCode() {
        return objtypeCode;
    }

    /**
     * 
     * @param objtypeCode
     */
    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }

    /**
     * 
     * @return Long
     */
    public Long getSocCode() {
        return socCode;
    }

    /**
     * 
     * @param socCode
     */
    public void setSocCode(Long socCode) {
        this.socCode = socCode;
    }

    /**
     * 
     * @return String
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
     * @return list MappedDataEntity
     */
    public List<MappedDataEntity> getMappedDataEntityList() {
        return mappedDataEntityList;
    }

    /**
     * 
     * @param mappedDataEntityList
     */
    public void setMappedDataEntityList(List<MappedDataEntity> mappedDataEntityList) {
        this.mappedDataEntityList = mappedDataEntityList;
    }

    /**
     * 
     * @param mappedDataEntity
     * @return MappedDataEntity
     */
    public MappedDataEntity addMappedDataEntity(MappedDataEntity mappedDataEntity) {
        getMappedDataEntityList().add(mappedDataEntity);
        mappedDataEntity.setDataEntity(this);
        return mappedDataEntity;
    }

    /**
     * 
     * @param mappedDataEntity
     * @return MappedDataEntity
     */
    public MappedDataEntity removeMappedDataEntity(MappedDataEntity mappedDataEntity) {
        getMappedDataEntityList().remove(mappedDataEntity);
        mappedDataEntity.setDataEntity(null);
        return mappedDataEntity;
    }

    /**
     * 
     * @return List MappedDataEntity
     */
    public List<MappedDataEntity> getMappedDataEntityList1() {
        return mappedDataEntityList1;
    }

    /**
     * 
     * @param mappedDataEntityList1
     */
    public void setMappedDataEntityList1(List<MappedDataEntity> mappedDataEntityList1) {
        this.mappedDataEntityList1 = mappedDataEntityList1;
    }

    /**
     * 
     * @param mappedDataEntity
     * @return MappedDataEntity
     */
    public MappedDataEntity addMappedDataEntity1(MappedDataEntity mappedDataEntity) {
        getMappedDataEntityList1().add(mappedDataEntity);
        mappedDataEntity.setDataEntity1(this);
        return mappedDataEntity;
    }

    /**
     * 
     * @param mappedDataEntity
     * @return MappedDataEntity
     */
    public MappedDataEntity removeMappedDataEntity1(MappedDataEntity mappedDataEntity) {
        getMappedDataEntityList1().remove(mappedDataEntity);
        mappedDataEntity.setDataEntity1(null);
        return mappedDataEntity;
    }

    /**
     * 
     * @return ObjectTypesEntity
     */
    public ObjectTypesEntity getObjectTypesEntity() {
        return objectTypesEntity;
    }

    public void setObjectTypesEntity(ObjectTypesEntity objectTypesEntity) {
        this.objectTypesEntity = objectTypesEntity;
    }

    /**
     * 
     * @return SocietiesEntity
     */
    public SocietiesEntity getSocietiesEntity() {
        return societiesEntity;
    }

    /**
     * 
     * @param societiesEntity
     */
    public void setSocietiesEntity(SocietiesEntity societiesEntity) {
        this.societiesEntity = societiesEntity;
    }

    public void setDisplayInTreeFlag(Long displayInTreeFlag) {
        this.displayInTreeFlag = displayInTreeFlag;
    }

    public Long getDisplayInTreeFlag() {
        return displayInTreeFlag;
    }
}
