package com.beshara.csc.inf.business.integration.eservices.infmap;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.integration.eservices.EServiceImpl;
import com.beshara.base.integration.eservices.dto.DropDownDTO;
import com.beshara.csc.inf.business.deploy.KwMapSession;
import com.beshara.csc.inf.business.deploy.KwStreetsSession;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.integration.eservices.infmap.dto.InfMapDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless(name = "InfInfMapEservice", mappedName = "InfInfMapEservice")

public class InfMapEserviceImp extends EServiceImpl implements IInfMapEservice {
    @EJB
    private KwMapSession kwMapSession;
    @EJB
    private KwStreetsSession kwStreetsSession;

    public InfMapEserviceImp() {
    }


    public List<InfMapDTO> getFirstLevel() throws DataBaseException, SharedApplicationException {
        try {
            List<InfMapDTO> dropDownDTOList = new ArrayList<InfMapDTO>();
            List<IBasicDTO> list = kwMapSession.getFirstLevel(RI());
            for (IBasicDTO basicDTO : list) {
                InfMapDTO infMapDTO = new InfMapDTO();
                infMapDTO.setValue(basicDTO.getCode().getKey());
                infMapDTO.setLabel(basicDTO.getName());
                dropDownDTOList.add(infMapDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public List<InfMapDTO> getChildrenList(String parentCode) throws DataBaseException, SharedApplicationException {
        try {
            List<InfMapDTO> dropDownDTOList = new ArrayList<InfMapDTO>();
            List<IBasicDTO> list =
                kwMapSession.getChildrenList(RI(), InfEntityKeyFactory.createKwMapEntityKey(parentCode));
            for (IBasicDTO basicDTO : list) {
                InfMapDTO infMapDTO = new InfMapDTO();
                infMapDTO.setValue(basicDTO.getCode().getKey());
                infMapDTO.setLabel(basicDTO.getName());
                dropDownDTOList.add(infMapDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


    public List<DropDownDTO> getAllStreet() throws DataBaseException, SharedApplicationException {
        try {
            List<DropDownDTO> dropDownDTOList = new ArrayList<DropDownDTO>();
            List<IBasicDTO> list = kwStreetsSession.getAll(RI());
            for (IBasicDTO basicDTO : list) {
                DropDownDTO dropDownDTO = new DropDownDTO();
                dropDownDTO.setValue(Long.valueOf(basicDTO.getCode().getKey()));
                dropDownDTO.setLabel(basicDTO.getName());
                dropDownDTOList.add(dropDownDTO);
            }
            return dropDownDTOList;
        } catch (RemoteException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


}
