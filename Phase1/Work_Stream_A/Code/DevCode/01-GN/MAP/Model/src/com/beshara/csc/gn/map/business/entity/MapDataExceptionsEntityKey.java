package com.beshara.csc.gn.map.business.entity;

import com.beshara.base.entity.EntityKey;

public class MapDataExceptionsEntityKey extends EntityKey implements IMapDataExceptionsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long objtypeCode;
    private Long soc1Code;
    private Long soc2Code;
    
    
    public MapDataExceptionsEntityKey() {
        super();
    }

    public MapDataExceptionsEntityKey( Long objtypeCode,  Long soc1Code,  Long soc2Code ) {
            super ( new Object[] {  objtypeCode,  soc1Code,  soc2Code  } ); 
    this.objtypeCode = objtypeCode;
        this.soc1Code = soc1Code;
        this.soc2Code = soc2Code;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getObjtypeCode() { 
   return objtypeCode;
    }      
public Long getSoc1Code() { 
   return soc1Code;
    }      
public Long getSoc2Code() { 
   return soc2Code;
    }      

}
