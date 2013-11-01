package com.jd.wms.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 应答配置抽象基类
 * <p>
 * 所有协议的应答配置都继承该类
 * </p>
 * @author pluto.bing.liu
 *
 */
@XmlRootElement( name = "response" )
public abstract class ResponseConfig extends AbstractAttributeDefinition {

}
