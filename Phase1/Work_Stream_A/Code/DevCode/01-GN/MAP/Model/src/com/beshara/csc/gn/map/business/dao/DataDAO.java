package com.beshara.csc.gn.map.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ITreeDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.csc.gn.map.business.dto.IDataDTO;
import com.beshara.csc.gn.map.business.dto.IDataSearchDTO;
import com.beshara.csc.gn.map.business.dto.IMappedValueDTO;
import com.beshara.csc.gn.map.business.dto.MapDTOFactory;
import com.beshara.csc.gn.map.business.entity.DataEntity;
import com.beshara.csc.gn.map.business.entity.IDataEntityKey;
import com.beshara.csc.gn.map.business.entity.ObjectTypesEntity;
import com.beshara.csc.gn.map.business.entity.SocietiesEntity;
import com.beshara.csc.gn.map.business.facade.MapEntityConverter;
import com.beshara.csc.gn.map.business.util.IMapConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class DataDAO extends MapBaseDAO {
    public DataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new DataDAO();
    }

    public IBasicDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            DataEntity dataEntity = EM().find(DataEntity.class, (IDataEntityKey)id);
            if (dataEntity == null) {
                throw new ItemNotFoundException();
            }
            IDataDTO dataDTO = MapEntityConverter.getDataDTO(dataEntity);
            if (dataEntity.getSocietiesEntity() != null) {
                dataDTO.setSocietiesDTO(MapEntityConverter.getSocietiesDTO(dataEntity.getSocietiesEntity()));
            }
            if (dataEntity.getObjectTypesEntity() != null) {
                dataDTO.setObjectTypesDTO(MapEntityConverter.getObjectTypesDTO(dataEntity.getObjectTypesEntity()));
            }
            return dataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<DataEntity> list =
                EM().createNamedQuery("DataEntity.findAll").setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (DataEntity data : list) {
                arrayList.add(MapEntityConverter.getDataDTO(data));
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
            Query query = EM().createNamedQuery("DataEntity.findNewId");
            Long id = (Long)query.getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
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

    public IBasicDTO add(IBasicDTO dataDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            DataEntity dataEntity = new DataEntity();
            IDataDTO dataDTO = (IDataDTO)dataDTOParam;
            ObjectTypesEntity typeEntity = EM().find(ObjectTypesEntity.class, dataDTO.getObjtypeCode());
            SocietiesEntity socEntity = EM().find(SocietiesEntity.class, dataDTO.getSocCode());
            if (typeEntity != null && socEntity != null) {
                dataEntity.setObjectTypesEntity(typeEntity);
                dataEntity.setSocietiesEntity(socEntity);
            } else {
                throw new ItemNotFoundException();
            }
            dataEntity.setSqlStatement(dataDTO.getSqlStatement());
            dataEntity.setCreatedBy(dataDTO.getCreatedBy());
            dataEntity.setCreatedDate(dataDTO.getCreatedDate());
            // dataEntity.setLastUpdatedBy ( dataDTO.getLastUpdatedBy ( ) ) ;
            // dataEntity.setLastUpdatedDate ( dataDTO.getLastUpdatedDate ( ) ) ;
            doAdd(dataEntity);
            return (IBasicDTO)dataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public Boolean update(IBasicDTO dataDTOParam) throws DataBaseException, SharedApplicationException {
        try {
            IDataDTO dataDTO = (IDataDTO)dataDTOParam;
            DataEntity dataEntity = EM().find(DataEntity.class, dataDTO.getCode());
            //dataEntity.setSocCode ( dataDTO.getSocCode ( ) ) ;
            if (dataEntity == null) {
                throw new ItemNotFoundException();
            }
            dataEntity.setSqlStatement(dataDTO.getSqlStatement());
            // dataEntity.setCreatedBy ( dataDTO.getCreatedBy ( ) ) ;
            // dataEntity.setCreatedDate ( dataDTO.getCreatedDate ( ) ) ;
            dataEntity.setLastUpdatedBy(dataDTO.getLastUpdatedBy());
            dataEntity.setLastUpdatedDate(dataDTO.getLastUpdatedDate());
            doUpdate(dataEntity);
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

    public Boolean remove(IBasicDTO dataDTO) throws DataBaseException, SharedApplicationException {
        try {
            DataEntity dataEntity = EM().find(DataEntity.class, (IDataEntityKey)dataDTO.getCode());
            if (dataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(dataEntity);
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

    public List<IBasicDTO> ListByTypeAndSoc(Long objtypeCode, Long socCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            String query =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      socCode).getSingleResult().toString();
            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            List results = EM().createNativeQuery(query).getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            List<String> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.getAllMappedDataList").setParameter("objCode1",
                                                                                            objtypeCode).setParameter("socCode1",
                                                                                                                      socCode).getResultList();

            if (mappedDataList == null && results == null) {
                throw new NoResultException();
            }
            while (it.hasNext()) {
                List result = (List)it.next();
                String code = result.get(0).toString();
                String name = result.get(1).toString();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(code);
                mappedValueDto.setName(name);
                mappedValueDto.setHasMappedValues(mappedDataList.contains(code));
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2(Long objtypeCode, Long socCode1,
                                                    Long socCode2) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            String query = "";
            String exception = "";
            try {
                exception =
                        EM().createNamedQuery("MapDataExceptionsEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("soc1Code",
                                                                                                                                           socCode1).setParameter("soc2Code",
                                                                                                                                                                  socCode2).getSingleResult().toString();
            } catch (Exception e) {
                ;
                //e.printStackTrace();
            }
            if (exception.equals("")) {
                query =
                        EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                              socCode1).getSingleResult().toString();
            } else {
                query = exception;
            }

            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            List results = EM().createNativeQuery(query).getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            List<String> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2").setParameter("objCode1",
                                                                                                objtypeCode).setParameter("objCode2",
                                                                                                                          objtypeCode).setHint("toplink.refresh",
                                                                                                                                               "true").setParameter("socCode1",
                                                                                                                                                                    socCode1).setParameter("socCode2",
                                                                                                                                                                                           socCode2).getResultList();
            if (mappedDataList == null && results == null) {
                throw new NoResultException();
            }
            while (it.hasNext()) {
                List result = (List)it.next();
                String code = result.get(0).toString();
                String name = result.get(1).toString();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(code);
                mappedValueDto.setName(name);
                mappedValueDto.setHasMappedValues(mappedDataList.contains(code));
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> ListByTypeAndSoc1AndSoc2AndMappedFilter(Long objtypeCode, Long socCode1, Long socCode2,
                                                                   Boolean mapped) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            String query = "";
            String exception = "";
            try {
                exception =
                        EM().createNamedQuery("MapDataExceptionsEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("soc1Code",
                                                                                                                                           socCode1).setParameter("soc2Code",
                                                                                                                                                                  socCode2).getSingleResult().toString();
            } catch (Exception e) {
                ;
                //e.printStackTrace();
            }
            if (exception.equals("")) {
                query =
                        EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                              socCode1).getSingleResult().toString();
            } else {
                query = exception;
            }
            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            List results = EM().createNativeQuery(query).getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            List<String> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2").setParameter("objCode1",
                                                                                                objtypeCode).setParameter("objCode2",
                                                                                                                          objtypeCode).setHint("toplink.refresh",
                                                                                                                                               "true").setParameter("socCode1",
                                                                                                                                                                    socCode1).setParameter("socCode2",
                                                                                                                                                                                           socCode2).getResultList();
            if (mappedDataList == null && results == null) {
                throw new NoResultException();
            }
            while (it.hasNext()) {
                List result = (List)it.next();
                String code = result.get(0).toString();
                String name = result.get(1).toString();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(code);
                mappedValueDto.setName(name);
                if (mapped != null) {
                    if (mapped.equals(mappedDataList.contains(code))) {
                        mappedValueDto.setHasMappedValues(mapped);
                        list.add(mappedValueDto);
                    }
                } else {
                    mappedValueDto.setHasMappedValues(mappedDataList.contains(code));
                    list.add(mappedValueDto);
                }


            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> ListByTypeAndSocFiltered(Long objtypeCode, Long socCode,
                                                    String name) throws DataBaseException, SharedApplicationException {
        try {
            String queryStr =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      socCode).getSingleResult().toString();
            StringBuilder sb = new StringBuilder(queryStr);
            String whereCondStr = " WHERE name like '" + name + "'";
            sb.append(" WHERE ");
            String condition = QueryConditionBuilder.getEjbqlSimilarCharsCondition("name", name);
            sb.append(condition);
            //String filteredQueryStr = queryStr + whereCondStr;

            Query query =
                EM().createNativeQuery(sb.toString().replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW)));
            //add where condition for te query to filter data by specific name
            name = "%";
            List results = query.getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                //PopupDTO popupValueDto = new PopupDTO ( ) ;
                //popupValueDto.setStrCode ( result.get ( 0 ) .toString ( ) ) ;
                //popupValueDto.setName ( result.get ( 1 ) .toString ( ) ) ;
                //list.add ( popupValueDto ) ;
                IMappedValueDTO mappedValueDTO = MapDTOFactory.createMappedValueDTO();
                mappedValueDTO.setStrCode(result.get(0).toString());
                mappedValueDTO.setName(result.get(1).toString());
                list.add(mappedValueDTO);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> listByTypeAndSoc1FilteredByName(Long objtypeCode, Long socCode1, Long socCode2,
                                                           String name) throws DataBaseException,
                                                                               SharedApplicationException {

        try {
            String query =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      socCode1).getSingleResult().toString();

            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuffer strBuff = new StringBuffer(query);
            // String whereCondStr = " WHERE name like '" + name + "'";
            String condition = QueryConditionBuilder.getNativeSqlSimilarCharsCondition("name", name);
            strBuff.append(" where ");
            strBuff.append(condition);
            System.out.println("::::::::::::::::::::\n" +
                    strBuff);
            List results = EM().createNativeQuery(strBuff.toString()).getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            List<String> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2").setParameter("objCode1",
                                                                                                objtypeCode).setParameter("objCode2",
                                                                                                                          objtypeCode).setParameter("socCode1",
                                                                                                                                                    socCode1).setParameter("socCode2",
                                                                                                                                                                           socCode2).getResultList();
            if (mappedDataList == null && results == null) {
                throw new NoResultException();
            }
            while (it.hasNext()) {
                List result = (List)it.next();
                String code = result.get(0).toString();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(code);
                mappedValueDto.setName(result.get(1).toString());
                mappedValueDto.setHasMappedValues(mappedDataList.contains(code));
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> ListByTypeAndSocFilteredCode(Long objtypeCode, Long socCode,
                                                        Long code) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            String queryStr =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      socCode).getSingleResult().toString();

            queryStr = queryStr.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            String whereCondStr = " WHERE code = " + code;
            String filteredQueryStr = queryStr + whereCondStr;
            Query query = EM().createNativeQuery(filteredQueryStr);
            //add where condition for te query to filter data by specific name
            //code="%" ;
            List results = query.getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                //PopupDTO popupValueDto = new PopupDTO ( ) ;
                //popupValueDto.setStrCode ( result.get ( 0 ) .toString ( ) ) ;
                //popupValueDto.setName ( result.get ( 1 ) .toString ( ) ) ;
                //list.add ( popupValueDto ) ;
                IMappedValueDTO mappedValueDTO = MapDTOFactory.createMappedValueDTO();
                mappedValueDTO.setStrCode(result.get(0).toString());
                mappedValueDTO.setName(result.get(1).toString());
                list.add(mappedValueDTO);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> listByTypeAndSoc1FilteredByCode(Long objtypeCode, Long socCode1, Long socCode2,
                                                           String code) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            String query =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      socCode1).getSingleResult().toString();

            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            String whereCondStr = " WHERE code = '" + code + "'";
            String filteredQueryStr = query + whereCondStr;
            List results = EM().createNativeQuery(filteredQueryStr).getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            List<String> mappedDataList =
                EM().createNamedQuery("MappedDataEntity.ListByTypeAndSoc1AndSoc2").setParameter("objCode1",
                                                                                                objtypeCode).setParameter("objCode2",
                                                                                                                          objtypeCode).setParameter("socCode1",
                                                                                                                                                    socCode1).setParameter("socCode2",
                                                                                                                                                                           socCode2).getResultList();
            if (mappedDataList == null && results == null) {
                throw new NoResultException();
            }
            while (it.hasNext()) {
                List result = (List)it.next();
                code = result.get(0).toString();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(code);
                mappedValueDto.setName(result.get(1).toString());
                mappedValueDto.setHasMappedValues(mappedDataList.contains(code));
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> ListByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                 String soc1Value) throws DataBaseException,
                                                                          SharedApplicationException {

        try {
            String finalQuery = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (finalQuery.equals(""))
                finalQuery = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);


            finalQuery = finalQuery.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            List results = EM().createNativeQuery(finalQuery).getResultList();
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {


                List result = (List)it.next();
                IMappedValueDTO valueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                valueDto.setStrCode(result.get(0).toString());
                valueDto.setName(result.get(1).toString());
                //                valueDto.setTreeLevel(Long.valueOf(result.get(3).toString()));
                //
                //                valueDto.setLeafFlag(Long.valueOf(result.get(4).toString()));
                //                EntityKey fek = new EntityKey();
                //                fek.setKey(result.get(5).toString());
                //
                //                EntityKey pek = new EntityKey();
                //                pek.setKey(result.get(6).toString());
                //                valueDto.setFirstParent(fek);
                //valueDto.setParentCode(pek);
                list.add(valueDto);


            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getTreeByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                    String soc1Value) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            String finalQuery = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (finalQuery.equals(""))
                finalQuery = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);

            finalQuery = finalQuery.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TREE_VIEW));


            System.out.println("\n" +
                    "after replace");
            System.out.println("\n" +
                    finalQuery);

            List results = EM().createNativeQuery(finalQuery).getResultList();
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO valueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                valueDto.setStrCode(result.get(0).toString());
                valueDto.setName(result.get(1).toString());
                valueDto.setTreeLevel(Long.valueOf(result.get(3).toString()));

                valueDto.setLeafFlag(Long.valueOf(result.get(4).toString()));
                EntityKey fek =
                    new EntityKey((((BigInteger)((BigDecimal)result.get(5)).toBigIntegerExact()).toString()));
                valueDto.setFirstParent(fek);
                //fek.setKey((((BigInteger)((BigDecimal)result.get(5)).toBigIntegerExact()).toString()));
                if (!ISystemConstant.FIRST_LEVEL_IN_TREE.equals(valueDto.getTreeLevel())) {
                    EntityKey pek =
                        new EntityKey((((BigInteger)((BigDecimal)result.get(6)).toBigIntegerExact()).toString()));
                    valueDto.setParentCode(pek);
                }

                //pek.setKey((((BigInteger)((BigDecimal)result.get(6)).toBigIntegerExact()).toString()));


                list.add((ITreeDTO)valueDto);

            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    private String getSqlQueryFromDataEntity(Long objtypeCode, Long soc2Code, Long soc1Code, String soc1Value) {

        String query =
            EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                  soc2Code).getSingleResult().toString();

        //query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
        System.out.println("first query:==" + query);
        String mappedDataStatement =
            " ( SELECT SOC2_VALUE as name from GN_MAP_MAPPED_DATA where OBJTYPE1_CODE =" + objtypeCode +
            " and OBJTYPE2_CODE = " + objtypeCode + " and SOC1_CODE = " + soc1Code + " and SOC2_CODE = " + soc2Code +
            " and SOC1_VALUE = '" + soc1Value + "' ) ";
        String finalQuery = query + " Where to_char ( code ) not in " + mappedDataStatement;
        System.out.println("final query:==" + finalQuery);
        return finalQuery;
    }

    private String getSqlQueryFromDataExceptionsEntity(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                       String soc1Value) {
        String exception = "";
        try {
            exception =
                    EM().createNamedQuery("MapDataExceptionsEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("soc1Code",
                                                                                                                                       soc2Code).setParameter("soc2Code",
                                                                                                                                                              soc1Code).getSingleResult().toString();

            //  exception = exception.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            String mappedDataStatement =
                " ( SELECT SOC2_VALUE as name from GN_MAP_MAPPED_DATA where OBJTYPE1_CODE =" + objtypeCode +
                " and OBJTYPE2_CODE = " + objtypeCode + " and SOC1_CODE = " + soc1Code + " and SOC2_CODE = " +
                soc2Code + " and SOC1_VALUE = '" + soc1Value + "' ) ";
            String finalQuery = exception + "Where to_char ( code ) not in " + mappedDataStatement;
            System.out.println(finalQuery);
            return finalQuery;
        } catch (Exception e) {
            return "";
            //e.printStackTrace();
        }

    }

    public List<IBasicDTO> ListByTypeAndSoc2CodeWithPaging(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                           String soc1Value,
                                                           IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                SharedApplicationException {

        try {
            String finalQuery = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (finalQuery.equals(""))
                finalQuery = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);


            finalQuery = finalQuery.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            Query query = EM().createNativeQuery(finalQuery);

            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            List results = query.getResultList();
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO valueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                valueDto.setStrCode(result.get(0).toString());
                valueDto.setName(result.get(1).toString());
                list.add(valueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public Boolean DisplayTreeFlag(Long objtypeCode, Long soc2Code) throws DataBaseException,
                                                                           SharedApplicationException {

        try {
            Long treeFlag = IMapConstants.MAP_TABLE_VIEW;

            treeFlag =
                    (Long)EM().createNamedQuery("DataEntity.getTreeFlag").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                                soc2Code).getSingleResult();

            return (IMapConstants.MAP_TREE_VIEW.equals(treeFlag));
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFiltered(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                         String soc1Value, String param) throws DataBaseException,
                                                                                                SharedApplicationException {

        try {
            String query = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (query.equals(""))
                query = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            //        String query =
            //            em.createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode",
            //                                                                       objtypeCode).setParameter("socCode",
            //                                                                                                 soc2Code).getSingleResult().toString();
            //        String mappedDataStatement =
            //            " ( SELECT SOC2_VALUE as name from GN_MAP_MAPPED_DATA where OBJTYPE1_CODE =" +
            //            objtypeCode + " and OBJTYPE2_CODE = " + objtypeCode +
            //            " and SOC1_CODE = " + soc1Code + " and SOC2_CODE = " + soc2Code +
            //            " and SOC1_VALUE = '" + soc1Value + "' ) ";
            //        String finalQuery =
            //            query + "Where to_char ( code ) not in " + mappedDataStatement +
            //            "";
            //StringBuffer sbQuery = new StringBuffer(finalQuery);
            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuffer sbQuery = new StringBuffer(query);

            String condition = QueryConditionBuilder.getNativeSqlSimilarCharsCondition("name", param);
            sbQuery.append(" and ");
            sbQuery.append(condition);
            System.out.println("\n" +
                    sbQuery);
            List results = EM().createNativeQuery(sbQuery.toString()).getResultList();
            if (results == null) {
                throw new NoResultException();

            }
            System.out.println("\n" +
                    sbQuery);
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                mappedValueDto.setStrCode(result.get(0).toString());
                mappedValueDto.setName(result.get(1).toString());
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * get all Values executed from sql query execluding values selected before filtered by name * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredByCode(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                               String soc1Value, String code) throws DataBaseException,
                                                                                                     SharedApplicationException {

        try {
            String query = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (query.equals(""))
                query = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            //        String query =
            //            em.createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode",
            //                                                                       objtypeCode).setParameter("socCode",
            //                                                                                                 soc2Code).getSingleResult().toString();
            //        String mappedDataStatement =
            //            " ( SELECT SOC2_VALUE as name from GN_MAP_MAPPED_DATA where OBJTYPE1_CODE =" +
            //            objtypeCode + " and OBJTYPE2_CODE = " + objtypeCode +
            //            " and SOC1_CODE = " + soc1Code + " and SOC2_CODE = " + soc2Code +
            //            " and SOC1_VALUE = '" + soc1Value + "' ) ";
            //        String finalQuery =
            //            query + "Where to_char ( code ) not in " + mappedDataStatement +
            //            "";
            //StringBuffer sbQuery = new StringBuffer(finalQuery);
            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));


            StringBuffer sbQuery = new StringBuffer(query);

            sbQuery.append(" and CODE = '" + code + "' ");

            //        String condition =
            //            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("name",
            //                                                                    param);
            //        sbQuery.append(" and ");
            //        sbQuery.append(condition);
            //        System.out.println("\n" + sbQuery);
            System.out.println("\n" +
                    "after replace");
            System.out.println("\n" +
                    sbQuery);
            List results = EM().createNativeQuery(sbQuery.toString()).getResultList();
            System.out.println("\n" +
                    sbQuery);
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                mappedValueDto.setStrCode(result.get(0).toString());
                mappedValueDto.setName(result.get(1).toString());
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredByCodeWithPaging(IBasicDTO _srchDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {

        try {
            IDataSearchDTO srchDTO = (IDataSearchDTO)_srchDTO;
            String query =
                getSqlQueryFromDataExceptionsEntity(srchDTO.getObjtypeCode(), srchDTO.getSoc2Code(), srchDTO.getSoc1Code(),
                                                    srchDTO.getSoc1Value());
            if (query.equals(""))
                query =
                        getSqlQueryFromDataEntity(srchDTO.getObjtypeCode(), srchDTO.getSoc2Code(), srchDTO.getSoc1Code(),
                                                  srchDTO.getSoc1Value());


            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuffer sbQuery = new StringBuffer(query);

            sbQuery.append(" and CODE = '" + srchDTO.getCodeValue() + "' ");


            Query ejbQl = EM().createNativeQuery(sbQuery.toString());
            if (srchDTO.getRequestDTO() != null) {
                ejbQl.setFirstResult(srchDTO.getRequestDTO().getFirstRowNumber().intValue());
                ejbQl.setMaxResults(srchDTO.getRequestDTO().getMaxNoOfRecords().intValue());
            }
            List results = ejbQl.getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            System.out.println("\n" +
                    sbQuery);
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                mappedValueDto.setStrCode(result.get(0).toString());
                mappedValueDto.setName(result.get(1).toString());
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> ListByTypeAndSoc2CodeFilteredWithPaging(IBasicDTO _srchDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            IDataSearchDTO srchDTO = (IDataSearchDTO)_srchDTO;

            String query =
                getSqlQueryFromDataExceptionsEntity(srchDTO.getObjtypeCode(), srchDTO.getSoc2Code(), srchDTO.getSoc1Code(),
                                                    srchDTO.getSoc1Value());
            if (query.equals(""))
                query =
                        getSqlQueryFromDataEntity(srchDTO.getObjtypeCode(), srchDTO.getSoc2Code(), srchDTO.getSoc1Code(),
                                                  srchDTO.getSoc1Value());


            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuffer sbQuery = new StringBuffer(query);

            String condition = QueryConditionBuilder.getNativeSqlSimilarCharsCondition("name", srchDTO.getName());
            sbQuery.append(" and ");
            sbQuery.append(condition);
            System.out.println("\n" +
                    sbQuery);
            Query ejbQl = EM().createNativeQuery(sbQuery.toString());
            if (srchDTO.getRequestDTO() != null) {
                ejbQl.setFirstResult(srchDTO.getRequestDTO().getFirstRowNumber().intValue());
                ejbQl.setMaxResults(srchDTO.getRequestDTO().getMaxNoOfRecords().intValue());
            }
            List results = ejbQl.getResultList();
            System.out.println("\n" +
                    sbQuery);
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                mappedValueDto.setStrCode(result.get(0).toString());
                mappedValueDto.setName(result.get(1).toString());
                list.add(mappedValueDto);
            }
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     *
     * Edited by Hany Omar and Amir Nasr 9/11/2014
     * get all soc2Value ( code and name ) for specific mapped value in mapped data table * @param objtypeCode
     * @param soc1Code
     * @param soc2Code
     * @param soc1Value
     * @return list of PopupDTOs
     * @throws RemoteException
     */
    public List<IBasicDTO> ListMappedT0ByTypeAndSoc2Code(Long objtypeCode, Long soc2Code, Long soc1Code,
                                                         String soc1Value, String mapStatus) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            String query =
                EM().createNamedQuery("DataEntity.getSQLQuery").setParameter("objtypeCode", objtypeCode).setParameter("socCode",
                                                                                                                      soc2Code).getSingleResult().toString();

            query = query.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuilder mappedDataStatement =
                new StringBuilder(" ( SELECT SOC2_VALUE as code from GN_MAP_MAPPED_DATA where OBJTYPE1_CODE =");
            mappedDataStatement.append(objtypeCode);
            mappedDataStatement.append(" and OBJTYPE2_CODE = ");
            mappedDataStatement.append(objtypeCode);
            mappedDataStatement.append(" and SOC1_CODE = ");
            mappedDataStatement.append(soc1Code);
            mappedDataStatement.append(" and SOC2_CODE = ");
            mappedDataStatement.append(soc2Code);
            mappedDataStatement.append(" and SOC1_VALUE = '");
            mappedDataStatement.append(soc1Value);
            mappedDataStatement.append("' ");
            if (mapStatus != null) {
                mappedDataStatement.append(" AND MAP_STATUS = ");
                mappedDataStatement.append(mapStatus);
            }
            mappedDataStatement.append(" )");

            String finalQuery = query + " Where to_char ( code ) in " + mappedDataStatement.toString();
            List results = EM().createNativeQuery(finalQuery).setHint("toplink.refresh", "true").getResultList();
            if (results == null) {
                throw new NoResultException();
            }
            Iterator it = results.iterator();
            ArrayList list = new ArrayList();
            while (it.hasNext()) {
                List result = (List)it.next();
                IMappedValueDTO mappedValueDto = MapDTOFactory.createMappedValueDTO();
                //popupValueDto.setLongCode ( Long.parseLong ( result.get ( 0 ) .toString ( ) ) ) ;
                mappedValueDto.setStrCode(result.get(0).toString());
                mappedValueDto.setName(result.get(1).toString());
                list.add(mappedValueDto);
            }
            System.out.println(finalQuery);
            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long getTotalCount(Long objtypeCode, Long soc2Code, Long soc1Code,
                              String soc1Value) throws DataBaseException, SharedApplicationException {
        try {
            String finalQuery = getSqlQueryFromDataExceptionsEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            if (finalQuery.equals(""))
                finalQuery = getSqlQueryFromDataEntity(objtypeCode, soc2Code, soc1Code, soc1Value);
            finalQuery = finalQuery.replaceAll(":p_falg", String.valueOf(IMapConstants.MAP_TABLE_VIEW));
            StringBuffer countQuery = new StringBuffer("SELECT count(*) FROM (");
            countQuery.append(finalQuery);
            countQuery.append(")");
            //finalQuery = finalQuery.replaceFirst("SELECT[a-zA-Z1-9\\,\\s\\_]*FROM", "SELECT COUNT(*) FROM");
            System.out.println(countQuery.toString());
            Long count = 0L;
            Vector v = (Vector)EM().createNativeQuery(countQuery.toString()).getSingleResult();
            if (v != null && v.size() > 0) {
                count = Long.parseLong(v.get(0).toString());
            }

            return count;
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
