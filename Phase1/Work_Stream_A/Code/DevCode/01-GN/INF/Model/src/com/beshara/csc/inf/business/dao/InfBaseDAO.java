package com.beshara.csc.inf.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.csc.sharedutils.business.util.querybuilder.ArabicAlphabetConstants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class InfBaseDAO extends BaseDAO {

    public InfBaseDAO() {
        super();
    }

    @Override
    protected String getEMJNDI() {
        return "em/BSC";
    }
    
    
    public static String getNativeSqlSimilarCharsCondition(String coloumnName, 
                                             String searchValue) {

        List<String> condtions = new ArrayList<String>();
        List<String> tempCond = new ArrayList<String>();
        StringBuilder condtionString = new StringBuilder("");

        if (searchValue != null || searchValue.length() != 0) {
            String[] tokens = searchValue.split("[\\s%]+");
            for (String token: tokens) {
                List<String> tokenCases = new ArrayList<String>();
                List<String> tempTokenCases = new ArrayList<String>();

                tokenCases.add(token);

                if (token.startsWith(Character.toString(ArabicAlphabetConstants.AlephWithLowerHamza)) || 
                    token.startsWith(Character.toString(ArabicAlphabetConstants.AlephWithMadda)) || 
                    token.startsWith(Character.toString(ArabicAlphabetConstants.AlephWithOutHamza)) || 
                    token.startsWith(Character.toString(ArabicAlphabetConstants.AlephWithUpperHamza))) {

                    for (String tokenCase: tokenCases) {
                        tempTokenCases.add(Character.toString(ArabicAlphabetConstants.AlephWithLowerHamza) + 
                                           tokenCase.substring(1));
                        tempTokenCases.add(Character.toString(ArabicAlphabetConstants.AlephWithUpperHamza) + 
                                           tokenCase.substring(1));
                        tempTokenCases.add(Character.toString(ArabicAlphabetConstants.AlephWithMadda) + 
                                           tokenCase.substring(1));
                        tempTokenCases.add(Character.toString(ArabicAlphabetConstants.AlephWithOutHamza) + 
                                           tokenCase.substring(1));
                    }
                    tokenCases.clear();
                    tokenCases.addAll(tempTokenCases);
                    tempTokenCases.clear();
                }
                if (token.endsWith(Character.toString(ArabicAlphabetConstants.TaaMarbota)) || 
                    token.endsWith(Character.toString(ArabicAlphabetConstants.Haa))) {
                    for (String tokenCase: tokenCases) {
                        tempTokenCases.add(tokenCase.substring(0, 
                                                               tokenCase.length() - 
                                                               1) + 
                                           Character.toString(ArabicAlphabetConstants.TaaMarbota));
                        tempTokenCases.add(tokenCase.substring(0, 
                                                               tokenCase.length() - 
                                                               1) + 
                                           Character.toString(ArabicAlphabetConstants.Haa));
                    }
                    tokenCases.clear();
                    tokenCases.addAll(tempTokenCases);
                    tempTokenCases.clear();
                }
                if (token.endsWith(Character.toString(ArabicAlphabetConstants.Yaa)) || 
                    token.endsWith(Character.toString(ArabicAlphabetConstants.AlephMaksora))) {
                    for (String tokenCase: tokenCases) {
                        tempTokenCases.add(tokenCase.substring(0, 
                                                               tokenCase.length() - 
                                                               1) + 
                                           Character.toString(ArabicAlphabetConstants.Yaa));
                        tempTokenCases.add(tokenCase.substring(0, 
                                                               tokenCase.length() - 
                                                               1) + 
                                           Character.toString(ArabicAlphabetConstants.AlephMaksora));
                    }
                    tokenCases.clear();
                    tokenCases.addAll(tempTokenCases);
                    tempTokenCases.clear();
                }

                if (condtions.size() != 0) {
                    for (String cond: condtions) {
                        for (String tokenCase: tokenCases) {
                            tempCond.add(cond + " " + tokenCase);
                        }
                    }
                } else {
                    tempCond.addAll(tokenCases);
                }

                condtions.clear();
                condtions.addAll(tempCond);
                tempCond.clear();
            }


            Iterator<String> iter = condtions.iterator();
            condtionString.append(" " + coloumnName + " = '" + iter.next()  + 
                                  "'");

            while (iter.hasNext()) {
                condtionString.append(" OR " + coloumnName + " = '" + iter.next() + "'");
            }

        } else {
            condtionString.append(" " + coloumnName + "=" + coloumnName);
        }
         return " ("+condtionString.toString()+") ";
    }
}