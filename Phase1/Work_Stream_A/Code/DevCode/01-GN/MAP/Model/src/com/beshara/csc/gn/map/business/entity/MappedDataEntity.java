package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries

( { @NamedQuery(name = "MappedDataEntity.findAll", 
                query = "select o from MappedDataEntity o")
        , 
        @NamedQuery(name = "MappedDataEntity.findNewId", query = "select MAX(o.objtype1Code) from MappedDataEntity o")
        , 
        @NamedQuery(name = "MappedDataEntity.getMappedDataListByValue", query = 
                    "select data from MappedDataEntity data where data.objtype1Code=:objectCode1 and data.soc1Code=:socCode1 and data.soc1Value=:socValue1 and data.soc2Code=:socCode2")
        , 
        @NamedQuery(name = "MappedDataEntity.getMappedDataList", query = "select data from MappedDataEntity data where data.objtype1Code=:objectCode1 and data.soc1Code=:socCode1 and data.soc2Code=:socCode2")
        , 
        @NamedQuery(name = "MappedDataEntity.checkMappedDataExist", query = "select count(data.objtype1Code) from MappedDataEntity data where data.objtype1Code=:objCode1 and data.objtype2Code=:objCode2 and data.soc1Code=:socCode1 and data.soc2Code=:socCode2 and data.soc1Value=:socVal1 and data.soc2Value=:socVal2 ")
        , 
        @NamedQuery(name = "MappedDataEntity.getAllMappedDataList", query = "select data.soc1Value from MappedDataEntity data where data.objtype1Code=:objCode1 and data.soc1Code=:socCode1")
        , 
        @NamedQuery(name = "MappedDataEntity.ListByTypeAndSoc1AndSoc2", query = 
                    "select data.soc1Value from MappedDataEntity data where data.objtype1Code=:objCode1 " + 
                    "and data.objtype2Code=:objCode2 and data.soc1Code=:socCode1 and data.soc2Code=:socCode2")
        ,
        @NamedQuery(name = "MappedDataEntity.ListByTypeAndSoc1AndSoc2AndVal2", query = 
                    "select data from MappedDataEntity data where data.objtype1Code=:objCode1 " + 
                    "and data.objtype2Code=:objCode2 and data.soc1Code=:socCode1 and data.soc2Code=:socCode2 And data.soc2Value= :socValue2")
       ,
       @NamedQuery(name = "MappedDataEntity.ListByTypeAndSoc1AndSoc2AndVal1", query = 
                    "select o from MappedDataEntity o where o.objtype1Code=:objCode1 " + 
                    "and o.objtype2Code=:objCode2 and o.soc1Code=:socCode1 and o.soc2Code=:socCode2 And o.soc1Value= :socValue1")
        } )
@Table(name = "GN_MAP_MAPPED_DATA")
@IdClass(IMappedDataEntityKey.class)
public class MappedDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "MAP_STATUS", nullable = false)
    private Long mapStatus;
    @Id
    @Column(name = "OBJTYPE1_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long objtype1Code;
    @Id
    @Column(name = "OBJTYPE2_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long objtype2Code;
    @Id
    @Column(name = "SOC1_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long soc1Code;
    @Id
    @Column(name = "SOC1_VALUE", nullable = false)
    private String soc1Value;
    @Id
    @Column(name = "SOC2_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long soc2Code;
    @Id
    @Column(name = "SOC2_VALUE", nullable = false)
    private String soc2Value;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "SOC1_CODE", 
                                referencedColumnName = "SOC_CODE")
            , 
            @JoinColumn(name = "OBJTYPE1_CODE", referencedColumnName = "OBJTYPE_CODE")
            } )
    private DataEntity dataEntity;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "SOC2_CODE", 
                                referencedColumnName = "SOC_CODE")
            , 
            @JoinColumn(name = "OBJTYPE2_CODE", referencedColumnName = "OBJTYPE_CODE")
            } )
    private DataEntity dataEntity1;

    public MappedDataEntity() {
    }


    /**
     * 
     * @return Long
     */
    public Long getMapStatus() {
        return mapStatus;
    }

    /**
     * 
     * @param mapStatus
     */
    public void setMapStatus(Long mapStatus) {
        this.mapStatus = mapStatus;
    }

    /**
     * 
     * @return Long
     */
    public Long getObjtype1Code() {
        return objtype1Code;
    }

    /**
     * 
     * @param objtype1Code
     */
    public void setObjtype1Code(Long objtype1Code) {
        this.objtype1Code = objtype1Code;
    }

    /**
     * 
     * @return Long
     */
    public Long getObjtype2Code() {
        return objtype2Code;
    }

    /**
     * 
     * @param objtype2Code
     */
    public void setObjtype2Code(Long objtype2Code) {
        this.objtype2Code = objtype2Code;
    }

    /**
     * 
     * @return Long
     */
    public Long getSoc1Code() {
        return soc1Code;
    }

    /**
     * 
     * @param soc1Code
     */
    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    /**
     * 
     * @return String
     */
    public String getSoc1Value() {
        return soc1Value;
    }

    /**
     * 
     * @param soc1Value
     */
    public void setSoc1Value(String soc1Value) {
        this.soc1Value = soc1Value;
    }

    /**
     * 
     * @return Long
     */
    public Long getSoc2Code() {
        return soc2Code;
    }

    /**
     * 
     * @param soc2Code
     */
    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }

    /**
     * 
     * @return String
     */
    public String getSoc2Value() {
        return soc2Value;
    }

    /**
     * 
     * @param soc2Value
     */
    public void setSoc2Value(String soc2Value) {
        this.soc2Value = soc2Value;
    }

    /**
     * 
     * @return DataEntity
     */
    public DataEntity getDataEntity() {
        return dataEntity;
    }

    /**
     * 
     * @param dataEntity
     */
    public void setDataEntity(DataEntity dataEntity) {
        this.dataEntity = dataEntity;
    }

    /**
     * 
     * @return DataEntity
     */
    public DataEntity getDataEntity1() {
        return dataEntity1;
    }

    /**
     * 
     * @param dataEntity1
     */
    public void setDataEntity1(DataEntity dataEntity1) {
        this.dataEntity1 = dataEntity1;
    }
}
