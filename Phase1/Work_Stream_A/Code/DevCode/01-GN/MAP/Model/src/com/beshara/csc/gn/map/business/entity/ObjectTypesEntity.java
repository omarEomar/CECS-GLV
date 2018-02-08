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
@NamedQueries( { @NamedQuery(name = "ObjectTypesEntity.findAll", 
                             query = "select o from ObjectTypesEntity o order by o.objtypeName")
        , 
        @NamedQuery(name = "ObjectTypesEntity.findNewId", query = "select MAX(o.objtypeCode) from ObjectTypesEntity o")
        , 
        @NamedQuery(name = "ObjectTypesEntity.getAllByName", query = "select o from ObjectTypesEntity o where o.objtypeName like :name")
        , 
        @NamedQuery(name = "ObjectTypesEntity.getCodeName", query = "select new com.beshara.csc.gn.map.business.dto.ObjectTypesDTO(o.objtypeCode, o.objtypeName) from ObjectTypesEntity o order by o.objtypeName")
        } )
@Table(name = "GN_MAP_OBJECT_TYPES")
@IdClass(IObjectTypesEntityKey.class)
public class

ObjectTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "OBJTYPE_CODE", nullable = false)
    private Long objtypeCode;
    @Column(name = "OBJTYPE_NAME", nullable = false)
    private String objtypeName;
    @OneToMany(mappedBy = "objectTypesEntity")
    private List<DataEntity> dataEntityList;

    public ObjectTypesEntity() {
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
     * @return String
     */
    public String getObjtypeName() {
        return objtypeName;
    }

    /**
     * 
     * @param objtypeName
     */
    public void setObjtypeName(String objtypeName) {
        this.objtypeName = objtypeName;
    }

    /**
     * 
     * @return list DataEntity
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
        dataEntity.setObjectTypesEntity(this);
        return dataEntity;
    }

    /**
     * 
     * @param dataEntity
     * @return DataEntity
     */
    public DataEntity removeDataEntity(DataEntity dataEntity) {
        getDataEntityList().remove(dataEntity);
        dataEntity.setObjectTypesEntity(null);
        return dataEntity;
    }
}
