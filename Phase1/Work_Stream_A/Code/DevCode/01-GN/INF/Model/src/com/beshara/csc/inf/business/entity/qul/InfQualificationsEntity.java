package com.beshara.csc.inf.business.entity.qul;


//import com.beshara.csc.hr.crs.business.entity.AssociateQualificationEntity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author A.ElMasry
 * Nov.2014
 */
@Entity
@Table(name = "NL_QUL_QUALIFICATIONS")
public class InfQualificationsEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Column(name = "QUAL_DTL_CODE")
    private String qualificationDtlCode;

    /**

     */
    @Id
    @Column(name = "QUALIFICATION_KEY", nullable = false)
    private Long qualificationKey;

    /**

     */
    @Column(name = "QUALIFICATION_NAME", nullable = false)
    private String qualificationName;


    /**
     */
    public InfQualificationsEntity() {
    }


    /**
     * @return
     */
    public Long getQualificationKey() {
        return qualificationKey;
    }

    /**
     * @param qualificationKey
     */
    public void setQualificationKey(Long qualificationKey) {
        this.qualificationKey = qualificationKey;
    }


    /**
     * @return
     */
    public String getQualificationName() {
        return qualificationName;
    }


    /**
     * @param qualificationName
     */
    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }


    public void setQualificationDtlCode(String qualificationDtlCode) {
        this.qualificationDtlCode = qualificationDtlCode;
    }

    public String getQualificationDtlCode() {
        return qualificationDtlCode;
    }


}
