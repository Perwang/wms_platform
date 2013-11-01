package com.jd.wms.resource.impl;

import java.io.File;

import com.jd.wms.resource.ResourceFilter;

public abstract class AbstractFileFilter implements ResourceFilter {

	public < T, R > R doFilter( T t ) throws Exception {
		if ( ! ( t instanceof File ) ) {
			throw new IllegalArgumentException( "The argument must be an instance of java.io.File." );
		}
		final File file = ( File ) t;
		return filter( file );
	}
	
	protected abstract < R > R filter( File file ) throws Exception;
	
	public abstract < T > T getResult();

}
