package com.jd.wms.memqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MemoryQueue {
	protected final String name;
	protected int capacity;
	protected BlockingQueue< ? > queue;
	protected AtomicBoolean isActive = new AtomicBoolean( false );
	protected volatile Processor processor;

	public MemoryQueue( String name, int capacity ) {
		this.name = name;
		this.capacity = capacity;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity( int capacity ) {
		this.capacity = capacity;
	}

	public String getName() {
		return this.name;
	}

	public void start() {
		if ( !this.isActive.get() ) {
			init();
			this.isActive.set( true );
		}
	}

	protected abstract void init();

	public void stop() {
		if ( this.isActive.get() ) {
			this.isActive.set( false );
			this.queue.clear();
			this.queue = null;
		}
	}

	public boolean isActive() {
		return this.isActive.get();
	}

	@SuppressWarnings( "unchecked" )
	public < T > BlockingQueue< T > getQueue() {
		return ( BlockingQueue< T > ) this.queue;
	}

	public Processor getProcessor() {
		if ( this.processor == null ) {
			synchronized ( this ) {
				if ( this.processor == null ) {
					this.processor = new QueueProcessor( this );
				}
			}
		}
		return this.processor;
	}
}