package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * 通用配置类
 * @author pluto.bing.liu
 *
 */
public class CommonsConfig extends AbstractAttributeDefinition {
	
	@XmlAttribute
	protected String charset;

	public String getCharset() {
		return charset;
	}

	public void setCharset( String charset ) {
		this.charset = charset;
	}
	
}
