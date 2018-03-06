package com.beshara.csc.inf.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

public interface IPersonsInformationClient extends IBasicClient {

    public IBasicDTO getQulDto(ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                        SharedApplicationException;

    public List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException;

    public String getSocietyCodeByCenter(String centerCode) throws DataBaseException, SharedApplicationException;

    public String getQualKey(ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                      SharedApplicationException;

    public List<IResultDTO> removePersonInfoAndPersonQual(List<IBasicDTO> list) throws DataBaseException,
                                                                                 SharedApplicationException;
    public List<IBasicDTO> getQualForEmp(Long civilID, boolean currentQul) throws DataBaseException,
                                                                                      SharedApplicationException;
    public Boolean checkQualificationForPerson(Long realCivil, Long centerCode,
                                               String centerQualCode) throws SharedApplicationException,
                                                                             DataBaseException ;
    
    public IPersonsInformationDTO updatePersonQualificationAndInformation(IBasicDTO dto) throws DataBaseException,
                                                                                                SharedApplicationException;
}
