package com.beshara.csc.inf.business.dto;
import com.beshara.base.dto.*;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
import com.beshara.csc.inf.business.entity.NewspapersEntityKey;
import java.io.Serializable;
import java.sql.Timestamp;
public class NewspapersDTO extends InfDTO implements INewspapersDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
     private Long paperId ; 
    private String paperLocation;
   private String paperName ; 
    private Long tabrecSerial;
    public NewspapersDTO() {    }  
    public NewspapersDTO(Long paperCode, String paperName) {   
        setCode(new NewspapersEntityKey(paperCode));
        setName(paperName);
    }  
        public NewspapersDTO(NewspapersEntity newspapersEntity) { 
        this.auditStatus = newspapersEntity.getAuditStatus();
        this.setCreatedBy(newspapersEntity.getCreatedBy());
        this.setCreatedDate(newspapersEntity.getCreatedDate());
        this.setLastUpdatedBy(newspapersEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(newspapersEntity.getLastUpdatedDate());
        this.paperId = newspapersEntity.getPaperId ( ) ; 
        this.paperLocation = newspapersEntity.getPaperLocation();
        this.paperName = newspapersEntity.getPaperName ( ) ; 
        setCode(new NewspapersEntityKey(newspapersEntity.getPaperId()));
        setName(newspapersEntity.getPaperName());
    } 
        public void setAuditStatus(Long auditStatus) {    
            this.auditStatus = auditStatus;
            
    }   
        public Long getAuditStatus() {     
            return auditStatus;
    } 
         
     public void setPaperId ( Long paperId ) { 
     this.paperId = paperId ; 
     } 
     
     public Long getPaperId ( ) { 
     return paperId ; 
     } 
    public void setPaperLocation(String paperLocation) {        this.paperLocation = paperLocation;
    }    public String getPaperLocation() {        return paperLocation;
    } 
     public void setPaperName ( String paperName ) { 
     this.paperName = paperName ; 
     } 
     
     public String getPaperName ( ) { 
     return paperName ; 
     } 
     
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }}
