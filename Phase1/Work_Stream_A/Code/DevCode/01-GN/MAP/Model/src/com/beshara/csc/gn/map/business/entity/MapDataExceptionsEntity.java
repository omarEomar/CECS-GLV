package com.beshara.csc.gn.map.business.entity;


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
 * This Class Manipulate the Persistence Methods of MapDataExceptions Entity.
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
@NamedQueries( { @NamedQuery(name = "MapDataExceptionsEntity.findAll",
                             query = "select o from MapDataExceptionsEntity o"),
                 @NamedQuery(name = "MapDataExceptionsEntity.findNewId",
                             query = "select MAX(o.objtypeCode) from MapDataExceptionsEntity o"),
                 @NamedQuery(name = "MapDataExceptionsEntity.getSQLQuery",
                             query = "select data.sqlStatement from MapDataExceptionsEntity data where data.objtypeCode=:objtypeCode and data.soc1Code=:soc1Code and data.soc2Code=:soc2Code") })
@Table(name = "GN_MAP_DATA_EXCEPTIONS")

@IdClass(IMapDataExceptionsEntityKey.class)
public class MapDataExceptionsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "OBJTYPE_CODE", nullable = false)
    private Long objtypeCode;
    @Id
    @Column(name = "SOC1_CODE", nullable = false)
    private Long soc1Code;
    @Id
    @Column(name = "SOC2_CODE", nullable = false)
    private Long soc2Code;
    @Column(name = "SQL_STATEMENT", nullable = false)
    private String sqlStatement;
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    //@ManyToOne
    //@JoinColumn(name="OBJTYPE_CODE", referencedColumnName="OBJTYPE_CODE")
    //private MapObjectTypesEntity mapObjectTypesEntity;
    //@ManyToOne
    //@JoinColumn(name="SOC2_CODE", referencedColumnName="SOC_CODE")
    //private MapSocietiesEntity mapSocietiesEntity;
    //@ManyToOne
    //@JoinColumn(name="SOC1_CODE", referencedColumnName="SOC_CODE")
    //private MapSocietiesEntity mapSocietiesEntity;


    /**
     * MapDataExceptionsEntity Default Constructor
     */
    public MapDataExceptionsEntity() {
    }


    /**
     * @return Long
     */
    public Long getObjtypeCode() {
        return objtypeCode;
    }

    /**
     * @return Long
     */
    public Long getSoc1Code() {
        return soc1Code;
    }

    /**
     * @return Long
     */
    public Long getSoc2Code() {
        return soc2Code;
    }

    /**
     * @return String
     */
    public String getSqlStatement() {
        return sqlStatement;
    }

    /**
     * @return Long
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Long
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
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
     * @param objtypeCode
     */
    public void setObjtypeCode(Long objtypeCode) {
        this.objtypeCode = objtypeCode;
    }

    /**
     * @param soc1Code
     */
    public void setSoc1Code(Long soc1Code) {
        this.soc1Code = soc1Code;
    }

    /**
     * @param soc2Code
     */
    public void setSoc2Code(Long soc2Code) {
        this.soc2Code = soc2Code;
    }

    /**
     * @param sqlStatement
     */
    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
