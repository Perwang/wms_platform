/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol.plugin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.jd.wms.servicebus.protocol.ProtocolConfigProcessor;

/**
 * 通讯协议插件类
 * <p>
 * 用于封装协议插件的相关操作类
 * </p>
 * @author liubing
 * Date Nov 1, 2013
 */
@XmlRootElement( name = "plugin" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProtocolPlugin {
	@XmlAttribute( required = true )
	private String protocol;
	@XmlElement( name = "config-processor", required = true )
	private String processorClazz;
	@XmlTransient
	private volatile ProtocolConfigProcessor configProcessor;
	
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol( String protocol ) {
		this.protocol = protocol;
	}
	
	/**
	 * @return the processorClazz
	 */
	public String getProcessorClazz() {
		return processorClazz;
	}

	/**
	 * @param processorClazz the processorClazz to set
	 */
	public void setProcessorClazz( String processorClazz ) {
		this.processorClazz = processorClazz;
	}

	public ProtocolConfigProcessor getConfigProcessor() throws Exception {
		if ( configProcessor == null ) {
			synchronized ( this ) {
				if ( configProcessor == null ) {
					Class< ? > clazz =  Class.forName( this.processorClazz );
					configProcessor = ( ProtocolConfigProcessor ) clazz.newInstance();
				}
			}
		}
		return configProcessor;
	}

}
