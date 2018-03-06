package com.beshara.csc.inf.business.integration.eservices.infkwcitizen.dto;


import com.beshara.csc.inf.business.client.IKwtCitizensResidentsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwStreetsDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IGenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;

import java.sql.Date;

public class KwtCitizensResidentsMapper {
    public KwtCitizensResidentsMapper() {
        super();
    }

    public static IKwtCitizensResidentsDTO kwtCitizensResidentsDTOMapping(KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO) {
        IKwtCitizensResidentsClient kwtCitizensResidentsClient = InfClientFactory.getKwtCitizensResidentsClient();
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = new KwtCitizensResidentsDTO();
        try {
            kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)kwtCitizensResidentsClient.getCitizenInformation(kwtCitizensResidentsEserviceDTO.getCivilId());
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        kwtCitizensResidentsDTO.setMapCode(kwtCitizensResidentsEserviceDTO.getMapCode());
        kwtCitizensResidentsDTO.setStreetCode(kwtCitizensResidentsEserviceDTO.getStreetCode());
        kwtCitizensResidentsDTO.setAddressInDetails(kwtCitizensResidentsEserviceDTO.getAddressInDetails());
        kwtCitizensResidentsDTO.setBuildingNo(kwtCitizensResidentsEserviceDTO.getBuildingNo());
        kwtCitizensResidentsDTO.setFlatNo(kwtCitizensResidentsEserviceDTO.getFlatNo());
        kwtCitizensResidentsDTO.setFloorNo(kwtCitizensResidentsEserviceDTO.getFloorNo());
        kwtCitizensResidentsDTO.setEMail(kwtCitizensResidentsEserviceDTO.getEMail());
        kwtCitizensResidentsDTO.setPhonesNo(kwtCitizensResidentsEserviceDTO.getPhonesNo());
        kwtCitizensResidentsDTO.setMobileNo(kwtCitizensResidentsEserviceDTO.getMobileNo());
        return kwtCitizensResidentsDTO;
    }

    public static KwtCitizensResidentsEserviceDTO getKwtCitizensResidentsEserviceDTO(IKwtCitizensResidentsDTO kwtCitizensResidentsDTO) {
        KwtCitizensResidentsEserviceDTO kwtCitizensResidentsEserviceDTO = new KwtCitizensResidentsEserviceDTO();
        kwtCitizensResidentsEserviceDTO.setCivilId(kwtCitizensResidentsDTO.getCivilId());
        kwtCitizensResidentsEserviceDTO.setFirstName(kwtCitizensResidentsDTO.getFirstName());
        kwtCitizensResidentsEserviceDTO.setSecondName(kwtCitizensResidentsDTO.getSecondName());
        kwtCitizensResidentsEserviceDTO.setThirdName(kwtCitizensResidentsDTO.getThirdName());
        kwtCitizensResidentsEserviceDTO.setLastName(kwtCitizensResidentsDTO.getLastName());
        kwtCitizensResidentsEserviceDTO.setFamilyName(kwtCitizensResidentsDTO.getFamilyName());
        kwtCitizensResidentsEserviceDTO.setEnglishName(kwtCitizensResidentsDTO.getEnglishName());
        kwtCitizensResidentsEserviceDTO.setFullNameColumn(kwtCitizensResidentsDTO.getName());
        if (kwtCitizensResidentsDTO.getGenderTypesDTO() != null) {
            kwtCitizensResidentsEserviceDTO.setGencntryName(kwtCitizensResidentsDTO.getGenderTypesDTO().getName());
            kwtCitizensResidentsEserviceDTO.setGentypeCode(kwtCitizensResidentsDTO.getGentypeCode());
        }
        if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
            kwtCitizensResidentsEserviceDTO.setReligionName(kwtCitizensResidentsDTO.getReligionsDTO().getName());
            kwtCitizensResidentsEserviceDTO.setReligionCode(kwtCitizensResidentsDTO.getReligionCode());
        }
        if (kwtCitizensResidentsDTO.getBirthDate() != null) {
            kwtCitizensResidentsEserviceDTO.setBirthDate(new Date(kwtCitizensResidentsDTO.getBirthDate().getTime()));
        }
        if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
            kwtCitizensResidentsEserviceDTO.setMarstatusCode(kwtCitizensResidentsDTO.getMaritalStatusDTO().getMarstatusCode());
            kwtCitizensResidentsEserviceDTO.setMarstatusName(kwtCitizensResidentsDTO.getMaritalStatusDTO().getMarstatusName());
        }
        if (kwtCitizensResidentsDTO.getCapstatusCode() != null) {
            kwtCitizensResidentsEserviceDTO.setMarstatusCode(kwtCitizensResidentsDTO.getMaritalStatusDTO().getMarstatusCode());

        }
        if (kwtCitizensResidentsDTO.getCountriesDTO() != null) {
            kwtCitizensResidentsEserviceDTO.setNationality(((IGenderCountryEntityKey)kwtCitizensResidentsDTO.getCountriesDTO().getCode()).getCntryCode());
            kwtCitizensResidentsEserviceDTO.setNationalityName(kwtCitizensResidentsDTO.getCountriesDTO().getName());
        }


        if (kwtCitizensResidentsDTO.getKwMapDTO() != null) {
            if (kwtCitizensResidentsDTO.getKwMapDTO().getTreeLevel().equals(1L)) {
                kwtCitizensResidentsEserviceDTO.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getKwMapDTO().getTreeLevel().equals(2L)) {
                kwtCitizensResidentsEserviceDTO.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                kwtCitizensResidentsEserviceDTO.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getKwMapDTO().getTreeLevel().equals(3L)) {
                kwtCitizensResidentsEserviceDTO.setCityCode(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setCityName(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getName());
                kwtCitizensResidentsEserviceDTO.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                kwtCitizensResidentsEserviceDTO.setPieceCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                kwtCitizensResidentsEserviceDTO.setPieceName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
            }
        }

        kwtCitizensResidentsEserviceDTO.setMltstatusCode(kwtCitizensResidentsDTO.getMltstatusCode());
        kwtCitizensResidentsEserviceDTO.setCapstatusCode(kwtCitizensResidentsDTO.getCapstatusCode());
        kwtCitizensResidentsEserviceDTO.setMapCode(kwtCitizensResidentsDTO.getMapCode());
        kwtCitizensResidentsEserviceDTO.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
        if (kwtCitizensResidentsDTO.getStreetCode() != null) {
            IKwStreetsDTO kwStreetsDTO = null;
            try {
                kwStreetsDTO =
                        (IKwStreetsDTO)InfClientFactory.getKwStreetsClient().getById(InfEntityKeyFactory.createKwStreetsEntityKey(kwtCitizensResidentsDTO.getStreetCode()));
                if (kwStreetsDTO != null) {
                    kwtCitizensResidentsEserviceDTO.setStreetName(kwStreetsDTO.getName());
                }
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
        kwtCitizensResidentsEserviceDTO.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());
        kwtCitizensResidentsEserviceDTO.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
        kwtCitizensResidentsEserviceDTO.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
        kwtCitizensResidentsEserviceDTO.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
        kwtCitizensResidentsEserviceDTO.setEMail(kwtCitizensResidentsDTO.getEMail());
        kwtCitizensResidentsEserviceDTO.setPhonesNo(kwtCitizensResidentsDTO.getPhonesNo());
        kwtCitizensResidentsEserviceDTO.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
        kwtCitizensResidentsEserviceDTO.setMobileNo(kwtCitizensResidentsDTO.getMobileNo());
        return kwtCitizensResidentsEserviceDTO;
    }

}
