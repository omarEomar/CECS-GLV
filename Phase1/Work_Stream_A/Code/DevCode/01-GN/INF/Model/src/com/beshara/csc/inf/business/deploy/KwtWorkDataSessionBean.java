package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.inf.business.dao.KwtWorkDataDAO;
import com.beshara.csc.inf.business.dto.IKwtBasicWrkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.entity.KwtWorkDataEntity;
import com.beshara.csc.inf.business.exception.ConflictExperienceException;
import com.beshara.csc.inf.business.shared.ApplyMergeSort;
import com.beshara.csc.inf.business.shared.IInfConstant;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDateException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ICRSConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import oracle.toplink.essentials.exceptions.TransactionException;


@Stateless(name = "KwtWorkDataSession", mappedName = "Inf-Model-KwtWorkDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class KwtWorkDataSessionBean extends InfBaseSessionBean implements KwtWorkDataSession {

    public KwtWorkDataSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return KwtWorkDataEntity.class;
    }

    @Override
    protected KwtWorkDataDAO DAO() {
        return (KwtWorkDataDAO)super.DAO();
    }

    /**
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getAll();
    }

    @Override
    public List<IBasicDTO> getAll(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {
        return DAO().getAll(civilId);

    }

    @Override
    public List<IBasicDTO> getAllForCRS(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                 SharedApplicationException {
        return DAO().getAllForCRS(civilId);
    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        IKwtWorkDataDTO kwtWorkDataDTO = (IKwtWorkDataDTO)dto;
        if (kwtWorkDataDTO.getAllowNomAgain() == null) {
            kwtWorkDataDTO.setAllowNomAgain(ICRSConstant.NOT_ALLOW_NOM_AGAIN);
        }
        if (kwtWorkDataDTO.getPerFlag() == null) {
            kwtWorkDataDTO.setPerFlag(ISystemConstant.ACTIVE_FLAG);
        }
        if (kwtWorkDataDTO.getPisFlag() == null) {
            kwtWorkDataDTO.setPisFlag(ISystemConstant.ACTIVE_FLAG);
        }
        return DAO().add(kwtWorkDataDTO);
    }

    /**
     * @param list
     * @return List
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    @Override
    public List<ResultDTO> allowCandidate(IRequestInfoDTO ri, List<IBasicDTO> list) throws DataBaseException,
                                                                                           SharedApplicationException {
        List<ResultDTO> resultList = new ArrayList<ResultDTO>();
        for (IBasicDTO basic : list) {
            ResultDTO result = new ResultDTO();
            result.setCurrentObject(basic);
            try {
                result.setStatus(DAO().update(basic) ? ISystemConstant.ITEM_ADDED : ISystemConstant.ITEM_NOT_ADDED);
            } catch (ItemNotFoundException e) {
                result.setStatus(ISystemConstant.ITEM_NOT_ADDED);
            }
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * * @param list
     * @return
     */
    @Override
    public List<IResultDTO> remove(IRequestInfoDTO ri, List<IBasicDTO> list) throws DataBaseException,
                                                                                    SharedApplicationException {
        boolean transactionBegun = isTransactionBegun();
        if (transactionBegun) {
            suspendTransaction();
        }
        List resultList = new ArrayList();
        for (IBasicDTO kwtWorkDataDTO : list) {
            try {
                beginTransaction();
                DAO().remove(kwtWorkDataDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwtWorkDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwtWorkDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwtWorkDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwtWorkDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            } catch (Exception e) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(kwtWorkDataDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                //resultDTO.setDatabaseException ( new DataBaseException ( SharedUtils.getExceptionMessage ( e.getCause ( ) .getMessage ( ) ) ) ) ;
                resultList.add(resultDTO);
            }
        }
        if (transactionBegun) {
            resumeTransaction();
        }

        return resultList;
    }

    @Override
    public Boolean checkResignedMinsAllowNomination(IRequestInfoDTO ri, Long minsCode,
                                                    Long civilId) throws DataBaseException,
                                                                         SharedApplicationException {
        return DAO().checkResignedMinsAllowNomination(minsCode, civilId);
    }

    @Override
    public Boolean isEmpHasExperience(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                               SharedApplicationException {
        Long numofExp = DAO().countNumOfExperiences(civilId);
        if (numofExp == null || numofExp == 0L) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    public List<IBasicDTO> getGovExperiences(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                      SharedApplicationException {
        return DAO().getByGovFlag(civilId, ISystemConstant.GOVERNMENT_FLAG);

    }

    @Override
    public List<IBasicDTO> getNonGovExperiences(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().getByGovFlag(civilId, ISystemConstant.NON_GOVERNMENT_FLAG);
    }

    @Override
    public IBasicDTO getLastByCivilAndMinistry(IRequestInfoDTO ri, Long civilId,
                                               Long minCode) throws DataBaseException, SharedApplicationException {
        return DAO().getLastByCivilAndMinistry(civilId, minCode);

    }


    @Override
    public List<IBasicDTO> getByCivilIdOrderByDate(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                            SharedApplicationException {
        List<IBasicDTO> kwtMinDataDTOParentListTemp = DAO().getByCivilIdOrderByDate(civilId);


        return prepareKwtWorkDataTreeForView(kwtMinDataDTOParentListTemp);
    }

    @Override
    public List<IBasicDTO> getByCivilIdOrderByDateForSCP(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                                  SharedApplicationException {
        List<IBasicDTO> kwtMinDataDTOParentListTemp = DAO().getByCivilIdOrderByDateForSCP(civilId);
        return kwtMinDataDTOParentListTemp;

    }

    /**
     * Overloaded method to add unsaved list To tree
     * @param ri
     * @param civilId
     * @param unsavedList
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getByCivilIdOrderByDate(IRequestInfoDTO ri, Long civilId,
                                                   List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                 SharedApplicationException {
        List<IBasicDTO> kwtMinDataDTOParentListTemp;
        try {
            kwtMinDataDTOParentListTemp = DAO().getByCivilIdOrderByDate(civilId);
        } catch (Exception e) {
            kwtMinDataDTOParentListTemp = new ArrayList();
        }
        kwtMinDataDTOParentListTemp.addAll(unsavedList);
        //TODO get sortedListBy mergeSort
        List<IKwtWorkDataTreeDTO> sortedList = ApplyMergeSort.sortList((List)kwtMinDataDTOParentListTemp);
        return prepareKwtWorkDataTreeForView((List)sortedList);
    }

    private List<IBasicDTO> prepareKwtWorkDataTreeForView(List<IBasicDTO> kwtMinDataDTOParentListTemp) throws DataBaseException,
                                                                                                              SharedApplicationException {
        List<IBasicDTO> kwtMinDataDTOParentList = new ArrayList();
        for (int i = 0; i < kwtMinDataDTOParentListTemp.size(); i++) {
            if (i == 0) {
                if (((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).getWorkCentersDTO() != null &&
                    ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).getPerFlag() == 0) {
                    ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).setServiceYears(0);
                    ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).setServiceMonths(0);
                    ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).setServiceDays(0);
                }
                IKwtWorkDataTreeDTO kwtMinDataDTO = (IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0);
                IMinistriesDTO ministriesDTO = kwtMinDataDTO.getMinistriesDTO();
                String ministryName = ministriesDTO.getName();
                kwtMinDataDTO.setName(ministryName);
                ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(0)).setTreeLevel(1L);
                kwtMinDataDTOParentList.add(kwtMinDataDTOParentListTemp.get(0));
            } else {
                if (((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i -
                                                                          1)).getMinistriesDTO().getCode().getKey().equals(((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getMinistriesDTO().getCode().getKey())) {
                    Boolean hasGabs =
                        checkGaps(kwtMinDataDTOParentListTemp.get(i - 1), kwtMinDataDTOParentListTemp.get(i));
                    if (hasGabs) {
                        if (((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getWorkCentersDTO() != null &&
                            ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getPerFlag() == 0) {
                            ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceYears(0);
                            ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceMonths(0);
                            ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceDays(0);
                        }
                        ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setTreeLevel(1L);
                        kwtMinDataDTOParentListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getMinistriesDTO().getName());
                        kwtMinDataDTOParentList.add(kwtMinDataDTOParentListTemp.get(i));
                    } else {
                        IBasicDTO basicDTOAfterMerge =
                            mergetwoPeriods(kwtMinDataDTOParentList.get(kwtMinDataDTOParentList.size() - 1),
                                            kwtMinDataDTOParentListTemp.get(i));
                        kwtMinDataDTOParentList.set(kwtMinDataDTOParentList.size() - 1, basicDTOAfterMerge);
                    }
                } else {

                    if (((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getWorkCentersDTO() != null &&
                        ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getPerFlag() == 0) {
                        ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceYears(0);
                        ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceMonths(0);
                        ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setServiceDays(0);
                    }
                    kwtMinDataDTOParentListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).getMinistriesDTO().getName());
                    ((IKwtWorkDataTreeDTO)kwtMinDataDTOParentListTemp.get(i)).setTreeLevel(1L);
                    kwtMinDataDTOParentList.add(kwtMinDataDTOParentListTemp.get(i));
                }
            }
        }
        return sortByDate(kwtMinDataDTOParentList);
    }

    @Override
    public List<IBasicDTO> getWorkCenterGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, String jobCode,
                                                  Date fromDate, Date untilDate) throws DataBaseException,
                                                                                        SharedApplicationException {
        List<IBasicDTO> kwtWorkCenterDataDTOListTemp =
            DAO().getWorkCenterGroupping(civilId, minCode, jobCode, fromDate, untilDate);
        List<IBasicDTO> kwtWorkCenterDataDTOList = new ArrayList();
        for (int i = 0; i < kwtWorkCenterDataDTOListTemp.size(); i++) {
            if (((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(0)).getWorkCentersDTO() != null) {
                kwtWorkCenterDataDTOListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).getWorkCentersDTO().getName());
                ((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).setLeafFlag(0L);
                ((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).setTreeLevel(3L);
                kwtWorkCenterDataDTOList.add(kwtWorkCenterDataDTOListTemp.get(i));
            }
        }
        return sortByDate(kwtWorkCenterDataDTOList);

    }

    /**
     * overload for getWorkCenterGroupping to get with unsavedList
     * @param ri
     * @param civilId
     * @param minCode
     * @param jobCode
     * @param fromDate
     * @param untilDate
     * @param unsavedList
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getWorkCenterGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, String jobCode,
                                                  Date fromDate, Date untilDate,
                                                  List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                SharedApplicationException {
        List<IBasicDTO> kwtWorkCenterDataDTOListTemp =
            DAO().getWorkCenterGroupping(civilId, minCode, jobCode, fromDate, untilDate);

        List<IKwtWorkDataTreeDTO> sortedList = new ArrayList();
        if (unsavedList != null && unsavedList.size() > 0) {
            if (kwtWorkCenterDataDTOListTemp == null || kwtWorkCenterDataDTOListTemp.isEmpty()) {
                for (int i = 0; i < unsavedList.size(); i++) {
                    String unSavedJobCode =
                        unsavedList.get(i).getJobsDTO() != null ? unsavedList.get(i).getJobsDTO().getCode().getKey() :
                        unsavedList.get(i).getExtraJob();
                    if (unsavedList.get(i).getMinistriesDTO().getCode().getKey().equals(minCode.toString()) &&
                        unSavedJobCode.equals(jobCode) &&
                        (unsavedList.get(i).getFromDate().after(fromDate) || unsavedList.get(i).getFromDate().equals(fromDate)) &&
                        unsavedList.get(i).getFromDate().before(untilDate)) {
                        kwtWorkCenterDataDTOListTemp.add(unsavedList.get(i));
                    }
                }
            } else {
                for (int i = 0; i < unsavedList.size(); i++) {
                    String unSavedJobCode2 =
                        unsavedList.get(i).getJobsDTO() != null ? unsavedList.get(i).getJobsDTO().getCode().getKey() :
                        unsavedList.get(i).getExtraJob();
                    if (unsavedList.get(i).getMinistriesDTO().getCode().getKey().equals(minCode.toString()) &&
                        unSavedJobCode2.equals(jobCode) &&
                        (unsavedList.get(i).getFromDate().after(fromDate) || unsavedList.get(i).getFromDate().equals(fromDate)) &&
                        unsavedList.get(i).getFromDate().before(untilDate)) {
                        kwtWorkCenterDataDTOListTemp.add(unsavedList.get(i));
                    }
                }
                sortedList = ApplyMergeSort.sortList((List)kwtWorkCenterDataDTOListTemp);
                kwtWorkCenterDataDTOListTemp.clear();
                kwtWorkCenterDataDTOListTemp.addAll(sortedList);
            }
        }
        List<IBasicDTO> kwtWorkCenterDataDTOList = new ArrayList();
        for (int i = 0; i < kwtWorkCenterDataDTOListTemp.size(); i++) {
            if (((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(0)).getWorkCentersDTO() != null) {
                kwtWorkCenterDataDTOListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).getWorkCentersDTO().getName());
                ((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).setLeafFlag(0L);
                ((IKwtWorkDataTreeDTO)kwtWorkCenterDataDTOListTemp.get(i)).setTreeLevel(3L);
                kwtWorkCenterDataDTOList.add(kwtWorkCenterDataDTOListTemp.get(i));
            }
        }
        return sortByDate(kwtWorkCenterDataDTOList);

    }


    public Long getWorkCenterGrouppingSize(IRequestInfoDTO ri, Long civilId, Long minCode, String jobCode,
                                           Date fromDate, Date untilDate,
                                           List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                         SharedApplicationException {
        Long kwtWorkCenterDataDTOListTempSize =
            DAO().getWorkCenterGrouppingCount(civilId, minCode, jobCode, fromDate, untilDate);
        if (unsavedList != null && unsavedList.size() > 0) {
            for (int i = 0; i < unsavedList.size(); i++) {
                String unSavedJobCode =
                    unsavedList.get(i).getJobsDTO() != null ? unsavedList.get(i).getJobsDTO().getCode().getKey() :
                    unsavedList.get(i).getExtraJob();

                if (unsavedList.get(i).getMinistriesDTO().getCode().getKey().equals(minCode.toString()) &&
                    unSavedJobCode.equals(jobCode) && unsavedList.get(i).getWorkCentersDTO() != null &&
                    (unsavedList.get(i).getFromDate().after(fromDate) ||
                     unsavedList.get(i).getFromDate().equals(fromDate)) &&
                    unsavedList.get(i).getFromDate().before(untilDate)) {
                    kwtWorkCenterDataDTOListTempSize++;
                }
            }
        }
        return kwtWorkCenterDataDTOListTempSize;

    }

    /**
     * OverLoaded Method to view unsavedList in tree
     * @param ri
     * @param civilId
     * @param minCode
     * @param fromDate
     * @param untilDate
     * @param unsavedList
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getJobGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, Date fromDate,
                                           Date untilDate,
                                           List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                         SharedApplicationException {
        List<IBasicDTO> kwtJobDataDTOListTemp = DAO().getJobGroupping(civilId, minCode, fromDate, untilDate);
        //TODO set unsavedlist with sorted list in a TempSortedList
        List<IKwtWorkDataTreeDTO> sortedList = new ArrayList();
        if (unsavedList != null && unsavedList.size() > 0) {
            if (kwtJobDataDTOListTemp == null || kwtJobDataDTOListTemp.isEmpty()) {
                for (int i = 0; i < unsavedList.size(); i++) {
                    if (unsavedList.get(i).getMinistriesDTO().getCode().getKey().equals(minCode.toString()) &&
                        (unsavedList.get(i).getFromDate().after(fromDate) ||
                         unsavedList.get(i).getFromDate().equals(fromDate)) &&
                        unsavedList.get(i).getFromDate().before(untilDate)) {
                        kwtJobDataDTOListTemp.add(unsavedList.get(i));
                    }
                }
            } else {
                for (int i = 0; i < unsavedList.size(); i++) {
                    if (unsavedList.get(i).getMinistriesDTO().getCode().getKey().equals(minCode.toString()) &&
                        (unsavedList.get(i).getFromDate().after(fromDate) ||
                         unsavedList.get(i).getFromDate().equals(fromDate)) &&
                        unsavedList.get(i).getFromDate().before(untilDate)) {
                        kwtJobDataDTOListTemp.add(unsavedList.get(i));
                    }
                }
                sortedList = ApplyMergeSort.sortList((List)kwtJobDataDTOListTemp);
                kwtJobDataDTOListTemp.clear();
                kwtJobDataDTOListTemp.addAll(sortedList);
            }
        }
        List<IBasicDTO> kwtJobDataDTOList = new ArrayList();
        for (int i = 0; i < kwtJobDataDTOListTemp.size(); i++) {
            if (i == 0) {
                if (((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).getWorkCentersDTO() != null &&
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).getPerFlag() == 0) {
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceYears(0);
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceMonths(0);
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceDays(0);
                }

                IKwtWorkDataTreeDTO kwtJobDataDTO = (IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0);
                IJobsDTO jobsDTO = kwtJobDataDTO.getJobsDTO();
                kwtJobDataDTO.setTreeLevel(2L);
                String jobName = null;
                String jobCode = null;
                if (jobsDTO == null) {
                    jobCode = kwtJobDataDTO.getExtraJob();
                    jobName = kwtJobDataDTO.getExtraJob();
                } else {
                    jobCode = kwtJobDataDTO.getJobsDTO().getCode().getKey();
                    jobName = jobsDTO.getName();
                }
                if (getWorkCenterGrouppingSize(ri, civilId, minCode, jobCode, fromDate, untilDate,
                                               unsavedList).equals(0L)) {
                    kwtJobDataDTO.setLeafFlag(0L);

                }
                kwtJobDataDTO.setName(jobName);
                kwtJobDataDTOList.add(kwtJobDataDTO);
            } else {
                IKwtWorkDataTreeDTO kwtJobDataDTOListTempFirst = ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i));
                String jobCodeFirst =
                    kwtJobDataDTOListTempFirst.getJobsDTO() != null ? kwtJobDataDTOListTempFirst.getJobsDTO().getCode().getKey() :
                    kwtJobDataDTOListTempFirst.getExtraJob();
                String jobNameFirst =
                    kwtJobDataDTOListTempFirst.getJobsDTO() != null ? kwtJobDataDTOListTempFirst.getJobsDTO().getName() :
                    kwtJobDataDTOListTempFirst.getExtraJob();

                IKwtWorkDataTreeDTO kwtJobDataDTOListTempPrev =
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i - 1));
                String jobCodePrev =
                    kwtJobDataDTOListTempPrev.getJobsDTO() != null ? kwtJobDataDTOListTempPrev.getJobsDTO().getCode().getKey() :
                    kwtJobDataDTOListTempPrev.getExtraJob();
                //                String jobNamePrev =
                //                    kwtJobDataDTOListTempPrev.getJobsDTO() != null ? kwtJobDataDTOListTempPrev.getJobsDTO().getName() :
                //                    kwtJobDataDTOListTempPrev.getExtraJob();

                if (jobCodePrev.equals(jobCodeFirst)) {
                    Boolean hasGabs = checkGaps(kwtJobDataDTOListTempPrev, kwtJobDataDTOListTempFirst);
                    if (hasGabs) {
                        kwtJobDataDTOListTempFirst.setName(jobNameFirst);
                        kwtJobDataDTOList.add(kwtJobDataDTOListTempFirst);
                    } else {
                        IBasicDTO basicDTOAfterMerge =
                            mergetwoPeriods(kwtJobDataDTOList.get(kwtJobDataDTOList.size() - 1),
                                            kwtJobDataDTOListTempFirst);
                        kwtJobDataDTOList.set(kwtJobDataDTOList.size() - 1, basicDTOAfterMerge);
                    }
                } else {
                    if (kwtJobDataDTOListTempFirst.getWorkCentersDTO() != null &&
                        kwtJobDataDTOListTempFirst.getPerFlag() == 0) {
                        kwtJobDataDTOListTempFirst.setServiceYears(0);
                        kwtJobDataDTOListTempFirst.setServiceMonths(0);
                        kwtJobDataDTOListTempFirst.setServiceDays(0);
                    }
                    kwtJobDataDTOListTempFirst.setName(jobNameFirst);
                    (kwtJobDataDTOListTempFirst).setTreeLevel(2L);
                    if (getWorkCenterGrouppingSize(ri, civilId, minCode, jobCodeFirst, fromDate, untilDate,
                                                   unsavedList).equals(0L)) {
                        (kwtJobDataDTOListTempFirst).setLeafFlag(0L);
                    }
                    kwtJobDataDTOList.add(kwtJobDataDTOListTempFirst);
                }
            }
        }
        return sortByDate(kwtJobDataDTOList);
    }

    @Override
    public List<IBasicDTO> getJobGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, Date fromDate,
                                           Date untilDate) throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> kwtJobDataDTOListTemp = DAO().getJobGroupping(civilId, minCode, fromDate, untilDate);
        List<IBasicDTO> kwtJobDataDTOList = new ArrayList();
        for (int i = 0; i < kwtJobDataDTOListTemp.size(); i++) {
            if (i == 0) {
                if (((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).getWorkCentersDTO() != null &&
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).getPerFlag() == 0) {
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceYears(0);
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceMonths(0);
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setServiceDays(0);
                }
                IKwtWorkDataTreeDTO kwtJobDataDTO = (IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0);
                IJobsDTO jobsDTO = kwtJobDataDTO.getJobsDTO();
                String jobName = jobsDTO.getName();
                kwtJobDataDTO.setName(jobName);
                ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setTreeLevel(2L);
                if (getWorkCenterGroupping(ri, civilId, minCode,
                                           ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).getJobsDTO().getCode().getKey(),
                                           fromDate, untilDate).size() == 0) {
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(0)).setLeafFlag(0L);
                }
                kwtJobDataDTOList.add(kwtJobDataDTOListTemp.get(0));
            } else {
                if (((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i -
                                                                    1)).getJobsDTO().getCode().getKey().equals(((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getJobsDTO().getCode().getKey())) {
                    Boolean hasGabs = checkGaps(kwtJobDataDTOListTemp.get(i - 1), kwtJobDataDTOListTemp.get(i));
                    if (hasGabs) {
                        kwtJobDataDTOListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getJobsDTO().getName());
                        kwtJobDataDTOList.add(kwtJobDataDTOListTemp.get(i));
                    } else {
                        IBasicDTO basicDTOAfterMerge =
                            mergetwoPeriods(kwtJobDataDTOList.get(kwtJobDataDTOList.size() - 1),
                                            kwtJobDataDTOListTemp.get(i));
                        kwtJobDataDTOList.set(kwtJobDataDTOList.size() - 1, basicDTOAfterMerge);
                    }
                } else {
                    if (((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getWorkCentersDTO() != null &&
                        ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getPerFlag() == 0) {
                        ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).setServiceYears(0);
                        ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).setServiceMonths(0);
                        ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).setServiceDays(0);
                    }
                    kwtJobDataDTOListTemp.get(i).setName(((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getJobsDTO().getName());
                    ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).setTreeLevel(2L);
                    if (getWorkCenterGroupping(ri, civilId, minCode,
                                               ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).getJobsDTO().getCode().getKey(),
                                               fromDate, untilDate).size() == 0) {
                        ((IKwtWorkDataTreeDTO)kwtJobDataDTOListTemp.get(i)).setLeafFlag(0L);
                    }
                    kwtJobDataDTOList.add(kwtJobDataDTOListTemp.get(i));
                }
            }
        }
        return sortByDate(kwtJobDataDTOList);
    }


    private List<IBasicDTO> sortByDate(List<IBasicDTO> list) throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> sortedList = new ArrayList();
        try {
            for (int i = list.size() - 1; i >= 0; i--) {
                sortedList.add(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedList;
    }

    private Boolean checkGaps(IBasicDTO firstRow, IBasicDTO secondRow) {
        Date untilDate = new Date(((IKwtWorkDataTreeDTO)firstRow).getUntilDate().getTime() + (1000 * 60 * 60 * 24));
        if (((IKwtWorkDataTreeDTO)secondRow).getFromDate().compareTo(untilDate) == 0) {
            return false;

        } else {
            return true;
        }
    }

    private IBasicDTO mergetwoPeriods(IBasicDTO firstRow, IBasicDTO secondRow) {
        int totalMonth = 0;
        int totalDayes = 0;
        if (((IKwtWorkDataTreeDTO)secondRow).getPerFlag() == 1) {
            totalMonth =
                    ((IKwtWorkDataTreeDTO)firstRow).getServiceMonths() + ((IKwtWorkDataTreeDTO)secondRow).getServiceMonths();
            totalDayes =
                    ((IKwtWorkDataTreeDTO)firstRow).getServiceDays() + ((IKwtWorkDataTreeDTO)secondRow).getServiceDays();
            ((IKwtWorkDataTreeDTO)firstRow).setServiceYears(((IKwtWorkDataTreeDTO)firstRow).getServiceYears() +
                                                            ((IKwtWorkDataTreeDTO)secondRow).getServiceYears());
            ((IKwtWorkDataTreeDTO)firstRow).setServiceMonths(totalMonth);
            ((IKwtWorkDataTreeDTO)firstRow).setServiceDays(totalDayes);
            firstRow = prepareTotalperiod(firstRow, totalMonth, totalDayes);
        }
        ((IKwtWorkDataTreeDTO)firstRow).setUntilDate(((IKwtWorkDataTreeDTO)secondRow).getUntilDate());
        return firstRow;
    }

    private IBasicDTO prepareTotalperiod(IBasicDTO firstRow, int totalMonths, int totalDays) {
        IKwtWorkDataTreeDTO dto = (IKwtWorkDataTreeDTO)firstRow;
        int months = 0;
        int days = 0;
        while (totalMonths > 12) {
            months++;
            totalMonths = totalMonths - 12;
            dto.setServiceYears(dto.getServiceYears() + months);
        }
        dto.setServiceMonths(totalMonths);
        if (totalMonths == 12) {
            dto.setServiceMonths(0);
            dto.setServiceYears(dto.getServiceYears() + 1);
        }
        while (totalDays > 30) {
            days++;
            totalDays = totalDays - 30;
            dto.setServiceMonths(dto.getServiceMonths() + days);
        }
        dto.setServiceDays(totalDays);
        if (totalMonths == 30) {
            dto.setServiceDays(0);
            dto.setServiceMonths(dto.getServiceMonths() + 1);
        }
        return dto;
    }

    @Override
    public IBasicDTO getTreeNodeById(IRequestInfoDTO ri, IEntityKey id) throws DataBaseException,
                                                                               SharedApplicationException {
        return DAO().getTreeNodeById(id);
    }

    @Override
    public boolean checkAboutHireDate(IRequestInfoDTO ri, IEmpCandidatesDTO empCandidateDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().checkAboutHireDate(empCandidateDTO);
    }

    @Override
    public Long findNewId(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().findNewId();
    }

    public IBasicDTO addCMT(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                                  SharedApplicationException {
        IKwtWorkDataDTO kwtWorkDataDTO = (IKwtWorkDataDTO)kwtWorkDataDTO1;
        if (kwtWorkDataDTO.getAllowNomAgain() == null) {
            kwtWorkDataDTO.setAllowNomAgain(ICRSConstant.NOT_ALLOW_NOM_AGAIN);
        }
        if (kwtWorkDataDTO.getPerFlag() == null) {
            kwtWorkDataDTO.setPerFlag(ISystemConstant.ACTIVE_FLAG);
        }
        if (kwtWorkDataDTO.getPisFlag() == null) {
            kwtWorkDataDTO.setPisFlag(ISystemConstant.ACTIVE_FLAG);
        }
        return DAO().add(kwtWorkDataDTO);
    }

    public IBasicDTO getLastByCivilIdAndTrxCode(IRequestInfoDTO ri, Long civilId, List<Long> trxCodList,
                                                boolean included) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return DAO().getLastByCivilIdAndTrxCode(civilId, trxCodList, included);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public IBasicDTO getAnyLastByCivilIdAndTrxCode(IRequestInfoDTO ri, Long civilId, List<Long> trxCodList,
                                                   boolean included) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return DAO().getAnyLastByCivilIdAndTrxCode(civilId, trxCodList, included);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public IBasicDTO getKwtWorkDataForMov(IRequestInfoDTO ri, Long realCivilId, Long ndbType,
                                          Date movingDate) throws DataBaseException, SharedApplicationException {


        return DAO().getKwtWorkDataForMov(realCivilId, ndbType, movingDate);


    }

    public Boolean checkExperiencesConflict(IRequestInfoDTO ri, Long realCivilId, java.sql.Date fromDate,
                                            java.sql.Date untilDate, List<Long> trxCodList,
                                            boolean includeTrx) throws DataBaseException, SharedApplicationException {
        return DAO().checkExperiencesConflict(realCivilId, fromDate, untilDate, trxCodList, includeTrx);
    }

    public IBasicDTO addKwtWorkDataTreeDTO(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                                                 SharedApplicationException {
        IKwtWorkDataTreeDTO kwtWorkDataDTO = (IKwtWorkDataTreeDTO)kwtWorkDataDTO1;
        kwtWorkDataDTO.setTreeLevel(ISystemConstant.ACTIVE_FLAG);
        kwtWorkDataDTO.setLeafFlag(ISystemConstant.ACTIVE_FLAG);
        kwtWorkDataDTO.setPisFlag(ISystemConstant.ACTIVE_FLAG);

        if (kwtWorkDataDTO.getAllowNomAgain() == null) {
            kwtWorkDataDTO.setAllowNomAgain(ICRSConstant.NOT_ALLOW_NOM_AGAIN);
        }
        if (kwtWorkDataDTO.getPerFlag() == null) {
            kwtWorkDataDTO.setPerFlag(ISystemConstant.ACTIVE_FLAG);
        }
        if (kwtWorkDataDTO.getPisFlag() == null) {
            kwtWorkDataDTO.setPisFlag(ISystemConstant.ACTIVE_FLAG);
        }

        kwtWorkDataDTO.setJobHistoryStatus(ISystemConstant.ACTIVE_FLAG);

        return DAO().addKwtWorkDataTreeDTO(kwtWorkDataDTO);
    }


    public IBasicDTO getByRealCivilIdAndMaxSerial(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().getByRealCivilIdAndMaxSerial(realCivilId);
    }
    public Boolean applyEmpWrkDataChanges(IRequestInfoDTO ri,IKwtBasicWrkDataDTO kwtBasicWrkDataDTO)throws DataBaseException,
                                                                                  SharedApplicationException {
        
    try {        
        // (1) check if applyDate Less Than first emp degree fromDate
        boolean applyDateBeforeFirstDegreeDate=DAO().checkApplyDateBeforeFirstDegreeDate(kwtBasicWrkDataDTO);
        if(applyDateBeforeFirstDegreeDate)
            throw new InvalidDateException();
        
        // (2) check if applyDate Less Than emp fromDate
        boolean applyDateBeforeEmpWrkDataFromDate = DAO().checkApplyDateBeforeWrkDataFromDate(kwtBasicWrkDataDTO);
       
        if(applyDateBeforeEmpWrkDataFromDate)
        {     
        // (3) check if last change was in the same field (job,otherjob,wrkcode)
            boolean lastChangeInSameEmpWrkData = true;
            if(kwtBasicWrkDataDTO.isChangeJobCode()){
                kwtBasicWrkDataDTO.setChangeType(IInfConstant.CHANGE_TYPE_JOBCODE);
                lastChangeInSameEmpWrkData =DAO().checkIfLastChangeInSameEmpWrkData(kwtBasicWrkDataDTO);
            }
            if(kwtBasicWrkDataDTO.isChangeWrkCode()){
                kwtBasicWrkDataDTO.setChangeType(IInfConstant.CHANGE_TYPE_WRKCODE);
                lastChangeInSameEmpWrkData =DAO().checkIfLastChangeInSameEmpWrkData(kwtBasicWrkDataDTO);
            }
            if(kwtBasicWrkDataDTO.isChangeOtherJobCode()){
                kwtBasicWrkDataDTO.setChangeType(IInfConstant.CHANGE_TYPE_OTHERJOBCODE);
                lastChangeInSameEmpWrkData =DAO().checkIfLastChangeInSameEmpWrkData(kwtBasicWrkDataDTO);
            }
            
            if(!lastChangeInSameEmpWrkData)
            {
                // (4) apply new business 
                DAO().applyEmpWrkDataRequiredChanges(kwtBasicWrkDataDTO);                
            }
        }else{
            // (5) close until date and add new 
            DAO().applyEmpWrkDataNormalChanges(kwtBasicWrkDataDTO);
        }
        return true;
    }catch(DataBaseException e){
        rollbackTransaction();
        throw e;
    }catch(SharedApplicationException e){
        rollbackTransaction();
        throw e;
    }
    }


    public IBasicDTO getKwtWorkDataForInternalMovAfterExc(IRequestInfoDTO ri, Long realCivilId,
                                                          Date movingDate) throws DataBaseException,
                                                                                  SharedApplicationException {


        Calendar cal = Calendar.getInstance();
        cal.setTime(movingDate);
        cal.add(Calendar.DATE, -1);
        movingDate = new Date(cal.getTime().getTime());

        return DAO().getKwtWorkDataForInternalMovAfterExc(realCivilId, movingDate);
    }

    public List<IBasicDTO> updateJobCodeForADC(IRequestInfoDTO ri, List<IBasicDTO> basicDTOList,
                                               String jobCode) throws DataBaseException, SharedApplicationException {
        StringBuilder realCivilIds = new StringBuilder("");
        for (IBasicDTO employeeSearchDTO : basicDTOList) {
            ((EmployeeSearchDTO)employeeSearchDTO).setActiveFlag(0L);
            realCivilIds.append(((EmployeeSearchDTO)employeeSearchDTO).getRealCivilId().toString());
            realCivilIds.append(",");
        }
        if (realCivilIds.length() > 0) {
            realCivilIds = realCivilIds.deleteCharAt(realCivilIds.length() - 1);
            List<EmployeeSearchDTO> resultList = (List)DAO().getReadyToUpdateListForADC(realCivilIds.toString());
            Long realCivil;
            for (IBasicDTO employeeSearchDTO : basicDTOList) {
                for (EmployeeSearchDTO dto : resultList) {
                    realCivil = ((EmployeeSearchDTO)employeeSearchDTO).getRealCivilId();
                    if (realCivil.equals(dto.getRealCivilId())) {
                        ((EmployeeSearchDTO)employeeSearchDTO).setActiveFlag(1L);
                        break;
                    }
                }
            }
            DAO().updateJobCodeForADC(basicDTOList, jobCode);
        }
        return basicDTOList;
    }

    public Boolean updateWorkCodeForADC(IRequestInfoDTO ri, Long civilId, String workCode) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException {
        return DAO().updateWorkCodeForADC(civilId, workCode);

    }

    public Boolean checkDeplicatedDataforWrkCode(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException {

        return DAO().checkDeplicatedDataforWrkCode(civilId);
    }

    public Boolean updateWorkCodeForMov(IRequestInfoDTO ri, Long realCivil, String workCode,
                                        java.sql.Date movDate) throws DataBaseException, SharedApplicationException {


        return DAO().updateWorkCodeForMov(realCivil, workCode, movDate);

    }

    public List<IBasicDTO> getHistoricalScaleBgtProgram(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                                 SharedApplicationException {
        return DAO().getHistoricalScaleBgtProgram(civilId);
    }


    public Boolean updateKwtWorkDataTreeDTO(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                                                  SharedApplicationException {
        Long Job_Hisory_Status_Update = 2L;
        IKwtWorkDataTreeDTO kwtWorkDataDTO = (IKwtWorkDataTreeDTO)kwtWorkDataDTO1;
        kwtWorkDataDTO.setJobHistoryStatus(Job_Hisory_Status_Update);
        boolean result = DAO().checkExperienceConflict(kwtWorkDataDTO);
        if (result) {
            throw new ConflictExperienceException();
        }
        return DAO().updateKwtWorkDataTreeDTO(kwtWorkDataDTO);
    }

    ////////////////////////////////////ESRV//////////////////////////////////////

    public String getFirstExperience(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        return DAO().getFirstExperience(realCivilId);
    }

    public IBasicDTO getRecordContainsJobReason(IRequestInfoDTO ri, Long realCivilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getRecordContainsJobReason(realCivilId);
    }
    
    public Boolean checkExperiencesConflictExcluded(IRequestInfoDTO ri, Long realCivilId, java.sql.Date fromDate,
                                                    java.sql.Date untilDate, List<Long> trxCodList, boolean includeTrx,
                                                    List<Long> excludedLst) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().checkExperiencesConflictExcluded(realCivilId, fromDate, untilDate, trxCodList, includeTrx,
                                                      excludedLst);
    }

    public IBasicDTO getWorkCodeAndJobCodeByMovingDate(IRequestInfoDTO ri,Long realCivilId,
                                                             java.sql.Date moveDate) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().getWorkCodeAndJobCodeByMovingDate(realCivilId, moveDate);
}
    public IBasicDTO getKwtWorkDataByMovingDate(IRequestInfoDTO ri, Long realCivilId, Date movingDate) throws DataBaseException, SharedApplicationException {


        return DAO().getKwtWorkDataByMovingDate(realCivilId,  movingDate);


    }

    public boolean isFirstJobBeforeApplyDateSuprvisory(IRequestInfoDTO ri,
                                                       IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                                      SharedApplicationException {
        return DAO().isFirstJobBeforeApplyDateSuprvisory(kwtBasicWrkDataDTO);
    }

    public java.sql.Date getJobAssignDate(IRequestInfoDTO ri, Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        return DAO().getJobAssignDate(realCivilId, jobCode);
    }
    
    
    public java.sql.Date getTechJobAssignDate(IRequestInfoDTO ri, Long realCivilId, Long jobCode ) throws DataBaseException,
                                                                        SharedApplicationException
    {
        
        return DAO().getTechJobAssignDate(realCivilId, jobCode);
    }
    
    
    public java.sql.Date getEmployeeFirstHireDate (IRequestInfoDTO ri,Long realCivilId) throws DataBaseException,SharedApplicationException {
        
        
            return DAO().getEmployeeFirstHireDate(realCivilId);
        }


    
}
