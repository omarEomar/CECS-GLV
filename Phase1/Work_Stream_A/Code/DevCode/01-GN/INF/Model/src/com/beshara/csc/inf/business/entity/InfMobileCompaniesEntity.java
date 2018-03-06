package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

@Entity
@NamedQueries( { @NamedQuery(name = "InfMobileCompaniesEntity.findAll",
                             query = "select o from InfMobileCompaniesEntity o"),
                 @NamedQuery(name = "InfMobileCompaniesEntity.findNewId",
                             query = "select MAX(o.mobCompanyCode) from InfMobileCompaniesEntity o"),
                 @NamedQuery(name = "InfMobileCompaniesEntity.searchByCode",
                             query = "select o from InfMobileCompaniesEntity o where o.mobCompanyCode=:code"),

        @NamedQuery(name = "InfMobileCompaniesEntity.searchByName",
                    query = "select o from InfMobileCompaniesEntity o where o.mobCompanyName like :name order by o.mobCompanyName") })
@Table(name = "INF_MOBILE_COMPANIES")
@IdClass(IInfMobileCompaniesEntityKey.class)

public class InfMobileCompaniesEntity extends BasicEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MOBCOMPANY_CODE", nullable = false)
    private Long mobCompanyCode;
    @Column(name = "MOBCOMPANY_NAME", nullable = false)
    private String mobCompanyName;

    public InfMobileCompaniesEntity() {
    }


    public void setMobCompanyCode(Long mobCompanyCode) {
        this.mobCompanyCode = mobCompanyCode;
    }

    public Long getMobCompanyCode() {
        return mobCompanyCode;
    }

    public void setMobCompanyName(String mobCompanyName) {
        this.mobCompanyName = mobCompanyName;
    }

    public String getMobCompanyName() {
        return mobCompanyName;
    }
}
