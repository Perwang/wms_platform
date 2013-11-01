/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class ComponentManager {
		
	private static volatile ComponentManager _instance;
	
	private ComponentManager() {
	}
	
	public static ComponentManager getInstance() {
		if ( _instance == null ) {
			synchronized ( ComponentManager.class ) {
				if ( _instance == null ) {
					_instance = new ComponentManager();
				}
			}
		}
		return _instance;
	}
	
	private Map< String, Component > registry = new ConcurrentHashMap< String, Component >( 16 );

	public void register( String key, Component component ) {
		if ( key == null ) {
			throw new IllegalArgumentException( "The id of the component can not be null." );
		}
		if ( registry.containsKey( key ) ) {
			throw new IllegalArgumentException( "The id named [" + key + "] has exist." );
		}
		registry.put( key, component );
	}
	
	public Collection< Component > getAll() {
		return registry.values();
	}
	
	public Component lookup( String key ) {
		if ( key == null || "".equals( key ) ) {
			throw new IllegalArgumentException( "The key can not be null." );
		}
		return registry.get( key );
	}
	
	public Component remove( String key ) {
		if ( key == null || "".equals( key ) ) {
			throw new IllegalArgumentException( "The key can not be null." );
		}
		return registry.remove( key );
	}
	
	public void start( String key ) throws Exception {
		final Component component = lookup( key );
		if ( component == null ) {
			throw new NosuchComponentException( "No such component named [" + key + "]" );
		}
		if ( component.getActivator() == null ) {
			return ;
		}
		component.getActivator().doStart();
	}
	
	public void stop( String key ) throws Exception {
		final Component component = lookup( key );
		if ( component == null ) {
			throw new NosuchComponentException( "No such component named [" + key + "]" );
		}
		component.getActivator().doStop();
	}
	
	public void startAll() throws Exception {
		final Iterator< String > iter = registry.keySet().iterator();
		while ( iter.hasNext() ) {
			String key = iter.next();
			start( key );
		}
	}
	
	public void stopAll() throws Exception {
		final Iterator< String > iter = registry.keySet().iterator();
		while ( iter.hasNext() ) {
			String key = iter.next();
			stop( key );
		}
	}
	
}
