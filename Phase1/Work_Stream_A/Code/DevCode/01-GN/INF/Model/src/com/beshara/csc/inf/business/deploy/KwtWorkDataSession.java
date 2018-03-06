package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.inf.business.dto.IKwtBasicWrkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface KwtWorkDataSession extends BasicSession {
    public List<IBasicDTO> getAll(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;

    public List<IBasicDTO> getAllForCRS(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    /**
     * @param list
     * @return List
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public List<ResultDTO> allowCandidate(IRequestInfoDTO ri, List<IBasicDTO> list) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    Boolean checkResignedMinsAllowNomination(IRequestInfoDTO ri, Long minsCode, Long civilId) throws RemoteException,
                                                                                                     DataBaseException,
                                                                                                     SharedApplicationException;

    Boolean isEmpHasExperience(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;

    List<IBasicDTO> getGovExperiences(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                               SharedApplicationException;

    List<IBasicDTO> getNonGovExperiences(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    IBasicDTO getLastByCivilAndMinistry(IRequestInfoDTO ri, Long civilId, Long minCode) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public List<IBasicDTO> getByCivilIdOrderByDate(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;
    
    public List<IBasicDTO> getByCivilIdOrderByDateForSCP(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    public List<IBasicDTO> getByCivilIdOrderByDate(IRequestInfoDTO ri, Long civilId,
                                                   List<IKwtWorkDataTreeDTO> unsavedList) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;

    public List<IBasicDTO> getWorkCenterGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, String jobCode,
                                                  Date fromDate, Date untilDate) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

    public List<IBasicDTO> getWorkCenterGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, String jobCode,
                                                  Date fromDate, Date untilDate,
                                                  List<IKwtWorkDataTreeDTO> unsavedList) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public List<IBasicDTO> getJobGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, Date fromDate,
                                           Date untilDate,
                                           List<IKwtWorkDataTreeDTO> unsavedList) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    public List<IBasicDTO> getJobGroupping(IRequestInfoDTO ri, Long civilId, Long minCode, Date fromDate,
                                           Date untilDate) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    public IBasicDTO getTreeNodeById(IRequestInfoDTO ri, IEntityKey id) throws RemoteException, DataBaseException,
                                                                               SharedApplicationException;


    public boolean checkAboutHireDate(IRequestInfoDTO ri, IEmpCandidatesDTO empCandidateDTO) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public Long findNewId(IRequestInfoDTO ri) throws RemoteException, DataBaseException, SharedApplicationException;

    public IBasicDTO addCMT(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws RemoteException, DataBaseException,
                                                                                  SharedApplicationException;

    public IBasicDTO getLastByCivilIdAndTrxCode(IRequestInfoDTO ri, Long civilId, List<Long> trxCodList,
                                                boolean included) throws RemoteException, DataBaseException,
                                                                         SharedApplicationException;
    
    public IBasicDTO getAnyLastByCivilIdAndTrxCode(IRequestInfoDTO ri, Long civilId, List<Long> trxCodList,
                                                boolean included) throws RemoteException, DataBaseException,
                                                                         SharedApplicationException;

    public IBasicDTO getKwtWorkDataForMov(IRequestInfoDTO ri, Long realCivilId, Long ndbType,
                                          Date movingDate) throws DataBaseException, RemoteException,
                                                                  SharedApplicationException;

    public Boolean checkExperiencesConflict(IRequestInfoDTO ri, Long realCivilId, java.sql.Date fromDate,
                                            java.sql.Date untilDate, List<Long> trxCodList,
                                            boolean includeTrx) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;

    public IBasicDTO addKwtWorkDataTreeDTO(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;
    
        IBasicDTO getByRealCivilIdAndMaxSerial(IRequestInfoDTO ri,Long realCivilId)throws RemoteException,DataBaseException, SharedApplicationException;

    public IBasicDTO getKwtWorkDataForInternalMovAfterExc( IRequestInfoDTO ri,Long realCivilId, Date movingDate) throws  RemoteException, DataBaseException,
                                                                                                  SharedApplicationException ;
    
    public List<IBasicDTO> updateJobCodeForADC( IRequestInfoDTO ri, List<IBasicDTO> basicDTOList, String jobCode) throws  RemoteException, DataBaseException,
                                                                                                  SharedApplicationException ;
    
    public Boolean updateWorkCodeForADC(IRequestInfoDTO ri, Long civilId, String workCode ) throws  RemoteException, DataBaseException, SharedApplicationException ;


    public Boolean checkDeplicatedDataforWrkCode(IRequestInfoDTO ri,Long civilId) throws   RemoteException, DataBaseException, SharedApplicationException ;
    
    public Boolean updateWorkCodeForMov(IRequestInfoDTO ri,Long realCivil, String workCode, java.sql.Date movDate) throws RemoteException,DataBaseException,
                                                                                              SharedApplicationException ;

    public List<IBasicDTO> getHistoricalScaleBgtProgram(IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException,
                                                                             SharedApplicationException ;
    public String getFirstExperience(IRequestInfoDTO ri,Long realCivilId) throws RemoteException,DataBaseException, SharedApplicationException;
    
    public Boolean updateKwtWorkDataTreeDTO(IRequestInfoDTO ri, IBasicDTO kwtWorkDataDTO1) throws RemoteException,
                                                                                                 DataBaseException,
                                                                                                 SharedApplicationException;
    
    public IBasicDTO getRecordContainsJobReason(IRequestInfoDTO ri,Long realCivilId) throws RemoteException,DataBaseException, SharedApplicationException;
    
    public Boolean checkExperiencesConflictExcluded(IRequestInfoDTO ri, Long realCivilId, java.sql.Date fromDate,
                                            java.sql.Date untilDate, List<Long> trxCodList,
                                            boolean includeTrx, List<Long> excludedLst) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;
    
    public IBasicDTO getWorkCodeAndJobCodeByMovingDate(IRequestInfoDTO ri,Long realCivilId,
                                                             java.sql.Date moveDate) throws RemoteException,DataBaseException,
                                                                                            SharedApplicationException;
    public IBasicDTO getKwtWorkDataByMovingDate(IRequestInfoDTO ri, Long realCivilId, Date movingDate) throws DataBaseException, RemoteException,
                                                                  SharedApplicationException;
    public Boolean applyEmpWrkDataChanges(IRequestInfoDTO ri,IKwtBasicWrkDataDTO kwtBasicWrkDataDTO)throws RemoteException,DataBaseException,
                                                                                  SharedApplicationException ;
    public boolean isFirstJobBeforeApplyDateSuprvisory(IRequestInfoDTO ri,
                                                       IKwtBasicWrkDataDTO kwtBasicWrkDataDTO) throws RemoteException,DataBaseException,
                                                                                                      SharedApplicationException;

    public java.sql.Date getJobAssignDate(IRequestInfoDTO ri, Long realCivilId, Long jobCode) throws RemoteException,
                                                                                                     DataBaseException,
                                                                                                     SharedApplicationException;


    public java.sql.Date getTechJobAssignDate(IRequestInfoDTO ri, Long realCivilId,
                                            Long jobCode  ) throws RemoteException, DataBaseException,
                                                                        SharedApplicationException;
    public java.sql.Date getEmployeeFirstHireDate (IRequestInfoDTO ri,Long realCivilId) throws  RemoteException,DataBaseException,SharedApplicationException;

}
