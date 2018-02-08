package com.beshara.csc.nl.reg.presentation.backingbean.courtdegree;

import com.beshara.base.entity.*;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.qul.business.dto.IGeneralSpecsDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGCourtDegreesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;

public class CourtDegreeListBean extends LookUpBaseBean {
    public CourtDegreeListBean() {
        setPageDTO(RegDTOFactory.createREGCourtDegreesDTO()); //set this the page dto 
        super.setSelectedPageDTO(RegDTOFactory.createREGCourtDegreesDTO());
        setClient(RegClientFactory.getREGCourtDegreesClient());
        setSaveSortingState(true);
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    } // public void orderByCode ( ) { 
    // sort ( "qulgeneralspecsCode" ) ; 
    // } 
    // 
    // public void orderByName ( ) { 
    // sort ( "qulgeneralspecsCode" ) ; 
    // } 
    // 
    // public void showHideCode ( ) { 
    // showHideColumn ( "code_column" ) ; 
    // } 
    // 
    // public void showHideName ( ) { 
    // showHideColumn ( "name_column" ) ; 
    // } 
    // 
    // public void search ( ) throws DataBaseException , NoResultException { 
    // try { 
    // if ( this.getSearchType ( ) .intValue ( ) == 0 ) 
    // super.setSearchEntityObj ( ( new Long ( this.getSearchQuery ( ) ) ) ) ; 
    // super.search ( ) ; 
    // } 
    // catch ( Exception e ) { 
    // e.printStackTrace ( ) ; 
    // setSearchMode ( true ) ; 
    // setMyTableData ( new ArrayList ( ) ) ; 
    // } 
    // } 

    public void search() throws DataBaseException, NoResultException {
        try {
            if (getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }
    }
}
