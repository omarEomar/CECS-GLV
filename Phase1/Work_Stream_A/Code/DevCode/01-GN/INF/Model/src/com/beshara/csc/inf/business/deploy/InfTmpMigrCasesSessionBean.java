package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfTmpMigrCasesDAO;
import com.beshara.csc.inf.business.entity.InfTmpMigrCasesEntity;
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
@Stateless(name = "InfTmpMigrCasesSession", mappedName = "Inf-Model-InfTmpMigrCasesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfTmpMigrCasesSessionBean extends InfBaseSessionBean implements InfTmpMigrCasesSession {
    //@PersistenceContext(unitName = "Inf")
    //private EntityManager em;


    /**
     * JobsSession Default Constructor
     */
    public InfTmpMigrCasesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfTmpMigrCasesEntity.class;
    }

    @Override
    protected InfTmpMigrCasesDAO DAO() {
        return (InfTmpMigrCasesDAO)super.DAO();
    }

    public List<IBasicDTO> getAllByDataTypeCode(IRequestInfoDTO ri, Long dataType,
                                                Long deskCode) throws DataBaseException, SharedApplicationException {
        return DAO().getAllByDataTypeCode(dataType, deskCode);
    }

    public String getCaseCode(IRequestInfoDTO ri, Long dataType, Long diskCode) throws DataBaseException,
                                                                                      SharedApplicationException {

        return DAO().getCaseCode(dataType, diskCode);
    }
}
