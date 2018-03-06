package com.beshara.csc.inf.business.dto;
import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.BuildingOwnerTypesEntity;
import com.beshara.csc.inf.business.entity.IBuildingOwnerTypesEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
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
public class BuildingOwnerTypesDTO extends InfDTO implements IBuildingOwnerTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
        private String buildingOwnerName;
    /** 
     * BuildingOwnerTypesDTO Default Constructor */
    public BuildingOwnerTypesDTO() {    }    /** 
     * @param buildingOwnerTypesEntity 
     */
    public BuildingOwnerTypesDTO(BuildingOwnerTypesEntity buildingOwnerTypesEntity) {        this.setCode(InfEntityKeyFactory.createBuildingOwnerTypesEntityKey(buildingOwnerTypesEntity.getOwntypeCode()));
        this.setName(buildingOwnerTypesEntity.getOwntypeName());
        this.setCreatedDate(buildingOwnerTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(buildingOwnerTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(buildingOwnerTypesEntity.getLastUpdatedDate());
        this.setCreatedBy(buildingOwnerTypesEntity.getCreatedBy());
        this.setBuildingOwnerName(buildingOwnerTypesEntity.getOwntypeName());
    }    public BuildingOwnerTypesDTO(Long code, String name) {        setCode(InfEntityKeyFactory.createBuildingOwnerTypesEntityKey(code));
        setName(name);
    }

    public void setBuildingOwnerName(String buildingOwnerName) {
        this.buildingOwnerName = buildingOwnerName;
    }

    public String getBuildingOwnerName() {
        return buildingOwnerName;
    }
}
