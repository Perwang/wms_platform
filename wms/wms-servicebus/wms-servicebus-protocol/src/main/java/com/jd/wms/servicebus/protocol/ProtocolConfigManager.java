/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.wms.Env;
import com.jd.wms.servicebus.protocol.config.ProtocolConfig;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class ProtocolConfigManager {
	
	private static Logger LOG = LoggerFactory.getLogger( ProtocolConfigManager.class );
	
	private static volatile ProtocolConfigManager _instance;
		
	private ProtocolConfigManager() {
	}
	
	public static ProtocolConfigManager getInstance() {
		if ( _instance == null ) {
			synchronized (  ProtocolConfigManager.class ) {
				if ( _instance == null ) {
					_instance = new ProtocolConfigManager();
				}
			}
		}
		return _instance;
	}
	
	private Map< String, ProtocolConfig > configs = new ConcurrentHashMap< String, ProtocolConfig >( 16 );

	public void readConfigs() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance( createJaxbContextPath(), 
					this.getClass().getClassLoader() );
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final File configDir = new File( Env.getInstance().getConfPath() + "protocol/" );
			if ( ! configDir.exists() ) {
				return ;
			}
			final File[] files = configDir.listFiles();
			String id = null;
			for ( File file : files ) {
				ProtocolConfig config = ( ProtocolConfig ) unmarshaller.unmarshal( file );
				String fileName = file.getName();
				id = fileName.substring( 0, fileName.lastIndexOf( "." ) );
				configs.put( id, config );
			}
		} catch ( JAXBException e ) {
			LOG.error( e.getMessage(), e );
		}		
	}
	
	private static List< String > packageNames = new LinkedList< String >();

	/**
	 * @param name
	 */
	public void addPackageName( String name ) {
		packageNames.add( name );
	}
	
	public String createJaxbContextPath() {
		final StringBuffer buffer = new StringBuffer( 16 );
		buffer.append( ProtocolConfig.class.getPackage().getName() );
		for ( String packageName : packageNames ) {
			buffer.append( ":" );
			buffer.append( packageName );
		}
		return buffer.toString();
	}
	
}
