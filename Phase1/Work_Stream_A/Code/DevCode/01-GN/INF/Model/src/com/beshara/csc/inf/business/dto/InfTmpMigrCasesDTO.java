package com.beshara.csc.inf.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.entity.InfTmpMigrCasesEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class InfTmpMigrCasesDTO extends InfDTO implements IInfTmpMigrCasesDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long datatypCode;
private Long caseCode;
private String name ; 
private Long needUpdate;


    /**
     * InfTmpMigrCasesDTO Default Constructor
     */
    public InfTmpMigrCasesDTO() {
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
public Long getCaseCode() {
   return caseCode;
}
/**
* @return String 
*/
public String getName(){
   return name ;
}
/**
* @return Long
*/
public Long getNeedUpdate() {
   return needUpdate;
}

    
/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) {
   this.datatypCode=datatypCode;
}
/**
* @param caseCode
*/
public void setCaseCode(Long caseCode) {
   this.caseCode=caseCode;
}
/**
* @param name
*/
public void setName( String name ){
   this.name = name;
}
/**
* @param needUpdate
*/
public void setNeedUpdate(Long needUpdate) {
   this.needUpdate=needUpdate;
}

    
}

