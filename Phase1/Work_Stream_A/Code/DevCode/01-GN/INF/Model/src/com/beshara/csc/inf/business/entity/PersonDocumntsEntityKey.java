package com.beshara.csc.inf.business.entity;
import com.beshara.base.entity.*;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.base.entity.*;
public class PersonDocumntsEntityKey extends EntityKey implements IPersonDocumntsEntityKey  {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    public Long civilId;
    public Long empDocSerial;
    public PersonDocumntsEntityKey() {    }    
    public PersonDocumntsEntityKey(Long civilId, Long empDocSerial) 
    {        super(new Object[] { civilId, empDocSerial });
        this.civilId = civilId;
        this.empDocSerial = empDocSerial;
    } 
    public PersonDocumntsEntityKey(String code) {        super(new Object[] { code });
        String[] codeArray = code.split("\\*");
        this.civilId = Long.parseLong(codeArray[0]);
        this.empDocSerial = Long.parseLong(codeArray[1]);
    }      
         public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }    public Long getEmpDocSerial() {        return empDocSerial;
    }
          
         
         }
