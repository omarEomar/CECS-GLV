package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class InfDocumentTypesDAO extends InfBaseDAO {
    public InfDocumentTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfDocumentTypesDAO();
    }

    public IInfDocumentTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfDocumentTypesEntityKey id = (IInfDocumentTypesEntityKey)id1;
            InfDocumentTypesEntity documentTypesEntity = EM().find(InfDocumentTypesEntity.class, id);
            if (documentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfDocumentTypesDTO documentTypesDTO = InfDTOFactory.createInfDocumentTypesDTO(documentTypesEntity);
            return documentTypesDTO;
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
            List<InfDocumentTypesEntity> list =
                EM().createNamedQuery("InfDocumentTypesEntity.findAll").setHint("toplink.refresh",
                                                                                "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (InfDocumentTypesEntity documentTypes : list) {
                arrayList.add(InfDTOFactory.createInfDocumentTypesDTO(documentTypes));
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
            Query query = EM().createNamedQuery("InfDocumentTypesEntity.findNewId");
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

    public IInfDocumentTypesDTO add(IBasicDTO documentTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfDocumentTypesEntity documentTypesEntity = new InfDocumentTypesEntity();
            IInfDocumentTypesDTO documentTypesDTO = (IInfDocumentTypesDTO)documentTypesDTO1;
            Long newID = findNewId();
            IInfDocumentTypesEntityKey ent = new InfDocumentTypesEntityKey(newID);
            documentTypesEntity.setDoctypeCode(ent.getDoctypeCode());
            documentTypesEntity.setDoctypeName(documentTypesDTO.getName());
            documentTypesEntity.setCreatedBy(documentTypesDTO.getCreatedBy());
            documentTypesEntity.setCreatedDate(documentTypesDTO.getCreatedDate());
            documentTypesEntity.setAuditStatus(documentTypesDTO.getAuditStatus());
            documentTypesEntity.setTabrecSerial(documentTypesDTO.getTabrecSerial());
            doAdd(documentTypesEntity);
            // Set PK after creation
            documentTypesDTO.setCode(ent);
            return documentTypesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO documentTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfDocumentTypesDTO documentTypesDTO = (IInfDocumentTypesDTO)documentTypesDTO1;
            InfDocumentTypesEntity documentTypesEntity =
                EM().find(InfDocumentTypesEntity.class, (IInfDocumentTypesEntityKey)documentTypesDTO.getCode());
            if (documentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            documentTypesEntity.setDoctypeCode(((IInfDocumentTypesEntityKey)documentTypesDTO.getCode()).getDoctypeCode());
            documentTypesEntity.setDoctypeName(documentTypesDTO.getName());
            documentTypesEntity.setCreatedBy(documentTypesDTO.getCreatedBy());
            documentTypesEntity.setCreatedDate(documentTypesDTO.getCreatedDate());
            documentTypesEntity.setLastUpdatedBy(documentTypesDTO.getLastUpdatedBy());
            documentTypesEntity.setLastUpdatedDate(documentTypesDTO.getLastUpdatedDate());
            documentTypesEntity.setAuditStatus(documentTypesDTO.getAuditStatus());
            documentTypesEntity.setTabrecSerial(documentTypesDTO.getTabrecSerial());
            doUpdate(documentTypesEntity);
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

    public Boolean remove(IBasicDTO documentTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfDocumentTypesDTO documentTypesDTO = (IInfDocumentTypesDTO)documentTypesDTO1;
            InfDocumentTypesEntity documentTypesEntity =
                EM().find(InfDocumentTypesEntity.class, (IInfDocumentTypesEntityKey)documentTypesDTO.getCode());
            if (documentTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(documentTypesEntity);
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

            StringBuffer query = new StringBuffer("select o from InfDocumentTypesEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.doctypeName", name));

            List<InfDocumentTypesEntity> list =
                EM().createQuery(query.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            for (InfDocumentTypesEntity hireTypes : list) {
                arrayList.add(InfDTOFactory.createInfDocumentTypesDTO(hireTypes));
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
            list.add(getById(InfEntityKeyFactory.createInfDocumentTypesEntityKey((Long)code)));
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
            return EM().createNamedQuery("InfDocumentTypesEntity.getCodeName").setHint("toplink.refresh",
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
            List<InfDocumentTypesEntity> list =
                EM().createNamedQuery("InfDocumentTypesEntity.getByName").setParameter("name",
                                                                                       name).setHint("toplink.refresh",
                                                                                                     "true").getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            return InfDTOFactory.createInfDocumentTypesDTO(list.get(0));

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public boolean duplicatedDocumentType(String doctypeName, Long doctypeCode) throws DataBaseException,
                                                                                       SharedApplicationException {


        StringBuilder queryStr = new StringBuilder("SELECT count(*) ");
        queryStr.append("FROM INF_DOCUMENT_TYPES D WHERE ");
        queryStr.append(getNativeSqlSimilarCharsCondition("D.DOCTYPE_NAME", doctypeName));
        // queryStr.append("WHERE D.DOCTYPE_NAME = '" + doctypeName + "'");
        if (doctypeCode != null) {
            queryStr.append("AND D.DOCTYPE_CODE != " + doctypeCode);
        }

        Query query = EM().createNativeQuery(queryStr.toString());
        Vector resultVector = (Vector)query.getSingleResult();
        int res = Integer.parseInt(resultVector.get(0).toString());
        if (res > 0) {
            return true;
        } else {
            return false;
        }


    }
}
