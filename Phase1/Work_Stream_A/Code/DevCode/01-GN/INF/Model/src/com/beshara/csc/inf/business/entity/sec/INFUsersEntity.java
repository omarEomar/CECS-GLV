package com.beshara.csc.inf.business.entity.sec;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.gn.sec.business.entity.IUsersEntityKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries(value =
              { @NamedQuery(name = "Users.findAll", query = "select o from UsersEntity o"), @NamedQuery(name = "Users.findAllByCivilId",
                                                                                                        query =
                                                                                                        "select o from UsersEntity o where o.civilId=:civilId") })
@Entity
@Table(name = "GN_SEC_USERS")
@IdClass(IUsersEntityKey.class)
public class INFUsersEntity extends BasicEntity {
    public INFUsersEntity() {
        super();
    }


    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "DB_USER")
    private String dbUser;
    @Column(nullable = false)
    private Long locked;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Id
    @Column(name = "USER_CODE", nullable = false)
    private Long userCode;
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "CIVIL_ID", nullable = true)
    private Long civilId;


    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public Long getLocked() {
        return locked;
    }

    public void setLocked(Long locked) {
        this.locked = locked;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }
}

