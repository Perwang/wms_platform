package com.jd.wms.servicebus.protocol;

import com.jd.wms.servicebus.protocol.config.ProtocolConfig;

/**
 * 通讯链接接口
 * <p>
 * 定义运行时通讯处理类操作行为
 * </p>
 * @author pluto.bing.liu
 *
 */
public interface Connection {
	/**
	 * 查询停止状态
	 * @return true已停止；false未停止
	 */
	public boolean isShutdown();
	/**
	 * 查询运行状态
	 * @return true已运行；false未运行
	 */
	public boolean isStartup();
	/**
	 * 数据接收
	 * <p>
	 * 由于Server端处理接收数据通常是使用线程，因此该方法在作为Server运行的通讯实现类中可以为空方法。
	 * </p>
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public < R > R receive() throws Exception;
	/**
	 * 数据发送
	 * <p>
	 * 在同步链接时，该方法可以包含处理同步返回数据的代码
	 * </p>
	 */
	public < T, R > R send( T t ) throws Exception;
	/**
	 * 启动
	 * @throws Exception
	 */
	public void start() throws Exception;
	/**
	 * 停止
	 * @throws Exception
	 */
	public void stop() throws Exception;
	/**
	 * 获取当前链接配置
	 * @return
	 */
	public < T extends ProtocolConfig > T getConfig();
	
	/**
	 * 设置当前链接配置
	 * @param config
	 */
	public < T extends ProtocolConfig > void setConfig( T t );
	
}
