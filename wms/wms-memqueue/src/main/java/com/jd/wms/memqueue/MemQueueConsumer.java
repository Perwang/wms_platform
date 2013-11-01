package com.jd.wms.memqueue;

public class MemQueueConsumer {
	private MemoryQueue queue;

	public MemQueueConsumer( String name ) {
		this.queue = MemoryQueueManager.getInstance().lookup( name );
	}

	@SuppressWarnings( "unchecked" )
	public < T > T consume() {
		Object t = null;
		try {
			t = this.queue.getProcessor().take();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return ( T ) t;
	}
}