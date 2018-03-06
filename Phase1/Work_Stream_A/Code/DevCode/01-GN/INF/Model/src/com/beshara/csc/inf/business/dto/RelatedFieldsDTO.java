package com.beshara.csc.inf.business.dto;
import com.beshara.csc.inf.business.dto.FieldsDTO;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntity;
import com.beshara.csc.inf.business.entity.RelatedFieldsEntityKey;
import java.sql.Timestamp;
public class RelatedFieldsDTO extends InfDTO implements IRelatedFieldsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long tabrecSerial;
    private Long referOrder;
    private IFieldsDTO fieldsDTO;
    private IFieldsDTO fieldsReferencedDTO;
    public RelatedFieldsDTO() {    }    public RelatedFieldsDTO(RelatedFieldsEntity entity) {        setCode(new RelatedFieldsEntityKey(entity.getFieldEntity().getFldCode(),                                            entity.getFieldReferencedEntity().getFldCode()));
        setReferOrder(entity.getReferOrder());
        setFieldsDTO(new FieldsDTO(entity.getFieldEntity()));
        setFieldsReferencedDTO(new FieldsDTO(entity.getFieldReferencedEntity()));
        setAuditStatus(entity.getAuditStatus());
        setTabrecSerial(entity.getTabrecSerial());
        setCreatedBy(entity.getCreatedBy());
        setCreatedDate(entity.getCreatedDate());
        setLastUpdatedBy(entity.getLastUpdatedBy());
        setLastUpdatedDate(entity.getLastUpdatedDate());
    }    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    public Long getAuditStatus() {        return auditStatus;
    }    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public Long getTabrecSerial() {        return tabrecSerial;
    }    public void setReferOrder(Long referOrder) {        this.referOrder = referOrder;
    }    public Long getReferOrder() {        return referOrder;
    }    public void setFieldsDTO(IFieldsDTO fieldsDTO) {        this.fieldsDTO = fieldsDTO;
    }    public IFieldsDTO getFieldsDTO() {        return fieldsDTO;
    }    public void setFieldsReferencedDTO(IFieldsDTO fieldsReferencedDTO) {        this.fieldsReferencedDTO = fieldsReferencedDTO;
    }    public IFieldsDTO getFieldsReferencedDTO() {        return fieldsReferencedDTO;
    }}
