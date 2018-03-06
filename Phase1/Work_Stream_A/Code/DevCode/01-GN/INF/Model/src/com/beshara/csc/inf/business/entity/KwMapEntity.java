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
import javax.persistence.QueryHint;
import javax.persistence.Table;


@Entity
@NamedQueries
//        , 
//        @NamedQuery(name = "KwMapEntity.findNewId", query = "select MAX(o.mapCode) from KwMapEntity o")
( { @NamedQuery(name = "KwMapEntity.findAll", 
                query = "select o from KwMapEntity o order by o.mapCode")
        , 
        @NamedQuery(name = "KwMapEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.KwMapDTO(o.mapCode,o.mapName) from KwMapEntity o order by o.mapName")
        , @NamedQuery(name="KwMapEntity.getFirstLevel",query="select o from   KwMapEntity  o WHERE o.treeLevel=:treeLevel")
        ,@NamedQuery(name = "KwMapEntity.getChildrenList", query = "select o from KwMapEntity o where o.kwMapEntity.mapCode = :mapCode") 
        , @NamedQuery(name="KwMapEntity.getTotalNumber",query="select count(o.mapCode) from KwMapEntity o ")
              , 
        @NamedQuery(name = "KwMapEntity.searchByName", query = "select o from KwMapEntity o where o.mapName like :mapName order by o.mapName")
        , 
            @NamedQuery(name = "KwMapEntity.getById", query = "select o from KwMapEntity o where o.mapCode=:mapCode")

       , @NamedQuery(name = "KwMapEntity.searchByCode", query = "select o from KwMapEntity o where o.typeCode=:typeCode order by o.mapName")
       , @NamedQuery(name = "KwMapEntity.searchByTypeCode", query = "select o from KwMapEntity o where o.typeCode=:typeCode order by o.mapName")
     
      , @NamedQuery(name = "KwMapEntity.countChildren", query = "select count(o.mapCode) from KwMapEntity o where o.kwMapEntity.mapCode=:mapCode", 
                    hints = 
                    { @QueryHint(name = "toplink.refresh", value = "true")
                    } )
        } )
@Table(name = "INF_KW_MAP")
@IdClass(IKwMapEntityKey.class)
public class KwMapEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy; 
    @Column(name = "CREATED_DATE", nullable = false)
    private Timestamp createdDate;
    @Column(name = "FIRST_PARENT", nullable = false)
    private String firstParent;
    @Column(name = "LAST_UPDATED_BY")
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Timestamp lastUpdatedDate;
    @Column(name = "LEAF_FLAG", nullable = false)
    private Long leafFlag;
    @Id
    @Column(name = "MAP_CODE", nullable = false)
    private String mapCode;
    @Column(name = "MAP_NAME", nullable = false)
    private String mapName;
    @Column(name = "TREE_LEVEL", nullable = false)
    private Long treeLevel;
    @Column(name = "TYPE_CODE", nullable = false)
    private Long typeCode;
    @Column(name = "PARENT_MAP_CODE", nullable = true, insertable = false, 
            updatable = false)
    private String parentMapCode;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    @ManyToOne
    @JoinColumn(name = "PARENT_MAP_CODE", referencedColumnName = "MAP_CODE")
    private KwMapEntity kwMapEntity;
    
    @OneToMany(mappedBy = "kwMapEntity")
    private List<KwMapEntity> kwMapEntityList;

    public KwMapEntity() {
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getFirstParent() {
        return firstParent;
    }

    public void setFirstParent(String firstParent) {
        this.firstParent = firstParent;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(Long leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }


    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Long getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public KwMapEntity getKwMapEntity() {
        return kwMapEntity;
    }

    public void setKwMapEntity(KwMapEntity kwMapEntity) {
        this.kwMapEntity = kwMapEntity;
    }

    public List<KwMapEntity> getKwMapEntityList() {
        return kwMapEntityList;
    }

    public void setKwMapEntityList(List<KwMapEntity> kwMapEntityList) {
        this.kwMapEntityList = kwMapEntityList;
    }

    public KwMapEntity addKwMapEntity(KwMapEntity kwMapEntity) {
        getKwMapEntityList().add(kwMapEntity);
        kwMapEntity.setKwMapEntity(this);
        return kwMapEntity;
    }

    public KwMapEntity removeKwMapEntity(KwMapEntity kwMapEntity) {
        getKwMapEntityList().remove(kwMapEntity);
        kwMapEntity.setKwMapEntity(null);
        return kwMapEntity;
    }

    public void setParentMapCode(String parentMapCode) {
        this.parentMapCode = parentMapCode;
    }

    public String getParentMapCode() {
        return parentMapCode;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
