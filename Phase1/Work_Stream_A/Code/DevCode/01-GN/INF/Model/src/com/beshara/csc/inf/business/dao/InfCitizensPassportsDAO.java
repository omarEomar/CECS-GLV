package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfCitizensPassportsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfCitizensPassportsEntityKey;
import com.beshara.csc.inf.business.entity.InfCitizensPassportsEntity;
import com.beshara.csc.inf.business.entity.InfCitizensPassportsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class InfCitizensPassportsDAO extends InfBaseDAO {
    public InfCitizensPassportsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new InfCitizensPassportsDAO();
    }

    /**<code>select o from InfCitizensPassportsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IInfCitizensPassportsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfCitizensPassportsEntity> list =
                EM().createNamedQuery("InfCitizensPassportsEntity.findAll").setHint("toplink.refresh",
                                                                                    "true").getResultList();
            for (InfCitizensPassportsEntity infCitizensPassports : list) {
                arrayList.add(InfDTOFactory.createInfCitizensPassportsDTO(infCitizensPassports));
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
            Long id = (Long)EM().createNamedQuery("InfCitizensPassportsEntity.findNewId").getSingleResult();
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
     * Create a New InfCitizensPassportsEntity * @param infCitizensPassportsDTO1
     * @return IInfCitizensPassportsDTO
     */
    @Override
    public IInfCitizensPassportsDTO add(IBasicDTO infCitizensPassportsDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            InfCitizensPassportsEntity infCitizensPassportsEntity = new InfCitizensPassportsEntity();
            Long code = findNewId();
            IInfCitizensPassportsDTO infCitizensPassportsDTO = (IInfCitizensPassportsDTO)infCitizensPassportsDTO1;
            infCitizensPassportsEntity.setSerialNo(code);
            infCitizensPassportsEntity.setCivilId(infCitizensPassportsDTO.getCivilId());
            infCitizensPassportsEntity.setPassportNo(infCitizensPassportsDTO.getPassportNo());
            infCitizensPassportsEntity.setIssueCountry(infCitizensPassportsDTO.getIssueCountry());
            infCitizensPassportsEntity.setIssueDate(infCitizensPassportsDTO.getIssueDate());
            infCitizensPassportsEntity.setExpDate(infCitizensPassportsDTO.getExpDate());

            doAdd(infCitizensPassportsEntity);
            infCitizensPassportsDTO.setCode(new InfCitizensPassportsEntityKey(code));
            return infCitizensPassportsDTO;
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
     * Update an Existing InfCitizensPassportsEntity * @param infCitizensPassportsDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO infCitizensPassportsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfCitizensPassportsDTO infCitizensPassportsDTO = (IInfCitizensPassportsDTO)infCitizensPassportsDTO1;
            InfCitizensPassportsEntity infCitizensPassportsEntity =
                EM().find(InfCitizensPassportsEntity.class, (IInfCitizensPassportsEntityKey)infCitizensPassportsDTO.getCode());
            infCitizensPassportsEntity.setSerialNo(((IInfCitizensPassportsEntityKey)infCitizensPassportsDTO.getCode()).getSerialNo());
            infCitizensPassportsEntity.setCivilId(infCitizensPassportsDTO.getCivilId());
            infCitizensPassportsEntity.setPassportNo(infCitizensPassportsDTO.getPassportNo());
            infCitizensPassportsEntity.setIssueCountry(infCitizensPassportsDTO.getIssueCountry());
            infCitizensPassportsEntity.setIssueDate(infCitizensPassportsDTO.getIssueDate());
            infCitizensPassportsEntity.setExpDate(infCitizensPassportsDTO.getExpDate());
            doUpdate(infCitizensPassportsEntity);
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
     * Remove an Existing InfCitizensPassportsEntity * @param infCitizensPassportsDTO1
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO infCitizensPassportsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfCitizensPassportsDTO infCitizensPassportsDTO = (IInfCitizensPassportsDTO)infCitizensPassportsDTO1;
            InfCitizensPassportsEntity infCitizensPassportsEntity =
                EM().find(InfCitizensPassportsEntity.class, (IInfCitizensPassportsEntityKey)infCitizensPassportsDTO.getCode());
            if (infCitizensPassportsEntity == null) {
                throw new FinderException();
            }
            doRemove(infCitizensPassportsEntity);
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
     * Get InfCitizensPassportsEntity By Primary Key * @param id1
     * @return IInfCitizensPassportsDTO
     */
    @Override
    public IInfCitizensPassportsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfCitizensPassportsEntityKey id = (IInfCitizensPassportsEntityKey)id1;
            InfCitizensPassportsEntity infCitizensPassportsEntity = EM().find(InfCitizensPassportsEntity.class, id);
            if (infCitizensPassportsEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfCitizensPassportsDTO infCitizensPassportsDTO =
                InfDTOFactory.createInfCitizensPassportsDTO(infCitizensPassportsEntity);
            return infCitizensPassportsDTO;

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
     * Search for InfCitizensPassportsEntity * <br> * @return List
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }


    /**
     * Get InfCitizensPassportsEntity By CivilID * @param civilID
     * @return List of IBasicDTO
     * @throw RemoteException
     * @throw FinderException
     */
    public List<IBasicDTO> getByCivilID(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            Query query = EM().createNamedQuery("InfCitizensPassportsEntity.getByCivilID");
            query.setParameter("civilId", civilID);
            List<InfCitizensPassportsEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (InfCitizensPassportsEntity infCitizensPassports : list) {
                arrayList.add(InfDTOFactory.createInfCitizensPassportsDTO(infCitizensPassports));
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

    public IInfCitizensPassportsDTO getByPassportNo(Long civilID, String passportNo) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            IInfCitizensPassportsDTO dto = null;
            Query query = EM().createNamedQuery("InfCitizensPassportsEntity.getByPassportNo");
            query.setParameter("passportNo", passportNo);
            query.setParameter("civilId", civilID);
            InfCitizensPassportsEntity entity = (InfCitizensPassportsEntity)query.getSingleResult();
            if (entity == null) {
                throw new NoResultException();
            }
            dto = InfDTOFactory.createInfCitizensPassportsDTO(entity);
            return dto;

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
