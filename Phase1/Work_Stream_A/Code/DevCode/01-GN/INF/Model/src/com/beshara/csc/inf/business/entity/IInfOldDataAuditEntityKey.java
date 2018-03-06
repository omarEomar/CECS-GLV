package com.beshara.csc.gn.inf.business.entity;

import com.beshara.base.entity.IEntityKey;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IInfOldDataAuditEntityKey extends IEntityKey {
    
    public int hashCode();
    
    Long getSerial() ; 

}
