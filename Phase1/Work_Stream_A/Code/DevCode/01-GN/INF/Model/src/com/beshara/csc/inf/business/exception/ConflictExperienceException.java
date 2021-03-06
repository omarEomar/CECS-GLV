package com.beshara.csc.inf.business.exception;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.ApplicationException;

 
public class ConflictExperienceException extends SharedApplicationException {

    /**
     *
     * @since 08/02/2017
     * @AddeddBy M.abdelsabour B>H Team
     * @Note added new constractors to pass exception
     * constructors generated by ExceptionHandlerProcessor
     */
    @SuppressWarnings("compatibility:4646714579605866321")
    private static final long serialVersionUID = 1L;
    public static final String DEF_MSG = "ConflictExperienceException";
    public static final String MSG_KEY = "ConflictExperienceException";
    public static final String BDL_KEY = "ConflictExperienceException";

    public ConflictExperienceException(String message, Throwable e, String bundleKey, String messageKey) {
        super(message, e, bundleKey, messageKey);
    }


    public ConflictExperienceException(String message, Throwable e, String messageKey) {
        super(message, e, BDL_KEY, messageKey);
    }


    public ConflictExperienceException(String message, Throwable e) {
        super(message, e, BDL_KEY, MSG_KEY);
    }


    public ConflictExperienceException(Throwable e, String bundleKey, String messageKey) {
        super(DEF_MSG, e, bundleKey, messageKey);
    }


    public ConflictExperienceException(Throwable e, String messageKey) {
        super(DEF_MSG, e, BDL_KEY, messageKey);
    }


    public ConflictExperienceException(Throwable e) {
        super(DEF_MSG, e, BDL_KEY, MSG_KEY);
    }


    public ConflictExperienceException(String message, String bundleKey, String messageKey) {
        super(message, bundleKey, messageKey);
    }


    public ConflictExperienceException(String message, String messageKey) {
        super(message, BDL_KEY, messageKey);
    }


    public ConflictExperienceException() {

        super(DEF_MSG, BDL_KEY, MSG_KEY);

    }

    public ConflictExperienceException(String message) {

        super(message, BDL_KEY, MSG_KEY);

    }
}

