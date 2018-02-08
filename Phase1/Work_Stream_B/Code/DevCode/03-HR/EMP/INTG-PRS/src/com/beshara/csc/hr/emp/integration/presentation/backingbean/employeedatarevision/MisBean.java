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

public class MisBean extends LookupMaintainBaseBean
{
  private static final long serialVersionUID = 1L;
  private List<IFinancialMisCandidatesDTO> misDataTableLst = new ArrayList();
  private List<IFinancialMisCandidatesDTO> vacDataTableLst = new ArrayList();
  GovEmpMaintainBean maintainBean = GovEmpMaintainBean.getInstance();

  public MisBean() {
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
    IFinancialEmpPayedCandsDTO dto = MisDTOFactory.createFinancialEmpPayedCandDTO();
    IFinancialMisCandidatesDTO financialMisCandidatesDTO = MisDTOFactory.createFinancialMisCandidatesDTO();
    financialMisCandidatesDTO.setRealCivilId(this.maintainBean.getRealCivilId().toString());
    financialMisCandidatesDTO.setCndstatusCode(null);
    financialMisCandidatesDTO.setParentMissionType(Long.valueOf(1L));
    try
    {
      dto = MisClientFactory.getMisCandidatesClient().getAllMissionsByRealCivilAndCandStatus(financialMisCandidatesDTO);

      this.misDataTableLst = dto.getMisCandidatesList();
      getMisLstSize();
    }
    catch (DataBaseException e) {
      e.printStackTrace();
      this.misDataTableLst = null;
    } catch (SharedApplicationException e) {
      e.printStackTrace();
      this.misDataTableLst = null;
    }

    financialMisCandidatesDTO.setParentMissionType(Long.valueOf(2L));
    IFinancialEmpPayedCandsDTO dto1 = MisDTOFactory.createFinancialEmpPayedCandDTO();
    try {
      dto1 = MisClientFactory.getMisCandidatesClient().getAllMissionsByRealCivilAndCandStatus(financialMisCandidatesDTO);

      this.vacDataTableLst = dto1.getMisCandidatesList();
      getVacLstSize();
    } catch (DataBaseException e) {
      e.printStackTrace();
      this.vacDataTableLst = null;
    } catch (SharedApplicationException e) {
      e.printStackTrace();
      this.vacDataTableLst = null;
    }
  }

  public int getMisLstSize()
  {
    if (this.misDataTableLst == null) {
      return 0;
    }
    return this.misDataTableLst.size();
  }

  public int getVacLstSize() {
    if (this.vacDataTableLst == null) {
      return 0;
    }
    return this.vacDataTableLst.size();
  }

  public void setMisDataTableLst(List<IFinancialMisCandidatesDTO> misDataTableLst) {
    this.misDataTableLst = misDataTableLst;
  }

  public List<IFinancialMisCandidatesDTO> getMisDataTableLst() {
    return this.misDataTableLst;
  }

  public void setVacDataTableLst(List<IFinancialMisCandidatesDTO> vacDataTableLst) {
    this.vacDataTableLst = vacDataTableLst;
  }

  public List<IFinancialMisCandidatesDTO> getVacDataTableLst() {
    return this.vacDataTableLst;
  }
}