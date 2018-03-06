package com.beshara.csc.inf.business.dto;
import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntity;
import com.beshara.csc.inf.business.entity.IApprovalMakersEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
/** 
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ; 
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ; 
 * Taha Abdul Mejid 30-Oct-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ; 
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group 
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany 
 * @version 1.0 
 * @since 03/09/2007 
 */
public class ApprovalMakersDTO extends InfDTO implements IApprovalMakersDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    /** 
     * ApprovalMakersDTO Default Constructor */
    public ApprovalMakersDTO() {    }    /** 
     * @param approvalMakersEntity 
     */
                                          
     private Long aprmakerCode;
    private String aprmakerName;
    private String dynamicFlag ="0";
    private  String functionName; 
                                          
    public ApprovalMakersDTO(ApprovalMakersEntity approvalMakersEntity) {       
        this.setCode(InfEntityKeyFactory.createApprovalMakersEntityKey(approvalMakersEntity.getAprmakerCode()));
        this.setAprmakerCode(approvalMakersEntity.getAprmakerCode());
        this.setName(approvalMakersEntity.getAprmakerName());
        this.setAprmakerName(approvalMakersEntity.getAprmakerName());
        this.setDynamicFlag(approvalMakersEntity.getDynamicFlag());
        this.setFunctionName(approvalMakersEntity.getFunctionName());
         this.setCreatedBy(approvalMakersEntity.getCreatedBy());
        this.setCreatedDate(approvalMakersEntity.getCreatedDate());
        this.setLastUpdatedBy(approvalMakersEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(approvalMakersEntity.getLastUpdatedDate());
    }    /** 
     * Constructor to get name and code * @param code 
     * @param name 
     */
    public ApprovalMakersDTO(Long code, String name) {        
        this.setCode(InfEntityKeyFactory.createApprovalMakersEntityKey(code));
        this.setName(name);
    }

    public void setAprmakerCode(Long aprmakerCode) {
        this.aprmakerCode = aprmakerCode;
    }

    public Long getAprmakerCode() {
        return aprmakerCode;
    }

    public void setAprmakerName(String aprmakerName) {
        this.aprmakerName = aprmakerName;
    }

    public String getAprmakerName() {
        return aprmakerName;
    }

    public void setDynamicFlag(String dynamicFlag) {
        this.dynamicFlag = dynamicFlag;
    }

    public String getDynamicFlag() {
 
  
        return dynamicFlag;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
