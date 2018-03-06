package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfTmpDataDisksEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class InfTmpDataDisksDTO extends InfDTO implements IInfTmpDataDisksDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long datatypCode;
private Long diskCode;
private Timestamp diskDate;


    /**
     * InfTmpDataDisksDTO Default Constructor
     */
    public InfTmpDataDisksDTO() {
        super();
    }



/**
* @return Long
*/
public Long getDatatypCode() {
   return datatypCode;
}
/**
* @return Long
*/
public Long getDiskCode() {
   return diskCode;
}
/**
* @return Timestamp
*/
public Timestamp getDiskDate() {
   return diskDate;
}

    
/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) {
   this.datatypCode=datatypCode;
}
/**
* @param diskCode
*/
public void setDiskCode(Long diskCode) {
   this.diskCode=diskCode;
}
/**
* @param diskDate
*/
public void setDiskDate(Timestamp diskDate) {
   this.diskDate=diskDate;
}

    
}

