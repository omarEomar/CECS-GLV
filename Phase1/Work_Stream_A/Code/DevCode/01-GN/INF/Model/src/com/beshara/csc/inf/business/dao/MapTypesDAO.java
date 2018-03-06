package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IMapTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IMapTypesEntityKey;
import com.beshara.csc.inf.business.entity.MapTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class MapTypesDAO extends InfBaseDAO {
    public MapTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new MapTypesDAO();
    }

    public List<IMapTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<MapTypesEntity> list =
                EM().createNamedQuery("MapTypesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (MapTypesEntity mapTypes : list) {
                arrayList.add(InfDTOFactory.createMapTypesDTO(mapTypes));
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
     * Create a New MapTypesEntity * @param mapTypesDTO
     * @return IMapTypesDTO
     */
    public IMapTypesDTO add(IBasicDTO mapTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            MapTypesEntity mapTypesEntity = new MapTypesEntity();
            IMapTypesDTO mapTypesDTO = (IMapTypesDTO)mapTypesDTO1;
            mapTypesEntity.setTypeCode(((IMapTypesEntityKey)mapTypesDTO.getCode()).getTypeCode());
            mapTypesEntity.setTypeName(mapTypesDTO.getTypeName());
            mapTypesEntity.setCreatedBy(mapTypesDTO.getCreatedBy());
            mapTypesEntity.setCreatedDate(mapTypesDTO.getCreatedDate());
            mapTypesEntity.setAuditStatus(mapTypesDTO.getAuditStatus());
            mapTypesEntity.setTabrecSerial(mapTypesDTO.getTabrecSerial());
            doAdd(mapTypesEntity);
            // Set PK after creation
            return mapTypesDTO;
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
     * Update an Existing MapTypesEntity * @param mapTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO mapTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMapTypesDTO mapTypesDTO = (IMapTypesDTO)mapTypesDTO1;
            MapTypesEntity mapTypesEntity = EM().find(MapTypesEntity.class, (IMapTypesEntityKey)mapTypesDTO.getCode());
            mapTypesEntity.setTypeCode(((IMapTypesEntityKey)mapTypesDTO.getCode()).getTypeCode());
            mapTypesEntity.setTypeName(mapTypesDTO.getTypeName());
            mapTypesEntity.setCreatedBy(mapTypesDTO.getCreatedBy());
            mapTypesEntity.setCreatedDate(mapTypesDTO.getCreatedDate());
            mapTypesEntity.setLastUpdatedBy(mapTypesDTO.getLastUpdatedBy());
            mapTypesEntity.setLastUpdatedDate(mapTypesDTO.getLastUpdatedDate());
            mapTypesEntity.setAuditStatus(mapTypesDTO.getAuditStatus());
            mapTypesEntity.setTabrecSerial(mapTypesDTO.getTabrecSerial());
            doUpdate(mapTypesEntity);
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
     * Remove an Existing MapTypesEntity * @param mapTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO mapTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IMapTypesDTO mapTypesDTO = (IMapTypesDTO)mapTypesDTO1;
            MapTypesEntity mapTypesEntity = EM().find(MapTypesEntity.class, (IMapTypesEntityKey)mapTypesDTO.getCode());
            if (mapTypesEntity == null) {
                throw new FinderException();
            }
            doRemove(mapTypesEntity);
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
     * Get MapTypesEntity By Primary Key * @param id
     * @return IMapTypesDTO
     */
    public IMapTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IMapTypesEntityKey id = (IMapTypesEntityKey)id1;
            MapTypesEntity mapTypesEntity = EM().find(MapTypesEntity.class, id);
            if (mapTypesEntity == null) {
                throw new FinderException();
            }
            IMapTypesDTO mapTypesDTO = InfDTOFactory.createMapTypesDTO(mapTypesEntity);
            return mapTypesDTO;
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
            Long id = (Long)EM().createNamedQuery("MapTypesEntity.findNewId").getSingleResult();
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

}
