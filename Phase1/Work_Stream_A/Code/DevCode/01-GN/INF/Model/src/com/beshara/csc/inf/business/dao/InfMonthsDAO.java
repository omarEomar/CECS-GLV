package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfMonthsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfMonthsEntityKey;
import com.beshara.csc.inf.business.entity.InfMonthsEntity;
import com.beshara.csc.inf.business.entity.InfMonthsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class InfMonthsDAO extends InfBaseDAO {
    public InfMonthsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfMonthsDAO();
    }

    public IInfMonthsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {

        try {
            IInfMonthsEntityKey id = (IInfMonthsEntityKey)id1;

            InfMonthsEntity infMonthsEntity = EM().find(InfMonthsEntity.class, id);
            if (infMonthsEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfMonthsDTO infMonthsDTO = InfDTOFactory.createMonthsDTO(infMonthsEntity);
            return infMonthsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IInfMonthsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfMonthsEntity> list =
                EM().createNamedQuery("InfMonthsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfMonthsEntity infMonths : list) {
                arrayList.add(InfDTOFactory.createMonthsDTO(infMonths));
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
            Query query = EM().createNamedQuery("InfMonthsEntity.findNewId");
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

    public IInfMonthsDTO add(IBasicDTO infMonthsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfMonthsEntity infMonthsEntity = new InfMonthsEntity();

            IInfMonthsDTO infMonthsDTO = (IInfMonthsDTO)infMonthsDTO1;
            Long findNewId = findNewId();
            IInfMonthsEntityKey ent = new InfMonthsEntityKey(findNewId);
            infMonthsEntity.setMonthCode(ent.getMonthCode());
            infMonthsEntity.setMonthName(infMonthsDTO.getName());
            infMonthsEntity.setCreatedBy(infMonthsDTO.getCreatedBy());
            infMonthsEntity.setCreatedDate(infMonthsDTO.getCreatedDate());
            infMonthsEntity.setAuditStatus(infMonthsDTO.getAuditStatus());
            infMonthsEntity.setTabrecSerial(infMonthsDTO.getTabrecSerial());


            doAdd(infMonthsEntity);
            // Set PK after creation
            infMonthsDTO.setCode(ent);
            return infMonthsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO infMonthsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfMonthsDTO infMonthsDTO = (IInfMonthsDTO)infMonthsDTO1;

            InfMonthsEntity infMonthsEntity =
                EM().find(InfMonthsEntity.class, (IInfMonthsEntityKey)infMonthsDTO.getCode());
            if (infMonthsEntity == null) {
                throw new ItemNotFoundException();
            }
            infMonthsEntity.setMonthCode(((IInfMonthsEntityKey)infMonthsDTO.getCode()).getMonthCode());
            infMonthsEntity.setMonthName(infMonthsDTO.getName());
            infMonthsEntity.setCreatedBy(infMonthsDTO.getCreatedBy());
            infMonthsEntity.setCreatedDate(infMonthsDTO.getCreatedDate());
            infMonthsEntity.setLastUpdatedBy(infMonthsDTO.getLastUpdatedBy());
            infMonthsEntity.setLastUpdatedDate(infMonthsDTO.getLastUpdatedDate());
            infMonthsEntity.setAuditStatus(infMonthsDTO.getAuditStatus());
            infMonthsEntity.setTabrecSerial(infMonthsDTO.getTabrecSerial());
            doUpdate(infMonthsEntity);
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

    public Boolean remove(IBasicDTO infMonthsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfMonthsDTO infMonthsDTO = (IInfMonthsDTO)infMonthsDTO1;

            InfMonthsEntity infMonthsEntity =
                EM().find(InfMonthsEntity.class, (IInfMonthsEntityKey)infMonthsDTO.getCode());

            if (infMonthsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infMonthsEntity);
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("InfMonthsEntity.getCodeName").getResultList();
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
            ArrayList arrayList = new ArrayList();
            List<InfMonthsEntity> list =
                EM().createNamedQuery("InfMonthsEntity.searchByCode").setParameter("monthCode",
                                                                                   (Long)code).getResultList();
            for (InfMonthsEntity entity : list) {
                arrayList.add(InfDTOFactory.createMonthsDTO(entity));
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

    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        List<BasicEntity> list = searchByName("InfMonthsEntity", "monthName", searchName);
        //em.createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
        if (list.size() == 0)
            throw new NoResultException();
        if (list != null) {
            for (BasicEntity obj : list) {
                InfMonthsEntity entity = (InfMonthsEntity)obj;
                //for (InfMonthsEntity entity : list) {
                arrayList.add(InfDTOFactory.createMonthsDTO(entity));
            }
        }

        return arrayList;
    }

    public List<BasicEntity> searchByName(String entityName, String colName,
                                          String searchName) throws DataBaseException, SharedApplicationException {

        StringBuilder ejbQL = new StringBuilder("select o from ");
        ejbQL.append(entityName);
        ejbQL.append(" o where ( ");
        ejbQL.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o." + colName, searchName));
        ejbQL.append(" ) order by o.");
        ejbQL.append(colName);
        List<BasicEntity> list = EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();

        if (list == null || list.size() == 0)
            throw new NoResultException();
        return list;
    }
}
