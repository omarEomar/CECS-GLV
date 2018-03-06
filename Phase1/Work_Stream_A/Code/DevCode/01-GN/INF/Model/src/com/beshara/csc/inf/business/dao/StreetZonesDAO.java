package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IStreetZonesDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IStreetZonesEntityKey;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.inf.business.entity.KwStreetsEntity;
import com.beshara.csc.inf.business.entity.StreetZonesEntity;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class StreetZonesDAO extends InfBaseDAO {
    public StreetZonesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new StreetZonesDAO();
    }

    public List<IYearsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<StreetZonesEntity> list =
                EM().createNamedQuery("StreetZonesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (StreetZonesEntity streetZones : list) {
                arrayList.add(InfDTOFactory.createStreetZonesDTO(streetZones));
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
            Query query = EM().createNamedQuery("StreetZonesEntity.findNewId");
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
    /**
     * Create a New StreetZonesEntity * @param streetZonesDTO
     * @return IStreetZonesDTO
     */
    public IStreetZonesDTO add(IBasicDTO streetZonesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            StreetZonesEntity streetZonesEntity = new StreetZonesEntity();
            IStreetZonesDTO streetZonesDTO = (IStreetZonesDTO)streetZonesDTO1;
            streetZonesEntity.setMapCode(((IStreetZonesEntityKey)streetZonesDTO.getCode()).getMapCode());
            streetZonesEntity.setStreetCode(((IStreetZonesEntityKey)streetZonesDTO.getCode()).getStreetCode());
            KwStreetsEntity kwStreetsEntity = new KwStreetsEntity();
            kwStreetsEntity.setStreetCode(streetZonesEntity.getStreetCode());
            KwMapEntity kwMapEntity = new KwMapEntity();
            kwMapEntity.setMapCode(streetZonesEntity.getMapCode());
            streetZonesEntity.setKwMapEntity(kwMapEntity);
            streetZonesEntity.setKwStreetsEntity(kwStreetsEntity);
            streetZonesEntity.setCreatedBy(streetZonesDTO.getCreatedBy());
            streetZonesEntity.setCreatedDate(streetZonesDTO.getCreatedDate());
            streetZonesEntity.setAuditStatus(streetZonesDTO.getAuditStatus());
            streetZonesEntity.setTabrecSerial(streetZonesDTO.getTabrecSerial());
            doAdd(streetZonesEntity);
            // Set PK after creation
            return streetZonesDTO;
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
     * Update an Existing StreetZonesEntity * @param streetZonesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO streetZonesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IStreetZonesDTO streetZonesDTO = (IStreetZonesDTO)streetZonesDTO1;
            StreetZonesEntity streetZonesEntity =
                EM().find(StreetZonesEntity.class, (IStreetZonesEntityKey)streetZonesDTO.getCode());
            streetZonesEntity.setMapCode(((IStreetZonesEntityKey)streetZonesDTO.getCode()).getMapCode());
            streetZonesEntity.setStreetCode(((IStreetZonesEntityKey)streetZonesDTO.getCode()).getStreetCode());
            streetZonesEntity.setCreatedBy(streetZonesDTO.getCreatedBy());
            streetZonesEntity.setCreatedDate(streetZonesDTO.getCreatedDate());
            streetZonesEntity.setLastUpdatedBy(streetZonesDTO.getLastUpdatedBy());
            streetZonesEntity.setLastUpdatedDate(streetZonesDTO.getLastUpdatedDate());
            streetZonesEntity.setAuditStatus(streetZonesDTO.getAuditStatus());
            streetZonesEntity.setTabrecSerial(streetZonesDTO.getTabrecSerial());
            doUpdate(streetZonesEntity);
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
     * Remove an Existing StreetZonesEntity * @param streetZonesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO streetZonesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IStreetZonesDTO streetZonesDTO = (IStreetZonesDTO)streetZonesDTO1;
            StreetZonesEntity streetZonesEntity =
                EM().find(StreetZonesEntity.class, (IStreetZonesEntityKey)streetZonesDTO.getCode());
            if (streetZonesEntity == null) {
                throw new NoResultException();
            }
            doRemove(streetZonesEntity);
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
     * Get StreetZonesEntity By Primary Key * @param id
     * @return IStreetZonesDTO
     */
    public IStreetZonesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IStreetZonesEntityKey id = (IStreetZonesEntityKey)id1;
            StreetZonesEntity streetZonesEntity = EM().find(StreetZonesEntity.class, id);
            if (streetZonesEntity == null) {
                throw new NoResultException();
            }
            IStreetZonesDTO streetZonesDTO = InfDTOFactory.createStreetZonesDTO(streetZonesEntity);
            return streetZonesDTO;
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
     * Search for StreetZonesEntity * <br> * @return List
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

   

    public List<IBasicDTO> getByMapCode(String mapCode) throws  DataBaseException, SharedApplicationException {
        try{
        ArrayList arrayList = new ArrayList();
        List<KwStreetsEntity> list =
            EM().createNamedQuery("StreetZonesEntity.getByMapCode").setParameter("mapCode", mapCode).getResultList();
        for (KwStreetsEntity kwStreetsEntity : list) {
            arrayList.add(InfDTOFactory.createKwStreetsDTO(kwStreetsEntity));
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

    public List<IBasicDTO> getNotSelectedByMapCode(String mapCode) throws  DataBaseException, SharedApplicationException {
        try{
        ArrayList arrayList = new ArrayList();
        List<KwStreetsEntity> list =
            EM().createNamedQuery("StreetZonesEntity.getNotSelectedByMapCode").setParameter("mapCode",
                                                                                          mapCode).getResultList();
        for (KwStreetsEntity kwStreetsEntity : list) {
            arrayList.add(InfDTOFactory.createKwStreetsDTO(kwStreetsEntity));
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
