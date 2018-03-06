package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "DecisionMakerTypesEntity.findAll",
                             query = "select o from DecisionMakerTypesEntity o order by o.decmkrtypeName"),
                 @NamedQuery(name = "DecisionMakerTypesEntity.findNewId",
                             query = "select MAX(o.decmkrtypeCode) from DecisionMakerTypesEntity o"),
                 @NamedQuery(name = "DecisionMakerTypesEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.DecisionMakerTypesDTO(o.decmkrtypeCode, o.decmkrtypeName) from DecisionMakerTypesEntity o order by o.decmkrtypeName"),
                 @NamedQuery(name = "DecisionMakerTypesEntity.searchByName",
                             query = "select o from DecisionMakerTypesEntity o where o.decmkrtypeName like :decmkrtypeName order by o.decmkrtypeName"),
                 @NamedQuery(name = "DecisionMakerTypesEntity.searchByCode",
                             query = "select o from DecisionMakerTypesEntity o where o.decmkrtypeCode=:decmkrtypeCode order by o.decmkrtypeName")
                 })
@Table(name = "INF_DECISION_MAKER_TYPES")
@IdClass(IDecisionMakerTypesEntityKey.class)
// @EditedBy @author Aly Noor @since 06/26/2014 eidted to extends BasicEntity
// to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
public class DecisionMakerTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DECMKRTYPE_CODE", nullable = false)
    private Long decmkrtypeCode;
    @Column(name = "DECMKRTYPE_NAME", nullable = false)
    private String decmkrtypeName;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    //    @OneToMany(mappedBy = "decisionMakerTypesEntity")
    //    private List<DecisionsEntity> decisionsEntityList;

    public DecisionMakerTypesEntity() {
    }

    public Long getDecmkrtypeCode() {
        return decmkrtypeCode;
    }

    public void setDecmkrtypeCode(Long decmkrtypeCode) {
        this.decmkrtypeCode = decmkrtypeCode;
    }

    public String getDecmkrtypeName() {
        return decmkrtypeName;
    }

    public void setDecmkrtypeName(String decmkrtypeName) {
        this.decmkrtypeName = decmkrtypeName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    //    public List<DecisionsEntity> getDecisionsEntityList() {
    //        return decisionsEntityList;
    //    }
    //
    //    public void setDecisionsEntityList(List<DecisionsEntity> decisionsEntityList) {
    //        this.decisionsEntityList = decisionsEntityList;
    //    }

}

