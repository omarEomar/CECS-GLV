package com.beshara.csc.gn.grs.integration.presentation.backingbean.addconditions;

import java.io.Serializable;

public interface ConditionResultsCaller extends Serializable{
    
    String getBackNavigationKey();
    
    void restore(Object obj);
}
