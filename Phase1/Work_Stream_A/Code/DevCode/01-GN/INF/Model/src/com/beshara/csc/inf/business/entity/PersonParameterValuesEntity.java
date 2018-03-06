package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of PersonParameterValues Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Entity
@NamedQueries( { @NamedQuery(name = "PersonParameterValuesEntity.findAll", 
                             query = 
                             "select o from PersonParameterValuesEntity o")
        , 
        @NamedQuery(name = "PersonParameterValuesEntity.findNewId", query = "select MAX(o.civilId) from PersonParameterValuesEntity o")
        } )
@Table(name = "INF_PERSON_PARAMETER_VALUES")
@IdClass(IPersonParameterValuesEntityKey.class)
public class PersonParameterValuesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "PARAMETER_CODE", nullable = false)
    private Long parameterCode;
    @Column(name = "PARAM_VALUE", nullable = false)
    private String paramValue;
    @Column(name = "PARAM_VALUE_DATE", nullable = false)
    private Timestamp paramValueDate;
    @Column(name = "VALUE_DESC", nullable = true)
    private String valueDesc;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    //@ManyToOne 
    //@JoinColumn(name="PARAMETER_CODE", referencedColumnName="PARAMETER_CODE")
    //private GrsParametersEntity grsParametersEntity;
    //@ManyToOne 
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private KwtCitizensResidentsEntity kwtCitizensResidentsEntity;


    /**
     * PersonParameterValuesEntity Default Constructor
     */
    public PersonParameterValuesEntity() {
    }


    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Long
     */
    public Long getParameterCode() {
        return parameterCode;
    }

    /**
     * @return String
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getParamValueDate() {
        return paramValueDate;
    }

    /**
     * @return String
     */
    public String getValueDesc() {
        return valueDesc;
    }

    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param parameterCode
     */
    public void setParameterCode(Long parameterCode) {
        this.parameterCode = parameterCode;
    }

    /**
     * @param paramValue
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * @param paramValueDate
     */
    public void setParamValueDate(Timestamp paramValueDate) {
        this.paramValueDate = paramValueDate;
    }

    /**
     * @param valueDesc
     */
    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}
