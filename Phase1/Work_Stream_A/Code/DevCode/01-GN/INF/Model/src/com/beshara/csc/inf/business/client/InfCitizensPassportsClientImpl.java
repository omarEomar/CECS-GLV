package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.InfCitizensPassportsSession;
import com.beshara.csc.inf.business.dto.IInfCitizensPassportsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class InfCitizensPassportsClientImpl extends BaseClientImpl3 implements IInfCitizensPassportsClient {
    /**
     * @param infCitizensPassportsSession
     */
    public InfCitizensPassportsClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return InfCitizensPassportsSession.class;
    }

    @Override
    protected InfCitizensPassportsSession SESSION() {
        return (InfCitizensPassportsSession)super.SESSION();
    }

    /**
     * Get IInfCitizensPassportsDTO By CivilID * @param civilID
     * @return IInfCitizensPassportsDTO
     * @throw DataBaseException
     * @throw SharedApplicationException
     */
    public List<IBasicDTO> getByCivilID(Long civilID) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getByCivilID(RI(), civilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public IInfCitizensPassportsDTO getByPassportNo(Long civilID, String passportNo) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getByPassportNo(RI(), civilID, passportNo);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
}
