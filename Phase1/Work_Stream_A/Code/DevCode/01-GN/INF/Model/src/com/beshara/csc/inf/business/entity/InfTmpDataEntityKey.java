package com.beshara.csc.inf.business.entity;

import java.io.Serializable;
import com.beshara.base.entity.EntityKey;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public class InfTmpDataEntityKey extends EntityKey implements IInfTmpDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long datatypCode;
    private Long diskCode;
    private Long civilId;
    
    
    public InfTmpDataEntityKey() {
        super();
    }

    public InfTmpDataEntityKey( Long datatypCode,  Long diskCode,  Long civilId ) {
            super ( new Object[] {  datatypCode,  diskCode,  civilId  } ); 
    this.datatypCode = datatypCode;
        this.diskCode = diskCode;
        this.civilId = civilId;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getDatatypCode() { 
   return datatypCode;
    }      
public Long getDiskCode() { 
   return diskCode;
    }      
public Long getCivilId() { 
   return civilId;
    }      

}
