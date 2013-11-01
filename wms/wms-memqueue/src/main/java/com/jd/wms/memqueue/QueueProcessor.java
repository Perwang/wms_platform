package com.jd.wms.memqueue;

public class QueueProcessor extends AbstractProcessor {
	public QueueProcessor( MemoryQueue queue ) {
		super( queue );
	}

	public < T > boolean offer( T t ) throws Exception {
		boolean success = false;
		if ( this.queue.isActive() ) {
			this.queue.getQueue().offer( t );
		}
		return success;
	}

	@SuppressWarnings( "unchecked" )
	public < T > T take() throws Exception {
		return ( T ) this.queue.getQueue().take();
	}
}