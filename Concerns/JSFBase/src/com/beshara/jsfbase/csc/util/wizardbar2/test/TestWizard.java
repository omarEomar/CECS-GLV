package com.beshara.jsfbase.csc.util.wizardbar2.test;

import com.beshara.jsfbase.csc.util.wizardbar2.config.loading.ConfigLoader;


public class TestWizard {
    public TestWizard() {
    }

    public void test() {


        Long time1 = System.currentTimeMillis();
        ConfigLoader loader = ConfigLoader.getInstance();
        //  System.out.println(((Wizard)(loader.getWizardBarConfig().getWizardMap().get("test"))).getId());
        System.out.println((System.currentTimeMillis() - time1));
    }

    public static void main(String[] args) {

        TestWizard testWizard = new TestWizard();
        testWizard.test();

    }
}
