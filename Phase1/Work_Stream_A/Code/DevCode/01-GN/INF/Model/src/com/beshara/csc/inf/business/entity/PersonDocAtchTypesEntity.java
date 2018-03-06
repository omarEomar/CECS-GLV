package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries( { @NamedQuery(name = "PersonDocAtchTypesEntity.findAll",
                             query = "select o from PersonDocAtchTypesEntity o") })
@Table(name = "INF_PERSON_DOC_ATCH_TYPES")
@IdClass(IPersonDocAtchTypesEntityKey.class)
public class PersonDocAtchTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
    @Id
    @Column(name = "DOCATCTYPE_CODE", nullable = false)
    private Long docAtcTypeCode;
    @Column(name = "DOCATCTYPE_NAME")
    private String docAtcTypeName;
   
    public PersonDocAtchTypesEntity() {
     
    }

    public void setDocAtcTypeCode(Long docAtcTypeCode) {
        this.docAtcTypeCode = docAtcTypeCode;
    }

    public Long getDocAtcTypeCode() {
        return docAtcTypeCode;
    }

    public void setDocAtcTypeName(String docAtcTypeName) {
        this.docAtcTypeName = docAtcTypeName;
    }

    public String getDocAtcTypeName() {
        return docAtcTypeName;
    }
}
