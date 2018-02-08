package com.beshara.csc.nl.reg.integration.presentation.backingbean.util;


import com.beshara.csc.nl.reg.business.dto.IModuleRelationsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTO;

import java.util.ArrayList;
import java.util.List;

public class TableInfo extends RegDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String tableName;
    private String tableDesc;
    private List<IModuleRelationsDTO> moduleRelations = 
        new ArrayList<IModuleRelationsDTO>();

    public TableInfo() {
    }

    public TableInfo(String tableName, 
                     IModuleRelationsDTO moduleRelationsDTO) {
        this.tableName = tableName;
        setName(tableName);
        moduleRelations.add(moduleRelationsDTO);
    }

    public TableInfo(String tableName, String tableDesc, 
                     IModuleRelationsDTO moduleRelationsDTO) {
        this.tableName = tableName;
        this.tableDesc = tableDesc;
        setName(tableName);
        moduleRelations.add(moduleRelationsDTO);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public int getRecordsCount() {
        return moduleRelations.size();
    }

    public void addRecord(IModuleRelationsDTO moduleRelationsDTO) {
        moduleRelations.add(moduleRelationsDTO);
    }

    public boolean contains(Long serial) {
        for (IModuleRelationsDTO m: moduleRelations) {
            if (m.getTabrecSerialRef().equals(serial)) {
                return true;
            }
        }
        return false;
    }

    public void setModuleRelations(List<IModuleRelationsDTO> moduleRelations) {
        this.moduleRelations = moduleRelations;
    }

    public List<IModuleRelationsDTO> getModuleRelations() {
        return moduleRelations;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public String getTableDesc() {
        return tableDesc;
    }
}
