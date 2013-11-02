/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.wms.Env;
import com.jd.wms.servicebus.protocol.config.ProtocolConfig;
import com.jd.wms.servicebus.protocol.plugin.ProtocolPlugin;

/**
 * 协议配置管理类
 * <p>
 * 注册当前所有协议实例配置
 * </p>
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
	
	private Map< String, ProtocolConfig > registry = new ConcurrentHashMap< String, ProtocolConfig >( 16 );

	public void readAll() {
		final File configDir = new File( Env.getInstance().getConfPath() + "protocol/configuration" );
		if ( ! configDir.exists() ) {
			return ;
		}
		final File[] files = configDir.listFiles();
		final XMLInputFactory xif = XMLInputFactory.newFactory();
		XMLStreamReader xsr = null;
		String protocol = null;
		FileInputStream fis = null;
		ProtocolConfig config = null;
		for ( File file : files ) {
			try {
				fis = new FileInputStream( file );
				xsr = xif.createXMLStreamReader( fis );
				if ( ! xsr.hasNext() ) {
					continue ;
				}
				xsr.next();
				protocol = xsr.getLocalName().toString();
			} catch ( FileNotFoundException e ) {
				LOG.error( e.getMessage(),  e );
			} catch ( XMLStreamException e ) {
				LOG.error( e.getMessage(),  e );
			} catch ( Exception e ) {
				LOG.error( e.getMessage(),  e );
			} finally {
				if ( xsr != null ) {
					try {
						xsr.close();
					} catch ( XMLStreamException e ) {
						LOG.error( e.getMessage(),  e );
					}
					xsr = null;
				}
			}
			try {
				fis = new FileInputStream( file );
				config = readByProtocol( protocol, fis );
			} catch ( Exception e ) {
				e.printStackTrace();
			}
			String id = file.getName().substring( 0, file.getName().lastIndexOf( "." ) );
			registry.put( id, config );
		}
	}
	
	/**
	 * @param tagName
	 * @param xsr
	 */
	private ProtocolConfig readByProtocol( String protocol, InputStream is ) throws Exception {
		if ( protocol == null || "".equals( protocol ) ) {
			throw new IllegalArgumentException( "The prtocol string can not be null." );
		}
		protocol = protocol.substring( 
				protocol.lastIndexOf( "." ) + 1, protocol.length() );
		final ProtocolPlugin plugin = ProtocolPluginManager.getInstance().lookup( protocol );
		final ProtocolConfigProcessor processor = plugin.getConfigProcessor();
		ProtocolConfig config = new ProtocolConfig();
		processor.read( is, config );
		return config;
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

	/**
	 * 
	 */
	public Map< String, ProtocolConfig > getAll() {
		return this.registry;
	}
	
}
