package com.beshara.csc.gn.map.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.TreeDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;

import java.util.List;


/**
 */
public class MappedValueDTO extends TreeDTO implements IMappedValueDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long longCode;
    private String strCode;
    private String name;
    private boolean hasMappedValues;
    
    /** 
     * MappedValueDTO Default Constructor */
    public MappedValueDTO() {    }    /** 
     * @param longCode 
     * @param name 
     */
    public MappedValueDTO(Long longCode, String name) {        this.longCode = longCode;
        setName(name);
        this.name = name;
    }    /** 
     * * @param strCode 
     * @param name 
     */
    public MappedValueDTO(String strCode, String name) {        this.strCode = strCode;
        this.name = name;
        setName(name);
    } /**
 * * @param longCode
 */
    //public void setLOngCode ( Long longCode ) { 
    // this.longCode = longCode ; 
    // } 
    /**
 * @return Long
 */
    //public Long getCode ( ) { 
    // return longCode ; 
    // } 
    /** 
     * @param name 
     */
    public void setName(String name) {        this.name = name;
    }    /** 
     * @return String 
     */
    public String getName() {        return name;
    }    public void setStrCode(String strCode) {        setCode(new EntityKey(new Object[] { strCode }));
        this.strCode = strCode;
    }    public String getStrCode() {        return strCode;
    }    public void setHasMappedValues(boolean hasMappedValues) {        this.hasMappedValues = hasMappedValues;
    }    public boolean getHasMappedValues() {        return hasMappedValues;
    }


    @Override
    public IEntityKey getParentCode() {
        return super.getParentCode();
    }

   

    @Override
    public IBasicDTO getParentObject() {
        if(super.getParentObject() == null && getParentCode()!= null){
            IMappedValueDTO parentDTO = MapDTOFactory.createMappedValueDTO();
            parentDTO.setCode(getParentCode());
            setParentObject(parentDTO);
        }
        return super.getParentObject();
    }

   

    @Override
    public List<IBasicDTO> getChildrenList() {
        return super.getChildrenList();
    }

    
    @Override
    public Long getChildernNumbers() {
        return super.getChildernNumbers();
    }

    
    @Override
    public Long getLeafFlag() {
        return super.getLeafFlag();
    }

    

    @Override
    public boolean isBooleanLeafFlag() {
        return super.isBooleanLeafFlag();
    }

    
    @Override
    public Long getTreeLevel() {
        return super.getTreeLevel();
    }

    
    @Override
    public IEntityKey getFirstParent() {
        return super.getFirstParent();
    }
}
