package com.jd.wms.memqueue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityMemoryQueue< T extends Cloneable > extends MemoryQueue {
	public PriorityMemoryQueue( String name, int capacity ) {
		super( name, capacity );
	}

	protected void init() {
		this.queue = new PriorityBlockingQueue< T >( this.capacity );
	}
}