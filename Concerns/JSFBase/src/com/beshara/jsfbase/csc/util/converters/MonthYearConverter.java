package com.beshara.jsfbase.csc.util.converters;

import java.sql.Date;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;


public class MonthYearConverter implements Converter {
    public MonthYearConverter() {
    }


    public Object getAsObject(FacesContext context, UIComponent component, 
                              String string) throws ConverterException {
        Date date = null;
        System.out.print(string);

        if (string != null)
            try {
                // the format to enter "24?Feb?1998 17:39:35"
                SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

                java.util.Date mdate = 
                    sdf.parse(string); //Date.valueOf(string);
                date = new Date(mdate.getTime());

                // System.out.print(ts.toString());                
            } catch (Exception e)

            {

                System.out.print(string);
            }
        return date;
    }

    public String getAsString(FacesContext context, UIComponent component, 
                              Object object) throws ConverterException {
        String mydate = null;
        if (object != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");

            Date date = (Date)object;
            mydate = sdf.format(date);

        }
        return mydate;
    }


}
