package com.beshara.csc.inf.business.dao;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.dao.EntityConverterUtils;
import com.beshara.csc.flm.flm.business.client.IFileClient;
import com.beshara.csc.flm.flm.business.dto.FileDTO;
import com.beshara.csc.flm.flm.business.entity.FileEntityKey;
import com.beshara.csc.gn.inf.business.dto.IInfOldDataAuditDTO;
import com.beshara.csc.gn.inf.business.dto.InfOldDataAuditDTO;
import com.beshara.csc.gn.inf.business.entity.InfOldDataAuditEntity;
import com.beshara.csc.hr.bgt.business.dto.BgtProgramsDTO;
import com.beshara.csc.inf.business.dto.IInfMobileCompaniesDTO;
import com.beshara.csc.inf.business.dto.IInfTmpDataDTO;
import com.beshara.csc.inf.business.dto.IInfTmpDataDisksDTO;
import com.beshara.csc.inf.business.dto.IInfTmpDataFieldsDTO;
import com.beshara.csc.inf.business.dto.IInfTmpDataTypesDTO;
import com.beshara.csc.inf.business.dto.IInfTmpMigrCasesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.IYearsDTO;
import com.beshara.csc.inf.business.dto.IdentificationTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.InfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.InfPresentationChannelDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.PersonDocAtchTypesDTO;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.dto.PersonDocumntsDTO;
import com.beshara.csc.inf.business.dto.PersonQualificationsDTO;
import com.beshara.csc.inf.business.dto.PersonsInformationDTO;
import com.beshara.csc.inf.business.dto.SpecialPeriodsDTO;
import com.beshara.csc.inf.business.dto.UnitsOfMeasureDTO;
import com.beshara.csc.inf.business.dto.holidays.HolidaysSimpleDTO;
import com.beshara.csc.inf.business.dto.holidays.IHolidaysSimpleDTO;
import com.beshara.csc.inf.business.entity.HolidaysEntity;
import com.beshara.csc.inf.business.entity.HolidaysEntityKey;
import com.beshara.csc.inf.business.entity.IInfPresentationChannelEntityKey;
import com.beshara.csc.inf.business.entity.IInfTmpDataDisksEntityKey;
import com.beshara.csc.inf.business.entity.IInfTmpDataEntityKey;
import com.beshara.csc.inf.business.entity.IInfTmpDataFieldsEntityKey;
import com.beshara.csc.inf.business.entity.IInfTmpDataTypesEntityKey;
import com.beshara.csc.inf.business.entity.IInfTmpMigrCasesEntityKey;
import com.beshara.csc.inf.business.entity.IPersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.IPersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.ISpecialPeriodsEntityKey;
import com.beshara.csc.inf.business.entity.IUnitsOfMeasureEntityKey;
import com.beshara.csc.inf.business.entity.IdentificationTypesEntity;
import com.beshara.csc.inf.business.entity.IdentificationTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfMobileCompaniesEntity;
import com.beshara.csc.inf.business.entity.InfPresentationChannelEntity;
import com.beshara.csc.inf.business.entity.InfPresentationChannelEntityKey;
import com.beshara.csc.inf.business.entity.InfTmpDataDisksEntity;
import com.beshara.csc.inf.business.entity.InfTmpDataEntity;
import com.beshara.csc.inf.business.entity.InfTmpDataFieldsEntity;
import com.beshara.csc.inf.business.entity.InfTmpDataTypesEntity;
import com.beshara.csc.inf.business.entity.InfTmpMigrCasesEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntity;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntity;
import com.beshara.csc.inf.business.entity.PersonDocAtchTypesEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocAttachemntsEntityKey;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntity;
import com.beshara.csc.inf.business.entity.PersonDocumntsEntityKey;
import com.beshara.csc.inf.business.entity.PersonQualificationsEntity;
import com.beshara.csc.inf.business.entity.PersonsInformationEntity;
import com.beshara.csc.inf.business.entity.SpecialPeriodsEntity;
import com.beshara.csc.inf.business.entity.SpecialPeriodsEntityKey;
import com.beshara.csc.inf.business.entity.UnitsOfMeasureEntity;
import com.beshara.csc.inf.business.entity.UnitsOfMeasureEntityKey;
import com.beshara.csc.inf.business.entity.YearsEntity;
import com.beshara.csc.inf.business.entity.YearsEntityKey;
import com.beshara.csc.inf.business.facade.InfEntityConvertr;
import com.beshara.csc.nl.qul.business.client.QulClientFactory;
import com.beshara.csc.nl.qul.business.dto.ICenterQualificationsDTO;
import com.beshara.csc.nl.qul.business.dto.IQualificationsDTO;
import com.beshara.csc.nl.qul.business.entity.ICenterQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.IQualificationsEntityKey;
import com.beshara.csc.nl.qul.business.entity.QulEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Timestamp;

