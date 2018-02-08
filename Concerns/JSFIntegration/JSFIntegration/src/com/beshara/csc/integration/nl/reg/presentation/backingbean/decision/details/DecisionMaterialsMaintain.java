package com.beshara.csc.nl.reg.presentation.backingbean.decision.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.nl.reg.business.client.IRegDecisionMaterialRelsClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialRelsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegDecisionMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.business.entity.IRegDecisionMaterialsEntityKey;
import com.beshara.csc.nl.reg.business.entity.RegEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.event.ActionEvent;


public class DecisionMaterialsMaintain extends ManyToManyDetailsMaintain {

    private int index;

    //Show add, edit & del divs by parameters
    private boolean addDivShown;
    private boolean editDivShown;
    private boolean delDivShown;
    private List nonDeletedMaterial = new ArrayList();
    private boolean firstVisitForRelatedMaterial = true;
    private List<IBasicDTO> currentListBackup=new ArrayList();
    
    private IRegDecisionMaterialsDTO searchDTO= RegDTOFactory.createRegDecisionMaterialsDTO();

    public DecisionMaterialsMaintain() {

        setDivMainContentMany("divREGMainContentMany");
        setSaveSortingState(true);
         setPageDTO(RegDTOFactory.createRegDecisionMaterialsDTO());
        setSelectedPageDTO(RegDTOFactory.createRegDecisionMaterialsDTO());

    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowpaging(false);
        app.setShowLookupEdit(true);
        app.setShowDelConfirm(true);
        app.setShowSearch(true);

        return app;
    }

    public void add() {

        getPageDTO().setStatusFlag(new Long(0));
        getPageDTO().setCode(RegEntityKeyFactory.createRegDecisionMaterialsEntityKey(null,null,null,getMinId()));
        ((IRegDecisionMaterialsDTO)getPageDTO()).setRelatedMaterialsCount(0L);
        if(!isSearchMode()){
            getCurrentDetails().add(getPageDTO());
        }else{
            cancelSearch();
            getCurrentListBackup().add(getPageDTO());
        }

    }

    public void hideAddDiv() {
        setAddDivShown(false);
    }

    public void saveAndNew() {
        this.add();
        setPageDTO(RegDTOFactory.createRegDecisionMaterialsDTO());
        setSuccess(true);
    }

    public Long getMinId() {

        Long maxId = new Long(-1);
        if (this.getCurrentDetails() == null || this.getCurrentDetails().size() == 0) {
            
            return maxId;
        }
        for (IBasicDTO dto: this.getCurrentDetails()) {
            Long id = 
                ((IRegDecisionMaterialsEntityKey)dto.getCode()).getDecmaterialCode();
            if (id < maxId)
                maxId = id;
        }
        return maxId - 1;
    }

    public void EditChange() {
        IRegDecisionMaterialsDTO originalDTO = 
            (IRegDecisionMaterialsDTO)getCurrentDisplayDetails().get(index);
        IRegDecisionMaterialsDTO updatedDTO = 
            (IRegDecisionMaterialsDTO)getPageDTO();
        originalDTO.setDecmaterialHeader(updatedDTO.getDecmaterialHeader());
        originalDTO.setDecmaterialText(updatedDTO.getDecmaterialText());
        resetSelection();
        hideEditDiv();
        if(isSearchMode()){
            cancelSearch();
        }
    }

