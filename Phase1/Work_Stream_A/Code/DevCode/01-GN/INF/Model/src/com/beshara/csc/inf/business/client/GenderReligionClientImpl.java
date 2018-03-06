package com.beshara.csc.inf.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.deploy.GenderReligionSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class GenderReligionClientImpl extends BaseClientImpl3 implements IGenderReligionClient {
    private GenderReligionSession genderReligionSession;

    /**
     * @param GenderReligionSession
     */
    public GenderReligionClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return GenderReligionSession.class;
    }

    @Override
    protected GenderReligionSession SESSION() {
        return (GenderReligionSession)super.SESSION();
    }

    public List<IBasicDTO> searchByGenRegCode(Long genTypeCode, Long religionsCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SESSION().searchByGenRegCode(RI(), genTypeCode, religionsCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }

    }


}
