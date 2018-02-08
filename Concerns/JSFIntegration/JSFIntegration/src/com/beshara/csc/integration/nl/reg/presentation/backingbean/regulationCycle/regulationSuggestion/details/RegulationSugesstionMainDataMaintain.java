package com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.regulationSuggestion.details;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IREGRegulationMaterialsDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationSearchDTO;
import com.beshara.csc.nl.reg.business.dto.IRegulationsDTO;
import com.beshara.csc.nl.reg.business.dto.ITypesDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.EditorBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.RegulationCycleMaintainBean;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RegulationMainDataMaintain;
import com.beshara.csc.nl.reg.presentation.backingbean.regulationCycle.abstractDetails.RegulationMaterialsMaintain;
import com.beshara.csc.sharedutils.business.util.SharedUtils;


public class RegulationSugesstionMainDataMaintain extends RegulationMainDataMaintain {
    
    public RegulationSugesstionMainDataMaintain() {
        RegulationCycleMaintainBean.getInstance().setFinishNavigationCase("sugesstionregulationlist");
    }
   
    private boolean isValidRegulationKey() {
        boolean validKey = true;
//        RegulationCycleMaintainBean regulationMaintainBean = RegulationCycleMaintainBean.getInstance();
//        boolean addMode = regulationMaintainBean.getMaintainMode() == 0;
//        if(addMode || regulationMaintainBean.isCopyFlage() ){
//            IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
//            IRegulationSearchDTO regulationSearchDTO = RegDTOFactory.createRegulationSearchDTO();
//            regulationSearchDTO.setNumber(regulationsDTO.getRegulationNumber());
//            regulationSearchDTO.setRegulationType(new Long(regulationsDTO.getTypesDTOKey()));
//            regulationSearchDTO.setRegulationYear(new Long(regulationsDTO.getYearsDTOKey()));
//            
//            try {
//                RegClientFactory.getRegulationsClient().searchCenter(regulationSearchDTO);
//                validKey = false;
//                setInvalidRegulationKey(true);
//            } catch (Exception e) {
//                System.err.println("==================== ID IS READY TO USE ====================");
//                }
//            if(validKey){
//                try{
//                    String typeKey = regulationsDTO.getTypesDTOKey();
//                    for (IBasicDTO type: getTypes()) {
//                        if (type.getCode().getKey().equals(typeKey)) {
//                            ITypesDTO typesDto = (ITypesDTO)type;
//                            regulationsDTO.setTypesDto(typesDto);
//                            break;
//                        }
//                    }                 
//                    String yearKey = regulationsDTO.getYearsDTOKey();
//                    for (IBasicDTO year: getIssuanceYears()) {
//                        if (year.getCode().getKey().equals(yearKey)) {
//                            IYearsDTO yearsDto = (IYearsDTO)year;
//                            regulationsDTO.setYearsDto(yearsDto);
//                            break;
//                        }
//                    }
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
        return validKey;
    }
    public String returnEditorAction()
    {
    return "regulationcyclemaindata";
    }
    
    public String openEditor()
    {
        RegulationCycleMaintainBean regulationMaintainBean = RegulationCycleMaintainBean.getInstance();
        IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
        EditorBean editorBean = (EditorBean)evaluateValueBinding("editorCycleBean");
        editorBean.setData(regulationsDTO.getRegulationText());
       return "goToEditorCycleList";
    }
    public void addMaterialInEditorView()
    {
        RegulationCycleMaintainBean regulationMaintainBean = RegulationCycleMaintainBean.getInstance();
        RegulationMaterialsMaintain regulationMaterialsMaintainBean =(RegulationMaterialsMaintain)evaluateValueBinding("regulationCycleMaterialsMaintainBean");
        IRegulationsDTO regulationsDTO = (IRegulationsDTO)regulationMaintainBean.getPageDTO();
        String material="";
        for(int i=0 ; i<regulationMaterialsMaintainBean.getCurrentDetails().size();i++)
        {
            if(regulationMaterialsMaintainBean.getCurrentDetails().get(i).getStatusFlag()!=null)
            {            
                if(regulationMaterialsMaintainBean.getCurrentDetails().get(i).getStatusFlag()!=1L)
                {
                material=material+ "<p><span style=font-size:small><strong>"+((IREGRegulationMaterialsDTO)regulationMaterialsMaintainBean.getCurrentDetails().get(i)).getRegmaterialText()+"</span></strong></p>";
                }
                else
                {
                material="";
                }
            }
            else
            {
                material=material+ "<p><span style=font-size:small><strong>"+((IREGRegulationMaterialsDTO)regulationMaterialsMaintainBean.getCurrentDetails().get(i)).getRegmaterialText()+"</span></strong></p>";
            }
        }
      
        if(material.equals(""))
        {
        getSharedUtil().handleException(new Exception(),"com.beshara.csc.nl.reg.presentation.resources.reg","noMaterialErrorMsg");
        //showMatrialMsg=true;
        }
        else
        {
        regulationsDTO.setRegulationText(material);
        //showMatrialMsg=false;
        }
    }
    
}
