package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.ILanguagesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.ILanguagesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.LanguagesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class LanguagesDAO extends InfBaseDAO {
    public LanguagesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new LanguagesDAO();
    }

    public List<ILanguagesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<LanguagesEntity> list =
                EM().createNamedQuery("LanguagesEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (LanguagesEntity languages : list) {
                arrayList.add(InfDTOFactory.createLanguagesDTO(languages));
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

    public ILanguagesDTO add(IBasicDTO languagesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            LanguagesEntity languagesEntity = new LanguagesEntity();
            ILanguagesDTO languagesDTO = (ILanguagesDTO)languagesDTO1;
            languagesDTO.setCode(InfEntityKeyFactory.createLanguagesEntityKey(findNewId()));
            languagesEntity.setLanguageCode(((ILanguagesEntityKey)languagesDTO.getCode()).getLanguageCode());
            languagesEntity.setLanguageName(languagesDTO.getName());
            languagesEntity.setCreatedBy(languagesDTO.getCreatedBy());
            languagesEntity.setCreatedDate(languagesDTO.getCreatedDate());
            languagesEntity.setAuditStatus(languagesDTO.getAuditStatus());
            languagesEntity.setTabrecSerial(languagesDTO.getTabrecSerial());
            doAdd(languagesEntity);
            return languagesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO languagesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ILanguagesDTO languagesDTO = (ILanguagesDTO)languagesDTO1;
            LanguagesEntity languagesEntity = EM().find(LanguagesEntity.class, languagesDTO.getCode());
            languagesEntity.setLanguageCode(((ILanguagesEntityKey)languagesDTO.getCode()).getLanguageCode());
            languagesEntity.setLanguageName(languagesDTO.getName());
            languagesEntity.setCreatedBy(languagesDTO.getCreatedBy());
            languagesEntity.setCreatedDate(languagesDTO.getCreatedDate());
            languagesEntity.setLastUpdatedBy(languagesDTO.getLastUpdatedBy());
            languagesEntity.setLastUpdatedDate(languagesDTO.getLastUpdatedDate());
            languagesEntity.setAuditStatus(languagesDTO.getAuditStatus());
            languagesEntity.setTabrecSerial(languagesDTO.getTabrecSerial());
            doUpdate(languagesEntity);
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
     * Remove an Existing LanguagesEntity * @param languagesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO languagesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            ILanguagesDTO languagesDTO = (ILanguagesDTO)languagesDTO1;
            LanguagesEntity languagesEntity = EM().find(LanguagesEntity.class, languagesDTO.getCode());
            if (languagesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(languagesEntity);
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
     * Get LanguagesEntity By Primary Key * @param id
     * @return ILanguagesDTO
     */
    public ILanguagesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            ILanguagesEntityKey id = (ILanguagesEntityKey)id1;
            LanguagesEntity languagesEntity = EM().find(LanguagesEntity.class, id);
            if (languagesEntity == null) {
                throw new ItemNotFoundException();
            }
            ILanguagesDTO languagesDTO = InfDTOFactory.createLanguagesDTO(languagesEntity);
            return languagesDTO;
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
            Long id = (Long)EM().createNamedQuery("LanguagesEntity.findNewId").getSingleResult();
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
     * get list of Languages with code and name ordered by name * @return List of Languages
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("LanguagesEntity.getCodeName").getResultList();
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
            List<LanguagesEntity> list =
                EM().createNamedQuery("LanguagesEntity.searchByCode").setParameter("languageCode",
                                                                                   code).getResultList();
            for (LanguagesEntity entity : list) {
                arrayList.add(InfDTOFactory.createLanguagesDTO(entity));
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
            StringBuilder ejbQL = new StringBuilder("select o from LanguagesEntity   o where o.languageName like '%");
            ejbQL.append(searchName);
            ejbQL.append("%' order by o.languageName");
            List<LanguagesEntity> list =
                EM().createQuery(ejbQL.toString()).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            for (LanguagesEntity entity : list) {
                arrayList.add(InfDTOFactory.createLanguagesDTO(entity));
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

   
    @Override
    public IBasicDTO getByName(String name) throws DataBaseException, SharedApplicationException {
        
        try {
            List list =
                EM().createNamedQuery("LanguagesEntity.getByName").setParameter("name", name).setHint("toplink.refresh",
                                                                                                         "true").getResultList();
            if (list.size() > 0) {
                return (InfDTOFactory.createLanguagesDTO((LanguagesEntity)list.get(0)));
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
