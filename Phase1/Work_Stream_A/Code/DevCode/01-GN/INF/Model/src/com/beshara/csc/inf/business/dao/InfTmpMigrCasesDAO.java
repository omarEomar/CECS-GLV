package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.inf.business.dto.IInfTmpMigrCasesDTO;
import com.beshara.csc.inf.business.entity.IInfTmpMigrCasesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.InfTmpMigrCasesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class InfTmpMigrCasesDAO extends InfBaseDAO {

    /**
     * InfTmpMigrCasesDAO Default Constructor
     */
    public InfTmpMigrCasesDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new InfTmpMigrCasesDAO();
    }


    /**<code>select o from InfTmpMigrCasesEntity o</code>.
     * @return List
     */
    public List<IInfTmpMigrCasesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<InfTmpMigrCasesEntity> list = EM().createNamedQuery("InfTmpMigrCasesEntity.findAll").getResultList();
            for (InfTmpMigrCasesEntity infTmpMigrCases : list) {
                arrayList.add(InfEntityConverter.getInfTmpMigrCasesDTO(infTmpMigrCases));

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


    /**
     * Create a New InfTmpMigrCasesEntity
     * @param infTmpMigrCasesDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO infTmpMigrCasesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            InfTmpMigrCasesEntity infTmpMigrCasesEntity = new InfTmpMigrCasesEntity();

            IInfTmpMigrCasesDTO infTmpMigrCasesDTO = (IInfTmpMigrCasesDTO)infTmpMigrCasesDTO1;

            infTmpMigrCasesEntity.setDatatypCode(infTmpMigrCasesDTO.getDatatypCode());
            infTmpMigrCasesEntity.setCaseCode(infTmpMigrCasesDTO.getCaseCode());
            infTmpMigrCasesEntity.setName(infTmpMigrCasesDTO.getName());
            infTmpMigrCasesEntity.setNeedUpdate(infTmpMigrCasesDTO.getNeedUpdate());


            doAdd(infTmpMigrCasesEntity);
            IInfTmpMigrCasesEntityKey ek =
                InfEntityKeyFactory.createInfTmpMigrCasesEntityKey(infTmpMigrCasesEntity.getDatatypCode(),
                                                                   infTmpMigrCasesEntity.getCaseCode());
            infTmpMigrCasesDTO.setCode(ek);

            // Set PK after creation
            return infTmpMigrCasesDTO;
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
     * Update an Existing InfTmpMigrCasesEntity
     * @param infTmpMigrCasesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO infTmpMigrCasesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpMigrCasesDTO infTmpMigrCasesDTO = (IInfTmpMigrCasesDTO)infTmpMigrCasesDTO1;

            InfTmpMigrCasesEntity infTmpMigrCasesEntity =
                EM().find(InfTmpMigrCasesEntity.class, (IInfTmpMigrCasesEntityKey)infTmpMigrCasesDTO.getCode());

            infTmpMigrCasesEntity.setName(infTmpMigrCasesDTO.getName());
            infTmpMigrCasesEntity.setNeedUpdate(infTmpMigrCasesDTO.getNeedUpdate());

            doUpdate(infTmpMigrCasesEntity);
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

    /**
     * Remove an Existing InfTmpMigrCasesEntity
     * @param infTmpMigrCasesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO infTmpMigrCasesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpMigrCasesDTO infTmpMigrCasesDTO = (IInfTmpMigrCasesDTO)infTmpMigrCasesDTO1;

            InfTmpMigrCasesEntity infTmpMigrCasesEntity =
                EM().find(InfTmpMigrCasesEntity.class, (IInfTmpMigrCasesEntityKey)infTmpMigrCasesDTO.getCode());

            if (infTmpMigrCasesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(infTmpMigrCasesEntity);
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

    /**
     * Get InfTmpMigrCasesEntity By Primary Key
     * @param id
     * @return InfTmpMigrCasesDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IInfTmpMigrCasesEntityKey id = (IInfTmpMigrCasesEntityKey)id1;

            InfTmpMigrCasesEntity infTmpMigrCasesEntity = EM().find(InfTmpMigrCasesEntity.class, id);
            if (infTmpMigrCasesEntity == null) {
                throw new ItemNotFoundException();
            }
            IInfTmpMigrCasesDTO infTmpMigrCasesDTO = InfEntityConverter.getInfTmpMigrCasesDTO(infTmpMigrCasesEntity);
            return infTmpMigrCasesDTO;
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
     * Get the MaxId of InfTmpMigrCasesEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("InfTmpMigrCasesEntity.findNewId").getSingleResult();
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

    public List<IBasicDTO> getAllByDataTypeCode(Long dataType, Long deskCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder query = new StringBuilder("SELECT *\n" +
                    "  FROM inf_tmp_migr_cases c\n" +
                    " WHERE c.datatyp_code =");
            query.append(dataType);
            query.append(" AND c.need_update = 1 AND c.CASE_CODE not in (select mc.CASE_CODE from inf_tmp_disk_migr_cases mc where mc.DISK_CODE=");
            query.append(deskCode);
            query.append(" ) order by c.CASE_CODE");
            System.out.println("View query >>>>>>>>>> " + query.toString());
            Query q =
                EM().createNativeQuery(query.toString(), InfTmpMigrCasesEntity.class).setHint("toplink.refresh", "true");
            List<InfTmpMigrCasesEntity> list = q.getResultList();
            for (InfTmpMigrCasesEntity infTmpMigrCases : list) {
                arrayList.add(InfEntityConverter.getInfTmpMigrCasesDTO(infTmpMigrCases));

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

    public String getCaseCode(Long dataType, Long diskCode) throws DataBaseException, SharedApplicationException {
        String caseCode = "";
        try {
            StringBuilder sql =
                new StringBuilder("select d.CASE_CODE from INF_TMP_DISK_MIGR_CASES d where d.DATATYP_CODE=" +
                                  dataType + " and d.DISK_CODE=" + diskCode);

            System.out.println("query>>>>>>>>>" + sql.toString());
            Query query = EM().createNativeQuery(sql.toString());
            List<Vector> list = query.getResultList();
            for (int i = 0; i < list.size(); i++) {
                Vector v;
                v = list.get(i);
                caseCode += v.get(0).toString();
                if (i < list.size() - 1) {
                    caseCode += " , ";
                }

            }
            if (!caseCode.equals(""))
            caseCode = "(" + caseCode + ")";

            return caseCode;
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
