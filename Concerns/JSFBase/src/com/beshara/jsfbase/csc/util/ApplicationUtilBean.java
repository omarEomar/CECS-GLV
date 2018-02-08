package com.beshara.jsfbase.csc.util;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Bean Encapsulate global resources overall the application (online users, number of onlin users,..)
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Amir Nasr    13-SEP-2007     Created
 *    <br>
 *    
 * @author       Beshara Group   
 * @version      1.0   
 * @since        13/09/2007   
 */
public class ApplicationUtilBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Map onlineUsersList = new HashMap();
    private Integer onlineUsersCount = 0;

    public ApplicationUtilBean() {
    }

    public void setOnlineUsersList(Map loggedUser) {
        this.onlineUsersList = loggedUser;
    }

    public Map getOnlineUsersList() {
        return onlineUsersList;
    }

    public void setOnlineUsersCount(Integer onlineUsers) {
        this.onlineUsersCount = onlineUsers;
    }

    public Integer getOnlineUsersCount() {
        return onlineUsersCount;
    }

    /**
     * remove specific user from online users list, and decrement number of online users by 1
     * @param userName String that represent the user entityName of the logout user
     */
    public void removeUser(String userName) {
        if (userName != null) {
            onlineUsersList.remove(userName);
            onlineUsersCount--;
        }
    }

    /**
     * add specific user to online users list, and increment number of online users by 1
     */
    public void addUser() /*should take Login User Bean as a parameter*/ {
        onlineUsersList.put("", ""); //should put Login User DTO 
        onlineUsersCount++;
    }

    /**
     * check on specific user if he is online or not
     * @param userName String that represent the user entityName of the user
     */
    public boolean isUserOnline(String userName) {
        return onlineUsersList.containsKey(userName);
    }
}
