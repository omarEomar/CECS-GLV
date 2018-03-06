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
 
public class InfTmpDataTypesEntityKey extends EntityKey implements IInfTmpDataTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long datatypCode;
    
    
    public InfTmpDataTypesEntityKey() {
        super();
    }

    public InfTmpDataTypesEntityKey( Long datatypCode ) {
            super ( new Object[] {  datatypCode  } ); 
    this.datatypCode = datatypCode;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getDatatypCode() { 
   return datatypCode;
    }      

}