public class InfEntityConverter {
    public InfEntityConverter() {
        super();
    }

    public static PersonDocumntsDTO getPersonDocumntsDTO(PersonDocumntsEntity ent) throws DataBaseException,
                                                                                          SharedApplicationException {
        PersonDocumntsDTO dto = new PersonDocumntsDTO();
        fillPersonDocumntsDTO(dto, ent);
        return dto;
    }

    public static void fillPersonDocumntsDTO(PersonDocumntsDTO dto, PersonDocumntsEntity ent) throws DataBaseException,
                                                                                                     SharedApplicationException {

        PersonDocumntsEntityKey ek =
            new PersonDocumntsEntityKey(ent.getKwtCitizensResidentsEntity().getCivilId(), ent.getEmpDocSerial());
        dto.setCode(ek);
        dto.setDoctypeCode(ent.getDoctypeCode());
        dto.setComments(ent.getComments());
        dto.setReferenceTableName(ent.getReferenceTableName());
        dto.setReferenceTableSerial(ent.getReferenceTableSerial());

        dto.setKwtCitizensResidentsDTO(InfDTOFactory.createKwtCitizensResidentsDTO(ent.getKwtCitizensResidentsEntity()));
        dto.setInfDocumentTypesDTO((InfDocumentTypesDTO)InfDTOFactory.createInfDocumentTypesDTO(ent.getInfDocumentTypesEntity()));
        dto.setStatus(ent.getStatus());

        EntityConverterUtils.fillSharedInfo(ent, dto);
    }

    public static PersonDocAttachemntsDTO getPersonDocAttachemntsDTO(PersonDocAttachemntsEntity ent) throws DataBaseException,
                                                                                                            SharedApplicationException {
        PersonDocAttachemntsDTO dto = new PersonDocAttachemntsDTO();
        fillPersonDocAttachemntsDTO(dto, ent);
        return dto;
    }

    public static void fillPersonDocAttachemntsDTO(PersonDocAttachemntsDTO dto,
                                                   PersonDocAttachemntsEntity ent) throws DataBaseException,
                                                                                          SharedApplicationException {

        IPersonDocAttachemntsEntityKey ek =
            new PersonDocAttachemntsEntityKey(ent.getCivilId(), ent.getEmpDocSerial(), ent.getSerial());
        dto.setCode(ek);
        dto.setAttachmentDate(ent.getAttachmentDate());
        dto.setDocAtcType(ent.getDocAtcType());
        dto.setAttachmentDesc(ent.getAttachmentDesc());

        String fileId = ent.getFileId();
        if (fileId != null) {
            IFileClient fileClient = ClientFactoryUtil.getInstance(IFileClient.class);
            FileEntityKey fileEK = new FileEntityKey(fileId);
            FileDTO file = (FileDTO)fileClient.getById(fileEK);
            dto.setFile(file);
        }

        dto.setValidFrom(ent.getValidFrom());
        dto.setValidUntil(ent.getValidUntil());
        dto.setPersonDocumntsDTO(new PersonDocumntsDTO(ent.getPersonDocumntsEntity()));
        dto.setPersonDocAtchTypesDTO(new PersonDocAtchTypesDTO(ent.getPersonDocAtchTypesEntity()));
        dto.setStatus(ent.getStatus());
        dto.setCivilId(ent.getCivilId());
        EntityConverterUtils.fillSharedInfo(ent, dto);
    }

