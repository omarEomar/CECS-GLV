package com.beshara.csc.gn.map.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "SocietiesEntity.findAll", 
                             query = "select o from SocietiesEntity o order by o.socName")
        , 
        @NamedQuery(name = "SocietiesEntity.findNewId", query = "select MAX(o.socCode) from SocietiesEntity o")
        , 
        @NamedQuery(name = "SocietiesEntity.findByObjType", query = "select o from SocietiesEntity o where o.socCode in (select s.socCode from DataEntity s where s.socCode = o.socCode and s.objtypeCode=:objtypeCode)")
        , 
        @NamedQuery(name = "SocietiesEntity.getAllByName", query = "select o from SocietiesEntity o where o.socName like :name"),
        @NamedQuery(name = "SocietiesEntity.getAllByFlag", 
                             query = "select o from SocietiesEntity o where o.SocietiesStatus=:centerOrSystemFlag order by o.socName"),
        @NamedQuery(name = "SocietiesEntity.searchByMinCode", query = "select o from SocietiesEntity o where o.minCode = :minCode")
        } )
@Table(name = "GN_MAP_SOCIETIES")
@IdClass(ISocietiesEntityKey.class)
public class SocietiesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SOC_CODE", nullable = false)
    private Long socCode;
    @Column(name = "SOC_NAME", nullable = false)
    private String socName;
    @OneToMany(mappedBy = "societiesEntity")
    private List<DataEntity> dataEntityList;
    
    
    @Column(name = "MIN_CODE", nullable = true)
    private Long minCode;
    @Column(name = "CENTER_OR_SYSTEM", nullable = false)
    private Long SocietiesStatus;

    public SocietiesEntity() {
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
    public String getSocName() {
        return socName;
    }

    /**
     * 
     * @param socName
     */
    public void setSocName(String socName) {
        this.socName = socName;
    }

    /**
     * 
     * @return List DataEntity
     */
    public List<DataEntity> getDataEntityList() {
        return dataEntityList;
    }

    /**
     * 
     * @param dataEntityList
     */
    public void setDataEntityList(List<DataEntity> dataEntityList) {
        this.dataEntityList = dataEntityList;
    }

    /**
     * 
     * @param dataEntity
     * @return DataEntity
     */
    public DataEntity addDataEntity(DataEntity dataEntity) {
        getDataEntityList().add(dataEntity);
        dataEntity.setSocietiesEntity(this);
        return dataEntity;
    }

    /**
     * 
     * @param dataEntity
     * @return DataEntity
     */
    public DataEntity removeDataEntity(DataEntity dataEntity) {
        getDataEntityList().remove(dataEntity);
        dataEntity.setSocietiesEntity(null);
        return dataEntity;
    }

    

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setSocietiesStatus(Long SocietiesStatus) {
        this.SocietiesStatus = SocietiesStatus;
    }

    public Long getSocietiesStatus() {
        return SocietiesStatus;
    }
}
