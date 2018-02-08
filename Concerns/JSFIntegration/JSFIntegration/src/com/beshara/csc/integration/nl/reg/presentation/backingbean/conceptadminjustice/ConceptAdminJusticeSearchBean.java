package com.beshara.csc.nl.reg.presentation.backingbean.conceptadminjustice;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGCourtChambersDTO;
import com.beshara.csc.nl.reg.business.dto.IREGCourtDegreesDTO;
import com.beshara.csc.nl.reg.business.dto.IRegAdminJusticeSearchDTO;
import com.beshara.csc.nl.reg.business.dto.ISubjectsDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;


public class ConceptAdminJusticeSearchBean extends LookUpBaseBean {

    private List<IREGCourtDegreesDTO> courtDegreesList = 
        new ArrayList<IREGCourtDegreesDTO>();
    private List<IREGCourtChambersDTO> courtChambersList = 
        new ArrayList<IREGCourtChambersDTO>();
    private List<IYearsDTO> yearsList = new ArrayList<IYearsDTO>();
    private String subjectName = "";
    SubjectTreeDivBean treepageDivBean;

    public ConceptAdminJusticeSearchBean() {
        setPageDTO(RegDTOFactory.createRegAdminJusticeSearchDTO());
        setClient(RegClientFactory.getREGAdminJusticeClientForCenter());
        treepageDivBean = 
                (SubjectTreeDivBean)evaluateValueBinding("SubjectTreePaggedDivBean");
        treepageDivBean.setBundleName("com.beshara.csc.nl.reg.presentation.resources.reg_");
        treepageDivBean.setMyTableData(new ArrayList());
        treepageDivBean.setRootName("reg_subjects");
        treepageDivBean.setClient(RegClientFactory.getSubjectsClient());
        treepageDivBean.setPageDTO(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDto(RegDTOFactory.createSubjectsDTO());
        treepageDivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowContent1(true);
        app.setShowTreediv(true);
        return app;
    }

    public void setCourtDegreesList(List<IREGCourtDegreesDTO> courtDegreesList) {
        this.courtDegreesList = courtDegreesList;
    }

    public List<IREGCourtDegreesDTO> getCourtDegreesList() {
        if (courtDegreesList == null || courtDegreesList.size() == 0) {
            try {
                courtDegreesList = 
                        (List)RegClientFactory.getREGCourtDegreesClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return courtDegreesList;
    }

    public void setYearsList(List<IYearsDTO> yearsList) {
        this.yearsList = yearsList;
    }

    public List<IYearsDTO> getYearsList() {
        if (yearsList == null || yearsList.size() == 0) {
            try {
                yearsList = InfClientFactory.getYearsClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return yearsList;
    }

    public void setCourtChambersList(List<IREGCourtChambersDTO> courtChambersList) {
        this.courtChambersList = courtChambersList;
    }

    public List<IREGCourtChambersDTO> getCourtChambersList() {
        if (courtChambersList == null || courtChambersList.size() == 0) {
            try {
                courtChambersList = 
                        (List)RegClientFactory.getRegCourtChambersClient().getCodeName();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return courtChambersList;
    }

    public void openSubjectAddDiv() {
        try {
            treepageDivBean.setSearchMode(false);
            treepageDivBean.setSearchQuery("");
            treepageDivBean.setSerachResult(false);
            treepageDivBean.getHtmlTree().collapseAll();
            treepageDivBean.setReadCenter(true);
            treepageDivBean.setSelectedNodeId("");
            treepageDivBean.setSelectionNo(0);
            treepageDivBean.setEnableSearchByCode(true);
            treepageDivBean.setTreeNodeBase(null);
            treepageDivBean.setMyTableData(RegClientFactory.getSubjectsClient().getFirstLevel());
            //the add button in the div will invoke manytomany add function
            MethodBinding mb = 
                evaluateMethodBinding("conceptAdminJusticeSearchBean" + ".add", 
                                      null);
            treepageDivBean.getOkCommandButton().setAction(mb);
            treepageDivBean.getOkCommandButton().setReRender("selectedNodeField");
            ValueBinding vb = 
                FacesContext.getCurrentInstance().getApplication().createValueBinding("#{(!SubjectTreePaggedDivBean.enabledRoot )&& (SubjectTreePaggedDivBean.selectionNo==1)}");
            treepageDivBean.getOkCommandButton().setValueBinding("rendered", 
                                                                 vb);
            MethodBinding mb2 = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding("#{conceptAdminJusticeSearchBean.cancelTreeSearch}", 
                                                                                       null);
            treepageDivBean.getCancelSearchCommandButton().setAction(mb2);
            //loading customized initialization so if you extends this class you must overrid following method 
            try {
                treepageDivBean.cancelSearchTree();
            } catch (Exception e) {
                e.printStackTrace(); // TODO
            }

            treepageDivBean.setSearchType(1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cancelSearchTree() throws DataBaseException, RemoteException, 
                                          Exception {
        treepageDivBean = 
                (SubjectTreeDivBean)evaluateValueBinding("SubjectTreePaggedDivBean");
        treepageDivBean.cancelSearchTree();
        treepageDivBean.getHtmlTree().collapseAll();
    }

    public void cancelTreeSearch() {

        treepageDivBean.setSearchQuery("");
        treepageDivBean.setSerachResult(false);
        treepageDivBean.setSearchMode(false);
        treepageDivBean.setSelectionNo(0);
        treepageDivBean.setSelectedNodeId("");
        treepageDivBean.setMyTableData(new ArrayList());
        treepageDivBean.setTreeNodeBase(null);
        treepageDivBean.getHtmlTree().collapseAll();
        treepageDivBean.setReadCenter(true);
        treepageDivBean.setDtoDetails(RegDTOFactory.createSubjectsDTO());
        try {
            treepageDivBean.cancelSearchTree();
        } catch (Exception e) {
            e.printStackTrace(); // TODO
        }
        treepageDivBean.setSearchType(1);
    }

    public void add() {
      if(treepageDivBean!=null)
      {
        setSubjectName(((ISubjectsDTO) treepageDivBean.getDtoDetails()).getName());
        ((IRegAdminJusticeSearchDTO)getPageDTO()).setSubjectCode(new Long(((ISubjectsDTO) treepageDivBean.getDtoDetails()).getCode().getKey()));
    }}

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
