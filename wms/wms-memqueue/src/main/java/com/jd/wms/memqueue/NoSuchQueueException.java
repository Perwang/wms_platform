package com.jd.wms.memqueue;

public class NoSuchQueueException extends Exception {
	private static final long serialVersionUID = -8167208884271081541L;

	public NoSuchQueueException( String message ) {
		super( message );
	}
}