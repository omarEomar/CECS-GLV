package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.csc.hr.mis.business.client.MisClientFactory;
import com.beshara.csc.hr.mis.business.dto.MisDTOFactory;
import com.beshara.csc.hr.mis.business.dto.financialbenefits.IFinancialEmpPayedCandsDTO;
import com.beshara.csc.hr.mis.business.dto.financialbenefits.IFinancialMisCandidatesDTO;
import com.beshara.csc.hr.trn.business.client.TrnClientFactory;
import com.beshara.csc.hr.trn.business.dto.IInqueryAboutCoursesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;

import java.util.ArrayList;
import java.util.List;

public class TrainingCoursesBean extends LookupMaintainBaseBean{
    private List<IInqueryAboutCoursesDTO> trainingCoursesTableLst = new ArrayList();
    GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();
    public TrainingCoursesBean() {
        setContentDiv("module_tabs_cont");
    }
    public void initiateBeanOnce() {
      getAllData();
    }

    public AppMainLayout appMainLayoutBuilder() {
      AppMainLayout app = super.appMainLayoutBuilder();
      app.setShowWizardBar(true);
      app.setShowdatatableContent(true);
      app.setShowContent1(false);
      return app;
    }
    public void getAllData() {
      
      try
      {
        trainingCoursesTableLst = TrnClientFactory.getTrnTrainingCoursesClient().getAllByRealCivil(maintainBean.getRealCivilId(),null);
        getTrainingCoursesLstSize();
      }
      catch (DataBaseException e) {
        e.printStackTrace();
        this.trainingCoursesTableLst = null;
      } catch (SharedApplicationException e) {
        e.printStackTrace();
        this.trainingCoursesTableLst = null;
      }
    }

    public int getTrainingCoursesLstSize()
    {
      if (this.trainingCoursesTableLst == null) {
        return 0;
      }
      return this.trainingCoursesTableLst.size();
    }
    public void setTrainingCoursesTableLst(List<IInqueryAboutCoursesDTO> trainingCoursesTableLst) {
        this.trainingCoursesTableLst = trainingCoursesTableLst;
    }

    public List<IInqueryAboutCoursesDTO> getTrainingCoursesTableLst() {
        return trainingCoursesTableLst;
    }
}
