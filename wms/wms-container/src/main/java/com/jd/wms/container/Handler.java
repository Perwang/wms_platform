package com.jd.wms.container;

public interface Handler {
	
	public < T > void handle( T t ) throws Exception;

}
