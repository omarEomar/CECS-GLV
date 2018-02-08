package com.beshara.csc.gn.map.business.dto;

import java.sql.Timestamp;

public interface IMapDataExceptionDTO {
    /**
     * @return Long
     */
    public Long getObjtypeCode();

    /**
     * @return Long
     */
    public Long getSoc1Code();

    /**
     * @return Long
     */
    public Long getSoc2Code();

    /**
     * @return String
     */
    public String getSqlStatement();

    /**
     * @return Long
     */
    public Long getCreatedBy();

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate();

    /**
     * @return Long
     */
    public Long getLastUpdatedBy();

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate();

    /**
     * @return Long
     */
    public Long getAuditStatus();

    /**
     * @return Long
     */
    public Long getTabrecSerial();

    /**
     * @param objtypeCode
     */
    public void setObjtypeCode(Long objtypeCode);

    /**
     * @param soc1Code
     */
    public void setSoc1Code(Long soc1Code);

    /**
     * @param soc2Code
     */
    public void setSoc2Code(Long soc2Code);

    /**
     * @param sqlStatement
     */
    public void setSqlStatement(String sqlStatement);

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy);

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate);

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy);

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate);

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus);

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial);
}
