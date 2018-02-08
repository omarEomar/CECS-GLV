package com.beshara.csc.gn.map.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.gn.map.business.dao.MapDataExceptionsDAO;
import com.beshara.csc.gn.map.business.entity.MapDataExceptionsEntity;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
 
@Stateless(name = "MapDataExceptionsSession", mappedName = "Map-Model-MapDataExceptionsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(MapDataExceptionsSession.class)
public class MapDataExceptionsSessionBean extends MapBaseSessionBean implements MapDataExceptionsSession {
   
    /**
     * JobsSession Default Constructor
     */
    public MapDataExceptionsSessionBean() {        
    }
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return  MapDataExceptionsEntity.class;
    }

    @Override
    protected  MapDataExceptionsDAO DAO() {
        return  ( MapDataExceptionsDAO)super.DAO();
    }

    
}
