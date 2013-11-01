/**
 * 
 */
package com.jd.wms.container;

import com.jd.wms.context.DataContext;

/**
 * 业务异常类
 * <p>
 * 
 * </p>
 * @author bjyfliubing
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 4107987912564998303L;
	
	private DataContext currentContext;
	
	public BusinessException( DataContext context ) {
		this.currentContext = context;
	}

	/**
	 * @return the currentContext
	 */
	@SuppressWarnings( "unchecked" )
	public < T > T getCurrentContext() {
		return ( T ) currentContext;
	}

}
