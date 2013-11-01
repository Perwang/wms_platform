package com.jd.wms.memqueue;

public abstract class AbstractProcessor implements Processor {
	
	protected MemoryQueue queue;

	protected AbstractProcessor( MemoryQueue queue ) {
		this.queue = queue;
	}
}
