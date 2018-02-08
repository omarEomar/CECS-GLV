package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;


public class RecordDataDTO<List> implements java.io.Serializable{

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    
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
