package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IFieldTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.inf.business.entity.FieldTypesEntityKey;
import com.beshara.csc.inf.business.entity.IFieldTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class FieldTypesDAO extends InfBaseDAO {
    public FieldTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new FieldTypesDAO();
    }

    /**<code>select o from FieldTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            System.out.println("In Gteall Faced");
            ArrayList arrayList = new ArrayList();
            List<FieldTypesEntity> list =
                EM().createNamedQuery("FieldTypesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list != null && list.size() == 0) {
                throw new FinderException();
            }
            System.out.println("list.size ( ) > " + list.size());
            for (FieldTypesEntity fieldTypes : list) {
                arrayList.add(InfDTOFactory.createFieldTypesDTO(fieldTypes));
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

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("FieldTypesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(0);
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
     * Create a New FieldTypesEntity * @param fieldTypesDTO
     * @return IFieldTypesDTO
     * @throws RemoteException
     */
    @Override
    public IBasicDTO add(IBasicDTO fieldTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IFieldTypesDTO fieldTypesDTO = (IFieldTypesDTO)fieldTypesDTO1;
            Long code = findNewId();
            FieldTypesEntity fieldTypesEntity = new FieldTypesEntity();
            fieldTypesEntity.setFldtypeCode(code);
            fieldTypesEntity.setFldtypeName(fieldTypesDTO.getName());
            doAdd(fieldTypesEntity);
            fieldTypesDTO.setCode(new FieldTypesEntityKey(code));
            return fieldTypesDTO;
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
     * Update an Existing FieldTypesEntity * @param fieldTypesDTO
     * @return Boolean
     * @throws RemoteException
     */
    @Override
    public Boolean update(IBasicDTO fieldTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IFieldTypesDTO fieldTypesDTO = (IFieldTypesDTO)fieldTypesDTO1;
            FieldTypesEntity fieldTypesEntity =
                EM().find(FieldTypesEntity.class, (IFieldTypesEntityKey)fieldTypesDTO.getCode());
            if (fieldTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            fieldTypesEntity.setFldtypeName(fieldTypesDTO.getName());
            doUpdate(fieldTypesEntity);
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
     * Remove an Existing FieldTypesEntity * @param fieldTypesDTO
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public Boolean remove(IBasicDTO fieldTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IFieldTypesDTO fieldTypesDTO = (IFieldTypesDTO)fieldTypesDTO1;
            FieldTypesEntity fieldTypesEntity =
                EM().find(FieldTypesEntity.class, (IFieldTypesEntityKey)fieldTypesDTO.getCode());
            if (fieldTypesEntity == null) {
                throw new FinderException();
            }
            doRemove(fieldTypesEntity);
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
     * Get FieldTypesEntity By Primary Key * @param id
     * @return IFieldTypesDTO
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            FieldTypesEntity fieldTypesEntity = EM().find(FieldTypesEntity.class, (IFieldTypesEntityKey)id);
            if (fieldTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IFieldTypesDTO fieldTypesDTO = InfDTOFactory.createFieldTypesDTO(fieldTypesEntity);
            return fieldTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**<code>select o from FieldTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("FieldTypesEntity.findCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**<code>select o from FieldTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<FieldTypesEntity> list =
                EM().createNamedQuery("FieldTypesEntity.findName").setParameter("name", name).getResultList();
            for (FieldTypesEntity fieldTypes : list) {
                arrayList.add(InfDTOFactory.createFieldTypesDTO(fieldTypes));
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

    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(InfEntityKeyFactory.createFieldTypesEntityKey((Long)code)));
            return list;
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
