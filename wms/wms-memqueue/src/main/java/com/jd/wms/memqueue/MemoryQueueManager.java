package com.jd.wms.memqueue;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.jd.wms.memqueue.model.ConfigurationDefinition;
import com.jd.wms.memqueue.model.QueueDefinition;

public class MemoryQueueManager {
	private static volatile MemoryQueueManager _instance;

	public static MemoryQueueManager getInstance() {
		if ( _instance == null ) {
			synchronized ( MemoryQueueManager.class ) {
				if ( _instance == null ) {
					_instance = new MemoryQueueManager();
					_instance.init();
				}
			}
		}
		return _instance;
	}

	private void init() {
		try {
			JAXBContext context = JAXBContext
					.newInstance( ConfigurationDefinition.class.getPackage()
							.getName() );

			ConfigurationDefinition configuration = ( ConfigurationDefinition ) context
					.createUnmarshaller().unmarshal(
							getClass().getClassLoader().getResource(
									"memqueue/memqueue.xml" ) );

			if ( configuration != null ) {
				for ( QueueDefinition queue : configuration.getQueues() )
					if ( queue.isPriority() )
						createPriorityQueue( queue.getName(),
								queue.getCapacity() );
					else
						creatQueue( queue.getName(), queue.getCapacity() );
			}
		} catch ( JAXBException e ) {
			e.printStackTrace();
		}
	}

	public < T > boolean creatQueue( String name, int capacity ) {
		boolean success = false;
		try {
			MemoryQueue queue = new DefaultMemoryQueue< T >( name, capacity );
			queue.start();
			MemoryQueueRegistry.getInstance().register( name, queue );
			success = true;
		} catch ( QueueExistException e ) {
			e.printStackTrace();
		}
		return success;
	}

	public < T extends Cloneable > boolean createPriorityQueue( String name,
			int capacity ) {
		boolean success = false;
		try {
			MemoryQueue queue = new PriorityMemoryQueue< T >( name, capacity );
			queue.start();
			MemoryQueueRegistry.getInstance().register( name, queue );
			success = true;
		} catch ( QueueExistException e ) {
			e.printStackTrace();
		}
		return success;
	}

	public boolean destroyQueue( String name ) {
		boolean success = false;
		try {
			MemoryQueue queue = MemoryQueueRegistry.getInstance().remove( name );
			queue.stop();
			success = true;
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return success;
	}

	public MemoryQueue lookup( String name ) {
		return MemoryQueueRegistry.getInstance().get( name );
	}
}