package com.jd.wms.servicebus.protocol.config;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 请求配置抽象基类
 * <p>
 * 所有不同协议的请求配置都继承该类
 * </p>
 * @author pluto.bing.liu
 *
 */
@XmlRootElement( name = "request" )
public abstract class RequestConfig extends AbstractAttributeDefinition {
	
	protected List< ParamConfig > params = new LinkedList< ParamConfig >();

	public List< ParamConfig > getParams() {
		return params;
	}

	public void setParams( List< ParamConfig > params ) {
		this.params = params;
	}

}
