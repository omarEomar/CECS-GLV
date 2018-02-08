package com.beshara.csc.nl.reg.integration.presentation.backingbean.regulation.details;

/**
 * @author Ali Abdel-Aziz
 */
public class ColumnHeader implements java.io.Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String _label;
    private String _width;
    private boolean _editable;
    private int _index;
    private Long columnType;
    
    public ColumnHeader() {
    }

//    public ColumnHeader(String label, String width, boolean editable, int columnIndex) {
//        _label = label;
//        _width = width;
//        _editable = editable;
//        _index = columnIndex;
//    }
     public ColumnHeader(String label, String width, Long uiControlType, int columnIndex) {
         _label = label;
         _width = width;
         this.columnType = uiControlType;
         _index = columnIndex;
     }

    //=========================================================================
    // Getters
    //=========================================================================

    public String getLabel() {
        return _label;
    }

    public String getWidth() {
        return _width;
    }

    public boolean isEditable() {
        return _editable;
    }

    //=========================================================================
    // Getters
    //=========================================================================

    public void setLabel(String label) {
        _label = label;
    }

    public void setWidth(String width) {
        _width = width;
    }

    public void setEditable(boolean editable) {
        _editable = editable;
    }


    public void setColumnIndex(int index) {
        this._index = index;
    }

    public int getColumnIndex() {
        return _index;
    }
    public void setColumnType(Long columnType) {
        this.columnType = columnType;
    }

    public Long getColumnType() {
        return columnType;
    }
}
