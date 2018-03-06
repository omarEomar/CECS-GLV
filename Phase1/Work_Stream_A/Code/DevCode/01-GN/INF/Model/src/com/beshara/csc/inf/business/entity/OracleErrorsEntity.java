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
 * This Class Manipulate the Persistence Methods of OracleErrors Entity.
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
@NamedQueries( { @NamedQuery(name = "OracleErrorsEntity.findAll", 
                             query = "select o from OracleErrorsEntity o order by o.errCode")
        , 
        @NamedQuery(name = "OracleErrorsEntity.findNewId", query = "select MAX(o.errNum) from OracleErrorsEntity o")
        , 
        @NamedQuery(name = "OracleErrorsEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.OracleErrorsDTO(o.errNum,o.errAMsg) from OracleErrorsEntity o order by o.errAMsg")
        , 
        @NamedQuery(name = "OracleErrorsEntity.searchByName", query = "select o from OracleErrorsEntity o where o.errAMsg like :errAMsg order by o.errAMsg")
        , 
        @NamedQuery(name = "OracleErrorsEntity.searchByCode", query = "select o from OracleErrorsEntity o where o.errCode=:errCode order by o.errAMsg")
     
        
        
        } )
@Table(name = "INF_ORACLE_ERRORS")
@IdClass(IOracleErrorsEntityKey.class)
public class OracleErrorsEntity extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ERR_NUM", nullable = false)
    private Long errNum;
    @Column(name = "ERR_CODE", nullable = true)
    private Long errCode;
    @Column(name = "ERR_E_MSG", nullable = true)
    private String errEMsg;
    @Column(name = "ERR_A_MSG", nullable = true)
    private String errAMsg;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * OracleErrorsEntity Default Constructor
     */
    public OracleErrorsEntity() {
    }


    /**
     * @return Long
     */
    public Long getErrNum() {
        return errNum;
    }

    /**
     * @return String
     */
    public String getErrEMsg() {
        return errEMsg;
    }

    /**
     * @return String
     */
    public String getErrAMsg() {
        return errAMsg;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param errNum
     */
    public void setErrNum(Long errNum) {
        this.errNum = errNum;
    }

    /**
     * @param errEMsg
     */
    public void setErrEMsg(String errEMsg) {
        this.errEMsg = errEMsg;
    }

    /**
     * @param errAMsg
     */
    public void setErrAMsg(String errAMsg) {
        this.errAMsg = errAMsg;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


    public void setErrCode(Long errCode) {
        this.errCode = errCode;
    }

    public Long getErrCode() {
        return errCode;
    }
}
