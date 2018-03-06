package com.beshara.csc.inf.business.dto;


import com.beshara.csc.inf.business.entity.InfEservicesTypesEntity;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;

import javax.persistence.Column;


public class InfEservicesTypesDTO extends InfDTO implements IInfEservicesTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long servicesId; 
    private String servicesName;
    private Long tabrecSerial;

    public InfEservicesTypesDTO() {
        super();
    }

    public  InfEservicesTypesDTO(Long servicesId, String servicesName) {
        this.setCode(InfEntityKeyFactory.createInfEservicesTypesEntityKey(servicesId));
        this.setName(servicesName);
    }

    public InfEservicesTypesDTO(InfEservicesTypesEntity documentTypesEntity) {
        setCode(InfEntityKeyFactory.createInfEservicesTypesEntityKey(documentTypesEntity.getServicesId()));
        setName(documentTypesEntity.getServicesName());
        this.tabrecSerial = documentTypesEntity.getTabrecSerial();
    }

   
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesName() {
        return servicesName;
    }
}
