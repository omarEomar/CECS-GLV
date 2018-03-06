package com.beshara.csc.inf.business.dto;


import java.sql.Date;

public class KwtBasicWrkDataDTO  extends InfDTO implements IKwtBasicWrkDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long realCivilId;
    private Long trxTypeCode;
    private Date applyDate;
    private String jobCode;
    private String otherJobCode;
    private String wrkCode;
    private Long minCode;
    private Long changeType;
    private boolean changeJobCode;
    private boolean changeOtherJobCode;
    private boolean changeWrkCode;
    private boolean changeJobCodeAndOtherJobCode;
    public KwtBasicWrkDataDTO() {
        super();
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setTrxTypeCode(Long trxTypeCode) {
        this.trxTypeCode = trxTypeCode;
    }

    public Long getTrxTypeCode() {
        return trxTypeCode;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    
    public void setOtherJobCode(String otherJobCode) {
        this.otherJobCode = otherJobCode;
    }

    public String getOtherJobCode() {
        return otherJobCode;
    }
    public void setChangeJobCode(boolean changeJobCode) {
        this.changeJobCode = changeJobCode;
    }

    public boolean isChangeJobCode() {
        return changeJobCode;
    }

    public void setChangeOtherJobCode(boolean changeOtherJobCode) {
        this.changeOtherJobCode = changeOtherJobCode;
    }

    public boolean isChangeOtherJobCode() {
        return changeOtherJobCode;
    }

    public void setChangeWrkCode(boolean changeWrkCode) {
        this.changeWrkCode = changeWrkCode;
    }

    public boolean isChangeWrkCode() {
        return changeWrkCode;
    }

    public void setChangeType(Long changeType) {
        this.changeType = changeType;
    }

    public Long getChangeType() {
        return changeType;
    }

    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    public String getWrkCode() {
        return wrkCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setChangeJobCodeAndOtherJobCode(boolean changeJobCodeAndOtherJobCode) {
        this.changeJobCodeAndOtherJobCode = changeJobCodeAndOtherJobCode;
    }

    public boolean isChangeJobCodeAndOtherJobCode() {
        return changeJobCodeAndOtherJobCode;
    }
}
