package com.beshara.csc.inf.business.integration.eservices.smartinfo;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.base.integration.eservices.dto.DropDownDTO;
import com.beshara.csc.inf.business.deploy.CountriesSession;
import com.beshara.csc.inf.business.deploy.InfDocumentTypesSession;
import com.beshara.csc.inf.business.deploy.InfMonthsSession;
import com.beshara.csc.inf.business.deploy.WeekDaysSession;
import com.beshara.csc.inf.business.deploy.YearsSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.jws.WebService;

import weblogic.wsee.wstx.wsat.Transactional;


@Stateless(name = "SmartInfoEServiece", mappedName = "SmartInfoEServiece")

@WebService(endpointInterface = "com.beshara.csc.inf.business.integration.eservices.smartinfo.ISmartInfoEServiece")
@Transactional
public class SmartInfoEServieceImpl extends EServiceImpl implements ISmartInfoEServiece {
    @EJB
    private WeekDaysSession weekDaysSession;
    @EJB
    private YearsSession yearsSession;
    @EJB
    private InfMonthsSession infMonthsSession;

    @EJB
    private InfDocumentTypesSession InfDocumentTypesSession;
    @EJB
    private CountriesSession countriesSession;

    public SmartInfoEServieceImpl() {
        super();
    }

    public List<DropDownDTO>  getWeeks() throws DataBaseException, SharedApplicationException {
        try {
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list = weekDaysSession.getAll(RI());
            for (IBasicDTO dto : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(dto.getCode().getKey()));
                dropDownDTO.setLabel(dto.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e);
        }
    }

    public List<DropDownDTO>  getDocInfType() throws DataBaseException, SharedApplicationException {
        try {
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list = InfDocumentTypesSession.getCodeName(RI());
            for (IBasicDTO dto : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(dto.getCode().getKey()));
                dropDownDTO.setLabel(dto.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e);
        }
    }
    public List<DropDownDTO> getyears() throws DataBaseException, SharedApplicationException {
        try {
            System.out.println("getyearsgetyearsgetyearsgetyearsgetyearsgetyears");
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list =  yearsSession.getAll(RI());
            for (IBasicDTO dto : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(dto.getCode().getKey()));
                dropDownDTO.setLabel(dto.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            System.out.println("getyearsgetyearsgetyearsgetyearsgetyearsgetyearsdropDownDTOList"+dropDownDTOList.size());
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e);
        }
    }
    public List<DropDownDTO>  getMonths() throws DataBaseException, SharedApplicationException {
        try {
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list =  infMonthsSession.getAll(RI());
            for (IBasicDTO dto : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(dto.getCode().getKey()));
                dropDownDTO.setLabel(dto.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e);
        }
    }
    public List<DropDownDTO>  getCountries() throws DataBaseException, SharedApplicationException {
        try {
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list =  countriesSession.getCodeName(RI());
            for (IBasicDTO dto : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(dto.getCode().getKey()));
                dropDownDTO.setLabel(dto.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e);
        }
    }
}
