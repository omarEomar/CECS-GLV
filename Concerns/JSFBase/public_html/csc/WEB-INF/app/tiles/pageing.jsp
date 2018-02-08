<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<tr>
    <td width="820" height="20" align="center">
        <h:panelGrid id="panelGrd_scroller" columns="1"
                     >
            <t:dataScroller id="scroller_scroller1" for="dataT_data"
                            fastStep="5" pageCountVar="pageCount"
                            pageIndexVar="pageIndex"
                            styleClass="scroller" paginator="true"
                            paginatorMaxPages="5"
                            paginatorTableClass="paginator"
                            paginatorActiveColumnStyle="font-size: 10pt;text-decoration: none;font-weight:bold"
                            immediate="true">
                <f:facet name="first">
                    <h:panelGroup id="panelGrp_first">
                        <t:graphicImage id="img_firstOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_first}"
                                        url="/images/#{globalResources.photoFolder}/arrow-last.gif"
                                        border="1"/>
                        <t:graphicImage id="img_firstOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/images/#{globalResources.photoFolder}/arrow-last_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="last">
                    <h:panelGroup id="panelGrp_last">
                        <t:graphicImage id="img_lastOn"
                                        rendered="#{pageIndex < pageCount}"
                                        title="#{globalResources.scroller_last}"
                                        url="/images/#{globalResources.photoFolder}/arrow-first.gif"
                                        border="1"/>
                        <t:graphicImage id="img_lastOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/arrow-first_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="previous">
                    <h:panelGroup id="panelGrp_prv">
                        <t:graphicImage id="img_prvOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_previous}"
                                        url="/images/#{globalResources.photoFolder}/arrow-next.gif"
                                        border="1"/>
                        <t:graphicImage id="img_prvOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/images/#{globalResources.photoFolder}/arrow-next_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="next">
                    <h:panelGroup id="panelGrp_nxt">
                        <t:graphicImage id="img_nxtOn"
                                        rendered="#{pageIndex < pageCount}"
                                        title="#{globalResources.scroller_next}"
                                        url="/images/#{globalResources.photoFolder}/arrow-previous.gif"
                                        border="1"/>
                        <t:graphicImage id="img_nxtOff"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/arrow-previous_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastforward">
                    <h:panelGroup id="panelGrp_ffrwrd">
                        <t:graphicImage id="img_ffrwrdOn"
                                        rendered="#{pageIndex < pageCount}"
                                        title="#{globalResources.scroller_fastforward}"
                                        url="/images/#{globalResources.photoFolder}/arrow-fr.gif"
                                        border="1"/>
                        <t:graphicImage id="img_ffrwrdOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex >= pageCount}"
                                        url="/images/#{globalResources.photoFolder}/arrow-fr_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
                <f:facet name="fastrewind">
                    <h:panelGroup id="panelGrp_frwnd">
                        <t:graphicImage id="img_frwndOn"
                                        rendered="#{pageIndex > 1}"
                                        title="#{globalResources.scroller_fastrewind}"
                                        url="/images/#{globalResources.photoFolder}/arrow-ff.gif"
                                        border="1"/>
                        <t:graphicImage id="img_frwndOff"
                                        onclick="return false;"
                                        rendered="#{pageIndex <= 1}"
                                        url="/images/#{globalResources.photoFolder}/arrow-ff_gray.gif"
                                        border="1"/>
                    </h:panelGroup>
                </f:facet>
            </t:dataScroller>
        </h:panelGrid>
    </td>
</tr>
