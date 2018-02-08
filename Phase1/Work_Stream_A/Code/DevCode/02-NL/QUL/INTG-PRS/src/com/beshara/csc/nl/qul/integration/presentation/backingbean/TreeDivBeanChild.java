package com.beshara.csc.nl.qul.integration.presentation.backingbean;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.jsfbase.csc.backingbean.TreeDivBean;

import java.util.ArrayList;
import java.util.List;

public class TreeDivBeanChild extends TreeDivBean {
    @SuppressWarnings("compatibility:-2857325980708235127")
    private static final long serialVersionUID = 1L;

    public TreeDivBeanChild() {
        super();
    }


    public List getTotalList() {
//        List totalList = null;
//
//        try {
//
//            IBasicDTO filterDTO = getFilterDTO();
//
//            if (filterDTO == null) {
//                totalList = getClient().getAll();
//            } else {
//                totalList = getClient().getAll(filterDTO);
//            }
//
//        } catch (SharedApplicationException ne) {
//            totalList = new ArrayList();
//            ne.printStackTrace();
//        } catch (DataBaseException db) {
//            db.printStackTrace();        
//        } catch (Exception e) {
//            e.printStackTrace();        
//        }
        return new ArrayList();
    }
}
