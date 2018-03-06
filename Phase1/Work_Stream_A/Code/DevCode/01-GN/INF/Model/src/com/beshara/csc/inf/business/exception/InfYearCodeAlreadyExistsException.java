package com.beshara.csc.inf.business.exception;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.ApplicationException;
 
public class InfYearCodeAlreadyExistsException extends SharedApplicationException {

/**
 *
 * @since 25/11/2015
 * @updatedBy TechnicalTeam [A.Agamy]
 * @Note added new constractors to pass exception
 * constructors generated by ExceptionHandlerProcessor
 */

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
	public static final String DEF_MSG = "InfYearCodeAlreadyExistsException";
	public static final String MSG_KEY = "InfYearCodeAlreadyExistsException";
	public static final String BDL_KEY = "InfYearCodeAlreadyExistsException";

	public InfYearCodeAlreadyExistsException(String message, Throwable e, String bundleKey, String messageKey){
	super(message, e, bundleKey, messageKey);
	}


	public InfYearCodeAlreadyExistsException(String message, Throwable e, String messageKey){
	super(message, e, BDL_KEY, messageKey);
	}


	public InfYearCodeAlreadyExistsException(String message, Throwable e){
	super(message, e, BDL_KEY, MSG_KEY);
	}


	public InfYearCodeAlreadyExistsException(Throwable e, String bundleKey, String messageKey){
	super(DEF_MSG, e, bundleKey, messageKey);
	}


	public InfYearCodeAlreadyExistsException(Throwable e, String messageKey){
	super(DEF_MSG, e, BDL_KEY, messageKey);
	}


	public InfYearCodeAlreadyExistsException(Throwable e){
	super(DEF_MSG, e, BDL_KEY, MSG_KEY);
	}


	public InfYearCodeAlreadyExistsException(String message, String bundleKey, String messageKey){
	super(message, bundleKey, messageKey);
	}


	public InfYearCodeAlreadyExistsException(String message, String messageKey){
	super(message, BDL_KEY, messageKey);
	}


    public InfYearCodeAlreadyExistsException() {

	super(DEF_MSG, BDL_KEY, MSG_KEY);

    }

	public InfYearCodeAlreadyExistsException(String message){

	super(message, BDL_KEY, MSG_KEY);

    }
}