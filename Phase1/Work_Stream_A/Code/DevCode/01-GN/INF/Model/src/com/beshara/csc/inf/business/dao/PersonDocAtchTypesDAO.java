package com.beshara.csc.inf.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class PersonDocAtchTypesDAO extends InfBaseDAO{
    public PersonDocAtchTypesDAO() {
        super();
    }
    @Override
    protected BaseDAO newInstance() {
        return new PersonDocAtchTypesDAO();
    }
    @Override
    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            PersonDocAtchTypesEntity ent = EM().find(PersonDocAtchTypesEntity.class, id);
            if (ent == null) {
                throw new ItemNotFoundException();
            }
            return InfEntityConverter.getPersonDocAtchTypesDTO(ent);
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
