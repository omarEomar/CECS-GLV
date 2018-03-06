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
 
public class InfTmpDataFieldsEntityKey extends EntityKey implements IInfTmpDataFieldsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long datatypCode;
    private Long fieldOrder;
    
    
    public InfTmpDataFieldsEntityKey() {
        super();
    }

    public InfTmpDataFieldsEntityKey( Long datatypCode,  Long fieldOrder ) {
            super ( new Object[] {  datatypCode,  fieldOrder  } ); 
    this.datatypCode = datatypCode;
        this.fieldOrder = fieldOrder;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getDatatypCode() { 
   return datatypCode;
    }      
public Long getFieldOrder() { 
   return fieldOrder;
    }      

}
