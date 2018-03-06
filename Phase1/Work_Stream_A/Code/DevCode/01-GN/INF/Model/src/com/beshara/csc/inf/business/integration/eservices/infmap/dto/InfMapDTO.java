package com.beshara.csc.inf.business.integration.eservices.infmap.dto;

import java.io.Serializable;

public class InfMapDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String value;
    private String label;

    public InfMapDTO() {
        super();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
