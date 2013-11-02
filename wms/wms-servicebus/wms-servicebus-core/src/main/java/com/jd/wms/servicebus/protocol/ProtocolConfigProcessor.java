/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.io.InputStream;
import java.io.OutputStream;

import com.jd.wms.servicebus.protocol.config.ProtocolConfig;

/**
 * 协议配置处理器接口
 * <p>
 * 定义用于协议配置的读写方法
 * </p>
 * @author liubing
 * Date Nov 1, 2013
 */
public interface ProtocolConfigProcessor {
	
	/**
	 * 读取配置
	 * <p>将配置输入流读取成协议配置对象</p>
	 * @param is 配置对应输入流
	 * @param 对应的配置对象
	 * @throws Exception
	 */
	public void read( InputStream is, ProtocolConfig config ) throws Exception;
	
	/**
	 * 写入配置
	 * <p>将配置对象内容写入对应的输出流</p>
	 * @param config 配置对象
	 * @param 对应的输出流
	 * @throws Exception
	 */
	public void write( ProtocolConfig config, OutputStream os ) throws Exception;

}
