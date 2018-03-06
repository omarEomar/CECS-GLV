package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IApprovalMakersDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntity;
import com.beshara.csc.inf.business.entity.ApprovalMakersEntityKey;
import com.beshara.csc.inf.business.entity.IApprovalMakersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class ApprovalMakersDAO extends InfBaseDAO {
    public ApprovalMakersDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new ApprovalMakersDAO();
    }

    @Override
    public List<IApprovalMakersDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ApprovalMakersEntity> list =
                EM().createNamedQuery("ApprovalMakersEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            for (ApprovalMakersEntity approvalMakers : list) {
                arrayList.add(InfDTOFactory.createApprovalMakersDTO(approvalMakers));
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
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("ApprovalMakersEntity.findNewId").getSingleResult();
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
     * Create a New ApprovalMakersEntity * @param approvalMakersDTO
     * @return IApprovalMakersDTO
     */
    @Override
    public IApprovalMakersDTO add(IBasicDTO approvalMakersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            Long code = findNewId();

            IApprovalMakersDTO approvalMakersDTO = (IApprovalMakersDTO)approvalMakersDTO1;
            ApprovalMakersEntity approvalMakersEntity = new ApprovalMakersEntity();
            approvalMakersEntity.setAprmakerCode(code);
            approvalMakersEntity.setDynamicFlag(approvalMakersDTO.getDynamicFlag());
            approvalMakersEntity.setAprmakerName(approvalMakersDTO.getAprmakerName());
            approvalMakersEntity.setCreatedBy(approvalMakersDTO.getCreatedBy());
            approvalMakersEntity.setCreatedDate(approvalMakersDTO.getCreatedDate());
            approvalMakersEntity.setLastUpdatedBy(approvalMakersDTO.getLastUpdatedBy());
            approvalMakersEntity.setLastUpdatedDate(approvalMakersDTO.getLastUpdatedDate());

            doAdd(approvalMakersEntity);

            approvalMakersDTO.setCode(new ApprovalMakersEntityKey(code));
            return approvalMakersDTO;
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
     * Update an Existing ApprovalMakersEntity * @param approvalMakersDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO approvalMakersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IApprovalMakersDTO approvalMakersDTO = (IApprovalMakersDTO)approvalMakersDTO1;
            ApprovalMakersEntity approvalMakersEntity =
                EM().find(ApprovalMakersEntity.class, (IApprovalMakersEntityKey)approvalMakersDTO.getCode());
            approvalMakersEntity.setAprmakerCode(((IApprovalMakersEntityKey)approvalMakersDTO.getCode()).getAprmakerCode());
            approvalMakersEntity.setDynamicFlag(approvalMakersDTO.getDynamicFlag());
            approvalMakersEntity.setAprmakerName(approvalMakersDTO.getAprmakerName());
            doUpdate(approvalMakersEntity);
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
     * Remove an Existing ApprovalMakersEntity * @param approvalMakersDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO approvalMakersDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IApprovalMakersDTO approvalMakersDTO = (IApprovalMakersDTO)approvalMakersDTO1;
            ApprovalMakersEntity approvalMakersEntity =
                EM().find(ApprovalMakersEntity.class, (IApprovalMakersEntityKey)approvalMakersDTO.getCode());
            if (approvalMakersEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(approvalMakersEntity);
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
     * Get ApprovalMakersEntity By Primary Key * @param id
     * @return IApprovalMakersDTO
     */
    @Override
    public IApprovalMakersDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IApprovalMakersEntityKey id = (IApprovalMakersEntityKey)id1;
            ApprovalMakersEntity approvalMakersEntity = EM().find(ApprovalMakersEntity.class, id);
            if (approvalMakersEntity == null) {
                throw new ItemNotFoundException();
            }
            IApprovalMakersDTO approvalMakersDTO = InfDTOFactory.createApprovalMakersDTO(approvalMakersEntity);
            return approvalMakersDTO;
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
     * Search for ApprovalMakersEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    public List<IBasicDTO> simpleSearch(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList();
            StringBuilder ejbql =
                new StringBuilder("select o from ApprovalMakersEntity o where o.aprmakerCode=o.aprmakerCode");

            if (basicDTO.getCode() != null) {
                ejbql.append(" and o.aprmakerCode=" +
                             (((IApprovalMakersEntityKey)basicDTO.getCode())).getAprmakerCode());
            }

            if (basicDTO.getName() != null) {
                ejbql.append(" and o.aprmakerName like '%" + basicDTO.getName() + "%'");

            } else {
                arrayList = new ArrayList(0);
            }

            List<ApprovalMakersEntity> listOfEntity = EM().createQuery(ejbql.toString()).getResultList();
            System.out.println("size=" + listOfEntity.size());

            for (ApprovalMakersEntity approvalMakersEntity : listOfEntity) {
                arrayList.add(InfDTOFactory.createApprovalMakersDTO(approvalMakersEntity));
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

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("ApprovalMakersEntity.getCodeName").getResultList();
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
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<ApprovalMakersEntity> list =
                EM().createNamedQuery("ApprovalMakersEntity.searchByCode").setParameter("aprmakerCode",
                                                                                        (Long)code).getResultList();
            for (ApprovalMakersEntity entity : list) {
                arrayList.add(InfDTOFactory.createApprovalMakersDTO(entity));
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
     * @EditedBy @author Aly Noor @since 06/26/2014 eidted to use generic method InfBaseFacadeBean.searchByName
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<BasicEntity> list = searchByName("ApprovalMakersEntity", "aprmakerName", searchName);
            if (list != null) {
                for (BasicEntity obj : list) {
                    ApprovalMakersEntity entity = (ApprovalMakersEntity)obj;
                    arrayList.add(InfDTOFactory.createApprovalMakersDTO(entity));
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

    /**
     * Get code and name
     * @return list
     * @author A.Elmasry
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getApprovalmakerForMov() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("ApprovalMakersEntity.findApprovalMakerForMov").getResultList();
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
    
    
    @Override
        public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
            
            try {
                List list =
                    EM().createNamedQuery("ApprovalMakersEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                             "true").getResultList();
                if (list.size() > 0) {
                    return (InfDTOFactory.createApprovalMakersDTO((ApprovalMakersEntity)list.get(0)));
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
