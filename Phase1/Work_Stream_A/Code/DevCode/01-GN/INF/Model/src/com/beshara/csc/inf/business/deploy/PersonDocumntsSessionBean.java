package com.beshara.csc.inf.business.deploy;

import com.beshara.base.dao.DAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.PersonDocAttachemntsDAO;
import com.beshara.csc.inf.business.dao.PersonDocumntsDAO;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.dto.PersonDocumntsDTO;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
@Stateless(name = "InfPersonDocumntsSession", mappedName = "InfPersonDocumntsSession")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(PersonDocumntsSession.class)

public class PersonDocumntsSessionBean extends InfBaseSessionBean implements PersonDocumntsSession{
    public PersonDocumntsSessionBean() {
        super();
    }
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return PersonDocumntsEntity.class;
    }

    @Override
    protected PersonDocumntsDAO DAO() {
        return (PersonDocumntsDAO)super.DAO();
    }
    @Override
    public PersonDocumntsDTO add(IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException {
        try {
            PersonDocumntsDTO dto = (PersonDocumntsDTO)dtoParam;
//            PersonDocumntsDTO dtonew = DAO().add(dto);
            DAO().add(dto);
          PersonDocAttachemntsDAO personDocAttachemntsDAO =
              (PersonDocAttachemntsDAO)newDAOInstance(PersonDocAttachemntsEntity.class);
        personDocAttachemntsDAO.add(dto);
            return (PersonDocumntsDTO)dto;   
        }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } 
    }
    
    public List<IBasicDTO> getAllByCivilAndDocType(IRequestInfoDTO ri, Long civilId,
                                                   String doctypeCode) throws DataBaseException,
                                                                              SharedApplicationException {
        return DAO().getAllByCivilAndDocType(civilId, doctypeCode);
    }
        
}
