package com.beshara.csc.gn.grs.presentation.backingbean.lines;

import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.client.IParametersClient;
import com.beshara.csc.gn.grs.business.dto.GrsDTOFactory;
import com.beshara.csc.gn.grs.business.dto.ILineValuesDTO;
import com.beshara.csc.gn.grs.business.dto.ILinesDTO;
import com.beshara.csc.gn.grs.business.dto.IOperatorsDTO;
import com.beshara.csc.gn.grs.business.dto.IParameterValuesDTO;
import com.beshara.csc.gn.grs.business.dto.IParametersDTO;
import com.beshara.csc.gn.grs.business.dto.ISearchLinesDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IOperatorsEntityKey;
import com.beshara.csc.gn.grs.business.entity.IParametersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;
import com.beshara.jsfbase.csc.util.Helper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;


public class LinesListBean extends LookUpBaseBean {

    private String parameterCode;

    private List parametersList = new ArrayList();

    private String lineName;

    private IParametersDTO parametersDTO = GrsDTOFactory.createParametersDTO();

    private ISearchLinesDTO searchDTO = GrsDTOFactory.createSearchLinesDTO();

    protected List<IBasicDTO> parameterValues = new ArrayList<IBasicDTO>();

    private IParametersClient parametrClient = 
        GrsClientFactory.getParametersClient();

    public LinesListBean() {

        setClient(GrsClientFactory.getLinesClient());

        setPageDTO(GrsDTOFactory.createLinesDTO());

        setSelectedPageDTO(GrsDTOFactory.createLinesDTO());

        searchDTO = GrsDTOFactory.createSearchLinesDTO();

        setUsingPaging(true);

    }

    public void searchLine() throws DataBaseException, NoResultException {

        System.out.println("Calling search()...");
        this.setSearchMode(true);

        if (parameterCode != null && !parameterCode.equals("")) {
            searchDTO.setParameterCode(Long.parseLong(parameterCode));
        }

        if (searchDTO.getLineValue() != null && 
            searchDTO.getLineValue().equals("")) {
            searchDTO.setLineValue(null);
        }
        if (searchDTO.getLineName() != null)
            searchDTO.setLineName(formatSearchString(searchDTO.getLineName()));

        if (isUsingPaging()) {

            generatePagingRequestDTO("linesListBean", "searchLineWithPaging", 
                                     searchDTO);
            resetPageIndex();

        } else {

            this.setMyTableData(getSearchResult());
            this.repositionPage(0);

        }

        if (getSelectedDTOS() != null)
            setSelectedDTOS(new ArrayList<IBasicDTO>());
        if (getHighlightedDTOS() != null)
            setHighlightedDTOS(new ArrayList<IBasicDTO>());

    }

    public PagingResponseDTO searchLineWithPaging(PagingRequestDTO request) {

        searchDTO = (ISearchLinesDTO)request.getSearchDTO();

        try {
            return new PagingResponseDTO(getSearchResult());
        } catch (DataBaseException e) {
            return new PagingResponseDTO(new ArrayList());
        }

    }

    private List getSearchResult() throws DataBaseException {

        List searchResult = null;

        try {

            searchResult = getClient().search(searchDTO);

        } catch (ItemNotFoundException e) { //this means no search results found
            searchResult = new ArrayList();
            e.printStackTrace();

        } catch (NoResultException ne) { //this means no search results found
            ne.printStackTrace();
            searchResult = new ArrayList();

        } catch (Exception db) {
            db.printStackTrace();
            ErrorDisplay errorDisplay = 
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(db.getMessage());
            errorDisplay.setSystemException(true);
            throw new DataBaseException(db.getMessage());
        }

        return searchResult;

    }

    public void cancelSearch() throws DataBaseException {

        System.out.println("Calling cancelSearch()...");
        this.setParameterCode(null);
        this.setSearchDTO(GrsDTOFactory.createSearchLinesDTO());
        super.cancelSearch();

    }


