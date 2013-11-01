package com.jd.wms.servicebus.protocol;

/**
 * 无链接异常
 * <p>
 * 该类继承<CODE>Exception</CODE>
 * </p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class NoConnectionException extends Exception {

	private static final long serialVersionUID = 8032394852033419579L;
	
	public NoConnectionException( String message ) {
		super( message );
	}

}
