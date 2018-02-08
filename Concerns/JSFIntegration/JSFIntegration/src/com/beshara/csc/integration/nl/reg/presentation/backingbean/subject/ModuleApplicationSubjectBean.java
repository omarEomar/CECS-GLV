package com.beshara.csc.nl.reg.presentation.backingbean.subject;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.gn.sec.business.dto.ISecApplicationModulesDTO;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IDutiesDTO;
import com.beshara.csc.nl.job.business.dto.IJobCatDutiesDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.reg.business.client.IRegModuleSubjectsClient;
import com.beshara.csc.nl.reg.business.client.ITypesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegModuleSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.dto.RegModuleSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.TypesDTO;
import com.beshara.csc.nl.reg.business.entity.ISubjectsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.lov.LOVBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class ModuleApplicationSubjectBean extends LookUpBaseBean{
    private ITreeDTO mapregsubDTO;
    private static final String BACK_BEAN_NAME = "moduleApplicationSubjectBean";
    public ModuleApplicationSubjectBean() {
    
        setPageDTO(new RegModuleSubjectsDTO()); //set this the page dto
        setSaveSortingState(true);
        super.setSelectedPageDTO(new RegModuleSubjectsDTO());
        //IRegModuleSubjectsDTO
        setClient((IRegModuleSubjectsClient)RegClientFactory.getRegModuleSubjectsClient());
        setLovBaseBean(new LOVBaseBean());
        getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        getLovBaseBean().setSearchTyp("0");
        setLovBaseBean(new LOVBaseBean());
        setDivMainContent("divMainContentWithCnt2");
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");
    
    }
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowContent1(true);
        app.setShowdatatableContent(true);
        app.setShowLovDiv(true);


        return app;
    }
  
    public void openListOfValuesDiv() {
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().getSelectedDTOS().clear();
    //        getLovBaseBean().setMultiSelect(true);
        FacesContext.getCurrentInstance().getApplication().createValueBinding("#{pageBeanName.lovBaseBean.searchTyp}").setValue(FacesContext.getCurrentInstance(),"0");
        //getLovBaseBean().setSearchTyp("0");
        getLovBaseBean().unCheck();
       

        try {
                
                SecClientFactory.getSecApplicationModulesClient().getLeavesCodeName();
            } 
//            catch (SharedApplicationException e) {
//                e.printStackTrace();
//                getLovBaseBean().setMyTableData(new ArrayList(0));
//            }
            catch (DataBaseException e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList(0));
            } catch (Exception e) {
                e.printStackTrace();
                getLovBaseBean().setMyTableData(new ArrayList(0));
            }
        if (  getLovBaseBean().isSearchMode()) {
            getLovBaseBean().setSearchMode(true);
            getLovBaseBean().setCleanDataTableFlage(true);
        } else {
            getLovBaseBean().setSearchMode(false);
            getLovBaseBean().setCleanDataTableFlage(false);
            getLovBaseBean().setSearchQuery("");
        }
        getLovBaseBean().setCodeTypeString(false);
        getLovBaseBean().setReturnMethodName(BACK_BEAN_NAME + 
                                             ".returnLevelsLovDiv");
        getLovBaseBean().setSearchMethod(BACK_BEAN_NAME + ".searcher");
        getLovBaseBean().setCancelSearchMethod(BACK_BEAN_NAME + 
                                               ".cancelSearchLevelsLovDiv");
        getLovBaseBean().setRenderedDropDown("dataT_data_panel , OperationBar , theSelectedNodeId , selectedNodeTreeLevelId , treeDetailsDiv");
        getLovBaseBean().setOnCompleteList(getLovBaseBean().getOnCompleteList() + 
                                           " ; focusHighlightedNode ( ) ; ");
        getLovBaseBean().setHighlightedDTOS(null);
        getSelectedDTOS().clear();
        getLoveList();
        getLovBaseBean().getMyDataTable().setFirst(0);
        
    }
    private void getLoveList() {
        try {
            
            
         getLovBaseBean().setMyTableData(RegClientFactory.getRegModuleSubjectsClient().listAvailableBySubject(((ISubjectsEntityKey)((ISubjectsDTO)getMapregsubDTO()).getCode()).getSubjectCode()));
        } 
//        catch (SharedApplicationException e) {
//            e.printStackTrace();
//        }
        catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList()) ;
        } catch (Exception e) {
            
             getLovBaseBean().setMyTableData(new ArrayList()) ;
        }
       
    }
    
    public String back(){
        return "backToSubList";
    }
    
        public List getTotalList() {

            List totalList = null;
            
                try {

                     totalList = RegClientFactory.getRegModuleSubjectsClient().getAllBySubject(((ISubjectsEntityKey)((ISubjectsDTO)getMapregsubDTO()).getCode()).getSubjectCode());

                } catch (SharedApplicationException ne) {
                    totalList = new ArrayList();
                    ne.printStackTrace();
                } catch (DataBaseException db) {
                    getSharedUtil().handleException(db);
                } catch (Exception e) {
                    getSharedUtil().handleException(e);
                }
            
            return totalList;

        }
 public String returnLevelsLovDiv() {
     getSelectedDTOS().clear();
     try {
         List<IBasicDTO> insertableDTOS = new ArrayList <IBasicDTO> ();
         for (IBasicDTO dto: getLovBaseBean().getSelectedDTOS()) {
             IRegModuleSubjectsDTO _regModuleSubjectsDTO = 
                 RegDTOFactory.createRegModuleSubjectsDTO();
             _regModuleSubjectsDTO.setSubjectsDTO((ISubjectsDTO)getMapregsubDTO());
            _regModuleSubjectsDTO.setSecApplicationModulesDTO((ISecApplicationModulesDTO)dto);
             insertableDTOS.add(_regModuleSubjectsDTO);
         }


         RegClientFactory.getRegModuleSubjectsClient().addListOfModules(insertableDTOS);
         getAll();
         this.setSearchQuery("");
         this.setSearchType(0);
         this.setSearchMode(false);
         getSharedUtil().setSuccessMsgValue("SuccessAdds");
   }
//catch (SharedApplicationException e) {
//         e.printStackTrace();
//
//     }
catch (DataBaseException e) {
         e.printStackTrace();
     }    catch (Exception ex) {
                this.setShowErrorMsg(true);
                this.setErrorMsg(ex.getCause().getMessage());
                //showDiv();
            }
     this.setCurrentSelectedSearchIndex(getLovBaseBean().getIndexOfSelectedDataInDataTable());
     setSearchMode(false);
    
     return "";
 }

    public void searcher(Object searchType, Object searchQuery) {
        getLovBaseBean().unCheck();  
        getSelectedDTOS().clear();
        Long subjectCode = ((ISubjectsEntityKey)(getMapregsubDTO()).getCode()).getSubjectCode();
        try {
            if (searchQuery != null && !searchQuery.equals("")) {
                if (searchType.toString().equals("0")) {

                    getLovBaseBean().setMyTableData(RegClientFactory.getRegModuleSubjectsClient().searchAvailableByCode(new Long(searchQuery.toString()),subjectCode));
                    getLovBaseBean().unCheck();                                                                                            
                } else if (searchType.toString().equals("1")) {
                    getLovBaseBean().setMyTableData(RegClientFactory.getRegModuleSubjectsClient().searchAvailableByName(searchQuery.toString(),subjectCode));
                    getLovBaseBean().unCheck();  
                }
            } 
            setDataTableSearchResult(getLovBaseBean().getMyTableData());
            setSearchResultSize(getLovBaseBean().getMyTableData().size());
            getLovBaseBean().unCheck();  
            getSelectedDTOS().clear();
        } catch (DataBaseException e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        } catch (Exception e) {
            e.printStackTrace();
            getLovBaseBean().setMyTableData(new ArrayList(0));
        }

    }
    public void cancelSearchLevelsLovDiv() {
        getLovBaseBean().getSelectedDTOS().clear();
        getLovBaseBean().unCheck();
        try {
            getLovBaseBean().setMyTableData(RegClientFactory.getRegModuleSubjectsClient().listAvailableBySubject(((ISubjectsEntityKey)((ISubjectsDTO)getMapregsubDTO()).getCode()).getSubjectCode()));
            getLovBaseBean().unCheck();
        }   catch (SharedApplicationException e) {
                        e.printStackTrace();
                    } catch (DataBaseException e) {
                        e.printStackTrace();
                        new ArrayList();
                    }
        setSearchMode(false);
        getLovBaseBean().setSearchMode(false);
        getLovBaseBean().setSearchQuery("");
        getLovBaseBean().getSearchQuery();
       
        getLovBaseBean().setSearchTyp("0");
      

    }
    public void hideLovDiv() {

        if (!getLovBaseBean().isCleanDataTableFlage()) {
            getLovBaseBean().setMyTableData(new ArrayList<IBasicDTO>());
        }
            unCheck();
        
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }
        if (getLovBaseBean().getSelectedDTOS() != null) {
            getLovBaseBean().getSelectedDTOS().clear();
        }
        
    }
    public void setMapregsubDTO(ITreeDTO mapregsubDTO) {
        this.mapregsubDTO = mapregsubDTO;
    }

    public ITreeDTO getMapregsubDTO() {
        return mapregsubDTO;
    }
}
