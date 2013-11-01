/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public abstract class GenericActivator {
	
	protected AtomicBoolean running = new AtomicBoolean( false );
	
	public void doStart() throws Exception {
		if ( running.get() ) {
			return ;
		}
		start();
		running.set( true );
	}
	
	public void doStop() throws Exception {
		if ( ! running.get() ) {
			return ;
		}
		stop();
		running.set( false );
	}
	
	protected abstract void start() throws Exception;
	
	protected abstract void stop() throws Exception;
	
}
