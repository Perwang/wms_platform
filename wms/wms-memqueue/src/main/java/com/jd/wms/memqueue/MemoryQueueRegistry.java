package com.jd.wms.memqueue;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MemoryQueueRegistry {
	private static volatile MemoryQueueRegistry _instance;
	private Map< String, MemoryQueue > registry;

	private MemoryQueueRegistry() {
		this.registry = new ConcurrentSkipListMap< String, MemoryQueue >();
	}

	public static MemoryQueueRegistry getInstance() {
		if ( _instance == null ) {
			synchronized ( MemoryQueueRegistry.class ) {
				if ( _instance == null ) {
					_instance = new MemoryQueueRegistry();
				}
			}
		}
		return _instance;
	}

	public void register( String key, MemoryQueue queue )
			throws QueueExistException {
		if ( this.registry.containsKey( key ) ) {
			throw new QueueExistException( "The queue named [" + key
					+ "] has been exist." );
		}
		this.registry.put( key, queue );
	}

	public MemoryQueue remove( String key ) throws NoSuchQueueException {
		if ( !this.registry.containsKey( key ) ) {
			throw new NoSuchQueueException( "No such queue named [" + key
					+ "]." );
		}
		return ( MemoryQueue ) this.registry.remove( key );
	}

	public MemoryQueue get( String name ) {
		return ( MemoryQueue ) this.registry.get( name );
	}
}