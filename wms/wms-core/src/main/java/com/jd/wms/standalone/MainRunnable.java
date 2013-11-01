/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.standalone;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Iterator;

import com.jd.wms.component.Component;
import com.jd.wms.component.ComponentManager;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class MainRunnable implements Runnable {
	
	private ClassLoader parent;
	
	/**
	 * @param urls
	 */
	public MainRunnable( ClassLoader parent ) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println( "------------------------>The platform is starting..." );
		final long beginTime = System.currentTimeMillis();
		final Collection< Component > components = ComponentManager.getInstance().getAll();
		System.out.println( "------------------------>Current component count is " + components.size() );
		Iterator< Component > iter = components.iterator();
		final URL[] cUrls = new URL[ components.size() ];
		int i = 0;
		while ( iter.hasNext() ) {
			try {
				cUrls[ i++ ] = new File( iter.next().getFilePath() ).toURI().toURL();
			} catch ( MalformedURLException e ) {
				e.printStackTrace();
				System.exit( 0 );
			}
		}
		final ClassLoader componentCl = new URLClassLoader( cUrls, this.parent );
		iter = components.iterator();
		Component component = null;
		while ( iter.hasNext() ) {
			component = iter.next();
			component.setClassLoader( componentCl );
		}
		try {
			ComponentManager.getInstance().startAll();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		final long endTime= System.currentTimeMillis();
		System.out.println( "------------------------>The platform has been started. use time [" + ( endTime - beginTime ) + "ms]" );
	}

}
