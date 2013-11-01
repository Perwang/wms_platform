/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.context;

import java.io.Serializable;
import java.util.Map;

/**
 * 数据上下文接口
 * <p>
 * 定义上下文所含方法。
 * </p>
 * @author liubing
 * Date Oct 24, 2013
 */
public interface DataContext extends Serializable {
	
	/**
	 * 获得Context的唯一标识号（ID）
	 * @return
	 */
	public String getId();

	/**
	 * 获取是否无效状态
	 * @return
	 */
	public boolean isInvalid();
	
	/**
	 * 设置上下文为无效状态
	 */
	public void setInvalid( boolean invalid );
	
	/**
	 * 当前获取Context全部属性
	 * @return
	 */
	public Map< String, Object > getAttributes();
	/**
	 * 向Context中添加属性
	 * <p>
	 * 添加属性时，相同Key制定的value兼备新的value所覆盖。不允许空键（Null）。
	 * </>
	 * @param key
	 * @param value
	 */
	public void addAttribute( String key, Object value );
	
	/**
	 * 按Key获取Context.attribute中保存的对应值
	 * @param key
	 * @return
	 */
	public Object getAttribute( String key );
	
	/**
	 * 获取当前Context的有效载荷
	 * @return
	 */
	public < T > T getPayload();
	/**
	 * 获取设置当前Context的有效载荷
	 * @param t
	 */
	public < T > void setPayload( T t );
	
	/**
	 * 设置异常
	 * @param e
	 */
	public void setException( Exception e );
	
	/**
	 * 获得异常
	 */
	public Exception getException();
}
