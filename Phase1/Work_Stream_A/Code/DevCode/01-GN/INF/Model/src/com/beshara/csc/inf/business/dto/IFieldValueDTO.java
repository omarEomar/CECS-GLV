package com.beshara.csc.inf.business.dto;

import java.util.List;


public interface IFieldValueDTO  extends java.io.Serializable{

    public void setCode(String code);

    public String getCode();

    public void setName(String name);

    public String getName();

    public void setParentCode(String parentCode);

    public String getParentCode();

    public void setParentObject(IFieldValueDTO parentObject);

    public IFieldValueDTO getParentObject();

    public void setChildrenList(List<IFieldValueDTO> childrenList);

    public List<IFieldValueDTO> getChildrenList();

    public void setChildernNumbers(Long childernNumbers);

    public void setLeafFlag(Long leafFlag);

    public Long getLeafFlag();

    public void setTreeLevel(Long treeLevel);

    public Long getTreeLevel();

    public void setFirstParent(String firstParent);

    public String getFirstParent();

    public boolean isBooleanLeafFlag();

    public void setBooleanLeafFlag(boolean leafFlag);

    public Long getChildernNumbers();

}
