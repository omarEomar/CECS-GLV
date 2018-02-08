/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beshara.jsfbase.csc.util.wizardbar2.factory;

import com.beshara.jsfbase.csc.util.wizardbar2.config.impl.xml.parsing.XMLConfigParser;
import com.beshara.jsfbase.csc.util.wizardbar2.config.parsing.IConfigParser;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;


/**
 *
 * @author A.hamed
 */
public class ObjectFactory {

    public static IConfigParser getConfigurationParser() {

        String configFilePath = 
            ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("//WEB-INF//module//config//wizardbar-conf2.xml");
        return new XMLConfigParser(configFilePath);

    }
}
