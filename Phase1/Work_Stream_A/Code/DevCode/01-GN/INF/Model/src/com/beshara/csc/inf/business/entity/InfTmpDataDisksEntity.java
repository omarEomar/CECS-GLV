package com.beshara.csc.inf.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

@Entity
@NamedQueries({
@NamedQuery(name = "InfTmpDataDisksEntity.findAll", query = "select o from InfTmpDataDisksEntity o"),
@NamedQuery(name = "InfTmpDataDisksEntity.getByDataTypeCode", query = "select o from InfTmpDataDisksEntity o where o.datatypCode=:datatypCode order by o.diskCode"),
@NamedQuery(name = "InfTmpDataDisksEntity.findNewId", query = "select MAX(o.datatypCode) from InfTmpDataDisksEntity o")


})
@Table(name = "INF_TMP_DATA_DISKS")
@IdClass(IInfTmpDataDisksEntityKey.class)

public class InfTmpDataDisksEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Id
@Column(name="DATATYP_CODE", nullable=false)
private Long datatypCode;
@Id
@Column(name="DISK_CODE", nullable=false)
private Long diskCode;
@Column(name="DISK_DATE", nullable=false)
private Timestamp diskDate;
//@ManyToOne 
//@JoinColumn(name="DATATYP_CODE", referencedColumnName="DATATYP_CODE")
//private InfTmpDataTypesEntity infTmpDataTypesEntity;
 

    /**
     * InfTmpDataDisksEntity Default Constructor
     */    
    public InfTmpDataDisksEntity() {
    }


/**
* @return Long
*/
public Long getDatatypCode() {
   return datatypCode;
}
/**
* @return Long
*/
public Long getDiskCode() {
   return diskCode;
}
/**
* @return Timestamp
*/
public Timestamp getDiskDate() {
   return diskDate;
}


/**
* @param datatypCode
*/
public void setDatatypCode(Long datatypCode) {
   this.datatypCode=datatypCode;
}
/**
* @param diskCode
*/
public void setDiskCode(Long diskCode) {
   this.diskCode=diskCode;
}
/**
* @param diskDate
*/
public void setDiskDate(Timestamp diskDate) {
   this.diskDate=diskDate;
}


}
