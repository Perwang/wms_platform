package com.jd.wms.resource;

public interface ResourceScanner {
	
	public < R > void scan( R resource, ResourceFilter filter );

}
