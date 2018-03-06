package com.beshara.csc.inf.business.dto;
import java.io.Serializable;
import java.util.List;
public class FieldValueDTO implements IFieldValueDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String code;
    private String name;
    private String parentCode;
    private Long leafFlag;
    private Long treeLevel;
    private IFieldValueDTO parentObject;
    private List<IFieldValueDTO> childrenList;
    private Long childernNumbers;
    private String firstParent;
    public FieldValueDTO() {    }    public FieldValueDTO(String code, String name) {        this.setCode(code);
        this.setName(name);
    }    public void setCode(String code) {        this.code = code;
    }    public String getCode() {        return code;
    }    public void setName(String name) {        this.name = name;
    }    public String getName() {        return name;
    }    public void setParentCode(String parentCode) {        this.parentCode = parentCode;
    }    public String getParentCode() {        return parentCode;
    }    public void setParentObject(IFieldValueDTO parentObject) {        this.parentObject = parentObject;
    }    public IFieldValueDTO getParentObject() {        return parentObject;
    }    public void setChildrenList(List<IFieldValueDTO> childrenList) {        this.childrenList = childrenList;
    }    public List<IFieldValueDTO> getChildrenList() {        return childrenList;
    }    public void setChildernNumbers(Long childernNumbers) {        this.childernNumbers = childernNumbers;
    }    public void setLeafFlag(Long leafFlag) {        this.leafFlag = leafFlag;
    }    public Long getLeafFlag() {        return leafFlag;
    }    public void setTreeLevel(Long treeLevel) {        this.treeLevel = treeLevel;
    }    public Long getTreeLevel() {        return treeLevel;
    }    public void setFirstParent(String firstParent) {        this.firstParent = firstParent;
    }    public String getFirstParent() {        return firstParent;
    }    public boolean isBooleanLeafFlag() {        if (leafFlag != null && leafFlag.intValue() == 1) {            return true;
        }        return false;
    }    public void setBooleanLeafFlag(boolean leafFlag) {        if (leafFlag) {            setLeafFlag(new Long(1));
        } else {            setLeafFlag(new Long(0));
        }    }    public Long getChildernNumbers() {        if (childernNumbers != null && childernNumbers > childrenList.size())            return childernNumbers;
        else if (childrenList != null)            return new Long(childrenList.size());
        return 0L;
    }}
