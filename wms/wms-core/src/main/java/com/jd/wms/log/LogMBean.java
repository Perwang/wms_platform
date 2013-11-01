/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.log;

import java.util.Enumeration;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public interface LogMBean {
	
	public void init();
	
	public < T > Enumeration< T > getCurrentLoggers();
		
	public void setLevel( String name, int l );

}
