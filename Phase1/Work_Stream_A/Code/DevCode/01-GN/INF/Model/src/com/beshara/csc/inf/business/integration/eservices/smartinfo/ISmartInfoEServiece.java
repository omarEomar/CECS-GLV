package com.beshara.csc.inf.business.integration.eservices.smartinfo;


import com.beshara.base.integration.eservices.IEService;
import com.beshara.base.integration.eservices.dto.DropDownDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;

import javax.jws.WebService;


@Remote
@WebService
public interface ISmartInfoEServiece extends IEService {

    public List<DropDownDTO>  getWeeks() throws DataBaseException, SharedApplicationException;

    public List<DropDownDTO>  getDocInfType() throws DataBaseException, SharedApplicationException;
    public List<DropDownDTO> getyears() throws DataBaseException, SharedApplicationException;
    public List<DropDownDTO>  getMonths() throws DataBaseException, SharedApplicationException;
    public List<DropDownDTO>  getCountries() throws DataBaseException, SharedApplicationException ;
}
