package com.beshara.jsfbase.csc.backingbean;

import java.io.Serializable;


public class AppMainLayoutPortal implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private boolean showTreeDivDetails;
    private boolean showLookupAdd;
    private boolean showLookupEdit;
    private boolean showDelAlert;
    private boolean showDelAlertTree;
    private boolean showDelConfirm;
    private boolean showTreeAdd;
    private boolean showTreeEdit;
    private boolean showSearch;
    private boolean showContent1;
    private boolean showsteps;
    private boolean showbar;
    private boolean showpaging;
    private boolean showtreeContent;
    private boolean showdatatableContent;
    private boolean showNavigation;
    private boolean showMasterDetail;
    private boolean manyToMany;
    private boolean showTreediv;
    private boolean showCommonData;
    private boolean showCustomDiv1;
    private boolean showScirptGenerator;
    private boolean showLovDiv;
    private boolean showEmpSrchDiv;
    private boolean showshortCut;
    private boolean showWizardBar;
    private boolean showPagedTreediv;
    private boolean showTitlePic;


    // added to show title page or not 
    private boolean showTitlePage;


    public AppMainLayoutPortal() {
        setShowTreeDivDetails(false);
        setShowLookupAdd(false);
        setShowLookupEdit(false);
        setShowDelAlert(false);
        setShowDelAlertTree(false);
        setShowDelConfirm(false);
        setShowTreeAdd(false);
        setShowTreeEdit(false);
        setShowSearch(false);
        setShowContent1(false);
        setShowsteps(false);
        setShowbar(false);
        setShowpaging(false);
        setShowtreeContent(false);
        setShowdatatableContent(false);
        setShowNavigation(false);
        setShowMasterDetail(false);
        setManyToMany(false);
        setShowTreediv(false);
        setShowCommonData(false);
        setShowCustomDiv1(false);
        setShowScirptGenerator(false);
        setShowLovDiv(false);
        setShowTitlePage(false);
        setShowTitlePic(false);
        //    lookupPage();
    }

    public void showMasterDetailPage() {
        setShowSearch(true);
        setShowbar(true);
        setShowpaging(true);
        setShowMasterDetail(true);
        setShowCommonData(true);
        setShowshortCut(true);
    }

    private void defaultAttrbuite() {
        setShowbar(true);
        setShowDelConfirm(false);
        setShowDelAlert(false);
        setShowSearch(true);
        setShowshortCut(true);
    }

    public void showAddeditPage() {
        setShowContent1(true);
        setShowCommonData(true);
        setShowshortCut(true);
    }

    public void showTreePage() {
        setShowSearch(true);
        setShowbar(true);
        setShowtreeContent(true);
        setShowDelAlertTree(true);
        setShowTreeAdd(true);
        setShowTreeEdit(true);
        setShowTreeDivDetails(true);
        setShowDelConfirm(true);
        setShowCommonData(true);
        setShowshortCut(true);
    }

    private void showLookupListPopup() {
        setShowdatatableContent(true);
        setShowSearch(true);
        setShowbar(true);
        setShowpaging(true);
        setShowshortCut(true);
    }

    public void enableMany2ManyDetialMaintain() {
        showAddeditPage();
        setShowdatatableContent(true);
        setShowbar(true);
        setShowLookupAdd(true);
        setShowDelAlert(true);
        setShowDelConfirm(true);
        setShowshortCut(true);
    }

    public void showLookupListPage() {
        //        setShowSearch(true);
        //        setShowbar(true);
        //        setShowpaging(true);
        //        setShowLookupAdd(true);
        //        setShowLookupEdit(true);
        //        setShowDelAlert(true);
        //        setShowDelConfirm(true);
        //        setShowdatatableContent(true);
        //        setShowCommonData(true);
        //        setShowshortCut(true);
        setShowContent1(true);
        setShowTitlePic(true);
    }

    public void showManyToManyListPage() {
        setShowbar(true);
        setShowpaging(true);
        setShowDelAlert(true);
        setShowDelConfirm(true);
        setShowdatatableContent(true);
        setShowCommonData(true);
        setShowSearch(true);
        setShowshortCut(true);
    }


    public void showManyToManyMaintain() {
        setShowCommonData(true);
        setShowNavigation(true);
        setShowLookupAdd(true);
        setShowDelAlert(true);
        setShowDelConfirm(true);
        setShowsteps(true);
        setShowSearch(true);
        setShowbar(true);
        setShowdatatableContent(true);
        setShowpaging(true);
        setShowSearch(true);
        setManyToMany(true);
        setShowshortCut(true);
    }

    //    public void lookupPage() {
    //        setShowbar(true);
    //        setShowdatatableContent(true);
    //        setShowLookupAdd(true);
    //        setShowLookupEdit(true);
    //        setShowpaging(true);
    //        
    //    }


    public void setShowTreeDivDetails(boolean showTreeDivDetails) {
        this.showTreeDivDetails = showTreeDivDetails;
    }

    public boolean isShowTreeDivDetails() {
        return showTreeDivDetails;
    }

    public void setShowLookupAdd(boolean showLookupAdd) {
        this.showLookupAdd = showLookupAdd;
    }

    public boolean isShowLookupAdd() {
        return showLookupAdd;
    }

    public void setShowLookupEdit(boolean showLookupEdit) {
        this.showLookupEdit = showLookupEdit;
    }

    public boolean isShowLookupEdit() {
        return showLookupEdit;
    }

    public void setShowDelAlert(boolean showDelAlert) {
        this.showDelAlert = showDelAlert;
    }

    public boolean isShowDelAlert() {
        return showDelAlert;
    }

    public void setShowDelAlertTree(boolean showDelAlertTree) {
        this.showDelAlertTree = showDelAlertTree;
    }

    public boolean isShowDelAlertTree() {
        return showDelAlertTree;
    }

    public void setShowDelConfirm(boolean showDelConfirm) {
        this.showDelConfirm = showDelConfirm;
    }

    public boolean isShowDelConfirm() {
        return showDelConfirm;
    }

    public void setShowTreeAdd(boolean showTreeAdd) {
        this.showTreeAdd = showTreeAdd;
    }

    public boolean isShowTreeAdd() {
        return showTreeAdd;
    }

    public void setShowTreeEdit(boolean showTreeEdit) {
        this.showTreeEdit = showTreeEdit;
    }

    public boolean isShowTreeEdit() {
        return showTreeEdit;
    }

    public void setShowSearch(boolean showSearch) {
        this.showSearch = showSearch;
    }

    public boolean isShowSearch() {
        return showSearch;
    }

    public void setShowContent1(boolean showContent1) {
        this.showContent1 = showContent1;
    }

    public boolean isShowContent1() {
        return showContent1;
    }

    public void setShowsteps(boolean showsteps) {
        this.showsteps = showsteps;
    }

    public boolean isShowsteps() {
        return showsteps;
    }

    public void setShowbar(boolean showbar) {
        this.showbar = showbar;
    }

    public boolean isShowbar() {
        return showbar;
    }

    public void setShowpaging(boolean showpaging) {
        this.showpaging = showpaging;
    }

    public boolean isShowpaging() {
        return showpaging;
    }

    public void setShowtreeContent(boolean showtreeContent) {
        this.showtreeContent = showtreeContent;
    }

    public boolean isShowtreeContent() {
        return showtreeContent;
    }

    public void setShowdatatableContent(boolean showdatatableContent) {
        this.showdatatableContent = showdatatableContent;
    }

    public boolean isShowdatatableContent() {
        return showdatatableContent;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowMasterDetail(boolean showMasterDetail) {
        this.showMasterDetail = showMasterDetail;
    }

    public boolean isShowMasterDetail() {
        return showMasterDetail;
    }

    public void setManyToMany(boolean manyToMany) {
        this.manyToMany = manyToMany;
    }

    public boolean isManyToMany() {
        return manyToMany;
    }

    public void setShowTreediv(boolean showTreediv) {
        this.showTreediv = showTreediv;
    }

    public boolean isShowTreediv() {
        return showTreediv;
    }

    public void setShowCommonData(boolean showCommonData) {
        this.showCommonData = showCommonData;
    }

    public boolean isShowCommonData() {
        return showCommonData;
    }


    public void setShowCustomDiv1(boolean showCustomDiv1) {
        this.showCustomDiv1 = showCustomDiv1;
    }

    public boolean isShowCustomDiv1() {
        return showCustomDiv1;
    }

    public void setShowScirptGenerator(boolean showScirptGenerator) {
        this.showScirptGenerator = showScirptGenerator;
    }

    public boolean isShowScirptGenerator() {
        return showScirptGenerator;
    }

    public void setShowLovDiv(boolean showLovDiv) {
        this.showLovDiv = showLovDiv;
    }

    public boolean isShowLovDiv() {
        return showLovDiv;
    }


    public void setShowEmpSrchDiv(boolean showEmpSrchDiv) {
        this.showEmpSrchDiv = showEmpSrchDiv;
    }

    public boolean isShowEmpSrchDiv() {
        return showEmpSrchDiv;
    }

    public void setShowTitlePage(boolean showTitlePage) {
        this.showTitlePage = showTitlePage;
    }

    public boolean isShowTitlePage() {
        return showTitlePage;
    }


    public void setShowshortCut(boolean showshortCut) {
        this.showshortCut = showshortCut;
    }

    public boolean isShowshortCut() {
        return showshortCut;
    }

    public void setShowWizardBar(boolean showWizardBar) {
        this.showWizardBar = showWizardBar;
    }

    public boolean isShowWizardBar() {
        return showWizardBar;
    }

    public void setShowPagedTreediv(boolean showPagedTreediv) {
        this.showPagedTreediv = showPagedTreediv;
    }

    public boolean isShowPagedTreediv() {
        return showPagedTreediv;
    }

    public void setShowTitlePic(boolean showTitlePic) {
        this.showTitlePic = showTitlePic;
    }

    public boolean isShowTitlePic() {
        return showTitlePic;
    }
}
