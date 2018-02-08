package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationRevision.details;

import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RegulationMainDataMaintain;


public class RegulationRevisoinMainDataMaintain extends RegulationMainDataMaintain {
    
    public RegulationRevisoinMainDataMaintain() {
        RegulationCycleMaintainBean.getInstance().setFinishNavigationCase("revisionregulationlist");
        
    }
   
    
}
