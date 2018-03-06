package com.beshara.csc.inf.presentation.backingbean.maindata.mismisidntificationtypes;


import com.beshara.csc.hr.mis.business.client.IMisMisIdntificationTypesClient;
import com.beshara.csc.hr.mis.business.client.MisClientFactory;
import com.beshara.csc.hr.mis.business.dto.MisDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


public class MisMisIdntificationTypesListBean extends LookUpBaseBean {

    
	
	
    public MisMisIdntificationTypesListBean() {

        setPageDTO(MisDTOFactory.createMisMisIdntificationTypesDTO());
        super.setSelectedPageDTO(MisDTOFactory.createMisMisIdntificationTypesDTO());
        setClient((IMisMisIdntificationTypesClient) MisClientFactory.getMisMisIdntificationTypesClient());
	    setSingleSelection(true);
        setSaveSortingState(true);
    }
    
	
	public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        return app;
    }
	
	public void reInitializePageDTO() {
        this.setPageDTO(MisDTOFactory.createMisMisIdntificationTypesDTO());
    }
	
	public void initiateBeanOnce() {
	
	
	}
	
	
	
	public void search() throws DataBaseException, NoResultException {
        if (this.getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

	
	
}

