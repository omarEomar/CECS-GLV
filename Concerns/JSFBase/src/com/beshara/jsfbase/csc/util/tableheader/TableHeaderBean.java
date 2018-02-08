package com.beshara.jsfbase.csc.util.tableheader;


import com.beshara.jsfbase.csc.util.SharedUtilBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;

import javax.faces.webapp.UIComponentTag;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSession;

import org.ajax4jsf.ajax.html.HtmlAjaxCommandButton;

import org.apache.myfaces.component.html.ext.HtmlCommandButton;
import org.apache.myfaces.component.html.ext.HtmlPanelGrid;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import org.apache.myfaces.custom.navmenu.UINavigationMenuItem;
import org.apache.myfaces.custom.navmenu.jscookmenu.HtmlCommandJSCookMenu;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Bean compose the dynamic generation of table header (buttons and menus).
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g
 * <br><br>
 * <b>Creation/Modification History:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Amir Nasr    19-Jun-2008     Created
 *    <br>
 *    
 * @author       Beshara Group   
 * @version      1.0   
 * @since        19/01/2008   
 */
public class TableHeaderBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Map menuIndexActionMap = 
        new HashMap(); // this to hold each pair of index and action of each menu created by Assmaa Omar 
    private String menuIndex; //this var to hold the current menu index
    private String indexActionString = 
        ""; //this var to hold index action pair for the menu items that have javaScript actions
    private String ajaxMenuIndecis = 
        ""; //this var hold the menu indecis for those items that have ajax action

    private transient HtmlPanelGrid tableButtonsHeader = new HtmlPanelGrid();
    private transient HtmlPanelGrid tableHiddenHeader = new HtmlPanelGrid();
    private List<ModuleObject> objects = new ArrayList<ModuleObject>();
    private List menusList = new ArrayList();
    private String pageId;
    private String oldPageId = "";
    private Integer selectedCheckBoxesCount = new Integer(0);
    private Map selectedItemsMap = new HashMap();

    private HtmlCommandJSCookMenu menu;

    public TableHeaderBean() {
    }

    /**
     * Read the components from the source (db or xml) and generate them in as jsf components
     */
    public void readTableHeader() {
        try {
            HttpSession session = 
                (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            String confFile = getInitParam("tableHeaderDef");
            String path = 
                ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath(confFile);
            Document doc = parseXml(path);

            Element root = doc.getRootElement();
            if (pageId == null) {
                pageId = (String)session.getAttribute("pageId");
            } else {
                session.setAttribute("pageId", pageId);
            }
            Element pageType = root.getChild(pageId);
            if (pageType != null) {
                List<Element> components = pageType.getChildren();
                objects = new ArrayList<ModuleObject>();
                tableButtonsHeader = new HtmlPanelGrid();
                menusList = new ArrayList();
                for (Element component: components) {
                    objects.add(readComponentAttributes(component));
                }

                buildTableHeaderPanel(objects);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * parse the xml configuration file
     * @param path, the path of the xml file
     * @return Docmunemt
     */
    private Document parseXml(String path) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        FileInputStream file = new FileInputStream(new File(path));
        Document doc = builder.build(file);
        return doc;
    }

    /**
     * read the attribute of a component
     * @param component, represent the component (row in db, or xml tag)
     * @return ModuleObject, DTO carry the (row data, or xml tag)
     */
    private ModuleObject readComponentAttributes(Element component) {
        ModuleObject moduleObject = new ModuleObject();

        if (component.getAttribute("type") != null)
            moduleObject.setObjType(component.getAttribute("type").getValue());
        if (component.getAttribute("code") != null)
            moduleObject.setObjCode(component.getAttribute("code").getValue());
        if (component.getAttribute("name") != null)
            moduleObject.setObjName(component.getAttribute("name").getValue());
        if (component.getAttribute("action") != null)
            moduleObject.setObjPage(component.getAttribute("action").getValue());
        if (component.getAttribute("selectionFlag") != null)
            moduleObject.setSelectionFlag(component.getAttribute("selectionFlag").getValue());
        if (component.getAttribute("rerender") != null)
            moduleObject.setReRender(component.getAttribute("rerender").getValue());
        if (component.getAttribute("index") != null)
            moduleObject.setObjIndex(component.getAttribute("index").getValue());
        if (component.getAttribute("onComplete") != null)
            moduleObject.setOnComplete(component.getAttribute("onComplete").getValue());
        //by Soha
        if (component.getAttribute("styleclass") != null)
            moduleObject.setStyleClass(component.getAttribute("styleclass").getValue());

        if (component.getContentSize() > 0) {
            List<Element> childs = component.getChildren();
            for (Element child: childs) {
                moduleObject.getChildsList().add(readComponentAttributes(child));
            }
        }

        return moduleObject;
    }

    /**
     * build the jsf components from a list of DTO (readed from db || xml)
     * @param components, List of ModuleObject DTO
     */
    private void buildTableHeaderPanel(List<ModuleObject> components) {
        int buttonsNo = 0;
        for (ModuleObject component: components) {
            if (component.getObjType() != null && 
                component.getObjType().equals("button")) {
                buttonsNo++;
                if (component.getObjPage() != null && 
                    component.getObjPage().startsWith("ajax:")) {
                    HtmlAjaxCommandButton button = createAjaxButton(component);
                    tableButtonsHeader.getChildren().add(button);
                } else if (component.getObjPage() != null) {
                    HtmlCommandButton button = createButton(component);
                    tableButtonsHeader.getChildren().add(button);
                }
            } else if (component.getObjType() != null && 
                       component.getObjType().equals("menu")) {
                NavigationMenuItem subMenu = createMenu(component);
                menusList.add(subMenu);
            }
        }

        selectedCheckBoxesCount = new Integer(0);
        tableButtonsHeader.setColumns(buttonsNo);
    }

    /**
     * create a jsf button
     * @param buttonDTO
     * @return HtmlCommandButton, represent the jsf component
     */
    private HtmlCommandButton createButton(ModuleObject buttonDTO) {
        HtmlCommandButton button = new HtmlCommandButton();

        //        if (buttonDTO.getObjName() != null) {
        //            ValueBinding value = 
        //                FacesContext.getCurrentInstance().getApplication().createValueBinding(buttonDTO.getObjName());
        //            button.setValueBinding("value", value);
        //        }
        button.setValue("");

        if (buttonDTO.getObjCode() != null) {
            button.setId(buttonDTO.getObjCode());
        }
        if (buttonDTO.getObjPage() != null && 
            buttonDTO.getObjPage().startsWith("javascript:")) {
            button.setOnclick(buttonDTO.getObjPage().replaceAll("javascript:", 
                                                                "") + 
                              "return false;");
            button.setType("button");
        } else if (buttonDTO.getObjPage() != null) {
            MethodBinding action = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding(buttonDTO.getObjPage(), 
                                                                                       null);
            button.setAction(action);
        }

        if (buttonDTO.getStyleClass() != null) {
            button.setStyleClass(buttonDTO.getStyleClass());
        }

        if (buttonDTO.getSelectionFlag() != null && 
            !buttonDTO.getSelectionFlag().equals("0")) {
            button.setDisabled(true);
        }

        return button;
    }

    private HtmlAjaxCommandButton createAjaxButton(ModuleObject buttonDTO) {
        HtmlAjaxCommandButton button = new HtmlAjaxCommandButton();

        //        if (buttonDTO.getObjName() != null) {
        //            ValueBinding value = 
        //                FacesContext.getCurrentInstance().getApplication().createValueBinding(buttonDTO.getObjName());
        //            button.setValueBinding("value", value);
        //        }

        if (buttonDTO.getObjCode() != null) {
            button.setId(buttonDTO.getObjCode());
        }
        if (buttonDTO.getObjPage() != null && 
            buttonDTO.getObjPage().startsWith("ajax:")) {
            MethodBinding action = 
                FacesContext.getCurrentInstance().getApplication().createMethodBinding(buttonDTO.getObjPage().replaceAll("ajax:", 
                                                                                                                         ""), 
                                                                                       null);
            button.setAction(action);
        }
        if (buttonDTO.getReRender() != null) {
            button.setReRender(buttonDTO.getReRender());
        }

        if (buttonDTO.getOnComplete() != null) {
            button.setOncomplete(buttonDTO.getOnComplete());
        }

        if (buttonDTO.getStyleClass() != null) {
            button.setStyleClass(buttonDTO.getStyleClass());
        }

        if (buttonDTO.getSelectionFlag() != null && 
            !buttonDTO.getSelectionFlag().equals("0")) {
            //button.setDisabled(true);         //suspend now.................
        }

        return button;
    }

    /**
     * create a jsf menu
     * @param itemDTO
     * @return NavigationMenuItem, represent the jsf componenet
     */
    private NavigationMenuItem createMenu(ModuleObject itemDTO) {
        /*
         this part fill the  menuIndexActionMap var for ajax request  if the itemDto action start with Ajax:
         and concate ajaxMenuIndecis var with the itemDTO index to call ajax request only with these indecis
         or build indexActionString  var with index and action of menu item that has action start with javascript:
       */
        if (!menuIndexActionMap.containsKey(itemDTO.getObjIndex()) && 
            (itemDTO.getObjPage() != null)) {
            if (itemDTO.getObjPage().startsWith("javascript:")) {
                indexActionString += 
                        itemDTO.getObjIndex() + "#" + itemDTO.getObjPage() + 
                        "#" + itemDTO.getSelectionFlag() + "$";
                itemDTO.setObjPage(null);
            } else if (itemDTO.getObjPage().startsWith("Ajax:")) {
                ajaxMenuIndecis += itemDTO.getObjIndex() + "$";
                menuIndexActionMap.put(itemDTO.getObjIndex(), 
                                       itemDTO.getObjPage());
                itemDTO.setObjPage(null);
            }
        }

        NavigationMenuItem item = new NavigationMenuItem();
        if (itemDTO.getObjCode() != null)
            item.setValue(itemDTO.getObjCode());
        if (itemDTO.getObjName() != null) {
            item.setLabel(itemDTO.getObjName());
        }

        if (itemDTO.getObjPage() != null) {
            item.setAction(itemDTO.getObjPage());
        }

        if (itemDTO.getChildsList().size() > 0) {
            for (ModuleObject subMenu: itemDTO.getChildsList()) {
                item.add(createMenu(subMenu));
            }
        }

        return item;
    }

    /**
     * get an init parameter from web.xml
     * @param paramName , String represnt the parameter entityName
     * @return String, represent the parameter value
     */
    private String getInitParam(String paramName) {
        return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(paramName);
    }

    public void decrementSelected() {
        selectedCheckBoxesCount--;
    }

    public void incrementSelected() {
        selectedCheckBoxesCount++;
    }

    public void changeStatus(ActionEvent event) {
        SharedUtilBean sharedUtil = getSharedutilBean();
        Integer pageIndex = sharedUtil.getPageIndex();
        Integer count = 0;
        setSelectedCheckBoxesCount(0);

        String checkAll = 
            (String)event.getComponent().getParent().getAttributes().get("checkAll");
        Boolean checked = 
            (Boolean)((HtmlSelectBooleanCheckbox)event.getComponent().getParent()).getSubmittedValue();
        Integer listSize = 
            (Integer)event.getComponent().getParent().getAttributes().get("listSize");

        if (checked == null)
            checked = 
                    (Boolean)((HtmlSelectBooleanCheckbox)event.getComponent().getParent()).getValue();

        if (selectedItemsMap.get(pageIndex) != null) {
            count = (Integer)selectedItemsMap.get(pageIndex);
        }

        if (checkAll != null && checkAll.equals("true") && checked) {
            updateSelectedItemsMap(pageIndex, getNumber(listSize) - count);
        } else if (checkAll != null && checkAll.equals("true") && !checked) {
            updateSelectedItemsMap(pageIndex, 0);
        } else if (checked) {
            updateSelectedItemsMap(pageIndex, count + 1);
        } else {
            updateSelectedItemsMap(pageIndex, count - 1);
        }

        Set keys = selectedItemsMap.keySet();
        for (Object key: keys) {
            setSelectedCheckBoxesCount(getSelectedCheckBoxesCount() + 
                                       (Integer)selectedItemsMap.get((Integer)key));
        }

        checkStatus();
    }

    public void checkComponentStatus(ModuleObject object) {
        if (selectedCheckBoxesCount < 1) {
            if (object.getSelectionFlag().equals("1") || 
                object.getSelectionFlag().equals("2")) {
                setComponentStatus(object.getObjCode(), true);
            }
        } else if (selectedCheckBoxesCount == 1) {
            if (object.getSelectionFlag().equals("1") || 
                object.getSelectionFlag().equals("2")) {
                setComponentStatus(object.getObjCode(), false);
            }
        } else if (selectedCheckBoxesCount > 1) {
            if (object.getSelectionFlag().equals("1")) {
                setComponentStatus(object.getObjCode(), true);
            } else if (object.getSelectionFlag().equals("2")) {
                setComponentStatus(object.getObjCode(), false);
            }
        }
    }

    public HtmlCommandButton getButtonById(String btnId) {
        HtmlCommandButton button = 
            (HtmlCommandButton)tableButtonsHeader.findComponent(btnId);
        if (button != null)
            return button;

        return null;
    }

    public NavigationMenuItem getMenuById(String menuId) {
        for (int i = 0; i < menusList.size(); i++) {
            NavigationMenuItem menu = (NavigationMenuItem)menusList.get(i);
            if (menu.getNavigationMenuItems().length > 0) {
                for (int j = 0; j < menu.getNavigationMenuItems().length; 
                     j++) {
                    NavigationMenuItem subMenu = 
                        menu.getNavigationMenuItems()[j];
                    if (subMenu.getValue() != null && 
                        subMenu.getValue().equals(menuId))
                        return menu.getNavigationMenuItems()[j];
                }
            }
            if (menu.getValue() != null && menu.getValue().equals(menuId))
                return (NavigationMenuItem)menusList.get(i);
        }
        return null;
    }

    private void setComponentStatus(String id, boolean disabled) {
        HtmlCommandButton button = getButtonById(id);
        if (button != null) {
            button.setDisabled(disabled);
        } else {
            NavigationMenuItem menu = getMenuById(id);
            if (menu != null) {
                menu.setDisabled(disabled);
            }
        }
    }

    private Integer getNumber(Integer listSize) {
        SharedUtilBean sharedUtil = getSharedutilBean();
        Integer noOfRows = sharedUtil.getNoOfTableRows();
        Integer pageIndex = sharedUtil.getPageIndex();
        Integer noOfPages = listSize / noOfRows;
        Integer subPage = listSize % noOfRows;
        if (subPage > 0)
            noOfPages++;

        if (noOfPages == pageIndex) {
            if (subPage > 0) {
                return subPage;
            }
        }
        return noOfRows;
    }

    public void changeTreeHeader(ActionEvent event) {
        selectedCheckBoxesCount = 1;
        checkStatus();
    }

    private void checkStatus() {
        for (int i = 0; i < objects.size(); i++) {
            ModuleObject object = objects.get(i);
            for (int j = 0; j < object.getChildsList().size(); j++) {
                ModuleObject childObject = object.getChildsList().get(j);
                checkComponentStatus(childObject);
            }
            checkComponentStatus(object);
        }
    }


    public void setTableButtonsHeader(HtmlPanelGrid tableButtonsHeader) {
        this.tableButtonsHeader = tableButtonsHeader;
    }

    public HtmlPanelGrid getTableButtonsHeader() {
        getObjects();
        return tableButtonsHeader;
    }

    public void setMenusList(List menusList) {
        this.menusList = menusList;
    }

    public List getMenusList() {
        getObjects();
        return menusList;
    }

    public void setObjects(List<ModuleObject> objects) {
        this.objects = objects;
    }

    public List<ModuleObject> getObjects() {
        if (objects.size() == 0 || 
            (this.pageId != null && !this.pageId.equals(oldPageId))) {
            readTableHeader();
        }
        return objects;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
        if (!oldPageId.equals(pageId)) {
            readTableHeader();
            oldPageId = pageId;
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void delete() {
        System.out.println("delete.....");
    }

    public void setOldPageId(String oldPageId) {
        this.oldPageId = oldPageId;
    }

    public String getOldPageId() {
        return oldPageId;
    }

    public void setTableHiddenHeader(HtmlPanelGrid tablehiddenHeader) {
        this.tableHiddenHeader = tablehiddenHeader;
    }

    public HtmlPanelGrid getTableHiddenHeader() {
        return tableHiddenHeader;
    }

    public void setSelectedCheckBoxesCount(Integer selectedCheckBoxesCount) {
        this.selectedCheckBoxesCount = selectedCheckBoxesCount;
        checkStatus();
    }

    public Integer getSelectedCheckBoxesCount() {
        return selectedCheckBoxesCount;
    }

    /**
     * this method responsible for handling the ajax request and firing the corresponding action by doing method binding
     *  created by Assmaa Omar 
     * */
    public String handleAjaxAction(ActionEvent ae) {
        String action = "";

        if (!menuIndexActionMap.isEmpty() && 
            menuIndexActionMap.containsKey(menuIndex)) {
            action = (String)menuIndexActionMap.get(menuIndex);
            action = action.replaceFirst("Ajax:", "");
            if (action != null && action.length() != 0) {
                {
                    MethodBinding methodBinding = 
                        FacesContext.getCurrentInstance().getApplication().createMethodBinding(action, 
                                                                                               null);
                    methodBinding.invoke(FacesContext.getCurrentInstance(), 
                                         null);
                }
            }

        } else {
            System.out.println("handleAjaxAction:menuIndexActionMap empty");
        }
        return null;
    }

    public void setMenuIndexActionMap(Map menuIndexActionMap) {
        this.menuIndexActionMap = menuIndexActionMap;
    }

    public Map getMenuIndexActionMap() {
        return menuIndexActionMap;
    }

    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public void setIndexActionString(String indexActionString) {
        this.indexActionString = indexActionString;
    }

    public String getIndexActionString() {
        return indexActionString;
    }

    public void setAjaxMenuIndecis(String ajaxMenuIndecis) {
        this.ajaxMenuIndecis = ajaxMenuIndecis;
    }

    public String getAjaxMenuIndecis() {
        return ajaxMenuIndecis;
    }

    public void setSelectedItemsMap(Map selectedItems) {
        this.selectedItemsMap = selectedItems;
    }

    public Map getSelectedItemsMap() {
        return selectedItemsMap;
    }

    private SharedUtilBean getSharedutilBean() {
        return (SharedUtilBean)FacesContext.getCurrentInstance().getApplication().createValueBinding("#{shared_util}").getValue(FacesContext.getCurrentInstance());
    }

    private void updateSelectedItemsMap(Integer pageIndex, 
                                        Integer itemsCount) {
        if (selectedItemsMap.get(pageIndex) == null) {
            selectedItemsMap.put(pageIndex, itemsCount);
        } else {
            selectedItemsMap.put(pageIndex, itemsCount);
        }
    }

    public void setMenu(HtmlCommandJSCookMenu menu) {
        this.menu = menu;
        List childern = menu.getChildren();
        childern.clear();
        addMenuItems(childern, (NavigationMenuItem[])menusList.toArray(new NavigationMenuItem[0]));
    }

    public HtmlCommandJSCookMenu getMenu() {
        return menu;
    }

    private void addMenuItems(List uiMenuItems, 
                              NavigationMenuItem[] menuItems) {
        for (NavigationMenuItem menuItem: menuItems) {
            UINavigationMenuItem uiMenuItem = 
                createUINavigationMenuItem(menuItem);
            uiMenuItems.add(uiMenuItem);

            NavigationMenuItem[] subMenuItems = 
                menuItem.getNavigationMenuItems();
            if (subMenuItems.length != 0) {
                addMenuItems(uiMenuItem.getChildren(), subMenuItems);
            }

        }
    }

    private UINavigationMenuItem createUINavigationMenuItem(NavigationMenuItem menuItem) {
        UINavigationMenuItem uiMenuItem = new UINavigationMenuItem();
        
        Object value = menuItem.getValue();
        if(value != null) {
            uiMenuItem.setItemValue(menuItem.getValue());    
        }
        
        
        String label = menuItem.getLabel();
        if(label != null) {
            if(UIComponentTag.isValueReference(label)) {
                uiMenuItem.setValueBinding("itemLabel", FacesContext.getCurrentInstance().getApplication().createValueBinding(label));
            } else {
                uiMenuItem.setItemLabel(label);    
            }            
        }
        
        String action = menuItem.getAction();
        if(action != null) {
            if(UIComponentTag.isValueReference(action)) {
                uiMenuItem.setAction(FacesContext.getCurrentInstance().getApplication().createMethodBinding(action, null));
            } else {
                uiMenuItem.setAction(new ConstantMethodBinding(action));    
            }
            
        }

        return uiMenuItem;        
    }

}
