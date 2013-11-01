/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.log;

import com.jd.wms.component.GenericActivator;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 29, 2013
 */
public class Activator extends GenericActivator {

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#start()
	 */
	@Override
	protected void start() throws Exception {
		Log4jMBean logMBean = new Log4jMBean();
		logMBean.init();
		LoggerManager.getInstance().register( logMBean );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#stop()
	 */
	@Override
	protected void stop() throws Exception {
		
	}

}
