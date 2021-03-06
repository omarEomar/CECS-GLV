package com.beshara.csc.nl.reg.presentation.backingbean.type;

import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.nl.reg.business.client.ITypesClient;
import com.beshara.csc.nl.reg.business.client.IValiditiesClient;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.TypesDTO;
import com.beshara.jsfbase.csc.backingbean.LookUpBaseBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.jsfbase.csc.util.Helper;

import java.util.ArrayList;
import java.util.List;


public class TypeListBean extends LookUpBaseBean {
    IValiditiesClient validityClient;

    private List validitiesList = new ArrayList();

    private List validitiesListEdit = new ArrayList();

    private boolean validityMenuReadOnly = false;

    private Long bothTypesKEy = ISystemConstant.REGULATION_VALIDITY_ALL;

    private String validityKey = "";
    private String validityKeyAdd = "3";

    public TypeListBean() {

        setPageDTO(new TypesDTO()); //set this the page dto
        setSaveSortingState(true);

        //        ((TypesDTO)getPageDTO()).setValiditiesDTO(new ValiditiesDTO());

        //        ((TypesDTO)getPageDTO()).getValiditiesDTO().setCode(new EntityKey());

        super.setSelectedPageDTO(new TypesDTO());

        //        ((TypesDTO)super.getSelectedPageDTO()).setValiditiesDTO(new ValiditiesDTO());

        //        ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().setCode(new EntityKey());

        setClient((ITypesClient)RegClientFactory.getTypesClientForCenter());

        setAddDivTitleBindingString("#{regResources.type_AddDivTitle}");

        setEditDivTitleBindingString("#{regResources.type_EditDivTitle}");

        setDelAlertTitleBindingString("#{regResources.type_DelAlertTitle}");

        setDelConfirmTitleBindingString("#{regResources.type_DelConfirmTitle}");

        validityClient = RegClientFactory.getValiditiesClient();
        setDelConfirm("divSheardStyle1");
        setDelAlert("divSheardStyle1");

    }

    public void orderByCode() {
        sort("code.key");
    }

    public void orderByName() {
        sort("name");

    }

    public void orderByValidFor() {
        sort("validity_column");

    }

    public void showHideCode() {
        showHideColumn("code_column");
    }

    public void showHideName() {
        showHideColumn("name_column");
    }

    public void showHideValidFor() {
        showHideColumn("validity_column");
    }


    public void reInitializePageDTO() {
        setPageDTO(new TypesDTO());
    }

    public void setValiditiesList(List validitiesList) {
        this.validitiesList = validitiesList;
    }

    public List getValiditiesList() throws DataBaseException {
        validitiesList = validityClient.getCodeName();
        return validitiesList;
    }

    public void setValidityMenuReadOnly(boolean ValidityMenuReadOnly) {
        this.validityMenuReadOnly = ValidityMenuReadOnly;
    }

    //    public boolean isValidityMenuReadOnly() {
    //        System.out.println("bothTypesKEy" + bothTypesKEy);
    //        if (super.getSelectedPageDTO() != null && 
    //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO() != 
    //            null && 
    //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode() != 
    //            null && 
    //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode().getKey() != 
    //            null && 
    //            ((ValiditiesEntityKey)((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode()).getKey().toString().equals(bothTypesKEy)) {
    //            validityMenuReadOnly = true;
    //        }
    //
    //        return validityMenuReadOnly;
    //
    //    }

    public void setValiditiesListEdit(List validitiesListEdit) {
        this.validitiesListEdit = validitiesListEdit;
    }

    public void edit() throws DataBaseException, ItemNotFoundException, 
                              SharedApplicationException {
        IEntityKey validityEntityKey = new EntityKey();
        try {
            validityEntityKey = 
                    Helper.getEntityKey(validitiesList, validityKey);
            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().setCode(validityEntityKey);
            super.edit();
        } catch (Exception e) {
            // TODO
        }finally {
            unlock();
        }
        

    }

    /**
     * @return List<validitiesDTO> 
     * these list hold the values of validfor of typesDTO incase of having specific list for edit
     * */
    public List getValiditiesListEdit() throws DataBaseException {
        validitiesListEdit = validityClient.getCodeName();
        return validitiesListEdit;
        //         ValiditiesEntityKey validityEntityKey=new ValiditiesEntityKey();
        //          ValiditiesDTO  validityDTO=new ValiditiesDTO();
        //          validitiesListEdit=validityClient.getCodeName(); 
        //        if (super.getSelectedPageDTO() != null && 
        //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO() != 
        //            null && 
        //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode() != 
        //            null && 
        //            ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode().getKey() != 
        //            null ) 
        //            if(((ValiditiesEntityKey)((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode()).getKey().toString().equals(bothTypesKEy)){
        //                validityEntityKey.setKey(bothTypesKEy);
        //                validitiesListEdit.clear();
        //                try {
        //                    validityClient.getById(validityEntityKey)
        //                } catch (SharedApplicationException e) {
        //                    // TODO
        //                }
        //                validitiesListEdit.add()
        //            }else{
        //            
        //            }
        //            
        //        }

    }

    public void setValidityKey(String validityKey) {
        this.validityKey = validityKey;
    }

    public String getValidityKey() {
        try {
            if (validityKey != null && super.getSelectedPageDTO() != null && 
                ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO() != 
                null && 
                ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode() != 
                null && 
                ((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode().getKey() != 
                null) {
                validityKey = 
                        (((TypesDTO)super.getSelectedPageDTO()).getValiditiesDTO().getCode().getKey()).toString();
                System.out.println("validityKey------------------**********************-------------------" + 
                                   validityKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validityKey;
    }

    //    public void search() throws DataBaseException, NoResultException {
    //        if (this.getSearchType().intValue() == 0)
    //            super.setSearchEntityObj(new Long(this.getSearchQuery()));
    //        super.search();
    //    }

    public void setValidityKeyAdd(String validityKeyAdd) {
        this.validityKeyAdd = validityKeyAdd;
    }

    public String getValidityKeyAdd() {
        return validityKeyAdd;
    }

    public void save() {
        ((TypesDTO)getPageDTO()).setValidityKey(validityKeyAdd);
        validityKeyAdd = "3";
        super.save();
    }

    public void saveAndNew() throws DataBaseException {
        ((TypesDTO)getPageDTO()).setValidityKey(validityKeyAdd);
        validityKeyAdd = "3";
        super.saveAndNew();
    }

    public void cancelAdd() {
        validityKeyAdd = bothTypesKEy.toString();
        super.cancelAdd();
    }

    /**
     * Purpose: this method is to set the setSearchEntityObj with the actual value
     * Creation/Modification History :
     * 1.1 - Developer Name:  Amir Mosad
     * 1.2 - Date:  19-05-2009
     * 1.3 - Creation/Modification:Creation
     * 1.4-  Description:
     */
    public void search() throws DataBaseException, NoResultException {

        try {
            if (getSearchType().intValue() == 0)
                super.setSearchEntityObj(new Long(this.getSearchQuery()));
            super.search();
        } catch (Exception e) {
            e.printStackTrace();
            setSearchMode(true);
            setMyTableData(new ArrayList());
        }

    }
}
