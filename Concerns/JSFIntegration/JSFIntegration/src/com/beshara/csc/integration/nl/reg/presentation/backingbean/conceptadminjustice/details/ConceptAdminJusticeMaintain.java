package com.beshara.csc.nl.reg.presentation.backingbean.conceptadminjustice.details;

import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.jsfbase.csc.backingbean.AppMainLayout;
import com.beshara.jsfbase.csc.backingbean.ManyToManyDetailsMaintain;

import java.util.ArrayList;
import java.util.List;


public class ConceptAdminJusticeMaintain extends ManyToManyDetailsMaintain {

    private List yearList = new ArrayList();
    private List courtDegreesList = new ArrayList();
    private List courtChambersList = new ArrayList();

    public ConceptAdminJusticeMaintain() {
        setClient(RegClientFactory.getRegulationsClient());
        setContent1Div("divContent1Justic");
    }

    public AppMainLayout appMainLayoutBuilder() {
        AppMainLayout app = new AppMainLayout();
        app.showManyToManyMaintain();
        app.setShowContent1(true);
        app.setShowDelConfirm(false);
        app.setShowSearch(false);
        app.setShowDelAlert(false);
        app.setShowDelConfirm(false);
        app.setShowbar(false);
        app.setShowdatatableContent(false);
        app.setShowpaging(false);
        app.setShowLookupAdd(false);
        return app;
    }


    public void setYearList(List issuanceYears) {
        this.yearList = issuanceYears;
    }

    public List getYearList() {
        if (yearList == null || yearList.size() == 0) {
            try {
                yearList = 
                        InfClientFactory.getYearsClient().getCodeNameInCenter();
            } catch (DataBaseException e) {
                yearList = new ArrayList<IYearsDTO>();
            }

        }
        return yearList;
    }


    public void setCourtDegreesList(List courtDegreesList) {
        this.courtDegreesList = courtDegreesList;
    }

    public List getCourtDegreesList() {
        if (courtDegreesList.size() == 0)
            try {
                courtDegreesList = 
                        RegClientFactory.getREGCourtDegreesClient().getCodeNameInCenter();
            } catch (Exception e) {
                e.printStackTrace();
                courtDegreesList = new ArrayList();
            }
        return courtDegreesList;
    }

    public void setCourtChambersList(List courtChambersList) {
        this.courtChambersList = courtChambersList;
    }

    public List getCourtChambersList() {
        if (courtChambersList.size() == 0)
            try {
                courtChambersList = 
                        RegClientFactory.getRegCourtChambersClient().getCodeNameInCenter();
            } catch (Exception e) {
                e.printStackTrace();
                courtDegreesList = new ArrayList();
            }
        return courtChambersList;
    }
}
