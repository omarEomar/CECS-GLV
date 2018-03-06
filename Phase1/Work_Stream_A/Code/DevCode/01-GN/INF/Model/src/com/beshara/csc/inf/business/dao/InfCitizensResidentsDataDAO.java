package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfCitizensResidentsDataDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfCitizensResidentsDataEntityKey;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntity;
import com.beshara.csc.inf.business.entity.InfCitizensResidentsDataEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class InfCitizensResidentsDataDAO extends InfBaseDAO {
    public InfCitizensResidentsDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfCitizensResidentsDataDAO();
    }


    public IInfCitizensResidentsDataDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfCitizensResidentsDataEntityKey id = (IInfCitizensResidentsDataEntityKey)id1;
            InfCitizensResidentsDataEntity infCitizensResidentsDataEntity =
                EM().find(InfCitizensResidentsDataEntity.class, id);
            if (infCitizensResidentsDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfCitizensResidentsDataDTO infCitizensResidentsDataDTO =
                InfDTOFactory.createInfCitizensResidentsDataDTO(infCitizensResidentsDataEntity);
            return infCitizensResidentsDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IInfCitizensResidentsDataDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfCitizensResidentsDataEntity> list =
                EM().createNamedQuery("InfCitizensResidentsDataEntity.findAll").setHint("toplink.refresh",
                                                                                        "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfCitizensResidentsDataEntity infCitizensResidentsData : list) {
                arrayList.add(InfDTOFactory.createInfCitizensResidentsDataDTO(infCitizensResidentsData));
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
            Query query = EM().createNamedQuery("InfCitizensResidentsDataEntity.findNewId");
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

    public IInfCitizensResidentsDataDTO add(IBasicDTO infCitizensResidentsDataDTO1) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            InfCitizensResidentsDataEntity infCitizensResidentsDataEntity = new InfCitizensResidentsDataEntity();
            IInfCitizensResidentsDataDTO infCitizensResidentsDataDTO =
                (IInfCitizensResidentsDataDTO)infCitizensResidentsDataDTO1;
            Long newID = findNewId();
            IInfCitizensResidentsDataEntityKey ent = new InfCitizensResidentsDataEntityKey(newID);
            infCitizensResidentsDataEntity.setSerial(ent.getSerial());
            infCitizensResidentsDataEntity.setCivilId(infCitizensResidentsDataDTO.getCivilId());
            infCitizensResidentsDataEntity.setResidentNo(infCitizensResidentsDataDTO.getResidentNo());
            infCitizensResidentsDataEntity.setIssueDate(infCitizensResidentsDataDTO.getIssueDate());
            infCitizensResidentsDataEntity.setExpDate(infCitizensResidentsDataDTO.getExpDate());
            infCitizensResidentsDataEntity.setRestypeCode(infCitizensResidentsDataDTO.getRestypeCode());
            infCitizensResidentsDataEntity.setCreatedBy(infCitizensResidentsDataDTO.getCreatedBy());
            infCitizensResidentsDataEntity.setCreatedDate(infCitizensResidentsDataDTO.getCreatedDate());
            infCitizensResidentsDataEntity.setAuditStatus(infCitizensResidentsDataDTO.getAuditStatus());
            infCitizensResidentsDataEntity.setTabrecSerial(infCitizensResidentsDataDTO.getTabrecSerial());
            doAdd(infCitizensResidentsDataEntity);
            infCitizensResidentsDataDTO.setCode(ent);
            // Set PK after creation
            return infCitizensResidentsDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO infCitizensResidentsDataDTO1) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            IInfCitizensResidentsDataDTO infCitizensResidentsDataDTO =
                (IInfCitizensResidentsDataDTO)infCitizensResidentsDataDTO1;
            InfCitizensResidentsDataEntity infCitizensResidentsDataEntity =
                EM().find(InfCitizensResidentsDataEntity.class,
                          (IInfCitizensResidentsDataEntityKey)infCitizensResidentsDataDTO.getCode());
            if (infCitizensResidentsDataEntity == null) {
                throw new ItemNotFoundException();
            }
            infCitizensResidentsDataEntity.setSerial(((IInfCitizensResidentsDataEntityKey)infCitizensResidentsDataDTO.getCode()).getSerial());
            infCitizensResidentsDataEntity.setCivilId(infCitizensResidentsDataDTO.getCivilId());
            infCitizensResidentsDataEntity.setResidentNo(infCitizensResidentsDataDTO.getResidentNo());
            infCitizensResidentsDataEntity.setIssueDate(infCitizensResidentsDataDTO.getIssueDate());
            infCitizensResidentsDataEntity.setExpDate(infCitizensResidentsDataDTO.getExpDate());
            infCitizensResidentsDataEntity.setRestypeCode(infCitizensResidentsDataDTO.getRestypeCode());
            infCitizensResidentsDataEntity.setCreatedBy(infCitizensResidentsDataDTO.getCreatedBy());
            infCitizensResidentsDataEntity.setCreatedDate(infCitizensResidentsDataDTO.getCreatedDate());
            infCitizensResidentsDataEntity.setLastUpdatedBy(infCitizensResidentsDataDTO.getLastUpdatedBy());
            infCitizensResidentsDataEntity.setLastUpdatedDate(infCitizensResidentsDataDTO.getLastUpdatedDate());
            infCitizensResidentsDataEntity.setAuditStatus(infCitizensResidentsDataDTO.getAuditStatus());
            infCitizensResidentsDataEntity.setTabrecSerial(infCitizensResidentsDataDTO.getTabrecSerial());
            doUpdate(infCitizensResidentsDataEntity);
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

    public Boolean remove(IBasicDTO infCitizensResidentsDataDTO1) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            IInfCitizensResidentsDataDTO infCitizensResidentsDataDTO =
                (IInfCitizensResidentsDataDTO)infCitizensResidentsDataDTO1;
            InfCitizensResidentsDataEntity infCitizensResidentsDataEntity =
                EM().find(InfCitizensResidentsDataEntity.class,
                          (IInfCitizensResidentsDataEntityKey)infCitizensResidentsDataDTO.getCode());
            if (infCitizensResidentsDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infCitizensResidentsDataEntity);
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

    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
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

    public IBasicDTO getByCivilID(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("InfCitizensResidentsDataEntity.getByCivilID");
            query.setParameter("civilId", civilID);
            List<InfCitizensResidentsDataEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            IInfCitizensResidentsDataDTO dto = InfDTOFactory.createInfCitizensResidentsDataDTO(list.get(0));
            return dto;
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