    public String addLine() {
        return "addLine";
    }

    public void selectedCheckboxs(ActionEvent event) {

        try {

            IClientDTO selected = 
                (IClientDTO)this.getMyDataTable().getRowData();

            if (selected.getChecked()) {


                boolean exist = false;
                for (IBasicDTO dto: this.getSelectedDTOS()) {
                    if (dto.getCode().getKey().equals(selected.getCode().getKey()))
                        exist = true;
                }
                if (!exist) {
                    this.getSelectedDTOS().add(selected);

                    System.out.println("adding...");
                    System.out.println(selected.getName());
                }
            }

            if (!(selected.getChecked())) {


                for (int i = 0; i < this.getSelectedDTOS().size(); i++) {

                    IBasicDTO dto = this.getSelectedDTOS().get(i);
                    if (dto.getCode().getKey().equals(selected.getCode().getKey())) {

                        this.getSelectedDTOS().remove(i);
                        System.out.println("removing...");
                        System.out.println(selected.getName());

                    }
                }


            }

            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ex = ctx.getExternalContext();
            HttpSession session = (HttpSession)ex.getSession(true);
            session.setAttribute("selectedDTOS", this.getSelectedDTOS());


        } catch (Exception e) {

            e.printStackTrace();

        }

        System.out.println("basebean::selectedCheckboxs::the size of the selected list=" + 
                           this.getSelectedDTOS().size());


        //for the edit operations
        if (this.getSelectedDTOS() != null && 
            this.getSelectedDTOS().size() == 1) {
            IBasicDTO dto = this.getSelectedDTOS().get(0);
            try {
                super.setSelectedPageDTO(getClient().getById(dto.getCode()));

                this.setAlreadyDeleted(false);
            } catch (ItemNotFoundException e) {

                e.printStackTrace();
                super.setAlreadyDeleted(true);

            }

            catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setSelectedPageDTO(new BasicDTO());
            setShowEdit(false);

        }

    }

