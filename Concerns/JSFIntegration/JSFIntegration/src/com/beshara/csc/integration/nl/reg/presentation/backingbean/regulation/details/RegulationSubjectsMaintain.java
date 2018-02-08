package com.beshara.csc.nl.reg.presentation.backingbean.regulation.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegualtionSubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegualtionSubjectsEntityKey;
import com.beshara.csc.nl.reg.business.entity.IRegulationsEntityKey;
import com.beshara.csc.nl.reg.business.entity.ISubjectsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;
import com.beshara.jsfbase.csc.backingbean.TreeManyToManyDetails;

import java.rmi.RemoteException;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;


public class RegulationSubjectsMaintain extends TreeManyToManyDetails {
    private IRegulationSearchDTO regulationSearchDTO;
    private String symbol;
    private String desc;
    private boolean hasAtLeastOneElement=true;
    
    public RegulationSubjectsMaintain() {
        setClient(RegClientFactory.getRegualtionSubjectsClient());
        //BeansUtil.setValue("regulationMaintainBean.content1Div","divContent1Dynamic");
        //BeansUtil.setValue("regulationMaintainBean.lookupAddDiv","subjectsAddDiv");
        setContent1Div("divContent1Dynamic");
        setLookupAddDiv("subjectsAddDiv");
        setDivMainContentMany("divREGMainContentMany");
        //for handling make add as tree div
        setTreeTabManagedBeanName("regulationSubjectsMaintainBean");
        initTreeDivBean();
        treedivBean.setEnableSearchByCode(true);
//        setSaveSortingState(true);
        
    }
    
    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        return app;
    }

