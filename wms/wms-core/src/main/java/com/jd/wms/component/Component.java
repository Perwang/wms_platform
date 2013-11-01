/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.component;


/**
 * 系统组件类
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class Component {
	
	private String name;
	
	private String version;
	
	private int level;
	
	private ClassLoader classLoader;
	
	private String filePath;
	
	private String activatorClass;
	
	private GenericActivator activator;
	
	public Component( String name, String version ) {
		this.name = name;
		this.version = version;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion( String version ) {
		if ( version == null || "".equals( version ) ) {
			version = "0.0.1";
		}
		this.version = version;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel( int level ) {
		this.level = level;
	}

	/**
	 * @return the classLoader
	 */
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * @param classLoader the classLoader to set
	 */
	public void setClassLoader( ClassLoader classLoader ) {
		this.classLoader = classLoader;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath( String filePath ) {
		this.filePath = filePath;
	}

	/**
	 * @return the activatorClass
	 */
	public String getActivatorClass() {
		return activatorClass;
	}

	/**
	 * @param activatorClass the activatorClass to set
	 */
	public void setActivatorClass( String activatorClass ) {
		this.activatorClass = activatorClass;
	}

	public String getId() {
		return new StringBuilder( name ).append( "-" ).append( version ).toString();
	}

	/**
	 * @return the activator
	 */
	public GenericActivator getActivator() throws Exception {
		if ( this.activatorClass != null ) {
			this.activator = ( GenericActivator ) Class.forName( this.activatorClass, true, this.classLoader ).newInstance();
		}
		return this.activator;
	}

}
