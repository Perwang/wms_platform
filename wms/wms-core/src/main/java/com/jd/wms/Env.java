/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * <p>
 * </p>
 * 
 * @author liubing Date Oct 28, 2013
 */
public class Env {
	
	private static final Env INSTANCE = new Env();
	
	private AtomicBoolean initialized = new AtomicBoolean( false );

	private String installPath;

	private String confPath;

	private String libPath;

	private String logPath;

	private Env() {
	}
	
	public static Env getInstance() {
		return INSTANCE;
	}
	
	private Properties properties = new Properties();
	
	private void initProperties() {
		final File file = new File( this.confPath + "env.properties" );
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( file );
			properties.load( fis );
		} catch ( Exception e ) {
			e.getMessage();
			throw new RuntimeException( e );
		} finally {
			if ( fis != null ) {
				try {
					fis.close();
					fis = null;
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Properties getProperties() {
		return this.properties;
	}

	/**
	 * @param installPath
	 */
	public void init( String installPath ) {
		if ( initialized.get() ) {
			return ;
		}
		this.installPath = installPath;
		this.confPath = new StringBuilder( this.installPath ).append( 
				File.separator ).append( "conf" ).append( File.separator ).toString();
		this.logPath = new StringBuilder( this.installPath ).append( 
				File.separator ).append( "logs" ).append( File.separator ).toString();
		initProperties();
		initialized.set( true );
	}

	/**
	 * @return the installPath
	 */
	public String getInstallPath() {
		return installPath;
	}

	/**
	 * @return the confPath
	 */
	public String getConfPath() {
		return confPath;
	}

	/**
	 * @return the libPath
	 */
	public String getLibPath() {
		return libPath;
	}

	/**
	 * @return the logPath
	 */
	public String getLogPath() {
		return logPath;
	}
	
}
