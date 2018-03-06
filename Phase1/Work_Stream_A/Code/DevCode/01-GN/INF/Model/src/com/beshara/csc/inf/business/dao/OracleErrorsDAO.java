package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IOracleErrorsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IOracleErrorsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.NewspapersEntity;
import com.beshara.csc.inf.business.entity.OracleErrorsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class OracleErrorsDAO extends InfBaseDAO {
    public OracleErrorsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new OracleErrorsDAO();
    }

    public List<IOracleErrorsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<OracleErrorsEntity> list =
                EM().createNamedQuery("OracleErrorsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (OracleErrorsEntity oracleErrors : list) {
                arrayList.add(InfDTOFactory.createOracleErrorsDTO(oracleErrors));
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
     * Create a New OracleErrorsEntity * @param oracleErrorsDTO
     * @return IOracleErrorsDTO
     */
    public IOracleErrorsDTO add(IBasicDTO oracleErrorsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            OracleErrorsEntity oracleErrorsEntity = new OracleErrorsEntity();
            IOracleErrorsDTO oracleErrorsDTO = (IOracleErrorsDTO)oracleErrorsDTO1;
            oracleErrorsDTO.setCode(InfEntityKeyFactory.createOracleErrorsEntityKey(findNewId()));
            oracleErrorsEntity.setErrNum(((IOracleErrorsEntityKey)oracleErrorsDTO.getCode()).getErrNum());
            // oracleErrorsEntity.setErrCode(((IOracleErrorsEntityKey)oracleErrorsDTO.getCode()).getErrCode());
            oracleErrorsEntity.setErrEMsg(oracleErrorsDTO.getErrEMsg());
            oracleErrorsEntity.setErrAMsg(oracleErrorsDTO.getErrAMsg());
            oracleErrorsEntity.setCreatedBy(oracleErrorsDTO.getCreatedBy());
            oracleErrorsEntity.setCreatedDate(oracleErrorsDTO.getCreatedDate());
            oracleErrorsEntity.setAuditStatus(oracleErrorsDTO.getAuditStatus());
            oracleErrorsEntity.setTabrecSerial(oracleErrorsDTO.getTabrecSerial());
            doAdd(oracleErrorsEntity);
            return oracleErrorsDTO;
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
     * Update an Existing OracleErrorsEntity * @param oracleErrorsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO oracleErrorsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IOracleErrorsDTO oracleErrorsDTO = (IOracleErrorsDTO)oracleErrorsDTO1;
            OracleErrorsEntity oracleErrorsEntity = EM().find(OracleErrorsEntity.class, oracleErrorsDTO.getCode());
            oracleErrorsEntity.setErrNum(((IOracleErrorsEntityKey)oracleErrorsDTO.getCode()).getErrNum());
            //        oracleErrorsEntity.setErrCode(((IOracleErrorsEntityKey)oracleErrorsDTO.getCode()).getErrCode());
            oracleErrorsEntity.setErrEMsg(oracleErrorsDTO.getErrEMsg());
            oracleErrorsEntity.setErrAMsg(oracleErrorsDTO.getErrAMsg());
            oracleErrorsEntity.setCreatedBy(oracleErrorsDTO.getCreatedBy());
            oracleErrorsEntity.setCreatedDate(oracleErrorsDTO.getCreatedDate());
            oracleErrorsEntity.setLastUpdatedBy(oracleErrorsDTO.getLastUpdatedBy());
            oracleErrorsEntity.setLastUpdatedDate(oracleErrorsDTO.getLastUpdatedDate());
            oracleErrorsEntity.setAuditStatus(oracleErrorsDTO.getAuditStatus());
            oracleErrorsEntity.setTabrecSerial(oracleErrorsDTO.getTabrecSerial());
            doUpdate(oracleErrorsEntity);
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
     * Remove an Existing OracleErrorsEntity * @param oracleErrorsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO oracleErrorsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IOracleErrorsDTO oracleErrorsDTO = (IOracleErrorsDTO)oracleErrorsDTO1;
            OracleErrorsEntity oracleErrorsEntity = EM().find(OracleErrorsEntity.class, oracleErrorsDTO.getCode());
            if (oracleErrorsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(oracleErrorsEntity);
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
     * Get OracleErrorsEntity By Primary Key * @param id
     * @return IOracleErrorsDTO
     */
    public IOracleErrorsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IOracleErrorsEntityKey id = (IOracleErrorsEntityKey)id1;
            OracleErrorsEntity oracleErrorsEntity = EM().find(OracleErrorsEntity.class, id);
            if (oracleErrorsEntity == null) {
                throw new ItemNotFoundException();
            }
            IOracleErrorsDTO oracleErrorsDTO = InfDTOFactory.createOracleErrorsDTO(oracleErrorsEntity);
            return oracleErrorsDTO;
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
            Long id = (Long)EM().createNamedQuery("OracleErrorsEntity.findNewId").getSingleResult();
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


    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("OracleErrorsEntity.getCodeName").getResultList();
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
            List<OracleErrorsEntity> list =
                EM().createNamedQuery("OracleErrorsEntity.searchByCode").setParameter("errCode",
                                                                                      (Long)code).getResultList();
            for (OracleErrorsEntity entity : list) {
                arrayList.add(InfDTOFactory.createOracleErrorsDTO(entity));
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
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder ejbQL = new StringBuilder("select o from OracleErrorsEntity   o where o.errAMsg like '%");
            ejbQL.append(searchName);
            ejbQL.append("%' order by o.errAMsg");
            List<NewspapersEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (NewspapersEntity entity : list) {
                arrayList.add(InfDTOFactory.createNewspapersDTO(entity));
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
