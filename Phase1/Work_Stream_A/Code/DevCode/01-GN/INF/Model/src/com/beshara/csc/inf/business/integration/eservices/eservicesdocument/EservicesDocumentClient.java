package com.beshara.csc.inf.business.integration.eservices.eservicesdocument;


import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.inf.business.integration.eservices.eservicesdocument.dto.EservicesDocumentDTO;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

public class EservicesDocumentClient extends EServiceClient {
    public EservicesDocumentClient() {
        super();
    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IEservicesDocument.class;
    }

    @Override
    protected IEservicesDocument SERVICE() {
        return (IEservicesDocument)super.SERVICE();
    }

    public List<EservicesDocumentDTO> getDocumentsByServicesId(Long servicesId) {
        try {
            return SERVICE().getDocumentsByServicesId(servicesId);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<EservicesDocumentDTO>();
    }

    public List<EservicesDocumentDTO> getAllDocuments() {
        try {
            return SERVICE().getAllDocuments();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<EservicesDocumentDTO>();
    }
}
