package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.emp.InfReasonDataDAO;
import com.beshara.csc.inf.business.entity.emp.InfReasonDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfReasonDataSession", mappedName = "Inf-Model-InfReasonDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(InfReasonDataSession.class)
public class InfReasonDataSessionBean extends InfBaseSessionBean implements InfReasonDataSession {


    public InfReasonDataSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfReasonDataEntity.class;
    }

    @Override
    protected InfReasonDataDAO DAO() {
        return (InfReasonDataDAO)super.DAO();
    }

    /**
     * @param sessionContext
     */


    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {

        return DAO().getCodeName();

    }

    public List<IBasicDTO> getReasonDataByType(IRequestInfoDTO ri,long reasontype) throws DataBaseException, SharedApplicationException  {
        return DAO().getReasonDataByType(reasontype);
    }

    public List<IBasicDTO> searchReasonData(IRequestInfoDTO ri,IBasicDTO infReasonDataDTO) throws DataBaseException, SharedApplicationException  {
        return DAO().searchReasonData(infReasonDataDTO);
    }
}
