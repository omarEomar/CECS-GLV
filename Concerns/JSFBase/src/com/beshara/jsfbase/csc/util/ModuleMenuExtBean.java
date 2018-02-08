package com.beshara.jsfbase.csc.util;

import com.beshara.common.web.jsf.shared.JSFHelper;
import com.beshara.csc.sec.engine.SecurityEngineExt;
import com.beshara.csc.sec.engine.SecurityExtUtils;
import com.beshara.csc.sec.engine.dto.ModuleDTO;
import com.beshara.sec.engine.dto.UserDTO;
import com.beshara.jsfbase.csc.backingbean.EncryptHelper;
import com.beshara.jsfbase.csc.backingbean.security.LoginBean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;


public class ModuleMenuExtBean implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private static final String BEAN_NAME = "ModuleMenuExt";

    private static final SecurityEngineExt securityEngine = 
        SecurityExtUtils.getEngine();

    private String intUrl;
    private String module;

    private Long hlpModuleCode;
    private Long emlModuleCode;
    private Long mapModuleCode;

    private String hlpUrl = StringUtils.EMPTY;
    private String emlUrl = StringUtils.EMPTY;
    private String mapUrl = StringUtils.EMPTY;

    public ModuleMenuExtBean() {
        intUrl = 
                JSFHelper.viewIdToUrl("/app/jsps/shared/integerationmainpage.jsf");

        module = 
                StringUtils.trimToNull(securityEngine.getModule().getAbbreviation());
        if (module != null) {
            module = module.toLowerCase();
        }
    }

    public static ModuleMenuExtBean getInstance() {
        return (ModuleMenuExtBean)JSFHelper.getValue(BEAN_NAME);
    }

    public String getInsureCreated() {
        return StringUtils.EMPTY;
    }

    public void setHlpModuleCode(Long hlpModuleCode) {
        this.hlpModuleCode = hlpModuleCode;
        hlpUrl = getModuleUrl(hlpModuleCode, true);
    }

    public Long getHlpModuleCode() {
        return hlpModuleCode;
    }

    public void setEmlModuleCode(Long emlModuleCode) {
        this.emlModuleCode = emlModuleCode;
        emlUrl = getModuleUrl(emlModuleCode, false);
    }

    public Long getEmlModuleCode() {
        return emlModuleCode;
    }

    public void setMapModuleCode(Long mapModuleCode) {
        this.mapModuleCode = mapModuleCode;
        mapUrl = getModuleUrl(mapModuleCode, true);
    }

    public Long getMapModuleCode() {
        return mapModuleCode;
    }

    public String getHlpUrl() {
        if (hlpUrl.length() == 0) {
            return StringUtils.EMPTY;
        }
        return String.format("%s/Pages/%s/index.html", hlpUrl, module);
    }

    public String getEmlUrl() {
        return emlUrl;
    }

    public String getReqAddUrl(int typeCode) {
        return String.format("%s/module/req/jsps/request/requestadd.jsf?user=%s&&typeCode=%s&&from=%s", 
                             JSFHelper.getContextPath(), getUser(), typeCode, 
                             module);
    }

    public String getReqApproveUrl(int appMakerId) {
        return String.format("%s/module/req/jsps/approval/approvallist.jsf?user=%s&&from=%s&appMakerId=%s", 
                             JSFHelper.getContextPath(), getUser(), module, 
                             appMakerId);
    }

    public String getMapListUrl(int objectType, int society) {
        return String.format("%s/module/map/jsps/map/maplist.jsf?user=%s&&from=%s&objectType=%s&society=%s", 
                             JSFHelper.getContextPath(), getUser(), module, 
                             objectType, society);
    }

    private String getModuleUrl(Long moduleCode, boolean removeIndex) {
        if (!securityEngine.isEnabled()) {
            return StringUtils.EMPTY;
        }

        ModuleDTO module = securityEngine.getModuleByCode(moduleCode);
        if (module == null) {
            return StringUtils.EMPTY;
        }

        String url = module.getUrlInMinistry();
        if (url == null || url.length() == 0) {
            return StringUtils.EMPTY;
        }

        if (removeIndex) {
            url = url.substring(0, url.lastIndexOf("/"));
        }

        return url;
    }

    private String getIntUrl(String url, String page) {
        if (url.length() == 0) {
            return StringUtils.EMPTY;
        }

        String location = String.format("%s/module/jsps/%s", url, page);
        location = EncryptHelper.encrypt(location);
        String redUrl = String.format("%s?location=%s", intUrl, location);
        return String.format("javascript:openIntWindow('%s');void(0);", 
                             redUrl);
    }

    private String getUser() {
        UserDTO user = LoginBean.getInstance().getUser();
        if (user == null) {
            return StringUtils.EMPTY;
        }
        return user.getCode().toString();
    }
}
