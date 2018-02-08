package com.beshara.jsfbase.csc.util.converters;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


public class TimeStampMonthYearConverter implements Converter {
    public TimeStampMonthYearConverter() {
    }


    public Object getAsObject(FacesContext facesContext, 
                              UIComponent uIComponent, 
                              String string) throws ConverterException {
        //   System.out.print(string);
        Timestamp ts = null;
        if (string != null)
            try {
                // the format to enter "24?Feb?1998 17:39:35"
                SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                java.util.Date date = sdf.parse(string);
                ts = new java.sql.Timestamp(date.getTime());
                // System.out.print(ts.toString());                
            } catch (Exception e)

            {

                System.out.print(string);
            }
        return ts;
    }

    public String getAsString(FacesContext facesContext, 
                              UIComponent uIComponent,

        Object object) throws ConverterException {
        String mydate = null;
        if (object != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            Timestamp ts = (Timestamp)object;
            Date date = new Date(ts.getTime() + (ts.getNanos() / 1000000));
            mydate = sdf.format(date);
        }
        return mydate;
    }


}
