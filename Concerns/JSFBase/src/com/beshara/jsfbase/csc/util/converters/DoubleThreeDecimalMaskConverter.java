package com.beshara.jsfbase.csc.util.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class DoubleThreeDecimalMaskConverter implements Converter {
    public DoubleThreeDecimalMaskConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uiComponent, String string) {

        if (string != null && !string.trim().equals("")) {

            try {
                NumberFormat doubleFormat = new DecimalFormat("####0.000");
                return doubleFormat.parse(string).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uiComponent, Object object) {

        if (object != null) {
            NumberFormat doubleFormat = new DecimalFormat("####0.000");
            return (doubleFormat.format(object));
        }

        return null;

    }

}
