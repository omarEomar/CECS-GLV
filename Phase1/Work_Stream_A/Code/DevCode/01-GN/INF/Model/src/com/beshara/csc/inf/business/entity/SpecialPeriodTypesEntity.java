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
@NamedQueries( { @NamedQuery(name = "SpecialPeriodTypesEntity.findAll",
                             query = "select o from SpecialPeriodTypesEntity o "),
                 @NamedQuery(name = "SpecialPeriodTypesEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.SpecialPeriodTypesDTO(o.spcprdtypeCode,o.spcprdtypeName) from SpecialPeriodTypesEntity o") })
@Table(name = "INF_SPECIAL_PERIOD_TYPES")
@IdClass(ISpecialPeriodTypesEntityKey.class)
public class SpecialPeriodTypesEntity extends BasicEntity{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    
    @Id
    @Column(name = "SPCPRDTYPE_CODE", nullable = false)
    private Long spcprdtypeCode; 
    @Column(name = "SPCPRDTYPE_NAME", nullable = false)
    private String spcprdtypeName;

    public SpecialPeriodTypesEntity() {
        super();
    }
    public void setSpcprdtypeCode(Long spcprdtypeCode) {
        this.spcprdtypeCode = spcprdtypeCode;
    }

    public Long getSpcprdtypeCode() {
        return spcprdtypeCode;
    }

    public void setSpcprdtypeName(String spcprdtypeName) {
        this.spcprdtypeName = spcprdtypeName;
    }

    public String getSpcprdtypeName() {
        return spcprdtypeName;
    }
}
