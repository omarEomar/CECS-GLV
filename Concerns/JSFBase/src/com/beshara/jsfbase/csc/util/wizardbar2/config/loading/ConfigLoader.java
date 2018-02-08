package com.beshara.jsfbase.csc.util.wizardbar2.config.loading;

import com.beshara.jsfbase.csc.util.wizardbar2.config.parsing.IConfigParser;
import com.beshara.jsfbase.csc.util.wizardbar2.factory.ObjectFactory;
import com.beshara.jsfbase.csc.util.wizardbar2.model.WizardBarConfig;


public class ConfigLoader {

    public static ConfigLoader instance = null;
    WizardBarConfig wizardBarConfig;

    private ConfigLoader() {
    }

    public static ConfigLoader getInstance() {

        if (instance == null) {
            instance = new ConfigLoader();
            instance.loadWizardBarConfig();
        }
        return instance;
    }

    private void loadWizardBarConfig() {

        IConfigParser parser = ObjectFactory.getConfigurationParser();
        wizardBarConfig = parser.parseConfigurations();

    }

    public WizardBarConfig getWizardBarConfig() {
        return wizardBarConfig;
    }
}
