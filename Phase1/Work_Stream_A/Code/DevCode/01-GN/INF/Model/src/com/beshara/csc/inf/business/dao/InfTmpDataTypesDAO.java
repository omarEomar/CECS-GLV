package com.beshara.csc.inf.business.dao;

import com.beshara.base.entity.EntityKey;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.inf.business.dto.*;

import com.beshara.csc.inf.business.entity.IInfTmpDataTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfTmpDataTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import com.beshara.base.dao.BaseDAO;

import com.beshara.base.dto.IBasicDTO;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.beshara.base.entity.IEntityKey;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpDataTypesDAO extends InfBaseDAO  {

  /**
   * InfTmpDataTypesDAO Default Constructor
   */
    public InfTmpDataTypesDAO() {
	   super();
    }
    
	
	@Override
    protected BaseDAO newInstance() {
        return new InfTmpDataTypesDAO();
    }
	

    /**<code>select o from InfTmpDataTypesEntity o</code>.
     * @return List
     */
    public List<IInfTmpDataTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
	try{
       ArrayList arrayList = new ArrayList();
        List<InfTmpDataTypesEntity> list = EM().createNamedQuery("InfTmpDataTypesEntity.findAll").getResultList();
        for (InfTmpDataTypesEntity infTmpDataTypes : list )  {
            arrayList.add(InfEntityConverter.getInfTmpDataTypesDTO(infTmpDataTypes));
			
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
     * Create a New InfTmpDataTypesEntity
     * @param infTmpDataTypesDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTmpDataTypesDTO1) throws DataBaseException, SharedApplicationException {
        try{
            InfTmpDataTypesEntity  infTmpDataTypesEntity = new InfTmpDataTypesEntity();
            
            IInfTmpDataTypesDTO infTmpDataTypesDTO = (IInfTmpDataTypesDTO) infTmpDataTypesDTO1;
            
            infTmpDataTypesEntity.setSocCode(infTmpDataTypesDTO.getSocCode()); 
infTmpDataTypesEntity.setDatatypCode(infTmpDataTypesDTO.getDatatypCode()); 
infTmpDataTypesEntity.setName(infTmpDataTypesDTO.getName()); 
infTmpDataTypesEntity.setWsUser(infTmpDataTypesDTO.getWsUser()); 
infTmpDataTypesEntity.setWsPass(infTmpDataTypesDTO.getWsPass()); 
infTmpDataTypesEntity.setWsDisk(infTmpDataTypesDTO.getWsDisk()); 
infTmpDataTypesEntity.setWsUrl(infTmpDataTypesDTO.getWsUrl()); 

            
            doAdd(infTmpDataTypesEntity);
			IInfTmpDataTypesEntityKey ek = InfEntityKeyFactory.createInfTmpDataTypesEntityKey(infTmpDataTypesEntity.getDatatypCode());
infTmpDataTypesDTO.setCode( ek );

            // Set PK after creation
            return infTmpDataTypesDTO;
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
     * Update an Existing InfTmpDataTypesEntity
     * @param infTmpDataTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTmpDataTypesDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataTypesDTO infTmpDataTypesDTO = (IInfTmpDataTypesDTO) infTmpDataTypesDTO1;
        
        InfTmpDataTypesEntity  infTmpDataTypesEntity = EM().find(InfTmpDataTypesEntity.class, (IInfTmpDataTypesEntityKey) infTmpDataTypesDTO.getCode());
        
        infTmpDataTypesEntity.setSocCode(infTmpDataTypesDTO.getSocCode()); 
infTmpDataTypesEntity.setName(infTmpDataTypesDTO.getName()); 
infTmpDataTypesEntity.setWsUser(infTmpDataTypesDTO.getWsUser()); 
infTmpDataTypesEntity.setWsPass(infTmpDataTypesDTO.getWsPass()); 
infTmpDataTypesEntity.setWsDisk(infTmpDataTypesDTO.getWsDisk()); 
infTmpDataTypesEntity.setWsUrl(infTmpDataTypesDTO.getWsUrl()); 

        doUpdate(infTmpDataTypesEntity);
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
     * Remove an Existing InfTmpDataTypesEntity
     * @param infTmpDataTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTmpDataTypesDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataTypesDTO infTmpDataTypesDTO = (IInfTmpDataTypesDTO) infTmpDataTypesDTO1;
        
        InfTmpDataTypesEntity  infTmpDataTypesEntity = EM().find(InfTmpDataTypesEntity.class, (IInfTmpDataTypesEntityKey) infTmpDataTypesDTO.getCode());
        
        if (infTmpDataTypesEntity == null)  {
             throw new ItemNotFoundException();   
        }
        doRemove(infTmpDataTypesEntity);
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
     * Get InfTmpDataTypesEntity By Primary Key
     * @param id
     * @return InfTmpDataTypesDTO
     */    
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataTypesEntityKey id = (IInfTmpDataTypesEntityKey) id1;
        
        InfTmpDataTypesEntity infTmpDataTypesEntity = EM().find(InfTmpDataTypesEntity.class, id);
        if (infTmpDataTypesEntity == null)  {
             throw new ItemNotFoundException();   
        }
        IInfTmpDataTypesDTO infTmpDataTypesDTO = InfEntityConverter.getInfTmpDataTypesDTO(infTmpDataTypesEntity);
        return infTmpDataTypesDTO;
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
     * Get the MaxId of InfTmpDataTypesEntity
     * <br>
     * @return Object
     */
       public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfTmpDataTypesEntity.findNewId").getSingleResult();
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
	
	
	/**
     * Get all by code equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfTmpDataTypesEntity> list =
                EM().createNamedQuery("InfTmpDataTypesEntity.searchByCode").setParameter("code",
                                                                                   code).getResultList();
            for (InfTmpDataTypesEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfTmpDataTypesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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
     * Get all by Name equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfTmpDataTypesEntity> list =
                EM().createNamedQuery("InfTmpDataTypesEntity.searchByName").setParameter("name", "%" + searchName + "%").getResultList();
            for (InfTmpDataTypesEntity entity : list) {
                arrayList.add(InfEntityConverter.getInfTmpDataTypesDTO(entity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

	
	


}
