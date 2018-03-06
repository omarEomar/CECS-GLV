package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.ITreeDTO;


public interface IKwMapDTO extends ITreeDTO {

    public static final String LEVEL_TYPE_GENERAL = "1";
    public static final String LEVEL_TYPE_SPECIAL = "0";

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setEnglishName(String englishName);

    public String getEnglishName();

    String getLevelTypeStr();

    void setLevelTypeStr(String levelType);

    boolean isLevelType();

    void setLevelType(boolean levelType);

    boolean isNextLevelRequireFlag();

    IKwMapDTO getkwMapEntity();

    void setkwMapEntity(IKwMapDTO kwMapEntity); 

    public void setTreePath(String treePath);

    public String getTreePath();

    public void setTypeCode(Long typeCode);

    public Long getTypeCode();  
}
