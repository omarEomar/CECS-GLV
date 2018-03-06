package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dao.CountriesDAO;
import com.beshara.csc.inf.business.dto.CountriesDTO;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.entity.CountriesEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntity;
import com.beshara.csc.inf.business.entity.GenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "CountriesSession", mappedName = "Inf-Model-CountriesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class CountriesSessionBean extends InfBaseSessionBean implements CountriesSession {

    /**
     * JobsSession Default Constructor */
    public CountriesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return CountriesEntity.class;
    }

    @Override
    protected CountriesDAO DAO() {
        return (CountriesDAO)super.DAO();
    }

    /**
     * @return List
     */
    @Override
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {
        List<ICountriesDTO> countriesDTOList = DAO().getAll();
        for (ICountriesDTO country : countriesDTOList) {
            ICountryCitiesDTO countryCitiesDTO = null;
            try {
                countryCitiesDTO =
                        (ICountryCitiesDTO)InfClientFactory.getCountryCitiesClient().getCapital(((ICountriesEntityKey)country.getCode()).getCntryCode());
            } catch (Exception e) {
                ;
            }
            if (countryCitiesDTO != null) {
                List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                list.add(countryCitiesDTO);
                country.setCountryCitiesDTOList(list);
            }
        }
        return (List)countriesDTOList;

    }

    /**
     * @param id
     * @return IBasicDTO
     */
    @Override
    public IBasicDTO getById(IEntityKey id) throws SharedApplicationException, DataBaseException {
        ICountriesDTO countriesDTO = (ICountriesDTO)DAO().getById(id);
        countriesDTO.setCountryCitiesDTOList((List)InfClientFactory.getCountryCitiesClient().getCities(((ICountriesEntityKey)countriesDTO.getCode()).getCntryCode()));
        return countriesDTO;
    }

    /**
     * Returns list of all countries Name and code only * @return List
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {
        return DAO().getCodeName();
    }

    @Override
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws SharedApplicationException,
                                                                                DataBaseException {
        List<ICountriesDTO> countriesDTOList = (List)DAO().searchByName(name);
        for (ICountriesDTO country : countriesDTOList) {
            ICountryCitiesDTO countryCitiesDTO = null;
            try {
                countryCitiesDTO =
                        (ICountryCitiesDTO)InfClientFactory.getCountryCitiesClient().getCapital(((ICountriesEntityKey)country.getCode()).getCntryCode());
            } catch (Exception e) {
                ;
            }
            if (countryCitiesDTO != null) {
                List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                list.add(countryCitiesDTO);
                country.setCountryCitiesDTOList(list);
            }
        }
        return (List)countriesDTOList;
    }

    @Override
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws SharedApplicationException,
                                                                                DataBaseException {
        List<ICountriesDTO> countriesDTOList = (List)DAO().searchByCode(code);
        for (ICountriesDTO country : countriesDTOList) {
            ICountryCitiesDTO countryCitiesDTO = null;
            try {
                countryCitiesDTO =
                        (ICountryCitiesDTO)InfClientFactory.getCountryCitiesClient().getCapital(((ICountriesEntityKey)country.getCode()).getCntryCode());
            } catch (Exception e) {
                ;
            }
            if (countryCitiesDTO != null) {
                List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                list.add(countryCitiesDTO);
                country.setCountryCitiesDTOList(list);
            }
        }
        return (List)countriesDTOList;

    }

    /**
     * return country without cities
     * @author I.Omar
     * */
    public List<IBasicDTO> searchCountries(IRequestInfoDTO ri, Long code,
                                           List<String> excludedList) throws SharedApplicationException,
                                                                             DataBaseException {
        return DAO().searchCountries(code, excludedList);
    }

    /**
     * return country without cities
     * @author I.Omar
     * */
    public List<IBasicDTO> searchCountries(IRequestInfoDTO ri, String name,
                                           List<String> excludedList) throws SharedApplicationException,
                                                                             DataBaseException {
        return DAO().searchCountries(name, excludedList);
    }

    /**
     * return country without excluded list
     * @author I.Omar
     * */
    public List<IBasicDTO> getAllWithoutExcludedList(IRequestInfoDTO ri,
                                                     List<String> excludedList) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getAllWithoutExcludedList(excludedList);
    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        if (DAO().checkDublicateName(basicDTO.getName()).size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        ICountriesDTO countriesDTO = (CountriesDTO)basicDTO;
        Long countryCode = 0L;
        try {
            countriesDTO = (CountriesDTO)super.add(ri, countriesDTO);
            countryCode = ((ICountriesEntityKey)(countriesDTO).getCode()).getCntryCode();
            for (IBasicDTO dto : countriesDTO.getGenderCountryDTOList()) {
                IGenderCountryDTO genderCountry = (IGenderCountryDTO)dto;
                genderCountry.setCode(new GenderCountryEntityKey(genderCountry.getGenderTypesDTO().getGentypeCode(),
                                                                 countryCode));
                genderCountry.setCountriesDTO(countriesDTO);
                super.newDAOInstance(GenderCountryEntity.class).add(genderCountry);
            }
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
            throw new DataBaseException("EnteredNameAlreadyExist");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countriesDTO;
    }

    @Override
    public Boolean update(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                         DataBaseException {
        List<IBasicDTO> nameList = DAO().checkDublicateName(basicDTO.getName());
        if (nameList != null && nameList.size() > 0) {
            if (!nameList.get(0).getCode().getKey().equals(basicDTO.getCode().getKey())) {
                throw new DataBaseException("EnteredNameAlreadyExist");
            }
        }
        ICountriesDTO countriesDTO = (CountriesDTO)basicDTO;
        try {
            super.update(ri, countriesDTO);

            for (IBasicDTO dto : countriesDTO.getGenderCountryDTOList()) {
                IGenderCountryDTO genderCountry = (IGenderCountryDTO)dto;
                super.newDAOInstance(GenderCountryEntity.class).update(genderCountry);

            }
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
        }
        return Boolean.TRUE;
    }

    @Override
    public List<IBasicDTO> getAllOrderByClass(IRequestInfoDTO ri) throws SharedApplicationException,
                                                                         DataBaseException {
        return DAO().getAllOrderByClass();
    }

    public IBasicDTO getCodeName(IRequestInfoDTO ri, IEntityKey key) throws DataBaseException,
                                                                            SharedApplicationException {
        return DAO().getCodeName(key);
    }

    
    public List<IBasicDTO> getCodeNameByQulKey(IRequestInfoDTO ri, String qulKey) throws SharedApplicationException,
                                                                         DataBaseException{
        return DAO().getCodeNameByQulKey(qulKey);
    }
}
