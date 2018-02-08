package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.csc.nl.reg.business.dto.IREGDesignTabRecordsDTO;


public class RecordDataDTO<List> implements java.io.Serializable{

   private List recordDataRow;
   private int recIndex;
        
    public RecordDataDTO() {
        this.recordDataRow=null;
    }
    
    public RecordDataDTO(List recordDataRow) {
        this.recordDataRow=recordDataRow;
    }

        /**
     * @param List<RequestDataDTO> requestGroupDataRow.
     */
    public void setRecordDataRow(List recordDataRow) {
        this.recordDataRow = recordDataRow;
    }

    /**
     * @returns List<RequestDataDTO>.
     */
    public List getRecordDataRow() {
        return this.recordDataRow;
    }
    
    public void setRecIndex(int recIndex) {
        this.recIndex = recIndex;
    }

    public int getRecIndex() {
        return recIndex;
    }
}
