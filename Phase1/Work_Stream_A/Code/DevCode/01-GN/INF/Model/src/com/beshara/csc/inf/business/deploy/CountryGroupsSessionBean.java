package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.CountryGroupsDAO;
import com.beshara.csc.inf.business.dto.CountryGroupsDTO;
import com.beshara.csc.inf.business.entity.CountryGroupsEntity;
import com.beshara.csc.inf.business.entity.ICountryGroupsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
@Stateless(name = "CountryGroupsSession", mappedName = "Inf-Model-CountryGroupsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class CountryGroupsSessionBean extends InfBaseSessionBean implements CountryGroupsSession {

    /**
     * JobsSession Default Constructor */
    public CountryGroupsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return CountryGroupsEntity.class;
    }

    @Override
    protected CountryGroupsDAO DAO() {
        return (CountryGroupsDAO)super.DAO();
    }

    /**
     * @param countryGroupsDTO
     * @return ICountryGroupsDTO
     */

    private boolean isNameExist(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {

        try {
            if (((CountryGroupsDTO)basicDTO).getCountryGroupsDTO() != null) {

                IBasicDTO dto;
                Long parentCode =
                    ((ICountryGroupsEntityKey)((CountryGroupsDTO)basicDTO).getCountryGroupsDTO().getCode()).getCntrygrpCode();
                dto = DAO().getByNameForChildren(basicDTO.getName(), parentCode);
                return (dto != null);

            } else {
                return (DAO().getByName(basicDTO.getName()) != null);
            }


        } catch (ItemNotFoundException e) {
            return true;
        }
    }
    
    private boolean isNameExistForEdit(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {

        try {
            if (((CountryGroupsDTO)basicDTO).getCountryGroupsDTO() != null) {

                IBasicDTO dto;
                Long parentCode =
                    ((ICountryGroupsEntityKey)((CountryGroupsDTO)basicDTO).getCountryGroupsDTO().getCode()).getCntrygrpCode();
                dto = DAO().getByNameForChildren(basicDTO.getName(), parentCode);
                if(dto == null){
                       return false;
                }else if(((CountryGroupsDTO)basicDTO).getCntrygrpName().equals(dto.getName())){
                    return false;
                }else if(dto != null){
                    return true;
                }

            } else {
                IBasicDTO dto =DAO().getByName(basicDTO.getName());
                if(dto == null){
                       return false;
                }else if(((CountryGroupsDTO)basicDTO).getCntrygrpName().equals(dto.getName())){
                    return false;
                }else if(dto != null){
                    return true;
                }
                
            }


        } catch (ItemNotFoundException e) {
            return true;
        }
        return false;
    }
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO countryGroupsDTO) throws DataBaseException,
                                                                                SharedApplicationException {

        if (isNameExist(ri, countryGroupsDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.add(ri, countryGroupsDTO);
    }
    
    public Boolean update(IRequestInfoDTO ri, IBasicDTO countryGroupsDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        if (isNameExistForEdit(ri, countryGroupsDTO)) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }
        return super.update(ri, countryGroupsDTO);
    }
    
    /**
     * List all cats * @param
     * @return List of ICountryGroupsDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCats(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCats();
    }

    /**
     * List all groups by cat code * @param
     * @return List of ICountryGroupsDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getGroups(IRequestInfoDTO ri, Long catCode) throws DataBaseException,
                                                                              SharedApplicationException {
        return DAO().getGroups(catCode);
    }

    public List<IBasicDTO> searchByCode(Object catCode) throws DataBaseException, SharedApplicationException {
        return DAO().searchByCode(catCode);
    }

    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String grpName) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().searchByName(grpName);
    }

    public List<IBasicDTO> searchGrpByCode(IRequestInfoDTO ri, Long grpCode, Long catCode) throws DataBaseException,
                                                                                                  SharedApplicationException {
        return DAO().searchGrpByCode(grpCode, catCode);
    }

    public List<IBasicDTO> searchGrpByName(IRequestInfoDTO ri, Long catCode, String catName) throws DataBaseException,
                                                                                                    SharedApplicationException {
        return DAO().searchGrpByName(catCode, catName);
    }

  
}
