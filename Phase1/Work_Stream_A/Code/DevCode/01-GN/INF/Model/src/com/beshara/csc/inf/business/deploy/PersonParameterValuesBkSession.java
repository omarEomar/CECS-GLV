package com.beshara.csc.inf.business.deploy ; 
 import com.beshara.base.deploy.BasicSession ; 
 import com.beshara.csc.inf.business.dto.IPersonParameterValuesBkDTO ; 
 import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException ; 
 import java.util.Collection ; 
 import java.util.List ; 
 import javax.ejb.Remote ; 
 import java.rmi.RemoteException ; 
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
 public interface PersonParameterValuesBkSession extends BasicSession { // Add your extra methods 
 } 