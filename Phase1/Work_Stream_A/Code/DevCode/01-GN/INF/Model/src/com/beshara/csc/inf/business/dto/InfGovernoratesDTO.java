package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfGovernoratesEntity;
import com.beshara.csc.inf.business.entity.InfGovernoratesEntityKey;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
public class InfGovernoratesDTO extends InfDTO implements IInfGovernoratesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long governorateCode;
    private String governorateName;
    private Long auditStatus;
    private Long tabrecSerial;


    /**
     * InfGovernoratesDTO Default Constructor
     */
    public InfGovernoratesDTO() {
        super();
    }
    
    public InfGovernoratesDTO(Long governoratesCode, String governoratesName) {
        this.setCode(new InfGovernoratesEntityKey(governoratesCode));
        this.setName(governoratesName);
    }

    /**
     * @param infGovernoratesEntity
     */
    public InfGovernoratesDTO(InfGovernoratesEntity infGovernoratesEntity) {
        
        this.governorateCode = infGovernoratesEntity.getGovernorateCode();
        this.setCode(new InfGovernoratesEntityKey(infGovernoratesEntity.getGovernorateCode()));
        this.setName(infGovernoratesEntity.getGovernorateName());
        this.governorateName = infGovernoratesEntity.getGovernorateName();
        this.setCreatedBy(infGovernoratesEntity.getCreatedBy());
        this.setCreatedDate(infGovernoratesEntity.getCreatedDate()); 
        this.setLastUpdatedBy(infGovernoratesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(infGovernoratesEntity.getLastUpdatedDate());
        this.setAuditStatus(infGovernoratesEntity.getAuditStatus());
        this.setTabrecSerial(infGovernoratesEntity.getTabrecSerial());


    }

    /**
     * @return Long
     */
    public Long getGovernorateCode() {
        return governorateCode;
    }

    /**
     * @return String
     */
    public String getGovernorateName() {
        return governorateName;
    }




    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param governorateCode
     */
    public void setGovernorateCode(Long governorateCode) {
        this.governorateCode = governorateCode;
    }

    /**
     * @param governorateName
     */
    public void setGovernorateName(String governorateName) {
        this.governorateName = governorateName;
    }


    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


}

