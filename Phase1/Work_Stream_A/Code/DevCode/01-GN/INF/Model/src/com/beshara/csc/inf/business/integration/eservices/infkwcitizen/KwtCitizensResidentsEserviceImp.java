package com.beshara.csc.inf.business.integration.eservices.infkwcitizen;


import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.csc.inf.business.deploy.KwtCitizensResidentsSession;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.integration.eservices.infkwcitizen.dto.KwtCitizensResidentsMapper;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless(name = "InfKwtCitizensResidentsEservice", mappedName = "InfKwtCitizensResidentsEservice")
public class KwtCitizensResidentsEserviceImp extends EServiceImpl implements IKwtCitizensResidentsEservice {

    @EJB
    private KwtCitizensResidentsSession kwtCitizensResidentsSession;

    public KwtCitizensResidentsEserviceImp() {
    }

    public void update(KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            kwtCitizensResidentsSession.update(RI(),
                                               KwtCitizensResidentsMapper.kwtCitizensResidentsDTOMapping(kwtCitizensResidentsEserviceDTO));
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
    
    public KwtCitizensResidentsEserviceDTO getKwtCitizenInformation(Long civilId) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            return KwtCitizensResidentsMapper.getKwtCitizensResidentsEserviceDTO((IKwtCitizensResidentsDTO)kwtCitizensResidentsSession.getCitizenInformation(RI(),civilId));
            } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
    
    public KwtCitizensResidentsEserviceDTO getCitizenName(Long civilId) throws DataBaseException, SharedApplicationException {
    IKwtCitizensResidentsDTO kwtCitizensResidentsDTO;
        return new KwtCitizensResidentsEserviceDTO();
//        try {
//             kwtCitizensResidentsDTO = (IKwtCitizensResidentsDTO)kwtCitizensResidentsSession.getCitizenName(civilId);
//            return KwtCitizensResidentsMapper.getKwtCitizensResidentsEserviceDTO(kwtCitizensResidentsDTO);
//        } catch (RemoteException e) {
//            throw new DataBaseException(e.getMessage());
//        }
        
    }
    public KwtCitizensResidentsEserviceDTO getKwtCitizensResidentQuls(Long civilId) {
          try {
              IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                  (IKwtCitizensResidentsDTO)kwtCitizensResidentsSession.getKwtCitizensResidentQuls(RI(), civilId);
              return KwtCitizensResidentsMapper.getKwtCitizensResidentsEserviceDTO(kwtCitizensResidentsDTO);
          } catch (Exception e) {
              return new KwtCitizensResidentsEserviceDTO();
          }
      }
}
