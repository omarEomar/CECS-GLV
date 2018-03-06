package com.beshara.csc.inf.business.integration.eservices.infkwcitizen;


import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.eservice.emp.request.validation.KwtCitizensResidentsEserviceDTO;
import com.beshara.eservice.workflow.humantask.validation.IBaseTaskData;
import com.beshara.eservice.workflow.humantask.validation.IBaseTaskValidator;

import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;


@Stateless(name = "EmpChangeInfoProcessValidationCallBackService",
           mappedName = "EmpChangeInfoProcessValidationCallBackService")
@Remote(IBaseTaskValidator.class)
public class EmpChangeInfoProcessValidationCallBackService implements IBaseTaskValidator {

    @EJB
    private IKwtCitizensResidentsEservice KwtCitizensResidentsEserviceImp;

    public EmpChangeInfoProcessValidationCallBackService() {
        super();
    }

    @Override
    public List<String> validateTaskOperation(String action, String userName, IBaseTaskData iBaseTaskData,
                                              Locale locale, List<String> list) {
        KwtCitizensResidentsEserviceDTO task = (KwtCitizensResidentsEserviceDTO)iBaseTaskData;
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaavalidateTaskOperation" + action);

        if (list == null || list.size() == 0) {
            if (action.equals("Finish")) {
                try {
                    KwtCitizensResidentsEserviceImp.update(task);
                } catch (DataBaseException e) {
                    list.add(" <<ValidationCallBack " + e.getMessage() + "<<ValidationCallBack");
                } catch (SharedApplicationException e) {
                    list.add(" <<ValidationCallBack " + e.getMessage() + "<<ValidationCallBack");
                }
            }
        }
        System.out.println("lllllllllllllllllllllllllllllllllllllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                           list.size());
        return list;
    }
}
