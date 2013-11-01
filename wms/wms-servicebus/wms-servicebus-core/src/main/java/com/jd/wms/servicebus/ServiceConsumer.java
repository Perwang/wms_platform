/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 18, 2013
 */
public interface ServiceConsumer {
	
	public < T, R > R consume( String serviceName, T t ) throws Exception;

}
