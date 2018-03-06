package com.beshara.csc.inf.business.entity.bgt;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HR_BGT_PROGRAMS")
public class BgtProgramsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "PRG_CODE", nullable = false)
    private String prgCode;
    @Column(name = "PRG_NAME", nullable = false)
    private String prgName;

    public BgtProgramsEntity() {
    }


    public String getPrgCode() {
        return prgCode;
    }

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgName() {
        return prgName;
    }

    public void setPrgName(String prgName) {
        this.prgName = prgName;
    }
}
