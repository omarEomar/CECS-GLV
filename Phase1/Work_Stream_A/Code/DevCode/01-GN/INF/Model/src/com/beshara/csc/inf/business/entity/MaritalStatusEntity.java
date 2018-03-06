package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of MaritalStatus Entity.
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
 * @author Aly Noor
 * @version      2.0
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "MaritalStatusEntity.findAll",
                             query = "select o from MaritalStatusEntity o order by o.marStatusName"),
                 @NamedQuery(name = "MaritalStatusEntity.checkDublicateName",
                             query = "select o from MaritalStatusEntity o where o.marStatusName = :name"),
                 @NamedQuery(name = "MaritalStatusEntity.getRelatedGenders",
                             query = "select o from MaritalStatusEntity o where o.marstatusCode=:marstatusCode "),
                 @NamedQuery(name = "MaritalStatusEntity.findNewId",
                             query = "select MAX(o.marstatusCode) from MaritalStatusEntity o"),
                 @NamedQuery(name = "MaritalStatusEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.MaritalStatusDTO(o.marstatusCode,o.marStatusName) from MaritalStatusEntity o order by o.marStatusName"),
                 @NamedQuery(name = "MaritalStatusEntity.searchByName",
                             query = "select o from MaritalStatusEntity o where o.marStatusName like :marStatusName order by o.marStatusName"),
                 @NamedQuery(name = "MaritalStatusEntity.searchByCode",
                             query = "select o from MaritalStatusEntity o where o.marstatusCode=:maritalStatusCode order by o.marStatusName") })
@Table(name = "INF_MARITAL_STATUS")
@IdClass(IMaritalStatusEntityKey.class)
public class MaritalStatusEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "MARSTATUS_CODE", nullable = false)
    private Long marstatusCode;
    @Column(name = "MARSTATUS_NAME", nullable = false)
    private String marStatusName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    /**
     * MaritalStatusEntity Default Constructor
     */
    public MaritalStatusEntity() {
    }


    /**
     * @return Long
     */
    public Long getMarstatusCode() {
        return marstatusCode;
    }


    /**
     * @param marstatusCode
     */
    public void setMarstatusCode(Long marstatusCode) {
        this.marstatusCode = marstatusCode;
    }


    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setMarStatusName(String marStatusName) {
        this.marStatusName = marStatusName;
    }

    public String getMarStatusName() {
        return marStatusName;
    }

}
