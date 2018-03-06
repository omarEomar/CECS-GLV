package com.beshara.csc.inf.business.integration.eservices.infkwcitizen;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;

import javax.ejb.Remote;

@Remote
public interface IKwtCitizensResidentsEservice extends IEService {
    public void update(KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO) throws DataBaseException,
                                                                                               SharedApplicationException;
    
    public KwtCitizensResidentsEserviceDTO getKwtCitizenInformation(Long civilId) throws DataBaseException,
                                                                                               SharedApplicationException;
    
    public KwtCitizensResidentsEserviceDTO getCitizenName(Long civilId) throws DataBaseException,
                                                                                               SharedApplicationException ;
    public KwtCitizensResidentsEserviceDTO getKwtCitizensResidentQuls(Long civilId);
}
