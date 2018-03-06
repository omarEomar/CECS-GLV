package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IFieldValueDTO;
import com.beshara.csc.inf.business.dto.IFieldsDTO;
import com.beshara.csc.inf.business.dto.IRelatedFieldsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.FieldTypesEntity;
import com.beshara.csc.inf.business.entity.FieldsEntity;
import com.beshara.csc.inf.business.entity.IFieldsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class FieldsDAO extends InfBaseDAO {
    public FieldsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new FieldsDAO();
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<FieldsEntity> list =
                EM().createNamedQuery("FieldsEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list != null && list.size() == 0) {
                throw new NoResultException();
            }
            for (FieldsEntity fields : list) {
                arrayList.add(InfDTOFactory.createFieldsDTO(fields));
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
     * Create a New FieldsEntity * @param fieldsDTO
     * @return IFieldsDTO
     * @throws RemoteException
     */
    public IBasicDTO add(IBasicDTO fieldsDTO1) throws DataBaseException,
                                                      SharedApplicationException { // FieldTypesEntity fldentity=new FieldTypesEntity ( ) ;
        // fldentity.setFldtypeCode ( ( ( IFieldTypesEntityKey ) fieldsDTO.getFieldTypesDTO ( ) .getCode ( ) ) .getFldtypeCode ( ) ) ;
        // fieldsEntity.setFieldTypesEntity ( fldentity ) ;
        try {
            IFieldsDTO fieldsDTO = (IFieldsDTO)fieldsDTO1;
            FieldsEntity fieldsEntity = new FieldsEntity();
            fieldsDTO.setCode(InfEntityKeyFactory.createFieldsEntityKey(findNewId()));
            fieldsEntity.setFldCode(((IFieldsEntityKey)fieldsDTO.getCode()).getFldCode());
            fieldsEntity.setFldName(fieldsDTO.getName());
            fieldsEntity.setFldDesc(fieldsDTO.getFldDesc());
            if (fieldsDTO.getFieldTypesDTO() != null) {
                FieldTypesEntity fldentity = EM().find(FieldTypesEntity.class, fieldsDTO.getFieldTypesDTO().getCode());
                if (fldentity != null) {
                    fieldsEntity.setFieldTypesEntity(fldentity);
                }
            }
            fieldsEntity.setDisplayedType(fieldsDTO.getDisplayedType());
            fieldsEntity.setSqlStatement(fieldsDTO.getSqlStatement());
            fieldsEntity.setCreatedBy(fieldsDTO.getCreatedBy());
            fieldsEntity.setCreatedDate(fieldsDTO.getCreatedDate());
            fieldsEntity.setLastUpdatedBy(fieldsDTO.getLastUpdatedBy());
            fieldsEntity.setLastUpdatedDate(fieldsDTO.getLastUpdatedDate());
            doAdd(fieldsEntity);
            return fieldsDTO;
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
     * Update an Existing FieldsEntity * @param fieldsDTO
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean update(IBasicDTO fieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IFieldsDTO fieldsDTO = (IFieldsDTO)fieldsDTO1;
            FieldsEntity fieldsEntity = EM().find(FieldsEntity.class, fieldsDTO.getCode());
            fieldsEntity.setFldName(fieldsDTO.getName());
            fieldsEntity.setFldDesc(fieldsDTO.getFldDesc());
            fieldsEntity.setSqlStatement(fieldsDTO.getSqlStatement());
            fieldsEntity.setCreatedBy(fieldsDTO.getCreatedBy());
            fieldsEntity.setCreatedDate(fieldsDTO.getCreatedDate());
            fieldsEntity.setLastUpdatedBy(fieldsDTO.getLastUpdatedBy());
            fieldsEntity.setLastUpdatedDate(fieldsDTO.getLastUpdatedDate());
            if (fieldsDTO.getFieldTypesDTO() != null) {
                FieldTypesEntity fldentity = EM().find(FieldTypesEntity.class, fieldsDTO.getFieldTypesDTO().getCode());
                if (fldentity != null) {
                    fieldsEntity.setFieldTypesEntity(fldentity);
                } else {
                    throw new ItemNotFoundException();
                }
            }
            doUpdate(fieldsEntity);
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
     * Remove an Existing FieldsEntity * @param fieldsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    public Boolean remove(IBasicDTO fieldsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IFieldsDTO fieldsDTO = (IFieldsDTO)fieldsDTO1;
            FieldsEntity fieldsEntity = EM().find(FieldsEntity.class, fieldsDTO.getCode());
            if (fieldsEntity == null) {
                throw new FinderException();
            }
            doRemove(fieldsEntity);
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

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("FieldsEntity.findNewId").getSingleResult();
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
     * Get FieldsEntity By Primary Key * @param id
     * @return IFieldsDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            FieldsEntity fieldsEntity = EM().find(FieldsEntity.class, id);
            if (fieldsEntity == null) {
                throw new ItemNotFoundException();
            }
            IFieldsDTO fieldsDTO = InfDTOFactory.createFieldsDTO(fieldsEntity);
            if (fieldsEntity.getRelatedFieldsEntityList() != null) {
                List<IRelatedFieldsDTO> relatedFieldsList = new ArrayList<IRelatedFieldsDTO>();
                for (RelatedFieldsEntity relatedEntity : fieldsEntity.getRelatedFieldsEntityList()) {
                    relatedFieldsList.add(InfDTOFactory.createRelatedFieldsDTO(relatedEntity));
                }
                fieldsDTO.setRelatedFieldsDTOList(relatedFieldsList);
            }
            return fieldsDTO;
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
            return EM().createNamedQuery("FieldsEntity.findCodeName").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**<code>select o from FieldsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<FieldsEntity> list =
                EM().createNamedQuery("FieldsEntity.findName").setParameter("name", name).getResultList();
            for (FieldsEntity fields : list) {
                arrayList.add(InfDTOFactory.createFieldsDTO(fields));
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
            list.add(getById(InfEntityKeyFactory.createFieldsEntityKey((Long)code)));
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

    public List<IBasicDTO> getRelatedFields(IEntityKey fieldCode) throws DataBaseException,
                                                                         SharedApplicationException {
        List<IFieldsDTO> returnedList = new ArrayList<IFieldsDTO>();
        List<FieldsEntity> list =
            EM().createNamedQuery("FieldsEntity.getRelatedFields").setParameter("fieldCode", ((IFieldsEntityKey)fieldCode).getFldCode()).getResultList();
        if (list != null) {
            for (FieldsEntity entity : list) {
                returnedList.add(InfDTOFactory.createFieldsDTO(entity));
            }
        }
        return (List)returnedList;
    }

    public String getSqlQueryValue(List params, String funcName) throws DataBaseException, SharedApplicationException {
        try {
            Query qry = EM().createNativeQuery("select " + funcName + " from dual");
            for (int i = 0; i < params.size(); i++) {
                qry.setParameter(i + 1, params.get(i));
            }
            List<List> list = qry.getResultList();
            if (list != null) {
                String sqlQuery = (String)list.get(0).get(0);
                return sqlQuery;
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

    public List<IFieldValueDTO> calculateFieldValue(String nativeQuery, IFieldsDTO fieldDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            Query qry = EM().createNativeQuery(nativeQuery);
            List<IFieldValueDTO> results = new ArrayList<IFieldValueDTO>();
            List list = qry.getResultList();
            if (list != null) {
                Iterator it = list.iterator();
                IFieldValueDTO fieldValueDTO;
                while (it.hasNext()) {
                    List result = (List)it.next();
                    fieldValueDTO = InfDTOFactory.createFieldValueDTO();
                    fieldValueDTO.setCode(result.get(0).toString());
                    if (result.get(1) != null)
                        fieldValueDTO.setName(result.get(1).toString());
                    else
                        fieldValueDTO.setName("");
                    if (result.size() > 2 && result.get(2) != null) {
                        fieldValueDTO.setParentCode(result.get(2).toString());
                    }
                    if (result.size() > 3 && result.get(3) != null) {
                        fieldValueDTO.setLeafFlag(Long.valueOf(result.get(3).toString()));
                    }
                    if (result.size() > 4 && result.get(4) != null) {
                        fieldValueDTO.setTreeLevel(Long.valueOf(result.get(4).toString()));
                    }
                    results.add(fieldValueDTO);
                }
            }
            return results;
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
