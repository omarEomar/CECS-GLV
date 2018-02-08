package com.beshara.jsfbase.csc.custom;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.myfaces.custom.datascroller.HtmlDataScroller;
/**
 * Overwrite the HtmlDataScrollerRenderer to handle the disable action
 * of the facets and also the current page
 * @author Ashraf Gaber, 28/3/2011
 */

public class HtmlDataScrollerRenderer extends org.apache.myfaces.custom.datascroller.HtmlDataScrollerRenderer {
    protected static final String FACET_FIRST = "first".intern();
    protected static final String FACET_PREVOIUS = "previous".intern();
    protected static final String FACET_NEXT = "next".intern();
    protected static final String FACET_LAST = "last".intern();
    protected static final String FACET_FAST_FORWARD = "fastf".intern();
    protected static final String FACET_FAST_REWIND = "fastr".intern();
    protected static final String PAGE_NAVIGATION = "idx".intern();

    protected void renderFacet(FacesContext facesContext, 
                               HtmlDataScroller scroller, 
                               UIComponent facetComp, 
                               String facetName) throws IOException {
        HtmlCommandLink link = null;
        String onclick = scroller.getOnclick();
        String ondblclick = scroller.getOndblclick();

        boolean isLinkDisabled = false;
        if (scroller.getPageIndex() == 1 && 
            (facetName.equals(FACET_FIRST) || facetName.equals(FACET_PREVOIUS) || 
             facetName.equals(FACET_FAST_REWIND)) || 
            (scroller.getPageIndex() == scroller.getPageCount() && 
             (facetName.equals(FACET_LAST) || facetName.equals(FACET_NEXT) || 
              facetName.equals(FACET_FAST_FORWARD)))) {
            isLinkDisabled = true;
        }

        if (!isLinkDisabled) {
            link = getLink(facesContext, scroller, facetName);
            if (onclick != null) {
                link.setOnclick(onclick);
            }
            if (ondblclick != null) {
                link.setOndblclick(ondblclick);
            }
            link.encodeBegin(facesContext);
        }

        facetComp.encodeBegin(facesContext);
        if (facetComp.getRendersChildren())
            facetComp.encodeChildren(facesContext);
        facetComp.encodeEnd(facesContext);

        if (!isLinkDisabled) {
            link.encodeEnd(facesContext);
        }
    }

    protected void renderPaginator(FacesContext facesContext, 
                                   HtmlDataScroller scroller) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        int maxPages = scroller.getPaginatorMaxPages();
        if (maxPages <= 1) {
            maxPages = 2;
        }
        int pageCount = getPageCount(scroller.getUIData());
        if (pageCount <= 1) {
            return;
        }
        int pageIndex = getPageIndex(scroller.getUIData());
        int delta = maxPages / 2;

        int pages;
        int start;
        if (pageCount > maxPages && pageIndex > delta) {
            pages = maxPages;
            start = pageIndex - pages / 2 - 1;
            if (start + pages > pageCount) {
                start = pageCount - pages;
            }
        } else {
            pages = pageCount < maxPages ? pageCount : maxPages;
            start = 0;
        }

        writer.startElement("table", scroller);

        String styleClass = scroller.getPaginatorTableClass();
        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, null);
        }
        String style = scroller.getPaginatorTableStyle();
        if (style != null) {
            writer.writeAttribute("style", style, null);
        }

        writer.startElement("tr", scroller);

        for (int i = start, size = start + pages; i < size; i++) {
            int idx = i + 1;
            writer.startElement("td", scroller);
            String cStyleClass;
            String cStyle;
            if (idx == pageIndex) {
                cStyleClass = scroller.getPaginatorActiveColumnClass();
                cStyle = scroller.getPaginatorActiveColumnStyle();
            } else {
                cStyleClass = scroller.getPaginatorColumnClass();
                cStyle = scroller.getPaginatorColumnStyle();
            }
            if (cStyleClass != null) {
                writer.writeAttribute("class", cStyleClass, null);
            }
            if (cStyle != null) {
                writer.writeAttribute("style", cStyle, null);
            }
            HtmlCommandLink link = 
                getLink(facesContext, scroller, Integer.toString(idx), idx);
            if (idx == pageIndex) {
                HtmlOutputText uiText = 
                    (HtmlOutputText)facesContext.getApplication().createComponent(HtmlOutputText.COMPONENT_TYPE);
                uiText.setTransient(true);
                uiText.setValue(Integer.toString(idx));
                uiText.setParent(link.getParent());
                uiText.setId(link.getId()+ "sheka");
                uiText.encodeBegin(facesContext);
                uiText.encodeEnd(facesContext);
            } else {

                link.encodeBegin(facesContext);
                link.encodeChildren(facesContext);

                link.encodeEnd(facesContext);
            }
            writer.endElement("td");
        }

        writer.endElement("tr");
        writer.endElement("table");
    }

    protected int getPageIndex(UIData uiData) {
        int rows = uiData.getRows();
        int pageIndex;
        if (rows > 0) {
            pageIndex = uiData.getFirst() / rows + 1;
        } else {
            pageIndex = 0;
        }
        if (uiData.getFirst() % rows > 0) {
            pageIndex++;
        }
        return pageIndex;
    }

    protected int getPageCount(UIData uiData) {
        int rows = uiData.getRows();
        int pageCount;
        if (rows > 0) {
            pageCount = rows <= 0 ? 1 : uiData.getRowCount() / rows;
            if (uiData.getRowCount() % rows > 0) {
                pageCount++;
            }
        } else {
            rows = 1;
            pageCount = 1;
        }
        return pageCount;
    }


}