    public String editLine() {

        try {

            String value = " ";
            FacesContext fc = FacesContext.getCurrentInstance();
            Application app = fc.getApplication();
            LinesEditBean lineEditBean = 
                (LinesEditBean)app.createValueBinding("#{linesEditBean}").getValue(fc);
            ILinesDTO linesDTO = GrsDTOFactory.createLinesDTO();
            try {
                linesDTO = 
                        (ILinesDTO)getClient().getById((getSelectedDTOS().get(0)).getCode());
            } catch (SharedApplicationException e) {
                super.getAll();
                super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                setSelectedPageDTO(new BasicDTO());
                setSelectedDTOS(new ArrayList<IBasicDTO>());
                return null;
            } catch (DataBaseException e) {

                super.getAll();
                super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                setSelectedPageDTO(new BasicDTO());
                setSelectedDTOS(new ArrayList<IBasicDTO>());

                return null;
            } catch (Exception e) {
                super.getAll();
                super.getSharedUtil().setErrMsgValue("FailureInUpdate");
                setSelectedPageDTO(new BasicDTO());
                setSelectedDTOS(new ArrayList<IBasicDTO>());
                return null;
            }

            if (linesDTO != null) {
                lineEditBean.setPageDTO(linesDTO);
                //lineEditBean.setPageDTO(getSelectedDTOS().get(0));
                //LinesDTO linesDTO=(LinesDTO)getSelectedDTOS().get(0);

                lineEditBean.setParametersDTO((IParametersDTO)(linesDTO.getParametersDTO()));
                lineEditBean.setOperatorsDTO((IOperatorsDTO)(linesDTO.getOperatorsDTO()));
                lineEditBean.setParameterCode(((IParametersEntityKey)((IParametersDTO)(linesDTO.getParametersDTO())).getCode()).getParameterCode().toString());
                lineEditBean.setOperationCode(((IOperatorsEntityKey)((IOperatorsDTO)(linesDTO.getOperatorsDTO())).getCode()).getOperatorSign());

                if (linesDTO.getParameterLineValuesList() != null) {
                    int i=0;
                    for (IBasicDTO basic:linesDTO.getParameterLineValuesList()) {
                        value += ((IParameterValuesDTO)basic).getName();
                    if(i<linesDTO.getParameterLineValuesList().size()-1)   
                        {
                        value +=",";
                        i++;
                        }
                    }

                }

                else if (linesDTO.getLineValuesList() != null && 
                         linesDTO.getParameterLineValuesList() == null) {
                    int i=0;
                    for (IBasicDTO basic: linesDTO.getLineValuesList()) {
                        value += ((ILineValuesDTO)basic).getLnevalue();
                        if(i<linesDTO.getLineValuesList().size()-1)   
                            {
                            value +=",";
                            i++;
                            }
                    }
                }
                if (linesDTO.getParameterLineValuesList() != null && 
                    linesDTO.getLineValuesList() != null) {
                    //lineEditBean.setLineValueList((List<BasicDTO>)linesDTO.getParameterLineValuesList());
                    lineEditBean.setLineValueList((List)linesDTO.getParameterLineValuesList());
                    //lineEditBean.setSelectedAvailableValues((List<IBasicDTO>)linesDTO.getParameterLineValuesList());
                    lineEditBean.setSelectedAvailableValues((List)linesDTO.getParameterLineValuesList());
                    lineEditBean.setSelectedListSize(linesDTO.getParameterLineValuesList().size());
                } else if (linesDTO.getLineValuesList() != null) {
                    //lineEditBean.setLineValueList((List<BasicDTO>)linesDTO.getLineValuesList());
                    lineEditBean.setLineValueList((List)linesDTO.getLineValuesList());
                    lineEditBean.setSelectedAvailableValues((List<IBasicDTO>)linesDTO.getLineValuesList());
                    lineEditBean.setSelectedListSize(linesDTO.getLineValuesList().size());
                }
                lineEditBean.setValue(value);
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

        return "editLine";
    }

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideConditionName() {
        showHideColumn("name_column");
    }

    public void setParameterCode(String parameterCode) {
        System.out.println("setParameterCode" + parameterCode);
        this.parameterCode = parameterCode;
    }

    public String getParameterCode() {
        System.out.println("getParameterCode" + parameterCode);
        return parameterCode;
    }

    public void updateParametrValuesList(ActionEvent ae) {
        System.out.println("calling.......... updateParametrValuesList");
        if ((parameterCode != null) && !parameterCode.equals("")) {

            IEntityKey parameterEntityKey = 
                GrsEntityKeyFactory.createParametersEntityKey();
            try {
                parameterEntityKey = 
                        Helper.getEntityKey(parametersList, parameterCode);
                parametersDTO = 
                        (IParametersDTO)parametrClient.getById(parameterEntityKey);
                if (parametersDTO.isValidSQL())
                    parameterValues = 
                            parametrClient.getParameterValues(parametersDTO);


            } catch (SharedApplicationException e) {
                e.printStackTrace();
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setParametersList(List parametersList) {
        this.parametersList = parametersList;
    }

    public List getParametersList() throws DataBaseException, 
                                           SharedApplicationException {
        try {

            parametersList = parametrClient.getCodeName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametersList;
    }

    public void setParametersDTO(IParametersDTO parametersDTO) {
        this.parametersDTO = parametersDTO;
    }

    public IParametersDTO getParametersDTO() {
        return parametersDTO;
    }

    public void setParameterValues(List<IBasicDTO> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public List<IBasicDTO> getParameterValues() {
        return parameterValues;
    }

    public void setSearchDTO(ISearchLinesDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    public ISearchLinesDTO getSearchDTO() {
        return searchDTO;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineName() {
        return lineName;
    }
}
