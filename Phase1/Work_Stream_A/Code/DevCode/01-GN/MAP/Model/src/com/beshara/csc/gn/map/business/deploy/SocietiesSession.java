package com.beshara.csc.gn.map.business.deploy ;

import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;

/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */ 
 @Remote 
 public interface SocietiesSession extends BasicSession { /** 
 * get all data with specific objectTypeCode * @param objtypeCode 
 * @return List ISocietiesDTO 
 * @throws RemoteException 
 */ 
 public List<IBasicDTO> listByObjectType ( IRequestInfoDTO ri,Long objtypeCode ) throws RemoteException , SharedApplicationException , DataBaseException ; 
 
 public List<IBasicDTO> getAllByFalg(IRequestInfoDTO ri,Long flag) throws RemoteException , SharedApplicationException , DataBaseException ;
     
 public List<IBasicDTO> searchByMinCode(IRequestInfoDTO ri,Long minCode) throws RemoteException ,DataBaseException , SharedApplicationException;
 
     public List<IBasicDTO> searchByNameAndStatus(IRequestInfoDTO ri,String name , String SystemOrCenterFlag) throws RemoteException,DataBaseException , SharedApplicationException ;
 } 