    public static PersonDocAtchTypesDTO getPersonDocAtchTypesDTO(PersonDocAtchTypesEntity ent) throws DataBaseException,
                                                                                                      SharedApplicationException {
        PersonDocAtchTypesDTO dto = new PersonDocAtchTypesDTO();
        fillPersonDocAtchTypesDTO(dto, ent);
        return dto;
    }

    public static void fillPersonDocAtchTypesDTO(PersonDocAtchTypesDTO dto,
                                                 PersonDocAtchTypesEntity ent) throws DataBaseException,
                                                                                      SharedApplicationException {

        IPersonDocAtchTypesEntityKey ek = new PersonDocAtchTypesEntityKey(ent.getDocAtcTypeCode());
        dto.setCode(ek);
        dto.setDocAtcTypeName(ent.getDocAtcTypeName());
        EntityConverterUtils.fillSharedInfo(ent, dto);
    }

    public static SpecialPeriodsDTO getSpecialPeriodsDTO(SpecialPeriodsEntity ent) throws DataBaseException,
                                                                                          SharedApplicationException {
        SpecialPeriodsDTO dto = new SpecialPeriodsDTO();
        fillSpecialPeriodsDTO(dto, ent);
        return dto;
    }

    public static void fillSpecialPeriodsDTO(SpecialPeriodsDTO dto, SpecialPeriodsEntity ent) throws DataBaseException,
                                                                                                     SharedApplicationException {

        ISpecialPeriodsEntityKey ek = new SpecialPeriodsEntityKey(ent.getSerial());
        dto.setCode(ek);
        dto.setSerial(ent.getSerial());
        dto.setStatus(ent.getStatus());
        dto.setPrgCode(ent.getPrgCode());
        dto.setPeriodDesc(ent.getPeriodDesc());
        dto.setFromDate(ent.getFromDate());
        dto.setUntilDate(ent.getUntilDate());
        dto.setYearCode(ent.getYearCode());
        dto.setMinCode(ent.getMinCode());
        dto.setSpcprdtypeCode(ent.getSpcprdtypeCode());
        BgtProgramsDTO bgtProgramsDTO = new BgtProgramsDTO();
        dto.setBgtProgramsDTO(bgtProgramsDTO);
        if (ent.getBgtProgramsEntity() != null) {
            bgtProgramsDTO.setName(ent.getBgtProgramsEntity().getPrgName());
        }
        EntityConverterUtils.fillSharedInfo(ent, dto);
    }

    public static IYearsDTO getYearsDTO(YearsEntity yearsEntity) {
        IYearsDTO dto = InfDTOFactory.createYearsDTO();
        fillYearsDTO(dto, yearsEntity);
        return dto;

    }

    public static void fillYearsDTO(IYearsDTO dto, YearsEntity yearsEntity) {
        dto.setCode(new YearsEntityKey(yearsEntity.getYearCode()));
        dto.setName(yearsEntity.getYearName());
        dto.setYearCode(yearsEntity.getYearCode());
        dto.setYearName(yearsEntity.getYearName());
        dto.setBudgetEndDate(yearsEntity.getBudgetEndDate());
        dto.setBudgetStartDate(yearsEntity.getBudgetStartDate());
        dto.setEndDate(getNextDay(yearsEntity.getEndDate()));
        dto.setStartDate(getNextDay(yearsEntity.getStartDate()));
    }

    private static Timestamp getNextDay(Timestamp ts) {
        long oneDay = 1 * 24 * 60 * 60 * 1000;
        // to add to the timestamp
        ts.setTime(ts.getTime() + oneDay);
        return ts;
    }


