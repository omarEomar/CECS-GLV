package com.beshara.csc.inf.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.io.Serializable;

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
 * This Class Manipulate the Persistence Methods of GroupCountries Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 *     
 * @author       Beshara Group   
 * @version      1.0   
 *
 * @author Aly Noor 
 * @version      2.0 
 * @since 06/29/2014 eidted to extends BasicEntity to follow project standard and to be used through generic method InfBaseFacadeBean.searchByName
 */

@Entity
@NamedQueries


( { @NamedQuery(name = "NewspapersEntity.findAll", 
                query = "select o from NewspapersEntity o order by o.paperName")
        , 
        @NamedQuery(name = "NewspapersEntity.findNewId", query = "select MAX(o.paperId) from NewspapersEntity o")
        , 
        @NamedQuery(name = "NewspapersEntity.getCodeName", query = "select new com.beshara.csc.inf.business.dto.NewspapersDTO(o.paperId, o.paperName) from NewspapersEntity o order by o.paperName")
               , 
        @NamedQuery(name = "NewspapersEntity.searchByName", query = "select o from NewspapersEntity o where o.paperName like :paperName order by o.paperName")
        , 
        @NamedQuery(name = "NewspapersEntity.searchByCode", query = "select o from NewspapersEntity o where o.paperId=:paperId order by o.paperName")
        ,
        @NamedQuery(name = "NewspapersEntity.getByName", query = "select o from NewspapersEntity o where o.paperName =:name") 
      } )
@Table(name = "INF_NEWSPAPERS")
@IdClass(INewspapersEntityKey.class)
public class NewspapersEntity  extends BasicEntity  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "PAPER_ID", nullable = false)
    private Long paperId;
    @Column(name = "PAPER_LOCATION")
    private String paperLocation;
    @Column(name = "PAPER_NAME", nullable = false)
    private String paperName;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;


    public NewspapersEntity() {
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getPaperLocation() {
        return paperLocation;
    }

    public void setPaperLocation(String paperLocation) {
        this.paperLocation = paperLocation;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
