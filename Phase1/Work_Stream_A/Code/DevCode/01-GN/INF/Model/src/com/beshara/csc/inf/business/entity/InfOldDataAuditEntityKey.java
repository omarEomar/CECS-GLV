package com.beshara.csc.gn.inf.business.entity;

import java.io.Serializable;
import com.beshara.base.entity.EntityKey;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public class InfOldDataAuditEntityKey extends EntityKey implements IInfOldDataAuditEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long serial;
    
    
    public InfOldDataAuditEntityKey() {
        super();
    }

    public InfOldDataAuditEntityKey( Long serial ) {
            super ( new Object[] {  serial  } ); 
    this.serial = serial;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getSerial() { 
   return serial;
    }      

}
