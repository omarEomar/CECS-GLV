package com.beshara.csc.gn.map.business.dto;
import com.beshara.base.entity.*;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;
import java.io.Serializable;
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
public class ObjectTypesDTO extends MapClientDTO implements IObjectTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

 //private String objtypeName ; 
    /** 
     * ObjectTypesDTO Default Constructor */
    public ObjectTypesDTO() {    }    public ObjectTypesDTO(Long code, String name) {        setCode(MapEntityKeyFactory.createObjectTypesEntityKey(code));
        setName(name);
    }    /** 
     * @param objectTypesEntity 
     */
    @Deprecated
    public ObjectTypesDTO(ObjectTypesEntity objectTypesEntity) {        MapEntityConverter.fillObjectTypesDTO(this, objectTypesEntity);
    } /**
 * @return Long
 */
    //public Long getObjtypeCode ( ) { 
    // return objtypeCode ; 
    // } 
    /**
 * @return String
 */
    //public String getObjtypeName ( ) { 
    // return objtypeName ; 
    // } 
    /**
 * @param objtypeCode
 */
    //public void setObjtypeCode ( Long objtypeCode ) { 
    // this.objtypeCode = objtypeCode ; 
    // } 
    /**
 * @param objtypeName
 */
    //public void setObjtypeName ( String objtypeName ) { 
    // this.objtypeName = objtypeName ; 
    // } 
}
