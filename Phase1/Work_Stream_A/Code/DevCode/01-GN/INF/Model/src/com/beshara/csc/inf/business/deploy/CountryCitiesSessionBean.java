package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.CountryCitiesDAO;
import com.beshara.csc.inf.business.dto.CountryCitiesDTO;
import com.beshara.csc.inf.business.dto.ICountryCitiesDTO;
import com.beshara.csc.inf.business.entity.CountryCitiesEntity;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.ICountryCitiesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.inf.CountryAlreadyHasCapital;
import com.beshara.csc.sharedutils.business.util.constants.IInfConstants;

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
@Stateless(name = "CountryCitiesSession", mappedName = "Inf-Model-CountryCitiesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class CountryCitiesSessionBean extends InfBaseSessionBean implements CountryCitiesSession {
    public CountryCitiesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return CountryCitiesEntity.class;
    }

    @Override
    protected CountryCitiesDAO DAO() {
        return (CountryCitiesDAO)super.DAO();
    }

    public List<IBasicDTO> getCities(IRequestInfoDTO ri, Long countryCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return DAO().getCities(countryCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    /**
     * @param _countryCitiesDTO
     * @return ICountryCitiesDTO
     */
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO _countryCitiesDTO) throws DataBaseException,
                                                                                 SharedApplicationException {
        /*
         * check duplicated name
         */
        if (isNameExist(_countryCitiesDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        ICountryCitiesDTO countryCitiesDTO = (ICountryCitiesDTO)_countryCitiesDTO;
        if (countryCitiesDTO.getCapitalFlag().equals(IInfConstants.CAPITAL_FLAG)) {
            validateHasCapital(ri, ((ICountriesEntityKey)countryCitiesDTO.getCountriesDTO().getCode()).getCntryCode());
        }
        return super.add(ri, countryCitiesDTO);
    }

    private boolean isNameExist(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            CountryCitiesDTO dto = (CountryCitiesDTO)basicDTO;
            return (DAO().getByName(dto.getName()) != null);
        } catch (ItemNotFoundException e) {
            return true;
        }
    }

    private boolean isNameExistForEdit(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            CountryCitiesDTO _dto = (CountryCitiesDTO)basicDTO;
            IBasicDTO oldDTO = DAO().getByName(_dto.getName());
            if (oldDTO == null) {
                return false;
            } else if (_dto.getCntrycityName().equals(oldDTO.getName())) {
                return false;
            } else if (oldDTO != null) {
                return true;
            }

        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }

    /**
     * @param _countryCitiesDTO
     * @return Boolean
     */
    public Boolean update(IRequestInfoDTO ri, IBasicDTO _countryCitiesDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        /*
         * check for duplicated
         */
        if (isNameExistForEdit(_countryCitiesDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        ICountryCitiesDTO countryCitiesDTO = (ICountryCitiesDTO)_countryCitiesDTO;
        if (countryCitiesDTO.getCapitalFlag().equals(IInfConstants.CAPITAL_FLAG)) {
            ICountryCitiesDTO capital = null;
            try {
                capital =
                        (ICountryCitiesDTO)DAO().getCapital(((ICountriesEntityKey)countryCitiesDTO.getCountriesDTO().getCode()).getCntryCode());
                if (!((ICountryCitiesEntityKey)capital.getCode()).getCntrycityCode().equals(((ICountryCitiesEntityKey)countryCitiesDTO.getCode()).getCntrycityCode())) {
                    throw new CountryAlreadyHasCapital();
                }
            } catch (ItemNotFoundException e) {
                ;
            }
        }
        return super.update(ri, countryCitiesDTO);
    }

    public IBasicDTO getCapital(IRequestInfoDTO ri, Long countryCode) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            return DAO().getCapital(countryCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    public void validateHasCapital(IRequestInfoDTO ri, Long countryCode) throws DataBaseException,
                                                                                SharedApplicationException {

        try {
            DAO().getCapital(countryCode);
            throw new CountryAlreadyHasCapital();
        } catch (ItemNotFoundException e) {
            ;
        }
    }

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Long cntryCode,
                                        Long cntrycityCode) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().searchByCode(cntryCode, cntrycityCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }

    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, Long cntryCode,
                                        String cntrycityName) throws DataBaseException, SharedApplicationException {

        try {
            return DAO().searchByName(cntryCode, cntrycityName);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }

    }
}
