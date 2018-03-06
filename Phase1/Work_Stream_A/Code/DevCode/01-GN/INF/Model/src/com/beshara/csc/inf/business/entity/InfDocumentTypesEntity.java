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
@NamedQueries( { @NamedQuery(name = "InfDocumentTypesEntity.findAll",
                             query = "select o from InfDocumentTypesEntity o"),
                 @NamedQuery(name = "InfDocumentTypesEntity.findNewId",
                             query = "select MAX(o.doctypeCode) from InfDocumentTypesEntity o"),
                 @NamedQuery(name = "InfDocumentTypesEntity.searchByName",
                             query = "select o from InfDocumentTypesEntity o where o.doctypeName like :name"),
                 @NamedQuery(name = "InfDocumentTypesEntity.getByName",
                             query = "select o from InfDocumentTypesEntity o where o.doctypeName = :name"),
                 @NamedQuery(name = "InfDocumentTypesEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.InfDocumentTypesDTO(o.doctypeCode,o.doctypeName) from InfDocumentTypesEntity o order by o.doctypeName") })
@Table(name = "INF_DOCUMENT_TYPES")
@IdClass(IInfDocumentTypesEntityKey.class)
public class InfDocumentTypesEntity extends BasicEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DOCTYPE_CODE", nullable = false)
    private Long doctypeCode;
    @Column(name = "DOCTYPE_NAME", nullable = false, length = 400)
    private String doctypeName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    public InfDocumentTypesEntity() {
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public String getDoctypeName() {
        return doctypeName;
    }

    public void setDoctypeName(String doctypeName) {
        this.doctypeName = doctypeName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }
}
