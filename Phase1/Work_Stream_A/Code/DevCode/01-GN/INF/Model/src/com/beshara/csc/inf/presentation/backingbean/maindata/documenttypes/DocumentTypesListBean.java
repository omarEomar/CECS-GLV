package com.beshara.csc.inf.presentation.backingbean.maindata.documenttypes;


import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemCanNotBeUpdatedException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;


public class DocumentTypesListBean extends LookUpBaseBean {

    
	
	
    public DocumentTypesListBean() {
        getSharedUtil().setLocalBundle("com.beshara.csc.inf.presentation.resources.inf");
        setPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        super.setSelectedPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
        setClient( InfClientFactory.getInfDocumentTypesClient());
	    setSingleSelection(true);
        setSaveSortingState(true);
    }
    
	
	public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        return app;
    }
	
	public void reInitializePageDTO() {
        this.setPageDTO(InfDTOFactory.createInfDocumentTypesDTO());
    }
	
	
	public void search() throws DataBaseException, NoResultException {
        if (this.getSearchType().intValue() == 0)
            super.setSearchEntityObj(new Long(this.getSearchQuery()));
        super.search();
    }

    
    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {

        //TODO locking code
        // be sure that we are still locking the edited item
        // if not cancel the edit operation and show an exception 
        // message to the user
        if (insureLocked()) {

            SharedUtilBean sharedUtil = getSharedUtil();

            try {
                // because we are always closing the edit div, 
                // so we must always unlock the edited item 
                // to leave it to other users
                try {
                    executeEdit();
                } finally {
                    //TODO locking code
                    // unlock the edited item in update success or failure
                    // so we added it in this finally block
                    unlock();
                }

                cancelSearch();

                if (super.isUsingPaging()) {
                    generatePagingRequestDTO((String)getSelectedPageDTO().getCode().getKey());

                } else {
                    getPageIndex((String)getSelectedPageDTO().getCode().getKey());
                }

                if (super.getHighlightedDTOS() != null) {
                    super.getHighlightedDTOS().clear();
                    super.getHighlightedDTOS().add(this.getSelectedPageDTO());
                }

                super.setShowEdit(false);
                sharedUtil.setSuccessMsgValue("SuccesUpdated");

            } catch (DataBaseException e) {
                sharedUtil.handleException(e);
            } catch (ItemNotFoundException e) {
                sharedUtil.handleException(e);
            } catch (ItemCanNotBeUpdatedException e) {
                sharedUtil.handleException(e);

            }catch (SharedApplicationException e) {
               sharedUtil.handleException(e);
            } catch (Exception e) {
                sharedUtil.handleException(e, 
                                           "com.beshara.jsfbase.csc.msgresources.globalexceptions", 
                                           "FailureInUpdate");

            }
        }
        
        setSelectedRadio("");

    }
    
	
}

