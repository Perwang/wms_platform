/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.component;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class InvalidAttributeValueException extends Exception {

	private static final long serialVersionUID = 893621038372582523L;
	
	public InvalidAttributeValueException( String message ) {
		super( message );
	}

}
