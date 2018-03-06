package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IBloodGroupsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.BloodGroupsEntity;
import com.beshara.csc.inf.business.entity.BloodGroupsEntityKey;
import com.beshara.csc.inf.business.entity.IBloodGroupsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class BloodGroupsDAO extends InfBaseDAO {
    public BloodGroupsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new BloodGroupsDAO();
    }

    /**<code>select o from BloodGroupsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BloodGroupsEntity> list =
                EM().createNamedQuery("BloodGroupsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (BloodGroupsEntity bloodGroups : list) {
                arrayList.add(InfDTOFactory.createBloodGroupsDTO(bloodGroups));
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
            Long id = (Long)EM().createNamedQuery("BloodGroupsEntity.findNewId").getSingleResult();
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
     * Create a New BloodGroupsEntity * @param bloodGroupsDTO
     * @return IBloodGroupsDTO
     */
    @Override
    public IBasicDTO add(IBasicDTO bloodGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            BloodGroupsEntity bloodGroupsEntity = new BloodGroupsEntity();
            Long code = findNewId();
            IBloodGroupsDTO bloodGroupsDTO = (IBloodGroupsDTO)bloodGroupsDTO1;
            bloodGroupsEntity.setBldgroupCode(code);
            bloodGroupsEntity.setBldgroupName(bloodGroupsDTO.getName());
            doAdd(bloodGroupsEntity);
            bloodGroupsDTO.setBldgroupCode(code);
            bloodGroupsDTO.setCode(new BloodGroupsEntityKey(code));
            return bloodGroupsDTO;
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
     * Update an Existing BloodGroupsEntity * @param bloodGroupsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO bloodGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IBloodGroupsDTO bloodGroupsDTO = (IBloodGroupsDTO)bloodGroupsDTO1;
            BloodGroupsEntity bloodGroupsEntity = EM().find(BloodGroupsEntity.class, bloodGroupsDTO.getCode());
            bloodGroupsEntity.setBldgroupCode(((IBloodGroupsEntityKey)bloodGroupsDTO.getCode()).getBldgroupCode());
            bloodGroupsEntity.setBldgroupName(bloodGroupsDTO.getName());
            doUpdate(bloodGroupsEntity);
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
     * Remove an Existing BloodGroupsEntity * @param bloodGroupsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO bloodGroupsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IBloodGroupsDTO bloodGroupsDTO = (IBloodGroupsDTO)bloodGroupsDTO1;
            BloodGroupsEntity bloodGroupsEntity =
                EM().find(BloodGroupsEntity.class, (IBloodGroupsEntityKey)bloodGroupsDTO.getCode());
            if (bloodGroupsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(bloodGroupsEntity);
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
     * Get BloodGroupsEntity By Primary Key * @param id
     * @return IBloodGroupsDTO
     */
    @Override
    public IBloodGroupsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IBloodGroupsEntityKey id = (IBloodGroupsEntityKey)id1;
            BloodGroupsEntity bloodGroupsEntity = EM().find(BloodGroupsEntity.class, id);
            if (bloodGroupsEntity == null) {
                throw new ItemNotFoundException();
            }
            IBloodGroupsDTO bloodGroupsDTO = InfDTOFactory.createBloodGroupsDTO(bloodGroupsEntity);
            return bloodGroupsDTO;
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
     * Search for BloodGroupsEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("BloodGroupsEntity.getCodeName").getResultList();
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
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BloodGroupsEntity> list =
                EM().createNamedQuery("BloodGroupsEntity.searchByCode").setParameter("bldgroupCode",
                                                                                     (Long)code).getResultList();
            for (BloodGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createBloodGroupsDTO(entity));
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
     * Get all by name like string * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BloodGroupsEntity> list =
                EM().createNamedQuery("BloodGroupsEntity.searchByName").setParameter("bldgroupName",
                                                                                     "%" + name + "%").getResultList();
            for (BloodGroupsEntity entity : list) {
                arrayList.add(InfDTOFactory.createBloodGroupsDTO(entity));
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
    
    @Override
       public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
           
           try {
               List list =
                   EM().createNamedQuery("BloodGroupsEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                            "true").getResultList();
               if (list.size() > 0) {
                   return (InfDTOFactory.createBloodGroupsDTO((BloodGroupsEntity)list.get(0)));
               }
               return null;
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
