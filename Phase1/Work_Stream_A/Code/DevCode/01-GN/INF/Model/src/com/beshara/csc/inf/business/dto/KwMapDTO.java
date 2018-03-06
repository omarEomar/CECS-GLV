package com.beshara.csc.inf.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.inf.business.entity.KwMapEntityKey;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class KwMapDTO extends TreeDTO implements IKwMapDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long tabrecSerial;
    private boolean levelType;
    
    private String treePath;
    private boolean nextLevelRequireFlag;
    private String englishName;

    private IKwMapDTO kwMapEntity;
    
    private Long typeCode;
    
    /**
     * KwMapDTO Default Constructor */
    public KwMapDTO() {
    }

    public KwMapDTO(String code, String name) {
        setCode(new KwMapEntityKey(code));
        setName(name);
    }

    /**
     * @param kwMapEntity
     */
    public KwMapDTO(KwMapEntity kwMapEntity) {
        setCode(new KwMapEntityKey(kwMapEntity.getMapCode()));
        setName(kwMapEntity.getMapName());
        setTreeLevel(kwMapEntity.getTreeLevel());
        setTypeCode(kwMapEntity.getTypeCode());
        setLeafFlag(kwMapEntity.getLeafFlag());
        setFirstParent(new KwMapEntityKey(kwMapEntity.getFirstParent()));
        if (kwMapEntity.getKwMapEntity() != null) {
            setParentCode(new KwMapEntityKey(kwMapEntity.getKwMapEntity().getMapCode()));
            setParentObject(new KwMapDTO(kwMapEntity.getKwMapEntity()));
        }
        this.setCreatedBy(kwMapEntity.getCreatedBy());
        this.setCreatedDate(kwMapEntity.getCreatedDate());
        this.setLastUpdatedBy(kwMapEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(kwMapEntity.getLastUpdatedDate());
        this.auditStatus = kwMapEntity.getAuditStatus();
        this.tabrecSerial = kwMapEntity.getTabrecSerial();
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public boolean isNextLevelRequireFlag() {
        return nextLevelRequireFlag;
    }

    public void setLevelType(boolean levelType) {
        this.levelType = levelType;
    }

    public boolean isLevelType() {
        return levelType;
    }


    public void setLevelTypeStr(String levelType) {
        if (levelType != null && !(levelType.trim().equals(""))) {
            setLevelType(levelType.equals(IKwMapDTO.LEVEL_TYPE_GENERAL));
        }
    }


    public String getLevelTypeStr() {
        if (levelType) {
            return IKwMapDTO.LEVEL_TYPE_GENERAL;
        } else {
            return IKwMapDTO.LEVEL_TYPE_SPECIAL;
        }
    }

    public void setkwMapEntity(IKwMapDTO kwMapEntity) {
        this.kwMapEntity = kwMapEntity;
    }

    public IKwMapDTO getkwMapEntity() {
        return kwMapEntity;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public String getTreePath() {
        if (getTreeLevel() != 1 && getParentObject() != null) {
            StringBuilder fullName = new StringBuilder("");
            IBasicDTO parent = getParentObject();
            while (parent != null && parent.getCode() != null) {
                fullName.append(parent.getName());
                if (((IKwMapDTO)parent).getParentObject() != null &&
                    ((IKwMapDTO)parent).getParentObject().getCode() != null) {
                    fullName.append("/");
                    parent = ((IKwMapDTO)parent).getParentObject();
                } else {
                    break;
                }
            }
            String[] x = fullName.toString().split("/");
            fullName = new StringBuilder("");
            for (int i = x.length - 1; i >= 0; i--) {
                fullName.append(x[i]);
                if (i > 0)
                    fullName.append("/");
            }
            treePath = fullName.toString();
        }
        return treePath;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public Long getTypeCode() {
        return typeCode;
    }
}
