/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.standalone;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.jd.wms.Env;
import com.jd.wms.component.ComponentResourceFilter;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class StandAloneMain {
	
	private static ExecutorService single = Executors.newSingleThreadExecutor( new ThreadFactory() {

		@Override
		public Thread newThread( Runnable r ) {
			return new Thread( r, "StandAlone-Thread" );
		}
		
	} );
	
	private ClassLoader parent;
			
	public void main( ClassLoader classLoader, URL[] urls, String installPath, String... args ) throws Exception {
		this.parent = classLoader;
		Env.getInstance().init( installPath );
		doStart( urls, args );
	}

	/**
	 * @param args
	 */
	private void doStart( URL[] urls, String[] args ) throws Exception {
		final ComponentResourceFilter crf = new ComponentResourceFilter();
		final List< URL > commonUrls = new LinkedList< URL >();
		File file = null;
		URL url = null;
		for ( int i = 0; i < urls.length; i++ ) {
			url = urls[ i ];
			System.out.println( url.toString() );
			file = new File( url.toURI() );
			if ( ! ( ( Boolean ) crf.doFilter( file ) ) ) {
				commonUrls.add( url );
			}
		}
		final URL[] commonUrlArray = new URL[ commonUrls.size() ];
		commonUrls.toArray( commonUrlArray );
		commonUrls.clear();
		final ClassLoader commonsCl = new URLClassLoader( commonUrlArray, this.parent );
		
		single.execute( new MainRunnable( commonsCl ) );
	}

}
