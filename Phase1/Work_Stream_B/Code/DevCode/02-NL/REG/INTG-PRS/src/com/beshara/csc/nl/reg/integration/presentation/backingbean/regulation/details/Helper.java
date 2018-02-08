package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;

import com.beshara.csc.nl.reg.business.dto.IREGDedignTablesDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabColumnsDTO;
import com.beshara.csc.nl.reg.business.dto.IREGDesignTabRecordsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;

import java.util.ArrayList;
import java.util.List;


public class Helper {
  
  private static int Add_Mode=0;
  private static int Edit_Mode=0;
    public Helper() {
    }
    
    public static List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> getAllDataRecords(List<IREGDesignTabColumnsDTO> columnsList, IREGDedignTablesDTO tableDTO,  int pageMode) {
        List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> rowDataList= new ArrayList<RecordDataDTO<List<IREGDesignTabRecordsDTO>>>();
        try {
            int dataListSize = 0;
            if(columnsList != null && columnsList.size() > 0 && columnsList.get(0).getRegDesignTabRecordsDTOList() != null &&
                columnsList.get(0).getRegDesignTabRecordsDTOList().size() > 0)
                dataListSize = columnsList.get(0).getRegDesignTabRecordsDTOList().size();
                
            if(pageMode==Add_Mode)
               rowDataList= constructAddDataStrcture(columnsList, tableDTO,dataListSize);
    //        else if(pageMode==Edit_Mode)
    //          rowDataList=  constructEditDataStrcture(columnsList, tableDTO,dataListSize);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rowDataList;
    }

    private static List constructAddDataStrcture(List<IREGDesignTabColumnsDTO> columnsList, IREGDedignTablesDTO tableDTO, int maxRecordsSize) {
    
        List<RecordDataDTO<List<IREGDesignTabRecordsDTO>>> rowDataList= new ArrayList<RecordDataDTO<List<IREGDesignTabRecordsDTO>>>();
        for(int i = 0; i<maxRecordsSize; i++) {
            RecordDataDTO<List<IREGDesignTabRecordsDTO>> recordDataDTO = new RecordDataDTO<List<IREGDesignTabRecordsDTO>>();
            recordDataDTO.setRecIndex(i);
            List<IREGDesignTabRecordsDTO> recordDataRow = new ArrayList<IREGDesignTabRecordsDTO>();
            for(int j=0; j<columnsList.size(); j++) {
                IREGDesignTabRecordsDTO elem = RegDTOFactory.createREGDesignTabRecordsDTO();
                if(columnsList.get(j).getRegDesignTabRecordsDTOList() !=null && columnsList.get(j).getRegDesignTabRecordsDTOList().get(i) !=null)
                        elem=columnsList.get(j).getRegDesignTabRecordsDTOList().get(i);
                
                elem.setRegDesignTabColumnsDTO(columnsList.get(j));
                recordDataRow.add(elem);
            }
            recordDataDTO.setRecordDataRow(recordDataRow);
            rowDataList.add(recordDataDTO);
         }
         
        return rowDataList;
    }
    
   
    
}
