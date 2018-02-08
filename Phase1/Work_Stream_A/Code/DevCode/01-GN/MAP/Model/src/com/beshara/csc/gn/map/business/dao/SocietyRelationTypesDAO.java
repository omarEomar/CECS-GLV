package com.beshara.csc.gn.map.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.ISocietyRelationTypesDTO;
import com.beshara.csc.gn.map.business.entity.ISocietyRelationTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.SocietyRelationTypesEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

public class SocietyRelationTypesDAO extends MapBaseDAO {
    public SocietyRelationTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SocietyRelationTypesDAO();
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SocietyRelationTypesEntity> list =
                EM().createNamedQuery("SocietyRelationTypesEntity.findAll").setHint("toplink.refresh",
                                                                                    "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietyRelationTypesEntity relation : list) {
                arrayList.add(MapEntityConverter.getSocietyRelationTypesDTO(relation));
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

    public IBasicDTO add(IBasicDTO _socrelationsDTO) throws DataBaseException, SharedApplicationException {
        try {
            ISocietyRelationTypesDTO socrelationsDTO = (ISocietyRelationTypesDTO)_socrelationsDTO;
            SocietyRelationTypesEntity socrelationsEntity = new SocietyRelationTypesEntity();
            socrelationsEntity.setReltypeCode(socrelationsDTO.getReltypeCode());
            socrelationsEntity.setReltypeName(socrelationsDTO.getReltypeName());
            socrelationsEntity.setReltypeSymbole(socrelationsDTO.getReltypeSymbole());
            socrelationsEntity.setReltypeCode(socrelationsDTO.getReltypeCode());
            socrelationsEntity.setCreatedBy(socrelationsDTO.getCreatedBy());
            socrelationsEntity.setCreatedDate(socrelationsDTO.getCreatedDate());
            doAdd(socrelationsEntity);
            return socrelationsDTO;
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
        try {
            SocietyRelationTypesEntity societyRelationTypesEntity =
                EM().find(SocietyRelationTypesEntity.class, (ISocietyRelationTypesEntityKey)id);
            if (societyRelationTypesEntity == null) {
                throw new NoResultException();
            }
            ISocietyRelationTypesDTO societyRelationTypesDTO =
                MapEntityConverter.getSocietyRelationTypesDTO(societyRelationTypesEntity);
            return societyRelationTypesDTO;
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


