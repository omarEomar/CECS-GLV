package com.beshara.csc.gn.map.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.map.business.dto.IMappedDataDTO;
import com.beshara.csc.gn.map.business.entity.DataEntity;
import com.beshara.csc.gn.map.business.entity.IMappedDataEntityKey;
import com.beshara.csc.gn.map.business.entity.IObjectTypesEntityKey;
import com.beshara.csc.gn.map.business.entity.ISocietiesEntityKey;
import com.beshara.csc.gn.map.business.entity.MapEntityKeyFactory;
import com.beshara.csc.gn.map.business.entity.MappedDataEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class MappedDataDAO extends MapBaseDAO {
    public MappedDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new MappedDataDAO();
    }


    /**
     * get all MappedDataDTOs * <br> * <code>select o from MappedDataEntity IoI<I/IcIoIdIeI>I.I * @return List IMappedDataDTO
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<MappedDataEntity> list =
                EM().createNamedQuery("MappedDataEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (MappedDataEntity mappedData : list) {
                arrayList.add(MapEntityConverter.getMappedDataDTO(mappedData));
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
            Long id = (Long)EM().createNamedQuery("MappedDataEntity.findNewId").getSingleResult();
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
     * create a MapEntityConverter.getMappedDataDTO * @param mappedDataDTO the dto to insert
     * @return IMappedDataDTO the inserted dto with generated code
     * @throws RemoteException
     */
    @Override
    public IBasicDTO add(IBasicDTO mappedDataDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            IMappedDataDTO mappedDataDTO = (IMappedDataDTO)mappedDataDTOParam;
            MappedDataEntity mappedDataEntity = new MappedDataEntity();
            DataEntity dataEntity1 =
                EM().find(DataEntity.class, MapEntityKeyFactory.createDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype1Code().getCode()).getObjtypeCode(),
                                                                                    ((ISocietiesEntityKey)mappedDataDTO.getSoc1Code().getCode()).getSocCode()));
            DataEntity dataEntity2 =
                EM().find(DataEntity.class, MapEntityKeyFactory.createDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype2Code().getCode()).getObjtypeCode(),
                                                                                    ((ISocietiesEntityKey)mappedDataDTO.getSoc2Code().getCode()).getSocCode()));
            if (dataEntity1 == null || dataEntity2 == null) {
                throw new ItemNotFoundException();
            }
            mappedDataEntity.setDataEntity(dataEntity1);
            mappedDataEntity.setSoc1Value(mappedDataDTO.getSoc1Value());
            mappedDataEntity.setDataEntity1(dataEntity2);
            mappedDataEntity.setSoc2Value(mappedDataDTO.getSoc2Value());
            mappedDataEntity.setMapStatus(mappedDataDTO.getMapStatus());
            MappedDataEntity tempEntity =
                EM().find(MappedDataEntity.class, MapEntityKeyFactory.createMappedDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype1Code().getCode()).getObjtypeCode(),
                                                                                                ((IObjectTypesEntityKey)mappedDataDTO.getObjtype2Code().getCode()).getObjtypeCode(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc1Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc1Value(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc2Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc2Value()));
            if (tempEntity != null)
                EM().createNamedQuery("MappedDataEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            doAdd(mappedDataEntity);
            return mappedDataDTO;
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
     * update an existing mappedDataDTO * @param mappedDataDTO the dto to update
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public Boolean update(IBasicDTO mappedDataDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            IMappedDataDTO mappedDataDTO = (IMappedDataDTO)mappedDataDTOParam;
            MappedDataEntity mappedDataEntity =
                EM().find(MappedDataEntity.class, MapEntityKeyFactory.createMappedDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype1Code().getCode()).getObjtypeCode(),
                                                                                                ((IObjectTypesEntityKey)mappedDataDTO.getObjtype2Code().getCode()).getObjtypeCode(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc1Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc1Value(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc2Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc2Value()));
            DataEntity dataEntity1 =
                EM().find(DataEntity.class, MapEntityKeyFactory.createDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype1Code().getCode()).getObjtypeCode(),
                                                                                    ((ISocietiesEntityKey)mappedDataDTO.getSoc1Code().getCode()).getSocCode()));
            DataEntity dataEntity2 =
                EM().find(DataEntity.class, MapEntityKeyFactory.createDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype2Code().getCode()).getObjtypeCode(),
                                                                                    ((ISocietiesEntityKey)mappedDataDTO.getSoc2Code().getCode()).getSocCode()));
            if (mappedDataEntity == null || dataEntity1 == null || dataEntity2 == null) {
                throw new ItemNotFoundException();
            }
            mappedDataEntity.setDataEntity(dataEntity1);
            mappedDataEntity.setSoc1Value(mappedDataDTO.getSoc1Value());
            mappedDataEntity.setDataEntity1(dataEntity2);
            mappedDataEntity.setSoc2Value(mappedDataDTO.getSoc2Value());
            mappedDataEntity.setMapStatus(mappedDataDTO.getMapStatus());
            doUpdate(mappedDataEntity);
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
     * Remove an existing dto * @param mappedDataDTO the dto to be removed
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public Boolean remove(IBasicDTO mappedDataDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            IMappedDataDTO mappedDataDTO = (IMappedDataDTO)mappedDataDTOParam;
            MappedDataEntity mappedDataEntity =
                EM().find(MappedDataEntity.class, MapEntityKeyFactory.createMappedDataEntityKey(((IObjectTypesEntityKey)mappedDataDTO.getObjtype1Code().getCode()).getObjtypeCode(),
                                                                                                ((IObjectTypesEntityKey)mappedDataDTO.getObjtype2Code().getCode()).getObjtypeCode(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc1Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc1Value(),
                                                                                                ((ISocietiesEntityKey)mappedDataDTO.getSoc2Code().getCode()).getSocCode(),
                                                                                                mappedDataDTO.getSoc2Value()));
            if (mappedDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(mappedDataEntity);
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
     * Get IMappedDataDTO with specific id * @param id the Id for IMappedDataDTO to retrieve
     * @return IMappedDataDTO
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public IMappedDataDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            MappedDataEntity mappedDataEntity = EM().find(MappedDataEntity.class, (IMappedDataEntityKey)id);
            if (mappedDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IMappedDataDTO mappedDataDTO = MapEntityConverter.getMappedDataDTO(mappedDataEntity);
            return mappedDataDTO;
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
     * get list of IMappedDataDTO that maps specific MappedDTO * @param mappedDataDto the dto to search its mapped data
     * @return List list of mapped data with the given dto
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getMappedDataList(IBasicDTO mappedDataDTOParam) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            IMappedDataDTO mappedDataDto = (IMappedDataDTO)mappedDataDTOParam;
            ArrayList arrayList = new ArrayList();
            List<MappedDataEntity> list =
                EM().createNamedQuery("MappedDataEntity.getMappedDataListByValue").setParameter("objectCode1",
                                                                                                ((IObjectTypesEntityKey)mappedDataDto.getObjtype1Code().getCode()).getObjtypeCode()).setParameter("socCode1",
                                                                                                                                                                                                  ((ISocietiesEntityKey)mappedDataDto.getSoc1Code().getCode()).getSocCode()).setParameter("socValue1",
                                                                                                                                                                                                                                                                                          mappedDataDto.getSoc1Value()).setParameter("socCode2",
                                                                                                                                                                                                                                                                                                                                     ((ISocietiesEntityKey)mappedDataDto.getSoc2Code().getCode()).getSocCode()).getResultList();
            for (MappedDataEntity mappedData : list) {
                arrayList.add(MapEntityConverter.getMappedDataDTO(mappedData));
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

    public void flushData() throws DataBaseException, SharedApplicationException {
        EM().flush();
    }

    /**
     * check the existance of a specific MappedData in the database * @param mappedDataDto
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean checkMappedDataExist(IBasicDTO mappedDataDTOParam) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            IMappedDataDTO mappedDataDto = (IMappedDataDTO)mappedDataDTOParam;
            Query query = EM().createNamedQuery("MappedDataEntity.checkMappedDataExist");
            query.setParameter("objCode1",
                               ((IObjectTypesEntityKey)mappedDataDto.getObjtype1Code().getCode()).getObjtypeCode());
            query.setParameter("objCode2",
                               ((IObjectTypesEntityKey)mappedDataDto.getObjtype2Code().getCode()).getObjtypeCode());
            query.setParameter("socCode1", ((ISocietiesEntityKey)mappedDataDto.getSoc1Code().getCode()).getSocCode());
            query.setParameter("socCode2", ((ISocietiesEntityKey)mappedDataDto.getSoc2Code().getCode()).getSocCode());
            query.setParameter("socVal1", mappedDataDto.getSoc1Value());
            query.setParameter("socVal2", mappedDataDto.getSoc2Value());
            Long count = (Long)query.getSingleResult();
            if (count.intValue() > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
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

    public Boolean updateMappedDataList(List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                               SharedApplicationException {
        return Boolean.FALSE;
    }

    /**
     * check if specific value is mapped for One to One relation Validation
     * @param objtypeCode
     * @param socCode1
     * @param socCode2
     * @param socValue
     * @return
     */
    public Boolean isValueAlreadyMapped(Long objtypeCode, Long socCode1, Long socCode2,
                                        String socValue) throws DataBaseException, SharedApplicationException {
        try {
            List<MappedDataEntity> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2AndVal2").setParameter("objCode1",
                                                                                                       objtypeCode).setParameter("objCode2",
                                                                                                                                 objtypeCode).setParameter("socCode1",
                                                                                                                                                           socCode1).setParameter("socCode2",
                                                                                                                                                                                  socCode2).setParameter("socValue2",
                                                                                                                                                                                                         socValue).setHint("toplink.refresh",
                                                                                                                                                                                                                           true).getResultList();
            boolean _exist = (mappedDataList != null && mappedDataList.size() > 0) ? true : false;
            if (!_exist) {
                mappedDataList =
                        EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2AndVal1").setParameter("objCode1",
                                                                                                               objtypeCode).setParameter("objCode2",
                                                                                                                                         objtypeCode).setParameter("socCode1",
                                                                                                                                                                   socCode2).setParameter("socCode2",
                                                                                                                                                                                          socCode1).setParameter("socValue1",
                                                                                                                                                                                                                 socValue).setHint("toplink.refresh",
                                                                                                                                                                                                                                   true).getResultList();

                _exist = (mappedDataList != null && mappedDataList.size() > 0) ? true : false;
            }

            return _exist;
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
