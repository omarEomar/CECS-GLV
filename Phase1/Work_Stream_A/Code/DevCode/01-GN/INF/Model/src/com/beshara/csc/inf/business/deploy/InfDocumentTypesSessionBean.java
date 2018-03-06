package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.InfDocumentTypesDAO;
import com.beshara.csc.inf.business.dto.InfDocumentTypesDTO;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.exception.DuplicatedNameException;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfDocumentTypesSession", mappedName = "Inf-Model-InfDocumentTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(InfDocumentTypesSession.class)

public class InfDocumentTypesSessionBean extends InfBaseSessionBean implements InfDocumentTypesSession {


    public InfDocumentTypesSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return InfDocumentTypesEntity.class;
    }

    @Override
    protected InfDocumentTypesDAO DAO() {
        return (InfDocumentTypesDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();

    }

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException {
        return super.search(ri, basicDTO);
    }


    public List<IBasicDTO> searchByName(IRequestInfoDTO ri, String name) throws DataBaseException,
                                                                                SharedApplicationException {


        return DAO().searchByName(name);


    }

    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().searchByCode(code);

    }

    public IBasicDTO add(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        InfDocumentTypesDTO infDocumentTypesDTO = (InfDocumentTypesDTO)iBasicDTO;
        if (DAO().duplicatedDocumentType(infDocumentTypesDTO.getName(), null)) {
            throw new DuplicatedNameException();
        }
        return super.add(iRequestInfoDTO, infDocumentTypesDTO);
    }


    public Boolean update(IRequestInfoDTO iRequestInfoDTO, IBasicDTO iBasicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        InfDocumentTypesDTO infDocumentTypesDTO = (InfDocumentTypesDTO)iBasicDTO;
        if (infDocumentTypesDTO.getCode() != null && DAO().duplicatedDocumentType(infDocumentTypesDTO.getName(), Long.valueOf(infDocumentTypesDTO.getCode().getKey()))) {
            throw new DuplicatedNameException();
        }
        return super.update(iRequestInfoDTO, infDocumentTypesDTO);
    }
}
