<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<t:panelGrid columns="5" style="margin-right:auto;margin-left:auto;">
    <%--<t:commandButton value="#{intgBundle.lovSearch}" disabled="#{deductionEnquiryBean.resultMode}" action="#{deductionEnquiryBean.searchRequests}" onclick="return validateDedEnquiry();" styleClass="btn btn-info"/>--%>
    <%--<t:commandButton value="#{intgBundle.lovCancelSearch}" action="#{deductionEnquiryBean.cancelSearch}"  styleClass="btn btn-info"/>--%>
    <%--<t:commandButton value="#{intgBundle.viewDepositeData}"  disabled="#{!deductionEnquiryBean.resultMode}" action="#{deductionEnquiryBean.showRemainingData}" styleClass="btn btn-info" style="width:125px;" />--%>
    <t:commandButton value="#{intgBundle.showInsDtls}"  disabled="#{!deductionEnquiryBean.resultMode}" action="#{deductionEnquiryBean.showEmpInstallments}" styleClass="btn btn-info" style="width:135px;" />

    <t:panelGroup forceId="true" id="backBtnPanel" rendered="#{deductionEnquiryBean.backNavCase !=null}">
          <t:commandButton value="#{intgBundle.return}"  styleClass="btn btn-info"  action="#{deductionEnquiryBean.back}"  />
    </t:panelGroup>
</t:panelGrid>
  
