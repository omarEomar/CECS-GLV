package com.beshara.csc.gn.map.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;

public class SocietiesDAO extends MapBaseDAO {
    public SocietiesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SocietiesDAO();
    }

    /**<code>select o from SocietiesEntity IoI<I/IcIoIdIeI>I.I
     * Get all ISocietiesDTO * @return List ISocietiesDTO
     */
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SocietiesEntity> list =
                EM().createNamedQuery("SocietiesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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
     * Create a New SocietiesEntity * @param soc1DTO
     * @return ISocietiesDTO
     */
    public IBasicDTO add(IBasicDTO societiesDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            System.out.println("add------------------------------");
            Long maxId = findNewId();
            ISocietiesDTO societiesDTO = (ISocietiesDTO)societiesDTOParam;
            SocietiesEntity societiesEntity = new SocietiesEntity();
            societiesEntity.setSocName(societiesDTO.getName());
            societiesEntity.setCreatedBy(societiesDTO.getCreatedBy());
            societiesEntity.setCreatedDate(societiesDTO.getCreatedDate());
            // societiesEntity.setLastUpdatedBy ( societiesDTO.getLastUpdatedBy ( ) ) ;
            // societiesEntity.setLastUpdatedDate ( societiesDTO.getLastUpdatedDate ( ) ) ;
            societiesEntity.setMinCode(societiesDTO.getMinCode());
            societiesEntity.setSocietiesStatus(societiesDTO.getSocietiesStatus());
            societiesEntity.setSocCode(maxId);
            doAdd(societiesEntity);
            societiesDTO.setCode(MapEntityKeyFactory.createSocietiesEntityKey(maxId));
            return societiesDTO;
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
     * Update an Existing SocietiesEntity * @param soc1DTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO societiesDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            ISocietiesDTO societiesDTO = (ISocietiesDTO)societiesDTOParam;
            SocietiesEntity societiesEntity = EM().find(SocietiesEntity.class, societiesDTO.getCode());
            if (societiesEntity == null) {
                throw new NoResultException();
            }
            societiesEntity.setSocName(societiesDTO.getName());
            // societiesEntity.setCreatedBy ( societiesDTO.getCreatedBy ( ) ) ;
            // societiesEntity.setCreatedDate ( societiesDTO.getCreatedDate ( ) ) ;
            societiesEntity.setLastUpdatedBy(societiesDTO.getLastUpdatedBy());
            societiesEntity.setLastUpdatedDate(societiesDTO.getLastUpdatedDate());
            doUpdate(societiesEntity);
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
     * Remove an Existing SocietiesEntity * @param soc1DTO the Dto to be removed
     * @return Boolean
     */
    public Boolean remove(IBasicDTO societiesDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            ISocietiesDTO societiesDTO = (ISocietiesDTO)societiesDTOParam;
            SocietiesEntity societiesEntity =
                EM().find(SocietiesEntity.class, (ISocietiesEntityKey)societiesDTO.getCode());
            if (societiesEntity == null) {
                throw new NoResultException();
            }
            doRemove(societiesEntity);
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
     * Get the MaxId of SocietiesEntity * <br> * @return Long
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("SocietiesEntity.findNewId");
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
     * Get SocietiesEntity By Primary Key * @param id primary key
     * @return ISocietiesDTO
     */
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            SocietiesEntity societiesEntity = EM().find(SocietiesEntity.class, (ISocietiesEntityKey)id);
            if (societiesEntity == null) {
                throw new NoResultException();
            }
            ISocietiesDTO societiesDTO = MapEntityConverter.getSocietiesDTO(societiesEntity);
            return societiesDTO;
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
     * get all data with specific objectTypeCode * @param long
     * @return List ISocietiesDTO
     */
    public List<IBasicDTO> ListByObjectType(Long objtypeCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<SocietiesEntity> list =
                EM().createNamedQuery("SocietiesEntity.findByObjType").setParameter("objtypeCode",
                                                                                    objtypeCode).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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
     * get all SocietiesDTOs with socName like a given name * @return List ISocietiesDTO
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from SocietiesEntity o ");
            query.append(" where ");
            String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.socName", name);
            query.append(condition);

            List<SocietiesEntity> list = EM().createQuery(query.toString()).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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
     * get SocietiesDTOs with specific code * @return List ISocietiesDTO
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(MapEntityKeyFactory.createSocietiesEntityKey((Long)code)));
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

    //if flag = 1 it mean center(ministry)
    // if flag = 2 it mean system

    public List<IBasicDTO> getAllByFalg(Long flag) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            List<SocietiesEntity> list =
                EM().createNamedQuery("SocietiesEntity.getAllByFlag").setParameter("centerOrSystemFlag",
                                                                                   flag).setHint("toplink.refresh",
                                                                                                 "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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

    public List<IBasicDTO> searchByMinCode(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();

            List<SocietiesEntity> list =
                EM().createNamedQuery("SocietiesEntity.searchByMinCode").setParameter("minCode",
                                                                                      minCode).setHint("toplink.refresh",
                                                                                                       "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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


    public List<IBasicDTO> searchByNameAndStatus(String name, String SystemOrCenterFlag) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder query = new StringBuilder("select o from SocietiesEntity o ");
            query.append(" where ");
            String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.socName", name);
            query.append(condition);
            query.append(" And o.SocietiesStatus = ").append(SystemOrCenterFlag);


            List<SocietiesEntity> list = EM().createQuery(query.toString()).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (SocietiesEntity societies : list) {
                arrayList.add(MapEntityConverter.getSocietiesDTO(societies));
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
