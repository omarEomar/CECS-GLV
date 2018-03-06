package com.beshara.csc.inf.business.dto;

import java.util.List;


public interface ICountriesDTO extends IInfDTO {
    public void setLanguagesDTO(ILanguagesDTO languagesDTO);

    public ILanguagesDTO getLanguagesDTO();

    public void setCurrenciesDTO(ICurrenciesDTO currenciesDTO);

    public ICurrenciesDTO getCurrenciesDTO();

    public void setCountryCitiesDTOList(List<ICountryCitiesDTO> countryCitiesDTOList);

    public List<ICountryCitiesDTO> getCountryCitiesDTOList();

    public void setGenderCountryDTOList(List<IGenderCountryDTO> genderCountryDTOList);

    public List<IGenderCountryDTO> getGenderCountryDTOList();

    public String getLanguageKey();

    public void setLanguageKey(String key);

    public String getCurrencyKey();

    public void setCurrencyKey(String key);

    public void setCtyclassCode(Long ctyclassCode);

    public Long getCtyclassCode();

    public void setCntryCapitalName(String cntryCapitalName);

    public String getCntryCapitalName();
}
