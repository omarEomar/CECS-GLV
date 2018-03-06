package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.CountriesEntityKey;
import com.beshara.csc.inf.business.entity.CurrenciesEntityKey;
import com.beshara.csc.inf.business.entity.LanguagesEntityKey;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.util.List;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class CountriesDTO extends InfDTO implements ICountriesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    // private Long cntryCode ;
    private String cntryCapitalName;
    // private String cntryName ;
    //private Long languageCode ;
    private Long ctyclassCode;
    private ILanguagesDTO languagesDTO;
    private ICurrenciesDTO currenciesDTO;
    private List<ICountryCitiesDTO> countryCitiesDTOList;
    private List<IGenderCountryDTO> genderCountryDTOList;

    /**
     * CountriesDTO Default Constructor */
    public CountriesDTO() {
    }

    public CountriesDTO(Long code, String name) {
        setCode(new CountriesEntityKey(code));
        setName(name);
    }

    /**
     * @param countriesEntity
     */
    public CountriesDTO(CountriesEntity countriesEntity) {
        setCode(new CountriesEntityKey(countriesEntity.getCntryCode()));
        setName(countriesEntity.getCntryName());
        // this.cntryCode = countriesEntity.getCntryCode ( ) ;
        // this.cntryName = countriesEntity.getCntryName ( ) ;
        if (countriesEntity.getLanguagesEntity() != null) {
            this.languagesDTO = new LanguagesDTO(countriesEntity.getLanguagesEntity());
        }
        if (countriesEntity.getCurrenciesEntity() != null) {
            this.currenciesDTO = new CurrenciesDTO(countriesEntity.getCurrenciesEntity());
        }
        this.setCreatedBy(countriesEntity.getCreatedBy());
        this.setCreatedDate(countriesEntity.getCreatedDate());
        this.setLastUpdatedBy(countriesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(countriesEntity.getLastUpdatedDate());
        this.setCtyclassCode(countriesEntity.getCtyclassCode());
        //this.languageCode = countriesEntity.getLanguageCode ( ) ;
    } // /**
    // * @return Long
    // */
    // public Long getCntryCode ( ) {
    // return cntryCode ;
    // }
    //
    // /**
    // * @return String
    // */
    // public String getCntryName ( ) {
    // return cntryName ;
    // }
    // /**
    // * @return Long
    // */
    // public Long getLanguageCode ( ) {
    // return languageCode ;
    // }
    // /**
    // * @param cntryCode
    // */
    // public void setCntryCode ( Long cntryCode ) {
    // this.cntryCode = cntryCode ;
    // }
    //
    // /**
    // * @param cntryName
    // */
    // public void setCntryName ( String cntryName ) {
    // this.cntryName = cntryName ;
    // }
    // /**
    // * @param languageCode
    // */
    // public void setLanguageCode ( Long languageCode ) {
    // this.languageCode = languageCode ;
    // }

    public void setLanguagesDTO(ILanguagesDTO languagesDTO) {
        this.languagesDTO = languagesDTO;
    }

    public ILanguagesDTO getLanguagesDTO() {
        return languagesDTO;
    }

    public void setCurrenciesDTO(ICurrenciesDTO currenciesDTO) {
        this.currenciesDTO = currenciesDTO;
    }

    public ICurrenciesDTO getCurrenciesDTO() {
        return currenciesDTO;
    }

    public void setCountryCitiesDTOList(List<ICountryCitiesDTO> countryCitiesDTOList) {
        this.countryCitiesDTOList = countryCitiesDTOList;
    }

    public List<ICountryCitiesDTO> getCountryCitiesDTOList() {
        return countryCitiesDTOList;
    }

    public void setGenderCountryDTOList(List<IGenderCountryDTO> genderCountryDTOList) {
        this.genderCountryDTOList = genderCountryDTOList;
    }

    public List<IGenderCountryDTO> getGenderCountryDTOList() {
        return genderCountryDTOList;
    }

    public String getLanguageKey() {
        try {
            if (this.languagesDTO != null)
                return (String)languagesDTO.getCode().getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setLanguageKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE + "")) {
            LanguagesEntityKey entityKey = new LanguagesEntityKey(Long.parseLong(key));
            this.languagesDTO = new LanguagesDTO();
            this.languagesDTO.setCode(entityKey);
            this.languagesDTO.setLanguageCode(Long.parseLong(key));
        } else
            this.languagesDTO = null;
    }

    public String getCurrencyKey() {
        try {
            if (this.currenciesDTO != null)
                return (String)currenciesDTO.getCode().getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCurrencyKey(String key) {
        if (key != null && !key.equals(ISystemConstant.VIRTUAL_VALUE + "")) {
            CurrenciesEntityKey entityKey = new CurrenciesEntityKey(Long.parseLong(key));
            this.currenciesDTO = new CurrenciesDTO();
            this.currenciesDTO.setCode(entityKey);
            this.currenciesDTO.setCurrencyCode(Long.parseLong(key));
        } else
            this.currenciesDTO = null;
    }

    public void setCtyclassCode(Long ctyclassCode) {
        this.ctyclassCode = ctyclassCode;
    }

    public Long getCtyclassCode() {
        return ctyclassCode;
    }

    public void setCntryCapitalName(String cntryCapitalName) {
        this.cntryCapitalName = cntryCapitalName;
    }

    public String getCntryCapitalName() {
        if (countryCitiesDTOList != null && countryCitiesDTOList.size() > 0) {
            cntryCapitalName = countryCitiesDTOList.get(0).getName();
        }
        return cntryCapitalName;
    }
}
