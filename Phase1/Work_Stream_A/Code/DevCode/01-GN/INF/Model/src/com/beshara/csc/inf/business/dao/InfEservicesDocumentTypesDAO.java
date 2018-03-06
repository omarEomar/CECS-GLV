package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfEservicesDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfEservicesDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEservicesDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfEservicesDocumentTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class InfEservicesDocumentTypesDAO extends InfBaseDAO {
    public InfEservicesDocumentTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfEservicesDocumentTypesDAO();
    }

    public IInfEservicesDocumentTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfEservicesDocumentTypesEntityKey id = (IInfEservicesDocumentTypesEntityKey)id1;
            InfEservicesDocumentTypesEntity infEservicesDocumentTypesEntity =
                EM().find(InfEservicesDocumentTypesEntity.class, id);
            if (infEservicesDocumentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO =
                InfDTOFactory.createInfEservicesDocumentTypesDTO(infEservicesDocumentTypesEntity);
            return infEservicesDocumentTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<InfEservicesDocumentTypesEntity> list =
                EM().createNamedQuery("InfEservicesDocumentTypesEntity.findAll").setHint("toplink.refresh",
                                                                                         "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfEservicesDocumentTypesEntity infEservicesDocumentTypes : list) {
                arrayList.add(InfDTOFactory.createInfEservicesDocumentTypesDTO(infEservicesDocumentTypes));
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
            Query query = EM().createNamedQuery("InfEservicesDocumentTypesEntity.findNewId");
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

    public IInfEservicesDocumentTypesDTO add(IBasicDTO infEservicesDocumentTypesDTO1) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            InfEservicesDocumentTypesEntity infEservicesDocumentTypesEntity = new InfEservicesDocumentTypesEntity();
            IInfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO =
                (IInfEservicesDocumentTypesDTO)infEservicesDocumentTypesDTO1;
            //            Long newID = findNewId();
            IInfEservicesDocumentTypesEntityKey ent =
                new InfEservicesDocumentTypesEntityKey(infEservicesDocumentTypesDTO.getDocTypeCode(),
                                                       infEservicesDocumentTypesDTO.getServicesId());
            infEservicesDocumentTypesEntity.setDocTypeCode(ent.getDoctypeCode());
            infEservicesDocumentTypesEntity.setServicesId(infEservicesDocumentTypesDTO.getServicesId());
            infEservicesDocumentTypesEntity.setAttachmentRequiredFlag(infEservicesDocumentTypesDTO.getAttachmentRequiredFlag());
            infEservicesDocumentTypesEntity.setCreatedBy(infEservicesDocumentTypesDTO.getCreatedBy());
            infEservicesDocumentTypesEntity.setCreatedDate(infEservicesDocumentTypesDTO.getCreatedDate());
            infEservicesDocumentTypesEntity.setAuditStatus(infEservicesDocumentTypesDTO.getAuditStatus());
            infEservicesDocumentTypesEntity.setTabrecSerial(infEservicesDocumentTypesDTO.getTabrecSerial());
            doAdd(infEservicesDocumentTypesEntity);
            // Set PK after creation
            infEservicesDocumentTypesDTO.setCode(ent);
            return infEservicesDocumentTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO infEservicesDocumentTypesDTO1) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            IInfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO =
                (IInfEservicesDocumentTypesDTO)infEservicesDocumentTypesDTO1;
            InfEservicesDocumentTypesEntity infEservicesDocumentTypesEntity =
                EM().find(InfEservicesDocumentTypesEntity.class, infEservicesDocumentTypesDTO.getCode());
            if (infEservicesDocumentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            infEservicesDocumentTypesEntity.setDocTypeCode(infEservicesDocumentTypesDTO.getDocTypeCode());
            infEservicesDocumentTypesEntity.setServicesId(infEservicesDocumentTypesDTO.getServicesId());
            infEservicesDocumentTypesEntity.setAttachmentRequiredFlag(infEservicesDocumentTypesDTO.getAttachmentRequiredFlag());
            infEservicesDocumentTypesEntity.setCreatedBy(infEservicesDocumentTypesDTO.getCreatedBy());
            infEservicesDocumentTypesEntity.setCreatedDate(infEservicesDocumentTypesDTO.getCreatedDate());
            infEservicesDocumentTypesEntity.setLastUpdatedBy(infEservicesDocumentTypesDTO.getLastUpdatedBy());
            infEservicesDocumentTypesEntity.setLastUpdatedDate(infEservicesDocumentTypesDTO.getLastUpdatedDate());
            infEservicesDocumentTypesEntity.setAuditStatus(infEservicesDocumentTypesDTO.getAuditStatus());
            infEservicesDocumentTypesEntity.setTabrecSerial(infEservicesDocumentTypesDTO.getTabrecSerial());
            doUpdate(infEservicesDocumentTypesEntity);
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

    public Boolean remove(IBasicDTO infEservicesDocumentTypesDTO1) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            IInfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO =
                (IInfEservicesDocumentTypesDTO)infEservicesDocumentTypesDTO1;
            InfEservicesDocumentTypesEntity infEservicesDocumentTypesEntity =
                EM().find(InfEservicesDocumentTypesEntity.class, infEservicesDocumentTypesDTO.getCode());
            if (infEservicesDocumentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infEservicesDocumentTypesEntity);
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


    public List<IBasicDTO> getByServicesId(Long servicesId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> infEservicesDocumentTypesList =
                EM().createNamedQuery("InfEservicesDocumentTypesEntity.getByServicesId").setParameter("servicesId",
                                                                                                      servicesId).setHint("toplink.refresh",
                                                                                                                          "true").getResultList();
            return infEservicesDocumentTypesList;
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
