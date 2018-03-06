package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.inf.business.deploy.KwtWorkDataSession;
import com.beshara.csc.inf.business.dto.IKwtBasicWrkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Date;
import java.util.List;


public class KwtWorkDataClientImpl extends BaseClientImpl3 implements IKwtWorkDataClient {

    /**
     * * @param kwtWorkDataSession
     */
    public KwtWorkDataClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return KwtWorkDataSession.class;
    }

    @Override
    protected KwtWorkDataSession SESSION() {
        return (KwtWorkDataSession)super.SESSION();
    }


    public List<IBasicDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAll(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getAllByCivilIdForCRS(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllForCRS(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<ResultDTO> allowCandidate(List<IBasicDTO> list) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().allowCandidate(RI(), list);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean checkResignedMinsAllowNomination(Long minsCode, Long civilId) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            return SESSION().checkResignedMinsAllowNomination(RI(), minsCode, civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isEmpHasExperience(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().isEmpHasExperience(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getGovExperiences(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getGovExperiences(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getNonGovExperiences(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getNonGovExperiences(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getLastByCivilAndMinistry(Long civilId, Long minCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().getLastByCivilAndMinistry(RI(), civilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> getByCivilIdOrderByDate(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getByCivilIdOrderByDate(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getByCivilIdOrderByDateForSCP(Long civilId) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().getByCivilIdOrderByDateForSCP(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> getByCivilIdOrderByDate(Long civilId,
                                                   List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().getByCivilIdOrderByDate(RI(), civilId, unsavedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> getWorkCenterGroupping(Long civilId, Long minCode, String jobCode, Date fromDate,
                                                  Date untilDate) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getWorkCenterGroupping(RI(), civilId, minCode, jobCode, fromDate, untilDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getWorkCenterGroupping(Long civilId, Long minCode, String jobCode, Date fromDate,
                                                  Date untilDate,
                                                  List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return SESSION().getWorkCenterGroupping(RI(), civilId, minCode, jobCode, fromDate, untilDate, unsavedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getJobGroupping(Long civilId, Long minCode, Date fromDate,
                                           Date untilDate) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getJobGroupping(RI(), civilId, minCode, fromDate, untilDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getJobGroupping(Long civilId, Long minCode, Date fromDate, Date untilDate,
                                           List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            return SESSION().getJobGroupping(RI(), civilId, minCode, fromDate, untilDate, unsavedList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getTreeNodeById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getTreeNodeById(RI(), id);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public boolean checkAboutHireDate(IEmpCandidatesDTO empCandidateDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().checkAboutHireDate(RI(), empCandidateDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().findNewId(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                boolean included) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getLastByCivilIdAndTrxCode(RI(), civilId, trxCodList, included);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getAnyLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                   boolean included) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().getAnyLastByCivilIdAndTrxCode(RI(), civilId, trxCodList, included);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IBasicDTO getKwtWorkDataForMov(Long realCivilId, Long ndbType, Date movingDate) throws DataBaseException,

            SharedApplicationException {
        try {
            return SESSION().getKwtWorkDataForMov(RI(), realCivilId, ndbType, movingDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean checkExperiencesConflict(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                            List<Long> trxCodList, boolean includeTrx) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SESSION().checkExperiencesConflict(RI(), realCivilId, fromDate, untilDate, trxCodList, includeTrx);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO addKwtWorkDataTreeDTO(IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            return SESSION().addKwtWorkDataTreeDTO(RI(), kwtWorkDataDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getByRealCivilIdAndMaxSerial(Long realCivilId) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().getByRealCivilIdAndMaxSerial(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public IBasicDTO getKwtWorkDataForInternalMovAfterExc(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            return SESSION().getKwtWorkDataForInternalMovAfterExc(RI(), realCivilId, movingDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            return SESSION().updateJobCodeForADC(RI(), basicDTOList, jobCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public Boolean updateWorkCodeForADC(Long civilId, String workCode) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().updateWorkCodeForADC(RI(), civilId, workCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean checkDeplicatedDataforWrkCode(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().checkDeplicatedDataforWrkCode(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public Boolean updateWorkCodeForMov(Long realCivil, String workCode,
                                        java.sql.Date movDate) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().updateWorkCodeForMov(RI(), realCivil, workCode, movDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public List<IBasicDTO> getHistoricalScaleBgtProgram(Long civilId) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            return SESSION().getHistoricalScaleBgtProgram(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public String getFirstExperience(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getFirstExperience(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public Boolean updateKwtWorkDataTreeDTO(IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().updateKwtWorkDataTreeDTO(RI(), kwtWorkDataDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getRecordContainsJobReason(Long realCivilId) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getRecordContainsJobReason(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean checkExperiencesConflictExcluded(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                                    List<Long> trxCodList, boolean includeTrx,
                                                    List<Long> excludedLst) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().checkExperiencesConflictExcluded(RI(), realCivilId, fromDate, untilDate, trxCodList,
                                                              includeTrx, excludedLst);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getWorkCodeAndJobCodeByMovingDate(Long realCivilId,
                                                       java.sql.Date moveDate) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SESSION().getWorkCodeAndJobCodeByMovingDate(RI(), realCivilId, moveDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO getKwtWorkDataByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getKwtWorkDataByMovingDate(RI(), realCivilId, movingDate);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    public Boolean applyEmpWrkDataChanges(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            return SESSION().applyEmpWrkDataChanges(RI(), kwtBasicWrkDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    public boolean isFirstJobBeforeApplyDateSuprvisory(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                                      SharedApplicationException {
        try {
            return SESSION().isFirstJobBeforeApplyDateSuprvisory(RI(), kwtBasicWrkDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public java.sql.Date getJobAssignDate(Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().getJobAssignDate(RI(), realCivilId, jobCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public java.sql.Date getTechJobAssignDate(Long realCivilId, Long jobCode
                                              ) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getTechJobAssignDate(RI(), realCivilId, jobCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
    public java.sql.Date getEmployeeFirstHireDate(Long realCivilId ) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmployeeFirstHireDate(RI(), realCivilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
    


    
}