    public static UnitsOfMeasureDTO getUnitsOfMeasureDTO(UnitsOfMeasureEntity ent) throws DataBaseException,
                                                                                          SharedApplicationException {
        UnitsOfMeasureDTO dto = new UnitsOfMeasureDTO();
        fillUnitsOfMeasureDTO(dto, ent);
        return dto;
    }

    public static void fillUnitsOfMeasureDTO(UnitsOfMeasureDTO dto, UnitsOfMeasureEntity ent) throws DataBaseException,
                                                                                                     SharedApplicationException {

        IUnitsOfMeasureEntityKey ek = new UnitsOfMeasureEntityKey(ent.getUnitCode());
        dto.setCode(ek);
        dto.setName(ent.getUntitArabicName());
        dto.setUnitCode(ent.getUnitCode());
        dto.setUntitArabicName(ent.getUntitArabicName());
        dto.setUntitEnglishName(ent.getUntitEnglishName());
        dto.setDefaultUnit(ent.getDefaultUnit());
        dto.setUnitUse(ent.getUnitUse());
        dto.setConvertFunToDefault(ent.getConvertFunToDefault());

        EntityConverterUtils.fillSharedInfo(ent, dto);
    }

    public static InfPresentationChannelDTO getInfPresentationChannelDTO(InfPresentationChannelEntity ent) throws DataBaseException,
                                                                                                                  SharedApplicationException {
        InfPresentationChannelDTO dto = new InfPresentationChannelDTO();
        fillInfPresentationChannelDTO(dto, ent);
        return dto;
    }

    public static void fillInfPresentationChannelDTO(InfPresentationChannelDTO dto,
                                                     InfPresentationChannelEntity ent) throws DataBaseException,
                                                                                              SharedApplicationException {

        IInfPresentationChannelEntityKey ek = new InfPresentationChannelEntityKey(ent.getChannelId());
        dto.setCode(ek);
        dto.setName(ent.getName());
        dto.setChannelNameEn(ent.getChannelNameEn());

        EntityConverterUtils.fillSharedInfo(ent, dto);
    }
    
    public static IHolidaysSimpleDTO getHolidaysSimpleDTO(HolidaysEntity ent) throws DataBaseException,
                                                                                              SharedApplicationException {
            IHolidaysSimpleDTO dto = new HolidaysSimpleDTO();
            fillHolidaysSimpleDTO(dto, ent);
            return dto;
        }

        public static void fillHolidaysSimpleDTO(IHolidaysSimpleDTO dto, HolidaysEntity ent) throws DataBaseException,
                                                                                                         SharedApplicationException {

            
            dto.setCode(new HolidaysEntityKey(ent.getYearsEntity().getYearCode(),
                                          ent.getHolidayTypesEntity().getHoltypeCode(),
                                          ent.getFromDate()));
            dto.setFromDate(ent.getFromDate());
            dto.setUntilDate(ent.getUntilDate());
            dto.setStatus(ent.getStatus());
            dto.setHolidayDesc(ent.getHolidayDescribtion());
        }
    
    public static IInfMobileCompaniesDTO getInfMobileCompaniesDTO(InfMobileCompaniesEntity infMobileCompanies) {
        IInfMobileCompaniesDTO dto = InfDTOFactory.createInfMobileCompaniesDTO();
        fillInfMobileCompaniesDTO(dto, infMobileCompanies);
        return dto;
    }

    private static void fillInfMobileCompaniesDTO(IInfMobileCompaniesDTO dto,
                                                  InfMobileCompaniesEntity infMobileCompanies) {
        dto.setCode(InfEntityKeyFactory.createInfMobileCompaniesEntityKey(infMobileCompanies.getMobCompanyCode()));
        dto.setName(infMobileCompanies.getMobCompanyName());
        EntityConverterUtils.fillSharedInfo(infMobileCompanies, dto);
    }
    
    public static IInfMobileCompaniesDTO getInfMobileCompaniesDTO(Long mobCompanyCode, String mobCompanyName) {
        IInfMobileCompaniesDTO dto = InfDTOFactory.createInfMobileCompaniesDTO();
        fillInfMobileCompaniesDTO(dto, mobCompanyCode, mobCompanyName);
        return dto;
    }

