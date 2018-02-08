package com.beshara.csc.gn.map.presentation.backingbean.society;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.map.business.client.MapClientFactory;
import com.beshara.csc.gn.map.business.dto.ISocietiesDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;


public class SocietyAddBean extends LookUpBaseBean {
    private final static String SEARCH_BY_CENTER_CODE = "1";
    private final static String SEARCH_BY_SYSTEM_CODE = "2";
    private String selectedRadioValue = SEARCH_BY_CENTER_CODE;
    private boolean showCenterPanel = true;
    private SearchMinistriesCustomHelperBean ministryHelper = new SearchMinistriesCustomHelperBean("societyAddBean");
    private static final String BUNDLE_NAME = "com.beshara.csc.gn.map.presentation.resources.map";
    private String ministryCode;
    private IMinistriesDTO ministriesDTO;
    private String societyName;

    public SocietyAddBean() {
        setPageDTO(MapDTOFactory.createSocietiesDTO());
        setClient(MapClientFactory.getSocietiesClient());
        initMinistryDiv();
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.setShowdatatableContent(true);
        app.setShowIntegrationDiv1(true);
        return app;
    }

    public String getSearchByCenterCode() {
        return SEARCH_BY_CENTER_CODE;
    }

    public String getSearchBySystemCode() {
        return SEARCH_BY_SYSTEM_CODE;
    }

    public void selectedValueChanged() {
        if (selectedRadioValue.equals(SEARCH_BY_CENTER_CODE)) {
            setShowCenterPanel(true);
        } else {
            setShowCenterPanel(false);
        }
        setSocietyName("");
        setMinistryCode("");

    }

    private void initMinistryDiv() {

        ministryHelper.setReturnMethodName("societyAddBean.saveLinkage");
        ministryHelper.setPreOpenMethodName("societyAddBean.preLinkMinistry");


    }

    public void preLinkMinistry() {
        if (ministriesDTO != null) {
            List<IMinistriesDTO> ministriesList = new ArrayList<IMinistriesDTO>();
            ministriesList.add(ministriesDTO);
            ministryHelper.setExcludedMinistriesList((List)ministriesList);

        }
        initMinistryDiv();
    }

    public void saveLinkage() {


        if (ministryHelper.getSelectedDTOSList() != null && ministryHelper.getSelectedDTOSList().size() == 1) {


            ministriesDTO = (IMinistriesDTO)ministryHelper.getSelectedDTOSList().get(0);

            this.setMinistryCode(ministriesDTO.getCode().getKey());
            this.setSocietyName(ministriesDTO.getName());
            if (ministriesDTO != null) {
                List execludedMinsList = new ArrayList<IMinistriesDTO>();
                execludedMinsList.add(ministriesDTO);
                ministryHelper.setExcludedMinistriesList(execludedMinsList);
            }
        }

        if (ministryHelper.getSelectedDTOSList().size() > 1) {


            getSharedUtil().setErrMsgValue(getSharedUtil().messageLocator(BUNDLE_NAME, "cantNotChooseMoreThanOne"));

        }

    }

    public String addItem() {
        //this parameter to Differentiates save and saveAndNew
        //if false , so addMode
        //else saveAndNewMode

        reIntializeMsgs();
        this.add(false);
        return "societylist";
    }

    public void saveNewItem() {
        reIntializeMsgs();
        this.add(true);


    }

    public void add(boolean saveAndNewMode) {

        SharedUtilBean sharedUtil = getSharedUtil();
        ListBean listBean =
            (ListBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{societiesListBean}").getValue(FacesContext.getCurrentInstance());
        ISocietiesDTO dto = MapDTOFactory.createSocietiesDTO();

        try {
            if (SEARCH_BY_CENTER_CODE.equals(selectedRadioValue)) {
                dto.setMinCode(Long.valueOf(ministryCode));
            }
            dto.setSocietiesStatus(Long.valueOf(selectedRadioValue));
            dto.setName(getSocietyName());
            IBasicDTO basicDTO = MapClientFactory.getSocietiesClient().add(dto);

            listBean.getAll();
            listBean.getPageIndex(basicDTO.getCode().getKey());
            if (listBean.getHighlightedDTOS() != null) {
                listBean.getHighlightedDTOS().clear();
                listBean.getHighlightedDTOS().add(basicDTO);
            }
            this.setSearchQuery("");
            this.setSearchType(0);
            this.setSearchMode(false);
            setMinistryCode(null);
            setSocietyName(null);
            if (!saveAndNewMode) {
                listBean.getSharedUtil().setSuccessMsgValue("SuccessAdds");
            } else {
                this.setSuccess(true);
            }
        } catch (DataBaseException e) {
            this.setShowErrorMsg(true);

            //            sharedUtil.handleException(e,
            //                                       "com.beshara.jsfbase.csc.msgresources.globalexceptions",
            //                                       "FailureInAdd");
            listBean.getSharedUtil().handleException(e);
            this.setErrorMsg(sharedUtil.getErrMsgValue());
        } catch (SharedApplicationException e) {
            this.setShowErrorMsg(true);
            listBean.getSharedUtil().handleException(e);
            this.setErrorMsg("FailureInAdd");


        } catch (Exception e) {
            this.setShowErrorMsg(true);
            this.setErrorMsg("FailureInAdd");
            listBean.getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                                     "FailureInAdd");

        }

        setSelectedRadio("");
    }

    public String goBack() {
        return "societylist";
    }

    public void getMinById() {
        List<IMinistriesEntityKey> entityKeyList = new ArrayList<IMinistriesEntityKey>();
        try {

            IMinistriesClient ministryClient = OrgClientFactory.getMinistriesClient();
            entityKeyList.add(new MinistriesEntityKey(Long.parseLong(ministryCode)));
            List<IMinistriesDTO> ministriesList = (List)ministryClient.getCodeNameById(entityKeyList);
            if (ministriesList != null && ministriesList.size() != 0) {
                setMinistriesDTO(ministriesList.get(0));
                setSocietyName(getMinistriesDTO().getName());
            }


            if (ministriesDTO != null) {
                List execludedMinsList = new ArrayList<IMinistriesDTO>();
                execludedMinsList.add(ministriesDTO);

                ministryHelper.setExcludedMinistriesList(execludedMinsList);
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            setSocietyName("");
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            setSocietyName("");
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            setSocietyName("");
        }
    }


    public void setSelectedRadioValue(String selectedRadioValue) {
        this.selectedRadioValue = selectedRadioValue;
    }

    public String getSelectedRadioValue() {
        return selectedRadioValue;
    }

    public void setShowCenterPanel(boolean showCenterPanel) {
        this.showCenterPanel = showCenterPanel;
    }

    public boolean isShowCenterPanel() {
        return showCenterPanel;
    }

    public void setMinistryHelper(SearchMinistriesCustomHelperBean ministryHelper) {
        this.ministryHelper = ministryHelper;
    }

    public SearchMinistriesCustomHelperBean getMinistryHelper() {
        return ministryHelper;
    }

    public void setMinistryCode(String ministryCode) {
        this.ministryCode = ministryCode;
    }

    public String getMinistryCode() {
        return ministryCode;
    }

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO) {
        this.ministriesDTO = ministriesDTO;
    }

    public IMinistriesDTO getMinistriesDTO() {
        return ministriesDTO;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getSocietyName() {
        return societyName;
    }
}
