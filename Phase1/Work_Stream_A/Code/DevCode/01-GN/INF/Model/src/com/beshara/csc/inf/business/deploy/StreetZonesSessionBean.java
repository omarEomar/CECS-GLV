package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.StreetZonesDAO;
import com.beshara.csc.inf.business.dto.IKwStreetsDTO;
import com.beshara.csc.inf.business.dto.IStreetZonesDTO;
import com.beshara.csc.inf.business.dto.KwStreetsDTO;
import com.beshara.csc.inf.business.dto.StreetZonesDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.StreetZonesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

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
@Stateless(name = "StreetZonesSession", mappedName = "Inf-Model-StreetZonesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(StreetZonesSession.class)
public class StreetZonesSessionBean extends InfBaseSessionBean implements StreetZonesSession { //@PersistenceContext ( unitName = "Inf" )

    public StreetZonesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return StreetZonesEntity.class;
    }

    @Override
    protected StreetZonesDAO DAO() {
        return (StreetZonesDAO)super.DAO();
    }

    @Override
    public List<IBasicDTO> getByMapCode(IRequestInfoDTO ri, String mapCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().getByMapCode(mapCode);
    }

    @Override
    public List<IBasicDTO> getNotSelectedByMapCode(IRequestInfoDTO ri, String mapCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().getNotSelectedByMapCode(mapCode);

    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, List<IBasicDTO> streetZonesDTOs, String mapCode) throws DataBaseException,
                                                                                                     SharedApplicationException {
        IKwStreetsDTO streetDTO = new KwStreetsDTO();
        IStreetZonesDTO streetZonesDTO = new StreetZonesDTO();
        try {
            //            for (int i = 0; i < selectedStreetZoneDTOList.size(); i++) {
            //                streetDTO = (IKwStreetsDTO)streetZonesDTOs.get(i);
            //                if (streetDTO.getChecked() != null && streetDTO.getChecked() == false) {
            //                    streetZonesDTO.setCode(InfEntityKeyFactory.createStreetZonesEntityKey(mapCode,
            //                                                                                          Long.valueOf(streetDTO.getCode().getKey())));
            //                    getDAO().remove(streetZonesDTO);
            //                }
            //            }
            for (int i = 0; i < streetZonesDTOs.size(); i++) {
                streetDTO = (IKwStreetsDTO)streetZonesDTOs.get(i);
                if (streetDTO.getChecked() != null && streetDTO.getChecked() == true) {
                    streetZonesDTO.setCode(InfEntityKeyFactory.createStreetZonesEntityKey(mapCode,
                                                                                          Long.valueOf(streetDTO.getCode().getKey())));
                    DAO().add(streetZonesDTO);
                }
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return streetDTO;
        } catch (DataBaseException e) {
            e.printStackTrace();
            return streetDTO;
        }
        return streetDTO;

    }

    @Override
    public IBasicDTO remove(IRequestInfoDTO ri, List<IBasicDTO> streetZonesDTOs, String mapCode) {
        IKwStreetsDTO streetDTO = new KwStreetsDTO();
        IStreetZonesDTO streetZonesDTO = new StreetZonesDTO();
        try {
            for (int i = 0; i < streetZonesDTOs.size(); i++) {
                streetDTO = (IKwStreetsDTO)streetZonesDTOs.get(i);
                if (streetDTO.getChecked() != null && streetDTO.getChecked() == true) {
                    streetZonesDTO.setCode(InfEntityKeyFactory.createStreetZonesEntityKey(mapCode,
                                                                                          Long.valueOf(streetDTO.getCode().getKey())));
                    DAO().remove(streetZonesDTO);
                }
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return streetDTO;
        } catch (DataBaseException e) {
            e.printStackTrace();
            return streetDTO;
        }
        return streetDTO;


    }

    /**
     * @param streetZonesDTO
     * @return Boolean
     */
    public Boolean update(IRequestInfoDTO ri, IBasicDTO streetZonesDTO) throws SharedApplicationException,
                                                                               DataBaseException {
        return DAO().update(streetZonesDTO);
    }

    /**
     * @param streetZonesDTO
     * @return Boolean
     */
    public Boolean remove(IRequestInfoDTO ri, IBasicDTO streetZonesDTO) throws SharedApplicationException,
                                                                               DataBaseException {
        return DAO().remove(streetZonesDTO);
    }

    /**
     * @param id
     * @return IStreetZonesDTO
     */
    public IBasicDTO getById(IRequestInfoDTO ri, IEntityKey id) throws SharedApplicationException, DataBaseException {
        return DAO().getById(id);
    }


    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException {
        return DAO().search(basicDTO);
    }


    public List<IBasicDTO> getNotSelectedByMapCode(String mapCode) throws SharedApplicationException,
                                                                          DataBaseException {
        return DAO().getNotSelectedByMapCode(mapCode);

    }

}
