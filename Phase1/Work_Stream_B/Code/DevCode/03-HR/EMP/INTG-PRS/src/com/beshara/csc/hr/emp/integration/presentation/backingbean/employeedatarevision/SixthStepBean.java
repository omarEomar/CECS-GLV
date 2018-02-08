package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.mov.business.client.MovClientFactory;
import com.beshara.csc.hr.mov.business.dto.IMovMovingRequestsDTO;
import com.beshara.csc.hr.mov.business.dto.MovDTOFactory;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.hr.vac.business.dto.IVacEmployeeVacationsDTO;
import com.beshara.csc.hr.vac.business.dto.VacDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.constants.IMovConstants;
import com.beshara.csc.sharedutils.business.util.constants.IVacConstants;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;


public class SixthStepBean extends LookupMaintainBaseBean {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "govEmpSixthStepBean";
    private static final String NAVIGATION_KEY = "govempsixthstep";

    private IVacEmployeeVacationsDTO periodicalVac = 
        VacDTOFactory.createVacEmployeeVacationsDTO();
    private IVacEmployeeVacationsDTO sickVac = 
        VacDTOFactory.createVacEmployeeVacationsDTO();
    private IVacEmployeeVacationsDTO casualVac = 
        VacDTOFactory.createVacEmployeeVacationsDTO();
    private IVacEmployeeVacationsDTO hajVac = 
        VacDTOFactory.createVacEmployeeVacationsDTO();

    private Long periodicalVacPeriod =  new Long(0L);
    private Long sickVacPeriod =  new Long(0L);
    private Long casualVacPeriod =  new Long(0L);
    private Long hajVacPeriod =  new Long(0L);
    private Long vacPeriod;
    private Long periodicalVacBalance = new Long(0L);

    private IMovMovingRequestsDTO delegateMov = 
        MovDTOFactory.createMovMovingRequestsDTO();
    private IMovMovingRequestsDTO secondingMov = 
        MovDTOFactory.createMovMovingRequestsDTO();
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

    public SixthStepBean() {
        setContent1Div("module_tabs_cont");
        
    }

    public static SixthStepBean getInstance() {
        return (SixthStepBean)JSFHelper.getValue(BEAN_NAME);
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = super.appMainLayoutBuilder();
        app.setShowWizardBar(true);
        app.setShowContent1(true);
        return app;
    }

