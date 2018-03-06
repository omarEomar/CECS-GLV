package com.beshara.csc.inf.business.exception;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class LastChangeInSameEmpWrkDataException extends SharedApplicationException {

/**
 *
 * @since 25/11/2015
 * @updatedBy TechnicalTeam [A.Agamy]
 * @Note added new constractors to pass exception
 * constructors generated by ExceptionHandlerProcessor
 */

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
	public static final String DEF_MSG = "LastChangeInSameEmpWrkDataException";
	public static final String MSG_KEY = "LastChangeInSameEmpWrkDataException";
	public static final String BDL_KEY = "LastChangeInSameEmpWrkDataException";

	public LastChangeInSameEmpWrkDataException(String message, Throwable e, String bundleKey, String messageKey){
	super(message, e, bundleKey, messageKey);
	}


	public LastChangeInSameEmpWrkDataException(String message, Throwable e, String messageKey){
	super(message, e, BDL_KEY, messageKey);
	}


	public LastChangeInSameEmpWrkDataException(String message, Throwable e){
	super(message, e, BDL_KEY, MSG_KEY);
	}


	public LastChangeInSameEmpWrkDataException(Throwable e, String bundleKey, String messageKey){
	super(DEF_MSG, e, bundleKey, messageKey);
	}


	public LastChangeInSameEmpWrkDataException(Throwable e, String messageKey){
	super(DEF_MSG, e, BDL_KEY, messageKey);
	}


	public LastChangeInSameEmpWrkDataException(Throwable e){
	super(DEF_MSG, e, BDL_KEY, MSG_KEY);
	}


	public LastChangeInSameEmpWrkDataException(String message, String bundleKey, String messageKey){
	super(message, bundleKey, messageKey);
	}


	public LastChangeInSameEmpWrkDataException(String message, String messageKey){
	super(message, BDL_KEY, messageKey);
	}


    public LastChangeInSameEmpWrkDataException() {

	super(DEF_MSG, BDL_KEY, MSG_KEY);

    }

	public LastChangeInSameEmpWrkDataException(String message){

	super(message, BDL_KEY, MSG_KEY);

    }
}