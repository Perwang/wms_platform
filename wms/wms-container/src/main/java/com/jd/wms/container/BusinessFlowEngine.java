/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.container;

/**
 *	业务流程引擎接口
 * <p></p>
 * @author liubing
 * Date Oct 14, 2013
 */
public interface BusinessFlowEngine< T, R > {
	
	/**
	 * 同步调用
	 * @param t
	 * @return
	 */
	public R syncSend( T t );
	
	/**
	 * 异步调用
	 * @param t
	 */
	public void asyncSend( T t );
	
}
