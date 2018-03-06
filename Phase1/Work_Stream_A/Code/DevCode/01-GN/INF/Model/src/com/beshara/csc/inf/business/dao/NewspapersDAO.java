package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.INewspapersDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.INewspapersEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class NewspapersDAO extends InfBaseDAO {
    public NewspapersDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new NewspapersDAO();
    }

    public List<INewspapersDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<NewspapersEntity> list =
                EM().createNamedQuery("NewspapersEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (NewspapersEntity newspapers : list) {
                arrayList.add(InfDTOFactory.createNewspapersDTO(newspapers));
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
     * Create a New NewspapersEntity * @param newspapersDTO
     * @return INewspapersDTO
     */
    public INewspapersDTO add(IBasicDTO newspapersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            NewspapersEntity newspapersEntity = new NewspapersEntity();
            INewspapersDTO newspapersDTO = (INewspapersDTO)newspapersDTO1;
            newspapersDTO.setCode(InfEntityKeyFactory.createNewspapersEntityKey(findNewId()));
            newspapersEntity.setPaperId(((INewspapersEntityKey)newspapersDTO.getCode()).getPaperId());
            newspapersEntity.setPaperName(newspapersDTO.getPaperName());
            newspapersEntity.setPaperLocation(newspapersDTO.getPaperLocation());
            newspapersEntity.setCreatedBy(newspapersDTO.getCreatedBy());
            newspapersEntity.setCreatedDate(newspapersDTO.getCreatedDate());
            newspapersEntity.setAuditStatus(newspapersDTO.getAuditStatus());
            newspapersEntity.setTabrecSerial(newspapersDTO.getTabrecSerial());
            doAdd(newspapersEntity);
            return newspapersDTO;
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
     * Update an Existing NewspapersEntity * @param newspapersDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO newspapersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            INewspapersDTO newspapersDTO = (INewspapersDTO)newspapersDTO1;
            NewspapersEntity newspapersEntity = EM().find(NewspapersEntity.class, newspapersDTO.getCode());
            newspapersEntity.setPaperId(((INewspapersEntityKey)newspapersDTO.getCode()).getPaperId());
            newspapersEntity.setPaperName(newspapersDTO.getPaperName());
            newspapersEntity.setPaperLocation(newspapersDTO.getPaperLocation());
            newspapersEntity.setCreatedBy(newspapersDTO.getCreatedBy());
            newspapersEntity.setCreatedDate(newspapersDTO.getCreatedDate());
            newspapersEntity.setLastUpdatedBy(newspapersDTO.getLastUpdatedBy());
            newspapersEntity.setLastUpdatedDate(newspapersDTO.getLastUpdatedDate());
            newspapersEntity.setAuditStatus(newspapersDTO.getAuditStatus());
            newspapersEntity.setTabrecSerial(newspapersDTO.getTabrecSerial());
            doUpdate(newspapersEntity);
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
     * Remove an Existing NewspapersEntity * @param newspapersDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO newspapersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            INewspapersDTO newspapersDTO = (INewspapersDTO)newspapersDTO1;
            NewspapersEntity newspapersEntity = EM().find(NewspapersEntity.class, newspapersDTO.getCode());
            if (newspapersEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(newspapersEntity);
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
     * Get NewspapersEntity By Primary Key * @param id
     * @return INewspapersDTO
     */
    public INewspapersDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            INewspapersEntityKey id = (INewspapersEntityKey)id1;
            NewspapersEntity newspapersEntity = EM().find(NewspapersEntity.class, id);
            if (newspapersEntity == null) {
                throw new ItemNotFoundException();
            }
            INewspapersDTO newspapersDTO = InfDTOFactory.createNewspapersDTO(newspapersEntity);
            return newspapersDTO;
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
            Long id = (Long)EM().createNamedQuery("NewspapersEntity.findNewId").getSingleResult();
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

    /**<code>select o from NewspapersEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<INewspapersDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            List<INewspapersDTO> list = EM().createNamedQuery("NewspapersEntity.getCodeName").getResultList();
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

    /**
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<NewspapersEntity> list =
                EM().createNamedQuery("NewspapersEntity.searchByCode").setParameter("paperId", code).getResultList();
            for (NewspapersEntity entity : list) {
                arrayList.add(InfDTOFactory.createNewspapersDTO(entity));
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


    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder ejbQL = new StringBuilder("select o from NewspapersEntity   o where o.paperName like '%");
            ejbQL.append(searchName);
            ejbQL.append("%' order by o.paperName");
            List<NewspapersEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (NewspapersEntity entity : list) {
                arrayList.add(InfDTOFactory.createNewspapersDTO(entity));
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
   
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("NewspapersEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createNewspapersDTO((NewspapersEntity)list.get(0)));
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

