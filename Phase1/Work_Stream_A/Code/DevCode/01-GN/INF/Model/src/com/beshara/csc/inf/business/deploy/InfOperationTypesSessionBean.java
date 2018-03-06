package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfOperationTypesDAO;
import com.beshara.csc.inf.business.entity.InfOperationTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
 
@Stateless(name = "InfOperationTypesSession" ,mappedName = "Inf-Model-InfOperationTypesSessionBean" )
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfOperationTypesSessionBean extends InfBaseSessionBean implements InfOperationTypesSession { 

    /**
     * JobsSession Default Constructor
     */
    public InfOperationTypesSessionBean() { 
    }
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfOperationTypesEntity.class;
    }

    @Override
    protected InfOperationTypesDAO DAO() {
        return (InfOperationTypesDAO)super.DAO();
    }
 
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }
}
