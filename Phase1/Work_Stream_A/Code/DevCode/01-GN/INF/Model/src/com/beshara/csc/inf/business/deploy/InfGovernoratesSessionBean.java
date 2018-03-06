package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfGovernoratesDAO;
import com.beshara.csc.inf.business.entity.InfGovernoratesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
@Stateless(name = "InfGovernoratesSession", mappedName = "Inf-Model-InfGovernoratesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class InfGovernoratesSessionBean extends InfBaseSessionBean implements InfGovernoratesSession {

    /**
     * JobsSession Default Constructor
     */
    public InfGovernoratesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfGovernoratesEntity.class;
    }

    @Override
    protected InfGovernoratesDAO DAO() {
        return (InfGovernoratesDAO)super.DAO();
    }

    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return DAO().searchByCode(code);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    /**
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return DAO().searchByName(name);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

}
