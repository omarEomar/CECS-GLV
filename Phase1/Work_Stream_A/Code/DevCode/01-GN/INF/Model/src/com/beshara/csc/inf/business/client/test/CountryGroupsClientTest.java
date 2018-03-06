package com.beshara.csc.inf.business.client.test;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.ICountriesClient;
import com.beshara.csc.inf.business.client.ICountryCitiesClient;
import com.beshara.csc.inf.business.client.ICountryGroupsClient;
import com.beshara.csc.inf.business.client.ICurrenciesClient;
import com.beshara.csc.inf.business.client.IGenderCountryClient;
import com.beshara.csc.inf.business.client.IGroupCountriesClient;
import com.beshara.csc.inf.business.client.IHandicapStatusClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.ICurrenciesDTO;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.dto.ILanguagesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

public class CountryGroupsClientTest {
    ICountryGroupsClient client;
    ICountriesClient countriesClient;
    ICountryCitiesClient countryCitiesClient;
    IHandicapStatusClient handicapStatusClient;
    IGenderCountryClient genderCountryClient;
    IGroupCountriesClient groupCountriesClient;
    ICurrenciesClient currenciesClient;

    public CountryGroupsClientTest() {
        client = InfClientFactory.getCountryGroupsClient();
        countriesClient = InfClientFactory.getCountriesClient();
        countryCitiesClient = InfClientFactory.getCountryCitiesClient();
        handicapStatusClient = InfClientFactory.getHandicapStatusClient();
        genderCountryClient = InfClientFactory.getGenderCountryClient();
        groupCountriesClient = InfClientFactory.getGroupCountriesClient();
        currenciesClient = InfClientFactory.getCurrenciesClient();
    }

    public static void main(String[] args) {
        CountryGroupsClientTest test = new CountryGroupsClientTest();
        try { //test.getCats ( ) ; 
            //test.getGroups ( ) ; 
            // test.getCities ( ) ; 
            //test.getCapital ( ) ; 
            // test.getAll ( ) ; 
            //test.getById ( ) ; 
            //test.add ( ) ; 
            //test.update ( ) ; 
            //test.validateHasCapital ( ) ; 
            // test.addCountryCities ( ) ; 
            //test.addHandicapStatus ( ) ; 
            // test.searchCountryGroups ( ) ; 
            // test.getGenderNames ( ) ; 
            //test.getCountries ( ) ; 
            //test.getCurrenciesCodeName ( ) ; 
            //test.searchCountryByCode ( ) ; 
            //test.getAllCountries ( ) ; 
            //test.addGroupCountries ( ) ; 
            // test.getAvailableCountries ( ) ; 
            //test.searchAvailableCountriesByCode ( ) ; 
            //test.seachAvailableCountriesByName ( ) ; 
            test.addCountryCities();
        } catch (SharedApplicationException e) { // TODO 
        } catch (DataBaseException e) { // TODO 
        }
    }

