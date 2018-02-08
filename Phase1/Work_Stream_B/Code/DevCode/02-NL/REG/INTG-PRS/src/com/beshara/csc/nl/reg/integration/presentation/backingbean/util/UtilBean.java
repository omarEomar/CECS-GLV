package com.beshara.csc.nl.reg.integration.presentation.backingbean.util;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.ISystemSettingsDTO;
import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;

import java.util.List;
import java.util.regex.Pattern;

public class UtilBean {
    public UtilBean() {
        super();
    }
    
    
    public static Long getMappedCodeBySysSettingCode(Long systemSettingCode) {
        try {
            List<IBasicDTO> list = InfClientFactory.getSystemSettingsClient().searchByCode(systemSettingCode);
            ISystemSettingsDTO systemSettingsDTO = (ISystemSettingsDTO)list.get(0);
            return Long.valueOf(systemSettingsDTO.getSyssettingValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Pattern getSimilerCharRegexPattern(String searchedString) {
        String[] tokens = searchedString.split("[\\s%]+");
        String pattern = ".*";
        String temp = null;
        for (String token: tokens) {
            if (!token.equals("")) {
                temp = new String(token);
                String checkStartWith = temp.substring(0, 1);
                String checkEndWith = 
                    temp.substring(temp.length() - 1, temp.length());
                if ((checkStartWith.equals(ArabicAlphabetConstants.AlephWithOutHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithUpperHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithLowerHamza + 
                                           "") || 
                     checkStartWith.equals(ArabicAlphabetConstants.AlephWithMadda + 
                                           "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.AlephWithOutHamza + "" + 
                        ArabicAlphabetConstants.AlephWithUpperHamza + "" + 
                        ArabicAlphabetConstants.AlephWithLowerHamza + "" + 
                        ArabicAlphabetConstants.AlephWithMadda + "" + "]";
                    temp = temp.substring(1);
                    temp = aleph + temp;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Haa + "") || 
                     checkEndWith.equals(ArabicAlphabetConstants.TaaMarbota + 
                                         "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.Haa + "" + ArabicAlphabetConstants.TaaMarbota + 
                        "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                if ((checkEndWith.equals(ArabicAlphabetConstants.Yaa + "") || 
                     checkEndWith.equals(ArabicAlphabetConstants.AlephMaksora + 
                                         "")) && temp.length() >= 1) {
                    String aleph = 
                        "[" + ArabicAlphabetConstants.Yaa + "" + ArabicAlphabetConstants.AlephMaksora + 
                        "" + "]";
                    temp = temp.substring(0, temp.length() - 1);
                    temp = temp + aleph;
                }
                pattern = pattern + temp + ".*";

                if (searchedString.startsWith("%") && 
                    !searchedString.endsWith("%")) {
                    pattern = pattern.substring(0, pattern.length() - 2);
                } else {
                    if (!searchedString.startsWith("%") && 
                        searchedString.endsWith("%")) {
                        pattern = pattern.substring(2);
                    }
                }
            }
        }


        Pattern searchStringPatten = Pattern.compile(pattern);
        return searchStringPatten;
    }
}
