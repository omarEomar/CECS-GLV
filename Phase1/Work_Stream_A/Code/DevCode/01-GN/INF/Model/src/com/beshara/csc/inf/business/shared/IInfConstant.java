package com.beshara.csc.inf.business.shared;

public interface IInfConstant {
    
    public static final Long INf_NON_NATIONALITY = Long.valueOf(0); 
    
    public static final Long ALL_SELECTIONS = Long.valueOf(0); 
    public static final Long HANDICAPD = Long.valueOf(1);
    public static final Long CAPTIVE_SON = Long.valueOf(2);
    public static final Long MARTYR_SON = Long.valueOf(3); 
        public static final Long WIDOW_OF_A_MARTYR = Long.valueOf(4); 
        public static final Long CAPTIVE_WIFE = Long.valueOf(5); 
        public static final Long WIFE_MISSING = Long.valueOf(6); 
    public static final Long KWT_MOTHER = Long.valueOf(13); 
    
    
    public static final Long FATHER_FROM_SOLDER = Long.valueOf(14); 
    public static final Long HOLIDAY_TYPE_CODE = Long.valueOf(10); 
    public static final String WEB_SERVICES_PARAM_GENDER_TYPE= "1";
    public static final String WEB_SERVICES_PARAM_NATIONALITY_TYPE= "69";
    
    Long CURRENT_QAULIFICATION_STATUS_ZERO = 0L;
    Long CURRENT_QAULIFICATION_STATUS_ONE = 1L;
    
    public static final Long CHANGE_TYPE_JOBCODE=1L;
    public static final Long CHANGE_TYPE_WRKCODE=2L;
    public static final Long CHANGE_TYPE_OTHERJOBCODE=3L;
    public static final Long CHANGE_TYPE_WRKCODE_JOBCODE=4L;
}