    public String init() { 
        
        periodicalVac = getLastVacation(IVacConstants.VAC_VACATION_TYPE_PERIODIC);
        periodicalVacPeriod = vacPeriod;
        sickVac = getLastVacation(IVacConstants.VAC_VACATION_TYPE_SICK);
        sickVacPeriod = vacPeriod;
        casualVac = getLastVacation(IVacConstants.VAC_VACATION_TYPE_CASUAL);
        casualVacPeriod = vacPeriod;
        hajVac = getLastVacation(IVacConstants.VAC_VACATION_TYPE_HAJ);
        hajVacPeriod = vacPeriod;
        
        try {
            if(maintainBean.getMinistryCode() != null){
            periodicalVacBalance =VacClientFactory.getVacEmployeeBalancesClient(maintainBean.getMinistryCode()).getPeriodicVacBalanceByCivilId(maintainBean.getRealCivilId());
            }
            else
            {
            periodicalVacBalance =VacClientFactory.getVacEmployeeBalancesClient().getPeriodicVacBalanceByCivilId(maintainBean.getRealCivilId());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        delegateMov = getLastMoving(IMovConstants.MOV_TYPE_DELEGATE);
        secondingMov = getLastMoving(IMovConstants.MOV_TYPE_SECONDING);

        return NAVIGATION_KEY;
    }
    
    public void getAll() throws DataBaseException {
    }

    private IVacEmployeeVacationsDTO getLastVacation(Long vacType) {
        vacPeriod = 0L;
        try {
            IVacEmployeeVacationsDTO vacDTO=null;
            if(maintainBean.getMinistryCode() != null){
             vacDTO = (IVacEmployeeVacationsDTO)VacClientFactory.getVacEmployeeVacationsClient(maintainBean.getMinistryCode()).getLastAprovedEmpVacationByCivilIdAndVacTypeTree(GovEmpMaintainBean.getEmpCivilId(),vacType);
            }
            else
            {
            vacDTO = (IVacEmployeeVacationsDTO)VacClientFactory.getVacEmployeeVacationsClient().getLastAprovedEmpVacationByCivilIdAndVacTypeTree(GovEmpMaintainBean.getEmpCivilId(),vacType);
            }
            if(vacDTO != null){
                Long fromDateTime = vacDTO.getFromDate().getTime();
                Long toDateTime = vacDTO.getUntilDate().getTime();
                long oneDayTime = 1000 * 60 * 60 * 24 ;
                vacPeriod = new Long ((Math.abs(toDateTime - fromDateTime) / oneDayTime) + 1);   // consider the start day also
            }
            return vacDTO;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Throwable e) {
             e.printStackTrace();
        }
        return VacDTOFactory.createVacEmployeeVacationsDTO();
    }

    private IMovMovingRequestsDTO getLastMoving(Long movType)  {
        IMovMovingRequestsDTO lastMovRequest = null;
        IMovMovingRequestsDTO lastLocalMovRequest = null;
        IMovMovingRequestsDTO lastCenterMovRequest = null;
        try {
            if(maintainBean.getMinistryCode() != null){
            lastLocalMovRequest = (IMovMovingRequestsDTO)MovClientFactory.getMovMovingRequestsClientForMinistry(maintainBean.getMinistryCode()).getLastExecutedMovByCivilIdAndMovTypeTree(GovEmpMaintainBean.getEmpCivilId(),movType);
            }
            else
            {
                lastLocalMovRequest = (IMovMovingRequestsDTO)MovClientFactory.getMovMovingRequestsClient().getLastExecutedMovByCivilIdAndMovTypeTree(GovEmpMaintainBean.getEmpCivilId(),movType);
            }
        } catch (Throwable e) {
             e.printStackTrace();
        }
        try {
            lastCenterMovRequest = (IMovMovingRequestsDTO)MovClientFactory.getMovMovingRequestsClientForCenter().getLastExecutedMovByCivilIdAndMovTypeTree(GovEmpMaintainBean.getEmpCivilId(),movType);
        } catch (Throwable e) {
             e.printStackTrace();
        }
        
        if(lastLocalMovRequest != null && lastCenterMovRequest == null){
            lastMovRequest = lastLocalMovRequest;
        } else if (lastLocalMovRequest == null && lastCenterMovRequest != null){
            lastMovRequest = lastCenterMovRequest;
        } else if (lastLocalMovRequest != null && lastCenterMovRequest != null){
            lastMovRequest = lastLocalMovRequest;
            if(lastCenterMovRequest.getMovingDate().after(lastLocalMovRequest.getMovingDate())){
                lastMovRequest = lastCenterMovRequest;
            }
        } else {
            lastMovRequest = MovDTOFactory.createMovMovingRequestsDTO();
        }
        
        return lastMovRequest;
    }

    public void setPeriodicalVac(IVacEmployeeVacationsDTO periodicalVac) {
        this.periodicalVac = periodicalVac;
    }

    public IVacEmployeeVacationsDTO getPeriodicalVac() {
        return periodicalVac;
    }

    public void setSickVac(IVacEmployeeVacationsDTO sickVac) {
        this.sickVac = sickVac;
    }

    public IVacEmployeeVacationsDTO getSickVac() {
        return sickVac;
    }

    public void setCasualVac(IVacEmployeeVacationsDTO casualVac) {
        this.casualVac = casualVac;
    }

    public IVacEmployeeVacationsDTO getCasualVac() {
        return casualVac;
    }

    public void setPeriodicalVacBalance(Long periodicalVacBalance) {
        this.periodicalVacBalance = periodicalVacBalance;
    }

    public Long getPeriodicalVacBalance() {
        return periodicalVacBalance;
    }

    public void setDelegateMov(IMovMovingRequestsDTO delegateMov) {
        this.delegateMov = delegateMov;
    }

    public IMovMovingRequestsDTO getDelegateMov() {
        return delegateMov;
    }

    public void setSecondingMov(IMovMovingRequestsDTO secondingMov) {
        this.secondingMov = secondingMov;
    }

    public IMovMovingRequestsDTO getSecondingMov() {
        return secondingMov;
    }

    public void setPeriodicalVacPeriod(Long periodicalVacPeriod) {
        this.periodicalVacPeriod = periodicalVacPeriod;
    }

    public Long getPeriodicalVacPeriod() {
        return periodicalVacPeriod;
    }

    public void setSickVacPeriod(Long sickVacPeriod) {
        this.sickVacPeriod = sickVacPeriod;
    }

    public Long getSickVacPeriod() {
        return sickVacPeriod;
    }

    public void setCasualVacPeriod(Long casualVacPeriod) {
        this.casualVacPeriod = casualVacPeriod;
    }

    public Long getCasualVacPeriod() {
        return casualVacPeriod;
    }

    public void setHajVac(IVacEmployeeVacationsDTO hajVac) {
        this.hajVac = hajVac;
    }

    public IVacEmployeeVacationsDTO getHajVac() {
        return hajVac;
    }

    public void setHajVacPeriod(Long hajVacPeriod) {
        this.hajVacPeriod = hajVacPeriod;
    }

    public Long getHajVacPeriod() {
        return hajVacPeriod;
    }
    
    public boolean isEmpHasHajVac(){
        if(hajVac != null && hajVac.getCode() != null){
            return true;
        }
        return false;
    }
}
