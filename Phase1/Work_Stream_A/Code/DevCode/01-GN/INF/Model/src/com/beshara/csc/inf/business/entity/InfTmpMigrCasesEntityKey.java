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
 
public class InfTmpMigrCasesEntityKey extends EntityKey implements IInfTmpMigrCasesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long datatypCode;
    private Long caseCode;
    
    
    public InfTmpMigrCasesEntityKey() {
        super();
    }

    public InfTmpMigrCasesEntityKey( Long datatypCode,  Long caseCode ) {
            super ( new Object[] {  datatypCode,  caseCode  } ); 
    this.datatypCode = datatypCode;
        this.caseCode = caseCode;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getDatatypCode() { 
   return datatypCode;
    }      
public Long getCaseCode() { 
   return caseCode;
    }      

}
