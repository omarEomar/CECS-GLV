package com.beshara.csc.inf.business.client.test;


import com.beshara.csc.inf.business.deploy.PersonQualificationsSession;
import com.beshara.csc.inf.business.dto.IPersonQualificationsDTO;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ServiceLocator;

import java.rmi.RemoteException;

import javax.naming.NamingException;


/**
 */
public class PersonQualificationsClientTest {
    /** 
     */
    PersonQualificationsSession personQualificationsSession;

    public PersonQualificationsClientTest() {
        try { /*
 * Use ServiceLocator to Lookup Session Remote and make the appropriate Process * */
            ServiceLocator serviceLocator = ServiceLocator.getInstance();
            personQualificationsSession = 
                    (PersonQualificationsSession)serviceLocator.lookup("PersonQualificationsSession", 
                                                                       PersonQualificationsSession.class);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /** 
     * @param args 
     */
    public static void main(String[] args) {
        PersonQualificationsClientTest test = 
            new PersonQualificationsClientTest();
        //IPersonQualificationsClient personQualificationsClient = InfClientFactory.getPersonQualificationsClient ( ) ; 
        try { //test.validatePersonQualification ( ) ; 
            test.updateData();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void validatePersonQualification() throws RemoteException, 
                                                     SharedApplicationException, 
                                                     DataBaseException {
        IPersonQualificationsDTO personQualificationsDTO =
            // ( IPersonQualificationsDTO ) personQualificationsSession.getById ( InfEntityKeyFactory.createPersonQualificationsEntityKey ( 280120601782L , "10/12/202/1" ) ) ; 
            // ( IPersonQualificationsDTO ) personQualificationsSession.getById ( InfEntityKeyFactory.createPersonQualificationsEntityKey ( 280120601782L , "10/69/0/0" ) ) ; 
            (IPersonQualificationsDTO)personQualificationsSession.getById(InfEntityKeyFactory.createPersonQualificationsEntityKey(280120601782L, 
                                                                                                                                  "117/88/726/0"));
//        personQualificationsSession.validatePersonQualification(personQualificationsDTO);
    }

    public void updateData() throws RemoteException, 
                                    SharedApplicationException, 
                                    DataBaseException { //PersonQualificationsDTO personQualificationsDTO = ( IPersonQualificationsDTO ) personQualificationsSession.getById ( InfEntityKeyFactory.createPersonQualificationsEntityKey ( 280120601782L , "10/12/202/1" ) ) ; 
        IPersonQualificationsDTO personQualificationsDTO = 
            (IPersonQualificationsDTO)personQualificationsSession.getById(InfEntityKeyFactory.createPersonQualificationsEntityKey(280120601782L, 
                                                                                                                                  "10/69/0/0"));
        //personQualificationsSession.updateCaseDiffEduLevel ( personQualificationsDTO ) ; 
        // personQualificationsSession.updateCaseSameEduLevel ( personQualificationsDTO ) ; 
//        personQualificationsSession.updateCaseLrgQualDate(personQualificationsDTO);
    }
}
