package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IRelatedFieldsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IRelatedFieldsEntityKey;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class RelatedFieldsDAO extends InfBaseDAO {
    public RelatedFieldsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new RelatedFieldsDAO();
    }

    /**<code>select o from RelatedFieldsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IRelatedFieldsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<RelatedFieldsEntity> list =
                EM().createNamedQuery("RelatedFieldsEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (RelatedFieldsEntity relatedFields : list) {
                arrayList.add(InfDTOFactory.createRelatedFieldsDTO(relatedFields));
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
     * Create a New RelatedFieldsEntity * @param relatedFieldsDTO
     * @return IRelatedFieldsDTO
     */
    public IRelatedFieldsDTO add(IBasicDTO relatedFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            RelatedFieldsEntity relatedFieldsEntity = new RelatedFieldsEntity();
            IRelatedFieldsDTO relatedFieldsDTO = (IRelatedFieldsDTO)relatedFieldsDTO1;
            relatedFieldsEntity.setFldCode(((IRelatedFieldsEntityKey)relatedFieldsDTO.getCode()).getFldCode());
            relatedFieldsEntity.setFldCodeReferenced(((IRelatedFieldsEntityKey)relatedFieldsDTO.getCode()).getFldCodeReferenced());
            relatedFieldsEntity.setReferOrder(relatedFieldsDTO.getReferOrder());
            relatedFieldsEntity.setCreatedBy(relatedFieldsDTO.getCreatedBy());
            relatedFieldsEntity.setCreatedDate(relatedFieldsDTO.getCreatedDate());
            relatedFieldsEntity.setAuditStatus(relatedFieldsDTO.getAuditStatus());
            relatedFieldsEntity.setTabrecSerial(relatedFieldsDTO.getTabrecSerial());
            doAdd(relatedFieldsEntity);
            // Set PK after creation
            return relatedFieldsDTO;
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
     * Update an Existing RelatedFieldsEntity * @param relatedFieldsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO relatedFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRelatedFieldsDTO relatedFieldsDTO = (IRelatedFieldsDTO)relatedFieldsDTO1;
            RelatedFieldsEntity relatedFieldsEntity =
                EM().find(RelatedFieldsEntity.class, (IRelatedFieldsEntityKey)relatedFieldsDTO.getCode());
            relatedFieldsEntity.setFldCode(((IRelatedFieldsEntityKey)relatedFieldsDTO.getCode()).getFldCode());
            relatedFieldsEntity.setFldCodeReferenced(((IRelatedFieldsEntityKey)relatedFieldsDTO.getCode()).getFldCodeReferenced());
            relatedFieldsEntity.setReferOrder(relatedFieldsDTO.getReferOrder());
            relatedFieldsEntity.setCreatedBy(relatedFieldsDTO.getCreatedBy());
            relatedFieldsEntity.setCreatedDate(relatedFieldsDTO.getCreatedDate());
            relatedFieldsEntity.setLastUpdatedBy(relatedFieldsDTO.getLastUpdatedBy());
            relatedFieldsEntity.setLastUpdatedDate(relatedFieldsDTO.getLastUpdatedDate());
            relatedFieldsEntity.setAuditStatus(relatedFieldsDTO.getAuditStatus());
            relatedFieldsEntity.setTabrecSerial(relatedFieldsDTO.getTabrecSerial());
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
     * Remove an Existing RelatedFieldsEntity * @param relatedFieldsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO relatedFieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRelatedFieldsDTO relatedFieldsDTO = (IRelatedFieldsDTO)relatedFieldsDTO1;
            RelatedFieldsEntity relatedFieldsEntity =
                EM().find(RelatedFieldsEntity.class, (IRelatedFieldsEntityKey)relatedFieldsDTO.getCode());
            if (relatedFieldsEntity == null) {
                throw new NoResultException();
            }
            doRemove(relatedFieldsEntity);
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
     * Get RelatedFieldsEntity By Primary Key * @param id
     * @return IRelatedFieldsDTO
     */
    public IRelatedFieldsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IRelatedFieldsEntityKey id = (IRelatedFieldsEntityKey)id1;
            RelatedFieldsEntity relatedFieldsEntity = EM().find(RelatedFieldsEntity.class, id);
            if (relatedFieldsEntity == null) {
                throw new NoResultException();
            }
            IRelatedFieldsDTO relatedFieldsDTO = InfDTOFactory.createRelatedFieldsDTO(relatedFieldsEntity);
            return relatedFieldsDTO;
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
     * Search for RelatedFieldsEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws  DataBaseException, SharedApplicationException {
        try{
        return super.search(basicDTO);
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
            Query query = EM().createNamedQuery("RelatedFieldsEntity.findNewId");
            Long id = (Long)query.getSingleResult();
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
    

    public List<IBasicDTO> getReferncesByFLdCode(Long fldCode) throws  DataBaseException, SharedApplicationException {
        try{
        List<IRelatedFieldsDTO> referencesList = new ArrayList<IRelatedFieldsDTO>();
        List<RelatedFieldsEntity> list =
            EM().createNamedQuery("RelatedFieldsEntity.getReferenceFieldsOrdered").setParameter("fieldCode",
                                                                                              fldCode).getResultList();
        if (list != null) {
            for (RelatedFieldsEntity entity : list) {
                referencesList.add(InfDTOFactory.createRelatedFieldsDTO(entity));
            }
        }
        return (List)referencesList;
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
