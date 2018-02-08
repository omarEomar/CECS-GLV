package com.beshara.jsfbase.csc.util.converters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


public class FloatThreeDecimalMaskConverter implements Converter {
    public FloatThreeDecimalMaskConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uiComponent, String string) {

        if (string != null && !string.trim().equals("")) {

            try {
                NumberFormat FloatFormat = 
                    new DecimalFormat("####0.000", new DecimalFormatSymbols(Locale.US));
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
            NumberFormat FloatFormat = 
                new DecimalFormat("####0.000", new DecimalFormatSymbols(Locale.US));
            return (FloatFormat.format(object));
        }

        return null;

    }

}
