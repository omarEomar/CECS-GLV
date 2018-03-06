package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.SpecialPeriodsDTO;
import com.beshara.csc.inf.business.entity.SpecialPeriodTypesEntity;
import com.beshara.csc.inf.business.entity.SpecialPeriodTypesEntityKey;
import com.beshara.csc.inf.business.entity.SpecialPeriodsEntity;
import com.beshara.csc.inf.business.entity.SpecialPeriodsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class SpecialPeriodsDAO extends InfBaseDAO{
    public SpecialPeriodsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new SpecialPeriodsDAO();
    }
    public List<IBasicDTO> getAllByTypeAndMinCode(BasicDTO dtoPrm) throws DataBaseException, SharedApplicationException {
        try {
              SpecialPeriodsDTO dto = (SpecialPeriodsDTO)dtoPrm;
              ArrayList arrayList = new ArrayList();     
              Query query = EM().createNamedQuery("SpecialPeriodsEntity.getAllByTypeAndMinCode");
              query.setParameter("minCode", dto.getMinCode());
              query.setParameter("spcprdtypeCode", dto.getSpcprdtypeCode());
              List<SpecialPeriodsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            
            for (SpecialPeriodsEntity specialPeriod : list) {
                arrayList.add(InfEntityConverter.getSpecialPeriodsDTO(specialPeriod));
            }
            return arrayList;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public List<IBasicDTO> getAllByTypeAndMinCodeAndYear(BasicDTO dtoPrm) throws DataBaseException, SharedApplicationException {
        try {
              SpecialPeriodsDTO dto = (SpecialPeriodsDTO)dtoPrm;
              ArrayList arrayList = new ArrayList();     
              Query query = EM().createNamedQuery("SpecialPeriodsEntity.getAllByTypeAndMinCodeAndYear");
              query.setParameter("minCode", dto.getMinCode());
              query.setParameter("spcprdtypeCode", dto.getSpcprdtypeCode());
              query.setParameter("yearCode", dto.getYearCode());
              List<SpecialPeriodsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            
            for (SpecialPeriodsEntity specialPeriod : list) {
                arrayList.add(InfEntityConverter.getSpecialPeriodsDTO(specialPeriod));
            }
            return arrayList;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("SpecialPeriodsEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(0);
            } else {
                return id + 1L;
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    @Override
    public SpecialPeriodsDTO add(IBasicDTO dtoParm) throws DataBaseException, SharedApplicationException {
        try {
            Long code = findNewId();
            SpecialPeriodsDTO dto = (SpecialPeriodsDTO)dtoParm;
            SpecialPeriodsEntity specialPeriodsEntity = new SpecialPeriodsEntity();
            specialPeriodsEntity.setSerial(code);
            specialPeriodsEntity.setYearCode(dto.getYearCode());
            specialPeriodsEntity.setPeriodDesc(dto.getPeriodDesc());
            specialPeriodsEntity.setPrgCode(dto.getPrgCode());
            specialPeriodsEntity.setMinCode(dto.getMinCode());
            specialPeriodsEntity.setFromDate(dto.getFromDate());
            specialPeriodsEntity.setUntilDate(dto.getUntilDate());
            specialPeriodsEntity.setStatus(dto.getStatus());
            if(dto.getSpcprdtypeCode() != null){
                    SpecialPeriodTypesEntity specialPeriodTypesEntity =
                        EM().find(SpecialPeriodTypesEntity.class, new SpecialPeriodTypesEntityKey(dto.getSpcprdtypeCode()));
                    if (specialPeriodTypesEntity != null) {
                        specialPeriodsEntity.setSpecialPeriodTypesEntity(specialPeriodTypesEntity);
                    } else {
                        throw new ItemNotFoundException();
                    }
                }
            
            doAdd(specialPeriodsEntity);
            dto.setCode(new SpecialPeriodsEntityKey(code));
            dto.setSerial(code);
            return dto;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    @Override
    public Boolean update(IBasicDTO dtoParm) throws DataBaseException, SharedApplicationException {
        try {
            SpecialPeriodsDTO dto = (SpecialPeriodsDTO)dtoParm;
            
            SpecialPeriodsEntity specialPeriodsEntity = EM().find(SpecialPeriodsEntity.class, dto.getCode());
            if (specialPeriodsEntity == null) {
                throw new ItemNotFoundException();
            }
            specialPeriodsEntity.setSerial(dto.getSerial());
            specialPeriodsEntity.setYearCode(dto.getYearCode());
            specialPeriodsEntity.setPeriodDesc(dto.getPeriodDesc());
            specialPeriodsEntity.setPrgCode(dto.getPrgCode());
            specialPeriodsEntity.setMinCode(dto.getMinCode());
            specialPeriodsEntity.setFromDate(dto.getFromDate());
            specialPeriodsEntity.setUntilDate(dto.getUntilDate());
            specialPeriodsEntity.setStatus(dto.getStatus());
            if(dto.getSpcprdtypeCode() != null){
                    SpecialPeriodTypesEntity specialPeriodTypesEntity =
                        EM().find(SpecialPeriodTypesEntity.class, new SpecialPeriodTypesEntityKey(dto.getSpcprdtypeCode()));
                    if (specialPeriodTypesEntity != null) {
                        specialPeriodsEntity.setSpecialPeriodTypesEntity(specialPeriodTypesEntity);
                    } else {
                        throw new ItemNotFoundException();
                    }
                }
            doUpdate(specialPeriodsEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
}
