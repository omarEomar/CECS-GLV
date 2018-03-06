package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.GenderReligionDAO;
import com.beshara.csc.inf.business.dao.ReligionsDAO;
import com.beshara.csc.inf.business.dto.IGenderReligionDTO;
import com.beshara.csc.inf.business.dto.ReligionsDTO;
import com.beshara.csc.inf.business.entity.GenderReligionEntity;
import com.beshara.csc.inf.business.entity.GenderReligionEntityKey;
import com.beshara.csc.inf.business.entity.IReligionsEntityKey;
import com.beshara.csc.inf.business.entity.ReligionsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.cementj.base.trans.TransactionException;


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
@Stateless(name = "ReligionsSession", mappedName = "Inf-Model-ReligionsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(ReligionsSession.class)
public class ReligionsSessionBean extends InfBaseSessionBean implements ReligionsSession {


    /**
     * JobsSession Default Constructor */
    public ReligionsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return ReligionsEntity.class;
    }

    @Override
    protected ReligionsDAO DAO() {
        return (ReligionsDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();

    }

    @Override
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        if (DAO().checkDublicateName(basicDTO.getName()).size() > 0) {
            throw new DataBaseException("EnteredNameAlreadyExist");
        }

        ReligionsDTO religionsDTO = (ReligionsDTO)basicDTO;
        Long religionCode = 0L;

        try {
            religionsDTO = (ReligionsDTO)DAO().add(religionsDTO);
            religionCode = ((IReligionsEntityKey)(religionsDTO).getCode()).getReligionCode();


            for (IBasicDTO dto : religionsDTO.getGenderReligionList()) {
                IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)dto;
                genderReligionDTO.setCode(new GenderReligionEntityKey(genderReligionDTO.getGentypeCode(),
                                                                      religionCode));
                genderReligionDTO.setReligionCode(religionCode);
                //                GenderReligionDAO genderReligionDAO =(GenderReligionDAO)newDAOInstance(GenderReligionEntity.class);
                //                 genderReligionDAO.add(genderReligionDTO);

                ((GenderReligionDAO)newDAOInstance(GenderReligionEntity.class)).add(genderReligionDTO);


            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            throw new DataBaseException("EnteredNameAlreadyExist");
        } catch (SharedApplicationException e) {
            e.printStackTrace();

        }
        return religionsDTO;
    }

    @Override
    public Boolean update(IRequestInfoDTO ri , IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        ReligionsDTO religionsDTO = (ReligionsDTO)basicDTO;
        List<IBasicDTO> nameList = DAO().checkDublicateName(basicDTO.getName());
        if (nameList != null && nameList.size() > 0) {
            if (!nameList.get(0).getCode().getKey().equals(basicDTO.getCode().getKey())) {
                throw new DataBaseException("EnteredNameAlreadyExist");
            }
        }
        try {
            DAO().update(religionsDTO);

            for (IBasicDTO dto : religionsDTO.getGenderReligionList()) {
                IGenderReligionDTO genderReligionDTO = (IGenderReligionDTO)dto;
                //                genderReligionDTO.setCode(new GenderReligionEntityKey(genderReligionDTO.getGentypeCode(),
                //                                                                      religionCode));
                //                genderReligionDTO.setReligionCode(religionCode);
                ((GenderReligionDAO)newDAOInstance(GenderReligionEntity.class)).update(genderReligionDTO);


            }
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        //        return  religionsDTO;
        return Boolean.TRUE;
    }


}