    public void preEditDiv() {

        setEditDivShown(true);
        try {
            setPageDTO((IRegDecisionMaterialsDTO)getSharedUtil().deepCopyObject(getCurrentDisplayDetails().get(index)));
            setEditDivShown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void hideEditDiv() {
        setEditDivShown(false);
    }

    public void preDeleteDiv() {
        setDelDivShown(true);
    }

    public void delete() {
//        boolean hasRelatedMaterial = false;
        if (getCurrentDetails() == null)
            setCurrentDetails(new ArrayList<IBasicDTO>());
        if (getSelectedCurrentDetails() != null && 
            getSelectedCurrentDetails().size() > 0) {

            for (int i = 0; i < getSelectedCurrentDetails().size(); i++) {
                
                IRegDecisionMaterialsDTO selected=null;
                try {
                    selected = (IRegDecisionMaterialsDTO)getSelectedCurrentDetails().get(i);
                    if(selected.getRelatedMaterials()!=null){
                        selected.setRelatedMaterials((List)RegClientFactory.getRegDecisionMaterialRelsClient().getByMaterial(selected.getCode()));
                    }
                } catch (Exception e) {
                  e.printStackTrace();  // TODO
                } 

//                if (hasRelatedMaterial) {
//                    nonDeletedMaterial.add(selected);
//                } else {
                    if (selected.getStatusFlag() == null) {
                        selected.setStatusFlag(ISystemConstant.DELEDTED_ITEM);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    }
                    if (selected.getStatusFlag().longValue() == 
                        ISystemConstant.ADDED_ITEM.longValue()) {
                        getCurrentDetails().remove(selected);
                        getCurrentListBackup().remove(selected);
                        getSelectedCurrentDetails().remove(i);
                        i--;
                    }

//                }
            }


            this.restoreDetailsTablePosition(this.getCurrentDataTable(), 
                                             this.getCurrentDetails());
            this.resetSelection();
            hideDelDiv();

//            if (hasRelatedMaterial) {
//                this.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.delConfirm);settingFoucsAtDivDeleteConfirm();");
//            }
            
        }
        if(isSearchMode()){
            cancelSearch();
        }
    }

    public void hideDelDiv() {
        setDelDivShown(true);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void preAdd() {
        setPageDTO(RegDTOFactory.createRegDecisionMaterialsDTO());
        setAddDivShown(true);
        super.preAdd();
    }

    public void selectedCurrent(ActionEvent event) throws Exception {
        super.selectedCurrent(event);
        index = getCurrentDataTable().getRowIndex();
    }

    public IBasicDTO mapToCodeNameDTO(IBasicDTO dto) {
        IRegDecisionMaterialsDTO regDecisionMaterialsDTO = 
            (IRegDecisionMaterialsDTO)dto;
        return regDecisionMaterialsDTO;
    }


   

    public String linkRelatedMaterials() {
        setPageDTO((IRegDecisionMaterialsDTO)getSelectedCurrentDetails().get(0));
//        setPageDTO((IRegDecisionMaterialsDTO)getCurrentDisplayDetails().get(index));

        if (firstVisitForRelatedMaterial) {

            IRegDecisionMaterialRelsClient regMaterialRelClient = 
                RegClientFactory.getRegDecisionMaterialRelsClient();
            try {
                ((IRegDecisionMaterialsDTO)getPageDTO()).setRelatedMaterials((List)regMaterialRelClient.getByMaterial(getPageDTO().getCode()));
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
            firstVisitForRelatedMaterial = false;
        }
        return "relatedDecisionMaterials";
    }

    public void setAddDivShown(boolean addDivShown) {
        this.addDivShown = addDivShown;
    }

    public boolean isAddDivShown() {
        return addDivShown;
    }

    public void setEditDivShown(boolean editDivShown) {
        this.editDivShown = editDivShown;
    }

    public boolean isEditDivShown() {
        return editDivShown;
    }

    public void setDelDivShown(boolean delDivShown) {
        this.delDivShown = delDivShown;
    }

    public boolean isDelDivShown() {
        return delDivShown;
    }

    public void setNonDeletedMaterial(List nonDeletedMaterial) {
        this.nonDeletedMaterial = nonDeletedMaterial;
    }

    public List getNonDeletedMaterial() {
        return nonDeletedMaterial;
    }

    public void setFirstVisitForRelatedMaterial(boolean firstVisitForRelatedMaterial) {
        this.firstVisitForRelatedMaterial = firstVisitForRelatedMaterial;
    }

    public boolean isFirstVisitForRelatedMaterial() {
        return firstVisitForRelatedMaterial;
    }
    
    
    protected  Pattern  getSimilerCharRegexPattern(String searchedString)
       {
           String[] tokens=searchedString.split("[\\s%]+");
           String pattern = ".*";
           String temp=null;
           for(String token : tokens){
            if(!token.equals(""))
             {
                       temp=new String (token) ;
                       String checkStartWith=temp.substring(0,1);
                       String checkEndWith=temp.substring(temp.length()-1,temp.length());
                       if((checkStartWith.equals(ArabicAlphabetConstants.AlephWithOutHamza+"")||checkStartWith.equals(ArabicAlphabetConstants.AlephWithUpperHamza+"")|| checkStartWith.equals(ArabicAlphabetConstants.AlephWithLowerHamza+"") || checkStartWith.equals(ArabicAlphabetConstants.AlephWithMadda+""))  && temp.length()>=1){
                       String aleph ="["+ArabicAlphabetConstants.AlephWithOutHamza+""+ArabicAlphabetConstants.AlephWithUpperHamza+""+ArabicAlphabetConstants.AlephWithLowerHamza+""+ArabicAlphabetConstants.AlephWithMadda+""+"]" ;
                       temp=temp.substring(1); 
                       temp = aleph+temp;
                       }
                       if((checkEndWith.equals(ArabicAlphabetConstants.Haa+"") || checkEndWith.equals(ArabicAlphabetConstants.TaaMarbota+"")) && temp.length()>=1){
                           String aleph ="["+ArabicAlphabetConstants.Haa+""+ArabicAlphabetConstants.TaaMarbota+"" +"]";
                           temp=temp.substring(0,temp.length()-1);
                           temp = temp+aleph;
                       }
                       if((checkEndWith.equals(ArabicAlphabetConstants.Yaa+"") || checkEndWith.equals(ArabicAlphabetConstants.AlephMaksora+"")) && temp.length()>=1){
                           String aleph ="["+ArabicAlphabetConstants.Yaa+""+ArabicAlphabetConstants.AlephMaksora+"" +"]";
                           temp=temp.substring(0,temp.length()-1);
                           temp = temp+aleph;
                       }
                       pattern = pattern + temp +".*";
                   
                   if(searchedString.startsWith("%") && !searchedString.endsWith("%"))
                   {
                       pattern = pattern.substring(0,pattern.length()-2);
                   }
                   else{
                       if(!searchedString.startsWith("%") && searchedString.endsWith("%"))
                       {
                          pattern = pattern.substring(2);
                       } 
                   }
             }
           }
           
       
           Pattern searchStringPatten = Pattern.compile(pattern);
           return searchStringPatten;
       }

       public void search(){
           setSearchMode(true);
           List<IBasicDTO>detailList= new ArrayList<IBasicDTO>();
          Pattern headPattern=getSimilerCharRegexPattern(getSearchDTO().getDecmaterialHeader());
          Pattern textPattern=getSimilerCharRegexPattern(getSearchDTO().getDecmaterialText());

           List<IRegDecisionMaterialsDTO> list = (List)getCurrentDetails();
          for(IRegDecisionMaterialsDTO dto:list){
              boolean matches =true;
              Matcher headMatcher  = headPattern.matcher(dto.getDecmaterialHeader());
              matches &=headMatcher.matches();
              Matcher textMatcher  = textPattern.matcher(dto.getDecmaterialText());
              matches &=textMatcher.matches();
              if(searchDTO.getDecmaterialCode()!=null){
                  matches &=searchDTO.getDecmaterialCode().equals(dto.getDecmaterialCode());
              }
              if (matches) {
                 detailList.add(dto);
              }

          }
            
          resetSelection();
          setCurrentDetails(detailList);
    
       }
       
       public void cancelSearch(){
          setSearchMode(false);
          setSearchDTO(RegDTOFactory.createRegDecisionMaterialsDTO());
          setCurrentDetails(getCurrentListBackup());
       }
       
      public void backFromSearch(){
          if(!isSearchMode())
          {
              cancelSearch();
          }
          setJavaScriptCaller("hideLookUpDiv(window.blocker,window.divSearch,null,null);document.getElementById('searchButton').focus();");

      }
      
      public void showSearchDiv(){
          if(!isSearchMode()){
          setCurrentListBackup(getCurrentDetails());
          }
          setJavaScriptCaller("javascript:changeVisibilityDiv(window.blocker,window.divSearch);setFocusOnlyOnElement('search_first_inputTxt');");
      }

      public void setSearchDTO(IRegDecisionMaterialsDTO searchDTO) {
          this.searchDTO = searchDTO;
      }

      public IRegDecisionMaterialsDTO getSearchDTO() {
          return searchDTO;
      }

      public void setCurrentListBackup(List<IBasicDTO> currentListBackup) {
          this.currentListBackup = currentListBackup;
      }

      public List<IBasicDTO> getCurrentListBackup() {
          return currentListBackup;
      }
      
      public void removeCurrentFromAvailable() {

          System.out.println("removing current");
          if (currentDetails != null && availableDetails != null)
              for (int j = 0; j < currentDetails.size(); j++) {
                  IBasicDTO dto = currentDetails.get(j);
                  IBasicDTO codeNameDTO = this.mapToCodeNameDTO(dto);
                  boolean exist = false;
                  for (int i = 0; i < availableDetails.size(); i++) {
                      IBasicDTO availableDTO = availableDetails.get(i);

                      if (codeNameDTO.getCode().getKey().equals(availableDTO.getCode().getKey())) {
                          exist = true;
                          if (dto.getStatusFlag() != null && 
                              dto.getStatusFlag().longValue() != 
                              deleted.longValue()) {
                              availableDetails.remove(availableDTO);
                          }

                          if (dto.getStatusFlag() == null) {

                              availableDetails.remove(availableDTO);
                          }
                      }
                  }
                  if (!exist && dto.getStatusFlag() != null && 
                      dto.getStatusFlag().longValue() == deleted.longValue()) {
                          availableDetails.add(codeNameDTO);
                  }

              }
      }
}
