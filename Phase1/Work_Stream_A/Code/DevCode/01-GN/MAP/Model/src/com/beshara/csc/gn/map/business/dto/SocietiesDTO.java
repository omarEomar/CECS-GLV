package com.beshara.csc.gn.map.business.dto;

import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class SocietiesDTO extends MapClientDTO implements ISocietiesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

 // private Long socCode ; 
    //private String socName ; 
    
    
    private Long minCode;
    private Long societiesStatus;
    /** 
     * SocietiesDTO Default Constructor */
    public SocietiesDTO() {    }    /** 
     * @param societiesEntity 
     */
    @Deprecated
    public SocietiesDTO(SocietiesEntity societiesEntity) {        MapEntityConverter.fillSocietiesDTO(this, societiesEntity);
    }    public SocietiesDTO(Long socCode, String socName) {        this.setCode(MapEntityKeyFactory.createSocietiesEntityKey(socCode));
        this.setName(socName);
    } /**
 * @return Long
 */
    //public Long getSocCode ( ) { 
    // return socCode ; 
    // } 
    /**
 * @return String
 */
    //public String getSocName ( ) { 
    // return socName ; 
    // } 
    /**
 * @param socCode
 */
    //public void setSocCode ( Long socCode ) { 
    // this.socCode=socCode ; 
    // }
    //public void setSocName ( String socName ) {
    // this.socName=socName ; 
    // }

    
   

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setSocietiesStatus(Long societiesStatus) {
        this.societiesStatus = societiesStatus;
    }

    public Long getSocietiesStatus() {
        return societiesStatus;
    }
}
