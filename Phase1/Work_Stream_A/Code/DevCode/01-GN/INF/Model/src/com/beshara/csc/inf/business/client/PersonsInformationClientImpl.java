package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IResultDTO;
import com.beshara.csc.inf.business.deploy.PersonsInformationSession;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class PersonsInformationClientImpl extends BaseClientImpl3 implements IPersonsInformationClient {

    public PersonsInformationClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return PersonsInformationSession.class;
    }

    @Override
    protected PersonsInformationSession SESSION() {
        return (PersonsInformationSession)super.SESSION();
    }

    public String getSocietyCodeByCenter(String centerCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getSocietyCodeByCenter(RI(), centerCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getAll(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAll(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO getQulDto(ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            return SESSION().getQulDto(RI(), centerQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public String getQualKey(ICenterQualificationsDTO centerQualificationsDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SESSION().getQualKey(RI(), centerQualificationsDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IResultDTO> removePersonInfoAndPersonQual(List<IBasicDTO> list) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().removePersonInfoAndPersonQual(RI(), list);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
}
    }

    public List<IBasicDTO> getQualForEmp(Long civilID, boolean currentQul) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().getQualForEmp(RI(), civilID, currentQul);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
}
    }


    public Boolean checkQualificationForPerson(Long realCivil, Long centerCode,
                                               String centerQualCode) throws SharedApplicationException,
                                                                             DataBaseException {
        try {
            return SESSION().checkQualificationForPerson(RI(), realCivil, centerCode,centerQualCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }
    
    public IPersonsInformationDTO updatePersonQualificationAndInformation(IBasicDTO dto) throws DataBaseException,
                                                                                 SharedApplicationException {

        try {
            return SESSION().updatePersonQualificationAndInformation(RI(), dto);
        } catch (SharedApplicationException e) {
            throw (e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
