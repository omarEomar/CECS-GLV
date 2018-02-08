package com.beshara.jsfbase.csc.util.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class FloatNumberMaskConverter implements Converter {
    public FloatNumberMaskConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uiComponent, String string) {

        if (string != null && !string.trim().equals("")) {

            try {
                NumberFormat FloatFormat = new DecimalFormat("#,##0.000");
                return FloatFormat.parse(string).floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uiComponent, Object object) {

        if (object != null) {
            NumberFormat FloatFormat = new DecimalFormat("#,##0.000");
            return (FloatFormat.format(object));
        }

        return null;

    }
}
