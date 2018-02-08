package com.beshara.jsfbase.csc.util.converters;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class BigDecimalNumberMaskConverter implements Converter {
    public BigDecimalNumberMaskConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uiComponent, String string) {

        if (string != null && !string.trim().equals("")) {
            return new BigDecimal(string);
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uiComponent, Object object) {

        if (object != null) {
            NumberFormat BigDecimalFormat = new DecimalFormat("#,##0.000");
            return (BigDecimalFormat.format(object));
        }

        return null;

    }
}
