package com.beshara.jsfbase.csc.util.tableheader;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class ModuleObject implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String objIndex;
    private String objCode;
    private String objName;
    private String objType;
    private String objPrompt;
    private String databaseObject;
    private String objPage;
    private Long leafFlag;
    private Long treeLevel;
    private Long firstParent;
    private String selectionFlag;
    private String reRender;
    private String onComplete;
    //by sherif.omar
    private String styleClass ="";
    private List<ModuleObject> childsList = new ArrayList<ModuleObject>();
    
    public ModuleObject() {
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjPrompt(String objPrompt) {
        this.objPrompt = objPrompt;
    }

    public String getObjPrompt() {
        return objPrompt;
    }

    public void setDatabaseObject(String databaseObject) {
        this.databaseObject = databaseObject;
    }

    public String getDatabaseObject() {
        return databaseObject;
    }

    public void setObjPage(String objPage) {
        this.objPage = objPage;
    }

    public String getObjPage() {
        return objPage;
    }

    public void setLeafFlag(Long leafFlag) {
        this.leafFlag = leafFlag;
    }

    public Long getLeafFlag() {
        return leafFlag;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setFirstParent(Long firstParent) {
        this.firstParent = firstParent;
    }

    public Long getFirstParent() {
        return firstParent;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjType() {
        return objType;
    }

    public void setChildsList(List<ModuleObject> childsList) {
        this.childsList = childsList;
    }

    public List<ModuleObject> getChildsList() {
        return childsList;
    }

    public void setSelectionFlag(String selectionFlag) {
        this.selectionFlag = selectionFlag;
    }

    public String getSelectionFlag() {
        return selectionFlag;
    }

    public void setObjIndex(String objIndex) {
        this.objIndex = objIndex;
    }

    public String getObjIndex() {
        return objIndex;
    }

    public void setReRender(String reRender) {
        this.reRender = reRender;
    }

    public String getReRender() {
        return reRender;
    }

    public void setStyleClass(String imagePath) {
        this.styleClass = imagePath;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setOnComplete(String onComplete) {
        this.onComplete = onComplete;
    }

    public String getOnComplete() {
        return onComplete;
    }
}
