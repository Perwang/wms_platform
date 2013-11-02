/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.io.File;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jd.wms.Env;
import com.jd.wms.servicebus.protocol.plugin.ProtocolPlugin;
import com.jd.wms.servicebus.protocol.plugin.ProtocolPlugins;

/**
 * 通讯插件管理器
 * <p>
 * 用于管理通讯插件。例如：TCP， WS，HTTP等
 * </p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class ProtocolPluginManager {

	private static volatile ProtocolPluginManager _instance;
	
	private ProtocolPluginManager() {
	}
	
	public static ProtocolPluginManager getInstance() {
		if ( _instance == null ) {
			synchronized ( ProtocolPluginManager.class ) {
				if ( _instance == null ) {
					_instance = new ProtocolPluginManager();
				}
			}
		}
		return _instance;
	}
	
	/**
	 * 
	 */
	public void start() throws Exception {
		StringBuilder plugin_conf_path = new StringBuilder( Env.getInstance().getConfPath() );
		plugin_conf_path.append( "protocol" ).append( File.separator ).append( "plugins.xml" );
		final File plugin_conf_file = new File( plugin_conf_path.toString() );
		final JAXBContext context = JAXBContext.newInstance( ProtocolPlugins.class.getPackage().getName() );
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		this.plugins = ( ProtocolPlugins ) unmarshaller.unmarshal( plugin_conf_file );
	}

	public Map< String, ProtocolPlugin > plugins;
	
	public ProtocolPlugin lookup( String key ) {
		if ( ! plugins.containsKey( key ) ) {
			throw new NoSuchElementException( "No such plugin named [" + key + "]." );
		}
		return plugins.get( key );
	}
	
}
