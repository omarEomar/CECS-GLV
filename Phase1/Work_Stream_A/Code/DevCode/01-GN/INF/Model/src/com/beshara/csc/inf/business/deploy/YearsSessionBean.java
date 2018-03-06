package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.YearsDAO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "YearsSession", mappedName = "Inf-Model-YearsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(YearsSession.class)
public class YearsSessionBean extends InfBaseSessionBean implements YearsSession {


    public YearsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return YearsEntity.class;
    }

    @Override
    protected YearsDAO DAO() {
        return (YearsDAO)super.DAO();
    }

    public IBasicDTO add(IRequestInfoDTO  ri,IBasicDTO yearDTO) throws SharedApplicationException, DataBaseException {
        try {
            Long size = DAO().getYearCountByName(((IYearsDTO)yearDTO).getYearName());
            if (size > 0) {
                throw new DataBaseException("EnteredNameAlreadyExist");
            } else {
                return DAO().add(yearDTO);
            }
        } catch (ItemNotFoundException e) {
            throw new InvalidDataEntryException();
        }
    }

    public List<IYearsDTO> getCodeName(IRequestInfoDTO  ri) throws DataBaseException, SharedApplicationException {

        return DAO().getCodeName();
    }


    public List<IYearsDTO> getCodeNameUntil(IRequestInfoDTO  ri,Long yearCode) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeNameUntil(yearCode);
    }


    public List<IBasicDTO> getYearByName(IRequestInfoDTO  ri,String name) throws DataBaseException, SharedApplicationException {
        return DAO().getYearByName(name);
    }

    public List<IBasicDTO> search(IRequestInfoDTO  ri,IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {


        return DAO().search(basicDTO);
    }
    
    public IYearsDTO getByIdSimple(IRequestInfoDTO  ri,IEntityKey entityKey) throws DataBaseException, SharedApplicationException {
    
        return DAO().getByIdSimple( entityKey);
    }
    
    public List<IBasicDTO> getAllExcludedList(IRequestInfoDTO ri,List<String>excludedYears) throws DataBaseException, SharedApplicationException {


        return DAO().getAllExcludedList(excludedYears);
    }
    
    public List<IYearsDTO> getCodeNameAfterDate(IRequestInfoDTO ri, Timestamp date) throws DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().getCodeNameAfterDate(date);
    }

}