    private static void fillInfMobileCompaniesDTO(IInfMobileCompaniesDTO dto,
                                                  Long mobCompanyCode, String mobCompanyName) {
        dto.setCode(InfEntityKeyFactory.createInfMobileCompaniesEntityKey(mobCompanyCode));
        dto.setName(mobCompanyName);
    }

    public static IInfTmpDataTypesDTO getInfTmpDataTypesDTO(InfTmpDataTypesEntity infTmpDataTypesEntity) throws DataBaseException,
                                                                                                                SharedApplicationException {

        IInfTmpDataTypesDTO dto = InfDTOFactory.createInfTmpDataTypesDTO();
        fillInfTmpDataTypesDTO(dto, infTmpDataTypesEntity);
        return dto;
    }

    public static void fillInfTmpDataTypesDTO(IInfTmpDataTypesDTO dto, InfTmpDataTypesEntity infTmpDataTypesEntity) {

        IInfTmpDataTypesEntityKey ek =
            InfEntityKeyFactory.createInfTmpDataTypesEntityKey(infTmpDataTypesEntity.getDatatypCode());

        dto.setCode(ek);
        dto.setSocCode(infTmpDataTypesEntity.getSocCode());
        dto.setDatatypCode(infTmpDataTypesEntity.getDatatypCode());
        dto.setName(infTmpDataTypesEntity.getName());
        dto.setWsUser(infTmpDataTypesEntity.getWsUser());
        dto.setWsPass(infTmpDataTypesEntity.getWsPass());
        dto.setWsDisk(infTmpDataTypesEntity.getWsDisk());
        dto.setWsUrl(infTmpDataTypesEntity.getWsUrl());


    }

    public static IInfTmpDataFieldsDTO getInfTmpDataFieldsDTO(InfTmpDataFieldsEntity infTmpDataFieldsEntity) throws DataBaseException,
                                                                                                                    SharedApplicationException {

        IInfTmpDataFieldsDTO dto = InfDTOFactory.createInfTmpDataFieldsDTO();
        fillInfTmpDataFieldsDTO(dto, infTmpDataFieldsEntity);
        return dto;
    }

    public static void fillInfTmpDataFieldsDTO(IInfTmpDataFieldsDTO dto,
                                               InfTmpDataFieldsEntity infTmpDataFieldsEntity) {

        IInfTmpDataFieldsEntityKey ek =
            InfEntityKeyFactory.createInfTmpDataFieldsEntityKey(infTmpDataFieldsEntity.getDatatypCode(),
                                                                infTmpDataFieldsEntity.getFieldOrder());

        dto.setCode(ek);
        dto.setDatatypCode(infTmpDataFieldsEntity.getDatatypCode());
        dto.setFieldOrder(infTmpDataFieldsEntity.getFieldOrder());
        dto.setName(infTmpDataFieldsEntity.getName());
        dto.setVariableName(infTmpDataFieldsEntity.getVariableName());
        dto.setFieldLabel(infTmpDataFieldsEntity.getFieldLabel());
        dto.setFldtypeCode(infTmpDataFieldsEntity.getFldtypeCode());
        dto.setNewFieldOrder(infTmpDataFieldsEntity.getFieldOrder());

    }

    public static IInfTmpMigrCasesDTO getInfTmpMigrCasesDTO(InfTmpMigrCasesEntity infTmpMigrCasesEntity) throws DataBaseException,
                                                                                                                SharedApplicationException {

        IInfTmpMigrCasesDTO dto = InfDTOFactory.createInfTmpMigrCasesDTO();
        fillInfTmpMigrCasesDTO(dto, infTmpMigrCasesEntity);
        return dto;
    }

