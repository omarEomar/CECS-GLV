package com.beshara.csc.hr.emp.integration.presentation.backingbean.employeedatarevision;

import com.beshara.csc.hr.mis.business.client.IMisCandidatesClient;
import com.beshara.csc.hr.mis.business.client.MisClientFactory;
import com.beshara.csc.hr.mis.business.dto.MisDTOFactory;
import com.beshara.csc.hr.mis.business.dto.financialbenefits.IFinancialEmpPayedCandsDTO;
import com.beshara.csc.hr.mis.business.dto.financialbenefits.IFinancialMisCandidatesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.LookupMaintainBaseBean;
import java.util.ArrayList;
import java.util.List;

public class ExternalCoursesBean extends LookupMaintainBaseBean
{
  private List<IFinancialMisCandidatesDTO> externalCoursesLst = new ArrayList();
  GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

  public ExternalCoursesBean() { setContentDiv("module_tabs_cont"); }

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
    IFinancialEmpPayedCandsDTO dto = MisDTOFactory.createFinancialEmpPayedCandDTO();
    IFinancialMisCandidatesDTO financialMisCandidatesDTO = MisDTOFactory.createFinancialMisCandidatesDTO();
    financialMisCandidatesDTO.setRealCivilId(this.maintainBean.getRealCivilId().toString());
    financialMisCandidatesDTO.setCndstatusCode(null);
    financialMisCandidatesDTO.setParentMissionType(Long.valueOf(3L));
    try
    {
      dto = MisClientFactory.getMisCandidatesClient().getAllMissionsByRealCivilAndCandStatus(financialMisCandidatesDTO);

      this.externalCoursesLst = dto.getMisCandidatesList();
      getExternalCoursesLstSize();
    }
    catch (DataBaseException e) {
      e.printStackTrace();
      this.externalCoursesLst = null;
    } catch (SharedApplicationException e) {
      e.printStackTrace();
      this.externalCoursesLst = null;
    }
  }

  public int getExternalCoursesLstSize()
  {
    if (this.externalCoursesLst == null) {
      return 0;
    }
    return this.externalCoursesLst.size();
  }
  public void setExternalCoursesLst(List<IFinancialMisCandidatesDTO> externalCoursesLst) {
    this.externalCoursesLst = externalCoursesLst;
  }

  public List<IFinancialMisCandidatesDTO> getExternalCoursesLst() {
    return this.externalCoursesLst;
  }
}