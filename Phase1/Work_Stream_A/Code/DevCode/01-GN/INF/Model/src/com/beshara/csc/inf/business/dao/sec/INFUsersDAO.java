package com.beshara.csc.inf.business.dao.sec;


import com.beshara.base.dao.BaseDAO;
import com.beshara.common.dao.exceptions.DAOException;
import com.beshara.csc.inf.business.dao.InfBaseDAO;
import com.beshara.csc.inf.business.dto.UserDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class INFUsersDAO extends InfBaseDAO {
    public INFUsersDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new INFUsersDAO();
    }

    /**
     * @author Technical Team[m.sayed]
     * @since 12-6-2016
     * story ID CSC-17964
     * @param <T>
     * @param dtoType
     * @param civilid
     * @param isForPortal
     * @return
     * @throws DAOException
     */
    public UserDTO getUserByCivilIdForPortal(Long civilid, Long isForPortal) throws DataBaseException,
                                                                                    SharedApplicationException {

        UserDTO userDTO = new UserDTO();
        try {
            StringBuilder sBuilder = new StringBuilder("SELECT * FROM GN_SEC_USERS  U WHERE");
            sBuilder.append(" nvl(USER_DELETED,0) =0  And ");
            sBuilder.append(" U.CIVIL_ID = " + civilid);
            sBuilder.append(" and  USR_FOR_PORTAL  = " + isForPortal);
            Query query = EM().createNativeQuery(sBuilder.toString());
            query.setHint("toplink.refresh", "true");
            Vector result = (Vector)query.getResultList();
            if (result != null && result.size() > 0) {
                userDTO.setUserName(((List)result.elementAt(0)).get(1).toString());
                userDTO.setCode(Long.valueOf(((List)result.elementAt(0)).get(0).toString()));
            }
            return userDTO;
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
     * @author Technical Team[m.sayed]
     * @since 12-6-2016
     * story ID CSC-17964
     * @param isForPortal
     * @param civilid
     * @return
     * @throws DAOException
     */
    public void UpdateUserForPortal(Long civilid, Long isForPortal) throws DataBaseException,
                                                                           SharedApplicationException {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        try {
            StringBuilder sBuilder = new StringBuilder("UPDATE ");
            sBuilder.append(" GN_SEC_USERS ");
            sBuilder.append(" SET");
            sBuilder.append(" USR_FOR_PORTAL  =  " + isForPortal);
            sBuilder.append(" WHERE");
            sBuilder.append(" civil_id = " + civilid);
            ps = connection.prepareStatement(sBuilder.toString());
            ps.executeUpdate();
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