    public static void fillInfTmpMigrCasesDTO(IInfTmpMigrCasesDTO dto, InfTmpMigrCasesEntity infTmpMigrCasesEntity) {

        IInfTmpMigrCasesEntityKey ek =
            InfEntityKeyFactory.createInfTmpMigrCasesEntityKey(infTmpMigrCasesEntity.getDatatypCode(),
                                                               infTmpMigrCasesEntity.getCaseCode());

        dto.setCode(ek);
        dto.setDatatypCode(infTmpMigrCasesEntity.getDatatypCode());
        dto.setCaseCode(infTmpMigrCasesEntity.getCaseCode());
        dto.setName(infTmpMigrCasesEntity.getName());
        dto.setNeedUpdate(infTmpMigrCasesEntity.getNeedUpdate());


    }

    public static IInfTmpDataDisksDTO getInfTmpDataDisksDTO(InfTmpDataDisksEntity infTmpDataDisksEntity) throws DataBaseException,
                                                                                                                SharedApplicationException {

        IInfTmpDataDisksDTO dto = InfDTOFactory.createInfTmpDataDisksDTO();
        fillInfTmpDataDisksDTO(dto, infTmpDataDisksEntity);
        return dto;
    }

    public static void fillInfTmpDataDisksDTO(IInfTmpDataDisksDTO dto, InfTmpDataDisksEntity infTmpDataDisksEntity) {

        IInfTmpDataDisksEntityKey ek =
            InfEntityKeyFactory.createInfTmpDataDisksEntityKey(infTmpDataDisksEntity.getDatatypCode(),
                                                               infTmpDataDisksEntity.getDiskCode());

        dto.setCode(ek);
        dto.setDatatypCode(infTmpDataDisksEntity.getDatatypCode());
        dto.setDiskCode(infTmpDataDisksEntity.getDiskCode());
        dto.setDiskDate(infTmpDataDisksEntity.getDiskDate());


    }

    public static IInfTmpDataDTO getInfTmpDataDTO(InfTmpDataEntity infTmpDataEntity) throws DataBaseException,
                                                                                            SharedApplicationException {

        IInfTmpDataDTO dto = InfDTOFactory.createInfTmpDataDTO();
        fillInfTmpDataDTO(dto, infTmpDataEntity);
        return dto;
    }

    public static void fillInfTmpDataDTO(IInfTmpDataDTO dto, InfTmpDataEntity infTmpDataEntity) {

        IInfTmpDataEntityKey ek =
            InfEntityKeyFactory.createInfTmpDataEntityKey(infTmpDataEntity.getDatatypCode(), infTmpDataEntity.getDiskCode(),
                                                          infTmpDataEntity.getCivilId());

        dto.setCode(ek);
        dto.setDatatypCode(infTmpDataEntity.getDatatypCode());
        dto.setDiskCode(infTmpDataEntity.getDiskCode());
        dto.setCivilId(infTmpDataEntity.getCivilId());
        dto.setField6(infTmpDataEntity.getField6());
        dto.setField7(infTmpDataEntity.getField7());
        dto.setField4(infTmpDataEntity.getField4());
        dto.setField5(infTmpDataEntity.getField5());
        dto.setField1(infTmpDataEntity.getField1());
        dto.setField3(infTmpDataEntity.getField3());
        dto.setField2(infTmpDataEntity.getField2());
        dto.setField8(infTmpDataEntity.getField8());
        dto.setLoadStatus(infTmpDataEntity.getLoadStatus());
        dto.setMigrationStatus(infTmpDataEntity.getMigrationStatus());
        dto.setDatatypCodeMigr(infTmpDataEntity.getDatatypCodeMigr());
        dto.setCaseCode(infTmpDataEntity.getCaseCode());


    }

    public static IKwtCitizensResidentsDTO getSimpleKwtCitizensResidentsQulDTO(KwtCitizensResidentsEntity kwtCitizensResidentsEntity) {

        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = new KwtCitizensResidentsDTO();
        kwtCitizensResidentsDTO.setCivilId(kwtCitizensResidentsEntity.getCivilId());
        kwtCitizensResidentsDTO.setCode(new KwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));
        //        setName(kwtCitizensResidentsEntity.getFirstName()+" "+kwtCitizensResidentsEntity.getSecondName()+" "+kwtCitizensResidentsEntity.getThirdName()+" "+
        //                kwtCitizensResidentsEntity.getLastName()+" "+kwtCitizensResidentsEntity.getFamilyName());
        String fullCivilName =
            kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
            kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
        if (kwtCitizensResidentsEntity.getFamilyName() != null) {
            fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
}

