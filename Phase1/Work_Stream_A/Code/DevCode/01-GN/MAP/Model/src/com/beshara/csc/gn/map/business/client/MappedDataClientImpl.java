package com.beshara.csc.gn.map.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.deploy.MappedDataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
public class MappedDataClientImpl extends BaseClientImpl3 implements IMappedDataClient {
    public MappedDataClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return MappedDataSession.class;
    }

    @Override
    protected MappedDataSession SESSION() {
        return (MappedDataSession)super.SESSION();
    }

    /**
     * Create list of IMappedDataDTO mapped to a specific IMappedDataDTO * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean createMappedDataList(List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().createMappedDataList(RI(), mappedDataList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get list of IMappedDataDTO that maps specific IMappedDataDTO * the given mappedDataDto should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataDto
     * @return List IMappedDataDTO
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public List<IBasicDTO> getMappedDataList(IBasicDTO mappedDataDto) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            return SESSION().getMappedDataList(RI(), mappedDataDto);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * update MappedData for specific Dto , the list items should have objectType1Code , * objectType2Code soc1Code and soc1Value filled * @param mappedDataList
     * @return Boolean
     * @throws RemoteException
     * @throws ItemNotFoundException
     * @throws SharedApplicationException
     */
    public Boolean updateMappedDataList(List<IBasicDTO> mappedDataList) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return SESSION().updateMappedDataList(RI(), mappedDataList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean isValueAlreadyMapped(Long objtypeCode, Long socCode1, Long socCode2,
                                        String socValue) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().isValueAlreadyMapped(RI(), objtypeCode, socCode1, socCode2, socValue);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
