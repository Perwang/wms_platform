/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.spi.ws;

import com.jd.wms.servicebus.protocol.Connection;
import com.jd.wms.servicebus.protocol.spi.ws.config.Realization;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 2, 2013
 */
public class WsRealizationFactory {
	
	private static volatile WsRealizationFactory _instance;
	
	private WsRealizationFactory() {
	}
	
	public static WsRealizationFactory getInstance() {
		if ( _instance == null ) {
			synchronized ( WsRealizationFactory.class ) {
				if ( _instance == null ) {
					_instance = new WsRealizationFactory();
				}
			}
		}
		return _instance;
	}

	public Connection createAgent( Realization r ) {
		return null;
	}
	
	public Connection createProxy( Realization r ) {
		return null;
	}
	
}
