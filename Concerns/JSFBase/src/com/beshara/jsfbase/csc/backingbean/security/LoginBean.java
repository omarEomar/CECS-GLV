package com.beshara.jsfbase.csc.backingbean.security;

import com.beshara.csc.sec.engine.dto.MinistryDTO;
import com.beshara.sec.engine.dto.GroupDTO;
import com.beshara.sec.engine.dto.UserDTO;


public class LoginBean extends com.beshara.csc.sec.web.jsf.bean.LoginBean {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private UserDTO defaultUser;
    private GroupDTO defaultGroup;
    private MinistryDTO defaultMinistry;
    private int maxMinistryNameLength;
    private final int DEFAULT_MAX_LENGTH = 35;    

    public LoginBean() {

    }

    public static LoginBean getInstance() {
        return (LoginBean)com.beshara.csc.sec.web.jsf.bean.LoginBean.getInstance();
    }

    @Override
    public UserDTO getUser() {
        UserDTO user = super.getUser();
        if (user == null) {
            if (defaultUser == null) {
                defaultUser = new UserDTO();
                defaultUser.setCode(21409L);
                defaultUser.setUserName("root");
            }
            user = defaultUser;
        }
        return user;
    }

    @Override
    public GroupDTO getGroup() {
        GroupDTO group = super.getGroup();
        if (group == null) {
            if (defaultGroup == null) {
                defaultGroup = new GroupDTO();
                defaultGroup.setCode(999999999999L);
                defaultGroup.setName("مجموعة مطورى الأنظمة");
            }
            group = defaultGroup;
        }
        return group;
    }

    /**
         * @author TechnicalTeam[H.Ahmed]
         * @since 1/09/2015
         * @return
         * @Note CSC-13542 to show additional details for ministry
         *
     */
    @Override
    public MinistryDTO getMinistry() {
        MinistryDTO ministry = super.getMinistry();
        if (ministry == null) {
            if (defaultMinistry == null) {
                defaultMinistry = new MinistryDTO();
                defaultMinistry.setCode(13L);
                defaultMinistry.setName("ديوان الخدمة المدنية");
            }
            ministry = defaultMinistry;
        }
        return ministry;
    }

    @Override
    public String getMinistryName() {

        return getMinistry().getName();
    }

    public int getMaxNameLength(){
        if(maxMinistryNameLength == 0)
            return DEFAULT_MAX_LENGTH;
        return maxMinistryNameLength;
    }
    
    public String getMinistryShortName() {
        String _shortName = getMinistry().getName();
        if(_shortName.length()<= getMaxNameLength()){
            return _shortName;
        }
        _shortName = _shortName.substring(0, getMaxNameLength());
        _shortName = _shortName.substring(0, _shortName.lastIndexOf(" "));
            
        return "...".concat(_shortName);
    }

    public Long getGroupCode() {
        return getGroup().getCode();
    }

    public String getUserName() {
        return getUser().getUserName();
    }

    public void setMaxMinistryNameLength(int maxMinistryNameLength) {
        this.maxMinistryNameLength = maxMinistryNameLength;
    }

    public int getMaxMinistryNameLength() {
        return maxMinistryNameLength;
    }
}
