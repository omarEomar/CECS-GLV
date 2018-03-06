package com.beshara.csc.inf.business.integration.eservices.eservicesdocument;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.inf.business.integration.eservices.eservicesdocument.dto.EservicesDocumentDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;

import javax.jws.WebService;


@Remote
@WebService
public interface IEservicesDocument extends IEService {
    public List<EservicesDocumentDTO> getDocumentsByServicesId(Long servicesId) throws DataBaseException,
                                                                                       SharedApplicationException;

    public List<EservicesDocumentDTO> getAllDocuments() throws DataBaseException, SharedApplicationException;
}
