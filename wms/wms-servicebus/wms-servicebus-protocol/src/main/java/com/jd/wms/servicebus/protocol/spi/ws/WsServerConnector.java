/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.spi.ws;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.wms.servicebus.protocol.Connection;
import com.jd.wms.servicebus.protocol.config.ProtocolConfig;
import com.jd.wms.servicebus.protocol.spi.ws.config.Realization;
import com.jd.wms.servicebus.protocol.spi.ws.config.WsCommonsConfig;
import com.jd.wms.servicebus.protocol.spi.ws.config.WsProtocolConfig;

/**
 * WebService服务端连接器
 * <p>
 * 实现<CODE>Connection</CODE>接口
 * </p>
 * @author liubing
 * Date Nov 2, 2013
 */
public class WsServerConnector implements Connection {
	
	private static Logger LOG = LoggerFactory.getLogger( WsServerConnector.class );
	
	private AtomicBoolean running = new AtomicBoolean( false );

	private WsProtocolConfig config;
		
	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return ! running.get();
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return running.get();
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#receive()
	 */
	@Override
	public < R > R receive() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#send(java.lang.Object)
	 */
	@Override
	public < T, R > R send( T t ) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#start()
	 */
	@Override
	public void start() throws Exception {
		if ( running.get() ) {
			LOG.warn( "The instance of this protocol has been started." );
			return ;
		}
		if ( getConfig() == null ) {
			throw new IllegalArgumentException( "The config can not be null." );
		}
		final Realization rm = ( ( WsCommonsConfig ) getConfig().getCommons() ).getImpl();
		WsRealizationFactory.getInstance().create( rm );
		
		running.set( true );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#stop()
	 */
	@Override
	public void stop() throws Exception {
		if ( isShutdown() ) {
			LOG.warn( "The instance of this protocol has been stopped." );
			return ;
		}
		
		running.set( false );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#getConfig()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T extends ProtocolConfig > T getConfig() {
		return ( T ) this.config;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#setConfig(com.jd.wms.servicebus.protocol.config.ProtocolConfig)
	 */
	@Override
	public < T extends ProtocolConfig > void setConfig( T t ) {
		this.config = ( WsProtocolConfig ) t;
	}

}
