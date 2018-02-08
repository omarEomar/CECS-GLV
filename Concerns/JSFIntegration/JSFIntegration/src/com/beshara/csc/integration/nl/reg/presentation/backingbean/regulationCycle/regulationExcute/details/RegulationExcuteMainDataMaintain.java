package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationExcute.details;

import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RegulationMainDataMaintain;


public class RegulationExcuteMainDataMaintain extends RegulationMainDataMaintain {
    
    public RegulationExcuteMainDataMaintain() {
        RegulationCycleMaintainBean.getInstance().setFinishNavigationCase("executeregulationlist");
        
    }
   
    
}
