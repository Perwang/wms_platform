package com.jd.wms.memqueue;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemQueueProducer {
	private static Logger LOG = LoggerFactory
			.getLogger( MemQueueProducer.class );
	private MemoryQueue queue;

	public MemQueueProducer( String name ) {
		this.queue = MemoryQueueManager.getInstance().lookup( name );
	}

	public < T > void produce( T t ) {
		try {
			this.queue.getProcessor().offer( t );
		} catch ( Exception e ) {
			LOG.error( e.getMessage(), e );
		}
	}

	public < T > void batchProduce( List< T > list ) {
		try {
			this.queue.getQueue().addAll( list );
		} catch ( Exception e ) {
			LOG.error( e.getMessage(), e );
		}
	}
}