//    public void getListAvailable() throws DataBaseException, 
//                                          SharedApplicationException {
//
//
//        if (getMasterEntityKey() != null) {
//            setAvailableDetails(((IRegualtionSubjectsClient)getClient()).listAvailableEntitiesCenter(getMasterEntityKey()));
//
//        } else {
//            List<BasicDTO> chk = 
//                ((IRegualtionSubjectsClient)getClient()).listAvailableEntitiesCenter(new RegulationsEntityKey());
//            setAvailableDetails(chk);
//        }
//
//        super.getListAvailable();
//    }


    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {

        return ((IRegualtionSubjectsDTO)dto).getSubjectsDTO();
    }

    public IBasicDTO mapToDetailDTO(IBasicDTO dto) {
    
        IRegulationsDTO regDTO=null;
        IRegulationsEntityKey regEntityKey=null;
        IRegualtionSubjectsEntityKey  subjectEntityKey=null;
      
    
        IRegualtionSubjectsDTO regSubjectDTO = RegDTOFactory.createRegualtionSubjectsDTO();
        
        
        regSubjectDTO.setSubjectsDTO((ISubjectsDTO)dto);
        
        if (this.getMasterEntityKey() != null) {
            regEntityKey = (IRegulationsEntityKey)this.getMasterEntityKey();
            regDTO=RegDTOFactory.createRegulationsDTO();
            regDTO.setCode(regEntityKey);
            regSubjectDTO.setRegulationsDTO(regDTO);
        }
        
        
        if(regEntityKey !=null)
            subjectEntityKey=RegEntityKeyFactory.createRegualtionSubjectsEntityKey(regEntityKey.getRegtypeCode(),regEntityKey.getRegyearCode(),regEntityKey.getRegulationNumber(),Long.valueOf(dto.getCode().getKey().toString()));
        else
            subjectEntityKey=RegEntityKeyFactory.createRegualtionSubjectsEntityKey(null,null,null,Long.valueOf(dto.getCode().getKey().toString()));  
            
        regSubjectDTO.setCode(subjectEntityKey);
        
        return regSubjectDTO;

    }

    public void searchAvailable() throws DataBaseException, 
                                         SharedApplicationException {
//        System.out.println("search start");
//        regulationSearchDTO = new RegulationSearchDTO();
//        regulationSearchDTO.setMasterCode(getMasterEntityKey() == null ? 
//                                          new RegulationsEntityKey() : 
//                                          getMasterEntityKey());
//        if (getSearchType().equals(1)) {
//            regulationSearchDTO.setName(getDesc());
//        } else {
//            regulationSearchDTO.setCode1(new Long(getSymbol()));
//
//
//        }
//        try {
//            setAvailableDetails(((IRegualtionSubjectsClient)getClient()).filterAvailableEntitiesCenter(regulationSearchDTO));
//        } catch (SharedApplicationException e) {
//            setAvailableDetails(new ArrayList<BasicDTO>());
//            setSearchMode(true);
//        }
//        super.searchAvailable();
//
//        // AvailableEntitiesDTO avaliableEntitiesDto=new AvailableEntitiesDTO();
//        // avaliableEntitiesDto.setSearchString(this.formatSearchString(this.getSearchString()));
//        // if(getMasterEntityKey()!=null){
//        //     avaliableEntitiesDto.setMasterCode(getMasterEntityKey());
//        // }
//        //
//        //     try {
//        //         setAvailableDetails(((IRegualtionSubjectsClient)getClient()).filterAvailableEntitiesCenter(avaliableEntitiesDto));
//        //        
//        //     } catch (SharedApplicationException e) {
//        //         setAvailableDetails(new ArrayList());
//        //         setSearchMode(true);
//        //     }
//        //     super.searchAvailable();
    }

    public boolean validate() {
        System.out.println("from advantages Validate");
        hasAtLeastOneElement= (getCurrentListSize() > 0);
        return hasAtLeastOneElement;

    }

    public void resetDetailDTO(IBasicDTO dto) {
        //        ((JobDutiesDTO)dto).setActivateDate(null);
    }

    public void setRegulationSearchDTO(IRegulationSearchDTO regulationSearchDTO) {
        this.regulationSearchDTO = regulationSearchDTO;
    }

    public IRegulationSearchDTO getRegulationSearchDTO() {
        return regulationSearchDTO;
    }


    public Integer getZeroAsInteger() {
        return new Integer(0);
    }

    public Integer getOneAsInteger() {
        return new Integer(1);
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
    public void cancelSearchAvailable() {
        super.cancelSearchAvailable();
        symbol = null;
        desc = null;
    }
    
    public void preAdd() {
        super.preAdd();
        try {
            this.cancelSearchAvailable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHasAtLeastOneElement(boolean hasAtLeastOneElement) {
        this.hasAtLeastOneElement = hasAtLeastOneElement;
    }

    public boolean getHasAtLeastOneElement() {
        return hasAtLeastOneElement;
    }

    public void initTreeDivBean(){
    
       if(getTreeTabManagedBeanName()!=null){
       
           treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
      
            // to avoid getall by the tree client

            try {
                setAvailableDetails(RegClientFactory.getSubjectsClient().getAllInCenter());
            } catch (SharedApplicationException e) {
                setAvailableDetails(new ArrayList());
                e.printStackTrace();
            } catch (Exception e) {
                setAvailableDetails(new ArrayList());
                e.printStackTrace();
            }
        treedivBean.setMyTableData(this.availableDetails);
        treedivBean.setTreeNodeBase(null);
        treedivBean.setShowTreeContent(true);
        //the add button in the div will invoke manytomany add function
        MethodBinding mb =evaluateMethodBinding(getTreeTabManagedBeanName()+".add",null); 
        treedivBean.getOkCommandButton().setAction(mb);
        treedivBean.getOkCommandButton().setReRender("dataT_data_panel,searchText,OperationBar,okPanel2");
        
        
        ValueBinding vb = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{treeDivBean.enabledNotLeaf && !treeDivBean.enabledRoot && (treeDivBean.selectionNo==1)}");
        treedivBean.getOkCommandButton().setValueBinding("rendered", vb);
        
        MethodBinding mb2 = FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{regulationSubjectsMaintainBean.cancelTreeSearch}",null);
        treedivBean.getCancelSearchCommandButton().setAction(mb2);
        //loading customized initialization so if you extends this class you must overrid following method 
         initializeTreeDiv();
        }
      
    }
    public void initializeTreeDiv() {
    
        treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
        treedivBean.setBundleName("com.beshara.csc.nl.reg.presentation.resources.reg_");
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.setRootName("reg_subjects");
        treedivBean.setClient(RegClientFactory.getSubjectsClient());
        treedivBean.setPageDTO(RegDTOFactory.createSubjectsDTO());
        treedivBean.setDto(RegDTOFactory.createSubjectsDTO());
        treedivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
        treedivBean.setSelectionNo(0);
        treedivBean.setReadCenter(true);
        treedivBean.setSelectedNodeId("");
        
    }
    public void cancelTreeSearch(){
    
        treedivBean.setSearchQuery("");
        treedivBean.setSearchType(0);
        treedivBean.setSearchMode(false);
        treedivBean.setSelectionNo(0);
        treedivBean.setMyTableData(new ArrayList());
        treedivBean.setTreeNodeBase(null);
        treedivBean.setShowTreeContent(true);
        treedivBean.getHtmlTree().collapseAll();
    }
    
    public void openSubjectAddDiv(){
        this.initializeTreeDiv();
        treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
        try {
            treedivBean.cancelSearchTree();
            treedivBean.getHtmlTree().collapseAll();
            
        } catch (Exception e) {
            // TODO
        }
        initTreeDivBean();
        //  cancelTreeSearch();
         
    }
    
    
         public void cancelSearchTree() throws DataBaseException, RemoteException, 
                                               Exception {
             treedivBean = (TreeDivBean)evaluateValueBinding("treeDivBean");
             treedivBean.cancelSearchTree();
             treedivBean.getHtmlTree().collapseAll();
         }
    
}
