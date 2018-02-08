package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;

import com.beshara.csc.nl.reg.business.dto.IREGDesignTabColumnsDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabRecordsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IREGDesignTabRecordsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.constants.IRegConstants;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;


public class Record  implements java.io.Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private List data;
    private List columnHeaders;
    
    private static final int SORT_ASCENDING = 1;
    private static final int SORT_DESCENDING = -1;
    
    private List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> recordDataList;// all rows at datable ,each row is list of cells 
    private List<IREGDesignTabColumnsDTO> columns;
    private static  int Add_Mode=0;
    private static  int Edit_Mode=1;
    private static  int maxRecordIndex=0;
    private Long deleted = ISystemConstant.DELEDTED_ITEM;
    private Long added = ISystemConstant.ADDED_ITEM;
    private int rowSize=0;
    
    public Record() {
   
    
    }
    public Record(List<IREGDesignTabColumnsDTO> columns,List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> recordDataList) {
        try {
            this.recordDataList = recordDataList;
            this.columns=columns;
            fillColumnHeaders();
            fillData();
            maxRecordIndex=recordDataList.size();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setData(List data) {
        this.data = data;
    }

    public List getData() {
        return data;
    }

    public List getColumnHeaders() {
        return columnHeaders;
    }

    public void setRecordDataList(List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> recordDataList) {
        this.recordDataList = recordDataList;
    }

    public List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> getRecordDataList() {
        return recordDataList;
    }

    
    private void fillColumnHeaders() {
    
        int columnSize=0;
        columnSize=columns.size();
        rowSize=columnSize;
        List headerList = new ArrayList();
        if(columnSize > 0) {
            headerList.add(0,new ColumnHeader(null, "10", 0L, 0 ));//instead of check box column
            for (int i = 0; i < columns.size(); i++) {
                headerList.add(new ColumnHeader(columns.get(i).getName(), "100", columns.get(i).getDestabcolumnType(), i+1));
            } 
            columnHeaders = headerList;
        }
        
    }
    
    private void fillData() {

        List rowList = new ArrayList();
        for(int j=0; j<recordDataList.size(); j++) {
               RecordDataDTO relatedFieldsElem = recordDataList.get(j);
               List colList = new ArrayList();
               colList.add(0,"");//instead of check box column
               List<IREGDesignTabRecordsDTO> requestGroupDataRow = (List<IREGDesignTabRecordsDTO>)relatedFieldsElem.getRecordDataRow();
               for(int i=0; i< requestGroupDataRow.size(); i++){
                  IREGDesignTabRecordsDTO row= requestGroupDataRow.get(i);
                  if(row.getStatusFlag() ==null || row.getStatusFlag().intValue()==added.intValue()){
                      row.setChecked(false);
                      row.setRecNum(new Long(j+1));
                      colList.add(row);
                  }
               } 
               if(colList.size()==1)
                   colList.clear();
               if(colList.size()>1)     
                   rowList.add(colList);
        }
        
        data = rowList;
    }


    public void setColumns(List<IREGDesignTabColumnsDTO> columns) {
        this.columns = columns;
    }

    public List<IREGDesignTabColumnsDTO> getColumns() {
        return columns;
    }
    
    public void addNewRecord(Long status){
             try {
                List recordDataRow = new ArrayList();
                 int recIndex=getMaxRecordIndex();
                 setMaxRecordIndex(recIndex+1);
                 recordDataRow.add(0,"");
                 for(int j=0; j<columns.size(); j++) {
                     Long maxRecordNum=new Long(getMaxRecordIndex());
                     IREGDesignTabRecordsDTO elem = RegDTOFactory.createREGDesignTabRecordsDTO();
                     elem.setStatusFlag(status);
                     IREGDesignTabRecordsEntityKey key=RegEntityKeyFactory.createREGDesignTabRecordsEntityKey(null, 
                                                                                   null, 
                                                                                   null, 
                                                                                   null, 
                                                                                   null, 
                                                                                   maxRecordNum);
                     elem.setRegDesignTabColumnsDTO(columns.get(j));
                     elem.setRecNum(maxRecordNum);
                     elem.setCode(key);
                     recordDataRow.add(elem);
                 }
                 data.add(recordDataRow);
                 SharedUtilBean bean=  SharedUtilBean.getInstance();
                 addRowToActualRecordList(recIndex,(List<IREGDesignTabRecordsDTO>)bean.deepCopyObject(recordDataRow));
             }
             catch (Exception e) {
                 e.printStackTrace();
             }
    }
    
     public void addRowToActualRecordList(int recIndex,List<IREGDesignTabRecordsDTO> recordDataRow){
         RecordDataDTO newRecordDataDTO=new RecordDataDTO();
         recordDataRow.remove(0);
         newRecordDataDTO.setRecordDataRow(recordDataRow);
         newRecordDataDTO.setRecIndex(recIndex);
         this.getRecordDataList().add(newRecordDataDTO);
     }   

    public void setColumnHeaders(List columnHeaders) {
        this.columnHeaders = columnHeaders;
    }


    public void setMaxRecordIndex(int maxRecordIndex) {
        this.maxRecordIndex = maxRecordIndex;
    }

    public int getMaxRecordIndex() {
        return maxRecordIndex;
    }
    
    public void deleteRecord(int recIndex){
    
      for(int i=0;i<data.size();i++){
          List recordDataRow = (List)data.get(i);
          if(((IREGDesignTabRecordsDTO)recordDataRow.get(1)).getRecNum().intValue()==(recIndex+1))
              data.remove(i);       
      }
        deleteRecordFromActualList(recIndex);
        
    }
   public void deleteRecordFromActualList(int recIndex){
   
        for(int i=0;i<recordDataList.size();i++){
            System.out.println("Class---------->>>>>"+recordDataList.get(i).getClass());
            RecordDataDTO recordDataDTO=(RecordDataDTO)recordDataList.get(i);
            if( recordDataDTO.getRecIndex()==recIndex){
                List<IREGDesignTabRecordsDTO> cellsList = (List<IREGDesignTabRecordsDTO>)recordDataDTO.getRecordDataRow();
                if(cellsList.get(0).getStatusFlag()==null){
                  for(int j=0;j<cellsList.size();j++){
                      cellsList.get(j).setStatusFlag(deleted);
                  }
                }
                else if(cellsList.get(0).getStatusFlag().intValue()==added.intValue()) 
                  recordDataList.remove(i);
                  
            }
                
        }
        updateActualDataListBeforeSave();
        reOrderRowesIndecies();
    } 
    
    public void reOrderRowesIndecies(){
        for(int i=0;i<recordDataList.size();i++){
                RecordDataDTO recordDataDTO=(RecordDataDTO)recordDataList.get(i);
                recordDataDTO.setRecIndex(i);
            }
            fillData();
            maxRecordIndex=recordDataList.size();
    }
    
    public void resetSelectionData(){
        for(int i=0;i<data.size();i++){
            List recordDataRow = (List)data.get(i);
            recordDataRow.set(0, "");
            for(int j=1;j<recordDataRow.size();j++){
                ((IREGDesignTabRecordsDTO)recordDataRow.get(j)).setChecked(false);
            }
                  
        }
    
    }
    
    public void updateRecord(RecordDataDTO editableRecordDataDTO){
       int recIndex=editableRecordDataDTO.getRecIndex();
       
       // update record at displayed data
        List<IREGDesignTabRecordsDTO> newRecordDataRow = (List<IREGDesignTabRecordsDTO>)editableRecordDataDTO.getRecordDataRow();
        
        for(int i=0;i<data.size();i++){
            List oldrecordDataRow = (List)data.get(i);
            if(((IREGDesignTabRecordsDTO)oldrecordDataRow.get(1)).getRecNum().intValue()==(recIndex+1))
               {
                   for(int j=1;j<oldrecordDataRow.size();j++){
                       IREGDesignTabRecordsDTO recordDTO=(IREGDesignTabRecordsDTO)oldrecordDataRow.get(j);
                       recordDTO.setValue(newRecordDataRow.get(j-1).getValue());
                   }
               }       
        }
        // update record at actual data
         for(int i=0;i<recordDataList.size();i++){
             RecordDataDTO recordDataDTO=(RecordDataDTO)recordDataList.get(i);
             if( recordDataDTO.getRecIndex()==recIndex){
                 List<IREGDesignTabRecordsDTO> cellsList = (List<IREGDesignTabRecordsDTO>)recordDataDTO.getRecordDataRow();
                   for(int j=0;j<cellsList.size();j++){
                       cellsList.get(j).setValue(newRecordDataRow.get(j).getValue());
                   }
                 }
      }
    }
    
    public List<IREGDesignTabRecordsDTO> getRecordRowByIndex(int recIndex){
        try {
            for(int i=0;i<data.size();i++){
                List recordDataRow = (List)data.get(i);
                if(((IREGDesignTabRecordsDTO)recordDataRow.get(1)).getRecNum().intValue()==(recIndex+1)){
                      return recordDataRow;
                   }       
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public  List<IREGDesignTabColumnsDTO> constructColumnList() {
        updateActualDataListBeforeSave();
        for(int cloumnIndex=0;cloumnIndex<columns.size();cloumnIndex++){
               List rowList=new ArrayList();
               for(int rowIndex=0;rowIndex<recordDataList.size();rowIndex++){
                   RecordDataDTO recordDataDTO=(RecordDataDTO)recordDataList.get(rowIndex);
                   List<IREGDesignTabRecordsDTO> cellsList = (List<IREGDesignTabRecordsDTO>)recordDataDTO.getRecordDataRow();                   
                        rowList.add(cellsList.get(cloumnIndex));
               }
              columns.get(cloumnIndex).setRegDesignTabRecordsDTOList(rowList);
            }
             return columns;  
        }
        
    public void updateActualDataListBeforeSave(){
            try {
                int recIndex=0;
                SharedUtilBean bean=  SharedUtilBean.getInstance();
                for(int rowIndex=0;rowIndex<recordDataList.size();rowIndex++){
                
                    RecordDataDTO recordDataDTO=(RecordDataDTO)recordDataList.get(rowIndex);
                    recIndex=recordDataDTO.getRecIndex();
                    List<IREGDesignTabRecordsDTO> cellsList = (List<IREGDesignTabRecordsDTO>)recordDataDTO.getRecordDataRow();
                    
                    if(cellsList.get(0).getStatusFlag() !=null && cellsList.get(0).getStatusFlag().equals(added)) {
                    
                        for(int i=0;i<data.size();i++){
                            List recordDataRow = (List)data.get(i);
                            if(((IREGDesignTabRecordsDTO)recordDataRow.get(1)).getRecNum().intValue()==(recIndex+1)){
                                   for(int k=1;k<recordDataRow.size();k++){
                                      cellsList.get(k-1).setValue(((IREGDesignTabRecordsDTO)recordDataRow.get(k)).getValue());
                                   }
                               }       
                        }
                        
                    }
                       
                }
                
                
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void setRowSize(int rowSize) {
        this.rowSize = rowSize;
    }

    public int getRowSize() {
        return rowSize;
    }
    
    public boolean validateData() {
        try {
            for (int i = 0; i < data.size(); i++) {
                List recordDataRow = (List)data.get(i);
                for (int j = 1; j < recordDataRow.size(); j++) {
                    IREGDesignTabRecordsDTO dto = ((IREGDesignTabRecordsDTO)recordDataRow.get(j));
                    if (dto.getRegDesignTabColumnsDTO().getDestabcolumnType().equals(IRegConstants.COLUMN_LONG_TYPE))
                        try {
                            Integer.parseInt(dto.getValue());
                        } catch (NumberFormatException e) {
                            return false;
                        }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
