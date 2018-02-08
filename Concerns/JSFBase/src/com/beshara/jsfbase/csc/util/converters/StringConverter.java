package com.beshara.jsfbase.csc.util.converters;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


class StringConverter implements Converter {

    public StringConverter() {
    }

    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uIComponent, 
                              String string) throws ConverterException {
        return string;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uIComponent,

        Object object) throws ConverterException {
        String mydate = null;
        if (object != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp ts = (Timestamp)object;
            Date date = new Date(ts.getTime() + (ts.getNanos() / 1000000));
            mydate = sdf.format(date);
        }
        return mydate;
    }
}