        kwtCitizensResidentsDTO.setName(fullCivilName);
        kwtCitizensResidentsDTO.setFirstName(kwtCitizensResidentsEntity.getFirstName());
        kwtCitizensResidentsDTO.setSecondName(kwtCitizensResidentsEntity.getSecondName());
        kwtCitizensResidentsDTO.setThirdName(kwtCitizensResidentsEntity.getThirdName());
        kwtCitizensResidentsDTO.setLastName(kwtCitizensResidentsEntity.getLastName());
        kwtCitizensResidentsDTO.setFamilyName(kwtCitizensResidentsEntity.getFamilyName());
        kwtCitizensResidentsDTO.setEnglishName(kwtCitizensResidentsEntity.getEnglishName());
        kwtCitizensResidentsDTO.setBirthDate(kwtCitizensResidentsEntity.getBirthDate());
        kwtCitizensResidentsDTO.setGentypeCode(kwtCitizensResidentsEntity.getGentypeCode());

        kwtCitizensResidentsDTO.setReligionCode(kwtCitizensResidentsEntity.getReligionCode());
        kwtCitizensResidentsDTO.setNationality(kwtCitizensResidentsEntity.getNationality());
        kwtCitizensResidentsDTO.setActiveFlag(kwtCitizensResidentsEntity.getActiveFlag());

