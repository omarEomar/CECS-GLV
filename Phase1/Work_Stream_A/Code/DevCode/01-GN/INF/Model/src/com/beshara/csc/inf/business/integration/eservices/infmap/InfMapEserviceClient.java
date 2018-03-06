package com.beshara.csc.inf.business.integration.eservices.infmap;


import com.beshara.base.integration.eservices.EServiceClient;
import com.beshara.base.integration.eservices.IEService;
import com.beshara.base.integration.eservices.dto.DropDownDTO;
import com.beshara.csc.inf.business.integration.eservices.infmap.dto.InfMapDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class InfMapEserviceClient extends EServiceClient {

    @Override
    protected Class<? extends IEService> getEServiceInterface() {
        return IInfMapEservice.class;
    }

    protected IInfMapEservice SERVICE() {
        return (IInfMapEservice)super.SERVICE();
    }

    public InfMapEserviceClient() {
        super();
    }

    public List<InfMapDTO> getFirstLevel() throws DataBaseException, SharedApplicationException {
        return SERVICE().getFirstLevel();
    }

    public List<InfMapDTO> getChildrenList(String parentCode) throws DataBaseException, SharedApplicationException {
        return SERVICE().getChildrenList(parentCode);
    }


    public List<DropDownDTO> getAllStreet() throws DataBaseException, SharedApplicationException {
        return SERVICE().getAllStreet();
    }

}
