package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.KwMapDAO;
import com.beshara.csc.inf.business.entity.KwMapEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
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
@Stateless(name = "KwMapSession", mappedName = "Inf-Model-KwMapSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class KwMapSessionBean extends InfBaseSessionBean implements KwMapSession {

    /**
     * JobsSession Default Constructor */
    public KwMapSessionBean() {
    }


    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return KwMapEntity.class;
    }

    @Override
    protected KwMapDAO DAO() {
        return (KwMapDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    public Long getChildrenNumber(IRequestInfoDTO ri, IEntityKey entityKey) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().getChildrenNumber(entityKey);
    }

    public List<IBasicDTO> getFirstLevel(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getFirstLevel();
    }

    public List<IBasicDTO> getChildrenList(IRequestInfoDTO ri, IEntityKey entityKey) throws DataBaseException,
                                                                                            SharedApplicationException {
        return DAO().getChildrenList(entityKey);
    }

    public Integer getTotalNumber(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getTotalNumber();
    }

    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return DAO().searchByName(name);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return DAO().searchByCode(code);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> searchByTypeCode(IRequestInfoDTO ri, Long code) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return DAO().searchByTypeCode(code);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO kwMapDTO1) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return DAO().search(kwMapDTO1);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }
}
