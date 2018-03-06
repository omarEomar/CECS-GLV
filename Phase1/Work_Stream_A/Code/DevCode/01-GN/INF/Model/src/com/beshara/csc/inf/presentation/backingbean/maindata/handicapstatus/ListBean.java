package com.beshara.csc.inf.presentation.backingbean.maindata.handicapstatus;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IHandicapStatusDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;


public class ListBean extends LookUpBaseBean {

    public ListBean() {
        setPageDTO(InfDTOFactory.createHandicapStatusDTO());
        setSelectedPageDTO(InfDTOFactory.createHandicapStatusDTO());
        setClient(InfClientFactory.getHandicapStatusClient());
        setSingleSelection(true);
        setSaveSortingState(true);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        return app;
    }

    public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createHandicapStatusDTO());
    }

    public void search() throws DataBaseException, NoResultException {

        if (getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));

        super.search();
    }

    public void cancelAdd() {

        setPageDTO(InfDTOFactory.createHandicapStatusDTO());
        reIntializeMsgs();
        setPageMode(0);
    }

    public void cancelEdit() {
        setPageMode(0);
    }
    
    public void postRecord(){

        try {
            InfClientFactory.getHandicapStatusCMTClient().postRecord((IHandicapStatusDTO)getSelectedDTOS().get(0));
            getAll();
            getSharedUtil().handleSuccMsg("com.beshara.csc.inf.presentation.resources.inf_ar","post_Succes_Msg_Label");
        } catch (SharedApplicationException e) {
           e.printStackTrace(); 
            getSharedUtil().handleException(e, 
                                       "com.beshara.csc.inf.presentation.resources.inf_ar", 
                                       "post_exception_Msg_Label");
                                       
        } catch (DataBaseException e) {
           e.printStackTrace(); 
            getSharedUtil().handleException(e, 
                                       "com.beshara.csc.inf.presentation.resources.inf_ar", 
                                       "post_exception_Msg_Label");
        }
        catch (Exception e) {
           e.printStackTrace();        
            getSharedUtil().handleException(e, 
                                       "com.beshara.csc.inf.presentation.resources.inf_ar", 
                                       "post_exception_Msg_Label");
           
           
        }
        getSelectedDTOS().clear();
        setSelectedRadio("");
    }
}
