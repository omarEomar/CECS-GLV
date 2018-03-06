package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.IInfTmpDataFieldsDTO;
import com.beshara.csc.inf.business.entity.IInfTmpDataFieldsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfTmpDataFieldsEntity;
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

public class InfTmpDataFieldsDAO extends InfBaseDAO {

    /**
     * InfTmpDataFieldsDAO Default Constructor
     */
    public InfTmpDataFieldsDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new InfTmpDataFieldsDAO();
    }


    /**<code>select o from InfTmpDataFieldsEntity o</code>.
     * @return List
     */
    public List<IInfTmpDataFieldsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfTmpDataFieldsEntity> list =
                EM().createNamedQuery("InfTmpDataFieldsEntity.findAll").getResultList();
            for (InfTmpDataFieldsEntity infTmpDataFields : list) {
                arrayList.add(InfEntityConverter.getInfTmpDataFieldsDTO(infTmpDataFields));

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
     * Create a New InfTmpDataFieldsEntity
     * @param infTmpDataFieldsDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTmpDataFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfTmpDataFieldsEntity infTmpDataFieldsEntity = new InfTmpDataFieldsEntity();

            IInfTmpDataFieldsDTO infTmpDataFieldsDTO = (IInfTmpDataFieldsDTO)infTmpDataFieldsDTO1;

            infTmpDataFieldsEntity.setDatatypCode(infTmpDataFieldsDTO.getDatatypCode());
            infTmpDataFieldsEntity.setFieldOrder(infTmpDataFieldsDTO.getFieldOrder());
            infTmpDataFieldsEntity.setName(infTmpDataFieldsDTO.getName());
            infTmpDataFieldsEntity.setVariableName(infTmpDataFieldsDTO.getVariableName());
            infTmpDataFieldsEntity.setFieldLabel(infTmpDataFieldsDTO.getFieldLabel());
            infTmpDataFieldsEntity.setFldtypeCode(infTmpDataFieldsDTO.getFldtypeCode());


            doAdd(infTmpDataFieldsEntity);
            IInfTmpDataFieldsEntityKey ek =
                InfEntityKeyFactory.createInfTmpDataFieldsEntityKey(infTmpDataFieldsEntity.getDatatypCode(),
                                                                    infTmpDataFieldsEntity.getFieldOrder());
            infTmpDataFieldsDTO.setCode(ek);

            // Set PK after creation
            return infTmpDataFieldsDTO;
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
     * Update an Existing InfTmpDataFieldsEntity
     * @param infTmpDataFieldsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTmpDataFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpDataFieldsDTO infTmpDataFieldsDTO = (IInfTmpDataFieldsDTO)infTmpDataFieldsDTO1;

            InfTmpDataFieldsEntity infTmpDataFieldsEntity =
                EM().find(InfTmpDataFieldsEntity.class, (IInfTmpDataFieldsEntityKey)infTmpDataFieldsDTO.getCode());

            infTmpDataFieldsEntity.setName(infTmpDataFieldsDTO.getName());
            infTmpDataFieldsEntity.setVariableName(infTmpDataFieldsDTO.getVariableName());
            infTmpDataFieldsEntity.setFieldLabel(infTmpDataFieldsDTO.getFieldLabel());
            infTmpDataFieldsEntity.setFldtypeCode(infTmpDataFieldsDTO.getFldtypeCode());

            doUpdate(infTmpDataFieldsEntity);
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
     * Remove an Existing InfTmpDataFieldsEntity
     * @param infTmpDataFieldsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTmpDataFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpDataFieldsDTO infTmpDataFieldsDTO = (IInfTmpDataFieldsDTO)infTmpDataFieldsDTO1;

            InfTmpDataFieldsEntity infTmpDataFieldsEntity =
                EM().find(InfTmpDataFieldsEntity.class, (IInfTmpDataFieldsEntityKey)infTmpDataFieldsDTO.getCode());

            if (infTmpDataFieldsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infTmpDataFieldsEntity);
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
     * Get InfTmpDataFieldsEntity By Primary Key
     * @param id
     * @return InfTmpDataFieldsDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpDataFieldsEntityKey id = (IInfTmpDataFieldsEntityKey)id1;

            InfTmpDataFieldsEntity infTmpDataFieldsEntity = EM().find(InfTmpDataFieldsEntity.class, id);
            if (infTmpDataFieldsEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfTmpDataFieldsDTO infTmpDataFieldsDTO =
                InfEntityConverter.getInfTmpDataFieldsDTO(infTmpDataFieldsEntity);
            return infTmpDataFieldsDTO;
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
     * Get the MaxId of InfTmpDataFieldsEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfTmpDataFieldsEntity.findNewId").getSingleResult();
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

    public List<IBasicDTO> getDataFieldsByType(Long dataType) throws DataBaseException,SharedApplicationException{
        try {
            ArrayList arrayList = new ArrayList();
            List<InfTmpDataFieldsEntity> list =
                EM().createNamedQuery("InfTmpDataFieldsEntity.getDataFieldsByType").setParameter("dataType", dataType).getResultList();
            for (InfTmpDataFieldsEntity infTmpDataFields : list) {
                arrayList.add(InfEntityConverter.getInfTmpDataFieldsDTO(infTmpDataFields));

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

}
