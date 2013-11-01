package com.jd.wms.memqueue;

import java.util.concurrent.LinkedBlockingQueue;

public class DefaultMemoryQueue< T > extends MemoryQueue {
	public DefaultMemoryQueue( String name, int capacity ) {
		super( name, capacity );
	}

	protected void init() {
		this.queue = new LinkedBlockingQueue< T >( getCapacity() );
	}
}
