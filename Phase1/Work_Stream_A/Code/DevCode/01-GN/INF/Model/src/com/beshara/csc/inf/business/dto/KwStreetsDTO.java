package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;


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
public class KwStreetsDTO extends InfDTO implements IKwStreetsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long streetLengthInKm;
    private String kwStreetsName;

    /**
     * KwStreetsDTO Default Constructor */
    public KwStreetsDTO() {
    }

    /**
     * @param kwStreetsEntity
     */
    public KwStreetsDTO(KwStreetsEntity kwStreetsEntity) {
        setCode(InfEntityKeyFactory.createKwStreetsEntityKey(kwStreetsEntity.getStreetCode()));
        setName(kwStreetsEntity.getStreetName());
        this.streetLengthInKm = kwStreetsEntity.getStreetLengthInKm();
        this.setCreatedBy(kwStreetsEntity.getCreatedBy());
        this.setCreatedDate(kwStreetsEntity.getCreatedDate());
        this.setLastUpdatedBy(kwStreetsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(kwStreetsEntity.getLastUpdatedDate());
        this.setKwStreetsName(kwStreetsEntity.getStreetName());
    }

    public KwStreetsDTO(Long code, String name) {
        this.setCode(InfEntityKeyFactory.createKwStreetsEntityKey(code));
        this.setName(name);
    }

    /**
     * @return Long
     */
    public Long getStreetLengthInKm() {
        return streetLengthInKm;
    }

    /**
     * @param streetLengthInKm
     */
    public void setStreetLengthInKm(Long streetLengthInKm) {
        this.streetLengthInKm = streetLengthInKm;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
    }

    public void setKwStreetsName(String kwStreetsName) {
        this.kwStreetsName = kwStreetsName;
    }

    public String getKwStreetsName() {
        return kwStreetsName;
    }
}
