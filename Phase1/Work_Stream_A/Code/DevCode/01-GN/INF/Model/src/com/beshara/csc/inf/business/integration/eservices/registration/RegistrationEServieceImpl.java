package com.beshara.csc.inf.business.integration.eservices.registration;


import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.csc.inf.business.deploy.INFUsersSession;
import com.beshara.csc.inf.business.dto.UserDTO;
import com.beshara.csc.inf.business.integration.eservices.registration.dto.UserDTOEservice;
import com.beshara.csc.inf.business.integration.eservices.registration.dto.UserDTOMapper;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.jws.WebService;

import weblogic.wsee.wstx.wsat.Transactional;


@Stateless(name = "RegistrationEServiece", mappedName = "RegistrationEServiece")
@WebService(endpointInterface =
            "com.beshara.csc.inf.business.integration.eservices.registration.IRegistrationEServiece")
@Transactional
public class RegistrationEServieceImpl extends EServiceImpl implements IRegistrationEServiece {

    @EJB
    private INFUsersSession usersSessionBean;

    public RegistrationEServieceImpl() {
        super();
    }

    public UserDTOEservice getUserByCivilIdForPortal(Long civilid, Long isForPortal) throws DataBaseException,
                                                                                            SharedApplicationException {

        try {
            UserDTO userDTO;
            userDTO = usersSessionBean.getUserByCivilIdForPortal(RI(), civilid, isForPortal);
            if (userDTO != null) {
                UserDTOEservice userDTOEservice = UserDTOMapper.userDTOEserviceDTOMapping(userDTO);
                return userDTOEservice;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserDTOEservice UpdateUserForPortal(Long civilid, Long isForPortal) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            System.out.println("UpdateUserForPortalUpdateUserForPortalUpdateUserForPortalUpdateUserForPortal" +
                               isForPortal);
            usersSessionBean.UpdateUserForPortal(RI(), civilid, isForPortal);
            return getUserByCivilIdForPortal(civilid, isForPortal);
        } catch (Exception e) {
            return null;
        }
    }


}
