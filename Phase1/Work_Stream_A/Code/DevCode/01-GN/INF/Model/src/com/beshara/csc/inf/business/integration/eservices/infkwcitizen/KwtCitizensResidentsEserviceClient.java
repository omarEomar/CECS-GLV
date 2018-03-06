package com.beshara.csc.inf.business.integration.eservices.infkwcitizen;

import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;

public class KwtCitizensResidentsEserviceClient extends EServiceClient {
    public KwtCitizensResidentsEserviceClient() {
        super();
    }

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IKwtCitizensResidentsEservice.class;
    }

    protected IKwtCitizensResidentsEservice SERVICE() {
        return (IKwtCitizensResidentsEservice)super.SERVICE();
    }

    public void update(KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        SERVICE().update(kwtCitizensResidentsEserviceDTO);
    }

    public KwtCitizensResidentsEserviceDTO getKwtCitizenInformation(Long civilId) throws DataBaseException,
                                                                                         SharedApplicationException {
        return SERVICE().getKwtCitizenInformation(civilId);
    }

    public KwtCitizensResidentsEserviceDTO checkKwtCitizenInformation(Long civilId) throws DataBaseException,
                                                                                           SharedApplicationException {
        return SERVICE().getCitizenName(civilId);
    }

    public KwtCitizensResidentsEserviceDTO getKwtCitizensResidentQuls(Long civilId) throws DataBaseException,
                                                                                           SharedApplicationException {
        return SERVICE().getKwtCitizensResidentQuls(civilId);
}
}
