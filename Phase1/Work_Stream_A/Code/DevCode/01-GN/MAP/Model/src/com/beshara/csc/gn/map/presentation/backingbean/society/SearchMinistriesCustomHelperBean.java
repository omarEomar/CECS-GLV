package com.beshara.csc.gn.map.presentation.backingbean.society;

import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.integration.presentation.backingbean.ministry.SearchMinistriesHelperBean;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class SearchMinistriesCustomHelperBean extends SearchMinistriesHelperBean {
    public SearchMinistriesCustomHelperBean(String pageBeanName) {
        super(pageBeanName,null);
    }
    
    @Override
    protected void fillCategoriesList() {
        try {
            setCategoriesList( OrgClientFactory.getCatsClient().getAll());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
        }
    }
    
}
