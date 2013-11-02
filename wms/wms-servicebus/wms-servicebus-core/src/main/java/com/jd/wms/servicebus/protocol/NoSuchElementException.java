/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class NoSuchElementException extends RuntimeException {

	private static final long serialVersionUID = -2110926633505293943L;
	
	public NoSuchElementException( String message ) {
		super( message );
	}
	
}
