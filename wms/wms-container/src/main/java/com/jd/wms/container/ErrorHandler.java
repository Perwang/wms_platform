package com.jd.wms.container;

public interface ErrorHandler {
	
	public < T > ErrorResult< ? > handle( Handler curPoint, T t, Exception e );

}
