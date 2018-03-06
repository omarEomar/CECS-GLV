package com.beshara.csc.inf.business.integration.eservices.eservicesdocument;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.csc.inf.business.deploy.InfEservicesDocumentTypesSession;
import com.beshara.csc.inf.business.dto.InfEservicesDocumentTypesDTO;
import com.beshara.csc.inf.business.integration.eservices.eservicesdocument.dto.EservicesDocumentDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.jws.WebService;

import weblogic.wsee.wstx.wsat.Transactional;


@Stateless(name = "EservicesDocument", mappedName = "EservicesDocument")

@WebService(endpointInterface =
            "com.beshara.csc.inf.business.integration.eservices.eservicesdocument.IEservicesDocument")
@Transactional
public class EservicesDocumentImpl extends EServiceImpl implements IEservicesDocument {
    @EJB
    private InfEservicesDocumentTypesSession infEservicesDocumentTypesSession;

    public EservicesDocumentImpl() {
    }

    public List<EservicesDocumentDTO> getDocumentsByServicesId(Long servicesId) throws DataBaseException,
                                                                                       SharedApplicationException {
        List<EservicesDocumentDTO> eservicesDocumentDTOList = new ArrayList<EservicesDocumentDTO>();
        List<IBasicDTO> list;
        try {
            list = infEservicesDocumentTypesSession.getByServicesId(RI(), servicesId);
        } catch (RemoteException e) {
            list = new ArrayList<IBasicDTO>();
        }
        for (IBasicDTO dto : list) {
            EservicesDocumentDTO eservicesDocumentDTO = new EservicesDocumentDTO();
            InfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO = (InfEservicesDocumentTypesDTO)dto;
            eservicesDocumentDTO.setDocTypeCode(Long.valueOf(infEservicesDocumentTypesDTO.getInfDocumentTypesDTO().getCode().getKey()));
            eservicesDocumentDTO.setDocTypeName(infEservicesDocumentTypesDTO.getInfDocumentTypesDTO().getName());
            eservicesDocumentDTO.setServicesId(Long.valueOf(infEservicesDocumentTypesDTO.getInfEservicesTypesDTO().getCode().getKey()));
            eservicesDocumentDTO.setServicesName(infEservicesDocumentTypesDTO.getInfEservicesTypesDTO().getName());
            eservicesDocumentDTO.setAttachmentRequiredFlag(infEservicesDocumentTypesDTO.getAttachmentRequiredFlag());
            eservicesDocumentDTOList.add(eservicesDocumentDTO);
        }
        return eservicesDocumentDTOList;
    }

    public List<EservicesDocumentDTO> getAllDocuments() throws DataBaseException, SharedApplicationException {
        List<EservicesDocumentDTO> eservicesDocumentDTOList = new ArrayList<EservicesDocumentDTO>();
        List<IBasicDTO> list;
        try {
            list = infEservicesDocumentTypesSession.getAll(RI());
        } catch (RemoteException e) {
            list = new ArrayList<IBasicDTO>();
        }
        for (IBasicDTO dto : list) {
            EservicesDocumentDTO eservicesDocumentDTO = new EservicesDocumentDTO();
            InfEservicesDocumentTypesDTO infEservicesDocumentTypesDTO = (InfEservicesDocumentTypesDTO)dto;
            eservicesDocumentDTO.setDocTypeCode(Long.valueOf(infEservicesDocumentTypesDTO.getInfDocumentTypesDTO().getCode().getKey()));
            eservicesDocumentDTO.setDocTypeName(infEservicesDocumentTypesDTO.getInfDocumentTypesDTO().getName());
            eservicesDocumentDTO.setServicesId(Long.valueOf(infEservicesDocumentTypesDTO.getInfEservicesTypesDTO().getCode().getKey()));
            eservicesDocumentDTO.setServicesName(infEservicesDocumentTypesDTO.getInfEservicesTypesDTO().getName());
            eservicesDocumentDTO.setAttachmentRequiredFlag(infEservicesDocumentTypesDTO.getAttachmentRequiredFlag());
            eservicesDocumentDTOList.add(eservicesDocumentDTO);
        }
        return eservicesDocumentDTOList;
    }

}
