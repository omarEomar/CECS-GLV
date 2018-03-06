package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of InfGovernorates Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
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

@Entity
@NamedQueries( { @NamedQuery(name = "InfGovernoratesEntity.findAll", 
                             query = "select o from InfGovernoratesEntity o")
        , 
        @NamedQuery(name = "InfGovernoratesEntity.findNewId", query = "select MAX(o.governorateCode) from InfGovernoratesEntity o")
        , 
        @NamedQuery(name = "InfGovernoratesEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.InfGovernoratesDTO(o.governorateCode,o.governorateName) from InfGovernoratesEntity o order by o.governorateName")
        , 
        @NamedQuery(name = "InfGovernoratesEntity.searchByName", query = "select o from InfGovernoratesEntity o where o.governorateName like :governorateName order by o.governorateName")
        , 
        @NamedQuery(name = "InfGovernoratesEntity.searchByCode", query = "select o from InfGovernoratesEntity o where o.governorateCode=:governorateCode order by o.governorateName")
        } )



@Table(name = "INF_GOVERNORATES")
@IdClass(IInfGovernoratesEntityKey.class)

public class InfGovernoratesEntity extends BasicEntity{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

@Id
@Column(name="GOVERNORATE_CODE", nullable=false)
private Long governorateCode;
@Column(name="GOVERNORATE_NAME", nullable=false)
private String governorateName;
@Column(name="CREATED_BY", nullable=true)
private Long createdBy;
@Column(name="CREATED_DATE", nullable=true)
private Timestamp createdDate;
@Column(name="LAST_UPDATED_BY", nullable=true)
private Long lastUpdatedBy;
@Column(name="LAST_UPDATED_DATE", nullable=true)
private Timestamp lastUpdatedDate;
@Column(name="AUDIT_STATUS", nullable=true)
private Long auditStatus;
@Column(name="TABREC_SERIAL", nullable=true)
private Long tabrecSerial;
 

    /**
     * InfGovernoratesEntity Default Constructor
     */    
    public InfGovernoratesEntity() {
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
public Long getCreatedBy() {
   return createdBy;
}
/**
* @return Timestamp
*/
public Timestamp getCreatedDate() {
   return createdDate;
}
/**
* @return Long
*/
public Long getLastUpdatedBy() {
   return lastUpdatedBy;
}
/**
* @return Timestamp
*/
public Timestamp getLastUpdatedDate() {
   return lastUpdatedDate;
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
   this.governorateCode=governorateCode;
}
/**
* @param governorateName
*/
public void setGovernorateName(String governorateName) {
   this.governorateName=governorateName;
}
/**
* @param createdBy
*/
public void setCreatedBy(Long createdBy) {
   this.createdBy=createdBy;
}
/**
* @param createdDate
*/
public void setCreatedDate(Timestamp createdDate) {
   this.createdDate=createdDate;
}
/**
* @param lastUpdatedBy
*/
public void setLastUpdatedBy(Long lastUpdatedBy) {
   this.lastUpdatedBy=lastUpdatedBy;
}
/**
* @param lastUpdatedDate
*/
public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
   this.lastUpdatedDate=lastUpdatedDate;
}
/**
* @param auditStatus
*/
public void setAuditStatus(Long auditStatus) {
   this.auditStatus=auditStatus;
}
/**
* @param tabrecSerial
*/
public void setTabrecSerial(Long tabrecSerial) {
   this.tabrecSerial=tabrecSerial;
}


}
