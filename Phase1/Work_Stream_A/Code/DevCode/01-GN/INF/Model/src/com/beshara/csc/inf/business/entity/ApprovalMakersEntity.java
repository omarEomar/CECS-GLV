package com.beshara.csc.inf.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.gn.sec.business.entity.AprovalMakerUsersEntity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of ApprovalMakers Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Abdul Mejid  30-Oct-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 * @EditedBy @author Aly Noor @since 06/26/2014 eidted to extends BasicEntity
 * to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */
@Entity
@NamedQueries( { @NamedQuery(name = "ApprovalMakersEntity.findAll",
                             query = "select o from ApprovalMakersEntity o order by o.aprmakerName"),
                 @NamedQuery(name = "ApprovalMakersEntity.findNewId",
                             query = "select MAX(o.aprmakerCode) from ApprovalMakersEntity o"),
                 @NamedQuery(name = "ApprovalMakersEntity.findCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.ApprovalMakersDTO(o.aprmakerCode,o.aprmakerName) from ApprovalMakersEntity o"),
                 @NamedQuery(name = "ApprovalMakersEntity.getCodeName",
                             query = "select new com.beshara.csc.inf.business.dto.ApprovalMakersDTO(o.aprmakerCode,o.aprmakerName) from ApprovalMakersEntity o order by o.aprmakerName"),
                 @NamedQuery(name = "ApprovalMakersEntity.searchByName",
                             query = "select o from ApprovalMakersEntity o where o.aprmakerName like :aprmakerName order by o.aprmakerName"),
                 @NamedQuery(name = "ApprovalMakersEntity.searchByCode",
                             query = "select o from ApprovalMakersEntity o where o.aprmakerCode=:aprmakerCode order by o.aprmakerName"),
                 @NamedQuery(name = "ApprovalMakersEntity.findApprovalMakerForMov",
                             query = "select new com.beshara.csc.inf.business.dto.ApprovalMakersDTO(o.aprmakerCode,o.aprmakerName) from ApprovalMakersEntity o where o.aprmakerCode in (261,262,263)"),
                 @NamedQuery(name = "ApprovalMakersEntity.getByName",
                             query = "select o from ApprovalMakersEntity o where o.aprmakerName =:name")})
@Table(name = "INF_APPROVAL_MAKERS")
@IdClass(IApprovalMakersEntityKey.class)
public class ApprovalMakersEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "APRMAKER_CODE", nullable = false)
    private Long aprmakerCode;
    @Column(name = "APRMAKER_NAME", nullable = false)
    private String aprmakerName;
    @Column(name = "DYNAMIC_FLAG", nullable = false)
    private String dynamicFlag;
    @Column(name = "FUNCTION_NAME")
    private String functionName;
    @OneToMany(mappedBy = "approvalMakersEntity")
    private List<AprovalMakerUsersEntity> aprovalMakerUsersEntityList;

    public ApprovalMakersEntity() {
    }

    /**
     *
     * @return
     */
    public Long getAprmakerCode() {
        return aprmakerCode;
    }

    /**
     *
     * @param aprmakerCode
     */
    public void setAprmakerCode(Long aprmakerCode) {
        this.aprmakerCode = aprmakerCode;
    }

    /**
     *
     * @return
     */
    public String getAprmakerName() {
        return aprmakerName;
    }

    /**
     *
     * @param aprmakerName
     */
    public void setAprmakerName(String aprmakerName) {
        this.aprmakerName = aprmakerName;
    }

    public void setAprovalMakerUsersEntityList(List<AprovalMakerUsersEntity> aprovalMakerUsersEntityList) {
        this.aprovalMakerUsersEntityList = aprovalMakerUsersEntityList;
    }

    public List<AprovalMakerUsersEntity> getAprovalMakerUsersEntityList() {
        return aprovalMakerUsersEntityList;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setDynamicFlag(String dynamicFlag) {
        this.dynamicFlag = dynamicFlag;
    }

    public String getDynamicFlag() {
        return dynamicFlag;
    }
}
