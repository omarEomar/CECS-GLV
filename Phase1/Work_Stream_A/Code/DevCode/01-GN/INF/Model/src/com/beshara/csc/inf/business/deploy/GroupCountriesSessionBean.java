package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.client.CountryCitiesClientImpl;
import com.beshara.csc.inf.business.client.GenderCountryClientImpl;
import com.beshara.csc.inf.business.client.ICountryCitiesClient;
import com.beshara.csc.inf.business.client.IGenderCountryClient;
import com.beshara.csc.inf.business.dao.GroupCountriesDAO;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.dto.ICountryGroupsDTO;
import com.beshara.csc.inf.business.dto.IGenderCountryDTO;
import com.beshara.csc.inf.business.dto.IGroupCountriesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.GroupCountriesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

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
@Stateless(name = "GroupCountriesSession", mappedName = "Inf-Model-GroupCountriesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class GroupCountriesSessionBean extends InfBaseSessionBean implements GroupCountriesSession {

    public GroupCountriesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return GroupCountriesEntity.class;
    }

    @Override
    protected GroupCountriesDAO DAO() {
        return (GroupCountriesDAO)super.DAO();
    }

    public List<IBasicDTO> addCountries(IRequestInfoDTO ri, IBasicDTO countryGroupDTO,
                                        List<IBasicDTO> countriesDTOList) throws DataBaseException,
                                                                                 SharedApplicationException {
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        for (IBasicDTO countriesDTO : countriesDTOList) {
            IGroupCountriesDTO groupCountriesDTO = InfDTOFactory.createGroupCountriesDTO();
            groupCountriesDTO.setCountriesDTO((ICountriesDTO)countriesDTO);
            groupCountriesDTO.setCountryGroupsDTO((ICountryGroupsDTO)countryGroupDTO);
            groupCountriesDTO = (IGroupCountriesDTO)super.add(ri, groupCountriesDTO);
            list.add(groupCountriesDTO);
        }
        return list;
    }

    public List<IBasicDTO> getCountries(IRequestInfoDTO ri, Long groupCode) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            List<IGroupCountriesDTO> groupCountriesDTOList = (List)DAO().getCountries(groupCode);
            for (IGroupCountriesDTO groupCountriesDTO : groupCountriesDTOList) {
                ICountryCitiesDTO countryCitiesDTO = null;
                List<IGenderCountryDTO> genderCountryDTOList = null;
                try {
                    ICountryCitiesClient countryCitiesClient = new CountryCitiesClientImpl();
                    countryCitiesDTO =
                            (ICountryCitiesDTO)countryCitiesClient.getCapital(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                } catch (NoResultException e) {
                    e.printStackTrace();
                }
                if (countryCitiesDTO != null) {
                    List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                    list.add(countryCitiesDTO);
                    groupCountriesDTO.getCountriesDTO().setCountryCitiesDTOList(list);
                }
                try {
                    IGenderCountryClient genderCountryClient = new GenderCountryClientImpl();
                    genderCountryDTOList =
                            (List)genderCountryClient.getGenderNames(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                    if (genderCountryDTOList.size() == 1) {
                        Long typeCode =
                            ((IGenderTypesEntityKey)(genderCountryDTOList.get(0).getGenderTypesDTO().getCode())).getGentypeCode();
                        IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO();
                        if (typeCode.equals(ISystemConstant.GENDER_MALE)) {
                            genderCountryDTOList.add(genderCountryDTOList.get(0));
                            genderCountryDTOList.set(0, genderCountryDTO);
                        } else {
                            genderCountryDTOList.add(genderCountryDTO);
                        }
                    }
                } catch (NoResultException e) {
                    ;
                }
                if (genderCountryDTOList != null) {
                    groupCountriesDTO.getCountriesDTO().setGenderCountryDTOList(genderCountryDTOList);
                }
            }
            return new ArrayList<IBasicDTO>(groupCountriesDTOList);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    /**
     * list available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getAvailableCountries(IRequestInfoDTO ri, Long catCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return DAO().getAvailableCountries(catCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    /**
     * search available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchAvailableCountriesByCode(IRequestInfoDTO ri, Long catCode,
                                                          Long countryCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return DAO().searchAvailableCountriesByCode(catCode, countryCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    /**
     * search available countries for group * @param catCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchAvailableCountriesByName(IRequestInfoDTO ri, Long catCode,
                                                          String countryName) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return DAO().searchAvailableCountriesByName(catCode, countryName);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    /**
     * search group countries by group code country code * @param groupCode
     * @param countryCode
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Long groupCode, Long countryCode) throws DataBaseException,
                                                                                                     SharedApplicationException {
        List<IGroupCountriesDTO> groupCountriesDTOList;
        try {
            groupCountriesDTOList = (List)DAO().searchByCode(groupCode, countryCode);

            for (IGroupCountriesDTO groupCountriesDTO : groupCountriesDTOList) {
                ICountryCitiesDTO countryCitiesDTO = null;
                List<IGenderCountryDTO> genderCountryDTOList = null;

                try {
                    ICountryCitiesClient countryCitiesClient = new CountryCitiesClientImpl();
                    countryCitiesDTO =
                            (ICountryCitiesDTO)countryCitiesClient.getCapital(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                } catch (DataBaseException e) {
                } catch (SharedApplicationException e) {
                }
                if (countryCitiesDTO != null) {
                    List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                    list.add(countryCitiesDTO);
                    groupCountriesDTO.getCountriesDTO().setCountryCitiesDTOList(list);
                }
                try {
                    IGenderCountryClient genderCountryClient = new GenderCountryClientImpl();
                    genderCountryDTOList =
                            (List)genderCountryClient.getGenderNames(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                } catch (DataBaseException e) {
                } catch (SharedApplicationException e) {
                }
                if (genderCountryDTOList != null && genderCountryDTOList.size() == 1) {
                    Long typeCode =
                        ((IGenderTypesEntityKey)(genderCountryDTOList.get(0).getGenderTypesDTO().getCode())).getGentypeCode();
                    IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO();
                    if (typeCode.equals(ISystemConstant.GENDER_MALE)) {
                        genderCountryDTOList.add(genderCountryDTOList.get(0));
                        genderCountryDTOList.set(0, genderCountryDTO);
                    } else {
                        genderCountryDTOList.add(genderCountryDTO);
                    }
                }
                if (genderCountryDTOList != null) {
                    groupCountriesDTO.getCountriesDTO().setGenderCountryDTOList(genderCountryDTOList);
                }
            }
            return new ArrayList<IBasicDTO>(groupCountriesDTOList);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }


    /**
     * search group countries by group code country name * @param groupCode
     * @param countryName
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, Long groupCode,
                                        String countryName) throws DataBaseException, SharedApplicationException {

        List<IGroupCountriesDTO> groupCountriesDTOList;
        try {
            groupCountriesDTOList = (List)DAO().searchByName(groupCode, countryName);

            for (IGroupCountriesDTO groupCountriesDTO : groupCountriesDTOList) {
                ICountryCitiesDTO countryCitiesDTO = null;
                List<IGenderCountryDTO> genderCountryDTOList = null;

                try {
                    ICountryCitiesClient countryCitiesClient = new CountryCitiesClientImpl();
                    countryCitiesDTO =
                            (ICountryCitiesDTO)countryCitiesClient.getCapital(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
                if (countryCitiesDTO != null) {
                    List<ICountryCitiesDTO> list = new ArrayList<ICountryCitiesDTO>();
                    list.add(countryCitiesDTO);
                    groupCountriesDTO.getCountriesDTO().setCountryCitiesDTOList(list);
                }
                try {
                    IGenderCountryClient genderCountryClient = new GenderCountryClientImpl();
                    genderCountryDTOList =
                            (List)genderCountryClient.getGenderNames(((ICountriesEntityKey)groupCountriesDTO.getCountriesDTO().getCode()).getCntryCode());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
                if (genderCountryDTOList != null && genderCountryDTOList.size() == 1) {
                    Long typeCode =
                        ((IGenderTypesEntityKey)(genderCountryDTOList.get(0).getGenderTypesDTO().getCode())).getGentypeCode();
                    IGenderCountryDTO genderCountryDTO = InfDTOFactory.createGenderCountryDTO();
                    if (typeCode.equals(ISystemConstant.GENDER_MALE)) {
                        genderCountryDTOList.add(genderCountryDTOList.get(0));
                        genderCountryDTOList.set(0, genderCountryDTO);
                    } else {
                        genderCountryDTOList.add(genderCountryDTO);
                    }
                }
                if (genderCountryDTOList != null) {
                    groupCountriesDTO.getCountriesDTO().setGenderCountryDTOList(genderCountryDTOList);
                }
            }
            return new ArrayList<IBasicDTO>(groupCountriesDTOList);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
}
