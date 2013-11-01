/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.launcher;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 29, 2013
 */
public class Launcher {
		
	private String _install;

	private ClassLoader classLoader;

	private Class< ? > mainClazz;
	
	public static void main( String... args ) {
		final Launcher startup = new Launcher();
		startup.start( args );
	}
	
	public void start( String... args ) {
		System.out.println( "=========================================" );
		final SecurityManager sm = System.getSecurityManager();
		if ( sm != null ) {
			sm.checkPermission( new RuntimePermission( "getProtectionDomain" ) );
		}
		final URL url = Launcher.class.getProtectionDomain().getCodeSource().getLocation();
		File codeSource = null;
		try {
			codeSource = new File( url.toURI() );
		} catch ( URISyntaxException e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
		this._install = codeSource.getParentFile().getAbsolutePath();
		System.out.println( ">The intall path is " + this._install );
		final String libPath = this._install.concat( File.separator ).concat( "lib" ).concat( File.separator );
		try {
			final List< URL > urls = frt( new File( libPath ) );
			if ( this.mainClazz == null ) {
				System.err.println( "====================Error=====================" );
				System.err.println( "No main class has been found.The system exist." );
				System.err.println( "==============================================" );
				System.exit( 0 );
			}
			final URL[] urlArray = new URL[ urls.size() ];
			urls.toArray( urlArray );
			urls.clear();
			final Method main = this.mainClazz.getDeclaredMethod( "main", ClassLoader.class, URL[].class, 
					String.class, String[].class );
			invokeMain( mainClazz.newInstance(), main, urlArray, args );
		} catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	/**
	 * @param main
	 */
	private void invokeMain( Object obj, Method main, URL[] urls, String[] args ) {
		try {
			main.invoke( obj, this.classLoader, urls, this._install, args );
		} catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}
	
	private List< URL > frt( File root ) throws Exception {
		final List< URL > urls = new LinkedList< URL >();
		final List< StackItem > stack = new LinkedList< StackItem >();
		if ( root.isFile() ) {
			fileFilter( root, urls );
			return urls;
		} else {
			stack.add( 0, new StackItem( root.listFiles(), -1 ) );
			StackItem item = null;
			int i = 0;
			File[] files = null;
			while ( ! stack.isEmpty() ) {
				item = stack.remove( 0 );
				files = item.getFiles();
				i = item.getIndex();
				i++;
				
				for ( ; files != null && i < files.length; i++ ) {
					if ( files[ i ].isDirectory() ) {
						stack.add( 0, new StackItem( files, i ) );
						files = files[ i ].listFiles();
						i = -1;
						continue ;
					}
					fileFilter( files[ i ], urls );
				}
			}
		}
		return urls;
	}
	
	private void fileFilter( File file, List< URL > urls ) throws Exception {
		if ( file.getName().endsWith( ".jar" ) ) {
			if ( this.mainClazz == null ) {
				jarFilter( file );
			}
			urls.add( file.toURI().toURL() );
		}
	}
	
	private void jarFilter( File file ) throws Exception {
		final JarFile jar = new JarFile( file );
		final Enumeration< JarEntry > entries = jar.entries();
		JarEntry entry = null;
		String name = null;
		while ( entries.hasMoreElements() ) {
			entry = entries.nextElement();
			if ( entry.isDirectory() ) {
				continue ;
			}
			name = entry.getName();
			if ( name.endsWith( ".class" )  ) {
				name = entry.getName().replaceAll( "/", "." ).substring( 
						0, entry.getName().lastIndexOf( "." ) );
				if ( name.equals( "com.jd.wms.standalone.StandAloneMain" ) ) {
					this.classLoader = new URLClassLoader( new URL[] { file.toURI().toURL() }, 
							ClassLoader.getSystemClassLoader() );
					this.mainClazz = this.classLoader.loadClass( name );
					break;
				}
			}
		}
	}
	
	class StackItem {
		private File[] files;
		
		private int index;
		
		StackItem( File[] files, int index ) {
			this.files = files;
			this.index = index;
		}

		public File[] getFiles() {
			return files;
		}

		public int getIndex() {
			return index;
		}
		
	}

}
