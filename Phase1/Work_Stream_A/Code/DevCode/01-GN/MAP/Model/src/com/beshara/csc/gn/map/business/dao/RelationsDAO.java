package com.beshara.csc.gn.map.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.IRelationsDTO;
import com.beshara.csc.gn.map.business.entity.IRelationsEntityKey;
import com.beshara.csc.gn.map.business.entity.RelationsEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

public class RelationsDAO extends MapBaseDAO{
    public RelationsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new RelationsDAO();
    }
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try{
        ArrayList arrayList = new ArrayList();
        List<RelationsEntity> list =
            EM().createNamedQuery("RelationsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        for (RelationsEntity relation : list) {
            arrayList.add(MapEntityConverter.getRelationsDTO(relation
                                                             ));
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

    public IBasicDTO add(IBasicDTO _relationsDTO) throws DataBaseException, SharedApplicationException {
       
        try{
        IRelationsDTO relationsDTO = (IRelationsDTO)_relationsDTO;
        RelationsEntity relationsEntity = new RelationsEntity();
        relationsEntity.setObjtypeCode(relationsDTO.getObjtypeCode());
        relationsEntity.setSoc1Code(relationsDTO.getSoc1Code());
        relationsEntity.setSoc2Code(relationsDTO.getSoc2Code());
        relationsEntity.setReltypeCode(relationsDTO.getReltypeCode());
        relationsEntity.setCreatedBy(relationsDTO.getCreatedBy());
        relationsEntity.setCreatedDate(relationsDTO.getCreatedDate());
        doAdd(relationsEntity);
        return relationsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    //    /**
    //     * Update an Existing SocietiesEntity * @param societiesDTO
    //     * @return Boolean
    //     * @throws RemoteException
    //     */
    public Boolean update(IBasicDTO societiesDTOParam) throws DataBaseException, SharedApplicationException {
        try{
        IRelationsDTO relationsDTO = (IRelationsDTO)societiesDTOParam;
        RelationsEntity relationsEntity = EM().find(RelationsEntity.class, relationsDTO.getCode());
        if (relationsEntity == null) {
            throw new NoResultException();
        }
    //        relationsEntity.setObjtypeCode(relationsDTO.getObjtypeCode());
    //        relationsEntity.setSoc1Code(relationsDTO.getSoc1Code());
    //        relationsEntity.setSoc2Code(relationsDTO.getSoc2Code());
        relationsEntity.setReltypeCode(relationsDTO.getReltypeCode());
        relationsEntity.setLastUpdatedBy(relationsDTO.getLastUpdatedBy());
        relationsEntity.setLastUpdatedDate(relationsDTO.getLastUpdatedDate());
        relationsEntity.setCreatedBy(relationsDTO.getCreatedBy());
        relationsEntity.setCreatedDate(relationsDTO.getCreatedDate());
        doUpdate(relationsEntity);
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
     * Remove an existing dto * @param RelationsDTO the dto to be removed
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    public Boolean remove(IBasicDTO relationsDTO) throws DataBaseException, SharedApplicationException {
        try{
        RelationsEntity relationsEntity =
            EM().find(RelationsEntity.class, (IRelationsEntityKey)relationsDTO.getCode());
        if (relationsEntity == null) {
            throw new NoResultException();
        }
        doRemove(relationsEntity);
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
    
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try{
        RelationsEntity entity = EM().find(RelationsEntity.class, (IRelationsEntityKey)id);
        if (entity == null) {
            throw new NoResultException();
        }
        IRelationsDTO dto = MapEntityConverter.getRelationsDTO(entity);
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
