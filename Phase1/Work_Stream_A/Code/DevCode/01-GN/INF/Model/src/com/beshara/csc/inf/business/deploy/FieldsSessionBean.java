package com.beshara.csc.inf.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dao.FieldsDAO;
import com.beshara.csc.inf.business.dto.IFieldValueDTO;
import com.beshara.csc.inf.business.dto.IFieldsDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.FieldsEntity;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.cementj.base.trans.TransactionException;


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
@Stateless(name = "FieldsSession", mappedName = "Inf-Model-FieldsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class FieldsSessionBean extends InfBaseSessionBean implements FieldsSession {
    public FieldsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return FieldsEntity.class;
    }

    @Override
    protected FieldsDAO DAO() {
        return (FieldsDAO)super.DAO();
    }

    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        return DAO().getCodeName();
    }

    public List<IBasicDTO> getRelatedFields(IRequestInfoDTO ri, IEntityKey fieldCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getRelatedFields(fieldCode);
    }

    public List<IFieldValueDTO> calculateFieldValue(IRequestInfoDTO ri, IBasicDTO fldDTO,
                                                    List params) throws DataBaseException, SharedApplicationException {
        IFieldsDTO fieldDTO = (IFieldsDTO)fldDTO;
        List<IFieldValueDTO> list = new ArrayList<IFieldValueDTO>();
        if (fieldDTO.getDisplayedType() != null &&
            (fieldDTO.getDisplayedType().equals(4L) || fieldDTO.getDisplayedType().equals(5L) ||
             fieldDTO.getDisplayedType().equals(1L) || fieldDTO.getDisplayedType().equals(6L))) {
            String sqlValue = DAO().getSqlQueryValue(params, fieldDTO.getSqlStatement());
            //aded by nora
            IFieldValueDTO dto = InfDTOFactory.createFieldValueDTO();
            dto.setName(sqlValue);
            list.add(dto);
            return list;
        }
        String sqlQuery = DAO().getSqlQueryValue(params, fieldDTO.getSqlStatement());
        if (sqlQuery != null) {
            return DAO().calculateFieldValue(sqlQuery, fieldDTO);
        } else {
            throw new InvalidDataEntryException();
        }
    }

    public List<IBasicDTO> getFieldWithRelatedFieldsValues(IRequestInfoDTO ri, IBasicDTO fldDTO,
                                                           String param) throws DataBaseException,
                                                                                SharedApplicationException {
        List<IBasicDTO> relatedFields = this.getRelatedFields(ri, fldDTO.getCode());
        List paramList = new ArrayList();
        paramList.add(param);
        for (IBasicDTO dto : relatedFields) {
            ((IFieldsDTO)dto).setFieldValueDTOList(this.calculateFieldValue(ri, dto, paramList));
        }
        return relatedFields;
    }

    public List<IBasicDTO> getFieldWithRelatedFieldsValues(IRequestInfoDTO ri, List<IBasicDTO> relatedFields,
                                                           IBasicDTO fldDTO, String param) throws DataBaseException,
                                                                                                  SharedApplicationException { // List<IBasicDTO> relatedFields=this.getRelatedFields ( fldDTO.getCode ( ) ) ;
        List paramList = new ArrayList();
        paramList.add(param);
        for (IBasicDTO dto : relatedFields) {
            ((IFieldsDTO)dto).setFieldValueDTOList(this.calculateFieldValue(ri, dto, paramList));
        }
        return relatedFields;
    }
}
