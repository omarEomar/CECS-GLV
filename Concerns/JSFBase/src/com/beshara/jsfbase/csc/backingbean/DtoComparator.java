package com.beshara.jsfbase.csc.backingbean;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;


public class DtoComparator implements Comparator {
    private String INVALID_LIST_EXPRESSION_EXCEPTION_MSG = 
        "Invalid listExpression param value";

    public DtoComparator() {
    }

    // Init --------------------------------------------------------------------------------------

    private List<String> getters;
    private String listExpression;
    private String elExpression;
    private String currentSortingRowIndex;
    private boolean ascending;
    private boolean useElExpression;

    // Constructor -------------------------------------------------------------------------------

    /**
     * @param getter The name of the getter of the field to sort on.
     * @param ascending The sort order: true = ascending, false = descending.
     */

    /**
     * Purpose: this constructor is used to split the id passed from Sort method in Basebean and add the 'get' word to the tag to go through its getters and get the value,
     * Creation/Modification History :
     * 1.1 - Developer Name: Mohamed Galala
     * 1.2 - Date:  Oct 19, 2008
     * 1.3 - Creation/Modification: Modification done in the following line (String entityName : getter.split("-"))
     * split(".") replaced by split("-") to split at the '-' typed in the commandlink id
     * 1.4-  Description: this constructor creates an ArrayList of gettters for a specific column, ex: in case of String getter= code-genspecsCode,
     * so the ArralyList created will contain 2 values, the first is getCode, the second is getGenspecsCode. (to get the inner DTOs)
     */
    public DtoComparator(String getter, boolean ascending) {

        this.getters = new ArrayList<String>();

        for (String name: getter.split("-")) {
            if (!name.startsWith("get") && !name.startsWith("is")) {
                name = 
"get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            }
            this.getters.add(name);
        }
        this.ascending = ascending;
    }

    /**
     * Purpose: this constructor is used to sort using the expression language (EL)
     * Creation/Modification History : 
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Jan 15, 2009 
     * @param listExpression : tabel list binding expression, ex. myListBean.myTableData
     * @param currentSortingRowIndex :which will carry the current row index at the sorting time
     * @param elExpression : the el which will be evaluated, cant handel the resources till now, ex. myListBean.myTableData[myListBean.currentSortingRowIndex].myAttribute
     * @param ascending : true/false
     */
    public DtoComparator(String listExpression, String currentSortingRowIndex, 
                         String elExpression, boolean ascending) {
        this.listExpression = listExpression;
        this.elExpression = 
                elExpression != null ? elExpression.replaceAll("&amp;", "&") : 
                elExpression;
        this.currentSortingRowIndex = currentSortingRowIndex;

        this.ascending = ascending;
        this.useElExpression = 
                listExpression != null && listExpression.length() > 0 && 
                elExpression != null && elExpression.length() > 0;
    }

    /**
     * @param getter The entityName of the getter of the field to sort on.
     */
    public DtoComparator(String getter) {
        this(getter, true);
    }

    // Actions -----------------------------------------------------------------------------------

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object object1, Object object2) {
        if (useElExpression) {
            return compareCurrentObjectsUsingEL(object1, object2);
        } else {
            return compareCurrentObjectsUsingGetters(object1, object2);
        }
    }

    /**
     * Purpose: this method is used to compare between two objects by evaluating the EL expression value
     * Creation/Modification History : 
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Jan 15, 2009 
     * @param object1 : the first object
     * @param object2 : the second one
     */
    private int compareCurrentObjectsUsingEL(Object object1, Object object2) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Object listObject = getValueBinding(ctx, listExpression).getValue(ctx);

        if (!(listObject instanceof List)) {
            throw new RuntimeException(INVALID_LIST_EXPRESSION_EXCEPTION_MSG + 
                                       ":" + listExpression);
        }
        int index = ((List)listObject).indexOf(object1);
        getValueBinding(ctx, currentSortingRowIndex).setValue(ctx, index);

        object1 = getValueBinding(ctx, elExpression).getValue(ctx);

        index = ((List)listObject).indexOf(object2);
        getValueBinding(ctx, currentSortingRowIndex).setValue(ctx, index);

        object2 = getValueBinding(ctx, elExpression).getValue(ctx);

        return compareObjects(object1, object2);
    }

    private int compareCurrentObjectsUsingGetters(Object object1, 
                                                  Object object2) {
        try {
            Iterator<String> iter = getters.iterator();
            while (object1 != null && object2 != null && iter.hasNext()) {
                String getter = iter.next();
                object1 = 
                        object1.getClass().getMethod(getter, new Class[0]).invoke(object1, 
                                                                                  new Object[0]);
                object2 = 
                        object2.getClass().getMethod(getter, new Class[0]).invoke(object2, 
                                                                                  new Object[0]);
            }
        } catch (Exception e) {
            // If this exception occurs, then it is usually a fault of the DTO developer.
            throw new RuntimeException("Cannot compare " + object1 + " with " + 
                                       object2 + " on " + getters, e);
        }

        return compareObjects(object1, object2);
    }

    /**
     * Purpose: this method is used to evaluate a value binding expression in the passed faces context
     * Creation/Modification History : 
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Jan 15, 2009 
     * @param facesContext
     * @param valueExpression
     */
    private ValueBinding getValueBinding(FacesContext facesContext, 
                                         String valueExpression) {
        return facesContext.getApplication().createValueBinding("#{" + 
                                                                valueExpression + 
                                                                "}");
    }

    /**
     * Purpose: this method is used to call the compare method
     * Creation/Modification History : 
     * 1.1 - Developer Name: Ashraf Gaber
     * 1.2 - Date:  Jan 15, 2009 
     * @param object1
     * @param object2
     */
    private int compareObjects(Object object1, Object object2) {
        if (object1 == null) {
            return ascending ? -1 : 1; // If ascending, current null first.
        } else if (object2 == null) {
            return ascending ? 1 : -1; // If ascending, another null first.
        }

        if (ascending) {
            return ((Comparable)object1).compareTo(object2); // Ascending.
        } else {
            return ((Comparable)object2).compareTo(object1); // Descending.
        }
    }
}
