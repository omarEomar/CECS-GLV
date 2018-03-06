package com.beshara.csc.inf.business.dto;


import java.util.List;

import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IInfTmpDataFieldsDTO extends IInfDTO {

    /**
     * @return Long
     */
    Long getDatatypCode();

    /**
     * @return Long
     */
    Long getFieldOrder();

    /**
     * @return String
     */
    String getName();

    /**
     * @return String
     */
    String getVariableName();

    /**
     * @return String
     */
    String getFieldLabel();

    /**
     * @return Long
     */
    Long getFldtypeCode();


    /**
     * @param datatypCode
     */
    public void setDatatypCode(Long datatypCode);

    /**
     * @param fieldOrder
     */
    public void setFieldOrder(Long fieldOrder);

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * @param variableName
     */
    public void setVariableName(String variableName);

    /**
     * @param fieldLabel
     */
    public void setFieldLabel(String fieldLabel);

    /**
     * @param fldtypeCode
     */
    public void setFldtypeCode(Long fldtypeCode);


    public void setNewFieldOrder(Long newFieldOrder);

    public Long getNewFieldOrder();


}
