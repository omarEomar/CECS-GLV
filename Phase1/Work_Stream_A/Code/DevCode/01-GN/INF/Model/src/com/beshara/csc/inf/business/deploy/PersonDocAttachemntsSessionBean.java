package com.beshara.csc.inf.business.deploy;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.flm.flm.business.client.ITransactionClient;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.dto.TransactionDTO;
import com.beshara.csc.flm.flm.business.entity.TransactionEntityKey;
import com.beshara.csc.inf.business.dao.PersonDocAttachemntsDAO;
import com.beshara.csc.inf.business.dao.PersonDocumntsDAO;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.dto.PersonDocumntsDTO;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "InfPersonDocAttachemntsSession", mappedName = "InfPersonDocAttachemntsSession")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(PersonDocAttachemntsSession.class)


public class PersonDocAttachemntsSessionBean extends InfBaseSessionBean implements PersonDocAttachemntsSession {
    public PersonDocAttachemntsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return PersonDocAttachemntsEntity.class;
    }

    @Override
    protected PersonDocAttachemntsDAO DAO() {
        return (PersonDocAttachemntsDAO)super.DAO();
    }

    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO dtoParam) throws DataBaseException, SharedApplicationException {
        PersonDocAttachemntsDTO dto = (PersonDocAttachemntsDTO)dtoParam;
        PersonDocumntsDAO personDocumntsDAO = (PersonDocumntsDAO)newDAOInstance(PersonDocumntsEntity.class);
        PersonDocumntsDTO perDocumntsDTO = personDocumntsDAO.add(dto.getPersonDocumntsDTO());
        dto.setPersonDocumntsDTO(perDocumntsDTO);
        dto = DAO().add(dto);

        ITransactionClient transactionClient = ClientFactoryUtil.getInstance(ITransactionClient.class);
        FileDTO file = dto.getFile();
        TransactionDTO transaction = file.getTransaction();
        TransactionEntityKey transactionEK = (TransactionEntityKey)transaction.getCode();
        transactionClient.commit(transactionEK.getId());

        return dto;
    }

    public List<IBasicDTO> getAllByCivilAndDocType(IRequestInfoDTO ri, Long civilId,
                                                   String doctypeCode) throws DataBaseException,
                                                                              SharedApplicationException {
        return DAO().getAllByCivilAndDocType(civilId, doctypeCode);
    }

    public IBasicDTO addPersonDocAttachment(IRequestInfoDTO ri, IBasicDTO dtoParam) throws DataBaseException,
                                                                                           SharedApplicationException {

        PersonDocAttachemntsDTO dto = (PersonDocAttachemntsDTO)dtoParam;
        PersonDocumntsDAO personDocumntsDAO = (PersonDocumntsDAO)newDAOInstance(PersonDocumntsEntity.class);

        PersonDocumntsDTO personDocumntsDTO = dto.getPersonDocumntsDTO();
        IInfDocumentTypesEntityKey infDocumentTypesEK =
            (IInfDocumentTypesEntityKey)personDocumntsDTO.getInfDocumentTypesDTO().getCode();

        Long civilId = personDocumntsDTO.getKwtCitizensResidentsDTO().getCivilId();
        String docTypeCode = infDocumentTypesEK.getDoctypeCode().toString();

        List<IBasicDTO> docLst = personDocumntsDAO.getAllByCivilAndDocType(civilId, docTypeCode);

        if (docLst != null && !docLst.isEmpty()) {
            dto.setPersonDocumntsDTO((PersonDocumntsDTO)docLst.get(docLst.size() - 1));
        } else {
            PersonDocumntsDTO perDocumntsDTO = personDocumntsDAO.add(personDocumntsDTO);
            dto.setPersonDocumntsDTO(perDocumntsDTO);
        }
        dto = DAO().add(dto);

        ITransactionClient transactionClient = ClientFactoryUtil.getInstance(ITransactionClient.class);
        FileDTO file = dto.getFile();
        TransactionDTO transaction = file.getTransaction();
        TransactionEntityKey transactionEK = (TransactionEntityKey)transaction.getCode();
        transactionClient.commit(transactionEK.getId());

        return dto;
    }
    
    public List<IBasicDTO> searchAllDocuments(IRequestInfoDTO ri, IBasicDTO searchDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException {
        return DAO().searchAllDocuments(searchDTO);
    }
}
