package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfTmpDataDisksDAO;
import com.beshara.csc.inf.business.entity.InfTmpDataDisksEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
@Stateless(name = "InfTmpDataDisksSession", mappedName = "Inf-Model-InfTmpDataDisksSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfTmpDataDisksSessionBean extends InfBaseSessionBean implements InfTmpDataDisksSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfTmpDataDisksSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfTmpDataDisksEntity.class;
    }

    @Override
    protected InfTmpDataDisksDAO DAO() {
        return (InfTmpDataDisksDAO)super.DAO();
    }

    public List<IBasicDTO> getByDataTypeCode(IRequestInfoDTO ri, Long dataType) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getByDataTypeCode(dataType);
    }
    
    public Long getDiskNum(IRequestInfoDTO ri, Long dataType) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getDiskNum(dataType);
    }

}
