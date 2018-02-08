package com.beshara.csc.gn.map.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.MapDataExceptionsDTO;
import com.beshara.csc.gn.map.business.entity.MapDataExceptionsEntity;
import com.beshara.csc.gn.map.business.entity.MapDataExceptionsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class MapDataExceptionsDAO extends MapBaseDAO {
    public MapDataExceptionsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new MapDataExceptionsDAO();
    }

    /**<code>select o from MapDataExceptionsEntity o</code>.
     * @return List
     */
    @Override
    public List<MapDataExceptionsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<MapDataExceptionsEntity> list =
                EM().createNamedQuery("MapDataExceptionsEntity.findAll").getResultList();
            for (MapDataExceptionsEntity mapDataExceptions : list) {
                arrayList.add(new MapDataExceptionsDTO(mapDataExceptions));
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
            Long id = (Long)EM().createNamedQuery("MapDataExceptionsEntity.findNewId").getSingleResult();
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
     * Create a New MapDataExceptionsEntity
     * @param mapDataExceptionsDTO
     * @return MapDataExceptionsDTO
     */
    @Override
    public MapDataExceptionsDTO add(IBasicDTO mapDataExceptionsDTO1) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            MapDataExceptionsEntity mapDataExceptionsEntity = new MapDataExceptionsEntity();
            Long code = findNewId();
            MapDataExceptionsDTO mapDataExceptionsDTO = (MapDataExceptionsDTO)mapDataExceptionsDTO1;

            mapDataExceptionsEntity.setObjtypeCode(code);
            mapDataExceptionsEntity.setSoc1Code(((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc1Code());
            mapDataExceptionsEntity.setSoc2Code(((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc2Code());
            mapDataExceptionsEntity.setSqlStatement(mapDataExceptionsDTO.getSqlStatement());

            doAdd(mapDataExceptionsEntity);
            MapDataExceptionsEntityKey codeKey =
                new MapDataExceptionsEntityKey(code, ((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc1Code(),
                                               ((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc2Code());
            mapDataExceptionsDTO.setCode(codeKey);
            mapDataExceptionsDTO.setObjtypeCode(code);
            return mapDataExceptionsDTO;
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
     * Update an Existing MapDataExceptionsEntity
     * @param mapDataExceptionsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO mapDataExceptionsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            MapDataExceptionsDTO mapDataExceptionsDTO = (MapDataExceptionsDTO)mapDataExceptionsDTO1;
            MapDataExceptionsEntity mapDataExceptionsEntity =
                EM().find(MapDataExceptionsEntity.class, (MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode());

            mapDataExceptionsEntity.setObjtypeCode(((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getObjtypeCode());
            mapDataExceptionsEntity.setSoc1Code(((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc1Code());
            mapDataExceptionsEntity.setSoc2Code(((MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode()).getSoc2Code());
            mapDataExceptionsEntity.setSqlStatement(mapDataExceptionsDTO.getSqlStatement());
            doUpdate(mapDataExceptionsEntity);
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
     * Remove an Existing MapDataExceptionsEntity
     * @param mapDataExceptionsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO mapDataExceptionsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            MapDataExceptionsDTO mapDataExceptionsDTO = (MapDataExceptionsDTO)mapDataExceptionsDTO1;
            MapDataExceptionsEntity mapDataExceptionsEntity =
                EM().find(MapDataExceptionsEntity.class, (MapDataExceptionsEntityKey)mapDataExceptionsDTO.getCode());
            if (mapDataExceptionsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(mapDataExceptionsEntity);
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
     * Get MapDataExceptionsEntity By Primary Key
     * @param id
     * @return MapDataExceptionsDTO
     */
    @Override
    public MapDataExceptionsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        MapDataExceptionsEntityKey id = (MapDataExceptionsEntityKey)id1;
        MapDataExceptionsEntity mapDataExceptionsEntity = EM().find(MapDataExceptionsEntity.class, id);
        if (mapDataExceptionsEntity == null) {
            throw new ItemNotFoundException();
        }
        MapDataExceptionsDTO mapDataExceptionsDTO = new MapDataExceptionsDTO(mapDataExceptionsEntity);
        return mapDataExceptionsDTO;
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
     * Search for MapDataExceptionsEntity
     * <br>
     * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

}
