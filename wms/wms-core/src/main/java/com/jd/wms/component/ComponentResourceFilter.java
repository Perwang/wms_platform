/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.component;

import java.io.File;
import java.util.jar.JarFile;

import com.jd.wms.constant.ManifestKeys;
import com.jd.wms.resource.ResourceFilter;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class ComponentResourceFilter implements ResourceFilter {
	
	/*
	 * (non-Javadoc)
	 * @see com.jd.wms.resource.ResourceFilter#doFilter(java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T, R > R doFilter( T t ) throws Exception {
		if ( ! ( t instanceof File ) ) {
			throw new IllegalArgumentException( "The argument must be an instance of java.io.File." );
		}
		final JarFile jar = new JarFile( ( File ) t );
		final String componentName = jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_NAME );
		if ( componentName == null || "".equals( componentName ) ) {
			return ( R ) Boolean.FALSE;
		}
		final Component component = new Component( componentName, 
				jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_VERSION ) );
		component.setFilePath( ( ( File ) t ).getAbsolutePath() );
		final String activatorClass = jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_ACTIVATOR );
		if ( activatorClass != null && ! "".equals( activatorClass ) ) {
			component.setActivatorClass( activatorClass );
		}
		ComponentManager.getInstance().register( component.getId(), component );
		return ( R ) Boolean.TRUE;
	}

}
