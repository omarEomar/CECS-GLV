package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.inf.business.dto.IKwtBasicWrkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.List;


public interface IKwtWorkDataClient extends IBasicClient {
    public List<IBasicDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getAllByCivilIdForCRS(Long civilId) throws DataBaseException, SharedApplicationException;

    /**
     * @param list
     * @return List
     * @throws RemoteException
     * @throws DataBaseException
     */
    public List<ResultDTO> allowCandidate(List<IBasicDTO> list) throws DataBaseException, SharedApplicationException;

    Boolean checkResignedMinsAllowNomination(Long minsCode, Long civilId) throws DataBaseException,
                                                                                 SharedApplicationException;

    Boolean isEmpHasExperience(Long civilId) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> getGovExperiences(Long civilId) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> getNonGovExperiences(Long civilId) throws DataBaseException, SharedApplicationException;

    IBasicDTO getLastByCivilAndMinistry(Long civilId, Long minCode) throws DataBaseException,
                                                                           SharedApplicationException;


    List<IBasicDTO> getByCivilIdOrderByDate(Long civilId) throws DataBaseException, SharedApplicationException;

    List<IBasicDTO> getByCivilIdOrderByDateForSCP(Long civilId) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getByCivilIdOrderByDate(Long civilId,
                                                   List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public List<IBasicDTO> getWorkCenterGroupping(Long civilId, Long minCode, String jobCode, Date fromDate,
                                                  Date untilDate) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getWorkCenterGroupping(Long civilId, Long minCode, String jobCode, Date fromDate,
                                                  Date untilDate,
                                                  List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                                SharedApplicationException;

    public List<IBasicDTO> getJobGroupping(Long civilId, Long minCode, Date fromDate,
                                           Date untilDate) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getJobGroupping(Long civilId, Long minCode, Date fromDate, Date untilDate,
                                           List<IKwtWorkDataTreeDTO> unsavedList) throws DataBaseException,
                                                                                         SharedApplicationException;

    public IBasicDTO getTreeNodeById(IEntityKey id) throws DataBaseException, SharedApplicationException;

    public boolean checkAboutHireDate(IEmpCandidatesDTO empCandidateDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    public Long findNewId() throws DataBaseException, SharedApplicationException;

    public IBasicDTO getLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                boolean included) throws DataBaseException, SharedApplicationException;

    public IBasicDTO getAnyLastByCivilIdAndTrxCode(Long civilId, List<Long> trxCodList,
                                                   boolean included) throws DataBaseException,
                                                                            SharedApplicationException;

    public IBasicDTO getKwtWorkDataForMov(Long realCivilId, Long ndbType, Date movingDate) throws DataBaseException,

            SharedApplicationException;

    public Boolean checkExperiencesConflict(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                            List<Long> trxCodList, boolean includeTrx) throws DataBaseException,
                                                                                              SharedApplicationException;

    public IBasicDTO addKwtWorkDataTreeDTO(IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                             SharedApplicationException;

    IBasicDTO getByRealCivilIdAndMaxSerial(Long realCivilId) throws DataBaseException, SharedApplicationException;


    public IBasicDTO getKwtWorkDataForInternalMovAfterExc(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IBasicDTO> updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                                    SharedApplicationException;

    public Boolean updateWorkCodeForADC(Long civilId, String workCode) throws DataBaseException,
                                                                              SharedApplicationException;

    public Boolean checkDeplicatedDataforWrkCode(Long civilId) throws DataBaseException, SharedApplicationException;

    public Boolean updateWorkCodeForMov(Long realCivil, String workCode,
                                        java.sql.Date movDate) throws DataBaseException, SharedApplicationException;

    public List<IBasicDTO> getHistoricalScaleBgtProgram(Long civilId) throws DataBaseException,
                                                                             SharedApplicationException;

    public String getFirstExperience(Long realCivilId) throws DataBaseException, SharedApplicationException;

    public Boolean updateKwtWorkDataTreeDTO(IBasicDTO kwtWorkDataDTO1) throws DataBaseException,
                                                                              SharedApplicationException;

    public IBasicDTO getRecordContainsJobReason(Long realCivilId) throws DataBaseException, SharedApplicationException;

    public Boolean checkExperiencesConflictExcluded(Long realCivilId, java.sql.Date fromDate, java.sql.Date untilDate,
                                                    List<Long> trxCodList, boolean includeTrx,
                                                    List<Long> excludedLst) throws DataBaseException,
                                                                                   SharedApplicationException;

    public IBasicDTO getWorkCodeAndJobCodeByMovingDate(Long realCivilId,
                                                       java.sql.Date moveDate) throws DataBaseException,
                                                                                      SharedApplicationException;

    public IBasicDTO getKwtWorkDataByMovingDate(Long realCivilId, Date movingDate) throws DataBaseException,
                                                                                          SharedApplicationException;
    public Boolean applyEmpWrkDataChanges(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                         SharedApplicationException;
    
    public boolean isFirstJobBeforeApplyDateSuprvisory(IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws DataBaseException,
                                                                                                      SharedApplicationException;
    
    public java.sql.Date getJobAssignDate(Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                 SharedApplicationException;
    public java.sql.Date getTechJobAssignDate(Long realCivilId, Long jobCode) throws DataBaseException,
                                                                                 SharedApplicationException;
    public java.sql.Date getEmployeeFirstHireDate(Long realCivilId ) throws DataBaseException, SharedApplicationException ;
}
