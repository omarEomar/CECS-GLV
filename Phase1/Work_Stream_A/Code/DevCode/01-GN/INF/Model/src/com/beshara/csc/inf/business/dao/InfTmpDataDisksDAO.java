package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfTmpDataDisksDTO;
import com.beshara.csc.inf.business.entity.IInfTmpDataDisksEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfTmpDataDisksEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpDataDisksDAO extends InfBaseDAO  {

  /**
   * InfTmpDataDisksDAO Default Constructor
   */
    public InfTmpDataDisksDAO() {
	   super();
    }
    
	
	@Override
    protected BaseDAO newInstance() {
        return new InfTmpDataDisksDAO();
    }
	

    /**<code>select o from InfTmpDataDisksEntity o</code>.
     * @return List
     */
    public List<IInfTmpDataDisksDTO> getAll() throws DataBaseException, SharedApplicationException {
	try{
       ArrayList arrayList = new ArrayList();
        List<InfTmpDataDisksEntity> list = EM().createNamedQuery("InfTmpDataDisksEntity.findAll").getResultList();
        for (InfTmpDataDisksEntity infTmpDataDisks : list )  {
            arrayList.add(InfEntityConverter.getInfTmpDataDisksDTO(infTmpDataDisks));
			
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
     * Create a New InfTmpDataDisksEntity
     * @param infTmpDataDisksDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTmpDataDisksDTO1) throws DataBaseException, SharedApplicationException {
        try{
            InfTmpDataDisksEntity  infTmpDataDisksEntity = new InfTmpDataDisksEntity();
            
            IInfTmpDataDisksDTO infTmpDataDisksDTO = (IInfTmpDataDisksDTO) infTmpDataDisksDTO1;
            
            infTmpDataDisksEntity.setDatatypCode(infTmpDataDisksDTO.getDatatypCode()); 
infTmpDataDisksEntity.setDiskCode(infTmpDataDisksDTO.getDiskCode()); 
infTmpDataDisksEntity.setDiskDate(infTmpDataDisksDTO.getDiskDate()); 

            
            doAdd(infTmpDataDisksEntity);
			IInfTmpDataDisksEntityKey ek = InfEntityKeyFactory.createInfTmpDataDisksEntityKey(infTmpDataDisksEntity.getDatatypCode(),infTmpDataDisksEntity.getDiskCode());
infTmpDataDisksDTO.setCode( ek );

            // Set PK after creation
            return infTmpDataDisksDTO;
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
     * Update an Existing InfTmpDataDisksEntity
     * @param infTmpDataDisksDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTmpDataDisksDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataDisksDTO infTmpDataDisksDTO = (IInfTmpDataDisksDTO) infTmpDataDisksDTO1;
        
        InfTmpDataDisksEntity  infTmpDataDisksEntity = EM().find(InfTmpDataDisksEntity.class, (IInfTmpDataDisksEntityKey) infTmpDataDisksDTO.getCode());
        
        infTmpDataDisksEntity.setDiskDate(infTmpDataDisksDTO.getDiskDate()); 

        doUpdate(infTmpDataDisksEntity);
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
     * Remove an Existing InfTmpDataDisksEntity
     * @param infTmpDataDisksDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTmpDataDisksDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataDisksDTO infTmpDataDisksDTO = (IInfTmpDataDisksDTO) infTmpDataDisksDTO1;
        
        InfTmpDataDisksEntity  infTmpDataDisksEntity = EM().find(InfTmpDataDisksEntity.class, (IInfTmpDataDisksEntityKey) infTmpDataDisksDTO.getCode());
        
        if (infTmpDataDisksEntity == null)  {
             throw new ItemNotFoundException();   
        }
        doRemove(infTmpDataDisksEntity);
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
     * Get InfTmpDataDisksEntity By Primary Key
     * @param id
     * @return InfTmpDataDisksDTO
     */    
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        IInfTmpDataDisksEntityKey id = (IInfTmpDataDisksEntityKey) id1;
        
        InfTmpDataDisksEntity infTmpDataDisksEntity = EM().find(InfTmpDataDisksEntity.class, id);
        if (infTmpDataDisksEntity == null)  {
             throw new ItemNotFoundException();   
        }
        IInfTmpDataDisksDTO infTmpDataDisksDTO = InfEntityConverter.getInfTmpDataDisksDTO(infTmpDataDisksEntity);
        return infTmpDataDisksDTO;
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
     * Get the MaxId of InfTmpDataDisksEntity
     * <br>
     * @return Object
     */
       public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfTmpDataDisksEntity.findNewId").getSingleResult();
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
	
    public List<IBasicDTO> getByDataTypeCode(Long dataType) throws DataBaseException, SharedApplicationException {
        try{
       ArrayList arrayList = new ArrayList();
        List<InfTmpDataDisksEntity> list = EM().createNamedQuery("InfTmpDataDisksEntity.getByDataTypeCode").setParameter("datatypCode", dataType).getResultList();
        for (InfTmpDataDisksEntity infTmpDataDisks : list )  {
            arrayList.add(InfEntityConverter.getInfTmpDataDisksDTO(infTmpDataDisks));
                        
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

    public Long getDiskNum(Long dataType){
            Long number = null;
            StringBuilder sql =
                new StringBuilder("select max(d.DISK_CODE) from INF_TMP_DATA_DISKS d where d.DATATYP_CODE=" + dataType);
            Query query = EM().createNativeQuery(sql.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
                number = ((BigDecimal)count.get(0)).longValue();
            }
            return number;
        }

}
