package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "InfEservicesTypesEntity.findAll",
                             query = "select o from InfEservicesTypesEntity o"),               
                 @NamedQuery(name = "IInfEservicesDocumentTypesEntityKey.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.InfEservicesDocumentTypesDTO(o.servicesId,o.servicesName) from InfEservicesTypesEntity o order by o.servicesName")  })
@Table(name = "INF_ESERVICES_TYPES")
@IdClass(IInfEservicesDocumentTypesEntityKey.class)
public class InfEservicesTypesEntity extends BasicEntity implements Serializable {


    @SuppressWarnings("compatibility:-6315318877712492660")
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "SERVICES_ID", nullable = false)
    private Long servicesId; 
    @Column(name = "SERVICES_NAME", nullable = false)
    private String servicesName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    public InfEservicesTypesEntity() {
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesName() {
        return servicesName;
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
