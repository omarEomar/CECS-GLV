package com.beshara.jsfbase.csc.util;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * @author       Ali Abdel-Aziz
 * @version      1.0
 * @since        22/11/2007
 */
public class Helper {
    public Helper() {
    }

    /**
     * formate search string that is if the entered text doesn't contain any %
     * character it will append % to the end of the entered string else leave 
     * it as it is.
     * 
     * @author Ali Abdel-Aziz
     *
     * @param inputString
     * 
     * @return java.lang.String
     */
    public static String formatSearchString(String inputString) {
        return (inputString.contains((CharSequence)"%")) ? inputString : 
               inputString + "%";
    }

    /**
     * SearchType Data Structure for tree.
     */
    enum SearchType {
        START_WITH,
        LIKE,
        END_WITH,
        UNKNOWEN,
        ;
    }

    /**
     * formate search string that is if the entered text doesn't contain any %
     * character it will append % to the end of the entered string else leave 
     * it as it is.
     * 
     * 
     * @param searchFor,
     * @param searchIn
     * @param isCaseSensitive
     * @return java.lang.boolean
     */
    public static boolean searchInTree(String searchFor, String searchIn, 
                                       boolean isCaseSensitive) {
        if (!isCaseSensitive) {
            searchFor = searchFor.toUpperCase();
            searchIn = searchIn.toUpperCase();
        }

        if (searchInTreeType(searchFor) == SearchType.START_WITH) {
            searchFor = searchFor.replaceAll("%", "");
            return searchIn.startsWith(searchFor);
        } else if (searchInTreeType(searchFor) == SearchType.LIKE) {
            searchFor = searchFor.replaceAll("%", "");
            return searchIn.contains((CharSequence)searchFor);
        } else if (searchInTreeType(searchFor) == SearchType.END_WITH) {
            searchFor = searchFor.replaceAll("%", "");
            return searchIn.endsWith(searchFor);
        }
        return false;
    }

    /**
     * Determine the search type whither it is START_WITH
     * 
     * @param searchFor.
     * @return SearchType
     */
    private static SearchType searchInTreeType(final String searchFor) {
        if (searchFor.startsWith("%") && searchFor.endsWith("%")) {
            return SearchType.LIKE;
        } else if (!searchFor.contains((CharSequence)"%") || 
                   searchFor.endsWith("%")) {
            return SearchType.START_WITH;
        } else if (searchFor.startsWith("%")) {
            return SearchType.END_WITH;
        }
        return SearchType.UNKNOWEN;
    }

    /**
     * implementor : Moatazz Mansour
     * this finish use to build the tree from the list of data
     * the object inside list have to extends ITreeDTO
     * @param data
     * @return List of roots each one contain list of childern and each object 
     *         contain list of its childern too and ......etc.  
     * @throws RemoteException
     */
    public static List<IBasicDTO> buildTree(List<IBasicDTO> data) throws Exception {
        //List <IBasicDTO> treeRoots = new ArrayList();
        List roots = new ArrayList();
        Map allElement = new HashMap();
        String key = null;
        //Fill the map of Data Entry
        for (IBasicDTO element: data) {

            if (ISystemConstant.FIRST_LEVEL_IN_TREE.equals(((ITreeDTO)element).getTreeLevel())) {
                roots.add(element);
            }
            key = element.getCode().getKey();
            allElement.put(key, element);

            // multiple calls to build tree with the same list need to reinitialize it added by aboelhassan hamed
            if (((ITreeDTO)element).getChildrenList() != null) {
                ((ITreeDTO)element).getChildrenList().clear();
            }
        }
        //Build the Tree
        for (IBasicDTO element: data) {
            if (!ISystemConstant.FIRST_LEVEL_IN_TREE.equals(((ITreeDTO)element).getTreeLevel())) {
                try {
                    key = ((ITreeDTO)element).getParentCode().getKey();
                } catch (Exception e) {
                    System.out.println("((ITreeDTO)element).getCode().getKey()>>>>>>>>>>>           " + 
                                       element.getCode().getKey());
                }
                ITreeDTO parent = (ITreeDTO)allElement.get(key);

                ((ITreeDTO)element).setParentObject(parent);
                if (parent != null) {
                    List childern = parent.getChildrenList();
                    childern.add(element);
                }
            }

        }
        return roots;
    }
    
    public static List<IBasicDTO> buildTree(List<IBasicDTO> data,Long treeLevel) throws Exception {
        //List <IBasicDTO> treeRoots = new ArrayList();
        List roots = new ArrayList();
        Map allElement = new HashMap();
        String key = null;
        //Fill the map of Data Entry
        for (IBasicDTO element: data) {

            if (((ITreeDTO)element).getTreeLevel().equals(treeLevel)) {
                roots.add(element);
            }
            key = element.getCode().getKey();
            allElement.put(key, element);

            // multiple calls to build tree with the same list need to reinitialize it added by aboelhassan hamed
            if (((ITreeDTO)element).getChildrenList() != null) {
                ((ITreeDTO)element).getChildrenList().clear();
            }
        }
        //Build the Tree
        for (IBasicDTO element: data) {
            if (!((ITreeDTO)element).getTreeLevel().equals(treeLevel)) {
                try {
                    key = ((ITreeDTO)element).getParentCode().getKey();
                } catch (Exception e) {
                    System.out.println("((ITreeDTO)element).getCode().getKey()>>>>>>>>>>>           " + 
                                       element.getCode().getKey());
                }
                ITreeDTO parent = (ITreeDTO)allElement.get(key);

                ((ITreeDTO)element).setParentObject(parent);
                if (parent != null) {
                    List childern = parent.getChildrenList();
                    childern.add(element);
                }
            }

        }
        return roots;
    }
    
    /*public static IEntityKey getEntityKey(List<IBasicDTO> data,Object _key) throws Exception {
        Map allElement = new HashMap();
        Object key;
        for(IBasicDTO element:data) {
            key =  element.getCode().getKey();
            allElement.put(key,element);
        }
        IBasicDTO object = (IBasicDTO)allElement.get(_key.toString());
        return object.getCode();
    }*/

    /**
     *   this method responsible for find an elemnt in list 
     *  @param data list to search in an item inside it
     *  @param requiredKey  item to search inside the list
     *  @throws ItemNotFoundException if item not found in the list
     *  return the entityKey relevant to that item in the list
     * 
     * 
     **/
    public static IEntityKey getEntityKey(List<IBasicDTO> data, 
                                          String requiredKey) throws ItemNotFoundException, 
                                                                     Exception {
        IEntityKey entityKey = null;
        for (IBasicDTO element: data) {
            String key = element.getCode().getKey();
            if (key.equals(requiredKey)) {
                entityKey = element.getCode();
            } else if (element instanceof ITreeDTO){
                entityKey = _getEntityKey(((ITreeDTO)element).getChildrenList(), requiredKey);
            }
            if(entityKey != null){
                break;
            }
        }
        if(entityKey != null){
            return entityKey;
        }
        throw new ItemNotFoundException();
    }
    private static IEntityKey _getEntityKey(List<IBasicDTO> data, 
                                           String requiredKey){
       IEntityKey entityKey = null;
       if(data != null){
           for (IBasicDTO element: data) {
               String key = element.getCode().getKey();
               if (key.equals(requiredKey)) {
                   entityKey = element.getCode();
                } else if (element instanceof ITreeDTO){
                   entityKey = _getEntityKey(((ITreeDTO)element).getChildrenList(), requiredKey);
                }
               if(entityKey != null){
                   break;
               }
           }
       }
       return entityKey;
   }
    

}
