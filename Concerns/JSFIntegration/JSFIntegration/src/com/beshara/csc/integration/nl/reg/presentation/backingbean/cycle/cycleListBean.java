package com.beshara.csc.nl.reg.presentation.backingbean.cycle;

import com.beshara.base.entity.*;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGCourtChambersDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;

public class cycleListBean extends LookUpBaseBean {
    public cycleListBean() {
        setPageDTO(RegDTOFactory.createREGCourtChambersDTO()); //set this the page dto 
        setSaveSortingState(true);
        super.setSelectedPageDTO(RegDTOFactory.createREGCourtChambersDTO());
        setClient(RegClientFactory.getRegCourtChambersClient());
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    }

    public void search() throws DataBaseException, NoResultException {
        try {
            if (this.getSearchType().intValue() == 0)
                super.setSearchEntityObj((new Long(this.getSearchQuery())));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }
}
