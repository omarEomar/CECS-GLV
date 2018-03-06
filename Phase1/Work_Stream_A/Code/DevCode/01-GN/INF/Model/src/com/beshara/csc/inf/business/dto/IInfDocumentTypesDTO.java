package com.beshara.csc.inf.business.dto;


public interface IInfDocumentTypesDTO extends IInfDTO {

    public void setDoctypeCode(Long doctypeCode);

    public Long getDoctypeCode();

    public void setDoctypeName(String doctypeName);

    public String getDoctypeName();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();
}
