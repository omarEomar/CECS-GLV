package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IResidentTypeDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IResidentTypeEntityKey;
import com.beshara.csc.inf.business.entity.ResidentTypeEntity;
import com.beshara.csc.inf.business.entity.ResidentTypeEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class ResidentTypeDAO extends InfBaseDAO {
    public ResidentTypeDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new ResidentTypeDAO();
    }

    /**<code>select o from ResidentTypeEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IResidentTypeDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ResidentTypeEntity> list =
                EM().createNamedQuery("ResidentTypeEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (ResidentTypeEntity residentType : list) {
                arrayList.add(InfDTOFactory.createResidentTypeDTO(residentType));
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
     * Create a New ResidentTypeEntity * @param residentTypeDTO1
     * @return IResidentTypeDTO
     */
    public IResidentTypeDTO add(IBasicDTO residentTypeDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ResidentTypeEntity residentTypeEntity = new ResidentTypeEntity();
            IResidentTypeEntityKey ent =new ResidentTypeEntityKey(findNewId());
            IResidentTypeDTO residentTypeDTO = (IResidentTypeDTO)residentTypeDTO1;
            residentTypeEntity.setRestypeCode(ent.getRestypeCode());
            residentTypeEntity.setRestypeName(residentTypeDTO.getName());
            residentTypeEntity.setCreatedBy(residentTypeDTO.getCreatedBy());
            residentTypeEntity.setCreatedDate(residentTypeDTO.getCreatedDate());
            residentTypeEntity.setAuditStatus(residentTypeDTO.getAuditStatus());
            residentTypeEntity.setTabrecSerial(residentTypeDTO.getTabrecSerial());
            doAdd(residentTypeEntity);
            // Set PK after creation
            residentTypeDTO.setCode(ent);
            return residentTypeDTO;
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
     * Update an Existing ResidentTypeEntity * @param residentTypeDTO1
     * @return Boolean
     */
    public Boolean update(IBasicDTO residentTypeDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IResidentTypeDTO residentTypeDTO = (IResidentTypeDTO)residentTypeDTO1;
            ResidentTypeEntity residentTypeEntity =
                EM().find(ResidentTypeEntity.class, (IResidentTypeEntityKey)residentTypeDTO.getCode());
            residentTypeEntity.setRestypeCode(((IResidentTypeEntityKey)residentTypeDTO.getCode()).getRestypeCode());
            residentTypeEntity.setRestypeName(residentTypeDTO.getName());
            residentTypeEntity.setCreatedBy(residentTypeDTO.getCreatedBy());
            residentTypeEntity.setCreatedDate(residentTypeDTO.getCreatedDate());
            residentTypeEntity.setLastUpdatedBy(residentTypeDTO.getLastUpdatedBy());
            residentTypeEntity.setLastUpdatedDate(residentTypeDTO.getLastUpdatedDate());
            residentTypeEntity.setAuditStatus(residentTypeDTO.getAuditStatus());
            residentTypeEntity.setTabrecSerial(residentTypeDTO.getTabrecSerial());
            doUpdate(residentTypeEntity);
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
     * Remove an Existing ResidentTypeEntity * @param residentTypeDTO1
     * @return Boolean
     */
    public Boolean remove(IBasicDTO residentTypeDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IResidentTypeDTO residentTypeDTO = (IResidentTypeDTO)residentTypeDTO1;
            ResidentTypeEntity residentTypeEntity =
                EM().find(ResidentTypeEntity.class, (IResidentTypeEntityKey)residentTypeDTO.getCode());
            if (residentTypeEntity == null) {
                throw new NoResultException();
            }
            doRemove(residentTypeEntity);
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
     * Get ResidentTypeEntity By Primary Key * @param id1
     * @return IResidentTypeDTO
     */
    public IResidentTypeDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IResidentTypeEntityKey id = (IResidentTypeEntityKey)id1;
            ResidentTypeEntity residentTypeEntity = EM().find(ResidentTypeEntity.class, id);
            if (residentTypeEntity == null) {
                throw new NoResultException();
            }
            IResidentTypeDTO residentTypeDTO = InfDTOFactory.createResidentTypeDTO(residentTypeEntity);
            return residentTypeDTO;
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
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("ResidentTypeEntity.getCodeName").getResultList();
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
            Query query = EM().createNamedQuery("ResidentTypeEntity.findNewId");
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
     * Search for ResidentTypeEntity * <br> * @return List
     */
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

    /**
     * Get all by code equal code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ResidentTypeEntity> list =
                EM().createNamedQuery("ResidentTypeEntity.searchByCode").setParameter("restypeCode",
                                                                                      (Long)code).getResultList();
            for (ResidentTypeEntity entity : list) {
                arrayList.add(InfDTOFactory.createResidentTypeDTO(entity));
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

    /**
     * Get all by name like string * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     * @author Aly Noor @since 06/29/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            //        List<ResidentTypeEntity> list =
            //            em.createNamedQuery("ResidentTypeEntity.searchByName").setParameter("restypeName", searchName).getResultList();
            List<BasicEntity> list = searchByName("ResidentTypeEntity", "restypeName", searchName);
            //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list != null) {
                for (BasicEntity obj : list) {
                    ResidentTypeEntity entity = (ResidentTypeEntity)obj;
                    //for (ResidentTypeEntity entity : list) {
                    arrayList.add(InfDTOFactory.createResidentTypeDTO(entity));
                }
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

    public List<BasicEntity> searchByName(String entityName, String colName,
                                          String searchName) throws DataBaseException, SharedApplicationException {
        //List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        //List<CountriesEntity> list =
        //   em.createNamedQuery("CountriesEntity.searchByName").setParameter("cntryName", cntryName).getResultList();

        try {
            StringBuilder ejbQL = new StringBuilder("select o from ");
            ejbQL.append(entityName);
            ejbQL.append(" o where ( ");
            ejbQL.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o." + colName, searchName));
            ejbQL.append(" ) order by o.");
            ejbQL.append(colName);
            List<BasicEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();

            if (list == null || list.size() == 0)
                throw new NoResultException();
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
    
    @Override
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("ResidentTypeEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createResidentTypeDTO((ResidentTypeEntity)list.get(0)));
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
