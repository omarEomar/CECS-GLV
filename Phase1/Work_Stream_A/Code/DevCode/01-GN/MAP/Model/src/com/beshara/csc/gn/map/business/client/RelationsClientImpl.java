package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.client.BasicClientImpl;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.csc.gn.map.business.deploy.DataSession;
import com.beshara.csc.gn.map.business.deploy.RelationsSession;
import com.beshara.csc.gn.map.business.deploy.RelationsSessionBean;
import com.beshara.csc.gn.map.business.deploy.SocietiesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.SystemException;

import java.rmi.RemoteException;

import java.util.List;



public class RelationsClientImpl extends BaseClientImpl3 implements IRelationsClient {
    public RelationsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return RelationsSession.class;
    }
    @Override
    protected RelationsSession SESSION() {
        return (RelationsSession)super.SESSION();
    }
    
  
   
}
