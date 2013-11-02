/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.plugin;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
@XmlRootElement( name = "plugins" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProtocolPlugins implements Map< String, ProtocolPlugin > {
	@XmlElement( name = "plugin" )
	private List< ProtocolPlugin > pluginArray = new LinkedList< ProtocolPlugin >();
	@XmlTransient
	private Map< String, ProtocolPlugin > plugins = new ConcurrentHashMap< String, ProtocolPlugin >( 16 );
	
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return plugins.size();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return plugins.isEmpty();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey( Object key ) {
		return plugins.containsKey( key );
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue( Object value ) {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public ProtocolPlugin get( Object key ) {
		return plugins.get( key );
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public ProtocolPlugin put( String key, ProtocolPlugin value ) {
		return plugins.put( key, value );
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public ProtocolPlugin remove( Object key ) {
		return plugins.remove( key );
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll( Map< ? extends String, ? extends ProtocolPlugin > m ) {
		plugins.putAll( m );
	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		plugins.clear();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set< String > keySet() {
		return plugins.keySet();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection< ProtocolPlugin > values() {
		return plugins.values();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set< java.util.Map.Entry< String, ProtocolPlugin >> entrySet() {
		return plugins.entrySet();
	}
	
	protected void afterUnmarshal( Unmarshaller unmarshaller, Object parent ) {
		if ( this.pluginArray == null || this.pluginArray.size() == 0 ) {
			return ;
		}
		ProtocolPlugin plugin = null;
		for ( int i = pluginArray.size() - 1; i >= 0 ; i-- ) {
			plugin = pluginArray.remove( i );
			put( plugin.getProtocol(), plugin );
		}
		pluginArray.clear();
	}
	
	protected void beforeMarshal( Marshaller marshaller ) {
		if ( this.plugins == null || size() == 0 ) {
			return ;
		}
		this.pluginArray.addAll( this.plugins.values() );
	}

}
