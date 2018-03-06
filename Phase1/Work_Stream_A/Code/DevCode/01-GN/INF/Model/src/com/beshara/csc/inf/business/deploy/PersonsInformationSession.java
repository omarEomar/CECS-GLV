package com.beshara.csc.inf.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PersonsInformationSession extends BasicSession {

    public IBasicDTO getQulDto(IRequestInfoDTO ri,
                               ICenterQualificationsDTO centerQualificationsDTO) throws RemoteException,
                                                                                        DataBaseException,
                                                                                        SharedApplicationException;

    public List<IBasicDTO> getAll(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                           SharedApplicationException;

    public String getSocietyCodeByCenter(IRequestInfoDTO ri, String centerCode) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    public IBasicDTO addCMT(IRequestInfoDTO ri, IBasicDTO personsInformationDTO1) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;

    public String getQualKey(IRequestInfoDTO ri,
                             ICenterQualificationsDTO centerQualificationsDTO) throws RemoteException,
                                                                                      DataBaseException,
                                                                                      SharedApplicationException;

    public List<IResultDTO> removePersonInfoAndPersonQual(IRequestInfoDTO iRequestInfoDTO,
                                                    List<IBasicDTO> list) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;
    
    public List<IBasicDTO> getQualForEmp(IRequestInfoDTO iRequestInfoDTO,Long civilID, boolean currentQul) throws RemoteException,DataBaseException,
                                                                                      SharedApplicationException;
    
    public Boolean checkQualificationForPerson(IRequestInfoDTO ri, Long realCivil, Long centerCode,
                                               String centerQualCode) throws RemoteException,SharedApplicationException,
                                                                             DataBaseException ;
    
    public IPersonsInformationDTO updatePersonQualificationAndInformation(IRequestInfoDTO ri,IBasicDTO dto) throws RemoteException,DataBaseException,
                                                                                                SharedApplicationException;
}
