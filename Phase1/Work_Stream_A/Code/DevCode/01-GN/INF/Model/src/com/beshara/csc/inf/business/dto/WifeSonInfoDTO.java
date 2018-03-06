package com.beshara.csc.inf.business.dto;

import java.sql.Date;
import java.sql.Timestamp;


public class WifeSonInfoDTO extends InfDTO implements IWifeSonInfoDTO {

    @SuppressWarnings("compatibility:-6853121857576994599")
    private static final long serialVersionUID = 1L;
    
    private Long empCivilId;
    private Long brancheCivilId;
    private short relationType;
    private Date marriageDate;
    private String brancheName;
    private Date birthDate;
    private IGenderTypesDTO genderTypesDTO;
    private IGenderCountryDTO genderCountryDTO;
    private String nationality;
    private Date deathDate;
    private short replyMsgCode;
    private String requestTime;
    private String replyMsg;
    private String responseTime;
    private String versionNumber;
    private Long noOfRequestRemaining;
    private String infoMsg;
    private String environmentDtls;
    private Timestamp allowanceDate;
    
    public WifeSonInfoDTO(){
    }


    public void setEmpCivilId(Long empCivilId) {
        this.empCivilId = empCivilId;
    }

    public Long getEmpCivilId() {
        return empCivilId;
    }

    public void setBrancheCivilId(Long brancheCivilId) {
        this.brancheCivilId = brancheCivilId;
    }

    public Long getBrancheCivilId() {
        return brancheCivilId;
    }

    public void setRelationType(short relationType) {
        this.relationType = relationType;
    }

    public short getRelationType() {
        return relationType;
    }

    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    public Date getMarriageDate() {
        return marriageDate;
    }

    public void setBrancheName(String brancheName) {
        this.brancheName = brancheName;
    }

    public String getBrancheName() {
        return brancheName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setReplyMsgCode(short replyMsgCode) {
        this.replyMsgCode = replyMsgCode;
    }

    public short getReplyMsgCode() {
        return replyMsgCode;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setNoOfRequestRemaining(Long noOfRequestRemaining) {
        this.noOfRequestRemaining = noOfRequestRemaining;
    }

    public Long getNoOfRequestRemaining() {
        return noOfRequestRemaining;
    }

    public void setInfoMsg(String infoMsg) {
        this.infoMsg = infoMsg;
    }

    public String getInfoMsg() {
        return infoMsg;
    }

    public void setEnvironmentDtls(String environmentDtls) {
        this.environmentDtls = environmentDtls;
    }

    public String getEnvironmentDtls() {
        return environmentDtls;
    }

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
        this.genderTypesDTO = genderTypesDTO;
    }

    public IGenderTypesDTO getGenderTypesDTO() {
        return genderTypesDTO;
    }


    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }


    public void setGenderCountryDTO(IGenderCountryDTO genderCountryDTO) {
        this.genderCountryDTO = genderCountryDTO;
    }

    public IGenderCountryDTO getGenderCountryDTO() {
        return genderCountryDTO;
    }

    public void setAllowanceDate(Timestamp allowanceDate) {
        this.allowanceDate = allowanceDate;
    }

    public Timestamp getAllowanceDate() {
        return allowanceDate;
    }
}
