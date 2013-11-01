/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import com.jd.wms.Env;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class Log4jMBean implements LogMBean {
	
	public void init() {
		final String filePath = Env.getInstance().getConfPath().concat( "log4j.properties" );
		final File file = new File( filePath );
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( file );
			Properties properties = new Properties();
			properties.load( fis );
			PropertyConfigurator.configure( properties );
		} catch ( FileNotFoundException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( fis != null ) {
				try {
					fis.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
				fis = null;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.log.LogMBean#getCurrentLoggers()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T > Enumeration< T > getCurrentLoggers() {
		return LogManager.getCurrentLoggers();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jd.wms.log.LogMBean#setLevel(java.lang.String, int)
	 */
	@Override
	public void setLevel( String name, int l ) {
		LogManager.getLogger( name ).setLevel( Level.toLevel( l ) );
	}

}
