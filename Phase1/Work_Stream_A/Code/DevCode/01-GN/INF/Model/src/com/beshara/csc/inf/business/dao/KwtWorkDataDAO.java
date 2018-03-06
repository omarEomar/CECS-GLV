package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.entity.ITrxTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.EmpUtils;
import com.beshara.csc.inf.business.dto.IKwtBasicWrkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.IKwtWorkDataEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntity;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntityKey;
import com.beshara.csc.inf.business.exception.LastChangeInSameEmpWrkDataException;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.BatchNotCompletedException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;

import oracle.jdbc.internal.OracleTypes;


public class KwtWorkDataDAO extends InfBaseDAO {
    public KwtWorkDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new KwtWorkDataDAO();
    }

    /**<code>select o from KwtWorkDataEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity));
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


    public List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.findAllResignationByCivilId").setParameter("civilId",
                                                                                                    civilId).setParameter("govFlag",
                                                                                                                          ISystemConstant.GOVERNMENT_FLAG).setHint("toplink.refresh",
                                                                                                                                                                   "true").getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity));
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

    public List<IBasicDTO> getAllForCRS(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.findAllResignationByCivilIdForCRS").setParameter("civilId",
                                                                                                          civilId).setParameter("endServiceConst",
                                                                                                                                new Long(200)).setHint("toplink.refresh",
                                                                                                                                                       "true").getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity));

                //System.out.println(kwtWorkDataEntity.getTrxTypesEntity().getTrxtypeCode());
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

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("KwtWorkDataEntity.findNewId").getSingleResult();
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

    @Override
    public IBasicDTO add(IBasicDTO KwtWorkDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwtWorkDataDTO kwtWorkDataDTO = (IKwtWorkDataDTO)KwtWorkDataDTO1;
            Long code = findNewId();
            KwtWorkDataEntity kwtWorkDataEntity = new KwtWorkDataEntity();
            kwtWorkDataEntity.setSerial(code);

            if (kwtWorkDataDTO.getKwtCitizensResidentsDTO() != null) {
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtWorkDataDTO.getKwtCitizensResidentsDTO().getCode());
                if (kwtCitizensResidentsEntity == null)
                    throw new ItemNotFoundException();
                kwtWorkDataEntity.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
            }
            kwtWorkDataEntity.setFromDate(kwtWorkDataDTO.getFromDate());
            kwtWorkDataEntity.setAllowNomAgain(kwtWorkDataDTO.getAllowNomAgain());

            if (kwtWorkDataDTO.getJobsDTO() != null) {
                IJobsEntityKey entityKey = (IJobsEntityKey)kwtWorkDataDTO.getJobsDTO().getCode();
                try {
                    kwtWorkDataEntity.setJobCode(entityKey.getJobCode());
                } catch (Exception e) {
                    kwtWorkDataEntity.setJobCode(null);
                }
            }

            if (kwtWorkDataDTO.getOtherJobsDTO() != null) {
                IJobsEntityKey entityKey = (IJobsEntityKey)kwtWorkDataDTO.getOtherJobsDTO().getCode();
                try {
                    kwtWorkDataEntity.setJobCodeOther(entityKey.getJobCode());
                } catch (Exception e) {
                    kwtWorkDataEntity.setJobCode(null);
                }
            }

            if (kwtWorkDataDTO.getMinistriesDTO() != null) {
                IMinistriesEntityKey entityKey = (IMinistriesEntityKey)kwtWorkDataDTO.getMinistriesDTO().getCode();
                kwtWorkDataEntity.setMinCode(entityKey.getMinCode());
            } else {
                kwtWorkDataEntity.setMinCode(null);
            }

            if (kwtWorkDataDTO.getWorkCentersDTO() != null) {
                try {
                    IWorkCentersEntityKey entityKey =
                        (IWorkCentersEntityKey)kwtWorkDataDTO.getWorkCentersDTO().getCode();
                    kwtWorkDataEntity.setWrkCode(entityKey.getWrkCode());
                    kwtWorkDataEntity.setWrkMinCode(entityKey.getMinCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                kwtWorkDataEntity.setWrkCode(null);
            }
            kwtWorkDataEntity.setUntilDate(kwtWorkDataDTO.getUntilDate());
            if (kwtWorkDataDTO.getFirstParent() == null)
                kwtWorkDataEntity.setFirstParent(code);
            else
                kwtWorkDataEntity.setFirstParent(kwtWorkDataDTO.getFirstParent());
            kwtWorkDataEntity.setTreeLevel(kwtWorkDataDTO.getTreeLevel());
            kwtWorkDataEntity.setLeafFlag(kwtWorkDataDTO.getLeafFlag());
            kwtWorkDataEntity.setActualExpDays(kwtWorkDataDTO.getActualExpDays());
            kwtWorkDataEntity.setActualExpMonths(kwtWorkDataDTO.getActualExpMonths());
            kwtWorkDataEntity.setActualExpYears(kwtWorkDataDTO.getActualExpYears());
            kwtWorkDataEntity.setPerFlag(kwtWorkDataDTO.getPerFlag());
            kwtWorkDataEntity.setPisFlag(kwtWorkDataDTO.getPisFlag());
            if (kwtWorkDataDTO.getTrxTypesDTO() != null && kwtWorkDataDTO.getTrxTypesDTO().getCode() != null) {
                Long trxTypesCode = ((ITrxTypesEntityKey)kwtWorkDataDTO.getTrxTypesDTO().getCode()).getTrxtypeCode();
                kwtWorkDataEntity.setTrxtypeCode(trxTypesCode);
            }

            kwtWorkDataEntity.setCulOfficeCode(kwtWorkDataDTO.getCulOfficeCode());
            if(kwtWorkDataDTO.getJobHistoryStatus()==null){
                kwtWorkDataEntity.setJobHistoryStatus(ISystemConstant.NON_ACTIVE_FLAG);
            }else{
                kwtWorkDataEntity.setJobHistoryStatus(kwtWorkDataDTO.getJobHistoryStatus()); 
            }

            doAdd(kwtWorkDataEntity);
            kwtWorkDataDTO.setCode(new KwtWorkDataEntityKey(code));
            kwtWorkDataDTO.setSerial(code);
            return kwtWorkDataDTO;
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
     * Add kwtWorkData treated with IKwtWorkDataTreeDTO
     * @param KwtWorkDataDTO1
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Kareem.sayed
     */
    public IBasicDTO addKwtWorkDataTreeDTO(IBasicDTO KwtWorkDataDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            IKwtWorkDataTreeDTO kwtWorkDataDTO = (IKwtWorkDataTreeDTO)KwtWorkDataDTO1;
            Long code = null;
            if (kwtWorkDataDTO.getCode() == null) {
                code = findNewId();
            } else {
                code = Long.valueOf(kwtWorkDataDTO.getCode().getKey());
            }
            KwtWorkDataEntity kwtWorkDataEntity = new KwtWorkDataEntity();
            kwtWorkDataEntity.setSerial(code);

            if (kwtWorkDataDTO.getKwtCitizensResidentsDTO() != null) {
                KwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                    EM().find(KwtCitizensResidentsEntity.class, (IKwtCitizensResidentsEntityKey)kwtWorkDataDTO.getKwtCitizensResidentsDTO().getCode());
                if (kwtCitizensResidentsEntity == null)
                    throw new ItemNotFoundException();
                kwtWorkDataEntity.setKwtCitizensResidentsEntity(kwtCitizensResidentsEntity);
            }
            kwtWorkDataEntity.setFromDate(kwtWorkDataDTO.getFromDate());
            kwtWorkDataEntity.setAllowNomAgain(kwtWorkDataDTO.getAllowNomAgain());

            if (kwtWorkDataDTO.getJobsDTO() != null) {
                IJobsEntityKey entityKey = (IJobsEntityKey)kwtWorkDataDTO.getJobsDTO().getCode();
                try {
                    kwtWorkDataEntity.setJobCode(entityKey.getJobCode());
                } catch (Exception e) {
                    kwtWorkDataEntity.setJobCode(null);
                }
            }

            if (kwtWorkDataDTO.getMinistriesDTO() != null) {
                IMinistriesEntityKey entityKey = (IMinistriesEntityKey)kwtWorkDataDTO.getMinistriesDTO().getCode();
                kwtWorkDataEntity.setMinCode(entityKey.getMinCode());
            } else {
                kwtWorkDataEntity.setMinCode(null);
            }

            if (kwtWorkDataDTO.getWorkCentersDTO() != null) {
                try {
                    IWorkCentersEntityKey entityKey =
                        (IWorkCentersEntityKey)kwtWorkDataDTO.getWorkCentersDTO().getCode();
                    kwtWorkDataEntity.setWrkCode(entityKey.getWrkCode());
                    kwtWorkDataEntity.setWrkMinCode(entityKey.getMinCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                kwtWorkDataEntity.setWrkCode(null);
            }
            kwtWorkDataEntity.setUntilDate(kwtWorkDataDTO.getUntilDate());
            if (kwtWorkDataDTO.getFirstParent() == null)
                kwtWorkDataEntity.setFirstParent(code);
            else
                kwtWorkDataEntity.setFirstParent(Long.valueOf(kwtWorkDataDTO.getFirstParent().getKey()));
            if (kwtWorkDataDTO.getTreeLevel() != null)
                kwtWorkDataEntity.setTreeLevel(kwtWorkDataDTO.getTreeLevel());
            else
                kwtWorkDataEntity.setTreeLevel(1L);
            if (kwtWorkDataDTO.getLeafFlag() != null)
                kwtWorkDataEntity.setLeafFlag(kwtWorkDataDTO.getLeafFlag());
            else
                kwtWorkDataEntity.setLeafFlag(1L);
            if (kwtWorkDataDTO.getActualExpDays() != null)
                kwtWorkDataEntity.setActualExpDays(kwtWorkDataDTO.getActualExpDays());
            else
                kwtWorkDataEntity.setActualExpDays(0L);
            if (kwtWorkDataDTO.getActualExpMonths() != null)
                kwtWorkDataEntity.setActualExpMonths(kwtWorkDataDTO.getActualExpMonths());
            else
                kwtWorkDataEntity.setActualExpMonths(0L);
            if (kwtWorkDataDTO.getActualExpYears() != null)
                kwtWorkDataEntity.setActualExpYears(kwtWorkDataDTO.getActualExpYears());
            else
                kwtWorkDataEntity.setActualExpYears(0L);
            if (kwtWorkDataDTO.getPerFlag() != null)
                kwtWorkDataEntity.setPerFlag(kwtWorkDataDTO.getPerFlag());
            else
                kwtWorkDataEntity.setPerFlag(1L);
            kwtWorkDataEntity.setPisFlag(kwtWorkDataDTO.getPisFlag());
            kwtWorkDataEntity.setExtraJob(kwtWorkDataDTO.getExtraJob());

            Long trxTypesCode = ((ITrxTypesEntityKey)kwtWorkDataDTO.getTrxTypesDTO().getCode()).getTrxtypeCode();
            kwtWorkDataEntity.setTrxtypeCode(trxTypesCode);
            
            if(kwtWorkDataDTO.getJobHistoryStatus()==null){
                kwtWorkDataEntity.setJobHistoryStatus(ISystemConstant.NON_ACTIVE_FLAG);
            }else{
                kwtWorkDataEntity.setJobHistoryStatus(kwtWorkDataDTO.getJobHistoryStatus()); 
            }
        
            doAdd(kwtWorkDataEntity);
            kwtWorkDataDTO.setCode(new KwtWorkDataEntityKey(code));
            kwtWorkDataDTO.setSerial(code);
            return kwtWorkDataDTO;
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
    public Boolean update(IBasicDTO KwtWorkDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IKwtWorkDataDTO kwtWorkDataDTO = (IKwtWorkDataDTO)KwtWorkDataDTO1;
            KwtWorkDataEntity kwtWorkDataEntity =
                EM().find(KwtWorkDataEntity.class, (IKwtWorkDataEntityKey)kwtWorkDataDTO.getCode());
            kwtWorkDataEntity.setAllowNomAgain(kwtWorkDataDTO.getAllowNomAgain());
            if (kwtWorkDataDTO.getJobsDTO() != null) {
                IJobsEntityKey entityKey = (IJobsEntityKey)kwtWorkDataDTO.getJobsDTO().getCode();
                try {
                    kwtWorkDataEntity.setJobCode(entityKey.getJobCode());
                } catch (Exception e) {
                    kwtWorkDataEntity.setJobCode(null);
                }
            }
            if (kwtWorkDataDTO.getMinistriesDTO() != null) {
                IMinistriesEntityKey entityKey = (IMinistriesEntityKey)kwtWorkDataDTO.getMinistriesDTO().getCode();
                kwtWorkDataEntity.setMinCode(entityKey.getMinCode());
            } else {
                kwtWorkDataEntity.setMinCode(null);
            }
            if(kwtWorkDataDTO.getFromDate() != null){
                kwtWorkDataEntity.setFromDate(kwtWorkDataDTO.getFromDate());
            }
            kwtWorkDataEntity.setUntilDate(kwtWorkDataDTO.getUntilDate());
            kwtWorkDataEntity.setFirstParent(kwtWorkDataDTO.getFirstParent());
            kwtWorkDataEntity.setTreeLevel(kwtWorkDataDTO.getTreeLevel());
            kwtWorkDataEntity.setLeafFlag(kwtWorkDataDTO.getLeafFlag());
            kwtWorkDataEntity.setActualExpDays(kwtWorkDataDTO.getActualExpDays());
            kwtWorkDataEntity.setActualExpMonths(kwtWorkDataDTO.getActualExpMonths());
            kwtWorkDataEntity.setActualExpYears(kwtWorkDataDTO.getActualExpYears());
            kwtWorkDataEntity.setPerFlag(kwtWorkDataDTO.getPerFlag());
            kwtWorkDataEntity.setPisFlag(kwtWorkDataDTO.getPisFlag());
            kwtWorkDataEntity.setExtraJob(kwtWorkDataDTO.getExtraJob());
            kwtWorkDataEntity.setCulOfficeCode(kwtWorkDataDTO.getCulOfficeCode());
            if(kwtWorkDataDTO.getJobHistoryStatus()==null){
                kwtWorkDataEntity.setJobHistoryStatus(ISystemConstant.NON_ACTIVE_FLAG);
            }else{
                kwtWorkDataEntity.setJobHistoryStatus(kwtWorkDataDTO.getJobHistoryStatus()); 
            }

            doUpdate(kwtWorkDataEntity);
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
     * * @param kwtWorkDataDTO
     * @return
     * @throws RemoteException
     * @throws XXXX
     */
    @Override
    public Boolean remove(IBasicDTO kwtWorkDataDTO) throws DataBaseException, SharedApplicationException {
        try {
            KwtWorkDataEntity kwtWorkDataEntity =
                EM().find(KwtWorkDataEntity.class, (IKwtWorkDataEntityKey)kwtWorkDataDTO.getCode());
            if (kwtWorkDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(kwtWorkDataEntity);
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
     * Get kwtWorkDataEntity By Primary Key * @param id
     * @return kwtWorkDataDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtWorkDataEntity kwtWorkDataEntity = EM().find(KwtWorkDataEntity.class, (IKwtWorkDataEntityKey)id);
            if (kwtWorkDataEntity == null) {
                throw new ItemNotFoundException();
            }
            return InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity);

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getTreeNodeById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            KwtWorkDataEntity kwtWorkDataEntity = EM().find(KwtWorkDataEntity.class, (IKwtWorkDataEntityKey)id);
            if (kwtWorkDataEntity == null) {
                throw new ItemNotFoundException();
            }
            return InfDTOFactory.createKwtWorkDataTreeDTO(kwtWorkDataEntity);
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
     * check for specific person that if he has allow nomination on resigned ministry or not * @param minsCode
     * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean checkResignedMinsAllowNomination(Long minsCode, Long civilId) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("KwtWorkDataEntity.checkResignedMinsAllowNomination");
            query.setParameter("minsCode", minsCode);
            query.setParameter("civilId", civilId);
            query.setParameter("notAllowNomAgain", ICRSConstant.NOT_ALLOW_NOM_AGAIN);
            Long count = (Long)query.getSingleResult();
            if (count == null || count.intValue() == 0)
                return Boolean.TRUE;
            return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long countNumOfExperiences(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("KwtWorkDataEntity.countNumOfExperiences");
            query.setParameter("civilId", civilId);

            return (Long)query.getSingleResult();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getByGovFlag(Long civilId, Long govFlag) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.findAllResignationByCivilId").setParameter("civilId",
                                                                                                    civilId).setParameter("govFlag",
                                                                                                                          govFlag).setHint("toplink.refresh",
                                                                                                                                           "true").getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity));
            }
            if (arrayList.size() == 0) {
                throw new NoResultException();
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

    public IBasicDTO getLastByCivilAndMinistry(Long civilId, Long minCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.getLastByCivilAndMinistry").setParameter("civilId",
                                                                                                  civilId).setParameter("minCode",
                                                                                                                        minCode).getResultList();
            if (list == null || list.size() == 0) {
                throw new ItemNotFoundException();
            }

            return InfDTOFactory.createKwtWorkDataDTO(list.get(0));
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getByCivilIdOrderByDate(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.getByCivilIdOrderByDate").setParameter("civilId",
                                                                                                civilId).getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataTreeDTO(kwtWorkDataEntity));
            }
            if (arrayList.size() == 0) {
                throw new NoResultException();
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

    public List<IBasicDTO> getByCivilIdOrderByDateForSCP(Long civilId) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.getByCivilIdOrderByDate").setParameter("civilId",
                                                                                                civilId).getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                IKwtWorkDataDTO dto =InfDTOFactory.createKwtWorkDataDTO(kwtWorkDataEntity); 
                java.sql.Date untilDate =
                    dto.getUntilDate() == null ? SharedUtils.getCurrentDate() : dto.getUntilDate();
                Long[] resultArr = getDiffDates(dto.getFromDate(), untilDate);
                dto.setServiceYears(Integer.parseInt(resultArr[0].toString()));
                dto.setServiceMonths(Integer.parseInt(resultArr[1].toString()));
                dto.setServiceDays(Integer.parseInt(resultArr[2].toString()));
                arrayList.add(dto);
            }
            if (arrayList.size() == 0) {
                throw new NoResultException();
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

    public Long[] getDiffDates(java.sql.Date startDate, java.sql.Date endDate) throws DataBaseException,
                                                                                      SharedApplicationException {
           Long[] countArr = null;
           if (startDate != null && endDate != null) {
              countArr=  new Long[3];
               Long daysNo = null;
               Long monthsNo = null;
               Long yearsNo = null;
               try {
                   System.out.println("getDiffDates startDate = " +
                                      ((startDate == null) ? " NULL " : startDate.toString()) + " , last = " +
                                      ((endDate == null) ? " NULL " : endDate.toString()));
                   try {
                       Query q = EM().createNativeQuery("SELECT 1 FROM dual");
                       q.setHint("toplink.refresh", "true");
                       q.getSingleResult();
                   } catch (Throwable e) {
                       e.printStackTrace();
                   }
                   final String queryStr = " { call HR_AOE_INTERFACE_PACK.GET_EXACT_DAYS_MONTHS_YEARS3 (?,?,?,?,?) } ";
                   Connection connection = getConnection(); //session.getAccessor().getConnection();
                   CallableStatement stm = null;
                   try {
                       stm = connection.prepareCall(queryStr);
                       stm.setDate(1, startDate);
                       stm.setDate(2, endDate);
                       stm.registerOutParameter(3, OracleTypes.NUMERIC);
                       stm.registerOutParameter(4, OracleTypes.NUMERIC);
                       stm.registerOutParameter(5, OracleTypes.NUMERIC);

                       stm.execute();

                       daysNo = stm.getLong(3);
                       monthsNo = stm.getLong(4);
                       yearsNo = stm.getLong(5);

                   } finally {
                       if (stm != null) {
                           try {
                               stm.close();
                           } catch (Throwable e) {
                               e.printStackTrace();
                           }
                       }
                   }
               } catch (Throwable e) {
                   e.printStackTrace();
               }

               System.out.println("getDiffDates yearsNo = " + yearsNo + " , monthsNo = " + monthsNo + " , daysNo = " +
                                  daysNo);
               countArr[0] = yearsNo;
               countArr[1] = monthsNo;
               countArr[2] = daysNo;
           }
           return countArr;
       }
    
    public List<IBasicDTO> getJobGroupping(Long civilId, Long minCode, Date fromDate,
                                           Date untilDate) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.getJobGroupping").setParameter("civilId",
                                                                                        civilId).setParameter("minCode",
                                                                                                              minCode).setParameter("fromDate",
                                                                                                                                    fromDate).setParameter("untilDate",
                                                                                                                                                           untilDate).getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataTreeDTO(kwtWorkDataEntity));
            }
            //            if (arrayList.size() == 0) {
            //                throw new NoResultException();
            //            }
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

    public List<IBasicDTO> getWorkCenterGroupping(Long civilId, Long minCode, String jobCode, Date fromDate,
                                                  Date untilDate) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            List<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            List<KwtWorkDataEntity> list =
                EM().createNamedQuery("KwtWorkDataEntity.getWorkCenterGroupping").setParameter("civilId",
                                                                                               civilId).setParameter("minCode",
                                                                                                                     minCode).setParameter("jobCode",
                                                                                                                                           jobCode).setParameter("fromDate",
                                                                                                                                                                 fromDate).setParameter("untilDate",
                                                                                                                                                                                        untilDate).getResultList();
            for (KwtWorkDataEntity kwtWorkDataEntity : list) {
                arrayList.add(InfDTOFactory.createKwtWorkDataTreeDTO(kwtWorkDataEntity));
            }
            //                if (arrayList.size() == 0) {
            //                    throw new NoResultException();
            //                }

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


    public Long getWorkCenterGrouppingCount(Long civilId, Long minCode, String jobCode, Date fromDate,
                                            Date untilDate) throws DataBaseException, SharedApplicationException {
        try {

            Query query =
                EM().createNamedQuery("KwtWorkDataEntity.getWorkCenterGrouppingCount").setParameter("civilId",
                                                                                                    civilId).setParameter("minCode",
                                                                                                                          minCode).setParameter("jobCode",
                                                                                                                                                jobCode).setParameter("fromDate",
                                                                                                                                                                      fromDate).setParameter("untilDate",
                                                                                                                                                                                             untilDate);
            Long count = (Long)query.getSingleResult();
            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public boolean checkAboutHireDate(IEmpCandidatesDTO empCandidateDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            StringBuilder sb = new StringBuilder("  SELECT COUNT(1) ");

            Long civilId = Long.valueOf(empCandidateDTO.getCitizensResidentsDTO().getCode().getKey());
            Date hireDate = empCandidateDTO.getHireDate();

            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            String shireDate = format.format(hireDate);

            sb.append(" FROM INF_KWT_WORK_DATA w");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(civilId);
            sb.append(" AND to_Date('" + shireDate + "','yyyy/MM/dd') <=(");
            sb.append(" select max(NVL(UNTIL_DATE,to_Date('" + shireDate + "','yyyy/MM/dd')))");
            sb.append(" FROM INF_KWT_WORK_DATA W1 WHERE W1.CIVIL_ID = W.CIVIL_ID )");
            String queryString = sb.toString();
            System.out.println(queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return true;
            }
            return false;
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
     * Get last record for Employee
     * @param civilId
     * @param trxCodList List of trxCode that used in query
     * @param included
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Kareem.Sayed
     */
    public IBasicDTO getLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                boolean included) throws DataBaseException,
                                                                         SharedApplicationException {
        StringBuilder ejbql = null;
        try {
            ejbql = new StringBuilder("select o from KwtWorkDataEntity o WHERE o.untilDate is null ");
            ejbql.append(" and o.kwtCitizensResidentsEntity.civilId='" + civilId + "'");
            if (trxCodList.size() > 0) {
                StringBuilder trxCodStr = new StringBuilder("");
                for (Long trxCode : trxCodList) {
                    trxCodStr.append(trxCode + ",");
                }
                if (included)
                    ejbql.append(" AND o.trxtypeCode IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) + ") ");
                else
                    ejbql.append(" AND o.trxtypeCode NOT IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) +
                                 ") ");
            }
            System.out.println(ejbql.toString());
            List<KwtWorkDataEntity> list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(list.get(0));
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
     * @author Black Horse [E.Saber]
     * @since 01/10/2015
     * @param civilId
     * @param trxCodList
     * @param included
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getAnyLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                   boolean included) throws DataBaseException,
                                                                            SharedApplicationException {
        StringBuilder ejbql = null;
        try {
            ejbql = new StringBuilder("select o from KwtWorkDataEntity o WHERE o.fromDate =");
            ejbql.append(" (select max(o1.fromDate) from KwtWorkDataEntity o1");
            ejbql.append(" where o.kwtCitizensResidentsEntity.civilId = o1.kwtCitizensResidentsEntity.civilId)");
            ejbql.append(" and o.kwtCitizensResidentsEntity.civilId='" + civilId + "'");
            if (trxCodList.size() > 0) {
                StringBuilder trxCodStr = new StringBuilder("");
                for (Long trxCode : trxCodList) {
                    trxCodStr.append(trxCode + ",");
                }
                if (included)
                    ejbql.append(" AND o.trxtypeCode IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) + ") ");
                else
                    ejbql.append(" AND o.trxtypeCode NOT IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) +
                                 ") ");
            }
            System.out.println(ejbql.toString());
            List<KwtWorkDataEntity> list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(list.get(0));
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
     * Get KWtWORKDATA reecord used for mov
     * @param realCivilId
     * @param trxCod
     * @param movingStartDate
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy M.abdelsabour
     */

    public IBasicDTO getKwtWorkDataForMov(Long realCivilId, Long ndbType, Date movingDate) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {

            Query query = EM().createNamedQuery("KwtWorkDataEntity.findKwtWorkDataForMov");
            query.setParameter("realCivilId", realCivilId);
            query.setParameter("ndbType", ndbType);
            query.setParameter("movingDate", movingDate);
            KwtWorkDataEntity entity = (KwtWorkDataEntity)query.getSingleResult();
            if (entity == null)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(entity);
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
     * check conflect in experiences
     * @param realCivilId
     * @param fromDate
     * @param untilDate
     * @param trxCodList
     * @param includeTrx
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Kareem.sayed
     */
    public Boolean checkExperiencesConflict(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                            List<Long> trxCodList, boolean includeTrx) throws DataBaseException,
                                                                                              SharedApplicationException {
        StringBuilder ejbql = null;
        try {
            ejbql = new StringBuilder("");
            ejbql.append(" SELECT COUNT(1) FROM INF_KWT_WORK_DATA W WHERE CIVIL_ID = " + realCivilId);
            if (trxCodList.size() > 0) {
                StringBuilder trxCodStr = new StringBuilder("");
                for (Long trxCode : trxCodList) {
                    trxCodStr.append(trxCode + ",");
                }
                if (includeTrx)
                    ejbql.append(" AND W.TRXTYPE_CODE IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) + ") ");
                else
                    ejbql.append(" AND W.TRXTYPE_CODE NOT IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) +
                                 ") ");
            }
            ejbql.append(" AND (");
            if (untilDate == null) {
                ejbql.append(" (to_Date('" + fromDate + "','yyyy/MM/dd') <= UNTIL_DATE AND UNTIL_DATE is NOT NULL");
                ejbql.append(" OR( UNTIL_DATE is null ))");
            } else {
                ejbql.append(" ( to_Date('" + fromDate + "','yyyy/MM/dd') <= UNTIL_DATE AND to_Date('" + untilDate +
                             "','yyyy/MM/dd') >= FROM_DATE)");
                ejbql.append(" OR( to_Date('" + untilDate + "','yyyy/MM/dd') >= FROM_DATE AND UNTIL_DATE IS NULL) ");
            }
            ejbql.append(" )");

            System.out.println("KwtWorkDataDAO.checkExperiencesConflict==========" + ejbql.toString());

            Query query = EM().createNativeQuery(ejbql.toString());

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
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
     * get record data by realCivilId and max serial
     * @param realCivilId
     * @return dto
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy I.Omar
     */
    public IBasicDTO getByRealCivilIdAndMaxSerial(Long realCivilId) throws DataBaseException,
                                                                           SharedApplicationException {
        StringBuilder ejbql = null;
        try {
            ejbql = new StringBuilder("select o from KwtWorkDataEntity o WHERE o.serial =");
            ejbql.append(" (select max(k.serial) from KwtWorkDataEntity k");
            ejbql.append(" where k.kwtCitizensResidentsEntity.civilId = '" + realCivilId + "')");
            ejbql.append(" and o.kwtCitizensResidentsEntity.civilId='" + realCivilId + "'");

            System.out.println(ejbql.toString());
            List<KwtWorkDataEntity> list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(list.get(0));
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
     * Get KWtWORKDATA reecord used for mov
     * @param realCivilId
     * @param trxCod
     * @param movingStartDate
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy S.Abdelaziz
     */

    public IBasicDTO getKwtWorkDataForInternalMovAfterExc(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {

            Query query = EM().createNamedQuery("KwtWorkDataEntity.findKwtWorkDataForInternalMovAfterExc");
            query.setParameter("realCivilId", realCivilId);
            query.setParameter("movingDate", movingDate);

            KwtWorkDataEntity entity = (KwtWorkDataEntity)query.getSingleResult();
            if (entity == null)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(entity);
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
     * getReadyToUpdateCivilsForADC
     * @param realCivilIds
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Black Hourse[E.Saber]
     */

    public List<IBasicDTO> getReadyToUpdateListForADC(String realCivilIds) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" SELECT INF.CIVIL_ID AS R_CIVIL_ID,COUNT(1) as RECORDS ");
            queryString.append(" FROM INF_KWT_WORK_DATA INF JOIN HR_EMP_EMPLOYEES EMP ");
            queryString.append("        ON EMP.REAL_CIVIL_ID = INF.CIVIL_ID ");
            queryString.append(" WHERE EMP.MIN_CODE = INF.MIN_CODE ");
            queryString.append("    AND EMP.JOB_CODE = INF.JOB_CODE ");
            queryString.append("    AND INF.UNTIL_DATE IS NULL ");
            queryString.append("    AND INF.CIVIL_ID IN ( " + realCivilIds + " ) ");
            queryString.append("    AND EMP.ACTIVE_FLAG = " + ISystemConstant.ACTIVE_FLAG);
            queryString.append("    AND EMP.HIRSTATUS_CODE IN ( " + hireStatus + " ) ");
            queryString.append("    AND EMP.HIRSTATUS_CODE <> 13 ");
            queryString.append(" GROUP BY INF.CIVIL_ID ");
            queryString.append(" HAVING COUNT(1) = 1 ");
            System.out.println(queryString.toString());
            Query query = EM().createNativeQuery(queryString.toString());
            query.setHint("toplink.refresh", "true");
            List<Vector> list = query.getResultList();
            List<IBasicDTO> arryList = new ArrayList<IBasicDTO>();
            IEmployeeSearchDTO dto;
            for (Vector obj : list) {
                dto = new EmployeeSearchDTO();
                dto.setRealCivilId(Long.parseLong(obj.elementAt(0).toString()));
                arryList.add(dto);
            }
            return arryList;
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
     * updateJobForADC
     * @param basicDTOList
     * @param jobCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Black Hourse[E.Saber]
     */

    public Boolean updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                            SharedApplicationException {
        CallableStatement stm = null;
        try {
            Boolean validToUpdate = false;
            StringBuilder queryString = new StringBuilder("BEGIN ");
            for (IBasicDTO basicDTO : basicDTOList) {
                EmployeeSearchDTO employeeSearchDTO = (EmployeeSearchDTO)basicDTO;
                if (employeeSearchDTO.getActiveFlag().equals(1L)) {
                    queryString.append(" UPDATE INF_KWT_WORK_DATA ");
                    queryString.append(" SET JOB_CODE = " + jobCode + " ");
                    queryString.append(" WHERE UNTIL_DATE IS NULL");
                    queryString.append(" AND MIN_CODE = " + employeeSearchDTO.getMinistryCode().toString() + " ");
                    queryString.append(" AND JOB_CODE = " + employeeSearchDTO.getJobCode() + " ");
                    queryString.append(" AND CIVIL_ID = " + employeeSearchDTO.getRealCivilId().toString() + " ; ");
                    if (!validToUpdate)
                        validToUpdate = true;
                }
            }
            queryString.append(" END;");
            System.out.println(queryString.toString());
            if (validToUpdate) {
                Connection con = getConnectionForUpdate();
                stm = con.prepareCall(queryString.toString());
                stm.executeUpdate();
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } finally {
            BaseDAO.close(stm);
        }
    }


    /**
     * updateWorkCodeForADC
     * @param civilId
     * @param  workCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Black Hourse
     */

    public Boolean updateWorkCodeForADC(Long civilId, String workCode) throws DataBaseException,
                                                                              SharedApplicationException {
        CallableStatement stm = null;
        try {
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" UPDATE INF_KWT_WORK_DATA ");
            queryString.append(" SET WRK_CODE = '" + workCode + "' ");

            queryString.append(" WHERE UNTIL_DATE IS NULL");
            queryString.append(" AND CIVIL_ID = " + civilId + " ");
            queryString.append(" AND TRXTYPE_CODE NOT IN (80,90) ");

            System.out.println("updateWorkCodeForADC" + queryString.toString());

            Connection con = getConnectionForUpdate();
            stm = con.prepareCall(queryString.toString());
            stm.executeUpdate();

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

    public Boolean checkDeplicatedDataforWrkCode(Long civilId) throws DataBaseException, SharedApplicationException {

        boolean valid = true;
        try {
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" SELECT * ");
            queryString.append(" FROM  INF_KWT_WORK_DATA");
            queryString.append(" WHERE UNTIL_DATE IS NULL");
            queryString.append(" AND CIVIL_ID = " + civilId + " ");
            queryString.append(" AND TRXTYPE_CODE NOT IN (80,90) ");

            System.out.println("checkDeplicatedDataforWrkCode" + queryString.toString());
            Query query = EM().createNativeQuery(queryString.toString());
            query.setHint("toplink.refresh", "true");
            List<Vector> list = query.getResultList();
            if (list != null && list.size() == 1) {
                valid = true;
            } else {
                valid = false;
            }
            return valid;
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
     * updateWorkCodeForMov
     * @param realcivilId
     * @param  workCode
     * @return  Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy M.abdelsabour Black Hourse
     */

    public Boolean updateWorkCodeForMov(Long realCivil, String workCode,
                                        java.sql.Date movDate) throws DataBaseException, SharedApplicationException {
        CallableStatement stm = null;
        try {
            StringBuilder queryString = new StringBuilder("");
            queryString.append(" UPDATE INF_KWT_WORK_DATA ");
            queryString.append(" SET WRK_CODE = '" + workCode + "' ");
            queryString.append(" WHERE CIVIL_ID = " + realCivil + " ");
            queryString.append("AND FROM_DATE = to_date('" + movDate + "','yyyy/MM/dd')");
            queryString.append(" AND TRXTYPE_CODE = 80 ");
            System.out.println("updateWorkCodeForMov" + queryString.toString());

            Connection con = getConnectionForUpdate();
            stm = con.prepareCall(queryString.toString());
            stm.executeUpdate();

            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } finally {
            BaseDAO.close(stm);
        }

    }

    public List<IBasicDTO> getHistoricalScaleBgtProgram(Long civilId) throws DataBaseException,
                                                                             SharedApplicationException {
        StringBuilder query =
            new StringBuilder("SELECT  w.wrk_code, w.from_date, w.until_date, c.cat_name, m.min_name,\n" +
                "                wc.wrk_name, bgt.prg_name,w.SERIAL ,bgt.prg_code\n" +
                "           FROM inf_kwt_work_data w,\n" +
                "                nl_org_work_centers wc,\n" +
                "                hr_bgt_programs bgt,\n" +
                "                nl_org_ministries m,\n" +
                "                nl_org_cats c\n" +
                "          WHERE w.wrk_code = wc.wrk_code\n" +
                "            AND wc.prg_code = bgt.prg_code\n" +
                "            AND w.wrkmin_code = m.min_code\n" +
                "            AND m.cat_code = c.cat_code\n" +
                "            AND w.wrk_code IS NOT NULL\n" +
                "            AND w.civil_id = ");
        query.append(civilId);
        query.append(" ORDER BY w.from_date");
        System.out.println("View query >>>>>>>>>> " + query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list = q.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        Long beforeBgtPrgCode = 0L;
        int index = 0;
        for (Vector row : list) {
            Long pgtPrgCode = Long.valueOf(row.get(8).toString());
            IKwtWorkDataDTO kwtWorkDataDTO = InfDTOFactory.createKwtWorkDataDTO();
            kwtWorkDataDTO.setFromDate(new java.sql.Date(((Timestamp)row.get(1)).getTime()));
            if (row.get(2) != null)
                kwtWorkDataDTO.setUntilDate(new java.sql.Date(((Timestamp)row.get(2)).getTime()));
            kwtWorkDataDTO.setCatName(row.get(3).toString());
            kwtWorkDataDTO.setMinName(row.get(4).toString());
            kwtWorkDataDTO.setWrkName(row.get(5).toString());
            kwtWorkDataDTO.setPrgName(row.get(6).toString());
            IKwtWorkDataEntityKey ek =
                InfEntityKeyFactory.createKwtWorkDataEntityKey(((BigDecimal)row.get(7)).longValue());
            kwtWorkDataDTO.setCode(ek);
            if (beforeBgtPrgCode.equals(pgtPrgCode)) {
                IKwtWorkDataDTO lastKwtWorkDataDTO = (IKwtWorkDataDTO)arrayList.get(index - 1);
                lastKwtWorkDataDTO.setUntilDate(kwtWorkDataDTO.getUntilDate());
                arrayList.remove(index - 1);
                index--;
                arrayList.add(lastKwtWorkDataDTO);
                index++;
            } else {
                beforeBgtPrgCode = pgtPrgCode;
                arrayList.add(kwtWorkDataDTO);
                index++;
            }

        }
        return arrayList;
    }
    ////////////////////////////////////ESRV//////////////////////////////////////

    public String getFirstExperience(Long realCivilId) throws DataBaseException, SharedApplicationException {
        String firstExperDate = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            StringBuilder query =
                new StringBuilder("select min(DATEE.FROM_DATE) from INF_KWT_WORK_DATA datee where DATEE.CIVIL_ID= ");
            query.append(realCivilId);
            System.out.println("getFirstExperience >>>>>>>>>> " + query.toString());
            Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
            Vector v = (Vector)q.getSingleResult();
            if ((Date)v.get(0) != null) {
                firstExperDate = df.format((Date)v.get(0));
            }
            return firstExperDate;
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
     * update kwtWorkData treated with IKwtWorkDataTreeDTO
     * @param KwtWorkDataDTO1
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy M.abdelsabour
     */
    public Boolean updateKwtWorkDataTreeDTO(IBasicDTO KwtWorkDataDTO1) throws DataBaseException,
                                                                              SharedApplicationException {
        try {

            IKwtWorkDataTreeDTO kwtWorkDataDTO = (IKwtWorkDataTreeDTO)KwtWorkDataDTO1;
            KwtWorkDataEntity kwtWorkDataEntity =
                EM().find(KwtWorkDataEntity.class, (IKwtWorkDataEntityKey)kwtWorkDataDTO.getCode());


            kwtWorkDataEntity.setFromDate(kwtWorkDataDTO.getFromDate());
            kwtWorkDataEntity.setAllowNomAgain(kwtWorkDataDTO.getAllowNomAgain());

            if (kwtWorkDataDTO.getJobsDTO() != null) {
                IJobsEntityKey entityKey = (IJobsEntityKey)kwtWorkDataDTO.getJobsDTO().getCode();
                try {
                    kwtWorkDataEntity.setJobCode(entityKey.getJobCode());
                } catch (Exception e) {
                    kwtWorkDataEntity.setJobCode(null);
                }
            } else {
                kwtWorkDataEntity.setJobCode(null);
            }

            if (kwtWorkDataDTO.getMinistriesDTO() != null) {
                IMinistriesEntityKey entityKey = (IMinistriesEntityKey)kwtWorkDataDTO.getMinistriesDTO().getCode();
                kwtWorkDataEntity.setMinCode(entityKey.getMinCode());
            } else {
                kwtWorkDataEntity.setMinCode(null);
            }

            if (kwtWorkDataDTO.getWorkCentersDTO() != null) {
                try {
                    IWorkCentersEntityKey entityKey =
                        (IWorkCentersEntityKey)kwtWorkDataDTO.getWorkCentersDTO().getCode();
                    kwtWorkDataEntity.setWrkCode(entityKey.getWrkCode());
                    kwtWorkDataEntity.setWrkMinCode(entityKey.getMinCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                kwtWorkDataEntity.setWrkCode(null);
            }
            kwtWorkDataEntity.setUntilDate(kwtWorkDataDTO.getUntilDate());
            if (kwtWorkDataDTO.getFirstParent() == null)
                kwtWorkDataEntity.setFirstParent(kwtWorkDataEntity.getSerial());
            else
                kwtWorkDataEntity.setFirstParent(Long.valueOf(kwtWorkDataDTO.getFirstParent().getKey()));
            if (kwtWorkDataDTO.getTreeLevel() != null)
                kwtWorkDataEntity.setTreeLevel(kwtWorkDataDTO.getTreeLevel());
            else
                kwtWorkDataEntity.setTreeLevel(1L);
            if (kwtWorkDataDTO.getLeafFlag() != null)
                kwtWorkDataEntity.setLeafFlag(kwtWorkDataDTO.getLeafFlag());
            else
                kwtWorkDataEntity.setLeafFlag(1L);
            if (kwtWorkDataDTO.getActualExpDays() != null)
                kwtWorkDataEntity.setActualExpDays(kwtWorkDataDTO.getActualExpDays());
            else
                kwtWorkDataEntity.setActualExpDays(0L);
            if (kwtWorkDataDTO.getActualExpMonths() != null)
                kwtWorkDataEntity.setActualExpMonths(kwtWorkDataDTO.getActualExpMonths());
            else
                kwtWorkDataEntity.setActualExpMonths(0L);
            if (kwtWorkDataDTO.getActualExpYears() != null)
                kwtWorkDataEntity.setActualExpYears(kwtWorkDataDTO.getActualExpYears());
            else
                kwtWorkDataEntity.setActualExpYears(0L);
            if (kwtWorkDataDTO.getPerFlag() != null)
                kwtWorkDataEntity.setPerFlag(kwtWorkDataDTO.getPerFlag());
            else
                kwtWorkDataEntity.setPerFlag(1L);
            kwtWorkDataEntity.setPisFlag(kwtWorkDataDTO.getPisFlag());
            kwtWorkDataEntity.setExtraJob(kwtWorkDataDTO.getExtraJob());

            Long trxTypesCode = ((ITrxTypesEntityKey)kwtWorkDataDTO.getTrxTypesDTO().getCode()).getTrxtypeCode();
            kwtWorkDataEntity.setTrxtypeCode(trxTypesCode);
            
            if(kwtWorkDataDTO.getJobHistoryStatus()==null){
                kwtWorkDataEntity.setJobHistoryStatus(ISystemConstant.NON_ACTIVE_FLAG);
            }else{
                kwtWorkDataEntity.setJobHistoryStatus(kwtWorkDataDTO.getJobHistoryStatus()); 
            }

            doUpdate(kwtWorkDataEntity);

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

    public boolean checkExperienceConflict(IBasicDTO kwtWorkDTO) throws DataBaseException, SharedApplicationException {

        try {
            IKwtWorkDataTreeDTO kwtWorkDataDTO = (IKwtWorkDataTreeDTO)kwtWorkDTO;
            Date fromDate = kwtWorkDataDTO.getFromDate();
            Date untilDate = kwtWorkDataDTO.getUntilDate();
            Long serial = Long.valueOf(kwtWorkDataDTO.getCode().getKey());
            Long realCivil = Long.valueOf(kwtWorkDataDTO.getKwtCitizensResidentsDTO().getCode().getKey());

            StringBuilder sb = new StringBuilder("  SELECT COUNT(1) ");
            sb.append(" FROM INF_KWT_WORK_DATA   ");
            sb.append(" WHERE CIVIL_ID = " + realCivil + "  ");
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)  ");
            sb.append(" AND (   ");
            if (untilDate != null) {
                sb.append(" to_date('" + fromDate + "','yyyy-mm-dd') <= nvl(until_date,sysdate) AND to_date('" +
                          untilDate + "','yyyy-mm-dd') >= FROM_DATE  ");
            } else {
                sb.append("  to_date('" + fromDate +
                          "','yyyy-mm-dd') <=  nvl(until_date,sysdate) AND sysdate >= FROM_DATE  ");
            }

            sb.append("  )  ");
            if(serial!=null)
            sb.append(" and SERIAL <> " + serial);

            String queryString = sb.toString();
            System.out.println(queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getRecordContainsJobReason(Long realCivilId) throws DataBaseException,
                                                                                 SharedApplicationException {
        StringBuilder queryString = new StringBuilder("");
        queryString.append(" SELECT EMP.*");
        queryString.append("   FROM (  SELECT *");
        queryString.append("             FROM INF_KWT_WORK_DATA KWT");
        queryString.append("            WHERE     KWT.CIVIL_ID =" + realCivilId);
        queryString.append("                  AND KWT.TRXTYPE_CODE NOT IN (80, 90, 280, 290)");
        queryString.append("                 AND KWT.JOB_CODE =");
        queryString.append("                         (SELECT KWT1.JOB_CODE");
        queryString.append("                            FROM INF_KWT_WORK_DATA KWT1");
        queryString.append("                           WHERE     KWT1.CIVIL_ID = KWT.CIVIL_ID");
        queryString.append("                                 AND KWT1.UNTIL_DATE IS NULL");
        queryString.append("                                 AND KWT1.TRXTYPE_CODE NOT IN (80, 90, 280, 290))");
        queryString.append("         ORDER BY KWT.FROM_DATE) EMP");
        queryString.append("  WHERE ROWNUM = 1");
        System.out.println(queryString.toString());
        Query query = EM().createNativeQuery(queryString.toString(), KwtWorkDataEntity.class);
        List<KwtWorkDataEntity> lst = query.getResultList();
        IKwtWorkDataDTO kwtWorkDataDTO = null;
        if(lst != null || !lst.isEmpty())
            kwtWorkDataDTO = InfDTOFactory.createKwtWorkDataDTO(lst.get(0));
        
        return kwtWorkDataDTO;
    }
    
    public Boolean checkExperiencesConflictExcluded(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                                    List<Long> trxCodList, boolean includeTrx,
                                                    List<Long> excludedLst) throws DataBaseException,
                                                                                              SharedApplicationException {
        StringBuilder ejbql = null;
        try {
            ejbql = new StringBuilder("");
            ejbql.append(" SELECT COUNT(1) FROM INF_KWT_WORK_DATA W WHERE CIVIL_ID = " + realCivilId);
            if (trxCodList.size() > 0) {
                StringBuilder trxCodStr = new StringBuilder("");
                for (Long trxCode : trxCodList) {
                    trxCodStr.append(trxCode + ",");
                }
                if (includeTrx)
                    ejbql.append(" AND W.TRXTYPE_CODE IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) + ") ");
                else
                    ejbql.append(" AND W.TRXTYPE_CODE NOT IN (" + trxCodStr.substring(0, trxCodStr.length() - 1) +
                                 ") ");
            }
            ejbql.append(" AND (");
            if (untilDate == null) {
                ejbql.append(" (to_Date('" + fromDate + "','yyyy/MM/dd') <= UNTIL_DATE AND UNTIL_DATE is NOT NULL");
                ejbql.append(" OR( UNTIL_DATE is null ))");
            } else {
                ejbql.append(" ( to_Date('" + fromDate + "','yyyy/MM/dd') <= UNTIL_DATE AND to_Date('" + untilDate +
                             "','yyyy/MM/dd') >= FROM_DATE)");
                ejbql.append(" OR( to_Date('" + untilDate + "','yyyy/MM/dd') >= FROM_DATE AND UNTIL_DATE IS NULL) ");
            }
            ejbql.append(" )");
            if (excludedLst.size() > 0) {
                StringBuilder excludedLstStr = new StringBuilder("");
                for (Long code : excludedLst) {
                    excludedLstStr.append(code + ",");
                }
                ejbql.append(" AND W.SERIAL NOT IN (" + excludedLstStr.substring(0, excludedLstStr.length() - 1) +
                             ") ");
            }
            System.out.println("KwtWorkDataDAO.checkExperiencesConflictExcluded -> " + ejbql.toString());

            Query query = EM().createNativeQuery(ejbql.toString());

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public IBasicDTO getWorkCodeAndJobCodeByMovingDate(Long realCivilId,
                                                       java.sql.Date moveDate) throws DataBaseException,
                                                                                      SharedApplicationException {
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        StringBuilder query =
            new StringBuilder("select w.job_code , w.wrk_code ,j.JOB_NAME ,wrk.WRK_NAME,wrk.PRG_CODE,pgt.PRG_NAME,w.WRKMIN_CODE from INF_KWT_WORK_DATA w , NL_JOB_JOBS j, NL_ORG_WORK_CENTERS wrk,HR_BGT_PROGRAMS pgt where j.JOB_CODE=w.JOB_CODE and wrk.WRK_CODE=w.WRK_CODE and pgt.PRG_CODE=wrk.PRG_CODE ");
        query.append(" and ( (  to_date('" + moveDate + "','yyyy/MM/dd')");
        query.append("  between w.FROM_DATE and w.UNTIL_DATE AND W.UNTIL_DATE IS NOT NULL) OR ");
        query.append("  (to_date('" + moveDate + "','yyyy/MM/dd')");
        query.append(" >= w.FROM_DATE and w.UNTIL_DATE IS NULL)) and w.civil_id = ");
        query.append(realCivilId);
        query.append(" and TRXTYPE_CODE not in (80 , 90 , 280 , 290)");
        System.out.println("View query >>>>>>>>>> " + query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list = q.getResultList();
        if (list != null && list.size() != 0) {
            for (Vector row : list) {
                IKwtWorkDataDTO kwtWorkDataDTO = InfDTOFactory.createKwtWorkDataDTO();
                Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
                IWorkCentersEntityKey wrkCenEk =
                    OrgEntityKeyFactory.createWorkCentersEntityKey(minCode, row.get(1).toString());
                IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
                workCentersDTO.setCode(wrkCenEk);
                workCentersDTO.setName(row.get(3).toString());
                IMinistriesDTO minDto = OrgDTOFactory.createMinistriesDTO();
                IMinistriesEntityKey minEK =
                    OrgEntityKeyFactory.createMinistriesEntityKey(((BigDecimal)row.get(6)).longValue());
                minDto.setCode(minEK);
                workCentersDTO.setMinistriesDTO(minDto);
                IBgtProgramsDTO bgtProgramsDTO = BgtDTOFactory.createBgtProgramsDTO();
                IBgtProgramsEntityKey bgtEk = BgtEntityKeyFactory.createBgtProgramsEntityKey(row.get(4).toString());
                bgtProgramsDTO.setCode(bgtEk);
                bgtProgramsDTO.setName(row.get(5).toString());
                workCentersDTO.setBgtProgramsDTO(bgtProgramsDTO);
                IJobsEntityKey jobEk = JobEntityKeyFactory.createJobsEntityKey(row.get(0).toString());
                IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
                jobsDTO.setCode(jobEk);
                jobsDTO.setName(row.get(2).toString());
                kwtWorkDataDTO.setJobsDTO(jobsDTO);
                kwtWorkDataDTO.setWorkCentersDTO(workCentersDTO);
                arrayList.add(kwtWorkDataDTO);
                return arrayList.get(0);
}

        }
        return null;
    }
    
    public IBasicDTO getKwtWorkDataByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {

            Query query = EM().createNamedQuery("KwtWorkDataEntity.findKwtWorkDataByMovingDate");
            query.setParameter("realCivilId", realCivilId);
            query.setParameter("movingDate", movingDate);
            KwtWorkDataEntity entity = (KwtWorkDataEntity)query.getSingleResult();
            if (entity == null)
                throw new NoResultException();
            return InfDTOFactory.createKwtWorkDataDTO(entity);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public boolean isFirstJobBeforeApplyDateSuprvisory(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);
            
            StringBuilder sb = new StringBuilder("  SELECT COUNT(*) ");
            sb.append(" FROM INF_KWT_WORK_DATA w");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");       
            sb.append("  AND FROM_DATE =(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
            sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy')) ");
            sb.append(" AND JOB_CODE IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153)) ");
               
            String queryString = sb.toString();
            System.out.println("isFirstJobBeforeApplyDateSuprvisory : "+queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public boolean checkApplyDateBeforeFirstDegreeDate(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            StringBuilder sb = new StringBuilder("  SELECT COUNT(*) ");
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);

            sb.append(" FROM INF_KWT_WORK_DATA w");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");       
            sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') ");
            sb.append(" <  (select MIN(FROM_DATE) from INF_KWT_WORK_DATA where TRXTYPE_CODE NOT IN (80,90,280,290) AND CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(")");
            String queryString = sb.toString();
            System.out.println("checkApplyDateBeforeFirstDegreeDate : "+queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public boolean checkApplyDateBeforeWrkDataFromDate(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            StringBuilder sb = new StringBuilder("  SELECT COUNT(*) ");
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);

            sb.append(" FROM INF_KWT_WORK_DATA w");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");       
            sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') <=FROM_DATE");
               
            String queryString = sb.toString();
            System.out.println("checkApplyDateBeforeWrkDataFromDate : "+queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 0L) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public boolean checkIfLastChangeInSameEmpWrkData(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            StringBuilder sb = new StringBuilder("  SELECT COUNT(*) FROM (");
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);
            String dataChanged="";
            if(kwtBasicWrkDataDTO.getChangeType()==null)
                throw new SharedApplicationException();
            if(kwtBasicWrkDataDTO.getChangeType().equals(IInfConstant.CHANGE_TYPE_JOBCODE))
                dataChanged="JOB_CODE";
            else if(kwtBasicWrkDataDTO.getChangeType().equals(IInfConstant.CHANGE_TYPE_WRKCODE))
                dataChanged="WRK_CODE";
            else if(kwtBasicWrkDataDTO.getChangeType().equals(IInfConstant.CHANGE_TYPE_OTHERJOBCODE))
                dataChanged="JOB_CODE_OTHER";           
            
            
            sb.append(" SELECT COUNT (*)  FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
            sb.append(" AND "+dataChanged+" IS NOT NULL ");
            //sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') <= NVL (UNTIL_DATE, SYSDATE)");
            sb.append("  AND FROM_DATE >=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
            sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy')) ");
            if(kwtBasicWrkDataDTO.getChangeType().equals(IInfConstant.CHANGE_TYPE_JOBCODE)){
                /// for insert eshrafia (before and after not eshrafia)
                sb.append(" AND NOT EXISTS (SELECT 1 FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153) AND J.JOB_CODE ='"+kwtBasicWrkDataDTO.getJobCode()+"'");
                sb.append(" AND (SELECT COUNT(*) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID = ");
                sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                //sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') <= NVL (UNTIL_DATE, SYSDATE)");
                sb.append("  AND FROM_DATE >=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
                sb.append(" WHERE CIVIL_ID = ");
                sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy')) ");
                sb.append(" AND JOB_CODE IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153)) )=0) ");
                
                /// for insert not eshrafia (before not eshrafia and after eshrafia)
                if(kwtBasicWrkDataDTO.isChangeJobCodeAndOtherJobCode()){
                    sb.append(" AND NOT EXISTS (SELECT 1 FROM  NL_JOB_JOBS J WHERE J.JOB_CODE =1 AND  J.JOB_CODE NOT IN (SELECT JOB_CODE FROM NL_JOB_JOBS WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153)) ");
                    sb.append(" AND (SELECT COUNT(*) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID = ");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    //sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') <= NVL (UNTIL_DATE, SYSDATE)");
                    sb.append("  AND FROM_DATE >=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
                    sb.append(" WHERE CIVIL_ID = ");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy')) ");
                    sb.append(" AND JOB_CODE NOT IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153)) )=1 ");
                    sb.append(" AND (SELECT COUNT(*) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID = ");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE= (SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID =");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE <= TO_DATE('" + apply_Date + "','dd/MM/yyyy'))");
                    sb.append(" AND JOB_CODE NOT IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153)) )=1");
                    sb.append(" AND (SELECT COUNT(*) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID = ");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE > TO_DATE('" + apply_Date + "','dd/MM/yyyy')");
                    sb.append(" AND JOB_CODE_OTHER IS NOT NULL AND JOB_CODE_OTHER <> (SELECT JOB_CODE FROM INF_KWT_WORK_DATA WHERE CIVIL_ID =");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE =(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA WHERE CIVIL_ID =");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE <= TO_DATE('" + apply_Date + "','dd/MM/yyyy'))))=0 )");
                }
            }
            ///////////////////
            sb.append(" GROUP BY ");
            sb.append(dataChanged);
            sb.append(")");
            String queryString = sb.toString();
            System.out.println("checkIfLastChangeInSameEmpWrkData :"+queryString);
            Query query = EM().createNativeQuery(queryString);

            Vector count = (Vector)query.getSingleResult();
            Long countLong = new Long(((BigDecimal)count.get(0)).longValue());
            if (countLong != null && countLong > 1L) {
                throw new LastChangeInSameEmpWrkDataException(dataChanged);
            }
            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public int[] applyEmpWrkDataRequiredChanges(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException, SharedApplicationException {
        int[] results = null; 
        Statement  stmt = null;   
        try {
            Connection con=getConnectionForUpdate();
            stmt = con.createStatement();  
            StringBuilder dataChanged=new StringBuilder("");
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);
            
            
            StringBuilder sb=new StringBuilder("");
            sb.append(" UPDATE INF_KWT_WORK_DATA SET UNTIL_DATE = TO_DATE('" + apply_Date + "','dd/MM/yyyy')-1 ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
            sb.append(" AND UNTIL_DATE > TO_DATE('" + apply_Date + "','dd/MM/yyyy')-1");
            sb.append(" AND FROM_DATE= (SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
            sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy'))");
            System.out.println("applyEmpWrkDataRequiredChanges 1 :"+sb.toString());
            stmt.addBatch(sb.toString());
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(kwtBasicWrkDataDTO.isChangeJobCode()){
                sb=new StringBuilder(" UPDATE INF_KWT_WORK_DATA SET ");
                sb.append(" JOB_CODE_OTHER =");
                if(!kwtBasicWrkDataDTO.isChangeJobCodeAndOtherJobCode()){
                    sb.append("NVL2((SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153) and J.JOB_CODE ='"+kwtBasicWrkDataDTO.getJobCode().toString()+"'");
                    sb.append("  AND (SELECT JOB_CODE FROM INF_KWT_WORK_DATA WHERE  CIVIL_ID = "+kwtBasicWrkDataDTO.getRealCivilId()+" AND TRXTYPE_CODE NOT IN (80,90,280,290) AND FROM_DATE=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
                    sb.append(" WHERE CIVIL_ID = ");
                    sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                    sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
                    sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy'))) ");  
                    sb.append("  NOT IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153))),JOB_CODE,JOB_CODE_OTHER)");                                       
                }else{
                    sb.append("'"+kwtBasicWrkDataDTO.getJobCode().toString()+"'");
                }
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
            sb.append(" AND FROM_DATE>= TO_DATE('" + apply_Date + "','dd/MM/yyyy')");
            System.out.println("applyEmpWrkDataRequiredChanges 2 :"+sb.toString());
            stmt.addBatch(sb.toString());
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if(!kwtBasicWrkDataDTO.isChangeJobCodeAndOtherJobCode())
            {
                sb=new StringBuilder(" UPDATE INF_KWT_WORK_DATA SET ");
                
                    if(kwtBasicWrkDataDTO.isChangeJobCode()){
                        //dataChanged.append(" JOB_CODE_OTHER =NVL2((SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153) and J.JOB_CODE ="+kwtBasicWrkDataDTO.getJobCode().toString()+"),JOB_CODE,JOB_CODE_OTHER),");
                        dataChanged.append(" JOB_CODE ='"+kwtBasicWrkDataDTO.getJobCode()+"',");                    
                    } 
                    if(kwtBasicWrkDataDTO.isChangeWrkCode()){
                        dataChanged.append(" WRK_CODE ='"+kwtBasicWrkDataDTO.getWrkCode()+"',");
                        dataChanged.append(" MIN_CODE ="+kwtBasicWrkDataDTO.getMinCode()+",");
                        dataChanged.append(" WRKMIN_CODE ="+kwtBasicWrkDataDTO.getMinCode()+",");
                    }
                    if(kwtBasicWrkDataDTO.isChangeOtherJobCode())
                        dataChanged.append(" JOB_CODE_OTHER='"+kwtBasicWrkDataDTO.getOtherJobCode()+"',");
                String _dataChanged=dataChanged.toString();
                _dataChanged=_dataChanged.substring(0, _dataChanged.length()-1);
                sb.append(_dataChanged);
                sb.append(" WHERE CIVIL_ID = ");
                sb.append(kwtBasicWrkDataDTO.getRealCivilId());
                sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
                sb.append(" AND FROM_DATE>= TO_DATE('" + apply_Date + "','dd/MM/yyyy')");
                System.out.println("applyEmpWrkDataRequiredChanges 3 :"+sb.toString());
                stmt.addBatch(sb.toString());
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////                
            sb=new StringBuilder(" INSERT INTO INF_KWT_WORK_DATA ");
            sb.append(" (SERIAL, FROM_DATE, UNTIL_DATE,TRXTYPE_CODE,JOB_CODE, JOB_CODE_OTHER,WRK_CODE,MIN_CODE,WRKMIN_CODE,CIVIL_ID,");
            sb.append(" ALLOW_NOM_AGAIN,ACTUAL_EXP_YEARS, ACTUAL_EXP_MONTHS, ACTUAL_EXP_DAYS,EXTRA_MINISTRY,EXTRA_JOB, PER_FLAG, PIS_FLAG, FIRST_PARENT,");
            sb.append(" TREE_LEVEL, LEAF_FLAG, PARENT_SERIAL, USER_CODE, AUDIT_STATUS, TABREC_SERIAL, APPROVED_STATUS,CULOFFICE_CODE, JOB_HIST_STATUS)");
            sb.append(" SELECT (SELECT MAX(SERIAL) + 1 FROM INF_KWT_WORK_DATA) SERIAL,");
            sb.append(" TO_DATE('" + apply_Date + "','dd/MM/yyyy') FROM_DATE,");
            sb.append(" (SELECT MIN(FROM_DATE)-1 FROM INF_KWT_WORK_DATA WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
            sb.append(" AND FROM_DATE > TO_DATE('" + apply_Date + "','dd/MM/yyyy')) UNTIL_DATE,");
            sb.append(kwtBasicWrkDataDTO.getTrxTypeCode().toString());sb.append(",");
            if(kwtBasicWrkDataDTO.isChangeJobCode()){
                sb.append("'"+kwtBasicWrkDataDTO.getJobCode().toString()+"'");sb.append(",");
            } else
                sb.append(" JOB_CODE ,");
            
            if(kwtBasicWrkDataDTO.isChangeOtherJobCode()){
                sb.append("'"+kwtBasicWrkDataDTO.getOtherJobCode().toString()+"'");sb.append(",");
            } else {
                    if(!kwtBasicWrkDataDTO.isChangeJobCode()){
                        sb.append(" JOB_CODE_OTHER ,");
                    }else{
                        sb.append(" NVL2((SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153) and J.JOB_CODE ='"+kwtBasicWrkDataDTO.getJobCode().toString()+"' AND WRKDATA.JOB_CODE NOT IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153))),JOB_CODE,JOB_CODE_OTHER) JOB_CODE_OTHER,");
                    }
            }
            if(kwtBasicWrkDataDTO.isChangeWrkCode()){
                sb.append("'"+kwtBasicWrkDataDTO.getWrkCode().toString()+"'");sb.append(",");
                sb.append(kwtBasicWrkDataDTO.getMinCode().toString()+" MIN_CODE,");
                sb.append(kwtBasicWrkDataDTO.getMinCode().toString()+" WRKMIN_CODE,");
            } else
                sb.append(" WRK_CODE , MIN_CODE,WRKMIN_CODE,");
            
            sb.append(" CIVIL_ID, ALLOW_NOM_AGAIN,ACTUAL_EXP_YEARS, ACTUAL_EXP_MONTHS, ACTUAL_EXP_DAYS,");
            sb.append(" EXTRA_MINISTRY,EXTRA_JOB, PER_FLAG, PIS_FLAG, FIRST_PARENT, TREE_LEVEL,");
            sb.append(" LEAF_FLAG, PARENT_SERIAL, USER_CODE, AUDIT_STATUS, TABREC_SERIAL, APPROVED_STATUS,CULOFFICE_CODE, JOB_HIST_STATUS ");
            sb.append(" FROM INF_KWT_WORK_DATA WRKDATA");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
            sb.append(" AND TO_DATE('" + apply_Date + "','dd/MM/yyyy') NOT IN (SELECT FROM_DATE FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290))");
            sb.append("  AND FROM_DATE=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");
            sb.append(" AND FROM_DATE < TO_DATE('" + apply_Date + "','dd/MM/yyyy')) ");
            System.out.println("applyEmpWrkDataRequiredChanges 4 :"+sb.toString());
            stmt.addBatch(sb.toString());
            
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            results = stmt.executeBatch();
            System.out.println("===applyEmpWrkDataRequiredChanges executeBatch===");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BatchNotCompletedException();
        } finally {
            close(stmt);
        }
        return results;
    }
    
    public int[] applyEmpWrkDataNormalChanges(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException, SharedApplicationException {
        int[] results = null; 
        Statement  stmt = null;   
        try {
            Connection con=getConnectionForUpdate();
            stmt = con.createStatement();
            Date applyDate = kwtBasicWrkDataDTO.getApplyDate();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String apply_Date = format.format(applyDate);
            StringBuilder sb=new StringBuilder("");
            sb.append(" UPDATE INF_KWT_WORK_DATA SET UNTIL_DATE = TO_DATE('" + apply_Date + "','dd/MM/yyyy')-1 ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)");            
            sb.append(" AND UNTIL_DATE IS NULL");
            System.out.println("applyEmpWrkDataRequiredChanges 1 :"+sb.toString());
            stmt.addBatch(sb.toString());
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
           
            sb=new StringBuilder(" INSERT INTO INF_KWT_WORK_DATA ");
            sb.append(" (SERIAL, FROM_DATE, UNTIL_DATE,TRXTYPE_CODE,JOB_CODE, JOB_CODE_OTHER,WRK_CODE,MIN_CODE,WRKMIN_CODE,CIVIL_ID,");
            sb.append(" ALLOW_NOM_AGAIN,ACTUAL_EXP_YEARS, ACTUAL_EXP_MONTHS, ACTUAL_EXP_DAYS,EXTRA_MINISTRY,EXTRA_JOB, PER_FLAG, PIS_FLAG, FIRST_PARENT,");
            sb.append(" TREE_LEVEL, LEAF_FLAG, PARENT_SERIAL, USER_CODE, AUDIT_STATUS, TABREC_SERIAL, APPROVED_STATUS,CULOFFICE_CODE, JOB_HIST_STATUS)");
            sb.append(" SELECT (SELECT MAX(SERIAL) + 1 FROM INF_KWT_WORK_DATA) SERIAL,");
            sb.append(" TO_DATE('" + apply_Date + "','dd/MM/yyyy') FROM_DATE,");
            sb.append(" NULL UNTIL_DATE,");
            sb.append(kwtBasicWrkDataDTO.getTrxTypeCode().toString());sb.append(",");
                if(kwtBasicWrkDataDTO.isChangeJobCode()){
                    sb.append("'"+kwtBasicWrkDataDTO.getJobCode().toString()+"'");sb.append(",");
                } else
                    sb.append(" JOB_CODE ,");
                
                if(kwtBasicWrkDataDTO.isChangeOtherJobCode()){
                    sb.append("'"+kwtBasicWrkDataDTO.getOtherJobCode().toString()+"'");sb.append(",");
                } else {
                    if(!kwtBasicWrkDataDTO.isChangeJobCode()){
                        sb.append(" JOB_CODE_OTHER ,");
                    }else{
                        sb.append(" NVL2((SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153) and J.JOB_CODE ='"+kwtBasicWrkDataDTO.getJobCode().toString()+"' AND WRKDATA.JOB_CODE NOT IN(SELECT J.JOB_CODE FROM  NL_JOB_JOBS J WHERE CAT_CODE IN (SELECT CAT_CODE FROM NL_JOB_CATS WHERE PARENT_CAT =153))),JOB_CODE,JOB_CODE_OTHER) JOB_CODE_OTHER,");
                    }
                }
                if(kwtBasicWrkDataDTO.isChangeWrkCode()){
                    sb.append("'"+kwtBasicWrkDataDTO.getWrkCode().toString()+"'");sb.append(",");
                    sb.append(kwtBasicWrkDataDTO.getMinCode().toString()+" MIN_CODE,");
                    sb.append(kwtBasicWrkDataDTO.getMinCode().toString()+" WRKMIN_CODE,");
                } else
                    sb.append(" WRK_CODE , MIN_CODE,WRKMIN_CODE,");
            
            sb.append(" CIVIL_ID, ALLOW_NOM_AGAIN,ACTUAL_EXP_YEARS, ACTUAL_EXP_MONTHS, ACTUAL_EXP_DAYS,");
            sb.append(" EXTRA_MINISTRY,EXTRA_JOB, PER_FLAG, PIS_FLAG, FIRST_PARENT, TREE_LEVEL,");
            sb.append(" LEAF_FLAG, PARENT_SERIAL, USER_CODE, AUDIT_STATUS, TABREC_SERIAL, APPROVED_STATUS,CULOFFICE_CODE, JOB_HIST_STATUS ");
            sb.append(" FROM INF_KWT_WORK_DATA WRKDATA");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290)"); 
            
            sb.append("  AND FROM_DATE=(SELECT MAX(FROM_DATE) FROM INF_KWT_WORK_DATA ");
            sb.append(" WHERE CIVIL_ID = ");
            sb.append(kwtBasicWrkDataDTO.getRealCivilId());
            sb.append(" AND TRXTYPE_CODE NOT IN (80,90,280,290))");
            System.out.println("applyEmpWrkDataRequiredChanges 2 :"+sb.toString());
            stmt.addBatch(sb.toString());
            
            results = stmt.executeBatch();
            System.out.println("===applyEmpWrkDataNormalChanges executeBatch===");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BatchNotCompletedException();
        } finally {
            close(stmt);
        }
        return results;
    }

    public java.sql.Date getJobAssignDate(Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        java.sql.Date jobAssignDate;
        StringBuilder queryString = new StringBuilder("");

        queryString.append(" SELECT MIN(FROM_DATE) ");
        queryString.append(" FROM INF_KWT_WORK_DATA W ");
        queryString.append(" WHERE CIVIL_ID = " + realCivilId);
        queryString.append(" AND W.TRXTYPE_CODE NOT IN (80,90,280,290) ");
        queryString.append(" AND job_code = " + jobCode);

        System.out.println("KwtWorkDataDAO.getJobAssignDate -> " + queryString.toString());

        Query query = EM().createNativeQuery(queryString.toString());

        Vector vec = (Vector)query.getSingleResult();
        jobAssignDate = new java.sql.Date(((Timestamp)vec.get(0)).getTime());

        return jobAssignDate;
    }


    public java.sql.Date getTechJobAssignDate(Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        java.sql.Date jobTechAssignDate;
        StringBuilder queryString = new StringBuilder("");


        queryString.append(" SELECT MIN(FROM_DATE )");
        queryString.append(" FROM INF_KWT_WORK_DATA W ");
        queryString.append(" WHERE CIVIL_ID =" + realCivilId);
        queryString.append(" AND W.TRXTYPE_CODE NOT IN (80,90,280,290) ");
        queryString.append(" AND ( job_code =" + jobCode + " or job_code_other =" + jobCode+")");
        System.out.println("KwtWorkDataDAO.getTechJobAssignDate -> " + queryString.toString());
        Query query = EM().createNativeQuery(queryString.toString());
        Vector vec = (Vector)query.getSingleResult();
        jobTechAssignDate = new java.sql.Date(((Timestamp)vec.get(0)).getTime());

        return jobTechAssignDate;

    }
    public java.sql.Date getEmployeeFirstHireDate (Long realCivilId) throws DataBaseException,SharedApplicationException {
        StringBuilder queryString = new StringBuilder("");
        queryString.append(" select HR_EMP_PAC2.GET_EMP_FIRST_HIRE_DATE("+realCivilId);
        queryString.append(" ) ");
        queryString.append(" from dual ");
        Query query = EM().createNativeQuery(queryString.toString());
        Vector vec = (Vector)query.getSingleResult();
        return new  java.sql.Date(((Timestamp)vec.get(0)).getTime());
    }

}
