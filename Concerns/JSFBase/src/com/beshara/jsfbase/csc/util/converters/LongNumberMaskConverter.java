package com.beshara.jsfbase.csc.util.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class LongNumberMaskConverter implements Converter {
    public LongNumberMaskConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uiComponent, String string) {

        if (string != null && !string.trim().equals("")) {

            try {
                NumberFormat LongFormat = new DecimalFormat("#,##0.000");
                return LongFormat.parse(string).longValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uiComponent, Object object) {

        if (object != null) {
            NumberFormat LongFormat = new DecimalFormat("#,##0.000");
            return (LongFormat.format(object));
        }

        return null;

    }
}
