package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfEservicesDocumentTypesDAO;
import com.beshara.csc.inf.business.entity.InfEservicesDocumentTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfEservicesDocumentTypesSession", mappedName = "InfEservicesDocumentTypesSession")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(InfEservicesDocumentTypesSession.class)

public class InfEservicesDocumentTypesSessionBean extends InfBaseSessionBean implements InfEservicesDocumentTypesSession {


    public InfEservicesDocumentTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfEservicesDocumentTypesEntity.class;
    }

    @Override
    protected InfEservicesDocumentTypesDAO DAO() {
        return (InfEservicesDocumentTypesDAO)super.DAO();
    }

    public List<IBasicDTO> getByServicesId(IRequestInfoDTO ri, Long servicesId) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            return DAO().getByServicesId(servicesId);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
}
