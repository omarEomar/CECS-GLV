package com.beshara.csc.nl.job.integration.presentation.backingbean.search;


import com.beshara.base.client.ServiceNotAvailableException;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.nl.job.business.client.ICatsClient;
import com.beshara.csc.nl.job.business.client.IJobCatTypesClient;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobCatLevelsDTO;
import com.beshara.csc.nl.job.business.dto.IJobCatTypesDTO;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.job.business.util.IJobConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.BaseBean;
import com.beshara.jsfbase.csc.backingbean.paging.PagingRequestDTO;
import com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO;
import com.beshara.jsfbase.csc.util.ErrorDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.apache.myfaces.component.html.ext.HtmlOutputText;
import org.apache.myfaces.custom.datascroller.ScrollerActionEvent;

import weblogic.ejb20.utils.OrderedSet;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class use to filter job categories  as J2EE Recommendation I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 11g ( 11.1.2.2.0) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generaor 26-May-2014 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Kareem Sayed
 * @version 1.0
 * @since 26/05/2014
 */
public class JobFilter extends BaseBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private static final int TREE_LEVEL_1 = 1;
    private static final int TREE_LEVEL_2 = 2;
    private static final int TREE_LEVEL_3 = 3;
    private static final int TREE_LEVEL_4 = 4;
    private static final int TREE_LEVEL_5 = 5;
    private static final int TREE_LEVEL_6 = 6;
    private static final int TREE_LEVEL_7 = 7;
    private static final String BUNDLE_NAME = "com.beshara.csc.nl.job.integration.presentation.resources.integration";
    private static final String BUNDLE_Key_JOB_GROUP = "inavlid_job_val_job_val";
    private static final String BUNDLE_KEY_LEVELS_GROUP = "inavlid_job_val_job_val1";
    private static final String BUNDLE_KEY_LEVEL = "inavlid_job_val_job_val2";
    private static final String BUNDLE_KEY_SUB_WORK_TYPE = "inavlid_job_val_job_val3";
    private static final String BUNDLE_KEY_WORK_TYPE_DESC = "inavlid_job_val_job_val4";
    private static final String BUNDLE_KEY_GROUP_TYPE = "inavlid_job_val_job_val5";
    private static final String BUNDLE_KEY_JOB_LEVEL = "inavlid_job_val_job_val6";

    private static HashMap<String, String> availablejobMap = new HashMap<String, String>();
    private String jobFieldValError = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private int codeMaxLength = IJobConstants.CODE_MAX_LENGHT;
    private String showMsg = IJobConstants.DISPAY_NONE;
    private transient HtmlOutputText errorMsgBind;
    private boolean showJobFieldValError;

    private Long minCode;
    private String jobKey = null;
    private String jobName = null;
    private String jobGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedJobGroup = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private String levelsGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedlevelsGroup = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private String levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private String subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private String workTypeDescCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private String groupTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedGroupType = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private List<SelectItem> groupTypeList = new ArrayList<SelectItem>();
    private String jobLevelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
    private String selectedJobLevel = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
    private List<SelectItem> jobLevelList = new ArrayList<SelectItem>();
    private String buttonValue = IJobConstants.VIEW_DATA;
    private boolean resetMode = Boolean.FALSE;
    private boolean disableSearchBtn = true;
    private List<String> excludedJobList = new ArrayList<String>();
    private String okButtonMethod;
    private String searchButtonMethod;
    private List<SelectItem> jobGroups = new ArrayList<SelectItem>();
    private List<SelectItem> levelsGroupFields = new ArrayList<SelectItem>();
    private List<SelectItem> levels = new ArrayList<SelectItem>();
    private List<SelectItem> subWorkTypes = new ArrayList<SelectItem>();
    private List<SelectItem> workTypeDescFields = new ArrayList<SelectItem>();
    private boolean loadSecAccessibleJobsOnly;
    private boolean disableLevelGroup;
    private boolean disableJobGroup;
    private com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO;
    IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
    private String notInCatCode;
    private boolean notShowLeaderJobs;
    private String cancelSearchButtonMethod;
    private Long startWithLeaderLevelCode;
    private String selectedLeaderLevel;
    private boolean superVisorJobs ; 


    public JobFilter() {
        super();
        setUsingPaging(true);
        setUsingBsnPaging(true);
        setSaveSortingState(true);

    }

    public PagingResponseDTO getAllWithPaging(PagingRequestDTO request) {
        return new PagingResponseDTO(new ArrayList());
    }

    public void addJobs() {
        if (getOkButtonMethod() != null) {
            JSFHelper.callMethod(getOkButtonMethod());
        }
    }

    public void searchJobs() throws DataBaseException, NoResultException {
        if (getSearchButtonMethod() != null) {
            JSFHelper.callMethod(getSearchButtonMethod());
        } else {
            search();
        }
    }

    public void resetValues(int level) {
        switch (level) {
        case TREE_LEVEL_1:
            this.levelsGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedlevelsGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.groupTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedGroupType = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
            break;

        case TREE_LEVEL_2:
            this.levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            break;

        case TREE_LEVEL_3:
            this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            break;

        case TREE_LEVEL_4:
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            break;

        case TREE_LEVEL_5:
            this.jobLevelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedJobLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            break;
            //
            //        case TREE_LEVEL_6:
            //            setSelectedLevelsValCode("");
            //            setLevelsCode("");

            //break;
        default:
            this.selectedJobGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.jobGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.levelsGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedlevelsGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.groupTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedGroupType = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
            this.jobLevelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.selectedJobLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.jobKey = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            this.jobName = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            setButtonValue(IJobConstants.VIEW_DATA);
            setResetMode(true);
            setMyTableData(new ArrayList<IBasicDTO>());
            setGroupTypeList(new ArrayList<SelectItem>());
            setJobLevelList(new ArrayList<SelectItem>());
            break;
        }

    }

    protected void showError(String compontntMsg) {
        setShowMsg(IJobConstants.DISPAY_BLOCK);
        errorMsgBind.setValue(compontntMsg);
        setShowJobFieldValError(true);
    }

    protected boolean isValidString(String strValue) {
        return (strValue != null && !strValue.trim().equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY) &&
                !strValue.trim().equals(IJobConstants.INITIAL_FIELD_VALUE_ZERO));
    }

    /**
     * Method to check about selected in HashMap
     * @param filter
     * @return boolean
     */
    protected boolean checkValidVal(int filter) {

        boolean valid = false;
        switch (filter) {
        case (IJobConstants.TREE_LEVEL_1):
            valid = this.getAvailablejobMap().containsKey(this.jobGroupCode);
            break;
        case (IJobConstants.TREE_LEVEL_2):
            valid = this.getAvailablejobMap().containsKey(this.levelsGroupCode);
            break;
        case (IJobConstants.TREE_LEVEL_3):
            valid = this.getAvailablejobMap().containsKey(this.levelCode);
            break;
        case (IJobConstants.TREE_LEVEL_4):
            valid = this.getAvailablejobMap().containsKey(this.getSubWorkTypeCode());
            break;
        case (IJobConstants.TREE_LEVEL_5):
            valid = this.getAvailablejobMap().containsKey(this.getWorkTypeDescCode());
            break;
        case (TREE_LEVEL_6):
            valid = checkedGroupTypeKeyFound();
            break;
        case (TREE_LEVEL_7):
            valid = checkedLevelKeyFound();
            break;
        default:
            break;
        }

        return valid;
    }

    private boolean checkedLevelKeyFound() {
        Long selectedCatId = null;
        boolean valid = false;
        IEntityKey toBeCheckedLevelKey =
            JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), getSelectedCatId(),
                                                         Long.parseLong(getGroupTypeCode()));
        valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());

        if (!valid && isValidString(getSelectedWorkTypeDesc())) {
            selectedCatId = Long.parseLong(getSelectedWorkTypeDesc());
            toBeCheckedLevelKey =
                    JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), selectedCatId,
                                                                 Long.parseLong(getGroupTypeCode()));
            valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());
        }
        if (!valid && isValidString(getSelectedSubWorkType())) {
            selectedCatId = Long.parseLong(getSelectedSubWorkType());
            toBeCheckedLevelKey =
                    JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), selectedCatId,
                                                                 Long.parseLong(getGroupTypeCode()));
            valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());
        }
        if (!valid && isValidString(getSelectedLevel())) {
            selectedCatId = Long.parseLong(getSelectedLevel());
            toBeCheckedLevelKey =
                    JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), selectedCatId,
                                                                 Long.parseLong(getGroupTypeCode()));
            valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());
        }
        if (!valid && isValidString(getSelectedlevelsGroup())) {
            selectedCatId = Long.parseLong(getSelectedlevelsGroup());
            toBeCheckedLevelKey =
                    JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), selectedCatId,
                                                                 Long.parseLong(getGroupTypeCode()));
            valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());
        }
        if (!valid && isValidString(getSelectedJobGroup())) {
            selectedCatId = Long.parseLong(getSelectedJobGroup());
            toBeCheckedLevelKey =
                    JobEntityKeyFactory.createCatLevelsEntityKey(Long.parseLong(getJobLevelCode()), selectedCatId,
                                                                 Long.parseLong(getGroupTypeCode()));
            valid = this.getAvailablejobMap().containsKey(toBeCheckedLevelKey.getKey());
        }


        return valid;
    }

    private boolean checkedGroupTypeKeyFound() {
        Long selectedCatId = null;
        boolean valid = false;
        IEntityKey toBeCheckedKey =
            JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), getSelectedCatId());
        valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());

        if (!valid && isValidString(getSelectedWorkTypeDesc())) {
            selectedCatId = Long.parseLong(getSelectedWorkTypeDesc());
            toBeCheckedKey =
                    JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), selectedCatId);
            valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());
        }
        if (!valid && isValidString(getSelectedSubWorkType())) {
            selectedCatId = Long.parseLong(getSelectedSubWorkType());
            toBeCheckedKey =
                    JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), selectedCatId);
            valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());
        }
        if (!valid && isValidString(getSelectedLevel())) {
            selectedCatId = Long.parseLong(getSelectedLevel());
            toBeCheckedKey =
                    JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), selectedCatId);
            valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());
        }
        if (!valid && isValidString(getSelectedlevelsGroup())) {
            selectedCatId = Long.parseLong(getSelectedlevelsGroup());
            toBeCheckedKey =
                    JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), selectedCatId);
            valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());
        }
        if (!valid && isValidString(getSelectedJobGroup())) {
            selectedCatId = Long.parseLong(getSelectedJobGroup());
            toBeCheckedKey =
                    JobEntityKeyFactory.createJobCatTypeEntityKey(Long.parseLong(getGroupTypeCode()), selectedCatId);
            valid = this.getAvailablejobMap().containsKey(toBeCheckedKey.getKey());
        }


        return valid;
    }

    private Long getSelectedCatId() {

        Long selectedCatId = null;
        if (isValidString(getSelectedWorkTypeDesc())) {
            selectedCatId = Long.parseLong(getSelectedWorkTypeDesc());
        } else if (isValidString(getSelectedSubWorkType())) {
            selectedCatId = Long.parseLong(getSelectedSubWorkType());
        } else if (isValidString(getSelectedLevel())) {
            selectedCatId = Long.parseLong(getSelectedLevel());
        } else if (isValidString(getSelectedlevelsGroup())) {
            selectedCatId = Long.parseLong(getSelectedlevelsGroup());
        } else if (isValidString(getSelectedJobGroup())) {
            selectedCatId = Long.parseLong(getSelectedJobGroup());
        }
        return selectedCatId;
    }

    protected IBasicDTO getSelectedCatDto() {
        IBasicDTO catDto = null;
        if (getSelectedCatId() != null) {
            ICatsClient catsClient = JobClientFactory.getCatsClient();
            try {
                catDto = catsClient.getById(JobEntityKeyFactory.createCatsEntityKey(getSelectedCatId()));
            } catch (DataBaseException e) {
                e.printStackTrace();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
            }
        }
        return catDto;
    }

    /************ START__ jobGroup Section***************/

    public void jobGroupCodeChanged() throws DataBaseException {

        if (isValidString(getJobGroupCode())) {
            if (checkValidVal(IJobConstants.TREE_LEVEL_1)) {
                this.selectedJobGroup = this.getJobGroupCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_Key_JOB_GROUP);
                showError(msg);
                this.jobGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedJobGroup = IJobConstants.INITIAL_FIELD_VALUE_ZERO;
                setGroupTypeList(new ArrayList<SelectItem>());
            }
        } else {
            this.selectedJobGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
        // TODO empty all under fileds
        resetValues(TREE_LEVEL_1);
        loadGroupTypeList();
        this.getAll();
    }

    public void selectedJobCodeChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedJobGroup.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.jobGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            setGroupTypeList(new ArrayList<SelectItem>());
        } else {
            this.jobGroupCode = this.selectedJobGroup;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
        resetValues(TREE_LEVEL_1);
        loadGroupTypeList();
        this.getAll();

    }

    public void setJobGroups(List<SelectItem> jobGroups) {
        this.jobGroups = jobGroups;
    }

    public List<SelectItem> getJobGroups() throws SharedApplicationException {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<IBasicDTO> fildDTOList = new ArrayList<IBasicDTO>(0);
        ICatsClient catsClient = JobClientFactory.getCatsClient();
        if (selectItems.isEmpty()) {
            try {
                fildDTOList = catsClient.getFirstLevel();
                if (isNotShowLeaderJobs()) {
                    for (IBasicDTO fildDTOCode : fildDTOList) {
                        if (fildDTOCode.getCode().getKey().equals("17")) {
                            fildDTOList.remove(fildDTOCode);
                            break;
                        }
                    }
                }
            } catch (DataBaseException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (IBasicDTO dto : fildDTOList) {
                selectItems.add(new SelectItem(String.valueOf(dto.getCode().getKey()), dto.getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(TREE_LEVEL_1));
            }
        }
        return selectItems;
    }

    /************ END__ jobGroup Section***************/

    /************ START__ levelsGroup Section***************/
    public void levelsGroupCodeChanged() {
        if (isValidString(getLevelsGroupCode())) {
            if (checkValidVal(IJobConstants.TREE_LEVEL_2)) {
                this.selectedlevelsGroup = this.getLevelsGroupCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_LEVELS_GROUP);
                showError(msg);
                this.levelsGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedlevelsGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }
        } else {
            this.selectedlevelsGroup = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
        resetValues(TREE_LEVEL_2);
        loadGroupTypeList();

    }

    public void selectedLevelsGroupCodeChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedlevelsGroup.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.levelsGroupCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        } else {
            this.levelsGroupCode = this.selectedlevelsGroup;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
        resetValues(TREE_LEVEL_2);
        loadGroupTypeList();
    }

    public List<SelectItem> getLevelsGroupFields() throws SharedApplicationException {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<IBasicDTO> fildDTOList = new ArrayList<IBasicDTO>(0);
        ICatsClient catsClient = JobClientFactory.getCatsClient();
        if (levelsGroupFields.isEmpty() && isValidString(getSelectedJobGroup())) {
            try {
                fildDTOList = catsClient.getAllByParentCode(Long.valueOf(getSelectedJobGroup()));
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (IBasicDTO dto : fildDTOList) {
                selectItems.add(new SelectItem(String.valueOf(dto.getCode().getKey()), dto.getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(IJobConstants.TREE_LEVEL_2));
            }
        } else if (levelsGroupFields.size() > 0) {
            selectItems.addAll(levelsGroupFields);
        }
        return selectItems;
    }

    /************ END__ levelsGroup Section***************/

    /************ START__ levels Section***************/
    public void levelsValChanged() {
        selectedLeaderLevel = null ;
        if (isValidString(getLevelCode())) {
            selectedLeaderLevel = getLevelCode();
            if (checkValidVal(IJobConstants.TREE_LEVEL_3)) {
                this.selectedLevel = this.getLevelCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_LEVEL);
                showError(msg);

                this.levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }

        } else {
            this.selectedLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
        resetValues(TREE_LEVEL_3);
        loadGroupTypeList();
    }

    public void selectedLevelsChanged(ActionEvent e) throws DataBaseException {
        selectedLeaderLevel = null ;
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedLevel.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.levelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        } else {
            selectedLeaderLevel = selectedLevel;
            this.levelCode = this.selectedLevel;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
        resetValues(TREE_LEVEL_3);
        loadGroupTypeList();
    }

    public List<SelectItem> getLevels() throws SharedApplicationException {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<IBasicDTO> fildDTOList = new ArrayList<IBasicDTO>(0);
        ICatsClient catsClient = JobClientFactory.getCatsClient();
        if (selectItems.isEmpty() && isValidString(getSelectedlevelsGroup())) {
            try {
                if (startWithLeaderLevelCode != null) {
                    fildDTOList = catsClient.getLevelsForLeadingJobs(startWithLeaderLevelCode);
                } else {
                    fildDTOList = catsClient.getAllByParentCode(Long.valueOf(getSelectedlevelsGroup()));
                }
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (IBasicDTO dto : fildDTOList) {
                selectItems.add(new SelectItem(String.valueOf(dto.getCode().getKey()), dto.getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(IJobConstants.TREE_LEVEL_3));
            }
        }
        return selectItems;
    }
    /************ END__ levels Section***************/

    /************ START__ sub_Work_Type Section***************/
    public void subWorkTypeValChanged() {
        if (isValidString(getSubWorkTypeCode())) {
            if (checkValidVal(IJobConstants.TREE_LEVEL_4)) {
                this.selectedSubWorkType = this.getSubWorkTypeCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_SUB_WORK_TYPE);
                showError(msg);
                this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }


        } else {
            this.selectedSubWorkType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
        resetValues(TREE_LEVEL_4);
        loadGroupTypeList();
    }

    public void selectedSubWorkTypeChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedSubWorkType.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.subWorkTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        } else {
            this.subWorkTypeCode = this.selectedSubWorkType;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
        resetValues(TREE_LEVEL_4);
        loadGroupTypeList();
    }

    public List<SelectItem> getSubWorkTypes() throws SharedApplicationException {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<IBasicDTO> fildDTOList = new ArrayList<IBasicDTO>(0);
        ICatsClient catsClient = JobClientFactory.getCatsClient();
        if (selectItems.isEmpty() && isValidString(getSelectedLevel())) {
            try {
                fildDTOList = catsClient.getAllByParentCode(Long.valueOf(getSelectedLevel()));
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (IBasicDTO dto : fildDTOList) {
                selectItems.add(new SelectItem(String.valueOf(dto.getCode().getKey()), dto.getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(IJobConstants.TREE_LEVEL_4));
            }
        }
        return selectItems;
    }

    /************ END__ sub_Work_Type Section***************/

    /************ START__ Work_Type_Desc Section***************/
    public void workTypeDescValChanged() {
        if (isValidString(getWorkTypeDescCode())) {
            if (checkValidVal(IJobConstants.TREE_LEVEL_5)) {
                this.selectedWorkTypeDesc = this.getWorkTypeDescCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_WORK_TYPE_DESC);
                showError(msg);

                this.workTypeDescCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }
        } else {
            this.selectedWorkTypeDesc = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }

        loadGroupTypeList();
    }

    public void selectedWorkTypeDescChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedWorkTypeDesc.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.workTypeDescCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        } else {
            this.workTypeDescCode = this.selectedWorkTypeDesc;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }

        loadGroupTypeList();
    }

    public List<SelectItem> getWorkTypeDescFields() throws SharedApplicationException {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        List<IBasicDTO> fildDTOList = new ArrayList<IBasicDTO>(0);
        ICatsClient catsClient = JobClientFactory.getCatsClient();
        if (selectItems.isEmpty() && isValidString(getSelectedSubWorkType())) {
            try {
                fildDTOList = catsClient.getAllByParentCode(Long.valueOf(getSelectedSubWorkType()));
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (IBasicDTO dto : fildDTOList) {
                selectItems.add(new SelectItem(String.valueOf(dto.getCode().getKey()), dto.getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(IJobConstants.TREE_LEVEL_5));
            }
        }
        return selectItems;
    }

    /************ END__ Work_Type_Desc Section***************/

    /************ START__  groupType Section***************/
    public void groupTypeValChanged() {
        if (isValidString(getGroupTypeCode())) {
            if (checkValidVal(TREE_LEVEL_6)) {
                this.selectedGroupType = this.getGroupTypeCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_GROUP_TYPE);
                showError(msg);
                this.selectedGroupType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.groupTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }
        } else {
            this.selectedGroupType = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
        resetValues(TREE_LEVEL_5);
        loadAllLevelsFields();
    }

    public void selectedGroupTypeCodeChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedGroupType.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.groupTypeCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        } else {
            this.groupTypeCode = this.selectedGroupType;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
        resetValues(TREE_LEVEL_5);
        loadAllLevelsFields();
    }

    private void loadGroupTypeList() {
        List<IJobCatTypesDTO> jobCatDtosList = null;
        IBasicDTO selectedCatDTO = getSelectedCatDto();
        if (selectedCatDTO != null) {
            jobCatDtosList = getTypesListRelatedToCat((ICatsDTO)selectedCatDTO, false);

            if (!(jobCatDtosList == null || jobCatDtosList.isEmpty())) {
                fillSelectTypesItems(jobCatDtosList);
            }
        } else {
            jobCatDtosList = new ArrayList<IJobCatTypesDTO>();
            groupTypeList = new ArrayList<SelectItem>();
            setSelectedGroupType(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setGroupTypeCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        }
    }

    /**
     *This Method fill comboBox with retrived Types
     * @createdBy Kareem Sayed
     * @since 27/05/2014
     */
    private void fillSelectTypesItems(List<IJobCatTypesDTO> jobCatDtosList) {
        //this.getAvailablejobMap().clear();
        groupTypeList = new ArrayList<SelectItem>();
        for (IJobCatTypesDTO dto : jobCatDtosList) {
            groupTypeList.add(new SelectItem((dto.getTypeCode().getCode().getKey()), dto.getTypeCode().getName()));
            this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(TREE_LEVEL_6));
        }
    }

    /**
     *This Method return Types that related with selected category
     * @param catDto selected category
     * @param isGetFromParent equll true if child equll null ,will get list that related by category parent
     * @return Types List
     * @createdBy Kareem Sayed
     * @since 27/05/2014
     */
    private List<IJobCatTypesDTO> getTypesListRelatedToCat(ICatsDTO catDto, boolean isGetFromParent) {
        List<IJobCatTypesDTO> jobCatDtosList = null;
        IJobCatTypesClient catTypesClient = JobClientFactory.getJobCatTypeClient();
        if (catDto != null) {
            try {
                jobCatDtosList = (List)catTypesClient.getTypeByCategory(catDto);

            } catch (DataBaseException e) {
                jobCatDtosList = new ArrayList<IJobCatTypesDTO>();
                setSelectedGroupType(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setGroupTypeCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                jobCatDtosList = new ArrayList<IJobCatTypesDTO>();
                setSelectedGroupType(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setGroupTypeCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            }
        }

        if ((jobCatDtosList == null || jobCatDtosList.isEmpty()) && isGetFromParent &&
            (catDto.getParentObject() != null)) {
            jobCatDtosList = getTypesListRelatedToCat((ICatsDTO)catDto.getParentObject(), true);
        }

        return jobCatDtosList;
    }

    /************ END__ groupType Section***************/

    /************ START__ Job_Level Section***************/
    public void jobLevelsChanged() {
        if (isValidString(getJobLevelCode())) {
            if (checkValidVal(TREE_LEVEL_7)) {
                this.selectedJobLevel = this.getJobLevelCode();
                setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setShowMsg(IJobConstants.DISPAY_NONE);
            } else {
                String msg = getSharedUtil().messageLocator(BUNDLE_NAME, BUNDLE_KEY_JOB_LEVEL);
                showError(msg);
                this.jobLevelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
                this.selectedJobLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
            }
        } else {
            this.selectedJobLevel = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;
        }
    }

    public void jobLevelCodeChanged(ActionEvent e) throws DataBaseException {
        setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        if (this.selectedJobLevel.equals(IJobConstants.INITIAL_FIELD_VALUE_EMPTY)) {
            this.jobLevelCode = IJobConstants.INITIAL_FIELD_VALUE_EMPTY;

        } else {
            this.jobLevelCode = this.selectedJobLevel;
            setJobFieldValError(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setShowMsg(IJobConstants.DISPAY_NONE);
        }
    }

    private void loadAllLevelsFields() {
        jobLevelList.clear();
        setJobLevelCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        setSelectedJobLevel(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        List<IBasicDTO> jobCatLevelsDTOList = new ArrayList<IBasicDTO>();
        if (isValidString(getGroupTypeCode())) {
            try {
                jobCatLevelsDTOList =
                        JobClientFactory.getJobCatLevelsClient().getAllLevelsByCatsAndType(getSelectedCatId(),
                                                                                           Long.valueOf(getGroupTypeCode()));

            } catch (SharedApplicationException e) {
                jobCatLevelsDTOList = new ArrayList<IBasicDTO>();
                setSelectedJobLevel(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setJobLevelCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                e.printStackTrace();
            } catch (DataBaseException e) {
                jobCatLevelsDTOList = new ArrayList<IBasicDTO>();
                setSelectedJobLevel(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                setJobLevelCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
                e.printStackTrace();
            }
        } else {
            jobCatLevelsDTOList = new ArrayList<IBasicDTO>();
            setSelectedJobLevel(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
            setJobLevelCode(IJobConstants.INITIAL_FIELD_VALUE_EMPTY);
        }
        if (!(jobCatLevelsDTOList == null || jobCatLevelsDTOList.isEmpty())) {
            for (IBasicDTO basicDTO : jobCatLevelsDTOList) {
                IJobCatLevelsDTO dto = (IJobCatLevelsDTO)basicDTO;
                jobLevelList.add(new SelectItem((dto.getLevelsDTO().getCode().getKey()),
                                                dto.getLevelsDTO().getName()));
                this.getAvailablejobMap().put(dto.getCode().getKey(), String.valueOf(TREE_LEVEL_7));
            }
        } else {

            jobLevelList.clear();
        }
    }

    /************ END__ Job_Level Section***************/
    public List getTotalList() {
        List totalList = null;
        if (!isDisableSearchBtn()) {
            try {
                totalList = getSearchResults();
            } catch (SharedApplicationException ne) {
                ne.printStackTrace();
                totalList = new ArrayList();
                ne.printStackTrace();
            } catch (DataBaseException db) {
                db.printStackTrace();
                getSharedUtil().handleException(db);
            } catch (Exception e) {
                e.printStackTrace();
                getSharedUtil().handleException(e);
            }
        }
        return totalList;
    }

    /**
     * Gets the search results.
     *
     * @return the search results
     * @throws DataBaseException the data base exception
     * @throws SharedApplicationException the shared application exception
     */
    protected List getSearchResults() throws DataBaseException, SharedApplicationException {
        IJobSearchCriteriaDTO jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
        List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
        if (getSelectedCatId() != null) {
            jobSearchCriteriaDTO.setCategoryCode(getSelectedCatId());
        }
        if (getGroupTypeCode() != null && !getGroupTypeCode().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setTypesCode(Long.valueOf(getGroupTypeCode()));
        }
        if (getJobLevelCode() != null && !getJobLevelCode().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setLevelsCode(Long.valueOf(getJobLevelCode()));
        }
        if (getJobKey() != null && !getJobKey().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setJobKey(getJobKey());
        }
        if (getJobName() != null && !getJobName().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setJobName(getJobName());
        }
        if (getExcludedJobList() != null) {
            jobSearchCriteriaDTO.setExcludedJobCodeList(getExcludedJobList());
        }
        jobSearchCriteriaDTO.setJobFreez(Long.valueOf(0L));
        jobSearchCriteriaDTO.setLoadSecAccessibleJobsOnly(this.loadSecAccessibleJobsOnly);
        jobSearchCriteriaDTO.setLoadWithAllSubCategories(true);
        resultList = JobClientFactory.getJobsClient().searchJobs(jobSearchCriteriaDTO);


        return resultList;
    }

    public void cancelCutomSearch() throws DataBaseException {
        if (isDisableJobGroup()) {
            resetValues(TREE_LEVEL_2);
            loadGroupTypeList();
        } else {
            resetValues(TREE_LEVEL_7);
        }
        if (isUsingPaging()) {
            getPagingBean().updateMyPadgedDataModel(new PagingResponseDTO(new ArrayList(), 0));
        }
        super.cancelSearch();
        setDisableSearchBtn(true);
        setButtonValue(IJobConstants.VIEW_DATA);
        setMyTableData(new ArrayList<IBasicDTO>());
        reIntializeMsgs();
        setSelectedRadio("");
        getSelectedDTOS().clear();
        resetPageIndex();
    }

    public void cancelSearch() throws DataBaseException {
        if (getCancelSearchButtonMethod() == null || getCancelSearchButtonMethod().isEmpty()) {
            cancelCutomSearch();
        } else {
            JSFHelper.callMethod(getCancelSearchButtonMethod());
        }
        selectedLeaderLevel = null ;
        
    }

    public void filterationData() {
        jobSearchCriteriaDTO = JobDTOFactory.createJobSearchCriteriaDTO();
        if (getSelectedCatId() != null) {
            jobSearchCriteriaDTO.setCategoryCode(getSelectedCatId());
        }
        if (getGroupTypeCode() != null && !getGroupTypeCode().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setTypesCode(Long.valueOf(getGroupTypeCode()));
        }
        if (getJobLevelCode() != null && !getJobLevelCode().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setLevelsCode(Long.valueOf(getJobLevelCode()));
        }
        if (getJobKey() != null && !getJobKey().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setJobKey(getJobKey());
        }
        if (getJobName() != null && !getJobName().equalsIgnoreCase("")) {
            jobSearchCriteriaDTO.setJobName(getJobName());
        }
        if (getExcludedJobList() != null) {
            jobSearchCriteriaDTO.setExcludedJobCodeList(getExcludedJobList());
        }
        if (getNotInCatCode() != null) {
            jobSearchCriteriaDTO.setNotInCatCode(getNotInCatCode());
        }
        jobSearchCriteriaDTO.setJobFreez(Long.valueOf(0L));
        jobSearchCriteriaDTO.setLoadSecAccessibleJobsOnly(this.loadSecAccessibleJobsOnly);
        jobSearchCriteriaDTO.setLoadWithAllSubCategories(true);
        if (startWithLeaderLevelCode != null) {
            jobSearchCriteriaDTO.setStartWithLeaderLevelCode(startWithLeaderLevelCode);
        }
        if (selectedLeaderLevel != null) {
            jobSearchCriteriaDTO.setSelectedLeaderLevel(Long.valueOf(selectedLeaderLevel));
        }

    }

    public void search() throws DataBaseException, NoResultException {

        System.out.println("Calling search()...");
        filterationData();
        this.setSearchMode(true);
        try {
            if (isUsingPaging()) {
                setDisableSearchBtn(false);
                setButtonValue(IJobConstants.RESET_VAL);
                setUpdateMyPagedDataModel(true);

                setPagingRequestDTO(new PagingRequestDTO("filterSearchJobsWithPaging"));

                setOldPageIndex(0);
                setCurrentPageIndex(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            super.setMyTableData(new ArrayList());
            super.setSearchMode(false);
        }
        //        else {
        //            setDisableSearchBtn(false);
        //            setButtonValue(IJobConstants.RESET_VAL);
        //            this.setMyTableData(getTotalList());
        //
        //        }
        if (getSelectedDTOS() != null) {
            getSelectedDTOS().clear();
        }

        if (getHighlightedDTOS() != null) {
            getHighlightedDTOS().clear();
        }
        this.repositionPage(0);
        setSelectedRadio("");

    }

    public com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO filterSearchJobsWithPaging(PagingRequestDTO pagingRequest) {

        IPagingResponseDTO bsnResponseDTO = getSearchJobsWithPaging(pagingRequest);
        com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO pagingResponseDTO = null;

        if (bsnResponseDTO.getBasicDTOList() == null) {
            pagingResponseDTO = new com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO(new ArrayList());
        } else {
            pagingResponseDTO =
                    new com.beshara.jsfbase.csc.backingbean.paging.PagingResponseDTO(bsnResponseDTO.getBasicDTOList());
            if (getCurrentPageIndex() == 1) {
                pagingResponseDTO.setTotalListSize(bsnResponseDTO.getCount().intValue());
                //                pagingRequest.setParams(new Object[] { bsnResponseDTO.getCount() });
                getPagingRequestDTO().setParams(new Object[] { bsnResponseDTO.getCount() });
            } else {
                pagingResponseDTO.setTotalListSize(((Long)getPagingRequestDTO().getParams()[0]).intValue());
            }
        }
        setUpdateMyPagedDataModel(true);
        return pagingResponseDTO;
    }

    private com.beshara.base.paging.impl.PagingResponseDTO getSearchJobsWithPaging(PagingRequestDTO pagingRequestDTO) {

        int pageIndex = getCurrentPageIndex();

        com.beshara.base.paging.impl.PagingRequestDTO bsnPagingRequestDTO =
            new com.beshara.base.paging.impl.PagingRequestDTO();

        bsnPagingRequestDTO.setPageNum(new Long(pageIndex));

        bsnPagingRequestDTO.setMaxNoOfRecords(new Long(getSharedUtil().getNoOfTableRows()));

        if (pageIndex == 1) {
            bsnPagingRequestDTO.setCountRequired(true);
        }
        if (isSorting()) {
            bsnPagingRequestDTO.setSortAscending(pagingRequestDTO.isSortAscending());
            List<String> sortingColumnList = new ArrayList<String>();
            //add ur sorting columns
            bsnPagingRequestDTO.setSortColumnList(sortingColumnList);
        }
        try {
            jobSearchCriteriaDTO.setPagingRequestDTO(bsnPagingRequestDTO);
            jobSearchCriteriaDTO.setMinCode(minCode);
            jobSearchCriteriaDTO.setNotShowLeaderJobs(notShowLeaderJobs);
             jobSearchCriteriaDTO.setSuperVisorJobs(superVisorJobs);
            bsnPagingResponseDTO =
                    (com.beshara.base.paging.impl.PagingResponseDTO)(JobClientFactory.getJobsClient()).searchJobsWithPagingForDivSearch(jobSearchCriteriaDTO);

        } catch (NoResultException ne) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            ne.printStackTrace();
        } catch (ServiceNotAvailableException e) {
            getSharedUtil().handleException(e, "com.beshara.jsfbase.csc.msgresources.globalexceptions",
                                            "ServiceNotAvailableException");
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (DataBaseException e) {
            getSharedUtil().handleException(e);
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        } catch (Throwable e) {
            bsnPagingResponseDTO = new com.beshara.base.paging.impl.PagingResponseDTO();
            e.printStackTrace();
        }

        return bsnPagingResponseDTO;
    }
    //    public void fillDataTable() throws DataBaseException {
    //        setSearchMode(true);
    //        setDisableSearchBtn(false);
    //        setButtonValue(IJobConstants.RESET_VAL);
    //        this.setMyTableData(getTotalList());
    //        //setResetMode(!resetMode);
    //    }

    public void openSearchJobsDiv(ActionEvent ae) {
        Boolean manyToMany = (Boolean)evaluateValueBinding("appMainLayout.manyToMany");
        String beanNameBindingKey = "pageBeanName";
        if (manyToMany != null && manyToMany) {
            beanNameBindingKey = "detailBeanName";
        }

        BaseBean currentBaseBean = (BaseBean)evaluateValueBinding(beanNameBindingKey);
        currentBaseBean.setJavaScriptCaller("changeVisibilityDiv(window.blocker,window.customDiv1);");

        if (isUsingPaging() && ae != null && ae instanceof ScrollerActionEvent) {
            super.changePageIndex(ae);
        }

    }

    public void back() throws DataBaseException {
        cancelSearch();
    }

    public void selectedCheckboxs(ActionEvent event) throws Exception {

        System.out.println("selectedCheckboxs only one time");

        try {

            Set<IBasicDTO> selectedSet = new OrderedSet();
            selectedSet.addAll(getSelectedDTOS());

            IClientDTO selected = (IClientDTO)this.getMyDataTable().getRowData();

            if (selected.getChecked()) {

                try {
                    selectedSet.add(selected);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                selected.setChecked(Boolean.TRUE);

                for (IBasicDTO item : selectedSet) {
                    if ((item.getCode().getKey()).equals(selected.getCode().getKey())) {
                        selectedSet.remove(item);
                        break;
                    }
                }

                selected.setChecked(Boolean.FALSE);

            }

            getSelectedDTOS().clear();
            getSelectedDTOS().addAll(selectedSet);
            System.out.print(getSelectedDTOS().size());
        } catch (Exception e) {

            ErrorDisplay errorDisplay =
                (ErrorDisplay)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{error_dissplay}").getValue(FacesContext.getCurrentInstance());
            errorDisplay.setErrorMsgKey(e.getMessage());
            errorDisplay.setSystemException(true);
            throw new Exception();

        }

    }

    /***********************************Getter and Setter******************************/
    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobGroupCode(String jobGroupCode) {
        this.jobGroupCode = jobGroupCode;
    }

    public String getJobGroupCode() {
        return jobGroupCode;
    }

    public void setSelectedJobGroup(String selectedJobGroup) {
        this.selectedJobGroup = selectedJobGroup;
    }

    public String getSelectedJobGroup() {
        return selectedJobGroup;
    }

    public void setJobFieldValError(String jobFieldValError) {
        this.jobFieldValError = jobFieldValError;
    }

    public String getJobFieldValError() {
        return jobFieldValError;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowJobFieldValError(boolean showJobFieldValError) {
        this.showJobFieldValError = showJobFieldValError;
    }

    public boolean isShowJobFieldValError() {
        return showJobFieldValError;
    }

    public static void setAvailablejobMap(HashMap<String, String> availablejobMap) {
        JobFilter.availablejobMap = availablejobMap;
    }

    public static HashMap<String, String> getAvailablejobMap() {
        return availablejobMap;
    }

    public void setErrorMsgBind(HtmlOutputText errorMsgBind) {
        this.errorMsgBind = errorMsgBind;
    }

    public HtmlOutputText getErrorMsgBind() {
        return errorMsgBind;
    }

    public void setCodeMaxLength(int codeMaxLength) {
        this.codeMaxLength = codeMaxLength;
    }

    public int getCodeMaxLength() {
        return codeMaxLength;
    }

    public void setLevelsGroupCode(String levelsGroupCode) {
        this.levelsGroupCode = levelsGroupCode;
    }

    public String getLevelsGroupCode() {
        return levelsGroupCode;
    }

    public void setSelectedlevelsGroup(String selectedlevelsGroup) {
        this.selectedlevelsGroup = selectedlevelsGroup;
    }

    public String getSelectedlevelsGroup() {
        return selectedlevelsGroup;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setSelectedLevel(String selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public String getSelectedLevel() {
        return selectedLevel;
    }

    public void setSubWorkTypeCode(String subWorkTypeCode) {
        this.subWorkTypeCode = subWorkTypeCode;
    }

    public String getSubWorkTypeCode() {
        return subWorkTypeCode;
    }

    public void setSelectedSubWorkType(String selectedSubWorkType) {
        this.selectedSubWorkType = selectedSubWorkType;
    }

    public String getSelectedSubWorkType() {
        return selectedSubWorkType;
    }

    public void setWorkTypeDescCode(String workTypeDescCode) {
        this.workTypeDescCode = workTypeDescCode;
    }

    public String getWorkTypeDescCode() {
        return workTypeDescCode;
    }

    public void setSelectedWorkTypeDesc(String selectedWorkTypeDesc) {
        this.selectedWorkTypeDesc = selectedWorkTypeDesc;
    }

    public String getSelectedWorkTypeDesc() {
        return selectedWorkTypeDesc;
    }

    public void setGroupTypeCode(String groupTypeCode) {
        this.groupTypeCode = groupTypeCode;
    }

    public String getGroupTypeCode() {
        return groupTypeCode;
    }

    public void setSelectedGroupType(String selectedGroupType) {
        this.selectedGroupType = selectedGroupType;
    }

    public String getSelectedGroupType() {
        return selectedGroupType;
    }

    public void setGroupTypeList(List<SelectItem> groupTypeList) {
        this.groupTypeList = groupTypeList;
    }

    public List<SelectItem> getGroupTypeList() {
        return groupTypeList;
    }

    public void setJobLevelCode(String jobLevelCode) {
        this.jobLevelCode = jobLevelCode;
    }

    public String getJobLevelCode() {
        return jobLevelCode;
    }

    public void setSelectedJobLevel(String selectedJobLevel) {
        this.selectedJobLevel = selectedJobLevel;
    }

    public String getSelectedJobLevel() {
        return selectedJobLevel;
    }

    public void setJobLevelList(List<SelectItem> jobLevelList) {
        this.jobLevelList = jobLevelList;
    }

    public List<SelectItem> getJobLevelList() {
        return jobLevelList;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setResetMode(Boolean resetMode) {
        this.resetMode = resetMode;
    }

    public Boolean getResetMode() {
        return resetMode;
    }

    public void setDisableSearchBtn(boolean disableSearchBtn) {
        this.disableSearchBtn = disableSearchBtn;
    }

    public boolean isDisableSearchBtn() {
        if ((getSelectedJobGroup() == null || getJobGroupCode() == "") || (getJobKey() == null || getJobKey() == "") ||
            (getJobName() == null || getJobName() == "")) {
            disableSearchBtn = true;
        }
        return disableSearchBtn;
    }

    public void setOkButtonMethod(String okButtonMethod) {
        this.okButtonMethod = okButtonMethod;
    }

    public String getOkButtonMethod() {
        return okButtonMethod;
    }

    public void setExcludedJobList(List<String> excludedJobList) {
        this.excludedJobList = excludedJobList;
    }

    public List<String> getExcludedJobList() {
        return excludedJobList;
    }


    public List getDataTableSearchResult() {
        return super.getDataTableSearchResult();
    }

    public void setLoadSecAccessibleJobsOnly(boolean loadSecAccessibleJobsOnly) {
        this.loadSecAccessibleJobsOnly = loadSecAccessibleJobsOnly;
    }

    public boolean isLoadSecAccessibleJobsOnly() {
        return loadSecAccessibleJobsOnly;
    }

    public void setLevelsGroupFields(List<SelectItem> levelsGroupFields) {
        this.levelsGroupFields = levelsGroupFields;
    }

    public void setLevels(List<SelectItem> levels) {
        this.levels = levels;
    }

    public void setSubWorkTypes(List<SelectItem> subWorkTypes) {
        this.subWorkTypes = subWorkTypes;
    }

    public void setWorkTypeDescFields(List<SelectItem> workTypeDescFields) {
        this.workTypeDescFields = workTypeDescFields;
    }

    public void setDisableLevelGroup(boolean disableLevelGroup) {
        this.disableLevelGroup = disableLevelGroup;
    }

    public boolean isDisableLevelGroup() {
        return disableLevelGroup;
    }

    public void setDisableJobGroup(boolean disableJobGroup) {
        this.disableJobGroup = disableJobGroup;
    }

    public boolean isDisableJobGroup() {
        return disableJobGroup;
    }

    public void setBsnPagingResponseDTO(com.beshara.base.paging.impl.PagingResponseDTO bsnPagingResponseDTO) {
        this.bsnPagingResponseDTO = bsnPagingResponseDTO;
    }

    public com.beshara.base.paging.impl.PagingResponseDTO getBsnPagingResponseDTO() {
        return bsnPagingResponseDTO;
    }

    public void setJobSearchCriteriaDTO(IJobSearchCriteriaDTO jobSearchCriteriaDTO) {
        this.jobSearchCriteriaDTO = jobSearchCriteriaDTO;
    }

    public IJobSearchCriteriaDTO getJobSearchCriteriaDTO() {
        return jobSearchCriteriaDTO;
    }


    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setSearchButtonMethod(String searchButtonMethod) {
        this.searchButtonMethod = searchButtonMethod;
    }

    public String getSearchButtonMethod() {
        return searchButtonMethod;
    }

    public void setNotInCatCode(String notInCatCode) {
        this.notInCatCode = notInCatCode;
    }

    public String getNotInCatCode() {
        return notInCatCode;
    }

    public void setNotShowLeaderJobs(boolean notShowLeaderJobs) {
        this.notShowLeaderJobs = notShowLeaderJobs;
    }

    public boolean isNotShowLeaderJobs() {
        return notShowLeaderJobs;
    }

    public void setCancelSearchButtonMethod(String cancelSearchButtonMethod) {
        this.cancelSearchButtonMethod = cancelSearchButtonMethod;
    }

    public String getCancelSearchButtonMethod() {
        return cancelSearchButtonMethod;
    }

    public void setStartWithLeaderLevelCode(Long startWithLeaderLevelCode) {
        this.startWithLeaderLevelCode = startWithLeaderLevelCode;
    }

    public Long getStartWithLeaderLevelCode() {
        return startWithLeaderLevelCode;
    }

    public void setSelectedLeaderLevel(String selectedLeaderLevel) {
        this.selectedLeaderLevel = selectedLeaderLevel;
    }

    public String getSelectedLeaderLevel() {
        return selectedLeaderLevel;
    }

    public void setSuperVisorJobs(boolean superVisorJobs) {
        this.superVisorJobs = superVisorJobs;
    }

    public boolean isSuperVisorJobs() {
        return superVisorJobs;
    }
}
