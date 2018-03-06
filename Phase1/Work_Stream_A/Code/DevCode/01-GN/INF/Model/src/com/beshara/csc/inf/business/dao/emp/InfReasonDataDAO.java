package com.beshara.csc.inf.business.dao.emp;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dao.InfBaseDAO;
import com.beshara.csc.inf.business.dto.IInfReasonDataDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.emp.IInfReasonDataEntityKey;
import com.beshara.csc.inf.business.entity.emp.IInfReasonTypesEntityKey;
import com.beshara.csc.inf.business.entity.emp.InfReasonDataEntity;
import com.beshara.csc.inf.business.entity.emp.InfReasonTypesEntity;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class InfReasonDataDAO extends InfBaseDAO {
    public InfReasonDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfReasonDataDAO();
    }

    public IBasicDTO getById(IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonDataEntityKey id = (IInfReasonDataEntityKey)entityKey;
            InfReasonDataEntity reasonDataEntity = EM().find(InfReasonDataEntity.class, id);
            if (reasonDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfReasonDataDTO reasonDataDTO = InfEntityConvertr.getReasonDataDTO(reasonDataEntity);
            return reasonDataDTO;
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
            List<InfReasonDataEntity> list =
                EM().createNamedQuery("InfReasonDataEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfReasonDataEntity reasonData : list) {
                arrayList.add(InfEntityConvertr.getReasonDataDTO(reasonData));
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

    public Long getNewRestypeCode(long restypecode) throws DataBaseException, SharedApplicationException  {
        Long id =
            (Long)EM().createNamedQuery("ReasonDataEntity.findNewIdByType").setParameter("restypeCode", restypecode).getSingleResult();
        if (id != null) {
            return id;
        } else {
            return new Long(0);
        }
    }

    public Long findNewId()throws DataBaseException, SharedApplicationException  {
        Long id = (Long)EM().createNamedQuery("InfReasonDataEntity.findNewId").getSingleResult();
        if (id != null) {
            return id;
        } else {
            return new Long(0);
        }
    }

    public IInfReasonDataDTO add(IBasicDTO reasonDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IInfReasonDataDTO reasonDataDTO = (IInfReasonDataDTO)reasonDataDTO1;
            InfReasonDataEntity reasonDataEntity = new InfReasonDataEntity();
            InfReasonTypesEntity reasonTypesEntity =
                EM().find(InfReasonTypesEntity.class, (IInfReasonTypesEntityKey)InfEntityKeyFactory.createReasonTypesEntityKey(reasonDataDTO.getRestypeCode()));
            //        InfReasonTypesEntity reasonTypesEntity1 = new InfReasonTypesEntity();
            //        reasonTypesEntity1.setRestypeCode(reasonDataDTO.getRestypeCode());
            //              reasonDataEntity.setRestypeCode(reasonTypesEntity.getRestypeCode());
            reasonDataEntity.setReasonTypesEntity(reasonTypesEntity);
            reasonDataEntity.setResdatDesc(reasonDataDTO.getResdatDesc());
            reasonDataEntity.setCreatedBy(reasonDataDTO.getCreatedBy());
            reasonDataEntity.setCreatedDate(reasonDataDTO.getCreatedDate());
            reasonDataEntity.setLastUpdatedBy(reasonDataDTO.getLastUpdatedBy());
            reasonDataEntity.setLastUpdatedDate(reasonDataDTO.getLastUpdatedDate());
            reasonDataEntity.setResdatDesc(reasonDataDTO.getName());
            reasonDataEntity.setResdatSerial(this.getNewRestypeCode(reasonDataDTO.getRestypeCode()) + 1);


            reasonDataDTO.setResdatSerial(reasonDataEntity.getResdatSerial());
            doAdd(reasonDataEntity);
            reasonDataDTO.setCode((IInfReasonDataEntityKey)InfEntityKeyFactory.createReasonDataEntityKey(reasonDataEntity.getResdatSerial(),
                                                                                                         reasonDataEntity.getReasonTypesEntity().getRestypeCode()));
            return reasonDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO reasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonDataDTO reasonDataDTO = (IInfReasonDataDTO)reasonDataDTO1;
            InfReasonDataEntity reasonDataEntity =
                EM().find(InfReasonDataEntity.class, (IInfReasonDataEntityKey)reasonDataDTO.getCode());
            if (reasonDataEntity == null) {
                throw new ItemNotFoundException();
            }
            reasonDataEntity.setResdatDesc(reasonDataDTO.getResdatDesc());
            reasonDataEntity.setCreatedBy(reasonDataDTO.getCreatedBy());
            reasonDataEntity.setCreatedDate(reasonDataDTO.getCreatedDate());
            reasonDataEntity.setLastUpdatedBy(reasonDataDTO.getLastUpdatedBy());
            reasonDataEntity.setLastUpdatedDate(reasonDataDTO.getLastUpdatedDate());
            reasonDataEntity.setResdatDesc(reasonDataDTO.getName());
            doUpdate(reasonDataEntity);

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


    public Boolean remove(IBasicDTO reasonDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfReasonDataDTO reasonDataDTO = (IInfReasonDataDTO)reasonDataDTO1;
            InfReasonDataEntity reasonDataEntity =
                EM().find(InfReasonDataEntity.class, (IInfReasonDataEntityKey)reasonDataDTO.getCode());
            if (reasonDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(reasonDataEntity);

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
        return super.search(basicDTO);

    }

    /**
     * Get list of code and name * return list of IReasonDataDTO initialize with code and name only * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {

        return EM().createNamedQuery("InfReasonDataEntity.getCodeName").getResultList();

    }

    public List<IBasicDTO> getReasonDataByType(long reasontype) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfReasonDataEntity> list =
                EM().createNamedQuery("InfReasonDataEntity.getReasondataByType").setHint("toplink.refresh",
                                                                                         "true").setParameter("restypeCode",
                                                                                                              reasontype).getResultList();
            for (InfReasonDataEntity reasonData : list) {
                arrayList.add(InfEntityConvertr.getReasonDataDTO(reasonData));
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
    public List<IBasicDTO> searchReasonData(IBasicDTO infReasonDataDTO) throws DataBaseException, SharedApplicationException {
        try{  IInfReasonDataDTO infReasonDataDTO1 = (IInfReasonDataDTO)infReasonDataDTO;
        StringBuilder ejbql =
            new StringBuilder("select o from InfReasonDataEntity o where o.resdatSerial=o.resdatSerial AND o.restypeCode=:restypeCode");
        if (infReasonDataDTO1.getCode() != null)
            ejbql.append(" AND o.resdatSerial = :resdatSerial ");
        if (infReasonDataDTO1.getName() != null && !infReasonDataDTO1.getName().equals("")) {
            ejbql.append(" and ");
            String condition =
                QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.resdatDesc", infReasonDataDTO1.getName());
            ejbql.append(condition);
        }

        Query query = EM().createQuery(ejbql.toString());
        if (infReasonDataDTO1.getCode() != null)
            query.setParameter("resdatSerial",
                               ((IInfReasonDataEntityKey)infReasonDataDTO1.getCode()).getResdatSerial()).setParameter("restypeCode",
                                                                                                                      ((IInfReasonDataEntityKey)infReasonDataDTO1.getCode()).getRestypeCode());
        if (infReasonDataDTO1.getName() != null && !infReasonDataDTO1.getName().equals(""))
            query.setParameter("restypeCode", infReasonDataDTO1.getRestypeCode());
        //        if (searchExtraDataTypesDTO1.getName() != null &&
        //            !searchExtraDataTypesDTO1.getName().equals(""))
        //            query.setParameter("extdattypeName",
        //                               searchExtraDataTypesDTO1.getName());

        List<InfReasonDataEntity> list = query.getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException ();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (InfReasonDataEntity entity : list) {
            listDTO.add(InfEntityConvertr.getReasonDataDTO(entity));
        }
        return listDTO;
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
