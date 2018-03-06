package com.beshara.csc.inf.business.deploy;

import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.inf.business.dto.InfTmpDataFieldsDTO;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;
import java.rmi.RemoteException;


 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Remote
public interface InfTmpDataFieldsSession extends BasicSession {

    // Add your extra methods
     
    public List<IBasicDTO> getDataFieldsByType(IRequestInfoDTO  ri,Long dataType) throws DataBaseException,SharedApplicationException,RemoteException;
    
}
