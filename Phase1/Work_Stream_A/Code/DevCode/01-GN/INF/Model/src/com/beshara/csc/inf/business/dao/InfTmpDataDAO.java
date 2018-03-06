package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfTmpDataDTO;
import com.beshara.csc.inf.business.entity.IInfTmpDataEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfTmpDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpDataDAO extends InfBaseDAO  {

  /**
   * InfTmpDataDAO Default Constructor
   */
    public InfTmpDataDAO() {
	   super();
    }
    
	
	@Override
    protected BaseDAO newInstance() {
        return new InfTmpDataDAO();
    }
	

    /**<code>select o from InfTmpDataEntity o</code>.
     * @return List
     */
    public List<IInfTmpDataDTO> getAll() throws DataBaseException, SharedApplicationException {
	try{
       ArrayList arrayList = new ArrayList();
        List<InfTmpDataEntity> list = EM().createNamedQuery("InfTmpDataEntity.findAll").getResultList();
        for (InfTmpDataEntity infTmpData : list )  {
            arrayList.add(InfEntityConverter.getInfTmpDataDTO(infTmpData));
			
        }
        return arrayList;
            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
	}

   
    /**
     * Create a New InfTmpDataEntity
     * @param infTmpDataDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTmpDataDTO1) throws DataBaseException, SharedApplicationException {
        try{
            InfTmpDataEntity  infTmpDataEntity = new InfTmpDataEntity();
            
            IInfTmpDataDTO infTmpDataDTO = (IInfTmpDataDTO) infTmpDataDTO1;
            
            infTmpDataEntity.setDatatypCode(infTmpDataDTO.getDatatypCode()); 
infTmpDataEntity.setDiskCode(infTmpDataDTO.getDiskCode()); 
infTmpDataEntity.setCivilId(infTmpDataDTO.getCivilId()); 
infTmpDataEntity.setField6(infTmpDataDTO.getField6()); 
infTmpDataEntity.setField7(infTmpDataDTO.getField7()); 
infTmpDataEntity.setField4(infTmpDataDTO.getField4()); 
infTmpDataEntity.setField5(infTmpDataDTO.getField5()); 
infTmpDataEntity.setField1(infTmpDataDTO.getField1()); 
infTmpDataEntity.setField3(infTmpDataDTO.getField3()); 
infTmpDataEntity.setField2(infTmpDataDTO.getField2()); 
infTmpDataEntity.setField8(infTmpDataDTO.getField8()); 
infTmpDataEntity.setLoadStatus(infTmpDataDTO.getLoadStatus()); 
infTmpDataEntity.setMigrationStatus(infTmpDataDTO.getMigrationStatus()); 
infTmpDataEntity.setDatatypCodeMigr(infTmpDataDTO.getDatatypCodeMigr()); 
infTmpDataEntity.setCaseCode(infTmpDataDTO.getCaseCode()); 

            
            doAdd(infTmpDataEntity);
			IInfTmpDataEntityKey ek = InfEntityKeyFactory.createInfTmpDataEntityKey(infTmpDataEntity.getDatatypCode(),infTmpDataEntity.getDiskCode(),infTmpDataEntity.getCivilId());
infTmpDataDTO.setCode( ek );

            // Set PK after creation
            return infTmpDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Update an Existing InfTmpDataEntity
     * @param infTmpDataDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTmpDataDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataDTO infTmpDataDTO = (IInfTmpDataDTO) infTmpDataDTO1;
        
        InfTmpDataEntity  infTmpDataEntity = EM().find(InfTmpDataEntity.class, (IInfTmpDataEntityKey) infTmpDataDTO.getCode());
        
        infTmpDataEntity.setField6(infTmpDataDTO.getField6()); 
infTmpDataEntity.setField7(infTmpDataDTO.getField7()); 
infTmpDataEntity.setField4(infTmpDataDTO.getField4()); 
infTmpDataEntity.setField5(infTmpDataDTO.getField5()); 
infTmpDataEntity.setField1(infTmpDataDTO.getField1()); 
infTmpDataEntity.setField3(infTmpDataDTO.getField3()); 
infTmpDataEntity.setField2(infTmpDataDTO.getField2()); 
infTmpDataEntity.setField8(infTmpDataDTO.getField8()); 
infTmpDataEntity.setLoadStatus(infTmpDataDTO.getLoadStatus()); 
infTmpDataEntity.setMigrationStatus(infTmpDataDTO.getMigrationStatus()); 
infTmpDataEntity.setDatatypCodeMigr(infTmpDataDTO.getDatatypCodeMigr()); 
infTmpDataEntity.setCaseCode(infTmpDataDTO.getCaseCode()); 

        doUpdate(infTmpDataEntity);
        return Boolean.TRUE;  
            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
	}
    
    /**
     * Remove an Existing InfTmpDataEntity
     * @param infTmpDataDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTmpDataDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataDTO infTmpDataDTO = (IInfTmpDataDTO) infTmpDataDTO1;
        
        InfTmpDataEntity  infTmpDataEntity = EM().find(InfTmpDataEntity.class, (IInfTmpDataEntityKey) infTmpDataDTO.getCode());
        
        if (infTmpDataEntity == null)  {
             throw new ItemNotFoundException();   
        }
        doRemove(infTmpDataEntity);
        return Boolean.TRUE;  
            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
	}
    
    /**
     * Get InfTmpDataEntity By Primary Key
     * @param id
     * @return InfTmpDataDTO
     */    
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataEntityKey id = (IInfTmpDataEntityKey) id1;
        
        InfTmpDataEntity infTmpDataEntity = EM().find(InfTmpDataEntity.class, id);
        if (infTmpDataEntity == null)  {
             throw new ItemNotFoundException();   
        }
        IInfTmpDataDTO infTmpDataDTO = InfEntityConverter.getInfTmpDataDTO(infTmpDataEntity);
        return infTmpDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }   
   }
    
    
    /**
     * Get the MaxId of InfTmpDataEntity
     * <br>
     * @return Object
     */
       public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfTmpDataEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
            }
            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
	}
	
	

}
