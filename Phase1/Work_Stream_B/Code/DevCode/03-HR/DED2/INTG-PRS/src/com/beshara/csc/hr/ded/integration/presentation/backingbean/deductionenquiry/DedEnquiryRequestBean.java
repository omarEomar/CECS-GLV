package com.beshara.csc.hr.ded.integration.presentation.backingbean.deductionenquiry;


import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.hr.ded.integration.business.client.DedClientFactory;
import com.beshara.csc.hr.ded.integration.business.dto.CSCReqDedDTO;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;


public class DedEnquiryRequestBean extends LookUpBaseBean {

    private final static String BUNDLE =
        "com.beshara.csc.hr.ded.integration.presentation.backingbean.resources.integration";
    private final static String BEAN_NAME = "dedEnquiryRequestBean";

    private CSCReqDedDTO dto = new CSCReqDedDTO();
    private int pageMode = 1; // 1 is As_Result,2 is As_BuildOn
    private List<CSCReqDedDTO> requestList = new ArrayList<CSCReqDedDTO>();

    private String reqID = null;
    private String reqType = null;
    private boolean issuerGov = true;
    private boolean enableOnSelect = true;
    private Long secandaryGuideId = null;

    public DedEnquiryRequestBean() {
    }

    public static DedEnquiryRequestBean getInstance() {
        return (DedEnquiryRequestBean)JSFHelper.getValue(BEAN_NAME);
    }

    public void setDto(CSCReqDedDTO dto) {
        this.dto = dto;
    }

    public CSCReqDedDTO getDto() {
        return dto;
    }

    public void setPageMode(int pageMode) {
        this.pageMode = pageMode;
    }

    public int getPageMode() {
        return pageMode;
    }

    public void setRequestList(List<CSCReqDedDTO> requestList) {
        this.requestList = requestList;
    }

    public List<CSCReqDedDTO> getRequestList() {
        return requestList;
    }

    public void showResultRequest(String transactionNo) {
        reqID = null;
        setPageMode(1);
        try {
            dto = DedClientFactory.getDeductionsClient().getResultRequestByTransactionNo(transactionNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dto == null) {
            dto = new CSCReqDedDTO();
        } else {

            dto.setRequestStatusName(getStatusValue(dto));
            reqID = dto.getId().toString();
            reqType = dto.getRequestType();
            issuerGov = dto.isIssuerMinGov();
        }
        setEnableOnSelect(true);
        //  setJavaScriptCaller("showCustomDivWithDiffStyle();");
        return;
    }

    public String getStatusValue(CSCReqDedDTO dto) {

        Long status = Long.valueOf(dto.getRequestStatusId());
        if (status.equals(0L)) {
            return getSharedUtil().messageLocator(BUNDLE, "created");
        } else if (status.equals(1L)) {
            return getSharedUtil().messageLocator(BUNDLE, "initialTestFailed");
        } else if (status.equals(2L)) {
            return getSharedUtil().messageLocator(BUNDLE, "initialTestDone");
        } else if (status.equals(3L)) {
            return getSharedUtil().messageLocator(BUNDLE, "finalTestFailed");
        } else if (status.equals(4L)) {
            return getSharedUtil().messageLocator(BUNDLE, "finalTestDone");
        } else if (status.equals(5L)) {
            return getSharedUtil().messageLocator(BUNDLE, "executionFailed");
        } else if (status.equals(6L)) {
            return getSharedUtil().messageLocator(BUNDLE, "executionSuccess");
        } else if (status.equals(7L)) {
            return getSharedUtil().messageLocator(BUNDLE, "ignoreSuccess");
        }

        return "";
    }

    public String getStatusValue() {

        CSCReqDedDTO dto = (CSCReqDedDTO)this.getMyDataTable().getRowData();
        return getStatusValue(dto);
    }

    public void showRequestsBasedOn(String transactionNo) {
        try {
            reqID = null;
            setPageMode(2);
            try {
                requestList =
                        DedClientFactory.getDeductionsClient().getRequestsNotReqisterByTransactionNo(transactionNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setEnableOnSelect(true);
            setJavaScriptCaller("showCustomDivWithDiffStyle();");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
    //    public String redirectRequestViewDetailsPage() {
    //    if(secandaryGuideId !=null){
    //    //                requestOperationMainClient.checkEnqueryRequestPermission(secandaryGuideId,
    //    //                                                                              getUserInfo().getMinistryId(),reqType);
    //        DeductionEnquiryBean deductionEnquiryBean =
    //            (DeductionEnquiryBean)evaluateValueBinding("deductionEnquiryBean");
    //        RequestEnquiryBean requestEnquiryBean =
    //            (RequestEnquiryBean)evaluateValueBinding("requestEnquiryBean");
    //        requestEnquiryBean.searchByRequestId(reqID, reqType, issuerGov,getUserInfo());
    //        DeductionEnquiryDTO dto=deductionEnquiryBean.getSearchCriteria();
    //
    //        requestEnquiryBean.init(dto,"backToDeductEnquiryPage","deductionEnquiryBean.searchDeductions");
    //        return "backToRequestEnquiryPage";
    //    }
    //    return null;
    //    }


    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getReqType() {
        return reqType;
    }

    public void setIssuerGov(boolean issuerGov) {
        this.issuerGov = issuerGov;
    }

    public boolean isIssuerGov() {
        return issuerGov;
    }

    public void obtainSelectedRow(ActionEvent ae) {

        CSCReqDedDTO dto = (CSCReqDedDTO)this.getMyDataTable().getRowData();
        reqID = dto.getId().toString();
        reqType = dto.getRequestType();
        issuerGov = dto.isIssuerMinGov();
        setEnableOnSelect(false);

    }

    public void setEnableOnSelect(boolean enableOnSelect) {
        this.enableOnSelect = enableOnSelect;
    }

    public boolean isEnableOnSelect() {
        return enableOnSelect;
    }

    public void setSecandaryGuideId(Long secandaryGuideId) {
        this.secandaryGuideId = secandaryGuideId;
    }

    public Long getSecandaryGuideId() {
        return secandaryGuideId;
    }
}
