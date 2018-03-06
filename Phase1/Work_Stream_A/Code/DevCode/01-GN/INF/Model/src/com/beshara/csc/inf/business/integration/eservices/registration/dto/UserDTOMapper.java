package com.beshara.csc.inf.business.integration.eservices.registration.dto;


import com.beshara.csc.inf.business.dto.UserDTO;


public class UserDTOMapper {
    public UserDTOMapper() {
        super();
    }

    public static UserDTOEservice userDTOEserviceDTOMapping(UserDTO userDTO) {
        UserDTOEservice userDTOEservice = new UserDTOEservice();
        userDTOEservice.setUserName(userDTO.getUserName());
        userDTOEservice.setCode(userDTO.getCode());
        userDTOEservice.setPassword(userDTO.getPassword());
        userDTOEservice.setPasswordExpiryDate(userDTO.getPasswordExpiryDate());
        userDTOEservice.setLocked(userDTO.getLocked());
        userDTOEservice.setDbUser(userDTO.getDbUser());
        userDTOEservice.setCivilNo(userDTO.getCivilNo());
//        userDTOEservice.setCreatedDate(userDTO.getCreatedDate());
        userDTOEservice.setCreatedBy(userDTO.getCreatedBy());
        userDTOEservice.setUserDeleted(userDTO.getUserDeleted());
        return userDTOEservice;
    }
}
