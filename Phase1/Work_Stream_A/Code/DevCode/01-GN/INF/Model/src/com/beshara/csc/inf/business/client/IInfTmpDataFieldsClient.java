package com.beshara.csc.inf.business.client;

import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.InfTmpDataFieldsDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Collection;
import java.util.List;
 
 /**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public interface IInfTmpDataFieldsClient extends IBasicClient {
     
     public List<IBasicDTO> getDataFieldsByType(Long dataType) throws DataBaseException,SharedApplicationException;
    
}
