package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfEservicesTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfEservicesTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfEservicesTypesEntity;
import com.beshara.csc.inf.business.entity.InfEservicesTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class InfEservicesTypesDAO extends InfBaseDAO {
    public InfEservicesTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfEservicesTypesDAO();
    }

    public IInfEservicesTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfEservicesTypesEntityKey id = (IInfEservicesTypesEntityKey)id1;
            InfEservicesTypesEntity iInfEservicesTypesEntity = EM().find(InfEservicesTypesEntity.class, id);
            if (iInfEservicesTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfEservicesTypesDTO infEservicesTypesDTO =
                InfDTOFactory.createInfEservicesTypesDTO(iInfEservicesTypesEntity);
            return infEservicesTypesDTO;
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
            ArrayList arrayList = new ArrayList();
            List<InfEservicesTypesEntity> list =
                EM().createNamedQuery("InfEservicesTypesEntity.findAll").setHint("toplink.refresh",
                                                                                 "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfEservicesTypesEntity iInfEservicesTypes : list) {
                arrayList.add(InfDTOFactory.createInfEservicesTypesDTO(iInfEservicesTypes));
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
            Query query = EM().createNamedQuery("InfEservicesTypesEntity.findNewId");
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

    public IInfEservicesTypesDTO add(IBasicDTO infEservicesTypesDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            InfEservicesTypesEntity iInfEservicesTypesEntity = new InfEservicesTypesEntity();
            IInfEservicesTypesDTO infEservicesTypesDTO = (IInfEservicesTypesDTO)infEservicesTypesDTO1;
            Long newID = findNewId();
            IInfEservicesTypesEntityKey ent = new InfEservicesTypesEntityKey(newID);
            iInfEservicesTypesEntity.setServicesId(ent.getServicesId());
            iInfEservicesTypesEntity.setServicesName(infEservicesTypesDTO.getServicesName());
            iInfEservicesTypesEntity.setCreatedBy(infEservicesTypesDTO.getCreatedBy());
            iInfEservicesTypesEntity.setCreatedDate(infEservicesTypesDTO.getCreatedDate());
            iInfEservicesTypesEntity.setAuditStatus(infEservicesTypesDTO.getAuditStatus());
            iInfEservicesTypesEntity.setTabrecSerial(infEservicesTypesDTO.getTabrecSerial());
            doAdd(iInfEservicesTypesEntity);
            // Set PK after creation
            infEservicesTypesDTO.setCode(ent);
            return infEservicesTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO infEservicesTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfEservicesTypesDTO infEservicesTypesDTO = (IInfEservicesTypesDTO)infEservicesTypesDTO1;
            InfEservicesTypesEntity iInfEservicesTypesEntity =
                EM().find(InfEservicesTypesEntity.class, (IInfEservicesTypesEntityKey)infEservicesTypesDTO.getCode());
            if (iInfEservicesTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            iInfEservicesTypesEntity.setServicesId(((IInfEservicesTypesEntityKey)infEservicesTypesDTO.getCode()).getServicesId());
            iInfEservicesTypesEntity.setServicesName(infEservicesTypesDTO.getServicesName());
            iInfEservicesTypesEntity.setCreatedBy(infEservicesTypesDTO.getCreatedBy());
            iInfEservicesTypesEntity.setCreatedDate(infEservicesTypesDTO.getCreatedDate());
            iInfEservicesTypesEntity.setLastUpdatedBy(infEservicesTypesDTO.getLastUpdatedBy());
            iInfEservicesTypesEntity.setLastUpdatedDate(infEservicesTypesDTO.getLastUpdatedDate());
            iInfEservicesTypesEntity.setAuditStatus(infEservicesTypesDTO.getAuditStatus());
            iInfEservicesTypesEntity.setTabrecSerial(infEservicesTypesDTO.getTabrecSerial());
            doUpdate(iInfEservicesTypesEntity);
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

    public Boolean remove(IBasicDTO infEservicesTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfEservicesTypesDTO infEservicesTypesDTO = (IInfEservicesTypesDTO)infEservicesTypesDTO1;
            InfEservicesTypesEntity iInfEservicesTypesEntity =
                EM().find(InfEservicesTypesEntity.class, infEservicesTypesDTO.getCode());
            if (iInfEservicesTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(iInfEservicesTypesEntity);
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

    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {

            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from InfEservicesTypesEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.doctypeName", name));

            List<InfEservicesTypesEntity> list =
                EM().createQuery(query.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            for (InfEservicesTypesEntity hireTypes : list) {
                arrayList.add(InfDTOFactory.createInfEservicesTypesDTO(hireTypes));
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


    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(InfEntityKeyFactory.createInfEservicesTypesEntityKey((Long)code)));
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("InfEservicesTypesEntity.getCodeName").setHint("toplink.refresh",
                                                                                        "true").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            List<InfEservicesTypesEntity> list =
                EM().createNamedQuery("InfEservicesTypesEntity.getByName").setParameter("name",
                                                                                        name).setHint("toplink.refresh",
                                                                                                      "true").getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            return InfDTOFactory.createInfEservicesTypesDTO(list.get(0));

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
