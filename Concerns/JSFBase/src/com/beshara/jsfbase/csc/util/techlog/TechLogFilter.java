package com.beshara.jsfbase.csc.util.techlog;


import com.beshara.base.techlog.TechLogHelper;
import com.beshara.base.techlog.TechLogRequest;
import com.beshara.common.factory.config.FactoryInstanceConfig;
import com.beshara.common.shared.SharedUtils;
import com.beshara.common.web.filter.WebFilter;
import com.beshara.common.web.filter.WebFilterEvent;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;


public class TechLogFilter implements WebFilter {
    
    private static final String TECH_LOG_START_TIME_ATTR = "TECH_LOG_START_TIME";
    
    public TechLogFilter() {
        super();
    }

    @Override
    public void initialize(FactoryInstanceConfig config) {
    }

    @Override
    public void service(WebFilterEvent event) {
        HttpServletRequest request = event.getRequest();
        if(event.isStartingRequest()) {
            request.setAttribute(TECH_LOG_START_TIME_ATTR, SharedUtils.getTimestamp());
        } else {
            Timestamp startTime = (Timestamp)request.getAttribute(TECH_LOG_START_TIME_ATTR);
            log(startTime, event.getException());
        }
    }

    private void log(Timestamp startTime, Throwable exp) {
        if (!TechLogHelper.isEnabled()) {
            return;
        }
        Timestamp endTime = SharedUtils.getTimestamp();
        TechLogRequest tlr = TechLogHelper.newRequest();
        TechLogHelper.load(tlr);
        tlr.setStartTime(startTime);
        tlr.setEndTime(endTime);
        TechLogHelper.setExpection(tlr, exp);
        TechLogHelper.log(tlr);
    }

    @Override
    public void terminate() {
    }
}
