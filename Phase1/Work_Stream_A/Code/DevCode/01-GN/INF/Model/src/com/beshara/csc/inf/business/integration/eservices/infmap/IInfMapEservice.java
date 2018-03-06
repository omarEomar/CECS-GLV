package com.beshara.csc.inf.business.integration.eservices.infmap;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.base.integration.eservices.dto.DropDownDTO;
import com.beshara.csc.inf.business.integration.eservices.infmap.dto.InfMapDTO;
import com.beshara.csc.inf.business.integration.eservices.infmap.dto.InfMapDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface IInfMapEservice extends IEService {
    public List<InfMapDTO> getFirstLevel() throws DataBaseException, SharedApplicationException ;
    public List<InfMapDTO> getChildrenList(String parentCode) throws DataBaseException, SharedApplicationException;
    public List<DropDownDTO> getAllStreet() throws DataBaseException, SharedApplicationException ;
}
