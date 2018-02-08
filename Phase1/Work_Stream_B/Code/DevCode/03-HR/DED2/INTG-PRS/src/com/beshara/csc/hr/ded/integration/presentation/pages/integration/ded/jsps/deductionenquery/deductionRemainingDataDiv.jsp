<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

  <t:panelGroup styleClass="confirmMsg_header">
         <h:outputText  value="#{resourcesBundle.depositeData}" />
  </t:panelGroup>
<t:panelGroup styleClass="lovDiv_body">
    <t:panelGroup styleClass="lovDiv_body_contents">
  <t:panelGrid columns="4" id="deduction_data" styleClass="dedRemainingDiv_body" rowClasses="row02,row01" cellpadding="3" cellspacing="0">

             <t:outputText  value="#{resourcesBundle.alreadyDeducted}"  styleClass="output_label nowraped_txt"  />
            <t:inputText id="alreadyDeducted" forceId="true" value="#{deductionEnquiryBean.remainingDataDto.totalPaid}" styleClass="margin_t5" disabled="true" />
             
             <t:outputText  value="#{resourcesBundle.alreadyReplyed}" styleClass="output_label nowraped_txt"/>
             <t:inputText id="alreadyReplyed" forceId="true" value="#{deductionEnquiryBean.remainingDataDto.totalReplyed}" disabled="true"/>
             
              <t:outputText  value="#{resourcesBundle.reservedForReply}" styleClass="output_label nowraped_txt"/>
             <t:inputText id="reservedForReply" forceId="true" value="#{deductionEnquiryBean.remainingDataDto.reservedToReply}" disabled="true"/>
           
        </t:panelGrid>

  <t:panelGrid columns="1" styleClass="dedRemainingDiv_footer">
        <t:commandButton styleClass="btn btn-info" value="#{resourcesBundle.accept}"  />    
  </t:panelGrid> 
  </t:panelGroup>
  </t:panelGroup>
  









        
  
