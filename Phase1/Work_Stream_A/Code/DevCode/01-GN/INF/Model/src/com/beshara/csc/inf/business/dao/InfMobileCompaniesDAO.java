package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.entity.InfMobileCompaniesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author       Beshara Group
 * @author       Black Hourse [E.Saber]
 * @version      1.0
 * @since        03/11/2016
 */

public class InfMobileCompaniesDAO extends InfBaseDAO {

    /**
     * InfPresentationChannelDAO Default Constructor
     */
    public InfMobileCompaniesDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new InfMobileCompaniesDAO();
    }


    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList<IBasicDTO>();
            List<InfMobileCompaniesEntity> list =
                EM().createNamedQuery("InfMobileCompaniesEntity.findAll").getResultList();
            for (InfMobileCompaniesEntity infMobileCompanies : list) {
                arrayList.add(InfEntityConverter.getInfMobileCompaniesDTO(infMobileCompanies));
            }
            return arrayList;
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