    void getCats() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = client.getCats();
        System.out.println("" + list);
    }

    void getGroups() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = client.getGroups(1L);
        System.out.println("" + list);
    }

    void getCities() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = countryCitiesClient.getCities(101L);
        System.out.println("" + list);
    }

    void getCapital() throws DataBaseException, SharedApplicationException {
        IBasicDTO capital = countryCitiesClient.getCapital(101L);
        System.out.println("" + capital);
    }

    void getAll() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = countriesClient.getAll();
        System.out.println("" + list);
    }

    void getById() throws DataBaseException, SharedApplicationException {
        IBasicDTO dto = 
            countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(1001L));
        System.out.println("" + dto);
    }

    void add() throws DataBaseException, SharedApplicationException {
        ILanguagesDTO lang = InfDTOFactory.createLanguagesDTO();
        lang.setCode(InfEntityKeyFactory.createLanguagesEntityKey(1L));
        ICurrenciesDTO cur = InfDTOFactory.createCurrenciesDTO();
        cur.setCode(InfEntityKeyFactory.createCurrenciesEntityKey(1L));
        ICountriesDTO country = InfDTOFactory.createCountriesDTO();
        country.setName("test1");
        country.setLanguagesDTO(lang);
        country.setCurrenciesDTO(cur);
        IBasicDTO dto = countriesClient.add(country);
        System.out.println("" + dto);
    }

    void update() throws DataBaseException, SharedApplicationException {
        ICountriesDTO country = 
            (ICountriesDTO)countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(809L));
        ILanguagesDTO lang = InfDTOFactory.createLanguagesDTO();
        lang.setCode(InfEntityKeyFactory.createLanguagesEntityKey(1L));
        country.setLanguagesDTO(lang);
        countriesClient.update(country);
    }

    void validateHasCapital() throws DataBaseException, 
                                     SharedApplicationException {
        countryCitiesClient.validateHasCapital(102L);
    }

    void addCountryCities() throws DataBaseException, 
                                   SharedApplicationException {
        ICountryCitiesDTO dto = InfDTOFactory.createCountryCitiesDTO();
        ICountriesDTO country = 
            (ICountriesDTO)countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(107L));
        dto.setCountriesDTO(country);
        dto.setName("new city");
        dto.setCapitalFlag(1L);
        IBasicDTO addedDTO = countryCitiesClient.add(dto);
        System.out.println("" + addedDTO);
    }

    void addHandicapStatus() throws DataBaseException, 
                                    SharedApplicationException {
        IHandicapStatusDTO dto = InfDTOFactory.createHandicapStatusDTO();
        dto.setName("testtt");
        dto.setHandicapRatio(20D);
        IBasicDTO dto1 = handicapStatusClient.add(dto);
        System.out.println("" + dto1);
    }

    void searchCountryGroups() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> list = client.searchByName("%xc%");
        System.out.println("" + list);
    }

    void getGenderNames() throws DataBaseException, 
                                 SharedApplicationException {
        List<IBasicDTO> list = genderCountryClient.getGenderNames(101L);
        System.out.println("" + list);
    }

    void getCountries() throws DataBaseException, SharedApplicationException {
        List<IBasicDTO> list = groupCountriesClient.getCountries(101L);
        System.out.println("" + list);
    }

    void getCurrenciesCodeName() throws DataBaseException, 
                                        SharedApplicationException {
        List<IBasicDTO> list = currenciesClient.getCodeName();
        System.out.println("" + list);
    }

    void searchCountryByCode() throws DataBaseException, 
                                      SharedApplicationException {
        List<IBasicDTO> list = countriesClient.searchByCode(101L);
        System.out.println("" + list);
    }

    void getAllCountries() throws DataBaseException, 
                                  SharedApplicationException {
        IBasicDTO list = 
            countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(810L));
        System.out.println("" + list);
    }

    void addGroupCountries() throws DataBaseException, 
                                    SharedApplicationException {
        IBasicDTO country1 = 
            countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(501L));
        IBasicDTO country2 = 
            countriesClient.getById(InfEntityKeyFactory.createCountriesEntityKey(502L));
        List<IBasicDTO> countryList = new ArrayList<IBasicDTO>();
        countryList.add(country1);
        countryList.add(country2);
        IBasicDTO countryGroup = 
            client.getById(InfEntityKeyFactory.createCountryGroupsEntityKey(103L));
////        Boolean b = 
////            groupCountriesClient.addCountries(countryGroup, countryList);
//        System.out.println("" + b);
    }

    void getAvailableCountries() throws DataBaseException, 
                                        SharedApplicationException {
        List<IBasicDTO> list = 
            groupCountriesClient.getAvailableCountries(101L);
        System.out.println("" + list);
    }

    void searchAvailableCountriesByCode() throws DataBaseException, 
                                                 SharedApplicationException {
        List<IBasicDTO> list = 
            groupCountriesClient.searchAvailableCountriesByCode(103L, 501L);
        System.out.println("" + list);
    }

    void seachAvailableCountriesByName() throws DataBaseException, 
                                                SharedApplicationException {
        List<IBasicDTO> list = 
            groupCountriesClient.searchAvailableCountriesByName(101L, "%n%");
        System.out.println("" + list);
    }
}