        return kwtCitizensResidentsDTO;
    }

    public static IPersonQualificationsDTO getSimplePersonQualificationsDTO(PersonQualificationsEntity infPersonQualificationsEntity) {

        IPersonQualificationsDTO personQualificationsDTO = new PersonQualificationsDTO();

        personQualificationsDTO.setCode(InfEntityKeyFactory.createPersonQualificationsEntityKey(infPersonQualificationsEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                                                String.valueOf(infPersonQualificationsEntity.getQualificationsEntity().getQualificationKey())));
        if (infPersonQualificationsEntity.getQualificationKey() != null &&
            !infPersonQualificationsEntity.getQualificationKey().equals("")) {
            IQualificationsEntityKey entityKey =
                QulEntityKeyFactory.createQualificationsEntityKey(Long.valueOf(infPersonQualificationsEntity.getQualificationKey()));
            try {
                personQualificationsDTO.setQualificationsDTO((IQualificationsDTO)QulClientFactory.getQualificationsClient().getSimpleQuallificationDTO(entityKey));
            } catch (Exception e) {
                e.printStackTrace();
                personQualificationsDTO.setQualificationsDTO(null);
            }
        }

        //TODO fill centerDTO with Code and Name only if you need it

        personQualificationsDTO.setQualificationDate(infPersonQualificationsEntity.getQualificationDate());
        personQualificationsDTO.setQualificationDegree(infPersonQualificationsEntity.getQualificationDegree());
        personQualificationsDTO.setGradeValue(infPersonQualificationsEntity.getGradeValue());
        if (infPersonQualificationsEntity.getGradeTypeEntity() != null) {
            personQualificationsDTO.setGradeTypeDto(InfEntityConvertr.getGradeTypesDTO(infPersonQualificationsEntity.getGradeTypeEntity()));
        }
        personQualificationsDTO.setCurrentQual(infPersonQualificationsEntity.getCurrentQual());

        return personQualificationsDTO;
    }

    public static IPersonsInformationDTO getSimplePersonsInformationDTO(PersonsInformationEntity personsInformationEntity) {

        IPersonsInformationDTO personsInformationDTO = new PersonsInformationDTO();

        personsInformationDTO.setCode(InfEntityKeyFactory.createPersonsInformationEntityKey(personsInformationEntity.getKwtCitizensResidentsEntity().getCivilId(),
                                                                                            personsInformationEntity.getRegistrationDate(),
                                                                                            personsInformationEntity.getSocCode()));
        personsInformationDTO.setDegree(personsInformationEntity.getDegree());

        if (personsInformationEntity.getCenterCode() != null &&
            personsInformationEntity.getCntqualificationCode() != null) {
            ICenterQualificationsEntityKey entityKey =
                QulEntityKeyFactory.createCenterQualificationsEntityKey(personsInformationEntity.getCenterCode(),
                                                                        personsInformationEntity.getCntqualificationCode());
            try {
                personsInformationDTO.setCenterQualificationsDTO((ICenterQualificationsDTO)QulClientFactory.getCenterQualificationsClient().getById(entityKey));
            } catch (Exception e) {
                personsInformationDTO.setCenterQualificationsDTO(null);
            }
            personsInformationDTO.setCntqualificationCode(entityKey.getCntqualificationCode());
        } else {
            personsInformationDTO.setCenterCode(null);
            personsInformationDTO.setCntqualificationCode(null);
        }


        personsInformationDTO.setGradeValue(personsInformationEntity.getGradeValue());

        return personsInformationDTO;
    }
    
    public static IdentificationTypesDTO getIdentificationTypesDTO(IdentificationTypesEntity ent) throws DataBaseException,
                                                                         SharedApplicationException {
        IdentificationTypesDTO dto = new IdentificationTypesDTO();
        fillIIdentificationTypesDTO(dto, ent);
        return dto;
    }

    public static void fillIIdentificationTypesDTO(IdentificationTypesDTO dto, IdentificationTypesEntity ent) throws DataBaseException,
                                                                                    SharedApplicationException {

        IdentificationTypesEntityKey ek = new IdentificationTypesEntityKey(ent.getIdtypeCode());
        dto.setCode(ek);
        dto.setIdtypeCode(ent.getIdtypeCode());
        dto.setName(ent.getName());
        dto.setNotes(ent.getNotes());
        dto.setValidFromDate(ent.getValidFromDate());
        dto.setValidUntilDate(ent.getValidUntilDate());
        dto.setValidDateFlag(checkDatePeriodActiveNow(ent.getValidFromDate(), ent.getValidUntilDate()));
        EntityConverterUtils.fillSharedInfo(ent, dto);
        dto.setTabRecSerial(ent.getTabRecSerail());
    }
    
    public static IInfOldDataAuditDTO getInfOldDataAuditDTO(InfOldDataAuditEntity ent) throws DataBaseException,
                                                                         SharedApplicationException {
        IInfOldDataAuditDTO dto = new InfOldDataAuditDTO();
        fillInfOldDataAuditDTO(dto, ent);
        return dto;
    }

    public static void fillInfOldDataAuditDTO(IInfOldDataAuditDTO dto, InfOldDataAuditEntity ent) throws DataBaseException,
                                                                                    SharedApplicationException {

      
        EntityConverterUtils.fillSharedInfo(ent, dto);
    }
    
    public static boolean checkDatePeriodActiveNow(Timestamp fromDate,Timestamp untilDate){
            
            if(fromDate==null&& untilDate==null){
                 return true;
            }
            
            Timestamp currentDate=new Timestamp(System.currentTimeMillis());
            int compareFromDateResult=0;
            int compareUntilDateResult=0;
            
            if(fromDate!=null&& untilDate==null){
                compareFromDateResult=fromDate.compareTo(currentDate);
                if(compareFromDateResult<=0)
                 return true;
            }
            else if(fromDate!=null && untilDate!=null){
                 compareFromDateResult=fromDate.compareTo(currentDate);
                 compareUntilDateResult=untilDate.compareTo(currentDate);
                
                if(compareFromDateResult<=0 && compareUntilDateResult>=0) // current Date is between 
                    return true;
            }
            else if(untilDate !=null&& fromDate==null){
                compareUntilDateResult=untilDate.compareTo(currentDate);
                if(compareUntilDateResult>=0)
                 return true;
            }
            return false;
        }

}
