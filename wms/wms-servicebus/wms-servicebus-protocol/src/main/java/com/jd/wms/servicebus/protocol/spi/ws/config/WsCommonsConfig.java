/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.spi.ws.config;

import javax.xml.bind.annotation.XmlElement;

import com.jd.wms.servicebus.protocol.config.CommonsConfig;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class WsCommonsConfig extends CommonsConfig {
	@XmlElement( name = "impl" )
	private Realization impl;

	/**
	 * @return the impl
	 */
	public Realization getImpl() {
		return impl;
	}

	/**
	 * @param impl the impl to set
	 */
	public void setImpl( Realization impl ) {
		this.impl = impl;
	}
	
}
