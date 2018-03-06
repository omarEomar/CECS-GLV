package com.beshara.csc.inf.business.dto;

import java.sql.Date;
import java.sql.Timestamp;


public interface IWifeSonInfoDTO extends IInfDTO {


    public void setEmpCivilId(Long empCivilId);

    public Long getEmpCivilId();

    public void setBrancheCivilId(Long brancheCivilId);

    public Long getBrancheCivilId();

    public void setRelationType(short relationType);

    public short getRelationType();

    public void setMarriageDate(Date marriageDate);

    public Date getMarriageDate();

    public void setBrancheName(String brancheName);

    public String getBrancheName();

    public void setBirthDate(Date birthDate);

    public Date getBirthDate();

    public void setDeathDate(Date deathDate);

    public Date getDeathDate();

    public void setReplyMsgCode(short replyMsgCode);

    public short getReplyMsgCode();

    public void setRequestTime(String requestTime);

    public String getRequestTime();

    public void setReplyMsg(String replyMsg);

    public String getReplyMsg();

    public void setResponseTime(String responseTime);

    public String getResponseTime();

    public void setVersionNumber(String versionNumber);

    public String getVersionNumber();

    public void setNoOfRequestRemaining(Long noOfRequestRemaining);

    public Long getNoOfRequestRemaining();

    public void setInfoMsg(String infoMsg);

    public String getInfoMsg();

    public void setEnvironmentDtls(String environmentDtls);

    public String getEnvironmentDtls();

    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

    public IGenderTypesDTO getGenderTypesDTO();

    public void setNationality(String nationality);

    public String getNationality();

    public void setGenderCountryDTO(IGenderCountryDTO genderCountryDTO);

    public IGenderCountryDTO getGenderCountryDTO();
    
    public void setAllowanceDate(Timestamp allowanceDate);

    public Timestamp getAllowanceDate();
}
